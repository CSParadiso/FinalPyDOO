/* Plan C: aprobó las cursadas de las correlativas y
 * los finales de todas las materias de 5 cuatrimestres previos al que se quiere anotar
 */

import java.util.List;

public class PlanEstudioC implements PlanEstudio {

  static final String nombre = "Plan C";
  static final String condiciones = "Aprobó las cursadas de las correlativas y los finales de todas las materias de 5 cuatrimestres previos al que se quiere anotar";

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
    materiasPrevias = materia.getCarrera().getMateriasPrevias(materia.getCuatrimestre(), 5);
    if (estudiante.getMaterias(estudiante.getCursadasAprobadas()).containsAll(materia.getCorrelativas())
        && estudiante.getMaterias(estudiante.getMateriasAprobadas()).containsAll(materiasPrevias))
      return true;
    else
      return false;
  }

  @Override
  public void cursarMateria(Materia materia, Estudiante estudiante) {
    Cursada cursada;
    if (puedeCursar(materia, estudiante)) {
      cursada = materia.cursar(estudiante);
      materia.agregarCursada(cursada);
    }
  }
}
