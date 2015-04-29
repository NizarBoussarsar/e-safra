package beans;

import java.io.IOException;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import services.interfaces.local.BusinessLogicServicesLocal;
import domain.BusManager;
import domain.Driver;
import domain.Passenger;
import domain.User;

@ManagedBean
@SessionScoped
public class UserBean {

	private User user = new User();
	private Boolean loggedInAsBusManager = false;
	private Boolean loggedInAsPassenger = false;
	private Boolean loggedInAsDriver = false;

	@EJB
	private BusinessLogicServicesLocal businessLogicServicesLocal;

	public String doLogin() {
		String navigateTo = "";
		User userFound = businessLogicServicesLocal.identification(
				user.getEmail(), user.getPassword());
		if (userFound != null) {
			user = userFound;
			if (userFound instanceof Passenger) {
				navigateTo = "/pages/passenger/home?faces-redirect=true";
			} else if (userFound instanceof BusManager) {
				loggedInAsBusManager = true;
				navigateTo = "/pages/busmanager/home?faces-redirect=true";
			} else if (userFound instanceof Driver) {
				navigateTo = "/pages/driver/home?faces-redirect=true";
			}
		} else {
			navigateTo = "/pages/public/404?faces-redirect=true";
		}
		return navigateTo;
	}

	public void doLogOut() {
		try {
			ExternalContext externalContext = FacesContext.getCurrentInstance()
					.getExternalContext();

			externalContext.invalidateSession();

			String homeURL = "http://localhost:8483/esafra-local/pages/public/index.jsf";
			FacesContext.getCurrentInstance().getExternalContext()
					.redirect(homeURL);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Boolean getLoggedInAsBusManager() {
		return loggedInAsBusManager;
	}

	public void setLoggedInAsBusManager(Boolean loggedInAsBusManager) {
		this.loggedInAsBusManager = loggedInAsBusManager;
	}

	public Boolean getLoggedInAsPassenger() {
		return loggedInAsPassenger;
	}

	public void setLoggedInAsPassenger(Boolean loggedInAsPassenger) {
		this.loggedInAsPassenger = loggedInAsPassenger;
	}

	public Boolean getLoggedInAsDriver() {
		return loggedInAsDriver;
	}

	public void setLoggedInAsDriver(Boolean loggedInAsDriver) {
		this.loggedInAsDriver = loggedInAsDriver;
	}

}