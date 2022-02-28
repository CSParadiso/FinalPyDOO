import java.util.ArrayList;
import java.util.Iterator;

public class Estudiante {
	private static int generadorLegajo = 0;
	private int legajo, documento;
	private String nombre, apellido;
	private ArrayList<Carrera> carreras;
	private ArrayList<Cursada> cursadasActuales;
	private ArrayList<Cursada> materiasAprobadas;
	private ArrayList<Cursada> cursadasAprobadas;
	private ArrayList<Cursada> cursadasDesaprobadas;
	private ArrayList<Carrera> recibido;
	
	Estudiante(int dni, String name, String lastName){
		generarLegajo();
		setDocumento(dni);
		setNombre(name);
		setApellido(lastName);
		carreras = new ArrayList<Carrera>();
		cursadasActuales = new ArrayList<Cursada>();
		materiasAprobadas = new ArrayList<Cursada>();
		cursadasAprobadas = new ArrayList<Cursada>();
		cursadasDesaprobadas = new ArrayList<Cursada>();
		recibido = new ArrayList<Carrera>();
	};
	
	private void generarLegajo() {
		generadorLegajo++;
		legajo = generadorLegajo;
	};
	
	public int getLegajo() {
		return legajo;
	};
	
	private void setDocumento(int dni) {
		documento = dni;
	};
	
	public int getDocumento() {
		return documento;
	};
	
	void setNombre(String name) {
		nombre = name;
	};
	
	public String getNombre() {
		return nombre;
	};
	
	void setApellido(String lastName) {
		apellido = lastName;
	};
	
	public String getApellido() {
		return apellido;
	};
	
	void agregarCarrera(Carrera carrera){
		carreras.add(carrera);
	};
	
	ArrayList<Carrera> getCarreras(){
		return carreras;
	};
	
	private void agregarCursada(Cursada cursada) {
		cursadasActuales.add(cursada);
	};
	
	public ArrayList<Cursada> getCursando(){
		return cursadasActuales;
	};
		
	void agregarCursadaAprobada(Cursada cursada) {
		cursadasAprobadas.add(cursada);
		cursadasActuales.remove(cursada);
	};
	
	ArrayList<Cursada> getCursadasAprobadas(){
		return cursadasAprobadas;
	};
	
	void agregarMateriaAprobada(Cursada cursada) {
		materiasAprobadas.add(cursada);
		cursadasAprobadas.remove(cursada);
	};
	
	ArrayList<Cursada> getMateriasAprobadas(){
		return materiasAprobadas;
	};
	
	void agregarCursadaDesaprobada(Cursada cursada) {
		cursadasDesaprobadas.add(cursada);
		cursadasActuales.remove(cursada);
	};
	
	ArrayList<Cursada> getCursadasDesaprobadas(){
		return cursadasDesaprobadas;
	};
	
	ArrayList<Cursada> getHistorialCursadas(){
		ArrayList<Cursada> historial = new ArrayList<Cursada>();
		historial.addAll(cursadasDesaprobadas);
		historial.addAll(materiasAprobadas);
		historial.addAll(cursadasAprobadas);
		historial.addAll(cursadasActuales);
		return historial;
	};
	
	Cursada cursar(Materia materia) {
		Cursada cursada = new Cursada(materia, this);
		agregarCursada(cursada);
		return cursada;
	};
	
	ArrayList<Materia> getMaterias(ArrayList<Cursada> cursadas){
		ArrayList<Materia> materias = new ArrayList<Materia>();
		Iterator<Cursada> iterador =  cursadas.iterator();
		while (iterador.hasNext())
			materias.add(iterador.next().getMateria());
		return materias;
	};
	
	void setRecibido(Carrera carrera) {
		recibido.add(carrera);
	};
	
	ArrayList<Carrera> getCarrerasRecibido(){
		return recibido;
	};
	
	public boolean isRecibido(Carrera carrera) {
		return (recibido.contains(carrera));
	};
}
