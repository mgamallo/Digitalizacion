

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.*;
import java.io.File;

public class RobotIanus extends Thread{
	
	static final String RUTADOCANULADO = "J:\\DIGITALIZACIÓN\\DOC. ANULADO.pdf";
	static final String RUTADOCANULADOB = "H:\\DIGITALIZACIÓN\\DOC. ANULADO.pdf";
	int retardoAbrirVentanaPropiaAsociar = InicioIanus.retardoAbrirVentanaPropiaAsociar;
	
	/*******
	 	Coordenadas Asociar:
	 		Coordenada Y.... 1169  Pillado por los pelos
	 								Si no dos tipos:
	 									Consulta, consult fech, Rx, Cia  	1177
	 									Urg, Hosp,...          				1165
	 		Coordenada X....
	 						Dos tipos:
	 						Urg, hosp, cia, cons fecha....					1570
	 						Cons, Rx...										1780
	 *******/
	
	
	int pdfASubir;
	int tamañoCarpeta;
	
	Point teclaAsociar = new Point();
	
	String cadenaAimprimir = "";
	
	//	Coordenadas area de texto nombre normalizado
	int x1 = 0;
	int y1 = 0;
	
	//	Coordenadas area de texto ruta archivo
	int rX = 600, rY = 400;
	
	//	Coordenadas boton aceptar subir
	int acX = 0, acY = 0;
	
	// Coordenadas del ultimo aceptar
	int aceptarManualX=1627, aceptarManualY=710; 
	
	//	Constructor
	
	//  creo no se utiliza
	public void run(){
		
		// InicioIanus.ventanaBotonesTeclas.jPanel.requestFocus();
		System.out.println("RequestFocus");
		
		try {
			Robot robot = new Robot();
			
	//		robot.delay(5000);
			
			
	// 		System.out.println("Imprime algo");
			
			//	---------------------  Ha habido que añadir esto para que siga funcionando. Al abrir el explorador de archivos...
			//	Otro Aceptar

			robot.delay(100);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
			
			System.out.println("Pulsa aceptar");
			
			if(!InicioIanus.sos){
				
				//	Acepta
				
				robot.mouseMove(acX, acY); 
				robot.mousePress(InputEvent.BUTTON1_MASK);
				robot.mouseRelease(InputEvent.BUTTON1_MASK);
				
				System.out.println("Acepta otra vez");
				
			//	Acepta 2 automatico si está en modo s. c.
				if(InicioIanus.turbo){
					robot.delay(200);
					robot.keyPress(KeyEvent.VK_ENTER);
					robot.keyRelease(KeyEvent.VK_ENTER);
					
					System.out.println("Ultimo aceptar");
				}
				
			//	Aceptar manual
				if(InicioIanus.numeroPantallas == 2)
					robot.mouseMove(aceptarManualX, aceptarManualY);
				
				// this.actualizaListaPdfsAbiertos();
			}
			else{
				System.out.println("Abortado");
			}
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void robotIanus(String cadena, int retardo, int pantallas, int tipoDocumento, String titulo,boolean asociarConBarraEspaciadora, boolean repetir){


		
		InicioIanus.sos = false;
		
		try{
			
			if(repetir){
				InicioIanus.ventanaE.pulsarListaPdfs();
			}						
			
			Robot robot = new Robot();
			
			int longitud = cadena.length();			
			char letras[] = new char[longitud];
			
			String rutaDocAnulado = RUTADOCANULADO;

			File fichero = new File(RUTADOCANULADO);
			
			if(!fichero.exists()){
				rutaDocAnulado = RUTADOCANULADOB;
			}

			
			Robot ro = new Robot();
			ro.delay(InicioIanus.retardoPegarTitulo);
			
			Dimension d = Toolkit.getDefaultToolkit().getScreenSize();

			tamañoCarpeta = VentanaExplorador.listaPdfs.getModel().getSize();
			
			//////****************** Nueva Asignación **********************///
			//	TipoDocumento
			//  1	Ingresos/Urg/Rx
			//	2	Consultas
			//	3	CIA
			//	4	Quirofano Ingre
			//	5   Anulacion
			
			cadenaAimprimir = cadena;
			
			/**********  Modulo forzar subida alternativa a 'alt' ********/
			
			if(InicioIanus.documentacion == 0 || InicioIanus.documentacion == 1){
				
				if(Inicio.botonCoordIngresos.getBackground() == Color.yellow){
					tipoDocumento = 1;
				}else if(Inicio.botonCoordConsulta.getBackground() == Color.yellow){
					tipoDocumento = 2;
				}else if(Inicio.botonCoordCIA.getBackground() == Color.yellow){
					tipoDocumento = 3;
				}else if(Inicio.botonCoordQuirof.getBackground() == Color.yellow){
					tipoDocumento = 4;
				}else{
					if(Inicio.botonCoordIngresos.getBackground() == Color.green){
						tipoDocumento = 1;
					}else if(Inicio.botonCoordConsulta.getBackground() == Color.green){
						tipoDocumento = 2;
					}else if(Inicio.botonCoordCIA.getBackground() == Color.green){
						tipoDocumento = 3;
					}else if(Inicio.botonCoordQuirof.getBackground() == Color.green){
						tipoDocumento = 4;
					}
					
					Inicio.limpiarBotonesCoordenadas();
					
					if(InicioIanus.servicioCombo.toLowerCase().contains("HOSP".toLowerCase()) || 
							InicioIanus.servicioCombo.toLowerCase().contains("URG".toLowerCase())){
						Inicio.botonCoordIngresos.setBackground(Color.green);
			       
			        }else if(InicioIanus.servicioCombo.toLowerCase().contains("CIA".toLowerCase())){
			        	Inicio.botonCoordCIA.setBackground(Color.green);
			        //	System.out.println(comboElegido);
			        }else{
			        	Inicio.botonCoordConsulta.setBackground(Color.green);
			        //	System.out.println("Consulta" + comboElegido);
			        }
				}
				
			}
			
			
			int comodin;
			if(tipoDocumento == 1){
				comodin = 0;
			}else if(tipoDocumento == 2){
				comodin = 1;
			}else if(tipoDocumento == 3){
				comodin = 2;
			}else if(tipoDocumento == 4){
				comodin = 3;
			}else{
				comodin = 4;
			}

			
			if(!InicioIanus.documentos[InicioIanus.pdfSeleccionado].servicio.equals(InicioIanus.documentos[InicioIanus.pdfSeleccionado-1].servicio)){
				InicioIanus.coordenadasQuirofanoOn = false;
				System.out.println("Anulamos coordenadas quirofano por cambio de servicio");
			}
			
			if(InicioIanus.coordenadasQuirofanoOn){
				comodin = 3;
				tipoDocumento = 4;
				System.out.println("Imprimimos quirofano. En robot.");
			}
			
			InicioIanus.lado = setCoordenadas(comodin);
			
			if(InicioIanus.numeroIanus == 1 || InicioIanus.documentacion == 0 || InicioIanus.documentacion == 3){
				System.out.println(InicioIanus.documentacion);
				if(InicioIanus.documentacion == 0 || InicioIanus.documentacion == 3){
					System.out.println("Estamos subiendo urgencias");
				}
				if(InicioIanus.asociarAutomatico || asociarConBarraEspaciadora)
					pulsarBotonAsociar(tipoDocumento);
				
			}
			
			
			//	Gestión de los títulos
			
			if(repetir){
				robot.mouseMove(x1, y1);
				//	System.out.println(x1 + ", " + y1 + " Pega nombre normalizado");
					robot.delay(75);
					robot.mousePress(InputEvent.BUTTON1_MASK);
					robot.mouseRelease(InputEvent.BUTTON1_MASK);
					robot.delay(100);
			}
			
			if(/*(InicioIanus.documentacion == 2 || InicioIanus.documentacion == 3) && */
					(InicioIanus.conjuntoTitulos.contains(cadena) || cadena.contains("Doc. anulado"))){
				// System.out.println("Debería imprimir el título");
				
				for(int f=0;f<InicioIanus.aliasTitulos.size();f++){
					if(InicioIanus.aliasTitulos.get(f).titulo.equals(cadena)){
						titulo = InicioIanus.aliasTitulos.get(f).alias;
						break;
					}
					else{
						titulo = cadena;
					}
				}

				
				// Clicka en el nombre secundario
				
				robot.mouseMove(x1, y1);
				//	System.out.println(x1 + ", " + y1 + " Pega nombre normalizado");
					robot.delay(75);
					robot.mousePress(InputEvent.BUTTON1_MASK);
					robot.mouseRelease(InputEvent.BUTTON1_MASK);
					robot.delay(100);
				
				// Escribe el título
									
				if(titulo.length() != 0){
					// System.out.println("Llegamos a este bucle");
					robot.mouseMove(x1, y1-27);
					
					robot.delay(100);
					robot.mousePress(InputEvent.BUTTON1_MASK);
					robot.mouseRelease(InputEvent.BUTTON1_MASK);
					robot.delay(100);
										
					
					if(!cadena.contains("Documento anulado")){
						for(int k = 0; k< titulo.length();k++){
							getChar(titulo.charAt(k));
							robot.delay(10);
							// System.out.println(titulo.charAt(k));
						}
					}
					else{
						robot.delay(100);
						getChar('a');
						robot.delay(500);
						robot.keyPress(KeyEvent.VK_UP);
						robot.keyRelease(KeyEvent.VK_UP);
						robot.delay(75);
						// robot.mouseMove(x1,y1-13);
						System.out.println("anulando");
					}
					
		

					robot.delay(200);
					robot.keyPress(KeyEvent.VK_ENTER);
					robot.keyRelease(KeyEvent.VK_ENTER);
					robot.delay(100);
					
					/*
					if(cadena.contains("s")&& !cadena.contains("Doc. anulado")){
						robot.mouseMove(x1, y1-27);
						
						robot.delay(200);
						robot.mousePress(InputEvent.BUTTON1_MASK);
						robot.mouseRelease(InputEvent.BUTTON1_MASK);
						robot.delay(100);
						
						for(int k = 0; k< titulo.length() && k < 7;k++){
							getChar(titulo.charAt(k));
							robot.delay(10);
							// System.out.println(titulo.charAt(k));
						}
						robot.delay(200);
						robot.keyPress(KeyEvent.VK_ENTER);
						robot.keyRelease(KeyEvent.VK_ENTER);
						robot.delay(100);
					}
					else{
						robot.delay(100);
						robot.mouseMove(x1,y1-13);
						System.out.println("anulando");
					}
					*/
				}
				
				
				
			}
			
		//	System.out.println("02 InicioIanus.sos vale " + InicioIanus.sos);
			

			//	Pega nombre normalizado		
			if(true){

				if(repetir && !InicioIanus.conjuntoTitulos.contains(cadena)){
					/*
					robot.mouseMove(x1, y1);	
					robot.delay(75);
					robot.mousePress(InputEvent.BUTTON1_MASK);
					robot.mouseRelease(InputEvent.BUTTON1_MASK);
					
					robot.delay(100);
					*/
					robot.mouseMove(x1, y1-27);	
					robot.delay(75);
					robot.mousePress(InputEvent.BUTTON1_MASK);
					robot.mouseRelease(InputEvent.BUTTON1_MASK);
					
						robot.delay(100);
						getChar('a');
						robot.delay(500);
						robot.keyPress(KeyEvent.VK_UP);
						robot.keyRelease(KeyEvent.VK_UP);
						robot.delay(75);
				}
			
				robot.mouseMove(x1, y1);
				//	System.out.println(x1 + ", " + y1 + " Pega nombre normalizado");
				robot.delay(75);
			
			
				robot.mousePress(InputEvent.BUTTON1_MASK);
				robot.mouseRelease(InputEvent.BUTTON1_MASK);
				if(cadena.toLowerCase().contains(InicioIanus.DOC_ANULADO.toLowerCase()) || repetir){
					robot.delay(100);
					robot.mousePress(InputEvent.BUTTON1_MASK);
					robot.mouseRelease(InputEvent.BUTTON1_MASK);
					robot.delay(100);
					robot.mousePress(InputEvent.BUTTON1_MASK);
					robot.mouseRelease(InputEvent.BUTTON1_MASK);
					robot.delay(75);
					robot.keyPress(KeyEvent.VK_CLEAR);
				}

				//	Actualiza el boton de ultimo subido
				InicioIanus.jBultimoNombreSubido.setText(cadena);
				
				robot.delay(50);
				
				CopEnPortapapeles copiarNombre = new CopEnPortapapeles();
				copiarNombre.copiarAlPortapapeles(cadena);
				
				robot.keyPress(KeyEvent.VK_CONTROL);
				robot.keyPress(KeyEvent.VK_V);
				robot.keyRelease(KeyEvent.VK_V);
				robot.keyRelease(KeyEvent.VK_CONTROL);
				
				robot.delay(250);
	
				
			//	System.out.println("03 InicioIanus.sos vale " + InicioIanus.sos);
				
				//	Imprime nombre archivo
				robot.mouseMove(rX, rY);
				robot.mousePress(InputEvent.BUTTON1_MASK);
				robot.mouseRelease(InputEvent.BUTTON1_MASK);	
				
				robot.delay(50);
				
				this.determinaPdfASubir();
			
				CopEnPortapapeles copiar = new CopEnPortapapeles();
			


				if(cadena.toLowerCase().contains(InicioIanus.DOC_ANULADO.toLowerCase())){
					copiar.copiarAlPortapapeles(rutaDocAnulado);
					File ficheroAux = new File(rutaDocAnulado);
					InicioIanus.estadisticaSantiago.add(new DatoEstadisticoSantiago(InicioIanus.servicioCombo,cadena,ficheroAux.length()/1024));
				}else{
					copiar.copiarAlPortapapeles(InicioIanus.tandaDePdfs[pdfASubir-1].getAbsolutePath().toString());
					// System.out.println(InicioIanus.tandaDePdfs[pdfASubir-1].getAbsolutePath().toString());
					InicioIanus.estadisticaSantiago.add(new DatoEstadisticoSantiago(InicioIanus.servicioCombo,cadena,InicioIanus.tandaDePdfs[pdfASubir-1].length()/1024));
				}
				
				
			//	System.out.println("04 InicioIanus.sos vale " + InicioIanus.sos);
				
				robot.delay(InicioIanus.retardoPulsarExaminar);		
				robot.keyPress(KeyEvent.VK_CONTROL);
				robot.keyPress(KeyEvent.VK_V);
				robot.keyRelease(KeyEvent.VK_V);
				robot.keyRelease(KeyEvent.VK_CONTROL);
				
				//start();
					
					
					//	---------------------  Ha habido que añadir esto para que siga funcionando. Al abrir el explorador de archivos...
					//	Otro Aceptar
	
					robot.delay(100);
					robot.keyPress(KeyEvent.VK_ENTER);
					robot.keyRelease(KeyEvent.VK_ENTER);
					
			
					
					/*
					if(InicioIanus.documentos[0].nhc.equals("Separador")){
						if(InicioIanus.pdfSeleccionado == 2 || InicioIanus.pdfSeleccionado == 3)
							robot.delay(2200);
						else
							robot.delay(InicioIanus.retardoAceptar);	
					}else{
						if(InicioIanus.pdfSeleccionado == 1 || InicioIanus.pdfSeleccionado == 2)
							robot.delay(2200);
						else
							robot.delay(InicioIanus.retardoAceptar);
					}
					 */
					
			//		System.out.println("05 InicioIanus.sos vale " + InicioIanus.sos);
					System.out.println("Empieza la condición");
					if(!InicioIanus.sos){
			//			System.out.println("Entró en la condición");
			//			System.out.println("06 InicioIanus.sos vale " + InicioIanus.sos);
						//	Acepta
						
						robot.mouseMove(acX, acY); 
						robot.mousePress(InputEvent.BUTTON1_MASK);
						robot.mouseRelease(InputEvent.BUTTON1_MASK);
						
					//	Acepta 2 automatico si está en modo s. c.
						if(InicioIanus.turbo){
							robot.delay(200);
							robot.keyPress(KeyEvent.VK_ENTER);
							robot.keyRelease(KeyEvent.VK_ENTER);
						}
						
					//	Aceptar manual
						if(InicioIanus.numeroPantallas == 2)
							robot.mouseMove(aceptarManualX, aceptarManualY);
						
						this.actualizaListaPdfsAbiertos();
					}
					else{
				//		System.out.println("-------------No entró en la condición");
				//		System.out.println("10 InicioIanus.sos vale " + InicioIanus.sos);
						System.out.println("Abortado");
					}
				
		
					this.actualizaListaPdfsAbiertos();
			}
				
			}catch (AWTException e){
				e.printStackTrace();
			}
	}
	
	private char setCoordenadas(int comodin){
		char lado;
		
		if (InicioIanus.numeroPantallas == 1){
			x1 = InicioIanus.coordenadasIanus[0][comodin];
			y1 = InicioIanus.coordenadasIanus[1][comodin];
			rX = InicioIanus.coordenadasIanus[2][comodin];
			rY = InicioIanus.coordenadasIanus[3][comodin];
			acX = InicioIanus.coordenadasIanus[4][comodin];
			acY = InicioIanus.coordenadasIanus[5][comodin];
			lado ='d';
		}
		else{
			if ((InicioIanus.numPdfsAbiertos == 2 && InicioIanus.ianus2pantallas)){
				//	Asociar en el ianus de la izquierda
				x1 = InicioIanus.coordenadasIanus[6][comodin];
				y1 = InicioIanus.coordenadasIanus[7][comodin];
				rX = InicioIanus.coordenadasIanus[8][comodin];
				rY = InicioIanus.coordenadasIanus[9][comodin];
				acX = InicioIanus.coordenadasIanus[10][comodin];
				acY = InicioIanus.coordenadasIanus[11][comodin];
				lado = 'i';
			}else
				{
				//	Asociar en el ianus de la derecha
				x1 = InicioIanus.coordenadasIanus[0][comodin];
				y1 = InicioIanus.coordenadasIanus[1][comodin];
				rX = InicioIanus.coordenadasIanus[2][comodin];
				rY = InicioIanus.coordenadasIanus[3][comodin];
				acX = InicioIanus.coordenadasIanus[4][comodin];
				acY = InicioIanus.coordenadasIanus[5][comodin];
				lado = 'd';
			}

		}
		return lado;
	}
	
	
	private void determinaPdfASubir(){
		if(InicioIanus.numPdfsAbiertos == 2){
					pdfASubir = 1;
		}
		else if (InicioIanus.numeroIanus == 2 && VentanaExplorador.numArchivo < tamañoCarpeta - 1 ){
			pdfASubir = 2;
		}
		else{
			pdfASubir = 1;
		}
		InicioIanus.numPdfsAbiertos--;
	}
	
	
	private void actualizaListaPdfsAbiertos(){
		if (InicioIanus.numPdfsAbiertos > 0){
			if (pdfASubir == 1){
				InicioIanus.tandaDePdfs[0] = InicioIanus.tandaDePdfs[1];
				if(InicioIanus.pdfsAlternados){
					Inicio.navegador2.webBrowser.setVisible(false);
					Inicio.navegador2.webBrowserPanel.setBackground(new Color(255,222,173));
				}
				else{
					Inicio.navegador1.webBrowser.setVisible(false);
					Inicio.navegador1.webBrowserPanel.setBackground(new Color(255,222,173));
				}
				
				if(InicioIanus.numeroIanus == 2){
					if(InicioIanus.pdfsAlternados){
						Inicio.navegador1.webBrowserPanel.setBackground(new Color(80,200,120));
					}
					else{
						Inicio.navegador2.webBrowserPanel.setBackground(new Color(80,200,120));
					}
				}
					
			}
		}
		else if (InicioIanus.numPdfsAbiertos == 0){
			Inicio.navegador1.webBrowser.setVisible(false);
			if (InicioIanus.numeroIanus == 2){
				Inicio.navegador2.webBrowser.setVisible(false);
				Inicio.navegador2.webBrowserPanel.setBackground(new Color(255,222,173));
			}
			if (InicioIanus.pdfsAlternados) InicioIanus.pdfsAlternados = false;
		}
	}
	
	
	public void imprimeChar(boolean inverso,int codigo, boolean acento){
		try{
			Robot robot = new Robot();

			if (inverso){
				robot.keyPress(KeyEvent.VK_SHIFT);
				robot.keyPress(codigo);
				robot.keyRelease(codigo);
				robot.keyRelease(KeyEvent.VK_SHIFT);
			}
			else if(acento){
				robot.keyPress(KeyEvent.VK_DEAD_ACUTE);
				robot.keyPress(codigo);
			}
			else{
				
				switch (codigo) {
				case KeyEvent.VK_SPACE:
					imprimeAscii(32);
					break;
				case KeyEvent.VK_A:
					imprimeAscii(97);
					break;
				case KeyEvent.VK_S:
					imprimeAscii(115);
					break;
				case KeyEvent.VK_D:
					imprimeAscii(100);
					break;
				case KeyEvent.VK_F:
					imprimeAscii(102);
					break;
				case KeyEvent.VK_Z:
					imprimeAscii(122);
					break;
				case KeyEvent.VK_X:
					imprimeAscii(120);
					break;
				case KeyEvent.VK_C:
					imprimeAscii(99);
					break;
				case KeyEvent.VK_V:
					imprimeAscii(118);
					break;

				default:
					robot.keyPress(codigo);
					robot.keyRelease(codigo);
					break;
				}
							}
					
		}catch (AWTException e){
			e.printStackTrace();
		}
	}
	
	private void ctrlW(){
		try{
			Robot robot = new Robot();
			robot.mousePress(InputEvent.BUTTON1_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_MASK);			
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_W);
			robot.keyRelease(KeyEvent.VK_W);
			robot.keyRelease(KeyEvent.VK_CONTROL);
		}catch (AWTException e){
			e.printStackTrace();
		}
	}
	
	private void cerrarPdfs(){
		try{
			Robot robot = new Robot();
			robot.mousePress(InputEvent.BUTTON1_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_MASK);
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_W);
			robot.keyRelease(KeyEvent.VK_W);
			robot.keyRelease(KeyEvent.VK_CONTROL);
		}catch (AWTException e){
			e.printStackTrace();
		}
	}
	
	private void pulsarBotonAsociar(int tipoDocumento){
		try{
			Robot robot = new Robot();
			
			Point punto = new Point();
			
			if (tipoDocumento == 2){   //	Consulta
				punto.x = 1780;
				
				if((InicioIanus.documentacion == 2 || InicioIanus.documentacion == 3)){
					if(InicioIanus.servicioCombo.contains("ALG") && 
							( cadenaAimprimir.contains(InicioIanus.PRUEBAS_DIAGNOSTICAS) || 
							  cadenaAimprimir.contains(InicioIanus.PRICK)				|| 
							  cadenaAimprimir.contains(InicioIanus.ESPIROMETRIA)		||
							  cadenaAimprimir.contains(InicioIanus.PRUEBAS_EPICUTANEAS)	||
							  cadenaAimprimir.contains(InicioIanus.PRUEBAS_PROVOCACION)			
							  )){
						if(!InicioIanus.nodoForzado){
							punto.x = 1646;
						}

					}
				}
				
				punto.y = 1169;
			}
			else if(tipoDocumento == 3 || tipoDocumento == 4 ){  //  Cia o quirofano
				punto.x = 1529;
				punto.y = 1169;
			}
			else{
				punto.x = 1620;
				punto.y = 1169;
			}
			
			robot.delay(50);
			for(int i=0;i<100;i++){
				robot.mouseMove(punto.x, punto.y); 
			}
			robot.mousePress(InputEvent.BUTTON1_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_MASK);
			
			robot.delay(InicioIanus.retardoAsociarAceptar);
			if(tipoDocumento == 1){		//  Ingreso, urg
				robot.keyPress(KeyEvent.VK_ENTER);
				robot.keyRelease(KeyEvent.VK_ENTER);					
			}
			
			System.out.println("Retardo para esperar a que abra la ventana");
			robot.delay(retardoAbrirVentanaPropiaAsociar);
		}catch (AWTException e){
			e.printStackTrace();
		}
	}
	
	
	
	public void getChar(char c){
		int codigo=0;
		boolean inverso = false;
		boolean acento = false;
		
		switch (c){
		case 'a': codigo = KeyEvent.VK_A;break;
		case 'b': codigo = KeyEvent.VK_B;break;
        case 'c': codigo = KeyEvent.VK_C; break;
        case 'd': codigo = KeyEvent.VK_D; break;
        case 'e': codigo = KeyEvent.VK_E; break;
        case 'f': codigo = KeyEvent.VK_F; break;
        case 'g': codigo = KeyEvent.VK_G; break;
        case 'h': codigo = KeyEvent.VK_H; break;
        case 'i': codigo = KeyEvent.VK_I; break;
        case 'j': codigo = KeyEvent.VK_J; break;
        case 'k': codigo = KeyEvent.VK_K; break;
        case 'l': codigo = KeyEvent.VK_L; break;
        case 'm': codigo = KeyEvent.VK_M; break;
        case 'n': codigo = KeyEvent.VK_N; break;
        case 'o': codigo = KeyEvent.VK_O; break;
        case 'p': codigo = KeyEvent.VK_P; break;
        case 'q': codigo = KeyEvent.VK_Q; break;
        case 'r': codigo = KeyEvent.VK_R; break;
        case 's': codigo = KeyEvent.VK_S; break;
        case 't': codigo = KeyEvent.VK_T; break;
        case 'u': codigo = KeyEvent.VK_U; break;
        case 'v': codigo = KeyEvent.VK_V; break;
        case 'w': codigo = KeyEvent.VK_W; break;
        case 'x': codigo = KeyEvent.VK_X; break;
        case 'y': codigo = KeyEvent.VK_Y; break;
        case 'z': codigo = KeyEvent.VK_Z; break;
        case ' ': codigo = KeyEvent.VK_SPACE;break;
        case '-': codigo = KeyEvent.VK_MINUS;break;
        case '.': codigo = 46;break;
//        case '\\': codigo = KeyEvent.VK_ALT_GRAPH; break;
        case '_': inverso = true; codigo = KeyEvent.VK_MINUS;break;
        case ':': inverso = true; codigo = 46;break;       
        case 'A': inverso = true;codigo = KeyEvent.VK_A; break;
        case 'B': inverso = true; codigo = KeyEvent.VK_B; break;
        case 'C': inverso = true; codigo = KeyEvent.VK_C; break;
        case 'D': inverso = true; codigo = KeyEvent.VK_D; break;
        case 'E': inverso = true; codigo = KeyEvent.VK_E; break;
        case 'F': inverso = true; codigo = KeyEvent.VK_F; break;
        case 'G': inverso = true; codigo = KeyEvent.VK_G; break;
        case 'H': inverso = true; codigo = KeyEvent.VK_H; break;
        case 'I': inverso = true; codigo = KeyEvent.VK_I; break;
        case 'J': inverso = true; codigo = KeyEvent.VK_J; break;
        case 'K': inverso = true; codigo = KeyEvent.VK_K; break;
        case 'L': inverso = true; codigo = KeyEvent.VK_L; break;
        case 'M': inverso = true; codigo = KeyEvent.VK_M; break;
        case 'N': inverso = true; codigo = KeyEvent.VK_N; break;
        case 'O': inverso = true; codigo = KeyEvent.VK_O; break;
        case 'P': inverso = true; codigo = KeyEvent.VK_P; break;
        case 'Q': inverso = true; codigo = KeyEvent.VK_Q; break;
        case 'R': inverso = true; codigo = KeyEvent.VK_R; break;
        case 'S': inverso = true; codigo = KeyEvent.VK_S; break;
        case 'T': inverso = true; codigo = KeyEvent.VK_T; break;
        case 'U': inverso = true; codigo = KeyEvent.VK_U; break;
        case 'V': inverso = true; codigo = KeyEvent.VK_V; break;
        case 'W': inverso = true; codigo = KeyEvent.VK_W; break;
        case 'X': inverso = true; codigo = KeyEvent.VK_X; break;
        case 'Y': inverso = true; codigo = KeyEvent.VK_Y; break;
        case 'Z': inverso = true; codigo = KeyEvent.VK_Z; break;
        case 'á': acento = true; codigo = KeyEvent.VK_A; break;
        case 'é': acento = true; codigo = KeyEvent.VK_E; break;
        case 'í': acento = true; codigo = KeyEvent.VK_I; break;
        case 'ó': acento = true; codigo = KeyEvent.VK_O; break;
        case 'ú': acento = true; codigo = KeyEvent.VK_U; break;
        
 
        case '0': codigo = KeyEvent.VK_0; break;
        case '1': codigo = KeyEvent.VK_1; break;
        case '2': codigo = KeyEvent.VK_2; break;
        case '3': codigo = KeyEvent.VK_3; break;
        case '4': codigo = KeyEvent.VK_4; break;
        case '5': codigo = KeyEvent.VK_5; break;
        case '6': codigo = KeyEvent.VK_6; break;
        case '7': codigo = KeyEvent.VK_7; break;
        case '8': codigo = KeyEvent.VK_8; break;
        case '9': codigo = KeyEvent.VK_9; break;		
		
		
		
		
		
		default:
			codigo = KeyEvent.VK_C;
		}
		
		this.imprimeChar(inverso,codigo, acento);
		
	}
	
	public static void imprimeAscii(int codigoAscii){
		String digitos = String.valueOf(codigoAscii);
		char dig[] = digitos.toCharArray();
		
		try {
			Robot robot = new Robot();
			
			robot.keyPress(KeyEvent.VK_ALT);
			for(int i=0;i<dig.length;i++){
				int teclaNumerica = 0;
				switch (dig[i]) {
				case '0':
					teclaNumerica = KeyEvent.VK_NUMPAD0;
					break;
				case '1':
					teclaNumerica = KeyEvent.VK_NUMPAD1;
					break;
				case '2':
					teclaNumerica = KeyEvent.VK_NUMPAD2;
					break;
				case '3':
					teclaNumerica = KeyEvent.VK_NUMPAD3;
					break;
				case '4':
					teclaNumerica = KeyEvent.VK_NUMPAD4;
					break;
				case '5':
					teclaNumerica = KeyEvent.VK_NUMPAD5;
					break;
				case '6':
					teclaNumerica = KeyEvent.VK_NUMPAD6;
					break;
				case '7':
					teclaNumerica = KeyEvent.VK_NUMPAD7;
					break;
				case '8':
					teclaNumerica = KeyEvent.VK_NUMPAD8;
					break;
				case '9':
					teclaNumerica = KeyEvent.VK_NUMPAD9;
					break;
				}
				robot.keyPress(teclaNumerica);
				robot.keyRelease(teclaNumerica);
			}
			
			robot.keyRelease(KeyEvent.VK_ALT);
			robot.delay(20);
			
			
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
