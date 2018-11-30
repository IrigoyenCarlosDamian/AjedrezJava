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
 * La Dama viene dada por una celda y un equipo 
 * @author Carlos
 *
 */
public class Dama extends Pieza {

	public Dama(Celda celda, Equipo equipo) {
		super(celda, equipo);
		this.setPuntos(10);
	}



	@Override
	/**
	 * Calculo los movimientos posibles de la dama en el turno de su equipo 
	 */
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
	 * Metodo:  El metodo "movimiento" sirve para enlistar las celdas en las que puede moverse una pieza en una direccion dada.
	 * Se enlistan las celdas libres en esa direccion hasta que se topa con una pieza, si esta pieza es enemiga
	 * tambien se enlista, en caso contrario no se agrega a la lista.
	 * Si esa pieza es enemiga, ademas de enlistarla en la lista que viene por parametro,
	 * tambien se agrega en la lista de jugadas posibles del equipo.
	 * Esta direccion esta dada por los parametros "fila" y "columna".
	 * La direcccion puede ser Hacia:
	 * Norte con fila y columna (-1,0) 
	 * Nor-Este con fila y columna (-1,+1)
	 * Este con fila y columna (0,+1)
	 * Sur-Este con fila y columna (+1,+1)
	 * Sur  con fila y columna (+1,0)
	 * Sur-Oeste con fila y columna (+1,-1)
	 * Oeste  con fila y columna (0,-1)
	 * Nor-Oeste 	con fila y columna (-1,-1)
	 * @param c: Celda donde se encuentra la pieza que se va a mover actualmente
	 * @param listaCelda: Lista donde se cargaran las celdas donde se puede mover la pieza
	 * @param fila: Solo puede venir un 1, -1 o un 0. La fila puede aumentar(1), decrementar(-1) o quedar fija(0).
	 * @param columna: Solo puede venir un 1, -1 o un 0. La columna puede aumentar(1), decrementar(-1) o quedar fija(0).    
	 */

	
	private void movimiento(Celda c, ArrayList<Celda> listaCelda, int fila, int columna) {
		

		boolean sePuede = true;
		Celda mov = new Celda(0, 0);
		while (sePuede) {
			try {
				mov = this.getTablero().getCelda(c.getFila() + fila, c.getColumna() + columna);// Celda a donde se avanza
				try {
					if (mov.puedeIngresar(this)) {// Unicamente devuelve verdad si esta vacia la celda
						listaCelda.add(mov);
					}
				} catch (PiezaAliadaException e) {
					sePuede = false; // Se termina la trayectoria a esa direccion
				} catch (PiezaEnemigaException e1) {
					listaCelda.add(mov);
					this.getEquipo().agregarJugadaConPrioridad(mov, mov.getPieza(), this);// Se genera una jugada con
																						// Prioridad
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
		return super.toString() + "D";
	}

}
