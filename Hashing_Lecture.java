import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Set;
import java.util.TreeMap;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

public class Hashing_Lecture {

    public static void main(String args[]){
        HashMap<String, Integer> hm = new HashMap<>();
        hm.put( "India", 100);
        hm.put("China", 150);
        hm.put("Indonesia", 30);

        System.out.println(hm);
        System.out.println("Population in China: "+hm.get("China"));
        System.out.println("Is China present in hashmap: "+hm.containsKey("China"));
        System.out.println("Removing China...");
        hm.remove("China");
        System.out.println(hm);
        System.out.print("Clear Hashmap: ");
        hm.clear();
        System.out.println(hm);



        //Iteration Over HashMaps
        hm.put( "India", 100);
        hm.put("China", 150);
        hm.put("Indonesia", 30);
        hm.put("Nepal", 5);
        hm.put("US", 50);
        Set<String> keys = hm.keySet();
        System.out.println("Keys are: "+keys);

        for (String key : keys) {
            System.out.printf("\tCountry: %s, Size: %d\n", key, hm.get(key));
        }
        
        System.out.println("\n\tSELF IMPLEMENTATION");
        HashMap_self<String, Integer> hm_self = new HashMap_self<>();
        hm_self.put("India", 121);
        hm_self.put("China", 200);
        hm_self.put("US", 100);
        hm_self.put("Indonesia", 10);

        ArrayList<String> keyset = hm_self.keySet();

        System.out.println(hm_self);
        System.out.println(keyset);
        System.out.println("Get India:" + hm_self.get("India"));
        System.out.println("Removing China..."); hm_self.remove("China");
        System.out.println(hm_self);


        System.out.println("\n\tLINKED HASHMAP");
        LinkedHashMap<String, Integer> lhm = new LinkedHashMap<>();
        lhm.put("India", 100);
        lhm.put("China", 150);
        lhm.put("US", 50);

        System.out.println(lhm);

        System.out.println("\n\tTREE MAP");
        TreeMap<String, Integer> tm = new TreeMap<>();
        tm.put("India", 100);
        tm.put("China", 150);
        tm.put("US", 50);
        tm.put("Indonesia", 10);

        System.out.println(tm);

        System.out.println("\n\t QUESTIONS");
        
        System.out.print("Majority Elements: "); majority_elements(new int[]{1,3,2,5,1,3,1,5,1});
        System.out.println("Valid Anagram: "+valid_anagram("knee", "neek"));

        System.out.println("\n\t HASHSET");
        HashSet<Integer> set = new HashSet<>();
        set.add(1);
        set.add(2);
        set.add(4);
        set.add(1);
        set.add(4);
        System.out.println(set);

        Iterator<Integer> itr = set.iterator();

        System.out.print("Using Iterator: ");
        while(itr.hasNext()){
            System.out.print(itr.next() + " ");
        }
        System.out.println();
        System.out.println("Count Distinct Elements: "+count_distinct_elements(new int[]{4,3,2,5,6,7,3,4,2,1}));
        System.out.println("Union: "+union(new int[]{7,3,9}, new int[]{6,3,9,2,9,4}));
        System.out.println("Intersection: "+intersection(new int[]{7,3,9}, new int[]{6,3,9,2,9,4}));

        HashMap<String, String> tickets = new HashMap<>();
        tickets.put("Chennai", "Bengaluru");
        tickets.put("Mumbai", "Delhi");
        tickets.put("Goa", "Chennai");
        tickets.put("Delhi", "Goa");
        System.out.println("Find Iternary From Tickets: "+find_iternary_from_ticket(tickets));
        System.out.println("Largest Subarray with Zero Sum: "+largest_subarray_with_0_sum(new int[]{15, -2, 2, -8, 1, 7, 10, 23}));
        System.out.println("Count of Subarrays with Sum equal to K: "+count_subarray_sum_equal_K(new int[]{10, 2, -2, -20, 10}, -10));
    }



    static class HashMap_self<K, V>{ // generics
        private class Node {
            K key;
            V value;

            public Node(K key, V value){
                this.key = key;
                this.value = value;
            }
        }

        private int size_N, size_n; // n -> node, N -> bucket
        private LinkedList<Node> buckets[];  //Array of likedlist

        @SuppressWarnings("unchecked")
        public HashMap_self() {
            this.size_N = 4; // intially size of buckets is 4
            this.buckets = new LinkedList[4]; // initially 4 buckets N = 4
            for(int i=0; i<4; i++){
                buckets[i] = new LinkedList<>(); // Initialize each bucket with empty LL
            }
        }

        private int hashFunction(K key){
            int hashcode = key.hashCode(); // internally implemented, gives a hash value
            return Math.abs(hashcode) % size_N; // returns index
        }

        private int searchInLinkedList(int idx, K key){
            LinkedList<Node> ll = buckets[idx];

            for(int i=0; i<ll.size(); i++){
                Node node = ll.get(i);
                if(node.key == key){
                    return i;
                }
            }

            return -1;
        }

        @SuppressWarnings("unchecked")
        private void reHashing(){
            LinkedList<Node> oldBucket[] = buckets;
            buckets = new LinkedList[size_N*2];
            size_N = size_N*2;

            for (int i = 0; i < buckets.length; i++) {
                buckets[i] = new LinkedList<>();
            }

            for (int i = 0; i < oldBucket.length; i++) {
                LinkedList<Node> ll = oldBucket[i];
                for (int j = 0; j < ll.size(); j++) {
                    Node node = ll.remove();
                    put(node.key, node.value);
                }
            }

        }

        public void put(K key, V value){
            int buckIdx = hashFunction(key);
            int dataIdx = searchInLinkedList(buckIdx, key);

            if(dataIdx != -1){
                Node node = buckets[buckIdx].get(dataIdx);
                node.value = value;
            }else{
                buckets[buckIdx].add(new Node(key, value));
                size_n++;
            }

            double lambda = (double)size_n/size_N;
            if(lambda > 2.0){ // threshold
                reHashing();
            }
        }

        public boolean containsKey(K key){
            int buckIdx = hashFunction(key);
            int dataIdx = searchInLinkedList(buckIdx, key);

            if(dataIdx != -1){
                return true;
            }else{
                return false;
            }
        }

        public V remove(K key){
            int buckIdx = hashFunction(key);
            int dataIdx = searchInLinkedList(buckIdx, key);

            if(dataIdx != -1){
                Node node = buckets[buckIdx].remove(dataIdx);
                size_n--;
                return node.value;
            }

            return null;
        }

        public V get(K key){
            int buckIdx = hashFunction(key);
            int dataIdx = searchInLinkedList(buckIdx, key);

            if(dataIdx != -1){
                Node node = buckets[buckIdx].get(dataIdx);
                return node.value;
            }
            
            return null;
        }

        public ArrayList<K> keySet(){
            ArrayList<K> keys = new ArrayList<>();
            for(int i=0; i<buckets.length; i++){
                LinkedList<Node> ll = buckets[i];
                for (Node node : ll) {
                    keys.add(node.key);
                }
            }

            return keys;
        }

        public boolean isEmpty(){
            return size_n == 0;
        }

        @Override
        public String toString() {
            String ans = "{";
            for(int i=0; i<size_N; i++){
                LinkedList<Node> ll = buckets[i];
                for (Node node : ll) {
                    ans+= node.key + ": " +node.value + ", ";
                }
            }
            return ans.substring(0, ans.length()-2)+"}";
        }
    }


    public static void majority_elements(int nums[]){
        HashMap<Integer, Integer> hm = new HashMap<>(); // number, frequency
        for(int i : nums){
            int frequency = hm.containsKey(i) ? (hm.get(i) + 1) : 1;
            hm.put(i, frequency);
            //hm.put(i, hm.getOrDefault(i, 0)+1);
        }

        for (int i : hm.keySet()) {
            if(hm.get(i) > nums.length/3){
                System.out.print(i + " ");
            }
        }
        System.out.println();
    }

    public static boolean valid_anagram(String s1, String s2){
        
        if(s1.length() != s2.length()){
            return false;
        }

        HashMap<Character, Integer> hm = new HashMap<>();
        for (int i = 0; i < s1.length(); i++) {
            hm.put(s1.charAt(i), hm.getOrDefault(s1.charAt(i), 0)+1);
        }

        for (int i = 0; i < s2.length(); i++) {
            if(hm.containsKey(s2.charAt(i))){
                hm.put(s2.charAt(i), hm.get(s2.charAt(i))-1); // reduce frequency
                if(hm.get(s2.charAt(i)) == 0){ // if freq is 0, then remove
                    hm.remove(s2.charAt(i));
                }
            }else{
                return false;
            }
        }

        return hm.isEmpty();
    }

    public static int count_distinct_elements(int nums[]){

        HashSet<Integer> set = new HashSet<>();
        for(int i : nums){
            set.add(i);
        }

        return set.size();
    }

    public static int union(int array1[], int array2[]){

        HashSet<Integer> set = new HashSet<>();

        for(int i : array1){
            set.add(i);
        }

        for(int i : array2){
            set.add(i);
        }

        return set.size();
    }
    
    public static int intersection(int array1[], int array2[]){

        HashSet<Integer> set1 = new HashSet<>();
        HashSet<Integer> set2 = new HashSet<>();

        for(int i : array1){
            set1.add(i);
        }

        for(int i : array2){
            set2.add(i);
        }

        int number_of_common_elements = 0;

        for(int i : set2){
            if(set1.contains(i)){
                number_of_common_elements++;
            }
        }

        return number_of_common_elements;
    }

    public static String find_iternary_from_ticket(HashMap<String, String> tickets){
        String start = getStartStation(tickets);
        String ans = start + "->";
        
        while(tickets.containsKey(start)){
            start = tickets.get(start);
            ans += start + "->";
        }
        return ans.substring(0, ans.length()-2);
    }

    public static String getStartStation(HashMap<String, String> tickets) {
        HashMap<String, String> reverseTicket = new HashMap<>();
        for(String i : tickets.keySet()){
            reverseTicket.put(tickets.get(i), i);
        }
        for(String i : tickets.keySet()){
            if(!reverseTicket.containsKey(i)){
                return i; // return ticket for which it is not in destination
            }
        }

        return null;
    }

    public static int largest_subarray_with_0_sum(int arr[]){
        HashMap<Integer, Integer> map = new HashMap<>(); // sum, index => key, value
        int sum = 0;
        int length = 0;
        for(int i=0; i<arr.length; i++){
            sum += arr[i];
            if(map.containsKey(sum)){
                length = Math.max(length, i - map.get(sum)); // length
            }else{
                map.put(sum, i);
            }
        }

        return length;
    }

    public static int count_subarray_sum_equal_K(int arr[], int k){
        HashMap<Integer, Integer> map = new HashMap<>(); // sum, count => key, value
        int sum = 0;
        int count = 0;
        map.put(0, 1);
        for(int j=0; j<arr.length; j++){
            sum += arr[j];
            if(map.containsKey(sum - k)){
                count += map.get(sum - k);
            }
            map.put(sum, map.getOrDefault(sum, 0)+1);
        }

        return count;   
    }

}
