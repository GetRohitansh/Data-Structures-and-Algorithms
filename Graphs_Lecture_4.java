import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
public class Graphs_Lecture_4 {
    /* 
    Topics Covered: 
     * Bellman Ford Algorithm (shortest path)
     * Prims Algorithm (Minimum Spanning Tree)
    */
    public static void main(String[] args) {
        Graph g1 = new Graph(5);
        g1.add_edge(0, 1, 2);
        g1.add_edge(0, 2, 4);
        g1.add_edge(1, 2, -4);
        g1.add_edge(2, 3, 2);
        g1.add_edge(3, 4, 4);
        g1.add_edge(4, 1, -1);
        System.out.print("Bellman Ford Algorithm (shortest path): "); bellman_ford_algorithm(g1.graph, 0);

        Graph g2 = new Graph(4);
        g2.add_edge(0, 1, 10);
        g2.add_edge(1, 0, 10);

        g2.add_edge(1, 3, 40);
        g2.add_edge(3, 1, 40);

        g2.add_edge(3, 2, 50);
        g2.add_edge(2, 3, 50);

        g2.add_edge(0, 2, 15);
        g2.add_edge(2, 0, 15);

        g2.add_edge(0, 3, 30);
        g2.add_edge(3, 0, 30);
        System.out.print("Prims Algorithm (Minimum Spanning Tree): "); prims_algorithm(g2.graph, 0);
    }

    // does not work for negative weighted cycles
    public static void bellman_ford_algorithm(ArrayList<Graph.Edge> graph[], int src){
        int distance[] = new int[graph.length];
        // initialize distance
        for(int i=0; i<distance.length; i++){
            if(i != src){
                distance[i] = Integer.MAX_VALUE;
            }
        }

        // operation V-1 times   O(V * E)
        for(int k=0; k<graph.length - 1; k++){

            // visit all edges O(E) time complexity
            for(int i=0; i<graph.length - 1; i++){
                for (int j = 0; j < graph[i].size(); j++) {
                    Graph.Edge edge = graph[i].get(j);
                    int u = edge.src;
                    int v = edge.dest;

                    // relaxation of the edge
                    if(distance[v] > distance[u] + edge.weight){
                        distance[v] = distance[u] + edge.weight;
                    }
                }
            }
        }

        System.out.println(Arrays.toString(distance));
    }

    static class Pair implements Comparable<Pair>{
        int vertex;
        Graph.Edge edge;
        int cost;

        public Pair(int vertex, int cost, Graph.Edge edge){
            this.vertex = vertex;
            this.cost = cost;
            this.edge = edge;
        }

        @Override
        public int compareTo(Graphs_Lecture_4.Pair o2) {
            return this.cost - o2.cost; // return smaller
        }
    }
    public static void prims_algorithm(ArrayList<Graph.Edge> graph[], int src){

        PriorityQueue<Pair> pq = new PriorityQueue<>();
        pq.add(new Pair(src, 0, null));

        boolean visited[] = new boolean[graph.length];
        int cost = 0; // MST total cost

        ArrayList<Graph.Edge> list = new ArrayList<>();
        
        while(!pq.isEmpty()){
            Pair curr = pq.remove();
            if(visited[curr.vertex] == false){ // if the vertex is not visited then only perform
                cost += curr.cost;
                visited[curr.vertex] = true;

                list.add(curr.edge); // store node and its cost

                // add neighbours to pq
                for(int i=0; i<graph[curr.vertex].size(); i++){
                    Graph.Edge edge = graph[curr.vertex].get(i);
                    pq.add(new Pair(edge.dest, edge.weight, edge)); // even if it adds visited, due to above condition cost won't increase
                }
            }
        }

        System.out.println(cost);
        System.out.print("\tEdges of MST are ");
        for(Graph.Edge edge : list){
            if(edge != null){
                System.out.print(edge.src + " -> " + edge.dest + ", ");
            }
        }
        System.out.println();
    }
}

class Graph {
    int V;
    ArrayList<Edge>[] graph;

    @SuppressWarnings("unchecked")
    public Graph(int V){
        this.V = V;        
        graph = new ArrayList[V];
        for(int i=0; i<V; i++){
            this.graph[i] = new ArrayList<>();
        }
    }
    
    class Edge {
        int src;
        int dest;
        int weight;

        public Edge(int src, int dest, int weight){
            this.src = src;
            this.dest = dest;
            this.weight = weight;
        }

        public Edge(int src, int dest){
            this.src = src;
            this.dest = dest;
            this.weight = 1;
        }
    }

    void add_edge(int src, int dest, int weight){
        graph[src].add(new Edge(src, dest, weight));
    }

    void add_edge(int src, int dest){
        graph[src].add(new Edge(src, dest));
    }
}
