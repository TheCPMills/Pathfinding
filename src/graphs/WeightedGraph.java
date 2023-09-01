package graphs;

import java.util.LinkedList;
import java.util.List;

public interface WeightedGraph<E> {
    // methods that are common to all graphs
    public List<Edge<E>> getEdge(Vertex<E> startNode, Vertex<E> endNode);
    public boolean addEdge(Edge<E> edge);
    public boolean isDirected();
    public boolean isMultiedged();
    public boolean isLoopable();

    public default Edge<E> addEdge(Vertex<E> startNode, Vertex<E> endNode, float weight) {
         if (!isLoopable() && startNode.equals(endNode)) {
            return null;
        }

        Edge<E> edge;
        if (isDirected()) {
            final class Directed<T> extends Edge<E> implements DirectedEdge<E>, WeightedEdge<E> {
                public Directed(Vertex<E> startVertex, Vertex<E> endVertex, float weight) {
                    super(startVertex, endVertex);
                    this.weight = weight;
                }
            }
            edge = new Directed<>(startNode, endNode, weight);
        } else {
            final class Weighted<T> extends Edge<E> implements WeightedEdge<E> {
                public Weighted(Vertex<E> startVertex, Vertex<E> endVertex, float weight) {
                    super(startVertex, endVertex);
                    this.weight = weight;
                }
            }
            edge = new Weighted<>(startNode, endNode, weight);
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

    public default Edge<E> removeEdge(Vertex<E> startNode, Vertex<E> endNode, float weight) {
        List<Edge<E>> edges = getEdge(startNode, endNode, weight);
        if (edges.size() > 0) {
            return edges.remove(0);
        }
        return null;
    }

    public default List<Edge<E>> getEdge(Vertex<E> startNode, Vertex<E> endNode, float weight) {
        List<Edge<E>> edges = getEdge(startNode, endNode);
        List<Edge<E>> weightedEdges = new LinkedList<>();
        for (Edge<E> edge : edges) {
            if (edge.getWeight() == weight) {
                weightedEdges.add(edge);
            }
        }
        return weightedEdges;
    }
}
