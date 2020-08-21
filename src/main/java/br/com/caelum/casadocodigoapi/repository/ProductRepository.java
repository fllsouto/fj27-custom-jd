package br.com.caelum.casadocodigoapi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.Repository;

import br.com.caelum.casadocodigoapi.model.Product;

public interface ProductRepository extends Repository<Product, Long> {

	List<Product> findAll();

	Optional<Product> findById(Long id);

	Product save(Product product);

	void delete(Product product);
}
