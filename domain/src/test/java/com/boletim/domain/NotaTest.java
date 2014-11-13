package com.boletim.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

import java.math.BigDecimal;

import org.junit.Test;

public class NotaTest {

	@Test
	public void deveriaCriarNota() {
		Nota nota = new Nota();
		assertNotNull(nota);
	}
	
	@Test
	public void deveriaCriarIdNota() {
		Nota nota = new Nota();
		nota.setIdNota(100L);
		assertSame(100L, nota.getIdNota());
	}
	
	@Test
	public void deveriaCriarValorNota() {
		Nota nota = new Nota();
		nota.setNota(BigDecimal.TEN);
		assertEquals(BigDecimal.TEN, nota.getNota());
	}
	
	@Test
	public void deveriaCriarAlunoNota() {
		Nota nota = new Nota();
		Aluno aluno = new Aluno();
		aluno.setIdAluno(10L);
		aluno.setNome("Altieres");
		nota.setAluno(aluno);
		assertEquals(aluno, nota.getAluno());
	}
	
	@Test
	public void deveriaCriarDisciplinaNota() {
		Nota nota = new Nota();
		Disciplina disciplina = new Disciplina();
		disciplina.setIdDisciplina(10L);
		disciplina.setNome("Algoritmos");
		nota.setDisciplina(disciplina);
		assertEquals(disciplina, nota.getDisciplina());
	}
	
	@Test
	public void deveriaCriarTipoNota() {
		Nota nota = new Nota();
		nota.setTipo(TipoNota.BIM_1);
		assertEquals(TipoNota.BIM_1, nota.getTipo());
	}
}
