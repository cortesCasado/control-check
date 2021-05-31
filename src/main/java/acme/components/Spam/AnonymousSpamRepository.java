package acme.components.Spam;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import acme.entities.spam.Spam;
import acme.framework.repositories.AbstractRepository;

public interface AnonymousSpamRepository extends AbstractRepository{
	
	@Query("select s from Spam s")
	List<Spam> findSpam();

}
