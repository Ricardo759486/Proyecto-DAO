package co.edu.unbosque.model.persistence;

import java.util.ArrayList;
import java.io.*;

import co.edu.unbosque.model.Persona;

public class ConexionBin {
	
	private ObjectInputStream input;
	private ObjectOutputStream output;
	
	public ConexionBin() {
	}
	
	public ConexionBin(File archivo) {

		if (archivo.exists()) {

		} else {
			try {
				archivo.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void writePersona(ArrayList<Persona> alPersonas, File archivo) {

		try {
			output = new ObjectOutputStream(new FileOutputStream(archivo));
			output.writeObject(alPersonas);
			output.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Persona> readPersona(File archivo) {

		ArrayList<Persona> alPersona = new ArrayList<Persona>();

		if (archivo.length() != 0){
			try {
				input = new ObjectInputStream(new FileInputStream(archivo));
				alPersona = (ArrayList<Persona>) input.readObject();

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}

		return alPersona;
	}

}
