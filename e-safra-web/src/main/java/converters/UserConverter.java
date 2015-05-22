package converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import services.interfaces.local.BusinessLogicServicesLocal;
import domain.User;

@FacesConverter("userConverter")
public class UserConverter implements Converter {

	@Inject
	BusinessLogicServicesLocal businessLogicServicesLocal;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {

		User user = null;
		if (!value.trim().equals("")) {
			user = businessLogicServicesLocal.findUserByEmail(value);
		}
		return user;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object object) {
		String eqString = null;
		if (object == null || object.equals("")) {
			eqString = "";
		} else {
			eqString = ((User) object).getEmail();
		}
		return eqString;
	}

}
