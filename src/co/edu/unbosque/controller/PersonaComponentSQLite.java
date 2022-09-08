package co.edu.unbosque.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import co.edu.unbosque.model.Persona;
import co.edu.unbosque.model.persistence.PersonaSQLiteDAO;
import co.edu.unbosque.view.PersonaSQLiteTemplate;
import co.edu.unbosque.view.RecursosService;

public class PersonaComponentSQLite extends MouseAdapter implements ActionListener, FocusListener {
	private PersonaSQLiteTemplate amigosTemplate;
	private PersonaSQLiteDAO personaSQLiteDAO;
	private String[] placeholders = { "Nombre", "Apellido", "Identificación", "Sexo", "Telefono", "Direccion",
			"Filtrar...", };
	private Persona amigo;

	public PersonaComponentSQLite() {
		personaSQLiteDAO = new PersonaSQLiteDAO();
		amigosTemplate = new PersonaSQLiteTemplate(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == amigosTemplate.getBMostrar())
			try {
				mostrarRegistrosTabla();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		if (e.getSource() == amigosTemplate.getBInsertar())
			try {
				insertarRegistroTabla();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		if (e.getSource() == amigosTemplate.getBModificar())
			try {
				modificarRegistroTabla();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		if (e.getSource() == amigosTemplate.getBEliminar())
			eliminarRegistroTabla();
		if (e.getSource() == amigosTemplate.getBFiltrar())
			filtrarRegistrosTabla();
	}

	public PersonaSQLiteTemplate getAmigosTemplate() {
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
			int fila = amigosTemplate.getTabla().getSelectedRow() +16;
			List<Persona> miLista;
			try {
				miLista = personaSQLiteDAO.listaPersonas();
				amigo = personaSQLiteDAO.buscarPersona(fila, miLista);
				amigosTemplate.getLIdValor().setText(amigo.getId() + "");
				amigosTemplate.getTNombre().setText(amigo.getNombre());
				amigosTemplate.getTApellido().setText(amigo.getApellido());
				amigosTemplate.getTIdentificacion().setText(amigo.getIdentificacion());
				amigosTemplate.getTSexo().setText(amigo.getSexo());
				amigosTemplate.getTTelefono().setText(amigo.getTelefono());
				amigosTemplate.getTDireccion().setText(amigo.getDireccion());
			} catch (Exception e1) {
				e1.printStackTrace();
			}
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

	public void restaurarValores() throws Exception {
		List<Persona> miLista = personaSQLiteDAO.listaPersonas();
		int tamaño = miLista.size();
		amigosTemplate.getLIdValor().setText(tamaño + "");
		amigosTemplate.getTNombre().setText(placeholders[0]);
		amigosTemplate.getTApellido().setText(placeholders[1]);
		amigosTemplate.getTIdentificacion().setText(placeholders[2]);
		amigosTemplate.getTSexo().setText(placeholders[3]);
		amigosTemplate.getTTelefono().setText(placeholders[4]);
		amigosTemplate.getTDireccion().setText(placeholders[5]);
		amigosTemplate.getTabla().setSelectionMode(0);
	}

	public void mostrarRegistrosTabla() throws Exception {
		List<Persona> miLista = personaSQLiteDAO.listaPersonas();
		int tamaño = miLista.size();
		boolean v = true;
		int i = 0;
		int f = 0;
		while(f < miLista.size()) {
			amigo = personaSQLiteDAO.buscarPersona(i, miLista);
			if(amigo != null) {
				this.agregarRegistro(amigo);	
				f++;
			}
			i++;
		}
	}

	public void insertarRegistroTabla() throws Exception {
		amigo = new Persona();
		List<Persona> miLista = personaSQLiteDAO.listaPersonas();
		int tamaño = miLista.size();
		amigo.setId(tamaño);
		amigo.setNombre(amigosTemplate.getTNombre().getText());
		amigo.setApellido(amigosTemplate.getTApellido().getText());
		amigo.setIdentificacion(amigosTemplate.getTIdentificacion().getText());
		amigo.setSexo(amigosTemplate.getTSexo().getText());
		amigo.setTelefono(amigosTemplate.getTTelefono().getText());
		amigo.setDireccion(amigosTemplate.getTDireccion().getText());
		this.agregarRegistro(amigo);
		personaSQLiteDAO.agregarPersona(amigo);
		restaurarValores();
	}

	public void modificarRegistroTabla() throws Exception {
		int fSeleccionada = amigosTemplate.getTabla().getSelectedRow()-1;
		if (fSeleccionada != -1) {
			amigosTemplate.getModelo().setValueAt(amigosTemplate.getTNombre().getText(), fSeleccionada, 1);
			amigosTemplate.getModelo().setValueAt(amigosTemplate.getTApellido().getText(), fSeleccionada, 2);
			amigosTemplate.getModelo().setValueAt(amigosTemplate.getTIdentificacion().getText(), fSeleccionada, 3);
			amigosTemplate.getModelo().setValueAt(amigosTemplate.getTSexo().getText(), fSeleccionada, 4);
			amigosTemplate.getModelo().setValueAt(amigosTemplate.getTTelefono().getText(), fSeleccionada, 5);
			amigosTemplate.getModelo().setValueAt(amigosTemplate.getTDireccion().getText(), fSeleccionada, 6);

			amigo = new Persona();
			List<Persona> miLista = personaSQLiteDAO.listaPersonas();
			amigo = personaSQLiteDAO.buscarPersona(fSeleccionada, miLista);
			amigo.setNombre(amigosTemplate.getTNombre().getText());
			amigo.setApellido(amigosTemplate.getTApellido().getText());
			amigo.setIdentificacion(amigosTemplate.getTIdentificacion().getText());
			amigo.setSexo(amigosTemplate.getTSexo().getText());
			amigo.setTelefono(amigosTemplate.getTTelefono().getText());
			amigo.setDireccion(amigosTemplate.getTDireccion().getText());
			personaSQLiteDAO.editarPersona(amigo);
			restaurarValores();
		} else
			JOptionPane.showMessageDialog(null, "seleccione una fila", "Error", JOptionPane.ERROR_MESSAGE);
	}

	public void eliminarRegistroTabla() {
		int fSeleccionada = amigosTemplate.getTabla().getSelectedRow();
		if (fSeleccionada != -1) {
			personaSQLiteDAO.eliminarPersona(fSeleccionada);
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