package br.com.caelum.casadocodigoapi.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private Book book;
	
	private BigDecimal price = BigDecimal.ZERO;
	
	@Enumerated(EnumType.STRING)
	private ProductKind kind;

	/**
	 * @deprecated hibernate only
	 */
	public Product() {
	}
    
	public Product(Book book, BigDecimal price, ProductKind kind) {
		this.book = book;
		this.price = price;
		this.kind = kind;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public BigDecimal getPrice() {
		return price.setScale(2, RoundingMode.HALF_UP);
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
	public String getTitle() {
		return this.book.getTitle();
	}
	public String getCoverUrl() {
		return this.book.getCoverUrl();
	}

	public String getCategory() {
		return this.book.getCategoryTitle();
	}

	public String getAuthor() {
		return this.book.getAuthorName();
	}

	public String getDescription() {
		return this.book.getDescription();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}


}
