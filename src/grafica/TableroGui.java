package grafica;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.GridBagLayout;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ajedrez.Celda;
import interfaces.IPiezaListener;
import pieza.Pieza;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.io.File;
import javax.swing.JMenu;
import javax.swing.*;

public class TableroGui extends JFrame implements IPiezaListener {

	private Container contents;// Contenedor de componentes que se emplearan para contruir la interfaz grafica
								// del tablero
	private JButton[][] cuadrados = new JButton[8][8];
	private Color negro = Color.gray;
	private Pieza pieza;

	//private JPanel contentPane;
	

	private ImageIcon caballob = new ImageIcon("caballob.png");
	private ImageIcon caballon = new ImageIcon("caballon.png");

	private Icon peonb = new ImageIcon("peonb.png");
	private Icon peonn = new ImageIcon("peonn.png");

	private Icon damab = new ImageIcon("damab.png");
	private Icon daman = new ImageIcon("daman.png");

	private Icon reyb = new ImageIcon("reyb.png");
	private Icon reyn = new ImageIcon("reyn.png");

	private Icon torreb = new ImageIcon("torreb.png");
	private Icon torren = new ImageIcon("torren.png");

	private Icon alfilb = new ImageIcon("alfilb.png");
	private Icon alfiln = new ImageIcon("alfiln.png");

	
	//Variables GLOBALES
	int columna;
	int fila;
	boolean segundoClick = false;
	Icon icono;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TableroGui frame = new TableroGui();
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
	public TableroGui() {

		JFrame frame = new JFrame();
		
		BorderLayout borderLayout = new BorderLayout(); //EN PROCESO
		GridLayout gridLayout = new GridLayout(8, 8);
		
		JPanel panelCentralPosta = new JPanel(gridLayout);//EN PROCESO

		frame.add(panelCentralPosta, BorderLayout.CENTER);//EN PROCESO
		
		JMenuBar barra=new JMenuBar();//EN PROCESO
		JMenuItem comenzar=new JMenuItem("New Game");//EN PROCESO
		JMenuItem salir=new JMenuItem("Exit");//EN PROCESO
		
		barra.add(comenzar);//EN PROCESO
		barra.add(salir);//EN PROCESO
		
		contents = getContentPane();//EN PROCESO
		
		contents.setLayout(gridLayout);
		

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				cuadrados[i][j] = new JButton();
				if ((i + j) % 2 != 0) {
					cuadrados[i][j].setBackground(negro);
				}
				contents.add(cuadrados[i][j]);
				cuadrados[i][j].addActionListener(b);// Se le agrega la accion tipica de JButton
			}
		}

		colocarPiezasTablero ();
		


		setSize(500, 500);
		setResizable(false);
		setLocationRelativeTo(null); // Centra la ventana
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	
		// setBounds(100, 100, 450, 300);
		// contentPane = new JPanel();
		// contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		// setContentPane(contentPane);
		// contentPane.setLayout(new GridLayout(1, 0, 0, 0));
	}

	public void colocarPiezasTablero () {
		cuadrados[0][0].setIcon(torren);
		cuadrados[0][1].setIcon(caballon);
		cuadrados[0][2].setIcon(alfiln);
		cuadrados[0][3].setIcon(reyn);
		cuadrados[0][4].setIcon(daman);
		cuadrados[0][5].setIcon(alfiln);
		cuadrados[0][6].setIcon(caballon);
		cuadrados[0][7].setIcon(torren);

		for (int i = 0; i < cuadrados.length; i++) {
			cuadrados[1][i].setIcon(peonn);
		}

		cuadrados[7][0].setIcon(torreb);
		cuadrados[7][1].setIcon(caballob);
		cuadrados[7][2].setIcon(alfilb);
		cuadrados[7][3].setIcon(reyb);
		cuadrados[7][4].setIcon(damab);
		cuadrados[7][5].setIcon(alfilb);
		cuadrados[7][6].setIcon(caballob);
		cuadrados[7][7].setIcon(torreb);

		for (int i = 0; i < cuadrados.length; i++) {
			cuadrados[6][i].setIcon(peonb);
		}
	}
	
	
	BotonPresionado b = new BotonPresionado();

	private class BotonPresionado implements ActionListener {
		/*
		 * Con esta logica el usuario va a presionar dos veces el tablero La primera vez
		 * sera sobre la pieza que quiere mover La segunda sera el boton a donde lo
		 * quiere mover
		 * 
		 */

		@Override
		public void actionPerformed(ActionEvent e) { // accion tipica de JButton es ser precionado
			Object fuente = e.getSource(); // Se guarda el boton apretado

			// Distinguimos que boton causo el efecto
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					if (fuente == cuadrados[i][j]) {
						botonApretado(i, j);
						return;
					}
				}
			}

		}

		public void botonApretado(int i, int j) {
			/*
			 * En el primer click si hay una pieza entra en el segundo if En el segundo
			 * click se mueve la pieza
			 */

			if (segundoClick) {
				if ((i != fila) || (j != columna)) {
					cuadrados[i][j].setIcon(icono);
					cuadrados[fila][columna].setIcon(null);
					segundoClick = false;
					icono = null;
					return;
				}
			}
			if (cuadrados[i][j].getIcon() != null) {
				icono = cuadrados[i][j].getIcon();
				fila = i;
				columna = j;
				segundoClick = true;
				return;
			}

		}
	}


	@Override
	public void piezaMovida(Pieza p, Celda c1, Celda c2) {
		
		
		Icon iconAux = cuadrados[c1.getFila()][c1.getColumna()].getIcon();
		//Icon iconAux = p.getIcon; //ESTO ES LO QUE CREO QUE TENDRIAMOS QUE HACER
		//PORQUE POR ALGO TE MANDAN LA PIEZA SINO NO SE, SINO PIEZA ESTA AL PEDO
		cuadrados[c2.getFila()][c2.getColumna()].setIcon(iconAux);//agarro el icono
		cuadrados[c1.getFila()][c1.getColumna()].setIcon(null);//lo muevo
		return;

	}

	@Override
	public void piezaComida(Pieza p) {
		// TODO Auto-generated method stub

	}
}
