package co.edu.unbosque.model.persistence;

import java.util.ArrayList;
import java.util.List;

import co.edu.unbosque.model.Persona;

public class PersonaArrayDAO implements PersonaDAO {

	public PersonaArrayDAO() {
	}

	public Persona buscarPersona(int id, ArrayList<Persona> alPersona) {

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

	public boolean agregarPersona2(Persona persona, ArrayList<Persona> alPersona) {

		Persona buscar = buscarPersona(persona.getId(), alPersona);

		if (buscar == null) {
			alPersona.add(persona);
			return true;
		} else {
			return false;
		}
	}

	public boolean eliminarPersona2(int id, ArrayList<Persona> alPersona) {

		boolean verificar = false;

		try {
			Persona eliminar = buscarPersona(id, alPersona);

			if (eliminar != null) {
				alPersona.remove(eliminar);
				verificar = true;
			} else {
				verificar = false;
			}
			return verificar;

		} catch (Exception e) {
		}
		return verificar;
	}

	public boolean editarPersona2(Persona persona, ArrayList<Persona> alPersona) {

		boolean encontrado = false;
		int id = persona.getId();

		for (int i = 0; i < alPersona.size(); i++) {
			if (alPersona.get(i).getId() == id) {
				alPersona.get(i).setIdentificacion(persona.getIdentificacion());
				alPersona.get(i).setNombre(persona.getNombre());
				alPersona.get(i).setApellido(persona.getApellido());
				alPersona.get(i).setSexo(persona.getSexo());
				alPersona.get(i).setTelefono(persona.getTelefono());
				alPersona.get(i).setDireccion(persona.getDireccion());
				;
				encontrado = true;
				break;
			} else {
				encontrado = false;
			}

		}
		return encontrado;
	}

	public String verTotalPersona(ArrayList<Persona> alPersona) {

		String texto = "";

		for (int i = 0; i < alPersona.size(); i++) {

			texto = ("\n" + "------------------------------" + "\n" + alPersona.get(i).getId() + "\n"
					+ alPersona.get(i).getIdentificacion() + "\n" + alPersona.get(i).getNombre() + "\n"
					+ alPersona.get(i).getApellido() + "\n" + alPersona.get(i).getSexo() + "\n"
					+ alPersona.get(i).getTelefono() + "\n" + alPersona.get(i).getDireccion() + "\n"
					+ "------------------------------" + "\n");
		}

		return texto;
	}

	@Override
	public boolean agregarPersona(Persona per) throws Exception {
		// TODO Auto-generated method stub
		return false;
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
		// TODO Auto-generated method stub
		return null;
	}

}
