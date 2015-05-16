package services.interfaces.remote;

import java.util.List;

import javax.ejb.Remote;

import domain.Bus;

@Remote
public interface BusServicesRemote {

	Boolean addBus(Bus bus);

	Boolean deleteBus(Bus bus);

	Boolean deleteBusById(Integer id);

	Boolean updateBus(Bus bus);

	Bus findBusById(Integer id);

	List<Bus> findAllBuses();

}
