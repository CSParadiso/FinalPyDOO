import java.util.*;

public class ControladorPanelConsultaEInscripciones {
  Estudiante estudiante;

  ControladorPanelConsultaEInscripciones() {
    actualizarListaEstudiantes();
    actualizarListaCarreras();
  };

  void actualizarListaEstudiantes() {
    ArrayList<Estudiante> estudiantes = Facultad.getEstudiantes();
    Iterator<Estudiante> iterador = estudiantes.iterator();
    PanelConsultaEInscripcion.modeloTablaEstudiantes.setRowCount(0); // Limpia la tabla de ítems
    while (iterador.hasNext()) {
      estudiante = iterador.next();
      PanelConsultaEInscripcion.modeloTablaEstudiantes
          .addRow(new String[] { ((Integer) estudiante.getLegajo()).toString(),
              ((Integer) estudiante.getDocumento()).toString(), estudiante.getNombre(), estudiante.getApellido() });
    }
    ;

  }

  void actualizarListaCarreras() {
    PanelConsultaEInscripcion.modeloListaCarreras.setList(Facultad.getNombresCarreras());
  };

  void visualizarDatosEstudiante() {
    PanelConsultaEInscripcion.campoNombre.setText(buscarEstudiante() == null ? "" : buscarEstudiante().getNombre());
    PanelConsultaEInscripcion.campoApellido.setText(buscarEstudiante() == null ? "" : buscarEstudiante().getApellido());
    PanelConsultaEInscripcion.campoLegajo
        .setText(buscarEstudiante() == null ? "" : ((Integer) buscarEstudiante().getLegajo()).toString());
    PanelConsultaEInscripcion.campoRecibido
        .setText(buscarEstudiante() == null ? "" : buscarEstudiante().getCarrerasRecibido().toString());
    PanelConsultaEInscripcion.campoCursando
        .setText(buscarEstudiante() == null ? "" : buscarEstudiante().getCarreras().toString());
    PanelConsultaEInscripcion.campoCursandoMateria
        .setText(buscarEstudiante() == null ? "" : buscarEstudiante().getCursando().toString());
    if (buscarEstudiante() != null)
      visualizarMateriasDisponibles(); // solo las que puede cursar
  };

  private Estudiante buscarEstudiante() {
    try {
      Object valorLegajo = PanelConsultaEInscripcion.tablaEstudiantes
          .getValueAt(PanelConsultaEInscripcion.tablaEstudiantes.getSelectedRow(), 0);
      int legajo = Integer.parseInt(valorLegajo.toString());
      return Facultad.buscarEstudiante(legajo);
    } catch (ArrayIndexOutOfBoundsException e) {
      return null;
    }
  };

  void visualizarMateriasDisponibles() {
    Estudiante estudiante = buscarEstudiante();
    Carrera carrera;
    PanelConsultaEInscripcion.modeloListaMaterias.clear();
    if (estudiante != null) {
      Iterator<Carrera> iteradorCarreras = estudiante.getCarreras().iterator();
      while (iteradorCarreras.hasNext()) {
        carrera = iteradorCarreras.next();
        Iterator<String> iteradorMaterias = carrera.getNombresMaterias().iterator();
        while (iteradorMaterias.hasNext()) {
          Materia materia = carrera.buscarMateria(iteradorMaterias.next());
          if (carrera.puedeCursar(materia, estudiante) &&
              !estudiante.getMaterias(estudiante.getMateriasAprobadas()).contains(materia) &&
              !estudiante.getMaterias(estudiante.getCursadasAprobadas()).contains(materia) &&
              !estudiante.getMaterias(estudiante.getCursando()).contains(materia))
            PanelConsultaEInscripcion.modeloListaMaterias.addElement(materia.getNombre());
        }
      }
    }
  };

  void botonInscribirACarreraPresionado() {
    Carrera carrera = Facultad.buscarCarrera((String) PanelConsultaEInscripcion.modeloListaCarreras.getValue());
    Estudiante estudiante = buscarEstudiante();
    if (carrera != null && estudiante != null) {
      if (!estudiante.getCarreras().contains(carrera)) {
        estudiante.agregarCarrera(carrera);
        visualizarDatosEstudiante();
      }
    }
  };

  void botonCursarMateriaPresionado() { // verificar que pasa en las iteraciones o encapsular para mayor detalle
    Estudiante estudiante = buscarEstudiante();
    List<Carrera> carreras = Facultad.getCarreras();
    List<String> materiasSeleccionadas = PanelConsultaEInscripcion.listaMaterias.getSelectedValuesList();
    if (materiasSeleccionadas != null && carreras != null && estudiante != null) {
      Iterator<Carrera> iteradorCarreras = carreras.iterator();
      while (iteradorCarreras.hasNext()) {
        Carrera carrera = iteradorCarreras.next();
        // System.out.println(carrera.getNombre());
        Iterator<String> iteradorMaterias = materiasSeleccionadas.iterator();
        while (iteradorMaterias.hasNext()) {
          Materia materia = carrera.buscarMateria(iteradorMaterias.next());
          if (materia != null && !estudiante.getMaterias(estudiante.getCursadasAprobadas()).contains(materia) &&
              !estudiante.getMaterias(estudiante.getCursando()).contains(materia)) {
            carrera.cursarMateria(materia, estudiante);
          }
          // else
          // System.out.println("Materia ya cursada o aprobada");
        }
      }
      visualizarMateriasDisponibles();
      visualizarDatosEstudiante();
    }
  };

}
