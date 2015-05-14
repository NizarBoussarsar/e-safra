package beans;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import services.interfaces.local.BusServicesLocal;
import services.interfaces.local.BusinessLogicServicesLocal;
import domain.Bus;
import domain.Line;

@ManagedBean
@ViewScoped
public class BusBean {

	private Bus bus = new Bus();
	private List<Bus> buses = new ArrayList<>();
	private Boolean visibility = false;

	@EJB
	private BusinessLogicServicesLocal businessLogicServicesLocal;

	@EJB
	private BusServicesLocal busServicesLocal;

	@ManagedProperty(value = "#{lineBean}")
	private LineBean lineBean;

	public void doSelect() {
		visibility = true;
	}

	public String doSaveOrUpdate() {
		Line lineSelected = lineBean.getLine();
		bus.setLine(lineSelected);
		busServicesLocal.updateBus(bus);
		visibility = false;
		return "";
	}

	public String doDeleteBus() {
		busServicesLocal.deleteBusById(bus.getId());
		visibility = false;
		return "";
	}

	// public String doAddBus() {
	// stationServicesLocal.addBus(bus);
	// visibility = false;
	// return "";
	// }

	public Bus getBus() {
		return bus;
	}

	public void setBus(Bus bus) {
		this.bus = bus;
	}

	public List<Bus> getBuses() {
		buses = busServicesLocal.findAllBuses();
		return buses;
	}

	public void setBuses(List<Bus> buses) {
		this.buses = buses;
	}

	public Boolean getVisibility() {
		return visibility;
	}

	public void setVisibility(Boolean visibility) {
		this.visibility = visibility;
	}

	public LineBean getLineBean() {
		return lineBean;
	}

	public void setLineBean(LineBean lineBean) {
		this.lineBean = lineBean;
	}

}
