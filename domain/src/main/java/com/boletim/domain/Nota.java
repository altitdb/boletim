package com.boletim.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Nota implements Serializable {

	private static final long serialVersionUID = 23L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idNota;
	private BigDecimal nota;
	@ManyToOne
	@JoinColumn(name = "ID_ALUNO")
	private Aluno aluno;
	@ManyToOne
	@JoinColumn(name = "ID_DISCIPLINA")
	private Disciplina disciplina;
	@Enumerated(EnumType.STRING)
	private TipoNota tipo;

	public void setIdNota(Long idNota) {
		this.idNota = idNota;
	}

	public Long getIdNota() {
		return idNota;
	}

	public void setNota(BigDecimal nota) {
		this.nota = nota;		
	}

	public BigDecimal getNota() {
		return nota;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setTipo(TipoNota tipo) {
		this.tipo = tipo;
	}

	public TipoNota getTipo() {
		return tipo;
	}

}
