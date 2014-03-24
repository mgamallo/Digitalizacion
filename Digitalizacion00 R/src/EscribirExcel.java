import java.io.File;
import java.util.Locale;

import javax.swing.JOptionPane;

import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.biff.CountryCode;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;


public class EscribirExcel {
	
	int numFilaUser;
	int indicePantallaIanus;
	
	public void escribir(String archivoDestino, String user, int pantallas, int ianus){

		try{
			WorkbookSettings wbSettings = new WorkbookSettings();
            wbSettings.setEncoding("ISO-8859-1");
            wbSettings.setLocale(new Locale("es", "ES"));
            wbSettings.setExcelDisplayLanguage("ES"); 
            wbSettings.setExcelRegionalSettings("ES"); 
            wbSettings.setCharacterSet(CountryCode.SPAIN.getValue());
	
            Workbook archivoExcel = Workbook.getWorkbook(new File(archivoDestino));
            Sheet hoja = archivoExcel.getSheet(3);
            int numC = hoja.getColumns();
            int numF = hoja.getRows(); 
            String[] listaUsers = new String[numF-1];
            int numUsers = listaUsers.length;
            for(int i=0;i<numUsers;i++){
            	listaUsers[i] = hoja.getCell(0,i+1).getContents().toString();
            }
            boolean encontrado = false;
            for(int i=0;i<numUsers;i++){								//	Fila Usuario
            	if(listaUsers[i].contains(user)){
            		numFilaUser = i+1;
            		encontrado = true;
            		break;
            	}
            }
            if(!encontrado){											//	Nuevo Usuario
            	numFilaUser = numUsers+1;
            }
            
            if(pantallas == 1 && ianus == 1){
            	indicePantallaIanus = 1;
            }else if(pantallas == 1 && ianus == 2){
            	indicePantallaIanus = 10;
            }else if(pantallas == 2 && ianus == 1){
            	indicePantallaIanus = 19;
            }else{
            	indicePantallaIanus = 28;
            }
            
            //	System.out.println(listaUsers[i]);
            
            
            WritableWorkbook libroEscritura = Workbook.createWorkbook(new File(archivoDestino), archivoExcel);
            
            archivoExcel.close();
            
            WritableSheet hojaE = libroEscritura.getSheet(3);
            
            jxl.write.Label texto;
            jxl.write.Number numero;

            if(!encontrado){
                texto = new jxl.write.Label(0,numFilaUser,user);
                hojaE.addCell(texto);
                /*
                 * 
                 * 	Posteriormente hay que poner las N que hagan falta
                 * 
                 * 
                 */
            }
            
            texto = new jxl.write.Label(indicePantallaIanus,numFilaUser,"S");		//	col, fil , val
            hojaE.addCell(texto);
            
            numero = new jxl.write.Number(indicePantallaIanus+1,numFilaUser,InicioIanus.coordenadas.coordenadas[0].x); 
            hojaE.addCell(numero);
            numero = new jxl.write.Number(indicePantallaIanus+2,numFilaUser,InicioIanus.coordenadas.coordenadas[0].y); 
            hojaE.addCell(numero);           
            numero = new jxl.write.Number(indicePantallaIanus+3,numFilaUser,InicioIanus.coordenadas.coordenadas[1].x); 
            hojaE.addCell(numero);
            numero = new jxl.write.Number(indicePantallaIanus+4,numFilaUser,InicioIanus.coordenadas.coordenadas[1].y); 
            hojaE.addCell(numero);             
            numero = new jxl.write.Number(indicePantallaIanus+5,numFilaUser,InicioIanus.coordenadas.coordenadas[2].x); 
            hojaE.addCell(numero);
            numero = new jxl.write.Number(indicePantallaIanus+6,numFilaUser,InicioIanus.coordenadas.coordenadas[2].y); 
            hojaE.addCell(numero);           
            numero = new jxl.write.Number(indicePantallaIanus+7,numFilaUser,InicioIanus.coordenadas.coordenadas[3].x); 
            hojaE.addCell(numero);
            numero = new jxl.write.Number(indicePantallaIanus+8,numFilaUser,InicioIanus.coordenadas.coordenadas[3].y); 
            hojaE.addCell(numero); 
            
            
            libroEscritura.write();
            libroEscritura.close();
            
	
		}catch(Exception ioe){
			// ioe.printStackTrace();
			JOptionPane.showMessageDialog(null, "Fichero en uso. No se puede guardar preferencias");
		}
	}
/*
	public static void main(String[] args){
		EscribirExcel prueba = new EscribirExcel();
		prueba.escribir("Documentos1.xls","pepe",1,1);
	
	}   */

}