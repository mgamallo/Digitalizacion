import java.awt.Color;
import java.io.IOException;

import chrriis.dj.nativeswing.swtimpl.NativeInterface;


public class CerrarTodo {
	
/*	public static void main(String[] args){
		CerrarTodo cl = new CerrarTodo();
		cl.close();
	}
*/	
	public void close(){

		 String cmd = "taskkill.exe /F /IM AcroRd32.exe /T";
		 String cmd2 = "taskkill.exe /F /IM iexplore.exe /T";
		 String cmd3 = "taskkill.exe /F /IM TeclasRapidas.exe /T";
		 String cmd4 = "taskkill.exe /F /IM TeclasExperimental.exe /T";
		 String cmd5 = "taskkill.exe /F /IM ianus.exe /T";

		 Process hijo, hijo2, hijo3, hijo4, hijo5;
		 try {
				hijo = Runtime.getRuntime().exec(cmd);
				hijo2 = Runtime.getRuntime().exec(cmd2);
				hijo3 = Runtime.getRuntime().exec(cmd3);
				hijo4 = Runtime.getRuntime().exec(cmd4);
				hijo5 = Runtime.getRuntime().exec(cmd5);
				
				
				hijo.waitFor();
				hijo2.waitFor();
				hijo3.waitFor();
				hijo4.waitFor();
				hijo5.waitFor();
				
				Thread.sleep(500);
								
				
				if ( hijo.exitValue()== 0 && hijo2.exitValue()== 0){
					System.out.println("acrobat matado con exito");
				}else{
					System.out.println("Incapaz de matar acrobat. Exit code: " + hijo.exitValue()+"n");
				}
		 } catch (IOException e) {
			System.out.println("Incapaz de matar.");
		 } catch (InterruptedException e) {
			System.out.println("Incapaz de matar.");
		 }
		
	}
	
	public void closePdf(){
		 String cmd = "taskkill.exe /F /IM AcroRd32.exe /T";

		 Process hijo, hijo2;
		 try {
				hijo = Runtime.getRuntime().exec(cmd);
									
				hijo.waitFor();
				
				Thread.sleep(500);
						
				if ( hijo.exitValue()== 0 ){
					System.out.println("acrobat matado con exito");
				}else{
					System.out.println("Incapaz de matar acrobat. Exit code: " + hijo.exitValue()+"n");
				}
		 } catch (IOException e) {
			System.out.println("Incapaz de matar.");
		 } catch (InterruptedException e) {
			System.out.println("Incapaz de matar.");
		 }
	}
}
