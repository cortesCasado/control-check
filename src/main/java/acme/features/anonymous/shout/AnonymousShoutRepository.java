
package acme.features.anonymous.shout;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;

import acme.entities.shouts.Shout;
import acme.framework.repositories.AbstractRepository;

public interface AnonymousShoutRepository extends AbstractRepository {

	@Query("select s from Shout s")
	Collection<Shout> findMany();

	@Query("SELECT i.code FROM Somp i")
	Collection<String> findCodes();

}
