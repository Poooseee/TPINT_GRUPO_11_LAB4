package negocioImpl;

import entidades.TelefonosXclientes;
import negocio.TelefonosXclientesNegocio;
import datos.TelefonosXclientesDao;
import datosImpl.TelefonosXclientesDaoImpl;
public class TelefonosXclientesNegocioImpl implements TelefonosXclientesNegocio {
TelefonosXclientesDao dao = new TelefonosXclientesDaoImpl();
	@Override
	public boolean modificar(TelefonosXclientes telXcli) {
		return dao.modificar(telXcli);
	}

}
