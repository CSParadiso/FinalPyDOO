import java.awt.*;
import javax.swing.*;

public class Ventana extends JFrame {
  JTabbedPane tabs;
  Container contenedor;

  Ventana() {
    // setLocation(0, 0);
    setMinimumSize(new Dimension(1200, 700));
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setTitle("SADEYC - Sistema Administrador de Estudiantes y Carreras");
    setVisible(true);

    tabs = new JTabbedPane();
    contenedor = getContentPane();

    contenedor.add(tabs);
    tabs.add("Alta Carrera/Materia", new PanelAltaCarrera());
    tabs.add("Alta Estudiante", new PanelAltaEstudiante());
    tabs.add("Consultas e Inscripciones", new PanelConsultaEInscripcion()); // Este panel puede servir para los otros
                                                                            // requerimientos
    tabs.add("Desempenio Estudiante", new PanelDesempenioEstudiante());
  }

}
