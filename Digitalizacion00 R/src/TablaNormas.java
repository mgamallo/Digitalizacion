import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;


public class TablaNormas extends JFrame{

	/**
	 * @param args
	 */
	
	JPanel panel = new JPanel();
	JPanel panelUp = new JPanel();
	JPanel panelUpIz = new JPanel();
	JPanel panelCenter = new JPanel();
	
	JButton buscarJB = new JButton("Buscar contenido");
	JButton limpiarJB = new JButton("Borrar Texto");
	JTextField textoAbuscarJTF = new JTextField(15);
	
	JScrollPane scroll;
	
	JLabel serviciosJL = new JLabel("SERVICIOS:   ");
	JLabel informacionJL = new JLabel("         Pulsa 2 veces para consultar o modificar una norma en concreto");
	JComboBox JCBservicios = new JComboBox();
		
	JTable tabla;
	MyTableModel modelo;
	Object[] filaObjetos = new Object[]{"Fecha","Contenido","Servicios"};
	Object[][] datos;
	
	Object[] fila = new Object[3];
	
	int numeroFilas = 0;
	ArrayList<Integer> filasMostradas = new ArrayList<Integer>();
	
	TablaNormas(){
		setTitle("Normas");
		setSize(880,400);
		this.setResizable(false);
		
		panel.setLayout(new BorderLayout());
		panelUp.setLayout(new BorderLayout());
		panelUpIz.setLayout(new FlowLayout());
		panelUpIz.add(serviciosJL);
		panelUpIz.add(JCBservicios);
		panelUpIz.setBackground(new java.awt.Color(250, 200, 224));
		panelUpIz.setBorder(BorderFactory.createLineBorder(Color.black));
		panelUp.setBackground(Color.white);
		panelUp.add(informacionJL,BorderLayout.CENTER);
		panelUp.add(panelUpIz,BorderLayout.WEST);
				
		panelCenter.setLayout(new BorderLayout());
		panelCenter.add(buscarJB,BorderLayout.EAST);
		panelCenter.add(textoAbuscarJTF,BorderLayout.CENTER);
		panelCenter.add(limpiarJB,BorderLayout.WEST);
		panel.add(panelCenter,BorderLayout.SOUTH);
		panel.add(panelUp,BorderLayout.NORTH);
		
		buscarJB.setBackground(Color.green);
		buscarJB.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
				buscarEnTabla();
			}
		});
		
		textoAbuscarJTF.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				buscarEnTabla();
			}
			
		});
		
		limpiarJB.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				textoAbuscarJTF.setText("");
			}
		});

		
		int j = InicioIanus.excel.getServicios().length;
		String[] itemNuevo = new String[j];
		itemNuevo = InicioIanus.excel.getServicios();
		for(int i=0;i<j;i++){
	            JCBservicios.addItem(itemNuevo[i]);
        }
		JCBservicios.insertItemAt("Todos",0);
		JCBservicios.setSelectedIndex(0);
		JCBservicios.setBackground(Color.white);
		JCBservicios.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				refrescarTabla();

			}
			
		});
		
		serviciosJL.setFont(new Font("Times",Font.BOLD,16));
		informacionJL.setFont(new Font("Times",Font.BOLD,14));
		
		modelo = new MyTableModel(datos,filaObjetos);
		tabla = new JTable(modelo);
		tabla.setRowHeight(30);
	//	tabla.setCellSelectionEnabled(true);
		tabla.setEnabled(true);
		EventoMouseClicked(tabla);
		
		TableColumn anchoColumnaFecha = tabla.getColumn("Fecha");
		anchoColumnaFecha.setMaxWidth(100);
		tabla.setBackground(new java.awt.Color(255, 255, 204));
		tabla.setFont(new Font("Times",Font.BOLD,12));
		
		TableColumn anchoColumnaContenido = tabla.getColumn("Contenido");
//		anchoColumnaContenido.setMaxWidth(500);
		anchoColumnaContenido.setMinWidth(600);
		scroll = new JScrollPane(tabla);
		
		TableColumn anchoColumnaServicios = tabla.getColumn("Servicios");
		anchoColumnaServicios.setMinWidth(120);
		anchoColumnaServicios.setMaxWidth(120);

		int tam = InicioIanus.excel.contenidoNovedades.length;
		for(int i=0;i<tam;i++){
			modelo.addRow(this.getCamposNormas(i));
			filasMostradas.add(i);
		}
		numeroFilas = modelo.getRowCount();
		
		panel.add(scroll,BorderLayout.CENTER);
		
		setContentPane(panel);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
	}
	
	class MyTableModel extends DefaultTableModel{
		public MyTableModel(Object[][] data, Object[] columnNames){
			super(data,columnNames);
		}
		
		public boolean isCellEditable(int row, int column){
			
			return false;
			
		}
		
		public Class<?> getColumnClass(int columnIndex){
			Class<?> clazz = Object.class;
			Object aux = getValueAt(0,columnIndex);
			if(aux!=null){
				clazz = aux.getClass();
			}
			return clazz;
		}
	}
	
	public void EventoMouseClicked(final JTable tabla){
		tabla.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				int fila = tabla.rowAtPoint(e.getPoint());
				if(e.getClickCount() == 2){
					new VentanaModificarMensajes(filasMostradas.get(fila));
					refrescarTabla();
				}
			}
		});
	}
	
	
	Object[] getCamposNormas(int fila){
		Object[] campos = new Object[3];
		campos[0] = InicioIanus.excel.contenidoNovedades[fila][0];
		campos[1] = InicioIanus.excel.contenidoNovedades[fila][1];
		campos[2] = InicioIanus.excel.getServicioNovedad(fila+1);
		return campos;
	}
	
	void refrescarTabla(){
  		int filas = modelo.getRowCount();
		
		for(int i=0;i<filas;i++){
				modelo.removeRow(0);
				filasMostradas.remove(0);
		}
		
		if(!JCBservicios.getSelectedItem().toString().contains("Todos")){	
			for(int i=0;i<numeroFilas;i++){
				if(InicioIanus.excel.getServicioNovedad(i+1).toLowerCase().contains(JCBservicios.getSelectedItem().toString().toLowerCase())){
					modelo.addRow(getCamposNormas(i));
					filasMostradas.add(i);
				}
				
			}
		}else{
			for(int i=0;i<numeroFilas;i++){
				modelo.addRow(getCamposNormas(i));
				filasMostradas.add(i);
			}
		}
	}
	
	void buscarEnTabla(){
  		int filas = modelo.getRowCount();
		
		for(int i=0;i<filas;i++){
				modelo.removeRow(0);
				filasMostradas.remove(0);
		}

		for(int i=0;i<numeroFilas;i++){
				if(InicioIanus.excel.contenidoNovedades[i][1].toString().toLowerCase().contains(textoAbuscarJTF.getText().toLowerCase())){
					modelo.addRow(getCamposNormas(i));
					filasMostradas.add(i);
				}
				
			}
	}
}
