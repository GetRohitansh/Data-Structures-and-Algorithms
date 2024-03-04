public class Disjoint_Set_Union {

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
    public static void main(String[] args) {
        initialize();
        System.out.println("Find: "+find(3));
        System.out.println("Union"); union(1, 3);
        System.out.println("Find: "+find(3));
        System.out.println("Union"); union(2, 4);
        System.out.println("Union"); union(3, 6);
        System.out.println("Union"); union(1, 4);
        System.out.println("Find: "+find(4));
        System.out.println("Union"); union(1, 5);
    }
}
