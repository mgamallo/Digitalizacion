import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Scanner;

import javax.swing.JOptionPane;


public class FicheroTXT {

	void escribeTXT(){
		FileWriter fichero = null;
		PrintWriter pw = null;
		
		String ruta = "FicherosTXT/";
		ruta += this.devuelveFecha();
		try{
			
			File fichero0 = new File(ruta);
			if(!fichero0.exists()){
				System.out.println("Fichero no existe");
				File ficheroCoordenadas = new File("Coordenadas.xls");
				File ficheroDocumentos = new File("Documentos.xls");
				File CoordenadasCopia = new File("copia seguridad documentos/automaticas/Coordenadas " 
						+ devuelveSoloFecha() + ".xls");
				File DocumentosCopia = new File("copia seguridad documentos/automaticas/Documentos " 
						+ devuelveSoloFecha() + ".xls");
				
				InputStream inC = new FileInputStream(ficheroCoordenadas);
				InputStream inD = new FileInputStream(ficheroDocumentos);
				OutputStream outC = new FileOutputStream(CoordenadasCopia);
				OutputStream outD = new FileOutputStream(DocumentosCopia);
				
				byte[] buf = new byte[1024];
				int len;
				
				while((len = inC.read(buf)) > 0){
					outC.write(buf, 0, len);
				}
				while((len = inD.read(buf)) > 0){
					outD.write(buf, 0, len);
				}
				inC.close();
				inD.close();
				outC.close();
				outD.close();
			}
			
			fichero = new FileWriter(ruta,true);
			pw = new PrintWriter(fichero);
			
			int tam = InicioIanus.estadisticaSantiago.size();
			for(int i=0;i<tam;i++){
				pw.println(InicioIanus.estadisticaSantiago.get(i).nombreServicio + "#" 
							+ InicioIanus.estadisticaSantiago.get(i).nombreDocumento + "#"
							+ InicioIanus.estadisticaSantiago.get(i).tamañoDocumento);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				//	Nuevamente aprovechamos el finally para
				//	asegurarnos que se cierra el fichero.
				if(null != fichero){
					fichero.close();
				}
			}catch(Exception e2){
				e2.printStackTrace();
			}
		}
	}
	
	void eliminaTxtErrores(){
		File directorio = new File("H:\\DIGITALIZACIÓN\\00 DOCUMENTACION\\99 Nombres Normalizados");
		if(!directorio.exists()){
			directorio = new File("j:\\DIGITALIZACIÓN\\00 DOCUMENTACION\\99 Nombres Normalizados");
		}
		//System.out.println("La ruta es: " + directorio.getAbsolutePath());
				
		File[] txtErrores = directorio.listFiles(new FilenameFilter(){
			public boolean accept(File directorio, String name){
				return name.toLowerCase().endsWith(".log");
			}
		});
		
		if(txtErrores != null){
			//System.out.println(txtErrores.length + "es el numero de logs");
			
			for(int i=0;i<txtErrores.length;i++){
				if(txtErrores[i].getName().contains("hs_err_pid")){
						txtErrores[i].delete();
				}
			}
		}
		
		
		
	}
	
	void abreFicherosTXT(){

			File directorio = new File("FicherosTXT");
			if(directorio.exists()){
				File[] pdfs = directorio.listFiles();
				/*		File[] pdfs = directorio.listFiles(new FilenameFilter(){
								public boolean accept(File directorio, String name){
									return name.toLowerCase().contains("NO BORRAR");
								}
						});
			*/
						
						for(int i=0;i< pdfs.length;i++){
							this.leeTXT(pdfs[i].getAbsolutePath());
						}
			}

			
			
	}
	
	void leeTXT(String ruta){
				
		System.out.println(ruta);
		
		File f = new File(ruta);
		Scanner s;
		try{
			s= new Scanner(f);
			while(s.hasNextLine()){
				String linea = s.nextLine();
				Scanner sl = new Scanner(linea);
				sl.useDelimiter("#");
				DatoEstadisticoSantiago des = new DatoEstadisticoSantiago();
				des.nombreServicio = sl.next(); System.out.print(des.nombreServicio);
				des.nombreDocumento = sl.next();
				des.tamañoDocumento = Double.parseDouble(sl.next());System.out.println("\t" + des.tamañoDocumento);
				
				InicioIanus.lecturaDatosEstadiscosSantiago.add(des);		//	Se pasará a InicioIanus.estadisticaSantiago
				
			}
			s.close();
		/*	
			for(int i = 0; i<InicioIanus.lecturaDatosEstadiscosSantiago.size();i++){
				System.out.println(i + "\t" + InicioIanus.lecturaDatosEstadiscosSantiago.get(i).nombreServicio + "\t" +
												InicioIanus.lecturaDatosEstadiscosSantiago.get(i).nombreDocumento + "\t" +
												InicioIanus.lecturaDatosEstadiscosSantiago.get(i).tamañoDocumento);
			}
		*/
			
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}
	}
	
	
	String devuelveFecha(){

		String nombreArchivo = "NO BORRAR ";
		
		Calendar fecha = Calendar.getInstance();
		int año = fecha.get(Calendar.YEAR);
		nombreArchivo += año;
		int mes = fecha.get(Calendar.MONTH);
		nombreArchivo += (" 0" + (++mes));
		int dia = fecha.get(Calendar.DATE);
		if(dia<10){
			nombreArchivo += (" 0" + dia + ".txt");
		}else{
			nombreArchivo += (" " + dia + ".txt");
		}
		
		
			
		return nombreArchivo;
		
	}
	
	
	String devuelveSoloFecha(){
		Calendar fecha = Calendar.getInstance();
		int año = fecha.get(Calendar.YEAR);
		String cadenaFecha = "";
		cadenaFecha += año;
		int mes = fecha.get(Calendar.MONTH);
		cadenaFecha += mes;
		int dia = fecha.get(Calendar.DATE);
		
		if(dia<10){
			cadenaFecha += ("0" + dia);
		}else{
			cadenaFecha += (dia);
		}
		
		return cadenaFecha;
	}
}


