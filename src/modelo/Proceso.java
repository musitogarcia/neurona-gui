package modelo;

import java.util.ArrayList;

public class Proceso {
	static Neurona neurona;

	static double[][] entradas;
	static double[] pesos;
	static double[] salidas;
	static ArrayList<String[]> resultados;

	static int numResultados, numGeneracion;

	public Proceso(double[][] entradas, double[] salidas, double[] pesos) {
		Proceso.entradas = entradas;
		Proceso.salidas = salidas;
		Proceso.pesos = pesos;
		Proceso.resultados = new ArrayList<String[]>();
		numResultados = 1;
		numGeneracion = 0;
	}

	public ArrayList<String[]> generar() {
		neurona = new Neurona(entradas, pesos, numResultados);
		int indice = 0;
		while (indice < entradas.length) {
			neurona.activacion(indice);
			String[] arreglo = { String.valueOf(numGeneracion), String.valueOf(neurona.getEntradas(indice)[0]),
					String.valueOf(neurona.getEntradas(indice)[1]), String.valueOf(neurona.getEntradas(indice)[2]),
					String.valueOf(neurona.getPesos()[0]), String.valueOf(neurona.getPesos()[1]),
					String.valueOf(neurona.getPesos()[2]), String.valueOf(salidas[indice]),
					String.valueOf(neurona.getSalidas()[0]) };
			resultados.add(arreglo);
			if (neurona.getSalidas()[0] == salidas[indice])
				indice++;
			else {
				for (int i = 0; i < pesos.length; i++)
					pesos[i] = convertir((pesos[i]) + (salidas[indice] * entradas[indice][i]));
				indice = 0;
				numGeneracion++;
			}
		}
		return resultados;
	}

	public static double convertir(double valor) {
		return Math.round(valor * 100) / 100.0;
	}

}
