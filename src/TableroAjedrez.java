import javax.swing.JApplet;
import java.awt.FlowLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JCheckBoxMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TableroAjedrez extends JApplet {

	/**
	 * Create the applet.
	 */
	public TableroAjedrez() {
		
		JMenuBar Juego = new JMenuBar();
		Juego.setToolTipText("Juego");
		setJMenuBar(Juego);
		
		JMenu mnJuego = new JMenu("Juego");
		Juego.add(mnJuego);
		
		JCheckBoxMenuItem chckbxmntmIniciar = new JCheckBoxMenuItem("Iniciar");
		chckbxmntmIniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		mnJuego.add(chckbxmntmIniciar);
		
		JCheckBoxMenuItem chckbxmntmSalir = new JCheckBoxMenuItem("Salir");
		mnJuego.add(chckbxmntmSalir);
		
		JCheckBoxMenuItem chckbxmntmConfiguracion = new JCheckBoxMenuItem("Configuracion");
		mnJuego.add(chckbxmntmConfiguracion);

	}
}
