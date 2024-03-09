import java.util.ArrayList;
import java.util.Stack;

public class Graphs_Supplemental {
    /* 
    Topics Covered: 
     * Strongly Connected Components (Kosaraju Algorithm)
     * Bridges in the Graph (Tarjan's Algorithm)
     * Articulation Point in the Graph (Tarjan's Algorithm)
    */

    public static void main(String[] args) {
        Graph g = new Graph(5);
        g.add_edge(1, 0);
        g.add_edge(0, 2);
        g.add_edge(2, 1);
        g.add_edge(0, 3);
        g.add_edge(3, 4);

        System.out.println("Strongly Connected Components (Kosaraju Algorithm): "); kosaraju_algorithm(g.graph);

        Graph g2 = new Graph(6);
        g2.add_edge(1, 0);
        g2.add_edge(0, 1);

        g2.add_edge(1, 2);
        g2.add_edge(2, 1);

        g2.add_edge(2, 0);
        g2.add_edge(0, 2);

        g2.add_edge(3, 0);
        g2.add_edge(0, 3);

        g2.add_edge(3, 4);
        g2.add_edge(4, 3);

        g2.add_edge(5, 4);
        g2.add_edge(4, 5);

        g2.add_edge(5, 3);
        g2.add_edge(3, 5);

        System.out.println("Bridges in the Graph: "); bridges_in_graph(g2.graph);

        Graph g3 = new Graph(5);
        g3.add_edge(0, 1);
        g3.add_edge(0, 2);
        g3.add_edge(0, 3);

        g3.add_edge(1, 0);
        g3.add_edge(1, 2);

        g3.add_edge(2, 0);
        g3.add_edge(2, 1);

        g3.add_edge(3, 0);
        g3.add_edge(3, 4);

        g3.add_edge(4, 3);

        System.out.println("Articulation Points in the Graph: "); articulation_points(g3.graph);

    }

    // Strongly Connected Component
    public static void kosaraju_algorithm(ArrayList<Graph.Edge> graph[]){
        // step 1: Get nodes in stack
        Stack<Integer> stack = new Stack<>();
        boolean visited[] = new boolean[graph.length];
        for (int i = 0; i < visited.length; i++) {
            if(visited[i] == false){
                topological_sort(graph, i, visited, stack);
            }
        }

        // step 2: Transpose the graph
        Graph transpose = new Graph(graph.length);

        // reverse all edges
        for(int i=0; i<graph.length; i++){
            for(int j=0; j<graph[i].size(); j++){
                Graph.Edge edge = graph[i].get(j);
                transpose.add_edge(edge.dest, edge.src); 
            }
        }

        // step 3: Perform DFS on transposed graph according to Topological Sort order
        visited = new boolean[graph.length];
        while(!stack.isEmpty()){
            int curr = stack.pop();
            if(visited[curr] == false){
                System.out.print("\tSCC -> ");
                dfs(transpose.graph, visited, curr);
                System.out.println();
            }
        }
    }
    public static void topological_sort(ArrayList<Graph.Edge> graph[], int curr, boolean visited[], Stack<Integer> stack){
        visited[curr] = true;

        for(int i=0; i<graph[curr].size(); i++){
            Graph.Edge edge = graph[curr].get(i);
            if(visited[edge.dest] == false){
                topological_sort(graph, edge.dest, visited, stack);
            }
        }

        stack.push(curr);
    }
    public static void dfs(ArrayList<Graph.Edge> graph[], boolean visited[], int curr){
        visited[curr] = true;
        System.out.print(curr + " ");

        for(int i=0; i<graph[curr].size(); i++){
            Graph.Edge edge = graph[curr].get(i);
            if(visited[edge.dest] == false){
                dfs(graph, visited, edge.dest);
            }
        }
    }


    public static void bridges_in_graph(ArrayList<Graph.Edge> graph[]){
        boolean visited[] = new boolean[graph.length];
        int discoveryTime[] = new int[graph.length];
        int low[] = new int[graph.length];
        for(int i=0; i<graph.length; i++){
            if(visited[i] == false){
                bridges_in_graph_helper(graph, visited, i, -1, discoveryTime, low, 0);
            }
        }
    }

    // Bridge in Graph (Modified DFS)
    public static void bridges_in_graph_helper(ArrayList<Graph.Edge> graph[], boolean visited[], int curr, int parent, int discoveryTime[], int low[], int time){

        visited[curr] = true;
        discoveryTime[curr] = low[curr] = ++time; // start from 1

        for(int i=0; i<graph[curr].size(); i++){
            Graph.Edge edge = graph[curr].get(i);
            int neighbour = edge.dest;

            if(neighbour != parent){ // neighbour not parent
                if(visited[neighbour] == false){ 
                    bridges_in_graph_helper(graph, visited, neighbour, curr, discoveryTime, low, time);
                    low[curr] = Math.min(low[curr], low[neighbour]); // low = lowest of low of neighbour
                    if(low[neighbour] > discoveryTime[curr]){ // if low[curr] < low[neightbour] // no other way to reach
                        System.out.println("\t"+curr+" -> "+neighbour);
                    }
                }else{
                    // already visited
                    low[curr] = Math.min(low[curr], discoveryTime[neighbour]);
                }
            }
            
        }
    }

    public static void articulation_points(ArrayList<Graph.Edge> graph[]){
        int discoveryTime[] = new int[graph.length];
        int low[] = new int[graph.length]; // lowest discovery time
        int time = 0;
        boolean visited[] = new boolean[graph.length];
        boolean ap[] = new boolean[graph.length];

        for(int i=0; i<graph.length; i++){
            if(visited[i] == false){
                
                articulation_points_helper(graph, i, -1, time, discoveryTime, low, visited, ap);
            }
        }


        System.out.print("\t");
        for(int i=0; i<graph.length; i++){
            if(ap[i] == true){                
                System.out.print(i + ", ");
            }
        }
        System.out.println();
    }

    public static void articulation_points_helper(ArrayList<Graph.Edge> graph[], int curr, int parent, int time,
                                                  int discoveryTime[], int low[], boolean visited[], boolean ap[]){
        
        visited[curr] = true;
        discoveryTime[curr] = low[curr] = ++time;
        int children = 0;

        for(int i=0; i<graph[curr].size(); i++){
            Graph.Edge e = graph[curr].get(i);
            int neighbour = e.dest;

            if(parent == neighbour){ // ignore
                continue;
            } else if(visited[neighbour] == true){
                low[curr] = Math.min(low[curr], discoveryTime[neighbour]);
            } else {
                articulation_points_helper(graph, neighbour, curr, time, discoveryTime, low, visited, ap);
                low[curr] = Math.min(low[curr], low[neighbour]);
                // possibility of articulation point
                if(parent != -1 && discoveryTime[curr] <= low[neighbour]){
                    // System.out.print(curr + ", ");
                    ap[curr] = true; // curr is A.P.
                }
                children++;
            }

        }

        if(parent == -1 && children > 1){
            ap[curr] = true;
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

        @Override
        public String toString() {
            return "("+src+", "+dest+")";
        }
    }

    void add_edge(int src, int dest, int weight){
        graph[src].add(new Edge(src, dest, weight));
    }

    void add_edge(int src, int dest){
        graph[src].add(new Edge(src, dest));
    }
}