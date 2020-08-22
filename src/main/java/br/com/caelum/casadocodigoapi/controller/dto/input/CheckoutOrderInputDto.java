package br.com.caelum.casadocodigoapi.controller.dto.input;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import br.com.caelum.casadocodigoapi.model.CheckoutOrder;
import br.com.caelum.casadocodigoapi.model.OrderItem;
import br.com.caelum.casadocodigoapi.repository.ProductRepository;

public class CheckoutOrderInputDto {

	private List<OrderItemInputDto> orderItems = new ArrayList<OrderItemInputDto>();

	public List<OrderItemInputDto> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItemInputDto> orderItems) {
		this.orderItems = orderItems;
	}
	
	public CheckoutOrder build(ProductRepository productRepository) {
		Set<OrderItem> orderItems = this.orderItems.stream()
				.map(orderItem -> orderItem.build(productRepository))
				.collect(Collectors.toSet());
		return new CheckoutOrder(orderItems);
	}
	
	
}
