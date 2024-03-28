package adt.linkedList;

import java.util.NoSuchElementException;

public class DoubleLinkedListImpl<T> extends SingleLinkedListImpl<T> implements
		DoubleLinkedList<T> {

	protected DoubleLinkedListNode<T> last;

	public DoubleLinkedListImpl() {
		super.setHead(new DoubleLinkedListNode<T>());
		this.setLast(new DoubleLinkedListNode<T>());
	}

	@Override
	public void insert(T element) {
		if (element != null) {
			DoubleLinkedListNode<T> newNode = new DoubleLinkedListNode<T>(element, new DoubleLinkedListNode<T>(),
					new DoubleLinkedListNode<T>());

			// Se estiver vazio, apenas definimos o head e o last como o novo nó.
			if (this.isEmpty()) {
				this.setHead(newNode);
				this.setLast(newNode);

			}

			// Senão, conectamos os nós e definimos o last como o novo nó.
			else {
				this.getLast().setNext(newNode);
				newNode.setPrevious(last);
				this.setLast(newNode);
			}
		}
	}

	@Override
	public void insertFirst(T element) {
		if (element != null) {
			DoubleLinkedListNode<T> newNode = new DoubleLinkedListNode<T>(element, new DoubleLinkedListNode<T>(),
					new DoubleLinkedListNode<T>());

			// Se estiver vazio, apenas colocamos o head e o last como o novo nó.
			if (this.isEmpty()) {
				this.setHead(newNode);
				this.setLast(newNode);
			}

			// Senão, colocamos um newNode, com next no head, para ser também o previous
			// desse head.
			// Se o head for nil, colocamos
			else {
				DoubleLinkedListNode<T> headNode = (DoubleLinkedListNode<T>) super.getHead();

				headNode.setPrevious(newNode);
				newNode.setNext(headNode);

				this.setHead(newNode);
			}
		}
	}

	@Override
	public void removeFirst() {
		// Se estiver vazio, lança uma exceção.
		if (this.isEmpty()) {
			throw new NoSuchElementException();
		}

		// Senão, definimos o head como o seu próximo. O anterior desse "novo" head
		// passa a ser um nó NIL.
		DoubleLinkedListNode<T> headNode = (DoubleLinkedListNode<T>) this.getHead();

		super.setHead(headNode.getNext());
		headNode.setPrevious(new DoubleLinkedListNode<T>());
	}

	@Override
	public void removeLast() {
		if (this.isEmpty()) {
			throw new NoSuchElementException();
		}

		// TODO N era p precisar disso, eu acho
		if (this.getHead().equals(this.getLast())) {
			this.removeFirst();
		}

		// Senão, definimos o tail como o seu anterior. O anterior desse "novo" head
		// passa a ser um nó NIL.
		DoubleLinkedListNode<T> tailNode = (DoubleLinkedListNode<T>) this.getLast();
		this.setLast(tailNode.getPrevious());
		this.getLast().setNext(new DoubleLinkedListNode<T>());

	}

	public DoubleLinkedListNode<T> getLast() {
		return last;
	}

	public void setLast(DoubleLinkedListNode<T> last) {
		this.last = last;
	}

}
