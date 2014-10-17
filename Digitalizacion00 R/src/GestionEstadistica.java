import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;


public class GestionEstadistica {

	ArrayList<String> listaServicios;
	ArrayList<String> listaNombresDocumentos;
	
	ConjuntoDatosEstadisticos[] cde;
	ArrayList<TablaServicioDocumentos> tsd = new ArrayList<TablaServicioDocumentos>();
	
	int tamañoTabla = 0;
		
	GestionEstadistica(){
		listaServicios = new ArrayList<String>();
		listaNombresDocumentos = new ArrayList<String>();
		
		this.getServiciosSubidos();
		this.getTablaEstadistica();
		this.getTablaDocPorServicio();
	}
	
	GestionEstadistica(Boolean estadisticaFinal){
		if(estadisticaFinal){
			listaServicios = new ArrayList<String>();
			listaNombresDocumentos = new ArrayList<String>();
			
			this.getServiciosSubidos();
			this.getTablaEstadistica();
			this.getTablaDocPorServicio();
		}
	}


	private void getServiciosSubidos(){
		
		int contador=0;
		for(int i=0; i<InicioIanus.estadisticaSantiago.size();i++){
			if(InicioIanus.estadisticaSantiago.get(i).nombreDocumento.equals("CMA")){
				System.out.println(++contador + "\t" + InicioIanus.estadisticaSantiago.get(i).nombreServicio + "\t" +
						InicioIanus.estadisticaSantiago.get(i).nombreDocumento + "\t" + InicioIanus.estadisticaSantiago.get(i).tamañoDocumento);
				
			}
		}
		
		System.out.println(contador);
		
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(int i=0; i<InicioIanus.estadisticaSantiago.size();i++){
			listaServicios.add(InicioIanus.estadisticaSantiago.get(i).nombreServicio);
			listaNombresDocumentos.add(InicioIanus.estadisticaSantiago.get(i).nombreDocumento);
		}
		
		HashSet<String> hsServicio = new HashSet<String>();
		hsServicio.addAll(listaServicios);
		HashSet<String> hsNombres = new HashSet<String>();
		hsNombres.addAll(listaNombresDocumentos);
		
		listaServicios.clear();
		listaNombresDocumentos.clear();
		listaServicios.addAll(hsServicio);
		listaNombresDocumentos.addAll(hsNombres);
		
		tamañoTabla = listaServicios.size();
		if(tamañoTabla < listaNombresDocumentos.size()){
			tamañoTabla = listaNombresDocumentos.size();
		}
		
		for(int i=0;i<listaServicios.size();i++){
			System.out.println("Servicio de " + listaServicios.get(i));
		}
		for(int i=0;i<listaNombresDocumentos.size();i++){
			System.out.println("Documento: " + listaNombresDocumentos.get(i));
		}

		
	}
	
	
	//	Recopila los datos estadisticos para almacenarlos en InicioIanus.conjuntoDatosEstadisticos. Se guardaría en la hoja 2 del fichero excel de estadisticas
	
	private void getTablaEstadistica(){
		cde = new ConjuntoDatosEstadisticos[listaNombresDocumentos.size()];
		for(int i=0; i<cde.length; i++){
			cde[i] = new ConjuntoDatosEstadisticos();
			cde[i].nombreDoc = listaNombresDocumentos.get(i);
			cde[i].maximo = 0;
			cde[i].minimo = 99999999;
			for(int j=0;j<InicioIanus.estadisticaSantiago.size();j++){
				if(InicioIanus.estadisticaSantiago.get(j).nombreDocumento.equals(cde[i].nombreDoc) ){
					cde[i].numeroDoc++;
					cde[i].media += InicioIanus.estadisticaSantiago.get(j).tamañoDocumento;
					if(cde[i].maximo<InicioIanus.estadisticaSantiago.get(j).tamañoDocumento){
						cde[i].maximo=InicioIanus.estadisticaSantiago.get(j).tamañoDocumento;
					}
					if(cde[i].minimo>InicioIanus.estadisticaSantiago.get(j).tamañoDocumento){
						cde[i].minimo=InicioIanus.estadisticaSantiago.get(j).tamañoDocumento;
					}
				}
			}
			cde[i].media = cde[i].media/cde[i].numeroDoc;
		}
		
		for(int i=0; i<cde.length; i++){
			cde[i].desviacion = 0;
			for(int j=0;j<InicioIanus.estadisticaSantiago.size();j++){
				if(InicioIanus.estadisticaSantiago.get(j).nombreDocumento.equals(cde[i].nombreDoc) ){
					cde[i].desviacion += Math.pow((InicioIanus.estadisticaSantiago.get(j).tamañoDocumento - cde[i].media),2);
				}
			}
			cde[i].desviacion = Math.sqrt(cde[i].desviacion/cde[i].numeroDoc);
			
			System.out.println(cde[i].nombreDoc + ": \t" + cde[i].numeroDoc + " documentos. Media: " + cde[i].media +
					"Desviación típica: " + cde[i].desviacion + ". Máximo: " + cde[i].maximo + ". Mínimo: " + cde[i].minimo);
		}
		
		InicioIanus.conjuntoDatosEstadisticos = cde;
		
	}

	private void getTablaDocPorServicio(){

		String nombreServicioAuxiliar = "";
		String nombreDocumentoAuxiliar = "";
		int numeroSubidos = 0;
		
			for(int i=0;i<listaServicios.size();i++){
				for(int j=0; j<listaNombresDocumentos.size();j++){
					for(int z=0; z<InicioIanus.estadisticaSantiago.size();z++){
						if(InicioIanus.estadisticaSantiago.get(z).nombreServicio.equals(listaServicios.get(i)) && 
								InicioIanus.estadisticaSantiago.get(z).nombreDocumento.equals(listaNombresDocumentos.get(j))){
									nombreServicioAuxiliar = listaServicios.get(i);
									nombreDocumentoAuxiliar = listaNombresDocumentos.get(j);
									numeroSubidos++;
						}
					}
					if(numeroSubidos != 0){
						tsd.add(new TablaServicioDocumentos(nombreServicioAuxiliar,nombreDocumentoAuxiliar,numeroSubidos));
					}
					numeroSubidos=0;
				}
	
			}

		InicioIanus.tablaServiciosDocumentos = tsd;	
	/*	
		for(int i=0; i<tsd.size();i++){
			
			System.out.println(tsd.get(i).nombreServicio + "\t" + tsd.get(i).nombreDocumento + ": \t" + tsd.get(i).numeroSubidos + " veces subido.");
		}
	*/	
		for(int i=0; i<tsd.size();i++){
			
			System.out.println(InicioIanus.tablaServiciosDocumentos.get(i).nombreServicio + "\t" + InicioIanus.tablaServiciosDocumentos.get(i).nombreDocumento + ": \t" + InicioIanus.tablaServiciosDocumentos.get(i).numeroSubidos + " veces subido.");
		}
		
	}
}
