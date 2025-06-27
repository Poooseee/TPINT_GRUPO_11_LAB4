package datosImpl;

import java.sql.*;
import java.util.*;
import datos.ClienteDao;
import entidades.*;

public class ClienteDaoImpl implements ClienteDao {

    private Conexion cn;

    @Override
    public List<Cliente> listar() {
        List<Cliente> lista = new ArrayList<>();
        try {
            cn = new Conexion();
            cn.Open();
            String query = "SELECT C.*, TxC.telefono_TxC, U.nick_usr, U.contraseña_usr FROM CLIENTES C "
            		+ "LEFT JOIN TELEFONOSXCLIENTES TxC ON C.DNI_Cl = TxC.DNI_TxC "
            		+ "LEFT JOIN USUARIOSXCLIENTES UxC ON C.DNI_Cl = UxC.DNI_UxC "
            		+ "RIGHT JOIN USUARIOS U ON UxC.idUsuario_UxC = U.idUsuario_Usr;";
            ResultSet rs = cn.query(query);
            while (rs.next()) {
                Cliente c = new Cliente();
                c.setDNI(rs.getString("DNI_Cl"));
                c.setCUIL(rs.getString("CUIL_Cl"));
                c.setNombre(rs.getString("nombre_Cl"));
                c.setApellido(rs.getString("apellido_Cl"));
                
                Sexo sexo = new Sexo();
                sexo.setSexo(rs.getString("sexo_Cl"));
                c.setSexo(sexo.getSexo());

                Pais nacionalidad = new Pais();
                nacionalidad.setNombre(rs.getString("nacionalidad_Cl"));
                c.setNacionalidad(nacionalidad);

                Provincia provincia = new Provincia();
                provincia.setNombre(rs.getString("provincia_Cl"));
                c.setProvincia(provincia);

                Localidad localidad = new Localidad();
                localidad.setNombre(rs.getString("localidad_Cl"));
                c.setLocalidad(localidad);

                c.setDomicilio(rs.getString("domicilio_Cl"));
                c.setFechaNacimiento(rs.getDate("nacimiento_Cl"));
                c.setEmail(rs.getString("mail_Cl"));
                c.setTelefono(rs.getString("telefono_TxC"));
                c.setNick(rs.getString("nick_usr"));
                c.setPassword(rs.getString("contraseña_usr"));
                c.setBaja(rs.getInt("baja_Cl"));

                lista.add(c);
            }
            rs.close();
            cn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public int agregar(Cliente cliente) {
        int filas = 0;
        try {
            cn = new Conexion();
            cn.Open();
            String query = "INSERT INTO CLIENTES (dni, cuil, nombre, apellido, sexo, idPais, nacionalidad, idProvincia, provincia, idLocalidad, localidad, fechaNacimiento, domicilio, email, telefono, baja) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = cn.prepare(query);
            ps.setString(1, cliente.getDNI());
            ps.setString(2, cliente.getCUIL());
            ps.setString(3, cliente.getNombre());
            ps.setString(4, cliente.getApellido());
            ps.setString(5, cliente.getSexo());
            ps.setInt(6, cliente.getNacionalidad().getId());
            ps.setString(7, cliente.getNacionalidad().getNombre());
            ps.setInt(8, cliente.getProvincia().getId());
            ps.setString(9, cliente.getProvincia().getNombre());
            ps.setInt(10, cliente.getLocalidad().getId());
            ps.setString(11, cliente.getLocalidad().getNombre());
            ps.setDate(12, cliente.getFechaNacimiento());
            ps.setString(13, cliente.getDomicilio());
            ps.setString(14, cliente.getEmail());
            ps.setString(15, cliente.getTelefono());
            ps.setInt(16, cliente.getBaja());

            filas = ps.executeUpdate();
            ps.close();
            cn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return filas;
    }

    @Override
    public boolean modificar(Cliente cliente) {
        boolean resultado = false;
        try {
            cn = new Conexion();
            cn.Open();
            String query = "UPDATE CLIENTES SET cuil=?, nombre=?, apellido=?, sexo=?, idPais=?, nacionalidad=?, idProvincia=?, provincia=?, idLocalidad=?, localidad=?, fechaNacimiento=?, domicilio=?, email=?, telefono=?, baja=? WHERE dni = ?";
            PreparedStatement ps = cn.prepare(query);
            ps.setString(1, cliente.getCUIL());
            ps.setString(2, cliente.getNombre());
            ps.setString(3, cliente.getApellido());
            ps.setString(4, cliente.getSexo());
            ps.setInt(5, cliente.getNacionalidad().getId());
            ps.setString(6, cliente.getNacionalidad().getNombre());
            ps.setInt(7, cliente.getProvincia().getId());
            ps.setString(8, cliente.getProvincia().getNombre());
            ps.setInt(9, cliente.getLocalidad().getId());
            ps.setString(10, cliente.getLocalidad().getNombre());
            ps.setDate(11, cliente.getFechaNacimiento());
            ps.setString(12, cliente.getDomicilio());
            ps.setString(13, cliente.getEmail());
            ps.setString(14, cliente.getTelefono());
            ps.setInt(16, cliente.getBaja());
            ps.setString(16, cliente.getDNI());
            resultado = ps.executeUpdate() > 0;
            ps.close();
            cn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultado;
    }

    @Override
    public int eliminar(String dni) {
        int filas = 0;
        try {
            cn = new Conexion();
            cn.Open();
            String query = "UPDATE CLIENTES SET baja_Cl = 1 WHERE DNI_Cl = ?";
            PreparedStatement ps = cn.prepare(query);
            ps.setString(1, dni);
            filas = ps.executeUpdate();
            ps.close();
            cn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return filas;
    }
    
	@Override
	public boolean existe(String dni) {
		  boolean existe = false;
	        try {
	            cn = new Conexion();
	            cn.Open();
	            String query = "SELECT * FROM CLIENTES WHERE DNI_CL = ?";
	            PreparedStatement ps = cn.prepare(query);
	            ps.setString(1, dni);
	          ResultSet rs = ps.executeQuery();
	          if(rs.next()) {
	        	  existe = true;
	          }
	            ps.close();
	            cn.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return existe;
	}
}