public class BacktrackingAssignment {
    /* 
    Topics Covered: 
     * Rat in a maze
     * Keypad combinations
     * Knight's Tour
    */
    public static void main(String[] args) {

        char maze[][] = { { '1', '0', '0', '0' },
                { '1', '1', '0', '1' },
                { '0', '1', '0', '0' },
                { '1', '1', '1', '1' } };

        System.out.println("Called 'Rat in Maze': ");
        rat_in_a_maze(maze, 0, 0);

        String keypad[] = { "oper", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz" };
        System.out.println("Called 'Keypad Combinations': ");
        keypad_combinations(keypad, "2", "", 0);
        System.out.println("\nCalling Knight's Tour...");
        knights_tour(9);
        

    }

    // Question 1
    // You are given a starting position for a rat, which is stuck in a maze at an
    // initial point (0,0) (the maze can be thought of as a 2-dimensional plane).
    // The maze would be given in the form of a square matrix of order N*N, where
    // the cells with value 0 represent the maze’s blocked locations, while value 1
    // is the open/available path that the rat can take to reach its destination.
    // The rat's destination is at (N - 1, N - 1). Your task is to find all the
    // possible paths that the rat can take to reach from the source to destination
    // in the maze. The possible directions that it can take to move in the maze are
    // 'U' (up) i.e. (x, y-1), 'D' (down) i.e. (x, y + 1), 'L' (left) i.e. (x - 1,
    // y), 'R' (right) i.e. (x + 1, y)."
    public static void rat_in_a_maze(char maze[][], int row, int col) {
        if (row == maze.length - 1 && col == maze.length - 1) {
            maze[row][col] = 'w';
            print_2D_array(maze);
            System.out.println();
            return;
        }

        if (row < 0 || col < 0 || row >= maze.length || col >= maze.length || maze[row][col] != '1') {
            return;
        }

        // left
        maze[row][col] = '.';
        rat_in_a_maze(maze, row, col - 1);
        maze[row][col] = '1';

        // right
        maze[row][col] = '.';
        rat_in_a_maze(maze, row, col + 1);
        maze[row][col] = '1';

        // up
        maze[row][col] = '.';
        rat_in_a_maze(maze, row - 1, col);
        maze[row][col] = '1';

        // down
        maze[row][col] = '.';
        rat_in_a_maze(maze, row + 1, col);
        maze[row][col] = '1';
    }

    // Question 2
    // Keypad Combinations Given a string containing digits from 2-9 inclusive,
    // print all possible letter combinations that the number could represent. You
    // can print the answer in any order
    // A mapping of digits to letters (just like on the telephone buttons) is given
    // below. Note that 1 does not map to any letters
    public static void keypad_combinations(String keypad[], String number, String ans, int index) {
        if (index == number.length()) {
            System.out.print(ans + " ");
            return;
        }
        String current_number = keypad[(int) number.charAt(index) - '0'];
        for (int i = 0; i < current_number.length(); i++) {
            keypad_combinations(keypad, number, ans + current_number.charAt(i), index + 1);
        }
    }

    // Question 3
    // Knight’s Tour  O(8 ^ (n^2))
    // Given a N*N board with the Knight placed on the first block of an empty
    // board. Moving according to the rules of chess,knights must visit each square
    // exactly once. Print the order of each cell in which they are visited.
    public static void knights_tour(int length){
        
        int xMove[] = {1,1,2,2,-1,-1,-2,-2};
        int yMove[] = {2,-2,1,-1,2,-2,1,-1};        
        
        int solution[][] = new int[length][length];
        for (int i = 0; i < solution.length; i++) {
            for (int j = 0; j < solution.length; j++) {
                solution[i][j] = -1;
            }
        }

        // AS Knight starts from (0,0)
        solution[0][0] = 0;        

        if(!solve_knight_tour(0, 0, 1, solution, xMove, yMove)){
            System.out.println("No solution exist");
        }
    }
    public static boolean solve_knight_tour(int x, int y, int move_number, int solution[][], int xMove[], int yMove[]){

        if(move_number == (solution.length * solution.length)){
            print_2D_array(solution);
            return true;
        }

        for(int i=0 ; i<xMove.length; i++) {
            int next_x = x + xMove[i];
            int next_y = y + yMove[i];

            if(isSafe_knight_tour(next_x, next_y, solution)){
                solution[next_x][next_y] = move_number;
                if(solve_knight_tour(next_x, next_y, move_number+1, solution, xMove, yMove)){ // find next move 
                    return true;
                }
                solution[next_x][next_y] = -1; //backtracking (if above condition fails)
            }
        }

        return false;
    }
    public static boolean isSafe_knight_tour(int row, int col, int solution[][]){
        if(row < 0 || col < 0 || row >= solution.length || col >= solution.length){
            return false;
        }
        return solution[row][col] == -1;
    }


    public static void print_2D_array(char array[][]) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void print_2D_array(int array[][]) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }
    }
}
