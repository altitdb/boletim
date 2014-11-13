package com.boletim.domain;

import static org.junit.Assert.*;

import org.junit.Test;

import com.google.gson.Gson;

public class ErroTest {

	@Test
	public void deveriaCriarNovoErro() {
		Erro error = new Erro("Erro bla bla");
		assertEquals("Erro bla bla", error.getMessage());
	}
	
	@Test
	public void deveriaRetornarJson() {
		Erro error = new Erro("Erro bla bla ãã");
		assertEquals("{\"erro\":\"Erro bla bla ãã\"}", error.getJson());
	}
	
	@Test
	public void deveriaCriarObjetoAPartirDoJson() {
		Erro error = new Erro("Erro bla bla ãã");
		String json = error.getJson();
		Gson gson = new Gson();
		assertEquals(error, gson.fromJson(json, Erro.class));
	}
}
