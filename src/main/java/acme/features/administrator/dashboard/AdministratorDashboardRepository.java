
package acme.features.administrator.dashboard;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.task.Task;
import acme.entities.workplan.Workplan;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorDashboardRepository extends AbstractRepository {
	
	//Control check
	
	@Query("SELECT 100.00 * COUNT(i) / (SELECT COUNT(j) FROM ReceiptEx j) FROM ReceiptEx i WHERE i.paidEx = true")
	Double flaggedAs();

	@Query("SELECT 100.00 * COUNT(i) / (SELECT COUNT(j) FROM ReceiptEx j) FROM ReceiptEx i WHERE i.totalPriceEx.amount >= 500 AND i.totalPriceEx.currency = 'EUR'")
	Double ratioOfShouts();

	@Query("SELECT AVG(i.totalPriceEx.amount) FROM ReceiptEx i WHERE i.totalPriceEx.currency = 'EUR'")
	Double avgMoneyCurrency1();

	@Query("SELECT STDDEV(i.totalPriceEx.amount) FROM ReceiptEx i WHERE i.totalPriceEx.currency = 'EUR'")
	Double dvtMoneyCurrency1();

	@Query("SELECT AVG(i.totalPriceEx.amount) FROM ReceiptEx i WHERE i.totalPriceEx.currency = 'USD'")
	Double avgMoneyCurrency2();

	@Query("SELECT STDDEV(i.totalPriceEx.amount) FROM ReceiptEx i WHERE i.totalPriceEx.currency = 'USD'")
	Double dvtMoneyCurrency2();
	

	
	

	//About tasks

	@Query("SELECT COUNT(t) FROM Task t where t.isPrivate = false")
	Integer numberOfPublicTasks();

	@Query("SELECT COUNT(t) FROM Task t where t.isPrivate = true")
	Integer numberOfPrivateTasks();

	@Query("SELECT COUNT(t) FROM Task t where t.executionPeriod.finalDate < CURRENT_TIMESTAMP")
	Integer numberOfFinishedTasks();

	@Query("SELECT COUNT(t) FROM Task t where t.executionPeriod.finalDate > CURRENT_TIMESTAMP")
	Integer numberOfNonFinishedTasks();

	//About task execution period

	@Query("SELECT MIN(t.executionPeriod.finalDate - t.executionPeriod.initialDate) FROM Task t")
	Double minOfTaskExecutionPeriods();

	@Query("SELECT MAX(t.executionPeriod.finalDate- t.executionPeriod.initialDate) FROM Task t")
	Double maxOfTaskExecutionPeriods();

	@Query("SELECT AVG(t.executionPeriod.finalDate - t.executionPeriod.initialDate) FROM Task t")
	Double averageOfTaskExecutionPeriods();

	@Query("SELECT STDDEV(t.executionPeriod.finalDate - t.executionPeriod.initialDate) FROM Task t")
	Double deviationOfTaskExecutionPeriods();

	//About task workload 

	@Query("SELECT MIN(t.workload) FROM Task t")
	Double minOfTaskWorkloads();

	@Query("SELECT MAX(t.workload) FROM Task t")
	Double maxOfTaskWorkloads();

	@Query("SELECT AVG(t.workload) FROM Task t")
	Double averageOfTaskWorkloads();

	@Query("SELECT STDDEV(t.workload) FROM Task t")
	Double deviationOfTaskWorkloads();

	//About workplans

	@Query("SELECT COUNT(w) FROM Workplan w where w.isPrivate = false")
	Integer numberOfPublicWorkplans();

	@Query("SELECT COUNT(w) FROM Workplan w where w.isPrivate = true")
	Integer numberOfPrivateWorkplans();

	@Query("SELECT COUNT(w) FROM Workplan w where w.executionPeriod.finalDate < CURRENT_TIMESTAMP")
	Integer numberOfFinishedWorkplans();

	@Query("SELECT COUNT(w) FROM Workplan w where w.executionPeriod.finalDate > CURRENT_TIMESTAMP")
	Integer numberOfNonFinishedWorkplans();

	//About workplan execution period

	@Query("SELECT MIN(w.executionPeriod.finalDate - w.executionPeriod.initialDate) FROM Workplan w")
	Double minOfWorkplanExecutionPeriods();

	@Query("SELECT MAX(w.executionPeriod.finalDate - w.executionPeriod.initialDate) FROM Workplan w")
	Double maxOfWorkplanExecutionPeriods();

	@Query("SELECT AVG(w.executionPeriod.finalDate - w.executionPeriod.initialDate) FROM Workplan w")
	Double averageOfWorkplanExecutionPeriods();

	@Query("SELECT STDDEV(w.executionPeriod.finalDate - w.executionPeriod.initialDate) FROM Workplan w")
	Double deviationOfWorkplanExecutionPeriods();

	//Chart 

	@Query("SELECT w FROM Workplan w")
	List<Workplan> findWorkplans();

	@Query("SELECT t FROM Task t")
	List<Task> findTasks();


}
