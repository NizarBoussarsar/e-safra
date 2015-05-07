package domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 * Entity implementation class for Entity: Ticket
 *
 */
@Entity
public class Ticket implements Serializable {

	private Long number;
	private Double price;
	private Date date;
	private Integer star;

	private static final long serialVersionUID = 1L;

	private Passenger passenger;
	private Bus bus;
	private Type typeDeparture;
	private Type typeArrival;

	// @Column(unique = true)

	public Ticket() {
		super();
	}

	public Ticket(Long number, Double price, Date date) {
		super();
		this.number = number;
		this.price = price;
		this.date = date;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Integer getStar() {
		return star;
	}

	public void setStar(Integer star) {
		this.star = star;
	}

	@ManyToOne
	public Passenger getPassenger() {
		return passenger;
	}

	public void setPassenger(Passenger passenger) {
		this.passenger = passenger;
	}

	@ManyToOne
	public Bus getBus() {
		return bus;
	}

	public void setBus(Bus bus) {
		this.bus = bus;
	}

	@OneToOne
	public Type getTypeDeparture() {
		return typeDeparture;
	}

	public void setTypeDeparture(Type typeDeparture) {
		this.typeDeparture = typeDeparture;
	}

	@OneToOne
	public Type getTypeArrival() {
		return typeArrival;
	}

	public void setTypeArrival(Type typeArrival) {
		this.typeArrival = typeArrival;
	}

	@Override
	public String toString() {
		return "Ticket [number=" + number + ", price=" + price + ", date="
				+ date + ", star=" + star + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((number == null) ? 0 : number.hashCode());
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
		return true;
	}

}
