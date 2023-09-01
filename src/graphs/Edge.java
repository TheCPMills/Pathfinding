package graphs;

public class Edge<E> {
    final protected Vertex<E> startVertex, endVertex;
    protected double weight;

    public Edge(Vertex<E> startVertex, Vertex<E> endVertex) {
        if (startVertex == null || endVertex == null) {
            throw new IllegalArgumentException("Cannot have null vertices");
        } else if (startVertex.equals(endVertex)) {
            throw new IllegalArgumentException("Cannot have looped edges");
        }
        this.startVertex = startVertex;
        this.endVertex = endVertex;
    }

    public Edge(Vertex<E> startVertex, Vertex<E> endVertex, double weight) {
        this(startVertex, endVertex);
        if (isWeighted()) {
            this.weight = weight;
        } else {
            throw new UnsupportedOperationException("Cannot set weight of unweighted edge");
        }
    }

    public Vertex<E> getStartVertex() {
        return startVertex;
    }

    public Vertex<E> getEndVertex() {
        return endVertex;
    }

    public double getWeight() throws UnsupportedOperationException {
        if (!isWeighted()) {
            throw new UnsupportedOperationException("Cannot get weight of unweighted edge");
        }
        return weight;
    }

    public boolean isDirected() {
        return (this instanceof DirectedEdge);
    }

    public boolean isWeighted() {
       return (this instanceof WeightedEdge);
    }

    public boolean isLoopable() {
        return (this instanceof LoopableEdge);
    }

    @Override
    @SuppressWarnings("unchecked")
    public final boolean equals(Object other) {
        Edge<E> otherEdge = (Edge<E>) (other);

        if (this.isDirected()) {
            if (otherEdge.isDirected()) {
                return this.startVertex.equals(otherEdge.startVertex) && this.endVertex.equals(otherEdge.endVertex);
            }
            return false;
        }
        if (otherEdge.isDirected()) {
            return false;
        }
        return (this.startVertex.equals(otherEdge.startVertex) && this.endVertex.equals(otherEdge.endVertex)) || (this.startVertex.equals(otherEdge.endVertex) && this.endVertex.equals(otherEdge.startVertex));
    }

    @Override
    public String toString() {
        if (isWeighted() && isDirected()) {
            return startVertex.toString() + " --(" + weight + ")-> " + endVertex.toString();
        } else if (isWeighted()) {
            return startVertex.toString() + " --(" + weight + ")-- " + endVertex.toString();
        } else if (isDirected()) {
            return startVertex.toString() + " ---> " + endVertex.toString();
        } else {
            return startVertex.toString() + " ---- " + endVertex.toString();
        }
    }
}