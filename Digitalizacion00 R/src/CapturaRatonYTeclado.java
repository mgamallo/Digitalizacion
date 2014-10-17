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
	public void nativeKeyPressed(NativeKeyEvent e) {
		// TODO Auto-generated method stub
		// System.out.println("NativeKeyPressed " + e.getKeyCode());
		if(e.getKeyCode()== 27){
			InicioIanus.sos = true;
			System.out.println("Pulsado escape");
			System.out.println(InicioIanus.sos);
		}
		
	//	System.out.println("Tecla ... " + e.getKeyCode());
		
		if(InicioIanus.documentacion == 1 || InicioIanus.documentacion == 0){
			switch (e.getKeyCode()) {
			case 112:
				System.out.println("Pulsado f1");
				Inicio.limpiarBotonesCoordenadasAmarillo(1);
				break;
			case 113:
				System.out.println("Pulsado f2");
				Inicio.limpiarBotonesCoordenadasAmarillo(2);
				break;
			case 114:
				System.out.println("Pulsado f3");
				Inicio.limpiarBotonesCoordenadasAmarillo(3);
				break;
			case 115:
				System.out.println("Pulsado f4");
				Inicio.limpiarBotonesCoordenadasAmarillo(4);
				break;
				
			case 81:  // q
				System.out.println("Pulsado f1");
				Inicio.limpiarBotonesCoordenadas(1);
				break;
			case 87:  // w
				System.out.println("Pulsado f2");
				Inicio.limpiarBotonesCoordenadas(2);
				break;
			case 69:  // e
				System.out.println("Pulsado f3");
				Inicio.limpiarBotonesCoordenadas(3);
				break;
			case 82:  // r
				System.out.println("Pulsado f4");
				Inicio.limpiarBotonesCoordenadas(4);
				break;
			}
		}

		


	}

	@Override
	public void nativeKeyReleased(NativeKeyEvent arg0) {
		// TODO Auto-generated method stub
		//System.out.println("NativeKeyReleased " + arg0.getKeyCode());
	}

	@Override
	public void nativeKeyTyped(NativeKeyEvent arg0) {
		// TODO Auto-generated method stub
		//System.out.println("NativeKeyTyped " + arg0.getKeyCode());
	}

}
