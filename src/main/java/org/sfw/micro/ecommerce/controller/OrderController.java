package org.sfw.micro.ecommerce.controller;

import java.util.List;

import org.sfw.micro.ecommerce.order.model.Order;
import org.sfw.micro.ecommerce.order.model.Product;
import org.sfw.micro.ecommerce.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order-service")
public class OrderController {

	@Autowired
	OrderService orderService;

	@GetMapping("/checkout/{category}/{brand}")
	public List<Product> checkOut(@PathVariable("category") String category, @PathVariable("brand") String brand) {
		System.out.println("Oredr Controller checkout");
		System.out.println(category+brand);
		return orderService.checkOutServ(category, brand);
	}

	@GetMapping("/place-order/{streetname}/{city}")
	public Order placeOrder(@PathVariable("streetname") String streetname, @PathVariable("city") String city) {
		System.out.println("Oredr Controller placeorder");
		System.out.println(streetname+city);
		return orderService.placeOrderServ(streetname, city);
	}

}
