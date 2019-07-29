package modelo;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {
	
	Connection con;
	String db = "curso_sql"; 
	int port = 3306;
	String host = "localhost";
	String url  = String.format("jdbc:mysql://%s:%d/%s?useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=Europe/Moscow", host, port, db);
	String user = "root";
	String password = "0110";
	
	
	public Conexion() {
			
	}
	
	public Connection dameConexion(){	
		
		try {		
			
			con = DriverManager.getConnection(url, user, password);		
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return con;
		
	}

}
