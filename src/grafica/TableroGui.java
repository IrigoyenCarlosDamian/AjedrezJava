package grafica;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import ajedrez.Ajedrez;
import ajedrez.Celda;
import ajedrez.Tablero;
import excepciones.FueraDeTableroException;
import interfaces.IJugador;
import interfaces.IPiezaListener;
import pieza.*;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Tablero grafico para el juego de ajedrez
 * 
 * @author Carlos
 *
 */
public class TableroGui extends JPanel implements IPiezaListener {

	private Container contents;// Contenedor de componentes que se emplearan para contruir la interfaz grafica
	// del tablero
	private BotonPresionado b = new BotonPresionado();
	private JButton[][] cuadrados = new JButton[8][8];
	private Color negro = Color.gray;
	private ArrayList<IJugador> jugadores = new ArrayList<IJugador>();
	/* Defino los inconos de las piezas */
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

	/**
	 * Create the panel.
	 */
	public TableroGui() {
		// setLayout(new GridLayout(1, 0, 0, 0));
		GridLayout gridLayout = new GridLayout(8, 8);

		contents = this;// EN PROCESO

		contents.setLayout(gridLayout);

		// setLayout(gridLayout);

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				cuadrados[i][j] = new JButton();
				if ((i + j) % 2 != 0) {
					cuadrados[i][j].setBackground(negro);
				}
				contents.add(cuadrados[i][j]);

				cuadrados[i][j].addActionListener(b);// Se le agrega la accion tipica de JButto
			} // esto es unicamente si el modo de juego es maquina vs maquina
		} // se desactiva los eventos del mouse en ese modo

		//colocarPiezasTablero();

		// setSize(500, 500);
		setVisible(true);
	}

	/**
	 *  setea el icono de la pieza a  los botones del gridlouyt
	 */

	public void colocarPiezasTablero() {
		Tablero tablero = Ajedrez.getSingletoneInstancia().getTablero();
		Celda[][] matrizCeldas = tablero.getCelda();
		
		for (int i = 0; i < matrizCeldas.length; i++) {
			for (int j = 0; j < matrizCeldas.length; j++) {
				if (matrizCeldas[i][j].getPieza() instanceof Caballo) {
					if (i==0) {//NEGROS
						cuadrados[i][j].setIcon(caballon);
					} else {//BLANCAS
						cuadrados[i][j].setIcon(caballob);
					}
				} else if(matrizCeldas[i][j].getPieza() instanceof Torre) {
					if (i==0) {//NEGROS
						cuadrados[i][j].setIcon(torren);
					} else {//BLANCAS
						cuadrados[i][j].setIcon(torreb);
					}
				} else if(matrizCeldas[i][j].getPieza() instanceof Alfil) {
					if (i==0) {//NEGROS
						cuadrados[i][j].setIcon(alfiln);
					} else {//BLANCAS
						cuadrados[i][j].setIcon(alfilb);
					}
				} else if(matrizCeldas[i][j].getPieza() instanceof Rey) {
					if (i==0) {//NEGROS
						cuadrados[i][j].setIcon(reyn);
					} else {//BLANCAS
						cuadrados[i][j].setIcon(reyb);
					}
				} else if(matrizCeldas[i][j].getPieza() instanceof Dama) {
					if (i==0) {//NEGROS
						cuadrados[i][j].setIcon(daman);
					} else {//BLANCAS
						cuadrados[i][j].setIcon(damab);
					}
				} else if(matrizCeldas[i][j].getPieza() instanceof Peon) {
					if (i==1) {//NEGROS
						cuadrados[i][j].setIcon(peonn);
					} else {//BLANCAS
						cuadrados[i][j].setIcon(peonb);
					}
				}
			}
		}
	}


	/** Con esta logica el usuario va a presionar dos veces el tablero La primera vez
		sera sobre la pieza que quiere mover La segunda sera el boton a donde lo
		quiere mover
	 * @author Carlos
	 */
	
	private class BotonPresionado implements ActionListener {
		

		@Override
		public void actionPerformed(ActionEvent e) { // accion tipica de JButton es ser precionado
			Object fuente = e.getSource(); // Se guarda el boton apretado

			// Distinguimos que boton causo el efecto
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					if (fuente == cuadrados[i][j]) {
						botonApretado(i, j);
						i=8;
						j=8;
						return;
					}
				}
			}

		}

		/**En el primer click si hay una pieza entra en el segundo if En el segundo
		 click se mueve la pieza 
		 * @param i fila 
		 * @param j comunas 
		 */
		public void botonApretado(int i, int j) {
			for(IJugador escuchador: jugadores) {
				escuchador.botonApretado(i, j);
			}

		}
	}

	@Override
	/**
	 * Setea el icono a un boton dada la celda origne y la celda destino
	 */
	public void piezaMovida(Pieza p, Celda c1, Celda c2) {

		Icon iconAux = cuadrados[c1.getFila()][c1.getColumna()].getIcon();
		cuadrados[c2.getFila()][c2.getColumna()].setIcon(iconAux);// agarro el icono
		cuadrados[c1.getFila()][c1.getColumna()].setIcon(null);// lo muevo
		VentanaPrincipal.movimientoDePiza(p, c2);
		return;

	}


	public void setPieza(Pieza piecitaSignificativa) {
		piecitaSignificativa.addPiezaListener(this);
	}

	public void addIJugadorListener(IJugador jugador) {
		this.jugadores.add(jugador);
	}

	public void removeIJugadorListener(IJugador jugador) {
		this.jugadores.remove(jugador);
	}

	@Override
	public void piezaComida(Pieza p) {
		Ajedrez.getSingletoneInstancia().piezaEliminada(p);
	}

}
