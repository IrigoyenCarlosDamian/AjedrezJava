package pieza;

import excepciones.*;
import java.util.ArrayList;

import ajedrez.Celda;
import ajedrez.Equipo;
import ajedrez.Tablero;
import excepciones.FueraDeTableroException;
import excepciones.PiezaAliadaException;
import excepciones.PiezaEnemigaException;

/**
 * El Alfil viene dado por una celda y un equipo 
 * @author Carlos
 *
 */
public class Alfil extends Pieza {

	public Alfil(Celda celda, Equipo equipo) {
		super(celda, equipo);
		super.setPuntos(4);
	}

	
	@Override
	/**
	 * calculo los movimientos posilbes del Alfil en el turno de su equipo 
	  */
	public ArrayList<Celda> getMovimientosPosibles() {
		ArrayList<Celda> listaCelda = new ArrayList<Celda>();
		Celda c = this.getCelda();// Celda origen donde esta la pieza actualmente
		Tablero tablero = getTablero();// Tramos al tablero

		movimiento(c, tablero, listaCelda, -1, -1); // NOR-OESTE
		movimiento(c, tablero, listaCelda, -1, +1); // NOR-ESTE
		movimiento(c, tablero, listaCelda, +1, +1); // SUR-ESTE
		movimiento(c, tablero, listaCelda, +1, -1); // SUR-OESTE

		return listaCelda;
	}

	/**
	 * 
	 * @param c
	 * @param tablero
	 * @param listaCelda
	 * @param fila
	 * @param columna
	 * 	  Se enlistan las celdas posibles segun los movimientos del  alfil si en fila
		  viene un +1 y culumna 0 significa movimiento SUR si en fila viene un -1 y
		  culumna 0 significa movimiento NORTE si en columna viene un +1 y fila 0
		  significa movimiento ESTE si en columna viene un -1 y fila 0 significa
		  movimiento OESTE
		  
	*/
	private void movimiento(Celda c, Tablero tablero, ArrayList<Celda> listaCelda, int fila, int columna) {
		
		boolean sePuede = true;
		Celda mov = new Celda(0, 0);
		while (sePuede) {
			try {
				mov = tablero.getCelda(c.getFila() + fila, c.getColumna() + columna);// Celda a donde se avanza
				try {
					if (mov.puedeIngresar(this)) {// Unicamente devuelve verdad si esta vacia la celda
						listaCelda.add(mov);
					}
				} catch (PiezaAliadaException e) {
					sePuede = false; // Se termina la trayectoria a esa direccion
				} catch (PiezaEnemigaException e1) {
					listaCelda.add(mov);
					this.getEquipo().setJugadaConPrioridad(mov, mov.getPieza(), this);
					sePuede = false; // Se termina la trayectoria a esa direccion
				}
			} catch (FueraDeTableroException e) {
				sePuede = false;
			}

			if (columna != 0)
				if (columna < 0) {
					columna--;
				} else {
					columna++;
				}
			if (fila != 0)
				if (fila < 0) {
					fila--;
				} else {
					fila++;
				}
		}
	}

	public String toString() {
		return super.toString() + "A";
	}

}
