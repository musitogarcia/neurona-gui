package controlador;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JToggleButton;

import vista.Ventana;

public class AccionBotonTipo implements ItemListener {
	Ventana ventana;

	public AccionBotonTipo(Ventana ventana) {
		this.ventana = ventana;
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		int filas = ventana.getModeloTabla().getRowCount();
		for (int i = 0; i < filas; i++)
			ventana.getModeloTabla().removeRow(0);
		JToggleButton boton = (JToggleButton) e.getItem();
		String[] valores = { "-1", "-1", "-1", "1" };
		if (boton.isSelected()) {
			for (int i = 1; i < valores.length - 1; i++)
				valores[i] = "1";
			boton.setText("OR");
		} else
			boton.setText("AND");
		for (int i = 0; i < ventana.getCajasSalidas().length; i++)
			ventana.getCajasSalidas()[i].setText(valores[i]);
	}

}
