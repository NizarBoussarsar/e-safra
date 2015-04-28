package domain;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class TicketId implements Serializable {

	private Integer passangerId;
	private Integer busId;

	private static final long serialVersionUID = 1L;

	public TicketId() {
		super();
	}

	public TicketId(Integer passangerId, Integer busId) {
		super();
		this.passangerId = passangerId;
		this.busId = busId;
	}



	public Integer getPassangerId() {
		return passangerId;
	}

	public void setPassangerId(Integer passangerId) {
		this.passangerId = passangerId;
	}

	public Integer getBusId() {
		return busId;
	}

	public void setBusId(Integer busId) {
		this.busId = busId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((busId == null) ? 0 : busId.hashCode());
		result = prime * result
				+ ((passangerId == null) ? 0 : passangerId.hashCode());
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
		TicketId other = (TicketId) obj;
		if (busId == null) {
			if (other.busId != null)
				return false;
		} else if (!busId.equals(other.busId))
			return false;
		if (passangerId == null) {
			if (other.passangerId != null)
				return false;
		} else if (!passangerId.equals(other.passangerId))
			return false;
		return true;
	}

}
