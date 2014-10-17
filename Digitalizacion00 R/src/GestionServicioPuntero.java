import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;


public class GestionServicioPuntero {
	public boolean gestion(){
		
		switch (InicioIanus.tipoSubida) {
		case 2:
		//case 3:

			if(InicioIanus.documentos[InicioIanus.pdfSeleccionado].servicio.equals(InicioIanus.documentos[InicioIanus.pdfSeleccionado-1].servicio)){
				try {
					Robot robot = new Robot();
					robot.mouseMove(InicioIanus.ClickImportante.x, InicioIanus.ClickImportante.y);
					System.out.println(InicioIanus.ClickImportante);
					robot.delay(100);
					robot.mousePress(InputEvent.BUTTON1_MASK);
					robot.mouseRelease(InputEvent.BUTTON1_MASK);
					robot.delay(InicioIanus.retardoAsociar);
					return true;
				} catch (AWTException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else{
				InicioIanus.coordenadasQuirofanoOn = false;
			}
			
			break;
		/*
		case 3:
			return true;
		*/
		
		}
		
		return false;
		
	}
}
