package br.com.caelum.casadocodigoapi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.Repository;

import br.com.caelum.casadocodigoapi.model.Category;

public interface CategoryRepository extends Repository<Category, Long> {

	List<Category> findAll();

	Optional<Category> findById(Long id);

	Category save(Category category);
}
