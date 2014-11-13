package com.boletim.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

@Entity
public class Aluno implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@SerializedName("id")
	private Long idAluno;
	private String nome;
	private String ra;

	public void setIdAluno(Long idAluno) {
		this.idAluno = idAluno;
	}

	public Long getIdAluno() {
		return idAluno;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setRa(String ra) {
		this.ra = ra;
	}

	public String getRa() {
		return ra;
	}

	public String getJson() {
		Gson gson = new Gson();
		return gson.toJson(this);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idAluno == null) ? 0 : idAluno.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((ra == null) ? 0 : ra.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Aluno other = (Aluno) obj;
		if (idAluno == null) {
			if (other.idAluno != null)
				return false;
		} else if (!idAluno.equals(other.idAluno))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (ra == null) {
			if (other.ra != null)
				return false;
		} else if (!ra.equals(other.ra))
			return false;
		return true;
	}

}
