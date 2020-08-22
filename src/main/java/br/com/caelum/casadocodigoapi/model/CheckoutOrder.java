package br.com.caelum.casadocodigoapi.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class CheckoutOrder {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToMany(cascade = CascadeType.PERSIST, mappedBy = "order")
	private List<OrderItem> orderItems = new ArrayList<OrderItem>();
	
	private BigDecimal total = BigDecimal.ZERO;
	
	private LocalDate creationDate;
	
    /**
     * @deprecated hibernate only
     */
    public CheckoutOrder() {
	}

	public CheckoutOrder(Set<OrderItem> orderItems) {
		this.total = calculateTotal(orderItems);
		this.creationDate = LocalDate.now();
		orderItems.stream().forEach(this::add);
	}
	
	private BigDecimal calculateTotal(Set<OrderItem> orderItems) {
		return orderItems.stream().map(item -> item.getItemPrice())
				.reduce(BigDecimal.ZERO, (subtotal, element) -> subtotal.add(element));
	}

	public void add(OrderItem orderItem) {
		this.orderItems.add(orderItem);
		orderItem.setOrder(this);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	public BigDecimal getTotal() {
		return total.setScale(2, RoundingMode.HALF_UP);
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public LocalDate getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDate creationDate) {
		this.creationDate = creationDate;
	}
}
