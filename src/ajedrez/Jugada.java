package ajedrez;


import pieza.Pieza;

public class Jugada {
	private Pieza pieza;
	private int fila;
	private int columna;

	public Jugada(Pieza pieza) {
		this(pieza,0,0);
	}

	public Jugada(Pieza pieza, int fila, int columna) {
		this.pieza = pieza;
		this.fila = fila;
		this.columna = columna;
	}
	
	
}
