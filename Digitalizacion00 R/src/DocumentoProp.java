
public class DocumentoProp {
	String nhc;
	String servicio;
	String nombreNormalizado;
	String rutaArchivo;
	
	DocumentoProp(String ruta){
		rutaArchivo = ruta;
		getPropiedades();
	}
	
	void getPropiedades(){
		
		int aux = rutaArchivo.indexOf("@");
		int auxNHC = rutaArchivo.indexOf("@",aux+1);

		nhc = rutaArchivo.substring(aux + 1, auxNHC -1);

		int auxServ = rutaArchivo.indexOf("@",auxNHC+1);
		servicio = rutaArchivo.substring(auxNHC+1,auxServ-1);

		int auxNombre = rutaArchivo.lastIndexOf("r_");
		nombreNormalizado = rutaArchivo.substring(auxServ+1,auxNombre-1);

	}
	
	void imprimePropiedades(){
		System.out.println(rutaArchivo);
		System.out.println("NHC: " + nhc);
		System.out.println("Servicio: " + servicio);
		System.out.println("Nombre: " + nombreNormalizado);
	}
	
}
