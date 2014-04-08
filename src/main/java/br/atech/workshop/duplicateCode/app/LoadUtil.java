/**
 * 
 */
package br.atech.workshop.duplicateCode.app;

import java.util.Scanner;

import br.atech.workshop.duplicateCode.gui.AbstractGui;
import br.atech.workshop.duplicateCode.gui.Gui1;
import br.atech.workshop.duplicateCode.gui.Gui2;
import br.atech.workshop.duplicateCode.gui.Gui3;

/**
 * 
 * 
 * @author marcio
 * 
 */
public class LoadUtil {

	/**
	 * 
	 * @param args
	 * @return
	 */
	public static AbstractGui loadGui(String[] args) {
		AbstractGui gui;
		int guiId;
		if (args.length == 1) {
			// informado na linha de comando
			guiId = (int) Integer.parseInt(args[0]);
		} else {
			// pedir para o usuário informar
			Scanner keyboard = new Scanner(System.in);
			System.out.print("Informe o número da tela (1-2):");
			guiId = keyboard.nextInt();
			keyboard.close();
		}

		switch (guiId) {
		case 1:
			gui = new Gui1(new App());
			break;
		case 2:
			gui = new Gui2();
			break;
		case 3:
			gui = new Gui3();
			break;
		default:
			gui = null;
			break;
		}

		return gui;
	}
}
