/**
 * 
 */
package br.atech.workshop.duplicateCode.dry;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.util.LinkedHashSet;
import java.util.Set;

import br.atech.workshop.duplicateCode.dry.listeners.RequestListener;
import br.atech.workshop.duplicateCode.dry.listeners.ResponseListener;
import br.atech.workshop.duplicateCode.dry.listeners.ValidationListener;
import br.atech.workshop.duplicateCode.gui.Gui;

/**
 * 
 * 
 * @author marcio
 * 
 * @param <T>
 */
public class SimpleEventListener<T extends Gui<?>> extends
		ExtendedEventListener<T> {

	private SimpleEventUtil<T> util;

	private Set<ValidationListener> validationListeners = new LinkedHashSet<>();
	private Set<RequestListener> requestListeners = new LinkedHashSet<>();
	private Set<ResponseListener> responseListeners = new LinkedHashSet<>();

	/**
	 * 
	 * @param gui
	 * @param exHandler
	 */
	public SimpleEventListener(T gui, ExceptionHandler exHandler) {
		super(gui, exHandler);
		try {
			util = new SimpleEventUtil<T>(gui, this);
		} catch (NoSuchMethodException | SecurityException
				| IllegalArgumentException | IllegalAccessException e) {
			throw new RuntimeException(e);
		}

		if (gui instanceof ValidationListener) {
			addListener((ValidationListener) gui);
		}
		if (gui instanceof RequestListener) {
			addListener((RequestListener) gui);
		}
		if (gui instanceof ResponseListener) {
			addListener((ResponseListener) gui);
		}

		if (gui.getController() instanceof ValidationListener) {
			addListener((ValidationListener) gui.getController());
		}
		if (gui.getController() instanceof RequestListener) {
			addListener((RequestListener) gui.getController());
		}
		if (gui.getController() instanceof ResponseListener) {
			addListener((ResponseListener) gui.getController());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.atech.workshop.bestpractices.util.AbstractActionListener#onAction(
	 * java.awt.event.ActionEvent)
	 */
	@Override
	public void onAction(ActionEvent event) throws Exception {
		try {
			getGui().getFrame().setCursor(
					Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

			/*
			 * Invocando os RequestListerners
			 */
			for (RequestListener listener : requestListeners) {
				listener.onRequest(event);
			}

			/*
			 * Invocando os ValidationListeners
			 */
			for (ValidationListener listener : validationListeners) {
				listener.onValidate(event);
			}

			super.onAction(event);

		} finally {

			try {
				/*
				 * Invocando os ResponseListener
				 */
				for (ResponseListener listener : responseListeners) {
					listener.onResponse(event);
				}
			} finally {
				getGui().getFrame().setCursor(
						Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.atech.workshop.duplicateCode.dry.GenericEventListener#getUtil()
	 */
	@Override
	public EventUtil<T> getUtil() {
		return util;
	}

	/**
	 * 
	 * @param listener
	 * @return
	 */
	public SimpleEventListener<T> addListener(ValidationListener listener) {
		validationListeners.add(listener);
		return this;
	}

	/**
	 * 
	 * @param listener
	 * @return
	 */
	public SimpleEventListener<T> addListener(RequestListener listener) {
		requestListeners.add(listener);
		return this;
	}

	/**
	 * 
	 * @param listener
	 * @return
	 */
	public SimpleEventListener<T> addListener(ResponseListener listener) {
		responseListeners.add(listener);
		return this;
	}

	/**
	 * 
	 * @param listener
	 * @return
	 */
	public SimpleEventListener<T> removeListener(Object listener) {
		validationListeners.remove(listener);
		requestListeners.remove(listener);
		responseListeners.remove(listener);
		return this;
	}
}
