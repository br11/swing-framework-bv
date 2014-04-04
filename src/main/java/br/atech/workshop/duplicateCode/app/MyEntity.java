package br.atech.workshop.duplicateCode.app;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

public class MyEntity {

	@NotNull
	@NotEmpty
	private String name;

	@NotNull
	@NotEmpty
	private String result;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

}
