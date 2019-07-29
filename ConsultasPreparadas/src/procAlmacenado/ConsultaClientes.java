package procAlmacenado;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import com.mysql.cj.jdbc.CallableStatement;

public class ConsultaClientes {

	public static void main(String[] args) {
		
		
		try {
			
			Connection con;
			String db = "curso_sql"; 
			int port = 3306;
			String host = "localhost";
			String url  = String.format("jdbc:mysql://%s:%d/%s?useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=Europe/Moscow", host, port, db);
			String user = "root";
			String password = "0110";
			
			con = DriverManager.getConnection(url, user, password);	
			
			
			CallableStatement misentencia = (CallableStatement) con.prepareCall("{call MUESTRA_CLIENTES}");
			
			ResultSet rs = misentencia.executeQuery();
			
			while (rs.next()) {
				
				System.out.println(rs.getString(1)+ " " + rs.getString(2) + " " + rs.getString(3));
				
			}
			
			rs.close();
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	
}
