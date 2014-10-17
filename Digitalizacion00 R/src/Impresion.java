import java.awt.AWTException;
import java.awt.Color;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;


public class Impresion {
	
	int pantallas;

	public void imprime(String cadena, boolean pulsadaBarraEspaciadora,int tipoDocAsubirForzado, boolean repetir){
		RobotIanus robotI = new RobotIanus();
		InicioIanus.nombreDocumento = cadena;
		
		/****************  Calcula las coordenadas correctas para el documento en cuestion  ***********/
		
		if(tipoDocAsubirForzado == 0){
			

			if(InicioIanus.comboServicios.getSelectedItem().toString().contains("HOSP") 		|| 
	        		InicioIanus.comboServicios.getSelectedItem().toString().contains("URG")){
	        				InicioIanus.tipoDocAsubir = 1;
	        
	        }else if(InicioIanus.comboServicios.getSelectedItem().toString().contains("CIA")){
	        	InicioIanus.tipoDocAsubir = 3;
		    }else{
	        	InicioIanus.tipoDocAsubir = 2;
	        }
			
			// Subir enfermería de dig como ingreso  
		    if(InicioIanus.comboServicios.getSelectedItem().toString().contains("DIG") && 
					(cadena.contains(InicioIanus.ENFERMERIA_ENDOSCOPIAS) ||
			   				 cadena.contains(InicioIanus.ENDOSCOPIA_DIGESTIVA) ||
			   				 cadena.contains(InicioIanus.MANOMETRIA) ||
			   				cadena.contains(InicioIanus.PHMETRIA))){
			       	 
			        	InicioIanus.tipoDocAsubir = 1; //
		    }
		    
		    if(InicioIanus.comboServicios.getSelectedItem().toString().contains("HOSP") 		&& 
		    		cadena.contains(InicioIanus.CONSENTIMIENTO)){
	        				InicioIanus.tipoDocAsubir = 2;
	        
	        }
		}
		else {
			InicioIanus.tipoDocAsubir = tipoDocAsubirForzado;
		}
		    
		    pantallas = InicioIanus.numeroPantallas;
		    
	        if(pantallas!= 1){ pantallas = 4;}
   
	        
        
        /**************   Imprime en Ianus     *****************************************************/
        
	        robotI.robotIanus(cadena, 1000, pantallas, InicioIanus.tipoDocAsubir, "",pulsadaBarraEspaciadora,repetir);
        
        
        
	        InicioIanus.estadistica.añadirDato(InicioIanus.servicioCombo, InicioIanus.nombreDocumento); // Revisar
        
        
        /***************** Abre los siguientes pdfs  **************************************************/
       
        int tamañoLista = VentanaExplorador.listaPdfs.getModel().getSize();

        if(InicioIanus.numPdfsAbiertos == 0){
        	if(VentanaExplorador.numArchivo + 1< tamañoLista){
        		
        		VentanaExplorador.numArchivo++;
        		
        		VentanaExplorador.listaPdfs.setSelectedIndex(VentanaExplorador.numArchivo);
         		int pdfNumero = VentanaExplorador.listaPdfs.getSelectedIndex();
         		InicioIanus.pdfSeleccionado = pdfNumero;
           		
         		InicioIanus.tandaDePdfs[0] = new File(InicioIanus.rutaCompletaPdfs[pdfNumero]);
        		try{

        			Inicio.navegador1.webBrowser.setVisible(true);
        			Inicio.navegador1.setPdf(InicioIanus.tandaDePdfs[0].getAbsolutePath().toString(),
        					InicioIanus.tandaDePdfs[0].getName().toString(),new Color(80,200,120));
       			   
        			//System.out.println(InicioIanus.tandaDePdfs[0].getName().toString());
        			
       			    InicioIanus.numPdfsAbiertos = 1;
       			    
	       			if(InicioIanus.documentacion == 2 || InicioIanus.documentacion == 3){
	         			InicioIanus.teclaAzul1.setText(InicioIanus.documentos[pdfNumero].nhc);
	         			InicioIanus.teclaAzul2.setText(InicioIanus.documentos[pdfNumero].nombreNormalizado);
	         			
	         			
	         			if(InicioIanus.ocr2IanusAutomatico){
	        				InicioIanus.botonIanus1.setText(InicioIanus.nhcIanus1);
	             			InicioIanus.botonIanus2.setText(InicioIanus.nhcIanus2);
	        			}
	        			else{
	        				InicioIanus.botonIanus1.setText(InicioIanus.documentos[pdfNumero].nhc);
	        			}

	         			InicioIanus.botonServicio.setText(InicioIanus.documentos[pdfNumero].servicio);
	         			InicioIanus.botonNombreNormalizado.setText(InicioIanus.documentos[pdfNumero].nombreNormalizado);
	         			
	         			if(!InicioIanus.comboServicios.getSelectedItem().toString().contains(InicioIanus.documentos[pdfNumero].servicio)){
	         				InicioIanus.botonServicio.setBackground(Color.red);
	         				Inicio.navegador1.ocrPanel.setBackground(Color.yellow);
	         				Inicio.navegador1.ocrBotonesVelocidad.setBackground(Color.yellow);
	         				Inicio.navegador1.ocrBotonesPanel.setBackground(Color.yellow);
	         				Inicio.navegador1.ocrControlPanel.setBackground(Color.yellow);
	         				Inicio.navegador1.radioButtonAuto.setBackground(Color.yellow);
	         				Inicio.navegador1.radioButtonExtremo.setBackground(Color.yellow);
	         				Inicio.navegador1.radioButtonManual.setBackground(Color.yellow);
	         				Inicio.navegador1.radioButtonRapido.setBackground(Color.yellow);
	         				
	         			}
	         			else{
	         				InicioIanus.botonServicio.setBackground(Color.green);
	         				Inicio.navegador1.ocrPanel.setBackground(new Color(255,222,173));
	         				Inicio.navegador1.ocrBotonesVelocidad.setBackground(new Color(255,222,173));
	         				Inicio.navegador1.ocrBotonesPanel.setBackground(new Color(255,222,173));
	         				Inicio.navegador1.ocrControlPanel.setBackground(new Color(255,222,173));
	         				Inicio.navegador1.radioButtonAuto.setBackground(new Color(255,222,173));
	         				Inicio.navegador1.radioButtonExtremo.setBackground(new Color(255,222,173));
	         				Inicio.navegador1.radioButtonManual.setBackground(new Color(255,222,173));
	         				Inicio.navegador1.radioButtonRapido.setBackground(new Color(255,222,173));
	         			}
	         			
	         			if(!InicioIanus.documentos[pdfNumero].nhc.equals(InicioIanus.documentos[pdfNumero-1].nhc)){
	         				Inicio.navegador1.ocrPanel.setBackground(Color.red);
	         				Inicio.navegador1.webBrowserPanel.setBackground(Color.red);
	         				Inicio.navegador1.ocrBotonesVelocidad.setBackground(Color.red);
	         				Inicio.navegador1.ocrBotonesPanel.setBackground(Color.red);
	         				Inicio.navegador1.ocrControlPanel.setBackground(Color.red);
	         				Inicio.navegador1.radioButtonAuto.setBackground(Color.red);
	         				Inicio.navegador1.radioButtonExtremo.setBackground(Color.red);
	         				Inicio.navegador1.radioButtonManual.setBackground(Color.red);
	         				Inicio.navegador1.radioButtonRapido.setBackground(Color.red);
	         			}
	         			else{
	         				Inicio.navegador1.ocrPanel.setBackground(new Color(255,222,173));
	         				Inicio.navegador1.webBrowserPanel.setBackground(new Color(80,200,120));
	         				Inicio.navegador1.ocrBotonesVelocidad.setBackground(new Color(255,222,173));
	         				Inicio.navegador1.ocrBotonesPanel.setBackground(new Color(255,222,173));
	         				Inicio.navegador1.ocrControlPanel.setBackground(new Color(255,222,173));
	         				Inicio.navegador1.radioButtonAuto.setBackground(new Color(255,222,173));
	         				Inicio.navegador1.radioButtonExtremo.setBackground(new Color(255,222,173));
	         				Inicio.navegador1.radioButtonManual.setBackground(new Color(255,222,173));
	         				Inicio.navegador1.radioButtonRapido.setBackground(new Color(255,222,173));
	         				
	         			}
	         			
	         			InicioIanus.comboServicios.setSelectedItem(InicioIanus.documentos[pdfNumero].servicio);
	         		}
       			 
       			    
        		}catch (Exception ev) {
        			System.out.println(ev);
        		}
        	}
        	if(InicioIanus.numeroIanus == 2 && VentanaExplorador.numArchivo + 1 < tamañoLista){
        		VentanaExplorador.numArchivo++;
        		//System.out.println(VentanaExplorador.numArchivo);
        		if(VentanaExplorador.numArchivo < tamañoLista){
            		VentanaExplorador.listaPdfs.setSelectedIndex(VentanaExplorador.numArchivo);
             		int pdfNumero = VentanaExplorador.listaPdfs.getSelectedIndex();
            
             		InicioIanus.tandaDePdfs[1] = new File(InicioIanus.rutaCompletaPdfs[pdfNumero]);
            		try{
            			
            			Inicio.navegador2.webBrowser.setVisible(true);
           			    Inicio.navegador2.setPdf(InicioIanus.tandaDePdfs[1].getAbsolutePath().toString(),
            					InicioIanus.tandaDePdfs[1].getName().toString(), new Color(255,246,143));
           			    
           			    InicioIanus.numPdfsAbiertos = 2;
           			    
	           			
            		}catch (Exception ev) {
            			System.out.println(ev);
            		}
            	}
 
        	}
        }
	}

	
}
