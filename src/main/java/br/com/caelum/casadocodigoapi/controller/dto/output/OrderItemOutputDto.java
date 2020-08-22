package br.com.caelum.casadocodigoapi.controller.dto.output;

import java.math.BigDecimal;

import br.com.caelum.casadocodigoapi.model.OrderItem;
import br.com.caelum.casadocodigoapi.model.Product;
import br.com.caelum.casadocodigoapi.repository.ProductRepository;

public class OrderItemOutputDto {

	private Long id;
	private Long productId;
	private Integer amount;
	private BigDecimal itemPrice;
	
	

	public OrderItemOutputDto(Long id, Long productId, Integer amount, BigDecimal itemPrice) {
		this.id = id;
		this.productId = productId;
		this.amount = amount;
		this.itemPrice = itemPrice;
	}

	public Long getId() {
		return id;
	}

	public Long getProductId() {
		return productId;
	}

	public Integer getAmount() {
		return amount;
	}

	public BigDecimal getItemPrice() {
		return itemPrice;
	}

	public static OrderItemOutputDto build(OrderItem item) {
		return new OrderItemOutputDto(item.getId(), item.getProduct().getId(), 
				item.getAmount(), item.getItemPrice());
	}

}
