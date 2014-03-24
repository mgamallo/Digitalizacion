

import java.awt.Point;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;

import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.biff.CountryCode;

/**
 *
 * @author Coco
 */
  

public class LeerExcel {
	
	Object[][] tablaDocumentos;
	Object[][] tablaCoordenadas;
	Object[][] tablaCoordenadasIanus;
	Object[][] tablaVisor;
    String[] habituales;
    String[] habituales2;
    DefaultListModel listaHabituales;
    DefaultListModel listaHabituales2;
	String[] todosDocumentos;
    DefaultListModel listaDocumentos;
    DefaultListModel listaUsuariosLista;
    DefaultListModel listaUsuariosListaUrg;
    DefaultComboBoxModel listaUsuarios;
    DefaultComboBoxModel listaUsuariosUrg;
    DefaultComboBoxModel listaServicios;
    DefaultComboBoxModel listaDocumentosCombo;
    DefaultListModel listaServiciosLista;
    String[] servicios;
    String[] documentosServicio;
    DefaultListModel vinculacionServicio;
    
    Object[][] tablaAvisosDoc;							//	Hoja 3 de Normas Doc
    Object[][] tablaAvisosUrg;							//	Hoja 10 de Normas Urg
    Object[][] tablaAvisosMensajesDoc;					//	Hoja 8 de Avisos Usuarios Doc
    Object[][] tablaAvisosMensajesUrg;					//	Hoja 11 de Avisos Usuarios Urg
    Object[][] tablaAvisoDocNuevo;						//	Hoja 9 de Aviso de doc. nuevo
    AvisosUsuario[] arraysAvisos;
    String[][] novedadesUsuario;
    String[][] contenidoNovedades;
    String[][] contenidoMensajes;						//	Hoja 7 Contenido de los avisos 
        
        int numDocumentos;
        int numServicios;
        
        int numUsuarioDoc;
        int numUsuarioUrg;
        
        boolean coordenadasGrabadas = false;

	
	public void leerExcel(String archivoDestino){
		try{
				WorkbookSettings wbSettings = new WorkbookSettings();
                        wbSettings.setEncoding("ISO-8859-1");
                        wbSettings.setLocale(new Locale("es", "ES"));
                        wbSettings.setExcelDisplayLanguage("ES"); 
                        wbSettings.setExcelRegionalSettings("ES"); 
                        wbSettings.setCharacterSet(CountryCode.SPAIN.getValue());
                        
                        Workbook archivoExcel = Workbook.getWorkbook(new File(archivoDestino));
                        
                        			
						
                        Sheet hoja = archivoExcel.getSheet(0);
                        int numColumnas =1;
                        int numFilas = hoja.getRows();
                        
                        
                        while(!hoja.getCell(numColumnas,0).getContents().toString().contains("Nombre que aparece")){
                        	numColumnas++;
                        }
                        numColumnas++;
                       
                        tablaDocumentos = new Object[numFilas][numColumnas];
                        servicios = new String[numColumnas-3];
                        todosDocumentos = new String[numFilas-1];
                        numDocumentos = numFilas-1;
                        numServicios = numColumnas-3;
                        
                       
                        //	Conjunto de nombres normalizados que poseen título
                        for(int fila = 1; fila<numFilas;fila++){
                        	String documento = hoja.getCell(numColumnas,fila).getContents().toString();
                        	if(documento.equals("S")){
                        		InicioIanus.conjuntoTitulos.add(hoja.getCell(0,fila).getContents().toString());
                        	}
                        }
                        
                        
                        for( Iterator<String> it = InicioIanus.conjuntoTitulos.iterator();it.hasNext();){
                        	System.out.println(it.next());
                        }
                        
                        
                        for (int fila=0;fila<numFilas;fila++){
                                for(int columna=0;columna<numColumnas;columna++){					
                                	tablaDocumentos[fila][columna] = hoja.getCell(columna, fila).getContents();
                                //	System.out.print(tablaDocumentos[fila][columna].toString());
                                }
                               // System.out.println();
                        }
                                // Array Servicios
                        listaServicios = new DefaultComboBoxModel();
                        listaServiciosLista = new DefaultListModel();
                        for(int i=2;i<numColumnas-1;i++){
                        	if(!tablaDocumentos[0][i].toString().contains("Nombre")){
                                servicios[i-2] = tablaDocumentos[0][i].toString();
                                listaServiciosLista.addElement(servicios[i-2]);
                                listaServicios.addElement(servicios[i-2]);
                        	}else{
                        		break;
                        	}
      
                         //   System.out.println(i + "\t" + servicios[i-2]);
                         }
                                
                        //  Array Todos Los Documentos
                        for(int i=1;i<numFilas;i++){
                            todosDocumentos[i-1] = tablaDocumentos[i][0].toString();
                        }
                        
                        //  Leer lista de todos los documentos        
                        listaDocumentos = new DefaultListModel();
                        listaDocumentosCombo = new DefaultComboBoxModel();
                        for(int i=0;i<todosDocumentos.length;i++){
                            listaDocumentos.addElement(todosDocumentos[i]);
                            listaDocumentosCombo.addElement(todosDocumentos[i]);
                        }        
                                
 
                        //  Leer hoja excel habituales
                        hoja=archivoExcel.getSheet(1);
                        int numFilasH = hoja.getRows();
                        habituales = new String[numFilasH];
                        listaHabituales = new DefaultListModel();
                        for(int i=0;i<numFilasH;i++){
                            listaHabituales.addElement(hoja.getCell(0, i).getContents().toString());
                            habituales[i] = listaHabituales.getElementAt(i).toString();
                        }
                        
                        //  Leer hoja excel habituales2
                        hoja=archivoExcel.getSheet(2);
                        int numFilasH2 = hoja.getRows();
                        habituales2 = new String[numFilasH2];
                        listaHabituales2 = new DefaultListModel();
                        for(int i=0;i<numFilasH2;i++){
                            listaHabituales2.addElement(hoja.getCell(0, i).getContents().toString());
                            habituales2[i] = listaHabituales2.getElementAt(i).toString();
                        } 
                        
                        //	Leer hoja excel usuarios
                        hoja=archivoExcel.getSheet(3);
                        int numFilasUs = hoja.getRows();
                        int numColumUs = hoja.getColumns();
                        listaUsuarios = new DefaultComboBoxModel();
                        listaUsuariosLista = new DefaultListModel();
                        for(int i=1;i<numFilasUs;i++){
                        	listaUsuarios.addElement(hoja.getCell(0, i).getContents().toString());
                        	listaUsuariosLista.addElement(hoja.getCell(0, i).getContents().toString());
                        }
                                                
                        tablaCoordenadas = new Object[numFilasUs-1][numColumUs];
                        
                        //	Leer coordenadas usuarios
                        for (int fila=0;fila<numFilasUs-1;fila++){
                            for(int columna=0;columna<numColumUs;columna++){
                            	if(hoja.getCell(columna,fila+1).getContents()!="")
                            		tablaCoordenadas[fila][columna] = hoja.getCell(columna, fila+1).getContents();
                            	else
                            		tablaCoordenadas[fila][columna] = 0;
 
                            }
                        } 
                        
                        //	Leer hoja excel usuariosUrgencias
                        hoja=archivoExcel.getSheet(10);
                        int numFilasUsUrg = hoja.getRows();
                        int numColumUsUrg = hoja.getColumns();
                        listaUsuariosUrg = new DefaultComboBoxModel();
                        listaUsuariosListaUrg = new DefaultListModel();
                        for(int i=1;i<numFilasUsUrg;i++){
                        	listaUsuariosUrg.addElement(hoja.getCell(0, i).getContents().toString());
                        	listaUsuariosListaUrg.addElement(hoja.getCell(0, i).getContents().toString());
                        }
                                                
                        tablaCoordenadas = new Object[numFilasUsUrg-1][numColumUsUrg];
                        
                        //	Leer coordenadas usuariosUrgencias
                        for (int fila=0;fila<numFilasUsUrg-1;fila++){
                            for(int columna=0;columna<numColumUsUrg;columna++){
                            	if(hoja.getCell(columna,fila+1).getContents()!="")
                            		tablaCoordenadas[fila][columna] = hoja.getCell(columna, fila+1).getContents();
                            	else
                            		tablaCoordenadas[fila][columna] = 0;
 
                            }
                        } 
                        
                        
                        //	Leer cuadro documentos Visor
                        hoja = archivoExcel.getSheet(4);
                        int numFilasVi = hoja.getRows();
                        int numColumVi = hoja.getColumns();
                        
                        tablaVisor = new Object[numFilasVi][numColumVi];
                        
                        for (int fila=0;fila<numFilasVi;fila++){
                                for(int columna=0;columna<numColumVi;columna++){					
                                tablaVisor[fila][columna] = hoja.getCell(columna, fila).getContents();
                                }
                        }
                        
                        //	Leer cuadro de coordenadas del Ianus
                        hoja = archivoExcel.getSheet(5);
                        int numFilasC = hoja.getRows();
                        int numColumC = hoja.getColumns();
                        
                        tablaCoordenadasIanus = new Object[numFilasC][numColumC];
                        for (int fila=0;fila<numFilasC;fila++){
                            for(int columna=0;columna<numColumC;columna++){					
                            	tablaCoordenadasIanus[fila][columna] = hoja.getCell(columna, fila).getContents();
                            }
                        }
                        
                        //	Leer novedades usuario Doc
                        hoja =archivoExcel.getSheet(3);
                        int numFilasN = hoja.getRows();
                        int numColumnN = hoja.getColumns();
                        tablaAvisosDoc = new Object[numFilasN-2][1 + (numColumnN-37)];
                        for(int i=0;i<numFilasN-2;i++){
                        	for(int j=0;j<(1+numColumnN-37);j++){
                        		if(j==0){
                        			tablaAvisosDoc[i][j] = new Object();
                        			tablaAvisosDoc[i][j] = hoja.getCell(0,i+2).getContents();
                        		
                        		}else{
                        			tablaAvisosDoc[i][j] = new Object();
                            		tablaAvisosDoc[i][j] = hoja.getCell(j+36,i+2).getContents();
                        		}
                        	}
                        }
                        
                        
                        //	Leer novedades usuario Urg
                        hoja =archivoExcel.getSheet(10);
                        numFilasN = hoja.getRows();
                        numColumnN = hoja.getColumns();
                        tablaAvisosUrg = new Object[numFilasN-2][1 + (numColumnN-37)];
                        for(int i=0;i<numFilasN-2;i++){
                        	for(int j=0;j<(1+numColumnN-37);j++){
                        		if(j==0){
                        			tablaAvisosUrg[i][j] = new Object();
                        			tablaAvisosUrg[i][j] = hoja.getCell(0,i+2).getContents();
                        		
                        		}else{
                        			tablaAvisosUrg[i][j] = new Object();
                            		tablaAvisosUrg[i][j] = hoja.getCell(j+36,i+2).getContents();
                        		}
                        	}
                        }
    
                        //	Leer novedades
                        hoja = archivoExcel.getSheet(6);
                        numFilasN = hoja.getRows();
                        int numColumN = hoja.getColumns();
                   //     System.out.println("Numero de columnas: " + numColumN);
                        contenidoNovedades = new String[numFilasN-1][numColumN];
                        for(int fila = 0;fila<numFilasN-1;fila++){
                        	for(int colum = 0; colum<numColumN;colum++){
                        		contenidoNovedades[fila][colum] = hoja.getCell(colum,fila+1).getContents();
                        	//	System.out.print(contenidoNovedades[fila][colum] + "\t");
                          	}
                        //	System.out.println();
                        }
                        
                        
                        //	Leer Mensajes-avisos usuario Documentacion
                        hoja =archivoExcel.getSheet(8);
                        numFilasN = hoja.getRows();
                        numColumnN = hoja.getColumns();
                        tablaAvisosMensajesDoc = new Object[numFilasN-1][numColumnN];
                        for(int i=0;i<numFilasN-1;i++){
                        	for(int j=0;j<numColumnN;j++){
                        			tablaAvisosMensajesDoc[i][j] = hoja.getCell(j,i+1).getContents();
                         	}
                        }
                        
                        
                        //	Leer Mensajes-avisos usuario Urgencias
                        hoja =archivoExcel.getSheet(11);
                        numFilasN = hoja.getRows();
                        numColumnN = hoja.getColumns();
                        tablaAvisosMensajesUrg = new Object[numFilasN-1][numColumnN];
                        for(int i=0;i<numFilasN-1;i++){
                        	for(int j=0;j<numColumnN;j++){
                        			tablaAvisosMensajesUrg[i][j] = hoja.getCell(j,i+1).getContents();
                         	}
                        }
         
                /*        obsoleto?
                        
                        novedadesUsuario = new String[numFilasN-2][2];
                        for(int fila = 0;fila<numFilasN-2;fila++){
                        	novedadesUsuario[fila][0] = hoja.getCell(0, fila+2).getContents();
                        	novedadesUsuario[fila][1] = hoja.getCell(37,fila+2).getContents();
                        }
                   */     
                        //	Leer mensajes-avisos
                        hoja = archivoExcel.getSheet(7);
                        numFilasN = hoja.getRows();
                        numColumN = hoja.getColumns();
                   //     System.out.println("Numero de columnas: " + numColumN);
                        contenidoMensajes = new String[numFilasN-1][numColumN];
                        for(int fila = 0;fila<numFilasN-1;fila++){
                        	for(int colum = 0; colum<numColumN;colum++){
                        		contenidoMensajes[fila][colum] = hoja.getCell(colum,fila+1).getContents();
                        		//System.out.print(contenidoMensajes[fila][colum] + "\t");
                          	}
                        //	System.out.println();
                        }
                        
                        //	Leer mensajes-DocumentosModelos Nuevos
                        hoja = archivoExcel.getSheet(9);
                        numFilasN = hoja.getRows();
                        numColumnN = hoja.getColumns();
                        tablaAvisoDocNuevo = new Object[numFilasN-1][numColumnN];
                        for(int i=0;i<numFilasN-1;i++){
                        	for(int j=0;j<numColumnN;j++){
                        		tablaAvisoDocNuevo[i][j] = hoja.getCell(j,i+1).getContents();
                        	//	System.out.print(tablaAvisoDocNuevo[i][j].toString() + "\t");
                         	}
                        //	System.out.println();
                        }
                        
                    
                        				
		}catch(Exception ioe){
			ioe.printStackTrace();
		}
	}
	
     Object[][] getListado(){
		return tablaDocumentos;
	}
        
     String[] getServicios(){
            return servicios;
        }
        
     DefaultListModel getHabituales(){
         return listaHabituales;
     }  
     
     DefaultListModel getHabituales2(){
    	 return listaHabituales2;
     }
        
     DefaultListModel getTodosDocumentos(){
         return listaDocumentos;
        }
     
     DefaultComboBoxModel getTodosUsuarios(){
    	 return listaUsuarios;
     }
        
     DefaultListModel getDocServicio(String servicio){
            int numVinculaciones = 0;
            int numServicio =1;
            boolean encontrado = false;
            DefaultListModel vinculacionAux = new DefaultListModel();

            //  Encontramos el número del servicio
            while(!encontrado && numServicio<=numServicios){
                if(tablaDocumentos[0][numServicio].toString().contains(servicio))
                    encontrado = true;
                else
                    numServicio++;
            }
            
            //  Encontramos el número de vinculaciones
            for(int i=1;i<=numDocumentos;i++){
                if (tablaDocumentos[i][numServicio].toString().equals("x"))
                    numVinculaciones++;
            }
            
            vinculacionServicio = new DefaultListModel();
            
            //  Devolvemos las vinculaciones en un array de cadena
            String[] vinculaciones = new String[numVinculaciones];

            InicioIanus.documentosServicio = new ArrayList<Object>();

            for(int i = 1; i<=numDocumentos;i++){
                if(tablaDocumentos[i][numServicio].toString().equals("x")){
                    vinculacionServicio.addElement(tablaDocumentos[i][0].toString());
                    InicioIanus.documentosServicio.add(tablaDocumentos[i][0].toString());
                }
            }
            
            
            //	Quitamos de la lista de documentos del servicio, los que ya estén en habituales
            
            int tamaño = habituales.length + habituales2.length;
            int tamaño1 = habituales.length;
            String[] todosLosHabituales = new String[tamaño];
    		for(int i=0;i<tamaño1;i++){
    			todosLosHabituales[i] = habituales[i];
    		}
    		for(int i=tamaño1;i<tamaño;i++){
    			todosLosHabituales[i] = habituales2[i-tamaño1];
    		}
 
            for(int i = 0; i<vinculacionServicio.size();i++){
            	encontrado = false;
            	for(int j=0;(j<tamaño && !encontrado);j++){
                	if(vinculacionServicio.getElementAt(i).equals(todosLosHabituales[j])){
                		encontrado = true;
                 	}
            	}
            	if(!encontrado){
            		vinculacionAux.addElement(vinculacionServicio.getElementAt(i));
            	}
            }

            return vinculacionAux;
            
            
            
        }
     
     String[] getDocServicioVisor(String servicio){
         int numVinculaciones = 0;
         int numServicio =1;
         boolean encontrado = false;
         //  Encontramos el número del servicio
         while(!encontrado && numServicio<numServicios){
             if(servicio == tablaDocumentos[0][numServicio])
                 encontrado = true;
             else
                 numServicio++;
         }

         //  Encontramos el número de vinculaciones
         for(int i=1;i<numDocumentos;i++){
             if (tablaDocumentos[i][numServicio].toString().equals("x"))
                 numVinculaciones++;
         }

         String[] vinculacionServicio = new String[numVinculaciones];
         
         //  Devolvemos las vinculaciones en un array de cadena
         String[] vinculaciones = new String[numVinculaciones];
         int aux=0;
         for(int i = 1; i<numDocumentos;i++){
             if(tablaDocumentos[i][numServicio].toString().equals("x")){
                 vinculacionServicio[aux]= tablaDocumentos[i][0].toString();
                 aux++;
             }
         }
         
         return vinculacionServicio;
     }
     
     Point[] getPreferencias(String nombreUser, int numPantallas, int numIanus){
    	 int numUsers = tablaCoordenadas.length;
    	 Point[] parejaCoordenadas = new Point[4];
    	 for(int i= 0;i<4;i++)
    		 parejaCoordenadas[i]= new Point();

    	 int indice;
    	 if(numPantallas == 1 & numIanus == 1)
    		 indice = 1;
    	 else if(numPantallas == 1 & numIanus == 2)
    		 indice = 10;
    	 else if(numPantallas == 2 & numIanus == 1)
    		 indice = 19;
    	 else
    		 indice = 28;
    	 
    		 
    	 for(int i=0;i<numUsers;i++){
    		 if(tablaCoordenadas[i][0].toString().contains(nombreUser)){
    			 if(!(tablaCoordenadas[i][1].toString().contains("N"))){
    				 coordenadasGrabadas = true;
	    			 for(int j=0;j<4;j++){
	    				 parejaCoordenadas[j].x = Integer.parseInt(tablaCoordenadas[i][indice +1].toString());indice++;
	    				 parejaCoordenadas[j].y = Integer.parseInt(tablaCoordenadas[i][indice +1].toString());indice++;   				 
	    			 }
	    			 break;
    			 }
    		 }
     	 }
    	 
    	 return parejaCoordenadas;
     }
     
     Boolean getGrabado(){
    	 return coordenadasGrabadas;
     }
     
     
     int[][] getCoordenadasIanus(int auxPantallas){
    	 int tabla[][] = new int[auxPantallas][3];     // Vale 6 para 1 pantalla, 12 para 2 pantallas
    	 if(InicioIanus.numeroPantallas == 2 && !InicioIanus.nombrePc.equals("hpchpMAHC14p")){
    		// System.out.println("2 pantallas");
    		 for(int i=0;i<6;i++){
    			 for(int j=0;j<3;j++){
    				 tabla[i][j] = Integer.parseInt(tablaCoordenadasIanus[8+i][j+1].toString());
    		//		 System.out.print(tabla[i][j] + "\t");
    			 }
    		 }
    		 for(int i=0;i<6;i++){   			 
       			 for(int j=0;j<3;j++){
    				 tabla[i+6][j] = Integer.parseInt(tablaCoordenadasIanus[15+i][j+1].toString());
    		 //	System.out.println("");
       			 }
    		 }
     	 }else if(InicioIanus.numeroPantallas == 1){
    		// System.out.println("1 pantallas");
     	   	 for(int i=0;i<6;i++){
     	   		 for(int j=0;j<3;j++){
     	   			 tabla[i][j] = Integer.parseInt(tablaCoordenadasIanus[1+i][j+1].toString());
     	   		//	 System.out.print(tabla[i][j] + "\t");
     	   		 }
     	   	 	// System.out.println("");	
     	 	}
     	 }else if(InicioIanus.nombrePc.equals("hpchpMAHC14p")){
     		// System.out.println("2 colosos");
    		 for(int i=0;i<6;i++){
    			 for(int j=0;j<3;j++){
    				 tabla[i][j] = Integer.parseInt(tablaCoordenadasIanus[22+i][j+1].toString());
    		//		 System.out.print(tabla[i][j] + "\t");
    			 }
    		 }
    		 for(int i=0;i<6;i++){   			 
       			 for(int j=0;j<3;j++){
    				 tabla[i+6][j] = Integer.parseInt(tablaCoordenadasIanus[15+i][j+1].toString());
    		 //	System.out.println("");
       			 }
    		 }
     	 }
    	 
    	 
		return tabla;
	}

     
     //	Devuelve al cadena con novedades del usuario de documentacion
     ArrayList<String> getNovedadesDoc(String user){
    	 
    	 int tam = tablaAvisosDoc.length;
    	 arraysAvisos = new AvisosUsuario[tam];
    	 for(int i=0;i<tam;i++){
    		 arraysAvisos[i] = new AvisosUsuario();
    	 }
  //  	 System.out.println("Numero de filas avisos: " + tam);
    	 for(int i=0;i<tam;i++){
    		 if(tablaAvisosDoc[i][0].toString().equals(user)){
    			 numUsuarioDoc = i;
         		 arraysAvisos[i].usuario = tablaAvisosDoc[i][0].toString();
       //  		 System.out.println(arraysAvisos[i].usuario.toString());
         		 for(int j=1;j<tablaAvisosDoc[i].length;j++){
         			 if(!tablaAvisosDoc[i][j].toString().equals("N")){
         				 arraysAvisos[i].numeroOrdenAviso.add(tablaAvisosDoc[i][j].toString());
         			 }
         			 else{
         				 arraysAvisos[i].numeroOrdenAviso.add("N");
         				 break;
         			 }
         		 }
    		 }

    	 }
	 
    	 return arraysAvisos[numUsuarioDoc].numeroOrdenAviso;

     }
     
     //	Devuelve la cadena con novedades del usuario de urgencias
    ArrayList<String> getNovedadesUrg(String user){
    	 
    	 int tam = tablaAvisosUrg.length;
    	 arraysAvisos = new AvisosUsuario[tam];
    	 for(int i=0;i<tam;i++){
    		 arraysAvisos[i] = new AvisosUsuario();
    	 }
 
    	 for(int i=0;i<tam;i++){
    		 if(tablaAvisosUrg[i][0].toString().equals(user)){
    			 numUsuarioUrg = i;
         		 arraysAvisos[i].usuario = tablaAvisosUrg[i][0].toString();
         		 for(int j=1;j<tablaAvisosUrg[i].length;j++){
         			 if(!tablaAvisosUrg[i][j].toString().equals("N")){
         				 arraysAvisos[i].numeroOrdenAviso.add(tablaAvisosUrg[i][j].toString());
         			 }
         			 else{
         				 arraysAvisos[i].numeroOrdenAviso.add("N");
         				 break;
         			 }
         		 }
    		 }

    	 }
	 
    	 return arraysAvisos[numUsuarioUrg].numeroOrdenAviso;

     }
     
     
   //	Devuelve un array con el número de orden de los nuevos avisos para Documentacion  
   ArrayList<String> getMensajesNuevosDoc(String user){
    	
	   	ArrayList<String> arrayMensajes = new ArrayList<String>();
	   
    	 int tam = tablaAvisosMensajesDoc.length;
    	 for(int i=0;i<tam;i++){
    		 if(tablaAvisosMensajesDoc[i][0].toString().contains(user)){
    			 for(int j=1;j<tablaAvisosMensajesDoc[i].length;j++){
    				 if(!tablaAvisosMensajesDoc[i][j].toString().contains("N")){
    					 arrayMensajes.add(tablaAvisosMensajesDoc[i][j].toString());
    				 }else{
    					 arrayMensajes.add("N");
    					 break;
    				 }
    			 }
    		 }
    	 }
    	 
    	 return arrayMensajes;
  
     }
     
  
   //	Devuelve un array con el número de orden de los nuevos mensajes para Urg 
   ArrayList<String> getMensajesNuevosUrg(String user){
    	
	   	ArrayList<String> arrayMensajes = new ArrayList<String>();
	   
    	 int tam = tablaAvisosMensajesUrg.length;
    	 for(int i=0;i<tam;i++){
    		 if(tablaAvisosMensajesUrg[i][0].toString().contains(user)){
    			 for(int j=1;j<tablaAvisosMensajesUrg[i].length;j++){
    				 if(!tablaAvisosMensajesUrg[i][j].toString().contains("N")){
    					 arrayMensajes.add(tablaAvisosMensajesUrg[i][j].toString());
    				 }else{
    					 arrayMensajes.add("N");
    					 break;
    				 }
    			 }
    		 }
    	 }
    	 
    	 return arrayMensajes;
  
     }
   
   
   //	Obtiene el numero de orden de las novedades en Documentos Nuevos
   ArrayList<String> getDocYmodelNuevos(String user){
   	
	   	ArrayList<String> arrayDocumentosNuevos = new ArrayList<String>();
	   
	   	int tam = this.tablaAvisoDocNuevo.length;
		for(int i=0;i<tam;i++){
		   		if(tablaAvisoDocNuevo[i][0].toString().contains(user)){
		   			for(int j=1;j<tablaAvisoDocNuevo[i].length;j++){
		   				if(!tablaAvisoDocNuevo[i][j].toString().contains("N")){
	   					 arrayDocumentosNuevos.add(tablaAvisoDocNuevo[i][j].toString());
	   					
		   				}else{
	   					 arrayDocumentosNuevos.add("N");
	   					 break;
		   				}
		   			}
		   		}
	   	 }
   	 
   	 return arrayDocumentosNuevos;
 
    }

   AvisosDocumentos getDatosNuevoDocumento(int numeroOrden){
	   
	   int fila =0;
	   
	   AvisosDocumentos av = new AvisosDocumentos();
	   av.nombreDocumento = tablaVisor[numeroOrden][1].toString();
	   av.rutaJpg = tablaVisor[numeroOrden][0].toString();
	   av.documentoNuevo = tablaVisor[numeroOrden][12].toString();


	   if(!av.documentoNuevo.contains("N")){
		   fila = Integer.parseInt(av.documentoNuevo);
	   }else{
		   for(int i = 0;i<numDocumentos;i++){
				   if(tablaDocumentos[i+1][0].toString().contains(av.nombreDocumento)){
					   fila = i + 1;
					   break;
				   }
			   }
	   }

	   for(int i = 0;i<numServicios;i++){
		   if(tablaDocumentos[fila][i+2].toString().toLowerCase().contains("x")){
			   av.serviciosDocumento.add(tablaDocumentos[0][i+2].toString());
		   }
	   }
	   
	   av.observacionesDocumento = tablaVisor[numeroOrden][5].toString();
	   return av;
   }
     
   
   DatosDocumentos getDatosDocumentoModificar(){
	   
	   int fila =0;
	   int numeroOrden = 0;
	   int auxInt = InicioIanus.auxRutaImagen.lastIndexOf(".");
	   String auxString = "";
	   if(auxInt <0){
		   auxString = InicioIanus.auxRutaImagen;
	   }else{
		    auxString = InicioIanus.auxRutaImagen.substring(0, auxInt);
	   }
	   
	   
	   for(int i=0;i<tablaVisor.length;i++){
		   if(tablaVisor[i][0].toString().contains(auxString)){
			   numeroOrden = i;
			   break;
		   }
	   }
	   
	   DatosDocumentos dd = new DatosDocumentos();
	   dd.nombreDocumento = tablaVisor[numeroOrden][1].toString();
	   dd.rutaJpg = tablaVisor[numeroOrden][0].toString();

	   for(int i = 0;i<numDocumentos;i++){
			if(tablaDocumentos[i+1][0].toString().toLowerCase().contains(dd.nombreDocumento.toLowerCase())){
					   fila = i + 1;
					   break;
			}
	   }
	   for(int i = 0;i<numServicios;i++){
		   System.out.println(numServicios + " " + fila);
		   if(tablaDocumentos[fila][i+2].toString().toLowerCase().contains("x")){
			   dd.serviciosDocumento.add(tablaDocumentos[0][i+2].toString());
			   System.out.println(tablaDocumentos[0][i+2].toString());
		   }
	   }
	   
	   for(int i = 0;i<5;i++){
		   if(!tablaVisor[numeroOrden][i+6].toString().isEmpty()){
			   dd.metaDatos.add(tablaVisor[numeroOrden][i+6].toString());
		   }
	   }
	   
	   dd.apariencia[0] = tablaVisor[numeroOrden][4].toString();
	   dd.apariencia[1] = tablaVisor[numeroOrden][3].toString();
	   
	   dd.observacionesDocumento = tablaVisor[numeroOrden][5].toString();
	   return dd;
   }
   
   
   
     String getContenidoNovedad(int fila){
    	 return contenidoNovedades[fila-1][1];
     }
     
     
     String getContenidoMensaje(int fila){
    	 String cadena = contenidoMensajes[fila-1][0];
    	 cadena += "\n" + contenidoMensajes[fila-1][1];
    	 return cadena;
     }
     
     
     
      
     int getNumUsuario(int usuarioDoc){
    	 if(usuarioDoc != 0 && usuarioDoc != 3){
        	 return numUsuarioDoc;
    	 }else{
    	  	 int tam = tablaAvisosUrg.length;

        	 for(int i=0;i<tam;i++){
        		 if(tablaAvisosUrg[i][0].toString().equals(InicioIanus.usuario)){
        			 numUsuarioUrg = i;
            		 break;
             	 }
             }

        	 return numUsuarioUrg;
    	 }

     }
     
     //	Devuelve el nombre de la foto de la norma
     String getFotoNovedad(int fila){
    	 return contenidoNovedades[fila-1][2];
     }
     
     //	Devuelve el nombre de la foto del mensaje
     String getFotoMensaje(int fila){
    	 return contenidoMensajes[fila-1][2];
     }
     
     //	Devuelve una cadena con los nombres de todos los servicios
     String getServicioNovedad(int fila){
    	 ArrayList<String> listaServicios = new ArrayList<String>();
     	 int j=0;
    	 String auxS = "";
    	 while(!contenidoNovedades[fila-1][j+3].equals("N")){
	    		 listaServicios.add(contenidoNovedades[fila-1][j + 3]);
	    		 if(j != 0){
	    			 auxS += ", " + listaServicios.get(j);
	    		 }else{
	    			 auxS += listaServicios.get(j);
	    		 }
	    	//	 System.out.println(auxS);
	    		 j++;
    	 }
    	 if(j!=0){
    		 auxS +=".";
    	 }
  
    	 return  auxS;
     }
     
     
     //	Devuelve una cadena con el nombre del emisor del mensaje
     String getEmisorMensaje(int fila){
    	 return contenidoMensajes[fila-1][3].toString();
     }
     
}


//	Clase estructura de datos de los avisos del usuario

class AvisosUsuario{
	String usuario;
	ArrayList<String> numeroOrdenAviso;
	
	AvisosUsuario(){
		usuario = "";
		numeroOrdenAviso = new ArrayList<String>();
	}
	
}
   
//	Clase estructura de datos del contenido de los avisos por nuevos documentos
class AvisosDocumentos{
	String nombreDocumento;
	String rutaJpg;
	ArrayList<String> serviciosDocumento;
	String observacionesDocumento;
	String documentoNuevo;
	
	AvisosDocumentos(){
		nombreDocumento = "";
		rutaJpg = "";
		serviciosDocumento = new ArrayList<String>();
		observacionesDocumento = "";
		documentoNuevo = "";
	}
}


//Clase estructura de datos del contenido de los avisos por nuevos documentos
class DatosDocumentos{
	String nombreDocumento;
	String rutaJpg;
	ArrayList<String> serviciosDocumento;
	ArrayList<String> metaDatos;
	String apariencia[];
	String observacionesDocumento;
	
	
	DatosDocumentos(){
		nombreDocumento = "";
		rutaJpg = "";
		serviciosDocumento = new ArrayList<String>();
		metaDatos = new ArrayList<String>();
		apariencia = new String[2];
		for(int i=0;i<2;i++){
			apariencia[i] = "";
		}
		observacionesDocumento = "";
	}
}
