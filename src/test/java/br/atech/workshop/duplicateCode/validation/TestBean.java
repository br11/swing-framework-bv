package br.atech.workshop.duplicateCode.validation;

import br.atech.workshop.duplicateCode.validation.Domain.PreDef;

/**
 * 
 * @author marcio
 *
 */
public class TestBean {
	
	@Domain({PreDef.Alfa, PreDef.NotNull})
	private String name;
	
	@Required
	@Domain(min = 18, max = 65)
	private Integer age;
	
	public TestBean(){
		super();
	}
	
	public TestBean(String nameVal, Integer ageVal) {
		name = nameVal;
		age = ageVal;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

}
