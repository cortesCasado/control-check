package acme.entities.receipt;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import acme.entities.shouts.Shout;
import acme.framework.datatypes.Money;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Receipt extends DomainEntity {

	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID = 1L;

	// Attributes -------------------------------------------------------------
	
	@NotBlank
	@Column(unique = true)
	@Pattern(regexp = "[0-3]{1}[0-9]{1}-[0-1]{1}[0-9]{1}-[0-9]{4} [0-9]{2}", message = "Error")
	protected String			referenciaEx;
	
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	protected Date				deadlineEx;

	@NotNull
	@Valid
	protected Money				totalPriceEx;

	@NotNull
	protected Boolean			paidEx;

	
	public void setDeadlineEx(Date deadlineEx) {
		Calendar c = Calendar.getInstance();
		c.setTime(deadlineEx);
		c.add(Calendar.DATE, 15);
		c.set(Calendar.HOUR, 8);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		this.deadlineEx = c.getTime();
	}

	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------
	
	@NotNull
	@Valid
	@OneToOne
	protected Shout shout;
	
	

}
