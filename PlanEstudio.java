
public interface PlanEstudio {
	boolean puedeCursar(Materia materia, Estudiante estudiante);
	void cursarMateria(Materia materia, Estudiante estudiante);
	//public PlanEstudio getPlan(String plan);
	public String getCondiciones();
}
