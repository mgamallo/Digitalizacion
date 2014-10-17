import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;


public class VentanaNormas extends JFrame{

	/**
	 * @param args
	 */
	JLabel servicioJL = new JLabel("Servicio:  ");
	JLabel contenidoJL = new JLabel("Observaciones: ");
	JLabel nada = new JLabel("          ");
	
	JButton aceptarJB = new JButton("Aceptar");
	JButton cancelarJB = new JButton("Cancelar");
	
	
	JList listaServicios = new JList();
	JComboBox servicioJCB = new JComboBox();
	DefaultComboBoxModel servicioJDCBM = new DefaultComboBoxModel();
	JTextArea contenidoJTA = new JTextArea("",4,30);
	
	JPanel panel0 = new JPanel();
	JPanel panel01 = new JPanel();

	JPanel panel1 = new JPanel();
	JPanel panel10 = new JPanel();

	JPanel panel2 = new JPanel();
	JScrollPane scroll = new JScrollPane();
	JScrollPane scrollLista = new JScrollPane();
	
	VentanaNormas(){
		this.setTitle("Añadir Observaciones");
		this.setSize(400,200);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				
		
		listaServicios.setModel(servicioJDCBM);
		listaServicios.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		Object[] seleccion = listaServicios.getSelectedValues();
	//	scrollLista.setViewportView(listaServicios);
		
		servicioJDCBM.removeAllElements();
		servicioJDCBM = InicioIanus.servicios;
		servicioJDCBM.insertElementAt("Todos",0);
		servicioJCB.setModel(servicioJDCBM);
		servicioJCB.setSelectedIndex(0);
		
		aceptarJB.addActionListener(new ActionListener(){			
        	public void actionPerformed(ActionEvent evento){
         	//	new GuardarNormas("Documentos.xls",servicioJCB.getSelectedItem().toString(),contenidoJTA.getText()); 
         		dispose();
        	}
        });
		
		
		cancelarJB.addActionListener(new ActionListener(){			
        	public void actionPerformed(ActionEvent evento){
        		dispose();       		
        	}
        });
		
		panel0.setLayout(new BorderLayout());
		panel0.add(servicioJL,BorderLayout.WEST);
		panel01.setLayout(new BorderLayout());
		panel01.add(servicioJCB,BorderLayout.WEST);
		panel0.add(panel01,BorderLayout.CENTER);
		panel0.add(nada,BorderLayout.EAST);
		
		setLayout(new BorderLayout());
		
		contenidoJTA.setLineWrap(true);
		scroll.setViewportView(contenidoJTA);
		panel1.setLayout(new BorderLayout());
		panel10.setLayout(new BorderLayout());
		panel10.add(contenidoJL,BorderLayout.EAST);
		panel1.add(panel10,BorderLayout.NORTH);
	//	panel1.add(contenidoJL,BorderLayout.NORTH);
		panel1.add(scroll);

		panel2.add(aceptarJB);
		panel2.add(cancelarJB);
		panel2.add(scrollLista);
		
		this.add(panel0,BorderLayout.NORTH);
		this.add(panel1,BorderLayout.CENTER);
		this.add(panel2,BorderLayout.SOUTH);
		
		pack();
		setLocationRelativeTo(null);
		this.setVisible(true);
		this.setResizable(false);
	}

	
/*
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new VentanaNormas();
	}
*/
}
