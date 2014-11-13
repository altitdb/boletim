package com.boletim.dao;

import java.util.Collections;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.boletim.domain.Aluno;
import com.boletim.domain.Nota;

@Stateless
public class NotaDAOImpl implements NotaDAO {

	@PersistenceContext
	private EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	public List<Nota> consultarNotas(Aluno aluno) {
		Query query = entityManager
				.createQuery("SELECT n FROM Nota n WHERE n.aluno = :aluno");
		query.setParameter("aluno", aluno);
		List<Nota> notas = query.getResultList();
		if (notas == null) {
			return Collections.emptyList();
		}
		return notas;
	}

}
