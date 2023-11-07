public class ArraysPart2 {

    /* 
    Topics Covered: 
     * Max Subarray Sum (Brute Force) O(n³)
     * Max Subarray Sum (Prefix Sum) O(n²)
     * Max Subarray Sum (Kadane's Algorithm) O(n)
     * Trapped Water (Auxiliary Array) O(n)
     * Buy And Sell Stocks O(n)
    */

    public static void main(String[] args) {
        int array[] = {-2, -3, -1, -2, -5, -3};
        System.out.println("Max Subarray Sum (Brute Force): "+ maxSubarraySum_BruteForce(array));
        System.out.println("Max Subarray Sum (Prefix Sum): "+ maxSubarraySum_PrefixSum(array));
        System.out.println("Max Subarray Sum (Kadane's Algorithm): "+ maxSubarraySum_Kadane(array));
        System.out.println("Trapped Water (Auxiliary Array): "+ trappedWater_HelperArray(new int[]{4,2,0,6,3,2,5}));
        System.out.println("Buy And Sell Stocks: "+ buyAndSellStocks(new int[]{7,1,5,3,6,4}));
    }

    public static int maxSubarraySum_BruteForce(int array[]){
        int maxSum=Integer.MIN_VALUE, currentSum=0;
        for(int i=0; i<array.length; i++){
            for(int j=i; j<array.length; j++){
                currentSum = 0;
                for(int k=i; k<=j; k++){
                    currentSum += array[k];
                }
                if(maxSum<currentSum){
                    maxSum = currentSum;
                }
            }
        }
        return maxSum;
    }

    public static int maxSubarraySum_PrefixSum(int array[]){
        int maxSum=Integer.MIN_VALUE, currentSum=0;

        int prefix[] = new int[array.length];
        prefix[0] = array[0];
        for(int i=1; i<array.length; i++){
            prefix[i] = prefix[i-1] + array[i];
        }

        for(int i=0; i<array.length; i++){            
            for(int j=i; j<array.length; j++){
                currentSum = (i==0 ? prefix[j] : prefix[j] - prefix[i-1]);
                if(maxSum<currentSum){
                    maxSum = currentSum;
                }
            }
        }
        return maxSum;
    }

    //If all negative then gives zero, to handle this we take another function
    public static int maxSubarraySum_Kadane(int array[]){

        int maxSum=Integer.MIN_VALUE, currentSum=0;
        int largest = Integer.MIN_VALUE;
        boolean allNegative = true;

        for(int i=0; i<array.length; i++){

            //To check if all are negative
            if(array[i]>0) allNegative = false;
            if(array[i]>largest) largest = array[i];

            currentSum = currentSum + array[i];
            if(currentSum<0){
                currentSum = 0;
            }
            
            maxSum = Math.max(currentSum, maxSum);
        }
        return (allNegative==true) ? largest : maxSum;
    }

    public static int trappedWater_HelperArray(int height[]){
        int length = height.length;
        int trapped_water = 0;

        int leftMax[] = new int[length];
        int rightMax[] = new int[length];

        int max = Integer.MIN_VALUE;
        for(int i=0; i<length; i++){
            if(max<height[i]) max = height[i];
            leftMax[i] = max;
        }

        max = Integer.MIN_VALUE;
        for(int i=length-1; i>=0; i--){
            if(max<height[i]) max = height[i];
            rightMax[i] = max;
        }

        for(int i=0; i<length; i++){
            trapped_water += Math.min(leftMax[i], rightMax[i]) - height[i];
        }

        return trapped_water;
    }

    public static int buyAndSellStocks(int prices[]){
        int buyPrice = Integer.MAX_VALUE;
        int maxProfit = 0;

        for(int i=0; i<prices.length; i++){
            if(buyPrice<prices[i]){
                int profit = prices[i]-buyPrice;
                maxProfit = Math.max(profit, maxProfit);
            }else{
                buyPrice = prices[i];
            }
        }
        return maxProfit;
    }
}
