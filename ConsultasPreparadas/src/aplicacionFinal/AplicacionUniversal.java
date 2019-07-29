package aplicacionFinal;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;

import java.sql.DatabaseMetaData;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;





public class AplicacionUniversal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		MarcoBBDD mimarco=new MarcoBBDD();
		
		mimarco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		mimarco.setVisible(true);

	}

}

class MarcoBBDD extends JFrame{

	public MarcoBBDD(){
		
		setBounds(300,300,700,700);
		
		LaminaBBDD milamina=new LaminaBBDD();
		
		add(milamina);
		
	}	
	
}

class LaminaBBDD extends JPanel{
	
	public LaminaBBDD(){
		
		setLayout(new BorderLayout());
		
		comboTablas=new JComboBox();
		
		areaInformacion=new JTextArea();
		
		add(areaInformacion,BorderLayout.CENTER);
		
		conectarBBDD();
		
		obtenerTablas();
		
		comboTablas.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String nombreTabla = (String) (comboTablas.getSelectedItem());
				
				mostrarInfoTabla(nombreTabla);
				
			}
		});
		
		add(comboTablas, BorderLayout.NORTH);
		
		}
	
	
	public void conectarBBDD() {
		
		con = null;
		
		String datos [] = new String [3];
		
		try {
			
			entrada= new FileReader("C:/Users/imelendezb/eclipse-workspaceEE/datos_configgg.txt");
			
		} catch (IOException e) {
			
			JFileChooser chooser = new JFileChooser();
			
			FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos de texto", "txt");
			
			chooser.setFileFilter(filter);
			
			int returnVal = chooser.showOpenDialog(this);
			
			if(returnVal == JFileChooser.APPROVE_OPTION) {
				
				try {
					entrada= new FileReader(chooser.getSelectedFile().getAbsoluteFile());
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
			}
			
		}
			
		
				try {
					
			BufferedReader mibuffer = new BufferedReader(entrada);
			
			for (int i = 0; i <= 2; i++) {
				
					datos[i] = mibuffer.readLine();
			}
			
			/*String db = "curso_sql"; 
			int port = 3306;
			String host = "localhost";
			String url  = String.format("jdbc:mysql://%s:%d/%s?useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=Europe/Moscow", host, port, db);
			String user = "root";
			String password = "0110";*/
			
			con = DriverManager.getConnection(datos[0], datos[1], datos[2]);	
			
			entrada.close();
			
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
		 
	}
	
	
	public void obtenerTablas() {
		
		ResultSet rs = null;
		
		try {
			
			DatabaseMetaData datosBBDD = con.getMetaData();
			
			rs = datosBBDD.getTables("curso_sql", null, null, null);
			
			while (rs.next()) {
				
				comboTablas.addItem(rs.getString("TABLE_NAME"));
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	public void mostrarInfoTabla(String tabla) {
		
		ArrayList<String> campos = new ArrayList<String>();
		
		String consulta = "SELECT * FROM " + tabla;
		
		try {
			
			areaInformacion.setText(" ");
			
			Statement st = con.createStatement();
			
			ResultSet rs = st.executeQuery(consulta);
			
			ResultSetMetaData rsBBDD = rs.getMetaData();
			
			for (int i = 1; i < rsBBDD.getColumnCount(); i++) {
				
				campos.add(rsBBDD.getColumnLabel(i));
				
			}
			
			while (rs.next()) {
				
				for (String nombreCampo : campos) {
					
					areaInformacion.append(rs.getString(nombreCampo) + " ");
				}
				
				areaInformacion.append("\n");
				
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
	}

	
	private JComboBox comboTablas;

	private JTextArea areaInformacion;
	
	private Connection con;
	
	private FileReader entrada;
	
	
	
	
}