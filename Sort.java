import java.util.Arrays;
import java.util.Collections;
public class Sort {

    /* 
    Topics Covered: 
     * Bubble Sort O(n²)
     * Selection Sort O(n²)
     * Insertion Sort O(n²)
     * Inbuilt Sort O(nlogn)
     * Counting Sort O(n+range)
    */

    public static void main(String[] args) {
        System.out.println("Bubble Sort: "+ Arrays.toString(bubbleSort(new int[]{5,4,1,3,2})));
        System.out.println("Selection Sort: "+ Arrays.toString(selectionSort(new int[]{5,4,1,3,2})));
        System.out.println("Insertion Sort: "+ Arrays.toString(insertionSort(new int[]{5,4,1,3,2})));

        int nums[] = new int[]{5,4,1,3,2};
        Arrays.sort(nums);
        System.out.println("Inbuild Sort: " + Arrays.toString(nums));
        Integer nums2[] = {5,4,1,3,2};
        Arrays.sort(nums2, Collections.reverseOrder());
        System.out.println("Inbuild Sort (reverse) {N.A. for primitive datatype}: " + Arrays.toString(nums2));
        System.out.println("Counting Sort: "+ Arrays.toString(countingSort(new int[]{1,4,1,3,2,4,3,7})));
        
    }

    public static int[] bubbleSort(int nums[]){
        for(int turn=0; turn<nums.length; turn++){
            for(int j=0; j<nums.length-turn-1; j++){
                if(nums[j]>nums[j+1]){
                    int temp = nums[j];
                    nums[j] = nums[j+1];
                    nums[j+1] = temp;
                }
            }
        }
        return nums;
    }

    public static int[] selectionSort(int nums[]){
        for(int i=0; i<nums.length; i++){
            int smallest_index = i;
            for(int j=i+1; j<nums.length; j++){
                if(nums[smallest_index]>nums[j]){
                    smallest_index = j;
                }
            }

            //swap with right position
            int temp = nums[i];
            nums[i] = nums[smallest_index];
            nums[smallest_index] = temp;
        }

        return nums;
    }

    public static int[] insertionSort(int nums[]){
        for(int i=1; i<nums.length; i++){
            int current = nums[i];
            int prev = i-1;

            //Find right position
            while(prev>=0 && nums[prev]>current){
                nums[prev+1] = nums[prev]; //push numbers
                prev--;
            }
            //prev == -1, then loop breaks
            nums[prev+1] = current;
        }
        return nums;
    }

    public static int[] countingSort(int nums[]){

        // Find Maximum value
        int largest = Integer.MIN_VALUE;
        for(int i=0; i<nums.length; i++){
            largest = Math.max(largest, nums[i]);
        }

        // Fill count with frequency of each value
        int count[] = new int[largest+1];
        for(int i=0; i<nums.length; i++){
            int index = nums[i];
            count[index]++;
        }

        // Update original array
        int counter = 0;
        for(int i=0; i<largest+1; i++){
            for(int j=0; j<count[i]; j++){
                nums[counter++] = i;
            }
        }
        return nums;
    }

}
