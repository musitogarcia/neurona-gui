package modelo;

public class Neurona {
	double activacion;
	double[][] entradas;
	double[] pesos;
	double[] salidas;

	static FuncionActivacion funcion;

	public Neurona(double[][] entradas, double[] pesos, int numSalidas) {
		this.pesos = pesos;
		this.entradas = entradas;
		this.salidas = new double[numSalidas];

		funcion = new FuncionActivacion();

	}

	public void activacion(int indice) {
		double suma = 0;
		for (int i = 0; i < entradas[indice].length; i++)
			suma += pesos[i] * entradas[indice][i];

		activacion = funcion.funcionSigno(suma);

		llenaSalidas();
	}

	public void llenaSalidas() {
		for (int i = 0; i < salidas.length; i++) {
			salidas[i] = activacion;
		}
	}

	public double[] getEntradas(int indice) {
		return entradas[indice];
	}

	public double[] getPesos() {
		return pesos;
	}

	public double[] getSalidas() {
		return salidas;
	}

}
