package br.com.caelum.casadocodigoapi.controller.dto.input;

import javax.validation.constraints.NotBlank;

import br.com.caelum.casadocodigoapi.model.Author;
import br.com.caelum.casadocodigoapi.model.Category;

public class AuthorInputDto {

	private Long id;

	@NotBlank
	private String name;

	@NotBlank
	private String email;

	private String avatarUrl;

	@NotBlank
	private String description;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAvatarUrl() {
		return avatarUrl;
	}

	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Author build() {
		Author author = new Author(name, email, avatarUrl, description);
		author.setId(id);
		return author;
	}

}
