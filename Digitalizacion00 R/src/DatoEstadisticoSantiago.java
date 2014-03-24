
public class DatoEstadisticoSantiago{
	
	String nombreServicio;
	String nombreDocumento;
	double tamañoDocumento;
	
	DatoEstadisticoSantiago(){
		nombreServicio = "";
		nombreDocumento = "";
		tamañoDocumento = 0;
	}
	
	DatoEstadisticoSantiago(String nomServicio, String nomDocumento, double tamDocumento){
		nombreServicio = nomServicio;
		nombreDocumento = nomDocumento;
		tamañoDocumento = tamDocumento;
	}
	
}

class ConjuntoDatosEstadisticos{
	String nombreDoc;
	int numeroDoc;
	double media;
	double mediana;
	double desviacion;
	double maximo;
	double minimo;

	
	ConjuntoDatosEstadisticos(){
		nombreDoc = "";
		numeroDoc = 0;
		media = 0;
		mediana = 0;
		desviacion = 0;
		maximo = 0;
		minimo = 0;

	}
}

class TablaServicioDocumentos{
	String nombreServicio;
	String nombreDocumento;
	int numeroSubidos;
	
	TablaServicioDocumentos(){
		nombreServicio = "";
		nombreDocumento = "";
		numeroSubidos = 0;
	}
	
	TablaServicioDocumentos(String servicio, String documento, int vecesSubido){
		nombreServicio = servicio;
		nombreDocumento = documento;
		numeroSubidos = vecesSubido;
	}
	
}