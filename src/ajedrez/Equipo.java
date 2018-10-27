package ajedrez;

import excepciones.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import excepciones.FueraDeTableroException;
import pieza.Pieza;
import pieza.Rey;

public class Equipo {
	private String nombre;
	private Ajedrez ajedrez;
	private boolean estaEnJaque;
	private ArrayList<Pieza> piezas;
	private Jugada jugadaConPrioridad;

	/* Construcotres */
	public Equipo(String nombre) {
		this.ajedrez = ajedrez.getInstancia();
		this.nombre = nombre;
		this.piezas = new ArrayList<Pieza>();

	}

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
	/* Metodos */
	/*
	 * en esta primera instancia devolvemos un mnovimiento cualquiera de todos los
	 * disponibles para una pieza dada
	 */
	// TODO[a futuro ver de mejorar esta implementacion ]
	public Jugada jugar() {
		ArrayList<Jugada> jugadas = new ArrayList<Jugada>();

		this.jugadaConPrioridad = null;
		
		for (Jugada j : this.calcularJugadsPosibles()) {
			jugadas.add(j);
		}
		
		if (this.jugadaConPrioridad != null) {
			if(jugadaConPrioridad.getPieza() != null)
				return this.jugadaConPrioridad;
		}
		/*
		 * Si esta instruccion da distinto de null, significa que hay algo para comer y para
		 * nuestra logica es una jugada prioritaria
		 */

		
		int i = jugadas.size();
		System.out.println("Cantidad de movimientos posibles: "+i);
		Random random = new Random(); // Instanciamos la clase Random
		int movimientoRandom = random.nextInt(jugadas.size()); // elegimos un movimiento al azar entre 0 y la cantidad
																// de movimientos posibles
		return jugadas.get(movimientoRandom); // devuelvo la jugada;
	}

	/* Retorna las jugadas posibles para una pieza dada (del arraylist) */
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
			} else {
				//piezas.remove(piezas.indexOf(p));
			}
			// jugadasPosibles=p.getMovimientosPosibles(); // por cada piza calculo sus
			// movimientos posibles y los asigno al ArrayList De Jugdas
		}
		return jugadasPosibles;
	}

	@Override
	public boolean equals(Object obj) {

		if (obj instanceof Equipo) {
			Equipo equipo = (Equipo) obj;
			if (this.nombre != equipo.nombre)
				return false;
		}
		return true;
	}

	/* Geters y Seters */

	public void setJugadaConPrioridad(Celda c, Pieza pEnemiga, Pieza pAliada) {
		/*
		 * Jugada con Prioridad es una jugada donde se puede comer una pieza enemiga 
		 * c es la celda a donde esta la pieza enemiga 
		 * pEnemiga es la pieza Enemiga
		 * pAliada es la pieza que va a realizar la jugada 
		 * Si hay una jugada existente se comparan los ptjes de las piezas La jugada con Prioridad siempre tendra la pEemiga con mayor puntaje
		 */
		Jugada j = new Jugada();

		if (this.jugadaConPrioridad != null) {
			if (this.jugadaConPrioridad.getPieza().getPuntos() < pEnemiga.getPuntos()) { // Si pEnemiga es mas
																							// importantt que la se
																							// tiene
				j.setFila(c.getFila()); // Se sobrescribe la jugada
				j.setColumna(c.getColumna());
				j.setPieza(pAliada);
				this.jugadaConPrioridad = j;
			}
		} else {
			j.setFila(c.getFila()); // Si no hay jugada existente se coloca la primera que aparezca
			j.setColumna(c.getColumna());
			j.setPieza(pAliada);
			this.jugadaConPrioridad = j;
		}
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