  

import java.awt.BorderLayout;   
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import chrriis.dj.nativeswing.swtimpl.components.JWebBrowser;   
  
/**  
 * @author Christopher Deckers  
 */  


public class VisualizadorAvisos{   
	  
	 JWebBrowser webBrowser;
	 JPanel webBrowserPanel = new JPanel(new BorderLayout());
		
	 JPanel panel;
	
	 VisualizadorAvisos(String titulo){
		 panel = new JPanel(new BorderLayout());   
		    
		    webBrowserPanel.setBorder(BorderFactory.createTitledBorder(titulo));   
		    webBrowser = new JWebBrowser();
		    //webBrowser.navigate("http://www.google.es");
		    //webBrowser.navigate("http://ianuschop");  
		    webBrowser.setBarsVisible(false);
		    webBrowser.setMenuBarVisible(false);
		    //webBrowser.setHTMLContent();   

		    //System.out.println(webBrowser.getBrowserType());
		   // webBrowser.setJavascriptEnabled(true);
 
		    
		    webBrowserPanel.add(webBrowser, BorderLayout.CENTER);   
		    panel.add(webBrowserPanel, BorderLayout.CENTER);  
	 }
	 
	 public void createContent(String titulo) {  
		  
		     
	  }   
  
}  