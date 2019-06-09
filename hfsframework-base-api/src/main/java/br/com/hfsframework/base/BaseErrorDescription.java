package br.com.hfsframework.base;

import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.core.type.TypeReference;

import br.com.hfsframework.util.converter.JSONConverter;
import br.com.hfsframework.util.converter.JSONListConverter;

public class BaseErrorDescription {

	private String error;
	private String errorDescription;

	public BaseErrorDescription(String error, String errorDescription) {
		super();
		this.error = error;
		this.errorDescription = errorDescription;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getErrorDescription() {
		return errorDescription;
	}

	public void setErrorDescription(String errorDescription) {
		this.errorDescription = errorDescription;
	}

	public String toJSON() {
		JSONConverter<BaseErrorDescription> conv = new JSONConverter<BaseErrorDescription>();
		return conv.toJSON(this, true);
	}

	public Optional<BaseErrorDescription> fromJSON(String jsonText) {
		if (!jsonText.isEmpty()) {
			return this.fromJSON(jsonText);
		}
		return Optional.empty();
	}

	public String listToJSON(List<BaseErrorDescription> list) {
		JSONListConverter<BaseErrorDescription> conv = new JSONListConverter<BaseErrorDescription>();
		return conv.listToJSON(list);
	}

	public List<BaseErrorDescription> jsonToLista(String jsonText) {
		JSONListConverter<BaseErrorDescription> conv = new JSONListConverter<BaseErrorDescription>();
		TypeReference<List<BaseErrorDescription>> mapType = new TypeReference<List<BaseErrorDescription>>() {
		};
		return conv.jsonToList(jsonText, mapType);
	}

	@Override
	public String toString() {
		return "BaseErrorDescription [error=" + error + ", errorDescription=" + errorDescription + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((error == null) ? 0 : error.hashCode());
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
		BaseErrorDescription other = (BaseErrorDescription) obj;
		if (error == null) {
			if (other.error != null)
				return false;
		} else if (!error.equals(other.error))
			return false;
		return true;
	}

	public static String buildInvalidToken(String token) {
		BaseErrorDescription bed = new BaseErrorDescription("invalid_token", "Access token expired: " + token); 
		return bed.toJSON();
	}

}
