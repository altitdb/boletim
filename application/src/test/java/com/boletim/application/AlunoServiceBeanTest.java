package com.boletim.application;

import static org.junit.Assert.*;

import java.util.List;

import javax.ejb.EJB;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.boletim.application.mock.AlunoDAOFake;
import com.boletim.application.mock.NotaDAOFake;
import com.boletim.dao.AlunoDAO;
import com.boletim.dao.NotaDAO;
import com.boletim.domain.Aluno;
import com.boletim.domain.Disciplina;
import com.boletim.domain.Nota;
import com.boletim.domain.TipoNota;

@RunWith(Arquillian.class)
public class AlunoServiceBeanTest {

	@Deployment
	public static Archive<?> createDeployment() {
		return ShrinkWrap
				.create(WebArchive.class, "application.war")
				.addClass(AlunoService.class)
				.addClass(AlunoServiceBean.class)
				.addClass(Nota.class)
				.addClass(Disciplina.class)
				.addClass(TipoNota.class)
				.addClass(Aluno.class)
				.addClass(AlunoDAO.class)
				.addClass(AlunoDAOFake.class)
				.addClass(NotaDAO.class)
				.addClass(NotaDAOFake.class)
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
	}

	@EJB
	private AlunoService alunoService;

	@Test
	public void deveriaConsultarNotasAluno() {
		String ra = "05008265";
		List<Nota> result = alunoService.consultarNotas(ra);
		assertEquals(4, result.size());
	}
	
	@Test
	public void deveriaNaoRetornarNotasParaAlunoInexistente() {
		String ra = "05008270";
		List<Nota> result = alunoService.consultarNotas(ra);
		assertEquals(0, result.size());
	}
	
	@Test
	public void deveriaNaoRetornarNotasParaAluno() {
		String ra = "05008275";
		List<Nota> result = alunoService.consultarNotas(ra);
		assertEquals(0, result.size());
	}
	
	@Test
	public void deveriaConsultarAlunoExistente() {
		String ra = "05008275";
		Aluno aluno = alunoService.consultar(ra);
		assertNotNull(aluno);
	}
	
	@Test
	public void deveriaConsultarAlunoInexistente() {
		String ra = "05008200";
		Aluno aluno = alunoService.consultar(ra);
		assertNull(aluno);
	}
}
