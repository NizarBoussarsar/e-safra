package tests;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import services.interfaces.remote.BusinessLogicServicesRemote;

public class TestFindStationOrderByLine {

	public static void main(String[] args) throws NamingException {
		Context context = new InitialContext();

		BusinessLogicServicesRemote businessLogicServicesRemote = (BusinessLogicServicesRemote) context
				.lookup("/e-safra-boussarsar/BusinessLogicServices!services.interfaces.BusinessLogicServicesRemote");

		System.out.println("The order of this stations is : "
				+ businessLogicServicesRemote.findStationOrderByLineId(3, 1));

	}

}
