import java.io.File;
import java.io.FileFilter;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;

import javax.swing.JOptionPane;


public class MoverCarpetas {
	
	public boolean moverPdf(int numPdf, String comentario, Boolean apartar){
		
		File firmados;
		if(InicioIanus.documentacion != 0 && InicioIanus.documentacion != 3){
			firmados = new File(InicioIanus.RUTA);
			if(!firmados.exists()){
				firmados = new File(InicioIanus.RUTAB);
			}
    	}else{
    		System.out.println("Apartados urgencias");
			firmados = new File(InicioIanus.RUTAURG);
			if(!firmados.exists()){
				firmados = new File(InicioIanus.RUTAURGB);
			}
    	}
		
		

		if(firmados.exists()){
			if(!apartar){	
				int indice;
				String aux;
				indice = firmados.getAbsolutePath().toString().lastIndexOf("\\");
				aux = firmados.getAbsolutePath().toString().substring(0, indice);
				if(InicioIanus.documentacion != 0 && InicioIanus.documentacion != 3){

					aux+="\\99 Dudas";
				}else{
					aux+="\\00 documentacion\\99 dudas";
				}
				aux+="\\" + InicioIanus.usuario;
				
				File dudas = new File(aux);
				if(!dudas.exists()){
					dudas.mkdir();
				}
				String nombrePdf = InicioIanus.rutaCompletaPdfs[numPdf];
				indice = nombrePdf.lastIndexOf("\\");
				nombrePdf = nombrePdf.substring(indice+1);
				aux+= "\\" + comentario + "¿ " + nombrePdf;
							
				dudas = new File(aux);
				File pdfDuda = new File(InicioIanus.rutaCompletaPdfs[numPdf]);
				boolean renombrado = pdfDuda.renameTo(dudas);
				
				if(!renombrado){
					JOptionPane.showMessageDialog(null, "No se ha podido mover el fichero");
				}else{
					JOptionPane.showMessageDialog(null, "Pdf movido a dudas");
				}
				
				return renombrado;
			}else{
				String aux = firmados.getAbsolutePath().toString();
				aux+= "\\Apartado por " + InicioIanus.usuario + ". Pendiente";
				
				File apartado = new File(aux);
				if(!apartado.exists()){
					apartado.mkdir();
				}
				String nombrePdf = InicioIanus.rutaCompletaPdfs[numPdf];
				int indice = nombrePdf.lastIndexOf("\\");
				nombrePdf = nombrePdf.substring(indice+1);
				aux+= "\\" + comentario + ". " + nombrePdf;
				
				apartado = new File(aux);
				File pdfApartado = new File(InicioIanus.rutaCompletaPdfs[numPdf]);
				boolean renombrado = pdfApartado.renameTo(apartado);
				
				if(!renombrado){
					JOptionPane.showMessageDialog(null, "No se ha podido mover el fichero");
				}else{
					JOptionPane.showMessageDialog(null, "Pdf movido a la carpeta Apartados");
				}
				
				return renombrado;
				
			}
		}
		return false;
	}
	
	
	public void moverPdfs(String rutaCarpeta, int hastaAqui){

		int indice = rutaCarpeta.lastIndexOf("\\");
		String ruta = rutaCarpeta.substring(0, indice);
		
		File rutaVieja = new File(ruta);
		
		FileFilter filtro = new FileFilter(){

			@Override
			public boolean accept(File fichero) {
				// TODO Auto-generated method stub
				
				if(!fichero.isDirectory()){
					return true;
				}
				return false;
			}
		};
		
		File[] archivosRutaVieja = rutaVieja.listFiles(filtro);
		if(archivosRutaVieja == null){
			JOptionPane.showMessageDialog(null, "No hay ficheros para mover");
		}
		
		int contadorM = 0;
		int contadorE = 0;
		String rutaYaSubidos = ruta + "\\Ya subidos " + InicioIanus.usuario;
		
		int index;
		String aux;
		String rutaNueva;
		
		File directorio = new File(rutaYaSubidos);
		directorio.mkdir();
		
		int numFicheros = archivosRutaVieja.length;
		for(int i=0;i<=hastaAqui;i++){
			aux = archivosRutaVieja[i].getName();
	//		aux = archivosRutaVieja[0].getAbsolute
			rutaNueva = rutaYaSubidos + "\\" + aux;
			
			File nombreNuevo = new File(rutaNueva);
			boolean renombrado = archivosRutaVieja[i].renameTo(nombreNuevo);

			if(renombrado){
				contadorM++;
			}else{
				contadorE++;
			}
		}
		if(contadorE == 0)
			JOptionPane.showMessageDialog(null, "Se han movido todos los pdfs: " + contadorM);
		else
			JOptionPane.showMessageDialog(null, "Se han movido " + contadorM + " pdfs.\n No se han podido mover " + contadorE
					+ ". Revisa la carpeta. ");

		File carpetaVieja = new File(ruta);
		int j= ruta.lastIndexOf(InicioIanus.usuario);
		ruta = ruta.substring(0, j);

		File carpetaNueva = new File(ruta);
		boolean renombrado = carpetaVieja.renameTo(carpetaNueva);
		if(!renombrado){
			JOptionPane.showMessageDialog(null, "No se ha podido renombrar la carpeta");
		}
		//System.exit(0);
	}
	
	public void mover(){
		File directorioFirmados = new File(InicioIanus.RUTA);
		if(!directorioFirmados.exists()){
			directorioFirmados = new File(InicioIanus.RUTAB);
		}
		if(directorioFirmados.exists()){

			FileFilter filtro = new FileFilter(){
	
				@Override
				public boolean accept(File fichero) {
					// TODO Auto-generated method stub
					
					if(fichero.isDirectory()){
						if(fichero.getName().endsWith(InicioIanus.usuario)){
							return true;
						}
					}
					return false;
				}
				
			};
			
			File[] directoriosUsuario = directorioFirmados.listFiles(filtro);
			if(directoriosUsuario == null){
				JOptionPane.showMessageDialog(null, "No hay directorios");
			}
				
			File asociados = new File(InicioIanus.RUTA);
			if(!asociados.exists()){
				asociados = new File(InicioIanus.RUTAB);
				}
			if(asociados.exists()){
				
				int contadorMovidos=0;
				int contadorError=0;
				
				String rutaAsociados = asociados.getAbsolutePath();
				int indice = rutaAsociados.lastIndexOf("\\");
				rutaAsociados = rutaAsociados.substring(0, indice);
				rutaAsociados+="\\04 Asociado";

				rutaAsociados = devuelveFecha(rutaAsociados);
				
				int numDirectorios = directoriosUsuario.length;
				for(int j=0; j<numDirectorios;j++){
					String rutaNueva = rutaAsociados+ "\\" + directoriosUsuario[j].getName();

					String soloRutaDirectorio = rutaNueva.substring(0,rutaNueva.lastIndexOf("\\"));
					//System.out.println("Solo ruta del directorio... " + soloRutaDirectorio);
					
					File directorio = new File(soloRutaDirectorio);
					boolean creaDirectorio = directorio.mkdirs();
					
					File nombreNuevo = new File(rutaNueva);
					boolean renombrado = directoriosUsuario[j].renameTo(nombreNuevo);

					if(renombrado){
						//	Se asigna la nueva ruta
						//	JOptionPane.showMessageDialog( null,"Exito.");
						contadorMovidos++;
					}else{
						//	JOptionPane.showMessageDialog( null,"Directorio en uso. No se ha podido mover.");
						contadorError++;
					}
				}

				JOptionPane.showMessageDialog(null, "Se han movido " + contadorMovidos + " carpetas.\n No se han podido mover " + contadorError);

		//			File nombreNuevo = new File(asociados)
					
			}else{
				JOptionPane.showMessageDialog(null, "No se pudo mover las carpetas.");
			}
		}else{
			JOptionPane.showMessageDialog(null, "No se pudo mover las carpetas.");
		}
	

	}	
	
	
	public void moverCarpetasUrg(){
		

		File carpetaAsubir;
		File asociadosUrg;
		
		String cadena = ":\\DIGITALIZACIÓN\\01 INFORMES URG (Colectiva)\\04 Asociado";
		String rutaAsociados = "";
		String letra = "";
		
		int contadorMovidos=0;
		int contadorError=0;
				
		//	Quitamos duplicados del arraylist de directorios
		
		HashSet<String> hs = new HashSet();
		hs.addAll(InicioIanus.carpetasAbiertas);
		InicioIanus.carpetasAbiertas.clear();
		InicioIanus.carpetasAbiertas.addAll(hs);
			
		FileFilter filtro = new FileFilter(){
			
			@Override
			public boolean accept(File fichero) {
				// TODO Auto-generated method stub
				
				if(fichero.isDirectory()){
					if(fichero.getName().endsWith(InicioIanus.usuario)){
						return true;
					}
				}
				return false;
			}
			
		};
		
		int numDirectorios;
		for(int i=0;i<InicioIanus.carpetasAbiertas.size();i++){
			
			if(i==0){
				letra = InicioIanus.carpetasAbiertas.get(i).substring(0, 1);
				rutaAsociados = letra + cadena;
				rutaAsociados = devuelveFecha(rutaAsociados);
				// System.out.println(rutaAsociados);
			}
			
			 carpetaAsubir = new File(InicioIanus.carpetasAbiertas.get(i));
			 
			 File[] directoriosUsuario = carpetaAsubir.listFiles(filtro);
			 if(directoriosUsuario == null){
					JOptionPane.showMessageDialog(null, "No hay directorios");
			 }

				
			 numDirectorios = directoriosUsuario.length;
			 for(int j=0; j<numDirectorios;j++){
				 
					String rutaNueva = rutaAsociados+ "\\" + directoriosUsuario[j].getName();
			//		System.out.println(rutaNueva);
			//		System.out.println(directoriosUsuario[j].getName());

					File nombreNuevo = new File(rutaNueva);
		/*			if(!nombreNuevo.exists()){
						System.out.println("Hola hola");
						nombreNuevo.mkdirs();
					}
		*/
					
			/*		
					File pruebaViejo = new File("H:\\DIGITALIZACIÓN\\01 INFORMES URG (Colectiva)\\00 ACTUAL 2012\\00 Fin de Semana\\02 Firmado\\No tocar. FS. Pruebas Man");
					File pruebaNuevo = new File("H:\\DIGITALIZACIÓN\\01 INFORMES URG (Colectiva)\\05 Asociado\\2013\\01 Enero\\No tocar. FS. Pruebas Man");
					
					boolean renombrando = pruebaViejo.renameTo(pruebaNuevo);
					if(renombrando){
						System.out.println("Renombrado");
					}
					else{
						System.out.println("No renombrado");
					}
			*/ 
					
					
			//		System.out.println("Nombre nuevo:   " + nombreNuevo.getAbsolutePath());
			//		System.out.println("Nombre antiguo: " + directoriosUsuario[j].getAbsolutePath());
			/*		if(directoriosUsuario[j].exists()){
						System.out.println("El fichero origen exite");
					}
			*/		
					boolean renombrado = directoriosUsuario[j].renameTo(nombreNuevo);

					if(renombrado){
						//	Se asigna la nueva ruta
						//	JOptionPane.showMessageDialog( null,"Exito.");
						contadorMovidos++;
					}else{
						//	JOptionPane.showMessageDialog( null,"Directorio en uso. No se ha podido mover.");
						contadorError++;
					}
				}

			 JOptionPane.showMessageDialog(null, "Se han movido " + contadorMovidos + " carpetas.\n No se han podido mover " + contadorError);
		}

	}	
	
	
	public void moverDudas(){	
		
		File directorio;
		directorio = new File(InicioIanus.RUTA);
		if(!directorio.exists()){
				directorio = new File(InicioIanus.RUTAB);
		}
		
		if(directorio.exists()){
			if(InicioIanus.dudasParaAsociar){
				String rutaV = directorio.getAbsolutePath();

				System.out.println("Ruta vieja..." + rutaV);
				int indice = rutaV.lastIndexOf("\\");
				rutaV = rutaV.substring(0, indice);
				String rutaN;
				String rutaAux;
				if(InicioIanus.documentacion == 0 || InicioIanus.documentacion == 3){
					indice = rutaV.lastIndexOf("\\");
					rutaAux = rutaV.substring(0,indice);
					rutaAux += "\\01 INFORMES URG (Colectiva)";
					System.out.println(rutaAux);
					rutaN = rutaAux;
				}else{
					rutaN = rutaV;
				}
				
				rutaV+= ("\\99 Dudas\\" + InicioIanus.usuario + " C " +InicioIanus.usuario);
				rutaN+= ("\\04 Asociado");
				
				File prueba = new File(rutaN);
				if(prueba.exists())
					System.out.println("La ruta de momento existe");
				else
					System.out.println("La ruta no existe.");
				
				rutaN = devuelveFecha(rutaN);
				Calendar fecha = Calendar.getInstance();
	
				rutaN+= ("\\" + InicioIanus.usuario + " "  + fecha.get(Calendar.DAY_OF_YEAR) + " " + fecha.get(Calendar.HOUR_OF_DAY)  );

				File rutaVieja = new File(rutaV);
				
				FileFilter filtro = new FileFilter(){
					public boolean accept(File fichero){
						if(!fichero.isDirectory()){
							return true;
						}
						return false;
					}
				};
				
				File[] archivosDudas = rutaVieja.listFiles(filtro);
				if(archivosDudas == null){
					JOptionPane.showMessageDialog(null, "No hay archivos de dudas para mover");
				}
				
				int contadorM=0;
				int contadorE=0;
						
				
				
				File rutaNueva = new File(rutaN);
				if(!rutaNueva.exists()){
					if(rutaNueva.mkdir())
						System.out.println("Directorio Creado");
					else
						System.out.println("Directorio no creado");
				}

				String aux;
				String rutaNv;
				int numFicheros = archivosDudas.length;
				for(int i=0;i<numFicheros;i++){
					aux = archivosDudas[i].getName();
					rutaNv = rutaN + "\\" + aux;
					rutaNueva = new File(rutaNv);
					boolean dudaMovidas = archivosDudas[i].renameTo(rutaNueva);
					if(dudaMovidas){
						contadorM++;
					}else{
						contadorE++;
					}
				}
				
				if(contadorE ==0)
					JOptionPane.showMessageDialog(null, "Se han movido todos los pdfs: " + contadorM);
				else
					JOptionPane.showMessageDialog(null, "Se han movido " + contadorM + " pdfs.\n No se han podido mover " + contadorE
							+ ". Revisa la carpeta. ");	
				
				rutaVieja.delete();
				
			}
		}

	}	
	
	public String devuelveFecha(String rutaAsociados){
	
		Calendar fecha = Calendar.getInstance();
		int año = fecha.get(Calendar.YEAR);
		rutaAsociados+=("\\" + año);
		int mes = fecha.get(Calendar.MONTH);
		String nombreMes = "";
		switch(mes){
		case 0:	nombreMes = "01 Enero";break;
		case 1:	nombreMes = "02 Febrero";break;
		case 2:	nombreMes = "03 Marzo";break;
		case 3:	nombreMes = "04 Abril";break;
		case 4:	nombreMes = "05 Mayo";break;
		case 5:	nombreMes = "06 Junio";break;
		case 6:	nombreMes = "07 Julio";break;
		case 7:	nombreMes = "08 Agosto";break;
		case 8:	nombreMes = "09 Septiembre";break;
		case 9:	nombreMes = "10 Octubre";break;
		case 10:	nombreMes = "11 Noviembre";break;
		case 11:	nombreMes = "12 Diciembre";break;
		}
		rutaAsociados+=("\\" + nombreMes);
			
		return rutaAsociados;
		
	}

}

