import java.awt.AWTException;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class VentanaCalibrador extends JFrame implements Runnable, ActionListener{

	/**
	 * @param args
	 */
	Thread hilo;
	boolean terminarHilo = true;
	
	Coordenadas coordenadas = new Coordenadas();
	
	URL urlImagen;
	JLabel fotoLabel = new JLabel();
	ImageIcon imagen;
	
	JButton botonCancelar = new JButton("Cancelar");
	JButton botonReiniciar = new JButton("Reiniciar");
	JButton botonEmpezar = new JButton("Empezar");
	JButton botonGuardar = new JButton("Guardar");
	JButton botonSiguiente = new JButton("Siguiente");
	
	JLabel crono = new JLabel("5.0");
	JLabel coordX = new JLabel("X: ");
	JLabel coordY = new JLabel("Y: ");
	JLabel etiquetaCoordenada = new JLabel("Paso 1 de 3.");
	JLabel etiquetaTipoCoordenada;
	JTextField explicacion = new JTextField(35);
	
	JPanel panel = new JPanel();
		
	String paso[] = { 	"Click Empezar y raton sobre el campo \"Titulo\"",
						"Click Empezar y raton sobre el campo \"Ficheiro\"",
						"Click Empezar y raton sobre el boton \"Aceptar\""};
	
	String pasoLabel[] = {	"Paso 1 de 3.",
							"Paso 2 de 3.",
							"Paso 3 de 3."
						 };
	
	int pasoVisible = 1;
	
	Point location = new Point();
	
	VentanaCalibrador(final String tipoCoordenada){

		setTitle(tipoCoordenada);
		etiquetaTipoCoordenada = new JLabel(tipoCoordenada);
		setSize(550,450);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		panel.setLayout(new GridBagLayout());
		
	//	urlImagen = this.getClass().getResource("cal\\Plantilla 01.jpg");
		imagen = new ImageIcon("cal\\Plantilla 01.jpg");
		fotoLabel.setIcon(imagen);
		
		
		
		GridBagConstraints conf002 = new GridBagConstraints();
		conf002.gridx=5;
		conf002.gridy=0;
		conf002.anchor = GridBagConstraints.EAST;
		etiquetaTipoCoordenada.setFont(new Font("Arial",Font.BOLD,22));
		etiquetaTipoCoordenada.setForeground(Color.red);
		
		panel.add(etiquetaTipoCoordenada,conf002);
		
		GridBagConstraints conf00 = new GridBagConstraints();
		conf00.gridx = 0;
		conf00.gridy = 0;
		conf00.insets = new Insets(10,0,20,10);
		conf00.anchor = GridBagConstraints.CENTER;
		panel.add(etiquetaCoordenada,conf00);		

		GridBagConstraints conf001 = new GridBagConstraints();
		conf001.gridx = 1;
		conf001.gridy = 0;
		conf001.gridwidth = 2;
		conf001.gridheight = 1;
		conf001.fill = GridBagConstraints.BOTH;
		conf001.insets = new Insets(10,0,20,0);
		conf001.anchor = GridBagConstraints.CENTER;
		explicacion.setText(paso[0]);
		explicacion.setEditable(false);
		explicacion.setBackground(Color.white);
		panel.add(explicacion,conf001);		
		
		GridBagConstraints conf0 = new GridBagConstraints();
		conf0.gridx = 0;
		conf0.gridy = 1;
		conf0.gridwidth = 3;
		conf0.gridheight = 4;
		panel.add(fotoLabel,conf0);
		
		GridBagConstraints conf1 = new GridBagConstraints();
		conf1.gridx = 0;
		conf1.gridy = 5;
		conf1.insets = new Insets(10,20,20,5);
		conf1.anchor = GridBagConstraints.CENTER;
		panel.add(botonCancelar,conf1);
		
	    botonCancelar.addActionListener(new ActionListener(){
	    	public void actionPerformed(java.awt.event.ActionEvent evt) {
	    		dispose();
	    	}
	    });
		
		GridBagConstraints conf2 = new GridBagConstraints();
		conf2.gridx = 1;
		conf2.gridy = 5;
		conf2.insets = new Insets(10,20,20,5);
		conf2.anchor = GridBagConstraints.CENTER;
		panel.add(botonReiniciar,conf2);
		
	    botonReiniciar.addActionListener(new ActionListener(){
	    	public void actionPerformed(java.awt.event.ActionEvent evt) {
	    		coordenadas = new Coordenadas();
	    		pasoVisible = 1;
	    		explicacion.setText(paso[0]);
	    		etiquetaCoordenada.setText(pasoLabel[0]);
	    		coordX.setText("X: " );
	    		coordY.setText("Y: " );
        		imagen = new ImageIcon("cal\\Plantilla 0" + pasoVisible + ".jpg");
	    		imagen.getImage().flush();
	    		fotoLabel.setIcon(imagen);
    			botonSiguiente.setVisible(true);
    			botonGuardar.setVisible(false);
	    	}
	    });
		
		GridBagConstraints conf3 = new GridBagConstraints();
		conf3.gridx = 2;
		conf3.gridy = 5;
		conf3.insets = new Insets(10,0,20,5);
		conf3.anchor = GridBagConstraints.EAST;
		panel.add(botonGuardar,conf3);
		
	    botonGuardar.addActionListener(new ActionListener(){
	    	public void actionPerformed(java.awt.event.ActionEvent evt) {
	    				coordenadas.aceptar = location;
	    				int confirmado = JOptionPane.showConfirmDialog(null, "¿Seguro que quieres grabar las nuevas coordenadas?");
	    				if(JOptionPane.OK_OPTION == confirmado){
	    					new GuardarCoordenadas("Documentos.xls",tipoCoordenada,coordenadas);
	    	    		}
	    				dispose();
	    	}
	    });
		
		botonGuardar.setVisible(false);
		
		GridBagConstraints conf4 = new GridBagConstraints();
		conf4.gridx = 5;
		conf4.gridy = 1;
		conf4.insets = new Insets(50,20,0,10);
		conf4.anchor = GridBagConstraints.CENTER;
		panel.add(botonEmpezar,conf4);
				
	    botonEmpezar.addActionListener(this);

		GridBagConstraints conf5 = new GridBagConstraints();
		conf5.gridx = 5;
		conf5.gridy = 2;
		conf5.insets = new Insets(10,30,0,5);
		conf5.anchor = GridBagConstraints.WEST;
		panel.add(coordX,conf5);
		
		GridBagConstraints conf6 = new GridBagConstraints();
		conf6.gridx = 5;
		conf6.gridy = 3;
		conf6.insets = new Insets(10,30,20,5);
		conf6.anchor = GridBagConstraints.WEST;
		panel.add(coordY,conf6);
		
		GridBagConstraints conf7 = new GridBagConstraints();
		conf7.gridx = 5;
		conf7.gridy = 5;
		conf7.insets = new Insets(10,20,20,0);
		conf7.anchor = GridBagConstraints.SOUTH;
		botonSiguiente.setEnabled(false);
		panel.add(botonSiguiente,conf7);
		
	    botonSiguiente.addActionListener(new ActionListener(){
	    	public void actionPerformed(java.awt.event.ActionEvent evt) {
	    		if(pasoVisible < 3){
	    			if(pasoVisible ==1){
	    				coordenadas.x = location;
	    			}
	    			if(pasoVisible ==2){
	    				coordenadas.ruta = location;
	    			}
	    			
	    			botonEmpezar.setEnabled(true);
	    			pasoVisible++;
	        		imagen = new ImageIcon("cal\\Plantilla 0" + pasoVisible + ".jpg");
		    		imagen.getImage().flush();
		    		fotoLabel.setIcon(imagen);
		    		coordX.setText("X: " );
		    		coordY.setText("Y: " );
		    		etiquetaCoordenada.setText(pasoLabel[pasoVisible-1]);
		    		explicacion.setText(paso[pasoVisible-1]);
		    		if(pasoVisible == 3){
		    			botonSiguiente.setVisible(false);
		    			botonGuardar.setVisible(true);
		    			botonGuardar.setEnabled(false);
		    		}
		    		botonSiguiente.setEnabled(false);
	    		}
	    	}
	    });
		
		GridBagConstraints conf8 = new GridBagConstraints();
		conf8.gridx = 5;
		conf8.gridy = 4;
		conf8.insets = new Insets(10,20,20,0);
		conf8.anchor = GridBagConstraints.NORTH;
		crono.setFont(new Font("Arial",Font.BOLD,20));
		crono.setForeground(Color.red);
		
		panel.add(crono,conf8);
		
		setContentPane(panel);
		setVisible(true);
	}

	public Point calibrador(){
	    try{
				Robot robot = new Robot();
				String cadena = "Hola";


				PointerInfo pointer = MouseInfo.getPointerInfo();
				return pointer.getLocation();

						
			}catch (AWTException e){
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    return null;
	}
/*	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		VentanaCalibrador v = new VentanaCalibrador("Consultas");
	}
*/
	@Override
	public void run() {
		// TODO Auto-generated method stub

		while(!terminarHilo){
				Thread ct = Thread.currentThread();
		
				String cadena;
				int segundos = 5;
				int decimas = 100;
				int cronometro = 500;
				crono.setText("5.0");
				 while(cronometro>0) {   
					 if(cronometro%100 == 0){
						 segundos--;
					 }
					 
					 try {
				    	hilo.sleep(100);
					 }catch(InterruptedException e) {}
					
					 cronometro-=10;
					 decimas-=10;
					 if(decimas == 0){
						 decimas = 100;
						 crono.setText(Integer.toString(segundos) + ".0");
					 }
					 if(decimas != 100)
						 crono.setText(Integer.toString(segundos) + "." + Integer.toString(decimas/10));
				 }
		
				location = calibrador();
				coordX.setText("X: " + Integer.toString(location.x));
				coordY.setText("Y: " + Integer.toString(location.y));
				botonSiguiente.setEnabled(true);
				terminarHilo = true;
				botonEmpezar.setEnabled(false);
				botonGuardar.setEnabled(true);
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg) {
		// TODO Auto-generated method stub
		Object ob = arg.getSource();
		if(ob instanceof JButton){
			JButton btn = (JButton)ob;
			if(btn.getText().equals("Empezar")){
				iniciarCrono();
			}
		}
	}
	
	public void iniciarCrono(){
		terminarHilo = false;
		hilo = new Thread(this);
		hilo.start();
	}

}


