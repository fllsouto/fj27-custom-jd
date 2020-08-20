package br.com.caelum.casadocodigoapi.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.caelum.casadocodigoapi.controller.dto.input.CategoryInputDto;
import br.com.caelum.casadocodigoapi.controller.dto.output.CategoryOutputDto;
import br.com.caelum.casadocodigoapi.model.Category;
import br.com.caelum.casadocodigoapi.repository.CategoryRepository;

@RestController
@RequestMapping("/api/admin/categories")
public class CategoryController {
	
	private CategoryRepository categoryRepository;
	
	@Autowired
	public CategoryController(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}


	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<CategoryOutputDto> list() {
		List<Category> categories = categoryRepository.findAll();
		return CategoryOutputDto.build(categories);
	}
	
	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public CategoryOutputDto show(@PathVariable("id") Long categoryId) {
		Category category = categoryRepository.findById(categoryId).orElseThrow(() ->
        	new IllegalArgumentException(String
        			.format("The Category with id %d wasn't found!", categoryId))
		);
		return new CategoryOutputDto(category);
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CategoryOutputDto> create(@Valid @RequestBody CategoryInputDto dto,
			UriComponentsBuilder uriBuilder) {
		Category category = dto.build();
		category = categoryRepository.save(category);
		
		URI path = uriBuilder.path("/api/admin/categories/{id}")
				.buildAndExpand(category.getId()).toUri();
		return ResponseEntity.created(path).body(new CategoryOutputDto(category));		
	}
	
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CategoryOutputDto> update(@Valid @RequestBody CategoryInputDto dto) {
		Category category = dto.build();
		category = categoryRepository.save(category);
		
		return ResponseEntity.ok().body(new CategoryOutputDto(category));		
	}

	
}
