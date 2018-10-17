package ajedrez;

import java.util.ArrayList;
import java.util.Arrays;

import pieza.*;

public class Tablero {

	public Celda[][] celda;

	/* Metodos */
	public Tablero() {
		this.celda = new Celda[8][8];
	}

	public void crear() {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				Celda c = new Celda(i, j);
				celda[i][j] = c;
			}

		}
	}

	public Celda getCelda(int fila, int columna) {
		return this.celda[fila][columna];
	}

	public void mover(Pieza pieza, int fila, int columna) {

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
		// return "Tablero [celda=" + Arrays.toString(celda) + "]";
		for (int i = 0; i < 8; i++) {
			System.out.println("----------------------------------------------------------"+"\t");
			for (int j = 0; j < 8; j++) {
				
				if (this.celda[i][j].getPieza() != null) {
					System.out.print(this.getCelda(i, j).getPieza() + "\t");

				}

			}
			System.out.println("\n");
		
		}
		System.out.println("----------------------------------------------------------"+"\t");
		return String.format("");
	}

}
