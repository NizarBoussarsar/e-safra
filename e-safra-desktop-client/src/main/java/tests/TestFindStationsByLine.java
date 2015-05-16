package tests;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import services.interfaces.remote.BusinessLogicServicesRemote;
import services.interfaces.remote.LineServicesRemote;
import domain.Line;
import domain.Station;

public class TestFindStationsByLine {

	public static void main(String[] args) throws NamingException {
		Context context = new InitialContext();

		BusinessLogicServicesRemote businessLogicServicesRemote = (BusinessLogicServicesRemote) context
				.lookup("/e-safra-boussarsar/BusinessLogicServices!services.interfaces.BusinessLogicServicesRemote");

		LineServicesRemote lineServicesRemote = (LineServicesRemote) context
				.lookup("/e-safra-boussarsar/LineServices!services.interfaces.LineServicesRemote");

		System.out
				.println("Choose the line that you would like to detail stations");
		
		List<Line> lines = lineServicesRemote.findAllLines();
		for (Integer i = 0; i < lines.size(); i++) {
			System.out.println(i + 1 + ": " + lines.get(i).getName());
		}

		@SuppressWarnings("resource")
		Scanner keyboard = new Scanner(System.in);
		Integer choice = keyboard.nextInt();
		List<Station> stations = new ArrayList<Station>();
		switch (choice) {
		case 1:
			stations = businessLogicServicesRemote.findStationsByLineId(1);
			for (Station station : stations) {
				System.out.println(station.getName());
			}
			break;
		case 2:
			stations = businessLogicServicesRemote.findStationsByLineId(2);
			for (Station station : stations) {
				System.out.println(station.getName());
			}
			break;
		case 3:
			stations = businessLogicServicesRemote.findStationsByLineId(3);
			for (Station station : stations) {
				System.out.println(station.getName());
			}
			break;
		case 4:
			stations = businessLogicServicesRemote.findStationsByLineId(4);
			for (Station station : stations) {
				System.out.println(station.getName());
			}
			break;
		default:
			System.out.println("This is not a good choice.");
			break;
		}
	}
}
