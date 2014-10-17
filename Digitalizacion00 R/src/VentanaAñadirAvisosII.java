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
import javax.swing.JComponent;
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


public class VentanaAñadirAvisosII extends JDialog{

	/**
	 * @param args
	 */
	
	JLabel avisoJL = new JLabel(" A Ñ A D I R   M E N S A J E ");
	JLabel fotoLabel = new JLabel();
	JLabel destinatariosJL = new JLabel("Para:                   ");
	JLabel contenidoJL = new JLabel("Mensaje:               ");
	
	JButton aceptarJB = new JButton("Aceptar");
	JButton cancelarJB = new JButton("Cancelar");
	JButton añadirImagenJB = new JButton("Añadir\n Imagen");
	JButton añadirDestinatarioJBdoc = new JButton("Documentación");
	JButton añadirDestinatarioJBurg = new JButton("Urgencias");
	JButton añadirMensaje = new JButton("Añadir Mensaje");
	
	JTextArea contenidoJTAUsuarios = new JTextArea("",3,30);
	JTextArea contenidoJTAMensaje = new JTextArea("",6,30);
	
	JList listaUsuariosDoc = new JList();
	JList listaUsuariosUrg = new JList();
	
	JPanel panel = new JPanel();
	JPanel panelG = new JPanel();
	JPanel panelN = new JPanel();
	JPanel panelS = new JPanel();
	
	static VisualizadorAvisos visual = new VisualizadorAvisos("Mensaje");

	
	JScrollPane scrollSer = new JScrollPane();
	//JScrollPane scrollObs = new JScrollPane();
	JScrollPane scrollListaDoc = new JScrollPane();
	JScrollPane scrollListaUrg = new JScrollPane();
	
	ImageIcon imagen;
	String nombreImagen = "Sin Imagen.jpg";
	
	String cadenaUsuariosUrg = "";
	String cadenaUsuariosDoc = "";

	String mensaje = "Escribe aquí el mensaje";
	
	int contadorNovedades = 1;
	
		
	Object[] itemsDoc;
	Object[] itemsUrg;
	
	VentanaAñadirAvisosII( /* final ArrayList<String> listaNovedades */ ){
		
		setSize(850,480);
	//	this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		panel.setLayout(new BorderLayout());
		panelG.setLayout(new GridBagLayout());
	//	panelG.setBackground(new Color(200,200,200));
		
		avisoJL.setFont(new Font("Serif",Font.BOLD,28));
		avisoJL.setForeground(Color.DARK_GRAY);
		panelN.add(avisoJL);
		
	//	urlImagen = this.getClass().getResource("cal\\Plantilla 01.jpg");
		
		InicioIanus.auxRutaImagen = nombreImagen;
		
		imagen = new ImageIcon("Imagenes\\250x350\\" + InicioIanus.auxRutaImagen);
		fotoLabel.setIcon(imagen);
		
		listaUsuariosDoc.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		
		GridBagConstraints conf00 = new GridBagConstraints();
		conf00.gridx = 0;
		conf00.gridy = 0;
		conf00.gridwidth = 2;
		conf00.gridheight = 5;
	//	conf00.weightx = 0.1;
		conf00.insets = new Insets(10,0,0,10);
		conf00.anchor = GridBagConstraints.EAST;
		panelG.add(fotoLabel,conf00);		

		GridBagConstraints conf30 = new GridBagConstraints();
		conf30.gridx = 3;
		conf30.gridy = 0;
		conf30.insets = new Insets(10,0,5,0);
	//	conf30.weightx = 0.9;
		panelG.add(destinatariosJL,conf30);		
		
		GridBagConstraints conf31 = new GridBagConstraints();
		conf31.gridx = 3;
		conf31.gridy = 1;
		conf31.gridwidth = 2;
	//	conf21.gridheight = 2;
		conf31.fill = GridBagConstraints.BOTH;
		contenidoJTAUsuarios.setLineWrap(true);
		contenidoJTAUsuarios.setText("Listado de usuarios");
		contenidoJTAUsuarios.setFont(new Font("Courier",Font.BOLD,16));
		contenidoJTAUsuarios.setEditable(false);
		contenidoJTAUsuarios.setBackground(Color.white);
	//	contenidoJTAUsuarios.setText("  " + InicioIanus.excel.getServicioNovedad(Integer.parseInt(listaNovedades.get(0))));
		scrollSer.setViewportView(contenidoJTAUsuarios);
		panelG.add(scrollSer,conf31);
		
		GridBagConstraints conf33 = new GridBagConstraints();
		conf33.gridx = 3;
		conf33.gridy = 3;
		conf33.insets = new Insets(10,0,5,0);
		panelG.add(contenidoJL,conf33);
	
		GridBagConstraints conf43 = new GridBagConstraints();
		conf43.gridx = 4;
		conf43.gridy = 3;
		conf43.gridwidth = 1;
		conf43.fill = GridBagConstraints.BOTH;
		//contenidoJTAMensaje.setLineWrap(true);
		//contenidoJTAMensaje.setText("");
		//scrollObs.setViewportView(contenidoJTAMensaje);
		panelG.add(añadirMensaje,conf43);
		
				
		GridBagConstraints conf34 = new GridBagConstraints();
		conf34.gridx = 3;
		conf34.gridy = 4;
		conf34.gridwidth = 2;
		conf34.fill = GridBagConstraints.BOTH;
		//contenidoJTAMensaje.setLineWrap(true);
		//contenidoJTAMensaje.setText("");
		//scrollObs.setViewportView(contenidoJTAMensaje);
		panelG.add(visual.panel,conf34);
		
		GridBagConstraints conf20 = new GridBagConstraints();
		conf20.gridx = 2;
		conf20.gridy = 0;
		conf20.gridwidth = 1;
		conf20.gridheight = 2;
		conf20.fill = GridBagConstraints.BOTH;
		conf20.anchor = GridBagConstraints.CENTER;
		conf20.insets = new Insets(10,0,0,15);
		DefaultListModel dLMdoc = new DefaultListModel();
		dLMdoc.removeAllElements();
		dLMdoc = InicioIanus.excel.listaUsuariosLista;
	//	dLM.remove(0);
		if(!InicioIanus.excel.listaUsuariosLista.get(InicioIanus.excel.listaUsuariosLista.size()-1).toString().contains("Todos")){
			dLMdoc.addElement("Todos");
		}
		if(InicioIanus.excel.listaUsuariosLista.get(0).toString().contains("Alias")){
			dLMdoc.remove(0);
		}
		listaUsuariosDoc.setModel(dLMdoc);
		scrollListaDoc.setViewportView(listaUsuariosDoc);
		panelG.add(scrollListaDoc,conf20);
		
		GridBagConstraints conf22 = new GridBagConstraints();
		conf22.gridx = 2;
		conf22.gridy = 2;
		conf22.insets = new Insets(0,0,0,15);
		panelG.add(añadirDestinatarioJBdoc,conf22);
		
		GridBagConstraints conf23 = new GridBagConstraints();
		conf23.gridx = 2;
		conf23.gridy = 3;
		conf23.gridwidth = 1;
		conf23.gridheight = 2;
		conf23.fill = GridBagConstraints.BOTH;
		conf23.anchor = GridBagConstraints.CENTER;
		conf23.insets = new Insets(10,0,0,15);
		DefaultListModel dLMurg = new DefaultListModel();
		dLMurg.removeAllElements();
		dLMurg = InicioIanus.excel.listaUsuariosListaUrg;
	//	dLM.remove(0);
		if(!InicioIanus.excel.listaUsuariosListaUrg.get(InicioIanus.excel.listaUsuariosListaUrg.size()-1).toString().contains("Todos")){
			dLMurg.addElement("Todos");
		}
		if(InicioIanus.excel.listaUsuariosListaUrg.get(0).toString().contains("Alias")){
			dLMurg.remove(0);
		}
		listaUsuariosUrg.setModel(dLMurg);
		scrollListaUrg.setViewportView(listaUsuariosUrg);
		panelG.add(scrollListaUrg,conf23);		
		
		GridBagConstraints conf25 = new GridBagConstraints();
		conf25.gridx = 2;
		conf25.gridy = 5;
		conf25.insets = new Insets(0,0,0,15);
		panelG.add(añadirDestinatarioJBurg,conf25);
		
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

	    añadirDestinatarioJBdoc.addActionListener(new ActionListener(){
	    	public void actionPerformed(java.awt.event.ActionEvent evt) {
	    		itemsDoc = listaUsuariosDoc.getSelectedValues();
	    		String cadena = "";
	    		for(int i = 0; i< itemsDoc.length;i++){
	    			if(itemsDoc[i].toString().contains("Todos")){
	    				cadena = "Todos";
	    				itemsDoc = new Object[1];
	    				itemsDoc[0] = "Todos";
	    				break;
	    			}else if(i != itemsDoc.length-1){
		    			cadena+= (itemsDoc[i].toString() + ", ");
	    			}else{
	    				cadena+= (itemsDoc[i].toString() + ". ");
	    			}

	    		}
	    		cadenaUsuariosDoc = cadena;

    			contenidoJTAUsuarios.setText(cadenaUsuariosDoc + "\n" + cadenaUsuariosUrg);
 
	    	}
	    });
	    
	    añadirDestinatarioJBurg.addActionListener(new ActionListener(){
	    	public void actionPerformed(java.awt.event.ActionEvent evt) {
	    		itemsUrg = listaUsuariosUrg.getSelectedValues();
	    		String cadena = "";
	    		for(int i = 0; i< itemsUrg.length;i++){
	    			if(itemsUrg[i].toString().contains("Todos")){
	    				cadena = "Todos";
	    				itemsUrg = new Object[1];
	    				itemsUrg[0] = "Todos";
	    				break;
	    			}else if(i != itemsUrg.length-1){
		    			cadena+= (itemsUrg[i].toString() + ", ");
	    			}else{
	    				cadena+= (itemsUrg[i].toString() + ". ");
	    			}

	    		}
	    		cadenaUsuariosUrg = cadena;
	    		
    			contenidoJTAUsuarios.setText(cadenaUsuariosDoc + "\n" + cadenaUsuariosUrg);
	    	}
	    });
	    
	    
	    añadirMensaje.addActionListener(new ActionListener(){
	    	public void actionPerformed(java.awt.event.ActionEvent evt) {
	    		EditorAvisos editor = new EditorAvisos(mensaje);
	    	}
	    });
	    
	    cancelarJB.addActionListener(new ActionListener(){
	    	public void actionPerformed(java.awt.event.ActionEvent evt) {
	    		dispose();
	    	}
	    });
	    
	    aceptarJB.addActionListener(new ActionListener(){
	    	public void actionPerformed(java.awt.event.ActionEvent evt) {

	         		new GuardarAvisos("Documentos.xls",contenidoJTAMensaje.getText(),InicioIanus.auxRutaImagen,itemsDoc,itemsUrg); 
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
