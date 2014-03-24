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


public class BorrarNovedades {
	int numFilaUser;
	int indicePantallaIanus;
	
	int hojaUsuario;
	int hojaUsuarioAvisos;

	
	
	Calendar calendario;
	
	public BorrarNovedades(String archivoDestino, int numUsuario){

		if(InicioIanus.documentacion != 0 && InicioIanus.documentacion != 3){
			hojaUsuario = 3;
			hojaUsuarioAvisos = 8;
		}else{
			hojaUsuario = 10;
			hojaUsuarioAvisos = 11;
		}
		
		try{
			WorkbookSettings wbSettings = new WorkbookSettings();
            wbSettings.setEncoding("ISO-8859-1");
            wbSettings.setLocale(new Locale("es", "ES"));
            wbSettings.setExcelDisplayLanguage("ES"); 
            wbSettings.setExcelRegionalSettings("ES"); 
            wbSettings.setCharacterSet(CountryCode.SPAIN.getValue());
	
            Workbook archivoExcel = Workbook.getWorkbook(new File(archivoDestino));

            Sheet hoja = archivoExcel.getSheet(hojaUsuario);
            int numFilas3 = hoja.getRows();
           
            WritableWorkbook libroEscritura = Workbook.createWorkbook(new File(archivoDestino), archivoExcel);

            archivoExcel.close();
            
            WritableSheet hojaE = libroEscritura.getSheet(hojaUsuario);
            
            jxl.write.Label texto;

       //     System.out.println(numUsuario);
            
            texto = new jxl.write.Label(37,numUsuario+2,"N");		//	col, fil , val
            hojaE.addCell(texto);	 
            for(int i=1;i<=InicioIanus.numeroNuevasNormas;i++){
                texto = new jxl.write.Label(37+i,numUsuario+2,"");		//	col, fil , val
            	hojaE.addCell(texto);	  
            }
            
            
            //	Borrar los avisos
            hojaE = libroEscritura.getSheet(hojaUsuarioAvisos);
            texto = new jxl.write.Label(1,numUsuario+1,"N");		//	col, fil , val
            hojaE.addCell(texto);	
          
            for(int i=1;i<=InicioIanus.numeroNuvosMensajes;i++){
                texto = new jxl.write.Label(i+1,numUsuario+1,"");		//	col, fil , val
            	hojaE.addCell(texto);	  
            }
 
            //	Borrar los avisos de documentos-modelos nuevos
            hojaE = libroEscritura.getSheet(9);
            texto = new jxl.write.Label(1,numUsuario+1,"N");		//	col, fil , val
            hojaE.addCell(texto);	
            for(int i=1;i<=InicioIanus.numeroNuevosDocModel;i++){
                texto = new jxl.write.Label(i+1,numUsuario+1,"");		//	col, fil , val
            	hojaE.addCell(texto);	  
            }
            		
            libroEscritura.write();
            libroEscritura.close();           
                   
	
		}catch(Exception ioe){
			// ioe.printStackTrace();
			JOptionPane.showMessageDialog(null, "Fichero en uso. No se puede borrar el aviso");
		}
	}
}
