package beans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import services.interfaces.local.BusServicesLocal;
import services.interfaces.local.BusinessLogicServicesLocal;
import services.interfaces.local.LineServicesLocal;
import services.interfaces.local.StationServicesLocal;
import domain.Bus;
import domain.Line;
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

	// Old
	// private Integer idSelectedLine;
	// private List<Line> lines = new ArrayList<>();
	// private List<SelectItem> items = new ArrayList<>();
	//
	// public Integer getIdSelectedLine() {
	// return idSelectedLine;
	// }
	//
	// public void setIdSelectedLine(Integer idSelectedLine) {
	// this.idSelectedLine = idSelectedLine;
	// }
	//
	// public List<Line> getLines() {
	// return lines;
	// }
	//
	// public void setLines(List<Line> lines) {
	// this.lines = lines;
	// }
	//
	// public List<SelectItem> getItems() {
	// lines = lineServicesLocal.findAllLines();
	// items = new ArrayList<SelectItem>(lines.size() + 1);
	// // On veut afficher dans le selectOneMenu les lignes el kol w nzidou
	// // zéda un message par défaut
	// items.add(new SelectItem(-1, "Please select a line .. "));
	// for (Line line : lines) {
	// items.add(new SelectItem(line.getId(), line.getName()));
	// }
	// return items;
	// }
	//
	// public void setItems(List<SelectItem> items) {
	// this.items = items;
	// }
	//
	// public Line getSelectedLineOld() {
	// return lineServicesLocal.findLineById(idSelectedLine);
	// }
	// Old

	// assignBusesToLine
	private Line line = new Line();
	private List<Line> lines = new ArrayList<>();
	private List<Bus> buses = new ArrayList<>();
	private Map<Integer, Boolean> checked = new HashMap<Integer, Boolean>();

	public String doAssignBusesLine() {
		List<Bus> checkedBuses = new ArrayList<>();
		for (Bus bus : buses) {
			if (checked.get(bus.getId())) {
				checkedBuses.add(bus);
			}
		}
		Line selectedLine = findSelectedLine();
		businessLogicServicesLocal.setLineBuses(selectedLine, checkedBuses);
		return "";
	}

	public Line getLine() {
		return line;
	}

	public void setLine(Line line) {
		this.line = line;
	}

	public List<Line> getLines() {
		lines = lineServicesLocal.findAllLines();
		return lines;
	}

	public void setLines(List<Line> lines) {
		this.lines = lines;
	}

	public List<Bus> getBuses() {
		buses = businessLogicServicesLocal.findAllAvailableBuses();
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

	public Line findSelectedLine() {
		return lineServicesLocal.findLineById(getLine().getId());
	}

	public Line doFindLineByName(String name) {
		return businessLogicServicesLocal.findLineByName(name);
	}

	// assignBusesToLine

	// createLine
	private List<Station> stations = new ArrayList<>();
	private DataModel<Station> stationsDataModel = new ListDataModel<>();
	private Map<Integer, Station> stationsMap = new HashMap<>();

	// Le DataModel<Station> est un enveloppe pour la List<Station> car la
	// classe List<> ne peut pas etre manipulé coté client

	public String doSelectLineStation() {
		Integer i = stationsMap.size();
		stationsMap.put(i, stationsDataModel.getRowData());
		System.out.println(stationsMap.get(i));
		return "";
		// dataModel.getRowData() : Retourne la ligne sélectionnée par
		// l'utilisateur
	}

	public String doCreateLine() {
		businessLogicServicesLocal.setLineStations(line, stationsMap);
		return "";
	}

	public List<Station> getStations() {
		stations = stationServicesLocal.findAllStations();
		return stations;
	}

	public void setStations(List<Station> stations) {
		this.stations = stations;
	}

	public DataModel<Station> getStationsDataModel() {
		stationsDataModel.setWrappedData(this.getStations());
		return stationsDataModel;
	}

	public void setStationsDataModel(DataModel<Station> stationsDataModel) {
		this.stationsDataModel = stationsDataModel;
	}

	public Map<Integer, Station> getStationsMap() {
		return stationsMap;
	}

	public void setStationsMap(Map<Integer, Station> stationsMap) {
		this.stationsMap = stationsMap;
	}
	// createLine
	
}