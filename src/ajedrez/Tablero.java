package ajedrez;

import excepciones.*;
import java.util.ArrayList;
import pieza.*;

/**
 * Clase Que define el tablero de ajedres 
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
	 * Metodo que se encarga de crea el tablero 
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
	 * Dada una fila y una columna pasada por parame
	 * @param fila valor entero entre 0 y 7 
	 * @param columna valor entero entre 0 y 7
	 * @throws FueraDeTableroException cuando la fila y/o columna pasada por parameto no corresponde a una valida se lanza la excepcion 
	 * @return Retorna una celda del tablero 
	 * 
	 */
	public Celda getCelda(int fila, int columna) throws FueraDeTableroException {

		if ((fila >= 0 && fila <= 7) && (columna >= 0 && columna <= 7)) {
			return this.celda[fila][columna];
		} else {
			throw new FueraDeTableroException();
		}
	}

	/**
	 * mueve la pieza que se manda por parametro a la fila y columna que se envian
	 * 
	 * @param pieza es la pieza que va a ser movida en el tablero
	 * @param fila es la fila destino de la pieza enviada por parametro
	 * @param columna es la columna destion de la pieza  enviada por parametro 
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
	 * de comer a dicha pieza 
	 * @param pieza  es la pieza de la cual se quiere evaluar si existen piezas del equipo contrario que son capaces de comerla 
	 * @return devevuele losMatones[ArrayList con las piezas que puede comer a la pieza enviada ]
	 */
	public ArrayList<Pieza> quienesMatan(Pieza pieza) {
		ArrayList<Pieza> losMatones = new ArrayList<Pieza>(); // defino un arrayList de piezas para verificar posibles piezas que puede comer a la enviada por parametro 
		int filaAliada = pieza.getCelda().getFila(); // obtengo la fila de la pieza que me envian 
		int columnaAliada = pieza.getCelda().getColumna(); // obtengo la columna de la pieza que me envian 
		Equipo equipoEnemigo = Ajedrez.getSingletoneInstancia().getEquipoContrario(pieza.getEquipo()); // obtengo el equipo contario al de la pieza que me envian 

		for (Pieza piezaEnemiga : equipoEnemigo.getPiezas()) {
			if (piezaEnemiga.getEstaViva() == true) {

				for (Celda celdaEnemiga : piezaEnemiga.getMovimientosPosibles()) { // del equipo enemigo calculo las jugadas posibles
					int filaEnemiga = celdaEnemiga.getFila(); //  obtengo la fila de un movimiento posible
					int columnaEnemiga = celdaEnemiga.getColumna(); // obtengo la columna de un movimiento posible 
					if ((filaEnemiga == filaAliada) && (columnaEnemiga == columnaAliada)) { // si la fila y columa de la pieza alida es igual a la de un movimientoPosible
																							//del equipo contrario la piezaEnemiga puede comer a la pieza aliada 
						losMatones.add(piezaEnemiga); // agrego la pieza al arrayList
					}
				}
			}
		}

		return losMatones; // aqui devuelvo la pieza que pueden matar a la pieza 
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
	/**
	 * 
	 * @return Retorna una celda del tablero 
	 */
	public Celda[][] getCelda() {
		return this.celda;
	}

}
