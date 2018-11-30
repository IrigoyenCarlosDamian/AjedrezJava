package pieza;

import excepciones.*;
import java.util.ArrayList;
import ajedrez.*;
/**
 * LA torre viene dada por una celda y una pieza 
 * @author Carlos
 *
 */
public class Torre extends Pieza {

	/* Constructores */

	public Torre(Celda celda, Equipo equipo) {
		super(celda, equipo);
		this.setPuntos(5);
	}

	/* Metodos */
	@Override
	/**
	 * Calcula los movimientos psobile de la torre en el turno de su equipo 
	 */
	public ArrayList<Celda> getMovimientosPosibles() {
		ArrayList<Celda> listaCelda = new ArrayList<Celda>();
		Celda c = this.getCelda();// Celda origen donde esta la pieza actualmente
		
		movimiento(c, listaCelda, -1, 0); // NORTE
		movimiento(c, listaCelda, +1, 0); // SUR
		movimiento(c, listaCelda, 0, +1); // ESTE
		movimiento(c, listaCelda, 0, -1); // OESTE

		return listaCelda;
	}
		
	/**
	 * 
	 * @param c es la celda en la que esta actualmente la torre 
	 * @param listaCelda son todas las celdas a la que puede llegar a ir  la torre 
	 * @param fila es una fila probable a la que la torre puede ir 
	 * @param columna es una columna prova a la que la torre puede ir
	 *  Se enlistan las celdas posibles segun los movimientos d la torre si en fila
	    viene un +1 y culumna 0 significa movimiento SUR si en fila viene un -1 y
		culumna 0 significa movimiento NORTE si en columna viene un +1 y fila 0
		significa movimiento ESTE si en columna viene un -1 y fila 0 significa
	    movimiento OESTE
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
		return super.toString() + "T";
	}
}