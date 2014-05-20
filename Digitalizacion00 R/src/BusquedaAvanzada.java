import java.awt.AWTException;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.InputEvent;


public class BusquedaAvanzada {
	
	Point paciente = new Point(2010,103);
	Point busquedaAvanzada = new Point(1582,951);
		
	public BusquedaAvanzada() {
		// TODO Auto-generated constructor stub
		
		try {
			Robot robot = new Robot();
			
			//	Sale del paciente actual
			robot.mouseMove(paciente.x, paciente.y);
			robot.delay(100);
			robot.mousePress(InputEvent.BUTTON1_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_MASK);
			robot.delay(100);
			
			//	Deshabilita teclas
			InicioIanus.ventanaBotonesTeclas.cerrarAutoHotKey(false);
			
			//	Pulsa busqueda avanzada
			robot.mouseMove(busquedaAvanzada.x, busquedaAvanzada.y);
			robot.delay(100);
			robot.mousePress(InputEvent.BUTTON1_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_MASK);
			robot.delay(100);
			
			
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
