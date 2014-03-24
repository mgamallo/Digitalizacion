import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;
import org.jnativehook.mouse.NativeMouseEvent;
import org.jnativehook.mouse.NativeMouseInputListener;


public class CoordenadasAsociar implements NativeMouseInputListener, NativeKeyListener{

		CoordenadasAsociar(){
			try {
                GlobalScreen.registerNativeHook();
	        }
	        catch (NativeHookException ex) {
	                System.err.println("There was a problem registering the native hook.");
	                System.err.println(ex.getMessage());
	
	              //  System.exit(1);
	        }
		}
		
		
		/********************** Métodos ratón  ********************************/
	
        public void nativeMouseClicked(NativeMouseEvent e) {
                // System.out.println("Mosue Clicked: " + e.getClickCount());
 
               // System.out.println("Posicion x: " + e.getX());
                //System.out.println("Posicion y: " + e.getY());
                InicioIanus.localizacionVentanaD = InicioIanus.ventanaD.getLocationOnScreen();
                InicioIanus.dimensionesVentanaD.x = InicioIanus.ventanaD.getWidth();
                InicioIanus.dimensionesVentanaD.y = InicioIanus.ventanaD.getHeight();
                
                InicioIanus.localizacionVentanaBotonesTeclas = InicioIanus.ventanaBotonesTeclas.getLocationOnScreen();
                InicioIanus.dimensionesVentanaBotonesTeclas.x = InicioIanus.ventanaBotonesTeclas.getWidth();
                InicioIanus.dimensionesVentanaBotonesTeclas.y = InicioIanus.ventanaBotonesTeclas.getHeight();
                
                //	Perfeccionar con métodos el tamaño de la ventanaD. Si el ratón es pulsado dentro de la ventana, no se actualiza el último click 
                //	para conservar la referencia del boton asociar de ianus.
               
                boolean anotarCoordenadas = true;
                
                if((e.getX() > InicioIanus.localizacionVentanaD.x) && (e.getX() < (InicioIanus.localizacionVentanaD.x + InicioIanus.dimensionesVentanaD.x)) 
                		&& (e.getY() > InicioIanus.localizacionVentanaD.y) && (e.getY() < (InicioIanus.localizacionVentanaD.y + InicioIanus.dimensionesVentanaD.y))){
		                	System.out.println("Dentro de la ventana interfaztabla");
		                	anotarCoordenadas = false;
                }
                
                if((e.getX() > InicioIanus.dimensionesVentanaBotonesTeclas.x) && (e.getX() < (InicioIanus.dimensionesVentanaBotonesTeclas.x + 
                		InicioIanus.dimensionesVentanaBotonesTeclas.x)) 
                		&& ((e.getX() > InicioIanus.dimensionesVentanaBotonesTeclas.y) && (e.getY() < (InicioIanus.dimensionesVentanaBotonesTeclas.y + 
                        		InicioIanus.dimensionesVentanaBotonesTeclas.y)))){
                			anotarCoordenadas = false;
                			System.out.println("Dentro de la ventana botones");
                		}
  
                if (anotarCoordenadas){
                  	InicioIanus.coordAsociar.x = e.getX();
                    InicioIanus.coordAsociar.y = e.getY();
                }
                
                
                
                // System.out.println("Posicion tabla x: " + InicioIanus.localizacionVentanaD.x);
                // System.out.println("Posicion tabla y: " + InicioIanus.localizacionVentanaD.y);
                
        }

        public void nativeMousePressed(NativeMouseEvent e) {
              //  System.out.println("Mosue Pressed: " + e.getButton());
        }

        public void nativeMouseReleased(NativeMouseEvent e) {
             //   System.out.println("Mosue Released: " + e.getButton());
        }

        public void nativeMouseMoved(NativeMouseEvent e) {
              //  System.out.println("Mosue Moved: " + e.getX() + ", " + e.getY());
        }

        public void nativeMouseDragged(NativeMouseEvent e) {
               // System.out.println("Mosue Dragged: " + e.getX() + ", " + e.getY());
        }

        
        /************************   Métodos teclado  ***********************************/
        public void nativeKeyPressed(NativeKeyEvent e) {
           /* System.out.println("Key Pressed: " + NativeKeyEvent.getKeyText(e.getKeyCode()));

            if (e.getKeyCode() == NativeKeyEvent.VK_ESCAPE) {
                    GlobalScreen.unregisterNativeHook();
            }  */
	    }
	
	    public void nativeKeyReleased(NativeKeyEvent e) {
	         //   System.out.println("Key Released: " + NativeKeyEvent.getKeyText(e.getKeyCode()));
	    }
	
	    public void nativeKeyTyped(NativeKeyEvent e) {
	         //   System.out.println("Key Typed: " + e.getKeyText(e.getKeyCode()));
	    }

}
