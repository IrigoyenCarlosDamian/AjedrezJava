package equipos;

import util.*;
import java.util.ArrayList;
import java.util.Random;

import ajedrez.Ajedrez;
import ajedrez.Celda;
import ajedrez.Equipo;
import ajedrez.Jugada;
import excepciones.FueraDeTableroException;
import grafica.TableroGui;
import interfaces.IJugador;
import pieza.Pieza;
import pieza.Rey;
import util.Esperar;

/**
 * El equipo se crea con un nombre su array de piezas correspondiente y
 * referencia a Ajedrez efectua sus movimientos priorizando comer las piezas del
 * equipo contrario con el puntaje mas alto
 * 
 * @author Carlos
 *
 */
public class EquipoMaster extends Equipo {
	private Jugada jugadaConPrioridad; // jugada con prioridad De equipo master para priorizar comer la pieza de mayor
										// puntaje

	/* Construcotres */
	public EquipoMaster(String nombre) {
		super(nombre);
	}

	/* Metodos */

	/* Geters y Seters */

	/**
	 * Metodo Que setea la jugada con prioridad del Equipo master Jugada con
	 * Prioridad es una jugada donde se puede comer una pieza Si hay una jugada
	 * existente se comparan los ptjes de las piezas La jugada con Prioridad siempre
	 * tendra la pEemiga con mayor puntaje
	 * 
	 * @param c es la celda a donde esta la pieza enemiga
	 * @param pEnemiga es la pieza Enemiga de la cual se ve su puntaje
	 * @param pAliada  es la pieza que va a realizar la jugada
	 * 
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

	@Override
	public Jugada jugar() {

		this.jugadaConPrioridad = null; // atributo para determinar una jugada proiritaria al inicio de la jugada es
										// null

		ArrayList<Jugada> jugadas = new ArrayList<Jugada>(); // array de jugadas de Equipo Master
		this.limpiarJugadasConPrioridad();
		for (Jugada j : this.calcularJugadsPosibles()) { // calculo las jugadas posibles del equipo
			jugadas.add(j);
		}
		
		Random random = new Random();
		
		if (this.getJugadasConPrioridad().size() > 0) { // si existe una jugada con prioriada[en la que puede comer]

			for (Jugada jugada : this.getJugadasConPrioridad()) {
				int filaEnemiga = jugada.getFila();
				int columnaEnemiga = jugada.getColumna();
				Celda cEnemiga;
				try {
					cEnemiga = this.getAjedrez().getTablero().getCelda(filaEnemiga, columnaEnemiga);
					Pieza pEnemiga = this.getAjedrez().getTablero().getCelda(filaEnemiga, columnaEnemiga).getPieza();
					setJugadaConPrioridad(cEnemiga, pEnemiga, jugada.getPieza());
				} catch (FueraDeTableroException e) {
					System.out.println("Movimiento Fuera de tablero");
				}
			}
			// Tenemos posibles piezas enemigas que se pueden comer
			Esperar.esprerar(); // delay de 2 segundos para ver bien los movimientos de las piezas
			return this.jugadaConPrioridad;

		} else { // sino existen jugadas en las quese puede comer 
			int movimientoRandom = random.nextInt(jugadas.size()); // elegimos un movimiento al azar entre 0 y la
																	// cantidad de jugadas posibles 
			Esperar.esprerar(); //delay de 2 segundos para poder ver bn los movimientos de las piezas 
			return jugadas.get(movimientoRandom); // devuelvo la jugada;
		}
	}

}