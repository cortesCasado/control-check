
package acme.entities.shouts;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.entities.receiptEx.ReceiptEx;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Shout extends DomainEntity {

	protected static final long	serialVersionUID	= 1L;

	//Control check

	@NotNull
	@Valid
	@OneToOne(mappedBy = "shout")
	protected ReceiptEx				receiptEx;

	@Temporal(TemporalType.TIMESTAMP)
	@Past
	@NotNull
	protected Date				moment;

	@NotBlank
	@Length(min = 5, max = 25)
	protected String			author;

	@NotBlank
	@Length(max = 100)
	protected String			text;

	@URL
	protected String			link;


	@Override
	public String toString() {
		return this.id + "," + this.version + "," + 
	this.moment + "," + this.author + "," + this.text + "," + this.link + "," + 
			
	this.receiptEx.getReferenciaEx() + "," + this.receiptEx.getDeadlineEx().toString() + "," + 
	this.receiptEx.getTotalPriceEx().getCurrency() + " " + this.receiptEx.getTotalPriceEx().getAmount() + "," + 
	this.receiptEx.getPaidEx();
	}

}
