import java.util.ArrayList;
import java.util.Collections;

public class KruskalsAlgorithm {

    static class Edge implements Comparable<Edge>{
        int src;
        int dest;
        int weight;

        public Edge(int src, int dest, int weight){
            this.src = src;
            this.dest = dest;
            this.weight = weight;
        }

        @Override
        public int compareTo(KruskalsAlgorithm.Edge o2) {
            return this.weight - o2.weight; // get shortest weight
        }
    }

    // Disjoint Set

    static int n = 7; // setting size of set
    static int[] parent = new int[n];
    static int[] rank = new int[n]; // initially all zero

    public static void initialize(){
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
        }
    }

    public static int find(int x){
        if(parent[x] == x){
            return x;
        }

        return parent[x] = find(parent[x]); // directly putting the top most parent (optimization)
    }

    public static void union(int a, int b){
        int parent_A = find(a);
        int parent_B = find(b);

        if(rank[parent_A] == rank[parent_B]){ // add B to A,   B ---> A
            parent[parent_B] = parent_A;
            rank[parent_A]++;
        }else if(rank[parent_A] < rank[parent_B]){
            parent[parent_A] = parent_B;
        }else{
            parent[parent_B] = parent_A;
        }
    }

    public static void create_graph(ArrayList<Edge> edges){
        //edges
        edges.add(new Edge(0, 1, 10));
        edges.add(new Edge(0, 2, 15));
        edges.add(new Edge(0, 3, 30));
        edges.add(new Edge(1, 3, 40));
        edges.add(new Edge(2, 3, 50));
    }

    // O(E + ElogE)
    public static int kruskals_algorithm(ArrayList<Edge> edges, int V){
        initialize(); // initialize parent set
        Collections.sort(edges); // O(ElogE)
        int mst_cost = 0;
        int count = 0;

        // run for V-1 Times
        for(int i=0; count < V-1; i++){ // O(E)  --> E == V-1 For MST
            Edge e = edges.get(i);
            // src, dest, wt
            int parA = find(e.src);
            int parB = find(e.dest);  
            if(parA != parB){ // to avoid cycle condition
                // both belong to different set
                union(e.src, e.dest);
                mst_cost += e.weight;
                count++;
            }
        }
        return mst_cost;
    }
    public static void main(String[] args) {
        int V = 4;
        ArrayList<Edge> edges = new ArrayList<>();
        create_graph(edges);
        System.out.println("Cost of MST (Kruskal's Algorithm): "+kruskals_algorithm(edges, V));
    }
}
