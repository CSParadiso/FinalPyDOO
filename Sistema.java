
public class Sistema {
	static Facultad facultad;
	static Ventana sistema;
	
	public static void main(String [] args) {
		// Creamos el objeto Facultad que contiene todas las clases (Model y Controller) que nos permiten desarrollar todo el modelo.
		facultad = new Facultad(); 
		// Creamos la ventana que nos permite visualizar los elementos (View)
		sistema = new Ventana(); 
	}
}
