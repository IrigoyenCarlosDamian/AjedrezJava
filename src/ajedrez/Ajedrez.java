package ajedrez;

import interfaces.IJuegoListener;
import util.Esperar;
import java.util.ArrayList;

import equipos.EquipoHumano;
import excepciones.FueraDeTableroException;
import pieza.*;

/**
 * Clase administrador de juego, es la encargada de crear, controlar, y mostrar
 * todo lo relacionado al juego
 * 
 * @author carlos
 */
public class Ajedrez {
	public static final String BLANCA = "Blanca";
	public static final String NEGRA = "Negra";
	private Tablero tablero;
	private Equipo blancas;
	private Equipo negras;
	private Equipo equipoEnTurno;
	private static Ajedrez instancia = new Ajedrez();// singletone
	private ArrayList<IJuegoListener> juegoListener;

	private Ajedrez() {
		this.juegoListener = new ArrayList<IJuegoListener>();
	}

	/**
	 * devuelve la instancia de AdministradorJuego
	 * 
	 * @return
	 */
	public static Ajedrez getSingletoneInstancia() {

		return instancia;
	}

	/**
	 * 
	 * metodo que se encargara iniciar el tablero y los equipos
	 * @param equipo1 es el equipo con las pizas blancas
	 * @param equipo2 es el equipo con las piezas negras  
	 */
	public void inicarJuego(Equipo equipo1, Equipo equipo2) {
		this.tablero = new Tablero();
		this.blancas = equipo1;
		this.negras = equipo2;
		this.tablero.crear();
		try {
			crearPiezasEnTablero();
		} catch (FueraDeTableroException e) {
			e.getMessage("te estas llendo del tablero");
		}
		/**
		 * Notifico al escuhador de que se inicio el juego 
		 */
		for (IJuegoListener escuchador : juegoListener) {
			escuchador.JuegoIniciado();
		}

	}



	/**
	 * Metodo que da inicio al Juego 
	 * 
	 * @throws FueraDeTableroException excepcion que se larga si la celda de destino es una indice invalido del array
	 */
	public void comenzar() throws FueraDeTableroException {

		while (!this.esFinJuego(blancas, negras)) {
			darTurnos(this.blancas);// Tira excepcion Fuera de tablero (getCelda de Tablero)
			if (!this.esFinJuego(negras, blancas)) {// Tira excepcion Fuera de tablero (getCelda de Tablero)
				darTurnos(this.negras);
			}
		}
		
		// Se termnino la partida
		for (IJuegoListener escuchador : juegoListener) {
			escuchador.juegoFinalizado();
		}
	}
	
	/**
	 * Gestiona los turnos de los equipos duarante la partida
	 * @param equipo
	 * @throws FueraDeTableroException excepcion que se larga si la celda de destino es una indice invalido del array 
	 */
	public void darTurnos(Equipo equipo) throws FueraDeTableroException {
		for (IJuegoListener escuchador : juegoListener) {
			escuchador.turnoActual(equipo);
		}

		setEquipoEnTurno(equipo);
		Jugada jugada = equipo.jugar();
		tablero.mover(jugada.getPieza(), jugada.getFila(), jugada.getColumna());
		if (!(equipo instanceof EquipoHumano)) {
			Esperar.esprerar();
		}
	}

	private boolean esFinJuego(Equipo equipoEnTurno, Equipo equipoEnemigo) {

		if (equipoEnTurno.getRey().getEstaViva() && equipoEnemigo.getRey().getEstaViva()) {
			int i = 0;
			Pieza rey = equipoEnTurno.getRey();
			i = this.getTablero().quienesMatan(rey).size();
			if (i > 0) {
				for (IJuegoListener escuchador : juegoListener) {
					escuchador.equipoEnJaque(equipoEnTurno);
				}

			}

			return false;
		}
		for (IJuegoListener escuchador : juegoListener) {
			escuchador.juegoFinalizado();
		}
		return true;
	}

	/*
	 * 
	 * @return Retorna la instancia de tablero
	 */
	public Tablero getTablero() {
		return this.tablero;
	}

	/**
	 * Metodo encargado de  Crea las piezas en el tablero de ajedrez
	 * @throws FueraDeTableroException excepcion que se larga si la celda de destino es una indice invalido del array
	 */
	public void crearPiezasEnTablero() throws FueraDeTableroException {

		Pieza p = null;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				Celda c = this.tablero.getCelda(i, j);// FueraDeTabero
				// Se agregan las piezas

				if (j == 0 || j == 7) {// Torres
					if (i == 0) {// Negras
						c.setPieza(p = new Torre(c, negras));
					}
					if (i == 7) {// Blancas
						c.setPieza(p = new Torre(c, blancas));
					}
				}
				if (j == 1 || j == 6) {// Caballos
					if (i == 0) {// Negras
						c.setPieza(p = new Caballo(c, negras));
					}
					if (i == 7) {// Blancas
						c.setPieza(p = new Caballo(c, blancas));
					}
				}
				if (j == 2 || j == 5) {// Alfles
					if (i == 0) {// Negras

						c.setPieza(p = new Alfil(c, negras));
					}
					if (i == 7) {// Blancas
						c.setPieza(p = new Alfil(c, blancas));
					}
				}
				if (j == 3) {// Reyes
					if (i == 0) {// Negras
						c.setPieza(p = new Rey(c, negras));
					}
					if (i == 7) {// Blancas
						c.setPieza(p = new Rey(c, blancas));
					}
				}
				if (j == 4) {// Damas
					if (i == 0) {// Negras
						c.setPieza(p = new Dama(c, negras));
					}
					if (i == 7) {// Blancas
						c.setPieza(p = new Dama(c, blancas));
					}
				}

				if (i == 1) { // Peones Negros
					c.setPieza(p = new Peon(c, negras));
				}
				if (i == 6) {// Peones Blancos
					c.setPieza(p = new Peon(c, blancas));
				}

				if (1 < i && i < 6) {
					p = null;
				} else {
					if (p.getEquipo().equals(blancas)) {
						blancas.getPiezas().add(p);
					} else {
						negras.getPiezas().add(p);
					}
				}
				this.tablero.getCelda()[i][j].setPieza(p);
			}
		}
	}

	/**
	 * Metodo  que recibe como argumento un equipo y devuelve el equipo contrario
	 * @param equipo equipo del que queremos conocer su rival 
	 * @return Dado un equipo pasado por parameto devuelve el equipo contrario
	 */
	public Equipo getEquipoContrario(Equipo equipo) {

		if (equipo.getNombre() == Ajedrez.BLANCA) {
			return negras;
		} else {
			return blancas;
		}
	}
	/**
	 * notifica al escuhador que la pieza pasada por paramete
	 * @param p pieza que  fue comida 
	 * 
	 */
	public void piezaEliminada(Pieza p) {
		for (IJuegoListener escuchador : juegoListener) {
			escuchador.piezaComida(p);
		}
	}
	/**
	 * 
	 * @return Retrona el equipo blanco 
	 */
	public Equipo getBlancas() {
		return blancas;
	}
	/**
	 * 
	 * @return Retorna el equipo negro 
	 */
	public Equipo getNegras() {
		return negras;
	}
	/**
	 * 
	 * @return Retorna el equipo que tiene el turno actul 
	 */
	public Equipo getEquipoEnTurno() {
		return equipoEnTurno;
	}

	public void setEquipoEnTurno(Equipo equipoEnTurno) {
		this.equipoEnTurno = equipoEnTurno;
	}

	public void setBlancas(Equipo blancas) {
		this.blancas = blancas;
	}

	public void setNegras(Equipo negras) {
		this.negras = negras;
	}

	/**
	 * Agrega  al escuhador al array 
	 * @param listener clase que implementa la interfas IJuegoListener
	 */
	public void addJuegoListener(IJuegoListener listener) {
		this.juegoListener.add(listener);
	}
}
