import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.filechooser.FileNameExtensionFilter;


public class VentanaModificarDocumentos extends JDialog{

	/**
	 * @param args
	 */
	DocumentoNuevo doc = new DocumentoNuevo();		//	Objeto con la estructura de datos de un nuevo documento
	
	JLabel avisoJL;
	JLabel fotoLabel = new JLabel();
	JButton nombreDocumentoJB = new JButton("Metadatos");
	JLabel servicioJL = new JLabel("Servicios a los que afecta:            ");
	JLabel contenidoJL = new JLabel("Observaciones:               ");
	
	JButton aceptarJB = new JButton("Aceptar");
	JButton cancelarJB = new JButton("Cancelar");
	JButton añadirImagenJB = new JButton("Modificar\n Imagen");
	JButton añadirServiciosJB = new JButton("Modificar Servicios");
	
	JTextField contenidoJTFNombre = new JTextField(17);
	JTextArea contenidoJTAServ = new JTextArea("",3,30);
	JTextArea contenidoJTAObs = new JTextArea("",4,30);
	
	JList listaServicios = new JList();
	
	JPanel panel = new JPanel();
	JPanel panelG = new JPanel();
	JPanel panelN = new JPanel();
	JPanel panelS = new JPanel();
	JPanel panelNombreDocumento = new JPanel();

	JScrollPane scrollSer = new JScrollPane();
	JScrollPane scrollObs = new JScrollPane();
	JScrollPane scrollLista = new JScrollPane();
	
	ImageIcon imagen;
	String nombreImagen = "Sin Imagen.jpg";

	int contadorNovedades = 1;
	
	Object[] serviciosAfectados;
	
	Boolean imagenCargada = false;
	Boolean serviciosSeleccionados = false;
	Boolean nombreCargado = false;
	
	DatosDocumentos datosDocumentoModificar;
	
	VentanaModificarDocumentos( final String tipoDeNovedad ){
		
		setSize(870,480);
	//	this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		
		panel.setLayout(new BorderLayout());
		panelG.setLayout(new GridBagLayout());
		
		if(tipoDeNovedad.contains("Documento")){
			avisoJL = new JLabel(" M O D I F I C A R   D O C U M E N T O ");
		}else{
			avisoJL = new JLabel(" M O D I F I C A R   M O D E L O  ");
		}
		avisoJL.setFont(new Font("Serif",Font.BOLD,28));
		avisoJL.setForeground(Color.DARK_GRAY);
		panelN.add(avisoJL);
		
		
		datosDocumentoModificar = new DatosDocumentos();
		datosDocumentoModificar = InicioIanus.excel.getDatosDocumentoModificar();
		
		doc.nombre = datosDocumentoModificar.nombreDocumento;
		doc.apariencia = datosDocumentoModificar.apariencia[0];
		doc.color = datosDocumentoModificar.apariencia[1];
		doc.metaDatos = datosDocumentoModificar.metaDatos;
		doc.nombreImagen = datosDocumentoModificar.rutaJpg;
		doc.observaciones = datosDocumentoModificar.observacionesDocumento;
		doc.serviciosAfectados = datosDocumentoModificar.serviciosDocumento;
		
		
		System.out.println(datosDocumentoModificar.nombreDocumento);
		
	//	urlImagen = this.getClass().getResource("cal\\Plantilla 01.jpg");
		
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
		panelG.add(fotoLabel,conf00);		

		GridBagConstraints conf30 = new GridBagConstraints();
		conf30.gridx = 3;
		conf30.gridy = 0;
		conf30.insets = new Insets(10,0,5,0);
		conf30.anchor = GridBagConstraints.WEST;
		panelNombreDocumento.setLayout(new FlowLayout());
		nombreDocumentoJB.setForeground(Color.red);
		panelNombreDocumento.add(nombreDocumentoJB);
		contenidoJTFNombre.setEditable(false);
		contenidoJTFNombre.setBackground(Color.white);
		contenidoJTFNombre.setText(datosDocumentoModificar.nombreDocumento);
		panelNombreDocumento.add(contenidoJTFNombre);
	//	conf30.weightx = 0.9;
		panelG.add(panelNombreDocumento,conf30);		
		
		GridBagConstraints conf31 = new GridBagConstraints();
		conf31.gridx = 3;
		conf31.gridy = 1;
		conf31.insets = new Insets(10,0,5,0);
		conf31.anchor = GridBagConstraints.SOUTHWEST;
		panelG.add(servicioJL,conf31);
		
		GridBagConstraints conf32 = new GridBagConstraints();
		conf32.gridx = 3;
		conf32.gridy = 2;
		conf32.fill = GridBagConstraints.BOTH;
		conf32.gridwidth = 2;
		contenidoJTAServ.setLineWrap(true);
		String listaDeServicios ="";
		for(int j=0;j<datosDocumentoModificar.serviciosDocumento.size();j++){
			if(j!= datosDocumentoModificar.serviciosDocumento.size()-1){
				listaDeServicios += datosDocumentoModificar.serviciosDocumento.get(j) + ", ";
			}
			else{
				listaDeServicios += datosDocumentoModificar.serviciosDocumento.get(j) + ".";
			}
		}
		contenidoJTAServ.setText(listaDeServicios);
		contenidoJTAServ.setEditable(false);
		contenidoJTAServ.setBackground(Color.white);
		scrollSer.setViewportView(contenidoJTAServ);
		panelG.add(scrollSer,conf32);
		
		
		
		GridBagConstraints conf33 = new GridBagConstraints();
		conf33.gridx = 3;
		conf33.gridy = 3;
		conf33.insets = new Insets(10,0,5,0);
		conf33.anchor = GridBagConstraints.SOUTHWEST;
		panelG.add(contenidoJL,conf33);
		
		
	
		GridBagConstraints conf34 = new GridBagConstraints();
		conf34.gridx = 3;
		conf34.gridy = 4;
		conf34.fill = GridBagConstraints.BOTH;
		contenidoJTAObs.setLineWrap(true);
		contenidoJTAObs.setText(datosDocumentoModificar.observacionesDocumento);
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

		aceptarJB.setEnabled(false);
		
		GridBagConstraints conf25 = new GridBagConstraints();
		conf25.gridx = 2;
		conf25.gridy = 5;
		conf25.insets = new Insets(0,0,0,15);
		panelG.add(añadirServiciosJB,conf25);
		

		
	    añadirImagenJB.addActionListener(new ActionListener(){
	    	public void actionPerformed(java.awt.event.ActionEvent evt) {
	    		
	    		String ruta =InicioIanus.RUTA; 
	    		String rutab =InicioIanus.RUTAB;	
	    		if(!(new File(ruta).exists()))
	    			ruta = rutab;
	    		
	    		int aux = ruta.lastIndexOf("/");
	    		ruta = ruta.substring(0,aux);
	    		ruta += "/05 Documentos Nuevos/00 Imagenes";

	    		
	    		JFileChooser explorador = new JFileChooser(ruta);
	    		explorador.setDialogTitle("Abrir Imagen...");
	    		explorador.setCurrentDirectory(new File(ruta));
	    		explorador.setFileFilter(new FileNameExtensionFilter("Imagenes jpg","jpg"));
	    		int seleccion = explorador.showOpenDialog(null);
	    		if( seleccion == JFileChooser.APPROVE_OPTION){	
	    			File archivo = explorador.getSelectedFile();
	    			new Redimension(archivo.getName());
	    			imagenCargada = true;
	    			if(imagenCargada || serviciosSeleccionados || nombreCargado){
	    				aceptarJB.setEnabled(true);
	    			}
	    		}

	    		if(!InicioIanus.auxRutaImagen.isEmpty()){
	    			aux = ruta.lastIndexOf("/");
	    			ruta = ruta.substring(0,aux);
	    			aux = ruta.lastIndexOf("/");
	    			ruta = ruta.substring(0,aux);
	    			System.out.println(ruta);
		    		imagen = new ImageIcon(ruta + "/99 Nombres Normalizados/Imagenes/250x350/" + InicioIanus.auxRutaImagen);
		    		imagen.getImage().flush();
		    		fotoLabel.setIcon(imagen);
		    		fotoLabel.setBorder(BorderFactory.createLineBorder(Color.black));
		    		doc.nombreImagen = InicioIanus.auxRutaImagen;
	    		}

	    	}
	    });

	    añadirServiciosJB.addActionListener(new ActionListener(){
	    	public void actionPerformed(java.awt.event.ActionEvent evt) {
	    		serviciosAfectados = listaServicios.getSelectedValues();
	    		String cadena = "";
	    		for(int i = 0; i< serviciosAfectados.length;i++){
	    			doc.serviciosAfectados.add(serviciosAfectados[i].toString());
	    			cadena+= (serviciosAfectados[i].toString() + ", ");
	    		}
    			contenidoJTAServ.setText(cadena);
    			if(!cadena.isEmpty()){
    				serviciosSeleccionados = true;
    			}

    			
    			if(imagenCargada || serviciosSeleccionados || nombreCargado){
    				aceptarJB.setEnabled(true);
    			}
	    	}
	    });
	    
	    nombreDocumentoJB.addActionListener(new ActionListener(){
	    	public void actionPerformed(java.awt.event.ActionEvent evt) {
	    		AñadirMetadatos meta = new AñadirMetadatos(tipoDeNovedad, true, datosDocumentoModificar);
	    			
    			contenidoJTFNombre.setText(meta.nombreDocumento);
    			doc.nombre = meta.nombreDocumento;
    			doc.apariencia = meta.apariencia;
    			doc.color = meta.color;
    			doc.metaDatos.addAll(meta.listaMetadatos);
    			
    			if(!meta.nombreDocumento.isEmpty())
    				nombreCargado = true;
    			
    			if(imagenCargada || serviciosSeleccionados || meta.grabarDatos){
    				aceptarJB.setEnabled(true);
    			}
	    	}
	    });	    
	    
	    
	    cancelarJB.addActionListener(new ActionListener(){
	    	public void actionPerformed(java.awt.event.ActionEvent evt) {
	    		dispose();
	    	}
	    });
	    
	    aceptarJB.addActionListener(new ActionListener(){
	    	public void actionPerformed(java.awt.event.ActionEvent evt) {
         	//	new GuardarNormas("Documentos.xls",contenidoJTAObs.getText(),InicioIanus.auxRutaImagen,serviciosAfectados); 
	    		if(contenidoJTFNombre.getText().isEmpty()){
	    			JOptionPane.showMessageDialog(aceptarJB,"Falta el nombre del documento");
	    		}else{
	    			doc.observaciones = contenidoJTAObs.getText();
//	    			doc.imprimirDatos();
	    			new GuardarDocumentosNuevos("Documentos.xls", doc, tipoDeNovedad);
	           		dispose();
	    		}
  
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
