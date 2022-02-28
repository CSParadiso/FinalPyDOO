public class MateriaPromocionable extends Materia implements Promocionable {
	int notaParaPromocionar;
	
	MateriaPromocionable(Carrera carrerita, String name){
		super(carrerita, name);
	};
	
	MateriaPromocionable(Carrera carrerita, String name, int cuatri, int calificacionParaPromocionar, 
			int notaAprobacionCursada, int notaAprobacionFinal, boolean optional) {
		super(carrerita, name, cuatri, notaAprobacionCursada, notaAprobacionFinal, optional);
		setNotaParaPromocionar(calificacionParaPromocionar);
		// TODO Auto-generated constructor stub
	}
	
	void setNotaParaPromocionar(int calificacionParaPromocionar) {
		notaParaPromocionar = calificacionParaPromocionar;
	};
	
	public int getNotaParaPromocionar() {
		return notaParaPromocionar;
	};
	
	public void promocionar(Materia materia, Cursada cursada, int calificacion) {
		cursada.setNotaCursada(calificacion);
	}
	
}
