package pieza;

import java.util.ArrayList;

import ajedrez.*;


public class Peon extends Pieza {
	// private boolean esPrimerMovimiento; podriamos tener una varaible es primer movimneto y en base a esa preguntamos para hacer la comparacion 
	
	
	/*Constructores*/

	public Peon(Celda celda) {
		super(celda);
	}
	
	/*Metodos*/
	@Override
	public ArrayList<Celda> getMovimientosPosibles() {
		ArrayList<Celda> listaCelda = new ArrayList<Celda>();
		Celda c = this.getCelda();// Coordenada actual de la pieza
		Tablero tablero = this.getEquipo().getAjedrez().getTablero();// Tramos al tablero
		boolean condicion = false; // Condicion de celda disponible
		int f1, f2;
		if(this.getEquipo().getNombre()=="Blanca") {
			f2 = 2;
			f1 = 1;
		} else {
			f2 = -2;
			f1 = -1;
		}
		Celda c1 = tablero.getCelda(c.getFila() + f2, c.getColumna()); // Me ubico en la celda para dos moviimentos
		// Si es el primer movimiento
		if ((c.getFila() == 1) || (c.getFila() == 6)) {// Si es su primer movimiento
			condicion = c1.puedeIngresar(this); // Si no tiene pieza
			if (condicion) {
				listaCelda.add(c1);
			}
		}
		c1 = tablero.getCelda(c.getFila() + f1, c.getColumna()); // Movimiento normal de una celda
		condicion = c1.puedeIngresar(this);// Si no tiene pieza
		if (condicion) {
			listaCelda.add(c1);// Se puede agregar a la lista
		}
		return listaCelda;
	}
	@Override
	public void piezaMovida(Pieza pieza, Celda celdaOrigen, Celda celdaDestino) {
			this.getMovimientosPosibles();
		
	}
	@Override
	public void piezaComida(Pieza pieza) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String toString() {
		return "P";
	}
	


}
