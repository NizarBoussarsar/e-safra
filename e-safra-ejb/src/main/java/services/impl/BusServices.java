package services.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import services.interfaces.local.BusServicesLocal;
import services.interfaces.remote.BusServicesRemote;
import domain.Bus;

/**
 * Session Bean implementation class BusService
 */
@Stateless
public class BusServices implements BusServicesRemote, BusServicesLocal {

	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Default constructor.
	 */
	public BusServices() {

	}

	@Override
	public Boolean addBus(Bus bus) {
		Boolean b = false;
		try {
			entityManager.persist(bus);
			b = true;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return b;
	}

	@Override
	public Boolean deleteBus(Bus bus) {
		Boolean b = false;
		try {
			entityManager.remove(entityManager.merge(bus));
			b = true;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return b;
	}

	@Override
	public Boolean deleteBusById(Integer id) {
		Boolean b = false;
		try {
			Bus Bus = findBusById(id);
			entityManager.remove(Bus);
			b = true;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return b;
	}

	@Override
	public Boolean updateBus(Bus bus) {
		Boolean b = false;
		try {
			entityManager.merge(bus);
			b = true;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return b;
	}

	@Override
	public Bus findBusById(Integer id) {
		try {
			return entityManager.find(Bus.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Bus> findAllBuses() {
		List<Bus> buses = new ArrayList<>();
		try {
			String jpql = "select b from Bus b";
			Query query = entityManager.createQuery(jpql);
			buses = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return buses;
	}

}
