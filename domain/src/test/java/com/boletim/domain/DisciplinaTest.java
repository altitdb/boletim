package com.boletim.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

import org.junit.Test;

public class DisciplinaTest {
	
	@Test
	public void deveriaCriarDisciplina() {
		Disciplina disciplina = new Disciplina();
		assertNotNull(disciplina);	
	}
	
	@Test
	public void deveriaCriarIdDisciplina() {
		Disciplina disciplina = new Disciplina();
		disciplina.setIdDisciplina(10L);
		assertSame(10L, disciplina.getIdDisciplina());
	}
	
	@Test
	public void deveriaCriarNomeDisciplina() {
		Disciplina disciplina = new Disciplina();
		disciplina.setNome("Dispositivos Móveis");
		assertEquals("Dispositivos Móveis", disciplina.getNome());
	}
	
}