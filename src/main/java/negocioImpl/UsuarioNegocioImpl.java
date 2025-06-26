package negocioImpl;

import datos.UsuarioDao;
import datosImpl.UsuarioDaoImpl;
import entidades.Usuario;
import negocio.UsuarioNegocio;

public class UsuarioNegocioImpl implements UsuarioNegocio {

    private UsuarioDao usuarioDao = new UsuarioDaoImpl();

    @Override
    public Usuario login(String nick, String password) {
        Usuario usuario = new Usuario();
        usuario.setNickUsuario(nick);
        usuario.setContraseñaUsuario(password);

        try {
            Usuario usuarioAutenticado = usuarioDao.login(usuario);
            return usuarioAutenticado; // null si no existe
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}