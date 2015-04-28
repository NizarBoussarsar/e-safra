package beans;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import services.interfaces.local.BusinessLogicServicesLocal;
import services.interfaces.local.TicketServicesLocal;
import domain.Ticket;

@ManagedBean
@ViewScoped
public class TicketBean {

	@EJB
	private BusinessLogicServicesLocal businessLogicServicesLocal;

	@EJB
	private TicketServicesLocal ticketServicesLocal;

	Ticket ticket = new Ticket();

	public String doSome() {
		return "";
	}

	public Ticket getTicket() {
		return ticket;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}

}
