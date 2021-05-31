/*
 * Money.java
 *
 * Copyright (C) 2012-2021 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.datatypes;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import javax.persistence.Embeddable;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import acme.framework.datatypes.DomainDatatype;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class ExecutionPeriod extends DomainDatatype implements Comparable<ExecutionPeriod> {

	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	@NotNull
	protected LocalDateTime initialDate;

	@NotNull
	protected LocalDateTime finalDate;

	// Derived attributes -----------------------------------------------------

	@Transient
	public long getDays() {
		return ChronoUnit.DAYS.between(this.initialDate, this.finalDate);
	}

	@Override
	public int compareTo(final ExecutionPeriod o) {
		return (int) (this.getDays() - o.getDays());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.finalDate == null) ? 0 : this.finalDate.hashCode());
		result = prime * result + ((this.initialDate == null) ? 0 : this.initialDate.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (this.getClass() != obj.getClass())
			return false;
		ExecutionPeriod other = (ExecutionPeriod) obj;
		
		
		
		if (this.finalDate == null) {
			if (other.finalDate != null)
				return false;
		} else if (!this.finalDate.equals(other.finalDate))
			return false;
		
		
		
		if (this.initialDate == null) {
			if (other.initialDate != null)
				return false;
		} else if (!this.initialDate.equals(other.initialDate))
			return false;
		return true;
	}

}

