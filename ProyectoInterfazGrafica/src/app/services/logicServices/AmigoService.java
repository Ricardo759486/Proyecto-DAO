package app.services.logicServices;

import java.util.ArrayList;

import logic.ControlAmigos;

import models.Amigo;
import models.Persona;

public class AmigoService {
  private static AmigoService servicio;
  private ControlAmigos cAmigos;
  private ArrayList<Persona> amigos;

  public AmigoService() {
    cAmigos = new ControlAmigos();
    amigos = cAmigos.getAmigos();
  }

  public Persona devolverAmigo(int posicion) {
    try {
      return amigos.get(posicion);
    } catch (Exception e) {
      return null;
    }
  }

  public void agregarAmigo(Persona amigo) {
    this.amigos.add(amigo);
  }

  public int devolverCantidadAmigos() {
    return amigos.size();
  }

  public static AmigoService getService() {
    if (servicio == null) servicio = new AmigoService();
    return servicio;
  }
}
