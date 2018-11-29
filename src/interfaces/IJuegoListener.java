package interfaces;

import ajedrez.Equipo;
import pieza.Pieza;

public interface IJuegoListener {
	void equipoEnJaque(Equipo equipo);
	void turnoActual(Equipo equipo);
	void piezaComida(Pieza pieza);
	void JuegoIniciado();
	void juegoFinalizado();
	
	//TODO [CORRECCION] Falta metodo piezaMovida

}
