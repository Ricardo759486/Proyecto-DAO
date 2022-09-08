package co.edu.unbosque.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import co.edu.unbosque.model.Persona;
import co.edu.unbosque.model.persistence.ConexionBin;
import co.edu.unbosque.model.persistence.PersonaBinDAO;
import co.edu.unbosque.view.AmigosTemplate;
import co.edu.unbosque.view.RecursosService;

public class AmigosComponent extends MouseAdapter implements ActionListener, FocusListener {
	private AmigosTemplate amigosTemplate;
	private ArrayList<Persona> alPersonaBin;
	private File filePersonaBin;
	private ConexionBin conexionbin;
	private PersonaBinDAO personaBinDAO;
	private String[] placeholders = { "Nombre", "Apellido", "Identificación", "Sexo", "Telefono", "Direccion",
			"Filtrar...", };
	private Persona persona;

	public AmigosComponent() {
		filePersonaBin = new File("datos\\Personas.dat");
		conexionbin = new ConexionBin(filePersonaBin);
		personaBinDAO = new PersonaBinDAO(conexionbin);
		alPersonaBin = conexionbin.readPersona(filePersonaBin);
		amigosTemplate = new AmigosTemplate(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == amigosTemplate.getBMostrar())
			mostrarRegistrosTabla();
		if (e.getSource() == amigosTemplate.getBInsertar())
			insertarRegistroTabla();
		if (e.getSource() == amigosTemplate.getBModificar())
			modificarRegistroTabla();
		if (e.getSource() == amigosTemplate.getBEliminar())
			eliminarRegistroTabla();
		if (e.getSource() == amigosTemplate.getBFiltrar())
			filtrarRegistrosTabla();
	}

	public AmigosTemplate getAmigosTemplate() {
		return amigosTemplate;
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
			int fila = amigosTemplate.getTabla().getSelectedRow();
			persona = personaBinDAO.buscarPersona(fila, alPersonaBin);
			amigosTemplate.getLIdValor().setText(persona.getId() + "");
			amigosTemplate.getTNombre().setText(persona.getNombre());
			amigosTemplate.getTApellido().setText(persona.getApellido());
			amigosTemplate.getTIdentificacion().setText(persona.getIdentificacion());
			amigosTemplate.getTSexo().setText(persona.getSexo());
			amigosTemplate.getTTelefono().setText(persona.getTelefono());
			amigosTemplate.getTDireccion().setText(persona.getDireccion());
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
		amigosTemplate.getLIdValor().setText(alPersonaBin.size() + "");
		amigosTemplate.getTNombre().setText(placeholders[0]);
		amigosTemplate.getTApellido().setText(placeholders[1]);
		amigosTemplate.getTIdentificacion().setText(placeholders[2]);
		amigosTemplate.getTSexo().setText(placeholders[3]);
		amigosTemplate.getTTelefono().setText(placeholders[4]);
		amigosTemplate.getTDireccion().setText(placeholders[5]);
		amigosTemplate.getTabla().setSelectionMode(0);
	}

	public void mostrarRegistrosTabla() {
		int tamaño = alPersonaBin.size();
		for (int i = 0; i < tamaño; i++) {
			persona = personaBinDAO.buscarPersona(i, alPersonaBin);
			this.agregarRegistro(persona);
		}
		amigosTemplate.getLIdValor().setText(tamaño + "");
		amigosTemplate.getBMostrar().setEnabled(false);
	}

	public void insertarRegistroTabla() {
		persona = new Persona();
		persona.setId(alPersonaBin.size());
		persona.setNombre(amigosTemplate.getTNombre().getText());
		persona.setApellido(amigosTemplate.getTApellido().getText());
		persona.setIdentificacion(amigosTemplate.getTIdentificacion().getText());
		persona.setSexo(amigosTemplate.getTSexo().getText());
		persona.setTelefono(amigosTemplate.getTTelefono().getText());
		persona.setDireccion(amigosTemplate.getTDireccion().getText());
		this.agregarRegistro(persona);
		personaBinDAO.agregarPersona2(persona, alPersonaBin, filePersonaBin);
		restaurarValores();
	}
	
	public void modificarRegistroTabla() {
		int fSeleccionada = amigosTemplate.getTabla().getSelectedRow();
		if (fSeleccionada != -1) {
			amigosTemplate.getModelo().setValueAt(amigosTemplate.getTNombre().getText(), fSeleccionada, 1);
			amigosTemplate.getModelo().setValueAt(amigosTemplate.getTApellido().getText(), fSeleccionada, 2);
			amigosTemplate.getModelo().setValueAt(amigosTemplate.getTIdentificacion().getText(), fSeleccionada, 3);
			amigosTemplate.getModelo().setValueAt(amigosTemplate.getTSexo().getText(), fSeleccionada, 4);
			amigosTemplate.getModelo().setValueAt(amigosTemplate.getTTelefono().getText(), fSeleccionada, 5);
			amigosTemplate.getModelo().setValueAt(amigosTemplate.getTDireccion().getText(), fSeleccionada, 6);

			persona = personaBinDAO.buscarPersona(fSeleccionada, alPersonaBin);
			persona.setNombre(amigosTemplate.getTNombre().getText());
			persona.setApellido(amigosTemplate.getTApellido().getText());
			persona.setIdentificacion(amigosTemplate.getTIdentificacion().getText());
			persona.setSexo(amigosTemplate.getTSexo().getText());
			persona.setTelefono(amigosTemplate.getTTelefono().getText());
			persona.setDireccion(amigosTemplate.getTDireccion().getText());
			personaBinDAO.editarPersona2(persona, alPersonaBin, filePersonaBin);
			restaurarValores();
		} else
			JOptionPane.showMessageDialog(null, "seleccione una fila", "Error", JOptionPane.ERROR_MESSAGE);
	}

	public void eliminarRegistroTabla() {
		int fSeleccionada = amigosTemplate.getTabla().getSelectedRow();
		if (fSeleccionada != -1) {
			personaBinDAO.eliminarPersona2(fSeleccionada, alPersonaBin, filePersonaBin);
			amigosTemplate.getModelo().removeRow(fSeleccionada);
		} else {
			JOptionPane.showMessageDialog(null, "seleccione una fila", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void filtrarRegistrosTabla() {
		TableRowSorter<DefaultTableModel> trs = new TableRowSorter<>(amigosTemplate.getModelo());
		amigosTemplate.getTabla().setRowSorter(trs);
		trs.setRowFilter(RowFilter.regexFilter(amigosTemplate.getTConsulta().getText()));
	}

	public void agregarRegistro(Persona amigo) {
		amigosTemplate.getModelo().addRow(new Object[] { amigo.getId(), amigo.getNombre(), amigo.getApellido(),
				amigo.getIdentificacion(), amigo.getSexo(), amigo.getTelefono(), amigo.getDireccion(), });
	}
}