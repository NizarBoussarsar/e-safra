package services.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import services.interfaces.local.StationServicesLocal;
import services.interfaces.remote.StationServicesRemote;
import domain.Station;

/**
 * Session Bean implementation class StationServices
 */
@Stateless
public class StationServices implements StationServicesRemote,
		StationServicesLocal {

	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Default constructor.
	 */
	public StationServices() {

	}

	@Override
	public Boolean addStation(Station station) {
		Boolean b = false;
		try {
			entityManager.persist(station);
			b = true;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return b;
	}

	@Override
	public Boolean deleteStation(Station station) {
		Boolean b = false;
		try {
			entityManager.remove(entityManager.merge(station));
			b = true;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return b;
	}

	@Override
	public Boolean deleteStationById(Integer id) {
		Boolean b = false;
		try {
			Station station = findStationById(id);
			entityManager.remove(station);
			b = true;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return b;
	}

	@Override
	public Boolean updateStation(Station station) {
		Boolean b = false;
		try {
			entityManager.merge(station);
			b = true;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return b;
	}

	@Override
	public Station findStationById(Integer id) {
		try {
			return entityManager.find(Station.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Station> findAllStations() {
		List<Station> stations = new ArrayList<>();
		try {
			String jpql = "select s from Station s";
			Query query = entityManager.createQuery(jpql);
			stations = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return stations;
	}

}
