package adt.linkedList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class StudentDoubleLinkedListTest extends StudentLinkedListTest {

	private DoubleLinkedList<Integer> lista3;

	@Before
	public void setUp() throws Exception {

		getImplementations();

		// Lista com 3 elementos.
		lista1.insert(3);
		lista1.insert(2);
		lista1.insert(1);

		// Lista com 1 elemento.
		lista3.insert(1);
	}

	private void getImplementations() {
		// TODO O aluno deve ajustar aqui para instanciar sua implementação
		lista1 = new DoubleLinkedListImpl<Integer>();
		lista2 = new DoubleLinkedListImpl<Integer>();
		lista3 = new DoubleLinkedListImpl<Integer>();
	}

	// Métodos de DoubleLinkedList

	@Test
	public void testInsertFirst() {
		((DoubleLinkedList<Integer>) lista1).insertFirst(4);
		Assert.assertArrayEquals(new Integer[] { 4, 3, 2, 1 }, lista1.toArray());
		Assert.assertEquals(((DoubleLinkedListImpl<Integer>) lista1).getHead(), new DoubleLinkedListNode<Integer>(4, new DoubleLinkedListNode<Integer>(), new DoubleLinkedListNode<Integer>()));
	}

	@Test
	public void testRemoveFirst() {
		((DoubleLinkedList<Integer>) lista1).removeFirst();
		Assert.assertArrayEquals(new Integer[] { 2, 1 }, lista1.toArray());
		Assert.assertEquals(((DoubleLinkedListImpl<Integer>) lista1).getHead(), new DoubleLinkedListNode<Integer>(2, new DoubleLinkedListNode<Integer>(), new DoubleLinkedListNode<Integer>()));
	}

	@Test
	public void testRemoveLast() {
		((DoubleLinkedList<Integer>) lista1).removeLast();
		Assert.assertArrayEquals(new Integer[] { 3, 2 }, lista1.toArray());

		((DoubleLinkedList<Integer>) lista1).removeLast();
		((DoubleLinkedList<Integer>) lista1).removeLast();
		Assert.assertEquals(((DoubleLinkedListImpl<Integer>) lista1).getLast(), new DoubleLinkedListNode<Integer>());
	}
}