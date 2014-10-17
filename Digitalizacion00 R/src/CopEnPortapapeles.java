

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.KeyEvent;
import java.io.IOException;

import javax.swing.JOptionPane;


/**
 *
 * @author Coco
 */
public class CopEnPortapapeles{
   
 public void copiarAlPortapapeles(String documento){
    Clipboard portapapeles=Toolkit.getDefaultToolkit().getSystemClipboard();
    StringSelection texto=new StringSelection(documento);
    portapapeles.setContents(texto, texto);
 }   
 
 public String cogerDelPortapapeles(){
	 Clipboard portapapeles = Toolkit.getDefaultToolkit().getSystemClipboard();
	 Transferable t = portapapeles.getContents(null);
	 DataFlavor dataFlavorString;
	 String cadena ="";
	 String numeroHistoria = "";
	 
	 try {
		 // dataFlavorString = new DataFlavor("application/x-java-serialized-object; class=java.lang.String");
		//  dataFlavorString = new DataFlavor("text/plain; class=java.lang.String; charset=Unicode");
		  dataFlavorString = new DataFlavor("text/html; class=java.lang.String; charset=Unicode");
		  

		DataFlavor[] dfs = t.getTransferDataFlavors();
		for(int i=0;i<dfs.length;i++){
			System.out.println(dfs[i].getMimeType());
		}
		
		   
		//   cadena = (String) t.getTransferData(dataFlavorString);
		   
		if(t.isDataFlavorSupported(dataFlavorString)){
			// JOptionPane.showMessageDialog(null, "Es un dtaflavor ");
			cadena = (String) t.getTransferData(dataFlavorString);
			
			System.out.println(cadena);
			
			// JOptionPane.showMessageDialog(null, "La cadena vale: " + cadena);
			int aux = cadena.lastIndexOf("NHC");
			String subcadena = cadena.substring(aux, cadena.length());

			for(int i=0;i<subcadena.length();i++){
				if(Character.isDigit(subcadena.charAt(i))){
					numeroHistoria += subcadena.charAt(i);
				}
			}
			numeroHistoria = numeroHistoria.substring(0,numeroHistoria.length()-1);
			System.out.println(aux);
			System.out.println(numeroHistoria);
		//	JOptionPane.showMessageDialog(null, "El numero de historia es: " + numeroHistoria);
			InicioIanus.numeroHistoriaNuestro = numeroHistoria;

		}else{
			// JOptionPane.showMessageDialog(null, "Error. No es un dtaflavor ");
			System.out.println("No es un dtaflavor");
			VentanaExplorador.errorData = true;
			return "No";
		}
		
	

	 } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	 } catch (UnsupportedFlavorException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 
	 return numeroHistoria;
 }
 
 
 /*
 static public void main(String[] arg){
	 CopEnPortapapeles copi = new CopEnPortapapeles();
	 JOptionPane.showMessageDialog(null, "Copia la cadena");
	 copi.cogerDelPortapapeles();
 }
 */
}   
    