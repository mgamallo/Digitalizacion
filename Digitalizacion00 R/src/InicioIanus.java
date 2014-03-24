import java.awt.AWTException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.Robot;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.SwingUtilities;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyListener;
import org.jnativehook.mouse.NativeMouseInputListener;

import chrriis.dj.nativeswing.swtimpl.NativeInterface;

public class InicioIanus extends JFrame {

	/**
	 * @param args
	 */
	
	static final String RUTA ="j:/digitalización/00 documentacion/03 Firmado"; 
	static final String RUTAB ="H:/DIGITALIZACIÓN/00 DOCUMENTACION/03 Firmado";
	static final String RUTAURG ="j:/DIGITALIZACIÓN/01 INFORMES URG (Colectiva)"; 
	static final String RUTAURGB ="H:/DIGITALIZACIÓN/01 INFORMES URG (Colectiva)";	
	static final String RUTAPC = "c:/ianus/ianus.txt";                                  // Donde se almacena el nombre del pc
	
	static String nombrePc;
	static boolean ianus2pantallas = false;       	//	Si subimos con dos ianus, por defecto los ianus en una sola pantalla
	static Point coordAsociar = new Point(0,0);
	
	static File[] tandaDePdfs;
	static DocumentoProp[] documentos;
	static int numPdfsAbiertos = 0;
	static char lado = 'd';						// Determina donde está el ianus, si en la pantalla izquierda i, o en la derecha d
	static boolean arriba = false;				// Determina donde está el ianus, si en la parte superior true, o en la inferior false
	static boolean pdfsAlternados = false;
	
	static VisorWebPdf visorPdf1;
	static VisorWebPdf visorPdf2;
	
	
	
	
	static final String NOMBRE_FICHERO_EXCEL = "Documentos.xls";
	static final String NOMBRE_FICHERO_EXCEL_ESTADISTICA = "Estadisticas.xls";
	
	static String[] rutaCompletaPdfs;

	static String[] nombrePdf = new String[2];		//	Para ayudar en el caso de 2 Ianus
	static boolean par = false;						//	Para ayudar en el caso de 2 Ianus
	
	static String usuario = "";
	static PreferenciasUsuario coordenadas;
	static int numeroIanus;							//	Numero de Ianus abiertos
	static int numeroPantallas;
	
	static int[][] coordenadasIanus;				//	Coordenadas de los formularios de Ianus
	
	static boolean ficherosCargados = false;
	static boolean ventanaNombresAbierta = false;
	static boolean dudasParaAsociar = false;		//	Dudas pendientes de enviar a la carpeta asociar
	static String rutaDudasParaAsociar;				//	Ruta de los ficheros en dudas ya contestadas

	static DefaultComboBoxModel servicios;			//	Lista de los servicios
	static ArrayList<Object> documentosServicio;  //  Lista de los documentos del servicio
	
	static VentanaExplorador ventanaE;
	static InterFazTabla ventanaD;
	static Point localizacionVentanaD = new Point(0,0);
	static Point dimensionesVentanaD = new Point(0,0);
	
	static VentanaBotonesDocMini ventanaBotonesTeclas;
	static String ventanaBotonesTeclaAtributo;
	static Point localizacionVentanaBotonesTeclas = new Point(0,0);
	static Point dimensionesVentanaBotonesTeclas = new Point(0,0);
	
	static int documentosClick = 0;					//	Para contabilizar los intentos de pegar el nombre del documento
	static int documentosClickTotales = 0;
	static int segundosTotalesAcumulados = 0;
	
	static LeerExcel excel;							//	Datos hoja excel
	
	static String auxRutaImagen = "";				//	Para ayudar a la hora de asignar una imagen a una norma, aviso, comentario...
	static int numeroNuevasNormas = 0;				//	Para saber el numero de normas nuevas se muestran al principio del programa
	static int numeroNuvosMensajes = 0;				//	Para saber el numero de nuevos mensajes que se muestran al principio del programa
	static int numeroNuevosDocModel = 0;			//	Para saber el numero de nuevos documentos que se muestran al principio del programa
	
	static int documentacion = 1;			//	Para saber si vamos a subir urgencias, documentacion, o el nuevo sistema. por defecto documentacion
											//	0	Urg normal
											//	1	Doc normal
											//	2	Doc OCR
											//	3	Urg OCR
		
	static ArrayList<String> carpetasAbiertas = new ArrayList<String>();
	
	static boolean turbo = true;					//	Para no pedir la confirmación a la hora de subir un documento

	static int tipoDocAsubir;						//  1	Ingresos/Urg/Rx
													//	2	Consultas
													//	3	CIA
													//	4	Anulacion 
	
	static String servicioCombo;					//	Almacena el servicio seleccionado en el combo box
	static String nombreDocumento;					//	Almacena el nombre del documento seleccionado
	static double tamañoDocumento;					//	Almacena el tamaño en bytes del documento seleccionado
	
	static JButton jBultimoNombreSubido;			//	Para sincronizar el nombre del boton de ultimo nombre subido
	static JButton jButtonTeclas;					//	Para avisar cuando el AutoHotKey está habilitado
	static boolean teclasAzulesEditables = false;	//	Para facilitar la edicion de los botones azules
	static JButton teclaAzul1;
	static JButton teclaAzul2;
	
	static JComboBox comboServicios;				//	Para tener siempre referencia del servicio seleccionado
	
	static Estadisticas estadistica = new Estadisticas();				//	Almacena por servicios, el numero de veces que un nombre es usado
	static ArrayList<DatoEstadisticoSantiago> estadisticaSantiago = new ArrayList<DatoEstadisticoSantiago>();		//	Lista de combinación de datos servicio_nombre_tamaño
	static ArrayList<DatoEstadisticoSantiago> lecturaDatosEstadiscosSantiago = new ArrayList<DatoEstadisticoSantiago>();
	static ArrayList<TablaServicioDocumentos> tablaServiciosDocumentos = new ArrayList<TablaServicioDocumentos>();	//  Tablas para actualizar la hoja de estadisticas
	static ConjuntoDatosEstadisticos[] conjuntoDatosEstadisticos;
	
	
	static boolean documentacionVigo = false;
	static String numeroHistoriaNuestro ="";
	
	/****** Para gestionar la introduccion de nhcs en dos ianus ********************/

	static String nhcIanus1 = "";
	static String nhcIanus2 = "";
	static boolean nhc1Seleccionado = false;
	static boolean nhc2Seleccionado = false;
	static int pdfSeleccionado = 0;
	
	static JButton botonIanus1 = new JButton("Ianus 1");
	static JButton botonIanus2 = new JButton("Ianus 2");
	static JButton botonServicio = new JButton("Servicio");
	static JButton botonNombreNormalizado = new JButton("Nombre Documento");
	static JButton botonInicioSubidaOCR = new JButton("Inicio");
	static boolean gestion2ianusIniciada = false;
	static JButton botonResetSubidaOCR = new JButton("Reset");
	static JCheckBox check2IanusOCR = new JCheckBox();
	static JCheckBox checkPulsarAsociar = new JCheckBox();
	static boolean asociarAutomatico = true;
	
	//	static JCheckBox checkTurboOCR = new JCheckBox();
	static boolean ocr2IanusAutomatico = false;             //	Subir con dos ianus
	// static boolean ocrTurbo = true;
	static Gestion2Ianus gestion2ianus;
	
	static Point coordMinimizar = new Point(2003,8);
	static int retardoAsociar = 400;
	static int retardoAbrirVentanaPropiaAsociar = 800;
	
	static boolean nodoForzado = false;
	
	/*********************************************************************************/

	/******* Para gestionar el titulo normalizado a la hora de subir a ianus *********/

	static HashSet<String> conjuntoTitulos = new HashSet<String>();
	public static boolean suspensionTeclado = false;
	
	/**********************************************************************************/
	
	/******* Para gestionar la captura de ratón y teclado *****************************/
	
	static CapturaRatonYTeclado ratonYteclado;
	static Point ultimoClick = new Point();
	static Point ClickImportante = new Point();
	static int tipoSubida = 2;              //  0	Manual		
											//	1	SemiManual	Introduce el Nhc automat
											//	2	Rapida		Almacena las coordenadas del servicio para repeticiones
											//	3	Extrema		Solo pinchando en el servicio ya sube
	
	//	Coordenadas del cuadro de exploración de ianus
	static Point coordenadaNWExploradorIanus = new Point(1115,300);
	static Point coordenadaSEExploradorIanus = new Point(1310,1140);
	
	static boolean subidaExtrema = false;

	
	/**********************************************************************************/
	InicioIanus(){
	      /*
         * Set the Nimbus look and feel
         */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VentanaExplorador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaExplorador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaExplorador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaExplorador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice[] gs = ge.getScreenDevices();
				
		numeroPantallas = gs.length;
			
		
		nombrePc = new IdentificarPc().getIdentificacion(RUTAPC);
			
		
    	try {
			excel = new LeerExcel();
			excel.leerExcel(NOMBRE_FICHERO_EXCEL);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			System.out.println("Archivo excel en uso, o inexistente");
		}
		
       VentanaUrgODoc vud =  new VentanaUrgODoc();
       if(vud.getTipoDocumentacion() == -1){
    	   dispose();
       }
       if(InicioIanus.documentacion == 2 || InicioIanus.documentacion == 3){
    	   InicioIanus.ventanaBotonesTeclaAtributo = "Micro2";
    	   Inicio.navegador1.ocrPanel.setVisible(true);
       }
       
       VentanaInicio dialog = new VentanaInicio(new javax.swing.JFrame(), true);
       dialog.addWindowListener(new java.awt.event.WindowAdapter() {

               @Override
               public void windowClosing(java.awt.event.WindowEvent e) {
                 System.exit(0);
               }
        });
        dialog.setVisible(true);
        if (usuario != ""){
        	
        	Inicio.coordenadasVentanas.leerCoordenadasVentana("Coordenadas.xls");
        	
           	coordenadas = new PreferenciasUsuario();
           	// numeroPantallas = coordenadas.numPantallas;
        	
            Inicio.navegador1.frame.setBounds(Inicio.coordenadasVentanas.vPdf1);
            Inicio.navegador1.frame.setVisible(true);
        	
           	
           	if(InicioIanus.numeroIanus == 2){
           		//Inicio.navegador2 = new WebBrowserIanus("Visor número 2", new Color(255,246,143));
           		Inicio.navegador2.frame.setBounds(Inicio.coordenadasVentanas.vPdf2);
            	Inicio.navegador2.frame.setVisible(true);
           	}
           	
            ventanaE = new VentanaExplorador();
            ventanaE.setBounds(Inicio.coordenadasVentanas.vExplorador);
            ventanaE.setVisible(true);
            
            //	Captura eventos raton y teclado
            try {
    			GlobalScreen.registerNativeHook();
    		} catch (NativeHookException e) {
    			// TODO Auto-generated catch block
    			System.err.println("There was a problem registering the native hook.");
                System.err.println(e.getMessage());
    			//e.printStackTrace();
    		}
            
            
            ratonYteclado = new CapturaRatonYTeclado();
            GlobalScreen.getInstance().addNativeKeyListener(ratonYteclado);
            GlobalScreen.getInstance().addNativeMouseListener(ratonYteclado);
            GlobalScreen.getInstance().addNativeMouseMotionListener(ratonYteclado);
            
            System.out.println("LLegamos?");
            
        }
	}
}



class VentanaUrgODoc{
	
	int getTipoDocumentacion(){
		
		int opcion = JOptionPane.showOptionDialog(null, "¿Qué documentación vas a subir?", "Selector de documentación", 
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,null, new Object[] {"Urgencias","Documentación","DOC OCR","URG OCR"}, "Documentación");
		/*
		 * 0	urgencias
		 * 1	documentacion
		 * 2	ocr doc
		 * 3	ocr urg
		if(opcion == 0){
			InicioIanus.documentacion = false;
		}
		*/

		
		InicioIanus.documentacion = opcion;
		
		return opcion;
	}
	
}
