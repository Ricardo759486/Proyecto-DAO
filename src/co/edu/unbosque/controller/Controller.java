package co.edu.unbosque.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import co.edu.unbosque.model.Persona;
import co.edu.unbosque.model.persistence.ConexionBin;
import co.edu.unbosque.model.persistence.PersonaArrayDAO;
import co.edu.unbosque.model.persistence.PersonaBinDAO;
import co.edu.unbosque.model.persistence.PersonaSQLiteDAO;

public class Controller implements ActionListener {

	private ArrayList<Persona> alPersonaBin;
	private ArrayList<Persona> alPersonaArray;
	private Persona persona;
	private File filePersonaBin;
	private ConexionBin conexionbin;
	private PersonaBinDAO personaBinDAO;
	private PersonaArrayDAO personaArrayDAO;
	private PersonaSQLiteDAO personaSQLiteDAO;

	public Controller() throws Exception {
		filePersonaBin = new File("datos\\Personas.dat");
		conexionbin = new ConexionBin(filePersonaBin);
		personaBinDAO = new PersonaBinDAO(conexionbin);
		personaArrayDAO = new PersonaArrayDAO();
		personaSQLiteDAO = new PersonaSQLiteDAO();
		alPersonaBin = conexionbin.readPersona(filePersonaBin);
		alPersonaArray = new ArrayList<Persona>();
		asignarOyentes();
	}

	public void asignarOyentes() {

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();
	}
	
	public void conexionSqlite(int id, String identificacion, String nombre, String apellido, String sexo, String telefono,
			String direccion) throws Exception {
		
		persona = new Persona(id, identificacion, nombre, apellido, sexo, telefono, direccion);
		
		if(personaSQLiteDAO.agregarPersona(persona)) {
			System.out.println(
					"\n----------------------------------------\n" + "Registrado Correctamente el SQLite"
							+ "\n----------------------------------------\n");
		}
	}
	
	public void probarArray(int id, String identificacion, String nombre, String apellido, String sexo, String telefono,
			String direccion) {
		
		int desicion = 0;
		
		do {
			
			Scanner d = new Scanner(System.in);
			desicion = Integer.parseInt(d.nextLine()); 
		
			switch(desicion) {
			
			case 1:
				persona = new Persona(id, identificacion, nombre, apellido, sexo, telefono, direccion);
				if(personaArrayDAO.agregarPersona2(persona, alPersonaArray)) {
					System.out.println(
							"\n----------------------------------------\n" + "Registrado Correctamente el ArrayList"
									+ "\n----------------------------------------\n");
				}
				break;
				
			case 2: 
				System.out.println(personaArrayDAO.verTotalPersona(alPersonaArray));
				break;
			
			case 3:
				persona = new Persona(id, identificacion, "ANDRES", apellido, sexo, telefono, direccion);
				if(personaArrayDAO.editarPersona2(persona, alPersonaArray)) {
					System.out.println(
							"\n----------------------------------------\n" + "editado Correctamente el ArrayList"
									+ "\n----------------------------------------\n");
				}
				break;
				
			case 4:
				if(personaArrayDAO.eliminarPersona2(id, alPersonaArray)) {
					System.out.println(
							"\n----------------------------------------\n" + "eliminado Correctamente el ArrayList"
									+ "\n----------------------------------------\n");
				}
				break;
			}
		}while(desicion <= 4);
		

	}

}
