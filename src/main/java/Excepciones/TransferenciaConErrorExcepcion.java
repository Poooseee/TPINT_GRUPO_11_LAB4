package Excepciones;

public class TransferenciaConErrorExcepcion extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String getMessage() {
		
		return ("Ocurrio un error al enviar transferencia");
	}
	
	

}
