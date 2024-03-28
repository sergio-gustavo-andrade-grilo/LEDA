package adt.avltree;

import adt.bst.BSTNode;
import adt.bst.BSTVerifierImpl;

/**
 * Performs consistency validations within a AVL Tree instance
 * 
 * @author Claudio Campelo
 *
 * @param <T>
 */
public class AVLTreeVerifierImpl<T extends Comparable<T>> extends BSTVerifierImpl<T> implements AVLTreeVerifier<T> {

	private AVLTreeImpl<T> avlTree;

	public AVLTreeVerifierImpl(AVLTree<T> avlTree) {
		super(avlTree);
		this.avlTree = (AVLTreeImpl<T>) avlTree;
	}

	private AVLTreeImpl<T> getAVLTree() {
		return avlTree;
	}

	@Override
	public boolean isAVLTree() {
		return isBST() && checkAVL(this.getAVLTree().getRoot());
	}

	private boolean checkAVL(BSTNode<T> node) {
		boolean out = true;

		if (node != null && !node.isEmpty()) {
			int balance = this.getAVLTree().calculateBalance(node);
			out = -1 <= balance && balance <= 1;
			out = out && checkAVL((BSTNode<T>) node.getLeft()) && checkAVL((BSTNode<T>) node.getRight());
		}

		return out;
	}
}
