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
import interfaces.IPiezaListener;
import ajedrez.Prueba;
import equipos.EquipoHumano;
import equipos.EquipoMaster;
import equipos.EquipoOfensivo;
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
import javax.swing.ImageIcon;

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

/**
 * Clase que contiene el frame de la aplicacion grafica
 * 
 * @author Carlos
 *
 */
public class VentanaPrincipal extends JFrame implements ActionListener, IJuegoListener {

	private static int piezasVivasBlancas = 16;
	private static int piezasComidasBlancas = 0;
	private static int piezasVivasNegras = 16;
	private static int piezasComidasNegras = 0;
	private JPanel contentPane;
	private JPanel panel;
	private Ajedrez ajedrez;
	private JButton turnoActual;
	private JButton turnoActual2;
	public JScrollPane scrollPane;
	public static JTextArea textArea;
	private static JTextField textField;
	private static JTextField textField1;
	private static TableroGui tableroGui;

	/**
	 * Se crea el frame
	 */
	public VentanaPrincipal(TableroGui tablero) {
		this.tableroGui = tablero;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(new Dimension(671, 500));
		setLocationRelativeTo(null); // Centra la ventana
		this.panel = new JPanel();
		getContentPane().add(panel, BorderLayout.WEST);
		panel.setLayout(new BorderLayout(0, 0));
		turnoActual = new JButton("Equipo Negra");
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
		textArea.setForeground(Color.BLACK);
		turnoActual2 = new JButton("Equipo Blanca");
		turnoActual2.setAlignmentY(Component.TOP_ALIGNMENT);
		turnoActual2.setVerticalAlignment(SwingConstants.TOP);
		turnoActual2.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		this.panel.add(turnoActual2, BorderLayout.SOUTH);

		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.NORTH);

		textField1 = new JTextField();
		textField1.setText("Piezas Negras Vivas: 16" + " " + " Piezas Negras Comidas:0");
		textField1.setEditable(false);
		panel_1.add(textField1);
		JPanel panel_2 = new JPanel();
		getContentPane().add(panel_2, BorderLayout.SOUTH);
		textField = new JTextField();
		textField.setText("Piezas Blancas Vivas: 16" + " " + " Piezas Blancas Comidas:0");
		textField.setEditable(false);
		panel_2.add(textField);
		getContentPane().add(tablero, BorderLayout.CENTER);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu juegoMenu = new JMenu("Juego");
		menuBar.add(juegoMenu);
		JMenu juegoMenuIniciar = new JMenu("Iniciar");
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

		JMenuItem maquinaContrMaquina = new JMenuItem("Maquina Vs Maquina");
		juegoMenuIniciar.add(maquinaContrMaquina);
		JMenuItem maquinaContraPersona = new JMenuItem("Maquina Vs Persona");
		juegoMenuIniciar.add(maquinaContraPersona);
		JMenuItem personaContraPersona = new JMenuItem("Persona Vs Persona");
		juegoMenuIniciar.add(personaContraPersona);

		// Agrego el action command
		juegoMenuIniciar.setActionCommand("Iniciar");
		juegoMenuFinalizar.setActionCommand("Pausa");
		juegoMenuReiniciar.setActionCommand("Reinicar");
		juegoMenuCerrar.setActionCommand("Cerrar");
		ayudaMenuAcercaDe.setActionCommand("Acerca De");
		maquinaContrMaquina.setActionCommand("Maquina vs Maquina");
		maquinaContraPersona.setActionCommand("Maquina Vs Persona");
		personaContraPersona.setActionCommand("Persona vs Persona");

		// Agrego el listner a cada boton (quien los va a escuchar)
		juegoMenuFinalizar.addActionListener(this);
		juegoMenuReiniciar.addActionListener(this);
		juegoMenuCerrar.addActionListener(this);
		ayudaMenuAcercaDe.addActionListener(this);
		juegoMenuIniciar.addActionListener(this);
		maquinaContrMaquina.addActionListener(this);
		maquinaContraPersona.addActionListener(this);
		personaContraPersona.addActionListener(this);

	}

	/**
	 * Atiende los eventos de los botones del menu Bar
	 */
	public void actionPerformed(ActionEvent evento) {
		Runnable miRunnable = new Runnable() {
			public void run() {
				try {
					if (evento.getActionCommand() == "Iniciar") {

					} else if (evento.getActionCommand() == "Acerca De") {
						JOptionPane.showMessageDialog(null, "Integrantes: Irigoyen Carlos, Villanueva Alex");
					} else if (evento.getActionCommand() == "Reinicar") {
						Ajedrez.getSingletoneInstancia().setJuegoReiniciado(true);
						dispose();
						Prueba.main(null);
					} else if (evento.getActionCommand() == "Cerrar") {
						System.exit(0);
					} else if (evento.getActionCommand() == "Maquina vs Maquina") {
						textArea.append(null);
						Ajedrez ajedrez = Ajedrez.getSingletoneInstancia();
						
						Image img = new ImageIcon("iconoAjedrez.png").getImage();
						ImageIcon img2 = new ImageIcon(img.getScaledInstance(60, 60, Image.SCALE_SMOOTH));
						Object opcion = JOptionPane.showInputDialog(
								   null,
								   "Seleccione nivel de dificultad:",
								   "Antes de comenzar a jugar...",
								   JOptionPane.QUESTION_MESSAGE,
								   img2,  // null para icono defecto
								   new Object[] { "Nivel Ofensivo", "Nivel Master"}, 
								   "Elija nivel de Diicultad");
						if (opcion == "Nivel Master") {
							Equipo equipo1 = new EquipoMaster(Ajedrez.BLANCA);
							Equipo equipo2 = new EquipoMaster(Ajedrez.NEGRA);
							ajedrez.inicarJuego(equipo1, equipo2);
						} else {
							Equipo equipo1 = new EquipoOfensivo(Ajedrez.BLANCA);
							Equipo equipo2 = new EquipoOfensivo(Ajedrez.NEGRA);
							ajedrez.inicarJuego(equipo1, equipo2);
						}
						tableroGui.colocarPiezasTablero();
						for (Pieza p : ajedrez.getBlancas().getPiezas()) {
							tableroGui.setPieza(p);
						}
						for (Pieza p : ajedrez.getNegras().getPiezas()) {
							tableroGui.setPieza(p);
						}
						ajedrez.comenzar();

					} else if (evento.getActionCommand() == "Maquina Vs Persona") {
						textArea.append(null);
						Ajedrez ajedrez = Ajedrez.getSingletoneInstancia();
						EquipoHumano equipo1 = new EquipoHumano(Ajedrez.BLANCA);
						equipo1.setTablero(tableroGui);
						Image img = new ImageIcon("iconoAjedrez.png").getImage();
						ImageIcon img2 = new ImageIcon(img.getScaledInstance(60, 60, Image.SCALE_SMOOTH));
						Object opcion = JOptionPane.showInputDialog(
								   null,
								   "Seleccione nivel de dificultad:",
								   "Antes de comenzar a jugar...",
								   JOptionPane.QUESTION_MESSAGE,
								   img2,  // null para icono defecto
								   new Object[] { "Nivel Ofensivo", "Nivel Master"}, 
								   "Elija nivel de Diicultad");
						if (opcion == "Nivel Master") {
							Equipo equipo2 = new EquipoMaster(Ajedrez.NEGRA);
							ajedrez.inicarJuego(equipo1, equipo2);
						} else {
							Equipo equipo2 = new EquipoOfensivo(Ajedrez.NEGRA);
							ajedrez.inicarJuego(equipo1, equipo2);
						}

						tableroGui.colocarPiezasTablero();
						for (Pieza p : ajedrez.getBlancas().getPiezas()) {
							tableroGui.setPieza(p);
						}
						for (Pieza p : ajedrez.getNegras().getPiezas()) {
							tableroGui.setPieza(p);
						}
						ajedrez.comenzar();

					} else if (evento.getActionCommand() == "Persona vs Persona") {
						textArea.append(null);
						EquipoHumano equipo1 = new EquipoHumano(Ajedrez.BLANCA);
						equipo1.setTablero(tableroGui);
						EquipoHumano equipo2 = new EquipoHumano(Ajedrez.NEGRA);
						equipo2.setTablero(tableroGui);
						Ajedrez ajedrez = Ajedrez.getSingletoneInstancia();
						ajedrez.inicarJuego(equipo1, equipo2);
						tableroGui.colocarPiezasTablero();
						for (Pieza p : ajedrez.getBlancas().getPiezas()) {
							tableroGui.setPieza(p);
						}
						for (Pieza p : ajedrez.getNegras().getPiezas()) {
							tableroGui.setPieza(p);
						}
						ajedrez.comenzar();
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		Thread hilo = new Thread(miRunnable);
		hilo.start();

	}

	/**
	 * metodo que efectua un append en un textArea, muestra los movimiento que se
	 * efectuan en el tablero
	 * 
	 * @param pieza pieza que efectuna el movimiento
	 * @param celda celda destino de la pieza que es movida
	 */
	public static void movimientoDePiza(Pieza pieza, Celda celda) {
		String movimiento;
		movimiento = pieza.getClass().getSimpleName() + "(" + +celda.getFila() + "," + celda.getColumna() + ")\n";
		textArea.append(movimiento);

	}

	public void setAjedrez(Ajedrez ajedrez) {
		ajedrez.addJuegoListener(this);
	}

	@Override
	/**
	 * Muestra un menu de dialgo con el nombre del equipo que esta en jaques
	 */
	public void equipoEnJaque(Equipo equipo) {
		JOptionPane.showMessageDialog(null, equipo.getNombre() + " esta en jaque");
	}

	@Override
	/**
	 * Colorea de verde el boton correspondiente al equipo con el turno actual
	 */
	public void turnoActual(Equipo equipo) {
		if (equipo.getNombre() == Ajedrez.getSingletoneInstancia().BLANCA) {
			turnoActual2.setBackground(Color.green);
			turnoActual.setBackground(null);
		} else {
			turnoActual.setBackground(Color.green);
			turnoActual2.setBackground(null);
		}

	}

	@Override
	public void JuegoIniciado() {
		textArea.setText("Juego Iniciado: \n");
	}

	@Override
	public void juegoFinalizado() {
		
	}

	@Override
	/**
	 * muesta la cantidad de piezas vivas y piezas comidas de cada equipo
	 */
	public void piezaComida(Pieza pieza) {
		if (pieza.getEquipo().getNombre() == Ajedrez.getSingletoneInstancia().BLANCA) {
			int vivas = VentanaPrincipal.piezasVivasBlancas - 1;
			int comidas = VentanaPrincipal.piezasComidasBlancas + 1;
			
			VentanaPrincipal.textField.setText("Piezas Blancas Vivas: " + " " + vivas
					+ "  " + "Piezas Blancas Comidas:" + " " + comidas);
		} else if (pieza.getEquipo().getNombre() == Ajedrez.getSingletoneInstancia().NEGRA) {
			int vivas = VentanaPrincipal.piezasVivasNegras - 1;
			int comidas = VentanaPrincipal.piezasComidasNegras + 1;
			VentanaPrincipal.textField1.setText("Piezas Negras Vivas: " + " " + vivas + "  "
					+ "Piezas Negras Comidas:" + " " + comidas);
		}

	}

}
