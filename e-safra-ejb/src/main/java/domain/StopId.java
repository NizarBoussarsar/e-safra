package domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Embeddable;

@Embeddable
public class StopId implements Serializable {

	private Integer idBus;
	private Integer idStation;
	private Date arrivalDate;

	private static final long serialVersionUID = 1L;

	public StopId() {
		super();
	}

	public StopId(Integer idBus, Integer idStation) {
		super();
		this.idBus = idBus;
		this.idStation = idStation;
		this.arrivalDate = new Date();
	}

	public Integer getIdBus() {
		return idBus;
	}

	public void setIdBus(Integer idBus) {
		this.idBus = idBus;
	}

	public Integer getIdStation() {
		return idStation;
	}

	public void setIdStation(Integer idStation) {
		this.idStation = idStation;
	}

	public Date getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(Date arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idBus == null) ? 0 : idBus.hashCode());
		result = prime * result
				+ ((idStation == null) ? 0 : idStation.hashCode());
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
		StopId other = (StopId) obj;
		if (idBus == null) {
			if (other.idBus != null)
				return false;
		} else if (!idBus.equals(other.idBus))
			return false;
		if (idStation == null) {
			if (other.idStation != null)
				return false;
		} else if (!idStation.equals(other.idStation))
			return false;
		return true;
	}

}
