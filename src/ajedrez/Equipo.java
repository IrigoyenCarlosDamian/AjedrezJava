package ajedrez;

import java.util.ArrayList;
import java.util.Random;
import grafica.TableroGui;
import interfaces.IJugador;
import pieza.Pieza;
import pieza.Rey;

/**
 * Clase Abstracta para la jerarquia De Equipos El equipo se crea con un nombre
 * y su array de piezas correspondiente y referencia a Ajedrez
 * 
 * @author Carlos
 *
 */
public abstract class Equipo {
	private String nombre;
	private Ajedrez ajedrez;
	private boolean estaEnJaque;
	private ArrayList<Pieza> piezas;
	private ArrayList<Jugada> jugadasConPrioridad; // array con las jugadasPrioritaras de un equipo(ejemplo comer a un
													// peon o a una torre)

	/* Construcotres */
	public Equipo(String nombre) {
		this.ajedrez = ajedrez.getSingletoneInstancia();
		this.nombre = nombre;
		this.piezas = new ArrayList<Pieza>();
		this.jugadasConPrioridad = new ArrayList<Jugada>();
	}

	/**
	 * 
	 * @return Retorna el rey del equipo actual
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
	 * metodi abstracto cada equipo redifinira este metodo de acuerdo a su modo de
	 * juego
	 */
	public abstract Jugada jugar();

	/**
	 * 
	 * @return Retorna las jugadas posibles para una pieza dada (del arraylist)
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
	 * equals de equipo
	 */
	public boolean equals(Object obj) {

		if (obj instanceof Equipo) {
			Equipo equipo = (Equipo) obj;
			if (this.nombre != equipo.nombre)
				return false;
		}
		return true;
	}

	/**
	 * Jugada con Prioridad es una jugada donde se puede comer una pieza enemiga
	 * 
	 * @param c es la celda a donde esta la pieza enemiga
	 * @param pEnemiga pEnemiga es la pieza Enemiga
	 * @param pAliada  pAliada es la pieza que va a realizar la jugada
	 */

	public void agregarJugadaConPrioridad(Celda c, Pieza pEnemiga, Pieza pAliada) {
		/*
		 * Es una lista que contiene jugadas donde la pieza aliada puede comer una pieza
		 * enemiga
		 */

		Jugada j = new Jugada();
		this.jugadasConPrioridad.add(new Jugada(pAliada, c.getFila(), c.getColumna()));

	}
	/*Una Vez ejecutada la jugada limpio el array
	 * */
	public void limpiarJugadasConPrioridad() {
		this.jugadasConPrioridad.clear();
	}

	/**
	 * @return Retorna el arrayList de las jugadas prioritaras de un equipo
	 */
	public ArrayList<Jugada> getJugadasConPrioridad() {
		return jugadasConPrioridad;
	}
	/**
	 * 
	 * @return Retorna la instancia de Ajedrez
	 */
	public Ajedrez getAjedrez() {
		return ajedrez;
	}
	/**
	 * 
	 * @return Retorna el nombre el equipo 
	 */
	public String getNombre() {
		return this.nombre;
	}
	/**
	 * @return Retorna el Array List de piezas del equipo
	 */
	public ArrayList<Pieza> getPiezas() {
		return piezas;
	}
}