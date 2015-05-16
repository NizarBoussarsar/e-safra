package services.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import services.interfaces.local.LineServicesLocal;
import services.interfaces.remote.LineServicesRemote;
import domain.Line;

/**
 * Session Bean implementation class LineService
 */
@Stateless
public class LineServices implements LineServicesRemote, LineServicesLocal {

	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Default constructor.
	 */
	public LineServices() {

	}

	@Override
	public Boolean addLine(Line line) {
		Boolean b = false;
		try {
			entityManager.persist(line);
			b = true;
		} catch (Exception e) {
			System.out.println(e.getMessage());;
			return null;
		}
		return b;
	}

	@Override
	public Boolean deleteLine(Line line) {
		Boolean b = false;
		try {
			entityManager.remove(entityManager.merge(line));
			b = true;
		} catch (Exception e) {
			System.out.println(e.getMessage());;
			return null;
		}
		return b;
	}

	@Override
	public Boolean deleteLineById(Integer id) {
		Boolean b = false;
		try {
			Line Line = findLineById(id);
			entityManager.remove(Line);
			b = true;

		} catch (Exception e) {
			System.out.println(e.getMessage());;
			return null;
		}
		return b;
	}

	@Override
	public Boolean updateLine(Line line) {
		Boolean b = false;
		try {
			entityManager.merge(line);
			b = true;
		} catch (Exception e) {
			System.out.println(e.getMessage());;
			return null;
		}
		return b;
	}

	@Override
	public Line findLineById(Integer id) {
		try {
			return entityManager.find(Line.class, id);
		} catch (Exception e) {
			System.out.println(e.getMessage());;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Line> findAllLines() {
		List<Line> Lines = new ArrayList<>();
		try {
			String jpql = "select s from Line s";
			Query query = entityManager.createQuery(jpql);
			Lines = query.getResultList();
		} catch (Exception e) {
			System.out.println(e.getMessage());;
			return null;
		}
		return Lines;
	}

}
