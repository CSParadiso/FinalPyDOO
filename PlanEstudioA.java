/*
 * Plan A: aprobó las cursadas de las correlativas
 * */
public class PlanEstudioA implements PlanEstudio {
	
	static final String nombre = "Plan A";
	static final String condiciones = "Aprobó las cursadas de las correlativas";
	
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
		return (estudiante.getMaterias(estudiante.getCursadasAprobadas()).containsAll(materia.getCorrelativas()));
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
