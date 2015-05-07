package services.interfaces.remote;

import java.util.List;

import javax.ejb.Remote;

import domain.Section;

@Remote
public interface SectionServicesRemote {

	Boolean addSection(Section section);

	Boolean deleteSection(Section section);

	Boolean deleteSectionById(Integer id);

	Boolean updateSection(Section section);

	Section findSectionById(Integer id);

	List<Section> findAllSections();

}
