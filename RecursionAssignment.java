public class RecursionAssignment {
    /* 
    Topics Covered: 
     * All occurence of Key in Array
     * Print year in words 
     * Length of the string
     * Subsequence that start and end with same letter
     * Tower of Hanoi
    */
    public static void main(String[] args) {
        System.out.println("All occurrence: ");
        all_occurrence(new int[]{3, 2, 4, 5, 6, 2, 7, 2, 2}, 2, 0);
        String number_arr[] = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
        System.out.println("\nPrint Year: "+print_year("4015", 0, new StringBuilder(""), number_arr));
        System.out.println("Length of String is: "+length_of_string("Herllo", 0));
        String str = "abcab";
        System.out.println("count of subsequence that start and end with same letter: "+count_subsequency_same_start_end(str, 0, str.length()-1));
        tower_of_hanoi("A", "B", "C", 10);
    }

    //Question1 : For a given integer array of size N, You have to find all the occurrences(indices) of a given element(Key) and print them.  
    // Use a recursive function to solve this problem.
    public static void all_occurrence(int arr[], int key, int i){
        if(i == arr.length){
            return;
        }

        if(arr[i] == key) {
            System.out.print(i+" ");
        }
        all_occurrence(arr, key, i+1);
    }

    // Question 2 :You are given a number (eg -  2019), convert it into a String of english like “two zero one nine”.  Use a recursive  
    // function to solve this problem.
    // NOTE - The digits of the number will only be in the range 0-9 and the last digit of a number can’t be 0.
    public static String print_year(String year, int i, StringBuilder ans, String num_array[]){
        if(i == year.length()){
            return ans.toString();
        }

        return print_year(year, i+1, ans.append(num_array[year.charAt(i)-'0'] + " "), num_array);
    }

    // Question 3 : Write a program to find Length of a String using Recursion
    public static int length_of_string(String str, int i){
        if(str.isEmpty()){
            return i;
        }

        return length_of_string(str.substring(1), i+1);
    }

    // Question 4 : We are given a string S, we need to find the count of all contiguous substrings starting and ending with the same 
    // character.
    public static int count_subsequency_same_start_end(String str, int start, int end){
        if(end == start){
            return 1;
        }
        if(start > end){
            return 0;
        }

        int result = count_subsequency_same_start_end(str, start+1, end) + count_subsequency_same_start_end(str, start, end-1) - count_subsequency_same_start_end(str, start+1, end-1);

        if(str.charAt(start) == str.charAt(end)){
            result++;
        }

        return result;
    }

    // Question 5 : TOWER OF HANOI
    // You have 3 towers and N disks of different sizes which can slide on to any tower.
    // The puzzle starts with disks sorted in ascending order of size from top to bottom (i.e., each disks its on top of an even larger one)
    public static void tower_of_hanoi(String src, String helper, String dest, int n){
        if(n==1){
            //move last disk from source to destination
            System.out.printf("Transfer disk %d from %s to %s\n", n, src, dest);
            return;
        }

        // top n-1 from A to B using C        
        tower_of_hanoi(src, dest, helper, n-1);
        System.out.printf("Transfer disk %d from %s to %s\n", n, src, dest);
        tower_of_hanoi(helper, src, dest, n-1);
    }
}
