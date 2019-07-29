package modelo;

import java.sql.*;

public class EjecutaConsultas {
	
	public EjecutaConsultas() {
		
		miConexion = new Conexion();
	}

	public ResultSet filtraBBDD(String seccion, String pais) {
		
		Connection conecta = miConexion.dameConexion();
		
		rs = null;
		
		try {
		
		if (!seccion.equals("Todos") && pais.equals("Todos")) {
			
			enviaConsultaSeccion = conecta.prepareStatement(consultaSeccion);
			
			enviaConsultaSeccion.setString(1, seccion);
			
			rs = enviaConsultaSeccion.executeQuery();

			
		} else if(seccion.equals("Todos") && !pais.equals("Todos")) {
			
			enviaConsultaPais = conecta.prepareStatement(consultaPais);
			
			enviaConsultaPais.setString(1, pais);
			
			rs = enviaConsultaPais.executeQuery();
			
		}else {
			
			enviaConsultaTodos = conecta.prepareStatement(consultaTodos);
			
			enviaConsultaTodos.setString(1, seccion);
			enviaConsultaTodos.setString(2, pais);
			
			rs = enviaConsultaTodos.executeQuery();
			
			
		}
		
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		return rs;
		
	}
	
	private Conexion miConexion;
	
	private ResultSet rs;
	
	
	
	private PreparedStatement enviaConsultaSeccion;
	private final String consultaSeccion = "SELECT NOMBRE_ARTÍCULO, SECCIÓN, PRECIO, PAÍS_DE_ORIGEN FROM PRODUCTOS WHERE SECCIÓN =?";
	
	private PreparedStatement enviaConsultaPais;
	private final String consultaPais = "SELECT NOMBRE_ARTÍCULO, SECCIÓN, PRECIO, PAÍS_DE_ORIGEN FROM PRODUCTOS WHERE PAÍS_DE_ORIGEN =?";
	
	private PreparedStatement enviaConsultaTodos;
	private final String consultaTodos = "SELECT NOMBRE_ARTÍCULO, SECCIÓN, PRECIO, PAÍS_DE_ORIGEN FROM PRODUCTOS WHERE SECCIÓN =? AND PAÍS_DE_ORIGEN =?";
	
	
	
}
