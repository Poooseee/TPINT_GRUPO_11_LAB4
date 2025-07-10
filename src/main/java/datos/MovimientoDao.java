package datos;

import entidades.Movimiento;
import entidades.Transferencia;

public interface MovimientoDao {
	
	public 	int realizarTransferencia(Movimiento movimientoEntrada, Movimiento movimientoSalida , Transferencia transferencia);
}
