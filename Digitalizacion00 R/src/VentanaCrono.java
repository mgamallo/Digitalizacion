import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.concurrent.TimeUnit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


public class VentanaCrono extends JFrame implements Runnable, ActionListener{

	/**
	 * @param args
	 */
	
	JLabel etiqueta1 = new JLabel("Actual");
	JLabel etiqueta2 = new JLabel("Acumulado");
	JLabel etiqueta3 = new JLabel("Records");
	
	JPanel panel1 = new JPanel();
	JPanel panel2 = new JPanel();
	JPanel panel3 = new JPanel();
	
	JButton inicioJB = new JButton("Inicio");
	JButton inicioJB2 = new JButton("Inicio");
	JButton pausaJB = new JButton("Pausa");
	JButton pausaJB2 = new JButton("Pausa");
	
	
	JTextField cronoJTF = new JTextField(7);
	JTextField cronoJTF2 = new JTextField(7);
	JTextField numHistJTF = new JTextField(4);
	JTextField numHistJTF2 = new JTextField(4);
	
	JTextField hisMinutoJTF = new JTextField(5);
	JTextField hisMinutoJTF2 = new JTextField(5);
	JTextField hisHoraJTF = new JTextField(5);
	JTextField hisHoraJTF2 = new JTextField(5);
	
	JLabel  hisMinutoJL = new JLabel("H/min");
	JLabel  hisMinutoJL2 = new JLabel("H/min");
	JLabel  hisHoraJL	= new JLabel("H/hor");
	JLabel  hisHoraJL2	= new JLabel("H/hor");
	
//	Lienzo lienzo = new Lienzo();
	JTextField lienzo = new JTextField(25);
	JTextField lienzo2 = new JTextField(25);
	
	JButton resetJB = new JButton("Reset");
	JButton resetJB2 = new JButton("Reset");
	
	JTabbedPane panelTab = new JTabbedPane();
	
	
	Thread hilo;
	boolean terminarHilo = true;

	int segundosTotales = 0;

	String cadenaReset ="";
	
	VentanaCrono(){
		this.setTitle("Estadisticas");
		setSize(850,120);
	//	setResizable(false);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

	//	PANEL 1 ***********************************************************************************
		
			panel1.setLayout(new GridBagLayout());
		
			
			GridBagConstraints conf00 = new GridBagConstraints();
			conf00.gridx=0;
			conf00.gridy=0;
			conf00.anchor = GridBagConstraints.WEST;
			conf00.fill= GridBagConstraints.BOTH;
			conf00.insets = new Insets(2,0,1,10);    //	(alto,izquierda,bajo,derecha)

			// etiquetaTipoCoordenada.setFont(new Font("Arial",Font.BOLD,22));
			// etiquetaTipoCoordenada.setForeground(Color.red);
			panel1.add(inicioJB,conf00);
			inicioJB.addActionListener(this);
			
			GridBagConstraints conf01 = new GridBagConstraints();
			conf01.gridx = 0;
			conf01.gridy = 1;
			conf01.insets = new Insets(1,0,2,10);    //	(alto,izquierda,bajo,derecha)
			conf01.anchor = GridBagConstraints.WEST;
			conf01.fill= GridBagConstraints.BOTH;
			pausaJB.setEnabled(false);
			panel1.add(pausaJB,conf01);	
			
			pausaJB.addActionListener(new ActionListener(){
		    	public void actionPerformed(java.awt.event.ActionEvent evt) {
		    		if(pausaJB.getText().contains("Pausa")){
		    			terminarHilo = true;
			    		pausaJB.setText("Continuar");
			    		resetJB.setEnabled(true);
			    		inicioJB.setEnabled(true);
		    		}else if(pausaJB.getText().contains("Continuar")){
		    			terminarHilo = false;
		    			iniciarCrono();
		    			inicioJB.setEnabled(false);
			    		resetJB.setEnabled(false);
		    			pausaJB.setText("Pausa");
			    		
		    		}

		    	}
			});

			GridBagConstraints conf10 = new GridBagConstraints();
			conf10.gridx = 1;
			conf10.gridy = 0;
			conf10.gridwidth = 1;
			conf10.gridheight = 2;
			conf10.fill = GridBagConstraints.BOTH;
			conf10.insets = new Insets(0,0,0,10);
			conf10.anchor = GridBagConstraints.CENTER;
			cronoJTF.setText("00:00:00");
			cronoJTF.setEditable(false);
			cronoJTF.setBackground(new Color(255,175,175));
			cronoJTF.setFont(new Font("Dialog",Font.BOLD,20));
			cronoJTF.setHorizontalAlignment(JTextField.CENTER);
			panel1.add(cronoJTF,conf10);		
			
			GridBagConstraints conf20 = new GridBagConstraints();
			conf20.gridx = 2;
			conf20.gridy = 0;
			conf20.gridwidth = 1;
			conf20.gridheight = 2;
			conf20.insets = new Insets(0,0,0,10);
			conf20.fill = GridBagConstraints.BOTH;
			conf20.anchor = GridBagConstraints.CENTER;
			numHistJTF.setText("0");
			numHistJTF.setEditable(false);
			numHistJTF.setBackground(new Color(255,200,175));
			numHistJTF.setFont(new Font("Dialog",Font.BOLD,20));
			numHistJTF.setHorizontalAlignment(JTextField.CENTER);
			panel1.add(numHistJTF,conf20);
		
			GridBagConstraints conf30 = new GridBagConstraints();
			conf30.gridx = 3;
			conf30.gridy = 0;
			conf30.insets = new Insets(0,0,2,10);
			conf30.anchor = GridBagConstraints.CENTER;
			conf30.fill = GridBagConstraints.BOTH;
			hisMinutoJTF.setText("0.00");
			hisMinutoJTF.setEditable(false);
			hisMinutoJTF.setBackground(new Color(200,250,250));
			hisMinutoJTF.setFont(new Font("Dialog",Font.BOLD,15));
			hisMinutoJTF.setHorizontalAlignment(JTextField.RIGHT);
			panel1.add(hisMinutoJTF,conf30);
			
			GridBagConstraints conf31 = new GridBagConstraints();
			conf31.gridx = 3;
			conf31.gridy = 1;
			conf31.insets = new Insets(2,0,0,10);
			conf31.anchor = GridBagConstraints.CENTER;
			conf31.fill = GridBagConstraints.BOTH;
			hisHoraJTF.setText("0.00");
			hisHoraJTF.setEditable(false);
			hisHoraJTF.setBackground(new Color(200,250,250));
			hisHoraJTF.setFont(new Font("Dialog",Font.BOLD,15));
			hisHoraJTF.setHorizontalAlignment(JTextField.RIGHT);
			panel1.add(hisHoraJTF,conf31);
			
			GridBagConstraints conf40 = new GridBagConstraints();
			conf40.gridx = 4;
			conf40.gridy = 0;
			conf40.insets = new Insets(0,0,2,10);
			conf40.anchor = GridBagConstraints.WEST;
			panel1.add(hisMinutoJL,conf40);
			
			GridBagConstraints conf41 = new GridBagConstraints();
			conf41.gridx = 4;
			conf41.gridy = 1;
			conf41.insets = new Insets(0,0,2,10);
			conf41.anchor = GridBagConstraints.WEST;
			panel1.add(hisHoraJL,conf41);
			
		
			GridBagConstraints conf50 = new GridBagConstraints();
			conf50.gridx = 5;
			conf50.gridy = 0;
			conf50.gridwidth = 3;
			conf50.gridheight = 2;			
			conf50.insets = new Insets(0,0,0,10);
			conf50.fill = GridBagConstraints.BOTH;
			conf50.anchor = GridBagConstraints.CENTER;
			lienzo.setBackground(Color.white);
			
			panel1.add(lienzo,conf50);
	
			GridBagConstraints conf80 = new GridBagConstraints();
			conf80.gridx = 8;
			conf80.gridy = 0;
			conf80.gridwidth = 1;
			conf80.gridheight = 2;
			conf80.fill = GridBagConstraints.BOTH;
			conf80.insets = new Insets(0,0,0,10);
			conf80.anchor = GridBagConstraints.CENTER;
			resetJB.setEnabled(false);
			panel1.add(resetJB,conf80);
			
			
			
			resetJB.addActionListener(new ActionListener(){
		    	public void actionPerformed(java.awt.event.ActionEvent evt) {
		    			terminarHilo = true;
		    			segundosTotales = 0;
			    		inicioJB.setText("Inicio");
			    		inicioJB.setEnabled(true);
			    		pausaJB.setText("Pausa");
			    		pausaJB.setEnabled(false);
						hisMinutoJTF.setText("0.00");
						hisHoraJTF.setText("0.00");
						cronoJTF.setText("00:00:00");
						cronoJTF2.setText(cadenaReset);
		    	}
			});

		
			//	PANEL 2 ***********************************************************************************
			
			panel2.setLayout(new GridBagLayout());
			
			cronoJTF2.setText("00:00:00");
			cronoJTF2.setEditable(false);
			cronoJTF2.setBackground(new Color(255,175,175));
			cronoJTF2.setFont(new Font("Dialog",Font.BOLD,20));
			cronoJTF2.setHorizontalAlignment(JTextField.CENTER);
			panel2.add(cronoJTF2,conf10);		

			numHistJTF2.setText(Integer.toString(InicioIanus.documentosClickTotales));
			numHistJTF2.setEditable(false);
			numHistJTF2.setBackground(new Color(255,200,175));
			numHistJTF2.setFont(new Font("Dialog",Font.BOLD,20));
			numHistJTF2.setHorizontalAlignment(JTextField.CENTER);
			panel2.add(numHistJTF2,conf20);
		
			hisMinutoJTF2.setText("0.00");
			hisMinutoJTF2.setEditable(false);
			hisMinutoJTF2.setBackground(new Color(200,250,250));
			hisMinutoJTF2.setFont(new Font("Dialog",Font.BOLD,15));
			hisMinutoJTF2.setHorizontalAlignment(JTextField.RIGHT);
			panel2.add(hisMinutoJTF2,conf30);
			
			hisHoraJTF2.setText("0.00");
			hisHoraJTF2.setEditable(false);
			hisHoraJTF2.setBackground(new Color(200,250,250));
			hisHoraJTF2.setFont(new Font("Dialog",Font.BOLD,15));
			hisHoraJTF2.setHorizontalAlignment(JTextField.RIGHT);
			panel2.add(hisHoraJTF2,conf31);

			panel2.add(hisMinutoJL2,conf40);
			
			panel2.add(hisHoraJL2,conf41);

			panel2.add(lienzo2,conf50);

			//	FIN  PANEL 2
			
		
		
		
		panelTab.addTab("Estadística Actual",null,panel1,"Primer Panel");
		panelTab.addTab("Acumulado",null,panel2,"Segundo Panel");
		panelTab.addTab("Marcas",null,panel3,"Tercer Panel");
		
	//	panel1.add(etiqueta1,SwingConstants.CENTER);
	//	panel2.add(etiqueta2,SwingConstants.CENTER);
		panel3.add(etiqueta3,SwingConstants.CENTER);
		
		this.getContentPane().add(panelTab);
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent arg) {
		// TODO Auto-generated method stub
		Object ob = arg.getSource();
		if(ob instanceof JButton){
			JButton btn = (JButton)ob;
			if(btn.getText().equals("Inicio")){
				inicioJB.setText("Guardar");
				inicioJB.setEnabled(false);
				iniciarCrono();
				pausaJB.setEnabled(true);
			}else if(btn.getText().equals("Guardar")){
				terminarHilo = true;
				pausaJB.setEnabled(false);
				JOptionPane.showMessageDialog(this, "Estadisticas de la carpeta almacenadas. \n Puedes comenzar otra carpeta.");
				InicioIanus.documentosClickTotales += InicioIanus.documentosClick;
				InicioIanus.segundosTotalesAcumulados += segundosTotales;
				segundosTotales = 0;
				InicioIanus.documentosClick = 0;
				inicioJB.setText("Inicio");
				pausaJB.setText("Pausa");
				cronoJTF.setText("00:00:00");
				InicioIanus.documentosClick = 0;
				numHistJTF.setText("0");
				hisMinutoJTF.setText("0.00");
				hisHoraJTF.setText("0.00");
				
				resetJB.setEnabled(false);
				
			}
		}
	}
	
	public void iniciarCrono(){
		terminarHilo = false;
		hilo = new Thread(this);
		hilo.start();
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		
		String cadena;
		String cadena2 = "00:00:00";

		int minutos = 0;
		int minutosA = 0;
			
		int horas = 0;
		int horasA = 0;
		int restoHora = 0;
		int restoHoraA = 0;
		
		int segundos = 0; // segundosTotales;
		int segundosA = 0;
		
		cadenaReset = cadena2;
		
		horas = segundosTotales/3600;
		restoHora = segundosTotales%3600;
		horasA = (InicioIanus.segundosTotalesAcumulados + segundosTotales)/3600;
		restoHoraA = (InicioIanus.segundosTotalesAcumulados + segundosTotales)%3600;

		minutos = restoHora/60;
		minutosA = restoHoraA/60;


		segundos = segundosTotales%60;
		segundosA = (InicioIanus.segundosTotalesAcumulados + segundosTotales)%60;

		float hisMin = 0;
		float hisHora = 0;

		String shoras = "00";
		String sminutos = "00";
		String ssegundos = "00";
		
		DecimalFormat df = new DecimalFormat("#####.00");
		
		while(!terminarHilo){
			Thread ct = Thread.currentThread();
			 
			try {
			    	hilo.sleep(1000);
			}catch(InterruptedException e) {}
				
			segundos++;
			segundosA++;
			segundosTotales++;
			if(segundos == 60){
				segundos =0;
				minutos++;
			}
			if(minutos == 60){
				minutos = 0;
				horas++;
			}
			ssegundos = "";
			if (segundos < 10){
				ssegundos = "0";
			}
			ssegundos += Integer.toString(segundos);
			sminutos = "";
			if(minutos < 10){
				sminutos = "0";
			}
			sminutos += Integer.toString(minutos);
			shoras = "";
			if(horas < 10){
				shoras = "0";
			}
			shoras += Integer.toString(horas);
			
	
			cadena = shoras + ":" + sminutos + ":" + ssegundos;
			 
			cronoJTF.setText(cadena);
			
			if(segundosA == 60){
				segundosA =0;
				minutosA++;
			}
			if(minutosA == 60){
				minutosA = 0;
				horasA++;
			}
			ssegundos = "";
			if (segundosA < 10){
				ssegundos = "0";
			}
			ssegundos += Integer.toString(segundosA);
			sminutos = "";
			if(minutosA < 10){
				sminutos = "0";
			}
			sminutos += Integer.toString(minutosA);
			shoras = "";
			if(horasA < 10){
				shoras = "0";
			}
			shoras += Integer.toString(horasA);
			
			cadena2 = shoras + ":" + sminutos + ":" + ssegundos;

			cronoJTF2.setText(cadena2);
			


			
			if(segundos !=0){
				hisMin = (float) InicioIanus.documentosClick/segundosTotales*60;
				hisHora = (float) InicioIanus.documentosClick/segundosTotales*3600;
			}
			
			numHistJTF.setText(Integer.toString(InicioIanus.documentosClick));
			numHistJTF2.setText(Integer.toString(InicioIanus.documentosClickTotales+ InicioIanus.documentosClick));

			hisMinutoJTF.setText(df.format(hisMin));
			hisHoraJTF.setText(df.format(hisHora));
			
			if(segundos !=0){
				hisMin = (float) (InicioIanus.documentosClickTotales + InicioIanus.documentosClick) /
						         (InicioIanus.segundosTotalesAcumulados + segundosTotales)*60;
				hisHora = (float) (InicioIanus.documentosClickTotales + InicioIanus.documentosClick) /
						          (InicioIanus.segundosTotalesAcumulados + segundosTotales)*3600;
			}
			
			hisMinutoJTF2.setText(df.format(hisMin));
			hisHoraJTF2.setText(df.format(hisHora));
			
		}
	
	}
	
	
	
/*	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new VentanaCrono();
	}

*/

}

class Lienzo extends Canvas{
	public void paint(Graphics g){
		g.drawRect(0, 0, 10, 5);
	}
}
