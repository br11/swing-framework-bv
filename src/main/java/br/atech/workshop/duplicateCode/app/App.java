/**
 * 
 */
package br.atech.workshop.duplicateCode.app;

/**
 * Camada de aplicação.
 * 
 * @author marcio
 * 
 */
public class App {

	/**
	 * 
	 * @param name
	 * @return
	 * @throws AppException
	 */
	public String feature1(String name) throws AppException {
		takeTime();
		return String.format("Bom dia %s!", name);
	}

	/**
	 * 
	 * @param name
	 * @return
	 * @throws AppException
	 */
	public String feature2(String name) throws AppException {
		takeTime();
		return String.format("Boa tarde %s!", name);
	}

	/**
	 * 
	 * @param name
	 * @return
	 * @throws AppException
	 */
	public String feature3(String name) throws AppException {
		takeTime();
		return String.format("Boa noite %s!", name);
	}

	private void takeTime() throws AppException {
		if (Math.random() > 0.9) {
			throw new AppException("Erro.");
		} else if (Math.random() > 0.8) {
			throw new RuntimeException();
		}

		try {
			Thread.sleep(700);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
