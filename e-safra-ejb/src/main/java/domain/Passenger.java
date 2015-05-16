package domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;

import org.codehaus.jackson.annotate.JsonIgnore;

/**
 * Entity implementation class for Entity: Passenger
 *
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Passenger extends User implements Serializable {

	private Double cash;
	private String facebookId;

	private static final long serialVersionUID = 1L;

	private List<Ticket> tickets;

	public Passenger() {
		super();
	}

	public Passenger(String firstName, String lastName, Character gender,
			Date birthDay, String email, String password, Double cash,
			String facebookId) {
		super(firstName, lastName, gender, birthDay, email, password);
		this.cash = cash;
		this.facebookId = facebookId;
	}

	public Double getCash() {
		return cash;
	}

	public void setCash(Double cash) {
		this.cash = cash;
	}

	public String getFacebookId() {
		return facebookId;
	}

	public void setFacebookId(String facebookId) {
		this.facebookId = facebookId;
	}

	@OneToMany(mappedBy = "passenger")
	@JsonIgnore
	public List<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((cash == null) ? 0 : cash.hashCode());
		result = prime * result
				+ ((facebookId == null) ? 0 : facebookId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Passenger other = (Passenger) obj;
		if (cash == null) {
			if (other.cash != null)
				return false;
		} else if (!cash.equals(other.cash))
			return false;
		if (facebookId == null) {
			if (other.facebookId != null)
				return false;
		} else if (!facebookId.equals(other.facebookId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Passenger [getFirstName()=" + getFirstName()
				+ ", getLastName()=" + getLastName() + ", getEmail()="
				+ getEmail() + ", getGender()=" + getGender()
				+ ", getBirthDay()=" + getBirthDay() + ", cash=" + cash
				+ ", facebookId=" + facebookId + "]";
	}

}
