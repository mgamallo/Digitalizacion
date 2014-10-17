import java.awt.AWTException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;


import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfReader;


public class PropiedadesPDF {
	
	Boolean horizontal = false;
	
	String formato = "A4";
	char orientacion = 'v';
	
	Dimension dimensionesPdf = new Dimension();
	int tamañoHoja = 0;     // -1 menor que un A4; 1 mayor que un A4
	int numeroPaginas = 0;
	
	PropiedadesPDF(String ruta){
		getPropiedades(ruta);
	}
	
	void getPropiedades(String ruta){
		
		try {
			PdfReader pdf = new PdfReader(ruta);
			
			numeroPaginas = pdf.getNumberOfPages();
			Rectangle pagina = pdf.getPageSize(1);
			
			getTamañoHoja(pagina);
			if(horizontal){
				orientacion = 'h';
			}
			else{
				orientacion = 'v';
			}
			
			if(tamañoHoja == 1){
				formato = "A3";
			}
			else if(tamañoHoja == -1){
				formato = "A5";
			}
			else{
				formato = "A4";
			}
			
			pdf.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	void getTamañoHoja(Rectangle tamañoPagina){
		
		dimensionesPdf.height = (int)tamañoPagina.getHeight();
		dimensionesPdf.width = (int) tamañoPagina.getWidth();
		
		System.out.println("alto: " + dimensionesPdf.height + " ancho: " + dimensionesPdf.width);
		
		if(dimensionesPdf.height >= dimensionesPdf.width){
			horizontal = false;
		}else{
			horizontal = true;
		}
		
		if(dimensionesPdf.height> 1000 || dimensionesPdf.width > 1000 ){
			tamañoHoja = 1;
		}else if(dimensionesPdf.height< 700 && dimensionesPdf.width <700){
			tamañoHoja = -1;
		}else{
			tamañoHoja = 0;
		}
	}
	
	
}

class RecolocaPdf{
	
	void setA3(String rutaArchivo, int numDeLaVentana, int retardo){
		PropiedadesPDF prop = new PropiedadesPDF(rutaArchivo);
		
		if(prop.formato.contains("A3")){
			try {
				Robot robot = new Robot();
				
				Point p;
				
				robot.delay(retardo);
				if (numDeLaVentana == 2){
					p = Inicio.navegador2.webBrowserPanel.getLocationOnScreen();
					Point raton = MouseInfo.getPointerInfo().getLocation();
					
					System.out.println(p.x + ", " + p.y);
					
					//robot.delay(3000);
					
					robot.mouseMove(p.x+100, p.y + 200);
					robot.mousePress(InputEvent.BUTTON1_MASK);
					robot.mouseRelease(InputEvent.BUTTON1_MASK);
					robot.delay(100);
					
					robot.mouseMove(raton.x, raton.y);
					
				}
				else{
					p = Inicio.navegador1.webBrowserPanel.getLocationOnScreen();
					Point raton = MouseInfo.getPointerInfo().getLocation();
					
					System.out.println(p.x + ", " + p.y);
					
					robot.delay(200);
					robot.mouseMove(p.x+100, p.y + 200);
					robot.mousePress(InputEvent.BUTTON1_MASK);
					robot.mouseRelease(InputEvent.BUTTON1_MASK);
					robot.delay(100);
					robot.mouseMove(raton.x, raton.y);
				}
				
				p.x += 100;
				p.y += 200;
				
				robot.delay(400);
				System.out.println("Empieza la recolocacion");
				for(int i=0;i<4;i++){
					robot.mouseMove(p.x, p.y);
					robot.keyPress(KeyEvent.VK_CONTROL);
					robot.keyPress(KeyEvent.VK_0);
					robot.keyRelease(KeyEvent.VK_0);
					robot.keyRelease(KeyEvent.VK_CONTROL);
					robot.delay(5);
				}
				
				for(int i=0;i<70;i++){
					robot.mouseMove(p.x, p.y);
					robot.keyPress(KeyEvent.VK_RIGHT);
					robot.keyRelease(KeyEvent.VK_RIGHT);
					robot.delay(5);
				}
				
				
				
				System.out.println("Termina la recolocacion");
				
			} catch (AWTException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
}
