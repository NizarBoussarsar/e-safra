package beans;

import java.io.IOException;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import services.interfaces.local.BusinessLogicServicesLocal;
import services.interfaces.local.UserServicesLocal;
import domain.BusManager;
import domain.Driver;
import domain.Passenger;
import domain.Station;
import domain.User;

@ManagedBean
@SessionScoped
public class UserBean {

	private User user = new User();
	private Passenger passenger = new Passenger();
	private Boolean loggedInAsBusManager = false;
	private String type;

	private Boolean loggedInAsPassenger = false;
	private Boolean loggedInAsDriver = false;
	private Boolean visibility = false;
	private String ConfirmPassword = "";
	private String ErrorMessage;
	@EJB
	private BusinessLogicServicesLocal businessLogicServicesLocal;
	@EJB
	private UserServicesLocal userServicesLocal;

	public String doInscription() {
		String navigateTo = "";
		System.out.println("ConfirmPassword" + ConfirmPassword);
		if (passenger.getPassword().equals(ConfirmPassword)) {
			if (userServicesLocal.findUserByEmail(passenger.getEmail())) {
				ErrorMessage = "Email address alrady in use";
				visibility = true;
			} else {
				System.out.println("we will add a user");

				userServicesLocal.addUser(passenger);
				navigateTo = "/pages/passenger/home?faces-redirect=true";
			}
		} else {
			ErrorMessage = "The passwords are not matching";
			visibility = true;
		}
		return navigateTo;
	}

	public String goToInscription() {
		return "/inscription?faces-redirect=true";
	}

	public String doLogin() {
		String navigateTo = "";
		User userFound = businessLogicServicesLocal.identification(
				user.getEmail(), user.getPassword());
		if (userFound != null) {
			user = userFound;
			if (userFound instanceof Passenger) {
				navigateTo = "/pages/passenger/home?faces-redirect=true";
				loggedInAsPassenger = true;
				type = "Passenger";
			} else if (userFound instanceof BusManager) {
				loggedInAsBusManager = true;
				type = "Bus Manager";

				navigateTo = "/pages/busmanager/home?faces-redirect=true";
			} else if (userFound instanceof Driver) {
				loggedInAsDriver = true;
				type = "Driver";

				navigateTo = "/pages/driver/home?faces-redirect=true";
			}
		} else {
			navigateTo = "/pages/public/404?faces-redirect=true";
		}
		System.out.println("navigate=" + navigateTo);
		return navigateTo;
	}

	public void doLogOut() {
		try {
			ExternalContext externalContext = FacesContext.getCurrentInstance()
					.getExternalContext();

			externalContext.invalidateSession();

			String homeURL = "http://localhost:8483/e-safra-web/login.jsf";
			FacesContext.getCurrentInstance().getExternalContext()
					.redirect(homeURL);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	public Station doFindUserByName(String name) {
		return businessLogicServicesLocal.findStationByName(name);
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

	public String getConfirmPassword() {
		return ConfirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		ConfirmPassword = confirmPassword;
	}

	public boolean isVisibility() {
		return visibility;
	}

	public void setVisibility(boolean visibility) {
		this.visibility = visibility;
	}

	public String getErrorMessage() {
		return ErrorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		ErrorMessage = errorMessage;
	}

	public Passenger getPassenger() {
		return passenger;
	}

	public void setPassenger(Passenger passenger) {
		this.passenger = passenger;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}