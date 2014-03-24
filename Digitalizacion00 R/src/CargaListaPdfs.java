import java.io.File;


public class CargaListaPdfs {

	String[] nombrePdfs;	//S�lo el nombre
	String[] rutaPdfs;		//path + nombre
	String rutaCarpeta;
	boolean cargado = false;
	
	

	
	CargaListaPdfs(boolean renombrar) {
		
		AbrirCarpeta carpeta = new AbrirCarpeta(renombrar);
		
		if(carpeta.eligeDirectorio == true){
			cargado = true;
			File[] ficheros = carpeta.getPdfs(renombrar);
			
			int tama�oDir = ficheros.length;
			nombrePdfs = new String[tama�oDir];
			rutaPdfs = new String[tama�oDir];
			rutaCarpeta = carpeta.nombreCarpeta;
		
			for(int i=0;i<tama�oDir;i++){
				nombrePdfs[i] = ficheros[i].getName();
				rutaPdfs[i] = ficheros[i].getAbsolutePath();
			}
		}
	}
	
	
	
	String[] getNombresPdfs(){
		return nombrePdfs;
	}
	
	String[] getRutaPdfs(){
		return rutaPdfs;
	}
	
	String getRutaCarpeta(){
		return rutaCarpeta;
	}
	
}
