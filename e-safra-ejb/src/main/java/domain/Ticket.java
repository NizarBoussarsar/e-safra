package domain;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

/**
 * Entity implementation class for Entity: Ticket
 *
 */
@Entity
public class Ticket implements Serializable {

	private TicketId ticketId;
	private Long number;
	private Double price;

	private static final long serialVersionUID = 1L;

	// private Passenger passenger;
	// private Bus bus;

	// @Column(unique = true)

	public Ticket() {
		super();
	}

	public Ticket(TicketId ticketId, Double price, Long number) {
		super();
		this.ticketId = ticketId;
		this.price = price;
		this.number = number;
	}

	@EmbeddedId
	public TicketId getTicketId() {
		return ticketId;
	}

	public void setTicketId(TicketId ticketId) {
		this.ticketId = ticketId;
	}

	public Long getNumber() {
		return number;
	}

	public void setNumber(Long number) {
		this.number = number;
	}

	public Double getPrice() {
		return this.price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	// @OneToOne
	// public Passenger getPassenger() {
	// return passenger;
	// }
	//
	// public void setPassenger(Passenger passenger) {
	// this.passenger = passenger;
	// }
	//
	// @OneToOne
	// public Bus getBus() {
	// return bus;
	// }
	//
	// public void setBus(Bus bus) {
	// this.bus = bus;
	// }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((number == null) ? 0 : number.hashCode());
		long temp;
		temp = Double.doubleToLongBits(price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ticket other = (Ticket) obj;
		if (number == null) {
			if (other.number != null)
				return false;
		} else if (!number.equals(other.number))
			return false;
		if (Double.doubleToLongBits(price) != Double
				.doubleToLongBits(other.price))
			return false;
		return true;
	}

}
