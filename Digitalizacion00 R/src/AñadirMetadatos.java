import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;


public class AñadirMetadatos extends JDialog {

	/**
	 * @param args
	 */
	
	ArrayList<String> listaMetadatos = new ArrayList<String>();
	String nombreDocumento = "";
	JComboBox JCBnombreDocumento = new JComboBox();
	String apariencia = "";
	String color = "";
	
	JPanel panelN = new JPanel();
	JPanel panel = new JPanel();
	JPanel panelC = new JPanel();
	JPanel panelCs = new JPanel();
	JPanel panelS = new JPanel();
	
	JButton JBAceptar = new JButton("Aceptar");
	JButton JBCancelar = new JButton("Cancelar");
	
	JLabel JLnombreDoc = new JLabel("Nombre del Documento:    ");
	JTextField JTFnombreDoc = new JTextField(20);
	
	JLabel JLmetadatos = new JLabel("Metadatos");
	JTextField	JTFmeta01 = new JTextField(15);
	JTextField	JTFmeta02 = new JTextField(15);
	JTextField	JTFmeta03 = new JTextField(15);
	JTextField	JTFmeta04 = new JTextField(15);
	JTextField	JTFmeta05 = new JTextField(15);
	
	JLabel JLapariencia = new JLabel("Apariencia");
	JComboBox JCBapariencia = new JComboBox();
	JLabel JLcolor = new JLabel("Color del documento");
	JTextField JTFcolor = new JTextField(15);
	
	boolean grabarDatos = false;
	
	
	Border borde = BorderFactory.createLineBorder(Color.gray);
	
	AñadirMetadatos(final String tipoDeNovedad, final boolean modificar, final DatosDocumentos dd){
		this.setSize(400, 300);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		panel.setLayout(new BorderLayout());
		panelC.setLayout(new GridBagLayout());
		panelCs.setLayout(new GridBagLayout());
		
		panel.add(panelN,BorderLayout.NORTH);
		panelN.add(JLnombreDoc);
		

		
		if(tipoDeNovedad.contains("Modelo") || modificar){
		    JCBnombreDocumento.setModel(InicioIanus.excel.listaDocumentosCombo);
		    JCBnombreDocumento.setMaximumRowCount(10);
		    if(modificar){
			    JCBnombreDocumento.setSelectedItem(dd.nombreDocumento);
		    }else{
			    JCBnombreDocumento.setSelectedIndex(0);
		    }
			panelN.add(JCBnombreDocumento);
		}else{
			panelN.add(JTFnombreDoc);
		}

		panelN.setBorder(borde);
	//	panelN.setBackground(Color.yellow);
		
		
		panel.add(panelC,BorderLayout.CENTER);
		
		GridBagConstraints conf00 = new GridBagConstraints();
		conf00.gridx = 0;
		conf00.gridy = 0;
	//	conf00.gridwidth = 2;
	//	conf00.gridheight = 5;
	//	conf00.weightx = 0.1;
	//	conf32.fill = GridBagConstraints.BOTH;
		conf00.insets = new Insets(10,0,0,10);
	//	conf00.anchor = GridBagConstraints.WEST;
		panelC.add(JLmetadatos,conf00);	
		
		GridBagConstraints conf10 = new GridBagConstraints();
		conf10.gridx = 1;
		conf10.gridy = 0;
		conf10.insets = new Insets(10,0,0,10);
		conf10.fill = GridBagConstraints.BOTH;
		if(modificar && (dd.metaDatos.size()>0)){
			JTFmeta01.setText(dd.metaDatos.get(0));
		}
		panelC.add(JTFmeta01,conf10);
		
		GridBagConstraints conf01 = new GridBagConstraints();
		conf01.gridx = 0;
		conf01.gridy = 1;
		conf01.insets = new Insets(10,0,0,10);
		conf01.fill = GridBagConstraints.BOTH;
		if(modificar && (dd.metaDatos.size()>1)){
			JTFmeta02.setText(dd.metaDatos.get(1));
		}
		panelC.add(JTFmeta02,conf01);		

		GridBagConstraints conf11 = new GridBagConstraints();
		conf11.gridx = 1;
		conf11.gridy = 1;
		conf11.insets = new Insets(10,0,0,10);
		conf11.fill = GridBagConstraints.BOTH;
		if(modificar && (dd.metaDatos.size()>2)){
			JTFmeta03.setText(dd.metaDatos.get(2));
		}
		panelC.add(JTFmeta03,conf11);		
		
		GridBagConstraints conf02 = new GridBagConstraints();
		conf02.gridx = 0;
		conf02.gridy = 2;
		conf02.insets = new Insets(10,0,0,10);
		conf02.fill = GridBagConstraints.BOTH;
		if(modificar && (dd.metaDatos.size()>3)){
			JTFmeta04.setText(dd.metaDatos.get(3));
		}
		panelC.add(JTFmeta04,conf02);		

		GridBagConstraints conf12 = new GridBagConstraints();
		conf12.gridx = 1;
		conf12.gridy = 2;
		conf12.insets = new Insets(10,0,0,10);
		conf12.fill = GridBagConstraints.BOTH;
		if(modificar && (dd.metaDatos.size()>4)){
			JTFmeta05.setText(dd.metaDatos.get(4));
		}
		panelC.add(JTFmeta05,conf12);	
		panelC.setBackground(Color.pink);
		
		GridBagConstraints conf03 = new GridBagConstraints();
		conf03.gridx = 0;
		conf03.gridy = 3;
		conf03.gridwidth = 2;
		conf03.gridheight = 2;
		conf03.insets = new Insets(10,0,0,10);
		conf03.fill = GridBagConstraints.BOTH;
		JLapariencia.setForeground(Color.black);
		JLcolor.setForeground(Color.black);
		panelCs.add(JLapariencia,conf00);
		
	    JCBapariencia.setModel(this.listaApariencia());
	    JCBapariencia.setMaximumRowCount(10);
		if(modificar){
			for(int j=0;j<this.listaApariencia().getSize();j++){
				JCBapariencia.setSelectedIndex(j);
			}

			JCBapariencia.setSelectedItem(dd.apariencia[0]);
		}else{
		    JCBapariencia.setSelectedIndex(0);
		}
		panelCs.add(JCBapariencia,conf10);
		panelCs.add(JLcolor,conf01);
		JTFcolor.setText("Ninguno");
		if(modificar){
			JTFcolor.setText(dd.apariencia[1]);
		}
		panelCs.add(JTFcolor,conf11);
		panelCs.setBorder(borde);
		panelC.add(panelCs,conf03);	
		panelCs.setBackground(new Color(100,200,100));
		
		GridBagConstraints conf04 = new GridBagConstraints();
		conf04.gridx = 0;
		conf04.gridy = 4;
		conf04.insets = new Insets(10,0,0,10);
		conf04.fill = GridBagConstraints.BOTH;

		
		panel.add(panelS, BorderLayout.SOUTH);
		panelS.add(JBCancelar);
		panelS.add(JBAceptar);
		
				
		JCBapariencia.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		
		
		JBCancelar.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(!modificar){
					listaMetadatos.clear();
					nombreDocumento = "";
					apariencia = "";
					color = "";
				}else{
					listaMetadatos.addAll(dd.metaDatos);
					nombreDocumento = dd.nombreDocumento;
					apariencia = dd.apariencia[0];
					color = dd.apariencia[1];
					
				}
				
				dispose();
			}
			
		});
		
		JBAceptar.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				listaMetadatos.add(JTFmeta01.getText());
				listaMetadatos.add(JTFmeta02.getText());
				listaMetadatos.add(JTFmeta03.getText());
				listaMetadatos.add(JTFmeta04.getText());
				listaMetadatos.add(JTFmeta05.getText());
				if(tipoDeNovedad.contains("Documento")){
					nombreDocumento = JTFnombreDoc.getText();
				}else{
					nombreDocumento = JCBnombreDocumento.getSelectedItem().toString();
				}

				apariencia = JCBapariencia.getSelectedItem().toString();
				color = JTFcolor.getText();
				grabarDatos = true;
				
				dispose();
			}
			
		});
		
		
		
		this.setLocationRelativeTo(null);
	//	this.setResizable(false);
		this.setContentPane(panel);
		this.setModal(true);
		this.setVisible(true);
	}

	
	//	Método para cargar la lista de graficos, fotos, cuadros...
	DefaultComboBoxModel listaApariencia(){
		DefaultComboBoxModel aparienciaDCBM = new DefaultComboBoxModel();
		int numFilas = InicioIanus.excel.tablaVisor.length;

		ArrayList<String> apariencias = new ArrayList();
		for(int i=1;i<numFilas;i++){
				apariencias.add(InicioIanus.excel.tablaVisor[i][4].toString());
		}

		HashSet<String> quitaDuplicados = new HashSet<String>();
		quitaDuplicados.addAll(apariencias);
		apariencias.clear();
		apariencias.addAll(quitaDuplicados);
		apariencias.remove("N");
		apariencias.remove("");
		Collections.sort(apariencias,String.CASE_INSENSITIVE_ORDER);
		
		numFilas = apariencias.size();
		for(int i=0;i<numFilas;i++)
			aparienciaDCBM.addElement(apariencias.get(i));
		aparienciaDCBM.insertElementAt("Ninguna", 0);
		return aparienciaDCBM;
	}

	
	
/*	
	static public void main(String arg[]){
		new AñadirMetadatos();
	}
	*/
}
