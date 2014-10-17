import java.awt.AWTException;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Robot;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;




public class DocumentosVigo {

	/**
	 * @param args
	 */
/*	public static void main(String[] args) {
		// TODO Auto-generated method stub

		DocumentosVigo dV = new DocumentosVigo();
		dV.subirVigo("hola");
	}
*/
	
	Point nhc = new Point(1347,656);
	Point centro = new Point(1478,625);
	Point justificacion = new Point(1455,625);
	Point paciente = new Point(1095,131);
	Point centroPantalla = new Point(502,653);
	
	
	Object centroVigo;
	char password[] = new char[4];
	
	String numeroChop="";
	
/*	JPanel panelContraseña = new JPanel();
	JLabel contraseñaJL = new JLabel("Introduce la contraseña");
	JPasswordField campoContraseña = new JPasswordField();
	*/
	DocumentosVigo(boolean primeraVez){
		if(primeraVez){
		
			centroVigo = JOptionPane.showInputDialog(null, "Seleccione el centro","Selector de centro",JOptionPane.QUESTION_MESSAGE,null,new Object[]{
					"Universitario de Vigo","Povisa"},"Povisa");
			VentanaExplorador.triggerVigo = true;
		}
	/*	panelContraseña.setLayout(new GridLayout(2,2));
		panelContraseña.add(contraseñaJL);
		panelContraseña.add(campoContraseña);

		
		int input = JOptionPane.showConfirmDialog(null, panelContraseña,"Contraseña", JOptionPane.OK_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE);
		password = campoContraseña.getPassword();
		
		*/
	}
	
	public void subirVigo(String rutaArchivo){

		int es = rutaArchivo.lastIndexOf(".");
		rutaArchivo = rutaArchivo.substring(0, es);
		String numHistVigo = JOptionPane.showInputDialog("Si no es correcto, \n" + "escribe el número de historia de Vigo.",rutaArchivo);
		char array[] = numHistVigo.toCharArray(); 
		
		try {
			Robot imprimeVigo = new Robot();
			
			imprimeVigo.mouseMove(nhc.x,nhc.y);							//	Imprimimos el numero de historia
			imprimeVigo.mousePress(InputEvent.BUTTON1_MASK);
			imprimeVigo.mouseRelease(InputEvent.BUTTON1_MASK);
			
			for(int j=0;j<array.length;j++){
				this.imprimeChar(false,array[j], false);
			}
			imprimeVigo.delay(100);
			
			imprimeVigo.mouseMove(centro.x,centro.y);					//	Seleccionamos el centro
			imprimeVigo.mousePress(InputEvent.BUTTON1_MASK);
			imprimeVigo.mouseRelease(InputEvent.BUTTON1_MASK);
						
			int aux = 6;
			if(centroVigo.toString().contains("Povisa")){
				aux = 15;
			}

			for(int i=0;i<aux;i++){
				imprimeVigo.keyPress(KeyEvent.VK_DOWN);
				imprimeVigo.delay(80);
			}
			imprimeVigo.keyRelease(KeyEvent.VK_DOWN);
			
			imprimeVigo.keyPress(KeyEvent.VK_ENTER);						//	Aceptar
			imprimeVigo.keyRelease(KeyEvent.VK_ENTER);
			
			imprimeVigo.delay(1500);
			imprimeVigo.mouseMove(centroPantalla.x,centroPantalla.y);
			int seleccion = JOptionPane.showOptionDialog
					(null,"Esperar a que el ianus esté cargado. ¿Solicita justificación?","Espera...",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,
							new Object[] {"Si","No"},"Si");
			
			imprimeVigo.mouseMove(justificacion.x,justificacion.y);					//	Focalizamos en Ianus
			imprimeVigo.mousePress(InputEvent.BUTTON1_MASK);
			imprimeVigo.mouseRelease(InputEvent.BUTTON1_MASK);
			
			//	Justificacion
			if(JOptionPane.OK_OPTION == seleccion){
				for(int i=0;i<4;i++){
					imprimeVigo.keyPress(KeyEvent.VK_DOWN);
					imprimeVigo.delay(100);
				}
				imprimeVigo.keyPress(KeyEvent.VK_ENTER);						//	Aceptar
				imprimeVigo.keyRelease(KeyEvent.VK_ENTER);
		
				imprimeVigo.mouseMove(centroPantalla.x,centroPantalla.y);	
				JOptionPane.showMessageDialog(null, "Continuar cuando el ianus esté cargado");
				imprimeVigo.delay(20);
			}
	
			numeroChop = copiaNumeroHistoria();

			if(numeroChop.isEmpty()){
				System.out.println("volver a copiar");
				numeroChop = copiaNumeroHistoria();
			}
			
			if(!numeroChop.equals("No")){
				System.out.println("No se que pasa en este punto");

				imprimeVigo.mouseMove(centroPantalla.x,centroPantalla.y);	
				int capturado = JOptionPane.showConfirmDialog(null, "¿Es correcto el número de Historia:  " + numeroChop, "hola",JOptionPane.YES_NO_OPTION);

				if (capturado == JOptionPane.OK_OPTION){
					System.out.println(InicioIanus.rutaCompletaPdfs[VentanaExplorador.numArchivo]); //	Ruta del archivo original
					int auxNombre = InicioIanus.rutaCompletaPdfs[VentanaExplorador.numArchivo].lastIndexOf("\\");
					
					String cadenaNombre = InicioIanus.rutaCompletaPdfs[VentanaExplorador.numArchivo].substring(auxNombre +1, InicioIanus.rutaCompletaPdfs[VentanaExplorador.numArchivo].length());
					System.out.println("Nombre del archivo: " + cadenaNombre);
					String cadenaEspejo = InicioIanus.rutaCompletaPdfs[VentanaExplorador.numArchivo].substring(0, auxNombre);
					cadenaEspejo = cadenaEspejo + "\\Espejo\\";
		
					File ficheroOriginal = new File(cadenaEspejo + cadenaNombre);
					File ficheroEspejo = new File(cadenaEspejo + numeroChop + " - " + cadenaNombre);
					
					boolean correcto = ficheroOriginal.renameTo(ficheroEspejo);
					if(correcto){
						System.out.println("Fichero renombrado");
						InicioIanus.rutaCompletaPdfs[VentanaExplorador.numArchivo] = ficheroEspejo.getAbsolutePath();
						System.out.println(InicioIanus.rutaCompletaPdfs[VentanaExplorador.numArchivo]);
					}else{
						System.out.println("Fichero no renombrado");
					}
					
					// JOptionPane.showMessageDialog(null, cadenaEspejo + numeroChop + " " + cadenaNombre);
				
				}

			
			}else{
				imprimeVigo.mouseMove(centroPantalla.x,centroPantalla.y);		
				JOptionPane.showMessageDialog(null, "Pulsa Aceptar");
				System.out.println("No entiendo nada");
			}
			
			
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}	
	
	
	public void subirVigoError(String rutaArchivo){


		Point paciente = new Point(1095,131);
		
 
		
		try {
			Robot imprimeVigo = new Robot();
			
			imprimeVigo.mouseMove(paciente.x,paciente.y);							//	Imprimimos el numero de historia
			imprimeVigo.mousePress(InputEvent.BUTTON1_MASK);
			imprimeVigo.mouseRelease(InputEvent.BUTTON1_MASK);
	
	
			numeroChop = copiaNumeroHistoria();

			System.out.println("el contenido es.. " + numeroChop);
			
			if(numeroChop.isEmpty()){
				System.out.println("volver a copiar");
				numeroChop = copiaNumeroHistoria();
			}
			if(!numeroChop.equals("No")){
				imprimeVigo.mouseMove(centroPantalla.x,centroPantalla.y);		
				int capturado = JOptionPane.showConfirmDialog(null, "¿Es correcto el número de Historia:  " + numeroChop, "hola",JOptionPane.YES_NO_OPTION);
				if (capturado == JOptionPane.OK_OPTION){
					System.out.println(InicioIanus.rutaCompletaPdfs[VentanaExplorador.numArchivo]); //	Ruta del archivo original
					int auxNombre = InicioIanus.rutaCompletaPdfs[VentanaExplorador.numArchivo].lastIndexOf("\\");
					
					String cadenaNombre = InicioIanus.rutaCompletaPdfs[VentanaExplorador.numArchivo].substring(auxNombre +1, InicioIanus.rutaCompletaPdfs[VentanaExplorador.numArchivo].length());
					System.out.println("Nombre del archivo: " + cadenaNombre);
					String cadenaEspejo = InicioIanus.rutaCompletaPdfs[VentanaExplorador.numArchivo].substring(0, auxNombre);
					cadenaEspejo = cadenaEspejo + "\\Espejo\\";
		
					File ficheroOriginal = new File(cadenaEspejo + cadenaNombre);
					File ficheroEspejo = new File(cadenaEspejo + numeroChop + " - " + cadenaNombre);
					
					boolean correcto = ficheroOriginal.renameTo(ficheroEspejo);
					if(correcto){
						System.out.println("Fichero renombrado");
						InicioIanus.rutaCompletaPdfs[VentanaExplorador.numArchivo] = ficheroEspejo.getAbsolutePath();
						System.out.println(InicioIanus.rutaCompletaPdfs[VentanaExplorador.numArchivo]);
					}else{
						System.out.println("Fichero no renombrado");
					}
					
					// JOptionPane.showMessageDialog(null, cadenaEspejo + numeroChop + " " + cadenaNombre);
				}
			}
			
			
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}	
	
	
	
	
	public String copiaNumeroHistoria(){
		
		Point paciente = new Point(1095,131);
		
		Robot imprimeVigo;
		try {
			imprimeVigo = new Robot();
			
			CopEnPortapapeles copiarNHC1 = new CopEnPortapapeles();
			//	Seleccionar paciente
			imprimeVigo.mouseMove(paciente.x, paciente.y);
			for(int i=0;i<3;i++){
				imprimeVigo.delay(100);
				imprimeVigo.mousePress(InputEvent.BUTTON1_MASK);
				imprimeVigo.mouseRelease(InputEvent.BUTTON1_MASK);
			}
			
			
			imprimeVigo.keyPress(KeyEvent.VK_CONTROL);
			imprimeVigo.keyPress(KeyEvent.VK_C);
			imprimeVigo.delay(400);
			imprimeVigo.keyRelease(KeyEvent.VK_C);
			imprimeVigo.keyRelease(KeyEvent.VK_CONTROL);
			imprimeVigo.delay(200);
			
			return copiarNHC1.cogerDelPortapapeles();
			
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "No";

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
				}
						
			}catch (AWTException e){
				e.printStackTrace();
			}
		}	
		
		
		
		public void getChar(char c){
			int codigo=0;
			boolean inverso = false;
			boolean acento = false;
			
			switch (c){
	
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
		
	}
	
	
}
