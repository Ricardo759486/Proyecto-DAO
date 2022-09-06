package co.edu.unbosque.model.persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.edu.unbosque.model.Persona;

public class PersonaSQLiteDAO extends ConexionSqlite implements PersonaDAO {

	@Override
	public boolean agregarPersona(Persona per) throws Exception {
		try {

			PreparedStatement pstm = conn.prepareStatement(String.format(
					"INSERT INTO Persona(Id, Nombre, Apellido, Identificacion, Sexo, Telefono, Direccion) VALUES (null, '%s', '%s','%s','%s','%s','%s')",
					per.getNombre(), per.getApellido(), per.getIdentificacion(), per.getSexo(), per.getTelefono(),
					per.getDireccion()));
			
			pstm.execute();
			return true;
		} catch (SQLException e) {
			System.out.println(e);
			return false;
		}
	}

	@Override
	public boolean editarPersona(Persona per) throws Exception {
		try {
			PreparedStatement pstm = conn.prepareStatement(String.format(
					"UPDATE Persona SET Nombre='%s', Apellido='%s', Identificacion='%s',Sexo='%s', Telefono='%s',Direccion='%s', WHERE ID='%d'",
					per.getNombre(), per.getApellido(), per.getIdentificacion(), per.getSexo(), per.getTelefono(),
					per.getDireccion(), per.getId()));
			pstm.execute();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean eliminarPersona(Persona per) throws Exception {

		try {
			PreparedStatement pstm = conn
					.prepareStatement(String.format("DELETE FROM Persona WHERE Id = %d", per.getNombre()));
			pstm.execute();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public List<Persona> listaPersonas() throws Exception {

		try {
			PreparedStatement pstm = conn.prepareStatement("SELECT * FROM Persona");
			ResultSet respuesta = pstm.executeQuery();

			List<Persona> miLista = new ArrayList<Persona>();

			while (respuesta.next()) {
				Persona per = new Persona(respuesta.getInt("Id"), respuesta.getString("Nombre"),
						respuesta.getString("Apellido"), respuesta.getString("Identificacion"),
						respuesta.getString("sexo"), respuesta.getString("Telefono"), respuesta.getString("Direccion"));

				miLista.add(per);
			}
			return miLista;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

}
