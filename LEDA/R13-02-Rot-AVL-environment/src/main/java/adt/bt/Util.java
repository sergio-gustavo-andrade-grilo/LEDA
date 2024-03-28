package adt.bt;

import adt.bst.BSTNode;

public class Util {

	/**
	 * A rotacao a esquerda em node deve subir e retornar seu filho a direita
	 * 
	 * @param node
	 * @return - noh que se tornou a nova raiz
	 */
	public static <T extends Comparable<T>> BSTNode<T> leftRotation(BSTNode<T> node) {
		BSTNode<T> pivot = (BSTNode<T>) node.getRight();

		pivot.setParent(node.getParent());

		if (!node.getParent().isEmpty()) {
			if (node.getData().compareTo(node.getParent().getData()) < 0) {
				pivot.getParent().setLeft(pivot);
			} else {
				pivot.getParent().setRight(pivot);
			}
		}

		node.setParent(pivot);
		node.setRight(pivot.getLeft());
		pivot.setLeft(node);

		return pivot;
	}

	/**
	 * A rotacao a direita em node deve subir e retornar seu filho a esquerda
	 * 
	 * @param node
	 * @return noh que se tornou a nova raiz
	 */
	public static <T extends Comparable<T>> BSTNode<T> rightRotation(BSTNode<T> node) {
		BSTNode<T> pivot = (BSTNode<T>) node.getLeft();

		pivot.setParent(node.getParent());

		if (!node.getParent().isEmpty()) {
			if (node.getData().compareTo(node.getParent().getData()) < 0) {
				pivot.getParent().setLeft(pivot);
			} else {
				pivot.getParent().setRight(pivot);
			}
		}

		node.setParent(pivot);
		node.setLeft(pivot.getRight());
		pivot.setRight(node);

		return pivot;
	}

	public static <T extends Comparable<T>> T[] makeArrayOfComparable(int size) {
		@SuppressWarnings("unchecked")
		T[] array = (T[]) new Comparable[size];
		return array;
	}
}
