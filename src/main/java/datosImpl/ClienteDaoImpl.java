package datosImpl;

import java.sql.*;
import java.util.*;
import datos.ClienteDao;
import entidades.*;
import negocioImpl.LocalidadNegocioImpl;
import negocioImpl.PaisNegocioImpl;
import negocioImpl.ProvinciaNegocioImpl;

public class ClienteDaoImpl implements ClienteDao {

    private Conexion cn;

    @Override
    public List<Cliente> listar() {
        List<Cliente> lista = new ArrayList<>();
        PaisNegocioImpl paNeg = new PaisNegocioImpl();
        ProvinciaNegocioImpl provNeg = new ProvinciaNegocioImpl();
        LocalidadNegocioImpl locNeg = new LocalidadNegocioImpl();
        
        try {
            cn = new Conexion();
            cn.Open();
            String query = "SELECT C.*, T.telefono_Tel, U.nick_usr, U.tipo_Usr, U.contraseña_Usr FROM CLIENTES C "
            		+ "LEFT JOIN TELEFONOSXCLIENTES TxC ON C.DNI_Cl = TxC.DNI_TxC "
            		+ "LEFT JOIN TELEFONOS T ON TxC.idTelefono_TxC = T.idTelefono_Tel "
            		+ "LEFT JOIN USUARIOSXCLIENTES UxC ON C.DNI_Cl = UxC.DNI_UxC "
            		+ "INNER JOIN USUARIOS U ON UxC.idUsuario_UxC = U.idUsuario_Usr "
            		+ "WHERE U.tipo_Usr = 'CLIENTE';";
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

                int idNac = (rs.getInt("nacionalidad_Cl"));
                Pais nacionalidad = new Pais();
                nacionalidad = paNeg.obtenerPaisxId(idNac);
                c.setNacionalidad(nacionalidad);
                
                int idPa = (rs.getInt("pais_Cl"));
                Pais pais = new Pais();
                pais = paNeg.obtenerPaisxId(idPa);
                c.setPais(pais);

                int idProv = (rs.getInt("provincia_Cl"));
                Provincia provincia = new Provincia();
                provincia = provNeg.obtenerProvinciaPorId(idProv, idPa);
                c.setProvincia(provincia);

                int idLoc = (rs.getInt("localidad_Cl"));
                Localidad localidad = new Localidad();
                localidad = locNeg.obtenerLocalidadPorId(idLoc, idProv, idPa);
                c.setLocalidad(localidad);

                c.setDomicilio(rs.getString("domicilio_Cl"));
                c.setFechaNacimiento(rs.getDate("nacimiento_Cl"));
                c.setEmail(rs.getString("mail_Cl"));
                c.setTelefono(rs.getString("telefono_Tel"));
                c.setNick(rs.getString("nick_Usr"));
                c.setPassword(rs.getString("contraseña_Usr"));
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
    public int agregar(Cliente cliente, Usuario user) {
        int filas = 0;
        try {
            cn = new Conexion();
            cn.Open();
            String queryCliente = "INSERT INTO CLIENTES (dni_Cl, cuil_Cl, nombre_Cl, apellido_Cl, sexo_Cl, pais_Cl, nacionalidad_Cl, provincia_Cl, localidad_Cl, nacimiento_Cl, domicilio_Cl, mail_Cl, baja_Cl) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = cn.prepare(queryCliente);
            ps.setString(1, cliente.getDNI());
            ps.setString(2, cliente.getCUIL());
            ps.setString(3, cliente.getNombre());
            ps.setString(4, cliente.getApellido());
            ps.setString(5, cliente.getSexo());
            ps.setInt(6, cliente.getPais().getId());
            ps.setInt(7, cliente.getNacionalidad().getId());
            ps.setInt(8, cliente.getProvincia().getId());
            ps.setInt(9, cliente.getLocalidad().getId());
            ps.setDate(10, cliente.getFechaNacimiento());
            ps.setString(11, cliente.getDomicilio());
            ps.setString(12, cliente.getEmail());
            ps.setInt(13, cliente.getBaja());
            filas = ps.executeUpdate();
            
            String queryTelefono = "INSERT INTO TELEFONOS (telefono_Tel) VALUES (?)";
            PreparedStatement psTel = cn.prepare(queryTelefono, Statement.RETURN_GENERATED_KEYS);
            psTel.setString(1, cliente.getTelefono());
            psTel.executeUpdate();
            int idTelefono = -1;
            ResultSet rsTel = psTel.getGeneratedKeys();
            if(rsTel.next()) {
            	idTelefono = rsTel.getInt(1);
            }else {
            	throw new SQLException("No se generó ID de Télefono");
            }
            
            String queryTxC = "INSERT INTO TELEFONOSxCLIENTES (dni_TxC, idTelefono_TxC) VALUES (?, ?)";
            PreparedStatement psTxC = cn.prepare(queryTxC);
            psTxC.setString(1, cliente.getDNI());
            psTxC.setInt(2, idTelefono);
            psTxC.executeUpdate();
            
            String queryUser = "INSERT INTO USUARIOS (nick_Usr, contraseña_Usr, tipo_Usr) VALUES (?, ?, ?)";
            PreparedStatement psUser = cn.prepare(queryUser, Statement.RETURN_GENERATED_KEYS);
            psUser.setString(1, user.getNickUsuario());
            psUser.setString(2, user.getContraseñaUsuario());
            psUser.setString(3, user.getTipoUsuario());
            psUser.executeUpdate();
            
            int idUsuario = -1;
            ResultSet rsUsr = psUser.getGeneratedKeys();
            if (rsUsr.next()) {
            	idUsuario = rsUsr.getInt(1);
            } else {
                throw new SQLException("No se generó ID de usuario.");
            }
            
            String queryUxC = "INSERT INTO USUARIOSXCLIENTES (idUsuario_UxC, dni_UxC) VALUES (?, ?)";
            PreparedStatement psUxC = cn.prepare(queryUxC);
            psUxC.setInt(1, idUsuario);
            psUxC.setString(2, cliente.getDNI());
            psUxC.executeUpdate();
            
            rsTel.close();
            rsUsr.close();
            ps.close();
            psTel.close();
            psTxC.close();
            psUser.close();
            psUxC.close();
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
            String query = "UPDATE CLIENTES SET nombre_Cl=?, apellido_Cl=?, sexo_Cl=?, pais_Cl=?, nacionalidad_Cl=?, provincia_Cl=?, localidad_Cl=?, nacimiento_Cl=?, domicilio_Cl=?, mail_Cl=? WHERE dni_cl = ?";
            PreparedStatement ps = cn.prepare(query);
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getApellido());
            ps.setString(3, cliente.getSexo());
            ps.setInt(4, cliente.getPais().getId());
            ps.setInt(5, cliente.getNacionalidad().getId());
            ps.setInt(6, cliente.getProvincia().getId());
            ps.setInt(7, cliente.getLocalidad().getId());
            ps.setDate(8, cliente.getFechaNacimiento());
            ps.setString(9, cliente.getDomicilio());
            ps.setString(10, cliente.getEmail());
            ps.setString(11, cliente.getDNI());
            resultado = ps.executeUpdate() > 0;
            System.out.println("Cliente actualizado: " + resultado);
            
            int idTel = 0;
            
            String queryTxC = "SELECT idTelefono_TxC FROM TELEFONOSXCLIENTES WHERE DNI_TxC = '" + cliente.getDNI() + "'";
            ResultSet rs = cn.query(queryTxC);
            while (rs.next()) {
            	idTel = rs.getInt("idTelefono_TxC");
            }
            rs.close();
            
            String queryTelefono = "UPDATE TELEFONOS SET telefono_Tel=? WHERE idTelefono_Tel=?";
            PreparedStatement psTel = cn.prepare(queryTelefono);
            psTel.setString(1, cliente.getTelefono());
            psTel.setInt(2, idTel);
            psTel.executeUpdate();
            
            String queryUser = "UPDATE USUARIOS SET contraseña_Usr=? WHERE nick_Usr=?";
            PreparedStatement psUser = cn.prepare(queryUser, Statement.RETURN_GENERATED_KEYS);
            psUser.setString(1, cliente.getPassword());
            psUser.setString(2, cliente.getNick());
            psUser.executeUpdate();
            
            psTel.close();
            psUser.close();
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