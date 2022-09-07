package co.edu.unbosque.view;

import co.edu.unbosque.controller.PersonaComponentArray;
import co.edu.unbosque.view.GraficosAvanzadosService;
import co.edu.unbosque.view.ObjGraficosService;
import co.edu.unbosque.view.RecursosService;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class PersonaArrayTemplate extends JPanel {
  private static final long serialVersionUID = 1L;
  
  // Declaración servicios y objetos
  private PersonaComponentArray amigosComponent;
  private ObjGraficosService sObjGraficos;
  private GraficosAvanzadosService sGraficosAvanzados;
  private RecursosService sRecursos;

  // Declaración objetos gráficos
  private JPanel pOpciones, pDatos;
  private JButton bMostrar, bInsertar, bFiltrar, bModificar, bEliminar;
  private JTextField tConsulta;
  private JLabel lTitulo, lInstrucciones, lEslogan;
  private JLabel lId, lIdValor, lNombre, lApellido, lIdentificacion, lSexo, lTelefono, lDireccion;
  private JTextField tNombre, tApellido, tIdentificacion, tSexo, tTelefono, tDireccion;

  // Declaración objetos decoradores
  private Color colorGris;

  // Declaración objetos para JTable
  private JScrollPane pTabla;
  private JPanel pCorner;
  private JTable tabla;
  private JTableHeader header;
  private DefaultTableModel modelo;
  private String[] cabecera = { "id", "Nombre", "Apellido", "Identificacion" , "Sexo", "Telefono", "Direccion" };

  public PersonaArrayTemplate(PersonaComponentArray amigosComponent) {
    this.amigosComponent = amigosComponent;
    this.sObjGraficos = ObjGraficosService.getService();
    this.sRecursos = RecursosService.getService();
    this.sGraficosAvanzados = GraficosAvanzadosService.getService();

    this.colorGris = new Color(235, 235, 235);

    this.crearJPanels();
    this.crearJTable();
    this.crearContenidoPOpciones();
    this.crearContenidoPDatos();

    this.setSize(850, 600);
    this.setBackground(sRecursos.getColorGrisClaro());
    this.setLayout(null);
    this.setVisible(true);
  }

  public void crearJPanels() {
    pOpciones = sObjGraficos.construirJPanel(10, 10, 580, 200, Color.WHITE, null);
    this.add(pOpciones);

    pDatos = sObjGraficos.construirJPanel(600, 10, 240, 580, Color.WHITE, null);
    this.add(pDatos);
  }

  public void crearJTable() {
    modelo = new DefaultTableModel();
    modelo.setColumnIdentifiers(cabecera);

    tabla = new JTable();
    tabla.setModel(modelo);
    tabla.addMouseListener(amigosComponent);

    tabla.setRowHeight(40);
    tabla.setShowHorizontalLines(false);
    tabla.setShowVerticalLines(false);

    header = tabla.getTableHeader();
    header.setPreferredSize(new Dimension(580, 30));

    pTabla = sObjGraficos.construirPanelBarra(
      tabla,
      10, 220, 580, 370,
      Color.WHITE,
      null
    );

    header.setDefaultRenderer(
      sGraficosAvanzados.devolverTablaPersonalizada(
        sRecursos.getColorPrincipal(),
        null, null,
        Color.WHITE,
        sRecursos.getFontLigera()
      )
    );

    tabla.setDefaultRenderer(
      Object.class,
      sGraficosAvanzados.devolverTablaPersonalizada(
        Color.WHITE,
        sRecursos.getColorGrisClaro(),
        sRecursos.getColorPrincipalOscuro(),
        sRecursos.getColorGrisOscuro(),
        sRecursos.getFontLigera()
      )
    );

    pTabla.getVerticalScrollBar().setUI(
      sGraficosAvanzados.devolverScrollPersonalizado(
        7, 10,
        Color.WHITE,
        Color.GRAY,
        sRecursos.getColorGrisOscuro()
      )
    );

    pCorner = new JPanel();
    pCorner.setBackground(sRecursos.getColorPrincipal());
    pTabla.setCorner(JScrollPane.UPPER_RIGHT_CORNER, pCorner);
    this.add(pTabla);
  }

  public void crearContenidoPOpciones() {
    // LABEL TITULO--------------------------------------------------------------------
    lTitulo = sObjGraficos.construirJLabel(
      "Edicion de Contactos",
      20, 10, 200, 30,
      null, null,
      sRecursos.getFontTitulo(),
      null,
      sRecursos.getColorGrisOscuro(),
      null,
      "c"
    );
    pOpciones.add(lTitulo);

    // TEXTFIELD CONSULTA--------------------------------------------------------------------
    tConsulta = sObjGraficos.construirJTextField(
      "Filtrar...",
      30, 60, 380, 40,
      sRecursos.getFontMediana(),
      colorGris,
      sRecursos.getColorGrisOscuro(),
      sRecursos.getColorGrisOscuro(),
      null,
      "c"
    );
    tConsulta.addFocusListener(amigosComponent);
    pOpciones.add(tConsulta);

    // BOTÓN FILTRAR--------------------------------------------------------------------
    bFiltrar = sObjGraficos.construirJButton(
      "Filtrar",
      430, 65, 120, 35,
      sRecursos.getCMano(),
      null,
      sRecursos.getFontMediana(),
      sRecursos.getColorPrincipal(),
      Color.WHITE,
      null,
      "c",
      true
    );
    bFiltrar.addMouseListener(amigosComponent);
    bFiltrar.addActionListener(amigosComponent);
    pOpciones.add(bFiltrar);

    // BOTÓN MOSTRAR--------------------------------------------------------------------
    bMostrar = sObjGraficos.construirJButton(
      "Mostrar",
      20, 145, 120, 35,
      sRecursos.getCMano(),
      null,
      sRecursos.getFontMediana(),
      sRecursos.getColorPrincipal(),
      Color.WHITE,
      null,
      "c",
      true
    );
    bMostrar.addMouseListener(amigosComponent);
    bMostrar.addActionListener(amigosComponent);
    pOpciones.add(bMostrar);

    // BOTÓN INSERTAR--------------------------------------------------------------------
    bInsertar = sObjGraficos.construirJButton(
      "Insertar",
      160, 145, 120, 35,
      sRecursos.getCMano(),
      null,
      sRecursos.getFontMediana(),
      sRecursos.getColorPrincipal(),
      Color.WHITE,
      null,
      "c",
      true
    );
    bInsertar.addMouseListener(amigosComponent);
    bInsertar.addActionListener(amigosComponent);
    pOpciones.add(bInsertar);

    // BOTÓN MODIFICAR--------------------------------------------------------------------
    bModificar = sObjGraficos.construirJButton(
      "Editar",
      300, 145, 120, 35,
      sRecursos.getCMano(),
      null,
      sRecursos.getFontMediana(),
      sRecursos.getColorPrincipal(),
      Color.WHITE,
      null,
      "c",
      true
    );
    bModificar.addMouseListener(amigosComponent);
    bModificar.addActionListener(amigosComponent);
    pOpciones.add(bModificar);

    // BOTÓN ELIMINAR--------------------------------------------------------------------
    bEliminar = sObjGraficos.construirJButton(
      "Eliminar",
      440, 145, 120, 35,
      sRecursos.getCMano(),
      null,
      sRecursos.getFontMediana(),
      sRecursos.getColorPrincipal(),
      Color.WHITE,
      null,
      "c",
      true
    );
    bEliminar.addMouseListener(amigosComponent);
    bEliminar.addActionListener(amigosComponent);
    pOpciones.add(bEliminar);
  }

  public void crearContenidoPDatos() {
    // LABEL INSTRUCCIONES ----------------------------------------------------------------
    lInstrucciones = sObjGraficos.construirJLabel(
      "<html>Informaci�n de la persona Array<html>",
      20, 10, 150, 50,
      null, null,
      sRecursos.getFontTitulo(),
      null,
      sRecursos.getColorGrisOscuro(),
      null,
      "l"
    );
    pDatos.add(lInstrucciones);

    // LABEL ID ----------------------------------------------------------------
    lId = sObjGraficos.construirJLabel(
      "Id:",
      20, 50, 180, 90,
      null, null,
      sRecursos.getFontLigera(),
      null,
      sRecursos.getColorPrincipalOscuro(),
      null,
      "l"
    );
    pDatos.add(lId);

    // LABEL ID CONTENIDO ----------------------------------------------------------
    lIdValor = sObjGraficos.construirJLabel(
      "0",
      40, 50, 180, 90,
      null, null,
      sRecursos.getFontLigera(),
      null,
      sRecursos.getColorPrincipalOscuro(),
      null,
      "l"
    );
    pDatos.add(lIdValor);

    // LABEL NOMBRE ----------------------------------------------------------------
    lNombre = sObjGraficos.construirJLabel(
      "Nombre:",
      20, 105, 160, 30,
      null, null,
      sRecursos.getFontLigera(),
      null,
      sRecursos.getColorPrincipalOscuro(),
      null,
      "l"
    );
    pDatos.add(lNombre);

    // TEXTFIELD NOMBRE ----------------------------------------------------------------
    tNombre = sObjGraficos.construirJTextField(
      "Nombre",
      30, 140, 180, 30,
      sRecursos.getFontLigera(),
      colorGris,
      sRecursos.getColorGrisOscuro(),
      sRecursos.getColorGrisOscuro(),
      null,
      "c"
    );
    tNombre.addFocusListener(amigosComponent);
    pDatos.add(tNombre);

    // LABEL Apellido ----------------------------------------------------------------
    lApellido = sObjGraficos.construirJLabel(
      "Apellido:",
      20, 180, 160, 30,
      null, null,
      sRecursos.getFontLigera(),
      null,
      sRecursos.getColorPrincipalOscuro(),
      null,
      "l"
    );
    pDatos.add(lApellido);

    // TEXTFIELD Apellido ----------------------------------------------------------------
    tApellido = sObjGraficos.construirJTextField(
      "Apellido",
      30, 215, 180, 30,
      sRecursos.getFontLigera(),
      colorGris,
      sRecursos.getColorGrisOscuro(),
      sRecursos.getColorGrisOscuro(),
      null,
      "c"
    );
    tApellido.addFocusListener(amigosComponent);
    pDatos.add(tApellido);

    // LABEL Identificacion ----------------------------------------------------------------
    lIdentificacion = sObjGraficos.construirJLabel(
      "Identificacion:",
      20, 265, 160, 30,
      null, null,
      sRecursos.getFontLigera(),
      null,
      sRecursos.getColorPrincipalOscuro(),
      null,
      "l"
    );
    pDatos.add(lIdentificacion);

    // TEXTFIELD IDENTIFICACION ----------------------------------------------------------------
    tIdentificacion = sObjGraficos.construirJTextField(
      "Identificacion",
      30, 300, 180, 30,
      sRecursos.getFontLigera(),
      colorGris,
      sRecursos.getColorGrisOscuro(),
      sRecursos.getColorGrisOscuro(),
      null,
      "c"
    );
    tIdentificacion.addFocusListener(amigosComponent);
    pDatos.add(tIdentificacion);

    // LABEL SEXO ----------------------------------------------------------------
    lSexo = sObjGraficos.construirJLabel(
      "Sexo:",
      20, 350, 160, 30,
      null, null,
      sRecursos.getFontLigera(),
      null,
      sRecursos.getColorPrincipalOscuro(),
      null,
      "l"
    );
    pDatos.add(lSexo);

    // TEXTFIELD SEXO ----------------------------------------------------------------
    tSexo = sObjGraficos.construirJTextField(
      "Sexo",
      30, 385, 180, 30,
      sRecursos.getFontLigera(),
      colorGris,
      sRecursos.getColorGrisOscuro(),
      sRecursos.getColorGrisOscuro(),
      null,
      "c"
    );
    tSexo.addFocusListener(amigosComponent);
    pDatos.add(tSexo);

    // LABEL TELEFONO ----------------------------------------------------------------
    lTelefono = sObjGraficos.construirJLabel(
      "Telefono:",
      20, 425, 160, 30,
      null, null,
      sRecursos.getFontLigera(),
      null,
      sRecursos.getColorPrincipalOscuro(),
      null,
      "l"
    );
    pDatos.add(lTelefono);

    // TEXTFIELD TELEFONO ----------------------------------------------------------------
    tTelefono = sObjGraficos.construirJTextField(
      "Telefono",
      30, 460, 180, 30,
      sRecursos.getFontLigera(),
      colorGris,
      sRecursos.getColorGrisOscuro(),
      sRecursos.getColorGrisOscuro(),
      null,
      "c"
    );
    tTelefono.addFocusListener(amigosComponent);
    pDatos.add(tTelefono);
    
    
    
 // LABEL DIRECCION ----------------------------------------------------------------
    lDireccion = sObjGraficos.construirJLabel(
      "Direccion:",
      20, 510, 160, 30,
      null, null,
      sRecursos.getFontLigera(),
      null,
      sRecursos.getColorPrincipalOscuro(),
      null,
      "l"
    );
    pDatos.add(lDireccion);

    // TEXTFIELD DIRECCION ----------------------------------------------------------------
    tDireccion = sObjGraficos.construirJTextField(
      "Direccion",
      30, 545, 180, 30,
      sRecursos.getFontLigera(),
      colorGris,
      sRecursos.getColorGrisOscuro(),
      sRecursos.getColorGrisOscuro(),
      null,
      "c"
    );
    tDireccion.addFocusListener(amigosComponent);
    pDatos.add(tDireccion);
  }

  public JTable getTabla() { return tabla; }

  public DefaultTableModel getModelo() { return modelo; }

  public JButton getBMostrar() { return bMostrar; }

  public JButton getBInsertar() { return bInsertar; }

  public JButton getBModificar() { return bModificar; }

  public JButton getBEliminar() { return bEliminar; }

  public JButton getBFiltrar() { return bFiltrar; }

  public JLabel getLIdValor() { return lIdValor; }

  public JTextField getTNombre() { return tNombre; }
  
  public JTextField getTApellido() { return tApellido; }

  public JTextField getTIdentificacion() { return tIdentificacion; }

  public JTextField getTSexo() { return tSexo; }

  public JTextField getTTelefono() { return tTelefono; }

  public JTextField getTDireccion() { return tDireccion; }

  public JTextField getTConsulta() { return tConsulta; }
}