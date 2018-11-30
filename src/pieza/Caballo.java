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
 * @author Carlos
 *	El Caballo viene dado por una celda y un equipo 
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

		Celda mov = new Celda(0, 0); // Celda destinada a los posibles movimientos del caballo
		int origen = c.getColumna() + c.getFila(); // Sumatoria de las coordenadas origen de la celda
		boolean filaColumnaDistinta;

		// Se recorre la porcion de tablero donde se puede desplazar el caballo
		movimiento(mov, c, listaCelda, 1, 2);
		movimiento(mov, c, listaCelda, 1, -2);
		movimiento(mov, c, listaCelda, -1, 2);
		movimiento(mov, c, listaCelda, -1, -2);

		movimiento(mov, c, listaCelda, 2, 1);
		movimiento(mov, c, listaCelda, 2, -1);
		movimiento(mov, c, listaCelda, -2, 1);
		movimiento(mov, c, listaCelda, -2, -1);

		return listaCelda;
	}
	/**
	 * 
	 * @param mov es la celda posible a la que puede ir el caballo 
	 * @param c es la celda  actual del caballo 
	 * @param listaCelda ArrayList con todas las pobiles celdas a la que puede ir el caballo 
	 * @param i fila  es la posible fila  a la que se puede dirigir el caballo
	 * (en el llamdo es a movimento es - si efecuta un movimeinto asia una columan anterioa a la que se encuentra)
	 * @param j columna idem que en fila  
	 * calulo las celdas posibles para el caballo 
	 * 
	 */
	private void movimiento(Celda mov, Celda c, ArrayList<Celda> listaCelda, int i, int j) {
		try {
			mov = this.getTablero().getCelda(c.getFila() + i, c.getColumna() + j);
			try {
				if (mov.puedeIngresar(this)) {// No hay pieza en la celda
					listaCelda.add(mov);
				}
			} catch (PiezaAliadaException e) {// No puede ingresar
				// System.out.println("Pieza aliada en celda no se puede ingresar");
			} catch (PiezaEnemigaException e) {
				this.getEquipo().agregarJugadaConPrioridad(mov, mov.getPieza(), this);// Se genera una jugada con Prioridad
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
