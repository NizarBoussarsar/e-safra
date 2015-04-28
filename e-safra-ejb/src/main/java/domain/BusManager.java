package domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

/**
 * Entity implementation class for Entity: BusManager
 *
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class BusManager extends User implements Serializable {

	private Integer accessLevel;
	
	private static final long serialVersionUID = 1L;

	public BusManager() {
		super();
	}

	public BusManager(String firstName, String lastName, Character gender,
			Date birthDay, String login, String password, Integer accessLevel) {
		super(firstName, lastName, gender, birthDay, login, password);
		this.accessLevel = accessLevel;
	}

	public Integer getAccessLevel() {
		return accessLevel;
	}

	public void setAccessLevel(Integer accessLevel) {
		this.accessLevel = accessLevel;
	}

	@Override
	public String toString() {
		return "BusManager [getFirstName()=" + getFirstName()
				+ ", getLastName()=" + getLastName() + ", getGender()="
				+ getGender() + ", getBirthDay()=" + getBirthDay()
				+ ", accessLevel=" + accessLevel + "]";
	}

}
