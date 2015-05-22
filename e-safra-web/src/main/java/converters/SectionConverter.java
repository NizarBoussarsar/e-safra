package converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import services.interfaces.local.BusinessLogicServicesLocal;
import domain.Section;

@FacesConverter("sectionConverter")
public class SectionConverter implements Converter {

	@Inject
	BusinessLogicServicesLocal businessLogicServicesLocal;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {

		Section section = null;
		if (!value.trim().equals("")) {
			section = businessLogicServicesLocal.findSectionByRank(Integer
					.parseInt(value));
		}
		return section;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object object) {
		String eqString = null;
		if (object == null || object.equals("")) {
			eqString = "";
		} else {
			eqString = ((Section) object).getRank().toString();
		}
		return eqString;
	}

}
