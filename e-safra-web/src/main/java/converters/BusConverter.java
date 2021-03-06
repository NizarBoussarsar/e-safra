package converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import services.interfaces.local.BusinessLogicServicesLocal;
import domain.Bus;

@FacesConverter("busConverter")
public class BusConverter implements Converter {

	@Inject
	BusinessLogicServicesLocal businessLogicServicesLocal;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {

		Bus bus = null;
		if (!value.trim().equals("")) {
			bus = businessLogicServicesLocal.findBusByNumber(value);
		}
		return bus;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object object) {
		String eqString = null;
		if (object == null || object.equals("")) {
			eqString = "";
		} else {
			eqString = ((Bus) object).getNumber();
		}
		return eqString;
	}

}
