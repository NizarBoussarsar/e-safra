package services.interfaces.local;

import java.util.List;

import javax.ejb.Local;

import domain.Line;

@Local
public interface LineServicesLocal {
	
	Boolean addLine(Line line);

	Boolean deleteLine(Line line);

	Boolean deleteLineById(Integer id);

	Boolean updateLine(Line line);

	Line findLineById(Integer id);

	List<Line> findAllLines();

}
