package sorting.linearSorting;

import sorting.AbstractSorting;

/**
 * Classe que implementa do Counting Sort vista em sala. Desta vez este
 * algoritmo deve satisfazer os seguitnes requisitos:
 * - Alocar o tamanho minimo possivel para o array de contadores (C)
 * - Ser capaz de ordenar arrays contendo numeros negativos
 */
public class ExtendedCountingSort extends AbstractSorting<Integer> {

	@Override
	public void sort(Integer[] array, int leftIndex, int rightIndex) {
		if (leftIndex <= rightIndex && leftIndex >= 0 && rightIndex >= 0 && leftIndex < array.length && rightIndex < array.length && array.length > 1) {
			
			// Encontrando o maior valor e o menor valor do array, respectivamente.
			int greatest = findGreatestInteger(array, leftIndex, rightIndex);
			int smallest = findSmallestInteger(array, leftIndex, rightIndex);

			// Criando o array de frequência
			Integer[] frequencyArray = new Integer[greatest - smallest + 1];
			
			// Populando o array de frequência com zeros
			for (int i = 0; i < frequencyArray.length; i++) {
				frequencyArray[i] = 0;
			}

			// Calculando a frequência dos elementos no array de frequência
			for (int i = leftIndex; i <= rightIndex; i++) {
				frequencyArray[array[i] - smallest]++;
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
				sortedArray[frequencyArray[array[i] - smallest] - 1] = array[i];
				frequencyArray[array[i] - smallest]--;
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

	private Integer findSmallestInteger(Integer[] array, int leftIndex, int rightIndex) {
		Integer smallest = array[leftIndex];

		for (int i = leftIndex + 1; i <= rightIndex; i++) {
			if (array[i] < smallest) {
				smallest = array[i];
			}
		}

		return smallest;
	}

}
