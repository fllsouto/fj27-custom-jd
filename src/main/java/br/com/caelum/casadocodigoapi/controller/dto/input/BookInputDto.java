package br.com.caelum.casadocodigoapi.controller.dto.input;

import java.time.LocalDate;
import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;

import org.springframework.format.annotation.DateTimeFormat;

import br.com.caelum.casadocodigoapi.model.Author;
import br.com.caelum.casadocodigoapi.model.Book;
import br.com.caelum.casadocodigoapi.model.Category;
import br.com.caelum.casadocodigoapi.repository.AuthorRepository;
import br.com.caelum.casadocodigoapi.repository.CategoryRepository;

public class BookInputDto {

	private Long id;

	@NotBlank
	private String title;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@PastOrPresent(message = "A data de lançamento deve ser anterior ou igual ao dia de hoje")
	private Date releaseDate;

	@Positive(message = "O número de páginas deve ser positivo")
	private Long pages;

	@NotNull(message = "O autor selecionado é inválido")
	private Long authorId;

	@NotNull(message = "A categoria selecionada é inválido")
	private Long categoryId;
	private String coverUrl;
	private String description;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public Long getPages() {
		return pages;
	}

	public void setPages(Long pages) {
		this.pages = pages;
	}

	public Long getAuthorId() {
		return authorId;
	}

	public void setAuthorId(Long authorId) {
		this.authorId = authorId;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public String getCoverUrl() {
		return coverUrl;
	}

	public void setCoverUrl(String coverUrl) {
		this.coverUrl = coverUrl;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Book build(AuthorRepository authorRepository, CategoryRepository categoryRepository) {
		
		Author author = authorRepository.findById(authorId).orElseThrow(() ->
    		new IllegalArgumentException(String
    			.format("The Author with id %d wasn't found!", authorId))
		);
		Category category = categoryRepository.findById(categoryId).orElseThrow(() ->
	    	new IllegalArgumentException(String
	    			.format("The Category with id %d wasn't found!", categoryId))
		);
		Book book = new Book(title, releaseDate, pages, author, category, coverUrl, description);
		book.setId(id);
		return book;
	}

}
