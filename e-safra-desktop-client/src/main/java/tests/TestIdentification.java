package tests;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import services.interfaces.remote.BusinessLogicServicesRemote;
import domain.User;

public class TestIdentification {

	public static void main(String[] args) throws NamingException {
		Context context = new InitialContext();

		BusinessLogicServicesRemote businessLogicServicesRemote = (BusinessLogicServicesRemote) context
				.lookup("/e-safra-boussarsar/BusinessLogicServices!services.interfaces.BusinessLogicServicesRemote");

		User user = businessLogicServicesRemote.identification(
				"nizar_boussarsar", "esprit");

		System.out.println(user.toString());
	}

}
