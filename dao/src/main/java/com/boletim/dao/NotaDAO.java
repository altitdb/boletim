package com.boletim.dao;

import java.util.List;

import javax.ejb.Local;

import com.boletim.domain.Aluno;
import com.boletim.domain.Nota;

@Local
public interface NotaDAO {

	List<Nota> consultarNotas(Aluno aluno);

}
