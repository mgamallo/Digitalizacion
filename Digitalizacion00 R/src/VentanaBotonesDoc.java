import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.swing.ActionMap;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *		CREO FUERA DE SERVICIO
 *
 * @author Coco
 */
public class VentanaBotonesDoc extends javax.swing.JFrame {

    /**
     * Creates new form VentanaBotones
     */
	int pantallas;
	
    public VentanaBotonesDoc() {
    	pantallas = InicioIanus.numeroPantallas;
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

    	jPanel = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        // jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        InicioIanus.teclaAzul1 = new javax.swing.JButton();
        InicioIanus.teclaAzul2 = new javax.swing.JButton();
        
        /*********************/
        jPanel2 = new javax.swing.JPanel();
        jPanel2.setBackground(new Color(255,228,225));
        InicioIanus.jButtonTeclas = new javax.swing.JButton("Teclas Habilitadas");
        InicioIanus.jButtonTeclas.setMinimumSize(new Dimension(170,30));
        InicioIanus.jButtonTeclas.setMaximumSize(new Dimension(170,30));
        InicioIanus.jButtonTeclas.setPreferredSize(new Dimension(170,30));
        InicioIanus.jButtonTeclas.setBackground(new Color(240,250,245));
        InicioIanus.jButtonTeclas.setToolTipText("Activa / Desactiva las teclas rápidad");
        
        jButtonAzules = new javax.swing.JButton("Botones Azules Fijados");
        jButtonAzules.setMinimumSize(new Dimension(170,30));
        jButtonAzules.setMaximumSize(new Dimension(170,30));
        jButtonAzules.setPreferredSize(new Dimension(170,30));
        jButtonAzules.setBackground(new Color(191,239,255));
        jButtonAzules.setToolTipText("Editables: Pulsar sobre cualquier nombre normalizado, para añadirlo como nombre a los botones azules" );
        
        jPanel2.add(InicioIanus.jButtonTeclas);
        jPanel2.add(jButtonAzules);
        
        /************/
        
        Dimension tamañoBotones = new Dimension(185,60);
        
        InicioIanus.jBultimoNombreSubido = new javax.swing.JButton();
        
        String b1, b2, b3;
        
        if(InicioIanus.documentacion != 0 && InicioIanus.documentacion != 3){
          	b1 = "Inclusion l.e.";
            b2 = "Interconsulta";
            b3 = "Consentimento informado";
        }else{
        	b1 = "Informe urg.";
            b2 = "Enfermería urg.";
            b3 = "URG";
        }
        
        String b4 = "Último doc.";
        String b5 = "EKG";
        String b6 = "Eco";
        
        String b7;
        String b8;
        if(InicioIanus.documentacion == 2 || InicioIanus.documentacion == 3){
        	b7 = "NHC";
            b8 = "Documento";
        }
        b7 = "Editable 1";
        b8 = "Editable 2";
        String b9 = "Doc. anulado";
        
        InicioIanus.excel.getDocServicio(InicioIanus.servicioCombo);
        
        setTitle("Teclas Rápidas");
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(780, 180));
        setResizable(true);

        File archivo2;
        if (InicioIanus.documentacion != 2 && InicioIanus.documentacion != 3){
        	archivo2 = new File("cal\\TeclasRapidas.exe");
        }
        else{
        	archivo2 = new File("cal\\TeclasExpiremental.exe");
        }
        
        try {
			Process p = Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + archivo2);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        
        
        jButton1.setBackground(new java.awt.Color(144,238,144));
        jButton1.setMaximumSize(tamañoBotones);
        jButton1.setMinimumSize(tamañoBotones);
        jButton1.setPreferredSize(tamañoBotones);
        jButton1.setFont(new java.awt.Font("Serif", 1, 18));
        jButton1.setText(b1);
        
        jButton1.addMouseListener(new MouseListener() {
         	 public void mouseClicked(MouseEvent evt)
         	 {
         		if (evt.getButton()==java.awt.event.MouseEvent.BUTTON3){
         			abrirAutoHotKey();
         			 JOptionPane.showMessageDialog(jButton1, "Teclas rápidas habilitadas");
             	}
             	else{
             		imprime(jButton1.getText());
             		jPanel.requestFocus();
             		}
         	 }

  			@Override
  			public void mouseEntered(MouseEvent arg0) {
  				// TODO Auto-generated method stub
  				
  			}

  			@Override
  			public void mouseExited(MouseEvent arg0) {
  				// TODO Auto-generated method stub
  				
  			}

  			@Override
  			public void mousePressed(MouseEvent arg0) {
  				// TODO Auto-generated method stub
  				
  			}

  			@Override
  			public void mouseReleased(MouseEvent arg0) {
  				// TODO Auto-generated method stub
  				
  			}
         });

        jButton9.setBackground(new java.awt.Color(144,238,144));
        jButton9.setMaximumSize(tamañoBotones);
        jButton9.setMinimumSize(tamañoBotones);
        jButton9.setPreferredSize(tamañoBotones);
        jButton9.setFont(new java.awt.Font("Serif", 1, 18));
        jButton9.setText(b2);
        
        jButton9.addMouseListener(new MouseListener() {
         	 public void mouseClicked(MouseEvent evt)
         	 {
         		if (evt.getButton()==java.awt.event.MouseEvent.BUTTON3){
         			/* Object seleccion = JOptionPane.showInputDialog(InicioIanus.teclaAzul2,"Seleccione el Documento","Selector de documentos",JOptionPane.QUESTION_MESSAGE,
   		        		   null, // unIcono,  // null para icono defecto
   		        		   new Object[] { "opcion 1", "opcion 2", "opcion 3" }, 
   		        		   "opcion 1");
   		        		   */
             	}
             	else{
             		imprime(jButton9.getText());
             		jPanel.requestFocus();
             		}
         	 }

  			@Override
  			public void mouseEntered(MouseEvent arg0) {
  				// TODO Auto-generated method stub
  				
  			}

  			@Override
  			public void mouseExited(MouseEvent arg0) {
  				// TODO Auto-generated method stub
  				
  			}

  			@Override
  			public void mousePressed(MouseEvent arg0) {
  				// TODO Auto-generated method stub
  				
  			}

  			@Override
  			public void mouseReleased(MouseEvent arg0) {
  				// TODO Auto-generated method stub
  				
  			}
         });

        jButton10.setBackground(new java.awt.Color(144,238,144));
        jButton10.setMaximumSize(tamañoBotones);
        jButton10.setMinimumSize(tamañoBotones);
        jButton10.setPreferredSize(tamañoBotones);
        jButton10.setFont(new java.awt.Font("Serif", 1, 18));
        jButton10.setText(b3);
        
        jButton10.addMouseListener(new MouseListener() {
         	 public void mouseClicked(MouseEvent evt)
         	 {
         		if (evt.getButton()==java.awt.event.MouseEvent.BUTTON3){
         			/* Object seleccion = JOptionPane.showInputDialog(InicioIanus.teclaAzul2,"Seleccione el Documento","Selector de documentos",JOptionPane.QUESTION_MESSAGE,
   		        		   null, // unIcono,  // null para icono defecto
   		        		   new Object[] { "opcion 1", "opcion 2", "opcion 3" }, 
   		        		   "opcion 1");
   		        		   */
             	}
             	else{
             		imprime(jButton10.getText());
             		jPanel.requestFocus();
             		}
         	 }

  			@Override
  			public void mouseEntered(MouseEvent arg0) {
  				// TODO Auto-generated method stub
  				
  			}

  			@Override
  			public void mouseExited(MouseEvent arg0) {
  				// TODO Auto-generated method stub
  				
  			}

  			@Override
  			public void mousePressed(MouseEvent arg0) {
  				// TODO Auto-generated method stub
  				
  			}

  			@Override
  			public void mouseReleased(MouseEvent arg0) {
  				// TODO Auto-generated method stub
  				
  			}
         });

        InicioIanus.jBultimoNombreSubido .setBackground(new java.awt.Color(255, 51, 51));
        InicioIanus.jBultimoNombreSubido .setMaximumSize(tamañoBotones);
        InicioIanus.jBultimoNombreSubido .setMinimumSize(tamañoBotones);
        InicioIanus.jBultimoNombreSubido .setPreferredSize(tamañoBotones);
        InicioIanus.jBultimoNombreSubido .setFont(new java.awt.Font("Serif", 1, 18));
        InicioIanus.jBultimoNombreSubido .setText(b4);
        
        InicioIanus.jBultimoNombreSubido .addMouseListener(new MouseListener() {
         	 public void mouseClicked(MouseEvent evt)
         	 {
         		if (evt.getButton()==java.awt.event.MouseEvent.BUTTON3){
         			cerrarAutoHotKey();
          			 JOptionPane.showMessageDialog(InicioIanus.jBultimoNombreSubido, "Teclas rápidas deshabilitadas");
           			
               	}
             	else{
             		imprime(InicioIanus.jBultimoNombreSubido .getText());
             		jPanel.requestFocus();
             		}
         	 }

  			@Override
  			public void mouseEntered(MouseEvent arg0) {
  				// TODO Auto-generated method stub
  				
  			}

  			@Override
  			public void mouseExited(MouseEvent arg0) {
  				// TODO Auto-generated method stub
  				
  			}

  			@Override
  			public void mousePressed(MouseEvent arg0) {
  				// TODO Auto-generated method stub
  				
  			}

  			@Override
  			public void mouseReleased(MouseEvent arg0) {
  				// TODO Auto-generated method stub
  				
  			}
         });

        jButton12.setBackground(new java.awt.Color(255, 255, 51));
        jButton12.setMaximumSize(tamañoBotones);
        jButton12.setMinimumSize(tamañoBotones);
        jButton12.setPreferredSize(tamañoBotones);
        jButton12.setFont(new java.awt.Font("Serif", 1, 18));
        jButton12.setText(b5);
        
        jButton12.addMouseListener(new MouseListener() {
         	 public void mouseClicked(MouseEvent evt)
         	 {
         		if (evt.getButton()==java.awt.event.MouseEvent.BUTTON3){
         			/* Object seleccion = JOptionPane.showInputDialog(InicioIanus.teclaAzul2,"Seleccione el Documento","Selector de documentos",JOptionPane.QUESTION_MESSAGE,
   		        		   null, // unIcono,  // null para icono defecto
   		        		   new Object[] { "opcion 1", "opcion 2", "opcion 3" }, 
   		        		   "opcion 1");
   		        		   */
             	}
             	else{
             		imprime(jButton12.getText());
             		jPanel.requestFocus();
             		}
         	 }

  			@Override
  			public void mouseEntered(MouseEvent arg0) {
  				// TODO Auto-generated method stub
  				
  			}

  			@Override
  			public void mouseExited(MouseEvent arg0) {
  				// TODO Auto-generated method stub
  				
  			}

  			@Override
  			public void mousePressed(MouseEvent arg0) {
  				// TODO Auto-generated method stub
  				
  			}

  			@Override
  			public void mouseReleased(MouseEvent arg0) {
  				// TODO Auto-generated method stub
  				
  			}
         });
        

        jButton13.setBackground(new java.awt.Color(255, 255, 51));
        jButton13.setMaximumSize(tamañoBotones);
        jButton13.setMinimumSize(tamañoBotones);
        jButton13.setPreferredSize(tamañoBotones);
        jButton13.setFont(new java.awt.Font("Serif", 1, 18));
        jButton13.setText(b6);
        
        jButton13.addMouseListener(new MouseListener() {
          	 public void mouseClicked(MouseEvent evt)
          	 {
          		if (evt.getButton()==java.awt.event.MouseEvent.BUTTON3){
          			/* Object seleccion = JOptionPane.showInputDialog(InicioIanus.teclaAzul2,"Seleccione el Documento","Selector de documentos",JOptionPane.QUESTION_MESSAGE,
    		        		   null, // unIcono,  // null para icono defecto
    		        		   new Object[] { "opcion 1", "opcion 2", "opcion 3" }, 
    		        		   "opcion 1");
    		        		   */
              	}
              	else{
              		imprime(jButton13.getText());
              		jPanel.requestFocus();
              		}
          	 }

   			@Override
   			public void mouseEntered(MouseEvent arg0) {
   				// TODO Auto-generated method stub
   				
   			}

   			@Override
   			public void mouseExited(MouseEvent arg0) {
   				// TODO Auto-generated method stub
   				
   			}

   			@Override
   			public void mousePressed(MouseEvent arg0) {
   				// TODO Auto-generated method stub
   				
   			}

   			@Override
   			public void mouseReleased(MouseEvent arg0) {
   				// TODO Auto-generated method stub
   				
   			}
          });
        

        InicioIanus.teclaAzul1.setBackground(new java.awt.Color(175,238,238));
        InicioIanus.teclaAzul1.setMaximumSize(tamañoBotones);
        InicioIanus.teclaAzul1.setMinimumSize(tamañoBotones);
        InicioIanus.teclaAzul1.setPreferredSize(tamañoBotones);
        InicioIanus.teclaAzul1.setFont(new java.awt.Font("Serif", 1, 18));
        InicioIanus.teclaAzul1.setText(b7);
        
        InicioIanus.teclaAzul1.addMouseListener(new MouseListener() {
       	 public void mouseClicked(MouseEvent evt)
       	 {
	       	if(InicioIanus.documentacion != 2 && InicioIanus.documentacion != 3){	
       		 	if (evt.getButton()==java.awt.event.MouseEvent.BUTTON3){
	       			 
	       			InicioIanus.excel.getDocServicio(InicioIanus.servicioCombo);
	       			
	       			Object[] objetosDocumentos = InicioIanus.documentosServicio.toArray();
	       			
	       			Object seleccion = JOptionPane.showInputDialog(InicioIanus.teclaAzul2,"Seleccione el Documento","Selector de documentos",JOptionPane.QUESTION_MESSAGE,
	 		        		   null, // unIcono,  // null para icono defecto
	 		        		   objetosDocumentos, 
	 		        		   objetosDocumentos[0]);
	       			
	       			InicioIanus.teclaAzul1.setText(seleccion.toString());
	           	}
	           	else{
	           		imprime(InicioIanus.teclaAzul1.getText());
	           		jPanel.requestFocus();
	           	}
	       	}
	       	else{
	       		introduceNHC();
	       		jPanel.requestFocus();
	       	}
       	 }

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
       });
        

        InicioIanus.teclaAzul2.setBackground(new java.awt.Color(175,238,238));
        InicioIanus.teclaAzul2.setMaximumSize(tamañoBotones);
        InicioIanus.teclaAzul2.setMinimumSize(tamañoBotones);
        InicioIanus.teclaAzul2.setPreferredSize(tamañoBotones);
        InicioIanus.teclaAzul2.setFont(new java.awt.Font("Serif", 1, 18));
        InicioIanus.teclaAzul2.setText(b8);
           
        InicioIanus.teclaAzul2.addMouseListener(new MouseListener() {
        	 public void mouseClicked(MouseEvent evt)
        	 {
	        	if(InicioIanus.documentacion != 2 && InicioIanus.documentacion != 3){	
        		 	if (evt.getButton()==java.awt.event.MouseEvent.BUTTON3){
	        			
	        			Object[] objetosDocumentos = InicioIanus.documentosServicio.toArray();
	           			
	           			Object seleccion = JOptionPane.showInputDialog(InicioIanus.teclaAzul2,"Seleccione el Documento","Selector de documentos",JOptionPane.QUESTION_MESSAGE,
	     		        		   null, // unIcono,  // null para icono defecto
	     		        		   objetosDocumentos, 
	     		        		   objetosDocumentos[0]);
	           			InicioIanus.teclaAzul2.setText(seleccion.toString());
	            	}
	            	else{
	            		 imprime(InicioIanus.teclaAzul2.getText());
	            		 jPanel.requestFocus();
	            	}
	        	}
	        	else{
	        		imprimeNombreDoc();
	        	}
        	 }

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
        });
 
        InicioIanus.jButtonTeclas.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if (InicioIanus.jButtonTeclas.getText().contains("Teclas Habilitadas")){
					cerrarAutoHotKey();
					jPanel1.requestFocus();
				}else{
					abrirAutoHotKey();
					jPanel.requestFocus();
				}
			}
        	
        });
        
        jButtonAzules.addActionListener(new ActionListener(){

  			@Override
  			public void actionPerformed(ActionEvent arg0) {
  				// TODO Auto-generated method stub
  				if(InicioIanus.documentacion != 2 && InicioIanus.documentacion != 3){
	  				if (jButtonAzules.getText().contains("Botones Azules Editables")){
	  					InicioIanus.teclasAzulesEditables = false;
	  					jButtonAzules.setText("Botones Azules Fijados");
	  					jButtonAzules.setBackground(new Color(191,239,255));
	  					jPanel.requestFocus();
	  				}else{
	  					InicioIanus.teclasAzulesEditables = true;
	  					jButtonAzules.setText("Botones Azules Editables");
	  					jButtonAzules.setBackground(new Color(000,191,255));
	  					jPanel.requestFocus();
	  				}
  				}
  			}
          	
          });
        
        
        KeyListener listener = new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				System.out.println("keyTyped="+KeyEvent.getKeyText(e.getKeyCode()));
			}

			@Override
			public void keyPressed(KeyEvent e) {
				System.out.println("keyPressed="+KeyEvent.getKeyText(e.getKeyCode()));
			}

			@Override
			public void keyReleased(KeyEvent e) {
				
				switch (e.getKeyCode()){
				case KeyEvent.VK_A: imprime(jButton1.getText());break;
				case KeyEvent.VK_F1:imprime(jButton1.getText());break;
				case KeyEvent.VK_S: imprime(jButton9.getText());break;
				case KeyEvent.VK_F2:imprime(jButton9.getText());break;
				case KeyEvent.VK_D: imprime(jButton10.getText());break;
				case KeyEvent.VK_F3:imprime(jButton10.getText());break;
				case KeyEvent.VK_F: imprime(InicioIanus.jBultimoNombreSubido .getText());break;
				case KeyEvent.VK_F4:imprime(InicioIanus.jBultimoNombreSubido .getText());break;
				case KeyEvent.VK_Z: imprime(jButton12.getText());break;
				case KeyEvent.VK_F5:imprime(jButton12.getText());break;
				case KeyEvent.VK_X: imprime(jButton13.getText());break;
				case KeyEvent.VK_F6:imprime(jButton13.getText());break;
				case KeyEvent.VK_F7:
				case KeyEvent.VK_C: 
					if(InicioIanus.documentacion != 2 && InicioIanus.documentacion != 3){
						imprime(InicioIanus.teclaAzul1.getText());break;
					}
					else{
						introduceNHC();
					}
					break;
				case KeyEvent.VK_F8:
				case KeyEvent.VK_V: 
					if(InicioIanus.documentacion != 2 && InicioIanus.documentacion != 3){
						imprime(InicioIanus.teclaAzul2.getText());break;
					}
					else{
						imprimeNombreDoc();
					}
					break;
				case KeyEvent.VK_ESCAPE: cerrarAutoHotKey();break;
				}
				
			//	if(e.getKeyCode() == KeyEvent.VK_A )
					
			//	 System.out.println("keyReleased="+KeyEvent.getKeyText(e.getKeyCode()));
			}
		};
		jPanel.addKeyListener(listener);
		jPanel.setFocusable(true);
        
        
 /*       
        ActionMap mapaAccion = jPanel1.getActionMap();
        InputMap map = jPanel1.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        KeyStroke key_F1 = KeyStroke.getKeyStroke(KeyEvent.VK_F1,0);
  */      
        
		
        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setBackground(Color.white);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(InicioIanus.teclaAzul1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(InicioIanus.teclaAzul2))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(InicioIanus.jBultimoNombreSubido )))
                .addContainerGap(129, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(InicioIanus.jBultimoNombreSubido , javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(InicioIanus.teclaAzul1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(InicioIanus.teclaAzul2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(250, Short.MAX_VALUE))
        );
/*
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
*/
       jPanel.setLayout(new BorderLayout());
       jPanel.add(jPanel2, BorderLayout.NORTH);
       jPanel.add(jPanel1, BorderLayout.CENTER);
       setContentPane(jPanel);
        
        
        
        pack();
        this.setVisible(true);
      //  abrirAutoHotKey();
    }// </editor-fold>

 
    public void introduceNHC(){
    	
    }
 
    public void imprimeNombreDoc(){
    	
    }
    
    public void imprime(String cadena){
        RobotIanus robotI = new RobotIanus();
        InicioIanus.nombreDocumento = cadena;
        
        System.out.println("Hola");
        System.out.println(InicioIanus.nombreDocumento);
        
        System.out.println(InicioIanus.nombreDocumento);
        System.out.println(InicioIanus.servicioCombo.toString());
        System.out.println(cadena + "\n ");
        
        if(InicioIanus.servicioCombo.contains("DIG") && 
   				(cadena.contains("Enfermería endosc.") ||
   				 cadena.contains("Endoscopia") ||
   				 cadena.contains("Manometría"))){
       	 
           		InicioIanus.tipoDocAsubir = 1; //	Subir enfermería de dig como ingreso    
           		System.out.println("Ingresos");
        }

        if(pantallas != 1){ pantallas = 4;}
        
        
//-----------------------------
      robotI.robotIanus(cadena, 1000, pantallas, InicioIanus.tipoDocAsubir, "",false,false);
        
        
        InicioIanus.estadistica.añadirDato(InicioIanus.servicioCombo, InicioIanus.nombreDocumento);

        
  //      InicioIanus.estadistica.
        
            if(InicioIanus.numeroIanus==1 || !InicioIanus.par){
	            VentanaExplorador.numArchivo++;
	            int tamañoLista = VentanaExplorador.listaPdfs.getModel().getSize();
	            if(VentanaExplorador.numArchivo<tamañoLista){
	            	VentanaExplorador.listaPdfs.setSelectedIndex(VentanaExplorador.numArchivo);
	            	
	        		int pdfNumero = VentanaExplorador.listaPdfs.getSelectedIndex();
	 
	        		File archivo = new File(InicioIanus.rutaCompletaPdfs[VentanaExplorador.numArchivo]);
	        		try{
//	        			Desktop.getDesktop().open(archivo);
	        			Robot robot = new Robot();
	        			robot.delay(400);
	       			    Process p = Runtime.getRuntime()
    					        .exec("rundll32 url.dll,FileProtocolHandler " + archivo);		        			
	       			    // Coloca cursor sobre aceptar
	        			robot.mouseMove(1504, 706);
	        		}catch (Exception ev) {
	        			System.out.println(ev);
	        		}
	            }
	            if(InicioIanus.numeroIanus == 2){
	            	VentanaExplorador.numArchivo++;
		            if(VentanaExplorador.numArchivo<tamañoLista){
		            	VentanaExplorador.listaPdfs.setSelectedIndex(VentanaExplorador.numArchivo);
		            	
		        		int pdfNumero = VentanaExplorador.listaPdfs.getSelectedIndex();
		 
		        		File archivo = new File(InicioIanus.rutaCompletaPdfs[VentanaExplorador.numArchivo]);
		        		try{
//		        			Desktop.getDesktop().open(archivo);
	            			Robot robot = new Robot();
	            			robot.delay(600);
		        			Process p = Runtime.getRuntime()
	    					        .exec("rundll32 url.dll,FileProtocolHandler " + archivo);			        			
	

	            			robot.delay(400);
	            			//	Ajusta los documentos horizontalmente

	        				robot.keyPress(KeyEvent.VK_CONTROL);
	        				robot.keyPress(KeyEvent.VK_SHIFT);
	        				robot.keyPress(KeyEvent.VK_K);
	        				robot.keyRelease(KeyEvent.VK_K);
	        				robot.keyRelease(KeyEvent.VK_SHIFT);
	        				robot.keyRelease(KeyEvent.VK_CONTROL);
			         			
		        		}catch (Exception ev) {
		        			System.out.println(ev);
		        		}		            	
		            }
	            }
   			
            }
        
        
        
        
   }
    
    public void cerrarAutoHotKey(){

 			try {
 				
				Robot robot = new Robot();
				robot.keyPress(KeyEvent.VK_ESCAPE);
				robot.keyRelease(KeyEvent.VK_ESCAPE);
				robot.delay(100);
				
				InicioIanus.jButtonTeclas.setBackground(new Color(255,106,106));
		    	InicioIanus.jButtonTeclas.setText("Teclas Deshabilitadas");
				
			} catch (AWTException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
 	
     }
     
     public void abrirAutoHotKey(){
    	 File archivo2;
    	 if(InicioIanus.documentacion != 2 && InicioIanus.documentacion != 3){
    		  archivo2 = new File("cal\\TeclasRapidas.exe");
    	 }
    	 else{
    		 archivo2 = new File("cal\\TeclasExpiremental.exe");
    	 }
         
         try {
 			Process p = Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + archivo2);
 			
 			InicioIanus.jButtonTeclas.setBackground(new Color(127,255,212));
	    	InicioIanus.jButtonTeclas.setText("Teclas Habilitadas");  
         
         } catch (IOException e1) {
 			// TODO Auto-generated catch block
 			e1.printStackTrace();
 		}
     }
    
    // Variables declaration - do not modify
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel;    

    private javax.swing.JButton jButtonAzules;
    // End of variables declaration
}
