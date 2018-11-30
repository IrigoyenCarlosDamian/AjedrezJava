package ajedrez;


import pieza.Pieza;
/**
 * Clase que implemmenta la jugada de un equipa 
 * Una jugada viene dada por una  pieza,una fila y una columna 
 * @author Carlos
 *
 */
public class Jugada {
	private Pieza pieza;
	private int fila;
	private int columna;

	/* Constructores */
	public Jugada() {

	}

	public Jugada(Pieza pieza, int fila, int columna) {
		this.pieza = pieza;
		this.fila = fila;
		this.columna = columna;
	}
	/* Geters y Seters */
	/**
	 * 
	 * @return Devuelve una peiza 
	 */
	public Pieza getPieza(){
		return pieza;
	}

	public void setPieza(Pieza pieza) {
		this.pieza = pieza;
	}
	/**
	 * 
	 * @return Retorna la fila correspondiente a una celda 
	 */
	public int getFila() {
		return fila;
	}

	public void setFila(int fila) {
		this.fila = fila;
	}
	/**
	 * 
	 * @return Retorna la Columana correspondiente a una celda 
	 */
	public int getColumna() {
		return columna;
	}

	public void setColumna(int columna) {
		this.columna = columna;
	}

}
