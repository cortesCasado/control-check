package acme.features.anonymous.workplan;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;

import acme.entities.workplan.Workplan;
import acme.framework.repositories.AbstractRepository;

public interface AnonymousWorkPlanRepository extends AbstractRepository{
	
	@Query("select s from Workplan s where s.isPrivate = 'false'")
	Collection<Workplan> findPublicWorkPlans();
	
	@Query("select w from Workplan w where w.id LIKE ?1")
	Workplan findWorkPlanById(int id);

}
