package acme.datatypes;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Embeddable;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import acme.framework.datatypes.DomainDatatype;
import acme.framework.datatypes.Money;
import lombok.Getter;

@Embeddable
@Getter
public class Info extends DomainDatatype {

	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------
	
	@Id
	protected String	rareID;
	
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	protected Date				moment;

	@NotNull
	@Valid
	protected Money				money;

	@NotNull
	protected Boolean			finalMode;

	
	public void setRareID(String rareID) {
		this.rareID = rareID;
	}

	
	public void setMoment(Date moment) {
		Calendar c = Calendar.getInstance();
		c.setTime(moment);
		c.add(Calendar.DATE, 1);
		this.moment = c.getTime();
	}

	
	public void setMoney(Money money) {
		this.money = money;
	}

	
	public void setFinalMode(Boolean finalMode) {
		this.finalMode = finalMode;
	}
	
	

	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------

}
