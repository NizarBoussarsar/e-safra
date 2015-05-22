package beans;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import services.interfaces.local.BusinessLogicServicesLocal;
import services.interfaces.local.StationServicesLocal;
import domain.Bus;
import domain.Passenger;
import domain.Station;
import domain.Ticket;

@ManagedBean
@SessionScoped
public class StationBean {
	private List<Station> stations = new ArrayList<>();
	private List<Station> stations2 = new ArrayList<>();
	private Boolean visibility = false;
	private Station stationDeparture = new Station();
	private Station stationArrival = new Station();
	private List<Bus> buses;
	private Bus busselected;
	private Station station = new Station();
	private Ticket ticket;
	Passenger passenger;

	public Passenger getPassenger() {
		return passenger;
	}

	public void setPassenger(Passenger passenger) {
		this.passenger = passenger;
	}

	@EJB
	private BusinessLogicServicesLocal businessLogicServicesLocal;

	@EJB
	private StationServicesLocal stationServicesLocal;

	@ManagedProperty("#{userBean}")
	private UserBean userBean;

	public UserBean getUserBean() {
		return userBean;
	}

	public String doSaveOrUpdate() {
		stationServicesLocal.updateStation(station);
		visibility = false;
		return "";
	}

	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}

	public String doSelectInCrud() {
		visibility = true;
		return "";
	}

	public String doSelect() {

		passenger = (Passenger) userBean.getUser();

		System.out.println("email Passenger" + passenger.getEmail());
		System.out.println("busselected" + busselected.getNumber());
		System.out.println("stationDeparture" + stationDeparture.getName());
		System.out.println("stationArrival" + stationDeparture.getName());

		ticket = businessLogicServicesLocal.buyTicket(passenger, busselected,
				stationDeparture, stationArrival);
		if (ticket != null) {
			return "/pages/passenger/ticket?faces-redirect=true";
		}
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

	public Boolean getVisibility() {
		return visibility;
	}

	public void setVisibility(Boolean visibility) {
		this.visibility = visibility;
	}

	public Ticket getTicket() {
		return ticket;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}

}
