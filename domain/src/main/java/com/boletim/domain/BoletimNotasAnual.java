package com.boletim.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BoletimNotasAnual implements BoletimNotas, Serializable {

	private static final long serialVersionUID = 23L;
	private String disciplina;
	private String nota1;
	private String nota2;
	private String nota3;
	private String nota4;
	private String notaExame;

	public String getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(String disciplina) {
		this.disciplina = disciplina;
	}

	public String getNota1() {
		return nota1;
	}

	public void setNota1(String nota1) {
		this.nota1 = nota1;
	}

	public String getNota2() {
		return nota2;
	}

	public void setNota2(String nota2) {
		this.nota2 = nota2;
	}

	public String getNota3() {
		return nota3;
	}

	public void setNota3(String nota3) {
		this.nota3 = nota3;
	}

	public String getNota4() {
		return nota4;
	}

	public void setNota4(String nota4) {
		this.nota4 = nota4;
	}

	public String getNotaExame() {
		return notaExame;
	}

	public void setNotaExame(String notaExame) {
		this.notaExame = notaExame;
	}
	
	public List<String> getKeys() {
		List<String> notas = new ArrayList<String>();
		notas.add("bim1");
		notas.add("bim2");
		notas.add("bim3");
		notas.add("bim4");
		notas.add("exame");
		return notas;
	}

	public List<String> getNotas() {
		List<String> notas = new ArrayList<String>();
		notas.add(nota1);
		notas.add(nota2);
		notas.add(nota3);
		notas.add(nota4);
		notas.add(notaExame);
		return notas;
	}

	public int getTotal() {
		return 5;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((disciplina == null) ? 0 : disciplina.hashCode());
		result = prime * result + ((nota1 == null) ? 0 : nota1.hashCode());
		result = prime * result + ((nota2 == null) ? 0 : nota2.hashCode());
		result = prime * result + ((nota3 == null) ? 0 : nota3.hashCode());
		result = prime * result + ((nota4 == null) ? 0 : nota4.hashCode());
		result = prime * result
				+ ((notaExame == null) ? 0 : notaExame.hashCode());
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
		BoletimNotasAnual other = (BoletimNotasAnual) obj;
		if (disciplina == null) {
			if (other.disciplina != null)
				return false;
		} else if (!disciplina.equals(other.disciplina))
			return false;
		if (nota1 == null) {
			if (other.nota1 != null)
				return false;
		} else if (!nota1.equals(other.nota1))
			return false;
		if (nota2 == null) {
			if (other.nota2 != null)
				return false;
		} else if (!nota2.equals(other.nota2))
			return false;
		if (nota3 == null) {
			if (other.nota3 != null)
				return false;
		} else if (!nota3.equals(other.nota3))
			return false;
		if (nota4 == null) {
			if (other.nota4 != null)
				return false;
		} else if (!nota4.equals(other.nota4))
			return false;
		if (notaExame == null) {
			if (other.notaExame != null)
				return false;
		} else if (!notaExame.equals(other.notaExame))
			return false;
		return true;
	}

}
