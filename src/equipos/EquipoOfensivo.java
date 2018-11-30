package equipos;

import java.util.ArrayList;
import java.util.Random;
import ajedrez.Ajedrez;
import ajedrez.Equipo;
import ajedrez.Jugada;
import grafica.TableroGui;
import pieza.Pieza;
import util.Esperar;

/**
 * Clase Equipo Ofensivo prioriza comer siempre que se posible sino solo avanza
 * si el el tamaño del array de las jugadas en las que puede comer es mayor a 1
 * entonces elige una de esas jugadas al azar El equipo viene definido por un
 * nombre
 * 
 * @author Carlos
 *
 */
public class EquipoOfensivo extends Equipo {

	private ArrayList<Jugada> jugadasPrioritarias;

	/* Construcotres */
	public EquipoOfensivo(String nombre) {
		super(nombre);
		this.jugadasPrioritarias = new ArrayList<Jugada>(); // array de las jugaadas en las que equipo ofensivo puede
															// comer
	}

	@Override

	/*
	 * Si hay jugadas donde se pueda comer alguna pieza enemiga: Se elige una de
	 * ellas al azar Sino: Se devuelve un movimiento posible random
	 */

	public Jugada jugar() {

		ArrayList<Jugada> jugadas = new ArrayList<Jugada>();
		this.limpiarJugadasConPrioridad();
		for (Jugada j : this.calcularJugadsPosibles()) {
			jugadas.add(j);
		}

		Random random = new Random();
		if (this.getJugadasConPrioridad().size() > 0) {
			// Tenemos posibles piezas enemigas que se pueden comer
			int movimientoRandom = random.nextInt(this.getJugadasConPrioridad().size()); // elegimos un movimiento al
																							// azar entre 0 y la
			Esperar.esprerar(); // dealy de 2 segundos para poder visualizar los movimientos
			return this.getJugadasConPrioridad().get(movimientoRandom);

		} else {
			int movimientoRandom = random.nextInt(jugadas.size()); // elegimos un movimiento al azar entre 0 y la
																	// cantidad de jugadas pobiles
			Esperar.esprerar();
			return jugadas.get(movimientoRandom); // devuelvo la jugada;
		}
	}

	/**
	 * recibe una jugada por parametro y la asigna al arrayList de
	 * jugadasPrioritarias
	 * 
	 * @param jugada es una jugada con prioridad para equipo master por lo que se
	 *               debe de agregar al array
	 */
	public void agregarJugadaPrioritaria(Jugada jugada) {
		this.jugadasPrioritarias.add(jugada);
	}
}
