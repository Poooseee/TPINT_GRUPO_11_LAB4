package negocio;


import entidades.Usuario;

public interface UsuarioNegocio {
    Usuario login(String nick, String password);
}