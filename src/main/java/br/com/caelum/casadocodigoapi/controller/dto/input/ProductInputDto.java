package br.com.caelum.casadocodigoapi.controller.dto.input;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import br.com.caelum.casadocodigoapi.model.Book;
import br.com.caelum.casadocodigoapi.model.Product;
import br.com.caelum.casadocodigoapi.model.ProductKind;
import br.com.caelum.casadocodigoapi.repository.BookRepository;

public class ProductInputDto {

	private Long id;

	@NotNull
	private Long bookId;

	@NotNull
	private BigDecimal price = BigDecimal.ZERO;

	@NotNull
	private ProductKind kind;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getBookId() {
		return bookId;
	}

	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public ProductKind getKind() {
		return kind;
	}

	public void setKind(ProductKind kind) {
		this.kind = kind;
	}

	public Product build(BookRepository bookRepository) {
		Book book = bookRepository.findById(bookId).orElseThrow(
				() -> new IllegalArgumentException(String.format("The Book with id %d wasn't found!", bookId)));
		Product author = new Product(book, price, kind);
		author.setId(id);
		return author;
	}

}
