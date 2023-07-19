/*
 * Plan E: aprobó los finales de las correlativas y los finales de todas las materias de 3
cuatrimestres previos.
 * */

import java.util.List;

public class PlanEstudioE implements PlanEstudio {

  static final String nombre = "Plan E";
  static final String condiciones = "Aprobó los finales de las correlativas y los finales de todas las materias de 3 cuatrimestres previos.";

  public PlanEstudio getPlan(String plan) {
    if (plan == nombre)
      return this;
    else
      return null;
  }

  @Override
  public String getCondiciones() {
    return condiciones;
  };

  @Override
  public boolean puedeCursar(Materia materia, Estudiante estudiante) {
    List<Materia> materiasPrevias;
    materiasPrevias = materia.getCarrera().getMateriasPrevias(materia.getCuatrimestre(), 3);
    if (estudiante.getMaterias(estudiante.getMateriasAprobadas()).containsAll(materia.getCorrelativas())
        && estudiante.getMaterias(estudiante.getMateriasAprobadas()).containsAll(materiasPrevias))
      return true;
    else
      return false;
  };

  @Override
  public void cursarMateria(Materia materia, Estudiante estudiante) {
    Cursada cursada;
    if (puedeCursar(materia, estudiante)) {
      cursada = materia.cursar(estudiante);
      materia.agregarCursada(cursada);
    }
  };
}
