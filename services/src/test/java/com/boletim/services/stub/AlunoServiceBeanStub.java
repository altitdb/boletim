package com.boletim.services.stub;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import com.boletim.application.AlunoService;
import com.boletim.domain.Aluno;
import com.boletim.domain.Disciplina;
import com.boletim.domain.Nota;
import com.boletim.domain.TipoNota;

@Stateless
public class AlunoServiceBeanStub implements AlunoService {

	public List<Nota> consultarNotas(String ra) {
		List<Nota> notas = new ArrayList<Nota>();
		Disciplina disciplina1 = getDisciplina(1L, "Dispositivos Móveis");
		Disciplina disciplina2 = getDisciplina(2L, "Algoritmos");
		if ("05008265".equals(ra)) {
			Nota nota1 = getNota(disciplina1, TipoNota.BIM_1);
			Nota nota2 = getNota(disciplina1, TipoNota.BIM_2);
			Nota nota3 = getNota(disciplina1, TipoNota.BIM_3);
			Nota nota4 = getNota(disciplina1, TipoNota.BIM_4);
			Nota notaExame = getNota(disciplina1, TipoNota.EXAME);
			notas.add(nota1);
			notas.add(nota2);
			notas.add(nota3);
			notas.add(nota4);
			notas.add(notaExame);
		} else if ("05008260".equals(ra)) {
			Nota nota1 = getNota(disciplina1, TipoNota.BIM_1);
			Nota nota2 = getNota(disciplina1, TipoNota.BIM_2);
			Nota nota3 = getNota(disciplina1, TipoNota.BIM_3);
			Nota nota4 = getNota(disciplina1, TipoNota.BIM_4);
			Nota notaExame1 = getNota(disciplina1, TipoNota.EXAME);
			notas.add(nota1);
			notas.add(nota2);
			notas.add(nota3);
			notas.add(nota4);
			notas.add(notaExame1);
			Nota nota5 = getNota(disciplina2, TipoNota.BIM_1);
			Nota nota6 = getNota(disciplina2, TipoNota.BIM_2);
			Nota nota7 = getNota(disciplina2, TipoNota.BIM_3);
			Nota nota8 = getNota(disciplina2, TipoNota.BIM_4);
			Nota notaExame2 = getNota(disciplina2, TipoNota.EXAME);
			notas.add(nota5);
			notas.add(nota6);
			notas.add(nota7);
			notas.add(nota8);
			notas.add(notaExame2);
		}
		return notas;
	}

	private Disciplina getDisciplina(Long id, String nome) {
		Disciplina disciplina = new Disciplina();
		disciplina.setIdDisciplina(id);
		disciplina.setNome(nome);
		return disciplina;
	}

	private Nota getNota(Disciplina disciplina, TipoNota tipoNota) {
		Nota nota = new Nota();
		nota.setDisciplina(disciplina);
		nota.setTipo(tipoNota);
		nota.setNota(BigDecimal.TEN);
		return nota;
	}

	public Aluno consultar(String ra) {
		if ("05008265".equals(ra)) {
			Aluno aluno = new Aluno();
			aluno.setIdAluno(1L);
			aluno.setNome("Altieres de Matos");
			aluno.setRa("05008265");
			return aluno;
		}
		return null;
	}

}
