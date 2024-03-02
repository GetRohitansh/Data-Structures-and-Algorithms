import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Graphs_Lecture_2 {

    /* 
    Topics Covered: 
     * BFS Traversal for Disjoint Components
     * DFS Traversal for Disjoint Components
     * Detect Cycle (Directed Graph)
     * Detect Cycle (Undirected Graph)
     * Check if Bipartite Graph
     * Topological Sort using DFS
    */
    public static void main(String[] args) {
         // takes input -> number of vertex
        Graph g2 = new Graph(10);
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

        // Vertex 7
        g2.add_edge(7, 8);
        g2.add_edge(7, 9);

        // Vertex 8
        g2.add_edge(8, 7);

        // Vertex 9
        g2.add_edge(9, 7);

        System.out.print("BFS Traversal for Disjoint Components: ");
        g2.BFS_traversal();
        System.out.println();
        System.out.print("DFS Traversal for Disjoint Components: ");
        g2.DFS_traversal();
        System.out.println();
        System.out.println("Cycle Exists (Undirected): "+detect_cycle_undirected(g2.graph));



        Graph g3 = new Graph(5);

        // Vertex 0
        g3.add_edge(0, 1);
        g3.add_edge(0, 2);
        // Vertex 1
        g3.add_edge(1, 0);
        g3.add_edge(1, 3);
        // Vertex 2
        g3.add_edge(2, 0);
        g3.add_edge(2, 4);
        // Vertex 3
        g3.add_edge(3, 1);
        g3.add_edge(3, 4);
        // Vertex 4
        g3.add_edge(4, 2);
        g3.add_edge(4, 3);

        System.out.println("Is Bipartite Graph (BFS): "+bipartite_graph_bfs(g3.graph));

        Graph g4 = new Graph(4);

        // Vertex 0
        g4.add_edge(0, 1);
        g4.add_edge(0, 3);
        // Vertex 1
        g4.add_edge(1, 0);
        g4.add_edge(1, 2);
        // Vertex 2
        g4.add_edge(2, 1);
        g4.add_edge(2, 3);
        // Vertex 3
        g4.add_edge(3, 0);
        g4.add_edge(3, 2);

        System.out.println("Is Bipartite Graph (BFS): "+bipartite_graph_bfs(g4.graph));

        Graph g5 = new Graph(4);
        g5.add_edge(0, 1);
        g5.add_edge(0, 2);
        g5.add_edge(1, 3);
        g5.add_edge(2, 3);
        System.out.println("Detect Cycle (Directed) (DFS): "+cycle_detection_directed(g5.graph));

        Graph g6 = new Graph(4);
        g6.add_edge(1, 0);
        g6.add_edge(0, 2);
        g6.add_edge(2, 3);
        g6.add_edge(3, 0);
        System.out.println("Detect Cycle (Directed) (DFS): "+cycle_detection_directed(g6.graph));

        Graph g7 = new Graph(6);
        g7.add_edge(5, 0);
        g7.add_edge(5, 2);
        g7.add_edge(4, 0);
        g7.add_edge(4, 1);
        g7.add_edge(2, 3);
        g7.add_edge(3, 1);
        System.out.print("Topological Sort (DFS): "); topological_sort_dfs(g7.graph);
    }


    // src has no parent element
    public static boolean detect_cycle_undirected(ArrayList<Graph.Edge> graph[]){
        boolean visited[] = new boolean[graph.length];
        for(int i=0; i<graph.length; i++){
            if(visited[i] == false){
                if(detect_cycle_undirected_util(graph, visited, i, -1)){ // diff components have diff src, and src does not have parents
                    return true;
                    // cycle exist in one of the parts/components
                }
            }
        }

        return false;
    }

    public static boolean detect_cycle_undirected_util(ArrayList<Graph.Edge> graph[], boolean visited[], int curr, int parent){
        visited[curr] = true;

        for(int i=0; i<graph[curr].size(); i++){
            Graph.Edge edge = graph[curr].get(i); // for next I am parent, and dest is child

            // case 3
            if(visited[edge.dest] == false){
                if(detect_cycle_undirected_util(graph, visited, edge.dest, edge.src)){
                    return true;
                }
            }

            // case 1
            else if(visited[edge.dest] == true && edge.dest != parent){
                return true;
            }

            // case 2 -> do nothing
        }

        return false;
    }

    public static boolean bipartite_graph_bfs(ArrayList<Graph.Edge> graph[]){
        int[] color = new int[graph.length];
        for(int i=0; i<color.length; i++){
            color[i] = -1;
        }

        for(int i=0; i<graph.length; i++){
            if(color[i] == -1){ // still not visited
                if(bipartite_graph_bfs_util(graph, color, i)){
                    return true;
                }
            }
        }

        return false;
    }

    public static boolean bipartite_graph_bfs_util(ArrayList<Graph.Edge> graph[], int[] color, int src){
        Queue<Integer> queue = new LinkedList<>();
        queue.add(src);
        color[src] = 0; // start from yellow color

        while(!queue.isEmpty()){
            int curr = queue.remove();
            for(int i=0; i<graph[curr].size(); i++){

                Graph.Edge edge = graph[curr].get(i);
                

                // add its neightbours in queue if not already visited
                if(color[edge.dest] == -1){
                    queue.add(edge.dest);
                    color[edge.dest] = color[curr] == 0 ? 1 : 0; // fill opposite color
                }
                else if(color[curr] == color[edge.dest]){ // neighbour already has same color, return false
                    return false; // not bipartite
                }
            }
        }
        return true; // all graph is filled, without returning false, it is bipartite
    }

    public static boolean cycle_detection_directed(ArrayList<Graph.Edge> graph[]){
        boolean stack[] = new boolean[graph.length]; // representation of call stack
        boolean visited[] = new boolean[graph.length];

        for(int i=0; i<graph.length; i++){
            if(visited[i] == false){
                if(cycle_detection_directed_util(graph, i, visited, stack)){
                    return true;
                }
            }
        }

        return false;
    }
    public static boolean cycle_detection_directed_util(ArrayList<Graph.Edge> graph[], int src, boolean visited[], boolean stack[]){
        visited[src] = true;
        stack[src] = true;
        for(int i=0; i<graph[src].size(); i++){
            Graph.Edge edge = graph[src].get(i);
            // As it is DFS, if cycle then the current (recursion) call stack after making a turn reaches to its start node 
            if(stack[edge.dest] == true){ // if neighbour already in stack for current recursion
                return true;
            }

            if(visited[edge.dest] == false){ // if neighbour not visited
                if(cycle_detection_directed_util(graph, edge.dest, visited, stack)){
                    return true;
                }
            }
        }
        stack[src] = false; // remove curr from stack
        return false;
    }

    public static void topological_sort_dfs(ArrayList<Graph.Edge> graph[]){
        Stack<Integer> stack = new Stack<>();
        boolean[] visited = new boolean[graph.length];

        for(int i=0; i<graph.length; i++){
            if(visited[i] == false){
                topological_sort_dfs_util(graph, i, visited, stack);
            }
        }

        while(!stack.isEmpty()){
            System.out.print(stack.pop() + " ");
        }
        System.out.println();
    }

    public static void topological_sort_dfs_util(ArrayList<Graph.Edge> graph[], int src, boolean visited[], Stack<Integer> stack){
        visited[src] = true;
        for(int i=0; i<graph[src].size(); i++){
            Graph.Edge edge = graph[src].get(i);
            if(visited[edge.dest] == false){
                topological_sort_dfs_util(graph, edge.dest, visited, stack);
            }
        }
        stack.add(src);
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

    // For Disjoint Components

    void BFS_traversal_util(boolean visited[], int src){
        Queue<Integer> queue = new LinkedList<>();
        queue.add(src);

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
    }

    void BFS_traversal(){
        boolean visited[] = new boolean[V];
        for (int i = 0; i < graph.length; i++) {
            if(visited[i] == false){
                BFS_traversal_util(visited, i);
            }
        }
    }

    void DFS_traversal_util(boolean visited[],  int curr){
        if(visited[curr] == true){ // already visited 
            return;
        }
        visited[curr] = true; 
        System.out.print(curr + " ");
        for(int i=0; i<graph[curr].size(); i++){
            Edge edge = graph[curr].get(i);
            DFS_traversal_util(visited, edge.dest);
        }
    }

    void DFS_traversal(){
        boolean visited[] = new boolean[V];
        for (int i = 0; i < graph.length; i++) {
            if(visited[i] == false){
                DFS_traversal_util(visited, i);
            }
        }
    }
}