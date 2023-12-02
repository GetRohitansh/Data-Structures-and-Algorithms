import java.util.ArrayList;
import java.util.Arrays;

public class ArrayListLecture {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        
        //Operations in ArrayList
        //Add
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(1, 5);
        System.out.println(list);

        //Get
        int element = list.get(1);
        System.out.println(element);

        //Delete
        list.remove(1);
        System.out.println(list);

        //Set
        list.set(2, 100);
        System.out.println(list);

        //Contains
        System.out.println(list.contains(1));
        System.out.println(list.contains(13));

        //size
        System.out.println(list.size());

        System.out.println("Print reverse list: ");
        print_reverse(list);
        System.out.println("Find maximum in list: " + find_maximum(list));
        System.out.println(swap2number(list, 2, 1));

        //multidirectional ArrayList
        ArrayList<ArrayList<Integer>> mainList = new ArrayList<>();
        ArrayList<Integer> innerList = new ArrayList<>();
        innerList.add(3); innerList.add(5);
        mainList.add(innerList);

        System.out.println("Container with most water (brute force): "+container_with_most_water_brute_force(new ArrayList<Integer>(Arrays.asList(1,8,6,2,5,4,8,3,7))));

        System.out.println("Container with most water (2 pointer approach): "+container_with_most_water_brute_force(new ArrayList<Integer>(Arrays.asList(1,8,6,2,5,4,8,3,7))));

        System.out.println("Pair sum 1 (brute force): "+pair_sum_1_brute_force(new ArrayList<Integer>(Arrays.asList(1,2,3,4,5,6)), 5));
        System.out.println("Pair sum 1 (2 pointer approach force): "+pair_sum_1_2_pointer_approach(new ArrayList<Integer>(Arrays.asList(1,2,3,4,5,6)), 15));
        System.out.println("Pair sum 2 (2 pointer approach force): "+pair_sum_2_2_pointer_approach(new ArrayList<Integer>(Arrays.asList(11,15,6,8,9,10)), 16));
    }

    public static void print_reverse(ArrayList<Integer> list){
        for(int i=list.size()-1; i>=0; i--){
            System.out.print(list.get(i) + " ");
        }
        System.out.println();
    }

    public static int find_maximum(ArrayList<Integer> list){
        int max = Integer.MIN_VALUE;
        for(int i=1; i<list.size(); i++){
            max = Math.max(max, list.get(i));
        }
        return max;
    }

    public static ArrayList<Integer> swap2number(ArrayList<Integer> list, int index1, int index2){
        int temp = list.get(index1);
        list.set(index1, list.get(index2));
        list.set(index2, temp);
 
        return list;
    }

    public static int container_with_most_water_brute_force(ArrayList<Integer> height){
        int max = Integer.MIN_VALUE;
        for(int i=0; i<height.size(); i++){
            for(int j=i+1; j<height.size(); j++){
                max = Math.max(max, Math.min(height.get(i), height.get(j))*(j-i));
            }
        }
        return max;
    }

    public static int container_with_most_water_2_pointer(ArrayList<Integer> height){
        int left_ptr = 0;
        int right_ptr = height.size()-1;

        int max = Integer.MIN_VALUE;
        while(left_ptr < right_ptr){
            int current_water = Math.min(height.get(left_ptr), height.get(right_ptr)) * (right_ptr - left_ptr);

            max  = Math.max(max, current_water);

            if(height.get(left_ptr) < height.get(right_ptr)){
                left_ptr++;
            }else{
                right_ptr--;
            }
        }
        return max;
    }

    // list is sorted
    public static Pair pair_sum_1_brute_force(ArrayList<Integer> list, int target){
        for (int i = 0; i < list.size(); i++) {
            for (int j = i+1; j < list.size(); j++) {
                if((list.get(i)+list.get(j)) == target) {
                    return new Pair(i, j);
                }
            }
        }

        return new Pair(-1, -1);
    }

    // list is sorted
    public static Pair pair_sum_1_2_pointer_approach(ArrayList<Integer> list, int target){
        int left_ptr = 0;
        int right_ptr = list.size()-1;

        while(left_ptr < right_ptr){
            int sum = list.get(left_ptr)+list.get(right_ptr);
            if (sum < target) {
                left_ptr++;
            } else if(sum > target){
                right_ptr--;
            }else{
                return new Pair(left_ptr, right_ptr);
            }
        }

        return new Pair(-1, -1);
    }

    public static Pair pair_sum_2_2_pointer_approach(ArrayList<Integer> list, int target){

        //find pivot (breaking point)
        int breaking_point = -1;
        for(int i=0; i<list.size()-1; i++){
            if(list.get(i)>list.get(i+1)){
                breaking_point = i;
            }
        }

        int left_ptr = breaking_point+1; //smaller
        int right_ptr = breaking_point; //larger

        while(left_ptr != right_ptr){
            int sum = list.get(left_ptr)+list.get(right_ptr);
            if (sum < target) {
                left_ptr = (left_ptr+1)%(list.size());
            } else if(sum > target){
                right_ptr = (list.size()+right_ptr-1)%(list.size());
            }else{
                return new Pair(left_ptr, right_ptr);
            }
        }

        return new Pair(-1, -1);
    }
}

class Pair{
    int num1;
    int num2;

    Pair(int num1, int num2){
        this.num1 = num1;
        this.num2 = num2;
    }

    
    @Override
    public String toString() {
        return "("+this.num1+", "+this.num2+")";
    }
}
