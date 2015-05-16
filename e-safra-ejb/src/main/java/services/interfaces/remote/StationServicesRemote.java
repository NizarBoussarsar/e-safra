package services.interfaces.remote;

import java.util.List;

import javax.ejb.Remote;

import domain.Station;

@Remote
public interface StationServicesRemote {

	Boolean addStation(Station station);

	Boolean deleteStation(Station station);

	Boolean deleteStationById(Integer id);

	Boolean updateStation(Station station);

	Station findStationById(Integer id);

	List<Station> findAllStations();
}
