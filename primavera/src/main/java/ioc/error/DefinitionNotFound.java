package ioc.error;

public class DefinitionNotFound extends RuntimeException {
    public DefinitionNotFound(Object key) {
        super(key + " not found in the application context");
    }
}
