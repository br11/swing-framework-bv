package br.atech.workshop.duplicateCode.gui;

import java.awt.event.ActionEvent;

import br.atech.workshop.duplicateCode.app.App;
import br.atech.workshop.duplicateCode.app.MyEntity;
import br.atech.workshop.duplicateCode.dry.AbstractControler;

/**
 * 
 * @author marcio
 * 
 */
public class Gui2Controller extends AbstractControler<MyEntity> {

	private App app = new App();

	/**
	 * 
	 */
	public Gui2Controller() {
		super(MyEntity.class);
	}

	public void goodMorningOnClick(ActionEvent event) throws Exception {
		app.feature1(getModel());
	}

	public void goodAfternoonOnClick(ActionEvent event) throws Exception {
		app.feature2(getModel());
	}

	public void goodNight(ActionEvent event) throws Exception {
		app.feature3(getModel());
	}
}
