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
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class VentanaMensajeDocNuevo extends JDialog{

	/**
	 * @param args
	 */
	
	JLabel avisoJL = new JLabel("¡ M O D E L O     N U E V O !");
	JLabel fotoLabel = new JLabel();
	JLabel nombreJL = new JLabel("Nombre del documento");
	JLabel servicioJL = new JLabel("Servicios a los que afecta:  ");
	JLabel contenidoJL = new JLabel("Observaciones: ");
	
	JButton aceptarJB = new JButton("Siguiente");
	
	JTextArea contenidoJTAServ = new JTextArea("",3,30);
	JTextArea contenidoJTAObs = new JTextArea("",6,30);
	
	
	JPanel panel = new JPanel();
	JPanel panelG = new JPanel();
	JPanel panelN = new JPanel();
	JPanel panelS = new JPanel();

	JScrollPane scrollSer = new JScrollPane();
	JScrollPane scrollObs = new JScrollPane();
	
	ImageIcon imagen;
	int contadorNovedades = 1;
	

	String cadena = "Sin Imagen.jpg";
	String cadenaServicios = "";

	AvisosDocumentos avisos = new AvisosDocumentos();
	
	
	
	VentanaMensajeDocNuevo(final ArrayList<String> listaNovedades){
		
		setSize(720,490);
		
		this.setBackground(Color.pink);
	//	this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		avisos = InicioIanus.excel.getDatosNuevoDocumento(Integer.parseInt(listaNovedades.get(0)));
		

		if(!avisos.documentoNuevo.contains("N")){
			avisoJL.setText("¡ D O C U M E N T O     N U E V O !");
			panelG.setBackground(new java.awt.Color(204, 255, 204));
		}else{
			panelG.setBackground(new java.awt.Color(255, 204, 204));
		}
		
		panel.setLayout(new BorderLayout());
		panelG.setLayout(new GridBagLayout());
		
		avisoJL.setFont(new Font("Serif",Font.BOLD,28));
		avisoJL.setForeground(Color.DARK_GRAY);
		panelN.add(avisoJL);
				
		cadena = avisos.rutaJpg + ".jpg";
		System.out.println(cadena);
		imagen = new ImageIcon("Imagenes\\250x350\\" +  cadena );
		fotoLabel.setIcon(imagen);
		fotoLabel.setToolTipText("Doble click para ampliar");
		fotoLabel.setBorder(BorderFactory.createLineBorder(Color.black));
		EventoMouseClicked(fotoLabel);
		
		GridBagConstraints conf00 = new GridBagConstraints();
		conf00.gridx = 0;
		conf00.gridy = 0;
		conf00.gridwidth = 2;
		conf00.gridheight = 6;
		conf00.insets = new Insets(10,0,0,10);
		conf00.anchor = GridBagConstraints.EAST;
		panelG.add(fotoLabel,conf00);		

		GridBagConstraints conf20 = new GridBagConstraints();
		conf20.gridx = 2;
		conf20.gridy = 0;
		conf20.insets = new Insets(10,0,5,0);
		conf20.anchor = GridBagConstraints.WEST;
		nombreJL.setForeground(Color.blue);
		nombreJL.setFont(new Font("Serif",Font.BOLD,28));
		nombreJL.setText(avisos.nombreDocumento);
		panelG.add(nombreJL,conf20);		
		
		GridBagConstraints conf21 = new GridBagConstraints();
		conf21.gridx = 2;
		conf21.gridy = 1;
		conf21.insets = new Insets(10,0,5,0);
		conf21.anchor = GridBagConstraints.WEST;
		panelG.add(servicioJL,conf21);		
		
		GridBagConstraints conf22 = new GridBagConstraints();
		conf22.gridx = 2;
		conf22.gridy = 2;
		conf22.gridwidth = 2;
	//	conf21.gridheight = 2;
		conf22.fill = GridBagConstraints.BOTH;
		for(int z=0;z<avisos.serviciosDocumento.size();z++){
			cadenaServicios += avisos.serviciosDocumento.get(z);
			if(z != avisos.serviciosDocumento.size()-1){
				cadenaServicios += ", ";
			}
			else{
				cadenaServicios += ".";
			}
		}
		contenidoJTAServ.setText(cadenaServicios);
		contenidoJTAServ.setLineWrap(true);
		contenidoJTAServ.setFont(new Font("Courier",Font.BOLD,16));
		contenidoJTAServ.setEditable(false);
		contenidoJTAServ.setBackground(Color.white);
		scrollSer.setViewportView(contenidoJTAServ);
		panelG.add(scrollSer,conf22);
		
		GridBagConstraints conf23 = new GridBagConstraints();
		conf23.gridx = 2;
		conf23.gridy = 4;
		conf23.insets = new Insets(10,0,5,0);
		conf23.anchor = GridBagConstraints.WEST;
		panelG.add(contenidoJL,conf23);
	
		GridBagConstraints conf24 = new GridBagConstraints();
		conf24.gridx = 2;
		conf24.gridy = 5;
		conf24.gridwidth = 2;
	//	conf24.gridheight = 2;
		conf24.fill = GridBagConstraints.BOTH;
		contenidoJTAObs.setLineWrap(true);
	//	contenidoJTAObs.setFont(new Font("Courier",Font.BOLD,16));
		contenidoJTAObs.setEditable(false);
		contenidoJTAObs.setBackground(Color.white);
		contenidoJTAObs.setText(avisos.observacionesDocumento);
		scrollObs.setViewportView(contenidoJTAObs);
		panelG.add(scrollObs,conf24);
		
		panelS.add(aceptarJB);
		if(listaNovedades.get(1).toString().contains("N")){
			aceptarJB.setText("Aceptar");
		}
		
	    aceptarJB.addActionListener(new ActionListener(){
	    	public void actionPerformed(java.awt.event.ActionEvent evt) {
	    		if(aceptarJB.getText().equals("Aceptar")){
	    			InicioIanus.numeroNuevosDocModel = contadorNovedades;
	    		//	System.out.println(contadorNovedades);
		    		dispose();
	    		}
	    		else{
	    			avisos = InicioIanus.excel.getDatosNuevoDocumento(Integer.parseInt(listaNovedades.get(contadorNovedades)));
	    			if(!avisos.documentoNuevo.contains("N")){
	    				avisoJL.setText("¡ D O C U M E N T O     N U E V O !");
	    				panelG.setBackground(new java.awt.Color(204, 255, 204));
	    			}else{
	    				avisoJL.setText("¡ M O D E L O     N U E V O !");
	    				panelG.setBackground(new java.awt.Color(255, 204, 204));
	    			}
	    			nombreJL.setText(avisos.nombreDocumento);
	    			
	    			cadenaServicios = "";
	    			for(int z=0;z<avisos.serviciosDocumento.size();z++){
	    				cadenaServicios += avisos.serviciosDocumento.get(z);
	    				if(z != avisos.serviciosDocumento.size()-1){
	    					cadenaServicios += ", ";
	    				}
	    				else{
	    					cadenaServicios += ".";
	    				}
	    			}
	    			contenidoJTAServ.setText(cadenaServicios);
	    			
	    			contenidoJTAObs.setText("  " + avisos.observacionesDocumento);
	    	//		cadena = InicioIanus.excel.getFotoNovedad(Integer.parseInt(listaNovedades.get(contadorNovedades)));
	    			imagen = new ImageIcon("Imagenes\\250x350\\" + avisos.rutaJpg + ".jpg");
	    			fotoLabel.setIcon(imagen);
	    			contadorNovedades++;
	    			if(listaNovedades.get(contadorNovedades).toString().contains("N")){
	    				aceptarJB.setText("Aceptar");
	    			}
	    		}
	    	}
	    });
		
		panel.add(panelN, BorderLayout.NORTH);
		panel.add(panelG, BorderLayout.CENTER);
		panel.add(panelS, BorderLayout.SOUTH);
		panel.setBorder(BorderFactory.createLineBorder(Color.black));
		
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
					Visor v = new Visor(avisos.nombreDocumento, avisos.rutaJpg);
				}

			}

		});
	}	
	
	
/*
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ArrayList <String> arrayPrueba = new ArrayList <String>();
		for(int i=0;i<5;i++){
			arrayPrueba.add(String.valueOf(i));
		}
		new VentanaMensajeDocNuevo(arrayPrueba);
	}
*/
}
