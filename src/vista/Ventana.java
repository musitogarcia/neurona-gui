package vista;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.table.DefaultTableModel;

import controlador.AccionBoton;
import controlador.AccionBotonTabla;
import controlador.AccionBotonTipo;

public class Ventana {
	private JFrame frame;
	private JPanel[] paneles;
	private boolean[] panelesHechos;
	private JLabel[] etiquetas;
	private JTextField[][] cajasEntradas;
	private JTextField[] cajasSalidas;
	private JTextField[] cajasPesos;
	private JTable tabla;
	private DefaultTableModel modeloTabla;
	private JScrollPane scroll;
	private JButton boton;
	private String[][] datos;
	private JToggleButton[] botonesActivacion;

	public Ventana() {
		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();
		crearEtiquetas();
		crearPaneles();

		hacerPanel0(layout, gbc);
		hacerPanel1(layout, gbc);
		hacerPanel2(layout, gbc);
		hacerPanel3();
		hacerPanel4(layout, gbc);
		hacerPanel5();

		crearVentana(layout, gbc);
	}

	public void crearEtiquetas() {
		etiquetas = new JLabel[7];
		String[] textos = { "Entradas", "Umbral", "x1", "x2", "salidas", "Resultados", "Pesos iniciales" };
		for (int i = 0; i < etiquetas.length; i++)
			etiquetas[i] = new JLabel(textos[i]);
	}

	public void crearPaneles() {
		paneles = new JPanel[6];
		panelesHechos = new boolean[paneles.length];
		for (int i = 0; i < paneles.length; i++) {
			paneles[i] = new JPanel();
			panelesHechos[i] = false;
		}
	}

	public void crearCajasEntradas() {
		String[][] entradas = { { "1", "0", "0" }, { "1", "1", "0" }, { "1", "0", "1" }, { "1", "1", "1" } };
		cajasEntradas = new JTextField[4][3];
		for (int i = 0; i < cajasEntradas.length; i++)
			for (int j = 0; j < cajasEntradas[i].length; j++) {
				cajasEntradas[i][j] = new JTextField(5);
				cajasEntradas[i][j].setText(entradas[i][j]);
			}
	}

	public void crearCajasSalidas() {
		String[] salidas = { "-1", "-1", "-1", "1" };
		cajasSalidas = new JTextField[4];
		for (int i = 0; i < cajasSalidas.length; i++) {
			cajasSalidas[i] = new JTextField(5);
			cajasSalidas[i].setText(salidas[i]);
		}
	}

	public void crearCajasPesos() {
		cajasPesos = new JTextField[3];
		for (int i = 0; i < cajasPesos.length; i++)
			cajasPesos[i] = new JTextField(5);
	}
	
	public void crearBotones() {
		botonesActivacion = new JToggleButton[2];
		botonesActivacion[0] = new JToggleButton("Tabla de verdad");
		botonesActivacion[0].addItemListener(new AccionBotonTabla(this));
		botonesActivacion[1] = new JToggleButton("AND");
		botonesActivacion[1].addItemListener(new AccionBotonTipo(this));
	}

	public void crearBoton() {
		boton = new JButton("Iniciar");
		if (panelesHechos[1] && panelesHechos[4])
			boton.addActionListener(new AccionBoton(this));
	}

	public void crearTabla() {
		String[] titulos = { "Generacion", "Umbral", "x1", "x2", "w1", "w2", "w3", "salida", "resultado" };
		datos = new String[1][titulos.length];
		for (int i = 0; i < titulos.length; i++) {
			datos[0][i] = "a";
		}
		modeloTabla = new DefaultTableModel();
		modeloTabla.setColumnIdentifiers(titulos);
		tabla = new JTable(modeloTabla);
		tabla.setAutoCreateRowSorter(true);

		scroll = new JScrollPane(tabla);
	}

	public void hacerPanel0(GridBagLayout layout, GridBagConstraints gbc) {
		crearBotones();
		paneles[0].setLayout(layout);
		gbc.gridy = 0;
		gbc.gridx = 0;
		paneles[0].add(etiquetas[0], gbc);
		gbc.gridx = 1;
		paneles[0].add(botonesActivacion[0], gbc);
		gbc.gridx = 2;
		paneles[0].add(botonesActivacion[1], gbc);

		panelesHechos[0] = true;
	}

	public void hacerPanel1(GridBagLayout layout, GridBagConstraints gbc) {
		crearCajasEntradas();
		crearCajasSalidas();
		paneles[1].setLayout(layout);
		gbc.gridy = 0;
		for (int i = 1; i < 5; i++) {
			gbc.gridx = i - 1;
			paneles[1].add(etiquetas[i], gbc);
		}
		for (int i = 0; i < cajasEntradas.length; i++) {
			int k = 0;
			for (int j = 0; j < cajasEntradas[i].length; j++) {
				gbc.gridy = i + 1;
				gbc.gridx = k++;
				paneles[1].add(cajasEntradas[i][j], gbc);
			}
			gbc.gridx = k;
			paneles[1].add(cajasSalidas[i], gbc);
		}
		panelesHechos[1] = true;
	}

	public void hacerPanel2(GridBagLayout layout, GridBagConstraints gbc) {
		crearTabla();
		paneles[2].setLayout(layout);
		gbc.gridy = 0;
		gbc.gridx = 0;
		paneles[2].add(etiquetas[5], gbc);
		gbc.gridy = 1;
		paneles[2].add(scroll, gbc);

		panelesHechos[2] = true;
	}

	public void hacerPanel3() {
		paneles[3].add(etiquetas[6]);
		panelesHechos[3] = true;
	}

	public void hacerPanel4(GridBagLayout layout, GridBagConstraints gbc) {
		crearCajasPesos();
		paneles[4].setLayout(layout);
		gbc.gridy = 1;
		for (int i = 0; i < cajasPesos.length; i++) {
			gbc.gridx = i;
			paneles[4].add(cajasPesos[i], gbc);
		}

		panelesHechos[4] = true;
	}

	public void hacerPanel5() {
		crearBoton();
		paneles[5].add(boton);
		panelesHechos[5] = true;
	}

	public void crearVentana(GridBagLayout layout, GridBagConstraints gbc) {
		frame = new JFrame("Neurona");
		frame.setLayout(layout);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		if (panelesHechos[0]) {
			gbc.gridy = 0;
			gbc.gridx = 0;
			frame.add(paneles[0], gbc);
		}

		if (panelesHechos[1]) {
			gbc.gridy = 1;
			frame.add(paneles[1], gbc);
		}

		if (panelesHechos[3]) {
			gbc.gridy = 2;
			frame.add(paneles[3], gbc);
		}

		if (panelesHechos[4]) {
			gbc.gridy = 3;
			frame.add(paneles[4], gbc);
		}

		if (panelesHechos[5]) {
			gbc.gridy = 4;
			frame.add(paneles[5], gbc);
		}

		if (panelesHechos[2]) {
			gbc.gridy = 0;
			gbc.gridx = 1;
			gbc.gridheight = 6;
			frame.add(paneles[2], gbc);
		}

		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	public JTextField[] getCajasSalidas() {
		return cajasSalidas;
	}

	public JTextField[][] getCajasEntradas() {
		return cajasEntradas;
	}

	public JTextField[] getCajasPesos() {
		return cajasPesos;
	}

	public DefaultTableModel getModeloTabla() {
		return modeloTabla;
	}
}
