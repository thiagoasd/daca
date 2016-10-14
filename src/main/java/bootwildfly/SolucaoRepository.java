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
	List<Solucao> findByLast(boolean last);
	List<Solucao> findByProblemIDAndLast(long problemaID, boolean last);
	Solucao findById(long id);
	void deleteByProblemID(long ProblemID);
	
}
