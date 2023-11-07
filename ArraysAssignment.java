import java.util.List;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Collections;

public class ArraysAssignment {
    public static void main(String[] args) {
        System.out.println("Question 1 (Appears Atleast Twice): " + appearsAtleastTwice(new int[] { 1, 3, 4, 2 }));
        System.out.println("Question 2 (Find Index of Target in Mountain Array): " + findIndex(new int[] {4,  5, 6, 7, 0, 1, 2}, 3));
        System.out.println("Question 3 (Buy And Sell Stocks): "+ buyAndSellStocks(new int[]{7,1,5,3,6,4}));
        System.out.println("Question 4 (Trapped Water (Auxiliary Array)): "+ trapped_water(new int[]{0, 1, 0,  2, 1, 0, 1, 3, 2, 1, 2, 1}));
        System.out.println("Question 5 (Triplets in Array): " + tripletSum(new int[] {-1, 0,  1, 2, -1, -4}));        
    }

    // Question 1:
    // Given an integer array nums, return true if any value appears at least twice
    // in the array, and return false if every element is distinct.
    public static boolean appearsAtleastTwice(int array[]) {
        for (int i = 0; i < array.length; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] == array[i])
                    return true;
            }
        }
        return false;
    }

    // Question 2:
    // There is an integer array nums sorted in ascending order (with distinct
    // values).Prior to being passed to your function, nums is possibly rotated at
    // an unknown pivot index k (1 <= k < nums.length) such that the resulting array
    // is [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]
    // (0-indexed). For example, [0,1,2,4,5,6,7] might be rotated at pivot index 3
    // and become [4,5,6,7,0,1,2].
    // Given the array nums after the possible rotation and an integer target,
    // return the index of target if it is in nums, or -1 if it is not in nums. You
    // must write an algorithm with O(log n) runtime complexity.

    public static int findIndex(int nums[], int target) {
        int peakIndex = peakIndex_Mountain(nums);

        // First Half
        int start = 0, end = peakIndex;
        while (start <= end) {
            int mid = start + (end - start) / 2;

            if (target == nums[mid]) {
                return mid;
            } else if (target < nums[mid]) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }

        // Second Half
        start = peakIndex + 1;
        end = nums.length - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;

            if (target == nums[mid]) {
                return mid;
            } else if (target < nums[mid]) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }

        return -1;
    }

    public static int peakIndex_Mountain(int nums[]) {

        int start = 0, end = nums.length - 1;

        while (start <= end) {
            int mid = start + (end - start) / 2;

            if (mid == nums.length - 1)
                return nums.length - 1;

            if (nums[mid] < nums[mid + 1]) {
                start = mid + 1;
            } else {
                return mid;
            }
        }

        return -1;
    }

    // Question 3:
    // You are given an array prices where prices[i] is the price of a given stock
    // on the ith day. Return the maximum profit you can achieve from this
    // transaction. If you cannot achieve any profit, return 0

    public static int buyAndSellStocks(int prices[]){
        int buyPrice = Integer.MAX_VALUE;
        int maxProfit = 0;

        for(int i=0; i<prices.length; i++){
            if(buyPrice<prices[i]){
                int profit = prices[i] - buyPrice;
                maxProfit = Math.max(profit, maxProfit);
            }else{
                buyPrice = prices[i]; //take lower prices[i] as buyprice
            }
        }
        return maxProfit;
    }


    //Question 4:
    // Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it can trap after raining.

    public static int trapped_water(int height[]){
        int length = height.length;
        int leftmax[] = new int[length];
        int rightmax[] = new int[length];

        int max = 0;
        for(int i=0; i<length; i++){
            if(max<height[i]){
                max = height[i];
            }
            leftmax[i] = max;
        }

        max = 0;
        for(int i=length-1; i>=0; i--){
            if(max<height[i]){
                max = height[i];
            }
            rightmax[i] = max;
        }

        int water = 0;
        for(int i=0; i<length; i++){
            water += Math.min(leftmax[i], rightmax[i]) - height[i];
        }
        return water;
    }

    //Question 5:
    //Given an integer array nums, return all the triplets [nums[i], nums[j],  nums[k]] such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.Notice that the solution set must not contain duplicate triplets.

    public static List<List<Integer>> tripletSum(int nums[]){
        List<List<Integer>> result = new ArrayList<>();
        
        for(int i=0; i<nums.length; i++){
            for(int j=i+1; j<nums.length; j++){
                for(int k=j+1; k<nums.length; k++){  

                    if(nums[i]+nums[j]+nums[k]==0){
                        List<Integer> triplet = new ArrayList<>();
                        triplet.add(nums[i]);
                        triplet.add(nums[j]);
                        triplet.add(nums[k]);
                        Collections.sort(triplet);
                        result.add(triplet);
                    }                    
                }
            }
        }
        return new ArrayList<List<Integer>>(new LinkedHashSet<List<Integer>> (result));
    }
}
