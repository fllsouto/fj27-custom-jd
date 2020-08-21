package br.com.caelum.casadocodigoapi.model;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

@Entity
public class Book {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;


	private String title;
	private LocalDate releaseDate;
	private Long pages;
	
	@ManyToOne
	private Author author;

	@ManyToOne
	private Category category;
	private String coverUrl = "https://firstfreerockford.org/wp-content/uploads/2018/08/placeholder-book-cover-default.png";

	@Lob
	private String description;
    
    /**
     * @deprecated hibernate only
     */
    public Book() {
	}
    
	public Book(String title, Date releaseDate, Long pages, Author author, Category category,
			String coverUrl, String description) {
		this.title = title;
		this.releaseDate = releaseDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		this.pages = pages;
		this.author = author;
		this.category = category;
		this.coverUrl = coverUrl;
		this.description = description;
	}

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

	public LocalDate getReleaseDate() {
		return releaseDate;
	}
	
	public Date getReleaseDateAsDate() {
		return java.sql.Date.valueOf(releaseDate);
	}

	public void setReleaseDate(LocalDate releaseDate) {
		this.releaseDate = releaseDate;
	}

	public Long getPages() {
		return pages;
	}

	public void setPages(Long pages) {
		this.pages = pages;
	}


	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
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
    
    public String getAuthorName() {
    	return this.author.getName();
    }
    
    public String getCategoryTitle() {
    	return this.category.getTitle();
    }
	
}
