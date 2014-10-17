import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;


public class VentanaModificarMensajes extends JDialog{

	/**
	 * @param args
	 */
	
	JLabel avisoJL = new JLabel("C O N S U L T A R / M O D I F I C A R     N O R M A S");
	JLabel fotoLabel = new JLabel();
	JLabel servicioJL = new JLabel("Servicios a los que afecta:  ");
	JLabel contenidoJL = new JLabel("Observaciones:               ");
	
	JButton aceptarJB = new JButton("Aceptar");
	JButton cancelarJB = new JButton("Cancelar");
	JButton añadirImagenJB = new JButton("Modificar\n Imagen");
	JButton añadirServiciosJB = new JButton("Modificar Servicios");
	JButton modificarNormaJB = new JButton("Modificar");
	
	JTextArea contenidoJTAServ = new JTextArea("",3,30);
	JTextArea contenidoJTAObs = new JTextArea("",6,30);
	
	JList listaServicios = new JList();
	
	JPanel panel = new JPanel();
	JPanel panelFoto = new JPanel();
	JPanel panelG = new JPanel();
	JPanel panelN = new JPanel();
	JPanel panelS = new JPanel();

	JScrollPane scrollSer = new JScrollPane();
	JScrollPane scrollObs = new JScrollPane();
	JScrollPane scrollLista = new JScrollPane();
	
	ImageIcon imagen;
	String nombreImagen;

	int contadorNovedades = 1;
	
	Object[] items;
	
	VentanaModificarMensajes( final int fila ){
		
		System.out.println(fila);
		
		setSize(890,500);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		panel.setLayout(new BorderLayout());
		panelG.setLayout(new GridBagLayout());
		
		avisoJL.setFont(new Font("Serif",Font.BOLD,28));
		avisoJL.setForeground(Color.DARK_GRAY);
		panelN.add(avisoJL);
		
		nombreImagen = InicioIanus.excel.contenidoNovedades[fila][2].toString();
		InicioIanus.auxRutaImagen = nombreImagen;
		
		imagen = new ImageIcon("Imagenes\\250x350\\" + InicioIanus.auxRutaImagen);
		fotoLabel.setIcon(imagen);
		
		listaServicios.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		
		
		GridBagConstraints conf00 = new GridBagConstraints();
		conf00.gridx = 0;
		conf00.gridy = 0;
		conf00.gridwidth = 2;
		conf00.gridheight = 5;
	//	conf00.weightx = 0.1;
		conf00.insets = new Insets(10,0,0,10);
		conf00.anchor = GridBagConstraints.EAST;
		panelFoto.add(fotoLabel);
		panelG.add(panelFoto,conf00);		

		GridBagConstraints conf30 = new GridBagConstraints();
		conf30.gridx = 3;
		conf30.gridy = 0;
		conf30.insets = new Insets(10,0,5,0);
	//	conf30.weightx = 0.9;
		panelG.add(servicioJL,conf30);		
		
		GridBagConstraints conf31 = new GridBagConstraints();
		conf31.gridx = 3;
		conf31.gridy = 1;
		conf31.gridwidth = 2;
	//	conf21.gridheight = 2;
		conf31.fill = GridBagConstraints.BOTH;
		contenidoJTAServ.setLineWrap(true);
		contenidoJTAServ.setText(InicioIanus.excel.getServicioNovedad(fila+1));
		contenidoJTAServ.setFont(new Font("Courier",Font.BOLD,16));
		contenidoJTAServ.setEditable(false);
		contenidoJTAServ.setBackground(Color.white);
	//	contenidoJTAServ.setText("  " + InicioIanus.excel.getServicioNovedad(Integer.parseInt(listaNovedades.get(0))));
		scrollSer.setViewportView(contenidoJTAServ);
		panelG.add(scrollSer,conf31);
		
		GridBagConstraints conf33 = new GridBagConstraints();
		conf33.gridx = 3;
		conf33.gridy = 3;
		conf33.insets = new Insets(10,0,5,0);
		panelG.add(contenidoJL,conf33);
	
		GridBagConstraints conf34 = new GridBagConstraints();
		conf34.gridx = 3;
		conf34.gridy = 4;
		conf34.gridwidth = 2;
		conf34.fill = GridBagConstraints.BOTH;
		contenidoJTAObs.setLineWrap(true);
		contenidoJTAObs.setText(InicioIanus.excel.contenidoNovedades[fila][1]);
		contenidoJTAObs.setEditable(false);
		scrollObs.setViewportView(contenidoJTAObs);
		panelG.add(scrollObs,conf34);
		
		GridBagConstraints conf21 = new GridBagConstraints();
		conf21.gridx = 2;
		conf21.gridy = 0;
		conf21.gridwidth = 1;
		conf21.gridheight = 5;
		conf21.fill = GridBagConstraints.BOTH;
		conf21.anchor = GridBagConstraints.CENTER;
		conf21.insets = new Insets(10,0,0,15);
		DefaultListModel dLM = new DefaultListModel();
		dLM.removeAllElements();
		dLM = InicioIanus.excel.listaServiciosLista;
		if(!InicioIanus.excel.listaServiciosLista.get(InicioIanus.excel.listaServiciosLista.size()-1).toString().contains("Todos")){
			dLM.addElement("Todos");
		}

		listaServicios.setModel(dLM);
		scrollLista.setViewportView(listaServicios);
		panelG.add(scrollLista,conf21);
		
		GridBagConstraints conf05 = new GridBagConstraints();
		conf05.gridx = 0;
		conf05.gridy = 5;
		conf05.gridwidth = 2;
		conf05.anchor = GridBagConstraints.CENTER;
//		conf05.insets = new Insets(10,0,0,5);
		panelG.add(añadirImagenJB,conf05);	
		
		
		GridBagConstraints conf35 = new GridBagConstraints();
		conf35.gridx = 3;
		conf35.gridy = 5;
		conf35.gridwidth = 2;
		panelS.add(modificarNormaJB);
		panelS.add(cancelarJB);
		panelS.add(aceptarJB);
		panelG.add(panelS,conf35);

		
		GridBagConstraints conf25 = new GridBagConstraints();
		conf25.gridx = 2;
		conf25.gridy = 5;
		conf25.insets = new Insets(0,0,0,15);
		panelG.add(añadirServiciosJB,conf25);
		

		añadirImagenJB.setEnabled(false);
	    añadirImagenJB.addActionListener(new ActionListener(){
	    	public void actionPerformed(java.awt.event.ActionEvent evt) {
	    		int seleccion = JOptionPane.showOptionDialog(añadirImagenJB, "¿Elegir imagen por...?", "Elegir imagen", 
	    				JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, 
	    				new Object[]{"Nombre", "Palabra clave", "Cancelar"},"Cancelar");
	    		if(seleccion == 1){
		    		new InterfazVisorMetaAñadir();
	    		}else if(seleccion == 0){
	    			new InterfazVisorNomDocAñadir();
	    		}

	    		if(!InicioIanus.auxRutaImagen.isEmpty()){
		    		imagen = new ImageIcon("Imagenes\\250x350\\" + InicioIanus.auxRutaImagen);
		    		imagen.getImage().flush();
		    		fotoLabel.setIcon(imagen);
		    		fotoLabel.setBorder(BorderFactory.createLineBorder(Color.black));
	    		}
	    	}
	    });

	    añadirServiciosJB.setEnabled(false);
	    añadirServiciosJB.addActionListener(new ActionListener(){
	    	public void actionPerformed(java.awt.event.ActionEvent evt) {
	    		añadirServicios();
	    	}
	    });
	    
	    modificarNormaJB.addActionListener(new ActionListener(){
	    	public void actionPerformed(java.awt.event.ActionEvent evt) {
	    		aceptarJB.setEnabled(true);
	    		añadirImagenJB.setEnabled(true);
	    		añadirServiciosJB.setEnabled(true);
	    		contenidoJTAObs.setEditable(true);
	    		modificarNormaJB.setEnabled(false);
	    	}
	    });
	    
	    cancelarJB.addActionListener(new ActionListener(){
	    	public void actionPerformed(java.awt.event.ActionEvent evt) {
	    		dispose();
	    	}
	    });
	    
	    aceptarJB.setEnabled(false);
	    aceptarJB.addActionListener(new ActionListener(){
	    	public void actionPerformed(java.awt.event.ActionEvent evt) {
         		new GuardarNormas("Documentos.xls",contenidoJTAObs.getText(),InicioIanus.auxRutaImagen,items,fila + 1); 
         		dispose();
	    	}
	    });

		panel.add(panelN, BorderLayout.NORTH);
		panel.add(panelG, BorderLayout.CENTER);
		
		this.setModal(true);
		setContentPane(panel);
		this.setLocationRelativeTo(null);
		setVisible(true);

	}

	void añadirServicios(){
		items = listaServicios.getSelectedValues();
		String cadena = "";
		for(int i = 0; i< items.length;i++){
			cadena+= (items[i].toString() + ", ");
		}
		contenidoJTAServ.setText(cadena);
	}
	

}
