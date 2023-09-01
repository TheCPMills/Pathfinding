package graphs;
import java.util.*;

public class Graph<E> {
    protected List<Vertex<E>> vertices = new LinkedList<>();
    protected List<Edge<E>> edges = new LinkedList<>();

    public Vertex<E> addVertex(E data) {
        Vertex<E> vertex = new Vertex<>(data);

        if (vertices.contains(vertex)) {
            vertex = vertices.get(vertices.indexOf(vertex));
        } else {
            addVertex(vertex);
        }
        return vertex;
    }

    public boolean addVertex(Vertex<E> vertex) {
        if (vertices.contains(vertex)) {
            return false;
        }
        return vertices.add(vertex);
    }

    public Edge<E> addEdge(Vertex<E> startNode, Vertex<E> endNode) {
        if (!isLoopable() && startNode.equals(endNode)) {
            return null;
        }

        Edge<E> edge;
        if (isDirected()) {
            final class Directed<T> extends Edge<E> implements DirectedEdge<E> {
                public Directed(Vertex<E> startVertex, Vertex<E> endVertex) {
                    super(startVertex, endVertex);
                }
            }

            edge = new Directed<>(startNode, endNode);
        } else {
            edge = new Edge<>(startNode, endNode);
        }

        if (!isMultiedged()) {
            List<Edge<E>> edges = getEdge(startNode, endNode);
            if (edges.size() > 0) {
                edge = edges.get(0);
            }
        } else {
            addEdge(edge);
        }

        return edge;
    }

    public boolean addEdge(Edge<E> edge) {
        if (isLoopable() != edge.isLoopable()) {
            return false;
        }

        if (isDirected() != edge.isDirected()) {
            return false;
        }

        if (isWeighted() != edge.isWeighted()) {
            return false;
        }

        if (!isMultiedged() && edges.contains(edge)) {
            return false;
        } else {
            addVertex(edge.getStartVertex());
            addVertex(edge.getEndVertex());
            return edges.add(edge);
        }
    }

    public Vertex<E> removeVertex(E data) {
        for (Vertex<E> vertex : vertices) {
            if (vertex.getData().equals(data)) {
                removeVertex(vertex);
                return vertex;
            }
        }
        return null;
    }

    public boolean removeVertex(Vertex<E> vertex) {
        for (Edge<E> edge : edges) {
            if (edge.getStartVertex().equals(vertex) || edge.getEndVertex().equals(vertex)) {
                removeEdge(edge);
            }
        }
        
        return vertices.remove(vertex);
    }

    public Edge<E> removeEdge(Vertex<E> startNode, Vertex<E> endNode) {
        List<Edge<E>> edges = getEdge(startNode, endNode);
        if (edges.size() > 0) {
            return edges.remove(0);
        }
        return null;
    }

    public boolean removeEdge(Edge<E> edge) {
        return edges.remove(edge);
    }

    public Vertex<E> getVertex(E data) {
        for (Vertex<E> vertex : vertices) {
            if (vertex.getData().equals(data)) {
                return vertex;
            }
        }
        return null;
    }

    public List<Edge<E>> getEdge(Vertex<E> startNode, Vertex<E> endNode) {
        List<Edge<E>> edges = new LinkedList<>();
        for (Edge<E> edge : this.edges) {
            if (edge.equals(new Edge<>(startNode, endNode))) {
                edges.add(edge);
            }
        }
        return edges;
    }

    public boolean isDirected() {
        return (this instanceof DirectedGraph);
    }

    public boolean isWeighted() {
        return (this instanceof WeightedGraph);
    }

    public boolean isLoopable() {
        return (this instanceof LoopableGraph);
    }

    public boolean isMultiedged() {
        return (this instanceof MultiedgedGraph);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Vertices:\n");
        for (Vertex<E> vertex : vertices) {
            builder.append("\t");
            builder.append(vertex.toString());
            builder.append("\n");
        }
        builder.append("\nEdges:\n");
        for (Edge<E> edge : edges) {
            builder.append("\t");
            builder.append(edge.toString());
        builder.append("\n");
        }
        return builder.toString();
    }
}