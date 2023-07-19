import java.util.ArrayList;
import java.util.Iterator;

public class ControladorPanelDesempenioEstudiante {

  Estudiante estudiante;

  ControladorPanelDesempenioEstudiante() {
    actualizarListaEstudiantes();
  };

  void actualizarListaEstudiantes() {
    ArrayList<Estudiante> estudiantes = Facultad.getEstudiantes();
    Iterator<Estudiante> iterador = estudiantes.iterator();
    PanelDesempenioEstudiante.modeloTablaEstudiantes.setRowCount(0); // Limpia la tabla de nitems
    while (iterador.hasNext()) {
      estudiante = iterador.next();
      if (estudiante.getCarreras().size() != 0) {
        Iterator<Carrera> carreras = estudiante.getCarreras().iterator();
        while (carreras.hasNext())
          PanelDesempenioEstudiante.modeloTablaEstudiantes
              .addRow(new String[] { ((Integer) estudiante.getLegajo()).toString(),
                  ((Integer) estudiante.getDocumento()).toString(), estudiante.getNombre(),
                  estudiante.getApellido(), carreras.next().getNombre() });

      }
      ;
    }
    actualizarListaMaterias();
  }

  void actualizarListaMaterias() {
    PanelDesempenioEstudiante.modeloTablaMaterias.setRowCount(0);
    Estudiante estudiante = buscarEstudiante();
    Carrera carrera = buscarCarrera();
    if (estudiante != null && carrera != null) {
      mostrarHistoriaAcademicaTerminada(estudiante, carrera);
      mostrarHistoriaAcademicaEnProgreso(estudiante, carrera);
    }
    habilitarSeteoDeNotas();
  };

  private Estudiante buscarEstudiante() {
    try {
      Object valorLegajo = PanelDesempenioEstudiante.tablaEstudiantes
          .getValueAt(PanelDesempenioEstudiante.tablaEstudiantes.getSelectedRow(), 0);
      // System.out.println("ValorLegajo = " + valorLegajo);
      int legajo = Integer.parseInt(valorLegajo.toString());
      return Facultad.buscarEstudiante(legajo);
    } catch (ArrayIndexOutOfBoundsException e) {
      return null;
    }
  };

  private Carrera buscarCarrera() {
    try {
      Object valorCarrera = PanelDesempenioEstudiante.tablaEstudiantes.getValueAt(
          PanelDesempenioEstudiante.tablaEstudiantes.getSelectedRow(), 4);
      // System.out.println("Carrera: " + valorCarrera);
      return Facultad.buscarCarrera((String) valorCarrera);
    } catch (ArrayIndexOutOfBoundsException e) {
      return null;
    }
  };

  private Materia buscarMateria(Carrera carrera) {
    if (carrera != null) {
      try {
        Object valorMateria = PanelDesempenioEstudiante.tablaMaterias.getValueAt(
            PanelDesempenioEstudiante.tablaMaterias.getSelectedRow(), 0);
        // System.out.println("Materia: " + valorMateria);
        return carrera.buscarMateria((String) valorMateria);
      } catch (ArrayIndexOutOfBoundsException e) {
        return null;
      }
    } else
      return null;
  };

  private void mostrarHistoriaAcademicaTerminada(Estudiante estudiante, Carrera carrera) {
    Iterator<Cursada> iterador = estudiante.getMateriasAprobadas().iterator();
    while (iterador.hasNext()) {
      Cursada cursada = iterador.next();
      if (cursada.getMateria().getCarrera() == carrera) {
        String nombreCursada = cursada.getMateria().getNombre();
        String promocionable = cursada.getMateria().esPromocionable() ? "Si" : "No";
        String notaCursada = ((Integer) cursada.getNotaCursada()).toString();
        String notaFinal = ((Integer) cursada.getNotaFinal()).toString();
        PanelDesempenioEstudiante.modeloTablaMaterias
            .addRow(new String[] { nombreCursada, promocionable, notaCursada, notaFinal });
      }
    }
    Iterator<Cursada> iteradorDesaprobadas = estudiante.getCursadasDesaprobadas().iterator();
    while (iteradorDesaprobadas.hasNext()) {
      Cursada cursada = iteradorDesaprobadas.next();
      if (cursada.getMateria().getCarrera() == carrera) {
        String nombreCursada = cursada.getMateria().getNombre();
        String promocionable = cursada.getMateria().esPromocionable() ? "Si" : "No";
        String notaCursada = ((Integer) cursada.getNotaCursada()).toString();
        PanelDesempenioEstudiante.modeloTablaMaterias
            .addRow(new String[] { nombreCursada, promocionable, notaCursada, "Cursada no Aprobada" });
      }
    }
  }

  private void mostrarHistoriaAcademicaEnProgreso(Estudiante estudiante, Carrera carrera) {
    Iterator<Cursada> iteradorAprobadas = estudiante.getCursadasAprobadas().iterator();
    Cursada cursada;
    while (iteradorAprobadas.hasNext()) {
      cursada = iteradorAprobadas.next();
      if (cursada.getMateria().getCarrera() == carrera) {
        PanelDesempenioEstudiante.modeloTablaMaterias.addRow(new String[] { cursada.getMateria().getNombre(),
            cursada.getMateria().esPromocionable() ? "Si" : "No",
            ((Integer) cursada.getNotaCursada()).toString(), " - " });
      }
    }
    Iterator<Cursada> iteradorCursando = estudiante.getCursando().iterator();
    while (iteradorCursando.hasNext()) {
      cursada = iteradorCursando.next();
      if (cursada.getMateria().getCarrera() == carrera) {
        PanelDesempenioEstudiante.modeloTablaMaterias.addRow(new String[] { cursada.getMateria().getNombre(),
            cursada.getMateria().esPromocionable() ? "Si" : "No", " - ", " - " });
      }
    }
  };

  void habilitarSeteoDeNotas() { // Si una materia desaprobni la cursada y se recursa, se habilita
    Carrera carrera = buscarCarrera();
    Estudiante estudiante = buscarEstudiante();
    Materia materia = buscarMateria(carrera);
    Cursada cursada = null;
    if (materia != null)
      cursada = identificarCursada(materia, estudiante);
    if (carrera != null && estudiante != null && materia != null && cursada != null) {
      if (estudiante.getCursando().contains(cursada)) {
        // System.out.println(estudiante.getCursando().toString());
        PanelDesempenioEstudiante.spinnerNotaCursada.setEnabled(true);
        PanelDesempenioEstudiante.spinnerNotaFinal.setEnabled(true);
        PanelDesempenioEstudiante.botonRegistrarNota.setEnabled(true);
      } else if (estudiante.getCursadasAprobadas().contains(cursada)) {
        PanelDesempenioEstudiante.spinnerNotaCursada.setEnabled(false);
        PanelDesempenioEstudiante.spinnerNotaFinal.setEnabled(true);
        PanelDesempenioEstudiante.botonRegistrarNota.setEnabled(true);
      } else // if (estudiante.getMateriasAprobadas().contains(cursada))
      {
        PanelDesempenioEstudiante.spinnerNotaCursada.setEnabled(false);
        PanelDesempenioEstudiante.spinnerNotaFinal.setEnabled(false);
        PanelDesempenioEstudiante.botonRegistrarNota.setEnabled(false);
      }
    } else {
      PanelDesempenioEstudiante.spinnerNotaCursada.setEnabled(false);
      PanelDesempenioEstudiante.spinnerNotaFinal.setEnabled(false);
      PanelDesempenioEstudiante.botonRegistrarNota.setEnabled(false);
    }
  };

  void botonRegistrarNotaPresionado() {
    int notaCursada = (int) PanelDesempenioEstudiante.spinnerNotaCursada.getValue();
    int notaFinal = (int) PanelDesempenioEstudiante.spinnerNotaFinal.getValue();
    Estudiante estudiante = buscarEstudiante();
    Carrera carrera = buscarCarrera();
    Materia materia = buscarMateria(carrera);
    if (estudiante != null && carrera != null && materia != null) {
      Iterator<Cursada> iterador = materia.getCursadas().iterator();
      while (iterador.hasNext()) {
        Cursada cursada = iterador.next();
        if (cursada.getEstudiante() == estudiante && cursada.getNotaCursada() < 0)
          cursada.setNotaCursada(notaCursada);
      }
    }
    if (estudiante != null && carrera != null && materia != null) {
      Iterator<Cursada> iterador = materia.getCursadas().iterator();
      while (iterador.hasNext()) {
        Cursada cursada = iterador.next();
        if (cursada.getEstudiante() == estudiante && cursada.getNotaFinal() < 0)
          cursada.setNotaFinal(notaFinal);
      }
    }
    actualizarListaMaterias();
    habilitarSeteoDeNotas();
  }

  private Cursada identificarCursada(Materia materia, Estudiante estudiante) {
    Cursada cursada, recorredorMateria, recorredorEstudiante;
    cursada = null;
    Iterator<Cursada> iteradorMateria = materia.getCursadas().iterator();
    while (iteradorMateria.hasNext()) {
      recorredorMateria = iteradorMateria.next();
      Iterator<Cursada> iteradorCursada = estudiante.getHistorialCursadas().iterator();
      while (iteradorCursada.hasNext()) {
        recorredorEstudiante = iteradorCursada.next();
        if (recorredorEstudiante == recorredorMateria)
          cursada = recorredorEstudiante;
      }
    }
    return cursada;
  };

}
