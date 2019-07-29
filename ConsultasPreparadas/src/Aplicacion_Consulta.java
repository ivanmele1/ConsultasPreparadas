import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;



public class Aplicacion_Consulta {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		JFrame mimarco=new Marco_Aplicacion();
		
		mimarco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		mimarco.setVisible(true);

	}

}

class Marco_Aplicacion extends JFrame{
	
	public Marco_Aplicacion(){
		
		setTitle ("Consulta BBDD");
		
		setBounds(500,300,400,400);
		
		setLayout(new BorderLayout());
		
		JPanel menus=new JPanel();
		
		menus.setLayout(new FlowLayout());
		
		secciones=new JComboBox();
		
		secciones.setEditable(false);
		
		secciones.addItem("Todos");
		
		paises=new JComboBox();
		
		paises.setEditable(false);
		
		paises.addItem("Todos");
		
		resultado= new JTextArea(4,50);
		
		resultado.setEditable(false);
		
		add(resultado);
		
		menus.add(secciones);
		
		menus.add(paises);	
		
		add(menus, BorderLayout.NORTH);
		
		add(resultado, BorderLayout.CENTER);
		
		JButton botonConsulta=new JButton("Consulta");	
		
		botonConsulta.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ejecutaConsulta();
				
			}
		});
		
		add(botonConsulta, BorderLayout.SOUTH);
		
		
		
		//---------------CONEXION BBDD------------------
		
				try {
					
					String db = "curso_sql"; 
			 		int port = 3306;
			 		String host = "localhost";
					String url  = String.format("jdbc:mysql://%s:%d/%s?useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=Europe/Moscow", host, port, db);
					String user = "root";
					String password = "0110";
					
					con = DriverManager.getConnection(url, user, password);
					if (con != null) {
						System.out.println(" Conexión con la base de datos creada");
					}else if (con == null) {
						System.out.println(" Conexión con la base de datos NO creada");
					}
					
					Statement st = con.createStatement();
					
					String consulta = "SELECT DISTINCTROW SECCIÓN FROM PRODUCTOS";
					
					ResultSet rs = st.executeQuery(consulta);
					
					while (rs.next()) {
						
						secciones.addItem(rs.getString(1));
						
					}
					
					rs.close();
					
					
					 consulta = "SELECT DISTINCTROW PAÍS_DE_ORIGEN FROM PRODUCTOS";
					
					 rs = st.executeQuery(consulta);
					
					while (rs.next()) {
						
						paises.addItem(rs.getString(1));
						
					}
					
					rs.close();
					
					
					
				} catch (Exception e) {
					// TODO: handle exception
				}
		
	}
	
	private void ejecutaConsulta() {
		
		ResultSet rs = null;
		
		try {
			
			resultado.setText("");
			
			String seccion = (String) secciones.getSelectedItem();
			
			String pais = (String) paises.getSelectedItem();
			
			if (!seccion.equals("Todos") && pais.equals("Todos")) {
				
				enviaConsultaSeccion = con.prepareStatement(consultaSeccion);
				
				enviaConsultaSeccion.setString(1, seccion);
				
				rs = enviaConsultaSeccion.executeQuery();
				
			} else if (seccion.equals("Todos") && !pais.equals("Todos")) {
				
				enviaConsultaPais = con.prepareStatement(consultaPais);
				
				enviaConsultaPais.setString(1, pais);
				
				rs = enviaConsultaPais.executeQuery();
				
			}else if (!seccion.equals("Todos") && !pais.equals("Todos")) {
				
				enviaConsultaTodos = con.prepareStatement(consultaTodos);
				
				enviaConsultaTodos.setString(1, seccion);
				
				enviaConsultaTodos.setString(2, pais);
				
				rs = enviaConsultaTodos.executeQuery();
				
			}
			
			
			
			while (rs.next()) {
				
				resultado.append(rs.getString(1));
				
				resultado.append(", ");
				
				resultado.append(rs.getString(2));
				
				resultado.append(", ");
				
				resultado.append(rs.getString(3));
				
				resultado.append(", ");
				
				resultado.append(rs.getString(4));
				
				resultado.append("\n");
				
				
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
	}
	
	
		
	private PreparedStatement enviaConsultaSeccion;
	
	private PreparedStatement enviaConsultaPais;
	
	private PreparedStatement enviaConsultaTodos;
	
	private final String consultaSeccion = "SELECT NOMBRE_ARTÍCULO, SECCIÓN, PRECIO, PAÍS_DE_ORIGEN FROM PRODUCTOS WHERE SECCIÓN=?";
	
	private final String consultaPais = "SELECT NOMBRE_ARTÍCULO, SECCIÓN, PRECIO, PAÍS_DE_ORIGEN FROM PRODUCTOS WHERE PAÍS_DE_ORIGEN=?";
	
	private final String consultaTodos = "SELECT NOMBRE_ARTÍCULO, SECCIÓN, PRECIO, PAÍS_DE_ORIGEN FROM PRODUCTOS WHERE SECCIÓN=? AND"
			+ " PAÍS_DE_ORIGEN=?";
	
	private JComboBox secciones;
	
	private JComboBox paises;
	
	private JTextArea resultado;	
	
	private Connection con;
	
	
	

	
}