package domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.codehaus.jackson.annotate.JsonIgnore;

/**
 * Entity implementation class for Entity: Lines
 *
 */
@Entity
public class Line implements Serializable {

	private Integer id;
	private String name;
	
	private static final long serialVersionUID = 1L;
	
	private List<Type> types;
	private List<Bus> buses;

	public Line() {
		super();
	}

	public Line(String name) {
		super();
		this.name = name;
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
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(mappedBy = "line")
	@JsonIgnore
	public List<Type> getTypes() {
		return types;
	}

	public void setTypes(List<Type> types) {
		this.types = types;
	}

	@OneToMany(mappedBy = "line")
	@JsonIgnore
	public List<Bus> getBuses() {
		return buses;
	}

	public void setBuses(List<Bus> buses) {
		this.buses = buses;
	}

	public void linkBusesToThisLine(List<Bus> buses) {
		this.buses = buses;
		for (Bus bus : buses) {
			bus.setLine(this);
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Line other = (Line) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Line [id=" + id + ", name=" + name + "]";
	}

}
