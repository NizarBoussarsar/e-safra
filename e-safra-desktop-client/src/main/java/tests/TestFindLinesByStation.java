package tests;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import services.interfaces.remote.BusinessLogicServicesRemote;
import services.interfaces.remote.StationServicesRemote;
import domain.Line;
import domain.Station;

public class TestFindLinesByStation {

	public static void main(String[] args) throws NamingException {
		Context context = new InitialContext();

		BusinessLogicServicesRemote businessLogicServicesRemote = (BusinessLogicServicesRemote) context
				.lookup("/e-safra-ejb/BusinessLogicServices!services.interfaces.remote.BusinessLogicServicesRemote");

		StationServicesRemote stationServicesRemote = (StationServicesRemote) context
				.lookup("/e-safra-ejb/StationServices!services.interfaces.remote.StationServicesRemote");

		System.out
				.println("Choose the station that you would like to show lines");

		List<Station> stations = stationServicesRemote.findAllStations();
		for (Integer i = 0; i < stations.size(); i++) {
			System.out.println(i + 1 + ": " + stations.get(i).getName());
		}

		@SuppressWarnings("resource")
		Scanner keyboard = new Scanner(System.in);
		Integer choice = keyboard.nextInt();
		List<Line> lines = new ArrayList<Line>();
		switch (choice) {
		case 1:
			lines = businessLogicServicesRemote.findLinesByStationId(1);
			for (Line line : lines) {
				System.out.println(line.getName());
			}
			break;
		case 2:
			lines = businessLogicServicesRemote.findLinesByStationId(2);
			for (Line line : lines) {
				System.out.println(line.getName());
			}
			break;
		case 3:
			lines = businessLogicServicesRemote.findLinesByStationId(3);
			for (Line line : lines) {
				System.out.println(line.getName());
			}
			break;
		case 4:
			lines = businessLogicServicesRemote.findLinesByStationId(4);
			for (Line line : lines) {
				System.out.println(line.getName());
			}
			break;
		case 5:
			lines = businessLogicServicesRemote.findLinesByStationId(5);
			for (Line line : lines) {
				System.out.println(line.getName());
			}
			break;
		case 6:
			lines = businessLogicServicesRemote.findLinesByStationId(6);
			for (Line line : lines) {
				System.out.println(line.getName());
			}
			break;
		case 7:
			lines = businessLogicServicesRemote.findLinesByStationId(7);
			for (Line line : lines) {
				System.out.println(line.getName());
			}
			break;
		case 8:
			lines = businessLogicServicesRemote.findLinesByStationId(8);
			for (Line line : lines) {
				System.out.println(line.getName());
			}
			break;
		case 9:
			lines = businessLogicServicesRemote.findLinesByStationId(9);
			for (Line line : lines) {
				System.out.println(line.getName());
			}
			break;
		default:
			System.out.println("This is not a good choice.");
			break;
		}
	}
}
