package adt.bst;

/**
 * - Esta eh a unica classe que pode ser modificada
 * 
 * @author adalbertocajueiro
 *
 * @param <T>
 */
public class SimpleBSTManipulationImpl<T extends Comparable<T>> implements SimpleBSTManipulation<T> {

	@Override
	public boolean equals(BST<T> tree1, BST<T> tree2) {
		return recursiveEquals((BSTNode<T>) tree1.getRoot(), (BSTNode<T>) tree2.getRoot());
	}

	private boolean recursiveEquals(BSTNode<T> current1, BSTNode<T> current2) {
		boolean out = false;

		if (current1.isEmpty() && current2.isEmpty()) {
			out = true;
		} else if (!current1.isEmpty() && current2.isEmpty()) {
			out = false;
		} else if (current1.isEmpty() && !current2.isEmpty()) {
			out = false;
		} else {
			if (current1.getData().equals(current2.getData())
					&& recursiveEquals((BSTNode<T>) current1.getLeft(), (BSTNode<T>) current2.getLeft())
					&& recursiveEquals((BSTNode<T>) current1.getRight(), (BSTNode<T>) current2.getRight())) {
				out = true;
			} else {
				out = false;
			}
		}

		return out;
	}

	@Override
	public boolean isSimilar(BST<T> tree1, BST<T> tree2) {
		return recursiveIsSimilar((BSTNode<T>) tree1.getRoot(), (BSTNode<T>) tree2.getRoot());
	}

	private boolean recursiveIsSimilar(BSTNode<T> current1, BSTNode<T> current2) {
		boolean out = false;

		if (current1.isEmpty() && current2.isEmpty()) {
			out = true;
		} else if (!current1.isEmpty() && current2.isEmpty()) {
			out = false;
		} else if (current1.isEmpty() && !current2.isEmpty()) {
			out = false;
		} else {
			if (recursiveIsSimilar((BSTNode<T>) current1.getLeft(), (BSTNode<T>) current2.getLeft())
					&& recursiveIsSimilar((BSTNode<T>) current1.getRight(), (BSTNode<T>) current2.getRight())) {
				out = true;
			} else {
				out = false;
			}
		}

		return out;
	}

	@Override
	public T orderStatistic(BST<T> tree, int k) {
		T out = null;

		if (0 < k && k <= tree.size()) {
			out = recursiveOrderStatistic((BSTNode<T>) tree.getRoot(), k, new int[] { 0 }).getData();
		}

		return out;
	}

	private BSTNode<T> recursiveOrderStatistic(BSTNode<T> current, int k, int[] index) {
		BSTNode<T> out = null;

		if (current == null || current.isEmpty()) {
			out = null;
		} else {
			BSTNode<T> left = recursiveOrderStatistic((BSTNode<T>) current.getLeft(), k, index);

			if (left != null && !left.isEmpty()) {
				out = left;
			} else {
				index[0]++;

				if (index[0] == k) {
					out = current;
				} else {
					out = recursiveOrderStatistic((BSTNode<T>) current.getRight(), k, index);

				}
			}
		}

		return out;
	}
}
