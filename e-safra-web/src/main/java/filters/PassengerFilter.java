package filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.UserBean;

@WebFilter("/pages/busmanager/*")
public class PassengerFilter implements Filter {

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest servletRequest,
			ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {

		System.out.println(" url = "
				+ ((HttpServletRequest) servletRequest).getRequestURL());

		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;

		UserBean userBean = (UserBean) request.getSession().getAttribute(
				"userBean");
		if (userBean != null && userBean.getLoggedInAsPassenger() == true) {
			filterChain.doFilter(request, response);
		} else if (request.getRequestURL().toString().contains("login.jsf")) {
			filterChain.doFilter(request, response);

		} else {
			response.sendRedirect(request.getContextPath() + "/login.jsf");
		}

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

}
