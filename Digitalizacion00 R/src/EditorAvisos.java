import java.awt.BorderLayout;   
import java.awt.Color;
import java.awt.Dimension;   
import java.awt.FlowLayout;   
import java.awt.event.ActionEvent;   
import java.awt.event.ActionListener;   
  
import javax.swing.BorderFactory;   
import javax.swing.JButton;   
import javax.swing.JComponent;   
import javax.swing.JDialog;
import javax.swing.JFrame;   
import javax.swing.JOptionPane;   
import javax.swing.JPanel;   
import javax.swing.JScrollPane;   
import javax.swing.JTextArea;   
import javax.swing.SwingUtilities;   
  
import chrriis.common.UIUtils;   
import chrriis.dj.nativeswing.swtimpl.NativeInterface;   
import chrriis.dj.nativeswing.swtimpl.components.HTMLEditorAdapter;   
import chrriis.dj.nativeswing.swtimpl.components.HTMLEditorSaveEvent;   
import chrriis.dj.nativeswing.swtimpl.components.JHTMLEditor;   
import chrriis.dj.nativeswing.swtimpl.components.JHTMLEditor.HTMLEditorImplementation;   
  
/**  
 * @author Christopher Deckers  
 */  
public class EditorAvisos {   
  
  protected static final String LS = System.getProperty("line.separator");   
  
  public JDialog dialog;
  JPanel contentPane;
  
  String mensaje;
  
  public EditorAvisos(final String mensaje) {   
	  	
	  	this.mensaje = mensaje;
	  
	    UIUtils.setPreferredLookAndFeel();   
	    SwingUtilities.invokeLater(new Runnable() {   
	      public void run() {   
	    	dialog = new JDialog();   
	        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);   
	        dialog.getContentPane().add(createContent(), BorderLayout.CENTER);   
	        dialog.setSize(800, 600);   
	        dialog.setLocationByPlatform(true); 
	        
	       dialog.setVisible(true); 
	       dialog.setModal(true);
	      }   
	    });   
	  } 
  
  
  public JComponent createContent() {   
	  contentPane = new JPanel(new BorderLayout());   

	  String configurationScript =   
    	      "FCKConfig.ToolbarSets[\"Default\"] = [\n" +   
    	      "['Save','-','NewPage' /* ,'Preview' */],\n" +   
    	      "['Cut','Copy','Paste','-','Print','SpellCheck'],\n" +   
    	      "['Undo','Redo','-','Find','Replace'],\n" +   
    	      "'/',\n" +   
    	      "['Templates','-','Style','FontFormat','FontName','FontSize','-','Smiley','SpecialChar'],\n" +   
    	      "'/',\n" +   
    	      "['Bold','Italic','Underline','StrikeThrough','-','TextColor','BGColor','-','Subscript','Superscript'],\n" +   
    	      "['OrderedList','UnorderedList','-','Outdent','Indent'],\n" +   
    	      "['JustifyLeft','JustifyCenter','JustifyRight','JustifyFull'],\n" +   
    	      "['Image','Flash','Table','Rule','PageBreak'],\n" +   
    	      "];\n" +   
    	      "FCKConfig.ToolbarCanCollapse = false;\n";   

	    final JHTMLEditor htmlEditor = new JHTMLEditor(HTMLEditorImplementation.FCKEditor, JHTMLEditor.FCKEditorOptions.setCustomJavascriptConfiguration(configurationScript));   
	    htmlEditor.setHTMLContent(mensaje);
	    htmlEditor.addHTMLEditorListener(new HTMLEditorAdapter() {   
	      @Override  
	      	public void saveHTML(HTMLEditorSaveEvent e) {   
		        JOptionPane.showMessageDialog(null, "Mensaje guardado"); 
		        VentanaAñadirAvisosII.visual.webBrowser.setHTMLContent(htmlEditor.getHTMLContent());
		        dialog.dispose();
	      	}     
	    });   
	    contentPane.add(htmlEditor, BorderLayout.CENTER);   
    /*
    JPanel southPanel = new JPanel(new BorderLayout());   
    southPanel.setBorder(BorderFactory.createTitledBorder("Custom Controls"));   
    JPanel middlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));   
    JButton setHTMLButton = new JButton("Set HTML");   
    middlePanel.add(setHTMLButton);   
    JButton getHTMLButton = new JButton("Get HTML");   
    middlePanel.add(getHTMLButton);   
    southPanel.add(middlePanel, BorderLayout.NORTH);   
    final JTextArea htmlTextArea = new JTextArea();   
    htmlTextArea.setText(   
        "<p style=\"text-align: center\">This is an <b>HTML editor</b>, in a <u><i>Swing</i></u> application.<br />" + LS +   
        "<img alt=\"DJ Project Logo\" src=\"http://djproject.sourceforge.net/common/logo.png\" /><br />" + LS +   
        "<a href=\"http://djproject.sourceforge.net/ns/\">DJ Project - Native Swing</a></p>"  
    );   
    htmlTextArea.setCaretPosition(0);   
    JScrollPane scrollPane = new JScrollPane(htmlTextArea);   
    scrollPane.setPreferredSize(new Dimension(0, 100));   
    southPanel.add(scrollPane, BorderLayout.CENTER);   
    contentPane.add(southPanel, BorderLayout.SOUTH);   
    getHTMLButton.addActionListener(new ActionListener() {   
      public void actionPerformed(ActionEvent e) {   
        htmlTextArea.setText(htmlEditor.getHTMLContent());   
        htmlTextArea.setCaretPosition(0);   
      }   
    });   
    setHTMLButton.addActionListener(new ActionListener() {   
      public void actionPerformed(ActionEvent e) {   
        htmlEditor.setHTMLContent(htmlTextArea.getText());   
      }   
    });   
    htmlEditor.setHTMLContent(htmlTextArea.getText());  
    
     */
    return contentPane;   
  }   
   
  public String getMensaje(){
	  return mensaje;
  }
}  
