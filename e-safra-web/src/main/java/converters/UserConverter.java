package converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import beans.LineBean;
import domain.Line;

@FacesConverter("userConverter")
public class UserConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		if (value == null) {
			return null;
		}
		LineBean lineBean = context.getApplication().evaluateExpressionGet(
				context, "#{userBean}", LineBean.class);
		Line line = lineBean.doFindLineByName(value);
		System.out.println(line);
		return line;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object object) {
		String string = null;
		if (object instanceof Line) {
			string = ((Line) object).getName();
		}
		return string;
	}

}
