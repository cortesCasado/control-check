package acme.entities.dashboard;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Dashboard implements Serializable {
	
	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	//Control check
	
	Double flaggedAs;
	Double ratioOfShouts;
	
	Double 	avgMoneyCurrency1;
	Double 	dvtMoneyCurrency1;
	
	Double 	avgMoneyCurrency2;
	Double 	dvtMoneyCurrency2;
	

	
	
	Integer numberOfPublicTasks;
	Integer numberOfPrivateTasks;
	Integer numberOfFinishedTasks;
	Integer numberOfNonFinishedTasks;
	
	Double 	minWorkloadTasks;
	Double 	maxWorkloadTasks;
	Double 	avgWorkloadTasks;
	Double 	dvtWorkloadTasks;
	
	Long 	minExecutionPeriodTasks;
	Long 	maxExecutionPeriodTasks;
	Double 	avgExecutionPeriodTasks;
	Double 	dvtExecutionPeriodTasks;
	
	Integer numberOfPublicWorkplans;
	Integer numberOfPrivateWorkplans;
	Integer numberOfFinishedWorkplans;
	Integer numberOfNonFinishedWorkplans;
	
	Double 	minWorkloadWorkplans;
	Double 	maxWorkloadWorkplans;
	Double 	avgWorkloadWorkplans;
	Double 	dvtWorkloadWorkplans;
	
	Long 	minExecutionPeriodWorkplans;
	Long 	maxExecutionPeriodWorkplans;
	Double 	avgExecutionPeriodWorkplans;
	Double 	dvtExecutionPeriodWorkplans;
	
	Integer totalNumberOfWorkplans;
	Long totalNumberOfPublishedWorkplans;
	Long totalNumberOfNonPublishedWorkplans;
	
}