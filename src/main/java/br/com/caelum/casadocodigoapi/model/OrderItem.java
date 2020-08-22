package br.com.caelum.casadocodigoapi.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class OrderItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private CheckoutOrder order;
	
	@ManyToOne
	private Product product;
	private Integer amount;
	private BigDecimal itemPrice = BigDecimal.ZERO;
	
    /**
     * @deprecated hibernate only
     */
    public OrderItem() {
	}

	public OrderItem(Product product, Integer amount) {
		this.product = product;
		this.amount = amount;
		this.itemPrice = product.getPrice().multiply(BigDecimal.valueOf(amount))
				.setScale(2, RoundingMode.HALF_UP);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public BigDecimal getItemPrice() {
		return itemPrice.setScale(2, RoundingMode.HALF_UP);
	}

	public void setItemPrice(BigDecimal itemPrice) {
		this.itemPrice = itemPrice;
	}

	public CheckoutOrder getOrder() {
		return order;
	}

	public void setOrder(CheckoutOrder order) {
		this.order = order;
	}
}
