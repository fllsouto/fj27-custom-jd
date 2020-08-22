package br.com.caelum.casadocodigoapi.controller.dto.output;

import java.math.BigDecimal;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import br.com.caelum.casadocodigoapi.model.Book;
import br.com.caelum.casadocodigoapi.model.CheckoutOrder;

public class CheckoutOrderOutputDto {

	private Long id;
	private BigDecimal total;
	private Date creationDate;
	private Set<OrderItemOutputDto> orderItems = new HashSet<OrderItemOutputDto>();

	public Long getId() {
		return id;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public Set<OrderItemOutputDto> getOrderItems() {
		return orderItems;
	}

	public CheckoutOrderOutputDto(CheckoutOrder order) {
		this.id = order.getId();
		this.creationDate = Date.from(order.getCreationDate().atStartOfDay(ZoneId.systemDefault()).toInstant());
		this.total = order.getTotal();
		this.orderItems.addAll(getOrders(order));
	}

	private Set<OrderItemOutputDto> getOrders(CheckoutOrder order) {
		return order.getOrderItems().stream().map(OrderItemOutputDto::build)
				.collect(Collectors.toSet());
	}

	public static List<CheckoutOrderOutputDto> build(List<CheckoutOrder> orders) {
		return orders.stream().map(CheckoutOrderOutputDto::new).collect(Collectors.toList());
	}

}
