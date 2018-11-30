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
 * 
 * @author Carlos El Caballo viene dado por una celda y un equipo
 */
public class Caballo extends Pieza {

	/* Constructores */

	public Caballo(Celda celda, Equipo equipo) {
		super(celda, equipo);
		this.setPuntos(3);
	}

	/**
	 * Calculo los movimientos posibles del caballo en el turno de su equipo
	 */
	public ArrayList<Celda> getMovimientosPosibles() {
		ArrayList<Celda> listaCelda = new ArrayList<Celda>();
		Celda c = this.getCelda();// Celda origen donde esta la pieza actualmente

		// Se recorre la porcion de tablero donde se puede desplazar el caballo
		movimiento(c, listaCelda, 1, 2);
		movimiento(c, listaCelda, 1, -2);
		movimiento(c, listaCelda, -1, 2);
		movimiento(c, listaCelda, -1, -2);

		movimiento(c, listaCelda, 2, 1);
		movimiento(c, listaCelda, 2, -1);
		movimiento(c, listaCelda, -2, 1);
		movimiento(c, listaCelda, -2, -1);

		return listaCelda;
	}

	/**
	 * Metodo "movimiento" enlista la celda a la que se puede mover el caballo,
	 * teniendo en cuenta la fila y columna que se envia por parametro. Si hay una
	 * pieza aliada no se agrega a la lista. Si hay una pieza enemiga se agrega a la
	 * lista, y tambien se agrega a la lista de jugadas con prioridad del equipo.
	 * 
	 * @param c: celda en donde se encuentra la pieza actualmente
	 * @param listaCelda: lista en donde se va enlistar las celdas posibles
	 *        delcaballo
	 * @param fila: defase de la fila, puede ser: +1,-1,+2 o -2
	 * @param columna: defase de la columna, puede ser: +1,-1,+2 o -2
	 */
	private void movimiento(Celda c, ArrayList<Celda> listaCelda, int fila, int columna) {
		Celda mov = new Celda(0, 0); // Celda destinada a los posibles movimientos del caballo
		try {
			mov = this.getTablero().getCelda(c.getFila() + fila, c.getColumna() + columna);
			try {
				if (mov.puedeIngresar(this)) {// No hay pieza en la celda
					listaCelda.add(mov);
				}
			} catch (PiezaAliadaException e) {// No puede ingresar
				// System.out.println("Pieza aliada en celda no se puede ingresar");
			} catch (PiezaEnemigaException e) {
				this.getEquipo().agregarJugadaConPrioridad(mov, mov.getPieza(), this);// Se genera una jugada con
																						// Prioridad
				listaCelda.add(mov);
			}
		} catch (FueraDeTableroException e) {
			// Te estas yendo afuera del tablero CUIDADO!!!
		}
	}

	public String toString() {
		return super.toString() + "C";
	}

}
