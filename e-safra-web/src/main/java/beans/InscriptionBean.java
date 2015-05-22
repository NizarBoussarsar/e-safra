package beans;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import services.interfaces.local.UserServicesLocal;
import domain.Passenger;

@ManagedBean
@ViewScoped
public class InscriptionBean {

	private Passenger passenger = new Passenger();
	private Boolean visibility = false;
	private String ConfirmPassword = "";
	private String ErrorMessage;
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
				navigateTo = "/login?faces-redirect=true";
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

}