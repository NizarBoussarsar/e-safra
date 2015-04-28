package utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import services.interfaces.local.BusinessLogicServicesLocal;
import domain.Bus;
import domain.BusManager;
import domain.Driver;
import domain.Line;
import domain.Passenger;
import domain.Station;

/**
 * Session Bean implementation class DBPopulation
 */
@Singleton
@Startup
public class DatabaseServices {

	@PersistenceContext
	private EntityManager entityManager;

	@EJB
	BusinessLogicServicesLocal businessLogicServicesLocal;

	public DatabaseServices() {

	}

	@PostConstruct
	public void populate() throws Exception {
		Driver driver1 = new Driver("Amine", "Jedidi", 'M',
				new SimpleDateFormat("MM/dd/yyyy").parse("03/04/1991"),
				"medamine.jedidi@esprit.tn", "esprit", 10, "10:00 AM");
		entityManager.persist(driver1);

		Driver driver2 = new Driver("Mohamed Amine", "Trabelsi", 'M',
				new SimpleDateFormat("MM/dd/yyyy").parse("01/02/1991"),
				"mohamedamine.trabelsi@esprit.tn", "esprit", 5, "00:00 PM");
		entityManager.persist(driver2);

		Passenger passenger1 = new Passenger("Nizar", "Boussarsar", 'M',
				new SimpleDateFormat("MM/dd/yyyy").parse("02/16/1991"),
				"nizar.boussarsar@esprit.tn", "esprit", 0.2D, null);
		entityManager.persist(passenger1);

		Passenger passenger2 = new Passenger("Youssef", "Kamoun", 'M',
				new SimpleDateFormat("MM/dd/yyyy").parse("19/09/1991"),
				"youssef.kamoun@esprit.tn", "esprit", 100D, null);
		entityManager.persist(passenger2);

		Passenger passenger3 = new Passenger("Raed", "Chammem", 'M',
				new SimpleDateFormat("MM/dd/yyyy").parse("05/12/1992"),
				"raed.chammem@esprit.tn", "esprit", 38.3D, null);
		entityManager.persist(passenger3);

		Passenger passenger4 = new Passenger("Mohamed Amine", "Haj Frej", 'M',
				new SimpleDateFormat("MM/dd/yyyy").parse("14/07/1991"),
				"mohamedamine.hajfrej@esprit.tn", "esprit", 77.9D, null);
		entityManager.persist(passenger4);

		Passenger passenger5 = new Passenger("Abdelhedi", "Belguith", 'M',
				new SimpleDateFormat("MM/dd/yyyy").parse("10/10/1991"),
				"abdelhedi.belghith@esprit.tn", "esprit", 44.4D, null);
		entityManager.persist(passenger5);

		BusManager busManager = new BusManager("Mohamed Ali", "Bettaieb", 'M',
				new SimpleDateFormat("MM/dd/yyyy").parse("01/03/1982"),
				"medali.bettaieb@esprit.tn", "esprit", 1);
		entityManager.persist(busManager);

		Bus bus1 = new Bus("B01");
		entityManager.persist(bus1);
		Bus bus2 = new Bus("B02");
		entityManager.persist(bus2);
		Bus bus3 = new Bus("B03");
		entityManager.persist(bus3);
		Bus bus4 = new Bus("B04");
		entityManager.persist(bus4);
		Bus bus5 = new Bus("B05");
		entityManager.persist(bus5);
		Bus bus6 = new Bus("B06");
		entityManager.persist(bus6);
		Bus bus7 = new Bus("B07");
		entityManager.persist(bus7);
		Bus bus8 = new Bus("B08");
		entityManager.persist(bus8);
		Bus bus9 = new Bus("B09");
		entityManager.persist(bus9);
		Bus bus10 = new Bus("B10");
		entityManager.persist(bus10);
		Bus bus11 = new Bus("B11");
		entityManager.persist(bus11);
		Bus bus12 = new Bus("B12");
		entityManager.persist(bus12);
		Bus bus13 = new Bus("B13");
		entityManager.persist(bus13);
		Bus bus14 = new Bus("B14");
		entityManager.persist(bus14);
		Bus bus15 = new Bus("B15");
		entityManager.persist(bus15);
		Bus bus16 = new Bus("B16");
		entityManager.persist(bus16);

		Station station1 = new Station("Marsa");
		entityManager.persist(station1);
		Station station2 = new Station("Sidi Daoud");
		entityManager.persist(station2);
		Station station3 = new Station("La Goulette");
		entityManager.persist(station3);
		Station station4 = new Station("Rades");
		entityManager.persist(station4);
		Station station5 = new Station("Megrine");
		entityManager.persist(station5);
		Station station6 = new Station("Ain Zaghouan");
		entityManager.persist(station6);
		Station station7 = new Station("Les berges du lac");
		entityManager.persist(station7);
		Station station8 = new Station("Charguia");
		entityManager.persist(station8);
		Station station9 = new Station("Borj Louzir");
		entityManager.persist(station9);

		List<Bus> listBuses1 = new ArrayList<>();
		listBuses1.add(bus1);
		listBuses1.add(bus2);
		List<Bus> listBuses2 = new ArrayList<>();
		listBuses2.add(bus3);
		listBuses2.add(bus4);
		List<Bus> listBuses3 = new ArrayList<>();
		listBuses3.add(bus5);
		listBuses3.add(bus6);
		List<Bus> listBuses4 = new ArrayList<>();
		listBuses4.add(bus7);
		listBuses4.add(bus8);

		Line line1 = new Line("Marsa - Megrine");
		line1.linkBusesToThisLine(listBuses1);
		entityManager.persist(line1);

		Line line2 = new Line("Marsa - Borj Louzir");
		line2.linkBusesToThisLine(listBuses2);
		entityManager.persist(line2);

		Line line3 = new Line("Megrine - Marsa");
		line3.linkBusesToThisLine(listBuses3);
		entityManager.persist(line3);

		Line line4 = new Line("Borj Louzir - Marsa");
		line4.linkBusesToThisLine(listBuses4);
		entityManager.persist(line4);

		// Link some specific stations to a specific line
		Map<Integer, Station> stations1 = new HashMap<>();
		stations1.put(0, station1); // Marsa
		stations1.put(1, station2); // Sidi Daoud
		stations1.put(2, station3); // La Goulette
		stations1.put(3, station4); // Rades
		stations1.put(4, station5); // Megrin
		businessLogicServicesLocal.setLineStations(line1, stations1);

		// Link some specific stations to a specific line
		Map<Integer, Station> stations2 = new HashMap<>();
		stations2.put(0, station1); // Marsa
		stations2.put(1, station6); // Ain Zaghouan
		stations2.put(2, station7); // Les berges du lac
		stations2.put(3, station8); // Charguia
		stations2.put(4, station9); // Borj Louzir
		businessLogicServicesLocal.setLineStations(line2, stations2);

		// Link some specific stations to a specific line
		Map<Integer, Station> stations3 = new HashMap<>();
		stations3.put(0, station5); // Megrin
		stations3.put(1, station4); // Rades
		stations3.put(2, station3); // La Goulette
		stations3.put(3, station2); // Sidi Daoud
		stations3.put(4, station1); // Marsa
		businessLogicServicesLocal.setLineStations(line3, stations3);

		// Link some specific stations to a specific line
		Map<Integer, Station> stations4 = new HashMap<>();
		stations4.put(0, station9); // Borj Louzir
		stations4.put(1, station8); // Charguia
		stations4.put(2, station7); // Les berges du lac
		stations4.put(3, station6); // Ain Zaghouan
		stations4.put(4, station1); // Marsa
		businessLogicServicesLocal.setLineStations(line4, stations4);

		// // Create a ticket and assign it to a passenger
		// businessLogicServicesLocal.buyTicket(passenger2, bus1, 2D);
		// businessLogicServicesLocal.buyTicket(passenger3, bus1, 2D);
		// businessLogicServicesLocal.buyTicket(passenger4, bus1, 2D);

		// Report some bus stops
		businessLogicServicesLocal.reportBusStop(0, bus3, station4);
		businessLogicServicesLocal.reportBusStop(1, bus1, station1);
		businessLogicServicesLocal.reportBusStop(2, bus2, station1);
		businessLogicServicesLocal.reportBusStop(3, bus1, station2);
		businessLogicServicesLocal.reportBusStop(4, bus2, station2);
		businessLogicServicesLocal.reportBusStop(0, bus2, station3);

	}
}