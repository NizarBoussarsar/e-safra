package tests;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import services.interfaces.remote.BusServicesRemote;
import services.interfaces.remote.BusinessLogicServicesRemote;
import services.interfaces.remote.StationServicesRemote;
import domain.Bus;
import domain.Station;

public class TestReportBusStop {

	public static void main(String[] args) throws NamingException {
		Context context = new InitialContext();

		StationServicesRemote stationServicesRemote = (StationServicesRemote) context
				.lookup("/e-safra-boussarsar/StationServices!services.interfaces.StationServicesRemote");

		BusServicesRemote busServicesRemote = (BusServicesRemote) context
				.lookup("/e-safra-boussarsar/BusServices!services.interfaces.BusServicesRemote");

		BusinessLogicServicesRemote businessLogicServicesRemote = (BusinessLogicServicesRemote) context
				.lookup("/e-safra-boussarsar/BusinessLogicServices!services.interfaces.BusinessLogicServicesRemote");

		Bus bus1 = busServicesRemote.findBusById(1);
		// Bus bus2 = busServicesRemote.findBusById(2);
		// Bus bus3 = busServicesRemote.findBusById(3);
		// Bus bus4 = busServicesRemote.findBusById(4);
		// Bus bus5 = busServicesRemote.findBusById(5);
		// Bus bus6 = busServicesRemote.findBusById(6);
		// Bus bus7 = busServicesRemote.findBusById(7);
		// Bus bus8 = busServicesRemote.findBusById(8);
		Station station1 = stationServicesRemote.findStationById(1);
		Station station2 = stationServicesRemote.findStationById(2);
		Station station3 = stationServicesRemote.findStationById(3);
		Station station4 = stationServicesRemote.findStationById(4);
		// Station station5 = stationServicesRemote.findStationById(5);
		// Station station6 = stationServicesRemote.findStationById(6);
		// Station station7 = stationServicesRemote.findStationById(7);
		// Station station8 = stationServicesRemote.findStationById(8);
		// Station station9 = stationServicesRemote.findStationById(9);

		businessLogicServicesRemote.reportBusStop(5, bus1, station1);
		businessLogicServicesRemote.reportBusStop(4, bus1, station2);
		businessLogicServicesRemote.reportBusStop(6, bus1, station3);
		businessLogicServicesRemote.reportBusStop(1, bus1, station4);

	}

}
