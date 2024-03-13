import java.util.Arrays;
import java.util.ArrayList;

public class Dynamic_Programming_2 {
    /* 
    Topics Covered:
     * Knapsack (recursion)
     * D.P. Knapsack (Memoization)
     * D.P. Knapsack (tabulation)
     * D.P. Unbounded Knapsack (Tabulation)
     * Target Sum Subset (Recursion)
     * D.P. Target Sum Subset (Tabulation)
     * D.P. Target Sum Subset (Memoization)
    */
    
    public static void main(String[] args) {
        int val[] = {15,14,10,45,30};
        int wt[] = {2,5,1,3,4};
        int W = 7;
        System.out.println("Knapsack (Recursion): "+knapsack(val, wt, W, val.length));

        int dp[][] = new int[val.length+1][W+1];
    
        // initialize DP array with -1
        for(int[] i : dp){
            Arrays.fill(i, -1);
        }
        System.out.println("D.P. Knapsack (Memoization): "+dp_knapsack_memoization(val, wt, W, val.length, dp));
        System.out.println("D.P. Knapsack (Tabulation): "+dp_knapsack_tabulation(val, wt, W, val.length));
        System.out.println("D.P. Unbounded Knapsack (Tabulation): "+dp_unbounded_knapsack_tabulation(val, wt, W, val.length));

        System.out.println("Target Sum Subset (Recursion): ");
        target_sum_subset(new int[]{4,2,7,1,3}, 10, new ArrayList<Integer>(), 0);
        System.out.println("D.P. Target Sum Subset (Tabulation): "+dp_target_sum_subset_tabulation(new int[]{4,2,7,1,3}, 10));

        int nums[] = {4,2,7,1,3}; int sum = 10;
        dp = new int[nums.length+1][sum+1];
        for(int[] i : dp){
            Arrays.fill(i, -1);
        }
        System.out.println("D.P. Target Sum Subset (Memoization): "+dp_target_sum_subset_memoization(nums, 10, nums.length, dp));
        print_dp(dp);
        
    }

    public static int knapsack(int val[], int wt[], int W, int n){
        if(W==0 || n==0){
            return 0;
        }

        if(wt[n-1] <= W){ // valid
            //include
            int ans1 = val[n-1] + knapsack(val, wt, W-wt[n-1], n-1);

            //exclude
            int ans2 = knapsack(val, wt, W, n-1);

            return Math.max(ans1, ans2);
        }else { // not valid
            //exclude
            return knapsack(val, wt, W, n-1);
        }
    }

    public static int dp_knapsack_memoization(int val[], int wt[], int W, int n, int dp[][]){
        if(W==0 || n==0){
            return 0;
        }

        if(dp[n][W] != -1){ // if already calculated
            return dp[n][W];
        }

        if(wt[n-1] <= W){ // valid
            //include
            int ans1 = val[n-1] + dp_knapsack_memoization(val, wt, W-wt[n-1], n-1, dp);

            //exclude
            int ans2 = dp_knapsack_memoization(val, wt, W, n-1, dp);

            return dp[n][W] = Math.max(ans1, ans2); // store and return
        }else { // not valid

            //exclude
            return dp[n][W] = dp_knapsack_memoization(val, wt, W, n-1, dp); // store and return
        }
    }

    public static int dp_knapsack_tabulation(int val[], int wt[], int W, int n){
        int dp[][] = new int[n+1][W+1];

        // dp[i, j] means val from index i, j
        
        // base case is 0
        for(int i=0; i<n; i++){
            dp[i][0] = 0;
        }
        for(int i=0; i<W; i++){
            dp[0][i] = 0;
        }

        for(int i=1; i<=n; i++){
            for(int j=1; j<=W; j++){
                // W is weight, n is item number
                int v = val[i-1]; // adjusted to i=1  (actually it is val of ith item only)
                int w = wt[i-1]; // adjusted to i=1  (actually it is wt of ith item only)
                if(w <= j){ // valid
                    //include
                    int includeProfit = v + dp[i-1][j-w]; // current profit + profit from remaining weight
                    int excludeProfit = dp[i-1][j]; // profit from last remaining weight
                    dp[i][j] = Math.max(includeProfit, excludeProfit);
                }else{ // exclude
                    int excludeProfit = dp[i-1][j]; // profit from last remaining weight
                    dp[i][j] = excludeProfit;
                }
            }
        }

        return dp[n][W];  
    }

    public static int dp_unbounded_knapsack_tabulation(int val[], int wt[], int W, int n){
        int dp[][] = new int[n+1][W+1];

        // dp[i, j] means max profit from index i (no. items), j (capacity of knapsack)
        
        // base case is when capacity or number of items is 0
        for(int i=0; i<n; i++){
            dp[i][0] = 0;
        }
        for(int i=0; i<W; i++){
            dp[0][i] = 0;
        }

        for(int i=1; i<=n; i++){
            for(int j=1; j<=W; j++){
                // W is weight, n is item number
                int v = val[i-1]; // adjusted to i=1  (actually it is val of ith item only)
                int w = wt[i-1]; // adjusted to i=1  (actually it is wt of ith item only)
                if(w <= j){ // valid
                    //include
                    int includeProfit = v + dp[i][j-w]; // current profit + profit from remaining weight includeing itself
                    int excludeProfit = dp[i-1][j]; // profit from last remaining weight
                    dp[i][j] = Math.max(includeProfit, excludeProfit);
                }else{ // exclude
                    int excludeProfit = dp[i-1][j]; // profit from last remaining weight
                    dp[i][j] = excludeProfit;
                }
            }
        }

        return dp[n][W];  
    }



    public static void target_sum_subset(int nums[], int sum, ArrayList<Integer> list, int index){

        if(index >= nums.length){
            // reached end (if sum == 0) then, print
            if(sum == 0){
                System.out.println("\t" + list); 
            }
            return;
        }        

        // System.out.println(list + ": sum -> "+sum);

        //include
        list.add(nums[index]);
        target_sum_subset(nums, sum-nums[index], list, index+1);
        list.remove(list.size()-1);
        
        //exclude
        target_sum_subset(nums, sum, list, index+1);
    }

    public static int dp_target_sum_subset_memoization(int val[], int sum, int n, int dp[][]){
        // 2 variables changing sum, n
        if(sum == 0){
            return dp[n][sum] = 1;
        }

        if(n == 1){
            if(val[0] == sum){
                return dp[n][sum] = 1;
            }else{
                return dp[n][sum] = 0;
            }
        }

        if(dp[n][sum] != -1){
            return dp[n][sum];
        }

        int not_take = dp_target_sum_subset_memoization(val, sum, n-1, dp);
        int take = 0;

        if(val[n-1] <= sum){ // valid, then include
            take = dp_target_sum_subset_memoization(val, sum-val[n-1], n-1, dp);
        }
            
        return dp[n][sum] = take | not_take; // give the value true if it is true
    }


    public static boolean dp_target_sum_subset_tabulation(int val[], int sum){
        int n = val.length;
        boolean dp[][] = new boolean[n+1][sum+1];
        
        //i = items & j = target sum
        //initialization
        for(int i=0; i<=n; i++){
            dp[i][0] = true;
        }
        

        for(int i=1; i<=n; i++){
            for(int j=1; j<=sum; j++){
                int v = val[i-1];
                //include
                if(v <= j && dp[i-1][j-v] == true){ // valid condition -> include
                    dp[i][j] = true;
                }
                //exclude
                else if(dp[i-1][j] == true){
                    dp[i][j] = true;
                }

                //otherwise false only
            }
        }

        print_dp(dp);
        return dp[n][sum];  
    }


    public static void print_dp(boolean dp[][]){
        System.out.println();
        System.out.print("   ");
        for(int i=0; i<dp[0].length; i++){
            System.out.print(i + "     ");
        }
        System.out.println();
        for(int i=0; i<dp.length; i++){
            System.out.print(i + "  ");
            for(int j=0; j<dp[0].length; j++){
                if(dp[i][j] == true)
                    System.out.print(dp[i][j] + "  ");
                else
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void print_dp(int dp[][]){
        System.out.print("    ");
        for(int i=0; i<dp[0].length; i++){
            System.out.print(i + "  ");
        }
        System.out.println();
        for(int i=0; i<dp.length; i++){
            System.out.print(i + "  ");
            for(int j=0; j<dp[0].length; j++){
                if(dp[i][j] != -1)
                    System.out.print(" " + dp[i][j] + " ");
                else
                System.out.print("  " + " "); //dp[i][j]
            }
            System.out.println();
        }
    }
}
