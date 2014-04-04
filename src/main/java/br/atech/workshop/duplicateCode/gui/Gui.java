package br.atech.workshop.duplicateCode.gui;

import javax.swing.JFrame;

/**
 * 
 * @author marcio
 * 
 */
public interface Gui {

	/**
	 * Exibir / Abrir a tela
	 */
	void show();

	/**
	 * Ocultar / Fechar a tela
	 */
	void hide();

	/**
	 * Exibir mensagem
	 * 
	 * @param err
	 */
	void print(String err);

	/**
	 * Prepara para o processamento de um evento.
	 */
	void reset();

	/**
	 * 
	 * @return
	 */
	JFrame getFrame();
	
	/**
	 * 
	 * @return
	 */
	Controller getController();
}