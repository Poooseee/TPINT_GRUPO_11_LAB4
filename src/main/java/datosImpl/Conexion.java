package datosImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexion {

	private String host = "jdbc:mysql://localhost:3306/DB_TP?useUnicode=true&characterEncoding=UTF-8";
	private String user = "root";
	private String pass = "root";
	//private String pass = "13121401";
	protected Connection connection;
	
	public Connection Open(){
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			this.connection = DriverManager.getConnection(host, user, pass);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return this.connection;
	}
	
	public ResultSet query(String query)
	{
		Statement st;
		ResultSet rs=null;
		try
		{
			st= connection.createStatement();
			rs= st.executeQuery(query);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return rs;
	}
	
	public boolean execute(String query)
	{
		Statement st;
		boolean save = true;
		try {
			st = connection.createStatement();
		    st.executeUpdate(query);
		}
		catch(SQLException e)
		{
			save = false;
			e.printStackTrace();
		}
		return save;
	}
	
	public PreparedStatement prepare(String query) throws SQLException{
	    return connection.prepareStatement(query);
	}
	
	public boolean close()
	{
		boolean ok=true;
		try {
			connection.close();
		}
		catch(Exception e)
		{
			ok= false;
			e.printStackTrace();
		}
		return ok;
	}

	public PreparedStatement prepare(String queryUser, int returnGeneratedKeys) throws SQLException {
		return connection.prepareStatement(queryUser, returnGeneratedKeys);
	}
	
}
