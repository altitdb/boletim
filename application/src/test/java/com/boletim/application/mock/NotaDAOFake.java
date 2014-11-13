package com.boletim.application.mock;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import com.boletim.dao.NotaDAO;
import com.boletim.domain.Aluno;
import com.boletim.domain.Nota;

@Stateless
public class NotaDAOFake implements NotaDAO {

	public List<Nota> consultarNotas(Aluno aluno) {
		List<Nota> notas = new ArrayList<Nota>();
		if ("05008265".equals(aluno.getRa())) {
			notas.add(new Nota());
			notas.add(new Nota());
			notas.add(new Nota());
			notas.add(new Nota());
		}		
		return notas;
	}

}
