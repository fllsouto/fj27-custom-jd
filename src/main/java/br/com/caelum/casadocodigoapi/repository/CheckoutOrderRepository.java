package br.com.caelum.casadocodigoapi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.Repository;

import br.com.caelum.casadocodigoapi.model.CheckoutOrder;

public interface CheckoutOrderRepository extends Repository<CheckoutOrder, Long> {
	
	CheckoutOrder save(CheckoutOrder order);

	List<CheckoutOrder> findAll();

	Optional<CheckoutOrder> findById(Long orderId);

}
