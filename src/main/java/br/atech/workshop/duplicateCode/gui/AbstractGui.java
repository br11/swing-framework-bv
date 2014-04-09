package br.atech.workshop.duplicateCode.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Implementação inicial de uma tela. Define a estrutura de componentes que é
 * padrão em toda tela.
 * 
 * @author marcio
 * 
 */
public abstract class AbstractGui {

	private final JFrame frame;

	private final JPanel messagePanel;
	private final JLabel messagelbl;
	private final JPanel contentPanel;
	private final JPanel actionPanel;

	/**
	 * Construtor
	 */
	public AbstractGui() {
		frame = new JFrame();
		getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		getFrame().setTitle("Example GUI");
		getFrame().setSize(700, 150);
		getFrame().setLocationRelativeTo(null);

		messagePanel = new JPanel();
		messagePanel.setVisible(false);
		messagelbl = new JLabel();
		messagePanel.add(messagelbl);

		contentPanel = new JPanel();
		contentPanel.setLayout(new GridLayout(3, 2));

		actionPanel = new JPanel();

		getFrame().add(messagePanel, BorderLayout.NORTH);
		getFrame().add(contentPanel, BorderLayout.CENTER);
		getFrame().add(actionPanel, BorderLayout.SOUTH);
	}

	/**
	 * Adiciona um componente ao painel de ação.
	 * 
	 * @param component
	 * @return
	 */
	public <T extends JComponent> T addAction(T component) {
		actionPanel.add(component);
		return component;
	}

	/**
	 * Adiciona um componente ao painel de conteúdo.
	 * 
	 * @param component
	 * @return
	 */
	public <T extends JComponent> T addContent(T component) {
		contentPanel.add(component);
		return component;
	}

	/**
	 * 
	 * @param err
	 */
	public void print(String err) {
		messagePanel.setVisible(true);
		messagelbl.setText(err);
	}

	/**
	 * 
	 */
	public void hide() {
		getFrame().setVisible(false);
	}

	/**
	 * 
	 */
	public void show() {
		getFrame().setVisible(true);
	}

	/**
	 * Limpa o painel de mensagens.
	 */
	public void reset() {
		messagePanel.setVisible(false);
		messagelbl.setText("");
	}

	/**
	 * 
	 * @return
	 */
	public JFrame getFrame() {
		return frame;
	}
}