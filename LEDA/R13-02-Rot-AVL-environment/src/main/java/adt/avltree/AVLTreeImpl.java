package adt.avltree;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;
import adt.bt.Util;

/**
 * 
 * Implementacao de uma arvore AVL
 * A CLASSE AVLTree herda de BSTImpl. VOCE PRECISA SOBRESCREVER A IMPLEMENTACAO
 * DE BSTIMPL RECEBIDA COM SUA IMPLEMENTACAO "OU ENTAO" IMPLEMENTAR OS SEGUITNES
 * METODOS QUE SERAO TESTADOS NA CLASSE AVLTREE:
 * - insert
 * - preOrder
 * - postOrder
 * - remove
 * - height
 * - size
 *
 * @author Claudio Campelo
 *
 * @param <T>
 */
public class AVLTreeImpl<T extends Comparable<T>> extends BSTImpl<T> implements
		AVLTree<T> {

	// TODO Do not forget: you must override the methods insert and remove
	// conveniently.
	@Override
	public void insert(T element) {
		this.recursiveInsert(element, new BSTNode<T>(), this.root);
	}

	private void recursiveInsert(T element, BSTNode<T> parent, BSTNode<T> node) {
		if (node.isEmpty()) {
			node.setData(element);
			node.setParent(parent);

			node.setLeft(new BSTNode<T>());
			node.getLeft().setParent(node);

			node.setRight(new BSTNode<T>());
			node.getRight().setParent(node);

		} else {
			if (element.compareTo(node.getData()) < 0) {
				recursiveInsert(element, node, (BSTNode<T>) node.getLeft());
			} else if (element.compareTo(node.getData()) > 0) {
				recursiveInsert(element, node, (BSTNode<T>) node.getRight());
			}

			this.rebalance(node);
		}
	}

	@Override
	public void remove(T element) {
		if (!isEmpty()) {
			if (element != null) {
				BSTNode<T> toRemove = search(element);

				if (!toRemove.isEmpty()) {
					if (toRemove.isLeaf()) {
						if (toRemove == this.root) {
							this.root = new BSTNode<T>();
							this.root.setParent(new BSTNode<T>());
							this.root.setLeft(new BSTNode<T>());
							this.root.setRight(new BSTNode<T>());

						} else {
							if (toRemove.getData().compareTo(toRemove.getParent().getData()) < 0) {
								toRemove.getParent().setLeft(new BSTNode<T>());
								toRemove.getParent().getLeft().setParent(toRemove.getParent());
							} else {
								toRemove.getParent().setRight(new BSTNode<T>());
								toRemove.getParent().getRight().setParent(toRemove.getParent());
							}
						}

						this.rebalanceUp(toRemove);

					} else if (!(toRemove.getLeft().isEmpty()) && toRemove.getRight().isEmpty()) {

						if (toRemove == this.root) {
							this.root = (BSTNode<T>) toRemove.getLeft();
							this.root.setParent(new BSTNode<T>());
						} else {
							toRemove.getLeft().setParent(toRemove.getParent());

							if (toRemove.getData().compareTo(toRemove.getParent().getData()) < 0) {
								toRemove.getParent().setLeft(toRemove.getLeft());
							} else {
								toRemove.getParent().setRight(toRemove.getLeft());
							}
						}

						this.rebalanceUp(toRemove);

					} else if (!(toRemove.getRight().isEmpty()) && toRemove.getLeft().isEmpty()) {

						if (toRemove == this.root) {
							this.root = (BSTNode<T>) toRemove.getRight();
							this.root.setParent(new BSTNode<T>());
						} else {
							toRemove.getRight().setParent(toRemove.getParent());

							if (toRemove.getData().compareTo(toRemove.getParent().getData()) < 0) {
								toRemove.getParent().setLeft(toRemove.getRight());

							} else {
								toRemove.getParent().setRight(toRemove.getRight());
							}
						}

						this.rebalanceUp(toRemove);

					} else {
						BSTNode<T> succ = sucessor(toRemove.getData());
						remove(succ.getData());
						toRemove.setData(succ.getData());

					}
				}
			}
		}
	}

	// AUXILIARY
	protected int calculateBalance(BSTNode<T> node) {
		int balance = 0;

		if (node != null && !node.isEmpty()) {
			balance = this.recursiveHeight((BSTNode<T>) node.getLeft())
					- this.recursiveHeight((BSTNode<T>) node.getRight());
		}

		return balance;
	}

	private int recursiveHeight(BSTNode<T> node) {
		int height = 0;

		if (node.isEmpty()) {
			height = -1;
		} else {
			int leftHeight = recursiveHeight((BSTNode<T>) node.getLeft());
			int rightHeight = recursiveHeight((BSTNode<T>) node.getRight());

			if (leftHeight >= rightHeight) {
				height = 1 + leftHeight;
			} else {
				height = 1 + rightHeight;
			}
		}

		return height;
	}

	// AUXILIARY
	protected void rebalance(BSTNode<T> node) {
		if (isUnbalanced(node)) {
			if (isRightPending(node)) {

				if (isLeftPending((BSTNode<T>) node.getRight())) {
					rotateRight((BSTNode<T>) node.getRight());
					rotateLeft(node);

				} else {
					rotateLeft(node);
				}

			} else {
				if (isRightPending((BSTNode<T>) node.getLeft())) {
					rotateLeft((BSTNode<T>) node.getLeft());
					rotateRight(node);

				} else {
					rotateRight(node);

				}

			}
		}
	}

	// AUXILIARY
	protected void rebalanceUp(BSTNode<T> node) {
		BSTNode<T> parent = (BSTNode<T>) node.getParent();

		while (parent != null && !parent.isEmpty()) {
			rebalance(parent);
			parent = (BSTNode<T>) parent.getParent();
		}
	}

	protected void rotateLeft(BSTNode<T> x) {
		BSTNode<T> pivot = Util.leftRotation(x);

		if (pivot.getParent().isEmpty()) {
			this.root = pivot;
		}
	}

	protected void rotateRight(BSTNode<T> x) {
		BSTNode<T> pivot = Util.rightRotation(x);

		if (pivot.getParent().isEmpty()) {
			this.root = pivot;
		}
	}

	protected boolean isLeftPending(BSTNode<T> node) {
		return this.calculateBalance(node) >= 1;
	}

	protected boolean isRightPending(BSTNode<T> node) {
		return this.calculateBalance(node) <= -1;
	}

	protected boolean isUnbalanced(BSTNode<T> node) {
		int balance = this.calculateBalance(node);
		return balance > 1 || balance < -1;
	}
}
