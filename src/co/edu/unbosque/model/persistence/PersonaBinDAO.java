package co.edu.unbosque.model.persistence;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import co.edu.unbosque.model.Persona;

public class PersonaBinDAO extends ConexionBin implements PersonaDAO {

	private ConexionBin archivo;

	public PersonaBinDAO(ConexionBin archivo) {

		this.archivo = archivo;
		verificarInvariante();
	}

	public Persona buscarPersona(String pIdentificacion, ArrayList<Persona> alPersona) {

		Persona encontrado = null;

		if (!alPersona.isEmpty()) {

			for (int i = 0; i < alPersona.size(); i++) {

				if (alPersona.get(i).getIdentificacion().equals(pIdentificacion)) {

					encontrado = alPersona.get(i);
				}
			}
		}
		return encontrado;
	}

	public boolean agregarPersona2(Persona persona, ArrayList<Persona> alPersona, File file) {

		if (buscarPersona(persona.getIdentificacion(), alPersona) == null) {

			alPersona.add(persona);
			this.writePersona(alPersona, file);
			return true;

		} else {

			return false;
		}

	}

	public boolean eliminarPersona2(String pIdentificacion, ArrayList<Persona> alPersona, File file) {

		boolean verificar = false;

		try {
			Persona eliminar = buscarPersona(pIdentificacion, alPersona);

			if (eliminar != null) {

				alPersona.remove(eliminar);
				file.delete();
				file.createNewFile();
				this.writePersona(alPersona, file);
				verificar = true;
			} else {
				verificar = false;
			}
			return verificar;

		} catch (Exception e) {
		}
		return verificar;
	}

	public boolean editarPersona2(Persona persona, ArrayList<Persona> alPersona, File file) {

		boolean encontrado = false;
		String identificacion = persona.getIdentificacion();

		for (int i = 0; i < alPersona.size(); i++) {

			if (alPersona.get(i).getIdentificacion().equals(identificacion)) {

				alPersona.get(i).setNombre(persona.getNombre());
				alPersona.get(i).setApellido(persona.getApellido());
				alPersona.get(i).setSexo(persona.getSexo());
				alPersona.get(i).setTelefono(persona.getTelefono());
				alPersona.get(i).setDireccion(persona.getDireccion());
				file.delete();
				try {
					file.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
				writePersona(alPersona, file);
				encontrado = true;
				break;
			} else {
				encontrado = false;
			}

		}
		return encontrado;
	}

	public String verApostadorTotal(ArrayList<Persona> alPersona) {

		String texto = "";

		for (int i = 0; i < alPersona.size(); i++) {

			texto = (alPersona.get(i).getIdentificacion() + "\n" + alPersona.get(i).getNombre() + "\n"
					+ alPersona.get(i).getApellido() + "\n" + alPersona.get(i).getSexo() + "\n"
					+ alPersona.get(i).getTelefono() + "\n" + alPersona.get(i).getDireccion() + "\n"
					+ "------------------------------" + "\n");
		}

		return texto;
	}

	@Override
	public void agregarPersona(Persona per) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void editarPersona(Persona per) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void eliminarPersona(Persona per) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Persona> listaPersonas() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	private void verificarInvariante() {
		assert archivo != null : "El archivo con el que se va ha trabajar no puede ser null";
	}

}
