import java.util.*;

public class Facultad {
	static ArrayList<Carrera> carreras;
	static ArrayList<Estudiante> estudiantes;
	
	Facultad(){
		carreras = new ArrayList<Carrera>();
		estudiantes = new ArrayList<Estudiante>();
		// Esto es solo para tener algunas materias visualizadas
		agregarDatosIniciales();
	}
	
	static Carrera nuevaCarrera(String nombre, String plan, int cantidadOpcionalesMinimas) {
		Carrera carrera = new Carrera(nombre, plan, cantidadOpcionalesMinimas);
		carreras.add(carrera);
		return carrera;
	};
	
	public static List<Carrera> getCarreras(){
		return carreras;
	};
	
	public static ArrayList<String> getNombresCarreras(){
		ArrayList<String> nombresCarreras = new ArrayList<String>();
		Iterator<Carrera> iterador = carreras.iterator();
		while(iterador.hasNext()) {
			nombresCarreras.add((iterador.next()).getNombre());
		}
		return nombresCarreras;
	};
	
	public static Carrera buscarCarrera(String name) {
		Carrera carrera;
		Iterator<Carrera> iterador = carreras.iterator();
		while (iterador.hasNext()){
			carrera = iterador.next();
			if (carrera.getNombre() == name)
				return carrera;
		};
		return null;
	};
	
	public static Estudiante nuevoEstudiante(int dni, String nombreEstudiante, String apellidoEstudiante) {
		Estudiante estudiante = new Estudiante(dni, nombreEstudiante, apellidoEstudiante);
		estudiantes.add(estudiante);
		return estudiante;
	};
		
	public static ArrayList<Estudiante> getEstudiantes(){
		return estudiantes;
	};
	
	public static Estudiante buscarEstudiante(int numeroLegajo) {
		Estudiante estudiante;
		Iterator<Estudiante> iterador = estudiantes.iterator();
		while (iterador.hasNext()) {
			estudiante = iterador.next();
			if (estudiante.getLegajo() == numeroLegajo)
				return estudiante;
		}
		return null;
	};
	
	public static ArrayList<Integer> getDocumentos(){
		ArrayList<Integer> documentos = new ArrayList<Integer>();
		Iterator<Estudiante> iterador = estudiantes.iterator();
		while (iterador.hasNext()) 
			documentos.add(iterador.next().getDocumento());
		return documentos;
	};
	
	void agregarDatosIniciales() {
		// Carreras y Materias
		Carrera carrera = nuevaCarrera("Analista Universitario en Sistemas", "Plan B", 0);
		carrera.nuevaMateria("Elementos de Inform�tica", 1, 4, 4, false);
		carrera.nuevaMateriaPromocionable("Expresi�n de Problemas y Algoritmos", 1, 4, 8, 4, false);
		carrera.nuevaMateria("Algebra", 1, 4, 4, false);
		carrera.nuevaMateria("Algoritmica y Programaci�n 1", 2, 4, 4, false);
		carrera.nuevaMateria("Elementos de L�gica y Matem�tica Discreta", 2, 4, 4, false);
		carrera.nuevaMateria("An�lisis Matem�tico", 2, 4, 4, false);
		carrera.nuevaMateria("Sistemas y Organizaciones", 3, 4, 4, false);
		carrera.nuevaMateria("Arquitectura de Computadoras", 3, 4, 4, false);
		carrera.nuevaMateria("Algor�tmica y Programaci�n 2", 3, 4, 4, false);
		carrera.nuevaMateria("Estad�stica", 3, 4, 4, false);
		carrera.nuevaMateria("Base de Datos 1", 4, 4, 4, false);
		carrera.nuevaMateria("Programaci�n y Dise�o Orientado a Objetos", 4, 4, 4, false);
		carrera.nuevaMateria("Ingenier�a de Software 1", 4, 4, 4, false);
		carrera.nuevaMateria("Laboratorio de Programaci�n y Lenguajes", 5, 4, 4, false);
		carrera.nuevaMateria("Fundamentos Te�ricos de Inform�tica", 5, 0, 0, false);
		carrera.nuevaMateria("Ingenier�a de Software 2", 5, 4, 4, false);
		carrera.nuevaMateria("Introducci�n a la Concurrencia", 5, 4, 4, false);
		carrera.nuevaMateria("Base de Datos 2", 6, 4, 4, false);
		carrera.nuevaMateria("Sistemas Operativos", 6, 4, 4, false);
		carrera.nuevaMateria("Laboratorio de Software", 6, 4, 4, false);
		carrera.nuevaMateria("Seminario de Aspectos Legales y Profesionales 1", 6, 4, 4, false);
		// Estudiantes
		nuevoEstudiante(33153672, "Cayetano Sim�n", "Paradiso");
		
	};
}
