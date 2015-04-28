package services.interfaces.remote;

import java.util.List;

import javax.ejb.Remote;

import domain.Ticket;

@Remote
public interface TicketServicesRemote {

	Boolean addTicket(Ticket ticket);

	Boolean deleteTicket(Ticket ticket);

	Boolean deleteTicketById(Integer id);

	Boolean updateTicket(Ticket ticket);

	Ticket findTicketById(Integer id);

	List<Ticket> findAllTicketes();

}
