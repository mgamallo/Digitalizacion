import java.awt.Color;

import javax.swing.JOptionPane;

import chrriis.dj.nativeswing.swtimpl.NativeInterface;
import chrriis.dj.nativeswing.swtimpl.components.JWebBrowser;


public class Inicio {

	/**
	 * @param args
	 */
	
	static WebBrowserIanus navegador1;
	static WebBrowserIanus navegador2;
	
	static CoordenadasVentana coordenadasVentanas = new CoordenadasVentana();
		
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	
		NativeInterface.open(); 
				
			navegador1 = new WebBrowserIanus("Digitalización 2.0.0", new Color(255,222,173), true);
			navegador2 = new WebBrowserIanus("Visor número 2", new Color(255,246,143), false);

	        new InicioIanus();
		NativeInterface.runEventPump(); 

	}

}
