package adt.linkedList;

import java.util.NoSuchElementException;

public class SingleLinkedListImpl<T> implements LinkedList<T> {

	protected SingleLinkedListNode<T> head;

	public SingleLinkedListImpl() {
		this.head = new SingleLinkedListNode<T>();
	}

	@Override
	public boolean isEmpty() {
		return this.head.isNIL();
	}

	@Override
	public int size() {
		SingleLinkedListNode<T> auxNode = this.getHead();
		int counter = 0;

		// Incrementamos o valor do counter até achar um elemento NIL.
		while (!auxNode.isNIL()) {
			auxNode = auxNode.getNext();
			counter++;
		}

		return counter;

	}

	@Override
	public T search(T element) {
		T out = null;

		if (element != null) {
			SingleLinkedListNode<T> auxNode = this.getHead();

			// Para ou quando encontrarmos um nó com os dados que estamos procurando ou
			// quando é NIL. Quando for NIL, significa que não achamos o elemento, portanto
			// retorna seus dados, que são null.
			while (!auxNode.isNIL() && auxNode.getData() != element) {
				auxNode = auxNode.getNext();
			}

			out = auxNode.getData();
		}

		return out;
	}

	@Override
	public void insert(T element) {
		if (element != null) {

			// Se a lista estiver vazia, apenas definimos valor do head como um novo nó.
			if (this.isEmpty()) {
				SingleLinkedListNode<T> newNode = new SingleLinkedListNode<T>(element, new SingleLinkedListNode<T>());
				this.setHead(newNode);

			}

			// Senão, procuramos o valor NIL no final da lista e atribuímos o seu valor como
			// um novo nó, com next NIL.
			else {

				SingleLinkedListNode<T> auxNode = this.getHead();

				while (!auxNode.isNIL()) {
					auxNode = auxNode.getNext();
				}

				auxNode.setData(element);
				auxNode.setNext(new SingleLinkedListNode<T>());
			}
		}
	}

	@Override
	public void remove(T element) {
		if (this.isEmpty()) {
			throw new NoSuchElementException();
		}

		if (element != null) {
			// Se o elemento estiver na cabeça, ela passa a ser o próximo nó.
			if (this.getHead().getData().equals(element)) {
				this.setHead(this.getHead().getNext());
			} else {
				SingleLinkedListNode<T> auxNode = this.getHead();

				// Procuramos o elemento na lista.
				while (!auxNode.isNIL() && auxNode.getData() != element) {
					auxNode = auxNode.getNext();
				}

				// Se o elemento for encontrado, atribuímos o valor dele como o seu próximo e o
				// next dele como o next do próximo, efetivamente removendo o elemento.
				if (!auxNode.isNIL()) {
					auxNode.setData(auxNode.getNext().getData());
					auxNode.setNext(auxNode.getNext().getNext());
				}
			}
		}
	}

	@Override
	public T[] toArray() {
		@SuppressWarnings("unchecked")
		T[] out = (T[]) new Object[this.size()];
		SingleLinkedListNode<T> auxNode = this.getHead();

		int counter = 0;
		while (counter < this.size()) {
			out[counter++] = auxNode.getData();
			auxNode = auxNode.getNext();
		}

		return out;
	}

	public SingleLinkedListNode<T> getHead() {
		return head;
	}

	public void setHead(SingleLinkedListNode<T> head) {
		this.head = head;
	}

}
