package datosImpl;

import java.sql.*;
import java.util.ArrayList;
import datos.MovimientoDao;
import entidades.Movimiento;

public class MovimientoDaoImpl implements MovimientoDao {
    private Conexion cn;

    @Override
    public ArrayList<Movimiento> obtenerPorCliente(String dniCliente) {
        ArrayList<Movimiento> movimientos = new ArrayList<>();
        try {
            cn = new Conexion();
            cn.Open();
            
            String query = "SELECT m.idMovimiento_Movs, m.fecha_Movs, c.CBU_Ctas as numero_cuenta, " +
                          "m.detalle_Movs, m.importe_Movs, tm.descripcion_TMovs as tipo_movimiento " +
                          "FROM MOVIMIENTOS m " +
                          "JOIN CUENTAS c ON m.numeroCuenta_Movs = c.numeroCuenta_Ctas " +
                          "JOIN TIPOS_DE_MOVIMIENTOS tm ON m.tipoMovimiento_Movs = tm.idTipoMov_TMovs " +
                          "WHERE m.DNI_Movs = ? " +
                          "ORDER BY m.fecha_Movs DESC";
            
            PreparedStatement stmt = cn.prepare(query);
            stmt.setString(1, dniCliente);
            
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()) {
                Movimiento m = new Movimiento();
                m.setId(rs.getInt("idMovimiento_Movs"));
                m.setFecha(rs.getDate("fecha_Movs"));
                m.setNroCuenta(rs.getString("numero_cuenta")); // Ahora es el CBU
                m.setDetalle(rs.getString("detalle_Movs"));
                m.setImporte(rs.getFloat("importe_Movs"));
                m.setTipo(rs.getString("tipo_movimiento")); // Ahora es la descripción
                movimientos.add(m);
            }
            
            rs.close();
            stmt.close();
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            if(cn != null) {
                cn.close();
            }
        }
        return movimientos;
    }

    @Override
    public ArrayList<Movimiento> filtrar(String dniCliente, String fecha, String nroCuenta, String importe, String tipo) {
        ArrayList<Movimiento> movimientos = new ArrayList<>();
        try {
            cn = new Conexion();
            cn.Open();
            
            StringBuilder query = new StringBuilder(
                "SELECT m.idMovimiento_Movs, m.fecha_Movs, c.CBU_Ctas as numero_cuenta, " +
                "m.detalle_Movs, m.importe_Movs, tm.descripcion_TMovs as tipo_movimiento " +
                "FROM MOVIMIENTOS m " +
                "JOIN CUENTAS c ON m.numeroCuenta_Movs = c.numeroCuenta_Ctas " +
                "JOIN TIPOS_DE_MOVIMIENTOS tm ON m.tipoMovimiento_Movs = tm.idTipoMov_TMovs " +
                "WHERE m.DNI_Movs = ? "
            );
            
            if(fecha != null && !fecha.isEmpty()) {
                query.append("AND DATE(m.fecha_Movs) = ? ");
            }
            
            if(nroCuenta != null && !nroCuenta.isEmpty()) {
                query.append("AND c.CBU_Ctas = ? ");
            }
            
            if(tipo != null && !tipo.isEmpty()) {
                query.append("AND tm.descripcion_TMovs = ? ");
            }
            
            query.append("ORDER BY m.fecha_Movs DESC");
            
            PreparedStatement stmt = cn.prepare(query.toString());
            int paramIndex = 1;
            stmt.setString(paramIndex++, dniCliente);
            
            if(fecha != null && !fecha.isEmpty()) {
                stmt.setDate(paramIndex++, Date.valueOf(fecha));
            }
            
            if(nroCuenta != null && !nroCuenta.isEmpty()) {
                stmt.setString(paramIndex++, nroCuenta);
            }
            
            if(tipo != null && !tipo.isEmpty()) {
                stmt.setString(paramIndex++, tipo);
            }
            
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()) {
                Movimiento m = new Movimiento();
                m.setId(rs.getInt("idMovimiento_Movs"));
                m.setFecha(rs.getDate("fecha_Movs"));
                m.setNroCuenta(rs.getString("numero_cuenta"));
                m.setDetalle(rs.getString("detalle_Movs"));
                m.setImporte(rs.getFloat("importe_Movs"));
                m.setTipo(rs.getString("tipo_movimiento"));
                movimientos.add(m);
            }
            
            rs.close();
            stmt.close();
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            if(cn != null) {
                cn.close();
            }
        }
        return movimientos;
    }
}