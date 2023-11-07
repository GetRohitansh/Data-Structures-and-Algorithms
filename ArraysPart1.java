import java.util.Arrays;

public class ArraysPart1 {
    /* 
    Topics Covered: 
     * Linear Search
     * Binary Search
     * Reverse an Array
     * Pairs in Array
     * Subarrays in Array
    */

    
    public static void main(String[] args) {
        int array[] = {2, 4, 6, 8, 10, 12};
        System.out.println("Linear Search: "+linearSearch(array, 8));
        System.out.println("Binary Search: "+binarySearch(array, 12));
        System.out.println("Reverse Array: "+Arrays.toString(reverseArray(array)));
        System.out.println("Pairs in Array: ");
        pairsInArray(array);
        System.out.println("SubArray: ");
        subArray(array);
    }

    public static int linearSearch(int array[], int target){
        for(int index=0; index<array.length; index++){
            if(array[index]==target){
                return index;
            }
        }
        return -1;
    }
    public static int binarySearch(int array[], int target){
        int start=0, end=array.length-1;
        while(start<=end){
            int mid = start + (end-start)/2;
            if(target==array[mid]){
                return mid;
            }
            else if(target<array[mid]){
                end = mid-1;
            }else{
                start = mid+1;
            }
        }
        return -1;
    }

    public static int[] reverseArray(int nums[]){
        int array[] = Arrays.copyOf(nums, nums.length);
        int start=0, end=array.length-1;
        while(start<end){
            int temp = array[start];
            array[start] = array[end];
            array[end] = temp;
            start++;
            end--;
        }
        return array;
    }

    public static void pairsInArray(int array[]){
        for(int i=0; i<array.length; i++){
            for(int j=i+1; j<array.length; j++){
                System.out.printf("(%d, %d) ", array[i], array[j]);
            }
            System.out.println();
        }
    }

    public static void subArray(int array[]){
        for(int i=0; i<array.length; i++){ //Select start element
            for(int j=i+1; j<array.length; j++){ //Select end element
                System.out.print("(");
                for(int k=i; k<=j; k++){ //Print everything in between
                    System.out.print(array[k]+" ");
                }
                System.out.print(") ");
            }
            System.out.println();
        }
    }
}
