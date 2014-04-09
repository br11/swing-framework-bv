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
	 * @param bean
	 * @return
	 * @throws AppException
	 */
	public MyEntity feature1(MyEntity bean) throws AppException {
		takeTime();
		MyEntity clone = bean.clone();
		clone.setResult(String.format("Bom dia %s!", bean.getName()));

		return clone;
	}

	/**
	 * 
	 * @param bean
	 * @return
	 * @throws AppException
	 */
	public MyEntity feature2(MyEntity bean) throws AppException {
		takeTime();
		MyEntity clone = bean.clone();
		clone.setResult(String.format("Boa tarde %s!", bean.getName()));

		return clone;
	}

	/**
	 * 
	 * @param bean
	 * @return
	 * @throws AppException
	 */
	public MyEntity feature3(MyEntity bean) throws AppException {
		takeTime();
		MyEntity clone = bean.clone();
		clone.setResult(String.format("Boa noite %s!", bean.getName()));

		return clone;
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
