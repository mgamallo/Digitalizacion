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


public class GuardarEstadisticas {
	int numFilaUser;
	int indicePantallaIanus;
	
	int filaDocumentoNuevo;
	
	Calendar calendario;
	
	public GuardarEstadisticas(String archivoDestino){

		try{
			WorkbookSettings wbSettings = new WorkbookSettings();
            wbSettings.setEncoding("ISO-8859-1");
            wbSettings.setLocale(new Locale("es", "ES"));
            wbSettings.setExcelDisplayLanguage("ES"); 
            wbSettings.setExcelRegionalSettings("ES"); 
            wbSettings.setCharacterSet(CountryCode.SPAIN.getValue());
	
            Workbook archivoExcel = Workbook.getWorkbook(new File(archivoDestino));
           
            WritableWorkbook libroEscritura = Workbook.createWorkbook(new File(archivoDestino), archivoExcel);

            archivoExcel.close();
            
            WritableSheet hojaE = libroEscritura.getSheet(12);


            //---------------------------------------------------------------------------------------------------------------------/
	
            
            //----------------------------------------------  Guardar el documento nuevo si es el caso ----------------------------/
  
            int columna = 0;
            int fila = 0;
            
            jxl.write.Number numero; 
            jxl.write.Label texto;
            
            System.out.println("Numero de servicios: " + InicioIanus.excel.numServicios);
            System.out.println("Numero de Documentos: " + InicioIanus.excel.numDocumentos);
            
            
            for(int i=0;i<InicioIanus.estadistica.listaServicios.size();i++){
            	for(int j=0;j<InicioIanus.excel.numServicios;j++){
            		if(InicioIanus.estadistica.listaServicios.get(i).nombreServicio.equals(InicioIanus.excel.tablaDocumentos[0][j+2].toString())){
            			columna = j + 2;
            			System.out.println("Columna: " + columna + " Servicio: " + InicioIanus.estadistica.listaServicios.get(i).nombreServicio);
            			for(int z=0;z<InicioIanus.estadistica.listaServicios.get(i).docUtilizados.size();z++){
                       		for(int k=0;k<InicioIanus.excel.numDocumentos;k++){
                       			if(InicioIanus.estadistica.listaServicios.get(i).docUtilizados.get(z).nombreDocumento.equals(InicioIanus.excel.tablaDocumentos[k+1][0].toString())){
                       				fila = k +1 ;
                        			System.out.print("Fila: " + fila + " Documento: " + InicioIanus.estadistica.listaServicios.get(i).docUtilizados.get(z).nombreDocumento);
                       				System.out.println(" Número de veces: " + InicioIanus.estadistica.listaServicios.get(i).docUtilizados.get(z).numeroUsos);
                        	//		numero = new jxl.write.Number(columna, fila, InicioIanus.estadistica.listaServicios.get(j).docUtilizados.get(k).numeroUsos);
                       		//		hojaE.addCell(numero);
                       		//		break;
                       			}
                      		}
                    	}
            	//	break;
            		}
            	}
            }
            
  /*          
                	aux = InicioIanus.excel.tablaDocumentos[i][0].toString().compareToIgnoreCase(doc.nombre);

                        texto = new jxl.write.Label(0,i,doc.nombre);
                        hojaE.addCell(texto);

                numero = new jxl.write.Number(12,numFilas4,filaDocumentoNuevo);
                hojaE.addCell(numero);
           
    */        
            
  
        
    //        libroEscritura.write();
            libroEscritura.close();           
                   
	
		}catch(Exception ioe){
			// ioe.printStackTrace();
			JOptionPane.showMessageDialog(null, "Fichero en uso. No se puede guardar la nueva norma");
		}
	
	}
}
