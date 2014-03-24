import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;
import org.jnativehook.mouse.NativeMouseEvent;
import org.jnativehook.mouse.NativeMouseInputListener;
import org.jnativehook.mouse.NativeMouseListener;


public class CapturaRatonYTeclado implements NativeKeyListener, NativeMouseInputListener{

	public CapturaRatonYTeclado() {
		// TODO Auto-generated constructor stub
		
		/*try {
			GlobalScreen.registerNativeHook();
		} catch (NativeHookException e) {
			// TODO Auto-generated catch block
			System.err.println("There was a problem registering the native hook.");
            System.err.println(e.getMessage());
			//e.printStackTrace();
		}
		*/
	}
	
	
	//	Métodos de ratón **********************************************************
	
	@Override
	public void nativeMouseClicked(NativeMouseEvent e) {
		// TODO Auto-generated method stub
		
		// System.out.println("Capturamos ratón");
		
		if(InicioIanus.coordenadaNWExploradorIanus.x < e.getX() && InicioIanus.coordenadaSEExploradorIanus.x > e.getX()
				&& InicioIanus.coordenadaNWExploradorIanus.y < e.getY() && InicioIanus.coordenadaSEExploradorIanus.y > e.getY() ){
			
			System.out.println("Dentro de la ventana del explorador de ianus");
			//InicioIanus.penultimoClick = InicioIanus.ultimoClick;
			InicioIanus.ultimoClick = e.getPoint();
			
				/*
				if(// InicioIanus.subidaExtrema // &&  InicioIanus.tipoSubida == 3 ){
				//	if(InicioIanus.documentos[InicioIanus.pdfSeleccionado].servicio.equals(InicioIanus.documentos[InicioIanus.pdfSeleccionado-1].servicio)){
				//		
							
						try {
							InicioIanus.ClickImportante = InicioIanus.ultimoClick;
							
							Robot robot = new Robot();
							robot.delay(1100);
							System.out.println("Pulsamos v");
							robot.keyPress(KeyEvent.VK_V);
							robot.keyRelease(KeyEvent.VK_V);
							robot.delay(100);
						} catch (AWTException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
	
						
	
						InicioIanus.subidaExtrema = true;
				//	}
				}
				
				*/
		}
		
	
		// System.out.println("El último pinchazo es en.. " + InicioIanus.ultimoClick);
	}

	@Override
	public void nativeMousePressed(NativeMouseEvent arg0) {
		// TODO Auto-generated method stub
		// System.out.println("mousepressed");
	}

	@Override
	public void nativeMouseReleased(NativeMouseEvent arg0) {
		// TODO Auto-generated method stub
		// System.out.println("mousereleased");
	}

	@Override
	public void nativeMouseDragged(NativeMouseEvent arg0) {
		// TODO Auto-generated method stub
		// System.out.println("mousedragged");
	}

	@Override
	public void nativeMouseMoved(NativeMouseEvent arg0) {
		// TODO Auto-generated method stub
		// System.out.println("mousemoved");
	}

	
	//	Métodos de teclado *************************************************************
	
	@Override
	public void nativeKeyPressed(NativeKeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void nativeKeyReleased(NativeKeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void nativeKeyTyped(NativeKeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
