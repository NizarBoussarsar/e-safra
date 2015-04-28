package services.interfaces.remote;

import java.util.List;

import javax.ejb.Remote;

import domain.Line;

@Remote
public interface LineServicesRemote {

	Boolean addLine(Line line);

	Boolean deleteLine(Line line);

	Boolean deleteLineById(Integer id);

	Boolean updateLine(Line line);

	Line findLineById(Integer id);

	List<Line> findAllLines();
}
