package beans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import services.interfaces.local.BusServicesLocal;
import services.interfaces.local.BusinessLogicServicesLocal;
import services.interfaces.local.LineServicesLocal;
import services.interfaces.local.SectionServicesLocal;
import services.interfaces.local.StationServicesLocal;
import domain.Bus;
import domain.Line;
import domain.Section;
import domain.Station;

@ManagedBean
@ViewScoped
public class LineBean {

	@EJB
	private BusinessLogicServicesLocal businessLogicServicesLocal;

	@EJB
	private LineServicesLocal lineServicesLocal;

	@EJB
	private BusServicesLocal busServicesLocal;

	@EJB
	private StationServicesLocal stationServicesLocal;

	@EJB
	private SectionServicesLocal sectionServicesLocal;

	public LineBean() {
		// TODO Auto-generated constructor stub
	}

	private Line line;
	private List<Line> lines;
	private List<Bus> buses;
	private Map<Integer, Boolean> checked;
	private Map<Integer, Integer> selected;
	private List<Station> stations;
	private List<Station> stations2;
	private Map<Integer, Station> stationsMap;
	private Station selectedStation;
	private List<Section> sections;
	private Section section;

	@PostConstruct
	public void init() {
		lines = lineServicesLocal.findAllLines();
		buses = businessLogicServicesLocal.findAllAvailableBuses();
		stations = stationServicesLocal.findAllStations();
		stations2 = new ArrayList<>();
		sections = sectionServicesLocal.findAllSections();
		line = new Line();
		checked = new HashMap<Integer, Boolean>();
		stationsMap = new HashMap<>();
		selectedStation = new Station();
		section = new Section();
		selected = new HashMap<>();
	}

	public Line doFindLineByName(String name) {
		return businessLogicServicesLocal.findLineByName(name);
	}

	public void doSelectLineStation() {
		Integer i = stationsMap.size();
		stationsMap.put(i, selectedStation);
		stations.remove(selectedStation);
	}

	public String doCreateLine() {
		businessLogicServicesLocal.setLineStations(line, stationsMap);
		return "/pages/busManager/assignsectionstoline?faces-redirect=true";
	}

	public String doAssignBusesLine() {
		List<Bus> checkedBuses = new ArrayList<>();
		for (Bus bus : buses) {
			if (checked.get(bus.getId())) {
				checkedBuses.add(bus);
			}
		}
		businessLogicServicesLocal.setLineBuses(line, checkedBuses);
		return "";
	}

	public String doAssignSectionsToLine() {
		System.out.println("test : "
				+ businessLogicServicesLocal.assignSectionsToLine(line.getId(),
						selected));
		return "";
	}

	public String doUpdateListStations() {
		stations2 = businessLogicServicesLocal.findStationsByLineId(line
				.getId());
		return "";
	}

	public Line getLine() {
		return line;
	}

	public void setLine(Line line) {
		this.line = line;
	}

	public List<Line> getLines() {
		return lines;
	}

	public void setLines(List<Line> lines) {
		this.lines = lines;
	}

	public List<Bus> getBuses() {
		return buses;
	}

	public void setBuses(List<Bus> buses) {
		this.buses = buses;
	}

	public Map<Integer, Boolean> getChecked() {
		return checked;
	}

	public void setChecked(Map<Integer, Boolean> checked) {
		this.checked = checked;
	}

	public Map<Integer, Station> getstationsMap() {
		return stationsMap;
	}

	public void setstationsMap(Map<Integer, Station> stationsMap) {
		this.stationsMap = stationsMap;
	}

	public List<Station> getStations() {
		return stations;
	}

	public void setStations(List<Station> stations) {
		this.stations = stations;
	}

	public Map<Integer, Station> getStationsMap() {
		return stationsMap;
	}

	public void setStationsMap(Map<Integer, Station> stationsMap) {
		this.stationsMap = stationsMap;
	}

	public Station getSelectedStation() {
		return selectedStation;
	}

	public void setSelectedStation(Station selectedStation) {
		this.selectedStation = selectedStation;
	}

	public List<Section> getSections() {
		return sections;
	}

	public void setSections(List<Section> sections) {
		this.sections = sections;
	}

	public Section getSection() {
		return section;
	}

	public void setSection(Section section) {
		this.section = section;
	}

	public List<Station> getStations2() {
		return stations2;
	}

	public void setStations2(List<Station> stations2) {
		this.stations2 = stations2;
	}

	public Map<Integer, Integer> getSelected() {
		return selected;
	}

	public void setSelected(Map<Integer, Integer> selected) {
		this.selected = selected;
	}

}