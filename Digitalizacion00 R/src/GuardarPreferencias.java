import java.awt.Dimension;
import java.awt.Point;


public class GuardarPreferencias {
	
	public void guardar(String nombreArchivo){
	Point vE = InicioIanus.ventanaE.getLocationOnScreen();
	Dimension vEdim = InicioIanus.ventanaE.getSize();
	Point vEpoint= new Point(vEdim.width,vEdim.height);
	Point vD = InicioIanus.ventanaD.getLocationOnScreen();
	Dimension vDdim = InicioIanus.ventanaD.getSize();
	Point vDpoint = new Point(vDdim.width,vDdim.height);
	
	Point[] coordTotales = new Point[4];
	coordTotales[0] = new Point(vE);
	coordTotales[1] = new Point(vEpoint);
	coordTotales[2] = new Point(vD);
	coordTotales[3] = new Point(vDpoint);

	//	Guardamos coordenadas en memoria
	InicioIanus.coordenadas.setCoordenadas(coordTotales);
	
	//	Guardamos preferencias en la hoja excel
	EscribirExcel guardarDatos = new EscribirExcel();
	guardarDatos.escribir(nombreArchivo, InicioIanus.usuario, InicioIanus.numeroPantallas, InicioIanus.numeroIanus);
	}
}
