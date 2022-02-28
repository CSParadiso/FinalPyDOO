import java.util.*;

public class Materia {
	private static int generadorCodigo = 0;
	private int codigo;
	private Carrera carrera;
	private String nombre;
	private boolean opcional;
	private int anioCarrera;
	private int cuatrimestre;
	private int notaParaAprobarFinal; //Sería la nota del final
	private int notaParaAprobarCursada;
	private ArrayList <Cursada> cursadas;
	private ArrayList <Materia> correlativas;
	
	Materia(Carrera carrerita, String name){
		setCarrera(carrerita);
		setNombre(name);
	};
	
	Materia(Carrera carrerita, String name, int cuatri, int notaAprobacionCursada, int notaAprobacionFinal, boolean optional){
		setCodigo();
		setCarrera(carrerita);
		setNombre(name);
		setCuatrimestre(cuatri);
		setNotaParaAprobarCursada(notaAprobacionCursada);
		setNotaParaAprobarFinal(notaAprobacionFinal);
		setOpcional(optional);
		cursadas = new ArrayList <Cursada>(); // La materia tiene una lista de cursadas
		correlativas = new ArrayList<Materia>();
	}
	
	private void setCodigo() {
		generadorCodigo++;
		codigo = generadorCodigo;
	};
	
	public int getCodigo() {
		return codigo;
	};
	
	void setCarrera(Carrera carrerita) {
		carrera = carrerita;
	};
	
	public Carrera getCarrera() {
		return carrera;
	};
	
	void setNombre(String name) {
		nombre = name;
	};
	
	public String getNombre() {
		return nombre;
	};
	
	void setOpcional(boolean condicion) {
		opcional = condicion;
		if (condicion) 
			this.getCarrera().setMateriaOpcional(this);
		else
			this.getCarrera().setMateriaObligatoria(this);
	};
	
	public boolean isOpcional() {
		return opcional;
	};
	
	void setAnioCarrera(){
		anioCarrera = (cuatrimestre % 2) == 0 ? (cuatrimestre/2) : (cuatrimestre/2) + 1;
	};
	
	void setAnioCarrera(int year) {
		anioCarrera = year;
	};
	
	public int getAnioCarrera() {
		return anioCarrera;
	};
	
	void setCuatrimestre(int cuatri) {
		cuatrimestre = cuatri;
	};
	
	public int getCuatrimestre() {
		return cuatrimestre;
	};
	
	void setNotaParaAprobarCursada(int notaAprobacion) {
		notaParaAprobarCursada = notaAprobacion;
	};
	
	public int getNotaParaAprobarCursada() {
		return notaParaAprobarCursada;
	};
	
	void setNotaParaAprobarFinal(int notaAprobacion) {
		notaParaAprobarFinal = notaAprobacion;
	};
	
	public int getNotaParaAprobarFinal() {
		return notaParaAprobarFinal;
	}
	
	void agregarCursada(Cursada cursada) {
		cursadas.add(cursada);
	}
	
	Cursada cursar(Estudiante estudiante) {
		Cursada cursada = estudiante.cursar(this);
		return cursada;
	};
	
	public ArrayList<Cursada> getCursadas(){
		return cursadas;
	}; 
	
	void añadirCorrelativa(Materia correlativa) {
		if (! correlativas.contains(correlativa)) 
			correlativas.add(correlativa);
	}
	
	void removerCorrelativa(Materia correlativa) {
		if (correlativas.contains(correlativa)) 
			correlativas.remove(correlativa);
	}
	
	public ArrayList<Materia> getCorrelativas(){
		return correlativas;
	};
	
	public int getNroCorrelativas() {
		return correlativas.size();
	}
	
	public boolean esPromocionable() {
		return (this instanceof MateriaPromocionable);
	}

	public ArrayList<String> getNombresCorrelativas() {
		ArrayList<String> nombresCorrelativas = new ArrayList<String>();
		Iterator<Materia> iterador = this.correlativas.iterator();
		while (iterador.hasNext())
			nombresCorrelativas.add(iterador.next().getNombre());
		return nombresCorrelativas;
	};
	
	@Override
	public String toString() {
		return nombre;
	};
}
	