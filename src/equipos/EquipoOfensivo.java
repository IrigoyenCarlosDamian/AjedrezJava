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
 * Clase Equipo Ofensivo efectua un movimiento random de todos los movimientos
 * posibles de sus piezas
 * 
 * @author Carlos
 *
 */
public class EquipoOfensivo extends Equipo {

	private ArrayList<Jugada> jugadasPrioritarias;

	/* Construcotres */
	public EquipoOfensivo(String nombre) {
		super(nombre);
		this.jugadasPrioritarias = new ArrayList<Jugada>();
	}

	@Override
	public Jugada jugar() {

		/*
		 * Si hay jugadas donde se pueda comer alguna pieza enemiga:
		 * 	Se elige una de ellas al azar
		 * Sino:
		 * 	Se devuelve un movimiento posible random
		 * */
		ArrayList<Jugada> jugadas = new ArrayList<Jugada>();
		this.limpiarJugadasConPrioridad();
		for (Jugada j : this.calcularJugadsPosibles()) {
			jugadas.add(j);
		}
		int i = jugadas.size();
		System.out.println("Cantidad de movimientos posibles: " + i);
		Random random = new Random();
		if (this.getJugadasConPrioridad().size() > 0) {
			// Tenemos posibles piezas enemigas que se pueden comer
			int movimientoRandom = random.nextInt(this.getJugadasConPrioridad().size()); // elegimos un movimiento al azar entre 0 y la
																	// cantidad
			Esperar.esprerar();
			return this.getJugadasConPrioridad().get(movimientoRandom);

		} else {
			int movimientoRandom = random.nextInt(jugadas.size()); // elegimos un movimiento al azar entre 0 y la cantidad
			Esperar.esprerar(); // TODO [CORRECCION] Para que esto?
			return jugadas.get(movimientoRandom); // devuelvo la jugada;
		}
	}

	public void agregarJugadaPrioritaria(Jugada jugada) {
		this.jugadasPrioritarias.add(jugada);
	}
}
