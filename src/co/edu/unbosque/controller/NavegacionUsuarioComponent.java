package co.edu.unbosque.controller;

import app.client.vistaPrincipal.VistaPrincipalComponent;

import app.services.graphicServices.RecursosService;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

public class NavegacionUsuarioComponent extends MouseAdapter implements ActionListener {
  private NavegacionUsuarioTemplate navegacionUsuarioTemplate;
  private VistaPrincipalComponent vistaPrincipalComponent;

  public NavegacionUsuarioComponent(VistaPrincipalComponent vistaPrincipalComponent) {
    this.vistaPrincipalComponent = vistaPrincipalComponent;
    this.navegacionUsuarioTemplate = new NavegacionUsuarioTemplate(this);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    this.vistaPrincipalComponent.mostrarComponente(e.getActionCommand().trim());
  }

  @Override
  public void mouseEntered(MouseEvent e) {
    JButton boton = ((JButton) e.getSource());
    boton.setContentAreaFilled(true);
    boton.setBackground(RecursosService.getService().getColorPrincipalOscuro());
  }

  @Override
  public void mouseExited(MouseEvent e) {
    JButton boton = ((JButton) e.getSource());
    boton.setContentAreaFilled(false);
  }

  public void actualizarValores() {
    this.navegacionUsuarioTemplate.getPSuperior().removeAll();
    this.navegacionUsuarioTemplate.crearJLabels();
    this.navegacionUsuarioTemplate.repaint();
  }

  public NavegacionUsuarioTemplate getNavegacionUsuarioTemplate() {
    return this.navegacionUsuarioTemplate;
  }
}