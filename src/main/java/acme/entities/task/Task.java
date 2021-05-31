package acme.entities.task;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.datatypes.ExecutionPeriod;
import acme.entities.workplan.Workplan;
import acme.framework.entities.DomainEntity;
import acme.framework.entities.UserAccount;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Task extends DomainEntity{



	protected static final long	serialVersionUID	= 1L;

	
	// Attributes -------------------------------------------------------------

		@NotBlank
		@Length(min = 1, max = 80)
		protected String			title;

		@NotBlank
		@Length(min = 1, max = 500)
		protected String			description;

		
		@NotNull
		protected Double			workload;

		@NotNull
		@Valid
		protected ExecutionPeriod executionPeriod;
		
		
		@URL
		protected String			url;

		@NotNull
		protected Boolean isPrivate;


		@Valid
		@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL) //Eager necesario para showWorkplan
		protected Set<Workplan> workplan;
		
		@Valid
		@ManyToOne
		protected UserAccount userAccount;
		
		public void addWorkplan(final Workplan workplan) {
			this.workplan.add(workplan);
		}

		@Override
		public String toString() {
			return "Task [title=" + this.title + ", description=" + this.description + ", workload=" + this.workload + 
				", executionPeriod=" + this.executionPeriod + ", url=" + this.url + ", isPrivate=" + this.isPrivate + 
				 ", userAccount=" + this.userAccount + "]";
		}
		
}
