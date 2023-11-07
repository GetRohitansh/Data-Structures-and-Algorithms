import java.util.Arrays;

public class DivideAndConquer {
    /* 
    Topics Covered: 
     * Merge Sort
     * Merge Sort (inplace)
     * Quick Sort (inplace)
     * Search in Rotated Sorted Array
    */
    public static void main(String[] args) {
        int array[] = {10,6,3,9,11,5,2,10,8,10};
        System.out.println("Merge Sort Array: "+Arrays.toString(mergeSort(array, 0, array.length-1)));
        System.out.println("Original Array: "+Arrays.toString(array));
        System.out.println("Merge Sort Array (inplace): called... mergeSort_inplace"); mergeSort_inplace(array, 0 , array.length-1);
        System.out.println("Original Changed to Array: "+Arrays.toString(array));

        array = new int[]{10,6,3,9,11,5,2,10,8,10};
        System.out.println("Quick Sort Array: called... QuickSort"); quickSort(array, 0 , array.length-1);
        System.out.println("Original Changed to Array: "+Arrays.toString(array));

        int rotated_array[] = {4,5,6,7,0,1,2};
        System.out.println("Search in rotated Array: "+search_in_rotated_array(rotated_array, 0, rotated_array.length-1, 0));
    }

    public static int[] mergeSort(int array[], int start, int end){
        if(start>=end){
            return new int[]{array[start]};
        }

        int mid = start + (end-start)/2;

        int left[] = mergeSort(array, start, mid);
        int right[] = mergeSort(array, mid+1, end);

        return mergeArray(left, right);
    }
    public static int[] mergeArray(int left[], int right[]){

        int leftPointer = 0;
        int rightPointer = 0;

        int tempArray[] = new int[left.length+right.length];

        int tempPointer = 0;

        while(leftPointer<left.length && rightPointer<right.length){
            if(left[leftPointer] < right[rightPointer]){
                tempArray[tempPointer] = left[leftPointer];
                leftPointer++;
            }else{
                tempArray[tempPointer] = right[rightPointer];
                rightPointer++;
            }
            tempPointer++;
        }

        while(leftPointer<left.length){
            tempArray[tempPointer++] = left[leftPointer++];
        }

        while(rightPointer<right.length){
            tempArray[tempPointer++] = right[rightPointer++];
        }

        return tempArray;
    }

    
    public static void mergeSort_inplace(int array[], int start, int end){
        if(start>=end){
            return;
        }

        int mid = start + (end-start)/2;

        mergeSort_inplace(array, start, mid); //left
        mergeSort_inplace(array, mid+1, end); //right

        mergeArray_inplace(array, start, mid, end);
    }
    public static void mergeArray_inplace(int array[], int start, int mid, int end){

        int leftPointer = start;
        int rightPointer = mid+1;

        int tempArray[] = new int[end-start+1];

        int tempPointer = 0;

        while(leftPointer<=mid && rightPointer<=end){
            if(array[leftPointer] < array[rightPointer]){
                tempArray[tempPointer] = array[leftPointer];
                leftPointer++;
            }else{
                tempArray[tempPointer] = array[rightPointer];
                rightPointer++;
            }
            tempPointer++;
        }

        while(leftPointer<=mid){
            tempArray[tempPointer++] = array[leftPointer++];
        }

        while(rightPointer<=end){
            tempArray[tempPointer++] = array[rightPointer++];
        }

        //copy from temp to original array
        for(int i=start, k=0; k<tempArray.length; i++, k++){
            array[i] = tempArray[k];
        }
    }

    public static void quickSort(int array[], int start, int end){
        if(start>=end){
            return;
        }

        int pivot = partition(array, start, end);
        quickSort(array, start, pivot-1);
        quickSort(array, pivot+1, end);
    }
    public static int partition(int array[],int start, int end){

        int index = start-1;

        for(int i=start; i<end; i++){
            if(array[i]<array[end]){
                index++;
                //swap
                int temp = array[i];
                array[i] = array[index];
                array[index] = temp;
            }
        }

        // put last element in right place
        index++;
        int temp = array[index];
        array[index] = array[end];
        array[end] = temp;

        return index;        
    }

    public static int search_in_rotated_array(int array[], int start, int end, int target){
        if(start > end){
            return -1;
        }

        int mid = start + (end-start)/2;

        if(target == array[mid]){
            return mid;
        }

        // start mid end
        if(array[start] <= array[mid]) { // A[mid] on L1
            if(array[start]<=target && target<=array[mid]){ // Left of L1
                return search_in_rotated_array(array, start, mid-1, target);

            }else{ // Right of L1
                return search_in_rotated_array(array, mid+1, end, target);

            }
        }else { // A[mid] on L2, array[mid]<=array[end]
            if(array[mid]<=target && target<=array[end]){ // Right of L2
                return search_in_rotated_array(array, mid+1, end, target);

            }else{ // Left of L2
                return search_in_rotated_array(array, start, mid-1, target);

            }
        }

    }
}

