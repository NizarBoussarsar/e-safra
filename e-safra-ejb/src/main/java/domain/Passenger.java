package domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

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

	// private Ticket ticket;

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

	// @OneToOne(mappedBy = "passenger")
	// public Ticket getTicket() {
	// return ticket;
	// }
	//
	// public void setTicket(Ticket ticket) {
	// this.ticket = ticket;
	// }

	@Override
	public String toString() {
		return "Passenger [getFirstName()=" + getFirstName()
				+ ", getLastName()=" + getLastName() + ", getEmail()="
				+ getEmail() + ", getGender()=" + getGender()
				+ ", getBirthDay()=" + getBirthDay() + ", cash=" + cash
				+ ", facebookId=" + facebookId + "]";
	}

}
