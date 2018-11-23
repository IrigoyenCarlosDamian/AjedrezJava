package grafica;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import ajedrez.Ajedrez;
import ajedrez.Celda;
import ajedrez.Equipo;
import interfaces.IJuegoListener;
import ajedrez.Prueba;
import excepciones.FueraDeTableroException;
import pieza.*;
import pieza.Pieza;
import util.Esperar;

import java.awt.FlowLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Panel;

import javax.swing.SwingConstants;
import javax.swing.BoxLayout;
import java.awt.Component;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JToolBar;
import java.awt.CardLayout;
import java.awt.GridBagLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class VentanaPrincipal extends JFrame implements ActionListener,IJuegoListener {
	
	private static int piezasVivasBlancas=16;
	private static int piezasComidasBlancas=0;
	private static int piezasVivasNegras=16;
	private static int piezasComidasNegras=0;
	private JPanel contentPane;
	private JPanel panel;
	private Ajedrez ajedrez;
	private JButton turnoActual;
	private JButton turnoActual2;
	public JScrollPane scrollPane;
	public static JTextArea textArea;
	private static JTextField textField;
	private static JTextField textField_1;
	
	/**
	 * Create the frame.
	 */
	public VentanaPrincipal(TableroGrafico tablero) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(new Dimension(671, 500));
		setLocationRelativeTo(null); // Centra la ventana
		this.panel = new JPanel();
		getContentPane().add(panel, BorderLayout.WEST);
		panel.setLayout(new BorderLayout(0, 0));
		turnoActual = new JButton("Turno del Equipo :");
		panel.add(turnoActual, BorderLayout.NORTH);
		turnoActual.setAlignmentY(Component.TOP_ALIGNMENT);
		turnoActual.setVerticalAlignment(SwingConstants.TOP);
		turnoActual.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		
		scrollPane = new JScrollPane();
		scrollPane.setAutoscrolls(true);
		panel.add(scrollPane, BorderLayout.CENTER);
		textArea = new JTextArea();
		textArea.setEditable(false);
		scrollPane.setViewportView(textArea);
		//textArea.setBorder(new TitledBorder("Jugadas"));
		textArea.setForeground(Color.BLACK);
		turnoActual2 = new JButton("Turno del Equipo :");
		turnoActual2.setAlignmentY(Component.TOP_ALIGNMENT);
		turnoActual2.setVerticalAlignment(SwingConstants.TOP);
		turnoActual2.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		this.panel.add(turnoActual2, BorderLayout.SOUTH);
		
		
		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.NORTH);
		
		textField = new JTextField();
		textField.setText("Piezas Negras Vivas: 16"+" "+" Piezas Negras Comidas:0");
		textField.setEditable(false);
		panel_1.add(textField);
		JPanel panel_2 = new JPanel();
		getContentPane().add(panel_2, BorderLayout.SOUTH);
		textField_1 = new JTextField();
		textField_1.setText("Piezas Blancas Vivas: 16"+" "+" Piezas Blancas Comidas:0");
		textField_1.setEditable(false);
		panel_2.add(textField_1);
		getContentPane().add(tablero, BorderLayout.CENTER);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu juegoMenu = new JMenu("Juego");
		menuBar.add(juegoMenu);
		JMenuItem juegoMenuIniciar = new JMenuItem("Iniciar");
		juegoMenu.add(juegoMenuIniciar);

		JMenuItem juegoMenuFinalizar = new JMenuItem("Pausa");
		juegoMenu.add(juegoMenuFinalizar);

		JMenuItem juegoMenuReiniciar = new JMenuItem("Reiniciar");
		juegoMenu.add(juegoMenuReiniciar);

		JMenuItem juegoMenuCerrar = new JMenuItem("Cerrar");
		juegoMenu.add(juegoMenuCerrar);

		JMenu ayudaMenu = new JMenu("Ayuda");
		menuBar.add(ayudaMenu);

		JMenuItem ayudaMenuAcercaDe = new JMenuItem("Acerca de...");
		ayudaMenu.add(ayudaMenuAcercaDe);
		
		JMenu modoDeJuego = new JMenu("ModoDeJuego");
		menuBar.add(modoDeJuego);
		
		JMenuItem maquinaContrMaquina = new JMenuItem("Maquina Vs Maquina");
		modoDeJuego.add(maquinaContrMaquina);
		
		JMenuItem maquinaContraPersona = new JMenuItem("Maquina Vs Persona");
		modoDeJuego.add(maquinaContraPersona);
		
		JMenuItem  personaContraPersona = new JMenuItem("Persona Vs Persona");
		modoDeJuego.add(personaContraPersona);

		// Agrego el action command
		juegoMenuIniciar.setActionCommand("Iniciar");
		juegoMenuFinalizar.setActionCommand("Pausa");
		juegoMenuReiniciar.setActionCommand("Reinicar");
		juegoMenuCerrar.setActionCommand("Cerrar");
		ayudaMenuAcercaDe.setActionCommand("Acerca De");
	
		// Agrego el listner a cada boton (quien los va a escuchar)
		juegoMenuFinalizar.addActionListener(this);
		juegoMenuReiniciar.addActionListener(this);
		juegoMenuCerrar.addActionListener(this);
		ayudaMenuAcercaDe.addActionListener(this);
		juegoMenuIniciar.addActionListener(this);

		/*
		 *
		 * setBounds(100, 100, 450, 300); contentPane = new JPanel();
		 * contentPane.setBorder(new EmptyBorder(5, 5, 5, 5)); contentPane.setLayout(new
		 * BorderLayout(0, 0)); setContentPane(contentPane);
		 */
	}
	public void actionPerformed(ActionEvent evento) {
		Runnable miRunnable = new Runnable() {
			public void run() {
				try {
					if (evento.getActionCommand() == "Iniciar") {
				
						Ajedrez.getSingletoneInstancia().comenzar();

					} else if (evento.getActionCommand() == "Acerca De") {
						JOptionPane.showMessageDialog(null, "Integrantes: Irigoyen Carlos, Villanueva Alex");
					} else if (evento.getActionCommand() == "Reinicar") {

						Ajedrez.getSingletoneInstancia().setJuegoReiniciado(true);
						dispose();
						Prueba.main(null);

					} else if (evento.getActionCommand() == "Cerrar") {
						System.exit(0);
					} else if (evento.getActionCommand() == "Pausa") {
						Ajedrez.getSingletoneInstancia().setJuegoReiniciado(true);
						JOptionPane.showMessageDialog(null, "Juego Pausado");
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		Thread hilo = new Thread(miRunnable);
		hilo.start();

	}
	
	public static void movimientoDePiza(Pieza pieza,Celda celda) {
		String movimiento;
		movimiento= pieza.getClass().getSimpleName()+"("+
				+celda.getFila()+
				","+celda.getColumna()+")\n";
		textArea.append(movimiento);

	}
	
	
	
	public void setVentanaPrincipal(Ajedrez ajedrez) {
		ajedrez.addJuegoListener(this);
	}

	@Override
	public void equipoEnJaque(Equipo equipo) {
		JOptionPane.showMessageDialog(null,equipo.getNombre()+"esta en jaque");
	}

	@Override
	public void turnoActual(Equipo equipo) {
		if(equipo.getNombre()==Ajedrez.getSingletoneInstancia().BLANCA) {
			turnoActual2.setBackground(Color.green);
			turnoActual.setBackground(null);
		}else {
			turnoActual.setBackground(Color.green);
			turnoActual2.setBackground(null);	
		}
		
		
		
	}
	
	public static void mostrarPiezaComida(Pieza pieza) {
		if(pieza.getEquipo().getNombre()==Ajedrez.getSingletoneInstancia().BLANCA) {
			VentanaPrincipal.piezasVivasBlancas=VentanaPrincipal.piezasVivasBlancas-1;
			VentanaPrincipal.piezasComidasBlancas= VentanaPrincipal.piezasComidasBlancas+1;
			VentanaPrincipal.textField_1.setText("Piezas Blancas Vivas: "
			+" "
			+VentanaPrincipal.piezasVivasBlancas+"  "
			+"Piezas Blancas Comidas:" 
			+" "
			+VentanaPrincipal.piezasComidasBlancas);
		}
		else if(pieza.getEquipo().getNombre()==Ajedrez.getSingletoneInstancia().NEGRA) {
			VentanaPrincipal.piezasVivasNegras=VentanaPrincipal.piezasVivasNegras-1;
			VentanaPrincipal.piezasComidasNegras=VentanaPrincipal.piezasComidasNegras+1;
			VentanaPrincipal.textField.setText("Piezas Negas Vivas: "
					+" "
					+VentanaPrincipal.piezasVivasNegras+"  "
					+"Piezas Negras Comidas:" 
					+" "
					+VentanaPrincipal.piezasComidasNegras);
		}
		
		
	}
	
	

	@Override
	public void JuegoIniciado() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void juegoFinalizado() {
		// TODO Auto-generated method stub
		
	}
	
	
	

}

