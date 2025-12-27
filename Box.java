// Box.java
public class Box<T> {
    private T item;

    public Box() {
        this.item = null;
    }

    public void put(T item) {
        if (this.item != null) {
            throw new IllegalStateException("Коробка уже занята!");
        }
        this.item = item;
    }

    public T take() {
        T taken = item;
        item = null;
        return taken;
    }

    public boolean isEmpty() {
        return item == null;
    }

    @Override
    public String toString() {
        return "Box[" + (item != null ? item : "пусто") + "]";
    }
}
