package core.concurrent;

import java.util.concurrent.Semaphore;

public class SemaphoreBlockingQueue<E> implements BlockingQueue<E> {
    private final int capacity;
    private final E[] elements;

    private volatile int head = 0;
    private volatile int tail = 0;

    private final Semaphore empty;
    private final Semaphore full;
    private final Semaphore tailSemaphore = new Semaphore(1);
    private final Semaphore headSemaphore = new Semaphore(1);

    @SuppressWarnings("unchecked")
    public SemaphoreBlockingQueue(final int capacity) {
        this.capacity = capacity;
        this.elements = (E[]) new Object[capacity];
        this.empty = new Semaphore(0);
        this.full = new Semaphore(capacity);
    }

    @Override
    public void enqueue(final E element) throws InterruptedException {
        this.full.acquire();

        this.tailSemaphore.acquire();
        this.elements[this.tail] = element;
        this.tail = (this.tail + 1) % this.capacity;
        this.tailSemaphore.release();

        this.empty.release();
    }

    @Override
    public E dequeue() throws InterruptedException {
        this.empty.acquire();

        this.headSemaphore.acquire();
        final E result = this.elements[this.head];
        this.head = (this.head + 1) % this.capacity;
        this.headSemaphore.release();

        this.full.release();
        return result;
    }
}
