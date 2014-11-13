package com.boletim.dao;

import javax.ejb.Local;

import com.boletim.domain.Aluno;

@Local	
public interface AlunoDAO {

	Aluno getByRa(String ra);

}
