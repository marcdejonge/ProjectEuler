package euler.graph;

import java.util.Iterator;

import euler.collection.IntObjectHashMap;

public class WeightedDirectedGraphImpl implements Graph<WeightedDirectedGraphImpl.Vertex, WeightedDirectedGraphImpl.Edge> {
    public static class Edge implements WeightedEdge<Vertex, Edge>, DirectedEdge<Vertex, Edge> {
        private final Vertex from;
        private final Vertex to;
        private final int weight;

        Edge(Vertex from, Vertex to, int weight) {
            if (from == null || to == null) {
                throw new NullPointerException();
            }
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            } else if (obj == null || getClass() != obj.getClass()) {
                return false;
            } else {
                Edge other = (Edge) obj;
                return from.equals(other.from) && to.equals(other.to) && weight == other.weight;
            }
        }

        @Override
        public Vertex getFrom() {
            return from;
        }

        @Override
        public Vertex getTo() {
            return to;
        }

        @Override
        public Vertex[] getVertices() {
            return new Vertex[] { from, to };
        }

        @Override
        public int getWeight() {
            return weight;
        }

        @Override
        public int hashCode() {
            return 31 * (31 * from.getId() + to.getId()) + weight;
        }

        @Override
        public String toString() {
            return "Edge(" + from.getId() + " -> " + to.getId() + ")";
        };
    }

    public static class Vertex implements euler.graph.Vertex<Vertex, Edge> {
        private final IntObjectHashMap<Edge> edges;
        private final int nr;

        Vertex(int nr) {
            this.nr = nr;
            edges = new IntObjectHashMap<>();
        }

        void addEdge(Vertex to, int weight) {
            edges.put(to.getId(), new Edge(this, to, weight));
        }

        @Override
        public int getId() {
            return nr;
        }

        @Override
        public Iterator<Edge> iterator() {
            return edges.values().iterator();
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("Vertex ").append(nr).append(":\t");
            for (Edge edge : this) {
                sb.append('(').append(edge.getWeight()).append(") -> ").append(edge.getTo()).append('\t');
            }
            return sb.toString();
        }
    }

    private final IntObjectHashMap<Vertex> vertices;

    public WeightedDirectedGraphImpl() {
        vertices = new IntObjectHashMap<Vertex>();
    }

    public <V extends euler.graph.Vertex<V, E>, E extends DirectedEdge<V, E> & WeightedEdge<V, E>> void addEdge(E edge) {
        addEdge(edge.getFrom().getId(), edge.getTo().getId(), edge.getWeight());
    }

    public void addEdge(int from, int to, int weight) {
        getOrCreateVertex(from).addEdge(getOrCreateVertex(to), weight);
    }

    public void addUndirectedEdge(int from, int to, int weight) {
        getOrCreateVertex(from).addEdge(getOrCreateVertex(to), weight);
        getVertex(to).addEdge(getVertex(from), weight);
    }

    @Override
    public Vertex getAnyVertex() {
        return getVertex(vertices.getAnyKey());
    }

    public Vertex getOrCreateVertex(int id) {
        if (!vertices.containsKey(id)) {
            vertices.put(id, new Vertex(id));
        }
        return vertices.get(id);
    }

    @Override
    public Vertex getVertex(int id) {
        if (!vertices.containsKey(id)) {
            return null;
        } else {
            return vertices.get(id);
        }
    }

    public boolean hasVertex(int id) {
        return vertices.containsKey(id);
    }

    @Override
    public Iterator<Vertex> iterator() {
        return vertices.values().iterator();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("WeightedGraph\n");
        for (Vertex vertex : this) {
            sb.append('\t').append(vertex).append('\n');
        }
        return sb.toString();
    }
}
