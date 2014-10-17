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


public class GuardarNormas {
	int numFilaUser;
	int indicePantallaIanus;
	Calendar calendario;
	
	//	Constructor para modificar y guardar normas
	public GuardarNormas(String archivoDestino, String contenidoNorma, String rutaJpg, Object[] servicios, int filaModificada){

		try{
			WorkbookSettings wbSettings = new WorkbookSettings();
            wbSettings.setEncoding("ISO-8859-1");
            wbSettings.setLocale(new Locale("es", "ES"));
            wbSettings.setExcelDisplayLanguage("ES"); 
            wbSettings.setExcelRegionalSettings("ES"); 
            wbSettings.setCharacterSet(CountryCode.SPAIN.getValue());
	
            Workbook archivoExcel = Workbook.getWorkbook(new File(archivoDestino));
            Sheet hoja = archivoExcel.getSheet(6);

            int numFilas6 = hoja.getRows();
            hoja = archivoExcel.getSheet(3);
            int numFilas3 = hoja.getRows();
           
            WritableWorkbook libroEscritura = Workbook.createWorkbook(new File(archivoDestino), archivoExcel);

            archivoExcel.close();
            
            WritableSheet hojaE = libroEscritura.getSheet(6);   // Hoja normas
            hojaE.removeRow(filaModificada);
            hojaE.insertRow(filaModificada);
            
            jxl.write.Label texto;
            int i=0;
            if(servicios != null){
	            for(i=0;i<servicios.length;i++){
	                texto = new jxl.write.Label(i + 3,filaModificada,servicios[i].toString());		//	col, fil , val
	                hojaE.addCell(texto);
	            }
            }
            texto = new jxl.write.Label(i + 3,filaModificada,"N");		//	col, fil , val
            hojaE.addCell(texto);
  												
            texto = new jxl.write.Label(1,filaModificada,contenidoNorma);		//	col, fil , val
            hojaE.addCell(texto);
            if(rutaJpg.isEmpty()){
            	texto = new jxl.write.Label(2,filaModificada,"Sin Imagen.jpg");
            }else{
                texto = new jxl.write.Label(2,filaModificada,rutaJpg);
            }
            hojaE.addCell(texto);
      
            calendario = calendario.getInstance();
            
            String dia = Integer.toString(calendario.get(Calendar.DATE));
            String mes = Integer.toString(calendario.get(Calendar.MONTH) + 1);
            String año = Integer.toString(calendario.get(Calendar.YEAR));

            String fechaNorma = dia +"/" + mes + "/" + año;
            texto = new jxl.write.Label(0,filaModificada,fechaNorma);		//	col, fil , val
            hojaE.addCell(texto);	  
               
            hojaE = libroEscritura.getSheet(3);                      // Usuarios
            hojaE.insertColumn(37);
            
            jxl.write.Number numero;
            texto = new jxl.write.Label(37,0,"Novedades");
            hojaE.addCell(texto);
            texto = new jxl.write.Label(38,0,"");
            hojaE.addCell(texto);
            
            //insertar columna
            for(i=0;i<numFilas3-2;i++){
                numero = new jxl.write.Number(37,i+2,filaModificada);
                hojaE.addCell(numero);
            }
            		
            libroEscritura.write();
            libroEscritura.close();           
                   
    		InicioIanus.excel.leerExcel(InicioIanus.NOMBRE_FICHERO_EXCEL);
	
		}catch(Exception ioe){
			// ioe.printStackTrace();
			JOptionPane.showMessageDialog(null, "Fichero en uso. No se puede guardar la nueva norma");
		}
	}
	
	//	Constructor para añadir normas
	public GuardarNormas(String archivoDestino, String contenidoNorma, String rutaJpg, Object[] servicios){

		try{
			WorkbookSettings wbSettings = new WorkbookSettings();
            wbSettings.setEncoding("ISO-8859-1");
            wbSettings.setLocale(new Locale("es", "ES"));
            wbSettings.setExcelDisplayLanguage("ES"); 
            wbSettings.setExcelRegionalSettings("ES"); 
            wbSettings.setCharacterSet(CountryCode.SPAIN.getValue());
	
            Workbook archivoExcel = Workbook.getWorkbook(new File(archivoDestino));
            Sheet hoja = archivoExcel.getSheet(6);

            int numFilas6 = hoja.getRows();
            hoja = archivoExcel.getSheet(3);
            int numFilas3 = hoja.getRows();
           
            WritableWorkbook libroEscritura = Workbook.createWorkbook(new File(archivoDestino), archivoExcel);

            archivoExcel.close();
            
            WritableSheet hojaE = libroEscritura.getSheet(6);
            
            jxl.write.Label texto;
            int i=0;
            if(servicios != null){
                for(i=0;i<servicios.length;i++){
                    texto = new jxl.write.Label(i + 3,numFilas6,servicios[i].toString());		//	col, fil , val
                    hojaE.addCell(texto);
                }
            }
 
            texto = new jxl.write.Label(i + 3,numFilas6,"N");		//	col, fil , val
            hojaE.addCell(texto);
  												
            texto = new jxl.write.Label(1,numFilas6,contenidoNorma);		//	col, fil , val
            hojaE.addCell(texto);
            if(rutaJpg.isEmpty()){
            	texto = new jxl.write.Label(2,numFilas6,"Sin Imagen.jpg");
            }else{
                texto = new jxl.write.Label(2,numFilas6,rutaJpg);
            }
            hojaE.addCell(texto);
      
            calendario = calendario.getInstance();
            
            String dia = Integer.toString(calendario.get(Calendar.DATE));
            String mes = Integer.toString(calendario.get(Calendar.MONTH));
            String año = Integer.toString(calendario.get(Calendar.YEAR));

            String fechaNorma = dia +"/" + mes + "/" + año;
            texto = new jxl.write.Label(0,numFilas6,fechaNorma);		//	col, fil , val
            hojaE.addCell(texto);	  
            
            hojaE = libroEscritura.getSheet(3);                      // Usuarios
            hojaE.insertColumn(37);
            
            jxl.write.Number numero;
            texto = new jxl.write.Label(37,0,"Novedades");
            hojaE.addCell(texto);
            texto = new jxl.write.Label(38,0,"");
            hojaE.addCell(texto);
            
            //insertar columna
            for(i=0;i<numFilas3-2;i++){
                numero = new jxl.write.Number(37,i+2,numFilas6);
                hojaE.addCell(numero);
            }
            		
            libroEscritura.write();
            libroEscritura.close();           
                   
    		InicioIanus.excel.leerExcel(InicioIanus.NOMBRE_FICHERO_EXCEL);
		}catch(Exception ioe){
			// ioe.printStackTrace();
			JOptionPane.showMessageDialog(null, "Fichero en uso. No se puede guardar la nueva norma");
		}
	}
}
