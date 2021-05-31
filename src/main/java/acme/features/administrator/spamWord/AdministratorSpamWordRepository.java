package acme.features.administrator.spamWord;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.spam.Spam;
import acme.entities.spam.SpamWord;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorSpamWordRepository extends AbstractRepository{	
	
	@Query("select s from Spam s")
	List<Spam> findSpam();
	
	@Query("select sw from SpamWord sw where sw.id LIKE ?1")
	SpamWord findSpamWordById(int id);
	
	@Query("select sw from SpamWord sw")
	List<SpamWord> findAllSpamWords();

}