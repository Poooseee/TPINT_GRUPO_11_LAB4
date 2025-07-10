package negocio;

import entidades.Movimiento;
import entidades.Transferencia;

public interface MovimientoNegocio {
	public int realizarTransferencia(Movimiento movimientoEntrada, Movimiento movimientoSalida , Transferencia transferencia);
		
}
