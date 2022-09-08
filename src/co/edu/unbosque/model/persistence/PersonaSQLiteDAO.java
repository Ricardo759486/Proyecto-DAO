package co.edu.unbosque.model.persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.edu.unbosque.model.Persona;

public class PersonaSQLiteDAO extends ConexionSqlite {

	public boolean agregarPersona(Persona per) {
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

	public boolean editarPersona(Persona per) {
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

	public boolean eliminarPersona(int id) {

		try {
			PreparedStatement pstm = conn.prepareStatement(String.format("DELETE FROM Persona WHERE Id = %d", id));
			pstm.execute();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public List<Persona> listaPersonas() {

		try {
			PreparedStatement pstm = conn.prepareStatement("SELECT * FROM Persona");
			ResultSet respuesta = pstm.executeQuery();

			List<Persona> miLista = new ArrayList<Persona>();

			while (respuesta.next()) {
				Persona per = new Persona(respuesta.getInt("Id"), respuesta.getString("Identificacion"),
						respuesta.getString("Nombre"), respuesta.getString("Apellido"),
						respuesta.getString("sexo"), respuesta.getString("Telefono"), respuesta.getString("Direccion"));

				miLista.add(per);
			}
			return miLista;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	public Persona buscarPersona(int id, List<Persona> alPersona) {

		Persona encontrado = null;

		if (!alPersona.isEmpty()) {

			for (int i = 0; i < alPersona.size(); i++) {

				if (alPersona.get(i).getId() == id) {

					encontrado = alPersona.get(i);
				}
			}
		}
		return encontrado;
	}

}
