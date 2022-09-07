package logic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import models.Amigo;
import models.Persona;

public class ControlAmigos {
  private ArrayList<Persona> amigos;

  public ControlAmigos() {
    amigos = new ArrayList<Persona>();
    cargarDatos();
  }

  public void cargarDatos() {
    File archivo = null;
    FileReader fr = null;
    BufferedReader br = null;
    try {
      archivo = new File("ProyectoInterfazGrafica/src/archives/Persona.txt");
      fr = new FileReader(archivo);
      br = new BufferedReader(fr);
      String linea;
      while ((linea = br.readLine()) != null) {
        String[] atributos = linea.split(",");
        Persona amigo = new Persona();
        amigo.setId(Integer.parseInt(atributos[0]));
        amigo.setNombre(atributos[1]);
        amigo.setApellido(atributos[2]);
        amigo.setIdentificacion(atributos[3]);
        amigo.setSexo(atributos[4]);
        amigo.setTelefono(atributos[5]);
        amigo.setDireccion(atributos[6]);
        amigos.add(amigo);
      }
      fr.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public ArrayList<Persona> getAmigos() { return amigos; }
}