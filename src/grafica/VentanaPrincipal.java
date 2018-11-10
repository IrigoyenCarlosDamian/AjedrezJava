package grafica;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ajedrez.Ajedrez;
import ajedrez.Prueba;
import excepciones.FueraDeTableroException;
import util.Esperar;

import java.awt.FlowLayout;
import java.awt.Button;
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

public class VentanaPrincipal extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JLabel turnoEquipo;
	private JPanel panel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipal frame = new VentanaPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentanaPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(new Dimension(671, 500));
		setLocationRelativeTo(null); // Centra la ventana

		this.panel = new JPanel();
		getContentPane().add(panel, BorderLayout.WEST);
		panel.setLayout(new BorderLayout(0, 0));

		JLabel turnoActual = new JLabel("Turno del Equipo :");
		turnoActual.setAlignmentY(Component.TOP_ALIGNMENT);
		turnoActual.setVerticalAlignment(SwingConstants.TOP);
		turnoActual.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		this.panel.add(turnoActual, BorderLayout.NORTH);

		this.turnoEquipo = new JLabel("Blanca");
		turnoEquipo.setAlignmentX(Component.TOP_ALIGNMENT);
		turnoEquipo.setVerticalAlignment(SwingConstants.TOP);
		turnoEquipo.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		this.panel.add(turnoEquipo, BorderLayout.CENTER);

		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.NORTH);

		JPanel panel_2 = new JPanel();
		getContentPane().add(panel_2, BorderLayout.SOUTH);

		TableroGRAFICO tablero = Ajedrez.getSingletoneInstancia().getTableroGui();
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

	public void setTurno(String turno) {
		this.turnoEquipo.setText(turno);
	}

	public void actionPerformed(ActionEvent evento) {
		Runnable miRunnable = new Runnable() {
			public void run() {
				try {
					if (evento.getActionCommand() == "Iniciar") {
						Ajedrez.getSingletoneInstancia().inicarJuego();
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

}
