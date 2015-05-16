package services.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import services.interfaces.local.SectionServicesLocal;
import services.interfaces.remote.SectionServicesRemote;
import domain.Section;

/**
 * Session Bean implementation class SectionServices
 */
@Stateless
public class SectionServices implements SectionServicesRemote,
		SectionServicesLocal {

	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Default constructor.
	 */
	public SectionServices() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Boolean addSection(Section section) {
		Boolean b = false;
		try {
			entityManager.persist(section);
			b = true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
		return b;
	}

	@Override
	public Boolean deleteSection(Section section) {
		Boolean b = false;
		try {
			entityManager.remove(entityManager.merge(section));
			b = true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
		return b;
	}

	@Override
	public Boolean deleteSectionById(Integer id) {
		Boolean b = false;
		try {
			Section section = findSectionById(id);
			entityManager.remove(section);
			b = true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
		return b;
	}

	@Override
	public Boolean updateSection(Section section) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Section findSectionById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Section> findAllSections() {
		// TODO Auto-generated method stub
		return null;
	}

}
