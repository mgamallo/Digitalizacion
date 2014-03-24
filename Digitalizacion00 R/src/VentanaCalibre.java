import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class VentanaCalibre extends JFrame{

	/**
	 * @param args
	 */
	
	JPanel panelIz = new JPanel();
	JPanel panelDc = new JPanel();
	JPanel panelIzDown = new JPanel();
	
	JLabel fotoLabel = new JLabel();
	ImageIcon imagen;
	
	JButton botonAnterior = new JButton("Atras");
	JButton botonSiguiente = new JButton("Siguiente");
	JButton botonEmpezar = new JButton("Empezar");
	JButton botonGuardar = new JButton("Guardar");
	JButton botonCancelar = new JButton("Cancelar");
	
	JLabel coordX = new JLabel();
	JLabel coordY = new JLabel();
	JLabel etiquetaCoordenada = new JLabel();
	
	VentanaCalibre(String tipoCoordenada){
		setSize(800,700);
		setTitle("Coordenadas de " + tipoCoordenada);
		
		imagen = new ImageIcon("cal\\Plantilla 01.jpg");
		fotoLabel.setIcon(imagen);
		
	/*	panelDc.setLayout(new BoxLayout(parent, defaultCloseOperation));
		panelDc.add(botonEmpezar);
	//	panelDc.add(botonGuardar);
	//	panelDc.add(botonCancelar);
*/		
		panelIzDown.setLayout(new FlowLayout());
		panelIzDown.add(botonAnterior);
		panelIzDown.add(botonSiguiente);
		
		panelIz.setLayout(new BorderLayout());
		panelIz.add(fotoLabel,BorderLayout.CENTER);
		panelIz.add(panelIzDown,BorderLayout.SOUTH);

		
	//	setLayout(new FlowLayout());
		add(panelIz);
	//	add(panelDc);
		
		
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setVisible(true);
		
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		VentanaCalibre v = new VentanaCalibre("Consultas");
	}

}
