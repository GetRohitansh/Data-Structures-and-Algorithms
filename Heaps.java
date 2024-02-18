import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Heaps {
    /* 
    Some Questions using Comparator Class Implementation
    Topics Covered:
     * Heap Class Implementation
     * Add, Remove, Heapify
     * Heap Sort
     * nearby_cars
     * Connect N ropes in minimum cost
     * Weakest Army Rows are
     * Sliding Window Maximum using Priority Queue
    */
    public static void main(String[] args) {
        HeapClass heap = new HeapClass();
        heap.add(2);
        heap.add(3);
        heap.add(4);
        heap.add(5);
        heap.add(10);
        heap.add(1);

        System.out.println(heap.list);
        heap.remove();
        System.out.println(heap.list);
        heap.remove();
        System.out.println(heap.list);

        int arr[] = new int[]{1,2,4,5,3};
        HeapClass.heapSort(arr);
        System.out.println("Heap Sort: "+Arrays.toString(arr));


        int points[][] = {{3,3}, {5,-1}, {-2, 4}};
        System.out.println("Nearby Cars: "+nearby_cars(points, 2));
        System.out.println("Connect N ropes with min. cost: "+connect_N_ropes_min_cost(new int[]{2,3,3,4,6}));

        int army[][] = {{1,0,0,0}, {1,1,1,1}, {1,0,0,0}, {1,0,0,0}};
        System.out.println("Weakest Army Rows are: "+weakestSoldier(army, 2));
        System.out.println("Sliding Window Maximum using Priority Queue: "+sliding_window_maximum(new int[]{1,3,-1,-3,5,3,6,7}, 3));
    }

    static class HeapClass {
        ArrayList<Integer> list = new ArrayList<>();

        public void add(int data){
            // add at last index
            list.add(data);
            int child_idx = list.size()-1;
            int parent_idx = (child_idx - 1)/2;

            while(list.get(child_idx) < list.get(parent_idx)){ // O(logN)
                //swap
                int temp = list.get(child_idx);
                list.set(child_idx, list.get(parent_idx));
                list.set(parent_idx, temp);

                child_idx = parent_idx;
                parent_idx = (child_idx - 1)/2;
            }
        }

        public int peek(){
            return list.get(0);
        }

        public int remove(){
            // 1. swap the first and last element
            int temp = list.get(0);
            list.set(0, list.get(list.size()-1));
            list.set(list.size()-1, temp);

            // 2. remove last element
            int ans = list.remove(list.size()-1);

            // 3. Heapify (fix the heap)
            heapify(0);

            return ans;
        }
        public void heapify(int idx){
            int left = 2 * idx + 1;
            int right = 2 * idx + 2;
            int min_idx = idx;

            if(left < list.size() && list.get(min_idx) > list.get(left)){
                min_idx = left; // left has smaller value
            }

            if(right < list.size() && list.get(min_idx) > list.get(right)){
                min_idx = right; // right has smaller value
            }

            if(min_idx != idx){
                // swap
                int temp = list.get(idx);
                list.set(idx, list.get(min_idx));
                list.set(min_idx, temp);

                heapify(min_idx);
            }    
        }

        public static void heapify_max(int idx, int size, int arr[]){
            int left = 2 * idx + 1;
            int right = 2 * idx + 2;
            int maxIdx = idx;

            if(left < size && arr[maxIdx] < arr[left]){
                maxIdx = left;
            }

            if(right < size && arr[maxIdx] < arr[right]){
                maxIdx = right;
            }

            if(maxIdx != idx){
                //swap
                int temp = arr[idx];
                arr[idx] = arr[maxIdx];
                arr[maxIdx] = temp;

                heapify_max(maxIdx, size, arr);
            }
        }
        public static void heapSort(int arr[]){ // T.C: O(log N), no extra space is used
            // step 1 - build max heap
            int n = arr.length;
            for(int i=n/2; i>=0; i--){
                heapify_max(i, n, arr);
            }

            // step 2 - push largest at end
            
            for(int i=n-1; i>0; i--){
                //swap largest, first
                int temp = arr[0];
                arr[0] = arr[i];
                arr[i] = temp;

                //fix the tree (leave last index)
                heapify_max(0, i, arr);
            }  
        }
    }

    static class Point implements Comparable<Point>{
        int x;
        int y;
        int dist_sqr;
        String name;

        static int counter;

        public Point(int x, int y){
            this.x = x;
            this.y = y;
            this.dist_sqr = x*x + y*y;
            this.name = "C"+counter;
            counter++;
        }

        @Override
        public int compareTo(Point p2) {
            return this.dist_sqr - p2.dist_sqr;
        }
    }

    public static String nearby_cars(int points[][], int k){

        PriorityQueue<Point> pq = new PriorityQueue<>();
        for(int i=0; i<points.length; i++){
            pq.add(new Point(points[i][0], points[i][1]));
        }
        
        String ans = "";
        while(k>0 && !pq.isEmpty()){
            ans += pq.remove().name + " ";
            k--;
        }
        return ans;
    }
 
    public static int connect_N_ropes_min_cost(int lengths[]){
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int i : lengths){
            pq.add(i);
        }

        int cost = 0;
        while(!pq.isEmpty()){
            int element1 = pq.remove();
            if(pq.isEmpty()){
                break;
            }
            int element2 = pq.remove();
            cost += element1 + element2;
            pq.add(element1 + element2);
        }
        return cost;
    }

    static class SoldierRow implements Comparable<SoldierRow> {
        int soldiers;
        int index;

        public SoldierRow(int soldiers, int index){
            this.soldiers = soldiers;
            this.index = index;
        }

        @Override
        public int compareTo(SoldierRow r2){
            if(this.soldiers == r2.soldiers){ // when equal lowest row first
                return this.index - r2.index;
            }
            return this.soldiers - r2.soldiers;
        }
    }

    public static ArrayList<Integer> weakestSoldier(int army[][], int k){
        ArrayList<Integer> ans = new ArrayList<>();
        PriorityQueue<SoldierRow> pq = new PriorityQueue<>();
        for(int i=0; i<army.length; i++){
            int count = 0;
            for(int j=0; j<army[0].length; j++){
               count =  army[i][j] == 1 ? 1 : 0;
            }
            pq.add(new SoldierRow(count, i));
        }

        for(int i=0; i<k; i++){
           ans.add(pq.remove().index);
        }

        return ans;
    }
    
    static class SlidingWindowPair implements Comparable<SlidingWindowPair>{
        int index;
        int number;

        public SlidingWindowPair(int number, int index){
            this.index = index;
            this.number = number;
        }

        @Override
        public int compareTo(SlidingWindowPair p2){
            return -(this.number - p2.number);  // -ve for implementing max heap
        }
    }

    public static ArrayList<Integer> sliding_window_maximum(int[] nums, int k){
        PriorityQueue<SlidingWindowPair> pq = new PriorityQueue<>();
        
        for(int i=0; i<k; i++){
            pq.add(new SlidingWindowPair(nums[i], i));
        }
        ArrayList<Integer> ans = new ArrayList<>();
        ans.add(pq.peek().number);

        for(int i=k; i<nums.length; i++){
            while(!pq.isEmpty() && pq.peek().index <= (i - k)){
                pq.remove();
            }
            pq.add(new SlidingWindowPair(nums[i], i));
            ans.add(pq.peek().number);
        }

        return ans;
    }
    
    
}
