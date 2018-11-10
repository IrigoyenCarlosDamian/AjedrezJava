package ajedrez;

import grafica.TableroGRAFICO;
import grafica.VentanaPrincipal;
//import grafica.TableroGui;
//import grafica.TableroGui.*;
import interfaces.IPiezaListener;
import util.Esperar;

import java.util.Scanner;

import javax.swing.JOptionPane;

import excepciones.FueraDeTableroException;
//import grafica.vTableroJuego;
import pieza.*;

public class Ajedrez {
	public static final String BLANCA = "Blanca";
	public static final String NEGRA = "Negra";
	
	private Tablero tablero;
	private TableroGRAFICO tableroGui;
	private VentanaPrincipal frame;
	private Equipo blancas;
	private Equipo negras;
	private static Ajedrez instancia = new Ajedrez();// singletone
	private  boolean juegoReiniciado;
	/* Geters y Seters */

	private Ajedrez() {

	}

	public static Ajedrez getSingletoneInstancia() {
	
		return instancia;
	}

	
	public void crearTableroGui() {
		this.tableroGui = new TableroGRAFICO();
		this.frame = new VentanaPrincipal();
		this.frame.setVisible(true);
	}
	
	public void inicarJuego() {
		/**xº
		 * @author carlos
		 * @param Inicializa El tablero, se crea el tablero y los equipos
		 * 
		 */
		// Tablero tablero= new Tablero();
		this.juegoReiniciado=false;
		this.tablero = new Tablero();
		this.blancas = new Equipo(BLANCA);
		this.negras = new Equipo(NEGRA);
		this.tablero.crear();
		try {
			crearPiezasEnTablero();
		} catch (FueraDeTableroException e) {
			// TODO: handle exception
		}
		mostrarTablero();

	}

	public void darTurnos(Equipo equipo, Tablero tablero) throws FueraDeTableroException {

		Jugada jugada = equipo.jugar();
		Celda mov = tablero.getCelda(jugada.getFila(), jugada.getColumna());// tira excepcion Fuera de tablero, pero no
																			// deberia salir del tablero
		
		tablero.mover(jugada.getPieza(), jugada.getFila(), jugada.getColumna());
		// System.out.println("Turno Del Equipo: "+equipo.getNombre());
		mostrarTablero();
	}

	public void mostrarTablero() {

		System.out.print(this.tablero);

	}

	public void comenzar() throws FueraDeTableroException {
		// vTableroJuego vista= new vTableroJuego(this.getInstancia());	
		while (!this.esFinJuego(blancas, negras)) {
			this.frame.setTurno(BLANCA);
			darTurnos(this.blancas, tablero);// Tira excepcion Fuera de tablero (getCelda de Tablero)
			Esperar.esprerar();
			if (!this.esFinJuego(negras, blancas)) {// Tira excepcion Fuera de tablero (getCelda de Tablero)
				this.frame.setTurno(NEGRA);
				darTurnos(this.negras, tablero);
				Esperar.esprerar();
			}
		}    if (blancas.getRey().getEstaViva()&& this.juegoReiniciado==false) {
					JOptionPane.showMessageDialog(null, "GANO EL EQUPO BLANCO");
				} else if(negras.getRey().getEstaViva() &&this.juegoReiniciado==false) {
					JOptionPane.showMessageDialog(null, "GANO EL EQUIPO NEGRO");
				}
				
				
	}		
	
	private boolean esFinJuego(Equipo equipo1, Equipo equipo2) {

		if (equipo1.getRey().getEstaViva() && equipo2.getRey().getEstaViva()&& this.juegoReiniciado==false) {
		
			return false;
		}
		return true;
	}

	
	//VER SI SE DEBE SACAR METODO
	public Tablero getTablero() {
		return this.tablero;
	}
	
	public TableroGRAFICO getTableroGui () {
		return this.tableroGui;
	}
	
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
					p.addPiezaListener(this.tableroGui);
				}

				// Se agrega como escuchador Tablero
				//p.addPiezaListener(tableroGui);

				this.tablero.celda[i][j].setPieza(p);
			}

		}
		
	}

	public void setJuegoReiniciado(boolean juegoReiniciado) {
		this.juegoReiniciado = juegoReiniciado;
	}
}
