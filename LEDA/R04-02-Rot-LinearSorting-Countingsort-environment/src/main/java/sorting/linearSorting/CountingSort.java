package sorting.linearSorting;

import sorting.AbstractSorting;

/**
 * Classe que implementa a estratégia de Counting Sort vista em sala.
 *
 * Procure evitar desperdício de memória: AO INVÉS de alocar o array de contadores
 * com um tamanho arbitrariamente grande (por exemplo, com o maior valor de entrada possível),
 * aloque este array com o tamanho sendo o máximo inteiro presente no array a ser ordenado.
 *
 * Seu algoritmo deve assumir que o array de entrada nao possui numeros negativos,
 * ou seja, possui apenas numeros inteiros positivos e o zero.
 *
 */
public class CountingSort extends AbstractSorting<Integer> {

	@Override
	public void sort(Integer[] array, int leftIndex, int rightIndex) {
		if (leftIndex <= rightIndex && leftIndex >= 0 && rightIndex >= 0 && leftIndex < array.length && rightIndex < array.length && array.length > 1) {
			
			// Criando o array de frequência a partir do maior valor do array original
			Integer[] frequencyArray = new Integer[findGreatestInteger(array, leftIndex, rightIndex) + 1];
			
			// Populando o array de frequência com zeros
			for (int i = 0; i < frequencyArray.length; i++) {
				frequencyArray[i] = 0;
			}

			// Calculando a frequência dos elementos no array de frequência
			for (int i = leftIndex; i <= rightIndex; i++) {
				frequencyArray[array[i]]++;
			}

			// Tornando o array de frequência um array da cumulativa
			for (int i = 1; i < frequencyArray.length; i++) {
				frequencyArray[i] += frequencyArray[i - 1];
			}
			
			// Criando um novo array, a fim de ordená-lo
			Integer[] sortedArray = new Integer[array.length];

			// Populando o novo array com zeros
			for (int i = 0; i < sortedArray.length; i++) {
				sortedArray[i] = 0;
			}

			// Colocando os elementos do array original de forma ordenada no novo array
			for (int i = rightIndex; i >= leftIndex; i--) {
				sortedArray[frequencyArray[array[i]] - 1] = array[i];
				frequencyArray[array[i]]--;
			}

			// Transferindo os elementos do novo array para o array original
			for (int i = 0; i < array.length; i++) {
				array[i] = sortedArray[i];
			}

		}
	}

	private Integer findGreatestInteger(Integer[] array, int leftIndex, int rightIndex) {
		Integer greatest = array[leftIndex];

		for (int i = leftIndex + 1; i <= rightIndex; i++) {
			if (array[i] > greatest) {
				greatest = array[i];
			}
		}

		return greatest;
	} 
}
