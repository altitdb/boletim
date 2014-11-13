package com.boletim.domain;

import java.io.Serializable;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

public class Erro implements Serializable {

	private static final long serialVersionUID = 23L;
	@SerializedName("erro")
	private String message;

	public Erro(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public String getJson() {
		Gson gson = new Gson();
		return gson.toJson(this);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((message == null) ? 0 : message.hashCode());
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
		Erro other = (Erro) obj;
		if (message == null) {
			if (other.message != null)
				return false;
		} else if (!message.equals(other.message))
			return false;
		return true;
	}

}
