/**
 * 
 */
package br.atech.workshop.duplicateCode.app;

import br.atech.workshop.duplicateCode.gui.AbstractGui;

/**
 * 
 * 
 * @author marcio
 * 
 */
public class Boot {

	/**
	 * 
	 * @param args
	 * @throws AppException
	 */
	public static void main(String[] args) throws AppException {
		AbstractGui gui = LoadUtil.loadGui(args);		
		if (gui != null) {
			gui.show();
		}
	}
}
