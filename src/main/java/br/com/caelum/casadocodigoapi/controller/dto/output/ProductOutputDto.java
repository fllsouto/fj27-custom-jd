package br.com.caelum.casadocodigoapi.controller.dto.output;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import br.com.caelum.casadocodigoapi.model.Product;
import br.com.caelum.casadocodigoapi.model.ProductKind;

public class ProductOutputDto {

	private Long id;
	private Long bookId;
	private BigDecimal price = BigDecimal.ZERO;
	private ProductKind kind;

	public Long getId() {
		return id;
	}

	public Long getBookId() {
		return bookId;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public ProductKind getKind() {
		return kind;
	}

	public ProductOutputDto(Product product) {
		this.id = product.getId();
		this.bookId = product.getBook().getId();
		this.price = product.getPrice();
		this.kind = product.getKind();
	}

	public static List<ProductOutputDto> build(List<Product> products) {
		return products.stream().map(ProductOutputDto::new).collect(Collectors.toList());
	}
}
