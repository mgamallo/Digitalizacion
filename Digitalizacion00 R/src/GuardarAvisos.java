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


public class GuardarAvisos {
	int numFilaUser;
	int indicePantallaIanus;
	Calendar calendario;
	
	public GuardarAvisos(String archivoDestino, String contenidoMensaje, String rutaJpg, Object[] usuariosDoc, Object[] usuariosUrg){

		try{
			WorkbookSettings wbSettings = new WorkbookSettings();
            wbSettings.setEncoding("ISO-8859-1");
            wbSettings.setLocale(new Locale("es", "ES"));
            wbSettings.setExcelDisplayLanguage("ES"); 
            wbSettings.setExcelRegionalSettings("ES"); 
            wbSettings.setCharacterSet(CountryCode.SPAIN.getValue());
	
            Workbook archivoExcel = Workbook.getWorkbook(new File(archivoDestino));
            Sheet hoja = archivoExcel.getSheet(7);                                            //Avisos

            int numFilas7 = hoja.getRows();			//	Avisos
            
            hoja = archivoExcel.getSheet(8);
            int numFilas8 = hoja.getRows();			//	Usuarios Avisos Doc
            
            hoja = archivoExcel.getSheet(11);		//	Usuarios Avisos Urg
            int numFilas11 = hoja.getRows();
            
            WritableWorkbook libroEscritura = Workbook.createWorkbook(new File(archivoDestino), archivoExcel);

            archivoExcel.close();
            
            WritableSheet hojaE = libroEscritura.getSheet(7);   
            
            jxl.write.Label texto = new jxl.write.Label(3,numFilas7,InicioIanus.usuario);
            hojaE.addCell(texto);
            
            if(usuariosDoc != null || usuariosUrg != null){
                int i=0;
                if(usuariosDoc != null){
                    for(i=0;i<usuariosDoc.length;i++){
                        texto = new jxl.write.Label(i + 4,numFilas7,usuariosDoc[i].toString());		//	col, fil , val
                        hojaE.addCell(texto);
                    }
                }

                int j=0;
                if(usuariosUrg != null){
                	for(j=0;j<usuariosUrg.length;j++){
                           texto = new jxl.write.Label(j + i + 4,numFilas7,usuariosUrg[j].toString());		//	col, fil , val
                           hojaE.addCell(texto);
                	}
                }

                texto = new jxl.write.Label(j + i + 4,numFilas7,"N");		//	col, fil , val
                hojaE.addCell(texto);
      												
                texto = new jxl.write.Label(1,numFilas7,contenidoMensaje);		//	col, fil , val
                hojaE.addCell(texto);
                if(rutaJpg.isEmpty()){
                	texto = new jxl.write.Label(2,numFilas7,"Sin Imagen.jpg");
                }else{
                    texto = new jxl.write.Label(2,numFilas7,rutaJpg);
                }
                hojaE.addCell(texto);
          
                calendario = calendario.getInstance();
                
                String dia = Integer.toString(calendario.get(Calendar.DATE));
                String mes = Integer.toString(calendario.get(Calendar.MONTH)+1);
                String año = Integer.toString(calendario.get(Calendar.YEAR));

                String fechaNorma = dia +"/" + mes + "/" + año;
                texto = new jxl.write.Label(0,numFilas7,fechaNorma);		//	col, fil , val
                hojaE.addCell(texto);	  
            }
 
            jxl.write.Number numero;
            
            if(usuariosDoc != null){   
	            hojaE = libroEscritura.getSheet(8);                      // Usuarios Doc 
	//            hojaE.insertColumn(1);
	            
	            
	                        
	            //insertar celdas avisos Usuarios Doc
	            
	            if(usuariosDoc[0].toString().contains("Todos")){
	            	hojaE.insertColumn(1);
	            	for(int jj=0;jj<numFilas8-1;jj++){
	            		numero = new jxl.write.Number(1,jj+1,numFilas7);
	                    hojaE.addCell(numero);
	            	}
	            }else{
	                for(int jj=0;jj<usuariosDoc.length;jj++){
	                	for(int k=0;k<numFilas8;k++){
	                    	if(hojaE.getCell(0, k).getContents().toString().contains(usuariosDoc[jj].toString())){
	                    		int z=1;
	                    		while(!hojaE.getCell(z,k).getContents().toString().contains("N")){
	                    			z++;
	                    		}
	                    		numero = new jxl.write.Number(z,k,numFilas7);
	                    		hojaE.addCell(numero);
	                    		texto = new jxl.write.Label(z+1,k,"N");
	                    		hojaE.addCell(texto);
	                    	}
	
	                	}
	                }
	            }
            }
   
            
            if(usuariosUrg != null){
                hojaE = libroEscritura.getSheet(11);                      // Usuarios Urg           
                //insertar celdas avisos Usuarios Urg
                 
                 if(usuariosUrg[0].toString().contains("Todos")){
                 	hojaE.insertColumn(1);
                 	System.out.println("Número de filas es de la hoja 11 es " + numFilas11);
                 	for(int jj=0;jj<numFilas11-1;jj++){
                 		numero = new jxl.write.Number(1,jj+1,numFilas7);
                         hojaE.addCell(numero);
                         System.out.println("Insertó .... " + numFilas7 + " . ");
                 	}
                 }else{
                     for(int jj=0;jj<usuariosUrg.length;jj++){
                     	for(int k=0;k<numFilas11-1;k++){
                         	if(hojaE.getCell(0, k).getContents().toString().contains(usuariosUrg[jj].toString())){
                         		int z=1;
                         		while(!hojaE.getCell(z,k).getContents().toString().contains("N")){
                         			z++;
                         		}
                         		numero = new jxl.write.Number(z,k,numFilas7);
                         		hojaE.addCell(numero);
                         		texto = new jxl.write.Label(z+1,k,"N");
                         		hojaE.addCell(texto);
                         	}

                     	}
                     }
                 }
            }
             
 
  
            
     /*       
            for(i=0;i<numFilas8-1;i++){
                numero = new jxl.write.Number(1,i+1,numFilas7);
                hojaE.addCell(numero);
            }
     */       	
            
            
            libroEscritura.write();
            libroEscritura.close();           
                   
	
		}catch(Exception ioe){
			// ioe.printStackTrace();
			JOptionPane.showMessageDialog(null, "Fichero en uso. No se puede guardar la nueva norma");
		}
	}
}
