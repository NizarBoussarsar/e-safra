package converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import services.interfaces.local.BusinessLogicServicesLocal;
import domain.Station;

@FacesConverter("stationConverter")
public class StationConverter implements Converter {

	@Inject
	BusinessLogicServicesLocal businessLogicServicesLocal;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {

		Station station = null;
		if (!value.trim().equals("")) {
			station = businessLogicServicesLocal.findStationByName(value);
		}
		return station;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object object) {
		String eqString = null;
		if (object == null || object.equals("")) {
			eqString = "";
		} else {
			eqString = ((Station) object).getName();
		}
		return eqString;
	}

}
