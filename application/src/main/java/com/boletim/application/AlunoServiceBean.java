package com.boletim.application;

import java.util.Collections;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.boletim.dao.AlunoDAO;
import com.boletim.dao.NotaDAO;
import com.boletim.domain.Aluno;
import com.boletim.domain.Nota;

@Stateless
public class AlunoServiceBean implements AlunoService {

	@EJB
	private AlunoDAO alunoDAO;
	
	@EJB
	private NotaDAO notaDAO;
	
	public List<Nota> consultarNotas(String ra) {
		Aluno aluno = alunoDAO.getByRa(ra);
		if (aluno == null) {
			return Collections.emptyList();
		}
		List<Nota> notas = notaDAO.consultarNotas(aluno);
		return notas;
	}

	public Aluno consultar(String ra) {
		return alunoDAO.getByRa(ra);
	}

}
