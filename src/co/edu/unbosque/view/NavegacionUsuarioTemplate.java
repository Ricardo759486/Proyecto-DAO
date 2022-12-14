package co.edu.unbosque.view;

import co.edu.unbosque.controller.NavegacionUsuarioComponent;
import co.edu.unbosque.view.ObjGraficosService;
import co.edu.unbosque.view.RecursosService;

import java.awt.Color;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

public class NavegacionUsuarioTemplate extends JPanel {
	private static final long serialVersionUID = 1L;

	// Declaración Servicios y dependencias
	private NavegacionUsuarioComponent navegacionUsuarioComponent;
	private ObjGraficosService sObjGraficos;
	private RecursosService sRecursos;

	// Declaración objetos gráficos
	private JPanel pSuperior, pInferior;
	private JLabel lNombreUsuario, lEslogan, lImagenUsuario, lIconoUsuario;
	private JButton bInicio, bPerfil, bAmigos, bProductos, bConfiguracion, bCerrarSesion;

	// Declaración Objetos Decoradores
	private ImageIcon iIconoUsuario, iInicio, iPerfil, iAmigos, iProductos;
	private ImageIcon iConfiguracion, iCerrarSesion, iDimAux;
	private Border bVacio;

	public NavegacionUsuarioTemplate(NavegacionUsuarioComponent navegacionUsuarioComponent) {
		this.navegacionUsuarioComponent = navegacionUsuarioComponent;
		this.sObjGraficos = ObjGraficosService.getService();
		this.sRecursos = RecursosService.getService();

		this.crearObjetosDecoradores();
		this.crearJPanels();
		this.crearJLabels();
		this.crearJButtons();

		this.setSize(250, 700);
		this.setLayout(null);
		this.setVisible(true);
	}

	public void crearJPanels() {
		this.pSuperior = sObjGraficos.construirJPanel(0, 0, 250, 300, sRecursos.getColorPrincipal(), null);
		this.add(pSuperior);

		this.pInferior = sObjGraficos.construirJPanel(0, 300, 250, 400, sRecursos.getColorPrincipal(), null);
		this.add(pInferior);
	}

	public void crearObjetosDecoradores() {
		this.iIconoUsuario = new ImageIcon("ProyectoInterfazGrafica/resources/images/usuario_navegacion.png");
		this.iInicio = new ImageIcon("ProyectoInterfazGrafica/resources/images/inicio.png");
		this.iPerfil = new ImageIcon("ProyectoInterfazGrafica/resources/images/perfil.png");
		this.iAmigos = new ImageIcon("ProyectoInterfazGrafica/resources/images/amigos.png");
		this.iProductos = new ImageIcon("ProyectoInterfazGrafica/resources/images/productos.png");
		this.iConfiguracion = new ImageIcon("ProyectoInterfazGrafica/resources/images/configuracion.png");
		this.iCerrarSesion = new ImageIcon("ProyectoInterfazGrafica/resources/images/salir.png");
		this.bVacio = new EmptyBorder(2, 20, 2, 2);
	}

	public void crearJButtons() {
		// BOTÓN
		// INICIO--------------------------------------------------------------------
		iDimAux = new ImageIcon(iInicio.getImage().getScaledInstance(20, 20, Image.SCALE_AREA_AVERAGING));
		this.bInicio = sObjGraficos.construirJButton("      ArrayList", 30, 30, 200, 40, sRecursos.getCMano(), iDimAux,
				sRecursos.getFontMediana(), null, Color.WHITE, bVacio, "l", false);
		this.bInicio.addActionListener(navegacionUsuarioComponent);
		this.bInicio.addMouseListener(navegacionUsuarioComponent);
		this.pInferior.add(bInicio);

		// BOTÓN
		// PERFIL--------------------------------------------------------------------
		iDimAux = new ImageIcon(iPerfil.getImage().getScaledInstance(20, 20, Image.SCALE_AREA_AVERAGING));
		this.bPerfil = sObjGraficos.construirJButton("      Binario", 30, 80, 200, 40, sRecursos.getCMano(), iDimAux,
				sRecursos.getFontMediana(), null, Color.WHITE, bVacio, "l", false);
		this.bPerfil.addActionListener(navegacionUsuarioComponent);
		this.bPerfil.addMouseListener(navegacionUsuarioComponent);
		this.pInferior.add(bPerfil);

		// BOTÓN
		// AMIGOS--------------------------------------------------------------------
		iDimAux = new ImageIcon(iAmigos.getImage().getScaledInstance(20, 20, Image.SCALE_AREA_AVERAGING));
		this.bAmigos = sObjGraficos.construirJButton("      SQLite", 30, 130, 200, 40, sRecursos.getCMano(), iDimAux,
				sRecursos.getFontMediana(), null, Color.WHITE, bVacio, "l", false);
		this.bAmigos.addActionListener(navegacionUsuarioComponent);
		this.bAmigos.addMouseListener(navegacionUsuarioComponent);
		this.pInferior.add(bAmigos);

		// BOTÓN
		// PRODUCTOS--------------------------------------------------------------------
		iDimAux = new ImageIcon(iProductos.getImage().getScaledInstance(20, 20, Image.SCALE_AREA_AVERAGING));
		this.bProductos = sObjGraficos.construirJButton("      Cassandra", 30, 180, 200, 40, sRecursos.getCMano(),
				iDimAux, sRecursos.getFontMediana(), null, Color.WHITE, bVacio, "l", false);
		this.bProductos.addActionListener(navegacionUsuarioComponent);
		this.bProductos.addMouseListener(navegacionUsuarioComponent);
		this.pInferior.add(bProductos);

	}

	public void crearJLabels() {
		// LABEL ICONO
		// USUARIO--------------------------------------------------------------------
		iDimAux = new ImageIcon(iIconoUsuario.getImage().getScaledInstance(40, 40, Image.SCALE_AREA_AVERAGING));
		this.lIconoUsuario = sObjGraficos.construirJLabel(null, 10, 20, 40, 40, null, iDimAux, null, null, null, null,
				"c");
		this.pSuperior.add(lIconoUsuario);

		// LABEL NOMBRE
		// USUARIO--------------------------------------------------------------------
		this.lNombreUsuario = sObjGraficos.construirJLabel("Programa DAO", (this.pSuperior.getWidth() - 200) / 2, 20,
				200, 40, null, null, sRecursos.getFontTitulo(), null, Color.WHITE, null, "c");
		this.pSuperior.add(lNombreUsuario);

		// LABEL IMAGEN
		// USUARIO--------------------------------------------------------------------
		//iDimAux = new ImageIcon(navegacionUsuarioComponent.getUsuario().getImagenUsuario().getImage()
				//.getScaledInstance(180, 180, Image.SCALE_AREA_AVERAGING));

		this.lImagenUsuario = sObjGraficos.construirJLabel(null, (this.pSuperior.getWidth() - 180) / 2, 75, 180, 180,
				null, iDimAux, null, null, null, null, "c");
		lImagenUsuario.setBorder(sRecursos.getBCircular());
		this.pSuperior.add(lImagenUsuario);
	}

	public JPanel getPSuperior() {
		return pSuperior;
	}
}