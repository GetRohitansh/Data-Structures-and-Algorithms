import java.util.Arrays;

public class Dynamic_Programming_6 {
    public static void main(String args[]){
        System.out.println("Matrix Chain Multiplication (Recursion): "+matrix_chain_multiplication(new int[]{1,2,3,4,3}, 1, 4));

        int n = 5;
        int dp[][] = new int[n][n];
        for(int i[] : dp){
            Arrays.fill(i, -1);
        }
        System.out.println("D.P. Matrix Chain Multiplication (Memoization): "+dp_matrix_chain_multiplication_memoization(new int[]{1,2,3,4,3}, 1, n-1, dp));

        System.out.println("D.P. Matrix Chain Multiplication (Tabulation): "+dp_matrix_chain_multiplication_tabulation(new int[]{1,2,3,4,3}));

        System.out.println("Minimum Partitioning: "+minimum_partitioning(new int[]{1,6,11,5}));

        System.out.println("Minimum Array Jump: "+minimum_array_jump(new int[]{2,3,1,1,4}));
    }

    public static int matrix_chain_multiplication(int arr[], int i, int j){
        if(i == j){
            return 0;
        }

        int min_cost = Integer.MAX_VALUE;
        for(int k=i; k<=j-1; k++){
            int cost1 = matrix_chain_multiplication(arr, i, k);
            int cost2 = matrix_chain_multiplication(arr, k+1, j);
            int selfcost = arr[i-1] * arr[k] * arr[j];

            int total_ans = cost1 + cost2 + selfcost;

            min_cost = Math.min(min_cost, total_ans);
        }

        return min_cost;
    }

    public static int dp_matrix_chain_multiplication_memoization(int arr[], int i, int j, int dp[][]){
        if(i == j){
            return dp[i][j] = 0;
        }

        if(dp[i][j] != -1){
            return dp[i][j];
        }

        int min_cost = Integer.MAX_VALUE;
        for(int k=i; k<=j-1; k++){
            int cost1 = dp_matrix_chain_multiplication_memoization(arr, i, k, dp);
            int cost2 = dp_matrix_chain_multiplication_memoization(arr, k+1, j, dp);
            int selfcost = arr[i-1] * arr[k] * arr[j];

            int total_ans = cost1 + cost2 + selfcost;

            min_cost = Math.min(min_cost, total_ans);
        }

        return dp[i][j] = min_cost;
    }

    public static int dp_matrix_chain_multiplication_tabulation(int arr[]){

        int n = arr.length;

        // create dp
        int dp[][] = new int[n][n];


        // initialize
        for(int i=0; i<n; i++){
            dp[i][i] = 0;
        }

        //fill bottom up
        for(int len=2; len <= n-1; len++){
            for(int i=1; i<=n-len; i++){
                int j = len + i-1; 
                int min_cost = Integer.MAX_VALUE;
                for(int k=i; k<j; k++){
                    int cost1 = dp[i][k];
                    int cost2 = dp[k+1][j];
                    int cost3 = arr[i-1]*arr[k]*arr[j];

                    int total_cost = cost1 + cost2 + cost3;

                    min_cost = Math.min(min_cost, total_cost);
                }
                dp[i][j] = min_cost;
            }
        }

        return dp[1][n-1];
    }

    public static int minimum_partitioning(int arr[]){
        int sum = Arrays.stream(arr).sum();
        // int set1 = knapsack_0_1(arr, sum/2, arr.length-1);
        int set1 = knapsack_0_1_tabulation(arr, sum/2);
        int set2 = sum - set1;
        return Math.abs(set1 - set2);
    }

    public static int knapsack_0_1(int arr[], int W, int i){
        if(i == 0 || W == 0){
            return 0;
        }

        if(arr[i] <= W){ // valid condition
            // take
            int take = arr[i] + knapsack_0_1(arr, W-arr[i], i-1);

            // leave
            int leave = knapsack_0_1(arr, W, i-1);

            return Math.max(take, leave);
        }else{ // invalid condition
            int leave = knapsack_0_1(arr, W, i-1);
            return leave;
        }
    }

    public static int knapsack_0_1_tabulation(int arr[], int W){
        int n = arr.length;
        int dp[][] = new int[n+1][W+1];
        

        // if W == 0, ans -> 0
        for(int i=0; i<=n; i++){
            dp[i][0] = 0;
        }

        // if n == 0, ans -> 0
        for(int j=0; j<=W; j++){
            dp[0][j] = 0;
        }

        for(int i=1; i<=n; i++){
            for(int j=1; j<=W; j++){
                int val = arr[i-1];
                if(val <= j){
                    int take = val + dp[i-1][j-val];
                    int leave = dp[i-1][j];

                    dp[i][j] = Math.max(take, leave);
                }else{
                    int leave = dp[i-1][j];
                    dp[i][j] = leave;
                }
            }
        }

        return dp[n][W];
    }

    public static int minimum_array_jump(int arr[]){
        int n = arr.length;
        int dp[] = new int[n];

        Arrays.fill(dp, -1);
        dp[n-1] = 0;

        for(int i=n-2; i>=0; i--){
            int steps = arr[i];
            int ans = Integer.MAX_VALUE;
            for(int j=i+1; j<=i+steps && j<n; j++){
                if(dp[j] != -1)
                    ans = Math.min(ans, dp[j]+1);
            }

            if(ans != Integer.MAX_VALUE)
                dp[i] = ans;
        }
        return dp[0];
    }
}
