import java.util.Arrays;

public class DivideandConquerAssignment {
     public static void main(String[] args) {
          int rotated_array[] = { 4, 5, 6, 7, 0, 1, 2 };
          System.out.println(
                    "Search in rotated array (iteration): " + search_in_rotated_array_iteration(rotated_array, 0));
          String array[] = { "mercury", "venus", "earth", "mars", "jupiter", "saturn", "uranus", "neptune" };
          System.out.println("Merge sort (string variant): original array --> " + Arrays.toString(array));
          System.out.println("original array --> " + Arrays.toString(array));
          mergeSort_strings(array, 0, array.length - 1);
          System.out.println("original array changed to: " + Arrays.toString(array));
          System.out.println("Majority Element number: " + majority_element(new int[] { 2, 2, 1, 1, 1, 2, 2 }));
          System.out.println("Majority Element number (divide & conquer): "
                    + majority_element_recursion(new int[] { 2, 2, 1, 1, 1, 2, 2 }, 0, 6));
          System.out.println("Inversion count (merge sort): " + inversion_count(new int[] { 1,5,3,2,4 }));
     }

     public static int search_in_rotated_array_iteration(int array[], int target) {
          // mid lies in either L1 or L2
          int start = 0;
          int end = array.length - 1;

          while (start <= end) {
               int mid = start + (end - start) / 2;

               if (array[mid] == target) {
                    return mid;
               }

               if (array[start] <= array[mid]) { // mid on L1
                    if (array[start] <= target && target <= array[mid]) { // target b/w start & mid
                         end = mid - 1;
                    } else { // target b/w mid & end
                         start = mid + 1;
                    }
               } else { // mid on L2
                    if (array[mid] <= target && target <= array[end]) { // target b/w mid & end
                         start = mid + 1;
                    } else { // target b/w start & mid
                         end = mid - 1;
                    }
               }
          }

          return -1;
     }

     // Question 1 : Apply Mergesort to sort an array of Strings. (Assume that all
     // the characters in all the Strings are in lowercase)
     public static void mergeSort_strings(String array[], int start, int end) {
          if (start >= end) {
               return;
          }

          int mid = start + (end - start) / 2;

          mergeSort_strings(array, start, mid);
          mergeSort_strings(array, mid + 1, end);

          merge_array(array, start, mid, end);
     }

     public static void merge_array(String array[], int start, int mid, int end) {
          String temp[] = new String[end - start + 1];

          int left_ptr = start;
          int right_ptr = mid + 1;
          int temp_ptr = 0;

          while (left_ptr <= mid && right_ptr <= end) {
               if (array[left_ptr].compareTo(array[right_ptr]) < 0) {
                    temp[temp_ptr++] = array[left_ptr++];
               } else {
                    temp[temp_ptr++] = array[right_ptr++];
               }
          }

          while (left_ptr <= mid) {
               temp[temp_ptr++] = array[left_ptr++];
          }

          while (right_ptr <= end) {
               temp[temp_ptr++] = array[right_ptr++];
          }

          for (int i = start, k = 0; i <= end; i++, k++) {
               array[i] = temp[k];
          }
     }

     // Question 2 : Given an array nums of size n, return the majority element.
     // (MEDIUM)
     // The majority element is the element that appears more than ⌊n/2⌋ times. You
     // may assume that the majority element always exists in the array.
     public static int majority_element(int nums[]) {
          int max = Integer.MIN_VALUE;
          for (int i = 0; i < nums.length; i++) {
               if (max < nums[i]) {
                    max = nums[i];
               }
          }

          int store_freq[] = new int[max + 1];
          int max_freq = 0;
          int max_freq_num = Integer.MIN_VALUE;

          for (int i = 0; i < nums.length; i++) {
               store_freq[nums[i]]++;
               if (max_freq < store_freq[nums[i]]) {
                    max_freq = store_freq[nums[i]];
                    max_freq_num = nums[i];
               }

               if (max_freq > nums.length / 2) {
                    return max_freq_num;
               }
          }

          return max_freq_num;
     }

     public static int majority_element_recursion(int nums[], int start, int end) {
          if (start >= end) {
               return nums[start];
          }

          int mid = start + (end - start) / 2;

          int left_major_element = majority_element_recursion(nums, start, mid);
          int right_major_element = majority_element_recursion(nums, mid + 1, end);

          if (left_major_element == right_major_element) {
               return left_major_element;
          } else {
               int left_major_element_count = count_elements(nums, left_major_element, start, end);
               int right_major_element_count = count_elements(nums, right_major_element, start, end);

               return left_major_element_count > right_major_element_count ? left_major_element : right_major_element;
          }
     }

     public static int count_elements(int nums[], int target, int start, int end) {
          int count = 0;
          for (int i = start; i <= end; i++) {
               if (nums[i] == target) {
                    count++;
               }
          }
          return count;
     }

     // Question 3 :Given an array of integers. Find theInversion Countin the array.
     // Inversion Count : For an array, inversion count indicate show far (or close)
     // the array is from being sorted. If the array is already sorted then the
     // inversion count is 0. If an array is sorted in the reverse order then the
     // inversion count is the maximum. Formally, two elements a[i] and a[j] form an
     // inversion if a[i] > a[j] and i < j.
     public static int inversion_count(int nums[]) {
          int nums_clone[] = nums.clone();
          return mergeSort_inversionCount(nums_clone, 0, nums_clone.length-1);
     }

     public static int mergeSort_inversionCount(int nums[], int start, int end) {
          if(start >= end){
               return 0;
          }

          int mid = start + (end - start) / 2;
          
          return mergeSort_inversionCount(nums, start, mid) + mergeSort_inversionCount(nums, mid + 1, end) + merge_inversionCount(nums, start, mid, end);
     }

     public static int merge_inversionCount(int nums[], int start, int mid, int end) {
          // calculate the change in pointers
          int left_ctr = start;
          int right_ctr = mid + 1;
          int temp_ctr = 0;

          int temp[] = new int[end - start + 1];

          int invCount = 0;

          while (left_ctr <= mid && right_ctr <= end) {
               if (nums[left_ctr] <= nums[right_ctr]) {
                    temp[temp_ctr++] = nums[left_ctr++];
               } else { //  right is smaller than left
                    temp[temp_ctr++] = nums[right_ctr++];
                    invCount += (mid+1 - left_ctr); // distance b/w right array and left counter
               }
          }

          while (left_ctr <= mid) {
               temp[temp_ctr++] = nums[left_ctr++];
          }

          while (right_ctr <= end) {
               temp[temp_ctr++] = nums[right_ctr++];
          }

          for (int i = start, k = 0; k < temp.length; i++, k++) {
               nums[i] = temp[k];
          }

          return invCount;
     }

}
