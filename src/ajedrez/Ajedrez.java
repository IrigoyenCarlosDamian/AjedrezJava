package ajedrez;

import util.uAjedrez;
import pieza.Alfil;
import pieza.Caballo;
import pieza.Dama;
import pieza.Peon;
import pieza.Rey;
import pieza.Torre;
import pieza.*;

public class Ajedrez {
	public static final String BLANCA ="Blanca";
	public static final String NEGRA = "Negra";
	
	private Tablero tablero; 
	private Equipo blancas;
	private Equipo negras;
	private static Ajedrez instancia = new Ajedrez();// singletone 
/*Geters y Seters*/
	private Ajedrez() {
		
	}
	
	public static Ajedrez getInstancia() {
		return instancia;
	}
	public void inicarJuego() {
		/**
		 * @author carlos
		 * @param
		 * Inicializa El tablero, se crea el tablero y los equipos
		 * 
		 */
		//Tablero tablero= new Tablero();
		this.tablero= new Tablero();
		this.tablero.crear();
		this.blancas= new Equipo(BLANCA); 
		this.crearPiezas(this.blancas,this.tablero);
		this.negras= new Equipo(NEGRA);
		this.crearPiezas(this.negras,this.tablero);
		this.mostrarTablero(this.tablero);
	}
	
	private void crearPiezas(Equipo equipo, Tablero tablero) {
		/*defino las celdas con sus piezas y a las piezas les defino una celda inicial*/
		if (equipo.getNombre()==BLANCA) {
			uAjedrez.crearPiezasNegras(tablero);
			uAjedrez.asingarEquipo(this.blancas,0,1,tablero);
			
		} else {
			uAjedrez.crearPiezasBlancas(tablero);
			uAjedrez.asingarEquipo(this.negras,6,7,tablero);
		}
	
		}
		public void mostrarTablero(Tablero tablero) {
			uAjedrez.mostrarTablero(tablero);
		}
		
		public Tablero getTablero() {
			return this.tablero;
		}
		
			
		
	/*Doy Los Turnos A los equipos*/
	/**
	 * Antes de comenzar el juego lo debo de iniciar
	 */
	/*public void comenzar () {
		while(!this.esFinJuego()) {
			uAjedrez.darTurnos(this.blancas);
			if (!this.esFinJuego()) {
				uAjedrez.darTurnos(this.negras);
			}
		}
	}*/

	

/*	private boolean esFinJuego(Equipo equipo) {
		equipo.getRey().getCelda()
	}*/
				
	} 
