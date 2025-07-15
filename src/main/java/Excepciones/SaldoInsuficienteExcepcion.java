package Excepciones;

public class SaldoInsuficienteExcepcion extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public SaldoInsuficienteExcepcion() {
		
	}

	@Override
	public String getMessage() {

		return "Saldo insuficiente";
	}
}
