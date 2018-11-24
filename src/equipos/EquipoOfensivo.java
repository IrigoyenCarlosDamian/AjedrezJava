package equipos;

import java.util.ArrayList;
import java.util.Random;
import ajedrez.Ajedrez;
import ajedrez.Equipo;
import ajedrez.Jugada;
import grafica.TableroGrafico;
import pieza.Pieza;
import util.Esperar;

/**
 * Clase Equipo Ofensivo  efectua un movimiento random de todos los movimientos posibles de sus piezas
 * @author Carlos
 *
 */
public class EquipoOfensivo extends Equipo {

	/* Construcotres */
	public EquipoOfensivo(String nombre) {
		super(nombre);
	}
	/**
	 *	Devuelve el rey del equipo que tiene el turno actual  
	 * @return
	 */
	public Pieza getRey() {
		return super.getRey();
	}
	
	@Override
	/**
	 * Calcula las jugadas posibles en el turno actual del equipo 
	 */
	
	public Jugada jugar() {
		ArrayList<Jugada> jugadas = new ArrayList<Jugada>();
		for (Jugada j : this.calcularJugadsPosibles()) {
			jugadas.add(j);
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
	
		return super.calcularJugadsPosibles();
	}

	@Override
	/**
	 * 
	 */
	public boolean equals(Object obj) {

		return super.equals(obj);
	}


	public Ajedrez getAjedrez() {
		return getAjedrez();
	}

	public String getNombre() {
		return super.getNombre();
	}

	public ArrayList<Pieza> getPiezas() {
		return getPiezas();
	}
	public void setTablero(TableroGrafico tableroGrafico) {
		tableroGrafico.addIJugadorListener(this);
	}
	
	@Override
	public void botonApretado(int fila, int columan) {
		
	}

}
