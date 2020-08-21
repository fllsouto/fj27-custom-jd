package br.com.caelum.casadocodigoapi.controller.dto.output;

import java.util.List;
import java.util.stream.Collectors;

import br.com.caelum.casadocodigoapi.model.Category;

public class CategoryOutputDto {

	private Long id;

	private String title;

	private String description;

	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}
	
	public CategoryOutputDto (Category category) {
		this.id = category.getId();
		this.title = category.getTitle();
		this.description = category.getDescription();
	}

	public static List<CategoryOutputDto> build(List<Category> categories) {
		return categories.stream().map(CategoryOutputDto::new).collect(Collectors.toList());
	}
}
