package acme.features.administrator.dashboard;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.dashboard.Dashboard;
import acme.entities.task.Task;
import acme.entities.workplan.Workplan;
import acme.features.administrator.spam.AdministratorSpamRepository;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractShowService;

@Service
public class AdministratorDashboardShowService implements AbstractShowService<Administrator, Dashboard> {

	@Autowired
	protected AdministratorDashboardRepository repository;
	
	@Autowired
	private AdministratorSpamRepository spamRepository;

	@Override
	public boolean authorise(final Request<Dashboard> request) {
		assert request != null;
		final String rol =request.getPrincipal().getActiveRole().getSimpleName();
		return rol.equals("Administrator");
	}

	@Override
	public void unbind(final Request<Dashboard> request, final Dashboard entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model,
			
			"flaggedAs",
			"ratioOfShouts",
		
			"avgMoneyCurrency1",
			"dvtMoneyCurrency1",
		
			"avgMoneyCurrency2",
			"dvtMoneyCurrency2",
			
			
			
			
			"numberOfPublicTasks", 
			"numberOfPrivateTasks", 
			"numberOfFinishedTasks", 
			"numberOfNonFinishedTasks",
		
			"numberOfPublicWorkplans",
			"numberOfPrivateWorkplans",
			"numberOfFinishedWorkplans",
			"numberOfNonFinishedWorkplans",
		
			"minWorkloadTasks",
			"maxWorkloadTasks",
			"avgWorkloadTasks",
			"dvtWorkloadTasks",
			
			"minExecutionPeriodTasks",
			"maxExecutionPeriodTasks",
			"avgExecutionPeriodTasks",
			"dvtExecutionPeriodTasks",
		
			"minWorkloadWorkplans",
			"maxWorkloadWorkplans",
			"avgWorkloadWorkplans",
			"dvtWorkloadWorkplans",
			
			"minExecutionPeriodWorkplans",
			"maxExecutionPeriodWorkplans",
			"avgExecutionPeriodWorkplans",
			"dvtExecutionPeriodWorkplans",
		
			"totalNumberOfWorkplans",
			"totalNumberOfPublishedWorkplans",
			"totalNumberOfNonPublishedWorkplans");
	}

	@Override
	public Dashboard findOne(final Request<Dashboard> request) {
		assert request != null;

		final Dashboard result = new Dashboard();
		
		//Control check
		
		result.setFlaggedAs(this.repository.flaggedAs());
		result.setRatioOfShouts(this.repository.ratioOfShouts());
		
		result.setAvgMoneyCurrency1(this.repository.avgMoneyCurrency1());
		result.setDvtMoneyCurrency1(this.repository.dvtMoneyCurrency1());
		
		result.setAvgMoneyCurrency2(this.repository.avgMoneyCurrency2());
		result.setDvtMoneyCurrency2(this.repository.dvtMoneyCurrency2());
		

		
		
		
		

		final List<Task> allTasks = this.repository.findTasks();
		final int numberOfTasks = allTasks.size();

		final List<Workplan> allWorkplans = this.repository.findWorkplans();
		final int numberOfWorkplans = allWorkplans.size();

		Long minExecutionPeriodTasks = numberOfTasks == 0? 0L : allTasks.get(0).getExecutionPeriod().getDays();
		Long maxExecutionPeriodTasks = numberOfTasks == 0? 0L : allTasks.get(0).getExecutionPeriod().getDays();
		final Double avgExecutionPeriodTasks = allTasks.stream().mapToLong(x -> x.getExecutionPeriod().getDays()).average().orElse(0.);
		Double dvtExecutionPeriodTasks = 0.;

		Double minWorkloadWorkplans = numberOfWorkplans == 0? 0. : Workplan.getTotalWorkload(allWorkplans.get(0));
		Double maxWorkloadWorkplans = numberOfWorkplans == 0? 0. : Workplan.getTotalWorkload(allWorkplans.get(0));
		final Double avgWorkloadWorkplans = allWorkplans.stream().mapToDouble(Workplan::getTotalWorkload).average().orElse(0.);
		Double dvtWorkloadWorkplans = 0.;

		Long minExecutionPeriodWorkplans = numberOfWorkplans == 0? 0L : allWorkplans.get(0).getExecutionPeriod().getDays();
		Long maxExecutionPeriodWorkplans = numberOfWorkplans == 0? 0L : allWorkplans.get(0).getExecutionPeriod().getDays();
		final Double avgExecutionPeriodWorkplans = allWorkplans.stream().mapToLong(x -> x.getExecutionPeriod().getDays()).average().orElse(0.);
		Double dvtExecutionPeriodWorkplans = 0.;

		for (final Workplan w : allWorkplans) {
			final Double workload = Workplan.getTotalWorkload(w);

			if (workload < minWorkloadWorkplans)
				minWorkloadWorkplans = workload;
			if (workload > maxWorkloadWorkplans)
				maxWorkloadWorkplans = workload;

			final Long days = w.getExecutionPeriod().getDays();

			if (days < minExecutionPeriodWorkplans)
				minExecutionPeriodWorkplans = days;
			if (days > maxExecutionPeriodWorkplans)
				maxExecutionPeriodWorkplans = days;

			dvtWorkloadWorkplans += Math.pow(workload - avgWorkloadWorkplans, 2);
			dvtExecutionPeriodWorkplans += Math.pow(days - avgExecutionPeriodWorkplans, 2);
		}

		if(numberOfWorkplans > 0) {
			dvtWorkloadWorkplans = Math.sqrt(dvtWorkloadWorkplans / numberOfWorkplans);
			dvtExecutionPeriodWorkplans = Math.sqrt(dvtExecutionPeriodWorkplans / numberOfWorkplans);
		}
		
		for (final Task t : allTasks) {
			final Long days = t.getExecutionPeriod().getDays();

			if (days < minExecutionPeriodTasks)
				minExecutionPeriodTasks = days;
			if (days > maxExecutionPeriodTasks)
				maxExecutionPeriodTasks = days;

			dvtExecutionPeriodTasks += Math.pow(days - avgExecutionPeriodTasks, 2);
		}
		if (numberOfTasks > 0)
			dvtExecutionPeriodTasks = Math.sqrt(dvtExecutionPeriodTasks / numberOfTasks);

		result.setNumberOfPublicTasks(this.repository.numberOfPublicTasks());
		result.setNumberOfPrivateTasks(this.repository.numberOfPrivateTasks());
		result.setNumberOfFinishedTasks(this.repository.numberOfFinishedTasks());
		result.setNumberOfNonFinishedTasks(this.repository.numberOfNonFinishedTasks());

		result.setNumberOfPublicWorkplans(this.repository.numberOfPublicWorkplans());
		result.setNumberOfPrivateWorkplans(this.repository.numberOfPrivateWorkplans());
		result.setNumberOfFinishedWorkplans(this.repository.numberOfFinishedWorkplans());
		result.setNumberOfNonFinishedWorkplans(this.repository.numberOfNonFinishedWorkplans());

		result.setMinWorkloadTasks(this.repository.minOfTaskWorkloads());
		result.setMaxWorkloadTasks(this.repository.maxOfTaskWorkloads());
		result.setAvgWorkloadTasks(this.repository.averageOfTaskWorkloads());
		result.setDvtWorkloadTasks(this.repository.deviationOfTaskWorkloads());

		result.setMinExecutionPeriodTasks(minExecutionPeriodTasks);
		result.setMaxExecutionPeriodTasks(maxExecutionPeriodTasks);
		result.setAvgExecutionPeriodTasks(avgExecutionPeriodTasks);
		result.setDvtExecutionPeriodTasks(dvtExecutionPeriodTasks);

		result.setMinWorkloadWorkplans(minWorkloadWorkplans);
		result.setMaxWorkloadWorkplans(maxWorkloadWorkplans);
		result.setAvgWorkloadWorkplans(avgWorkloadWorkplans);
		result.setDvtWorkloadWorkplans(dvtWorkloadWorkplans);

		result.setMinExecutionPeriodWorkplans(minExecutionPeriodWorkplans);
		result.setMaxExecutionPeriodWorkplans(maxExecutionPeriodWorkplans);
		result.setAvgExecutionPeriodWorkplans(avgExecutionPeriodWorkplans);
		result.setDvtExecutionPeriodWorkplans(dvtExecutionPeriodWorkplans);

		
		
		result.setTotalNumberOfWorkplans(numberOfWorkplans);
		
		//Añadir los workplan non published (con spam)
		
		final Long totalNumberOfPublishedWorkplans = allWorkplans.stream().filter(w -> w.isPublished(this.spamRepository.findSpam())).count();
		result.setTotalNumberOfPublishedWorkplans(totalNumberOfPublishedWorkplans);
		result.setTotalNumberOfNonPublishedWorkplans(numberOfWorkplans - totalNumberOfPublishedWorkplans);

		return result;
	}

}
