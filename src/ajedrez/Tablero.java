package ajedrez;

import excepciones.*;
import java.util.ArrayList;
import pieza.*;

/**
 * El Tablero esta comuesto por un array bidimensional de celdas
 * 
 * @author Carlos
 *
 */
public class Tablero {

	private Celda[][] celda;

	/* Constructor */
	public Tablero() {
		this.celda = new Celda[8][8];
	}

	/**
	 * Se crea el tablero con sus celdas
	 */
	public void crear() {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				Celda c = new Celda(i, j);
				celda[i][j] = c;
			}

		}
	}

	/**
	 * 
	 * @param fila
	 * @param columna
	 * @return
	 * @throws FueraDeTableroException Retorna una celda;
	 */
	public Celda getCelda(int fila, int columna) throws FueraDeTableroException {

		if ((fila >= 0 && fila <= 7) && (columna >= 0 && columna <= 7)) {
			return this.celda[fila][columna];
		} else {
			throw new FueraDeTableroException();
		}
	}

	/**
	 * mueve la pieza que se manda por parametro a la fila y columna que se envia
	 * 
	 * @param pieza
	 * @param fila
	 * @param columna
	 */

	public void mover(Pieza pieza, int fila, int columna) {

		if (this.celda[fila][columna].getPieza() != null) {
			this.celda[fila][columna].getPieza().setEstaViva(false);// Se mata la pieza
		}

		this.celda[pieza.getCelda().getFila()][pieza.getCelda().getColumna()].setPieza(null);// se elimina la pieza de
																								// la celda
		pieza.setCleda(this.celda[fila][columna]);// Se relaciona la pieza que se va a mover
		this.celda[fila][columna].setPieza(pieza);// Se coloca la pieza que se manda por parametro

	}

	/**
	 * Dada una pieza enviada por parameto retorana un ArrayList de las piezas capaz
	 * de comer a la pieza enviada por parametro
	 * 
	 * @param pieza
	 * @return
	 */
	public ArrayList<Pieza> quienesMatan(Pieza pieza) {
		// no se saben bien que hacen
		ArrayList<Pieza> losMatones = new ArrayList<Pieza>();
		int filaAliada = pieza.getCelda().getFila();
		int columnaAliada = pieza.getCelda().getColumna();
		Equipo equipoEnemigo = Ajedrez.getSingletoneInstancia().getEquipoContrario(pieza.getEquipo());

		for (Pieza piezaEnemiga : equipoEnemigo.getPiezas()) {
			if (piezaEnemiga.getEstaViva() == true) {

				for (Celda celdaEnemiga : piezaEnemiga.getMovimientosPosibles()) {
					int filaEnemiga = celdaEnemiga.getFila();
					int columnaEnemiga = celdaEnemiga.getColumna();
					if ((filaEnemiga == filaAliada) && (columnaEnemiga == columnaAliada)) {
						// La piezaEnemiga puede comer a la Aliada
						losMatones.add(piezaEnemiga);
					}
				}
			}
		}

		return losMatones; // aqui devuelvo la pieza que matan
	}

	/**
	 * Limpia el tablero de ajedrez
	 */
	public void limpiar() {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				celda[i][j] = null;
			}
		}

	}

	public Celda[][] getCelda() {
		return this.celda;
	}

}
