package services.interfaces.local;

import java.util.List;

import javax.ejb.Local;

import domain.Section;

@Local
public interface SectionServicesLocal {

	Boolean addSection(Section section);

	Boolean deleteSection(Section section);

	Boolean deleteSectionById(Integer id);

	Boolean updateSection(Section section);

	Section findSectionById(Integer id);

	List<Section> findAllSections();

}
