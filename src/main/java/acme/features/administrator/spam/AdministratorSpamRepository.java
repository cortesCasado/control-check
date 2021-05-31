package acme.features.administrator.spam;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.spam.Spam;
import acme.entities.spam.SpamWord;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorSpamRepository extends AbstractRepository{	
	@Query("select s from Spam s")
	List<Spam> findSpam();
	
	@Query("select s.spamWords from Spam s")
	List<SpamWord> findAllSpamWords();

}