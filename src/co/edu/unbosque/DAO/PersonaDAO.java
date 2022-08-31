package co.edu.unbosque.DAO;

import java.util.List;
import co.edu.unbosque.model.Persona;

public interface PersonaDAO {
	
	public void agregarPersona(Persona per) throws Exception;
	public void editarPersona(Persona per) throws Exception;
	public void eliminarPersona(Persona per) throws Exception;
	public List<Persona> listaPersonas() throws Exception;

}
