// Storage.java
public class Storage<T> {
    private final T item;
    private final T alternative;

    public Storage(T item, T alternative) {
        this.item = item;
        this.alternative = alternative;
    }

    public T get() {
        return item != null ? item : alternative;
    }

    @Override
    public String toString() {
        return "Storage[" + get() + "]";
    }
}
