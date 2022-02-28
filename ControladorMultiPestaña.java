import java.util.List;

// Clase Singleton. Se crea una sola instancia que en su constructor crea una instancia de cada uno de los controladores de las vistas
public class ControladorMultiPestaña {
	private static ControladorMultiPestaña instancia;
	private static ControladorPanelAltaCarrera controladorPanelAltaCarrera;
	private static ControladorPanelAltaEstudiante controladorPanelAltaEstudiante;
	private static ControladorPanelConsultaEInscripciones controladorPanelConsultaEInscripciones;
	private static ControladorPanelDesempeñoEstudiante controladorPanelDesempeñoEstudiante;
	
	private ControladorMultiPestaña(){};
	
	public static ControladorMultiPestaña getInstancia(Object panel){
		if (instancia == null)
			instancia = new ControladorMultiPestaña();
		if (panel instanceof PanelAltaCarrera)
			controladorPanelAltaCarrera = new ControladorPanelAltaCarrera();
		else if (panel instanceof PanelAltaEstudiante)
			controladorPanelAltaEstudiante = new ControladorPanelAltaEstudiante();
		else if (panel instanceof PanelConsultaEInscripcion)
			controladorPanelConsultaEInscripciones = new ControladorPanelConsultaEInscripciones();
		else if (panel instanceof PanelDesempeñoEstudiante)
			controladorPanelDesempeñoEstudiante = new ControladorPanelDesempeñoEstudiante();
		return instancia;
	};
		
	// Encapsulamiento PanelAltaCarrera
	void visualizarInfoPlan(String nombrePlan) {
		controladorPanelAltaCarrera.visualizarInfoPlan(nombrePlan);
	};
	
	void botonCrearCarreraPresionado(String nombre, String botonPlan, int cantidadOpcionales){
		controladorPanelAltaCarrera.botonCrearCarreraPresionado(nombre, botonPlan, cantidadOpcionales);
		controladorPanelConsultaEInscripciones.actualizarListaCarreras();
	}
	
	void botonCrearMateriaPresionado(String carrera, String nombre, int cuatrimestre, int promocionar,
			int aprobarCursada, int aprobarFinal, boolean opcional, boolean promocionable){
		controladorPanelAltaCarrera.botonCrearMateriaPresionado(carrera, nombre, cuatrimestre, promocionar,
				aprobarCursada, aprobarFinal, opcional, promocionable);
		controladorPanelConsultaEInscripciones.visualizarMateriasDisponibles();
	};
	
	void spinnerSeleccionarCarreraRecorrido(String nombreCarrera) {
		controladorPanelAltaCarrera.spinnerSeleccionarCarreraRecorrido(nombreCarrera);
	};
	
	void spinnerSeleccionarMateriaRecorrido(String nombreMateria) {
		controladorPanelAltaCarrera.spinnerSeleccionarMateriaRecorrido(nombreMateria);
	};
	
	void botonAgregarCorrelativasPresionado(List<String> correlativas){
		controladorPanelAltaCarrera.botonAgregarCorrelativasPresionado(correlativas);
		controladorPanelConsultaEInscripciones.visualizarMateriasDisponibles();
	};
	
	void botonRemoverCorrelativasPresionado(List<String> correlativas) {
		controladorPanelAltaCarrera.botonRemoverCorrelativasPresionado(correlativas);
		controladorPanelConsultaEInscripciones.visualizarMateriasDisponibles();
	};

	// Encapsulando el controlador PanelAltaEstudiante
	void botonRegistrarEstudiantePresionado(int documento, String nombre, String apellido) {
	controladorPanelAltaEstudiante.botonRegistrarEstudiantePresionado(documento, nombre, apellido);
	controladorPanelConsultaEInscripciones.actualizarListaEstudiantes();
	}
	
	// Encapsulando el controlador PanelConsultaEInscripciones
	void visualizarDatosEstudiante() {
		controladorPanelConsultaEInscripciones.visualizarDatosEstudiante();
	};
	
	void botonInscribirACarreraPresionado() {
		controladorPanelConsultaEInscripciones.botonInscribirACarreraPresionado();
		controladorPanelDesempeñoEstudiante.actualizarListaEstudiantes();
	};
	
	void botonCursarMateriaPresionado() {
		controladorPanelConsultaEInscripciones.botonCursarMateriaPresionado();
		controladorPanelDesempeñoEstudiante.actualizarListaMaterias();
	};
	
	// Encapsulando el Controlador PanelDesempeñoEstudiante
	
	void estudianteSeleccionado() {
		controladorPanelDesempeñoEstudiante.actualizarListaMaterias();
	};
	
	void materiaSeleccionada() {
		controladorPanelDesempeñoEstudiante.habilitarSeteoDeNotas();
	};
	
	void botonRegistrarNotaPresionado() {
		controladorPanelDesempeñoEstudiante.botonRegistrarNotaPresionado();
		controladorPanelConsultaEInscripciones.visualizarMateriasDisponibles();
		controladorPanelConsultaEInscripciones.visualizarDatosEstudiante();
	};
	
}
