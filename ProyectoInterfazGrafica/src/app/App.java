package app;

import javax.swing.SwingUtilities;

import app.client.vistaPrincipal.VistaPrincipalComponent;

public class App {
  public static void main(String[] args) {
    Runnable runApplication = new Runnable() {
      public void run() {
        VistaPrincipalComponent logn = new VistaPrincipalComponent();
        
        logn.getClass();
      }
    };
    SwingUtilities.invokeLater(runApplication);
  }
}