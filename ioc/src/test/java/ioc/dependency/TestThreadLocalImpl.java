package ioc.dependency;

import ioc.injection.Dependency;
import ioc.injection.Scope;

@Dependency(scope = Scope.THREAD_LOCAL)
public class TestThreadLocalImpl implements TestThreadLocal {
    private final long id;

    public TestThreadLocalImpl() {
        this.id = Thread.currentThread().getId();
    }

    @Override
    public long getState() {
        return id;
    }
}
