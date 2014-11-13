package com.boletim.application.mock;
import javax.ejb.Stateless;

import com.boletim.dao.AlunoDAO;
import com.boletim.domain.Aluno;

@Stateless
public class AlunoDAOFake implements AlunoDAO {

	public Aluno getByRa(String ra) {
		if ("05008265".equals(ra)) {
			Aluno aluno = new Aluno();
			aluno.setIdAluno(1L);
			aluno.setNome("Altieres");
			aluno.setRa("05008265");
			return aluno;
		} else if ("05008275".equals(ra)) {
			Aluno aluno = new Aluno();
			aluno.setIdAluno(2L);
			aluno.setNome("Altieres 2");
			aluno.setRa("05008275");
			return aluno;
		}
		return null;
	}

}
