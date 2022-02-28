import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
//import javax.swing.border.*;
import javax.swing.event.ChangeListener;

public class PanelAltaCarrera extends JPanel{
	static JTextField campoNombre, campoNombreMateriaNueva, campoInfoNombre, campoInfoCarrera, campoInfoPromocionable, campoInfoOpcional,
	campoInfoCuatrimestre, campoInfoNotaPromocionar, campoInfoNotaAprobarCursada, campoInfoNotaAprobarFinal, campoInfoCorrelativas, campoInfoPlan;
	JButton botonCrearCarrera, botonCrearMateria, botonAgregarCorrelativas, botonRemoverCorrelativas;
	JPanel panelNombre, panelPlanes, panelOpcionalesMinimas, panelBoxSuperior, panelInferiorIzquierdo, panelInferiorDerecho,
	panelBoxInferior, panelCarreras, panelMaterias, panelCuatrimestreMateria, panelNotasMateria, panelBotonesCorrelativas, 
	panelCorrelativas, panelNombreMateriaNueva, panelMateriaNueva, panelInfoMateria;
	JRadioButton botonPlanA, botonPlanB, botonPlanC, botonPlanD, botonPlanE;
	JCheckBox botonOpcional,botonPromocionable;
	static SpinnerListModel spinnerListaCarreras;
	static SpinnerListModel spinnerListaMaterias;
	static DefaultListModel<String> modeloListaCorrelativas;
	JList<String> listaCorrelativas;
	JSpinner spinnerSeleccionarCarrera, spinnerSeleccionarMateria, spinnerNroMateriasOpcionales, spinnerNotaPromocionar, 
	spinnerCuatrimestreMateria,	spinnerNotaCursadaMateria, spinnerNotaFinalMateria;
	ButtonGroup grupoBotones;
	ControladorMultiPestaña controlador; // Singleton, redirecciona a los tres distintos Controladores (uno por cada JTabbed Pane)
	
	PanelAltaCarrera(){
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		// Panel Nombre 
		campoNombre = new JTextField(25);
		panelNombre = new JPanel();
		panelNombre.setLayout(new BoxLayout(panelNombre, BoxLayout.X_AXIS));
		panelNombre.add(campoNombre);
		panelNombre.setBorder(BorderFactory.createTitledBorder("Nombre"));
				
		// Panel Planes
		botonPlanA = new JRadioButton("Plan A", true);
		botonPlanB = new JRadioButton("Plan B");
		botonPlanC = new JRadioButton("Plan C");
		botonPlanD = new JRadioButton("Plan D");
		botonPlanE = new JRadioButton("Plan E");
		botonCrearCarrera = new JButton("Crear Carrera");
		
		grupoBotones = new ButtonGroup();
		
		// Los botones forman parte de un grupo del cual solo se puede seleccionar uno
		grupoBotones.add(botonPlanA);
		grupoBotones.add(botonPlanB);
		grupoBotones.add(botonPlanC);
		grupoBotones.add(botonPlanD);
		grupoBotones.add(botonPlanE);
		
		panelPlanes = new JPanel();
		panelPlanes.setLayout(new BoxLayout(panelPlanes, BoxLayout.X_AXIS));
		panelPlanes.setBorder(BorderFactory.createTitledBorder("Plan"));
		panelPlanes.add(botonPlanA);
		panelPlanes.add(botonPlanB);
		panelPlanes.add(botonPlanC);
		panelPlanes.add(botonPlanD);
		panelPlanes.add(botonPlanE);
		
		// Panel Materias Opcionales Minimas
		spinnerNroMateriasOpcionales = new JSpinner(new SpinnerNumberModel(0, 0, 8, 1));
		((JSpinner.DefaultEditor) spinnerNroMateriasOpcionales.getEditor()).getTextField().setEditable(false); 
		((JSpinner.DefaultEditor) spinnerNroMateriasOpcionales.getEditor()).getTextField().setBackground(Color.white);
		panelOpcionalesMinimas = new JPanel();
		panelOpcionalesMinimas.setLayout(new BoxLayout(panelOpcionalesMinimas, BoxLayout.X_AXIS));
		panelOpcionalesMinimas.setBorder(BorderFactory.createTitledBorder("Materias opcionales minimas"));
		panelOpcionalesMinimas.add(Box.createRigidArea(new Dimension(150, 0)));
		panelOpcionalesMinimas.add(spinnerNroMateriasOpcionales);
			
		// Panel BoxSuperior
		panelBoxSuperior = new JPanel();
		panelBoxSuperior.setLayout(new BoxLayout(panelBoxSuperior, BoxLayout.X_AXIS));
		panelBoxSuperior.add(panelNombre, Box.LEFT_ALIGNMENT);
		panelBoxSuperior.add(Box.createHorizontalGlue());
		panelBoxSuperior.add(panelPlanes);
		panelBoxSuperior.add(Box.createHorizontalGlue());
		panelBoxSuperior.add(panelOpcionalesMinimas);
		panelBoxSuperior.add(Box.createHorizontalGlue());
		panelBoxSuperior.add(botonCrearCarrera, Box.RIGHT_ALIGNMENT);
		panelBoxSuperior.setMaximumSize(new Dimension(1400, 60));
				
		// Panel Box Inferior (Panel Inferior Izquierdo y Panel Inferior Derecho)
		spinnerListaCarreras = new SpinnerListModel(); // El contenido lo setea el controlador, un ArrayList de carreras
		spinnerSeleccionarCarrera = new JSpinner(spinnerListaCarreras);
		((JSpinner.DefaultEditor) spinnerSeleccionarCarrera.getEditor()).getTextField().setEditable(false); 
		((JSpinner.DefaultEditor) spinnerSeleccionarCarrera.getEditor()).getTextField().setBackground(Color.white);
		panelCarreras = new JPanel();
		panelCarreras.setLayout(new BoxLayout(panelCarreras, BoxLayout.X_AXIS));
		panelCarreras.setBorder(BorderFactory.createTitledBorder("Seleccionar Carrera"));
		panelCarreras.add(spinnerSeleccionarCarrera, Box.LEFT_ALIGNMENT);
		
		panelMateriaNueva = new JPanel();
		panelMateriaNueva.setLayout(new BoxLayout(panelMateriaNueva, BoxLayout.Y_AXIS));
		panelMateriaNueva.setBorder(BorderFactory.createTitledBorder("Crear Materia (de ser necesario)"));
		
		botonPromocionable = new JCheckBox("Promocionable");
		botonCrearMateria = new JButton("Crear Materia");
		panelNombreMateriaNueva = new JPanel();
		panelNombreMateriaNueva.setLayout(new BoxLayout(panelNombreMateriaNueva, BoxLayout.X_AXIS));
		campoNombreMateriaNueva = new JTextField("Nombre de la materia", 25);
		campoNombreMateriaNueva.setMaximumSize(new Dimension(400, 30));
		panelNombreMateriaNueva.add(campoNombreMateriaNueva);
		panelNombreMateriaNueva.add(botonCrearMateria);
		
		panelCuatrimestreMateria = new JPanel();
		panelCuatrimestreMateria.setLayout(new BoxLayout(panelCuatrimestreMateria, BoxLayout.X_AXIS));
		botonOpcional = new JCheckBox("Opcional");
		spinnerNotaPromocionar = new JSpinner(new SpinnerNumberModel(4, 4, 10, 1));
		((JSpinner.DefaultEditor) spinnerNotaPromocionar.getEditor()).getTextField().setEditable(false); 
		((JSpinner.DefaultEditor) spinnerNotaPromocionar.getEditor()).getTextField().setBackground(Color.white);
		spinnerCuatrimestreMateria = new JSpinner(new SpinnerNumberModel(1, 1, 12, 1));
		((JSpinner.DefaultEditor) spinnerCuatrimestreMateria.getEditor()).getTextField().setEditable(false); 
		((JSpinner.DefaultEditor) spinnerCuatrimestreMateria.getEditor()).getTextField().setBackground(Color.white);
		panelCuatrimestreMateria.add(botonPromocionable);
		panelCuatrimestreMateria.add(Box.createHorizontalGlue());
		panelCuatrimestreMateria.add(botonOpcional);
		panelCuatrimestreMateria.add(Box.createHorizontalGlue());
		panelCuatrimestreMateria.add(Box.createHorizontalGlue());
		panelCuatrimestreMateria.add(new JLabel("Cuatrimestre nro: "));
		panelCuatrimestreMateria.add(spinnerCuatrimestreMateria);
		
		panelNotasMateria = new JPanel();
		panelNotasMateria.setLayout(new BoxLayout(panelNotasMateria, BoxLayout.X_AXIS));
		spinnerNotaCursadaMateria = new JSpinner(new SpinnerNumberModel(4, 4, 10, 1));
		((JSpinner.DefaultEditor) spinnerNotaCursadaMateria.getEditor()).getTextField().setEditable(false); 
		((JSpinner.DefaultEditor) spinnerNotaCursadaMateria.getEditor()).getTextField().setBackground(Color.white);
		spinnerNotaFinalMateria = new JSpinner(new SpinnerNumberModel(4, 4, 10, 1));
		((JSpinner.DefaultEditor) spinnerNotaFinalMateria.getEditor()).getTextField().setEditable(false); 
		((JSpinner.DefaultEditor) spinnerNotaFinalMateria.getEditor()).getTextField().setBackground(Color.white);
		panelNotasMateria.add(new JLabel("Aprobar cursada con: "));
		panelNotasMateria.add(spinnerNotaCursadaMateria);
		panelNotasMateria.add(Box.createHorizontalGlue());
		panelNotasMateria.add(new JLabel("Aprobar final con: "));
		panelNotasMateria.add(spinnerNotaFinalMateria);
		
		panelMateriaNueva.add(panelNombreMateriaNueva);
		panelMateriaNueva.add(Box.createVerticalGlue());
		panelMateriaNueva.add(panelCuatrimestreMateria);
		panelMateriaNueva.add(panelNotasMateria);
		
		spinnerListaMaterias = new SpinnerListModel(); // se setea el contenido en el controlador, un ArrayList de Materias 
		spinnerSeleccionarMateria = new JSpinner(spinnerListaMaterias);
		((JSpinner.DefaultEditor) spinnerSeleccionarMateria.getEditor()).getTextField().setEditable(false); 
		((JSpinner.DefaultEditor) spinnerSeleccionarMateria.getEditor()).getTextField().setBackground(Color.white);
		spinnerCuatrimestreMateria = new JSpinner(new SpinnerNumberModel(1, 1, 12, 1));
		panelMaterias = new JPanel();
		panelMaterias.setLayout(new BoxLayout(panelMaterias, BoxLayout.Y_AXIS));
		panelMaterias.add(spinnerSeleccionarMateria);
				
		panelCorrelativas = new JPanel();
		panelCorrelativas.setLayout(new BoxLayout(panelCorrelativas, BoxLayout.Y_AXIS));
		panelCorrelativas.setBorder(BorderFactory.createTitledBorder("Agregar Correlativas (CTRL + click para deseleccionar)"));
		modeloListaCorrelativas = new DefaultListModel<String>();
		listaCorrelativas = new JList<String>(modeloListaCorrelativas);
		listaCorrelativas.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		listaCorrelativas.setVisibleRowCount(20);
		listaCorrelativas.setLayoutOrientation(JList.VERTICAL);
		JScrollPane panelScrollCorrelativas = new JScrollPane(listaCorrelativas);
		
		panelBotonesCorrelativas = new JPanel();
		panelBotonesCorrelativas.setLayout(new BoxLayout(panelBotonesCorrelativas, BoxLayout.X_AXIS));
		botonAgregarCorrelativas = new JButton("Agregar Correlativas");
		botonRemoverCorrelativas = new JButton("Remover Correlativas");
		panelBotonesCorrelativas.add(botonAgregarCorrelativas);
		panelBotonesCorrelativas.add(Box.createHorizontalGlue());
		panelBotonesCorrelativas.add(botonRemoverCorrelativas);
		
		panelCorrelativas.add(panelMaterias);
		panelCorrelativas.add(panelScrollCorrelativas);
		panelCorrelativas.add(panelBotonesCorrelativas);
		
		panelInferiorIzquierdo = new JPanel();
		panelInferiorIzquierdo.setLayout(new BoxLayout(panelInferiorIzquierdo, BoxLayout.Y_AXIS));
		panelInferiorIzquierdo.add(panelCarreras);
		panelInferiorIzquierdo.add(Box.createVerticalGlue());///////////// UTIMO
		panelInferiorIzquierdo.add(panelMateriaNueva);
		panelInferiorIzquierdo.add(Box.createVerticalGlue());///////////// UTIMO
		panelInferiorIzquierdo.add(panelCorrelativas);
		
		JPanel panelInfo1, panelInfo2, panelInfo3, panelInfo4;
		campoInfoNombre = new JTextField();
		campoInfoNombre.setEditable(false);
		campoInfoNombre.setBackground(Color.white);
		campoInfoCarrera = new JTextField();
		campoInfoCarrera.setEditable(false);
		campoInfoCarrera.setBackground(Color.white);
		panelInfo1 = new JPanel();
		panelInfo1.setLayout(new BoxLayout(panelInfo1, BoxLayout.X_AXIS));
		panelInfo1.add(new JLabel("Nombre: "));
		panelInfo1.add(campoInfoNombre);
		panelInfo1.add(Box.createHorizontalGlue());
		panelInfo1.add(new JLabel("Carrera: "));
		panelInfo1.add(campoInfoCarrera);
		campoInfoPromocionable = new JTextField();
		campoInfoPromocionable.setEditable(false);
		campoInfoPromocionable.setBackground(Color.white);
		campoInfoOpcional = new JTextField();
		campoInfoOpcional.setEditable(false);
		campoInfoOpcional.setBackground(Color.white);
		campoInfoCuatrimestre = new JTextField();
		campoInfoCuatrimestre.setEditable(false);
		campoInfoCuatrimestre.setBackground(Color.white);
		panelInfo2 = new JPanel();
		panelInfo2.setLayout(new BoxLayout(panelInfo2, BoxLayout.X_AXIS));
		panelInfo2.add(new JLabel("Promocionable: "));
		panelInfo2.add(campoInfoPromocionable);
		panelInfo2.add(new JLabel("Opcional: "));
		panelInfo2.add(campoInfoOpcional);
		panelInfo2.add(new JLabel("Cuatrimestre: "));
		panelInfo2.add(campoInfoCuatrimestre);
		panelInfo3 = new JPanel();
		panelInfo3.setLayout(new BoxLayout(panelInfo3, BoxLayout.X_AXIS));
		campoInfoNotaPromocionar = new JTextField();
		campoInfoNotaPromocionar.setEditable(false);
		campoInfoNotaPromocionar.setBackground(Color.white);
		campoInfoNotaAprobarCursada = new JTextField();
		campoInfoNotaAprobarCursada.setEditable(false);
		campoInfoNotaAprobarCursada.setBackground(Color.white);
		campoInfoNotaAprobarFinal = new JTextField();
		campoInfoNotaAprobarFinal.setEditable(false);
		campoInfoNotaAprobarFinal.setBackground(Color.white);
		panelInfo3 = new JPanel();
		panelInfo3.setLayout(new BoxLayout(panelInfo3, BoxLayout.X_AXIS));
		panelInfo3.add(new JLabel("Nota para Promocionar: "));
		panelInfo3.add(campoInfoNotaPromocionar);
		panelInfo3.add(new JLabel("Nota para aprobar Cursada: "));
		panelInfo3.add(campoInfoNotaAprobarCursada);
		panelInfo3.add(new JLabel("Nota para aprobar Final: "));
		panelInfo3.add(campoInfoNotaAprobarFinal);
		campoInfoCorrelativas = new JTextField();
		campoInfoCorrelativas.setEditable(false);
		campoInfoCorrelativas.setBackground(Color.white);
		panelInfo4 = new JPanel();
		panelInfo4.setLayout(new BoxLayout(panelInfo4, BoxLayout.X_AXIS));
		panelInfo4.add(new JLabel("Correlativas: "));
		panelInfo4.add(campoInfoCorrelativas);
		panelInfoMateria = new JPanel();
		panelInfoMateria.setLayout(new BoxLayout(panelInfoMateria, BoxLayout.Y_AXIS));
		panelInfoMateria.setBorder(BorderFactory.createTitledBorder("Info Materia"));
		panelInfoMateria.add(panelInfo1);
		panelInfoMateria.add(panelInfo2);
		panelInfoMateria.add(panelInfo3);
		panelInfoMateria.add(panelInfo4);
		panelInfoMateria.setMaximumSize(new Dimension(1500, 100));
						
		panelInferiorDerecho = new JPanel();
		panelInferiorDerecho.setLayout(new BoxLayout(panelInferiorDerecho, BoxLayout.Y_AXIS));
		campoInfoPlan = new JTextField("CARACTERÍSTICAS DEL PLAN DE ESTUDIO", 46);
		campoInfoPlan.setEditable(false);
		campoInfoPlan.setBackground(Color.white);
		campoInfoPlan.setFont(new Font("Arial", Font.ITALIC, 12));
		campoInfoPlan.setMaximumSize(new Dimension(1000, 20));
		panelInferiorDerecho.add(campoInfoPlan);
		panelInferiorDerecho.add(Box.createVerticalGlue());
		panelInferiorDerecho.add(panelInfoMateria);
		panelInferiorDerecho.add(Box.createVerticalGlue());
		JTextField campoEspacio = new JTextField("");
		campoEspacio.setEnabled(false);
		campoEspacio.setBackground(this.getBackground()); // Acá iría un arbol que va mostrando las carreras, sus materias y correlativas
		panelInferiorDerecho.add(campoEspacio);
				
		panelBoxInferior = new JPanel();
		panelBoxInferior.setLayout(new BoxLayout(panelBoxInferior, BoxLayout.X_AXIS));
		panelBoxInferior.add(panelInferiorIzquierdo);
		panelBoxInferior.add(panelInferiorDerecho);
		panelBoxInferior.setBorder(BorderFactory.createTitledBorder("ESTABLECER CURRICULA CARRERA"));
		
		// Panel Principal Alta Carrera
		add(panelBoxSuperior);
		add(panelBoxInferior);
		
		// INICIALIZADOR DE UNA INSTANCIA DEL CONTROLADOR (singleton) 
		controlador = ControladorMultiPestaña.getInstancia(this);
		//controlador.nuevoControlador(this);
		
		// LISTENERS PROPIOS DE LA VISTA
		campoNombre.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent m) {
				campoNombre.setText("");
			}
		});
		
		campoNombreMateriaNueva.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				campoNombreMateriaNueva.setText("");
			}
		});
		
		// Listeners que disparan el Controlador
		botonCrearCarrera.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				botonCrearCarreraPresionado();
			}
		});
		

		botonPlanA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				visualizarInfoPlan(determinarJRadioButtonPresionado());
			}
		});
		botonPlanB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				visualizarInfoPlan(determinarJRadioButtonPresionado());
			}
		});

		botonPlanC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				visualizarInfoPlan(determinarJRadioButtonPresionado());
			}
		});
		
		botonPlanD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				visualizarInfoPlan(determinarJRadioButtonPresionado());
			}
		});
		
		botonPlanE.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				visualizarInfoPlan(determinarJRadioButtonPresionado());
			}
		});
		
		botonPromocionable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (botonPromocionable.isSelected()) {
					panelCuatrimestreMateria.add(spinnerNotaPromocionar, 2);
					botonPromocionable.setText("Promocionable con: ");
				}
				else
				{
					panelCuatrimestreMateria.remove(spinnerNotaPromocionar);
					botonPromocionable.setText("Promocionable");
				}
			}
		});
		
		botonCrearMateria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				botonCrearMateriaPresionado();
			}
		});
		
		spinnerSeleccionarCarrera.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent c) {
				spinnerSeleccionarCarreraRecorrido();
			}
		});
		
		spinnerSeleccionarMateria.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent c) {
				spinnerSeleccionarMateriaRecorrido();
			}
		});
		
		botonAgregarCorrelativas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				botonAgregarCorrelativasPresionado();
			}
		});
		
		botonRemoverCorrelativas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				botonRemoverCorrelativasPresionado();
			}
		});
		
	}
		// Métodos privados de la clase
		private String determinarJRadioButtonPresionado() {
			// Esto hay que hacerlo menos rústico
			if (botonPlanA.isSelected())
				return botonPlanA.getText();
			else if (botonPlanB.isSelected())
				return botonPlanB.getText();
			else if (botonPlanC.isSelected())
				return botonPlanC.getText();
			else if (botonPlanD.isSelected())
				return botonPlanD.getText();
			else if (botonPlanE.isSelected())
				return botonPlanE.getText();
			else 
				return "";
				
		};
		
			
		// Métodos que invocan los listeners y luego se redirigen al controlador
		private void visualizarInfoPlan(String plan) {
			controlador.visualizarInfoPlan(plan);
		};
		
		private void botonCrearCarreraPresionado() {
			String nombre = campoNombre.getText();
			String botonPlan = determinarJRadioButtonPresionado();
			int cantidadOpcionales = (Integer)spinnerNroMateriasOpcionales.getValue();
			if (nombre.length() != 0)
				controlador.botonCrearCarreraPresionado(nombre, botonPlan, cantidadOpcionales);
			else
				campoNombre.setText("¡Debe ingresar un nombre para crear una carrera!");
		};
		
		private void botonCrearMateriaPresionado() {
			String carrera = (String)spinnerSeleccionarCarrera.getValue();
			String nombre = campoNombreMateriaNueva.getText();
			int cuatrimestre = (Integer)spinnerCuatrimestreMateria.getValue();
			int aprobarCursada = (Integer)spinnerNotaCursadaMateria.getValue();
			int aprobarFinal = (Integer)spinnerNotaFinalMateria.getValue();
			boolean opcional = botonOpcional.isSelected();
			boolean promocionable = botonPromocionable.isSelected();
			int promocionar = -1; // Si no es promocionable se establece en negativo
			if (promocionable)
				promocionar = (Integer)spinnerNotaPromocionar.getValue();
			if (nombre.length() != 0 && carrera != "Seleccione una carrera"  &&
					!nombre.equals("El nombre de la materia ya existe")  &&
					!nombre.equals("¡Debe ingresar un nombre para crear una carrera!") &&
					!nombre.equals("Nombre de la materia") && 
					!nombre.equals("¡Seleccionar Carrera y nombre de Materia!") &&
					!nombre.equals("¡No se pueden definir más materias opcionales!"))
				controlador.botonCrearMateriaPresionado(carrera, nombre, cuatrimestre, promocionar,
						aprobarCursada, aprobarFinal, opcional, promocionable);
			else
				campoNombreMateriaNueva.setText("¡Seleccionar Carrera y nombre de Materia!");
		};
		
		private void spinnerSeleccionarCarreraRecorrido() {
			controlador.spinnerSeleccionarCarreraRecorrido((String) spinnerSeleccionarCarrera.getValue());
		};
		
		private void spinnerSeleccionarMateriaRecorrido() {
			controlador.spinnerSeleccionarMateriaRecorrido((String)spinnerSeleccionarMateria.getValue());
		};
		
		private void botonAgregarCorrelativasPresionado(){
			controlador.botonAgregarCorrelativasPresionado(listaCorrelativas.getSelectedValuesList());
		};
		
		private void botonRemoverCorrelativasPresionado() {
			controlador.botonRemoverCorrelativasPresionado(listaCorrelativas.getSelectedValuesList());
		};
		
}
