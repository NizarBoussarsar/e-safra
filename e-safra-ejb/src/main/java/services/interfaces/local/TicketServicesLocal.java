package services.interfaces.local;

import java.util.List;

import javax.ejb.Local;

import domain.Ticket;

@Local
public interface TicketServicesLocal {

	Boolean addTicket(Ticket ticket);

	Boolean deleteTicket(Ticket ticket);

	Boolean deleteTicketById(Integer id);

	Boolean updateTicket(Ticket ticket);

	Ticket findTicketById(Integer id);

	List<Ticket> findAllTicketes();

}
