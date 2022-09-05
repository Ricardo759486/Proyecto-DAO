package co.edu.unbosque.model.persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import co.edu.unbosque.model.Persona;

public class PersonaSQLiteDAO extends ConexionSqlite implements PersonaDAO {

	public void probarConexion() {
		ConexionSqlite conexion = new ConexionSqlite();
	}

	@Override
	public boolean agregarPersona(Persona per) throws Exception {
		try {
			PreparedStatement pstm = conn.prepareStatement(
					"INSERT INTO Persona(Id, Nombre, Apellido, Identificacion, Sexo, Telefono, Direccion) VALUES (null,'"
							+ per.getNombre() + "','" + per.getApellido() + "','" + per.getIdentificacion() + "','"
							+ per.getSexo() + "','" + per.getTelefono() + "','" + per.getDireccion() + "')");
			pstm.execute();
			return true;
		} catch (SQLException e) {
			System.out.println(e);
			return false;
		}
	}
	
	public ResultSet consultarRegistros() {
		try {
			PreparedStatement pstm = conn.prepareStatement("");
			ResultSet respuesta = pstm.executeQuery();
			return respuesta;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
	
	
	@Override
	public boolean editarPersona(Persona per) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean eliminarPersona(Persona per) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Persona> listaPersonas() throws Exception {
		
		
		return null;
	}

}
