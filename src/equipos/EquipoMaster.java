package equipos;

import util.*;
import java.util.ArrayList;
import java.util.Random;

import ajedrez.Ajedrez;
import ajedrez.Celda;
import ajedrez.Equipo;
import ajedrez.Jugada;
import grafica.TableroGrafico;
import interfaces.IJugador;
import pieza.Pieza;
import pieza.Rey;
import util.Esperar;
/**
 * El equipo se crea con un nombre
 * su array de piezas correspondiente y referencia a Ajedrez
 *  efectua sus movimientos priorizando comer las piezas del equipo contrario 
 * @author Carlos
 *
 */
public  class EquipoMaster extends Equipo {
	private Jugada jugadaConPrioridad;

	/* Construcotres */
	public EquipoMaster(String nombre) {
		super(nombre);
	}
	/**
	 *	Devuelve el rey del equipo con el actual  
	 * @return
	 */
	public Pieza getRey() {
		 return super.getRey();
	}

	/* Metodos */
	
	@Override
	/*
	 * Calcula las jugadas posibles de las piezas del equipo dado un puntaje  que posen las piezas como atributo
	 * prioriza comer la pieza con mayor puntaje 
	 * 
	 * */
	public Jugada jugar() {
		ArrayList<Jugada> jugadas = new ArrayList<Jugada>();

		this.jugadaConPrioridad = null; // atributo para determinar una jugada proiritaria
		
		for (Jugada j : this.calcularJugadsPosibles()) {
			jugadas.add(j);
		}
		/*Si esta instruccion da distinto de null, significa que hay algo para comer y para
		  nuestra logica es una jugada prioritaria
		 */
		if (this.jugadaConPrioridad != null) {
			if(jugadaConPrioridad.getPieza() != null)
				return this.jugadaConPrioridad;
		}
		

		int i = jugadas.size();
		System.out.println("Cantidad de movimientos posibles: "+i);
		Random random = new Random(); // Instanciamos la clase Random
		int movimientoRandom = random.nextInt(jugadas.size()); // elegimos un movimiento al azar entre 0 y la cantidad
																// de movimientos posibles
		Esperar.esprerar();
		return jugadas.get(movimientoRandom); // devuelvo la jugada;
	}
	
	/**
	 *  Retorna las jugadas posibles para una pieza dada (del arraylist) 
	 * @return
	 */
	public ArrayList<Jugada> calcularJugadsPosibles() {
		ArrayList<Jugada> jugadasPosibles = new ArrayList<Jugada>();// arraylist con las jugadas para una pieza en
																	// particular

		for (Pieza p : super.getPiezas()) { // el array list de las piezas de un equipo
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
	public boolean equals(Object obj) {
		return super.equals(obj);
	
	}

	/* Geters y Seters */
	
	/**
	 * 
	 * @param c
	 * @param pEnemiga
	 * @param pAliada
	     Jugada con Prioridad es una jugada donde se puede comer una pieza enemiga 
		 c es la celda a donde esta la pieza enemiga 
		 pEnemiga es la pieza Enemiga
		 pAliada es la pieza que va a realizar la jugada 
		 Si hay una jugada existente se comparan los ptjes de las piezas La jugada con Prioridad siempre tendra la pEemiga con mayor puntaje	 
	 */

	public void setJugadaConPrioridad(Celda c, Pieza pEnemiga, Pieza pAliada) {
		
		 
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
		return getAjedrez();
	}

	public String getNombre() {
		return super.getNombre();
	}

	public ArrayList<Pieza> getPiezas() {
		return super.getPiezas();
	}
	public void setTablero(TableroGrafico tableroGrafico) {
		tableroGrafico.addIJugadorListener(this);
	}
	
	@Override
	public void botonApretado(int fila, int columan) {
		
	}

}