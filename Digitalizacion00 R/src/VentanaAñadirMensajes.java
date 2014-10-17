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


public class VentanaAñadirMensajes extends JDialog{

	/**
	 * @param args
	 */
	
	JLabel avisoJL = new JLabel(" A Ñ A D I R   N O R M A ");
	JLabel fotoLabel = new JLabel();
	JLabel servicioJL = new JLabel("Servicios a los que afecta:  ");
	JLabel contenidoJL = new JLabel("Observaciones:               ");
	
	JButton aceptarJB = new JButton("Aceptar");
	JButton cancelarJB = new JButton("Cancelar");
	JButton añadirImagenJB = new JButton("Añadir\n Imagen");
	JButton añadirServiciosJB = new JButton("Añadir Servicios");
	
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
	
	JComboBox tipoUsuarioJCB = new JComboBox();
	
	ImageIcon imagen;
	String nombreImagen = "Sin Imagen.jpg";

	int contadorNovedades = 1;
	
	Object[] items;
	
	VentanaAñadirMensajes( /* final ArrayList<String> listaNovedades */ ){
		
		setSize(850,500);
	//	this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		panel.setLayout(new BorderLayout());
		panelG.setLayout(new GridBagLayout());
		
		avisoJL.setFont(new Font("Serif",Font.BOLD,28));
		avisoJL.setForeground(Color.DARK_GRAY);
		panelN.add(avisoJL);
		
	//	urlImagen = this.getClass().getResource("cal\\Plantilla 01.jpg");
		
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

		tipoUsuarioJCB.addItem("Documentacion");
		tipoUsuarioJCB.addItem("Urgencias");
		tipoUsuarioJCB.addItem("Todos");
		
		
		GridBagConstraints conf30 = new GridBagConstraints();
		conf30.gridx = 3;
		conf30.gridy = 0;
		conf30.insets = new Insets(10,0,5,0);
	//	conf30.weightx = 0.9;
		panelG.add(tipoUsuarioJCB,conf30);		
		
		GridBagConstraints conf31 = new GridBagConstraints();
		conf31.gridx = 3;
		conf31.gridy = 1;
		conf31.gridwidth = 2;
	//	conf21.gridheight = 2;
		conf31.fill = GridBagConstraints.BOTH;
		contenidoJTAServ.setLineWrap(true);
		contenidoJTAServ.setText("Listado de servicios");
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
		contenidoJTAObs.setText("");
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
		panelS.add(cancelarJB);
		panelS.add(aceptarJB);
		panelG.add(panelS,conf35);

		
		GridBagConstraints conf25 = new GridBagConstraints();
		conf25.gridx = 2;
		conf25.gridy = 5;
		conf25.insets = new Insets(0,0,0,15);
		panelG.add(añadirServiciosJB,conf25);
		

		
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

	    añadirServiciosJB.addActionListener(new ActionListener(){
	    	public void actionPerformed(java.awt.event.ActionEvent evt) {
	    		items = listaServicios.getSelectedValues();
	    		String cadena = "";
	    		for(int i = 0; i< items.length;i++){
	    			cadena+= (items[i].toString() + ", ");
	    		}
    			contenidoJTAServ.setText(cadena);
	    	}
	    });
	    
	    cancelarJB.addActionListener(new ActionListener(){
	    	public void actionPerformed(java.awt.event.ActionEvent evt) {
	    		dispose();
	    	}
	    });
	    
	    aceptarJB.addActionListener(new ActionListener(){
	    	public void actionPerformed(java.awt.event.ActionEvent evt) {
         		new GuardarNormas("Documentos.xls",contenidoJTAObs.getText(),InicioIanus.auxRutaImagen,items); 
         		dispose();
	    	}
	    });

		panel.add(panelN, BorderLayout.NORTH);
		panel.add(panelG, BorderLayout.CENTER);
		
		this.setModal(false);
		setContentPane(panel);
		this.setLocationRelativeTo(null);
		setVisible(true);

	}

	
/*
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new VentanaAñadirMensajes();
	}
*/
}
