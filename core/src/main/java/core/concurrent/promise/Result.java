package core.concurrent.promise;

import java.util.NoSuchElementException;

public interface Result<T> {
    static <T> Result<T> pending() {
        return new Result<T>() {
            @Override
            public T get() {
                throw new IllegalStateException("pending");
            }

            @Override
            public Exception getReason() {
                throw new IllegalStateException("pending");
            }

            @Override
            public boolean isFulfilled() {
                return false;
            }

            @Override
            public boolean isFailed() {
                return false;
            }

            @Override
            public boolean isPending() {
                return true;
            }
        };
    }

    static <T> Result<T> success(final T value) {
        return new Result<T>() {
            @Override
            public T get() {
                return value;
            }

            @Override
            public Exception getReason() {
                throw new NoSuchElementException();
            }

            @Override
            public boolean isFulfilled() {
                return true;
            }

            @Override
            public boolean isFailed() {
                return false;
            }

            @Override
            public boolean isPending() {
                return false;
            }
        };
    }

    static <T> Result<T> fail(final Exception reason) {
        return new Result<T>() {
            @Override
            public T get() {
                throw new NoSuchElementException();
            }

            @Override
            public Exception getReason() {
                return reason;
            }

            @Override
            public boolean isFulfilled() {
                return false;
            }

            @Override
            public boolean isFailed() {
                return true;
            }

            @Override
            public boolean isPending() {
                return false;
            }
        };
    }

    T get();

    Exception getReason();

    boolean isFulfilled();

    boolean isFailed();

    boolean isPending();
}
