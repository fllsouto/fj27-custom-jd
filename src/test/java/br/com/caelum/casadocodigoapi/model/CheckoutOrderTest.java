package br.com.caelum.casadocodigoapi.model;

import	static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
// import org.springframework.test.context.junit4.SpringRunner;

// Não é necessário
//@RunWith(SpringRunner.class)
public class CheckoutOrderTest {

	
	@Test
	void umCheckoutOrderDeveTerValorIgualAZero() {
		Set<OrderItem> orderItems = Collections.emptySet();
		CheckoutOrder emptyCheckoutOrder = new CheckoutOrder(orderItems);
		
		assertThat(emptyCheckoutOrder.getTotal()).isEqualTo(BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP));
		assertThat(emptyCheckoutOrder.getOrderItems()).isEmpty();
	}
	
	@Test
	void oValorDoCheckoutOrderDeveSerIgualASomaDosSeusOrderItem() {
		HashSet<OrderItem> items = new HashSet<OrderItem>();

		items.add(createOrderItem(2, BigDecimal.valueOf(55.00), ProductKind.COMBO));   // 2 * 55.00 = 110.00
		items.add(createOrderItem(5, BigDecimal.valueOf(20.00), ProductKind.EBOOK));   // 5 * 20.00 = 100.00
		items.add(createOrderItem(4, BigDecimal.valueOf(40.00), ProductKind.PRINTED)); // 4 * 40.00 = 160.00
		
		CheckoutOrder emptyCheckoutOrder = new CheckoutOrder(items);
		
		BigDecimal expectedValue = BigDecimal.valueOf(370.00).setScale(2, RoundingMode.HALF_UP); // 100.00 + 110.00 + 160.00 = 370.00
		
		assertThat(emptyCheckoutOrder.getTotal()).isEqualTo(expectedValue);
	}
	
	private OrderItem createOrderItem(Integer amount, BigDecimal productPrice, ProductKind kind) {
		String title = "Algoritmos em Java";
		Date releaseDate = new Date();
		Long pages = 200l;
		Author author = new Author("Guilherme Silveira", "guilherme@gmail.com", "", "");
		Category category = new Category("Backend", "");
		String coverUrl = "";
		String description = "";
		Book book = new Book(title, releaseDate, pages, author, category, coverUrl, description);
		Product product = new Product(book, productPrice, kind);
		return new OrderItem(product, amount);
	}
}
