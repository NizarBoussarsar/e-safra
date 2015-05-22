package services.interfaces.local;

import java.util.List;
import java.util.Map;

import javax.ejb.Local;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import domain.Bus;
import domain.Line;
import domain.Passenger;
import domain.Section;
import domain.Station;
import domain.Stop;
import domain.Ticket;
import domain.Type;
import domain.User;

@Local
@Path("/service")
public interface BusinessLogicServicesLocal {

	User identification(String email, String password);

	Boolean changePassword(User user, String oldPassword, String newPassword,
			String confirmationPassword);

	// // At this stage, we don't need this one
	// Boolean profileEmailAlreadyExistsWithoutFacebookId(Profile profile);
	//
	// // At this stage, we don't need this one
	// Passenger accountRegisterOrLoginWithFacebook(Profile profile);

	Boolean setLineStations(Line line, Map<Integer, Station> stations);

	List<Station> findStationsByLineId(Integer id);

	Boolean reportBusStop(Integer nbFreePlaces, Bus bus, Station station);

	List<Line> findLinesByStationId(Integer id);

	List<Bus> findBusesByLineId(Integer id);

	Boolean setLineBuses(Line line, List<Bus> buses);

	List<Bus> findAllAvailableBuses();

	Integer findStationOrderByLineId(Integer idStation, Integer idLine);

	Stop findLastStopByBusId(Integer idBus);

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/coming-soon-buses")
	List<Bus> findComingSoonBuses(@QueryParam("station") String stationName);

	Bus findBusByNumber(String number);

	Station findStationByName(String name);

	Line findLineByName(String name);

	Section findSectionByRank(Integer rank);

	Ticket buyTicket(Passenger passenger, Bus bus, Station stationDeparture,
			Station stationArrival);

	Double getPriceBySectionNumber(Integer sectionNumber);

	List<Bus> findComingSoonBusesGoingToStation(String departureStationName,
			String arrivalStationName);

	Boolean assignSectionsToLine(Integer idLine, Map<Integer, Integer> map);

	Type findTypeByStationAndLine(Station station, Line line);

	User findUserByEmail(String email);
}
