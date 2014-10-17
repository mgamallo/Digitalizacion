import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

import javax.swing.JOptionPane;

import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.biff.CountryCode;
import jxl.read.biff.BiffException;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class CoordenadasVentana {
	
	
	
	
	Rectangle vPdf1 = new Rectangle();
	Rectangle vPdf2 = new Rectangle();
	Rectangle vExplorador = new Rectangle();
	Rectangle vTabla = new Rectangle();
	Rectangle vTeclas = new Rectangle();
	
	String argumentoVTeclas = "";
	
	
	void guardarCoordenadasVentana(String archivoDestino, String user, int pantallas, int ianus){
		
		try {
			WorkbookSettings wbSettings = new WorkbookSettings();
			wbSettings.setEncoding("ISO-8859-1");
			wbSettings.setLocale(new Locale("es", "ES"));
			wbSettings.setExcelDisplayLanguage("ES"); 
			wbSettings.setExcelRegionalSettings("ES"); 
			wbSettings.setCharacterSet(CountryCode.SPAIN.getValue());
			
			Workbook archivoExcel = Workbook.getWorkbook(new File(archivoDestino));
			Sheet hoja = archivoExcel.getSheet(0);
			
			int numColumnas = hoja.getColumns();
			int numFilas = hoja.getRows();
			
			
			int posInicial;
			
			if (pantallas == 1 && ianus == 1){
				posInicial = 0;
			}
			else if(pantallas == 1 && ianus == 2){
				posInicial = 20;
			}
			else if(pantallas == 2 && ianus == 1){
				posInicial = 40;
			}
			else{
				posInicial = 60;
			}
			
			int numFilaUser=0;			
			boolean encontrado = false;
	        
			for(int i= posInicial + 0; i< posInicial + 20; i++){
				System.out.println(hoja.getCell(0,i).getContents().toString());
				if(hoja.getCell(0,i).getContents().toString().contains(user)){
					numFilaUser = i;encontrado = true;break;
	            }
	        }
			System.out.println("El usuario tiene el numero: " + numFilaUser + hoja.getCell(0,numFilaUser).getContents().toString().contains(user));
			
			
			WritableWorkbook libroEscritura = Workbook.createWorkbook(new File(archivoDestino), archivoExcel);
            
            archivoExcel.close();
            
            WritableSheet hojaE = libroEscritura.getSheet(0);
            
            jxl.write.Label texto;
            jxl.write.Number numero;
			
   
            numero = new jxl.write.Number(1,numFilaUser,Inicio.navegador1.frame.getBounds().x); 
            hojaE.addCell(numero);
            numero = new jxl.write.Number(2,numFilaUser,Inicio.navegador1.frame.getBounds().y);
            hojaE.addCell(numero);           
            numero = new jxl.write.Number(3,numFilaUser,Inicio.navegador1.frame.getBounds().width); 
            hojaE.addCell(numero);
            numero = new jxl.write.Number(4,numFilaUser,Inicio.navegador1.frame.getBounds().height);  
            hojaE.addCell(numero);             
           // texto = new jxl.write.Label(5,numFilaUser,Inicio.ventanaPdf1.argumentos); 
           // hojaE.addCell(texto);
          
            if (InicioIanus.numeroIanus == 2){
                numero = new jxl.write.Number(6,numFilaUser,Inicio.navegador2.frame.getBounds().x); 
                hojaE.addCell(numero);
                numero = new jxl.write.Number(7,numFilaUser,Inicio.navegador2.frame.getBounds().y);
                hojaE.addCell(numero);           
                numero = new jxl.write.Number(8,numFilaUser,Inicio.navegador2.frame.getBounds().width); 
                hojaE.addCell(numero);
                numero = new jxl.write.Number(9,numFilaUser,Inicio.navegador2.frame.getBounds().height);  
                hojaE.addCell(numero);             
               // texto = new jxl.write.Label(10,numFilaUser,Inicio.ventanaPdf1.argumentos); 
               // hojaE.addCell(texto);
            }
            
            numero = new jxl.write.Number(11,numFilaUser,InicioIanus.ventanaE.getBounds().x); 
            hojaE.addCell(numero);
            numero = new jxl.write.Number(12,numFilaUser,InicioIanus.ventanaE.getBounds().y);
            hojaE.addCell(numero);           
            numero = new jxl.write.Number(13,numFilaUser,InicioIanus.ventanaE.getBounds().width); 
            hojaE.addCell(numero);
            numero = new jxl.write.Number(14,numFilaUser,InicioIanus.ventanaE.getBounds().height);  
            hojaE.addCell(numero);             
            // texto = new jxl.write.Label(15,numFilaUser,Inicio.ventanaMenu.argumentos); 
            // hojaE.addCell(texto);
            
            numero = new jxl.write.Number(16,numFilaUser,InicioIanus.ventanaD.getBounds().x); 
            hojaE.addCell(numero);
            numero = new jxl.write.Number(17,numFilaUser,InicioIanus.ventanaD.getBounds().y);
            hojaE.addCell(numero);           
            numero = new jxl.write.Number(18,numFilaUser,InicioIanus.ventanaD.getBounds().width); 
            hojaE.addCell(numero);
            numero = new jxl.write.Number(19,numFilaUser,InicioIanus.ventanaD.getBounds().height);  
            hojaE.addCell(numero);             
            // texto = new jxl.write.Label(20,numFilaUser,Inicio.ventanaTabla.argumentos); 
            // hojaE.addCell(texto);
            
            numero = new jxl.write.Number(21,numFilaUser,InicioIanus.ventanaBotonesTeclas.getBounds().x); 
            hojaE.addCell(numero);
            numero = new jxl.write.Number(22,numFilaUser,InicioIanus.ventanaBotonesTeclas.getBounds().y);
            hojaE.addCell(numero);           
            numero = new jxl.write.Number(23,numFilaUser,InicioIanus.ventanaBotonesTeclas.getBounds().width); 
            hojaE.addCell(numero);
            numero = new jxl.write.Number(24,numFilaUser,InicioIanus.ventanaBotonesTeclas.getBounds().height);  
            hojaE.addCell(numero);             
            if(InicioIanus.documentacion != 2 && InicioIanus.documentacion != 3){
            	texto = new jxl.write.Label(25,numFilaUser,InicioIanus.ventanaBotonesTeclaAtributo); 
                System.out.println(InicioIanus.ventanaBotonesTeclaAtributo);
                hojaE.addCell(texto);
            }
                                    
            
            libroEscritura.write();
            libroEscritura.close();
		
		
		} catch (Exception ioe) {
			// TODO Auto-generated catch block
			ioe.printStackTrace();
			JOptionPane.showMessageDialog(null, "Fichero en uso. No se puede guardar las coordenadas");
		} 
	}
	
	
	void leerCoordenadasVentana(String archivoDestino){
		
		try {
			WorkbookSettings wbSettings = new WorkbookSettings();
                    wbSettings.setEncoding("ISO-8859-1");
                    wbSettings.setLocale(new Locale("es", "ES"));
                    wbSettings.setExcelDisplayLanguage("ES"); 
                    wbSettings.setExcelRegionalSettings("ES"); 
                    wbSettings.setCharacterSet(CountryCode.SPAIN.getValue());
                    
                    Workbook archivoExcel;
					
						archivoExcel = Workbook.getWorkbook(new File(archivoDestino));

                    
                    Sheet hoja = archivoExcel.getSheet(0);
                    
                    int posInicial = 0;
        			
        			if (InicioIanus.numeroPantallas == 1 && InicioIanus.numeroIanus == 1){
        				posInicial = 0;
        			}
        			else if(InicioIanus.numeroPantallas == 1 && InicioIanus.numeroIanus == 2){
        				posInicial = 20;
        			}
        			else if(InicioIanus.numeroPantallas == 2 && InicioIanus.numeroIanus == 1 && !InicioIanus.nombrePc.equals("hpchpMAHC14p")){
        				posInicial = 40;
        			}
        			else if(InicioIanus.numeroPantallas ==2 && InicioIanus.numeroIanus == 2 && !InicioIanus.nombrePc.equals("hpchpMAHC14p")){
        				posInicial = 60;
        			}
        			else if(InicioIanus.numeroPantallas == 2 && InicioIanus.numeroIanus == 1 && InicioIanus.nombrePc.equals("hpchpMAHC14p")){
        				posInicial = 80;
        			}
        			else if(InicioIanus.numeroPantallas ==2 && InicioIanus.numeroIanus == 2 && InicioIanus.nombrePc.equals("hpchpMAHC14p")){
        				posInicial = 100;
        			}
        			
        			
        			int numFilaUser=0;			
        			boolean encontrado = false;
        	        
        			for(int i= posInicial + 0; i< posInicial + 20; i++){
        				System.out.println(hoja.getCell(0,i).getContents().toString());
        				if(hoja.getCell(0,i).getContents().toString().contains(InicioIanus.usuario)){
        					numFilaUser = i;encontrado = true;break;
        	            }
        	        }
        			
        			System.out.println(numFilaUser);
        			
        			vPdf1.setBounds(Integer.parseInt(hoja.getCell(1,numFilaUser).getContents()), Integer.parseInt(hoja.getCell(2,numFilaUser).getContents())
        					, Integer.parseInt(hoja.getCell(3,numFilaUser).getContents()), Integer.parseInt(hoja.getCell(4,numFilaUser).getContents()));

        			
        			       				
        			vPdf2.setBounds(Integer.parseInt(hoja.getCell(6,numFilaUser).getContents()), Integer.parseInt(hoja.getCell(7,numFilaUser).getContents())
        					, Integer.parseInt(hoja.getCell(8,numFilaUser).getContents()), Integer.parseInt(hoja.getCell(9,numFilaUser).getContents()));

        			        			
        			vExplorador.setBounds(Integer.parseInt(hoja.getCell(11,numFilaUser).getContents()), Integer.parseInt(hoja.getCell(12,numFilaUser).getContents())
        					, Integer.parseInt(hoja.getCell(13,numFilaUser).getContents()), Integer.parseInt(hoja.getCell(14,numFilaUser).getContents()));
        			
        			
        			vTabla.setBounds(Integer.parseInt(hoja.getCell(16,numFilaUser).getContents()), Integer.parseInt(hoja.getCell(17,numFilaUser).getContents())
        					, Integer.parseInt(hoja.getCell(18,numFilaUser).getContents()), Integer.parseInt(hoja.getCell(19,numFilaUser).getContents()));
        			
        			
        			vTeclas.setBounds(Integer.parseInt(hoja.getCell(21,numFilaUser).getContents()), Integer.parseInt(hoja.getCell(22,numFilaUser).getContents())
        					, Integer.parseInt(hoja.getCell(23,numFilaUser).getContents()), Integer.parseInt(hoja.getCell(24,numFilaUser).getContents()));
        			InicioIanus.ventanaBotonesTeclaAtributo = hoja.getCell(25,numFilaUser).getContents().toString();
        			System.out.println(InicioIanus.ventanaBotonesTeclaAtributo);
        			
        			archivoExcel.close();
        			
        			/*        			
        			System.out.println(Inicio.navegador1.frame.getLocation().x + " ," + Inicio.navegador1.frame.getLocation().y);
        			System.out.println(Inicio.navegador1.frame.getSize().height + " ," + Inicio.navegador1.frame.getSize().width);
        			System.out.println(Inicio.navegador2.frame.getLocation().x + " ," + Inicio.navegador2.frame.getLocation().y);
        			System.out.println(Inicio.navegador2.frame.getSize().height + " ," + Inicio.navegador2.frame.getSize().width);
        			System.out.println(InicioIanus.ventanaE.getLocation().x + " ," + InicioIanus.ventanaE.getLocation().y);
        			System.out.println(InicioIanus.ventanaE.getSize().height + " ," + InicioIanus.ventanaE.getSize().width);
        			
        			*/
        			
		} catch (BiffException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}     
        			
                    
	}
	
}
