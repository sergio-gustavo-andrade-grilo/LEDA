package adt.bst;

/**
 * 
 * Performs consistency validations within a BST instance
 * 
 * @author Claudio Campelo
 *
 * @param <T>
 */
public class BSTVerifierImpl<T extends Comparable<T>> implements BSTVerifier<T> {
	
	private BSTImpl<T> bst;

	public BSTVerifierImpl(BST<T> bst) {
		this.bst = (BSTImpl<T>) bst;
	}
	
	private BSTImpl<T> getBSt() {
		return bst;
	}

	@Override
	public boolean isBST() {
		boolean out = false;

		if (this.getBSt().isEmpty()) {
			out = true;
		} else {
			out = recursiveIsBST(this.getBSt().getRoot());
		}
		
		return out;
	}

	private boolean recursiveIsBST(BSTNode<T> node) {
		boolean out = true;

		if (node != null && !node.isEmpty() && !node.isLeaf()) {
			if (node.getLeft() != null && !node.getLeft().isEmpty()) {
				// O nó da esquerda existe e é maior
				if (node.getLeft().getData().compareTo(node.getData()) > 0) {
					out = false;
				}
			}

			if (node.getRight() != null && !node.getRight().isEmpty()) {
				// O nó da direita existe e é menor
				if (node.getRight().getData().compareTo(node.getData()) < 0) {
					out = false;
				}
			}

			out = out && recursiveIsBST((BSTNode<T>) node.getLeft()) && recursiveIsBST((BSTNode<T>) node.getRight());
		}

		return out;
	}
}
