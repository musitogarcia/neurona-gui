package vista;

import javax.swing.UIManager;

public class Main {

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
			new Ventana();
		} catch (Exception e) {
			System.out.println("Ocurrio' un error: " + e.getCause());
		}

	}

}
