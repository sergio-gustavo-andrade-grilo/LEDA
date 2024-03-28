package adt.queue;

import adt.stack.Stack;
import adt.stack.StackImpl;
import adt.stack.StackOverflowException;
import adt.stack.StackUnderflowException;

public class QueueUsingStack<T> implements Queue<T> {

	private Stack<T> stack1;
	private Stack<T> stack2;

	public QueueUsingStack(int size) {
		stack1 = new StackImpl<T>(size);
		stack2 = new StackImpl<T>(size);
	}

	@Override
	public void enqueue(T element) throws QueueOverflowException {
		if (element != null) {
			if (this.stack1.isFull()) {
				throw new QueueOverflowException();
			}

			// TODO Descobrir como evitar o try/catch
			try {
				this.stack1.push(element);
			} catch (StackOverflowException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		if (this.stack1.isEmpty()) {
			throw new QueueUnderflowException();
		}

		depositAll();

		T out = null;

		// TODO Descobrir como evitar o try/catch
		try {
			out = stack2.pop();
		} catch (StackUnderflowException e) {
			e.printStackTrace();
		}

		withdrawAll();

		return out;
	}

	@Override
	public T head() {
		T out = null;

		if (!this.stack1.isEmpty()) {
			depositAll();
			out = this.stack2.top();
			withdrawAll();
		}

		return out;

	}

	@Override
	public boolean isEmpty() {
		return this.stack1.isEmpty();
	}

	@Override
	public boolean isFull() {
		return this.stack1.isFull();
	}

	private void depositAll() {
		while (!stack1.isEmpty()) {

			// TODO Descobrir como evitar o try/catch
			try {
				stack2.push(stack1.pop());
			} catch (StackOverflowException | StackUnderflowException e) {
				e.printStackTrace();
			}
		}
	}

	private void withdrawAll() {
		while (!stack2.isEmpty()) {

			// TODO Descobrir como evitar o try/catch
			try {
				stack1.push(stack2.pop());
			} catch (StackOverflowException | StackUnderflowException e) {
				e.printStackTrace();
			}
		}
	}

}
