import java.util.Arrays;

public class BitManipulationAssignment {
    public static void main(String[] args) {
        System.out.println("Swap number without using third variable: "+Arrays.toString(swap_without_extra(new int[]{10, 50})));
        System.out.println("Add 1 to an integer using Bit Manipulation (x+1 = -~x): "+add_1_bitManipulation(15));
        upperToLower();
    }
    // Question 2 : Swap two numbers without using any third variable.
    public static int[] swap_without_extra(int nums[]){

        nums[0] = nums[0] ^ nums[1];
        nums[1] = nums[0] ^ nums[1];
        nums[0] = nums[0] ^ nums[1];

        return nums;
    }

    // Question 3 : Add 1 to an integer using Bit Manipulation.
    public static int add_1_bitManipulation(int num){
        return -(~num);
    }

    // Question 4: Convert uppercase characters to lowercase using bits.
    public static void upperToLower(){
        for(char ch='A'; ch<='Z'; ch++){
            System.out.print((char)(ch | ' '));
        }
    }
}
