package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import modelo.Proceso;
import vista.Ventana;

public class AccionBoton implements ActionListener {
	Ventana ventana;

	public AccionBoton(Ventana ventana) {
		this.ventana = ventana;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		boolean correcto = verificarVacios(ventana.getCajasEntradas());
		if (correcto) {
			correcto = verificarVacios(ventana.getCajasSalidas());
			if (correcto) {
				correcto = verificarVacios(ventana.getCajasPesos());
				if (correcto) {
					correcto = verificarNumeros(ventana.getCajasEntradas());
					if (correcto) {
						correcto = verificarNumeros(ventana.getCajasSalidas());
						if (correcto) {
							correcto = verificarNumeros(ventana.getCajasPesos());
							if (correcto) {
								enviarDatos(convertirDatos(ventana.getCajasEntradas()),
										convertirDatos(ventana.getCajasSalidas()),
										convertirDatos(ventana.getCajasPesos()));
							} else
								JOptionPane.showMessageDialog(null, "Valores de pesos erroneos");
						} else
							JOptionPane.showMessageDialog(null, "Valores de salidas erroneos");
					} else
						JOptionPane.showMessageDialog(null, "Valores de entradas erroneos");
				} else
					JOptionPane.showMessageDialog(null, "No puede haber pesos vacios");
			} else
				JOptionPane.showMessageDialog(null, "No puede haber salidas vacios");
		} else
			JOptionPane.showMessageDialog(null, "No puede haber entradas vacios");
	}

	public boolean verificarVacios(JTextField[] cajas) {
		boolean correcto = true;
		int i = 0;
		while (correcto && i < cajas.length)
			if (cajas[i++].getText().isEmpty())
				correcto = false;
		return correcto;
	}

	public boolean verificarVacios(JTextField[][] cajas) {
		boolean correcto = true;
		int i = 0;
		while (correcto && i < cajas.length) {
			int j = 0;
			while (j < cajas[i].length)
				if (cajas[i][j++].getText().isEmpty())
					correcto = false;
			i++;
		}
		return correcto;
	}

	public boolean verificarNumeros(JTextField[] cajas) {
		boolean correcto = true;
		int i = 0;
		while (correcto && i < cajas.length)
			try {
				Double.parseDouble(cajas[i++].getText());
			} catch (Exception e) {
				correcto = false;
			}
		return correcto;
	}

	public boolean verificarNumeros(JTextField[][] cajas) {
		boolean correcto = true;
		int i = 0;
		while (correcto && i < cajas.length) {
			int j = 0;
			while (j < cajas[i].length)
				try {
					Double.parseDouble(cajas[i][j++].getText());
				} catch (Exception e) {
					correcto = false;
				}
			i++;
		}
		return correcto;
	}

	public double[] convertirDatos(JTextField[] cajas) {
		double[] cajasNuevas = new double[cajas.length];
		int i = 0;
		for (JTextField caja : cajas)
			cajasNuevas[i++] = Double.parseDouble(caja.getText());
		return cajasNuevas;
	}

	public double[][] convertirDatos(JTextField[][] cajas) {
		double[][] cajasNuevas = new double[cajas.length][cajas[0].length];
		for (int i = 0; i < cajas.length; i++)
			for (int j = 0; j < cajas[i].length; j++)
				cajasNuevas[i][j] = Double.parseDouble(cajas[i][j].getText());

		return cajasNuevas;
	}

	public void enviarDatos(double[][] entradas, double[] salidas, double[] pesos) {
		String[] titulos = { "Generacion", "Umbral", "x1", "x2", "w1", "w2", "w3", "salida", "resultado" };
		Proceso controlador = new Proceso(entradas, salidas, pesos);
		ArrayList<String[]> lista = controlador.generar();
		String[][] resultados = new String[lista.size()][lista.get(0).length];
		for (int i = 0; i < resultados.length; i++)
			resultados[i] = lista.get(i);
		DefaultTableModel modeloTabla = ventana.getModeloTabla();
		modeloTabla.setDataVector(resultados, titulos);
		modeloTabla.fireTableDataChanged();
	}

}
