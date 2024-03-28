package adt.queue;

public class QueueImpl<T> implements Queue<T> {

	private T[] array;
	private int tail;

	@SuppressWarnings("unchecked")
	public QueueImpl(int size) {
		array = (T[]) new Object[size];
		tail = -1;
	}

	@Override
	public T head() {
		T out = null;

		if (!this.isEmpty()) {
			out = this.array[0];
		}

		return out;
	}

	@Override
	public boolean isEmpty() {
		return this.tail == -1;
	}

	@Override
	public boolean isFull() {
		return this.tail == this.array.length - 1;
	}

	private void shiftLeft() {
		for (int i = 0; i < this.tail; i++) {
			this.array[i] = this.array[i + 1];
		}

		this.tail--;
	}

	@Override
	public void enqueue(T element) throws QueueOverflowException {
		if (element != null) {
			if (this.isFull()) {
				throw new QueueOverflowException();
			}

			this.array[++this.tail] = element;
		}
	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		if (this.isEmpty()) {
			throw new QueueUnderflowException();
		}

		T out = this.array[0];
		shiftLeft();

		return out;
	}

}
