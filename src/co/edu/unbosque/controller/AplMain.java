package co.edu.unbosque.controller;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class AplMain {

	public static void main(String[] args) throws Exception {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (UnsupportedLookAndFeelException e) {
		} catch (ClassNotFoundException e) {
		} catch (InstantiationException e) {
		} catch (IllegalAccessException e) {
		}
		Controller control = new Controller();
		//control.probarArray(1,"1000519146", "Ricardo", "Cuevas", 'M', "3123043070", "CR. 6 N° 17-90");
		control.conexionSqlite(0, "1000519146", "Porras", "Ladino", "M", "3142589099", "CR. 16 N° 17-90");
	}

}
