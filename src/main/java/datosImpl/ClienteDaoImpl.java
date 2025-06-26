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
            String query = "SELECT * FROM CLIENTES";
            ResultSet rs = cn.query(query);
            while (rs.next()) {
                Cliente c = new Cliente();
                c.setDNI(rs.getString("dni"));
                c.setCUIL(rs.getString("cuil"));
                c.setNombre(rs.getString("nombre"));
                c.setApellido(rs.getString("apellido"));
                
                Sexo sexo = new Sexo(rs.getString("sexo"));
                c.setSexo(sexo);

                Pais pais = new Pais();
                pais.setId(rs.getInt("idPais"));
                pais.setNombre(rs.getString("nacionalidad"));
                c.setNacionalidad(pais);

                Provincia provincia = new Provincia();
                provincia.setId(rs.getInt("idProvincia"));
                provincia.setNombre(rs.getString("provincia"));
                c.setProvincia(provincia);

                Localidad localidad = new Localidad();
                localidad.setId(rs.getInt("idLocalidad"));
                localidad.setNombre(rs.getString("localidad"));
                c.setLocalidad(localidad);

                c.setFechaNacimiento(rs.getDate("fechaNacimiento"));
                c.setDomicilio(rs.getString("domicilio"));
                c.setEmail(rs.getString("email"));
                c.setTelefono(rs.getString("telefono"));
                c.setBaja(rs.getBoolean("baja"));

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
    public boolean agregar(Cliente cliente) {
        boolean resultado = false;
        try {
            cn = new Conexion();
            cn.Open();
            String query = "INSERT INTO CLIENTES (dni, cuil, nombre, apellido, sexo, idPais, nacionalidad, idProvincia, provincia, idLocalidad, localidad, fechaNacimiento, domicilio, email, telefono, baja) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = cn.prepare(query);
            ps.setString(1, cliente.getDNI());
            ps.setString(2, cliente.getCUIL());
            ps.setString(3, cliente.getNombre());
            ps.setString(4, cliente.getApellido());
            ps.setString(5, cliente.getSexo().getSexo());
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
            ps.setBoolean(16, cliente.getBaja() != null ? cliente.getBaja() : false);
            resultado = ps.executeUpdate() > 0;
            ps.close();
            cn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultado;
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
            ps.setString(4, cliente.getSexo().getSexo());
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
            ps.setBoolean(15, cliente.getBaja() != null ? cliente.getBaja() : false);
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
    public boolean eliminar(String dni) {
        boolean resultado = false;
        try {
            cn = new Conexion();
            cn.Open();
            String query = "DELETE FROM CLIENTES WHERE DNI_Cl = ?";
            PreparedStatement ps = cn.prepare(query);
            ps.setString(1, dni);
            resultado = ps.executeUpdate() > 0;
            ps.close();
            cn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultado;
    }

	
	@Override
	public boolean existe(String dni) {
		  boolean existe = false;
	        try {
	            cn = new Conexion();
	            cn.Open();
	            String query = "SELECT * FROM CLIENTES WHERE DNI_Cl = ?";
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
