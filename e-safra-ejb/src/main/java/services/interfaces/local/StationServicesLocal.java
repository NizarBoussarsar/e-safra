package services.interfaces.local;

import java.util.List;

import javax.ejb.Local;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import domain.Station;

@Local
@Path("/station")
public interface StationServicesLocal {

	Boolean addStation(Station station);

	Boolean deleteStation(Station station);

	Boolean deleteStationById(Integer id);

	Boolean updateStation(Station station);

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	Station findStationById(@PathParam("id") Integer id);

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	List<Station> findAllStations();

}
