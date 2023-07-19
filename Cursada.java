import java.util.ArrayList;
import java.util.Iterator;

public class Cursada {
  private Materia materia;
  private Estudiante estudiante;
  private int notaCursada = -1;
  private int notaFinal = -1;

  Cursada(Materia asignatura, Estudiante alumno) {
    materia = asignatura;
    estudiante = alumno;
  }

  public Materia getMateria() {
    return materia;
  };

  public Estudiante getEstudiante() {
    return estudiante;
  };

  void setNotaCursada(int calificacion) { // QUEDó BASTANTE POBLADO, SE PODRíA ENCAPUSLAR, LIMPIAR UN POCO y repartir
                                          // responsabilidades
    notaCursada = calificacion;
    if (notaCursada >= materia.getNotaParaAprobarCursada())
      estudiante.agregarCursadaAprobada(this);
    else
      estudiante.agregarCursadaDesaprobada(this);
    if (materia.esPromocionable() && notaCursada >= ((MateriaPromocionable) materia).getNotaParaPromocionar())
      setNotaFinal(notaCursada);
    ArrayList<Materia> materiasAprobadas = estudiante.getMaterias(estudiante.getMateriasAprobadas());
    if (materiasAprobadas.containsAll(materia.getCarrera().getMateriasObligatorias())) {
      int contadorOpcionales = 0;
      Iterator<Materia> iterador = materia.getCarrera().getMateriasOpcionales().iterator();
      Materia material;
      while (iterador.hasNext()) {
        material = iterador.next();
        if (materiasAprobadas.contains(material))
          contadorOpcionales++;
      }
      if (contadorOpcionales >= materia.getCarrera().getNroOpcionales())
        estudiante.setRecibido(materia.getCarrera());
    }
  };

  public int getNotaCursada() {
    return notaCursada;
  };

  void setNotaFinal(int calificacion) {
    if (calificacion >= materia.getNotaParaAprobarFinal() &&
        notaCursada >= materia.getNotaParaAprobarCursada()) {
      notaFinal = calificacion;
      estudiante.agregarMateriaAprobada(this);
    }
    ArrayList<Materia> materiasAprobadas = estudiante.getMaterias(estudiante.getMateriasAprobadas());
    if (materiasAprobadas.containsAll(materia.getCarrera().getMateriasObligatorias())) {
      int contadorOpcionales = 0;
      Iterator<Materia> iterador = materia.getCarrera().getMateriasOpcionales().iterator();
      Materia material;
      while (iterador.hasNext()) {
        material = iterador.next();
        if (materiasAprobadas.contains(material))
          contadorOpcionales++;
      }
      if (contadorOpcionales >= materia.getCarrera().getNroOpcionales())
        estudiante.setRecibido(materia.getCarrera());
    }
  };

  public int getNotaFinal() {
    return notaFinal;
  };

  @Override
  public String toString() {
    return materia.getNombre();
  };
}
