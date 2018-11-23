package pieza;

import excepciones.*;
import java.util.ArrayList;

import ajedrez.*;

/**
 * El peon viene dado por un celda y un equipo
 * @author Carlos
 *
 */
public class Peon extends Pieza {
	/* Constructores */

	public Peon(Celda celda, Equipo equipo) {
		super(celda, equipo);
		super.setPuntos(1);
	}

	/* Metodos */
	@Override
	/**
	 * Caluclo los movimientos posibles del peon en el turno de su equipo
	 */
	public ArrayList<Celda> getMovimientosPosibles() {
		ArrayList<Celda> listaCelda = new ArrayList<Celda>();
		boolean sePuede = true;
		Equipo blancas= new Equipo(Ajedrez.getSingletoneInstancia().BLANCA);
		Tablero tablero = this.getTablero();// Tramos al tablero
		
		Celda celdaActual = this.getCelda();// Celda origen donde esta la pieza actualmente
		Celda mov = new Celda(0, 0);// Celda a la que se mueve el Peon
		int movimientoNormal, primerMovimiento,filaInicial;
		if (this.getEquipo().equals(blancas)) {
			primerMovimiento = -2;
			movimientoNormal = -1;
			filaInicial = 6;
		} else {
			primerMovimiento = 2;
			movimientoNormal = 1;
			filaInicial = 1;
		}

		// Movimiento Normal de a una celda
		try {
			
			mov = tablero.getCelda(celdaActual.getFila() + movimientoNormal, celdaActual.getColumna());
			try {
				if (mov.puedeIngresar(this)) {
					listaCelda.add(mov);
				}
			} catch (PiezaAliadaException e) {// No puede ingresar
				// System.out.println("Pieza aliada en celda no se puede ingresar");
				sePuede = false;

			} catch (PiezaEnemigaException e) {
				// System.out.println("Pieza enemiga en celda no se puede ingresar");
				sePuede = false;
			}
		} catch (FueraDeTableroException e) {
			sePuede = false;
		}

		// Movimiento de a 2 celdas
		try {
			if (filaInicial == celdaActual.getFila()) {
				if (sePuede) {
					mov = tablero.getCelda(celdaActual.getFila() + primerMovimiento, celdaActual.getColumna());
					try {
						if (mov.puedeIngresar(this)) {//Celda vacia
							listaCelda.add(mov);
						}
					} catch (PiezaAliadaException e) {// No puede ingresar
						// System.out.println("Pieza aliada en celda no se puede ingresar");

					} catch (PiezaEnemigaException e) {
						// System.out.println("Pieza enemiga en celda no se puede ingresar");
					}
				}
			}
		} catch (FueraDeTableroException e) {
		}

		// Movimiento para comer a la DERECHA
		try {
			mov = tablero.getCelda(celdaActual.getFila() + movimientoNormal, celdaActual.getColumna() + 1);
			try {
				if (mov.puedeIngresar(this)) {//Celda vacia
					// System.out.println("No hay enemigo");
				}
			} catch (PiezaAliadaException e) {// No puede ingresar
				// System.out.println("Pieza aliada en celda no se puede ingresar");

			} catch (PiezaEnemigaException e) {

				this.getEquipo().setJugadaConPrioridad(mov, mov.getPieza(), this);// Se genera una jugada con Prioridad

				listaCelda.add(mov);
			}

		} catch (FueraDeTableroException e) {
		}

		// Movimiento para comer a la IZQUIERDA
		try {
			mov = tablero.getCelda(celdaActual.getFila() + movimientoNormal, celdaActual.getColumna() - 1);
			try {
				if (mov.puedeIngresar(this)) {
					// System.out.println("No hay enemigo");
				}
			} catch (PiezaAliadaException e) {// No puede ingresar
				// System.out.println("Pieza aliada en celda no se puede ingresar");

			} catch (PiezaEnemigaException e) {

				this.getEquipo().setJugadaConPrioridad(mov, mov.getPieza(), this);// Se genera una jugada con
																					// Prioridad

				listaCelda.add(mov);
			}

		} catch (FueraDeTableroException e) {
			// TODO: handle exception
		}

		return listaCelda;
	}

	
	@Override
	public String toString() {
		return super.toString() + "P";
	}

}
