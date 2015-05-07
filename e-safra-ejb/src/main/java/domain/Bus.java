package domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.codehaus.jackson.annotate.JsonIgnore;

/**
 * Entity implementation class for Entity: Bus
 *
 */
@Entity
public class Bus implements Serializable {

	private Integer id;
	private String number;

	private static final long serialVersionUID = 1L;

	private List<Stop> stops;
	private Line line;
	private List<Driver> drivers;
	private List<Ticket> tickets;

	public Bus() {
		super();
	}

	public Bus(String number) {
		super();
		this.number = number;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(unique = true)
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	@OneToMany(mappedBy = "bus")
	@JsonIgnore
	public List<Stop> getStops() {
		return stops;
	}

	public void setStops(List<Stop> stops) {
		this.stops = stops;
	}

	@ManyToOne
	public Line getLine() {
		return line;
	}

	public void setLine(Line line) {
		this.line = line;
	}

	@ManyToMany(mappedBy = "buses")
	@JsonIgnore
	public List<Driver> getDrivers() {
		return drivers;
	}

	public void setDrivers(List<Driver> drivers) {
		this.drivers = drivers;
	}

	@OneToMany(mappedBy = "bus")
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
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Bus other = (Bus) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (number == null) {
			if (other.number != null)
				return false;
		} else if (!number.equals(other.number))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Bus [id=" + id + ", number=" + number + "]";
	}

}
