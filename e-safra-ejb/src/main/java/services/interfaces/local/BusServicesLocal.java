package services.interfaces.local;

import java.util.List;

import javax.ejb.Local;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import domain.Bus;

@Local
@Path("/bus")
public interface BusServicesLocal {

	Boolean addBus(Bus bus);

	Boolean deleteBus(Bus bus);

	Boolean deleteBusById(Integer id);

	Boolean updateBus(Bus bus);

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	Bus findBusById(@PathParam("id") Integer id);

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	List<Bus> findAllBuses();

}
