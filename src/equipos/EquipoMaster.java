package equipos;

import excepciones.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import ajedrez.Ajedrez;
import ajedrez.Celda;
import ajedrez.Equipo;
import ajedrez.Jugada;
import excepciones.FueraDeTableroException;
import pieza.Pieza;
import pieza.Rey;
/**
 * El equipo se crea con un nombre
 * su array de piezas correspondiente y referencia a Ajedrez
 * @author Carlos
 *
 */
public  class EquipoMaster extends Equipo {
	private Jugada jugadaConPrioridad;
	private ArrayList<Pieza> piezas;

	/* Construcotres */
	public EquipoMaster(String nombre) {
		super(nombre);
		this.piezas = new ArrayList<Pieza>();
	}
	/**
	 *	Devuelve el rey del equipo actual  
	 * @return
	 */
	public Pieza getRey() {
		return super.getRey();
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
	@Override
	public Jugada jugar() {
		ArrayList<Jugada> jugadas = new ArrayList<Jugada>();

		this.jugadaConPrioridad = null; // atributo para determinar una jugada proiritaria
		
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
			if (this.getNombre() != equipo.getNombre())
				return false;
		}
		return true;
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
		return this.getNombre();
	}

	public ArrayList<Pieza> getPiezas() {
		return piezas;
	}

}
