package app.client.components.amigos;

import app.services.graphicServices.RecursosService;
import app.services.logicServices.AmigoService;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import models.Amigo;
import models.Persona;

public class PersonaComponentSQLite extends MouseAdapter implements ActionListener, FocusListener {
  private PersonaSQLiteTemplate amigosTemplate;
  private AmigoService sAmigos;
  private String[] placeholders = {
    "Nombre", "Apellido", "Identificación", "Sexo", "Telefono", "Direccion", "Filtrar...",
  };
  private Persona amigo;

  public PersonaComponentSQLite() {
    sAmigos = AmigoService.getService();
    amigosTemplate = new PersonaSQLiteTemplate(this);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == amigosTemplate.getBMostrar()) mostrarRegistrosTabla();
    if (e.getSource() == amigosTemplate.getBInsertar()) insertarRegistroTabla();
    if (e.getSource() == amigosTemplate.getBModificar()) modificarRegistroTabla();
    if (e.getSource() == amigosTemplate.getBEliminar()) eliminarRegistroTabla();
    if (e.getSource() == amigosTemplate.getBFiltrar()) filtrarRegistrosTabla();
  }

  public PersonaSQLiteTemplate getAmigosTemplate() {
    return amigosTemplate;
  }

  @Override
  public void focusGained(FocusEvent e) {
    JTextField textField = ((JTextField) e.getSource());
    textField.setBorder(RecursosService.getService().getBAzul());
    if (
      textField.getText().equals(placeholders[0]) ||
      textField.getText().equals(placeholders[1]) ||
      textField.getText().equals(placeholders[2]) ||
      textField.getText().equals(placeholders[3]) ||
      textField.getText().equals(placeholders[4]) ||
      textField.getText().equals(placeholders[5]) ||
      textField.getText().equals(placeholders[6])
    ) textField.setText("");
  }

  @Override
  public void focusLost(FocusEvent e) {
    JTextField textField = ((JTextField) e.getSource());
    textField.setBorder(null);
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    if (e.getSource() instanceof JTable) {
      int fila = amigosTemplate.getTabla().getSelectedRow();
      amigo = sAmigos.devolverAmigo(fila);
      amigosTemplate.getLIdValor().setText(amigo.getId() + "");
      amigosTemplate.getTNombre().setText(amigo.getNombre());
      amigosTemplate.getTApellido().setText(amigo.getApellido());
      amigosTemplate.getTIdentificacion().setText(amigo.getIdentificacion());
      amigosTemplate.getTSexo().setText(amigo.getSexo());
      amigosTemplate.getTTelefono().setText(amigo.getTelefono());
      amigosTemplate.getTDireccion().setText(amigo.getDireccion());
    }
  }

  @Override
  public void mouseEntered(MouseEvent e) {
    if (e.getSource() instanceof JButton) {
      JButton boton = ((JButton) e.getSource());
      boton.setBackground(RecursosService.getService().getColorPrincipalOscuro());
    }
  }

  @Override
  public void mouseExited(MouseEvent e) {
    if (e.getSource() instanceof JButton) {
      JButton boton = ((JButton) e.getSource());
      boton.setBackground(RecursosService.getService().getColorPrincipal());
    }
  }

  public void restaurarValores() {
    amigosTemplate.getLIdValor().setText(sAmigos.devolverCantidadAmigos() + "");
    amigosTemplate.getTNombre().setText(placeholders[0]);
    amigosTemplate.getTApellido().setText(placeholders[1]);
    amigosTemplate.getTIdentificacion().setText(placeholders[2]);
    amigosTemplate.getTSexo().setText(placeholders[3]);
    amigosTemplate.getTTelefono().setText(placeholders[4]);
    amigosTemplate.getTDireccion().setText(placeholders[5]);
    amigosTemplate.getTabla().setSelectionMode(0);
  }

  public void mostrarRegistrosTabla() {
    for (int i = 0; i < sAmigos.devolverCantidadAmigos(); i++) {
      amigo = sAmigos.devolverAmigo(i);
      this.agregarRegistro(amigo);
    }
    amigosTemplate.getLIdValor().setText(sAmigos.devolverCantidadAmigos() + "");
    amigosTemplate.getBMostrar().setEnabled(false);
  }

  public void insertarRegistroTabla() {
    amigo = new Persona();
    amigo.setId(sAmigos.devolverCantidadAmigos());
    amigo.setNombre(amigosTemplate.getTNombre().getText());
    amigo.setApellido(amigosTemplate.getTApellido().getText());
    amigo.setIdentificacion(amigosTemplate.getTIdentificacion().getText());
    amigo.setSexo(amigosTemplate.getTSexo().getText());
    amigo.setTelefono(amigosTemplate.getTTelefono().getText());
    amigo.setDireccion(amigosTemplate.getTDireccion().getText());
    this.agregarRegistro(amigo);
    sAmigos.agregarAmigo(amigo);
    restaurarValores();
  }

  public void modificarRegistroTabla() {
    int fSeleccionada = amigosTemplate.getTabla().getSelectedRow();
    if (fSeleccionada != -1) {
      amigosTemplate.getModelo()
        .setValueAt(amigosTemplate.getTNombre().getText(), fSeleccionada, 1);
      amigosTemplate.getModelo()
      .setValueAt(amigosTemplate.getTApellido().getText(), fSeleccionada, 2);
      amigosTemplate.getModelo()
        .setValueAt(amigosTemplate.getTIdentificacion().getText(), fSeleccionada, 3);
      amigosTemplate.getModelo()
        .setValueAt(amigosTemplate.getTSexo().getText(), fSeleccionada, 4);
      amigosTemplate.getModelo()
      .setValueAt(amigosTemplate.getTTelefono().getText(), fSeleccionada, 5);
      amigosTemplate.getModelo()
      .setValueAt(amigosTemplate.getTDireccion().getText(), fSeleccionada, 6);
      
      amigo = sAmigos.devolverAmigo(fSeleccionada);
      amigo.setNombre(amigosTemplate.getTNombre().getText());
      amigo.setApellido(amigosTemplate.getTApellido().getText());
      amigo.setIdentificacion(amigosTemplate.getTIdentificacion().getText());
      amigo.setSexo(amigosTemplate.getTSexo().getText());
      amigo.setTelefono(amigosTemplate.getTTelefono().getText());
      amigo.setDireccion(amigosTemplate.getTDireccion().getText());
      restaurarValores();
    } else JOptionPane.showMessageDialog(
      null,
      "seleccione una fila",
      "Error",
      JOptionPane.ERROR_MESSAGE
    );
  }

  public void eliminarRegistroTabla() {
    int fSeleccionada = amigosTemplate.getTabla().getSelectedRow();
    if (fSeleccionada != -1) 
      amigosTemplate.getModelo().removeRow(fSeleccionada); 
    else 
      JOptionPane.showMessageDialog(
        null,
        "seleccione una fila",
        "Error",
        JOptionPane.ERROR_MESSAGE
      );
  }

  public void filtrarRegistrosTabla() {
    TableRowSorter<DefaultTableModel> trs = new TableRowSorter<>(
      amigosTemplate.getModelo()
    );
    amigosTemplate.getTabla().setRowSorter(trs);
    trs.setRowFilter(
      RowFilter.regexFilter(amigosTemplate.getTConsulta().getText())
    );
  }

  public void agregarRegistro(Persona amigo) {
    amigosTemplate
      .getModelo()
      .addRow(
        new Object[] {
          amigo.getId(),
          amigo.getNombre(),
          amigo.getApellido(),
          amigo.getIdentificacion(),
          amigo.getSexo(),
          amigo.getTelefono(),
          amigo.getDireccion(),
        }
      );
  }
}