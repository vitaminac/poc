package test;

public abstract class UnitTestRunner {
    protected final Class<?> clazz;

    public UnitTestRunner(Class<?> clazz) {
        this.clazz = clazz;
    }

    abstract void run() throws Exception;
}
