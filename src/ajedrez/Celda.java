package ajedrez;

import excepciones.*;
import pieza.Pieza;

/**
 * La Celda Se crea con una fila y columna fija a posterior se le setea la pieza correspondiente
 * @author Carlos 
 */
public class Celda {
	private int fila;
	private int columna;
	private Pieza pieza;

	/* Constructores */
	public Celda(int fila, int columna) {
		this.fila = fila;
		this.columna = columna;
	}

	/**
	 * Determina si la pieza enviada por parameto puede ingresar a una celda
	 * @param pieza es la pieza de la cual se evalua si puede ingresar  a la celda 
	 * @throws PiezaAliadaException si en la celda a la que se quiere ingresar esta ocupada por una peiza del mismo equipo entonces se lanza  la excepcion
	 * @throws PiezaEnemigaException  si la celda a la que se quiere ingresar esta ocupada por una pieza del equipo contrario se lanza esta excepcion 
	 * 
	 * @return retorna verdadero si la celda a la que se quiere ingresar no pose pieza    
	 */
	public boolean puedeIngresar(Pieza pieza) throws PiezaAliadaException, PiezaEnemigaException {
		

		if(this.pieza==null) {
			return true;
		}else {
			if (this.getPieza().getEquipo().getNombre() != pieza.getEquipo().getNombre()) {
				throw new PiezaEnemigaException(); // debo de comer 
			} else {
				throw new PiezaAliadaException(); // no es un movimiento valido 
			}
		}
	}

	/**
	 * @param equipo es el equipo la pieza de la que se esta evaluando el movimiento actual 
	 * @return Si el equipo pasado por parametro es distinto al equipo de
	 * pieza ubicado en la celda devuelve TRUE sino devuelve false si
	 * no hay pieza en la celda devuelve un false
	 */

	public boolean estaOcupadaEquipoContrario(Equipo equipo) {
		if (equipo.getNombre() != this.getPieza().getEquipo().getNombre()) {
			return true;
		}

		return false;
	}

	/* Geters y seters */
	/**
	 * 
	 * @return retorna una fila
	 */
	public int getFila() {
		return fila;
	}
	/**
	 * 
	 * @return retorna una columna 
	 */
	public int getColumna() {
		return columna;
	}

	public void setFila(int fila) {
		this.fila = fila;
	}

	public void setColumna(int columna) {
		this.columna = columna;
	}
	/**
	 * 
	 * @return retorna la pieza  de una celda 
	 */
	public Pieza getPieza() {
		return pieza;
	}

	public void setPieza(Pieza pieza) {
		this.pieza = pieza;
	}

}
