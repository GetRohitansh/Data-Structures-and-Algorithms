import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Graphs_Lecture_1 {
    /* 
    Topics Covered: 
     * Find Neighbour of Node
     * BFS Traversal (2 Methods)
     * Check if given graph has path from Soure to Destination
    */
    public static void main(String[] args) {
        Graph g1 = new Graph(5);  // takes input -> number of vertex

        // Vertex 0
        g1.add_edge(0, 1, 5);
        // Vertex 1
        g1.add_edge(1, 0, 5);
        g1.add_edge(1, 2, 1);
        g1.add_edge(1, 3, 3);
        // Vertex 2
        g1.add_edge(2, 1, 1);
        g1.add_edge(2, 3, 1);
        g1.add_edge(2, 4, 2);
        // Vertex 3
        g1.add_edge(3, 2, 1);
        g1.add_edge(3, 1, 3);
        // Vertex 4
        g1.add_edge(4, 2, 2);

        System.out.print("Find Neighbour of Node: ");
        g1.find_neighbour_of(2);


        Graph g2 = new Graph(7);
        // Vertex 0
        g2.add_edge(0, 1);
        g2.add_edge(0, 2);

        // Vertex 1
        g2.add_edge(1, 0);
        g2.add_edge(1, 3);

        // Vertex 2
        g2.add_edge(2, 0);
        g2.add_edge(2, 4);

        // Vertex 3
        g2.add_edge(3, 1);
        g2.add_edge(3, 4);
        g2.add_edge(3, 5);

        // Vertex 4
        g2.add_edge(4, 2);
        g2.add_edge(4, 3);
        g2.add_edge(4, 5);

        // Vertex 5
        g2.add_edge(5, 3);
        g2.add_edge(5, 4);
        g2.add_edge(5, 6);

        // Vertex 6
        g2.add_edge(6, 5);
        System.out.print("BFS Traversal (method 1): ");
        g2.BFS_traversal();
        System.out.print("BFS Traversal (method 2): ");
        g2.BFS_traversal_2();
        System.out.print("DFS Traversal: ");
        g2.DFS_traversal(new boolean[g2.V], 0);
        System.out.println();
        System.out.println("Has Path (DFS Traversal): "+has_path(g2.graph, 0, 5, new boolean[g2.V]));
        System.out.println("Has Path (DFS Traversal): "+has_path(g2.graph, 0, 7, new boolean[g2.V]));

    }

    public static boolean has_path(ArrayList<Graph.Edge> graph[], int src, int dest, boolean visited[]){
        if(src == dest){
            return true;
        }
        visited[src] = true;        
        for(int i=0; i<graph[src].size(); i++){
            Graph.Edge edge = graph[src].get(i);
            // edge.dest is my immediate neightbour
            if(!visited[edge.dest] && has_path(graph, edge.dest, dest, visited)){ // search if unvisited neighbour can reach destination
                return true;
            } 
        }
        return false;
    }
}

class Graph {
    int V;
    ArrayList<Edge>[] graph; //int[] arr = new int[]

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

    void find_neighbour_of(int node){
        for(int i=0; i<graph[node].size(); i++){
            Edge edge = graph[node].get(i);
            System.out.print(edge.dest + " ");
        }
        System.out.println();
    }

    void BFS_traversal(){
        Queue<Integer> queue = new LinkedList<>();
        boolean visited[] = new boolean[V];

        queue.add(0);

        // fill all nodes in queue then check if it is already visited or not
        while(!queue.isEmpty()){
            int curr = queue.remove(); 
            if(visited[curr] == false){ // visit current
                System.out.print(curr + " ");
                visited[curr] = true;
                for(int i=0; i<graph[curr].size(); i++){
                    Edge edge = graph[curr].get(i);
                    queue.add(edge.dest);
                }
            }
        }
        System.out.println();
    }
    void BFS_traversal_2(){
        Queue<Integer> queue = new LinkedList<>();
        boolean visited[] = new boolean[V];

        queue.add(0);
        visited[0] = true;

        // before filling check if it is visited
        while(!queue.isEmpty()){
            int curr = queue.remove(); 
            System.out.print(curr + " ");
            for(int i=0; i<graph[curr].size(); i++){
                Edge edge = graph[curr].get(i);
                if(visited[edge.dest] == false){
                    queue.add(edge.dest);
                    visited[edge.dest] = true;
                }
            }
        }
        System.out.println();
    }

    void DFS_traversal(boolean visited[],  int curr){
        if(visited[curr] == true){ // already visited 
            return;
        }
        visited[curr] = true; 
        System.out.print(curr + " ");
        for(int i=0; i<graph[curr].size(); i++){
            Edge edge = graph[curr].get(i);
            DFS_traversal(visited, edge.dest);
        }
    }
}