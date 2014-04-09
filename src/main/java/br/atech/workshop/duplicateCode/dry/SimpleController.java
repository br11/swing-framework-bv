/**
 * 
 */
package br.atech.workshop.duplicateCode.dry;

/**
 * @author spac2
 * 
 */
public class SimpleController<T> extends AbstractControler<T> {

	/**
	 * 
	 * @param modelType
	 */
	public SimpleController(Class<T> modelType) {
		super(modelType);
	}

}
