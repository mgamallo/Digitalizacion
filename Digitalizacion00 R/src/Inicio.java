import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import chrriis.dj.nativeswing.swtimpl.NativeInterface;
import chrriis.dj.nativeswing.swtimpl.components.JWebBrowser;


public class Inicio {

	/**
	 * @param args
	 */
	
	static WebBrowserIanus navegador1;
	static WebBrowserIanus navegador2;
	
	
	static boolean avisochapuza = true;  // Para que aparezca el boton temporizador solo en navegador1
	
	static CoordenadasVentana coordenadasVentanas = new CoordenadasVentana();
	
	/******* Para gestionar las coordenadas de las subidas ****************************/
	
	 static public JButton botonCoordIngresos = new JButton("Ingresos");
	 static public JButton botonCoordConsulta = new JButton("Consultas");
	 static public JButton botonCoordCIA = new JButton("CIA");
	 static public JButton botonCoordQuirof = new JButton("Quirófano");
	 static public JPanel coordenadasPanel = new JPanel();
	
	/**********************************************************************************/
		
	


	public static void inicializacionBotonesCoordenadas(){
	    Inicio.botonCoordIngresos.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
				// System.out.println("Hola" + Inicio.botonCoordIngresos.getBackground());
				
				if(Inicio.botonCoordIngresos.getBackground() != Color.yellow ){
					limpiarBotonesCoordenadas();
					Inicio.botonCoordIngresos.setBackground(Color.yellow);
				}
				else if(Inicio.botonCoordIngresos.getBackground() == Color.yellow){
					Inicio.botonCoordIngresos.setBackground(Color.red);
					reasignarBotones();
				}
				
			}
		});
	    
	    Inicio.botonCoordConsulta.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
				// System.out.println("Hola" + Inicio.botonCoordIngresos.getBackground());
				
				if(Inicio.botonCoordConsulta.getBackground() != Color.yellow ){
					limpiarBotonesCoordenadas();
					Inicio.botonCoordConsulta.setBackground(Color.yellow);
				}
				else if(Inicio.botonCoordConsulta.getBackground() == Color.yellow){
					Inicio.botonCoordConsulta.setBackground(Color.red);
					reasignarBotones();
				}
				
			}
		});
	    
	    Inicio.botonCoordCIA.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
				// System.out.println("Hola" + Inicio.botonCoordIngresos.getBackground());
				
				if(Inicio.botonCoordCIA.getBackground() != Color.yellow ){
					limpiarBotonesCoordenadas();
					Inicio.botonCoordCIA.setBackground(Color.yellow);
				}
				else if(Inicio.botonCoordCIA.getBackground() == Color.yellow){
					Inicio.botonCoordCIA.setBackground(Color.red);
					reasignarBotones();
				}
				
			}
		});
	    
	    Inicio.botonCoordQuirof.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
				// System.out.println("Hola" + Inicio.botonCoordIngresos.getBackground());
				
				if(Inicio.botonCoordQuirof.getBackground() != Color.yellow ){
					limpiarBotonesCoordenadas();
					Inicio.botonCoordQuirof.setBackground(Color.yellow);
				}
				else if(Inicio.botonCoordQuirof.getBackground() == Color.yellow){
					Inicio.botonCoordQuirof.setBackground(Color.red);
					reasignarBotones();
				}
				
				
			}
		});
	}
	 
	
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		NativeInterface.open(); 
		
		inicializacionBotonesCoordenadas();
				
			navegador1 = new WebBrowserIanus("Digitalización 2.0.0", new Color(255,222,173), true);
			avisochapuza = false;
			navegador2 = new WebBrowserIanus("Visor número 2", new Color(255,246,143), false);

	        new InicioIanus();
		NativeInterface.runEventPump(); 

	}

	static public void limpiarBotonesCoordenadas(int tipoCoordenada){
		Inicio.botonCoordIngresos.setBackground(Color.red);
	    Inicio.botonCoordConsulta.setBackground(Color.red);
	    Inicio.botonCoordCIA.setBackground(Color.red);
	    Inicio.botonCoordQuirof.setBackground(Color.red);
	    
	    switch (tipoCoordenada) {
		case 1:
			Inicio.botonCoordIngresos.setBackground(Color.green);
			break;
		case 2:
			Inicio.botonCoordConsulta.setBackground(Color.green);
			break;
		case 3:
			Inicio.botonCoordCIA.setBackground(Color.green);
			break;
		case 4:
			Inicio.botonCoordQuirof.setBackground(Color.green);
			break;
		default:
			break;
		}
	}
	
	static public void limpiarBotonesCoordenadasAmarillo(int tipoCoordenada){
		Inicio.botonCoordIngresos.setBackground(Color.red);
	    Inicio.botonCoordConsulta.setBackground(Color.red);
	    Inicio.botonCoordCIA.setBackground(Color.red);
	    Inicio.botonCoordQuirof.setBackground(Color.red);
	    
	    switch (tipoCoordenada) {
		case 1:
			Inicio.botonCoordIngresos.setBackground(Color.yellow);
			break;
		case 2:
			Inicio.botonCoordConsulta.setBackground(Color.yellow);
			break;
		case 3:
			Inicio.botonCoordCIA.setBackground(Color.yellow);
			break;
		case 4:
			Inicio.botonCoordQuirof.setBackground(Color.yellow);
			break;
		default:
			break;
		}
	}
	
	static public void limpiarBotonesCoordenadas(){
		Inicio.botonCoordIngresos.setBackground(Color.red);
	    Inicio.botonCoordConsulta.setBackground(Color.red);
	    Inicio.botonCoordCIA.setBackground(Color.red);
	    Inicio.botonCoordQuirof.setBackground(Color.red);
	}
	
	static public void reasignarBotones(){
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
	
	static public void ocultarBotonesCoordenadas(){
		Inicio.botonCoordIngresos.setVisible(false);
	    Inicio.botonCoordConsulta.setVisible(false);
	    Inicio.botonCoordCIA.setVisible(false);
	    Inicio.botonCoordQuirof.setVisible(false);
	}
}
