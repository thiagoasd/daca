package bootwildfly;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import models.Teste;

public interface TesteRepository extends CrudRepository<Teste, Long>{
	List<Teste> findAll();
	Page<Teste> findAll(Pageable pageable);
	List<Teste> findByNome(String nome);
	List<Teste> findByProblemID(long id);
	Teste findById(long id);
	void deleteByProblemID(long problemID);
}
