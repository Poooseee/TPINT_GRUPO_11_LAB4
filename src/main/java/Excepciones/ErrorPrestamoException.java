package Excepciones;

public class ErrorPrestamoException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String getMessage() {
		return ("Ocurrio un error al insertar el préstamo");
	}
	
	

}
