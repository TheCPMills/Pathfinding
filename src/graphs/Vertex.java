package graphs;

public class Vertex<E> {
    final private E data;

    public Vertex(E data) {
        this.data = data;
    }

    public E getData() {
        return data;
    }

    @Override
    @SuppressWarnings("unchecked")
    public final boolean equals(Object other) {
        return this.data.equals(((Vertex<E>) (other)).getData());
    }

    @Override
    public String toString() {
        return data.toString();
    }
}
