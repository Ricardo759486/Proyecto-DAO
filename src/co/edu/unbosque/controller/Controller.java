package co.edu.unbosque.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import co.edu.unbosque.model.Persona;
import co.edu.unbosque.model.persistence.ConexionBin;
import co.edu.unbosque.model.persistence.PersonaBinDAO;

public class Controller implements ActionListener {

	private ArrayList<Persona> alPersona;
	private Persona persona;
	private File filePersonaBin;
	private ConexionBin conexionbin;
	private PersonaBinDAO personaBinDAO;

	public Controller() throws Exception {
		filePersonaBin = new File("datos\\Personas.dat");
		conexionbin = new ConexionBin(filePersonaBin);
		personaBinDAO = new PersonaBinDAO(conexionbin);
		alPersona = conexionbin.readPersona(filePersonaBin);
		asignarOyentes();
	}

	public void asignarOyentes() {

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();
	}

	public void pruebaAgregarBin(String identificacion, String nombre, String apellido, char sexo, String telefono,
			String direccion) {

		persona = new Persona(identificacion, nombre, apellido, sexo, telefono, direccion);
		try {
			if (personaBinDAO.agregarPersona2(persona, alPersona, filePersonaBin)) {
				System.out.println(
						"\n----------------------------------------\n" + "Registrado Correctamente el archivo binario"
								+ "\n----------------------------------------\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
