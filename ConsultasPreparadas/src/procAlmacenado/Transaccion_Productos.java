package procAlmacenado;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import com.mysql.cj.jdbc.CallableStatement;

public class Transaccion_Productos {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Connection con = null;
		
		try {
			
			String db = "curso_sql"; 
			int port = 3306;
			String host = "localhost";
			String url  = String.format("jdbc:mysql://%s:%d/%s?useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=Europe/Moscow", host, port, db);
			String user = "root";
			String password = "0110";
			
			con = DriverManager.getConnection(url, user, password);	
			
			con.setAutoCommit(false); //si no se realizan las tareas no completa
			
			Statement mistatement = con.createStatement();
			
			String instruccionSQL_1 = "DELETE FROM PRODUCTOS WHERE PAÍS_DE_ORIGEN = 'ITALIA'";
			
			String instruccionSQL_2 = "DELETE FROM PRODUCTOS WHERE PRECIO > 300 ";
			
			String instruccionSQL_3 = "UPDATE PRODUCTOS SET PRECIO = PRECIO * 1.15 ";
			
			boolean ejecutar = ejecutar_transaccion();
			
			if(ejecutar) {
				
				mistatement.executeUpdate(instruccionSQL_1);
				
				mistatement.executeUpdate(instruccionSQL_2);
				
				mistatement.executeUpdate(instruccionSQL_3);
				
				con.commit(); // da el Ok si se ejecuta todo
				
				System.out.println("Se ejecutó la transacción de forma correcta");
				
			} else {
				
				System.out.println("No se realizó ningún cambio");
			}
		
			
	}catch (Exception e) {
		
		
		try {
			con.rollback();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		System.out.println("Algo salió mal");
	}


	}
	
	static boolean ejecutar_transaccion() {
		
		String ejecucion = JOptionPane.showInputDialog("¿Ejecutamos transacción?");
		
		if(ejecucion.equals("Si")) return true;
		
		else return false;
		
	}

}
