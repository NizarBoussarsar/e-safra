package domain;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Entity implementation class for Entity: Type
 *
 */
@Entity
public class Type implements Serializable {

	private TypeId typeId;
	private String stationType;
	private Integer stationOrder;
	
	private static final long serialVersionUID = 1L;
	
	private Station station;
	private Line line;
	
	//@Column(unique = true)

	public Type() {
		super();
	}

	public Type(String stationType, Integer stationOrder, Station station, Line line) {
		super();
		this.stationType = stationType;
		this.stationOrder = stationOrder;
		this.typeId = new TypeId(line.getId(), station.getId());
	}

	@EmbeddedId
	public TypeId getTypeId() {
		return typeId;
	}

	public void setTypeId(TypeId typeId) {
		this.typeId = typeId;
	}

	public String getStationType() {
		return stationType;
	}

	public void setStationType(String type) {
		this.stationType = type;
	}

	public Integer getStationOrder() {
		return stationOrder;
	}

	public void setStationOrder(Integer stationOrder) {
		this.stationOrder = stationOrder;
	}

	@ManyToOne
	@JoinColumn(name = "idStation", referencedColumnName = "id", insertable = false, updatable = false)
	public Station getStation() {
		return station;
	}

	public void setStation(Station station) {
		this.station = station;
	}

	@ManyToOne
	@JoinColumn(name = "idLine", referencedColumnName = "id", insertable = false, updatable = false)
	public Line getLine() {
		return line;
	}

	public void setLine(Line line) {
		this.line = line;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((stationOrder == null) ? 0 : stationOrder.hashCode());
		result = prime * result + ((stationType == null) ? 0 : stationType.hashCode());
		result = prime * result + ((typeId == null) ? 0 : typeId.hashCode());
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
		Type other = (Type) obj;
		if (stationOrder == null) {
			if (other.stationOrder != null)
				return false;
		} else if (!stationOrder.equals(other.stationOrder))
			return false;
		if (stationType == null) {
			if (other.stationType != null)
				return false;
		} else if (!stationType.equals(other.stationType))
			return false;
		if (typeId == null) {
			if (other.typeId != null)
				return false;
		} else if (!typeId.equals(other.typeId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Type [typeId=" + typeId + ", stationType=" + stationType + ", stationOrder="
				+ stationOrder + "]";
	}

}
