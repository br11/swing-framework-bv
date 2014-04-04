package br.atech.workshop.duplicateCode.validation;

import java.util.Locale;

import javax.validation.MessageInterpolator;

public class CustomMessageInterpolator implements MessageInterpolator {

	public CustomMessageInterpolator() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.validation.MessageInterpolator#interpolate(java.lang.String,
	 * javax.validation.MessageInterpolator.Context)
	 */
	@Override
	public String interpolate(String validationMessage, Context context) {
		return validationMessage;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.validation.MessageInterpolator#interpolate(java.lang.String,
	 * javax.validation.MessageInterpolator.Context, java.util.Locale)
	 */
	@Override
	public String interpolate(String validationMessage, Context context,
			Locale locale) {
		return validationMessage;
	}
}
