package ajedrez;

import grafica.TableroGrafico;
import grafica.VentanaPrincipal;
import interfaces.IJuegoListener;
//import grafica.TableroGui;
//import grafica.TableroGui.*;
import interfaces.IPiezaListener;
import util.Esperar;

import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;

import excepciones.FueraDeTableroException;
//import grafica.vTableroJuego;
import pieza.*;

/**
 * Clase administrador de juego, es la encargada de crear, controlar, y mostrar todo lo relacionado al 
 * 	juego
 * @author carlos
 */
public class Ajedrez {
	public static final String BLANCA = "Blanca";
	public static final String NEGRA = "Negra";
	private Tablero tablero;
	private Equipo blancas;
	private Equipo negras;
	private static Ajedrez instancia = new Ajedrez();// singletone
	private ArrayList<IJuegoListener> juegoListener;
	private boolean juegoReiniciado;
	/* Geters y Seters */ 

	private Ajedrez() {
		this.juegoListener = new ArrayList<IJuegoListener>();

	}
	/**
	 * devuelve la instancia de AdministradorJuego
	 * @return
	 */

	public static Ajedrez getSingletoneInstancia() {

		return instancia;
	}
	/** 
	 * @author carlos
	 * @param Inicializa El tablero, se crea el tablero y los equipos
	 * 
	 */
	public void inicarJuego() {
		this.juegoReiniciado = false;
		this.tablero = new Tablero();
		this.blancas= new Equipo(BLANCA);
		this.negras= new Equipo(NEGRA); 
		
		this.tablero.crear();
		try {
			crearPiezasEnTablero();
		} catch (FueraDeTableroException e) {
			// TODO: handle exception
		}
		for (IJuegoListener escuchador : juegoListener) {
			escuchador.JuegoIniciado();
		}
		mostrarTablero();

	}

	/**
	 * 
	 * @param equipo
	 * @param tablero
	 * @throws FueraDeTableroException
	 *  Gestiona los turnos de los equipos duarante la partida 
	 */
	public void darTurnos(Equipo equipo, Tablero tablero) throws FueraDeTableroException {

		for (IJuegoListener escuchador : juegoListener) {
			escuchador.turnoActual(equipo);
		}

		Jugada jugada = equipo.jugar();
		Celda mov = tablero.getCelda(jugada.getFila(), jugada.getColumna());// tira excepcion Fuera de tablero, pero no deberia salir del tablero
		tablero.mover(jugada.getPieza(), jugada.getFila(), jugada.getColumna());
		mostrarTablero();
	}
	/**
	 * Muestra el tablero en consola (las Piezas se representan mediante caracteres)
	 */

	public void mostrarTablero() {

		System.out.print(this.tablero);

	}

	/**
	 * 
	 * @throws FueraDeTableroException
	 *  Cominza la partida de ajedrez
	 */
	public void comenzar() throws FueraDeTableroException {
		// vTableroJuego vista= new vTableroJuego(this.getInstancia());
		while (!this.esFinJuego(blancas, negras)) {
			// this.frame.setTurno(BLANCA);
			darTurnos(this.blancas, tablero);// Tira excepcion Fuera de tablero (getCelda de Tablero)
			Esperar.esprerar();
			if (!this.esFinJuego(negras, blancas)) {// Tira excepcion Fuera de tablero (getCelda de Tablero)
				// this.frame.setTurno(NEGRA);
				darTurnos(this.negras, tablero);
				Esperar.esprerar();
			}
		}
		// Se termnino la partida 
		for (IJuegoListener escuchador : juegoListener) {
			escuchador.juegoFinalizado();
		}
	}

	private boolean esFinJuego(Equipo equipoEnTurno, Equipo equipoEnemigo) {

		if (equipoEnTurno.getRey().getEstaViva()  && equipoEnemigo.getRey().getEstaViva()) {
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
	 * @return
	 * Retorna la instancia de  tablero 
	 */
	public Tablero getTablero() {
		return this.tablero;
	}
	/**
	 * 
	 * @throws FueraDeTableroException
	 * Crea las piezas en el tablero de ajedrez
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
					// p.addPiezaListener(this.tableroGui);
				}

				// Se agrega como escuchador Tablero
				// p.addPiezaListener(tableroGui);

				this.tablero.celda[i][j].setPieza(p);
			}

		}

	}

	/**
	 * 
	 * @param equipo
	 * @return
	 * Dado un equipo pasado por parameto devuelve el equipo contrario 
	 */
	public Equipo getEquipoContrario(Equipo equipo) {

		if (equipo.getNombre() == Ajedrez.BLANCA) {
			return negras;
		} else {
			return blancas;
		}
	}

	public void setJuegoReiniciado(boolean juegoReiniciado) {
		this.juegoReiniciado = juegoReiniciado;
	}

	public Equipo getBlancas() {
		return blancas;
	}

	public Equipo getNegras() {
		return negras;
	}

	public void addJuegoListener(IJuegoListener listener) {
		this.juegoListener.add(listener);
	}

}
