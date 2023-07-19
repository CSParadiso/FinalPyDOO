import java.util.List;

// Clase Singleton. Se crea una sola instancia que en su constructor crea una instancia de cada uno de los controladores de las vistas
public class ControladorMultiPestania {
  private static ControladorMultiPestania instancia;
  private static ControladorPanelAltaCarrera controladorPanelAltaCarrera;
  private static ControladorPanelAltaEstudiante controladorPanelAltaEstudiante;
  private static ControladorPanelConsultaEInscripciones controladorPanelConsultaEInscripciones;
  private static ControladorPanelDesempenioEstudiante controladorPanelDesempenioEstudiante;

  private ControladorMultiPestania() {
  };

  public static ControladorMultiPestania getInstancia(Object panel) {
    if (instancia == null)
      instancia = new ControladorMultiPestania();
    if (panel instanceof PanelAltaCarrera)
      controladorPanelAltaCarrera = new ControladorPanelAltaCarrera();
    else if (panel instanceof PanelAltaEstudiante)
      controladorPanelAltaEstudiante = new ControladorPanelAltaEstudiante();
    else if (panel instanceof PanelConsultaEInscripcion)
      controladorPanelConsultaEInscripciones = new ControladorPanelConsultaEInscripciones();
    else if (panel instanceof PanelDesempenioEstudiante)
      controladorPanelDesempenioEstudiante = new ControladorPanelDesempenioEstudiante();
    return instancia;
  };

  // Encapsulamiento PanelAltaCarrera
  void visualizarInfoPlan(String nombrePlan) {
    controladorPanelAltaCarrera.visualizarInfoPlan(nombrePlan);
  };

  void botonCrearCarreraPresionado(String nombre, String botonPlan, int cantidadOpcionales) {
    controladorPanelAltaCarrera.botonCrearCarreraPresionado(nombre, botonPlan, cantidadOpcionales);
    controladorPanelConsultaEInscripciones.actualizarListaCarreras();
  }

  void botonCrearMateriaPresionado(String carrera, String nombre, int cuatrimestre, int promocionar,
      int aprobarCursada, int aprobarFinal, boolean opcional, boolean promocionable) {
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

  void botonAgregarCorrelativasPresionado(List<String> correlativas) {
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
    controladorPanelDesempenioEstudiante.actualizarListaEstudiantes();
  };

  void botonCursarMateriaPresionado() {
    controladorPanelConsultaEInscripciones.botonCursarMateriaPresionado();
    controladorPanelDesempenioEstudiante.actualizarListaMaterias();
  };

  // Encapsulando el Controlador PanelDesempenioEstudiante

  void estudianteSeleccionado() {
    controladorPanelDesempenioEstudiante.actualizarListaMaterias();
  };

  void materiaSeleccionada() {
    controladorPanelDesempenioEstudiante.habilitarSeteoDeNotas();
  };

  void botonRegistrarNotaPresionado() {
    controladorPanelDesempenioEstudiante.botonRegistrarNotaPresionado();
    controladorPanelConsultaEInscripciones.visualizarMateriasDisponibles();
    controladorPanelConsultaEInscripciones.visualizarDatosEstudiante();
  };

}
