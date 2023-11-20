import java.util.Arrays;

public class Backtracking {
    public static void main(String[] args) {
        int array[] = new int[5];
        store_value_in_array(array, 0);
        System.out.println("store value in array (final): " + Arrays.toString(array));
        System.out.println("Subsets of a string: ");
        subset_string("abc", 0, "");
        System.out.println("Find permutations of the string: ");
        find_permutations("abc", "");

        int n = 5;
        char board[][] = new char[n][n];
        // initialize board
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] = '.';
            }
        }
        
        System.out.println("N Queens (all solutions): ");
        n_queens_all_solutions(board, 0);

        System.out.println("N Queens (count all solutions): "+n_queens_count_solutions(board, 0));

        System.out.println("N Queens (one solution): "+n_queens_one_solution(board, 0));
        print_board(board);

        System.out.println("Grid Ways: "+grid_ways(3, 3));
        System.out.println("Grid Ways: "+grid_ways_optimized(3, 3));


        int sudoku[][] = {
                          {0, 0, 8, 0, 0, 0, 0, 0, 0},
                          {4, 9, 0, 1, 5, 7, 0, 0, 2},
                          {0, 0, 3, 0, 0, 4, 1, 9, 0},
                          {1, 8, 5, 0, 6, 0, 0, 2, 0},
                          {0, 0, 0, 0, 2, 0, 0, 6, 0},
                          {9, 6, 0, 4, 0, 5, 3, 0, 0},
                          {0, 3, 0, 0, 7, 2, 0, 0, 4},
                          {0, 4, 9, 0, 3, 0, 0, 5, 7},
                          {8, 2, 7, 0, 0, 9, 0, 1, 3}
                        };

        
        System.out.println("Called Sudoku Solver... : ");
        boolean ans = sudoku_solver(sudoku, 0, 0);

        if(ans) {
            System.out.println("Answer exists");
            for(int arr[] : sudoku){
                System.out.println(Arrays.toString(arr));
            }
        }else {
            System.out.println("Sudoku does not exists");
        }
    }

    public static void store_value_in_array(int array[], int i) {
        if (i == array.length) {
            System.out.println("store value in array (from top of call stack): " + Arrays.toString(array));
            return;
        }
        array[i] = i + 1;
        store_value_in_array(array, i + 1); // func to call func
        array[i] -= 2; // backtracking step
    }

    public static void subset_string(String str, int i, String ans) {
        if (i == str.length()) {
            if (ans.length() == 0) {
                System.out.println("{}");
                return;
            }
            System.out.println('{' + ans + '}');
            return;
        }

        subset_string(str, i + 1, ans); // no choice
        subset_string(str, i + 1, ans + str.charAt(i)); // yes choice
    }

    public static void find_permutations(String str, String ans) {
        if (str.length() == 0) {
            System.out.println(ans);
            return;
        }

        // recursion
        for (int i = 0; i < str.length(); i++) {
            char curr = str.charAt(i);
            String newstr = str.substring(0, i) + str.substring(i + 1);
            find_permutations(newstr, ans + curr);
        }
    }

    public static boolean isSafe(char board[][], int row, int col){
        //vertical up check
        for(int i=row-1; i>=0; i--){
            if(board[i][col] == 'Q'){
                return false;
            }
        }

        //diagonal left up check
        for(int i=row-1, j=col+1;  i>=0 && j<board[0].length; i--, j++){
            if(board[i][j] == 'Q'){
                return false;
            }
        }

        //diagonal right up  check
        for(int i=row-1, j=col-1;  i>=0 && j>=0; i--, j--){
            if(board[i][j] == 'Q'){
                return false;
            }
        }

        return true;
    }
    public static void n_queens_all_solutions(char board[][], int row) {
        if (row == board.length) {
            print_board(board);
            System.out.println();
            return;
        }

        // column loop
        for (int col = 0; col < board.length; col++) {
            if(isSafe(board, row, col)){
                board[row][col] = 'Q'; // setting the queen in place
                n_queens_all_solutions(board, row + 1); // calling next row
                board[row][col] = '.'; // removing queen as only 1 queen in 1 row
            }
        }
    }

     
    public static int n_queens_count_solutions(char board[][], int row) {
        if (row == board.length) {
            return 1;
        }

        int ans = 0;
        // column loop
        for (int col = 0; col < board[0].length; col++) {
            if(isSafe(board, row, col)){
                board[row][col] = 'Q'; // setting the queen in place
                ans += n_queens_count_solutions(board, row + 1); // calling next row
                board[row][col] = '.'; // removing queen as only 1 queen in 1 row
            }
        }

        return ans;
    }

    public static boolean n_queens_one_solution(char board[][], int row) {
        if (row == board.length) {
            return true;
        }

        // column loop
        for (int col = 0; col < board.length; col++) {
            if(isSafe(board, row, col)){
                board[row][col] = 'Q'; // setting the queen in place
                if(n_queens_one_solution(board, row + 1)){
                    return true;
                }
                board[row][col] = '.';
            }
        }

        return false;
    }

    public static void print_board(char board[][]) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static int grid_ways(int n, int m){
        if(n == 1 || m == 1){
            return 1;
        }

        int up = grid_ways(n-1, m);
        int left = grid_ways(n, m-1);

        return up + left;
    }

    public static int grid_ways_optimized(int n, int m){
        return (factorial(n-1+m-1)/(factorial(n-1)*factorial(m-1)));
    }
    public static int factorial(int val){
        if(val == 1){
            return 1;
        }

        return val * factorial(val-1);
    }


    
    public static boolean sudoku_solver(int sudoku[][], int row, int col){
        //base case
        if(row == 9) {
            return true;
        }

        //recursive work  
        int nextRow = row, nextCol = col+1;
        if(col+1 == 9){
            nextRow = row + 1;
            nextCol = 0;
        } 
        
        if(sudoku[row][col] != 0){
            return sudoku_solver(sudoku, nextRow, nextCol);
        }

        for(int digit=1; digit<=9; digit++){
            if(isSafe_to_place_sudoku(sudoku, row, col, digit)){
                sudoku[row][col] = digit;
                if(sudoku_solver(sudoku, nextRow, nextCol)){ // solution exists
                    return true;
                }
                sudoku[row][col] = 0; //if returns false
            }
        }

        return false;
    }

    // check 1. ROW
    //       2. COLUMN
    //       3. GRID
    public static boolean isSafe_to_place_sudoku(int sudoku[][], int row, int col, int digit){

        for(int i=0; i<9; i++){
            if(sudoku[i][col] == digit){
                return false;
            }

            if(sudoku[row][i] == digit){
                return false;
            }
        }


        //for grid
        int startRow = (row/3) * 3;
        int startCol = (col/3) * 3;

        for(int i=startRow; i<startRow+3; i++){
            for (int j=startCol; j<startCol+3; j++) {
                if(sudoku[i][j] == digit){
                    return false;
                }
            }
        }

        return true;
    }
}
