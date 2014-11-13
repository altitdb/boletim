package com.boletim.dao;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.boletim.domain.Aluno;
import com.boletim.domain.Nota;
import com.boletim.domain.TipoNota;

@RunWith(Arquillian.class)
public class NotaDAOImplTest {

	@Deployment
	public static Archive<?> createDeployment() {
		return ShrinkWrap
				.create(WebArchive.class)
				.addClasses(AlunoDAO.class, AlunoDAOImpl.class, NotaDAO.class,
						NotaDAOImpl.class).addPackage(Nota.class.getPackage())
				.addAsResource("META-INF/persistence.xml")
				.addAsWebInfResource("jbossas-ds.xml")
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
	}

	@EJB
	private NotaDAO notaDAO;

	@EJB
	private AlunoDAO alunoDAO;

	@PersistenceContext
	private EntityManager entityManager;

	@Inject
	private UserTransaction utx;

	@Test
	public void deveriaConsultarNotasPorAluno() {
		prepararNotas();
		Aluno aluno = alunoDAO.getByRa("05008265");
		List<Nota> notas = notaDAO.consultarNotas(aluno);
		assertEquals(2, notas.size());
	}

	private void prepararNotas() {
		try {
			utx.begin();
			entityManager.joinTransaction();
			Aluno aluno = new Aluno();
			aluno.setNome("Altieres de Matos");
			aluno.setRa("05008265");
			aluno = entityManager.merge(aluno);
			Nota nota1 = new Nota();
			nota1.setAluno(aluno);
			nota1.setNota(BigDecimal.TEN);
			nota1.setTipo(TipoNota.BIM_1);
			entityManager.merge(nota1);
			Nota nota2 = new Nota();
			nota2.setAluno(aluno);
			nota2.setNota(BigDecimal.TEN);
			nota2.setTipo(TipoNota.BIM_2);
			entityManager.merge(nota2);
			utx.commit();
		} catch (Exception ex) {
			fail("cannot be here");
		}
	}
}
