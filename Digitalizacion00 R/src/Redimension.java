import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;


public class Redimension {

	/**
	 * @param args
	 */
	
	BufferedImage imagenFuente;
	Image imagen250x350, imagen600x840;
	
	String rutaOrigen = "";
	String rutaDestinoA = "";
	String rutaDestinoB = "";
	
	int alto, ancho;
	
	Calendar calendario;
	Redimension(String nombreFichero){

		File firmados = new File(InicioIanus.RUTA);
		if(!firmados.exists()){
			firmados = new File(InicioIanus.RUTAB);
			}
		rutaOrigen = firmados.getAbsolutePath().toString();
		System.out.println(rutaOrigen);
		int aux = rutaOrigen.lastIndexOf("\\");
		rutaOrigen = rutaOrigen.substring(0,aux);
		rutaDestinoA = rutaOrigen + "\\99 Nombres Normalizados\\Imagenes\\250x350\\";
		rutaDestinoB = rutaOrigen + "\\99 Nombres Normalizados\\Imagenes\\600x800\\";
		rutaOrigen += "\\05 Documentos Nuevos\\00 Imagenes\\";
		System.out.println(rutaOrigen);

		try{
			imagenFuente = ImageIO.read(new File(rutaOrigen + nombreFichero));
			
			//	Truco para conocer el tamaño de la imagen...
			ImageIcon imgIcon = new ImageIcon(rutaOrigen + nombreFichero);
			ancho = imgIcon.getIconWidth();
			alto = imgIcon.getIconHeight();
			if(alto >= ancho){
				imagen250x350 = imagenFuente.getScaledInstance(250, 350, Image.SCALE_AREA_AVERAGING);
				imagen600x840 = imagenFuente.getScaledInstance(600, 840, Image.SCALE_AREA_AVERAGING);
			}else{
				imagen250x350 = imagenFuente.getScaledInstance(350, 250, Image.SCALE_AREA_AVERAGING);
				imagen600x840 = imagenFuente.getScaledInstance(840, 600, Image.SCALE_AREA_AVERAGING);
			}
			System.out.println("Ancho: " + ancho + " Alto: " + alto);

			String nombreDocumento = this.getFechaHora(nombreFichero);
			ImageIO.write(getBufferedImage(imagen250x350), "jpg", new File(rutaDestinoA + nombreDocumento));
			ImageIO.write(getBufferedImage(imagen600x840), "jpg", new File(rutaDestinoB + nombreDocumento));
	
			InicioIanus.auxRutaImagen = nombreDocumento;
			
		}catch(IOException e){
			System.out.println("Error de escritura");
		}

	}
	
	String getFechaHora(String nombreFichero){
		
		int indice = nombreFichero.lastIndexOf(".");
		nombreFichero = nombreFichero.substring(0, indice);
		
		calendario = calendario.getInstance();
        
        String dia = Integer.toString(calendario.get(Calendar.DATE));
        String mes = Integer.toString(calendario.get(Calendar.MONTH));
        String año = Integer.toString(calendario.get(Calendar.YEAR));
        String hora = Integer.toString(calendario.get(Calendar.HOUR_OF_DAY));
        String minutos = Integer.toString(calendario.get(Calendar.MINUTE));
        String segundos = Integer.toString(calendario.get(Calendar.SECOND));

        String fechaNorma = nombreFichero + " " + año+ mes+ dia + " " + hora + minutos + segundos + ".jpg";
        System.out.println(fechaNorma);
		return fechaNorma;
	}
	
	static public BufferedImage getBufferedImage(Image imagenAConvertir){
		BufferedImage bi = new BufferedImage(imagenAConvertir.getWidth(null),imagenAConvertir.getHeight(null),BufferedImage.TYPE_INT_RGB);
		Graphics bg = bi.getGraphics();
		bg.drawImage(imagenAConvertir, 0, 0, null);
		bg.dispose();
		return bi;
	}
	/*
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Redimension("arbol.jpg");
	}
*/
}
