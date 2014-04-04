/**
 * 
 */
package br.atech.workshop.duplicateCode.app;

import java.util.Scanner;

import br.atech.workshop.duplicateCode.gui.AbstractGui;
import br.atech.workshop.duplicateCode.gui.Gui1;
import br.atech.workshop.duplicateCode.gui.Gui2;
import br.atech.workshop.duplicateCode.gui.Gui3;
import br.atech.workshop.duplicateCode.gui.Gui4;
import br.atech.workshop.duplicateCode.gui.Gui4a;
import br.atech.workshop.duplicateCode.gui.Gui5;
import br.atech.workshop.duplicateCode.gui.Gui6;
import br.atech.workshop.duplicateCode.gui.Gui7;
import br.atech.workshop.duplicateCode.gui.Gui8;

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
			System.out.print("Informe o número da tela (1-9):");
			guiId = keyboard.nextInt();
			keyboard.close();
		}

		switch (guiId) {
		case 1:
			gui = new Gui1(new App());
			break;
		case 2:
			gui = new Gui2(new App());
			break;
		case 3:
			gui = new Gui3(new App());
			break;
		case 4:
			gui = new Gui4(new App());
			break;
		case 5:
			gui = new Gui5(new App());
			break;
		case 6:
			gui = new Gui6(new App());
			break;
		case 7:
			gui = new Gui7(new App());
			break;
		case 8:
			gui = new Gui8(new App());
			break;
		case 9:
			gui = new Gui4a(new App());
			break;
		default:
			gui = null;
			break;
		}

		return gui;
	}
}
