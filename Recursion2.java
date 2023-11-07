public class Recursion2 {
    /* 
    Topics Covered: 
     * Tiling Problem 2xN floor with 2x1 tile
     * Remove Duplicates in a string
     * Pairing Problem
     * Binary String Problem
    */
    public static void main(String[] args) {
        System.out.println("Tiling Problem (2 x n): "+tiling_problem(10));
        System.out.println("Remove Duplicates in a String: "+remove_duplicate("appnnacollege", new boolean[26], 0, new StringBuilder("")));
        System.out.println("Pairing Problem: "+pairing_problem(3));
        System.out.println("Binary String Problem: ");
        binaryStringsProblem(4, "", 0);
    }

    // 2 x n or 3 x n ...
    public static int tiling_problem(int n){
        if(n<=1){
            return 1;
        }

        int ans = tiling_problem(n-1) + tiling_problem(n-2);

        return ans;
    }

    public static String remove_duplicate(String str, boolean map[], int i, StringBuilder ans){
        if(i == str.length()){
            return ans.toString();
        }

        // 'a' - 'a' = 0
        // 'b' - 'a' = 1
        if(map[str.charAt(i)-'a'] == false){
            map[str.charAt(i)-'a'] = true;
            return remove_duplicate(str, map, i+1, ans.append(str.charAt(i)));
        }

        return remove_duplicate(str, map, i+1, ans);
    }

    public static int pairing_problem(int n){
        if(n <= 2){
            return n;
        }
        int ans = pairing_problem(n-2) * (n-1) + pairing_problem(n-1);
                //number of choice for 1 = n-1
        return ans;
    }

    public static void binaryStringsProblem(int n, String ans, int lastplace){
        if(n==0){
            System.out.println(ans);
            return;
        }

        if(lastplace == 0) {
            binaryStringsProblem(n-1, ans+"0", 0);
            binaryStringsProblem(n-1, ans+"1", 1);
        }else {
            binaryStringsProblem(n-1, ans+"0", 0);
        }
    }
}
