import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

public class Carrera {
	private static int generadorCodigo = 0;
	private int codigo;
	private String nombre;
	private PlanEstudio plan;
	private List <Materia> obligatorias;
	private int nroOpcionales;
	private List <Materia> opcionales;
	
	Carrera(String name, String plancito, int optionals){
		setCodigo();
		setNombre(name);
		setPlan(plancito);
		obligatorias = new ArrayList<Materia>();
		opcionales = new ArrayList<Materia>();
		nroOpcionales = optionals;
	};
	
	void setCodigo() {
		generadorCodigo++;
		codigo = generadorCodigo;
	};
	
	public int getCodigo() {
		return codigo;
	};
	
	void setNombre(String name) {
		nombre = name;
	};
	
	public String getNombre() {
		return nombre;
	};
	
	void setMateriaObligatoria(Materia asignaturasObligatoria) {
		obligatorias.add(asignaturasObligatoria);
	};
	
	public List<Materia> getMateriasObligatorias() {
		return obligatorias;
	};
	
	void setNroOpcionales(int opcionalesMinimas) {
		nroOpcionales = opcionalesMinimas;
	}
	
	public int getNroOpcionales() {
		return nroOpcionales;
	};
	
	void setMateriaOpcional(Materia asignaturaOpcional){
		opcionales.add(asignaturaOpcional);
	};
	
	public List<Materia> getMateriasOpcionales() {
		return opcionales;
	};
	
	public ArrayList<Materia> getMaterias(){
		ArrayList<Materia> listadoMaterias = new ArrayList<Materia>();
		listadoMaterias.addAll(opcionales);
		listadoMaterias.addAll(obligatorias);
		return listadoMaterias;
	};
	
	public Materia buscarMateria(String name) {
		Materia materia, recorrida;
		materia = null;
		ArrayList<Materia> materias = getMaterias();
		if (getNombresMaterias().contains(name)) {
			Iterator<Materia> iterador = materias.iterator();
			while (iterador.hasNext()) {
				recorrida = iterador.next();
				if (recorrida != null && recorrida.getNombre() == name)
					materia = recorrida;
				;
			}
		}
		return materia;
	};
	
	public ArrayList<String> getNombresMaterias(){
		ArrayList<String> nombresMaterias = new ArrayList<String>();
		Iterator<Materia> iterador;
		iterador = opcionales.iterator();
		while (iterador.hasNext()) {
			nombresMaterias.add(iterador.next().getNombre());
		}
		iterador = obligatorias.iterator();
		while(iterador.hasNext()) {
			nombresMaterias.add(iterador.next().getNombre());
		}
		return nombresMaterias;
	}; 
	
	public List <Materia> getMateriasPrevias(int cuatrimestreMateria) {
		List <Materia> materias = new ArrayList<Materia>();
		Materia materia;
		Iterator<Materia> iterador;
		iterador = obligatorias.iterator();
		while (iterador.hasNext()){
			materia = (Materia)iterador.next();
			if (cuatrimestreMateria > materia.getCuatrimestre())
				materias.add(materia);
		}
		iterador = opcionales.iterator();
		while (iterador.hasNext()) {
			materia = iterador.next();
			if (cuatrimestreMateria > materia.getCuatrimestre())
				materias.add(materia);
		}
		return materias;
	};
	
	public List <Materia> getMateriasPrevias(int cuatrimestreMateria, int cuatrimestresPrevios) {
		List <Materia> materias = new ArrayList<Materia>();
		Materia materia;
		Iterator<Materia> iterador;
		iterador = obligatorias.iterator();
		while (iterador.hasNext()){
			materia = (Materia)iterador.next();
			if (cuatrimestreMateria - materia.getCuatrimestre() < cuatrimestresPrevios)
				materias.add(materia);
		}
		iterador = opcionales.iterator();
		while (iterador.hasNext()) {
			materia = iterador.next();
			if (cuatrimestreMateria - materia.getCuatrimestre() < cuatrimestresPrevios)
				materias.add(materia);
		}
		return materias;
	};
	
	void nuevaMateriaPromocionable(String name, int cuatrimestre, int calificacionParaPromocionar, int notaParaAprobarCursada, 
			int notaParaAprobarFinal, boolean optional) {
		new MateriaPromocionable(this, name, cuatrimestre, calificacionParaPromocionar, notaParaAprobarCursada, notaParaAprobarFinal, optional);
	};
	
	void nuevaMateria(String name, int cuatrimestre, int notaParaAprobarCursada, int notaParaAprobarFinal, boolean optional) {
		new Materia(this, name, cuatrimestre, notaParaAprobarCursada, notaParaAprobarFinal, optional);
	};
	
	void setPlan(String planEstudio) {
		switch (planEstudio) {
		case "Plan A" : plan = new PlanEstudioA();
			break;
		case "Plan B" : plan = new PlanEstudioB();
			break;
		case "Plan C" : plan = new PlanEstudioC();
			break;
		case "Plan D" : plan = new PlanEstudioD();
			break;
		case "Plan E" : plan = new PlanEstudioE();
			break;
		};
	};
	
	public PlanEstudio getPlanEstudio() {
		return plan;
	};
	
	public void cursarMateria(Materia materia, Estudiante estudiante) {
		plan.cursarMateria(materia, estudiante);
	};
	
	public boolean puedeCursar(Materia materia, Estudiante estudiante) {
		return plan.puedeCursar(materia, estudiante); //condiciones de cursada o no
	};
	
	@Override
	public String toString() {
		return this.getNombre();
	};
}
