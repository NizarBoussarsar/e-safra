package tests;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import services.interfaces.remote.BusinessLogicServicesRemote;
import domain.Bus;

public class TestFindComingSoonBuses {

	public static void main(String[] args) throws NamingException {
		String stationName = "Les berges du lac";

		Context context = new InitialContext();

		BusinessLogicServicesRemote businessLogicServicesRemote = (BusinessLogicServicesRemote) context
				.lookup("/e-safra-ejb/BusinessLogicServices!services.interfaces.remote.BusinessLogicServicesRemote");

		List<Bus> buses = businessLogicServicesRemote
				.findComingSoonBuses(stationName);

		if (buses.size() > 0) {
			System.out.println("There are " + buses.size()
					+ " buses that will pass by " + stationName + " soon !");
			for (Bus bus : buses) {
				System.out.println("Bus number : " + bus.getNumber());
			}
		} else {
			System.out.println("There are no buses that will pass by "
					+ stationName + " soon !");
		}

	}

}
