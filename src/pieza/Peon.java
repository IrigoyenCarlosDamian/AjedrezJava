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
		this.setPuntos(1);
	}

	/* Metodos */
	@Override
	/**
	 * 	Se calculan los movimientos posibles de la Pieza Peon:
	 * 	Este metodo devuelve una lista de Celdas.
	 * 	Esta lista contiene todas las celdas en las que se puede mover la Pieza Peon.
	 * 	Segun la logica de movimiento del peon este puede moverse:
	 * 	2 casillas hacia delante si es su primer movimiento. No puede comer con este movimiento.
	 * 	1 casilla hacia adelante que es su movimiento normal. No puede comer con este movimiento.
	 * 	1 casilla en diagonal delantera para poder comer. No puede moverse si no es para comer.
	 * 	Si puede comer, ademas de enlistar en la lista que retorna, tambien agrega esta celda
	 * 	en la lista de jugadas con prioridad del equipo.
	 */
	public ArrayList<Celda> getMovimientosPosibles() {
		ArrayList<Celda> listaCelda = new ArrayList<Celda>();
		boolean sePuede = true;
		String blancas= Ajedrez.getSingletoneInstancia().BLANCA;
		Tablero tablero = this.getTablero();// Tramos al tablero
		
		Celda celdaActual = this.getCelda();// Celda origen donde esta la pieza actualmente
		Celda mov = new Celda(0, 0);// Celda a la que se mueve el Peon
		int movimientoNormal, primerMovimiento,filaInicial;
		if (this.getEquipo().getNombre()==blancas) {
			primerMovimiento = -2; // si el equipo es el de las piezas blancas se decrementa
			movimientoNormal = -1;
			filaInicial = 6;
		} else {
			primerMovimiento = 2; // si el equipo es el de las piezas negras se incrementa 
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

				this.getEquipo().agregarJugadaConPrioridad(mov, mov.getPieza(), this);// Se genera una jugada con Prioridad

				listaCelda.add(mov);
			}

		} catch (FueraDeTableroException e) {
		}

		// Movimiento para comer a la IZQUIERDA
		try {
			mov = tablero.getCelda(celdaActual.getFila() + movimientoNormal, celdaActual.getColumna() - 1);
			try {
				if (mov.puedeIngresar(this)) {
					
				}
			} catch (PiezaAliadaException e) {// No puede ingresar

			} catch (PiezaEnemigaException e) {

				this.getEquipo().agregarJugadaConPrioridad(mov, mov.getPieza(), this);// Se genera una jugada con
																					// Prioridad
				listaCelda.add(mov);
			}

		} catch (FueraDeTableroException e) {
		
		}

		return listaCelda;
	}

	
	@Override
	public String toString() {
		return super.toString() + "P";
	}

}
