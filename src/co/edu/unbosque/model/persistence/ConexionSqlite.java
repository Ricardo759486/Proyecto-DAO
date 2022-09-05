package co.edu.unbosque.model.persistence;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConexionSqlite {
	
	String strConexionDB = "jdbc:sqlite:C:/Users/RICARDO/Desktop/project/sistema.s3db";
	Connection conn = null;
	
	public ConexionSqlite() {
		try {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection(strConexionDB);
			
			System.out.println("Conexion establecida");
			
		}catch (Exception e){
			System.out.println("Error de conexion" + e);
		}
	}

}
