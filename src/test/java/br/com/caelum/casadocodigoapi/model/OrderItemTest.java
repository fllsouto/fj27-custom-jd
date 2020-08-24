package br.com.caelum.casadocodigoapi.model;

import	static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

import org.junit.jupiter.api.Test;

class OrderItemTest {

	@Test
	void oItemPriceDeveraSerIgualAoProductPriceVezesAmount() {
		String title = "Algoritmos em Java";
		Date releaseDate = new Date();
		Long pages = 200l;
		Author author = new Author("Guilherme Silveira", "guilherme@gmail.com", "", "");
		Category category = new Category("Backend", "");
		String coverUrl = "";
		String description = "";
		Book book = new Book(title, releaseDate, pages, author, category, coverUrl, description);
		BigDecimal productPrice = BigDecimal.valueOf(25.50);
		ProductKind kind = ProductKind.COMBO;
		BigDecimal expectedResult = BigDecimal.valueOf(102.00).setScale(2, RoundingMode.HALF_UP);
		
		Product product = new Product(book, productPrice, kind );
		Integer amount = 4;
		OrderItem actualResult = new OrderItem(product, amount);
		
		assertThat(actualResult.getItemPrice()).isEqualTo(expectedResult);
	}

}
