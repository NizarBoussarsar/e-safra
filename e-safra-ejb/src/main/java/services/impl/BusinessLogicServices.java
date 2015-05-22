package services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import services.interfaces.local.BusServicesLocal;
import services.interfaces.local.BusinessLogicServicesLocal;
import services.interfaces.local.LineServicesLocal;
import services.interfaces.local.StationServicesLocal;
import services.interfaces.local.UserServicesLocal;
import services.interfaces.remote.BusinessLogicServicesRemote;
import domain.Bus;
import domain.Line;
import domain.Passenger;
import domain.Section;
import domain.Station;
import domain.Stop;
import domain.StopId;
import domain.Ticket;
import domain.Type;
import domain.TypeId;
import domain.User;

/**
 * Session Bean implementation class BusinessLogicServices
 */
@Stateless
public class BusinessLogicServices implements BusinessLogicServicesRemote,
		BusinessLogicServicesLocal {

	@PersistenceContext
	private EntityManager entityManager;

	@EJB
	private LineServicesLocal lineServicesLocal;

	@EJB
	private StationServicesLocal stationServicesLocal;

	@EJB
	private BusServicesLocal busServicesLocal;

	@EJB
	private UserServicesLocal userServicesLocal;

	/**
	 * Default constructor.
	 */
	public BusinessLogicServices() {

	}

	@Override
	public User identification(String email, String password) {
		try {
			String jpql = "select u from User u where u.email = :x and u.password = :y";
			Query query = entityManager.createQuery(jpql);
			query.setParameter("x", email);
			query.setParameter("y", password);
			return (User) query.getSingleResult();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	@Override
	public Boolean changePassword(User user, String oldPassword,
			String newPassword, String confirmationPassword) {
		Boolean b = false;
		try {
			entityManager.persist(user);
			if (user.getPassword() == oldPassword) {
				if (newPassword == confirmationPassword) {
					user.setPassword(newPassword);
					userServicesLocal.updateUser(user);
					b = true;
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return b;
	}

	// @Override
	// public Boolean profileEmailAlreadyExistsWithoutFacebookId(Profile
	// profile) {
	// User user;
	// Boolean b = false;
	// try {
	// String jpql =
	// "select u from User u where (u.email = :x and u.facebookId is null)";
	// Query query = entityManager.createQuery(jpql);
	// query.setParameter("x", profile.getEmail());
	// user = (User) query.getSingleResult();
	// if (user != null) {
	// System.out
	// .println("You have already been signed up by this email "
	// + "but not using Facebook ... Please try to log in "
	// + "using another Facebook Account");
	// } else {
	// b = true;
	// }
	// return b;
	// } catch (Exception e) {
	// System.out.println(e.getMessage());
	// return b;
	// }
	// }
	//
	// @Override
	// public Passenger accountRegisterOrLoginWithFacebook(Profile profile) {
	// Passenger passenger;
	// try {
	// if (!profileEmailAlreadyExistsWithoutFacebookId(profile)) {
	// String jpql1 = "select u from User u where u.facebookId = :x";
	// Query query1 = entityManager.createQuery(jpql1);
	// query1.setParameter("x", profile.getProviderId());
	// passenger = (Passenger) query1.getSingleResult();
	// if (passenger == null) {
	// System.out
	// .println("Creating a new passenger with a Facebook Account ..");
	// Date birthDay = new SimpleDateFormat("MM/dd/yyyy")
	// .parse(profile.getDob().getMonth() + "/"
	// + profile.getDob().getDay() + "/"
	// + profile.getDob().getYear());
	// Character gender = profile.getGender().toUpperCase()
	// .charAt(0);
	// String password = "changeme";
	// Double cash = 0D;
	// passenger = new Passenger(profile.getFirstName(),
	// profile.getLastName(), gender, birthDay,
	// profile.getEmail(), password, cash,
	// profile.getProviderId());
	// userServicesLocal.addUser(passenger);
	// System.out.println("Created ! Please change your password");
	// return passenger;
	// } else {
	// System.out
	// .println("This passenger have already signed up with Facebook ..");
	// return passenger;
	// }
	// }
	// return null;
	// } catch (Exception e) {
	// System.out.println(e.getMessage());
	// return null;
	// }
	// }

	@Override
	public Boolean setLineStations(Line line, Map<Integer, Station> stations) {
		Boolean b = false;
		try {
			entityManager.persist(line);
			String typeName = "";
			Integer firstTier = stations.size() / 3;
			Integer secondTier = (stations.size() / 3) * 2;
			Integer thirdTier = stations.size();
			for (int i = 0; i < stations.size(); i++) {
				if (i == 0) {
					typeName = "Departure Terminal";
				} else if (i == stations.size() - 1) {
					typeName = "Arrival Terminal";
				} else {
					typeName = "Intermediate Station";
				}
				Type type = new Type(new TypeId(line.getId(), stations.get(i)
						.getId()), typeName, i);
				if (i >= 0 & i <= firstTier)
					type.setSection(findSectionByRank(1));
				if (i > firstTier & i <= secondTier)
					type.setSection(findSectionByRank(2));
				if (i > secondTier & i <= thirdTier)
					type.setSection(findSectionByRank(3));
				entityManager.persist(type);
			}
			b = true;
			return b;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	@Override
	public Boolean setLineBuses(Line line, List<Bus> buses) {
		Boolean b = false;
		try {
			line.linkBusesToThisLine(buses);
			entityManager.merge(line);
			for (Bus bus : buses) {
				entityManager.merge(bus);
			}
			b = true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
		return b;
	}

	@Override
	public Boolean reportBusStop(Integer nbFreePlaces, Bus bus, Station station) {
		Boolean b = false;
		try {
			entityManager.merge(bus);
			entityManager.merge(station);
			Stop stop = new Stop(new StopId(bus.getId(), station.getId()),
					nbFreePlaces);
			stop.setBus(bus);
			stop.setStation(station);
			entityManager.persist(stop);
			b = true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return b;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Station> findStationsByLineId(Integer id) {
		List<Station> stations = new ArrayList<>();
		try {
			Line line = lineServicesLocal.findLineById(id);
			String jpql = "select s from Station s, Line l, Type t "
					+ "where t.station = s and t.line = l and t.line = :x order by t.stationOrder";
			// String jpql2 =
			// "SELECT s FROM Station s joins s.types ts WHERE ts.line = :x ORDER BY ts.stationOrder";
			Query query = entityManager.createQuery(jpql);
			query.setParameter("x", line);
			stations = query.getResultList();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
		return stations;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Line> findLinesByStationId(Integer id) {
		List<Line> lines = new ArrayList<>();
		try {
			Station station = stationServicesLocal.findStationById(id);
			String jpql = "select l from Station s, Line l, Type t "
					+ "where t.station = s and t.line = l and t.station = :x";
			// String jpql2 =
			// "SELECT l FROM Line l joins l.types ts WHERE ts.station = :x ";
			Query query = entityManager.createQuery(jpql);
			query.setParameter("x", station);
			lines = query.getResultList();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
		return lines;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Bus> findBusesByLineId(Integer id) {
		List<Bus> buses = new ArrayList<>();
		try {
			Line line = lineServicesLocal.findLineById(id);
			String jpql = "select b from Bus b where b.line = :param1";
			Query query = entityManager.createQuery(jpql);
			query.setParameter("param1", line);
			buses = query.getResultList();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
		return buses;
	}

	@SuppressWarnings("unchecked")
	public List<Bus> findAllAvailableBuses() {
		List<Bus> buses = new ArrayList<>();
		try {
			String jpql = "select b from Bus b where b.line is null";
			Query query = entityManager.createQuery(jpql);
			buses = query.getResultList();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
		return buses;
	}

	@Override
	public List<Bus> findComingSoonBuses(String stationName) {
		Station station = findStationByName(stationName);
		List<Bus> comingSoonBuses = new ArrayList<>();
		List<Line> lines = findLinesByStationId(station.getId());
		for (Line line : lines) {
			List<Bus> buses = findBusesByLineId(line.getId());
			for (Bus b : buses) {
				Stop lastOne = findLastStopByBusId(b.getId());
				if (lastOne != null) {
					Station lastStation = lastOne.getStation();
					Integer lastStationOrder = findStationOrderByLineId(
							lastStation.getId(), line.getId());
					Integer thisStationOrder = findStationOrderByLineId(
							station.getId(), line.getId());
					if (thisStationOrder != null && lastStationOrder != null
							&& lastStationOrder < thisStationOrder) {
						comingSoonBuses.add(b);
					}
				}
			}
		}
		return comingSoonBuses;
	}

	@Override
	public Stop findLastStopByBusId(Integer idBus) {
		Stop stop = null;
		try {
			String jpql = "select s from Stop s where s.bus.id = :param1 order by s.stopId.arrivalDate desc";
			TypedQuery<Stop> query = entityManager
					.createQuery(jpql, Stop.class)
					.setParameter("param1", idBus);
			List<Stop> stops = query.getResultList();
			if (stops.size() != 0) {
				stop = stops.get(0);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
		return stop;
	}

	@Override
	public Integer findStationOrderByLineId(Integer idStation, Integer idLine) {
		Integer order = null;
		try {
			String jpql = "select t.stationOrder from Type t where t.station.id = :param1 and t.line.id =:param2";
			TypedQuery<Integer> query = entityManager.createQuery(jpql,
					Integer.class);
			query.setParameter("param1", idStation);
			query.setParameter("param2", idLine);
			order = query.getSingleResult();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
		return order;
	}

	@Override
	public Bus findBusByNumber(String number) {
		Bus bus = null;
		try {
			String jpql = "select b from Bus b where b.number = :param1";
			Query query = entityManager.createQuery(jpql);
			query.setParameter("param1", number);
			bus = (Bus) query.getSingleResult();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
		return bus;
	}

	@Override
	public Station findStationByName(String name) {
		Station station = null;
		try {
			String jpql = "select s from Station s where s.name = :param1";
			Query query = entityManager.createQuery(jpql);
			query.setParameter("param1", name);
			station = (Station) query.getSingleResult();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
		return station;
	}

	@Override
	public Section findSectionByRank(Integer rank) {
		Section section = null;
		try {
			String jpql = "select s from Section s where s.rank = :param1";
			Query query = entityManager.createQuery(jpql);
			query.setParameter("param1", rank);
			section = (Section) query.getSingleResult();
		} catch (Exception e) {
			System.out.println("nizar");
			System.out.println(e.getMessage());
			return null;
		}
		return section;
	}

	@Override
	public Line findLineByName(String name) {
		Line line = null;
		try {
			String jpql = "select l from Line l where l.name = :param1";
			Query query = entityManager.createQuery(jpql);
			query.setParameter("param1", name);
			line = (Line) query.getSingleResult();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
		return line;
	}

	@Override
	public Double getPriceBySectionNumber(Integer sectionNumber) {
		Double price = 0D;
		switch (sectionNumber) {
		case 1:
			price = 320D;
			break;
		case 2:
			price = 470D;
			break;
		case 3:
			price = 650D;
			break;
		case 4:
			price = 800D;
			break;
		case 5:
			price = 970D;
			break;
		case 6:
			price = 1170D;
			break;
		case 7:
			price = 1350D;
			break;
		case 8:
			price = 1550D;
			break;
		default:
			break;
		}
		return price;
	}

	@Override
	public synchronized Boolean buyTicket(Passenger passenger, Bus bus,
			Station stationDeparture, Station stationArrival) {
		Boolean b = false;
		try {
			// entityManager.merge(passenger);
			// entityManager.merge(bus);
			// entityManager.merge(stationDeparture);
			// entityManager.merge(stationArrival);

			if (passenger != null && bus != null && stationDeparture != null
					&& stationArrival != null) {

				Type typeDeparture = entityManager.find(Type.class, new TypeId(
						bus.getLine().getId(), stationArrival.getId()));
				Type typeArrival = entityManager.find(Type.class, new TypeId(
						bus.getLine().getId(), stationArrival.getId()));
				Integer sectionNumber = (typeArrival.getSection().getRank() - typeDeparture
						.getSection().getRank()) + 1;
				Double price = getPriceBySectionNumber(sectionNumber);
				System.out.println("Price " + price);
				if (passenger.getCash() > price) {

					passenger.setCash(passenger.getCash() - price);
					Ticket ticket = new Ticket(price);

					ticket.setPassenger(passenger);
					ticket.setBus(bus);
					// Stop stop = entityManager.find(Stop.class,
					// new StopId(bus.getId(), stationDeparture.getId()));
					// stop.setNbFreeSpaces(stop.getNbFreeSpaces() - 1);

					// entityManager.merge(stop);
					entityManager.merge(passenger);
					entityManager.persist(ticket);
					b = true;
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return b;
	}

	@Override
	public List<Bus> findComingSoonBusesGoingToStation(
			String departureStationName, String arrivalStationName) {
		String arrivalTerminal = "Arrival Terminal";
		String intermideteStation = "Intermediate Station";
		List<Bus> busesComingToDepartureStation = findComingSoonBuses(departureStationName);
		List<Bus> busesGoingToArrivalStation = new ArrayList<Bus>();
		for (Bus bus : busesComingToDepartureStation) {
			List<Type> types = bus.getLine().getTypes();
			for (Type type : types) {
				if ((type.getStationType().equals(arrivalTerminal) || type
						.getStationType().equals(intermideteStation))
						&& type.getStation().getName()
								.equals(arrivalStationName)) {
					busesGoingToArrivalStation.add(bus);
				}
			}
		}
		return busesGoingToArrivalStation;
	}

	@Override
	public Boolean assignSectionToLine(Integer idLine,
			Map<Section, List<Station>> stationsMap) {
		Boolean b = false;
		try {
			for (Entry<Section, List<Station>> entry : stationsMap.entrySet()) {

				Section section = entry.getKey();
				List<Station> stations = entry.getValue();

				for (Station station : stations) {
					Type type = entityManager.find(Type.class, new TypeId(
							idLine, station.getId()));
					type.setSection(section);
					entityManager.persist(section);
				}
			}
			b = true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return b;

	}

	@Override
	public Type findTypeByStationAndLine(Station station, Line line) {
		return entityManager.find(Type.class,
				new TypeId(line.getId(), station.getId()));
	}
}
