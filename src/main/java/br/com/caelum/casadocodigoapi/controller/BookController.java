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

import br.com.caelum.casadocodigoapi.controller.dto.input.BookInputDto;
import br.com.caelum.casadocodigoapi.controller.dto.output.BookOutputDto;
import br.com.caelum.casadocodigoapi.model.Book;
import br.com.caelum.casadocodigoapi.repository.AuthorRepository;
import br.com.caelum.casadocodigoapi.repository.BookRepository;
import br.com.caelum.casadocodigoapi.repository.CategoryRepository;

@RestController
@RequestMapping("/api/admin/books")
public class BookController {

	private BookRepository bookRepository;

	private AuthorRepository authorRepository;

	private CategoryRepository categoryRepository;

	@Autowired
	public BookController(BookRepository bookRepository, AuthorRepository authorRepository,
			CategoryRepository categoryRepository) {
		this.bookRepository = bookRepository;
		this.authorRepository = authorRepository;
		this.categoryRepository = categoryRepository;
	}

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<BookOutputDto> list() {
		List<Book> categories = bookRepository.findAll();
		return BookOutputDto.build(categories);
	}

	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public BookOutputDto show(@PathVariable("id") Long bookId) {
		Book book = bookRepository.findById(bookId).orElseThrow(
				() -> new IllegalArgumentException(String.format("The Book with id %d wasn't found!", bookId)));
		return new BookOutputDto(book);
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BookOutputDto> create(@Valid @RequestBody BookInputDto dto, UriComponentsBuilder uriBuilder) {
		Book book = dto.build(authorRepository, categoryRepository);
		book = bookRepository.save(book);

		URI path = uriBuilder.path("/api/admin/books/{id}").buildAndExpand(book.getId()).toUri();
		return ResponseEntity.created(path).body(new BookOutputDto(book));
	}

	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BookOutputDto> update(@Valid @RequestBody BookInputDto dto) {
		Book book = dto.build(authorRepository, categoryRepository);
		book = bookRepository.save(book);

		return ResponseEntity.ok().body(new BookOutputDto(book));
	}
}
