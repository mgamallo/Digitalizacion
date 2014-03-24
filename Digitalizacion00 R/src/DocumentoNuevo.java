import java.util.ArrayList;


public class DocumentoNuevo {

	String nombre;
	String nombreImagen;
	ArrayList<String> serviciosAfectados;
	ArrayList<String> metaDatos;
	String apariencia;
	String color;
	String observaciones;
	
	DocumentoNuevo(){
		nombre ="";
		nombreImagen = "";
		serviciosAfectados = new ArrayList<String>();
		metaDatos = new ArrayList<String>();
		apariencia = "";
		color = "";
		observaciones = "";
		
	}
	
	void imprimirDatos(){
		System.out.println(nombre);
		System.out.println(nombreImagen);
		for(int i=0;i<serviciosAfectados.size();i++){
			System.out.println("Servicio: " + serviciosAfectados.get(i));
		}
		for(int i=0;i<metaDatos.size();i++){
			System.out.println("Metadato: " + metaDatos.get(i));
		}
		System.out.println(apariencia);
		System.out.println(color);
		System.out.println(observaciones);
		
	}
	
}
