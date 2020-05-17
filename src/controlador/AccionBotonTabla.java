package controlador;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JToggleButton;

import vista.Ventana;

public class AccionBotonTabla implements ItemListener {
	Ventana ventana;

	public AccionBotonTabla(Ventana ventana) {
		this.ventana = ventana;
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		int filas = ventana.getModeloTabla().getRowCount();
		for (int i = 0; i < filas; i++)
			ventana.getModeloTabla().removeRow(0);
		JToggleButton boton = (JToggleButton) e.getItem();
		String[][] valores = { { "1", "0", "0" }, { "1", "1", "0" }, { "1", "0", "1" }, { "1", "1", "1" } };
		if (boton.isSelected()) {
			String[][] tabla = { { "1", "0", "1" }, { "1", "1", "0" } };
			for (int i = 1; i < valores.length - 1; i++)
				for (int j = 0; j < valores[i].length; j++)
					valores[i][j] = tabla[i - 1][j];
		}
		for (int i = 0; i < ventana.getCajasEntradas().length; i++)
			for (int j = 0; j < ventana.getCajasEntradas()[i].length; j++)
				ventana.getCajasEntradas()[i][j].setText(valores[i][j]);
	}

}
