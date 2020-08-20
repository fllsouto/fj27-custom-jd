package br.com.caelum.casadocodigoapi.controller.dto.input;

import javax.validation.constraints.NotBlank;

import br.com.caelum.casadocodigoapi.model.Category;

public class CategoryInputDto {

	private Long id;
	
	@NotBlank
	private String title;
	
	@NotBlank
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Category build() {
		Category category = new Category(title, description);
		category.setId(id);
		return category;
	}

	
}
