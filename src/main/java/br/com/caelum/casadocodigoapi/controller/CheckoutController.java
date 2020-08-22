package br.com.caelum.casadocodigoapi.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.caelum.casadocodigoapi.controller.dto.input.CheckoutOrderInputDto;
import br.com.caelum.casadocodigoapi.controller.dto.output.CheckoutOrderOutputDto;
import br.com.caelum.casadocodigoapi.model.CheckoutOrder;
import br.com.caelum.casadocodigoapi.repository.CheckoutOrderRepository;
import br.com.caelum.casadocodigoapi.repository.ProductRepository;

@RestController
@RequestMapping("/api/checkout")
public class CheckoutController {

	private ProductRepository productRepository;
	
	private CheckoutOrderRepository orderRepository;

	@Autowired
	public CheckoutController(ProductRepository productRepository, CheckoutOrderRepository orderRepository) {
		this.productRepository = productRepository;
		this.orderRepository = orderRepository;
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CheckoutOrderOutputDto> checkout(@Valid @RequestBody CheckoutOrderInputDto dto, 
			UriComponentsBuilder uriBuilder) {
		CheckoutOrder order = dto.build(productRepository);
		order = orderRepository.save(order);
		

		URI path = uriBuilder.path("/api/admin/order/{id}").buildAndExpand(order.getId()).toUri();
		return ResponseEntity.created(path).body(new CheckoutOrderOutputDto(order));
		
	}
}
