package ajedrez;
import pieza.Pieza;

public class Celda {
	private int fila;
	private int columna;
	private Pieza pieza;
	
	/*Constructores*/
	public Celda(int fila, int columna) {
		this.fila= fila;
		this.columna= columna;
	}
	
	/*Metodos*/
	public boolean puedeIngresar(Pieza pieza) {
		boolean  condicion=true; // varaible para determinar el valor de verdad de el ingreso a una celda 
		
		return condicion; // evaluo el movimento de una pieza y retorno el valor de verdar(si puede ingresar o no)
	}
	public boolean estaOcupadaEquipoContrario(Equipo equipo) {
		boolean condicion=true;// varaible para determinar el valor de verdad de el ingreso a una celda
		return condicion; //evaluo el movimento de una pieza y retorno el valor de verdar(si puede ingresar o no)
	}
	
	
	/*Geters y seters*/
	
	public int getFila() {
		return fila;
	}
	public int getColumna() {
		return columna;
	}
	
	public Pieza getPieza() {
		return pieza;
	}
	public void setPieza(Pieza pieza) {
		this.pieza = pieza;
	}
	
}
