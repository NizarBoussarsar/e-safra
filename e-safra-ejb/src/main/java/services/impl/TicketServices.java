package services.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import services.interfaces.local.TicketServicesLocal;
import services.interfaces.remote.TicketServicesRemote;
import domain.Ticket;

/**
 * Session Bean implementation class TicketService
 */
@Stateless
public class TicketServices implements TicketServicesLocal,
		TicketServicesRemote {

	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Default constructor.
	 */
	public TicketServices() {

	}

	@Override
	public Boolean addTicket(Ticket ticket) {
		Boolean b = false;
		try {
			entityManager.persist(ticket);
			b = true;
		} catch (Exception e) {
			System.out.println(e.getMessage());;
			return null;
		}
		return b;
	}

	@Override
	public Boolean deleteTicket(Ticket ticket) {
		Boolean b = false;
		try {
			entityManager.remove(entityManager.merge(ticket));
			b = true;
		} catch (Exception e) {
			System.out.println(e.getMessage());;
			return null;
		}
		return b;
	}

	@Override
	public Boolean deleteTicketById(Integer id) {
		Boolean b = false;
		try {
			Ticket ticket = findTicketById(id);
			entityManager.remove(ticket);
			b = true;
		} catch (Exception e) {
			System.out.println(e.getMessage());;
			return null;
		}
		return b;
	}

	@Override
	public Boolean updateTicket(Ticket ticket) {
		Boolean b = false;
		try {
			entityManager.merge(ticket);
			b = true;
		} catch (Exception e) {
			System.out.println(e.getMessage());;
			return null;
		}
		return b;
	}

	@Override
	public Ticket findTicketById(Integer id) {
		try {
			return entityManager.find(Ticket.class, id);
		} catch (Exception e) {
			System.out.println(e.getMessage());;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Ticket> findAllTicketes() {
		List<Ticket> tickets = new ArrayList<>();
		try {
			String jpql = "select t from Ticket t";
			Query query = entityManager.createQuery(jpql);
			tickets = query.getResultList();
		} catch (Exception e) {
			System.out.println(e.getMessage());;
			return null;
		}
		return tickets;
	}

}
