import java.util.*;

public class ControladorPanelAltaCarrera {
  String nombreCarrera, nombreMateria;
  Carrera carrera;
  Materia materia;

  ControladorPanelAltaCarrera() {
    PanelAltaCarrera.spinnerListaCarreras.setList(Facultad.getNombresCarreras());
    nombreCarrera = (String) PanelAltaCarrera.spinnerListaCarreras.getValue();
    PanelAltaCarrera.spinnerListaMaterias.setList(Facultad.buscarCarrera(nombreCarrera).getNombresMaterias());
    nombreMateria = (String) PanelAltaCarrera.spinnerListaMaterias.getValue();
    carrera = Facultad.buscarCarrera(nombreCarrera);
    materia = carrera.buscarMateria(nombreMateria);
    spinnerSeleccionarMateriaRecorrido(nombreMateria);
  };

  void visualizarInfoPlan(String plan) {
    PlanEstudio planEstudio = determinarPlanEstudio(plan);
    if (planEstudio != null)
      PanelAltaCarrera.campoInfoPlan.setText(plan + ": " + planEstudio.getCondiciones());
  };

  void botonCrearCarreraPresionado(String nombre, String plan, int cantidadOpcionalesMinimas) {
    if (!Facultad.getNombresCarreras().contains(nombre)) {
      Facultad.nuevaCarrera(nombre, plan, cantidadOpcionalesMinimas);
      PanelAltaCarrera.spinnerListaCarreras.setList(Facultad.getNombresCarreras());
      PanelAltaCarrera.spinnerListaCarreras.setValue(nombre);
    } else
      PanelAltaCarrera.campoNombre.setText("El nombre de la Carrera: " + nombre + " ya existe en la Facultad");
  }

  void botonCrearMateriaPresionado(String nombreCarrera, String nombreMateria, int cuatrimestreMateria,
      int notaPromocionar,
      int aprobarCursada, int aprobarFinal, boolean opcional, boolean materiaPromocionable) {
    Carrera carrera = Facultad.buscarCarrera(nombreCarrera);
    if (carrera != null && !carrera.getNombresMaterias().contains(nombreMateria) &&
        hayCupoOpcionales(carrera, opcional)) { // no estoy chequeando si la materia es opcional
      if (materiaPromocionable) // acni puede ir polimorfismo
        carrera.nuevaMateriaPromocionable(nombreMateria, cuatrimestreMateria, notaPromocionar, aprobarCursada,
            aprobarFinal, opcional);
      else
        carrera.nuevaMateria(nombreMateria, cuatrimestreMateria, aprobarCursada, aprobarFinal, opcional);
      PanelAltaCarrera.spinnerListaMaterias.setList(carrera.getNombresMaterias());
    } else {
      if (!hayCupoOpcionales(carrera, opcional))
        PanelAltaCarrera.campoNombreMateriaNueva.setText("niNo se pueden definir mnis materias opcionales!");
      else
        PanelAltaCarrera.campoNombreMateriaNueva.setText("El nombre de la materia ya existe");
    }

  };

  // This model (SpinnerListModel) inherits a ChangeListener. The ChangeListeners
  // are notified whenever the model's value or list properties changes.
  void spinnerSeleccionarCarreraRecorrido(String nombreCarrera) {
    if (nombreCarrera == "Seleccione una carrera" | Facultad.buscarCarrera(nombreCarrera) == null)
      PanelAltaCarrera.spinnerListaMaterias.setList(new ArrayList<String>() {
        {
          add("Seleccione una materia existente");
        }
      });
    else {
      carrera = Facultad.buscarCarrera(nombreCarrera);
      if (carrera.getNombresMaterias().isEmpty())
        PanelAltaCarrera.spinnerListaMaterias.setList(new ArrayList<String>() {
          {
            add("Materia sin carreras");
          }
        });
      else
        PanelAltaCarrera.spinnerListaMaterias.setList(carrera.getNombresMaterias());
    }

  };

  void spinnerSeleccionarMateriaRecorrido(String nombreMateria) {
    materia = carrera.buscarMateria(nombreMateria);
    // System.out.println("Nombre que llega desde la materia buscada: " +
    // materia.getNombre());
    // System.out.println("Nombre que llega al mnitodo seleccionarMateriaRecorrido:
    // " + nombreMateria);
    ArrayList<String> materiasDeCarrera = carrera.getNombresMaterias();
    materiasDeCarrera.remove(nombreMateria);
    Iterator<String> iterador = materiasDeCarrera.iterator(); // Se debe a que DefaultModelList no tiene set(Contenedor)
    PanelAltaCarrera.modeloListaCorrelativas.clear();
    while (iterador.hasNext()) {
      PanelAltaCarrera.modeloListaCorrelativas.addElement(iterador.next());
    }
    mostrarInfoMateriaActual();
  };

  void botonAgregarCorrelativasPresionado(List<String> materiasCorrelativas) {
    String nombreCorrelativa;
    Iterator<String> iterador = materiasCorrelativas.iterator();
    while (iterador.hasNext()) {
      nombreCorrelativa = iterador.next();
      if (!materia.getNombresCorrelativas().contains(nombreCorrelativa))
        materia.aniadirCorrelativa(carrera.buscarMateria(nombreCorrelativa));
    }
    mostrarInfoMateriaActual();
  };

  void botonRemoverCorrelativasPresionado(List<String> materiasCorrelativas) {
    String nombreCorrelativa;
    Iterator<String> iterador = materiasCorrelativas.iterator();
    while (iterador.hasNext()) {
      nombreCorrelativa = iterador.next();
      if (materia.getNombresCorrelativas().contains(nombreCorrelativa))
        materia.removerCorrelativa(carrera.buscarMateria(nombreCorrelativa));
    }
    mostrarInfoMateriaActual();
  }

  private PlanEstudio determinarPlanEstudio(String nombrePlan) {
    PlanEstudio plan;
    switch (nombrePlan) {
      case "Plan A":
        plan = new PlanEstudioA();
        break;
      case "Plan B":
        plan = new PlanEstudioB();
        break;
      case "Plan C":
        plan = new PlanEstudioC();
        break;
      case "Plan D":
        plan = new PlanEstudioD();
        break;
      case "Plan E":
        plan = new PlanEstudioE();
        break;
      default:
        plan = null;
    }
    return plan;
  };

  private boolean hayCupoOpcionales(Carrera carrera, boolean materiaEsOpcional) {
    if (!materiaEsOpcional)
      return true;
    else {
      if (carrera.getMateriasOpcionales().size() < carrera.getNroOpcionales())
        return true;
      else
        return false;
    }
  };

  private void mostrarInfoMateriaActual() {
    if (materia != null) {
      PanelAltaCarrera.campoInfoNombre.setText(materia.getNombre());
      PanelAltaCarrera.campoInfoCarrera.setText(materia.getCarrera().getNombre());
      PanelAltaCarrera.campoInfoPromocionable.setText((materia.esPromocionable() ? "SI" : "NO"));
      PanelAltaCarrera.campoInfoOpcional.setText((materia.isOpcional() ? "SI" : "NO"));
      PanelAltaCarrera.campoInfoCuatrimestre.setText(((Integer) (materia.getCuatrimestre())).toString());
      PanelAltaCarrera.campoInfoNotaPromocionar.setText((materia.esPromocionable()
          ? ((Integer) (((MateriaPromocionable) materia).getNotaParaPromocionar())).toString()
          : " - "));
      PanelAltaCarrera.campoInfoNotaAprobarCursada
          .setText(((Integer) (materia.getNotaParaAprobarCursada())).toString());
      PanelAltaCarrera.campoInfoNotaAprobarFinal.setText(((Integer) (materia.getNotaParaAprobarFinal())).toString());
      PanelAltaCarrera.campoInfoCorrelativas.setText(materia.getNombresCorrelativas().toString());
    } else {
      PanelAltaCarrera.campoInfoNombre.setText(" - ");
      PanelAltaCarrera.campoInfoCarrera.setText(" - ");
      PanelAltaCarrera.campoInfoPromocionable.setText(" - ");
      PanelAltaCarrera.campoInfoOpcional.setText(" - ");
      PanelAltaCarrera.campoInfoCuatrimestre.setText(" - ");
      PanelAltaCarrera.campoInfoNotaPromocionar.setText(" - ");
      PanelAltaCarrera.campoInfoNotaAprobarCursada.setText(" - ");
      PanelAltaCarrera.campoInfoNotaAprobarFinal.setText(" - ");
      PanelAltaCarrera.campoInfoCorrelativas.setText(" - ");
    }
  };

}
