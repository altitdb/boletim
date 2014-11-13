package com.boletim.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

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
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.boletim.domain.Aluno;

@RunWith(Arquillian.class)
public class AlunoDAOImplTest {

	@Deployment
	public static Archive<?> createDeployment() {
		return ShrinkWrap.create(WebArchive.class, "dao.war")
				.addClasses(AlunoDAO.class, AlunoDAOImpl.class)
				.addPackage(Aluno.class.getPackage())
				.addAsResource("META-INF/persistence.xml")
				.addAsWebInfResource("jbossas-ds.xml")
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
	}

	@EJB
	private AlunoDAO alunoDAO;

	@PersistenceContext
	private EntityManager entityManager;

	@Inject
	private UserTransaction utx;

	@Test
	public void deveriaConsultarAlunoPorRa() {
		salvarAluno("Altieres de Matos", "05008265");
		Aluno aluno = alunoDAO.getByRa("05008265");
		assertNotNull(aluno);
	}

	@Test
	public void deveriaNaoEncontrarAlunoPorRa() {
		Aluno aluno = alunoDAO.getByRa("05008270");
		assertNull(aluno);
	}

	private void salvarAluno(String nome, String ra) {
		try {
			Aluno aluno = new Aluno();
			aluno.setNome(nome);
			aluno.setRa(ra);
			utx.begin();
			entityManager.persist(aluno);
			utx.commit();
		} catch (Exception ex) {
			Assert.fail("cant arrive here");
		}
	}
}
