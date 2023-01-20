package org.sfw.micro.ecommerce.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.sfw.micro.ecommerce.order.model.Order;
import org.sfw.micro.ecommerce.order.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class OrderService {
	int orderid;
	
	@Autowired
	RestTemplate restTemplate;
	String BASE_URI = "http://localhost:7002/cart-service";
	private List<Product> productList = new ArrayList<>();

	@HystrixCommand(fallbackMethod = "fallbackCheckout")
	public List<Product> checkOutServ(String category, String brand) {
		System.out.println("Oredr Service checkout");
		System.out.println(category + brand);
		String uri = BASE_URI + "/add-many-to-cart/" + category + "/" + brand;
		System.out.println(uri);
		List<LinkedHashMap<String, Object>> response = restTemplate.getForObject(uri, List.class);
		System.out.println("afgter claling get for tObjecr");
		for (LinkedHashMap<String, Object> productMap : response) {
			Integer pID = (Integer) productMap.get("productID");
			String name = (String) productMap.get("name");
			String brnd = (String) productMap.get("brand");
			String categry = (String) productMap.get("category");
			Double price = (Double) productMap.get("price");
			Product product = new Product(pID, name, brnd, categry, price);
			productList.add(product);
		}
		System.out.println(productList);
		return productList;
	}

	public List<Product> fallbackCheckout(String category, String brand) {
		System.out.println("Hystrix fallbacl method");
		return new ArrayList<>();
	}

	public Order placeOrderServ(String streetname, String city) {
		double total = 0;
		for (Product product : productList) {
			total += product.getProductID();
		}

		Order order = new Order(++orderid, productList, total, streetname, city);
		return order;
	}

}
