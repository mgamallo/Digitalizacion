import java.io.File;
import java.util.Locale;

import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.biff.CountryCode;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;


public class GuardarEstadisticaSantiago {
	
	Object[][] tablaNumeroSubidas;
	Object[][] tablaEstadisticas;
	
	int numColumnasHoja0 = 0;
	int numFilasHoja0 = 0;
	int numColumnasHoja1 = 0;
	int numFilasHoja1 = 0;	
	
	static Workbook archivoExcel;
	
	public void leerExcel(String archivoDestino, Boolean grabarSoloDatosEstadisticos){
		try{
				WorkbookSettings wbSettings = new WorkbookSettings();
                        wbSettings.setEncoding("ISO-8859-1");
                        wbSettings.setLocale(new Locale("es", "ES"));
                        wbSettings.setExcelDisplayLanguage("ES"); 
                        wbSettings.setExcelRegionalSettings("ES"); 
                        wbSettings.setCharacterSet(CountryCode.SPAIN.getValue());
                        
                        archivoExcel = Workbook.getWorkbook(new File(archivoDestino));
                        

			
						
                        Sheet hoja = archivoExcel.getSheet(0);
                        numColumnasHoja0 =1;
                        numFilasHoja0 = hoja.getRows()-1;
                        
                        
                        while(!hoja.getCell(numColumnasHoja0,0).getContents().toString().contains("Nombre que aparece")){
                        	numColumnasHoja0++;
                        }
                                                
                        tablaNumeroSubidas = new Object[numFilasHoja0][numColumnasHoja0];
                        for(int i=0;i<numFilasHoja0;i++){
                        	for(int j=0;j<numColumnasHoja0;j++){
                        		tablaNumeroSubidas[i][j] = new Object();
                        		tablaNumeroSubidas[i][j] = hoja.getCell(j,i).getContents();
                        	}
                        }
                      
                        /*  
                        for(int i=0;i<numFilasHoja0;i++){
                        	for(int j=0;j<numColumnasHoja0;j++){
                        		System.out.print(tablaNumeroSubidas[i][j] + "\t");
                        	}
                        	System.out.println();
                        }
                        */
                        
                        hoja = archivoExcel.getSheet(1);
                        numColumnasHoja1 = 7;
                        numFilasHoja1 = numFilasHoja0;
                        
                        tablaEstadisticas = new Object[numFilasHoja1][numColumnasHoja1];
                        for(int i=0;i<numFilasHoja1;i++){
                        	for(int j=0;j<numColumnasHoja1;j++){
                        		tablaEstadisticas[i][j] = new Object();
                        		tablaEstadisticas[i][j] = hoja.getCell(j,i).getContents();
                        	}
                        }
                      
                        /*
                        for(int i=0;i<numFilasHoja1;i++){
                        	for(int j=0;j<numColumnasHoja1;j++){
                        		System.out.print(tablaEstadisticas[i][j] + "\t");
                        	}
                        	System.out.println();
                        }
                        
                        */
                        
                        
                        
                        this.actualizarTablas(archivoDestino, grabarSoloDatosEstadisticos);
                      
		}catch(Exception ioe){
			ioe.printStackTrace();
		}
	}
	
	public void actualizarTablas(String archivoDestino, Boolean grabarSoloDatosEstadisticos){
		
		int fila=0;
		int columna =0;
		
		try{
			WritableWorkbook libroEscritura = Workbook.createWorkbook(new File(archivoDestino), archivoExcel);
			archivoExcel.close();
	        
	        WritableSheet hojaE = libroEscritura.getSheet(0);
	        jxl.write.Number numero;
	        jxl.write.Label texto;
			
	        if(!grabarSoloDatosEstadisticos){
				for(int k=0;k<InicioIanus.tablaServiciosDocumentos.size();k++){
					for(int i=1;i<numColumnasHoja0;i++){
						if(InicioIanus.tablaServiciosDocumentos.get(k).nombreServicio.equals(tablaNumeroSubidas[0][i].toString())){
							columna = i;
							break;
						}
					}
					for(int i=1;i<numFilasHoja0;i++){
						if(InicioIanus.tablaServiciosDocumentos.get(k).nombreDocumento.equals(tablaNumeroSubidas[i][0].toString())){
							fila = i;
							break;
						}
					}	
					int aux = Integer.parseInt(tablaNumeroSubidas[fila][columna].toString());
					int aux2 = aux + InicioIanus.tablaServiciosDocumentos.get(k).numeroSubidos;
					tablaNumeroSubidas[fila][columna] = aux + InicioIanus.tablaServiciosDocumentos.get(k).numeroSubidos;
					System.out.println("Antes: " + aux + ". Ahora: " + tablaNumeroSubidas[fila][columna]);
					 
					numero = new jxl.write.Number(columna,fila,aux2);
		            hojaE.addCell(numero);
				}
	        }
	        else{
				
				// Datos totales en la página 1 del excel
	        	
	        	hojaE = libroEscritura.getSheet(0);
	        	
	        	System.out.println(InicioIanus.tablaServiciosDocumentos.size() + "Datos ianus");
	        	
	        	for(int k=0;k<InicioIanus.tablaServiciosDocumentos.size();k++){
					for(int i=1;i<numColumnasHoja0;i++){
						if(InicioIanus.tablaServiciosDocumentos.get(k).nombreServicio.equals(tablaNumeroSubidas[0][i].toString())){
							columna = i;
							break;
						}
					}
					for(int i=1;i<numFilasHoja0;i++){
						if(InicioIanus.tablaServiciosDocumentos.get(k).nombreDocumento.equals(tablaNumeroSubidas[i][0].toString())){
							fila = i;
							break;
						}
					}	
					
					int aux2 = InicioIanus.tablaServiciosDocumentos.get(k).numeroSubidos;
					System.out.println(aux2);
					tablaNumeroSubidas[fila][columna] = InicioIanus.tablaServiciosDocumentos.get(k).numeroSubidos;
					System.out.println("Antes: " + 0 + ". Ahora: " + tablaNumeroSubidas[fila][columna]);
					 
					numero = new jxl.write.Number(columna,fila,aux2);
		            hojaE.addCell(numero);
				}
				
							
				hojaE = libroEscritura.getSheet(1);  //	Datos totales en la página 2 del excel
				
				for(int i=0;i<InicioIanus.conjuntoDatosEstadisticos.length;i++){
					for(int j=1;j<numFilasHoja1;j++){
						if(InicioIanus.conjuntoDatosEstadisticos[i].nombreDoc.equals(tablaEstadisticas[j][0])){
							numero = new jxl.write.Number(2,j,InicioIanus.conjuntoDatosEstadisticos[i].media);		// Media
							hojaE.addCell(numero);
							numero = new jxl.write.Number(3,j,InicioIanus.conjuntoDatosEstadisticos[i].numeroDoc);		// Numero de veces subida
							hojaE.addCell(numero);
							numero = new jxl.write.Number(4,j,InicioIanus.conjuntoDatosEstadisticos[i].desviacion);		// Desviacion
							hojaE.addCell(numero);
							numero = new jxl.write.Number(5,j,InicioIanus.conjuntoDatosEstadisticos[i].maximo);		// Media
							hojaE.addCell(numero);
							numero = new jxl.write.Number(6,j,InicioIanus.conjuntoDatosEstadisticos[i].minimo);		// Media
							hojaE.addCell(numero);
							texto = new jxl.write.Label(8,j,InicioIanus.conjuntoDatosEstadisticos[i].nombreDoc);
							hojaE.addCell(texto);
							break;
						}
					}
				}
	        }
			

			
	         libroEscritura.write();
	         libroEscritura.close();
			
		}catch(Exception ioe){
			ioe.printStackTrace();
		}

	}
}
