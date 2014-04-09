package br.atech.workshop.duplicateCode.dry.listeners;

import java.awt.event.ActionEvent;

/**
 * 
 * @author marcio
 * 
 */
public interface RequestListener {

	/**
	 * 
	 * @param event
	 * @throws Exception
	 */
	public void onRequest(ActionEvent event) throws Exception;
}
