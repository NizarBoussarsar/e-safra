package beans;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import services.interfaces.local.BusinessLogicServicesLocal;
import services.interfaces.local.StationServicesLocal;
import domain.Station;

@ManagedBean
@ViewScoped
public class StationBean {
	private List<Station> stations = new ArrayList<>();
	@EJB
	private BusinessLogicServicesLocal businessLogicServicesLocal;

	@EJB
	private StationServicesLocal stationServicesLocal;

	Station station = new Station();

	public String doSome() {
		return "";
	}

	public Station getStation() {
		return station;
	}

	public void setStation(Station station) {
		this.station = station;
	}

	public List<Station> getStations() {
		stations = stationServicesLocal.findAllStations();
		return stations;
	}

	public void setStations(List<Station> stations) {
		this.stations = stations;
	}

	public Station doFindStationByName(String name) {
		return businessLogicServicesLocal.findStationByName(name);
	}
}
