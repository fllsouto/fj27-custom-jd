package br.com.caelum.casadocodigoapi.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.caelum.casadocodigoapi.controller.dto.input.ProductInputDto;
import br.com.caelum.casadocodigoapi.controller.dto.output.ProductOutputDto;
import br.com.caelum.casadocodigoapi.model.Product;
import br.com.caelum.casadocodigoapi.repository.BookRepository;
import br.com.caelum.casadocodigoapi.repository.CategoryRepository;
import br.com.caelum.casadocodigoapi.repository.ProductRepository;

@RestController
@RequestMapping("/api/admin/products")
public class ProductController {

	private ProductRepository productRepository;
	
	private BookRepository bookRepository;

	@Autowired
	public ProductController(ProductRepository productRepository, BookRepository bookRepository,
			CategoryRepository categoryRepository) {
		this.productRepository = productRepository;
		this.bookRepository = bookRepository;
	}

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ProductOutputDto> list() {
		List<Product> categories = productRepository.findAll();
		return ProductOutputDto.build(categories);
	}

	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ProductOutputDto show(@PathVariable("id") Long productId) {
		Product product = productRepository.findById(productId).orElseThrow(
				() -> new IllegalArgumentException(String.format("The Product with id %d wasn't found!", productId)));
		return new ProductOutputDto(product);
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ProductOutputDto> create(@Valid @RequestBody ProductInputDto dto, UriComponentsBuilder uriBuilder) {
		Product product = dto.build(bookRepository);
		product = productRepository.save(product);

		URI path = uriBuilder.path("/api/admin/products/{id}").buildAndExpand(product.getId()).toUri();
		return ResponseEntity.created(path).body(new ProductOutputDto(product));
	}

	@DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ProductOutputDto> delete(@PathVariable("id") Long productId) {
		Product product = productRepository.findById(productId).orElseThrow(
				() -> new IllegalArgumentException(String.format("The Product with id %d wasn't found!", productId)));
		productRepository.delete(product);
		return ResponseEntity.ok().body(new ProductOutputDto(product));
	}
}
