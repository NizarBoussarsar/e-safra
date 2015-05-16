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
import domain.Type;
import domain.TypeId;

/**
 * Session Bean implementation class DatabaseServices
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
	public void populate() {

		try {
			Driver driver1 = new Driver("Amine", "Jedidi", 'M',
					new SimpleDateFormat("MM/dd/yyyy").parse("03/04/1991"),
					"medamine.jedidi@esprit.tn", "esprit", 10, "10:00 AM");
			entityManager.persist(driver1);

			Driver driver2 = new Driver("Mohamed Amine", "Trabelsi", 'M',
					new SimpleDateFormat("MM/dd/yyyy").parse("01/02/1991"),
					"mohamedamine.trabelsi@esprit.tn", "esprit", 5, "00:00 PM");
			entityManager.persist(driver2);

			Driver driver3 = new Driver("Firas", "Turki", 'M',
					new SimpleDateFormat("MM/dd/yyyy").parse("24/07/1992"),
					"firas.turku@esprit.tn", "esprit", 5, "04:00 PM");
			entityManager.persist(driver3);

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

			Passenger passenger4 = new Passenger("Mohamed Amine", "Haj Frej",
					'M',
					new SimpleDateFormat("MM/dd/yyyy").parse("14/07/1991"),
					"mohamedamine.hajfrej@esprit.tn", "esprit", 77.9D, null);
			entityManager.persist(passenger4);

			Passenger passenger5 = new Passenger("Abdelhedi", "Belguith", 'M',
					new SimpleDateFormat("MM/dd/yyyy").parse("10/10/1991"),
					"abdelhedi.belghith@esprit.tn", "esprit", 44.4D, null);
			entityManager.persist(passenger5);

			BusManager busManager = new BusManager("Mohamed Ali", "Bettaieb",
					'M',
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

			Station marsa = new Station("Marsa");
			entityManager.persist(marsa);
			Station sidi_daoud = new Station("Sidi Daoud");
			entityManager.persist(sidi_daoud);
			Station la_goulette = new Station("La Goulette");
			entityManager.persist(la_goulette);
			Station rades = new Station("Rades");
			entityManager.persist(rades);
			Station megrine = new Station("Megrine");
			entityManager.persist(megrine);
			Station ain_zaghouan = new Station("Ain Zaghouan");
			entityManager.persist(ain_zaghouan);
			Station les_berges_du_lac = new Station("Les berges du lac");
			entityManager.persist(les_berges_du_lac);
			Station charguia = new Station("Charguia");
			entityManager.persist(charguia);
			Station borj_louzir = new Station("Borj Louzir");
			entityManager.persist(borj_louzir);

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
			List<Bus> listBuses5 = new ArrayList<>();
			listBuses5.add(bus9);
			listBuses5.add(bus10);

			Line marsaTOmegrine = new Line("Marsa - Megrine");
			marsaTOmegrine.linkBusesToThisLine(listBuses1);
			entityManager.persist(marsaTOmegrine);

			Line marsaTOborj_louzir = new Line("Marsa - Borj Louzir");
			marsaTOborj_louzir.linkBusesToThisLine(listBuses2);
			entityManager.persist(marsaTOborj_louzir);

			Line megrineTOmarsa = new Line("Megrine - Marsa");
			megrineTOmarsa.linkBusesToThisLine(listBuses3);
			entityManager.persist(megrineTOmarsa);

			Line borj_louzirTOsidi_daoud = new Line("Borj Louzir - Sidi Daoud");
			borj_louzirTOsidi_daoud.linkBusesToThisLine(listBuses4);
			entityManager.persist(borj_louzirTOsidi_daoud);

			Line charguiaTOmarsa = new Line("Charguia - Marsa");
			charguiaTOmarsa.linkBusesToThisLine(listBuses5);
			entityManager.persist(charguiaTOmarsa);

			// Link some specific stations to a specific line => Create a type
			Map<Integer, Station> stations1 = new HashMap<>();
			stations1.put(0, marsa);
			stations1.put(1, sidi_daoud);
			stations1.put(2, la_goulette);
			stations1.put(3, rades);
			stations1.put(4, megrine);
			businessLogicServicesLocal.setLineStations(marsaTOmegrine,
					stations1);

			Map<Integer, Station> stations2 = new HashMap<>();
			stations2.put(0, marsa);
			stations2.put(1, ain_zaghouan);
			stations2.put(2, les_berges_du_lac);
			stations2.put(3, charguia);
			stations2.put(4, borj_louzir);
			businessLogicServicesLocal.setLineStations(marsaTOborj_louzir,
					stations2);

			Map<Integer, Station> stations3 = new HashMap<>();
			stations3.put(0, megrine);
			stations3.put(1, rades);
			stations3.put(2, les_berges_du_lac);
			stations3.put(3, la_goulette);
			stations3.put(4, sidi_daoud);
			stations3.put(5, marsa);
			businessLogicServicesLocal.setLineStations(megrineTOmarsa,
					stations3);

			Map<Integer, Station> stations4 = new HashMap<>();
			stations4.put(0, borj_louzir);
			stations4.put(1, charguia);
			stations4.put(2, les_berges_du_lac);
			stations4.put(3, ain_zaghouan);
			stations4.put(4, marsa);
			stations4.put(5, sidi_daoud);
			businessLogicServicesLocal.setLineStations(borj_louzirTOsidi_daoud,
					stations4);

			Map<Integer, Station> stations5 = new HashMap<>();
			stations5.put(0, charguia);
			stations5.put(1, les_berges_du_lac);
			stations5.put(2, ain_zaghouan);
			stations5.put(3, marsa);
			businessLogicServicesLocal.setLineStations(charguiaTOmarsa,
					stations5);

			// Fetch some types
			Type type1 = entityManager.find(Type.class, new TypeId(1, 1));
			Type type2 = entityManager.find(Type.class, new TypeId(1, 2));
			Type type3 = entityManager.find(Type.class, new TypeId(1, 3));
			Type type4 = entityManager.find(Type.class, new TypeId(1, 4));
			Type type5 = entityManager.find(Type.class, new TypeId(1, 5));

			// Create some tickets and assign it to a passenger
			businessLogicServicesLocal.buyTicket(passenger2, bus1, type1,
					type2, 1.1D);

			// businessLogicServicesLocal.buyTicket(passenger2, bus1, type2,
			// 1.2D);
			//
			// businessLogicServicesLocal.buyTicket(passenger2, bus1, type3,
			// 1.3D);
			//
			// businessLogicServicesLocal.buyTicket(passenger2, bus1, type4,
			// 1.4D);
			//
			// businessLogicServicesLocal.buyTicket(passenger2, bus1, type5,
			// 1.5D);

			// Report some bus stops
			businessLogicServicesLocal.reportBusStop(2, bus3, ain_zaghouan);
			businessLogicServicesLocal.reportBusStop(0, bus5, rades);
			businessLogicServicesLocal.reportBusStop(1, bus7, charguia);

			// Assign driver to bus
			// bus3.setDrivers(driver1);
			// bus5.setDriver(driver2);
			// bus7.setDriver(driver3);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}
}