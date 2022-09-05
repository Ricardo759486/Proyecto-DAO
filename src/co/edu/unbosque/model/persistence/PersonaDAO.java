package co.edu.unbosque.model.persistence;

import java.util.List;
import co.edu.unbosque.model.Persona;

public interface PersonaDAO {
	
	public boolean agregarPersona(Persona per) throws Exception;
	public boolean editarPersona(Persona per) throws Exception;
	public boolean eliminarPersona(Persona per) throws Exception;
	public List<Persona> listaPersonas() throws Exception;

}
