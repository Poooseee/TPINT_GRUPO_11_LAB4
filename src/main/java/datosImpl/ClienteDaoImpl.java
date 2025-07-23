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
    public List<Cliente> listar(Cliente datosFiltracion) {
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
            	    + "WHERE U.tipo_Usr = 'CLIENTE'";
            
            if(datosFiltracion.getDNI() != null && !datosFiltracion.getDNI().isBlank()) {
            	query+= " AND DNI_Cl like '%"+datosFiltracion.getDNI()+"%' ";;
            }
            if(datosFiltracion.getCUIL()!=null && !datosFiltracion.getCUIL().isBlank()) {
            	query+= " AND cuil_cl like '%"+datosFiltracion.getCUIL()+"%' ";
            }
            if(datosFiltracion.getNombre()!=null && !datosFiltracion.getNombre().isBlank()) {
            	query+= " AND nombre_cl like '%"+datosFiltracion.getNombre()+"%' ";
            }
            if(datosFiltracion.getApellido()!=null && !datosFiltracion.getApellido().isBlank()) {
            	query+= " AND apellido_cl like '%"+datosFiltracion.getApellido()+"%' ";
            }
            if(datosFiltracion.getSexo()!=null && !datosFiltracion.getSexo().isBlank()) {
            	query+= " AND sexo_cl like '"+datosFiltracion.getSexo()+"%' ";
            }
            if(datosFiltracion.getFechaNacimiento()!=null) {
            	System.out.println(datosFiltracion.getFechaNacimiento());
            	query+= " AND nacimiento_cl like '"+datosFiltracion.getFechaNacimiento()+"%' ";
            }
      
            System.out.println("DNI: " + datosFiltracion.getDNI());
            System.out.println("Nombre: " + datosFiltracion.getNombre());
            
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
                c.setNick(rs.getString("nick_usr"));
                c.setPassword(rs.getString("contraseña_Usr"));
                c.setBaja(rs.getInt("baja_Cl"));

                lista.add(c);
            }
            rs.close();
            cn.close();
        } catch (Exception e) {
        	System.out.println("Cantidad de clientes obtenidos: " + lista.size());
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
            
            String queryUsr = "UPDATE USUARIOS SET contraseña_Usr=? WHERE nick_Usr = ?";
            PreparedStatement psUsr = cn.prepare(queryUsr);
            psUsr.setString(1, cliente.getPassword());
            psUsr.setString(2, cliente.getNick());
            resultado = psUsr.executeUpdate() > 0;
            
            String queryTel = "UPDATE TELEFONOS T JOIN TELEFONOSXCLIENTES TxC ON T.idTelefono_Tel = TxC.idTelefono_TxC SET T.telefono_Tel = ? WHERE TxC.DNI_TxC = ?";
            PreparedStatement psTel = cn.prepare(queryTel);
            psTel.setString(1, cliente.getTelefono());
            psTel.setString(2, cliente.getDNI());
            resultado = psTel.executeUpdate() > 0;
            
            ps.close();
            psUsr.close();
            psTel.close();
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

	@Override
	public Cliente obtenerPorUsuarioNick(String nick) {
	    Cliente cliente = null;
	    try {
	        cn = new Conexion();
	        cn.Open();
	        String query = "SELECT C.* FROM CLIENTES C "
	                     + "INNER JOIN USUARIOSXCLIENTES UxC ON C.DNI_Cl = UxC.DNI_UxC "
	                     + "INNER JOIN USUARIOS U ON UxC.idUsuario_UxC = U.idUsuario_Usr "
	                     + "WHERE U.nick_Usr = ?";
	        PreparedStatement stmt = cn.prepare(query);
	        stmt.setString(1, nick);
	        ResultSet rs = stmt.executeQuery();

	        if (rs.next()) {
	            cliente = new Cliente();
	            cliente.setDNI(rs.getString("DNI_Cl"));
	            cliente.setCUIL(rs.getString("CUIL_Cl"));
	            cliente.setNombre(rs.getString("nombre_Cl"));
	            cliente.setApellido(rs.getString("apellido_Cl"));
	            
	            Sexo sexo = new Sexo();
	            sexo.setSexo(rs.getString("sexo_Cl"));
	            cliente.setSexo(sexo.getSexo());

	            PaisNegocioImpl paNeg = new PaisNegocioImpl();
	            ProvinciaNegocioImpl provNeg = new ProvinciaNegocioImpl();
	            LocalidadNegocioImpl locNeg = new LocalidadNegocioImpl();
	            
	            int idNac = rs.getInt("nacionalidad_Cl");
	            cliente.setNacionalidad(paNeg.obtenerPaisxId(idNac));

	            int idPa = rs.getInt("pais_Cl");
	            cliente.setPais(paNeg.obtenerPaisxId(idPa));

	            int idProv = rs.getInt("provincia_Cl");
	            cliente.setProvincia(provNeg.obtenerProvinciaPorId(idProv, idPa));

	            int idLoc = rs.getInt("localidad_Cl");
	            cliente.setLocalidad(locNeg.obtenerLocalidadPorId(idLoc, idProv, idPa));

	            cliente.setDomicilio(rs.getString("domicilio_Cl"));
	            cliente.setFechaNacimiento(rs.getDate("nacimiento_Cl"));
	            cliente.setEmail(rs.getString("mail_Cl"));
	            cliente.setBaja(rs.getInt("baja_Cl"));
	        }

	        rs.close();
	        stmt.close();
	        cn.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return cliente;
	}
	
	@Override
	public Cliente obtenerClienteCompleto(String dni) {
	    Cliente cliente = null;
	    try {
	        cn = new Conexion();
	        cn.Open();
	        
	        String query = "SELECT c.*, " +
	                "p.nombre_Pa as pais_nombre, p.idPais_Pa as pais_id, " +
	                "p.nacionalidad_Pa as nacionalidad_nombre, " +
	                "pr.nombre_prov as provincia_nombre, pr.idProvincia_Prov as provincia_id, pr.idPais_Prov as provincia_idPais, " +
	                "l.nombre_Loc as localidad_nombre, l.idLocalidad_Loc as localidad_id, l.idProvincia_Loc as localidad_idProvincia, " +
	                "s.sexo_Sex as sexo_nombre, " +
	                "t.telefono_Tel as telefono, " +
	                "u.nick_usr, u.contraseña_usr " +
	                "FROM CLIENTES c " +
	                "LEFT JOIN PAISES p ON c.pais_Cl = p.idPais_Pa " +
	                "LEFT JOIN PROVINCIAS pr ON c.provincia_Cl = pr.idProvincia_Prov " +
	                "LEFT JOIN LOCALIDADES l ON c.localidad_Cl = l.idLocalidad_Loc " +
	                "LEFT JOIN SEXOS s ON c.sexo_Cl = s.sexo_Sex " +
	                "LEFT JOIN TELEFONOSXCLIENTES tx ON c.DNI_Cl = tx.DNI_TxC " +
	                "LEFT JOIN TELEFONOS t ON tx.idTelefono_TxC = t.idTelefono_Tel " +
	                "LEFT JOIN USUARIOSXCLIENTES uxc ON c.DNI_Cl = uxc.DNI_UxC " +
	                "LEFT JOIN USUARIOS u ON uxc.idUsuario_UxC = u.idUsuario_Usr " +
	                "WHERE c.DNI_Cl = ?";
	        
	        PreparedStatement stmt = cn.prepare(query);
	        stmt.setString(1, dni);
	        ResultSet rs = stmt.executeQuery();

	        if (rs.next()) {
	            cliente = new Cliente();
	            
	            // Mapeo de campos directos
	            cliente.setDNI(rs.getString("DNI_Cl"));
	            cliente.setCUIL(rs.getString("CUIL_Cl"));
	            cliente.setNombre(rs.getString("nombre_Cl"));
	            cliente.setApellido(rs.getString("apellido_Cl"));
	            cliente.setSexo(rs.getString("sexo_nombre"));
	            cliente.setDomicilio(rs.getString("domicilio_Cl"));
	            cliente.setFechaNacimiento(rs.getDate("nacimiento_Cl"));
	            cliente.setEmail(rs.getString("mail_Cl"));
	            cliente.setTelefono(rs.getString("telefono"));
	            cliente.setNick(rs.getString("nick_usr"));
	            cliente.setPassword(rs.getString("contraseña_usr"));
	            cliente.setBaja(rs.getInt("baja_Cl"));
	            
	            // Mapeo de objetos relacionados
	            Pais nacionalidad = new Pais();
	            nacionalidad.setNombre(rs.getString("nacionalidad_nombre"));
	            nacionalidad.setId(0);
	            cliente.setNacionalidad(nacionalidad);
	            
	            Pais pais = new Pais();
	            pais.setId(rs.getInt("pais_id"));
	            pais.setNombre(rs.getString("pais_nombre"));
	            cliente.setPais(pais);
	            
	            Provincia provincia = new Provincia();
	            provincia.setId(rs.getInt("provincia_id"));
	            provincia.setIdPais(rs.getInt("provincia_idPais"));
	            provincia.setNombre(rs.getString("provincia_nombre"));
	            cliente.setProvincia(provincia);
	            
	            Localidad localidad = new Localidad();
	            localidad.setId(rs.getInt("localidad_id"));
	            localidad.setIdProvincia(rs.getInt("localidad_idProvincia"));
	            localidad.setNombre(rs.getString("localidad_nombre"));
	            cliente.setLocalidad(localidad);
	        }

	        rs.close();
	        stmt.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        if(cn != null) {
	            cn.close();
	        }
	    }
	    return cliente;
	}
}