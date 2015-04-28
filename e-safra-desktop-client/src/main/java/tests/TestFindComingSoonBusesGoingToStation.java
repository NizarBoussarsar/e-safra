package tests;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import services.interfaces.remote.BusinessLogicServicesRemote;
import domain.Bus;

public class TestFindComingSoonBusesGoingToStation {

	public static void main(String[] args) throws NamingException {
		String departureStationName = "Les berges du lac";
		String arrivalStationName = "Marsa";

		Context context = new InitialContext();

		BusinessLogicServicesRemote businessLogicServicesRemote = (BusinessLogicServicesRemote) context
				.lookup("/e-safra-ejb/BusinessLogicServices!services.interfaces.remote.BusinessLogicServicesRemote");

		List<Bus> buses = businessLogicServicesRemote
				.findComingSoonBusesGoingToStation(departureStationName,
						arrivalStationName);

		if (buses.size() > 0) {
			System.out.println("ok");
		}

	}

}
