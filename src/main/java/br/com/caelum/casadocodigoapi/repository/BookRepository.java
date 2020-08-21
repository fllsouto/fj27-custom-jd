package br.com.caelum.casadocodigoapi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.Repository;

import br.com.caelum.casadocodigoapi.model.Book;

public interface BookRepository extends Repository<Book, Long> {

	List<Book> findAll();

	Optional<Book> findById(Long id);

	Book save(Book book);
}
