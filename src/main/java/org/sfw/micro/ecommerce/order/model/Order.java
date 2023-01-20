package org.sfw.micro.ecommerce.order.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
	private int orderID;
	private List<Product> productList;
	private double total;
	private String streetName;
	private String city;

}
