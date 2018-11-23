package interfaces;

import ajedrez.Equipo;
import pieza.Pieza;

public interface IJuegoListener {
	void equipoEnJaque(Equipo equipo);
	void turnoActual(Equipo equipo);
	void JuegoIniciado();
	void juegoFinalizado();

}
