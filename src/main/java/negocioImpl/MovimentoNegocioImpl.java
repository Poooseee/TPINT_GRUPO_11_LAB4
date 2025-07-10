package negocioImpl;

import datosImpl.MovimientoDaoImpl;
import entidades.Movimiento;
import entidades.Transferencia;
import negocio.MovimientoNegocio;

public class MovimentoNegocioImpl implements MovimientoNegocio {
	
	MovimientoDaoImpl dao = new MovimientoDaoImpl();
	@Override
	public int realizarTransferencia(Movimiento movimientoEntrada, Movimiento movimientoSalida,
			Transferencia transferencia) {
		return dao.realizarTransferencia(movimientoEntrada, movimientoSalida, transferencia);
	}

}
