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


public class VentanaMensajes extends JDialog{

	/**
	 * @param args
	 */
	
	JLabel avisoJL = new JLabel("¡ N O R M A     N U E V A !");
	JLabel fotoLabel = new JLabel();
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

	
	VentanaMensajes(final ArrayList<String> listaNovedades){
		
		setSize(870,490);
		
		this.setBackground(Color.pink);
	//	this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		panel.setLayout(new BorderLayout());
		panelG.setLayout(new GridBagLayout());
		panelG.setBackground(new java.awt.Color(255, 255, 204));
		
		avisoJL.setFont(new Font("Serif",Font.BOLD,28));
		avisoJL.setForeground(Color.DARK_GRAY);
		panelN.add(avisoJL);
		
		cadena = InicioIanus.excel.getFotoNovedad(Integer.parseInt(listaNovedades.get(0)));
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
		panelG.add(servicioJL,conf20);		
		
		GridBagConstraints conf21 = new GridBagConstraints();
		conf21.gridx = 2;
		conf21.gridy = 1;
		conf21.gridwidth = 2;
	//	conf21.gridheight = 2;
		conf21.fill = GridBagConstraints.BOTH;
		contenidoJTAServ.setLineWrap(true);
		contenidoJTAServ.setFont(new Font("Times",Font.BOLD,16));
		contenidoJTAServ.setEditable(false);
		contenidoJTAServ.setBackground(Color.white);
		contenidoJTAServ.setText("  " + InicioIanus.excel.getServicioNovedad(Integer.parseInt(listaNovedades.get(0))));
		scrollSer.setViewportView(contenidoJTAServ);
		panelG.add(scrollSer,conf21);
		
		GridBagConstraints conf23 = new GridBagConstraints();
		conf23.gridx = 2;
		conf23.gridy = 3;
		conf23.insets = new Insets(10,0,5,0);
		panelG.add(contenidoJL,conf23);
	
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
		contenidoJTAObs.setText("  " + InicioIanus.excel.getContenidoNovedad(Integer.parseInt(listaNovedades.get(0))));
		scrollObs.setViewportView(contenidoJTAObs);
		panelG.add(scrollObs,conf24);
		
		panelS.add(aceptarJB);
		if(listaNovedades.get(1).toString().contains("N")){
			aceptarJB.setText("Aceptar");
		}
		
	    aceptarJB.addActionListener(new ActionListener(){
	    	public void actionPerformed(java.awt.event.ActionEvent evt) {
	    		if(aceptarJB.getText().equals("Aceptar")){
	    			InicioIanus.numeroNuevasNormas = contadorNovedades;
	    		//	System.out.println(contadorNovedades);
		    		dispose();
	    		}
	    		else{
	    			contenidoJTAServ.setText("  " + InicioIanus.excel.getServicioNovedad(Integer.parseInt(listaNovedades.get(contadorNovedades))));
	    			contenidoJTAObs.setText("  " + InicioIanus.excel.getContenidoNovedad(Integer.parseInt(listaNovedades.get(contadorNovedades))));
	    			cadena = InicioIanus.excel.getFotoNovedad(Integer.parseInt(listaNovedades.get(contadorNovedades)));
	    			imagen = new ImageIcon("Imagenes\\250x350\\" + cadena);
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
