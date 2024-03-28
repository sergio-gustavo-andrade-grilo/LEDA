package problems;

public class FloorBinarySearchImpl implements Floor {
	/**
	 * Retorna o elemento do array que eh igual ou menor e mais proximo possivel dele.
	 * 
	 * @param array
	 * @return o maior inteiro pertencente ao array que seja menor que x. Caso nao exista floor, 
	 * retorna null.
	 * 
	 * Restricoes:
	 * - seu metodo deve ter tempo O(n.log n)
	 * - seu metodo DEVE usar a estrategia de busca binaria (recursiva) para buscar o floor
	 * - toda sua solucao deve ser in-place (nao pode usar memoria extra)
	 * - voce nao pode usar nenhum metodo pronto de qualquer biblioteca.
	 * - voce pode assumir que o array nao tem elementos repetidos
	 * - todo e qualquer codigo que voce implementar deve estar na classe FloorBinarySearchImpl.
	 * 
	 **/
	@Override
	public Integer floor(Integer[] array, Integer x) {
		// Não tem restrição sobre ordenar o array.
		quickSort(array, 0, array.length - 1);
		return floorStrat(array, x, 0, array.length - 1);
	}

	// Procurar o elemento do meio e guardar ele como um candidato a ser floor
	// se for menor, procura antes do meio. Senão procura depois


	// Comparar o anterior com o novo.
	// Se o novo for maior que o anterior e menor ou igual ao procurado, o novo candidato
	// é mais apropriado

	// Retorna o novo no final de tudo.

	// If x is less than nums[low], its floor doesn’t exist.
	// If x is more than equal to the nums[high], it is the floor.
	// If x is equal to the middle element, it is the floor.
	// If x is more than the middle element, the floor exists in the right subarray nums[mid…high]. Here, include mid as part of the right subarray as the middle element can also be the floor.
	// If x is less than the middle element, the floor exists in the left subarray nums[low…mid-1].

	private Integer floorStrat(Integer[] array, Integer soughtValue, int leftIndex, int rightIndex) {
		// O escopo de procura é de leftIndex a rightIndex
		Integer out = null;

		if (array.length == 0) out = null;

		if (soughtValue.compareTo(array[leftIndex]) < 0) out = null;
		if (soughtValue.compareTo(array[leftIndex]) == 0) out = array[leftIndex];
		if (soughtValue.compareTo(array[rightIndex]) >= 0) out = array[rightIndex];

		int middleIndex = (leftIndex + rightIndex) / 2;
		if (soughtValue.equals(array[middleIndex])) out = array[middleIndex];
		else if (soughtValue.compareTo(array[middleIndex]) > 0) out = floorStrat(array, soughtValue, middleIndex, rightIndex);
		else out =  floorStrat(array, soughtValue, leftIndex, middleIndex - 1);

		return out;
	}

	private void quickSort(Integer[] array, int leftIndex, int rightIndex) {
		if (leftIndex < rightIndex) {
			int pivotIndex = partition(array, leftIndex, rightIndex);
			quickSort(array, leftIndex, pivotIndex - 1);
			quickSort(array, pivotIndex + 1, rightIndex);	
		}
	}

	private int partition(Integer[] array, int leftIndex, int rightIndex) {
		// TODO trocar o pivot por um melhor (random ou mediana de três)
		int pivot = array[leftIndex];
        int i = leftIndex;

        for (int j = leftIndex + 1; j <= rightIndex; j++) {
            if (array[j].compareTo(pivot) <= 0) {
				i++;

				Integer aux = array[i];
				array[i] = array[j];
				array[j] = aux;
            }
        }

        Integer aux = array[i];
		array[i] = array[leftIndex];
		array[leftIndex] = aux;
        
        return i; 
	}

}
