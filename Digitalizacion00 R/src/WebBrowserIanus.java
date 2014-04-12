  
import java.awt.AWTException;
import java.awt.BorderLayout;   
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;   
import java.awt.Font;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;   
import java.awt.event.ItemListener;   
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
  
import javax.swing.BorderFactory;   
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;   
import javax.swing.JComboBox;
import javax.swing.JComponent;   
import javax.swing.JFrame;   
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;   
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.SwingUtilities;   
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.eclipse.swt.widgets.Slider;
  
import chrriis.common.UIUtils;   
import chrriis.dj.nativeswing.swtimpl.NativeInterface;   
import chrriis.dj.nativeswing.swtimpl.components.JWebBrowser;   
  
/**  
 * @author Christopher Deckers  
 */  


public class WebBrowserIanus {   
  
	 public JWebBrowser webBrowser;
	 public JPanel webBrowserPanel = new JPanel(new BorderLayout());
	 
	 public JPanel ocrPanel = new JPanel(new BorderLayout());
	 public JPanel ocrControlPanel = new JPanel();
	 public JPanel ocrBotonesPanel = new JPanel();
	 public JPanel ocrBotonesVelocidad = new JPanel();
		
	 public JFrame frame;
	 
	 
	 public ButtonGroup grupoVelocidadSubida = new ButtonGroup();
	 public JRadioButton radioButtonManual = new JRadioButton("Manual");
	 public JRadioButton radioButtonAuto = new JRadioButton("Auto");
	 public JRadioButton radioButtonRapido = new JRadioButton("Rapido");
	 public JRadioButton radioButtonExtremo = new JRadioButton("Extremo");
	 
	 public JButton botonResetIanus = new JButton("Reset Ianus");
	 
	 private JLabel etiquetaEnBlanco = new JLabel("                  |               ");
	 private JLabel etiquetaEnBlancoII = new JLabel("              |            ");
	 
	 public JButton botonRetardos = new JButton("T");
	 
	 private boolean barraOCRvisible;
	 
	 private JSlider jslider = new JSlider(JSlider.HORIZONTAL, 0, 2000, InicioIanus.retardoAsociar);
	 private JLabel etiquetaRetardo = new JLabel(String.valueOf(InicioIanus.retardoAsociar));
	
	 public JComponent createContent() {  
		  
		    JPanel contentPane = new JPanel(new BorderLayout());   
		    
		    ocrControlPanel.setLayout(new BoxLayout(ocrControlPanel,BoxLayout.Y_AXIS));
		    //ocrControlPanel.setBorder(BorderFactory.createTitledBorder(""));
		    
		    webBrowserPanel.setBorder(BorderFactory.createTitledBorder("Digitalización"));   
		    webBrowser = new JWebBrowser();
		    //webBrowser.navigate("http://www.google.es");
		    //webBrowser.navigate("http://ianuschop");  
		    webBrowser.setBarsVisible(false);
		    webBrowser.setMenuBarVisible(false);
		    //System.out.println(webBrowser.getBrowserType());
		   // webBrowser.setJavascriptEnabled(true);
    
		    
		    webBrowserPanel.add(webBrowser, BorderLayout.CENTER);   
		    contentPane.add(webBrowserPanel, BorderLayout.CENTER); 
		    
		    if(barraOCRvisible){
		    	
		    	InicioIanus.checkPulsarAsociar.setSelected(true);
		    	InicioIanus.checkPulsarAsociar.setText("Auto Asociar");
		    	InicioIanus.checkPulsarAsociar.setToolTipText("Pulsa automáticamente el boton asociar"); 
		    	InicioIanus.checkPulsarAsociar.setBackground(new Color(255,222,173));
		    	InicioIanus.checkPulsarAsociar.addItemListener(new java.awt.event.ItemListener() {
		            public void itemStateChanged(java.awt.event.ItemEvent evt) {
		            	if( InicioIanus.checkPulsarAsociar.isSelected()){
		                    InicioIanus.asociarAutomatico = true;
		                }
		                else{
		                	InicioIanus.asociarAutomatico = false;
		                }
		            }
		        });   
		    	
		    	
		    	InicioIanus.check2IanusOCR.setSelected(false);
		    	InicioIanus.check2IanusOCR.setText("2 Ianus");
		    	InicioIanus.check2IanusOCR.setToolTipText("Gestión automática de 2 Ianus"); 
		    	InicioIanus.check2IanusOCR.setBackground(new Color(255,222,173));
		    	InicioIanus.check2IanusOCR.addItemListener(new java.awt.event.ItemListener() {
		            public void itemStateChanged(java.awt.event.ItemEvent evt) {
		            	if( InicioIanus.check2IanusOCR.isSelected()){
		                    InicioIanus.ocr2IanusAutomatico = true;
		                    InicioIanus.botonInicioSubidaOCR.setEnabled(true);
		                    InicioIanus.botonResetSubidaOCR.setEnabled(true);
		                    botonResetIanus.setEnabled(true);
		                }
		                else{
		                	InicioIanus.ocr2IanusAutomatico = false;
		                	InicioIanus.botonInicioSubidaOCR.setEnabled(false);
		                	InicioIanus.botonResetSubidaOCR.setEnabled(false);
		                }
		            }
		        });   
		    	
		    	/*
		    	InicioIanus.checkTurboOCR.setSelected(true);
		    	InicioIanus.checkTurboOCR.setText("Auto");
		    	InicioIanus.checkTurboOCR.setToolTipText("Introduce automáticamente el nhc"); 
		    	InicioIanus.checkTurboOCR.setBackground(new Color(255,222,173));
		    	InicioIanus.checkTurboOCR.addItemListener(new java.awt.event.ItemListener() {
		            public void itemStateChanged(java.awt.event.ItemEvent evt) {
		            	if( InicioIanus.checkTurboOCR.isSelected()){
		                    InicioIanus.ocrTurbo = true;
		                }
		                else{
		                	InicioIanus.ocrTurbo = false;
		                }
		            }
		        });   
		    	*/
		    	
		    	// ocrControlPanel.add(InicioIanus.checkTurboOCR);
		    	
		    	
		    	// ocrControlPanel.add(comboVelocidad);
	    	
		    	/* ocrControlPanel.add(InicioIanus.check2IanusOCR);
		    	ocrControlPanel.add(InicioIanus.botonInicioSubidaOCR);
		    	ocrControlPanel.add(InicioIanus.botonResetSubidaOCR);  */
		    	ocrControlPanel.setBackground(new Color(255,222,173));
		    	
		    	/*
		    	ocrControlPanel.setMaximumSize(new Dimension(70,70));
		    	ocrControlPanel.setPreferredSize(new Dimension(70,70));
		    	*/
		    	
		    	InicioIanus.botonInicioSubidaOCR.setEnabled(false);
		    	InicioIanus.botonInicioSubidaOCR.addActionListener(new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						if(InicioIanus.documentacion == 2 || InicioIanus.documentacion == 3){
							
							int i = JOptionPane.showConfirmDialog(null, "Tiene que haber 2 ianus abiertos en la \n pantalla de la" +
									" derecha, y maximizados");
							System.out.println(i);
							if(i == 0){
								InicioIanus.gestion2ianus = new Gestion2Ianus();
			        			InicioIanus.gestion2ianus.impresionInicial();
			        			InicioIanus.gestion2ianusIniciada = true;			        			
							}
		        		
		        		}
					}
		    		
		    	});
		    	
		    	InicioIanus.botonResetSubidaOCR.setEnabled(false);
		    	InicioIanus.botonResetSubidaOCR.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						if(InicioIanus.gestion2ianusIniciada){
							InicioIanus.gestion2ianus.reset();
						}
					}
				});
		    	
		    	botonRetardos.setSize(30,30);
		    	botonRetardos.setBackground(Color.cyan);
		    			    	
		    	grupoVelocidadSubida.add(radioButtonManual);
		    	grupoVelocidadSubida.add(radioButtonAuto);
		    	grupoVelocidadSubida.add(radioButtonRapido);
		    	//grupoVelocidadSubida.add(radioButtonExtremo);radioButtonExtremo.setEnabled(false);

		    	ocrBotonesVelocidad.add(botonRetardos);
		    	
		    	ocrBotonesVelocidad.add(InicioIanus.check2IanusOCR);
		    	ocrBotonesVelocidad.add(InicioIanus.botonInicioSubidaOCR);
		    	ocrBotonesVelocidad.add(InicioIanus.botonResetSubidaOCR);
		    	// ocrBotonesVelocidad.add(etiquetaEnBlanco);
		    	// ocrBotonesVelocidad.add(radioButtonManual);
		    	ocrBotonesVelocidad.add(radioButtonAuto);
		    	ocrBotonesVelocidad.add(radioButtonRapido);
		    		//ocrBotonesVelocidad.add(radioButtonExtremo);
		    	//ocrBotonesVelocidad.add(etiquetaEnBlancoII);
		    	// ocrBotonesVelocidad.add(InicioIanus.checkPulsarAsociar);
		    	ocrBotonesVelocidad.setBackground(new Color(255,222,173));
		    	ocrBotonesVelocidad.add(jslider);
		    	ocrBotonesVelocidad.add(etiquetaRetardo);
		    	// ocrBotonesVelocidad.setMaximumSize(new Dimension(400,20));
		    	ocrBotonesVelocidad.add(botonResetIanus);
		    	
		    	RadioListener myListener = new RadioListener();
		    	
		    	radioButtonAuto.setToolTipText("Cuando se pulsa la barra espaciadora, en Ianus, además de salir del paciente, se " +
		    			"introduce el siguiente nhc");
		    	radioButtonAuto.addActionListener(myListener);
		    	radioButtonAuto.setBackground(new Color(255,222,173));
		    	
		    	radioButtonRapido.setToolTipText("Cuando se pulsa la barra espaciadora, si el siguiente pdf es del mismo paciente y del " +
		    			"mismo servicio, sube automaticamente el pdf.");
		    	radioButtonRapido.addActionListener(myListener);
		    	radioButtonRapido.setBackground(new Color(255,222,173));
		    	
		    	radioButtonExtremo.setToolTipText("Al pinchar sobre el servicio ya sube el pdf. Después funciona como el módo rápido");
		    	radioButtonExtremo.addActionListener(myListener);
		    	radioButtonExtremo.setBackground(new Color(255,222,173));
		    	
		    	radioButtonManual.setToolTipText("Utiliza la tecla c para introducir el nhc, la tecla v para subir, y la barra espaciadora para salir del paciente");
		    	radioButtonManual.addActionListener(myListener);
		    	radioButtonManual.setBackground(new Color(255,222,173));
		    	
		    	// radioButtonAuto.setSelected(true);
		    	radioButtonRapido.setSelected(true);
		    	
		    	ocrBotonesPanel.add(InicioIanus.botonIanus1);
		    	ocrBotonesPanel.add(InicioIanus.botonIanus2,BorderLayout.CENTER);
		    	ocrBotonesPanel.add(InicioIanus.botonServicio,BorderLayout.CENTER);
		    	ocrBotonesPanel.add(InicioIanus.botonNombreNormalizado,BorderLayout.CENTER);
		    	
		    	ocrBotonesPanel.setBackground(new Color(255,222,173));
		    	
			    ocrPanel.add(ocrBotonesVelocidad,BorderLayout.SOUTH);
			    ocrPanel.add(ocrBotonesPanel,BorderLayout.CENTER);
			    ocrPanel.add(ocrControlPanel,BorderLayout.WEST);
			    
			    jslider.setMinorTickSpacing(10);
			    jslider.setMajorTickSpacing(500);
			    jslider.setPaintLabels(true);
			    jslider.setPaintTicks(true);
			    jslider.addChangeListener(new ChangeListener() {
					
					@Override
					public void stateChanged(ChangeEvent arg0) {
						// TODO Auto-generated method stub
						InicioIanus.retardoAsociar = (int) jslider.getValue();
						etiquetaRetardo.setText(String.valueOf(InicioIanus.retardoAsociar));
						//System.out.println(InicioIanus.retardoAsociar);
					}
				});
			    
			    jslider.setBackground(new Color(255,222,173));
			    			    
			    InicioIanus.botonIanus1.setBackground(Color.green);
			    InicioIanus.botonIanus2.setBackground(Color.pink);
			    InicioIanus.botonServicio.setBackground(Color.green);
			    InicioIanus.botonNombreNormalizado.setBackground(Color.green);

			    Dimension normal = new Dimension(160,50);
			    Dimension normalX = new Dimension(180,60);
			    Dimension grande = new Dimension(220,50);
			    
			    Font normalFont = new java.awt.Font("Serif", 1, 20);
			    Font mediaFont = new java.awt.Font("Serif", 1, 32);
			    Font grandeFont = new java.awt.Font("Serif", 1, 40);
			    
			    InicioIanus.botonIanus1.setPreferredSize(normal);
			    InicioIanus.botonIanus2.setPreferredSize(normal);
			    InicioIanus.botonServicio.setPreferredSize(normalX);
			    InicioIanus.botonNombreNormalizado.setPreferredSize(grande);
			       // jButtonAzules.setMinimumSize(micro);
			    //	jButtonAzules.setMaximumSize(micro);
			    //	jButtonAzules.setPreferredSize(micro);
			    
			    InicioIanus.botonIanus1.setFont(mediaFont);
			    InicioIanus.botonIanus2.setFont(mediaFont);
			    InicioIanus.botonServicio.setFont(grandeFont);
			    InicioIanus.botonNombreNormalizado.setFont(normalFont);
			    
			    etiquetaRetardo.setFont(normalFont);
			    
			    InicioIanus.botonIanus1.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						activaIanus(KeyEvent.VK_F7);
					}
				});
			    
			    InicioIanus.botonIanus2.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						activaIanus(KeyEvent.VK_F8);
					}
				});
			    
			    
			    botonResetIanus.setEnabled(false);
			    botonResetIanus.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
				        if (InicioIanus.documentacion == 2 || InicioIanus.documentacion == 3){
				        	
				        	
				        	String cmd = "taskkill.exe /F /IM ianus.exe /T";
				        	
						    File archivoIanus = new File("cal\\ianus.exe");
						    
						    try {
						    	Process hijo;
						    	hijo = Runtime.getRuntime().exec(cmd);
						    	hijo.waitFor();
						    	
						    	Thread.sleep(300);
						    	
								Process p = Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + archivoIanus);
								Thread.sleep(300);
								
								activaIanus(KeyEvent.VK_F5);
								
						    } catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} catch (InterruptedException e){
								e.printStackTrace();
							}
				        }
					}
				});
			    
			    ocrPanel.setBackground(new Color(255,222,173));
			    
			    TitledBorder borde = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder());
			    ocrPanel.setBorder(borde);
			    
			    contentPane.add(ocrPanel, BorderLayout.NORTH);
		    }

		    if(InicioIanus.documentacion == 0 || InicioIanus.documentacion == 1){
		    	if(!Inicio.avisochapuza){
			    	botonRetardos.setBackground(Color.lightGray);
			    	botonRetardos.setText("Temporizadores");
			    	webBrowserPanel.add(botonRetardos, BorderLayout.SOUTH);
			    	
			    	/*
			    	botonRetardos.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent arg0) {
							// TODO Auto-generated method stub
							new VentanaTemporizadores();
						}
					});
			    	*/
		    	}

		    }
		    
		    botonRetardos.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					new VentanaTemporizadores();
				}
			});


		    ocrPanel.setVisible(false);
		    
		    return contentPane;   
	  }   

	  
  public void setPdf(String ruta, String nombrePdf, Color color){
  
	  webBrowser.navigate(ruta);
	  
	  TitledBorder tb = BorderFactory.createTitledBorder( BorderFactory.createEtchedBorder() ,
			  "  " + nombrePdf + "        ", TitledBorder.RIGHT, TitledBorder.TOP, new Font("TimesRoman",Font.BOLD,14), Color.black);
	  
	  webBrowserPanel.setBorder(tb);
	  webBrowserPanel.setBackground(color);

  }
  
	private void activaIanus(int teclaIanus){
		try {
			Robot robot = new Robot();
			robot.keyPress(teclaIanus);
			robot.keyRelease(teclaIanus);
			robot.delay(200);
			
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
  
  
  /* Standard main method to try that test as a standalone application. */  
  public WebBrowserIanus(final String nombreFrame, final Color color, final boolean barraOCR) {   
  
	    //UIUtils.setPreferredLookAndFeel();   
	  
	  try {
          for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
              if ("Nimbus".equals(info.getName())) {
                  javax.swing.UIManager.setLookAndFeel(info.getClassName());
                  break;
              }
          }
      } catch (ClassNotFoundException ex) {
          java.util.logging.Logger.getLogger(VentanaExplorador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
      } catch (InstantiationException ex) {
          java.util.logging.Logger.getLogger(VentanaExplorador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
      } catch (IllegalAccessException ex) {
          java.util.logging.Logger.getLogger(VentanaExplorador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
      } catch (javax.swing.UnsupportedLookAndFeelException ex) {
          java.util.logging.Logger.getLogger(VentanaExplorador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
      }
	  	  
	  ///////
	  
	    SwingUtilities.invokeLater(new Runnable() {   
	      public void run() {   
	    	frame = new JFrame(nombreFrame);
	    	barraOCRvisible = barraOCR;
	        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);   
	        frame.getContentPane().add(createContent(), BorderLayout.CENTER);   
	        frame.setSize(860, 600);   
	        frame.setMinimumSize(new Dimension(800,300));
	        frame.setLocationByPlatform(true); 
	        
	        webBrowserPanel.setBackground(color);
	        	        
	       frame.setVisible(false); 
	      }   
	    });   
	  } 
  
  
	  // frame.setTitle(nombreFrame);
  class RadioListener implements ActionListener{

  	@Override
  	public void actionPerformed(ActionEvent e) {
  		// TODO Auto-generated method stub
  		if (radioButtonManual.isSelected()){
  			InicioIanus.tipoSubida = 0;
  		}
  		else if(radioButtonAuto.isSelected()){
  			InicioIanus.tipoSubida = 1;
  		}
  		else if(radioButtonRapido.isSelected()){
  			InicioIanus.tipoSubida = 2;
  		}
  		else if(radioButtonExtremo.isSelected()){
  			InicioIanus.tipoSubida = 3;
  		}
  	}
  	
  }                         
  
} 

