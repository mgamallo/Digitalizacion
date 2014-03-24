import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class VentanaMensajesAvisos extends JDialog{

	/**
	 * @param args
	 */
	
	JLabel avisoJL = new JLabel("¡ N U E V O   M E N S A J E !");
	JLabel fotoLabel = new JLabel();
	JLabel emisorMensajeJL = new JLabel("Mensaje de:   " );
	JLabel contenidoJL = new JLabel("Mensaje: ");
	
	JButton aceptarJB = new JButton("Siguiente");
	JButton responderJB = new JButton("Responder");
	
	JTextArea contenidoJTAServ = new JTextArea("",3,30);
	JTextArea contenidoJTAObs = new JTextArea("",6,30);
	
	VisualizadorAvisos visual = new VisualizadorAvisos("");
	
	JPanel panel = new JPanel();
	JPanel panelG = new JPanel();
	JPanel panelN = new JPanel();
	JPanel panelS = new JPanel();

	JScrollPane scrollSer = new JScrollPane();
	JScrollPane scrollObs = new JScrollPane();
	
	ImageIcon imagen;
	int contadorNovedades = 1;
	
	String cadena = "Sin Imagen.jpg";

	
	VentanaMensajesAvisos(final ArrayList<String> listaMensajesNuevos){
		
		setSize(720,490);
		
		this.setBackground(Color.pink);
	//	this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		panel.setLayout(new BorderLayout());
		panelG.setLayout(new GridBagLayout());
		
		avisoJL.setFont(new Font("Serif",Font.BOLD,28));
		avisoJL.setForeground(Color.DARK_GRAY);
		panelN.add(avisoJL);
		
		cadena = InicioIanus.excel.getFotoMensaje(Integer.parseInt(listaMensajesNuevos.get(0)));
		imagen = new ImageIcon("Imagenes\\250x350\\" + cadena);
		fotoLabel.setIcon(imagen);
		fotoLabel.setBorder(BorderFactory.createLineBorder(Color.black));
		fotoLabel.setToolTipText("Doble click para ampliar");
		EventoMouseClicked(fotoLabel);
		
		GridBagConstraints conf00 = new GridBagConstraints();
		conf00.gridx = 0;
		conf00.gridy = 0;
		conf00.gridwidth = 2;
		conf00.gridheight = 5;
		conf00.insets = new Insets(10,0,0,10);
		conf00.anchor = GridBagConstraints.EAST;
		panelG.add(fotoLabel,conf00);		

		GridBagConstraints conf20 = new GridBagConstraints();
		conf20.gridx = 2;
		conf20.gridy = 0;
		conf20.insets = new Insets(10,0,5,0);
		emisorMensajeJL.setText("De parte de:   " + InicioIanus.excel.getEmisorMensaje(Integer.parseInt(listaMensajesNuevos.get(0))));
		emisorMensajeJL.setFont(new Font("Serif",Font.BOLD,18));
		emisorMensajeJL.setForeground(Color.blue);
		panelG.add(emisorMensajeJL,conf20);		
		
		GridBagConstraints conf24 = new GridBagConstraints();
		conf24.gridx = 2;
		conf24.gridy = 4;
		conf24.gridwidth = 2;
	//	conf24.gridheight = 2;
		conf24.fill = GridBagConstraints.BOTH;
		contenidoJTAObs.setLineWrap(true);
	//	contenidoJTAObs.setFont(new Font("Courier",Font.BOLD,16));
		contenidoJTAObs.setEditable(false);
		contenidoJTAObs.setBackground(Color.white);
		contenidoJTAObs.setText("  " + InicioIanus.excel.getContenidoMensaje(Integer.parseInt(listaMensajesNuevos.get(0))));
		scrollObs.setViewportView(contenidoJTAObs);
		panelG.add(scrollObs,conf24);
		
		panelS.add(aceptarJB);
		if(listaMensajesNuevos.get(1).toString().contains("N")){
			aceptarJB.setText("Aceptar");
		}
		panelS.add(responderJB);

		
	    aceptarJB.addActionListener(new ActionListener(){
	    	public void actionPerformed(java.awt.event.ActionEvent evt) {
	    		if(aceptarJB.getText().equals("Aceptar")){
	    			InicioIanus.numeroNuvosMensajes = contadorNovedades;
	    		//	System.out.println(contadorNovedades);
		    		dispose();
	    		}
	    		else{
	    			emisorMensajeJL.setText("De parte de:   " + InicioIanus.excel.getEmisorMensaje(Integer.parseInt(listaMensajesNuevos.get(contadorNovedades))));
	    			contenidoJTAObs.setText("  " + InicioIanus.excel.getContenidoMensaje(Integer.parseInt(listaMensajesNuevos.get(contadorNovedades))));
	    			cadena = InicioIanus.excel.getFotoMensaje(Integer.parseInt(listaMensajesNuevos.get(contadorNovedades)));
	    			imagen = new ImageIcon("Imagenes\\250x350\\" + cadena);
	    			fotoLabel.setIcon(imagen);
	    			contadorNovedades++;
	    			if(listaMensajesNuevos.get(contadorNovedades).toString().contains("N")){
	    				aceptarJB.setText("Aceptar");
	    			}
	    		}
	    	}
	    });
	    
	    
	    responderJB.addActionListener(new ActionListener(){
	    	public void actionPerformed(java.awt.event.ActionEvent evt) {
	    		new VentanaAñadirAvisos();
	    	}
	    });
		
		panel.add(panelN, BorderLayout.NORTH);
		panel.add(panelG, BorderLayout.CENTER);
		panel.add(panelS, BorderLayout.SOUTH);
		panel.setBorder(BorderFactory.createLineBorder(Color.black));
		panelG.setBackground(Color.pink);
		
		this.setModal(true);
		setUndecorated(true);
		setContentPane(panel);
		this.setLocationRelativeTo(null);
		setVisible(true);

	}
	
	
	public void EventoMouseClicked(final JLabel fotoLabel){
		fotoLabel.addMouseListener(new MouseAdapter(){
			
			@Override
			public void mousePressed(MouseEvent e){
				
				if(e.getClickCount() == 2){
					int aux = cadena.lastIndexOf(".");
					String cadenaAux = cadena.substring(0,aux);
					Visor v = new Visor(null, cadenaAux);
				}

			}

		});
	}	
	
/*
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new VentanaMensajes();
	}
*/
}
