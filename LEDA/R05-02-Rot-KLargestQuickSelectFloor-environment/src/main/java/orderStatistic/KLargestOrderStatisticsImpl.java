package orderStatistic;

/**
 * Uma implementacao da interface KLargest que usa estatisticas de ordem para 
 * retornar um array com os k maiores elementos de um conjunto de dados/array.
 * 
 * A k-esima estatistica de ordem de um conjunto de dados eh o k-esimo menor
 * elemento desse conjunto. Por exemplo, considere o array [4,8,6,9,12,1]. 
 * A 3a estatistica de ordem eh 6, a 6a estatistica de ordem eh 12.
 * 
 * Note que, para selecionar os k maiores elementos, pode-se pegar todas as 
 * estatisticas de ordem maiores que k. 
 * 
 * Requisitos do algoritmo:
 * - DEVE ser in-place. Porem, voce pode modificar o array original.
 *   Voce pode criar ainda um array de tamanho k que vai armazenar apenas
 *   os elementos a serem retornados.
 * - Voce DEVE usar alguma ideia de algoritmo de ordenacao visto em sala 
 *   para calcular estatisticas de ordem.
 * - Se a entrada for invalida, deve-se retornar um array vazio (por exemplo,
 *   ao solicitar os 5 maiores elementos em um array que soh contem 3 elementos).
 *   Este metodo NUNCA deve retornar null.
 * 
 * @author campelo and adalberto
 *
 * @param <T>
 */
public class KLargestOrderStatisticsImpl<T extends Comparable<T>> implements KLargest<T>{

	// TODO Isso tá MUITO feio
	// TODO E não funciona se tiver elemento repetido

	@Override
	public T[] getKLargest(T[] array, int k) {
		T[] out = (T[]) new Comparable[0];

		if (array.length == 0 || k < 1 || k > array.length) {
			// Caso base: não faz nada
		} else {
			out = (T[]) new Comparable[k];

			int outIndex = 0;
			for (int i = array.length - k + 1; i <= array.length; i++) {
				out[outIndex++] = orderStatistics(array, i);
			}

		}
		
		return out;
	}

	/**
	 * Metodo que retorna a k-esima estatistica de ordem de um array, usando
	 * a ideia de algum algoritmo de ordenacao visto em sala. Caso nao exista 
	 * a k-esima estatistica de ordem, entao retorna null.
	 * 
	 * Obs: o valor de k deve ser entre 1 e N.
	 * 
	 * @param array
	 * @param k
	 * @return
	 */
	private T orderStatistics(T[] array, int k) {
		T out = null;

		if (k >= 1 || k <= array.length) {
			out = findSmallest(array);

			for (int i = 1; i < k; i++) {
				out = findSmallest(array, out);
			}
		}
		
		return out;
	}

	private T findSmallest(T[] array) {
		T smallest = array[0];

		for (T elem : array) {
			if (elem.compareTo(smallest) < 0) smallest = elem;
		}

		return smallest;
	}

	private T findSmallest(T[] array, T minimumValue) {
		T smallest = findGreatest(array);

		for (T elem : array) {
			if (elem.compareTo(smallest) < 0 && elem.compareTo(minimumValue) > 0) {
				smallest = elem;
			}
		}

		return smallest;
	}

	private T findGreatest(T[] array) {
		T greatest = array[0];

		for (T elem : array) {
			if (elem.compareTo(greatest) > 0) greatest = elem;
		}

		return greatest;
	}
}
