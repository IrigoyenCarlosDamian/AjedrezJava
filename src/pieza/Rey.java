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
		super.setPuntos(15);
	}

	public ArrayList<Celda> getMovimientosPosibles() {
		ArrayList<Celda> listaCelda = new ArrayList<Celda>();
		Celda c = this.getCelda();// Celda origen donde esta la pieza actualmente
		Tablero tablero = getTablero();// Tramos al tablero

		movimiento(c, tablero, listaCelda, -1, 0); // NORTE
		movimiento(c, tablero, listaCelda, -1, +1); // NOR-ESTE
		movimiento(c, tablero, listaCelda, 0, +1); // ESTE
		movimiento(c, tablero, listaCelda, +1, +1); // SUR-ESTE
		movimiento(c, tablero, listaCelda, +1, 0); // SUR
		movimiento(c, tablero, listaCelda, +1, -1); // SUR-OESTE
		movimiento(c, tablero, listaCelda, 0, -1); // OESTE
		movimiento(c, tablero, listaCelda, -1, -1); // NOR-OESTE
		return listaCelda;
	}

	private void movimiento(Celda c, Tablero tablero, ArrayList<Celda> listaCelda, int fila, int columna) {
		/*
		 * Se enlistan las celdas posibles segun los movimientos d la torre si en fila
		 * viene un +1 y culumna 0 significa movimiento SUR si en fila viene un -1 y
		 * culumna 0 significa movimiento NORTE si en columna viene un +1 y fila 0
		 * significa movimiento ESTE si en columna viene un -1 y fila 0 significa
		 * movimiento OESTE
		 */

		Celda mov = new Celda(0, 0);
		try {
			mov = tablero.getCelda(c.getFila() + fila, c.getColumna() + columna);// Celda a donde se avanza
			try {
				if (mov.puedeIngresar(this)) {// Unicamente devuelve verdad si esta vacia la celda
					listaCelda.add(mov);
				}
			} catch (PiezaAliadaException e) {
				// Se termina la trayectoria a esa direccion
			} catch (PiezaEnemigaException e1) {
				listaCelda.add(mov);
				this.getEquipo().setJugadaConPrioridad(mov, mov.getPieza(), this);// Se genera una jugada con
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
