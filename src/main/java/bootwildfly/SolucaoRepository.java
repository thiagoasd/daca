package bootwildfly;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import models.Solucao;

public interface SolucaoRepository extends CrudRepository<Solucao, Long>{
	List<Solucao> findAll();
	Page<Solucao> findAll(Pageable pageable);
	List<Solucao> findByProblemID(long id);
	Solucao findById(long id);
	
}
