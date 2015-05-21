package services.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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
		Boolean b = false;
		try {
			entityManager.merge(section);
			b = true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			;
			return null;
		}
		return b;
	}

	@Override
	public Section findSectionById(Integer id) {
		try {
			return entityManager.find(Section.class, id);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Section> findAllSections() {
		List<Section> sections = new ArrayList<>();
		try {
			String jpql = "select s from Section s";
			Query query = entityManager.createQuery(jpql);
			sections = query.getResultList();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			;
			return null;
		}
		return sections;
	}

}
