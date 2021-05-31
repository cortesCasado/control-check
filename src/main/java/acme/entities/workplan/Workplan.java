package acme.entities.workplan;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import acme.components.Spam.Spam1;
import acme.datatypes.ExecutionPeriod;
import acme.entities.spam.Spam;
import acme.entities.task.Task;
import acme.framework.entities.DomainEntity;
import acme.framework.entities.UserAccount;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Workplan extends DomainEntity{



	protected static final long	serialVersionUID	= 1L;

	
	// Attributes -------------------------------------------------------------

		@NotBlank
		protected String			title;
		
		@NotNull
		@Valid
		protected ExecutionPeriod 	executionPeriod;
		
		@NotNull
		protected Boolean 			isPrivate; 
		
		@Valid
		@ManyToOne
		protected UserAccount userAccount;

		@NotNull
		@Valid
		@ManyToMany(mappedBy = "workplan", fetch = FetchType.EAGER, cascade = CascadeType.ALL) //Eager necesario para showWorkplan
		protected Set<Task> task;
	
		// Derived attributes
		
		public static Double getTotalWorkload(final Workplan workplan) {
			return workplan.getTask().stream().mapToDouble(Task::getWorkload).sum();
		}
		
		public void addTask(final Task task) {
			final LocalDateTime initialDate = task.getExecutionPeriod().getInitialDate();
			final LocalDateTime finalDate = task.getExecutionPeriod().getFinalDate();
			
			if (initialDate.isBefore(this.getExecutionPeriod().getInitialDate())) {
				this.getExecutionPeriod().setInitialDate(initialDate.minusDays(1L).withHour(8));
			}
			
			if (finalDate.isAfter(this.getExecutionPeriod().getFinalDate())) {
				this.getExecutionPeriod().setFinalDate(finalDate.plusDays(1L).withHour(17));
			}
			
			this.task.add(task);
		}
		
		public List<Task> getTaskList() {
			return this.task.stream().collect(Collectors.toList());
		}
		
		public LocalDateTime getSuggestedInitialExecutionPeriod() {
			final LocalDateTime min = this.task.stream().map(t -> t.getExecutionPeriod().getInitialDate()).min(LocalDateTime::compareTo).orElse(LocalDateTime.now());
			LocalDateTime res = min.minusDays(1);
			res = LocalDateTime.of(res.getYear(), res.getMonth(), res.getDayOfMonth(),8,0);
			
			return res;
		}

		public LocalDateTime getSuggestedFinalExecutionPeriod() {
			final LocalDateTime max = this.task.stream().map(t -> t.getExecutionPeriod().getFinalDate()).max(LocalDateTime::compareTo).orElse(LocalDateTime.now());
			LocalDateTime res = max.plusDays(1);
			res = LocalDateTime.of(res.getYear(), res.getMonth(), res.getDayOfMonth(),17,00);
			
			return res;
		}
		
		public ExecutionPeriod getSuggestedExecutionPeriod() {
			final ExecutionPeriod ep = new ExecutionPeriod();
			ep.setInitialDate(this.getSuggestedInitialExecutionPeriod());
			ep.setFinalDate(this.getSuggestedFinalExecutionPeriod());
			return ep;
 		}
		
		public boolean isPublished(final List<Spam> spam) {
			return !(
				this.isPrivate ||
				Spam1.isSpam(this.title, spam) &&
				!this.getTitle().trim().isEmpty());
		}
 
		@Override
		public String toString() {
			return "Workplan [title=" + this.title + ", executionPeriod=" + this.executionPeriod + ", isPrivate=" + this.isPrivate + 
				", userAccount=" + this.userAccount + ", task=" + this.task + ", id=" + this.id + "]";
		}
		
}
