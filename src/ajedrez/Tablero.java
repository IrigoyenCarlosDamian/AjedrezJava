package ajedrez;

import excepciones.*;
import interfaces.IPiezaListener;

import java.util.ArrayList;
import java.util.Arrays;

import pieza.*;

public class Tablero{

	public Celda[][] celda;

	/* Metodos */
	public Tablero() {
		this.celda = new Celda[8][8];
	}

	public void crear() {
		/*
		 * Se crea el tablero con sus celdas 
		 * 
		 */
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				Celda c = new Celda(i, j);
				celda[i][j] = c;
			}

		}
	}

	public Celda getCelda(int fila, int columna) throws FueraDeTableroException {
		try {
			return this.celda[fila][columna];
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new FueraDeTableroException();
		}
	}

	public void mover(Pieza pieza, int fila, int columna) {
		/*
		 * mueve la pieza que se manda por parametro a la fila y columna que se envia
		 * 
		 */

		if (this.celda[fila][columna].getPieza() != null) {
			this.celda[fila][columna].getPieza().setEstaViva(false);// Se mata la pieza
		}

		this.celda[pieza.getCelda().getFila()][pieza.getCelda().getColumna()].setPieza(null);// se elimina la pieza de
																								// la celda
		pieza.setCleda(this.celda[fila][columna]);// Se relaciona la pieza que se va a mover
		this.celda[fila][columna].setPieza(pieza);// Se coloca la pieza que se manda por parametro

	}

	public ArrayList<Pieza> quienesMatan(Pieza pieza) {
		return null; // aqui devuelvo la pieza que matan
	}

	public void getPieza(Equipo equipo) {
		// devolver la pieza del equipo
	}

	public void limpiar() {
		// refresco la pantalla
	}

	@Override
	public String toString() {
		for (int i = 0; i < 8; i++) {
			System.out.println("----------------------------------------------------------" + "\t");
			for (int j = 0; j < 8; j++) {

				if (this.celda[i][j].getPieza() != null) {
					System.out.print(this.celda[i][j].getPieza() + "\t");
				} else {
					System.out.print(" " + "\t");
				}
			}
			System.out.println("\n");

		}
		System.out.println("----------------------------------------------------------" + "\t");
		return String.format("");
	}


}
