package acme.entities.workplan;


import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import acme.datatypes.ExecutionPeriod;
import acme.entities.task.Task;
import acme.framework.entities.DomainEntity;
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
		protected ExecutionPeriod 	executionPeriod;
		
		@NotNull
		protected Boolean 			isPrivate;


		@NotNull
		@Valid
		@ManyToMany(mappedBy = "workplan")
		protected List<Task> task;
		
		
		
	// Derived attributes
		
		Double getTotalWorkload() {
			return this.getTask().stream().mapToDouble(x -> x.getWorkload()).sum();
		}
		

}
