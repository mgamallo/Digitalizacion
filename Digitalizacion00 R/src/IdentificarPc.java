import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class IdentificarPc {

	
	String getIdentificacion(String ruta){
		File f = new File(ruta);
		Scanner s;
		String pc = "NoN";
		try{
			s = new Scanner(f);
			if (s.hasNextLine()){
				 pc = s.nextLine();
				System.out.println(pc);
			}
			s.close();
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}
		
		return pc;
	}
}	

