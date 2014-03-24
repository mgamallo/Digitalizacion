import java.util.ArrayList;


public class Estadisticas {

	/*
	 * 	Clase pendiente de eliminar
	 */
	
	
	
	ArrayList<DatosServicio> listaServicios;
	
	Estadisticas(){
		listaServicios = new ArrayList<DatosServicio>();
	}
	

	//	Añadir un dato estadistico
	public void añadirDato(String servicio, String documento){					
		
		boolean servicioEncontrado = false;
		boolean nombreEncontrado = false;
		
		for(int i=0;i<listaServicios.size();i++){
			if(listaServicios.get(i).nombreServicio.contains(servicio)){
				for(int j=0;j<listaServicios.get(i).docUtilizados.size();j++){
					if(listaServicios.get(i).docUtilizados.get(j).nombreDocumento.contains(documento)){
						listaServicios.get(i).docUtilizados.get(j).numeroUsos++;
					/*	System.out.println("El documento " + listaServicios.get(i).docUtilizados.get(j).nombreDocumento +
							" ha sido utilizado " + listaServicios.get(i).docUtilizados.get(j).numeroUsos + " veces");    */
						nombreEncontrado = true;
						break;
					}
				}
				if(!nombreEncontrado){
					listaServicios.get(i).docUtilizados.add(new NombresUtilizados(InicioIanus.nombreDocumento));
				/*	System.out.println("Nuevo nombre de documento añadido: " 
					+ listaServicios.get(i).docUtilizados.get(listaServicios.get(i).docUtilizados.size()-1).nombreDocumento);  */
				}
				servicioEncontrado = true;
				break;
			}
		}
		if(!servicioEncontrado){
			listaServicios.add(new DatosServicio(InicioIanus.servicioCombo));
		/*	System.out.println("Nuevo servicio añadido: " + listaServicios.get(listaServicios.size()-1).nombreServicio);  */
		}
	}
	
	//	Listar datos estadisticos
	public void imprimir(){
		for(int i=0;i<listaServicios.size();i++){
	//		System.out.println("El servicio " + listaServicios.get(i).nombreServicio + " ha utilizado los siguientes documentos: ");
			for(int j=0;j<listaServicios.get(i).docUtilizados.size();j++){
		/*		System.out.println("El documento " + listaServicios.get(i).docUtilizados.get(j).nombreDocumento + " ha sido utilizado " 
						+ listaServicios.get(i).docUtilizados.get(j).numeroUsos + " veces");                                                   */
			}
		}
	}
}

class DatosServicio{
	String nombreServicio = "";
	ArrayList<NombresUtilizados> docUtilizados; 
	DatosServicio(String nomServicio){
		nombreServicio = nomServicio;
		docUtilizados =new ArrayList<NombresUtilizados>();
		docUtilizados.add(new NombresUtilizados(InicioIanus.nombreDocumento));
	}
}

class NombresUtilizados{
	String nombreDocumento ="";
	int numeroUsos = 0;
	
	NombresUtilizados(String nombreDoc){
		nombreDocumento = nombreDoc;
		numeroUsos = 1;
	}
}