package procAlmacenado;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JOptionPane;

import com.mysql.cj.jdbc.CallableStatement;

public class Actualiza_Producto {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int nPrecio = Integer.parseInt(JOptionPane.showInputDialog("Introduce precio"));
		
		String nArticulo = JOptionPane.showInputDialog("Introduce nombre articulo");
		
		try {
					
					Connection con;
					String db = "curso_sql"; 
					int port = 3306;
					String host = "localhost";
					String url  = String.format("jdbc:mysql://%s:%d/%s?useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=Europe/Moscow", host, port, db);
					String user = "root";
					String password = "0110";
					
					con = DriverManager.getConnection(url, user, password);	
					
					CallableStatement misentencia = (CallableStatement) con.prepareCall("{call ACTUALIZA_PROD(?, ?)}");
					
					misentencia.setDouble(1, nPrecio);
					
					misentencia.setString(2, nArticulo);
					
					misentencia.execute();
					
					System.out.println("Actualiza!");
					
			}catch (Exception e) {
				// TODO: handle exception
			}

	}

}
