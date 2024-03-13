import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Dynamic_Programming_3 {
    /* 
    Topics Covered:
     * Coin Change Print All Solution (Recursion)
     * Coin Change (Recursion)
     * D.P. Coin Change (Memoization)
     * D.P. Coin Change (Tabulation)
     * D.P. Rod Cutting (Tabulation)
     * Longest Common Subsequence (Recursion)
     * D.P. Longest Common Subsequence (Memoization)
     * D.P. Longest Common Subsequence (Tabulation)
    */
    public static void main(String[] args) {
        int val[] = {2,5,3,6}; int sum = 10;
        int dp[][] = new int[val.length+1][sum+1];
    
        // initialize DP array with -1
        for(int[] i : dp){
            Arrays.fill(i, -1);
        }

        System.out.println("Coin Change Print All Solution (Recursion): ");
        coin_change(new int[]{1,2,3}, 2, 4, new ArrayList<Integer>(), new HashSet<>());
        System.out.println("Coin Change (Recursion): "+coin_change_count_ways(new int[]{2,5,3,6}, 3, 10));
        System.out.println("D.P. Coin Change (Memoization): "+dp_coin_change_memoization(new int[]{2,5,3,6}, 3, 10, dp));
        System.out.println("D.P. Coin Change (Tabulation): "+dp_coin_change_tabulation(new int[]{2,5,3,6}, 10));

        System.out.println("D.P. Rod Cutting (Tabulation): "+dp_rod_cutting_tabulation(new int[]{1,5,8,9,10,17,17,20}, new int[]{1,2,3,4,5,6,7,8}, 8));

        System.out.println("Longest Common Subsequence (Recursion): "+longest_common_subsequence("abcdge", "abedg"));



        String str1 = "abcdge";
        String str2 = "abedg";
        dp = new int[str1.length()+1][str2.length()+1];
        for(int[] i : dp){
            Arrays.fill(i, -1);
        }
        System.out.println("D.P. Longest Common Subsequence (Memoization): "+dp_longest_common_subsequence_memoization(str1, str2, dp));
        System.out.println("D.P. Longest Common Subsequence (Tabulation): "+dp_longest_common_subsequence_tabulation(str1, str2));
    }

    public static void coin_change(int val[], int index, int sum, ArrayList<Integer> list, Set<ArrayList<Integer>> set){

        if(sum == 0){
            if (!set.contains(list)) {
                System.out.println("\t"+list);
                set.add(new ArrayList<>(list)); // Add the combination to the set
            }
            return;
        }


        if(index < 0 || sum < 0){
            return;
        }
        
        // take current element again
        list.add(val[index]);
        coin_change(val, index, sum-val[index], list, set);
        list.remove(list.size()-1);

        //  take next element
        list.add(val[index]);
        coin_change(val, index-1, sum-val[index], list, set);
        list.remove(list.size()-1);

        // ignore next element
        coin_change(val, index-1, sum, list, set);
    }

    public static int dp_coin_change_tabulation(int coins[], int sum){
        int n = coins.length;
        int dp[][] = new int[n+1][sum+1];

        // if sum is zero then only 1 way  (initialize)
        for(int i=0; i<=n; i++){
            dp[i][0] = 1;
        }

        for(int i=1; i<=n; i++){
            for(int j=1; j<=sum; j++){
                int v = coins[i-1];
                if(v <= j){ // valid
                    //from including and excluding self
                    dp[i][j] = dp[i][j-v] + dp[i-1][j];
                }else{
                    //exclude
                    dp[i][j] = dp[i-1][j];
                }
            }
        }

        return dp[n][sum];
    }

    public static int coin_change_count_ways(int val[], int index, int sum){
        if(sum == 0){
            return 1;
        }

        if(index < 0 || sum < 0){
            return 0;
        }
        
        
        // not take element
        int not_take = coin_change_count_ways(val, index-1, sum);

        int take = 0;

        // take current element again
        if(val[index] <=sum)
            take = coin_change_count_ways(val, index, sum-val[index]);

        return take + not_take;
    }

    public static int dp_coin_change_memoization(int val[], int index, int sum, int dp[][]){
        if(sum == 0){
            return 1;
        }

        if(index < 0 || sum < 0){
            return 0;
        }
        
        if(dp[index][sum] != -1){
            return dp[index][sum];
        }


        // not take element
        int not_take = dp_coin_change_memoization(val, index-1, sum, dp);

        int take = 0;

        // take current element again
        if(val[index] <=sum)
            take = dp_coin_change_memoization(val, index, sum-val[index], dp);

        return take + not_take;
    }

    public static int dp_rod_cutting_tabulation(int price[], int length[], int totalLength){
        int n = price.length;
        int dp[][] = new int[n+1][totalLength+1];

        //(i, j) stores max profit from i pieces and total length j
        for(int i=0; i<=n; i++){
            //if j = 0, total length is zero, max profit = 0
            dp[i][0] = 0;
        }

        for(int i=0; i<=n; i++){
            //if i = 0, no. of pieces is zero, max profit = 0
            dp[i][0] = 0;
        }

        for(int i=1; i<=n; i++){
            for(int j=1; j<=totalLength; j++){
                int p = price[i-1];
                int l = length[i-1];
                // Max from include current, and also exclude 
                if(l <= j){
                    dp[i][j] = Math.max(p + dp[i][j-l], dp[i-1][j]);
                }else{
                    dp[i][j] = dp[i-1][j];
                }
                
            }
        }
        return dp[n][totalLength];
    }

    public static int longest_common_subsequence(String str1, String str2){
        if(str1.length() == 0 || str2.length() == 0){
            return 0;
        }

        // case 1: if last element of both are same
        if(str1.charAt(str1.length()-1) == str2.charAt(str2.length()-1)){
            str1 = str1.substring(0, str1.length()-1);
            str2 = str2.substring(0, str2.length()-1);

            // n-1, m-1
            return 1 + longest_common_subsequence(str1, str2);
        }

        // case 2: if last element of both are different
        else{
            // n-1, m
            int ans1 = longest_common_subsequence(str1.substring(0, str1.length()-1), str2);

            // n, m-1
            int ans2 = longest_common_subsequence(str1, str2.substring(0, str2.length()-1));

            return Math.max(ans1, ans2);
        }
    }

    public static int dp_longest_common_subsequence_memoization(String str1, String str2, int dp[][]){
        if(str1.length() == 0 || str2.length() == 0){
            return dp[str1.length()][str2.length()] = 0;
        }

        if(dp[str1.length()][str2.length()] != -1){
            return dp[str1.length()][str2.length()];
        }

        // case 1: if last element of both are same
        if(str1.charAt(str1.length()-1) == str2.charAt(str2.length()-1)){
            str1 = str1.substring(0, str1.length()-1);
            str2 = str2.substring(0, str2.length()-1);

            // n-1, m-1
            return dp[str1.length()][str2.length()] = 1 + longest_common_subsequence(str1, str2);
        }

        // case 2: if last element of both are different
        else{
            // n-1, m
            int ans1 = longest_common_subsequence(str1.substring(0, str1.length()-1), str2);

            // n, m-1
            int ans2 = longest_common_subsequence(str1, str2.substring(0, str2.length()-1));

            return dp[str1.length()][str2.length()] = Math.max(ans1, ans2);
        }
    }

    public static int dp_longest_common_subsequence_tabulation(String str1, String str2){

        // 1. create table
        int dp[][] = new int[str1.length()+1][str2.length()+1];

        // 2. meaning of (i, j)  is that LCS of string of size i and j

        // initialize, if(str.length() == 0 for both) then  LCS = 0
        // already 0 in java

        
        // 3. filling from bottom to up
        for(int i=1; i<=str1.length(); i++){
            for(int j=1; j<=str2.length(); j++){
                
                if(str1.charAt(i-1) == str2.charAt(j-1)){ // if same
                    dp[i][j] = 1 + dp[i-1][j-1];
                } else{ // if different
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        
        return dp[str1.length()][str2.length()];
    }
}
