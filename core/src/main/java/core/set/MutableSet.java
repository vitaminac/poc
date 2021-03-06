package core.set;

import java.util.function.Consumer;
import java.util.function.Supplier;

import core.functional.Enumerable;
import core.map.Map;

public interface MutableSet<E>
        extends
        Set<E>,
        Enumerable<E> {
    void add(E element);

    void remove(E element);

    void clear();

    static <T> MutableSet<T> fromMap(Supplier<Map<T, Boolean>> supplier) {
        final Map<T, Boolean> map = supplier.get();
        return new MutableSet<T>() {
            @Override
            public void forEach(Consumer<? super T> consumer) {
                map.forEach(consumer);
            }

            @Override
            public boolean contains(T element) {
                return map.get(element) != null;
            }

            @Override
            public void add(T element) {
                map.put(element, Boolean.TRUE);
            }

            @Override
            public void remove(T element) {
                map.remove(element);
            }

            @Override
            public void clear() {
                map.clear();
            }
        };
    }
}
