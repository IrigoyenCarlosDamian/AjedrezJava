package pieza;

import excepciones.*;
import java.util.ArrayList;

import ajedrez.*;

public class Peon extends Pieza {
	// private boolean esPrimerMovimiento; podriamos tener una varaible es primer
	// movimneto y en base a esa preguntamos para hacer la comparacion

	/* Constructores */

	public Peon(Celda celda, Equipo equipo) {
		super(celda, equipo);
	}

	/* Metodos */
	@Override
	public ArrayList<Celda> getMovimientosPosibles() {
		ArrayList<Celda> listaCelda = new ArrayList<Celda>();
		boolean sePuede = true;
		Tablero tablero = super.getEquipo().getAjedrez().getTablero();// Tramos al tablero
		Celda celdaActual = this.getCelda();// Celda origen donde esta la pieza actualmente
		Celda mov = new Celda(0, 0);// Celda a la que se mueve el Peon
		int movimientoNormal, primerMovimiento;
		if (this.getEquipo().getNombre() == "Blanca") {
			primerMovimiento = -2;
			movimientoNormal = -1;
		} else {
			primerMovimiento = 2;
			movimientoNormal = 1;
		}
		// Movimiento Normal de a una celda
		mov = tablero.getCelda(celdaActual.getFila() + movimientoNormal, celdaActual.getColumna());
		try {
			if (mov.puedeIngresar(this)) {
				listaCelda.add(mov);
			}
		} catch (PiezaAliadaException e) {// No puede ingresar
			System.out.println("Pieza aliada en celda no se puede ingresar");
			sePuede = false;

		} catch (PiezaEnemigaException e) {
			System.out.println("Pieza enemiga en celda no se puede ingresar");
			sePuede = false;
		}
		// Movimiento de a 2 celdas
		if (sePuede) {
			mov = tablero.getCelda(celdaActual.getFila() + primerMovimiento, celdaActual.getColumna());
			try {
				if (mov.puedeIngresar(this)) {
					listaCelda.add(mov);
				}
			} catch (PiezaAliadaException e) {// No puede ingresar
				System.out.println("Pieza aliada en celda no se puede ingresar");

			} catch (PiezaEnemigaException e) {
				System.out.println("Pieza enemiga en celda no se puede ingresar");
			}
		}
		// Movimiento para comer a la DERECHA
		mov = tablero.getCelda(celdaActual.getFila() + movimientoNormal, celdaActual.getColumna() + 1);
		try {
			if (mov.puedeIngresar(this)) {
				System.out.println("No hay enemigo");
			}
		} catch (PiezaAliadaException e) {// No puede ingresar
			System.out.println("Pieza aliada en celda no se puede ingresar");

		} catch (PiezaEnemigaException e) {
			listaCelda.add(mov);
		}
		
		// Movimiento para comer a la IZQUIERDA
		mov = tablero.getCelda(celdaActual.getFila() + movimientoNormal, celdaActual.getColumna() - 1);
		try {
			if (mov.puedeIngresar(this)) {
				System.out.println("No hay enemigo");
			}
		} catch (PiezaAliadaException e) {// No puede ingresar
			System.out.println("Pieza aliada en celda no se puede ingresar");

		} catch (PiezaEnemigaException e) {
			listaCelda.add(mov);
		}
		

		/*
		 * Excepciones: validarMovimiento Se puede intercambiar por una Exception
		 * 
		 * 
		 */

		return listaCelda;
	}

	public boolean primerMovimientoPeon() {
		/*
		 * Consulta si es el primer movimiento del peon
		 */
		if (this.getEquipo().getNombre() == Ajedrez.BLANCA) {
			if (this.getCelda().getFila() == 6)
				return true;
		} else {
			if (this.getCelda().getFila() == 1)
				return true;
		}
		return false;
	}

	@Override
	public void piezaMovida(Pieza pieza, Celda celdaOrigen, Celda celdaDestino) {
		// this.getMovimientosPosibles();

	}

	@Override
	public void piezaComida(Pieza pieza) {
		// TODO Auto-generated method stub

	}

	@Override
	public String toString() {
		return super.toString() + "P";
	}

}
