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
 * Entity implementation class for Entity: Driver
 *
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Driver extends User implements Serializable {

	private Integer seniority;
	private String shift;
	
	private static final long serialVersionUID = 1L;

	private List<Bus> buses;

	public Driver() {
		super();
	}

	public Driver(String firstName, String lastName, Character gender,
			Date birthDay, String email, String password, Integer seniority,
			String shift) {
		super(firstName, lastName, gender, birthDay, email, password);
		this.seniority = seniority;
		this.shift = shift;
	}

	public Integer getSeniority() {
		return seniority;
	}

	public void setSeniority(Integer seniority) {
		this.seniority = seniority;
	}

	public String getShift() {
		return shift;
	}

	public void setShift(String shift) {
		this.shift = shift;
	}

	@OneToMany(mappedBy = "driver")
	@JsonIgnore
	public List<Bus> getBuses() {
		return buses;
	}

	public void setBuses(List<Bus> buses) {
		this.buses = buses;
	}

	@Override
	public String toString() {
		return "Driver [getFirstName()=" + getFirstName() + ", getLastName()="
				+ getLastName() + ", getEmail()=" + getEmail()
				+ ", getGender()=" + getGender() + ", getBirthDay()="
				+ getBirthDay() + ", seniority=" + seniority + ", shift="
				+ shift + "]";
	}

}
