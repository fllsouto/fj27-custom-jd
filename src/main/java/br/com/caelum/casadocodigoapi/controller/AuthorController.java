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

import br.com.caelum.casadocodigoapi.controller.dto.input.AuthorInputDto;
import br.com.caelum.casadocodigoapi.controller.dto.output.AuthorOutputDto;
import br.com.caelum.casadocodigoapi.model.Author;
import br.com.caelum.casadocodigoapi.repository.AuthorRepository;

@RestController
@RequestMapping("/api/admin/authors")
public class AuthorController {
	
	private AuthorRepository authorRepository;
	
	@Autowired
	public AuthorController(AuthorRepository authorRepository) {
		this.authorRepository = authorRepository;
	}

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<AuthorOutputDto> list() {
		List<Author> categories = authorRepository.findAll();
		return AuthorOutputDto.build(categories);
	}
	
	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public AuthorOutputDto show(@PathVariable("id") Long authorId) {
		Author author = authorRepository.findById(authorId).orElseThrow(() ->
        	new IllegalArgumentException(String
        			.format("The Author with id %d wasn't found!", authorId))
		);
		return new AuthorOutputDto(author);
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AuthorOutputDto> create(@Valid @RequestBody AuthorInputDto dto,
			UriComponentsBuilder uriBuilder) {
		Author author = dto.build();
		author = authorRepository.save(author);
		
		URI path = uriBuilder.path("/api/admin/authors/{id}")
				.buildAndExpand(author.getId()).toUri();
		return ResponseEntity.created(path).body(new AuthorOutputDto(author));		
	}
	
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AuthorOutputDto> update(@Valid @RequestBody AuthorInputDto dto) {
		Author author = dto.build();
		author = authorRepository.save(author);
		
		return ResponseEntity.ok().body(new AuthorOutputDto(author));		
	}	
}
