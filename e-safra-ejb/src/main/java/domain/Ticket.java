package domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Entity implementation class for Entity: Ticket
 *
 */
@Entity
public class Ticket implements Serializable {

	private Long number;
	private Double price;
	private Date date;

	private static final long serialVersionUID = 1L;

	private Passenger passenger;
	private Station departureStation;
	private Station arrivalStation;
	private Bus bus;

	// @Column(unique = true)

	public Ticket() {
		super();
	}

	public Ticket(Double price, Date date, Passenger passenger,
			Station departureStation, Station arrivalStation, Bus bus) {
		super();
		this.price = price;
		this.date = date;
		this.passenger = passenger;
		this.departureStation = departureStation;
		this.arrivalStation = arrivalStation;
		this.bus = bus;
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

	@ManyToOne
	public Passenger getPassenger() {
		return passenger;
	}

	public void setPassenger(Passenger passenger) {
		this.passenger = passenger;
	}

	@ManyToOne
	public Station getDepartureStation() {
		return departureStation;
	}

	public void setDepartureStation(Station departureStation) {
		this.departureStation = departureStation;
	}

	@ManyToOne
	public Station getArrivalStation() {
		return arrivalStation;
	}

	public void setArrivalStation(Station arrivalStation) {
		this.arrivalStation = arrivalStation;
	}

	@ManyToOne
	public Bus getBus() {
		return bus;
	}

	public void setBus(Bus bus) {
		this.bus = bus;
	}
	
}
