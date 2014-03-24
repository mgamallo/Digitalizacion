import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.io.File;

import jxl.Sheet;
import jxl.Workbook;


public class PreferenciasUsuario {

	Point[] coordenadas;
	boolean grabadas;
	int numIanus;
	int numPantallas;
	
	PreferenciasUsuario(){
		coordenadas = new Point[4];
		numIanus = InicioIanus.numeroIanus;
		numPantallas = getNumPantallas();

		setCoordenadas(InicioIanus.excel.getPreferencias(InicioIanus.usuario, numPantallas, numIanus));
		grabadas = InicioIanus.excel.getGrabado();
	}
	
	int getNumPantallas(){
		
		//	Determinar número de pantallas
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice[] gs = ge.getScreenDevices();
		
		return gs.length;
	}
	
	void setCoordenadas(Point[] coord){
		coordenadas[0] = coord[0];
		coordenadas[1] = coord[1];
		coordenadas[2] = coord[2];
		coordenadas[3] = coord[3];
	}
	
	void leerCoordenadas(){
    	LeerExcel excel = new LeerExcel();
        excel.leerExcel("Documentos.xls");

	}
	
}

