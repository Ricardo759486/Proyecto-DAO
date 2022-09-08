package co.edu.unbosque.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import co.edu.unbosque.model.Persona;
import co.edu.unbosque.model.persistence.PersonaArrayDAO;
import co.edu.unbosque.view.PersonaArrayTemplate;
import co.edu.unbosque.view.RecursosService;

public class PersonaComponentArray extends MouseAdapter implements ActionListener, FocusListener {
	private PersonaArrayTemplate personaArrayTemplate;
	private ArrayList<Persona> alPersona;
	private PersonaArrayDAO personaArrayDAO;
	// private AmigoService sAmigos;
	private String[] placeholders = { "Nombre", "Apellido", "Identificaci�n", "Sexo", "Telefono", "Direccion",
			"Filtrar...", };
	private Persona persona;

	public PersonaComponentArray() {
		// sAmigos = AmigoService.getService();
		personaArrayTemplate = new PersonaArrayTemplate(this);
		personaArrayDAO = new PersonaArrayDAO();
		alPersona = new ArrayList();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// if (e.getSource() == amigosTemplate.getBMostrar()) mostrarRegistrosTabla();
		if (e.getSource() == personaArrayTemplate.getBInsertar())
			insertarRegistroTabla();
		if (e.getSource() == personaArrayTemplate.getBModificar())
			modificarRegistroTabla();
		if (e.getSource() == personaArrayTemplate.getBEliminar())
			eliminarRegistroTabla();
		if (e.getSource() == personaArrayTemplate.getBFiltrar())
			filtrarRegistrosTabla();
	}

	public PersonaArrayTemplate getAmigosTemplate() {
		return personaArrayTemplate;
	}

	@Override
	public void focusGained(FocusEvent e) {
		JTextField textField = ((JTextField) e.getSource());
		textField.setBorder(RecursosService.getService().getBAzul());
		if (textField.getText().equals(placeholders[0]) || textField.getText().equals(placeholders[1])
				|| textField.getText().equals(placeholders[2]) || textField.getText().equals(placeholders[3])
				|| textField.getText().equals(placeholders[4]) || textField.getText().equals(placeholders[5])
				|| textField.getText().equals(placeholders[6]))
			textField.setText("");
	}

	@Override
	public void focusLost(FocusEvent e) {
		JTextField textField = ((JTextField) e.getSource());
		textField.setBorder(null);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() instanceof JTable) {
			int fila = personaArrayTemplate.getTabla().getSelectedRow();
			// amigo = sAmigos.devolverAmigo(fila);
			personaArrayTemplate.getLIdValor().setText(persona.getId() + "");
			personaArrayTemplate.getTNombre().setText(persona.getNombre());
			personaArrayTemplate.getTApellido().setText(persona.getApellido());
			personaArrayTemplate.getTIdentificacion().setText(persona.getIdentificacion());
			personaArrayTemplate.getTSexo().setText(persona.getSexo());
			personaArrayTemplate.getTTelefono().setText(persona.getTelefono());
			personaArrayTemplate.getTDireccion().setText(persona.getDireccion());
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
		// amigosTemplate.getLIdValor().setText(sAmigos.devolverCantidadAmigos() + "");
		personaArrayTemplate.getTNombre().setText(placeholders[0]);
		personaArrayTemplate.getTApellido().setText(placeholders[1]);
		personaArrayTemplate.getTIdentificacion().setText(placeholders[2]);
		personaArrayTemplate.getTSexo().setText(placeholders[3]);
		personaArrayTemplate.getTTelefono().setText(placeholders[4]);
		personaArrayTemplate.getTDireccion().setText(placeholders[5]);
		personaArrayTemplate.getTabla().setSelectionMode(0);
	}
	
	public void mostrarRegistrosTabla() {
		int tam = alPersona.size();
		for (int i = 0; i < tam; i++) {
			persona = personaArrayDAO.buscarPersona(i, alPersona);
			this.agregarRegistro(persona);
		}
		personaArrayTemplate.getLIdValor().setText(tam + "");
		personaArrayTemplate.getBMostrar().setEnabled(false);
	}

	/*
	 * public void mostrarRegistrosTabla() { for (int i = 0; i <
	 * sAmigos.devolverCantidadAmigos(); i++) { amigo = sAmigos.devolverAmigo(i);
	 * this.agregarRegistro(amigo); }
	 * amigosTemplate.getLIdValor().setText(sAmigos.devolverCantidadAmigos() + "");
	 * amigosTemplate.getBMostrar().setEnabled(false); }
	 */

	public void insertarRegistroTabla() {
		persona = new Persona();
		persona.setId(alPersona.size());
		persona.setNombre(personaArrayTemplate.getTNombre().getText());
		persona.setApellido(personaArrayTemplate.getTApellido().getText());
		persona.setIdentificacion(personaArrayTemplate.getTIdentificacion().getText());
		persona.setSexo(personaArrayTemplate.getTSexo().getText());
		persona.setTelefono(personaArrayTemplate.getTTelefono().getText());
		persona.setDireccion(personaArrayTemplate.getTDireccion().getText());
		this.agregarRegistro(persona);
		personaArrayDAO.agregarPersona2(persona, alPersona);
		restaurarValores();
	}

	public void modificarRegistroTabla() {
		int fSeleccionada = personaArrayTemplate.getTabla().getSelectedRow();
		if (fSeleccionada != -1) {
			personaArrayTemplate.getModelo().setValueAt(personaArrayTemplate.getTNombre().getText(), fSeleccionada, 1);
			personaArrayTemplate.getModelo().setValueAt(personaArrayTemplate.getTApellido().getText(), fSeleccionada, 2);
			personaArrayTemplate.getModelo().setValueAt(personaArrayTemplate.getTIdentificacion().getText(), fSeleccionada, 3);
			personaArrayTemplate.getModelo().setValueAt(personaArrayTemplate.getTSexo().getText(), fSeleccionada, 4);
			personaArrayTemplate.getModelo().setValueAt(personaArrayTemplate.getTTelefono().getText(), fSeleccionada, 5);
			personaArrayTemplate.getModelo().setValueAt(personaArrayTemplate.getTDireccion().getText(), fSeleccionada, 6);

			persona = personaArrayDAO.buscarPersona(fSeleccionada, alPersona);
			persona.setNombre(personaArrayTemplate.getTNombre().getText());
			persona.setApellido(personaArrayTemplate.getTApellido().getText());
			persona.setIdentificacion(personaArrayTemplate.getTIdentificacion().getText());
			persona.setSexo(personaArrayTemplate.getTSexo().getText());
			persona.setTelefono(personaArrayTemplate.getTTelefono().getText());
			persona.setDireccion(personaArrayTemplate.getTDireccion().getText());
			personaArrayDAO.editarPersona2(persona, alPersona);
			restaurarValores();
		} else
			JOptionPane.showMessageDialog(null, "seleccione una fila", "Error", JOptionPane.ERROR_MESSAGE);
	}

	public void eliminarRegistroTabla() {
		int fSeleccionada = personaArrayTemplate.getTabla().getSelectedRow();
		if (fSeleccionada != -1) {
			personaArrayDAO.eliminarPersona2(fSeleccionada, alPersona);
			personaArrayTemplate.getModelo().removeRow(fSeleccionada);
		} else {
			JOptionPane.showMessageDialog(null, "seleccione una fila", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void filtrarRegistrosTabla() {
		TableRowSorter<DefaultTableModel> trs = new TableRowSorter<>(personaArrayTemplate.getModelo());
		personaArrayTemplate.getTabla().setRowSorter(trs);
		trs.setRowFilter(RowFilter.regexFilter(personaArrayTemplate.getTConsulta().getText()));
	}

	public void agregarRegistro(Persona amigo) {
		personaArrayTemplate.getModelo().addRow(new Object[] { amigo.getId(), amigo.getNombre(), amigo.getApellido(),
				amigo.getIdentificacion(), amigo.getSexo(), amigo.getTelefono(), amigo.getDireccion(), });
	}
}