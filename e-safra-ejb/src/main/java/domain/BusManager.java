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
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((accessLevel == null) ? 0 : accessLevel.hashCode());
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
		BusManager other = (BusManager) obj;
		if (accessLevel == null) {
			if (other.accessLevel != null)
				return false;
		} else if (!accessLevel.equals(other.accessLevel))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "BusManager [getFirstName()=" + getFirstName()
				+ ", getLastName()=" + getLastName() + ", getGender()="
				+ getGender() + ", getBirthDay()=" + getBirthDay()
				+ ", accessLevel=" + accessLevel + "]";
	}

}
