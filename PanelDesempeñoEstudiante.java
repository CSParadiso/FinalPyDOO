import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class PanelDesempeñoEstudiante extends JPanel {
	
	static DefaultTableModel modeloTablaEstudiantes, modeloTablaMaterias;
	static JTable tablaEstudiantes, tablaMaterias;
	JScrollPane panelListaEstudiantes, panelListaMaterias;
	static JSpinner spinnerNotaCursada, spinnerNotaFinal;
	static JButton botonRegistrarNota;
	ControladorMultiPestaña controlador;
	
	PanelDesempeñoEstudiante(){
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		String[] columnas = new String[] {"Legajo", "Documento", "Nombre", "Apellido", "Carrera"};
		modeloTablaEstudiantes = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int fila, int columna) { return false;}
		};
		modeloTablaEstudiantes.setColumnIdentifiers(columnas);
		tablaEstudiantes = new JTable(modeloTablaEstudiantes);
		tablaEstudiantes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		panelListaEstudiantes = new JScrollPane(tablaEstudiantes);
		
		JPanel panelBoxIzquierdo = new JPanel();
		panelBoxIzquierdo.setLayout(new BoxLayout(panelBoxIzquierdo, BoxLayout.Y_AXIS));
		panelBoxIzquierdo.add(panelListaEstudiantes);
		panelBoxIzquierdo.setPreferredSize(new Dimension(562, 2000));
		panelBoxIzquierdo.setMaximumSize(new Dimension(562, 2000));
		
		String [] columnasMaterias = new String[] {"Materia", "Promocionable", "Nota Cursada", "Nota Final"};
		modeloTablaMaterias = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int fila, int columna) {return false;};
		};
		modeloTablaMaterias.setColumnIdentifiers(columnasMaterias);
		tablaMaterias = new JTable(modeloTablaMaterias);
		tablaMaterias.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane panelListaMaterias = new JScrollPane(tablaMaterias);
		JPanel panelVerticalMateriasYNotas = new JPanel();
		panelVerticalMateriasYNotas.setLayout(new BoxLayout(panelVerticalMateriasYNotas, BoxLayout.X_AXIS));
		panelVerticalMateriasYNotas.add(panelListaMaterias);
		JLabel etiquetaNotaCursada = new JLabel("Nota Cursada: ");
		spinnerNotaCursada = new JSpinner(new SpinnerNumberModel(0, 0, 10, 1));
		((JSpinner.DefaultEditor) spinnerNotaCursada.getEditor()).getTextField().setEditable(false); 
		((JSpinner.DefaultEditor) spinnerNotaCursada.getEditor()).getTextField().setBackground(Color.white);
		spinnerNotaFinal = new JSpinner(new SpinnerNumberModel(0, 0, 10, 1));
		((JSpinner.DefaultEditor) spinnerNotaFinal.getEditor()).getTextField().setEditable(false); 
		((JSpinner.DefaultEditor) spinnerNotaFinal.getEditor()).getTextField().setBackground(Color.white);
		JLabel etiquetaNotaFinal = new JLabel("Nota Final: ");
		botonRegistrarNota = new JButton("Registrar Nota/s");
		JPanel panelSetearNotas = new JPanel();
		panelSetearNotas.setLayout(new BoxLayout(panelSetearNotas, BoxLayout.X_AXIS));
		panelSetearNotas.setMaximumSize(new Dimension(800, 20));
		panelSetearNotas.add(etiquetaNotaCursada);
		panelSetearNotas.add(spinnerNotaCursada);
		panelSetearNotas.add(etiquetaNotaFinal);
		panelSetearNotas.add(spinnerNotaFinal);
		panelSetearNotas.add(botonRegistrarNota);
		
		JPanel panelBoxDerecho = new JPanel();
		panelBoxDerecho.setLayout(new BoxLayout(panelBoxDerecho, BoxLayout.Y_AXIS));
		//panelBoxDerecho.add(new JTextField("Materias que pueden ser seteadas las notas"));
		panelBoxDerecho.add(panelVerticalMateriasYNotas);
		panelBoxDerecho.add(panelSetearNotas);
		
		add(panelBoxIzquierdo);
		add(panelBoxDerecho);
		
		// Inicialización del Controlador de esta Vista
		controlador = ControladorMultiPestaña.getInstancia(this);
		
		// Listeners propios de la vista
				
		// Listeners que disparan el controlador
		tablaEstudiantes.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				estudianteSeleccionado();
			}
		});
		
		tablaMaterias.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				materiaSeleccionada();
			};
		});
		
		botonRegistrarNota.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				botonRegistrarNotaPresionado();
			};
		});
	};
	
	// Métodos privados de la clase
	
	private void estudianteSeleccionado() {
		controlador.estudianteSeleccionado();
	};
	
	private void materiaSeleccionada() {
		controlador.materiaSeleccionada();
	};
	
	private void botonRegistrarNotaPresionado() {
		controlador.botonRegistrarNotaPresionado();
	};
	
}
