package adt.queue;

public class CircularQueue<T> implements Queue<T> {

	private T[] array;
	private int tail;
	private int head;
	private int elements;

	@SuppressWarnings("unchecked")
	public CircularQueue(int size) {
		array = (T[]) new Object[size];
		head = -1;
		tail = -1;
		elements = 0;
	}

	@Override
	public void enqueue(T element) throws QueueOverflowException {
		if (element != null) {
			if (this.isFull()) {
				throw new QueueOverflowException();
			}

			if (this.isEmpty()) {
				this.head++;
			}

			this.tail = (this.tail + 1) % this.array.length;

			this.array[this.tail] = element;

			this.elements++;
		}

	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		if (this.isEmpty()) {
			throw new QueueUnderflowException();
		}

		T out = this.array[this.head];
		this.head = (this.head + 1) % this.array.length;

		this.elements--;

		if (this.elements == 0) {
			this.head = -1;
			this.tail = -1;
		}

		return out;
	}

	@Override
	public T head() {
		return this.array[this.head];
	}

	@Override
	public boolean isEmpty() {
		return this.elements == 0;
	}

	@Override
	public boolean isFull() {
		return this.elements == this.array.length;
	}

}
