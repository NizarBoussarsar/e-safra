package converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import beans.StationBean;
import domain.Station;

@FacesConverter("stationConverter")
public class StationConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		if (value == null) {
			return null;
		}
		StationBean stationBean = context.getApplication()
				.evaluateExpressionGet(context, "#{stationBean}",
						StationBean.class);
		Station station = stationBean.doFindStationByName(value);
		System.out.println("Station="+station);
		return station;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object object) {
		String string = null;
		if (object instanceof Station) {
			string = ((Station) object).getName();
		}
		return string;
	}

}
