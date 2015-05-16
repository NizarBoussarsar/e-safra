package client;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import domain.Station;

public class RestClient {

	private static Client client = ClientBuilder.newClient();

	public static Station getStation(Integer id) {
		WebTarget target = client
				.target("http://localhost:8483/e-safra-web/ressources/station/"
						+ String.valueOf(id));

		Response response = target.request(MediaType.APPLICATION_JSON).get();

		Station station = response.readEntity(Station.class);

		return station;
	}

	public static void getStationsMethod1() {
		WebTarget target = client
				.target("http://localhost:8483/e-safra-web/ressources/station/");

		Response response = target.request(MediaType.APPLICATION_JSON).get();

		List<Station> listStations = response
				.readEntity(new GenericType<List<Station>>() {
				});

		System.out.println("Method 1");
		for (Station station : listStations) {
			System.out.println(station.toString());
		}
	}

	public static void getStationsMethod2() {
		WebTarget target = client
				.target("http://localhost:8483/e-safra-web/ressources/station/");

		List<Station> stations = target.request(MediaType.APPLICATION_JSON)
				.get(new GenericType<List<Station>>() {
				});

		System.out.println("Method 2");
		for (Station station : stations) {
			System.out.println(station.toString());
		}
	}

	public static void postStation() {
		Station station = new Station("Test");

		WebTarget target = client
				.target("http://localhost:8483/e-safra-web/ressources/station/create");

		Response response = target.request().post(
				Entity.entity(station, MediaType.APPLICATION_JSON));

		System.out.println(response.getStatus());

		response.close();
	}

	private static void getComingSoonBuses(String stationName) {
		WebTarget target = client
				.target("http://localhost:8483/e-safra-web/ressources/service/coming-soon-buses")
				.queryParam("station", stationName);

		List<Station> stations = target.request(MediaType.APPLICATION_JSON)
				.get(new GenericType<List<Station>>() {
				});

		for (Station s : stations) {
			System.out.println(s.toString());
		}
	}

	public static void main(String[] args) {

		// Station station = stationServicesLocal.findStationById(id);
		// Json.createObjectBuilder().add("id", station.getId())
		// .add("name", station.getName()).build();

		String stationName = "Rades";

		getComingSoonBuses(stationName);
	}
}
