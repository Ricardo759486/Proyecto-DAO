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

	public void conexionSqlite(int id, String identificacion, String nombre, String apellido, String sexo,
			String telefono, String direccion) throws Exception {

		persona = new Persona(id, identificacion, nombre, apellido, sexo, telefono, direccion);

		if (personaSQLiteDAO.agregarPersona(persona)) {
			System.out.println("\n----------------------------------------\n" + "Registrado Correctamente el SQLite"
					+ "\n----------------------------------------\n");
		}
	}

	public void probarArray(int id, String identificacion, String nombre, String apellido, String sexo, String telefono,
			String direccion) {

		int desicion = 0;

		do {

			System.out.println("Por favor escoja una opcion:\n1.) Agregar.\n2.) Ver lista de personas\n3.) Editar persona\n4.) Eliminar persona\n5.) Salir");

			Scanner d = new Scanner(System.in);
			desicion = Integer.parseInt(d.nextLine());


			switch (desicion) {

			case 1:
				persona = new Persona(id, identificacion, nombre, apellido, sexo, telefono, direccion);
				if (personaArrayDAO.agregarPersona2(persona, alPersonaArray)) {
					System.out.println("\n----------------------------------------\n"
							+ "Registrado Correctamente el ArrayList" + "\n----------------------------------------\n");
				}
				break;

			case 2:
				System.out.println(personaArrayDAO.verTotalPersona(alPersonaArray));
				break;

			case 3:
				persona = new Persona(id, identificacion, "ANDRES", apellido, sexo, telefono, direccion);
				if (personaArrayDAO.editarPersona2(persona, alPersonaArray)) {
					System.out.println("\n----------------------------------------\n"
							+ "editado Correctamente el archivo" + "\n----------------------------------------\n");
				}
				break;

			case 4:
				if (personaArrayDAO.eliminarPersona2(id, alPersonaArray)) {
					System.out.println("\n----------------------------------------\n"
							+ "eliminado Correctamente el archivo" + "\n----------------------------------------\n");
				}
				break;
			}
		} while (desicion <= 4);

	}

	public void probarBin(int id, String identificacion, String nombre, String apellido, String sexo, String telefono,
			String direccion) {

		int desicion = 0;

		do {

			System.out.println("Por favor escoja una opcion:\n1.) Agregar.\n2.) Ver lista de personas\n3.) Editar persona\n4.) Eliminar persona5.) Salir");

			Scanner d = new Scanner(System.in);
			desicion = Integer.parseInt(d.nextLine());


			switch (desicion) {

			case 1:
				persona = new Persona(id, identificacion, nombre, apellido, sexo, telefono, direccion);
				if (personaBinDAO.agregarPersona2(persona, alPersonaBin, filePersonaBin)) {
					System.out.println("\n----------------------------------------\n"
							+ "Registrado Correctamente el ArrayList" + "\n----------------------------------------\n");
				}
				break;

			case 2:
				System.out.println(personaBinDAO.verTotalPersona(alPersonaBin));
				break;

			case 3:
				persona = new Persona(id, identificacion, "ANDRES", apellido, sexo, telefono, direccion);
				if (personaBinDAO.editarPersona2(persona, alPersonaBin, filePersonaBin)) {
					System.out.println("\n----------------------------------------\n"
							+ "editado Correctamente el archivo" + "\n----------------------------------------\n");
				}
				break;

			case 4:
				if (personaBinDAO.eliminarPersona2(id, alPersonaBin, filePersonaBin)) {
					System.out.println("\n----------------------------------------\n"
							+ "eliminado Correctamente del archivo" + "\n----------------------------------------\n");
				}
				break;
			}
		} while (desicion <= 4);

	}

	public void probarSQL(int id, String identificacion, String nombre, String apellido, String sexo, String telefono,
			String direccion) throws Exception {

		int desicion = 0;

		do {

			System.out.println("Por favor escoja una opcion:\n1.) Agregar.\n2.) Ver lista de personas\n3.) Editar persona\n4.) Eliminar persona5.) Salir");

			Scanner d = new Scanner(System.in);
			desicion = Integer.parseInt(d.nextLine());

			switch (desicion) {


			case 1:
				persona = new Persona(id, identificacion, nombre, apellido, sexo, telefono, direccion);
				if (personaSQLiteDAO.agregarPersona(persona)) {
					System.out.println("\n----------------------------------------\n" + "Registrado Correctamente"
							+ "\n----------------------------------------\n");
				}
				break;

			case 2:
				System.out.println(personaSQLiteDAO.listaPersonas());
				break;

			case 3:
				persona = new Persona(id, identificacion, "ANDRES", apellido, sexo, telefono, direccion);
				if (personaSQLiteDAO.editarPersona(persona)) {
					System.out.println("\n----------------------------------------\n" + "editado Correctamente"
							+ "\n----------------------------------------\n");
				}
				break;

			case 4:
				if (personaSQLiteDAO.eliminarPersona(persona)) {
					System.out.println("\n----------------------------------------\n" + "eliminado Correctamente"
							+ "\n----------------------------------------\n");
				}
				break;
			}
		} while (desicion <= 4);

	}

	public void menu(int id, String identificacion, String nombre, String apellido, String sexo, String telefono,
			String direccion) throws Exception {
		int entrada = 0;
		do {
			System.out.println("Por favor escoja una opcion:\n1.) Crud de arreglo.\n2.) Crud de archivo binario\n3.) Crud de base de datos SQL\n4.) Salir");
			Scanner sc = new Scanner(System.in);
			entrada = Integer.parseInt(sc.nextLine());
			
			switch (entrada) {

			case 1:
				probarArray(id, identificacion, nombre, apellido, sexo, telefono, direccion);

				break;
			case 2:
				probarBin(id, identificacion, nombre, apellido, sexo, telefono, direccion);

				break;
			case 3:
				probarSQL(id, identificacion, nombre, apellido, sexo, telefono, direccion);

				break;

			default:
				break;
			}
			
		} while (entrada != 4);

	}

}
