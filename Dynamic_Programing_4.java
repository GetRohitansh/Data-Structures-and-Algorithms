import java.util.Arrays;
import java.util.HashSet;

public class Dynamic_Programing_4 {
    /* 
    Topics Covered:
     * Longest Common Substring (Recursion)
     * D.P. Longest Common Substring (Memoization)
     * D.P. Longest Common Substring (Tabulation)
     * 
     * Longest Increasing Subsequence (Recursion)
     * D.P. Longest Increasing Subsequence (Tabulation)
     * 
     * Edit Distance Min Operations (Recursion)
     * D.P. Edit Distance Min Operations (Memoization)
     * D.P. Edit Distance Min Operations (Tabulation)
     * 
     * D.P. String Conversion (Tabulation)
     * String Conversion (Shortcut)
    */
    
    public static void main(String[] args) {
        
        System.out.println("Longest Common Substring (Recursion): "+longest_common_substring("ABCDGH", "ACDGHR", 0, 0)); 

        String str1 = "ABCDGH";
        String str2 = "ACDGHR";
        int dp[][] = new int[str1.length()+1][str2.length()+1];
    
        // initialize DP array with -1
        for(int[] i : dp){
            Arrays.fill(i, -1);
        }
        System.out.println("D.P. Longest Common Substring (Memoization): "+dp_longest_common_substring_memoization(str1, str2, 0, 0, dp)); 
        System.out.println("D.P. Longest Common Substring (Tabulation): "+dp_longest_common_substring_tabulation(str1, str2));

        System.out.println("Longest Increasing Subsequence (Recursion): "+longest_increasing_subsequence(new int[]{50, 3, 10, 7, 40, 80}));
        System.out.println("D.P. Longest Increasing Subsequence (Tabulation): "+dp_longest_increasing_subsequence_tabulation(new int[]{50, 3, 10, 7, 40, 80}));


        str1 = "intention";
        str2 = "execution";
        dp = new int[str1.length()+1][str2.length()+1];
        // initialize DP array with -1
        for(int[] i : dp){
            Arrays.fill(i, -1);
        }
        System.out.println("Edit Distance Min Operations (Recursion): "+edit_distance(str1, str2, str1.length(), str2.length()));    
        System.out.println("D.P. Edit Distance Min Operations (Memoization): "+dp_edit_distance_memoization(str1, str2, str1.length(), str2.length(), dp));    
        System.out.println("D.P. Edit Distance Min Operations (Tabulation): "+dp_edit_distance_tabulation(str1, str2));
        System.out.println("D.P. String Conversion (Tabulation): "+dp_string_conversion_tabulation("pear", "sea"));
        System.out.println("String Conversion (Shortcut): "+string_conversion_shortcut("pear", "sea"));
    }

    public static int longest_common_substring(String str1, String str2, int curr, int max){ 
        if(str1.length() == 0 || str2.length() == 0){
            return max;
        }

        // case 1 : if last element of both are equal
        if(str1.charAt(str1.length()-1) == str2.charAt(str2.length()-1)){
            str1 = str1.substring(0, str1.length()-1);
            str2 = str2.substring(0, str2.length()-1);

            max = Math.max(curr+1, max);
            return longest_common_substring(str1, str2, curr+1, max);
        }

        // case 2 : if different, curr is reset to 0
        else {
            // n-1, m
            int ans1 = longest_common_substring(str1.substring(0, str1.length()-1), str2, 0, max);

            // n, m-1
            int ans2 = longest_common_substring(str1, str2.substring(0, str2.length()-1), 0, max);

            return Math.max(ans1, ans2);
        }
    }


    public static int dp_longest_common_substring_memoization(String str1, String str2, int curr, int max, int dp[][]){
        if(str1.length() == 0 || str2.length() == 0){
            return dp[str1.length()][str2.length()] = max;
        }

        int n = str1.length();
        int m = str2.length();

        if(dp[n][m] != -1){
            return dp[n][m];
        }

        // case 1 : if last element of both are equal
        if(str1.charAt(str1.length()-1) == str2.charAt(str2.length()-1)){

            str1 = str1.substring(0, n-1);
            str2 = str2.substring(0, m-1);

            max = Math.max(curr+1, max);
            return dp[n][m] = longest_common_substring(str1, str2, curr+1, max);
        }

        // case 2 : if different, curr is reset to 0
        else {

            // n-1, m
            int ans1 = longest_common_substring(str1.substring(0, n-1), str2, 0, max);

            // n, m-1
            int ans2 = longest_common_substring(str1, str2.substring(0, m-1), 0, max);

            return dp[n][m] = Math.max(ans1, ans2);
        }
    }


    public static int dp_longest_common_substring_tabulation(String str1, String str2){
        int n = str1.length();
        int m = str2.length();

        int dp[][] = new int[n+1][m+1];

        // initially if i == 0 / j == 0, then dp[i][j] = 0
        // (i,j) is LCS of strings of length i, j

        int ans = 0;
        for(int i=1; i<=n; i++){
            for(int j=1; j<=m; j++){
                if(str1.charAt(i-1) == str2.charAt(j-1)){
                    dp[i][j] = 1 + dp[i-1][j-1];
                    ans = Math.max(ans, dp[i][j]);
                }else{
                    dp[i][j] = 0;
                }
            }
        }
        return ans;
    }

    public static int longest_increasing_subsequence(int arr1[]){
        HashSet<Integer> set = new HashSet<>();
        for(int i=0; i<arr1.length; i++){
            set.add(arr1[i]);
        }

        // getting unique elements in arr2
        int arr2[] = new int[set.size()];
        int idx = 0;
        for(int i : set){
            arr2[idx] = i;
            idx++;
        }

        // sorting arr2
        Arrays.sort(arr2);

        return longest_increasing_subsequence_helper(arr1, arr2, arr1.length, arr2.length);  // under the hood performs LCS only
    }
    public static int longest_increasing_subsequence_helper(int arr1[], int arr2[], int n, int m){
        // if any array becomes empty, LCS is 0
        if(n == 0 || m == 0){
            return 0;
        }

        if(arr1[n-1] == arr2[m-1]){
            return 1 + longest_increasing_subsequence_helper(arr1, arr2, n-1, m-1);
        }else {
            int ans1 = longest_increasing_subsequence_helper(arr1, arr2, n, m-1);
            int ans2 = longest_increasing_subsequence_helper(arr1, arr2, n-1, m);

            return Math.max(ans1, ans2);
        }
    }
    public static int dp_longest_increasing_subsequence_tabulation(int arr1[]){
        HashSet<Integer> set = new HashSet<>();
        for(int i=0; i<arr1.length; i++){
            set.add(arr1[i]);
        }

        // getting unique elements in arr2
        int arr2[] = new int[set.size()];
        int idx = 0;
        for(int i : set){
            arr2[idx] = i;
            idx++;
        }

        // sorting arr2
        Arrays.sort(arr2);

        int n = arr1.length;
        int m = arr2.length;

        int dp[][] = new int[n+1][m+1];
        

        // performing LCS for arr1, arr2
        for(int i=1; i<=n; i++){
            for(int j=1; j<=m; j++){
                if(arr1[i-1] == arr2[j-1]){
                    dp[i][j] = 1 + dp[i-1][j-1];
                }else {
                    int ans1 = dp[i-1][j];
                    int ans2 = dp[i][j-1];

                    dp[i][j] = Math.max(ans1, ans2);
                }
            }
        }

        return dp[n][m];
    }

    public static int edit_distance(String str1, String str2, int n, int m){
        if(n == 0){
            return m;  // return n if m = 0 or vice-versa
        }

        if(m == 0){
            return n;
        }

        if(str1.charAt(n-1) == str2.charAt(m-1)){
            return edit_distance(str1, str2, n-1, m-1);
        }else{ // + 1 as one operation is required
            int add = 1 + edit_distance(str1, str2, n, m-1);
            int delete = 1 + edit_distance(str1, str2, n-1, m);
            int replace = 1 + edit_distance(str1, str2, n-1, m-1);

            return Math.min(add, Math.min(delete, replace));
        }
    }

    public static int dp_edit_distance_memoization(String str1, String str2, int n, int m, int dp[][]){
        if(n == 0){
            return dp[n][m] = m;  // return n if m = 0 or vice-versa
        }

        if(m == 0){
            return dp[n][m] = n;
        }

        if(dp[n][m] != -1){
            return dp[n][m];
        }

        if(str1.charAt(n-1) == str2.charAt(m-1)){
            return dp[n][m] = edit_distance(str1, str2, n-1, m-1);
        }else{ // + 1 as one operation is required
            int add = 1 + edit_distance(str1, str2, n, m-1);
            int delete = 1 + edit_distance(str1, str2, n-1, m);
            int replace = 1 + edit_distance(str1, str2, n-1, m-1);

            return dp[n][m] = Math.min(add, Math.min(delete, replace));
        }

    }


    public static int dp_edit_distance_tabulation(String str1, String str2){
        int n = str1.length();
        int m = str2.length();

        // create table
        int dp[][] = new int[n+1][m+1];

        // initialization
        for(int i=0; i<=n; i++){
            dp[i][0] = i;
        }

        for(int j=0; j<=m; j++){
            dp[0][j] = j;
        }

        // start filling bottom up
        for(int i=1; i<=n; i++){
            for(int j=1; j<=m; j++){
                if(str1.charAt(i-1) == str2.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1];
                }else{
                    // 3 choices
                    int add = dp[i][j-1] + 1;
                    int delete = dp[i-1][j] + 1;
                    int replace = dp[i-1][j-1] + 1;
                    
                    dp[i][j] = Math.min(add, Math.min(delete, replace));
                }
            }
        }
        return dp[n][m];
    }


    public static int dp_string_conversion_tabulation(String str1, String str2){
        int n = str1.length();
        int m = str2.length();

        // create table
        int dp[][] = new int[n+1][m+1];

        // initialization
        for(int i=0; i<=n; i++){
            dp[i][0] = i;
        }

        for(int j=0; j<=m; j++){
            dp[0][j] = j;
        }

        // start filling bottom up
        for(int i=1; i<=n; i++){
            for(int j=1; j<=m; j++){
                if(str1.charAt(i-1) == str2.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1];
                }else{
                    // 2 choices
                    int add = dp[i][j-1] + 1;
                    int delete = dp[i-1][j] + 1;
                    
                    dp[i][j] = Math.min(add,delete);
                }
            }
        }
        return dp[n][m];
    }

    public static int string_conversion_shortcut(String str1, String str2){
        int lcs = longest_common_subsequence(str1, str2);
        int del = str1.length() - lcs;
        int add = str2.length() - lcs;

        return del + add;
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

    
}
