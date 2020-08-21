package br.com.caelum.casadocodigoapi.controller.dto.output;

import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import br.com.caelum.casadocodigoapi.model.Book;

public class BookOutputDto {

	private Long id;
	private String title;
	private Date releaseDate;
	private Long pages;
	private Long authorId;
	private Long categoryId;
	private String coverUrl;
	private String description;

	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public Long getPages() {
		return pages;
	}

	public Long getAuthorId() {
		return authorId;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public String getCoverUrl() {
		return coverUrl;
	}

	public String getDescription() {
		return description;
	}

	public BookOutputDto(Book book) {
		this.id = book.getId();
		this.title = book.getTitle();
		this.releaseDate = Date.from(book.getReleaseDate().atStartOfDay(ZoneId.systemDefault()).toInstant());
		this.pages = book.getPages();
		this.authorId = book.getAuthor().getId();
		this.categoryId = book.getCategory().getId();
		this.coverUrl = book.getCoverUrl();
		this.description = book.getDescription();
	}

	public static List<BookOutputDto> build(List<Book> books) {
		return books.stream().map(BookOutputDto::new).collect(Collectors.toList());
	}
}
