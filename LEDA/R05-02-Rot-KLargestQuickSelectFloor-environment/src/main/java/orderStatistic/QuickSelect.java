package orderStatistic;

import util.Util;

/**
 * O quickselect eh um algoritmo baseado no quicksort para
 * descobrir/selectionar, em tempo linear, a k-esima estatistica de ordem
 * (k-esimo menor elemento) de um conjunto de dados.
 *
 * O quiskselect escolhe um elemento para ser o pivot e particiona o array
 * inicial em dois subarrays da mesma forma que o quicksort (elementos menores
 * que o pivot a esquerda do pivot e elementos maiores que o pivot a direita
 * dele). Entretanto, ao inves de chamar o quicksort recursivo nas duas metades,
 * o quickselect eh executado (recursivamente) apenas na metade que contem o
 * elemento que ele procura (o k-esimo menor elemento). Isso reduz a
 * complexidade de O(n.log n) para O(n)
 *
 * @author adalberto e campelo
 *
 */
public class QuickSelect<T extends Comparable<T>> {

	/**
	 * O algoritmo quickselect usa a mesma abordagem do quicksort para calcular o
	 * k-esimo menor elemento (k-esima estatistica de ordem) de um determinado
	 * array de dados comparaveis. Primeiro ele escolhe um elemento como o pivot
	 * e particiona os dados em duas partes, baseando-se no pivot (exatemente da
	 * mesma forma que o quicksort). Depois disso, ele chama recursivamente o
	 * mesmo algoritmo em apenas uma das metades (a que contem o k-esimo menor
	 * elemento). Isso reduz a complexidade de O(n.log n) para O(n).
	 *
	 * Caso o array seja vazio ou a ordem (posicao) do elemento desejado esteja
	 * fora do tamanho do array, o metodo deve retornar null.
	 *
	 *
	 * @param array
	 *            o array de dados a procurar o k-esimo menor elemento
	 *            este array normalmente nao esta ordenado
	 * @param k
	 *            a ordem do elemento desejado. 1 significa primeiro menor
	 *            elemento, 2 significa segundo menor elemento e assim por
	 *            diante
	 * @return
	 *
	 */
	public T quickSelect(T[] array, int k) {
		T out = null;

		if (k >= 1 && k <= array.length) {
			out = quickSortIdea(array, k, 0, array.length - 1);
		}

		return out;
	}

	private T quickSortIdea(T[] array, int k, int leftIndex, int rightIndex) {
		
		// A partição coloca o elemento de forma ordenada na posição dele,
		// efetivamente fazendo com que a sua posição + 1 seja a estatística de ordem.

		// Se essa posição for menor que a estatística de ordem, precisamos aplicar a
		// partição na segunda metade do array.

		// Senão, aplicamos na primeira.

		if (leftIndex >= rightIndex) {
			// Caso base
			return array[leftIndex];
		} else {
			// A partição informa a estatística de ordem do pivô (a sua posição + 1)
			int pivotOrderStatistic = partition(array, leftIndex, rightIndex);

			// Se a estatística de ordem do pivô for igual à procurada, retornamos
			// O elemento na posição do pivô.
			if (pivotOrderStatistic == k - 1) {
				return array[pivotOrderStatistic - 1];
			}

			// Se não encontrarmos a estatística de ordem desejada, procuramos em outra 
			// partição do array.
			else if (pivotOrderStatistic < k - 1) {
				return quickSortIdea(array, k, pivotOrderStatistic + 1, rightIndex);
			} else {
				return quickSortIdea(array, k, leftIndex, pivotOrderStatistic - 1);
			}
		}
	}

	private int partition(T[] array, int leftIndex, int rightIndex) {
		T pivot = array[leftIndex];

		int i = leftIndex;

		for (int j = leftIndex + 1; j <= rightIndex; j++) {
			if (array[j].compareTo(pivot) <= 0) {
				Util.swap(array, ++i, j);
			}
		}

		Util.swap(array, leftIndex, i);

		return i;
	}


}