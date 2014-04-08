package br.atech.workshop.duplicateCode.gui;

import br.atech.workshop.duplicateCode.app.App;
import br.atech.workshop.duplicateCode.app.MyEntity;
import br.atech.workshop.duplicateCode.dry.AbstractControler;

/**
 * 
 * @author marcio
 * 
 */
public class Gui3Controller extends AbstractControler<MyEntity> {

	private App app = new App();

	/**
	 * 
	 */
	public Gui3Controller() {
		super(MyEntity.class);
	}

	public void goodMorning() throws Exception {
		app.feature1(getModel());
	}

	public void goodAfternoon() throws Exception {
		app.feature2(getModel());
	}

	public void goodNight() throws Exception {
		app.feature3(getModel());
	}
}
