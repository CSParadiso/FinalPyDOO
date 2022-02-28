import java.util.*;

public class ControladorPanelAltaEstudiante {

	ControladorPanelAltaEstudiante(){
		actualizarListaEstudiantes();
	};
	
	private boolean isDocumentoRegistrado(int dni) {
		return (Facultad.getDocumentos().contains(dni));			
	};
	
	void botonRegistrarEstudiantePresionado(int dni, String nombreEstudiante, String apellidoEstudiante){
		if (!isDocumentoRegistrado(dni)) {
			Facultad.nuevoEstudiante(dni, nombreEstudiante, apellidoEstudiante);
			PanelAltaEstudiante.modeloTablaEstudiantes.setRowCount(0); // las filas que estaban son eliminadas segun la API
			actualizarListaEstudiantes();
		}
		else
			PanelAltaEstudiante.campoDocumento.setText("Documento ya registrado");
	};
	
	private void actualizarListaEstudiantes() {
		Estudiante estudiante;
		ArrayList<Estudiante> estudiantes = Facultad.getEstudiantes();
		ListIterator<Estudiante> iterador = estudiantes.listIterator(estudiantes.size());
		while (iterador.hasPrevious()) {
			estudiante = iterador.previous();
			PanelAltaEstudiante.modeloTablaEstudiantes.addRow(new String[] {((Integer)estudiante.getLegajo()).toString(), 
				((Integer)estudiante.getDocumento()).toString(), estudiante.getNombre(), estudiante.getApellido()});
		}
	};
	
}
