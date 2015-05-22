package beans;

import java.io.IOException;
import java.io.Serializable;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.brickred.socialauth.AuthProvider;
import org.brickred.socialauth.Profile;
import org.brickred.socialauth.SocialAuthConfig;
import org.brickred.socialauth.SocialAuthManager;
import org.brickred.socialauth.util.SocialAuthUtil;

import services.interfaces.local.BusinessLogicServicesLocal;

@ManagedBean(name = "userSession")
@SessionScoped
public class SocialAuthentificationBean implements Serializable {

	private SocialAuthManager manager;
	private String originalURL;
	private String providerID;
	private Profile profile;

	@EJB
	BusinessLogicServicesLocal businessLogicServicesLocal;

	private static final long serialVersionUID = 1L;

	public void socialConnect() {
		try {
			String propUrl = "oauth_consumer.properties";
			SocialAuthConfig config = SocialAuthConfig.getDefault();
			config.load(propUrl);
			manager = new SocialAuthManager();
			manager.setSocialAuthConfig(config);

			String successURL = "http://localhost:8483/e-safra-web/pages/public/social/success.jsf";

			String authenticationURL = manager.getAuthenticationUrl(providerID,
					successURL);

			FacesContext.getCurrentInstance().getExternalContext()
					.redirect(authenticationURL);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void pullUserInfo() {
		try {
			ExternalContext externalContext = FacesContext.getCurrentInstance()
					.getExternalContext();
			HttpServletRequest request = (HttpServletRequest) externalContext
					.getRequest();
			Map<String, String> map = SocialAuthUtil
					.getRequestParametersMap(request);
			if (this.manager != null) {
				AuthProvider provider = manager.connect(map);
				this.profile = provider.getUserProfile();
				this.profile.getValidatedId();
				
				///this.profile.getProviderId();
				
				// // Do what you want with the data (e.g. persist to the
				// database,
				// // etc.)
				System.out.println("User's Social profile: " + profile);

				// Passenger passenger = businessLogicServicesLocal
				// .accountRegisterOrLoginWithFacebook(profile);
				//
				// System.out.println(passenger.toString());

			} else {
				System.out.println("SocialAuthManager instance is null");
				String homeURL = "http://localhost:8483/e-safra-web/pages/public/index.jsf";
				FacesContext.getCurrentInstance().getExternalContext()
						.redirect(homeURL);
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public String doRedirectToButtonPage() {
		String homeURL = "http://localhost:8483/e-safra-web/pages/public/social/buttons.jsf";
		try {
			FacesContext.getCurrentInstance().getExternalContext()
					.redirect(homeURL);
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
		return "/pages/public/social/buttons?faces-redirect=true";
	}

	public void logOut() {
		try {
			ExternalContext externalContext = FacesContext.getCurrentInstance()
					.getExternalContext();

			manager.disconnectProvider(providerID);

			externalContext.invalidateSession();

			String homeURL = "http://localhost:8483/e-safra-web/pages/public/index.jsf";
			FacesContext.getCurrentInstance().getExternalContext()
					.redirect(homeURL);

		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	public SocialAuthManager getManager() {
		return manager;
	}

	public void setManager(SocialAuthManager manager) {
		this.manager = manager;
	}

	public String getOriginalURL() {
		return originalURL;
	}

	public void setOriginalURL(String originalURL) {
		this.originalURL = originalURL;
	}

	public String getProviderID() {
		return providerID;
	}

	public void setProviderID(String providerID) {
		this.providerID = providerID;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

}
