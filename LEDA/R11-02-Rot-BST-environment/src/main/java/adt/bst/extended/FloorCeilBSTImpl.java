package adt.bst.extended;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;

/**
 * Note que esta classe estende sua implementacao de BST (BSTImpl).
 * Dependendo do design que voce use, sua BSTImpl precisa ter apenas funcionando
 * corretamente o metodo insert para que voce consiga testar esta classe.
 */
public class FloorCeilBSTImpl extends BSTImpl<Integer> implements FloorCeilBST {

	@Override
	public Integer floor(Integer[] array, double numero) {
		Integer floor = null;

		if (array.length > 0) {
			for (Integer i : array) {
				this.insert(i);
			}

			floor = recursiveFloor(this.root, numero);
		}

		return floor;
	}

	private Integer recursiveFloor(BSTNode<Integer> current, double numero) {
		Integer floor = null;

		if (current.isEmpty()) {
			// Se o nó for vazio, retornamos null.
			floor = null;

		} else if (current.getData() == numero) {
			// Se encontramos o número, retornamos ele.
			floor = current.getData();

		} else if (current.getData() > numero) {
			// Se o número for menor, procuramos na subárvore à esquerda.
			floor = recursiveFloor((BSTNode<Integer>) current.getLeft(), numero);

		} else {
			// Se o número for maior, comparamos o floor da subárvore á direita com o nó
			// atual. O retorno depende dessa comparação.

			Integer rightSubtreeFloor = recursiveFloor((BSTNode<Integer>) current.getRight(), numero);

			if (rightSubtreeFloor != null && rightSubtreeFloor <= numero) {
				floor = rightSubtreeFloor;
			} else {
				floor = current.getData();
			}
		}

		return floor;
	}

	@Override
	public Integer ceil(Integer[] array, double numero) {
		Integer ceil = null;

		if (array.length > 0) {
			for (Integer i : array) {
				this.insert(i);
			}

			ceil = recursiveCeil(this.root, numero);
		}

		return ceil;
	}

	private Integer recursiveCeil(BSTNode<Integer> current, double numero) {
		Integer ceil = null;

		if (current.isEmpty()) {
			// Se o nó for vazio, retornamos null.
			ceil = null;

		} else if (current.getData() == numero) {
			// Se encontramos o número, retornamos ele.
			ceil = current.getData();

		} else if (current.getData() < numero) {
			// Se o número for maior, procuramos na subárvore à direita.
			ceil = recursiveCeil((BSTNode<Integer>) current.getRight(), numero);

		} else {
			// Se o número for menor, comparamos o ceil da subárvore á esquerda com o nó
			// atual. O retorno depende dessa comparação.

			Integer leftSubtreeCeil = recursiveCeil((BSTNode<Integer>) current.getLeft(), numero);

			if (leftSubtreeCeil != null && leftSubtreeCeil >= numero) {
				ceil = leftSubtreeCeil;
			} else {
				ceil = current.getData();
			}
		}

		return ceil;

	}

}
