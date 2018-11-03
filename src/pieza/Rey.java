package pieza;

import java.util.ArrayList;

import ajedrez.Celda;
import ajedrez.Equipo;
import ajedrez.Tablero;
import excepciones.FueraDeTableroException;
import excepciones.NoHayPiezaException;
import excepciones.PiezaAliadaException;
import excepciones.PiezaEnemigaException;

public class Rey extends Pieza {

	public Rey(Celda celda, Equipo equipo) {
		super(celda, equipo);
		super.setPuntos(15);
	}


	@Override
	public ArrayList<Celda> getMovimientosPosibles() {
		ArrayList<Celda> listaCelda = new ArrayList<Celda>();
		Tablero tablero = getTablero();// Tramos al tablero
		Celda mov = new Celda(0, 0);
		for (int i = this.getCelda().getFila() - 1; i < this.getCelda().getFila() + 2; i++) {
			for (int j = this.getCelda().getColumna() - 1; j < this.getCelda().getColumna() + 2; j++) {
				try {
					mov = tablero.getCelda(i, j);
					try {
						if (mov.puedeIngresar(this)
								&& ((this.getCelda().getFila() != i) && (this.getCelda().getFila() != j))) {// Celda
																											// vacia y
																											// no es la
																											// origen
							listaCelda.add(mov);
						}
					} catch (PiezaAliadaException e) {
						// Hay pieza y es aliada no se puede ingresar
					} catch (PiezaEnemigaException e) {
						// Hay piesa y es Enemiga, se puede comer
						this.getEquipo().setJugadaConPrioridad(mov, mov.getPieza(), this);// Se genera una jugada con
																							// Prioridad

						listaCelda.add(mov);
					}
				} catch (FueraDeTableroException e) {
					// TODO: handle exception
				}
			}
		}
		return listaCelda;
	}

	public String toString() {
		return super.toString() + "R";
	}

	/* Geters y Seters */

}
