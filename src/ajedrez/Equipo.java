package ajedrez;

import excepciones.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import excepciones.FueraDeTableroException;
import pieza.Pieza;
import pieza.Rey;

/**
 * El equipo se crea con un nombre
 * su array de piezas correspondiente y referencia a Ajedrez
 * @author Carlos
 *
 */
public abstract class Equipo {
	private String nombre;
	private Ajedrez ajedrez;
	private boolean estaEnJaque;
	//private Jugada jugadaConPrioridad;
	private ArrayList<Pieza> piezas;

	/* Construcotres */
	public Equipo(String nombre) {
		this.ajedrez = ajedrez.getSingletoneInstancia();
		this.nombre = nombre;
		this.piezas = new ArrayList<Pieza>();

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
	
	/*
	 * en esta primera instancia devolvemos un mnovimiento cualquiera de todos los
	 * disponibles para una pieza dada
	 */
	// TODO[a futuro ver de mejorar esta implementacion ]
	/**
	 * 
	 * @return
	 * Retorna una jugada random de  todas las disponibles para un equipo dado 
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