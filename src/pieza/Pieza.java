package pieza;

import java.util.ArrayList;
import ajedrez.Tablero;
import interfaces.IPiezaListener;
import ajedrez.Ajedrez;
import ajedrez.Celda;
import ajedrez.Equipo;

/**
 * Clase Abstracta para la jerarquia de las piezas una piesa viene dada por una
 * celda un equipo y un puntaje 
 * 
 * @author Carlos
 *
 */
public abstract class Pieza {
	private Celda celda;
	private Equipo equipo;
	private boolean estaViva;
	private int puntos;
	private ArrayList<IPiezaListener> piezaListener;
	private Tablero tablero = Ajedrez.getSingletoneInstancia().getTablero();

	/* Constructores */
	public Pieza() {

	}

	public Pieza(Celda celda, Equipo equipo) {
		this.equipo = equipo;
		this.celda = celda;
		this.estaViva = true;
		this.piezaListener = new ArrayList<IPiezaListener>();
	}

	/* Metodos */
	public abstract ArrayList<Celda> getMovimientosPosibles();

	/* Geters y Seters */

	public Tablero getTablero() {
		return this.tablero;
	}

	public Celda getCelda() {
		return celda;
	}

	public void setCleda(Celda celda) {
		// Cambio de Estado celda()
		// notificar a los escuchadores()
		// piezaMovida
		for (IPiezaListener escuchador : piezaListener) {
			escuchador.piezaMovida(this, this.celda, celda);
		}
		// TODO [CORRECCION] Ajedrez deberia escuchar a las celdas, para luego poder
		// informar del evento

		this.celda = celda;
	}
	/*
	 * Retorna los puntos correspondientes a una pieza 
	 */
	public int getPuntos() {
		return this.puntos;
	}

	public void setPuntos(int ptos) {
		this.puntos = ptos;
	}
	/**
	 * 
	 * @return Retorna el  equipo de una pieza 
	 */
	public Equipo getEquipo() {
		return this.equipo;
	}
	/**
	 * 
	 * @return si una piesa esta viva o  no 
	 */
	public boolean getEstaViva() {
		return this.estaViva;
	}

	public void setEstaViva(boolean estaMuerta) { // unicamente se llama al setEstaViva cuando la pieza es comida
													// el atributo estaViva se pone en true al momento de crar la pieza
		// Cambio de Estado celda()
		// notificar a los escuchadores()
		// piezaComida
		this.estaViva = estaMuerta;
		for (IPiezaListener escuchador : piezaListener) {
			escuchador.piezaComida(this);

		}
	}

	public void removePiezaListener(IPiezaListener listener) {
		this.piezaListener.remove(listener);
	}

	public void addPiezaListener(IPiezaListener listener) {
		this.piezaListener.add(listener);
	}

	@Override
	public String toString() {
		String s = "";
		if (this.equipo.getNombre() == Ajedrez.BLANCA) {
			s = "B";
		} else {
			s = "N";
		}
		return s;
	}

}
