package bootwildfly;

import org.springframework.data.repository.CrudRepository;
import models.Account;

public interface AccountRepository extends CrudRepository<Account, Long> {

	long findById(long Id);
	Account findByUsername(String username);
}
