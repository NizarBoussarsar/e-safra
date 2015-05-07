package domain;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Entity implementation class for Entity: Stop
 *
 */
@Entity
public class Stop implements Serializable {

	private StopId stopId;
	private Integer nbFreeSpaces;

	private static final long serialVersionUID = 1L;

	private Bus bus;
	private Station station;

	// @Column(unique = true)

	public Stop() {
		super();
	}

	public Stop(Integer nbFreeSpaces) {
		super();
		this.nbFreeSpaces = nbFreeSpaces;
		this.stopId = new StopId(bus.getId(), station.getId());
	}

	@EmbeddedId
	public StopId getStopId() {
		return stopId;
	}

	public void setStopId(StopId stopId) {
		this.stopId = stopId;
	}

	public Integer getNbFreeSpaces() {
		return this.nbFreeSpaces;
	}

	public void setNbFreeSpaces(Integer nbFreeSpaces) {
		this.nbFreeSpaces = nbFreeSpaces;
	}

	@ManyToOne
	@JoinColumn(name = "idBus", referencedColumnName = "id", insertable = false, updatable = false)
	public Bus getBus() {
		return bus;
	}

	public void setBus(Bus bus) {
		this.bus = bus;
	}

	@ManyToOne
	@JoinColumn(name = "idStation", referencedColumnName = "id", insertable = false, updatable = false)
	public Station getStation() {
		return station;
	}

	public void setStation(Station station) {
		this.station = station;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((nbFreeSpaces == null) ? 0 : nbFreeSpaces.hashCode());
		result = prime * result + ((stopId == null) ? 0 : stopId.hashCode());
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
		Stop other = (Stop) obj;
		if (nbFreeSpaces == null) {
			if (other.nbFreeSpaces != null)
				return false;
		} else if (!nbFreeSpaces.equals(other.nbFreeSpaces))
			return false;
		if (stopId == null) {
			if (other.stopId != null)
				return false;
		} else if (!stopId.equals(other.stopId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Stop [stopId=" + stopId + ", nbFreeSpaces=" + nbFreeSpaces
				+ "]";
	}

}
