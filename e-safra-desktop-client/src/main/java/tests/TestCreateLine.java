package tests;

import java.util.HashMap;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import services.interfaces.remote.BusinessLogicServicesRemote;
import services.interfaces.remote.LineServicesRemote;
import services.interfaces.remote.StationServicesRemote;
import domain.Line;
import domain.Station;

public class TestCreateLine {

	public static void main(String[] args) throws NamingException {
		Context context = new InitialContext();

		BusinessLogicServicesRemote businessLogicServicesRemote = (BusinessLogicServicesRemote) context
				.lookup("/e-safra-boussarsar/BusinessLogicServices!services.interfaces.BusinessLogicServicesRemote");

		StationServicesRemote stationServicesRemote = (StationServicesRemote) context
				.lookup("/e-safra-boussarsar/StationServices!services.interfaces.StationServicesRemote");

		LineServicesRemote lineServicesRemote = (LineServicesRemote) context
				.lookup("/e-safra-boussarsar/LineServices!services.interfaces.LineServicesRemote");

		Station station = stationServicesRemote.findStationById(1);
		Station station2 = stationServicesRemote.findStationById(2);
		Station station3 = stationServicesRemote.findStationById(3);

		Map<Integer, Station> stations = new HashMap<Integer, Station>();

		stations.put(0, station);
		stations.put(1, station2);
		stations.put(2, station3);

		Line line = lineServicesRemote.findLineById(1);

		businessLogicServicesRemote.setLineStations(line, stations);

	}

}
