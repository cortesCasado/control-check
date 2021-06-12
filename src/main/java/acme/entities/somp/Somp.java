package acme.entities.somp;

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
public class Somp extends DomainEntity {

	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID = 1L;

	// Attributes -------------------------------------------------------------
	
	@NotBlank
	@Column(unique = true)
	@Pattern(regexp = "^\\w{3}-[0-9]{2}[0-1]{1}[0-9]{1}[0-3]{1}[0-9]{1}-\\w{2}$", message = "Error")
	protected String			code;
		
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	protected Date				deadline;

	@NotNull
	@Valid
	protected Money				budget;

	@NotNull
	protected Boolean			important;
	
	public static String getCodeRegExp(Date d) {
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		
		String day = String.valueOf(c.get(Calendar.DAY_OF_MONTH));
		String month = String.valueOf(c.get(Calendar.MONTH) + 1);
		String year = String.valueOf(c.get(Calendar.YEAR));

		if (day.length() == 1)
			day = "0" + day;
		if (month.length() == 1)
			month = "0" + month;
		year = year.substring(2);

		return year + month + day;
	}

	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------
	
	@NotNull
	@Valid
	@OneToOne
	protected Shout shout;
	
	

}
