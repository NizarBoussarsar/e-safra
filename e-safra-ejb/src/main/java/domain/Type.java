package domain;

import java.io.Serializable;

import javax.persistence.CascadeType;
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
	private Section section;

	public Type() {
		super();
	}

	public Type(TypeId typeId, String stationType, Integer stationOrder) {
		super();
		this.typeId = new TypeId(line.getId(), station.getId());
		this.stationType = stationType;
		this.stationOrder = stationOrder;
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

	@ManyToOne(cascade = CascadeType.MERGE)
	public Section getSection() {
		return section;
	}

	public void setSection(Section section) {
		this.section = section;
	}

}
