package com.boletim.application;

import java.util.List;

import javax.ejb.Local;

import com.boletim.domain.Aluno;
import com.boletim.domain.Nota;

@Local
public interface AlunoService {

	List<Nota> consultarNotas(String ra);

	Aluno consultar(String ra);

}
