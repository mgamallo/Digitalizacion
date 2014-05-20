import java.awt.Color;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class VentanaTemporizadores extends JFrame{

	/**
	 * @param args
	 */
	
	JLabel labelIanus = new JLabel("Retardo para cambiar entre ianus      " + String.valueOf(InicioIanus.retardoInterIanus));
	JLabel labelAsociar = new JLabel("Retardo para pulsar el botón asociar      " + String.valueOf(InicioIanus.retardoAsociar));
	JLabel labelTitulo = new JLabel("Retardo para pegar el título      " + String.valueOf(InicioIanus.retardoPegarTitulo));
	JLabel labelExplorar = new JLabel("Retardo para pulsar el botón examinar      " + String.valueOf(InicioIanus.retardoPulsarExaminar));
	JLabel labelAceptar = new JLabel("Retardo para pulsar el botón aceptar       " + String.valueOf(InicioIanus.retardoAceptar));
	JLabel labelVacio = new JLabel(" ");
	JLabel labelVacio1 = new JLabel(" ");
	JLabel labelVacio2 = new JLabel(" ");
	JLabel labelVacio3 = new JLabel(" ");
	JLabel labelVacio4 = new JLabel(" ");
	
	JSlider sliderIanus = new JSlider(JSlider.HORIZONTAL,200,1200,InicioIanus.retardoInterIanus);
	JSlider sliderAsociar = new JSlider(JSlider.HORIZONTAL,0,2000,InicioIanus.retardoAsociar);
	JSlider sliderTitulo = new JSlider(JSlider.HORIZONTAL,0,1000,InicioIanus.retardoPegarTitulo);
	JSlider sliderExplorar = new JSlider(JSlider.HORIZONTAL,0,1000,InicioIanus.retardoPulsarExaminar);
	JSlider sliderAceptar = new JSlider(JSlider.HORIZONTAL,0,1000,InicioIanus.retardoAceptar);

	JPanel panel = new JPanel();
	
	public VentanaTemporizadores() {
		// TODO Auto-generated constructor stub
		
		setTitle("Temporizadores");
		setSize(500,300);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.add(labelVacio);
		panel.add(labelIanus);
		panel.add(sliderIanus);
		panel.add(labelVacio1);
		panel.add(labelAsociar);
		panel.add(sliderAsociar);
		panel.add(labelVacio2);
		panel.add(labelTitulo);
		panel.add(sliderTitulo);
		panel.add(labelVacio3);
		panel.add(labelExplorar);
		panel.add(sliderExplorar);
		panel.add(labelVacio4);
		panel.add(labelAceptar);
		panel.add(sliderAceptar);
		
		
	    sliderAsociar.setMinorTickSpacing(10);
	    sliderAsociar.setMajorTickSpacing(500);
	    // sliderAsociar.setPaintLabels(true);
	    // sliderAsociar.setPaintTicks(true);
	    sliderAsociar.setBackground(Color.white);
	    
	    sliderAsociar.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent arg0) {
				// TODO Auto-generated method stub
				InicioIanus.retardoAsociar = (int) sliderAsociar.getValue();
				labelAsociar.setText("Retardo para pulsar el botón asociar      " + String.valueOf(InicioIanus.retardoAsociar));
				//System.out.println(InicioIanus.retardoAsociar);
			}
		});
		
	    sliderIanus.setMinorTickSpacing(10);
	    sliderIanus.setMajorTickSpacing(500);
	    // sliderIanus.setPaintLabels(true);
	    // sliderIanus.setPaintTicks(true);
	    sliderIanus.setBackground(Color.white);
	    
	    sliderIanus.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent arg0) {
				// TODO Auto-generated method stub
				InicioIanus.retardoInterIanus = (int) sliderIanus.getValue();
				labelIanus.setText("Retardo para cambiar entre ianus      " + String.valueOf(InicioIanus.retardoInterIanus));
				//System.out.println(InicioIanus.retardoAsociar);
			}
		});
	    
	    sliderExplorar.setMinorTickSpacing(10);
	    sliderExplorar.setMajorTickSpacing(500);
	    // sliderExplorar.setPaintLabels(true);
	    // sliderExplorar.setPaintTicks(true);
	    sliderExplorar.setBackground(Color.white);
	    
	    sliderExplorar.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent arg0) {
				// TODO Auto-generated method stub
				InicioIanus.retardoPulsarExaminar = (int) sliderExplorar.getValue();
				labelExplorar.setText("Retardo para pulsar el botón examinar      " + String.valueOf(InicioIanus.retardoPulsarExaminar));
				//System.out.println(InicioIanus.retardoAsociar);
			}
		});
	    
	    sliderTitulo.setMinorTickSpacing(10);
	    sliderTitulo.setMajorTickSpacing(500);
	    // sliderTitulo.setPaintLabels(true);
	    // sliderTitulo.setPaintTicks(true);
	    sliderTitulo.setBackground(Color.white);
		
	    sliderTitulo.addChangeListener(new ChangeListener() {
	 			@Override
	 			public void stateChanged(ChangeEvent arg0) {
	 				// TODO Auto-generated method stub
	 				InicioIanus.retardoPegarTitulo = (int) sliderTitulo.getValue();
	 				labelTitulo.setText("Retardo para pegar el título      " + String.valueOf(InicioIanus.retardoPegarTitulo));
	 				//System.out.println(InicioIanus.retardoAsociar);
	 			}
	 		});
	    
	    sliderAceptar.setMinorTickSpacing(10);
	    sliderAceptar.setMajorTickSpacing(500);
	    // sliderTitulo.setPaintLabels(true);
	    // sliderTitulo.setPaintTicks(true);
	    sliderAceptar.setBackground(Color.white);
		
	    sliderAceptar.addChangeListener(new ChangeListener() {
	 			@Override
	 			public void stateChanged(ChangeEvent arg0) {
	 				// TODO Auto-generated method stub
	 				InicioIanus.retardoAceptar = (int) sliderAceptar.getValue();
	 				labelAceptar.setText("Retardo para pulsar el botón aceptar      " + String.valueOf(InicioIanus.retardoAceptar));
	 				//System.out.println(InicioIanus.retardoAsociar);
	 			}
	 		});
	    
	    panel.setBackground(Color.white);
		getContentPane().add(panel);
		setVisible(true);
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new VentanaTemporizadores();
	}

}
