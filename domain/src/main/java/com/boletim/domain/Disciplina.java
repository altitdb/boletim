package com.boletim.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Disciplina implements Serializable {
	
	private static final long serialVersionUID = 23L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idDisciplina;
	private String nome;

	public void setIdDisciplina(Long idDisciplina) {
		this.idDisciplina = idDisciplina;		
	}

	public Long getIdDisciplina() {
		return idDisciplina;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

}
