package core.pq;

public interface PriorityQueue<E> {
    void add(E element);

    E min();

    int size();

    boolean isEmpty();

    E remove();

    void clear();
}