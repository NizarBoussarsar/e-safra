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

}
