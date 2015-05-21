package beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import services.interfaces.local.BusinessLogicServicesLocal;
import services.interfaces.local.LineServicesLocal;
import services.interfaces.local.StationServicesLocal;
import domain.Line;
import domain.Station;

@ManagedBean
@ViewScoped
public class MapBean {

	@EJB
	private LineServicesLocal lineServicesLocal;

	@EJB
	private StationServicesLocal stationServicesLocal;

	@EJB
	private BusinessLogicServicesLocal businessLogicServicesLocal;

	private Station stationStart;
	private Station stationFinal;
	private List<Station> stationsList;
	private Line line; // 1

	private String stationsDeclare;
	private String startStationDeclare;
	private String endStationDeclare;
	private String pushString;

	private String myLat, myLan;
	private String firstStation, lastStation;

	public MapBean() {
	}

	@PostConstruct
	public void Init() {
		stationsDeclare = "";
		startStationDeclare = "";
		pushString = "";

		stationsList = businessLogicServicesLocal.findStationsByLineId(1);

		myLat = stationsList.get(0).getLatitude();
		myLan = stationsList.get(0).getLongitude();

		firstStation = stationsList.get(0).getName().replaceAll("\\s", "");
		lastStation = stationsList.get(stationsList.size() - 1).getName()
				.replaceAll("\\s", "");

		for (Station station : stationsList) {

			stationsDeclare += "var " + station.getName().replaceAll("\\s", "")
					+ "= new google.maps.LatLng(" + station.getLatitude()
					+ ", " + station.getLongitude() + "); \n";
			/********/
			startStationDeclare += "var "
					+ station.getName().replaceAll("\\s", "")
					+ "Marker = new google.maps.Marker({ position :"
					+ station.getName().replaceAll("\\s", "")
					+ ", map : map, title : '" + stationsList.get(0).getName()
					+ "', icon : image }); \n";
			/*********/

			if ((station.getId() != stationsList.get(0).getId())
					&& (station.getId() != stationsList.get(
							stationsList.size() - 1).getId())) {

				pushString += "	waypts.push({location : "
						+ station.getName().replaceAll("\\s", "")
						+ ", stopover : true }); \n";
			}

		}

	}


	public StationServicesLocal getStationServicesLocal() {
		return stationServicesLocal;
	}

	public void setStationServicesLocal(
			StationServicesLocal stationServicesLocal) {
		this.stationServicesLocal = stationServicesLocal;
	}

	public Station getStationStart() {
		return stationStart;
	}

	public void setStationStart(Station stationStart) {
		this.stationStart = stationStart;
	}

	public Station getStationFinal() {
		return stationFinal;
	}

	public void setStationFinal(Station stationFinal) {
		this.stationFinal = stationFinal;
	}

	public List<Station> getStationsList() {
		return stationsList;
	}

	public void setStationsList(List<Station> stationsList) {
		this.stationsList = stationsList;
	}

	public LineServicesLocal getLineServicesLocal() {
		return lineServicesLocal;
	}

	public void setLineServicesLocal(LineServicesLocal lineServicesLocal) {
		this.lineServicesLocal = lineServicesLocal;
	}

	public Line getLine() {
		return line;
	}

	public void setLine(Line line) {
		this.line = line;
	}

	public String getStationsDeclare() {
		return stationsDeclare;
	}

	public void setStationsDeclare(String stationsDeclare) {
		this.stationsDeclare = stationsDeclare;
	}

	public String getStartStationDeclare() {
		return startStationDeclare;
	}

	public void setStartStationDeclare(String startStationDeclare) {
		this.startStationDeclare = startStationDeclare;
	}

	public String getEndStationDeclare() {
		return endStationDeclare;
	}

	public void setEndStationDeclare(String endStationDeclare) {
		this.endStationDeclare = endStationDeclare;
	}

	public String getMyLat() {
		return myLat;
	}

	public void setMyLat(String myLat) {
		this.myLat = myLat;
	}

	public String getMyLan() {
		return myLan;
	}

	public void setMyLan(String myLan) {
		this.myLan = myLan;
	}

	public String getPushString() {
		return pushString;
	}

	public void setPushString(String pushString) {
		this.pushString = pushString;
	}

	public String getFirstStation() {
		return firstStation;
	}

	public void setFirstStation(String firstStation) {
		this.firstStation = firstStation;
	}

	public String getLastStation() {
		return lastStation;
	}

	public void setLastStation(String lastStation) {
		this.lastStation = lastStation;
	}

}
