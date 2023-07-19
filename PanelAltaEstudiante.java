import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class PanelAltaEstudiante extends JPanel {
  static JTextField campoDocumento;
  JTextField campoNombre;
  JTextField campoApellido;
  JButton botonRegistrarEstudiante;
  JPanel panelDatosEstudiante, panelVisualizadorEstudiante;
  static DefaultTableModel modeloTablaEstudiantes;
  JTable tablaEstudiantes;
  ControladorMultiPestania controlador;

  PanelAltaEstudiante() {
    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

    panelDatosEstudiante = new JPanel();
    panelDatosEstudiante.setLayout(new BoxLayout(panelDatosEstudiante, BoxLayout.X_AXIS));
    JPanel panelDocumento = new JPanel();
    panelDocumento.setLayout(new BoxLayout(panelDocumento, BoxLayout.X_AXIS));
    ;
    panelDocumento.setBorder(BorderFactory.createTitledBorder("Documento"));
    campoDocumento = new JTextField(8);
    panelDocumento.add(campoDocumento);
    JPanel panelNombre = new JPanel();
    panelNombre.setLayout(new BoxLayout(panelNombre, BoxLayout.X_AXIS));
    ;
    panelNombre.setBorder(BorderFactory.createTitledBorder("Nombre"));
    campoNombre = new JTextField();
    panelNombre.add(campoNombre);
    // panelNombre.setMaximumSize(new Dimension(600, 60));
    JPanel panelApellido = new JPanel();
    panelApellido.setLayout(new BoxLayout(panelApellido, BoxLayout.X_AXIS));
    panelApellido.setBorder(BorderFactory.createTitledBorder("Apellido"));
    campoApellido = new JTextField();
    panelApellido.add(campoApellido);
    botonRegistrarEstudiante = new JButton("Registrar Estudiante");
    panelDatosEstudiante.add(panelDocumento);
    panelDatosEstudiante.add(panelNombre, Box.LEFT_ALIGNMENT);
    panelDatosEstudiante.add(Box.createHorizontalGlue());
    panelDatosEstudiante.add(panelApellido);
    panelDatosEstudiante.add(Box.createHorizontalGlue());
    panelDatosEstudiante.add(botonRegistrarEstudiante, Box.RIGHT_ALIGNMENT);
    panelDatosEstudiante.setMaximumSize(new Dimension(1400, 45));

    modeloTablaEstudiantes = new DefaultTableModel();
    String[] columnas = new String[] { "Legajo", "Documento", "Nombre", "Apellido" };
    modeloTablaEstudiantes.setColumnIdentifiers(columnas);
    tablaEstudiantes = new JTable(modeloTablaEstudiantes);
    tablaEstudiantes.setEnabled(false);
    JScrollPane panelScrollEstudiantes = new JScrollPane(tablaEstudiantes);
    panelVisualizadorEstudiante = new JPanel(); // Puede ser info del ultimo agregado y una lista de todos ellos o algo
                                                // asni
    panelVisualizadorEstudiante.setLayout(new BoxLayout(panelVisualizadorEstudiante, BoxLayout.Y_AXIS));
    panelVisualizadorEstudiante.setBorder(BorderFactory.createTitledBorder("Estudiantes registrados"));
    panelVisualizadorEstudiante.add(panelScrollEstudiantes);

    add(panelDatosEstudiante);
    add(panelVisualizadorEstudiante);

    // Inicializador del Controlador de esta Vista (Singleton que redirige)
    controlador = ControladorMultiPestania.getInstancia(this);
    // controlador.nuevoControlador(this);

    // Listeners propios de la Vista
    campoDocumento.addMouseListener(new MouseAdapter() {
      public void mouseClicked(MouseEvent e) {
        campoDocumento.setText("");
      };
    });

    campoNombre.addMouseListener(new MouseAdapter() {
      public void mouseClicked(MouseEvent e) {
        campoNombre.setText("");
      }
    });

    campoApellido.addMouseListener(new MouseAdapter() {
      public void mouseClicked(MouseEvent e) {
        campoApellido.setText("");
      }
    });

    // Listeners que disparan el Controlador de esta Vista
    botonRegistrarEstudiante.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        botonRegistrarEstudiantePresionado();
      }
    });
  };

  // Mnitodos privados de la clase

  private boolean validarCamposCorrectos() {
    return (!campoNombre.getText().equals("") &&
        !campoNombre.getText().equals("Ingrese nombre vnilido") &&
        !campoApellido.getText().equals("") &&
        !campoApellido.getText().equals("Ingrese apellido vnilido") &&
        !campoDocumento.getText().equals("") &&
        !campoDocumento.getText().equals("Documento ya registrado") &&
        !(campoDocumento.getText().length() < 7) &&
        !(campoDocumento.getText().length() > 8));
  };

  private void botonRegistrarEstudiantePresionado() {
    if (validarCamposCorrectos()) { // FALTA AGREGAR LA COMPROBACION DE LOS CORRECTOS CARACTERES DE DOCUMENTO
      controlador.botonRegistrarEstudiantePresionado(Integer.valueOf(campoDocumento.getText()),
          campoNombre.getText(), campoApellido.getText());
      // System.out.println("Ingreso valido");
    } else {
      campoDocumento.setText("Ingrese documento vnilido");
      campoNombre.setText("Ingrese nombre vnilido");
      campoApellido.setText("Ingrese apellido vnilido");
      // System.out.println("Ingreso invalido");
    }
  };
}
