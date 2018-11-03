package interfaces;

import ajedrez.Celda;
import pieza.Pieza;

public interface IPiezaListener {

	void piezaMovida (Pieza p, Celda c1, Celda c2);
	void piezaComida(Pieza p);
}
