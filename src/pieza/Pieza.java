package pieza;

import java.util.ArrayList;
import ajedrez.Tablero;
import interfaces.IPiezaListener;
import ajedrez.Ajedrez;
import ajedrez.Celda;
import ajedrez.Equipo;

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
		//Cambio de Estado celda()
		//notificar a los escuchadores()
		//piezaMovida
		for (IPiezaListener escuchador : piezaListener) {
			escuchador.piezaMovida(this, this.celda, celda);
		}
		this.celda = celda;
	}

	public int getPuntos () {
		return this.puntos;
	}
	
	public void setPuntos (int ptos) {
		this.puntos = ptos;
	}
	
	public Equipo getEquipo() {
		return this.equipo;
	}

	public boolean getEstaViva() {
		return this.estaViva;
	}

	public void setEstaViva(boolean estaViva) {
		//Cambio de Estado celda()
		//notificar a los escuchadores()
		//piezaComida
		for (IPiezaListener escuchador : piezaListener) {
			escuchador.piezaComida(this);
		}
		this.estaViva = estaViva;
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
