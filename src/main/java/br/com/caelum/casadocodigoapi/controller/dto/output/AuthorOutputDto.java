package br.com.caelum.casadocodigoapi.controller.dto.output;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotBlank;

import br.com.caelum.casadocodigoapi.model.Author;
import br.com.caelum.casadocodigoapi.model.Category;

public class AuthorOutputDto {

	private Long id;
	private String name;
	private String email;
	private String avatarUrl;
	private String description;

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getAvatarUrl() {
		return avatarUrl;
	}

	public String getDescription() {
		return description;
	}

	public AuthorOutputDto(Author author) {
		this.id = author.getId();
		this.name = author.getName();
		this.email = author.getEmail();
		this.avatarUrl = author.getAvatarUrl();
		this.description = author.getDescription();
	}

	public static List<AuthorOutputDto> build(List<Author> authors) {
		return authors.stream().map(AuthorOutputDto::new).collect(Collectors.toList());
	}
}