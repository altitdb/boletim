package com.boletim.domain;

import static org.junit.Assert.*;

import org.junit.Test;

public class TipoNotaTest {

	@Test
	public void deveriaTrazerTodosValores() {
		assertEquals(5, TipoNota.values().length);
	}

}
