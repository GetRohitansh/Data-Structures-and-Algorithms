public class Dynamic_Programing_1 {
    /* 
    Topics Covered:
     * Fibonacci (recursion)
     * D.P. Fibonacci (memoization)
     * D.P. Fibonacci (tabulation)
     * Climbing Stairs (recursion)
     * D.P. Climbing Stairs (memoization)
     * D.P. Climbing Stairs (tabulation)
     * Climbing Stairs Variation (Recursion) (1, 2, 3 steps allowed)
    */

    public static void main(String[] args) {
        System.out.println("Fibonacci (recursion): "+fibonacci(5));
        System.out.println("D.P. Fibonacci (memoization): "+dp_fibonacci_memoization(46, new int[47]));
        System.out.println("D.P. Fibonacci (tabulation): "+dp_fibonacci_tabulation(46));
        System.out.println("Climbing Stairs (recursion): "+climbing_stairs(5));
        System.out.println("D.P. Climbing Stairs (memoization): "+dp_climbing_stairs_memoization(5, new int[41]));
        System.out.println("D.P. Climbing Stairs (tabulation): "+dp_climbing_stairs_tabulation(5));
        System.out.println("Climbing Stairs Variation (Recursion) (1, 2, 3 steps allowed): "+climbing_stairs_variation(5));
    }

    public static int fibonacci(int n){
        if(n==0 || n==1){
            return n;
        }

        return fibonacci(n-1) + fibonacci(n-2);
    }

    public static int dp_fibonacci_memoization(int n, int fibo[]){
        if(n==0 || n==1){
            return fibo[n] = n;
        }

        if(fibo[n] != 0){ // fibonacci(n) already calculated
            return fibo[n];
        }

        // calculate fibonacci(n) and store it in respective index of the array
        return fibo[n] = dp_fibonacci_memoization(n-1, fibo) + dp_fibonacci_memoization(n-2, fibo);
    }

    public static int dp_fibonacci_tabulation(int n){
        int dp[] = new int[n+1];
        dp[0] = 0; // dp[i] = fibonacci[i]
        dp[1] = 1;

        for(int i=2; i<=n; i++){
            dp[i] = dp[i-1] + dp[i-2];
        }

        // calculate fibonacci(n) and store it in respective index of the array
        return dp[n];
    }


    public static int climbing_stairs(int n){
        if(n == 0){
            return 1;
        }

        if(n < 0){
            return 0;
        }

        return climbing_stairs(n-1) + climbing_stairs(n-2);
    }

    public static int dp_climbing_stairs_memoization(int n, int dp[]){
        if(n == 0){
            return dp[n] = 1;
        }

        if(n < 0){
            return 0;
        }

        if(dp[n] != 0){ // if already calc. 
            return dp[n];
        }

        // calc. and store the value in dp array
        return dp[n] = dp_climbing_stairs_memoization(n-1, dp) + dp_climbing_stairs_memoization(n-2, dp);
    }

    public static int dp_climbing_stairs_tabulation(int n){
        int dp[] = new int[n+1];
        dp[0] = 1;
        dp[1] = 1;

        for(int i=2; i<=n; i++){
            dp[i] = dp[i-1] + dp[i-2];
        }

        return dp[n];
    }

    public static int climbing_stairs_variation(int n){ // 1, 2, 3 steps allowed
        if(n == 0){
            return 1;
        }

        if(n < 0){
            return 0;
        }

        return climbing_stairs_variation(n-1) + climbing_stairs_variation(n-2) + + climbing_stairs_variation(n-3);
    }
}
