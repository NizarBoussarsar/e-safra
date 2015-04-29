package tests;

import java.util.List;
import java.util.Random;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import services.interfaces.remote.BusinessLogicServicesRemote;
import domain.Bus;
import domain.Passenger;
import domain.Station;

public class TestFindComingSoonBusesGoingToStation {

	public static void main(String[] args) throws NamingException {

		Context context = new InitialContext();

		BusinessLogicServicesRemote businessLogicServicesRemote = (BusinessLogicServicesRemote) context
				.lookup("/e-safra-ejb/BusinessLogicServices!services.interfaces.remote.BusinessLogicServicesRemote");

		String email = "nizar.boussarsar@esprit.tn";
		String password = "esprit";

		Passenger passenger = (Passenger) businessLogicServicesRemote
				.identification(email, password);

		String departureStationName = "Les berges du lac";
		String arrivalStationName = "Marsa";

		List<Bus> buses = businessLogicServicesRemote
				.findComingSoonBusesGoingToStation(departureStationName,
						arrivalStationName);

		if (buses.size() > 0) {
			System.out.println("There are " + buses.size()
					+ " buses coming to " + departureStationName
					+ " and going to " + arrivalStationName + " !");

			for (Bus bus : buses) {
				System.out.println("Bus number : " + bus.getNumber());

				Random rand = new Random();

				Integer arrivalTime = rand.nextInt((10 - 5) + 1) + 5;
				System.out.println("Estimated arrival time : " + arrivalTime);
			}

		} else {
			System.out.println("There are no buses coming to "
					+ departureStationName + " and going to "
					+ arrivalStationName + " !");
		}

		Station departureStation = businessLogicServicesRemote
				.findStationByName(departureStationName);
		Station arrivalStation = businessLogicServicesRemote
				.findStationByName(arrivalStationName);

		System.out.println(businessLogicServicesRemote.buyTicket(passenger,
				departureStation, arrivalStation, buses.get(1), 0.1));

	}
}
