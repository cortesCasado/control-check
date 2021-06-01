package acme.entities.info;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import acme.entities.shouts.Shout;
import acme.framework.datatypes.Money;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Info extends DomainEntity {

	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------
	
	@NotNull
	@Column(unique = true)
	protected String	rareID;
	
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	protected Date				moment;

	@NotNull
	@Valid
	protected Money				money;

	@NotNull
	protected Boolean			flag;

	
	public void setMoment(Date moment) {
		Calendar c = Calendar.getInstance();
		c.setTime(moment);
		c.add(Calendar.DATE, 1);
		this.moment = c.getTime();
	}

	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------
	
	@NotNull
	@Valid
	@OneToOne
	protected Shout shout;

}
