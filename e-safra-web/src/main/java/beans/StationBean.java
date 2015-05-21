<<<<<<< HEAD
package beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import services.interfaces.local.BusinessLogicServicesLocal;
import services.interfaces.local.StationServicesLocal;
import domain.Bus;
import domain.Passenger;
import domain.Section;
import domain.Station;
import domain.Type;

@ManagedBean
@ViewScoped
public class StationBean {
	private List<Station> stations = new ArrayList<>();
	private List<Station> stations2 = new ArrayList<>();
	private Station stationDeparture = new Station();
	private Station stationArrival = new Station();
	private Date date1;
	private List<Bus> buses;
	private Bus busselected;

	@EJB
	private BusinessLogicServicesLocal businessLogicServicesLocal;

	@EJB
	private StationServicesLocal stationServicesLocal;

	@ManagedProperty("#{userBean}")
	private UserBean userBean;

	public UserBean getUserBean() {
		return userBean;
	}

	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}

	Station station = new Station();

	public String doSome() {
		return "";
	}

	public String doSelect() {
		List<Section> sections = businessLogicServicesLocal
				.findSectionByStationsAndLine(stationDeparture, stationArrival,
						busselected.getLine());
		Double price = (double) (sections.size() * 600);
		Type type1 = businessLogicServicesLocal.findTypeByStationAndLine(
				stationDeparture, busselected.getLine());
		Type type2 = businessLogicServicesLocal.findTypeByStationAndLine(
				stationArrival, busselected.getLine());
		System.out.println(businessLogicServicesLocal.buyTicket(
				(Passenger) userBean.getUser(), busselected, type1, type2,
				price));

		return "";
	}

	public String doSearchBus() {

		buses = businessLogicServicesLocal.findComingSoonBusesGoingToStation(
				stationDeparture.getName(), stationArrival.getName());
		System.out.println("size : " + buses.size());

		return "";

	}

	public Station getStationDeparture() {
		return stationDeparture;
	}

	public void setStationDeparture(Station stationDeparture) {
		this.stationDeparture = stationDeparture;
	}

	public Station getStationArrival() {
		return stationArrival;
	}

	public void setStationArrival(Station stationArrival) {
		this.stationArrival = stationArrival;
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

	public List<Station> getStations2() {
		stations2 = stationServicesLocal.findAllStations();
		return stations2;
	}

	public void setStations2(List<Station> stations2) {
		this.stations2 = stations2;
	}

	public Date getDate1() {
		return date1;
	}

	public void setDate1(Date date1) {
		this.date1 = date1;
	}

	public List<Bus> getBuses() {
		return buses;
	}

	public void setBuses(List<Bus> buses) {
		this.buses = buses;
	}

	public Bus getBusselected() {
		return busselected;
	}

	public void setBusselected(Bus busselected) {
		this.busselected = busselected;
	}

}
=======
package beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import services.interfaces.local.BusinessLogicServicesLocal;
import services.interfaces.local.StationServicesLocal;
import domain.Bus;
import domain.Station;

@ManagedBean
@ViewScoped
public class StationBean {
	private List<Station> stations = new ArrayList<>();
	private List<Station> stations2 = new ArrayList<>();
	private Station stationDeparture = new Station();
	private Station stationArrival = new Station();
	private Date date1;
	private List<Bus> buses;
	private Bus busselected;
	
	@EJB
	private BusinessLogicServicesLocal businessLogicServicesLocal;

	@EJB
	private StationServicesLocal stationServicesLocal;

	@ManagedProperty("#{userBean}")
	private UserBean userBean;

	public UserBean getUserBean() {
		return userBean;
	}

	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}

	Station station = new Station();

	public String doSome() {
		return "";
	}

	public String doSelect() {
		
		System.out.println("Bus selected=" + busselected.getNumber());
		System.out.println("Selected line=" + busselected.getLine().getName());
		System.out.println("User connected="
				+ userBean.getUser().getFirstName());
		System.out.println("Departure=" + stationDeparture);
		System.out.println("Arrival=" + stationArrival);

		return "";
	}

	public String doSearchBus() {

		buses = businessLogicServicesLocal.findComingSoonBusesGoingToStation(
				stationDeparture.getName(), stationArrival.getName());
		System.out.println("size : " + buses.size());

		return "";

	}

	public Station getStationDeparture() {
		return stationDeparture;
	}

	public void setStationDeparture(Station stationDeparture) {
		this.stationDeparture = stationDeparture;
	}

	public Station getStationArrival() {
		return stationArrival;
	}

	public void setStationArrival(Station stationArrival) {
		this.stationArrival = stationArrival;
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

	public List<Station> getStations2() {
		stations2 = stationServicesLocal.findAllStations();
		return stations2;
	}

	public void setStations2(List<Station> stations2) {
		this.stations2 = stations2;
	}

	public Date getDate1() {
		return date1;
	}

	public void setDate1(Date date1) {
		this.date1 = date1;
	}

	public List<Bus> getBuses() {
		return buses;
	}

	public void setBuses(List<Bus> buses) {
		this.buses = buses;
	}

	public Bus getBusselected() {
		return busselected;
	}

	public void setBusselected(Bus busselected) {
		this.busselected = busselected;
	}

}
>>>>>>> parent of cb0a130... 
