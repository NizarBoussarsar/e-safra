package ressources;

import javax.ejb.EJB;
import javax.json.Json;
import javax.json.JsonObject;

import services.interfaces.local.StationServicesLocal;
import domain.Station;

public class StationResources {

	@EJB
	StationServicesLocal stationServicesLocal;

	public StationResources() {

	}

	public JsonObject findStationById(Integer id) {
		Station station = stationServicesLocal.findStationById(id);
		return Json.createObjectBuilder().add("id", station.getId())
				.add("name", station.getName()).build();
	}

}
