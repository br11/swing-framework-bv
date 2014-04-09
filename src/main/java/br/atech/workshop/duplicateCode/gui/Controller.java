package br.atech.workshop.duplicateCode.gui;

/**
 * Processa os eventos da tela.
 * 
 * @author marcio
 * 
 */
public interface Controller<T> {

	/**
	 * 
	 * @return
	 */
	T getModel();
	
	/**
	 * 
	 * @param model
	 */
	void setModel(T model);
}
