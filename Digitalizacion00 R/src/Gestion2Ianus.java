import java.awt.AWTException;
import java.awt.Color;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.JOptionPane;


public class Gestion2Ianus {

	int indiceNhc1 = 0;
	int indiceNhc2 = 0;
	
	Gestion2Ianus(){
		buscaNHCInicio();
	}
	
	private void buscaNHCInicio(){
		for(int i=0;i<InicioIanus.documentos.length;i++){
			if(!InicioIanus.documentos[i].nhc.contains("Separador")){
				InicioIanus.nhcIanus1 = InicioIanus.documentos[i].nhc;
				indiceNhc1 = i;
				break;
			}
		}
		
		for(int i=indiceNhc1 + 1;i<InicioIanus.documentos.length;i++){
			if(!InicioIanus.documentos[i].nhc.equals(InicioIanus.nhcIanus1) &&
					!InicioIanus.documentos[i].nhc.contains("Separador")){
				InicioIanus.nhcIanus2 = InicioIanus.documentos[i].nhc;
				indiceNhc2 = i;
				break;
			}
		}
	}
	
	
	public void impresionInicial(){
		
		VentanaExplorador.numArchivo = indiceNhc1;
		VentanaExplorador.listaPdfs.setSelectedIndex(VentanaExplorador.numArchivo);
		InicioIanus.pdfSeleccionado = indiceNhc1;
		
		InicioIanus.tandaDePdfs[0] = new File(InicioIanus.rutaCompletaPdfs[indiceNhc1]);
		
		Inicio.navegador1.webBrowser.setVisible(true);
		
		
		Inicio.navegador1.setPdf(InicioIanus.tandaDePdfs[0].getAbsolutePath().toString(),
				InicioIanus.tandaDePdfs[0].getName().toString(),new Color(80,200,120));
		
		InicioIanus.numPdfsAbiertos = 1;
		 
		if(InicioIanus.documentacion == 2 || InicioIanus.documentacion == 3){
 			InicioIanus.teclaAzul1.setText(InicioIanus.documentos[indiceNhc1].nhc);
 			InicioIanus.teclaAzul2.setText(InicioIanus.documentos[indiceNhc1].nombreNormalizado);
 			
 			
 			// InicioIanus.botonIanus1.setText(InicioIanus.nhcIanus1);
 			// InicioIanus.botonIanus2.setText(InicioIanus.nhcIanus2);
 			InicioIanus.botonServicio.setText(InicioIanus.documentos[indiceNhc1].servicio);
 			InicioIanus.botonNombreNormalizado.setText(InicioIanus.documentos[indiceNhc1].nombreNormalizado);
 			
 			if(!InicioIanus.comboServicios.getSelectedItem().toString().equals(InicioIanus.documentos[indiceNhc1].servicio)){
 				InicioIanus.botonServicio.setBackground(Color.red);
 				Inicio.navegador1.ocrPanel.setBackground(Color.yellow);
 			}
 			else{
 				InicioIanus.botonServicio.setBackground(Color.green);
 				Inicio.navegador1.ocrPanel.setBackground(new Color(255,222,173));
 			}
 			InicioIanus.comboServicios.setSelectedItem(InicioIanus.documentos[indiceNhc1].servicio);
 		}
				
		InicioIanus.botonIanus1.setText(InicioIanus.nhcIanus1);
		InicioIanus.botonIanus2.setText(InicioIanus.nhcIanus2);
		
		// activa ianus1 y pega nhc1
		introduceNHC(InicioIanus.nhcIanus1, KeyEvent.VK_F10);

		// activa ianus2 y pega nhc2
		introduceNHC(InicioIanus.nhcIanus2, KeyEvent.VK_F12);
		// minimiza ianus2
		// minimizaVentana();

		// activa ianus1
		retardo(InicioIanus.retardoInterIanus);
		//for(int i=0; i<1000;i++)
		activaIanus(KeyEvent.VK_F7);
		//fijaVentana();
		
		//retardo(1000);
			// activaIanus(KeyEvent.VK_F7);
		InicioIanus.nhc1Seleccionado = true;
		

	}
	
	public void reset(){
		
		reseteaTeclas();
		
		indiceNhc1 = 0;
		indiceNhc2 = 0;
		
		indiceNhc1 = VentanaExplorador.listaPdfs.getSelectedIndex();
		
		for(int i=indiceNhc1;i<InicioIanus.documentos.length;i++){
			if(!InicioIanus.documentos[i].nhc.contains("Separador")){
				InicioIanus.nhcIanus1 = InicioIanus.documentos[i].nhc;
				indiceNhc1 = i;
				break;
			}
		}
		
		for(int i=indiceNhc1 + 1;i<InicioIanus.documentos.length;i++){
			if(!InicioIanus.documentos[i].nhc.equals(InicioIanus.nhcIanus1) &&
					!InicioIanus.documentos[i].nhc.contains("Separador")){
				InicioIanus.nhcIanus2 = InicioIanus.documentos[i].nhc;
				indiceNhc2 = i;
				break;
			}			
		}
		
		VentanaExplorador.numArchivo = indiceNhc1;
		VentanaExplorador.listaPdfs.setSelectedIndex(VentanaExplorador.numArchivo);
		InicioIanus.pdfSeleccionado = indiceNhc1;
		
		InicioIanus.tandaDePdfs[0] = new File(InicioIanus.rutaCompletaPdfs[indiceNhc1]);
		
		
		Inicio.navegador1.setPdf(InicioIanus.tandaDePdfs[0].getAbsolutePath().toString(),
				InicioIanus.tandaDePdfs[0].getName().toString(),new Color(80,200,120));
		
		InicioIanus.numPdfsAbiertos = 1;
		 
		if(InicioIanus.documentacion == 2 || InicioIanus.documentacion == 3){
 			InicioIanus.teclaAzul1.setText(InicioIanus.documentos[indiceNhc1].nhc);
 			InicioIanus.teclaAzul2.setText(InicioIanus.documentos[indiceNhc1].nombreNormalizado);
 			
 			
 			// InicioIanus.botonIanus1.setText(InicioIanus.nhcIanus1);
 			// InicioIanus.botonIanus2.setText(InicioIanus.nhcIanus2);
 			InicioIanus.botonServicio.setText(InicioIanus.documentos[indiceNhc1].servicio);
 			InicioIanus.botonNombreNormalizado.setText(InicioIanus.documentos[indiceNhc1].nombreNormalizado);
 			
 			if(!InicioIanus.comboServicios.getSelectedItem().toString().equals(InicioIanus.documentos[indiceNhc1].servicio)){
 				InicioIanus.botonServicio.setBackground(Color.red);
 				Inicio.navegador1.ocrPanel.setBackground(Color.yellow);
 			}
 			else{
 				InicioIanus.botonServicio.setBackground(Color.green);
 				Inicio.navegador1.ocrPanel.setBackground(new Color(255,222,173));
 			}
 			InicioIanus.comboServicios.setSelectedItem(InicioIanus.documentos[indiceNhc1].servicio);
 		}
				
		InicioIanus.botonIanus1.setText(InicioIanus.nhcIanus1);
		InicioIanus.botonIanus2.setText(InicioIanus.nhcIanus2);
		
		
		// activa ianus1 y pega nhc1
		introduceNHC(InicioIanus.nhcIanus1, KeyEvent.VK_F10);

		// activa ianus2 y pega nhc2
		introduceNHC(InicioIanus.nhcIanus2, KeyEvent.VK_F12);
		// minimizaVentana();
		
		// activa ianus1
		retardo(1000);
		//for(int i=0; i<1000;i++)
			activaIanus(KeyEvent.VK_F7);   //	Simplemente activa el ianus 1. No pulsa tecla
			//fijaVentana();
			
			/**   jugar con esta parte ***********************
			retardo(1000);
			activaIanus(KeyEvent.VK_F7);  //	
			
			**************************************************/
			
		InicioIanus.nhc1Seleccionado = true;
		
		
	}
	
	private void introduceNHC(String nhc, int teclaIanus){
    	try {
			Robot robot = new Robot();
			robot.keyPress(teclaIanus);
			robot.keyRelease(teclaIanus);
			robot.delay(200);
			
			/*robot.keyPress(teclaIanus);
			robot.keyRelease(teclaIanus);
			robot.delay(200);
			*/
			
			CopEnPortapapeles copiar = new CopEnPortapapeles();
			copiar.copiarAlPortapapeles(nhc);
			
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			robot.delay(400);
			
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
			robot.delay(InicioIanus.retardoInterIanus);
			
			
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
	
	public void gestion(){
		if (InicioIanus.nhc1Seleccionado){
			//System.out.println("Número de pdf: " + InicioIanus.pdfSeleccionado);
			 if(InicioIanus.documentos[InicioIanus.pdfSeleccionado].nhc.equals(InicioIanus.nhcIanus1)){
				 				 
				 if(InicioIanus.documentos[InicioIanus.pdfSeleccionado].nhc.equals(InicioIanus.documentos[InicioIanus.pdfSeleccionado-1].nhc)
							&& /*(!InicioIanus.documentos[InicioIanus.pdfSeleccionado].servicio.equals("DIG") && */ !InicioIanus.documentos[InicioIanus.pdfSeleccionado].servicio.equals("ALG")){
						 // JOptionPane.showMessageDialog(null, "En principio es el mismo paciente");
						 //	InicioIanus.tipoSubida = 1;
					 	int nodoDocumentoActual = 0;
					 	int nodoDocumentoAnterior = 0;
					 	
					 	if(InicioIanus.documentos[InicioIanus.pdfSeleccionado].servicio.equals("DIG")){
					 		if(!InicioIanus.documentos[InicioIanus.pdfSeleccionado].nombreNormalizado.equals("pHmetría") &&
					 				!InicioIanus.documentos[InicioIanus.pdfSeleccionado].nombreNormalizado.equals("Manometría") &&
					 				!InicioIanus.documentos[InicioIanus.pdfSeleccionado].nombreNormalizado.equals("Enfermería endoscopias") &&
					 				!InicioIanus.documentos[InicioIanus.pdfSeleccionado].nombreNormalizado.equals("Endoscopia Digestiva")
					 				){
					 			nodoDocumentoActual = 1;
					 		}
					 		else 
					 			nodoDocumentoActual = 2;
					 	}
					 	
					 	if(InicioIanus.documentos[InicioIanus.pdfSeleccionado-1].servicio.equals("DIG")){
					 		if(!InicioIanus.documentos[InicioIanus.pdfSeleccionado-1].nombreNormalizado.equals("pHmetría") &&
					 				!InicioIanus.documentos[InicioIanus.pdfSeleccionado-1].nombreNormalizado.equals("Manometría") &&
					 				!InicioIanus.documentos[InicioIanus.pdfSeleccionado-1].nombreNormalizado.equals("Enfermería endoscopias") &&
					 				!InicioIanus.documentos[InicioIanus.pdfSeleccionado-1].nombreNormalizado.equals("Endoscopia Digestiva")
					 				){
					 			nodoDocumentoAnterior = 1;
					 		}
					 		else 
					 			nodoDocumentoAnterior = 2;
					 	}
					 	
					 
						 if(new GestionServicioPuntero().gestion() && (nodoDocumentoActual == nodoDocumentoAnterior)){
							 Impresion impr = new Impresion();
							 impr.imprime(InicioIanus.teclaAzul2.getText(),true,0);
							 
						 }
				 }
				 else{
					 JOptionPane.showMessageDialog(null, "No cambia el paciente");
				 }
			 }
			 else{
				 
				 //desFijaVentana();
				 pulsaTecla(KeyEvent.VK_F9);    //	Sale del paciente en ianus
				 
				 Inicio.navegador1.webBrowserPanel.setBackground(new Color(80,200,120));
				 
				 indiceNhc1 = buscaIndice(indiceNhc2);
				 if(indiceNhc1 != -1){
					 InicioIanus.nhcIanus1 = InicioIanus.documentos[indiceNhc1].nhc;
					 
					 System.out.println("El nhc1 siguiente va a ser: " + InicioIanus.nhcIanus1);
					 retardo(800);
					 
					 introduceNHC(InicioIanus.nhcIanus1,KeyEvent.VK_F10);
					 // retardo(200);
					 
					 // minimizaVentana();
					 
					 activaIanus(KeyEvent.VK_F8);    // Activa el ianus 2, sin pulsar nada
					 
					 //fijaVentana();
					 
	
				 }
				 else{
					 //InicioIanus.nhcIanus1 = "Fin";
					 //System.out.println("No hay más pacientes");
					 retardo(200);
					 activaIanus(KeyEvent.VK_F8);    // Activa el ianus 2, sin pulsar nada
	
				 }
				 
				 InicioIanus.botonIanus1.setBackground(Color.pink);
				 InicioIanus.botonIanus1.setText(InicioIanus.nhcIanus1);
				 InicioIanus.botonIanus2.setBackground(Color.green);
				 InicioIanus.nhc2Seleccionado = true;
				 InicioIanus.nhc1Seleccionado = false;


				 //for(int i=0;i<50;i++)
	// jugamos con esta sentencia				 pulsaTecla(KeyEvent.VK_F8);
				 // buscar el siguiente numero para nhcIanus1
			 }
		}
		else if (InicioIanus.nhc2Seleccionado){
			//System.out.println("Número de pdf: " + InicioIanus.pdfSeleccionado);
			 if(InicioIanus.documentos[InicioIanus.pdfSeleccionado].nhc.equals(InicioIanus.nhcIanus2)){
				 
				 if(InicioIanus.documentos[InicioIanus.pdfSeleccionado].nhc.equals(InicioIanus.documentos[InicioIanus.pdfSeleccionado-1].nhc)
							/*&&  (!InicioIanus.documentos[InicioIanus.pdfSeleccionado].servicio.equals("DIG") */ && !InicioIanus.documentos[InicioIanus.pdfSeleccionado].servicio.equals("ALG")){
						 // JOptionPane.showMessageDialog(null, "En principio es el mismo paciente");
						 //	InicioIanus.tipoSubida = 1;
					 
					 	int nodoDocumentoActual = 0;
					 	int nodoDocumentoAnterior = 0;
					 	
					 	if(InicioIanus.documentos[InicioIanus.pdfSeleccionado].servicio.equals("DIG")){
					 		if(!InicioIanus.documentos[InicioIanus.pdfSeleccionado].nombreNormalizado.equals("pHmetría") &&
					 				!InicioIanus.documentos[InicioIanus.pdfSeleccionado].nombreNormalizado.equals("Manometría") &&
					 				!InicioIanus.documentos[InicioIanus.pdfSeleccionado].nombreNormalizado.equals("Enfermería endoscopias") &&
					 				!InicioIanus.documentos[InicioIanus.pdfSeleccionado].nombreNormalizado.equals("Endoscopia Digestiva")
					 				){
					 			nodoDocumentoActual = 1;
					 		}
					 		else 
					 			nodoDocumentoActual = 2;
					 	}
					 	
					 	if(InicioIanus.documentos[InicioIanus.pdfSeleccionado-1].servicio.equals("DIG")){
					 		if(!InicioIanus.documentos[InicioIanus.pdfSeleccionado-1].nombreNormalizado.equals("pHmetría") &&
					 				!InicioIanus.documentos[InicioIanus.pdfSeleccionado-1].nombreNormalizado.equals("Manometría") &&
					 				!InicioIanus.documentos[InicioIanus.pdfSeleccionado-1].nombreNormalizado.equals("Enfermería endoscopias") &&
					 				!InicioIanus.documentos[InicioIanus.pdfSeleccionado-1].nombreNormalizado.equals("Endoscopia Digestiva")
					 				){
					 			nodoDocumentoAnterior = 1;
					 		}
					 		else 
					 			nodoDocumentoAnterior = 2;
					 	}
					 	
					 
						 if(new GestionServicioPuntero().gestion() && (nodoDocumentoActual == nodoDocumentoAnterior)){
							 Impresion impr = new Impresion();
							 impr.imprime(InicioIanus.teclaAzul2.getText(),true,0);
							 
						 }
				 }
				 else{
					 JOptionPane.showMessageDialog(null, "No cambia el paciente");
				 }
				 
			 }
			 else{
				 
				 //desFijaVentana();
				 pulsaTecla(KeyEvent.VK_F9);
				 
				 Inicio.navegador1.webBrowserPanel.setBackground(new Color(80,200,120));
				 
				 indiceNhc2 = buscaIndice(indiceNhc1);
				 
				 if(indiceNhc2 != -1){
					 InicioIanus.nhcIanus2 = InicioIanus.documentos[indiceNhc2].nhc;
					 
					 System.out.println("El nhc2 siguiente va a ser: " + InicioIanus.nhcIanus2);
					 retardo(800);
					 
					 // minimizaVentana();
					 
					 introduceNHC(InicioIanus.nhcIanus2,KeyEvent.VK_F12);
					 
					 //fijaVentana();
					 
					 //retardo(1500);
					 activaIanus(KeyEvent.VK_F7);

				 }
				 else{
					 //InicioIanus.nhcIanus2 = "Fin";
					 //System.out.println("No hay más pacientes");
					 retardo(200);
					 activaIanus(KeyEvent.VK_F7);
				 }
				 
				 InicioIanus.botonIanus1.setBackground(Color.green);
				 InicioIanus.botonIanus2.setBackground(Color.pink);
				 InicioIanus.botonIanus2.setText(InicioIanus.nhcIanus2);
				 InicioIanus.nhc2Seleccionado = false;
				 InicioIanus.nhc1Seleccionado = true;
				 

				 
				 
				 //for(int i=0;i<50;i++)
		// jugamos con esta sentencia			 pulsaTecla(KeyEvent.VK_F7);

				 // buscar el siguiente numero para nhcIanus2
			 }
		}
	}
	
	public void activaIanus(int teclaIanus){
		try {
			Robot robot = new Robot();
			robot.keyPress(teclaIanus);
			robot.keyRelease(teclaIanus);
			robot.delay(200);
			
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
    public void pulsaTecla(int tecla){
    	try {
			Robot robot = new Robot();
			robot.keyPress(tecla);
			robot.keyRelease(tecla);
			robot.delay(200);
						
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    
    public void minimizaVentana(){
    	
    	try {
			Robot robot = new Robot();
			
			robot.mouseMove(InicioIanus.coordMinimizar.x, InicioIanus.coordMinimizar.y);
			robot.delay(100);
			robot.mousePress(InputEvent.BUTTON1_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_MASK);
			robot.delay(200);
									
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    
    public void reseteaTeclas(){
    	try {
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_F6);
			robot.keyRelease(KeyEvent.VK_F6);
			robot.delay(100);
			
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    
	public void retardo(int tiempo){
		try {
			Robot robot = new Robot();
			robot.delay(tiempo);
			
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void fijaVentana(){
		try {
			Robot robot = new Robot();
			
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_SPACE);
			robot.keyRelease(KeyEvent.VK_SPACE);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			robot.delay(200);
									
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void desFijaVentana(){
		try {
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_SPACE);
			robot.keyRelease(KeyEvent.VK_SPACE);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			robot.delay(200);
									
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int buscaIndice(int indice){
		
		int nuevoIndice = -1;
		
		for(int i=indice +1 ;i<InicioIanus.documentos.length;i++){
			if(!InicioIanus.documentos[i].nhc.contains("Separador") && 
					!InicioIanus.documentos[i].nhc.equals(InicioIanus.documentos[indice].nhc)){
				nuevoIndice = i;
				break;
			}
		}
		return nuevoIndice;
	}
}
