import java.util.Arrays;

public class Dynamic_Programming_5 {
    /* 
    Topics Covered:
     * Wildcard Matching (Recursion)
     * D.P. Wildcard Matching (Tabulation)
     * 
     * Catalan's Number (Recursion)
     * D.P. Catalan's Number (Memoization)
     * D.P. Catalan's Number (Tabulation)
     * 
     * Counting Trees (BST) (Catalan's Number using Tabulation)
     * 
     * Number of Mountain Ranges (Catalan's Number using Tabulation)
    */
    public static void main(String[] args) {
        String text = "aacb";
        String pattern = "a**?";
        System.out.println("D.P. Wildcard Matching (tabulation): "+dp_wildcard_matching_tabulation(text, pattern));
        System.out.println("Wildcard Matching (recursion): "+wildcard_matching(text, pattern, text.length()-1, pattern.length()-1));
        System.out.println("Catalan's Number (Recursion): "+catalan_number(5));

        int n = 5;
        int dp_catalan[] = new int[n+1];
        Arrays.fill(dp_catalan, -1);
        System.out.println("D.P. Catalan's Number (Memoization): "+dp_catalan_number_memoization(n, dp_catalan));

        System.out.println("D.P. Catalan's Number (Tabulation): "+dp_catalan_number_tabulation(5));

        System.out.println("Counting Trees (BST): "+count_BST_catalans_tabulation(5));
        System.out.println("Number of Mountain Ranges: "+number_of_mountain_ranges(5));
    }

    public static boolean wildcard_matching(String text, String pattern, int s, int p){
        if(s == 0 && p == 0){
            return true;
        }

        if(s == 0){
            return wildcard_matching(text, pattern, s, p-1);
        }

        if(p == 0){
            return false;
        }

        
        // main logic
        if(text.charAt(s) == pattern.charAt(p) || pattern.charAt(p) == '?'){
            return wildcard_matching(text, pattern, s-1, p-1);
        }else if(pattern.charAt(p) == '*'){ // pattern[j] == '*
            boolean ignore = wildcard_matching(text, pattern, s, p-1); // ignore *
            boolean include = wildcard_matching(text, pattern, s-1, p);

            return ignore || include;
        }else{
            return false;
        }
    }

    public static boolean dp_wildcard_matching_tabulation(String text, String pattern){
        int s = text.length();
        int p = pattern.length();

        boolean dp[][] = new boolean[s+1][p+1];
        
        // initialization
        dp[0][0] = true; 

        for(int i=1; i<=s; i++){ // if pattern is 0, then false
            dp[i][0] = false;
        }

        for(int j=1; j<=p; j++){ // if string is 0
            if(pattern.charAt(j-1) == '*'){
                dp[0][j] = dp[0][j-1];
            }
        }

        // tabulation
        for(int i=1; i<=s; i++){
            for(int j=1; j<=p; j++){
                if(text.charAt(i-1) == pattern.charAt(j-1) || pattern.charAt(j-1) == '?'){
                    dp[i][j] = dp[i-1][j-1];
                }else if(pattern.charAt(j-1) == '*'){ // pattern[j] == '*
                    dp[i][j] = dp[i-1][j] ||  dp[i][j-1];
                }else{
                    dp[i][j] = false;
                }
            }
        }

        return dp[s][p];
    }

    public static int catalan_number(int n){
        if(n <= 1){
            return 1;
        }
        int ans = 0;
        for(int i=0; i<n; i++){
            ans += catalan_number(i) * catalan_number(n-i-1);
        }
        return ans;
    }

    public static int dp_catalan_number_memoization(int n, int dp[]){

        if(n <= 1){
            return dp[n] = 1;
        }


        if(dp[n] != -1){
            return dp[n];
        }


        int ans = 0;
        for(int i=0; i<n; i++){
            ans += dp_catalan_number_memoization(i, dp) * dp_catalan_number_memoization(n-i-1, dp);
        }

        return dp[n] = ans;
    }

    public static int dp_catalan_number_tabulation(int n){

        // create table
        int dp[] = new int[n+1];

        // initialize
        dp[0] = 1;
        dp[1] = 1;

        // meaning -> dp[i] = ith catalan's number

        // bottom up manner - fill tables
        for(int i=2; i<=n; i++){
            int ans = 0;
            for(int j=0; j<i; j++){
                ans += dp[j] * dp[i-j-1];
            }
            dp[i] = ans;
        }

        return dp[n];
    }

    public static int count_BST_catalans_tabulation(int n){
        int dp[] = new int[n+1];
        dp[0] = 1;
        dp[1] = 1;

        for(int i=2; i<=n; i++){
            for(int j=0; j<i; j++){
                int right = dp[j];
                int left = dp[i-j-1];

                dp[i] += right * left;
            }
        }

        return dp[n];
    }

    public static int number_of_mountain_ranges(int n){
        int dp[] = new int[n+1];
        dp[0] = 1;
        dp[1] = 1;

        for(int i=2; i<=n; i++){
            for(int j=0; j<i; j++){
                int right = dp[j];
                int left = dp[i-j-1];

                dp[i] += right * left;
            }
        }

        return dp[n];
    }
}
