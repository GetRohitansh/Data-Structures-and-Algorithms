import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Graphs_Lecture_3 {
    /* 
    Topics Covered: 
     * Topological Sort using BFS (Kahn's Algorithm)
     * All paths from Source to Target
     * Dijkstra's Algorithm
    */

    public static void main(String[] args) {
        Graph g1 = new Graph(6);
        g1.add_edge(2, 3);
        g1.add_edge(3, 1);
        g1.add_edge(4, 0);
        g1.add_edge(4, 1);
        g1.add_edge(5, 0);
        g1.add_edge(5, 2);

        Graph g2 = new Graph(6);
        g2.add_edge(5, 0);
        g2.add_edge(5, 2);
        g2.add_edge(2, 3);
        g2.add_edge(0, 3);
        g2.add_edge(4, 0);
        g2.add_edge(4, 1);
        g2.add_edge(3, 1);

        Graph g3 = new Graph(8);
        g3.add_edge(5, 0);
        g3.add_edge(5, 2);
        g3.add_edge(5, 3);
        g3.add_edge(2, 4);
        g3.add_edge(0, 4);
        g3.add_edge(3, 4);
        g3.add_edge(3, 6);
        g3.add_edge(6, 4);
        g3.add_edge(6, 7);
        g3.add_edge(4, 1);

        System.out.print("Topological Sort (Kahn's Algo) (BFS): ");
        topological_sort_bfs(g1.graph);

        System.out.println("All paths from src to target: ");
        all_paths_from_src_to_dest(g2.graph, 4, 1, new ArrayList<>());

        System.out.println("All paths from src to target: ");
        all_paths_from_src_to_dest(g3.graph, 5, 1, new ArrayList<>());

        Graph g4 = new Graph(6);
        g4.add_edge(0, 1, 2);
        g4.add_edge(0, 2, 4);
        g4.add_edge(1, 2, 1);
        g4.add_edge(1, 3, 7);
        g4.add_edge(2, 4, 3);
        g4.add_edge(3, 5, 1);
        g4.add_edge(4, 3, 2);
        g4.add_edge(4, 5, 5);

        System.out.println("Dijkstra's Algorithm: "); dijkstra_algorithm(g4.graph, 0);
    }

    public static void topological_sort_bfs(ArrayList<Graph.Edge> graph[]){
        int[] inDegree = new int[graph.length];
        Queue<Integer> queue = new LinkedList<>();

        calc_inDegree(graph, inDegree);

        //initialize queue
        for (int i = 0; i < graph.length; i++) {
            if(inDegree[i] == 0){
                queue.add(i);
            }
        }

        while (!queue.isEmpty()) {
            int curr = queue.remove();
            System.out.print(curr + " "); // topological sort

            for (int i = 0; i < graph[curr].size(); i++) {
                Graph.Edge edge = graph[curr].get(i);
                inDegree[edge.dest]--;
                if(inDegree[edge.dest] == 0){
                    queue.add(edge.dest);
                }
            }
        }
        System.out.println();
    }
    public static void calc_inDegree(ArrayList<Graph.Edge> graph[], int[] inDegree){
        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph[i].size(); j++) {
                Graph.Edge edge = graph[i].get(j);
                inDegree[edge.dest]++;
            }
        }
    }

    public static void all_paths_from_src_to_dest(ArrayList<Graph.Edge> graph[], int src, int dest, ArrayList<Integer> path){
        if(src == dest){
            path.add(dest);
            System.out.println(path);
            path.remove(path.size()-1);
            return;
        }

        for(int i=0; i<graph[src].size(); i++){
            Graph.Edge edge = graph[src].get(i);
            path.add(edge.src);
            all_paths_from_src_to_dest(graph, edge.dest, dest, path);
            path.remove(path.size()-1); // backtracking
        }
    }


    static class Pair implements Comparable<Pair>{
        int node;
        int path_cost;

        public Pair(int node, int path_cost){
            this.node = node;
            this.path_cost = path_cost;
        }

        @Override
        public int compareTo(Graphs_Lecture_3.Pair o2) {
            return this.path_cost - o2.path_cost; // value with less value is returned
        }
    }
    public static void dijkstra_algorithm(ArrayList<Graph.Edge> graph[], int src){
        // initialize distance
        int distance[] = new int[graph.length];
        for(int i=0; i<distance.length; i++){
            if(i != src){
                distance[i] = Integer.MAX_VALUE; // +infinity
            }
        }

        boolean visited[] = new boolean[graph.length];
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        pq.add(new Pair(src, 0));
        while (!pq.isEmpty()) {
            Pair curr = pq.remove();
            if(!visited[curr.node]){
                visited[curr.node] = true;
                for (int i = 0; i < graph[curr.node].size(); i++) {
                    Graph.Edge edge = graph[curr.node].get(i);
                    int u = edge.src;
                    int v = edge.dest;
                    //update distance
                    if(distance[v] > distance[u] + edge.weight){
                        distance[v] = distance[u] + edge.weight;
                        pq.add(new Pair(v, distance[v]));
                    }
                }
            }
        }

        int count = 0;
        System.out.println("\tShortest Path's Cost");
        for(int i : distance){
            System.out.println("\t\t"+count + "->" + i);
            count++;
        }
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