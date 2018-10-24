package excepciones;

import ajedrez.Celda;


public class CeldaInvalidaException extends AjedrezException {
	private Celda celda;// celda fuera del tablero

	
	public CeldaInvalidaException(Celda celda){// constructor de la "excepcion"
		this.celda=celda;
	}

	public Celda getCelda() {
		return celda; // devuelvo la celda invalida 
	}

	@Override
	public String getMessage() {
		return String.format("Esta celda no esta en el tablero"+this.celda.getFila()+" "+this.getCelda().getColumna());
	}
	
}
