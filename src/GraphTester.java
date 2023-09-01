import graphs.*;

public class GraphTester {
    public static void main(String[] args) {
        Vertex<Integer> v0 = new Vertex<Integer>(0);
        Vertex<Integer> v1 = new Vertex<Integer>(1);
        Vertex<Integer> v2 = new Vertex<Integer>(2);
        Vertex<Integer> v3 = new Vertex<Integer>(3);

        Edge<Integer> e0 = new DirectedWeightedEdge<Integer>(v0, v1, 1);
        Edge<Integer> e1 = new DirectedWeightedEdge<Integer>(v1, v2, 2);
        Edge<Integer> e2 = new DirectedWeightedEdge<Integer>(v2, v3, 3);
        Edge<Integer> e3 = new DirectedWeightedEdge<Integer>(v3, v0, 4);
        Edge<Integer> e4 = new DirectedWeightedEdge<Integer>(v0, v2, 5);

        Graph<Integer> graph = new DirectedWeightedGraph<Integer>();
        graph.addEdge(e0);
        graph.addEdge(e1);
        graph.addEdge(e2);
        graph.addEdge(e3);
        graph.addEdge(e4);

        System.out.println(graph);
    }

    static class DirectedWeightedEdge<T> extends Edge<T> implements DirectedEdge<T>, WeightedEdge<T> {
        public DirectedWeightedEdge(Vertex<T> startVertex, Vertex<T> endVertex, double weight) {
            super(startVertex, endVertex, weight);
        }
    }

    static class DirectedWeightedGraph<T> extends Graph<T> implements DirectedGraph<T>, WeightedGraph<T> {
        public DirectedWeightedGraph() {
            super();
        }
    }
}