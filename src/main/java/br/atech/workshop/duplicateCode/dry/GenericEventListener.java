/**
 * 
 */
package br.atech.workshop.duplicateCode.dry;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import br.atech.workshop.duplicateCode.gui.Gui;

/**
 * 
 * Event listener componentizado para uso nas telas da aplicação.
 * 
 * @author marcio
 * 
 */
public class GenericEventListener<T extends Gui<?>> implements ActionListener,
		DocumentListener {

	private final EventUtil<T> util;
	private final ExceptionHandler exHandler;

	/**
	 * 
	 * @param gui
	 * @param exHandler
	 */
	public GenericEventListener(T gui, ExceptionHandler exHandler) {
		try {
			this.util = new EventUtil<T>(gui, this);
			this.exHandler = exHandler;
		} catch (NoSuchMethodException | SecurityException
				| IllegalArgumentException | IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 
	 */
	public void activate() {
		getUtil().activate();
	}

	/**
	 * 
	 */
	public void deactivate() {
		getUtil().deactivate();
	}

	/**
	 * 
	 * @return
	 */
	public T getGui() {
		return getUtil().getGui();
	}

	/**
	 * 
	 * @param event
	 * @throws Exception
	 */
	public void onAction(ActionEvent event) throws Exception {
		doAction(event);
	}

	/**
	 * 
	 * @param event
	 * @throws Exception
	 */
	protected void doChange(DocumentEvent event) throws Exception {
		getUtil().execute(event);
	}

	/**
	 * 
	 * @param event
	 * @throws Exception
	 */
	public void onChange(DocumentEvent event) throws Exception {
		doChange(event);
	}

	/**
	 * 
	 * @param event
	 * @throws Exception
	 */
	protected void doAction(ActionEvent event) throws Exception {
		getUtil().execute(event);
	}

	/**
	 * 
	 * @param exception
	 * @param event
	 */
	public void onException(Throwable exception, Object event) {
		getExHandler().handle(exception);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public final void actionPerformed(ActionEvent event) {
		try {
			onAction(event);
		} catch (InvocationTargetException e) {
			onException(e.getCause(), event);
		} catch (Exception e) {
			onException(e, event);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.event.DocumentListener#insertUpdate(javax.swing.event.
	 * DocumentEvent)
	 */
	@Override
	public final void insertUpdate(DocumentEvent event) {
		try {
			onChange(event);
		} catch (InvocationTargetException e) {
			onException(e.getCause(), event);
		} catch (Exception e) {
			onException(e, event);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.event.DocumentListener#removeUpdate(javax.swing.event.
	 * DocumentEvent)
	 */
	@Override
	public final void removeUpdate(DocumentEvent event) {
		try {
			onChange(event);
		} catch (InvocationTargetException e) {
			onException(e.getCause(), event);
		} catch (Exception e) {
			onException(e, event);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.event.DocumentListener#changedUpdate(javax.swing.event.
	 * DocumentEvent)
	 */
	@Override
	public final void changedUpdate(DocumentEvent event) {
		try {
			onChange(event);
		} catch (InvocationTargetException e) {
			onException(e.getCause(), event);
		} catch (Exception e) {
			onException(e, event);
		}
	}

	/**
	 * @return the util
	 */
	public EventUtil<T> getUtil() {
		return util;
	}

	/**
	 * @return the exHandler
	 */
	public ExceptionHandler getExHandler() {
		return exHandler;
	}

}
