package pieza;

import java.util.ArrayList;

import ajedrez.Celda;
import ajedrez.Equipo;
import ajedrez.Tablero;
import excepciones.FueraDeTableroException;
import excepciones.NoHayPiezaException;
import excepciones.PiezaAliadaException;
import excepciones.PiezaEnemigaException;
/**
 * El Rey viene dado por una celda y un equipo 
 * @author Carlos
 *
 */
public class Rey extends Pieza {

	public Rey(Celda celda, Equipo equipo) {
		super(celda, equipo);
		this.setPuntos(15);
	}

	public ArrayList<Celda> getMovimientosPosibles() {
		ArrayList<Celda> listaCelda = new ArrayList<Celda>();
		Celda c = this.getCelda();// Celda origen donde esta la pieza actualmente

		movimiento(c, listaCelda, -1, 0); // NORTE
		movimiento(c, listaCelda, -1, +1); // NOR-ESTE
		movimiento(c, listaCelda, 0, +1); // ESTE
		movimiento(c, listaCelda, +1, +1); // SUR-ESTE
		movimiento(c, listaCelda, +1, 0); // SUR
		movimiento(c, listaCelda, +1, -1); // SUR-OESTE
		movimiento(c, listaCelda, 0, -1); // OESTE
		movimiento(c, listaCelda, -1, -1); // NOR-OESTE
		return listaCelda;
	}
	/**
	 * 
	 * @param c es la celda en la que se encuentra actualmente el rey
	 * @param listaCelda son todas las celdas posibles a las que piede ir el rey
	 * @param fila es una fila probable a la que el rey puede ir 
	 * @param columna es una columna probable a la que el rey puede ir 
	 *  Se enlistan las celdas posibles segun los movimientos d la torre si en fila
	 * viene un +1 y culumna 0 significa movimiento SUR si en fila viene un -1 y
	 * culumna 0 significa movimiento NORTE si en columna viene un +1 y fila 0
	 * significa movimiento ESTE si en columna viene un -1 y fila 0 significa
	 * movimiento OESTE	 
	 */
	
	private void movimiento(Celda c, ArrayList<Celda> listaCelda, int fila, int columna) {
		

		Celda mov = new Celda(0, 0);
		try {
			mov = this.getTablero().getCelda(c.getFila() + fila, c.getColumna() + columna);// Celda a donde se avanza
			try {
				if (mov.puedeIngresar(this)) {// Unicamente devuelve verdad si esta vacia la celda
					listaCelda.add(mov);
				}
			} catch (PiezaAliadaException e) {
				// Se termina la trayectoria a esa direccion
			} catch (PiezaEnemigaException e1) {
				listaCelda.add(mov);
				this.getEquipo().agregarJugadaConPrioridad(mov, mov.getPieza(), this);// Se genera una jugada con
																					// Prioridad
				// Se termina la trayectoria a esa direccion
			}
		} catch (FueraDeTableroException e) {
		}

	}

	public String toString() {
		return super.toString() + "R";
	}

	/* Geters y Seters */

}
