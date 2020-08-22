package br.com.caelum.casadocodigoapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.caelum.casadocodigoapi.controller.dto.output.CheckoutOrderOutputDto;
import br.com.caelum.casadocodigoapi.model.CheckoutOrder;
import br.com.caelum.casadocodigoapi.repository.CheckoutOrderRepository;

@RestController
@RequestMapping("/api/admin/order")
public class OrderController {
	
	private CheckoutOrderRepository orderRepository;
	
	@Autowired
	public OrderController(CheckoutOrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<CheckoutOrderOutputDto> list() {
		List<CheckoutOrder> orders = orderRepository.findAll();
		return CheckoutOrderOutputDto.build(orders);
	}
	
	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public CheckoutOrderOutputDto show(@PathVariable("id") Long orderId) {
		CheckoutOrder checkoutOrder = orderRepository.findById(orderId).orElseThrow(() ->
        	new IllegalArgumentException(String
        			.format("The Order with id %d wasn't found!", orderId))
		);
		return new CheckoutOrderOutputDto(checkoutOrder);
	}	
}
