import java.io.File;
import java.util.Calendar;
import java.util.Locale;

import javax.swing.JOptionPane;

import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.biff.CountryCode;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;


public class GuardarDocumentosNuevos {
	int numFilaUser;
	int indicePantallaIanus;
	
	int filaDocumentoNuevo;
	
	Calendar calendario;
	
	public GuardarDocumentosNuevos(String archivoDestino, DocumentoNuevo doc, String tipoDeNovedad){

		try{
			WorkbookSettings wbSettings = new WorkbookSettings();
            wbSettings.setEncoding("ISO-8859-1");
            wbSettings.setLocale(new Locale("es", "ES"));
            wbSettings.setExcelDisplayLanguage("ES"); 
            wbSettings.setExcelRegionalSettings("ES"); 
            wbSettings.setCharacterSet(CountryCode.SPAIN.getValue());
	
            Workbook archivoExcel = Workbook.getWorkbook(new File(archivoDestino));
            Sheet hoja = archivoExcel.getSheet(4);

            int numFilas4 = hoja.getRows();
           
            WritableWorkbook libroEscritura = Workbook.createWorkbook(new File(archivoDestino), archivoExcel);

            archivoExcel.close();
            
            
            //---------------------------------------------- Guardamos los datos en la hoja 4, visor -----------------------------/
            WritableSheet hojaE = libroEscritura.getSheet(4);
            
            int aux = doc.nombreImagen.lastIndexOf(".");
            
            jxl.write.Number numero; 
            jxl.write.Label texto = new jxl.write.Label(0,numFilas4,doc.nombreImagen.substring(0,aux));
            hojaE.addCell(texto);
            texto = new jxl.write.Label(1,numFilas4,doc.nombre);
            hojaE.addCell(texto);
            
            //----------------------------------------------  Cambiar más adelante ------------------------------------
    //        texto = new jxl.write.Label(2,numFilas4,doc.serviciosAfectados.get(0));
    //        hojaE.addCell(texto);
            //---------------------------------------------------------------------------------------------------------

            if(doc.color.contains("Ninguno")){
                texto = new jxl.write.Label(3,numFilas4,"N");
            }else{
            	 texto = new jxl.write.Label(3,numFilas4,doc.color);
            }
            hojaE.addCell(texto);
            
            if(doc.apariencia.contains("Ninguna")){
                texto = new jxl.write.Label(4,numFilas4,"N");
            }else{
            	 texto = new jxl.write.Label(4,numFilas4,doc.apariencia);
            }           
            hojaE.addCell(texto);
            
            texto = new jxl.write.Label(5,numFilas4,doc.observaciones);
            hojaE.addCell(texto);
            
            for(int i= 0;i<doc.metaDatos.size();i++){
                texto = new jxl.write.Label(6 + i,numFilas4,doc.metaDatos.get(i));
                hojaE.addCell(texto);
            }
            
            texto = new jxl.write.Label(11,numFilas4,doc.nombre);
            hojaE.addCell(texto);
            
            if(!tipoDeNovedad.contains("Documento")){
                texto = new jxl.write.Label(12,numFilas4,"N");
                hojaE.addCell(texto);
            }



            //---------------------------------------------------------------------------------------------------------------------/
	
            
            //----------------------------------------------  Guardar el documento nuevo si es el caso ----------------------------/
            
            hojaE = libroEscritura.getSheet(0);
            aux = 0;
            if(tipoDeNovedad.contains("Documento")){
            	for(int i=1;i<InicioIanus.excel.numDocumentos;i++){
                	aux = InicioIanus.excel.tablaDocumentos[i][0].toString().compareToIgnoreCase(doc.nombre);
                	if(aux == 0){
                		break;
                	}else if(aux > 0){
                		filaDocumentoNuevo = i;
                        hojaE.insertRow(i);
                        texto = new jxl.write.Label(0,i,doc.nombre);
                        hojaE.addCell(texto);

                        int z = 0;
                        for(int j=0;j<doc.serviciosAfectados.size();j++){
                        	while(z < InicioIanus.excel.numServicios){
                        		if(InicioIanus.excel.tablaDocumentos[0][z+2].toString().contains(doc.serviciosAfectados.get(j))){
                        	        texto = new jxl.write.Label(z+2,i,"x");
                        	        hojaE.addCell(texto);
                        		//	System.out.print("j: " + j + "  z: " + z);
                            		break;
                        		}
                        		z++; 
                        	}
                        }   
                   		break;
                	}
            	}
            	hojaE = libroEscritura.getSheet(4);
                numero = new jxl.write.Number(12,numFilas4,filaDocumentoNuevo);
                hojaE.addCell(numero);
            }
            
            
            //---------------------------------------------------- Guardar el aviso ---------------------------------------------/
            
            hojaE = libroEscritura.getSheet(9);                      // Modelo Nuevo
            int numFilas9 = hojaE.getRows();
            														//insertar celdas avisos

            hojaE.insertColumn(1);
          	for(int j=0;j<numFilas9-1;j++){
          		numero = new jxl.write.Number(1,j+1,numFilas4);
                  hojaE.addCell(numero);
          	}
        
            libroEscritura.write();
            libroEscritura.close();           
                   
	
		}catch(Exception ioe){
			// ioe.printStackTrace();
			JOptionPane.showMessageDialog(null, "Fichero en uso. No se puede guardar la nueva norma");
		}
	
	}
}
