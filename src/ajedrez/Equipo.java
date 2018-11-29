package ajedrez;

import java.util.ArrayList;
import java.util.Random;
import grafica.TableroGui;
import interfaces.IJugador;
import pieza.Pieza;
import pieza.Rey;



/**
 * El equipo se crea con un nombre
 * su array de piezas correspondiente y referencia a Ajedrez
 * @author Carlos
 *
 */
public abstract class Equipo  {
	private String nombre;
	private Ajedrez ajedrez;
	private boolean estaEnJaque;
	private ArrayList<Pieza> piezas;
	private ArrayList<Jugada> jugadasConPrioridad;

	/* Construcotres */
	public Equipo(String nombre) {
		this.ajedrez = ajedrez.getSingletoneInstancia();
		this.nombre = nombre;
		this.piezas = new ArrayList<Pieza>();
		this.jugadasConPrioridad = new ArrayList<Jugada>();
	}
	/**
	 *	Devuelve el rey del equipo actual  
	 * @return
	 */
	public Pieza getRey() {
		int j = 0;
		for (int i = 0; i < this.piezas.size(); i++) {
			if (piezas.get(i) instanceof Rey) {
				j = i;
				break;
			}
		}
		return piezas.get(j);
	}

	/* Metodos */
	 
	 /**
	  * 
	  * @return
	  */
	public abstract Jugada jugar();
	
	/**
	 *  Retorna las jugadas posibles para una pieza dada (del arraylist) 
	 * @return
	 */
	public ArrayList<Jugada> calcularJugadsPosibles() {
		ArrayList<Jugada> jugadasPosibles = new ArrayList<Jugada>();// arraylist con las jugadas para una pieza en
																	// particular

		for (Pieza p : piezas) { // el array list de las piezas de un equipo
			Jugada jugada = new Jugada();
			if (p.getEstaViva()) {
				for (Celda c : p.getMovimientosPosibles()) {
					jugada.setPieza(p);
					jugada.setFila(c.getFila());
					jugada.setColumna(c.getColumna());
					jugadasPosibles.add(jugada);
				} 
			} 
		}
		return jugadasPosibles;
	}

	@Override
	/**
	 * 
	 */
	public boolean equals(Object obj) {

		if (obj instanceof Equipo) {
			Equipo equipo = (Equipo) obj;
			if (this.nombre != equipo.nombre)
				return false;
		}
		return true;
	}

	/* Geters y Seters */
	
	// La documentacion de "pEnemigo" va en @param pEnemigo
	/**
	 * @param c es la celda a donde esta la pieza enemiga
	 * @param pEnemiga pEnemiga es la pieza Enemiga
	 * @param pAliada pAliada es la pieza que va a realizar la jugada
	     Jugada con Prioridad es una jugada donde se puede comer una pieza enemiga   	 
	 */

	public void agregarJugadaConPrioridad(Celda c, Pieza pEnemiga, Pieza pAliada) {
		/*
		 * Es una lista que contiene jugadas donde la pieza aliada 
		 *	puede comer una pieza enemiga
		 * */
		 
		Jugada j = new Jugada();
		this.jugadasConPrioridad.add(new Jugada(pAliada,c.getFila(),c.getColumna()));
		
	}
	
	public void limpiarJugadasConPrioridad () {
		this.jugadasConPrioridad.clear();
	}
	
	public ArrayList<Jugada> getJugadasConPrioridad() {
		return jugadasConPrioridad;
	}
	public Ajedrez getAjedrez() {
		return ajedrez;
	}

	public String getNombre() {
		return this.nombre;
	}

	public ArrayList<Pieza> getPiezas() {
		return piezas;
	}
}