package com.boletim.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.boletim.domain.Aluno;

@Stateless
public class AlunoDAOImpl implements AlunoDAO {

	@PersistenceContext
	private EntityManager entityManager;

	public Aluno getByRa(String ra) {
		Query query = entityManager
				.createQuery("SELECT a FROM Aluno a WHERE a.ra = :ra");
		query.setParameter("ra", ra);
		try {
			return (Aluno) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

}
