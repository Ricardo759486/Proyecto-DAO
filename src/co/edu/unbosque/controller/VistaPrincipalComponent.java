package co.edu.unbosque.controller;

import java.awt.Frame;

import co.edu.unbosque.view.VistaPrincipalTemplate;

public class VistaPrincipalComponent {
  private VistaPrincipalTemplate vistaPrincipalTemplate;

  //Declaración Componentes
  private BarraTituloComponent barraTituloComponent;
  private NavegacionUsuarioComponent navegacionUsuarioComponent;
  private AmigosComponent amigosComponent;
  private PersonaComponentArray personaArrayComponent;
  private PersonaComponentSQLite personaSQLiteComponent;

  public VistaPrincipalComponent() {
    this.vistaPrincipalTemplate = new VistaPrincipalTemplate(this);
    this.barraTituloComponent = new BarraTituloComponent(this);
    this.navegacionUsuarioComponent = new NavegacionUsuarioComponent(this);
    this.amigosComponent = new AmigosComponent();
    this.personaArrayComponent = new PersonaComponentArray();

    vistaPrincipalTemplate.getPNavegacion()
      .add(navegacionUsuarioComponent.getNavegacionUsuarioTemplate());
    vistaPrincipalTemplate.getPBarra()
      .add(barraTituloComponent.getBarraTituloTemplate());
  }

  public VistaPrincipalTemplate getVistaPrincipalTemplate() {
    return this.vistaPrincipalTemplate;
  }

  public void mostrarComponente(String comando) {
    vistaPrincipalTemplate.getPPrincipal().removeAll();
    switch (comando) {
      case "Binario":
        if (this.amigosComponent == null) 
          this.amigosComponent = new AmigosComponent();
        vistaPrincipalTemplate.getPPrincipal()
          .add(amigosComponent.getAmigosTemplate());
        break;
      case "ArrayList":
          if (this.personaArrayComponent == null) 
            this.personaArrayComponent = new PersonaComponentArray();
          vistaPrincipalTemplate.getPPrincipal()
            .add(personaArrayComponent.getAmigosTemplate());
          break;
      case "SQLite":
          if (this.personaSQLiteComponent == null) 
            this.personaSQLiteComponent = new PersonaComponentSQLite();
          vistaPrincipalTemplate.getPPrincipal()
            .add(personaSQLiteComponent.getAmigosTemplate());
          break;
    }
    vistaPrincipalTemplate.repaint();
  }
  
  public void moverVentana(int posicionX, int posicionY) {
    this.vistaPrincipalTemplate.setLocation(posicionX, posicionY);
  }
  
  public void cerrar() {
    System.exit(0);
  }

  public void minimizar() {
    this.vistaPrincipalTemplate.setExtendedState(Frame.ICONIFIED);
  }
}