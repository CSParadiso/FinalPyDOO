import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelConsultaEInscripcion extends JPanel{
	JPanel panelBoxIzquierdo, panelInscripcionCarrera, panelNombre, panelApellido, panelLegajo, 
	panelRecibido, panelCursando, panelCursandoMateria, panelDatosEstudiante, panelInfoEstudiante, panelBoxDerecho;
	JButton botonInscribirACarrera, botonCursarMateria;
	JLabel etiquetaNombre, etiquetaApellido, etiquetaLegajo, etiquetaRecibido, etiquetaCursando, etiquetaCursandoMateria;
	static JTextField campoNombre, campoApellido, campoLegajo, campoRecibido, campoCursando, campoCursandoMateria;
	//static DefaultListModel<String> modeloListaEstudiantes;
	static DefaultListModel<String> modeloListaMaterias;
	static DefaultTableModel modeloTablaEstudiantes;
	static JTable tablaEstudiantes;
	static JList<String> listaMaterias;
	JScrollPane panelListaEstudiantes, panelListaMaterias;
	static SpinnerListModel modeloListaCarreras;
	ControladorMultiPestaña controlador;
	
	PanelConsultaEInscripcion(){
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		modeloTablaEstudiantes = new DefaultTableModel() {
			@Override 
			public boolean isCellEditable(int fila, int columna) {return false;}
		};
		String [] columnas = new String[] {"Legajo", "Documento", "Nombre", "Apellido"};
		modeloTablaEstudiantes.setColumnIdentifiers(columnas);
		tablaEstudiantes = new JTable(modeloTablaEstudiantes);
		tablaEstudiantes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		panelListaEstudiantes = new JScrollPane(tablaEstudiantes);
		panelBoxIzquierdo = new JPanel();
		panelBoxIzquierdo.setLayout(new BoxLayout(panelBoxIzquierdo, BoxLayout.Y_AXIS));
		panelBoxIzquierdo.add(panelListaEstudiantes);
		panelBoxIzquierdo.setPreferredSize(new Dimension(410, 2000));
		panelBoxIzquierdo.setMaximumSize(new Dimension(410, 2000));
		
		botonInscribirACarrera = new JButton("Inscribir a Carrera");
		modeloListaCarreras = new SpinnerListModel();
		panelInscripcionCarrera = new JPanel();
		panelInscripcionCarrera.setLayout(new BoxLayout(panelInscripcionCarrera, BoxLayout.X_AXIS));
		panelInscripcionCarrera.add(new JSpinner(modeloListaCarreras));
		panelInscripcionCarrera.add(botonInscribirACarrera);
		
		etiquetaNombre = new JLabel("Nombre: ");
		campoNombre = new JTextField(10);
		campoNombre.setEditable(false);
		campoNombre.setBackground(Color.white);
		panelNombre = new JPanel();
		panelNombre.setLayout(new BoxLayout(panelNombre, BoxLayout.X_AXIS));
		panelNombre.add(etiquetaNombre);
		panelNombre.add(campoNombre);
		
		etiquetaApellido = new JLabel("Apellido: ");
		campoApellido = new JTextField(10);
		campoApellido.setEditable(false);
		campoApellido.setBackground(Color.white);
		panelApellido = new JPanel();
		panelApellido.setLayout(new BoxLayout(panelApellido, BoxLayout.X_AXIS));
		panelApellido.add(etiquetaApellido);
		panelApellido.add(campoApellido);
		
		etiquetaLegajo = new JLabel("Legajo: ");
		campoLegajo = new JTextField(10);
		campoLegajo.setEditable(false);
		campoLegajo.setBackground(Color.white);
		panelLegajo = new JPanel();
		panelLegajo.setLayout(new BoxLayout(panelLegajo, BoxLayout.X_AXIS));
		panelLegajo.add(etiquetaLegajo);
		panelLegajo.add(campoLegajo);
		
		etiquetaRecibido = new JLabel("Recibido de: ");
		campoRecibido = new JTextField(10);
		campoRecibido.setEditable(false);
		campoRecibido.setBackground(Color.white);
		panelRecibido = new JPanel();
		panelRecibido.setLayout(new BoxLayout(panelRecibido, BoxLayout.X_AXIS));
		panelRecibido.add(etiquetaRecibido);
		panelRecibido.add(campoRecibido);
		
		etiquetaCursando = new JLabel("Cursando carrera/s: ");
		campoCursando = new JTextField(10);
		campoCursando.setEditable(false);
		campoCursando.setBackground(Color.white);
		panelCursando = new JPanel();
		panelCursando.setLayout(new BoxLayout(panelCursando, BoxLayout.X_AXIS));
		panelCursando.add(etiquetaCursando);
		panelCursando.add(campoCursando);
		
		etiquetaCursandoMateria = new JLabel("Cursando materia/s: ");
		campoCursandoMateria = new JTextField(10);
		campoCursandoMateria.setEditable(false);
		campoCursandoMateria.setBackground(Color.white);
		panelCursandoMateria = new JPanel();
		panelCursandoMateria.setLayout(new BoxLayout(panelCursandoMateria, BoxLayout.X_AXIS));
		panelCursandoMateria.add(etiquetaCursandoMateria);
		panelCursandoMateria.add(campoCursandoMateria);
		
		panelDatosEstudiante = new JPanel();
		panelDatosEstudiante.setLayout(new BoxLayout(panelDatosEstudiante, BoxLayout.Y_AXIS));
		panelDatosEstudiante.add(panelNombre);
		panelDatosEstudiante.add(panelApellido);
		panelDatosEstudiante.add(panelLegajo);
		panelDatosEstudiante.add(panelRecibido);
		panelDatosEstudiante.add(panelCursando);
		panelDatosEstudiante.add(panelCursandoMateria);
		
		panelInfoEstudiante = new JPanel();
		panelInfoEstudiante.setLayout(new BoxLayout(panelInfoEstudiante, BoxLayout.Y_AXIS));
		panelInfoEstudiante.add(panelDatosEstudiante);
		panelInfoEstudiante.setBorder(BorderFactory.createTitledBorder("Información Estudiante"));
		
		modeloListaMaterias = new DefaultListModel<String>();
		listaMaterias = new JList<String>(modeloListaMaterias);
		listaMaterias.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		listaMaterias.setVisibleRowCount(40);
		listaMaterias.setLayoutOrientation(JList.VERTICAL);
		Font fuente = new Font("Arial", Font.ITALIC, 12);
		listaMaterias.setFont(fuente);
		panelListaMaterias = new JScrollPane(listaMaterias);
		TitledBorder titulo = BorderFactory.createTitledBorder("Materias que el estudiante puede cursar");
		titulo.setTitleJustification(TitledBorder.RIGHT);
		panelListaMaterias.setBorder(titulo);
		
		botonCursarMateria = new JButton("Cursar Materia/s");
		
		panelBoxDerecho = new JPanel();
		panelBoxDerecho.setLayout(new BoxLayout(panelBoxDerecho, BoxLayout.Y_AXIS));
		panelBoxDerecho.add(panelInscripcionCarrera);
		panelBoxDerecho.add(Box.createVerticalGlue());
		panelBoxDerecho.add(panelInfoEstudiante);
		panelBoxDerecho.add(panelListaMaterias);
		panelBoxDerecho.add(botonCursarMateria);
				
		add(panelBoxIzquierdo);
		//add(Box.createHorizontalGlue());
		add(panelBoxDerecho);
		
		// Inicialización Controldor de esta Vista
		controlador = ControladorMultiPestaña.getInstancia(this);
				
		// Listeners que disparan el Controlador de esta Vista
		tablaEstudiantes.getSelectionModel().addListSelectionListener(new ListSelectionListener() { // NO es la tabla sinó su modo de selección
			public void valueChanged(ListSelectionEvent e) {
				filaSeleccionada();
			};
		});
		
		botonInscribirACarrera.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				botonInscribirACarreraPresionado();
			};
		});
		
		botonCursarMateria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				botonCursarMateriaPresionado();
			}
		});
		
	};
	
	// Métodos privados de la clase
	private void filaSeleccionada() {
		controlador.visualizarDatosEstudiante();
	};
	
	private void botonInscribirACarreraPresionado() {
		controlador.botonInscribirACarreraPresionado();
	};
	
	private void botonCursarMateriaPresionado() {
		controlador.botonCursarMateriaPresionado();
	};
	
}
