import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Graphs_Lecture_5 {
    /* 
    Topics Covered: 
     * Cheapest Flight with K stops
     * Connecting Cities With Minimum Cost (MSP using Prims Algo)
     * Flood Fill Algorithm
    */
    public static void main(String[] args) {
        Graph g1 = new Graph(4);
        g1.add_edge(0, 1, 100);
        g1.add_edge(1, 2, 100);
        g1.add_edge(2, 0, 100);
        g1.add_edge(1, 3, 600);
        g1.add_edge(2, 3, 200);
        System.out.println("Cheapest Flight with K stops: "+cheapest_flight_with_k_stops(g1.graph, 0, 3, 1));

        Graph g2 = new Graph(5);
        g2.add_edge(0, 1, 1);
        g2.add_edge(0, 2, 2);
        g2.add_edge(0, 3, 3);
        g2.add_edge(0, 4, 4);

        g2.add_edge(1, 0, 1);
        g2.add_edge(1, 2, 5);
        g2.add_edge(1, 4, 7);
        
        g2.add_edge(2, 0, 2);
        g2.add_edge(2, 1, 5);        
        g2.add_edge(2, 3, 6);
        
        g2.add_edge(3, 0, 3);
        g2.add_edge(3, 2, 6);

        g2.add_edge(4, 0, 4);
        g2.add_edge(4, 1, 7);
        System.out.println("Connecting Cities With Minimum Cost (MSP using Prims Algo): "+connecting_cities_with_minimum_cost(g2.graph));


        System.out.println("Flood Fill Algorithm: ");        
        int[][] graph1 = {{1,1,1},
                          {1,1,0},
                          {1,0,1}};
        graph1 = flood_fill_algo(graph1, 1, 1, 2); // original color is 1
        
        for(int[] arr : graph1){
            System.out.println(Arrays.toString(arr));
        }
        System.out.println();

        int[][] graph2 = {{0,0,0},
                          {0,0,0}};
        graph2 = flood_fill_algo(graph2, 1, 1, 2); // original color is 1
        for(int[] arr : graph2){
            System.out.println(Arrays.toString(arr));
        }
    }

    static class Info { 
        int vertex;
        int stops;
        int cost;

        public Info(int vertex, int cost, int stops){
            this.vertex = vertex;
            this.cost = cost;
            this.stops = stops;
        }
    }
    //uses dijkstra's  modified
    public static int cheapest_flight_with_k_stops(ArrayList<Graph.Edge> graph[], int src, int dest, int k){

        // initializing distance
        int distance[] = new int[graph.length];
        for(int i=0; i<distance.length; i++){
            if(i != src){
                distance[i] = Integer.MAX_VALUE;
            }
        }

        Queue<Info> q = new LinkedList<>();
        q.add(new Info(src, 0, 0));

        while(!q.isEmpty()){
            Info curr = q.remove();
            if(curr.stops > k ){
                break;
            }

            // relax neightbours
            for(int i=0; i<graph[curr.vertex].size(); i++){
                Graph.Edge edge = graph[curr.vertex].get(i);
                int v = edge.dest;
                // relaxing neighbours,  if curr.cost
                if(distance[v] > curr.cost + edge.weight && curr.stops <= k){
                    distance[v] = curr.cost + edge.weight;
                    q.add(new Info(v, distance[v], curr.stops+1));
                }
                
            }
            
        }

        return distance[dest];
    }


    static class Pair implements Comparable<Pair> {
        int vertex;
        int distance;

        public Pair(int vertex, int distance){
            this.vertex = vertex;
            this.distance = distance;
        }

        @Override
        public int compareTo(Graphs_Lecture_5.Pair o2) {
            return this.distance - o2.distance;
        }
    }
    public static int connecting_cities_with_minimum_cost(ArrayList<Graph.Edge> graph[]){
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        boolean visited[] = new boolean[graph.length];

        int min_cost = 0;
        pq.add(new Pair(0, 0)); // src, distance -> 0, 0
        while(!pq.isEmpty()){
            Pair curr = pq.remove();
            if(visited[curr.vertex] == false){
                visited[curr.vertex] = true;
                min_cost += curr.distance;
                // add its neighbours to the priority queue
                for(int i=0; i<graph[curr.vertex].size(); i++){
                    Graph.Edge edge = graph[curr.vertex].get(i);
                    pq.add(new Pair(edge.dest, edge.weight + curr.distance));
                }
            }
        }

        return min_cost;
    }


    public static int[][] flood_fill_algo(int[][] image, int startrow, int startcol, int color){
        boolean visited[][] = new boolean[image.length][image[0].length];
        helper_function(image, startrow, startcol, color, visited, image[startrow][startcol]);
        // original color is color that is encountered for very first time
        return image;
    }

    public static void helper_function(int[][] image, int startrow, int startcol, int color, boolean visited[][], int originalcolor){

         //checking boundary conditions
         if(startcol < 0 || startcol >= image[0].length || startrow < 0 || startrow >= image.length){
            return;
        }

        // original color is color that is encountered for very first time
        // is already visited, or any other color than required one
        if(visited[startrow][startcol] == true  || image[startrow][startcol] != originalcolor){
            return;
        }



        
        image[startrow][startcol] = color;
        visited[startrow][startcol] = true;

        // left
        helper_function(image, startrow, startcol-1, color, visited, originalcolor);
        //right
        helper_function(image, startrow, startcol+1, color, visited, originalcolor);
        //down
        helper_function(image, startrow+1, startcol, color, visited, originalcolor);
        //up
        helper_function(image, startrow-1, startcol, color, visited, originalcolor);
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