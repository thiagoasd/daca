package bootwildfly;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import models.Problema;

public interface ProblemaRepository extends CrudRepository<Problema, Long> {
	List<Problema> findAll();

	Page<Problema> findAll(Pageable pageable);

	Problema findById(long id);

	List<Problema> findByNome(String nome);
	
	long count();
}
