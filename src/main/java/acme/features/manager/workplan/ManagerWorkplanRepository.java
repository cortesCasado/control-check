/*
 * AdministratorUserAccountRepository.java
 *
 * Copyright (C) 2012-2021 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.manager.workplan;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.workplan.Workplan;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface ManagerWorkplanRepository extends AbstractRepository {
	
	@Query("SELECT w FROM Workplan w WHERE w.id = ?1")
	Optional<Workplan> findById(int id);
	
	@Query("SELECT w FROM Workplan w WHERE w.userAccount.id = ?1")
	List<Workplan> findAll(int id);

	@Query("SELECT w FROM Workplan w WHERE w.id = ?1 and w.userAccount.id = ?2")
	Optional<Workplan> findOneWorkplanByIdAndUA(int workplanId, int userAcountId);
	
	@Query("SELECT w FROM Workplan w")
	List<Workplan> findMany();
}
