import java.io.File;
import java.util.Locale;

import javax.swing.JOptionPane;

import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.biff.CountryCode;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;


public class GuardarCoordenadas {
	int numFilaUser;
	int indicePantallaIanus;
	
	public GuardarCoordenadas(String archivoDestino, String tipoCoordenada, Coordenadas coor){

		try{
			WorkbookSettings wbSettings = new WorkbookSettings();
            wbSettings.setEncoding("ISO-8859-1");
            wbSettings.setLocale(new Locale("es", "ES"));
            wbSettings.setExcelDisplayLanguage("ES"); 
            wbSettings.setExcelRegionalSettings("ES"); 
            wbSettings.setCharacterSet(CountryCode.SPAIN.getValue());
	
            Workbook archivoExcel = Workbook.getWorkbook(new File(archivoDestino));
            Sheet hoja = archivoExcel.getSheet(5);
            
            int comodin;
            
            if(InicioIanus.numeroPantallas == 1){
            	comodin = 1;
            }else{
            	comodin = 8;
            }
           
            WritableWorkbook libroEscritura = Workbook.createWorkbook(new File(archivoDestino), archivoExcel);
            
            archivoExcel.close();
            
            WritableSheet hojaE = libroEscritura.getSheet(5);
            
            jxl.write.Label texto;
            jxl.write.Number numero;
            
            int tipoC;
            
            if(tipoCoordenada.contains("Ingresos")){
                tipoC=1;
            }else if(tipoCoordenada.contains("Consultas")){
            	tipoC=2;
            }else if(tipoCoordenada.contains("CMA")){
            	tipoC=3;
            }else{
            	tipoC=4;
            }
            
        	numero = new jxl.write.Number(tipoC,comodin,coor.x.x);
            hojaE.addCell(numero);
        	numero = new jxl.write.Number(tipoC,comodin+1,coor.x.y);
            hojaE.addCell(numero);
        	numero = new jxl.write.Number(tipoC,comodin+2,coor.ruta.x);
            hojaE.addCell(numero);
        	numero = new jxl.write.Number(tipoC,comodin+3,coor.ruta.y);
            hojaE.addCell(numero);
           	numero = new jxl.write.Number(tipoC,comodin+4,coor.aceptar.x);
            hojaE.addCell(numero);
        	numero = new jxl.write.Number(tipoC,comodin+5,coor.aceptar.y);
            hojaE.addCell(numero);
            
            libroEscritura.write();
            libroEscritura.close();           
                   
	
		}catch(Exception ioe){
			// ioe.printStackTrace();
			JOptionPane.showMessageDialog(null, "Fichero en uso. No se puede guardar las coordenadas");
		}
	}
}
