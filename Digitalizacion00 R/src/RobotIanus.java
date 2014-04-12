

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

public class RobotIanus {
	
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
	

	
	//	Constructor
	public void robotIanus(String cadena, int retardo, int pantallas, int tipoDocumento, String titulo,boolean asociarConBarraEspaciadora){


		
		try{
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
			//	4	Anulacion
			
			cadenaAimprimir = cadena;
			
			int comodin;
			if(tipoDocumento == 1){
				comodin = 0;
			}else if(tipoDocumento == 2){
				comodin = 1;
			}else if(tipoDocumento == 3){
				comodin = 2;
			}else
				comodin = 3;
			
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
			
			InicioIanus.suspensionTeclado = true;
			
			if(InicioIanus.conjuntoTitulos.contains(cadena) || cadena.contains("Doc. anulado")){
				// System.out.println("Debería imprimir el título");
				
				titulo = cadena;
				
				if(titulo.length() != 0){
					// System.out.println("Llegamos a este bucle");
					robot.mouseMove(x1, y1-27);
					
					robot.delay(100);
					robot.mousePress(InputEvent.BUTTON1_MASK);
					robot.mouseRelease(InputEvent.BUTTON1_MASK);
					robot.delay(100);
					
					if(!cadena.contains("Doc. anulado")){
						for(int k = 0; k< titulo.length() && k < 5;k++){
							getChar(titulo.charAt(k));
							robot.delay(70);
							// System.out.println(titulo.charAt(k));
						}
					}
					else{
						robot.delay(100);
						robot.mouseMove(x1,y1-13);
						System.out.println("anulando");
					}
					

					robot.delay(200);
					robot.keyPress(KeyEvent.VK_ENTER);
					robot.keyRelease(KeyEvent.VK_ENTER);
					robot.delay(100);
				}
				
				
				
			}
			
			InicioIanus.suspensionTeclado = false;
			

			//	Pega nombre normalizado				
			robot.mouseMove(x1, y1);
		//	System.out.println(x1 + ", " + y1 + " Pega nombre normalizado");
			robot.delay(75);
			robot.mousePress(InputEvent.BUTTON1_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_MASK);
			if(cadena.toLowerCase().contains("Doc. Anulado".toLowerCase())){
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

			
			//	Imprime nombre archivo
			robot.mouseMove(rX, rY);
			robot.mousePress(InputEvent.BUTTON1_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_MASK);	
			
			robot.delay(50);
			
			this.determinaPdfASubir();
			
			CopEnPortapapeles copiar = new CopEnPortapapeles();

			if(cadena.toLowerCase().contains("Doc. Anulado".toLowerCase())){
				copiar.copiarAlPortapapeles(rutaDocAnulado);
				File ficheroAux = new File(rutaDocAnulado);
				InicioIanus.estadisticaSantiago.add(new DatoEstadisticoSantiago(InicioIanus.servicioCombo,cadena,ficheroAux.length()/1024));
			}else{
				copiar.copiarAlPortapapeles(InicioIanus.tandaDePdfs[pdfASubir-1].getAbsolutePath().toString());
				// System.out.println(InicioIanus.tandaDePdfs[pdfASubir-1].getAbsolutePath().toString());
				InicioIanus.estadisticaSantiago.add(new DatoEstadisticoSantiago(InicioIanus.servicioCombo,cadena,InicioIanus.tandaDePdfs[pdfASubir-1].length()/1024));
			}
			
			this.actualizaListaPdfsAbiertos();
			
				robot.delay(InicioIanus.retardoPulsarExaminar);		
				robot.keyPress(KeyEvent.VK_CONTROL);
				robot.keyPress(KeyEvent.VK_V);
				robot.keyRelease(KeyEvent.VK_V);
				robot.keyRelease(KeyEvent.VK_CONTROL);
				
				
				//	---------------------  Ha habido que añadir esto para que siga funcionando. Al abrir el explorador de archivos...
				//	Otro Aceptar

				robot.delay(100);
				robot.keyPress(KeyEvent.VK_ENTER);
				robot.keyRelease(KeyEvent.VK_ENTER);
				robot.delay(100);
	
				
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
				robot.keyPress(codigo);
				robot.keyRelease(codigo);
				// Duda... hace falta el keyrelease??
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
							(cadenaAimprimir.contains("Pruebas diagnósticas") || cadenaAimprimir.contains("Espirometría"))){
						if(!InicioIanus.nodoForzado){
							punto.x = 1646;
						}

					}
				}
				
				punto.y = 1169;
			}
			else if(tipoDocumento == 3){  //  Cia
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
			
			robot.delay(200);
			if(tipoDocumento == 1){		//  Ingreso, urg
				robot.keyPress(KeyEvent.VK_ENTER);
				robot.keyRelease(KeyEvent.VK_ENTER);					
			}
			
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
	
}
