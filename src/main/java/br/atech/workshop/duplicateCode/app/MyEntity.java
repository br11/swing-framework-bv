package br.atech.workshop.duplicateCode.app;

import br.atech.workshop.duplicateCode.validation.Required;

public class MyEntity implements Cloneable {

	@Required
	private String name;

	@Required
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#clone()
	 */
	@Override
	public MyEntity clone() {
		try {
			return (MyEntity) super.clone();
		} catch (CloneNotSupportedException e) {
			throw new RuntimeException(e);
		}
	}
}
