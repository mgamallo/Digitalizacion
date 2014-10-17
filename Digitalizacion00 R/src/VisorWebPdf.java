import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

import chrriis.dj.nativeswing.swtimpl.components.JWebBrowser;


public class VisorWebPdf extends JFrame{

	private String ruta;
	
	JWebBrowser webBrowser;

	VisorWebPdf( String nombreMarco, String ruta){
		
		this.ruta = ruta;
		
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(800,600);
		
		JPanel panel = new JPanel(new BorderLayout());
		JPanel webBrowserPanel = new JPanel(new BorderLayout());
		
		webBrowserPanel.setBorder(BorderFactory.createTitledBorder(nombreMarco));
		webBrowser = new JWebBrowser();
		webBrowser.navigate(ruta);
		webBrowser.setButtonBarVisible(false);
		webBrowser.setBarsVisible(false);
		webBrowser.setMenuBarVisible(false);
		webBrowserPanel.add(webBrowser, BorderLayout.CENTER);
		panel.add(webBrowserPanel, BorderLayout.CENTER);
		
		this.getContentPane().add(panel, BorderLayout.CENTER);
		
		
		this.setVisible(true);
	}
	
	public void setPdf(String nuevaRuta){
		this.ruta = nuevaRuta;
		webBrowser.navigate(ruta);
	}
	
}
