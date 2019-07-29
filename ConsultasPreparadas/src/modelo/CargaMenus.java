package modelo;

import java.sql.*;

public class CargaMenus {
	
	public CargaMenus() {
		
		con = new Conexion();
	}
	
	public String ejecutaConsultas() {
		
		Productos miProducto = null;
		
		Connection accesoBBDD = con.dameConexion();
		
		try {
			
			Statement secciones = accesoBBDD.createStatement();
			
			Statement paises = accesoBBDD.createStatement();
			
			rs = secciones.executeQuery("SELECT DISTINCTROW SECCIÓN FROM PRODUCTOS");
			
			rs2 = paises.executeQuery("SELECT DISTINCTROW PAÍS_DE_ORIGEN FROM PRODUCTOS");
				
				miProducto = new Productos();
				
				miProducto.setSeccion(rs.getString(1));
				
				miProducto.setpOrigen(rs2.getString(1));
				
			rs.close();
			
			rs2.close();
			
			accesoBBDD.close();
			
		} catch (Exception e) {
			
			// TODO: handle exception
		}
		
		return miProducto.getSeccion();
	}
	
	/*public ResultSet ejecutaConsultas() {
		
		Connection accesoBBDD = con.dameConexion();
		
		try {
			
			Statement secciones = accesoBBDD.createStatement();
			
			return rs = secciones.executeQuery("SELECT DISTINCTROW SECCIÓN FROM PRODUCTOS");
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		return null;
		
		
	}*/
	
	
	
	Conexion con;
	
	public ResultSet rs;
	
	public ResultSet rs2;

}
