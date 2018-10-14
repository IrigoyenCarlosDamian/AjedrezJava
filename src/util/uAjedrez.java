package util;
import ajedrez.*;
import pieza.*;

public abstract class uAjedrez {
	
	
	public static void crearPiezasNegras(Tablero tablero){
		/*Creo Las Torres*/
		tablero.getCelda(0,0).setPieza(new Torre(tablero.getCelda(0,0)));
		tablero.getCelda(0,7).setPieza(new Torre(tablero.getCelda(0,7)));
		
		/*Creo Los Caballos*/
		tablero.getCelda(0,1).setPieza(new Caballo(tablero.getCelda(0,1)));
		tablero.getCelda(0,6).setPieza(new Caballo(tablero.getCelda(0,6)));
		
		/*Crelo Los Alfiles*/
		tablero.getCelda(0,2).setPieza(new Alfil(tablero.getCelda(0,2)));
		tablero.getCelda(0,5).setPieza(new Alfil(tablero.getCelda(0,5)));
		/*Creo El Rey*/
		tablero.getCelda(0,3).setPieza(new Rey(tablero.getCelda(0,3)));
		/*Creo la Dama*/
		tablero.getCelda(0,4).setPieza(new Dama(tablero.getCelda(0,4)));
		/*Creo Los Peones*/
		for(int i=0;i<8;i++) {
			tablero.getCelda(1,i).setPieza(new Peon(tablero.getCelda(1,i)));
		}		
	}
	
	public static void crearPiezasBlancas(Tablero tablero){
		for(int i=0;i<8;i++) {
			tablero.getCelda(6,i).setPieza(new Peon(tablero.getCelda(6,i)));
		}
		/*Creo Las Torres*/
		tablero.getCelda(7,0).setPieza(new Torre(tablero.getCelda(7,0)));
		tablero.getCelda(7,7).setPieza(new Torre(tablero.getCelda(7,7)));
		
		/*Creo Los Caballos*/
		tablero.getCelda(7,1).setPieza(new Caballo(tablero.getCelda(7,1)));
		tablero.getCelda(7,6).setPieza(new Caballo(tablero.getCelda(7,6)));
		
		/*Crelo Los Alfiles*/
		tablero.getCelda(7,2).setPieza(new Alfil(tablero.getCelda(7,2)));
		tablero.getCelda(7,5).setPieza(new Alfil(tablero.getCelda(7,5)));
		/*Creo El Rey*/
		tablero.getCelda(7,3).setPieza(new Rey(tablero.getCelda(7,3)));
		/*Creo la Dama*/
		tablero.getCelda(7,4).setPieza(new Dama(tablero.getCelda(7,4)));
		/*Creo Los Peones*/
	}
	
	/*Asigno las  piezas a los equipos*/
		public static void asingarEquipo(Equipo equipo,int fila1,int fila2,Tablero tablero) {
			/*asigno las piezas*/
			for(int i=0;i<8;i++) {
				equipo.getPiezas().add(tablero.getCelda(fila1,i).getPieza());
				equipo.getPiezas().add(tablero.getCelda(fila2,i).getPieza());
			}
			
		}
		
		public static void darTurnos(Equipo equipo) {
			Jugada jugada = equipo.jugar();
			//this.tablero.mover(jugada.getPieza(), jugada.getFila(), jugada.getColumna());
			verificarEstadoJuego(equipo);
			mostrarJugada(jugada);
		}
		
		private static void mostrarJugada(Jugada jugada) {
			
		}
		
		private static void verificarEstadoJuego(Equipo equipo) {
			
		}
		public static void mostrarTablero(Tablero tablero) {
				System.out.print(tablero);
			}
		}

		
		


