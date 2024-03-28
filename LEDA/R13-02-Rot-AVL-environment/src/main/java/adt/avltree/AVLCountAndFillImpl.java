package adt.avltree;

import java.util.Arrays;
import java.util.HashSet;

import adt.bst.BSTNode;

public class AVLCountAndFillImpl<T extends Comparable<T>> extends
		AVLTreeImpl<T> implements AVLCountAndFill<T> {

	private int LLcounter;
	private int LRcounter;
	private int RRcounter;
	private int RLcounter;

	public AVLCountAndFillImpl() {

	}

	@Override
	public int LLcount() {
		return LLcounter;
	}

	@Override
	public int LRcount() {
		return LRcounter;
	}

	@Override
	public int RRcount() {
		return RRcounter;
	}

	@Override
	public int RLcount() {
		return RLcounter;
	}

	@Override
	public void fillWithoutRebalance(T[] array) {
		HashSet<T> set = new HashSet<T>();
		T[] arrayBST = this.order();

		for (int i = 0; i < arrayBST.length; i++) {
			set.add(arrayBST[i]);
		}

		for (int i = 0; i < array.length; i++) {
			set.add(array[i]);
		}

		array = (T[]) set.toArray(new Comparable[0]);

		this.root = new BSTNode<T>();

		int level = 0;
		while (fillWithoutRebalance(array, 0, array.length, level)) {
			level++;
		}
	}

	private boolean fillWithoutRebalance(T[] array, int left, int right, int level) {
		boolean out = false;

		if (right > left) {
			int middle = (left + right) / 2;

			if (level == 0) {
				this.insert(array[middle]);
				out = true;
			} else {
				out = fillWithoutRebalance(array, left, middle, level - 1);
				out = fillWithoutRebalance(array, middle + 1, right, level - 1);
			}
		}

		return out;
	}

	@Override
	protected void rebalance(BSTNode<T> node) {
		if (isUnbalanced(node)) {
			if (isRightPending(node)) {

				if (isLeftPending((BSTNode<T>) node.getRight())) {
					rotateRight((BSTNode<T>) node.getRight());
					rotateLeft(node);
					this.RLcounter++;

				} else {
					rotateLeft(node);
					this.RRcounter++;
				}

			} else {
				if (isRightPending((BSTNode<T>) node.getLeft())) {
					rotateLeft((BSTNode<T>) node.getLeft());
					rotateRight(node);
					this.LRcounter++;

				} else {
					rotateRight(node);
					this.LLcounter++;

				}

			}
		}
	}

}
