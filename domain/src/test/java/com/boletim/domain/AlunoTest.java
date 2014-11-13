package com.boletim.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

import org.junit.Test;

public class AlunoTest {
	
	@Test
	public void deveriaCriarAluno() {
		Aluno aluno = new Aluno();
		assertNotNull(aluno);
	}
	
	@Test
	public void deveriaCriarIdAluno() {
		Aluno aluno = new Aluno();
		aluno.setIdAluno(12L);
		assertSame(12L, aluno.getIdAluno());
	}
	
	@Test
	public void deveriaCriarNomeAluno() {
		Aluno aluno = new Aluno();
		aluno.setNome("Altieres de Matos");
		assertEquals("Altieres de Matos", aluno.getNome());
	}
	
	@Test
	public void deveriaCriarRaAluno() {
		Aluno aluno = new Aluno();
		aluno.setRa("50008265");
		assertEquals("50008265", aluno.getRa());
	}
		
	@Test
	public void deveriaCriarJson() {
		Aluno aluno = new Aluno();
		aluno.setIdAluno(1L);
		aluno.setNome("Altieres de Matos");
		aluno.setRa("05008265");
		String jsonExpected = "{\"id\":1,\"nome\":\"Altieres de Matos\",\"ra\":\"05008265\"}";
		assertEquals(jsonExpected, aluno.getJson());
	}
}