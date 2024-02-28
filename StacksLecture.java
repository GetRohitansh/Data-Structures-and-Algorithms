import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class StacksLecture {
    /* 
    Topics Covered: 
     * Stack ArrayList implementation
     * Stack LinkedList implementation
     * Stack Java Collections Framework (JCF) implementation
     * Push at bottom
     * Reverse a string using stack
     * Stock Span Problem
     * Next Greater Element
     * Valid Parenthesis
     * Duplicate Parentheses
     * Maximum Area in Histogram
    */
    public static void main(String[] args) {
        Stack_ArrayList stack = new Stack_ArrayList();
        stack.push(10);
        stack.push(20);
        stack.push(30);
        stack.push(40);
        stack.push(50);
        
        //top first then bottom
        System.out.println("ArrayList Implementation: ");
        stack.print_stack();
        System.out.println(stack.pop());
        stack.print_stack();

        ////////////////////////

        Stack_LinkedList stack_LL = new Stack_LinkedList();
        stack_LL.push(10);
        stack_LL.push(20);
        stack_LL.push(30);
        stack_LL.push(40);
        stack_LL.push(50);

        System.out.println("\nLinkedList Implementation: ");
        stack_LL.print_stack();
        System.out.println(stack_LL.pop());
        stack_LL.print_stack();

        /////////////////////////

        Stack<Integer> stack_JCF = new Stack<>();
        stack_JCF.push(10);
        stack_JCF.push(20);
        stack_JCF.push(30);
        stack_JCF.push(40);
        stack_JCF.push(50);        

        System.out.println("\nJava Collections Framework Implementation: ");
        System.out.println(stack_JCF.pop());
        print_stack_JCF(stack_JCF);
        System.out.println();

        //////////////////////////

        Stack<Integer> stack_1 = new Stack<>();
        stack_1.push(3);
        stack_1.push(4);
        stack_1.push(5);
        stack_1.push(6);

        System.out.print("Push at bottom: ");
        push_at_bottom(stack_1, 1);
        print_stack_JCF(stack_1);

        System.out.println("Reverse string using stack: "+reverse_string("abcdef"));
        System.out.print("Reverse a stack: "); reverse_a_stack(stack_1);
        print_stack_JCF(stack_1);

        System.out.println("Stock span problem (brute force): "+Arrays.toString(stock_span_problem_brute_force(new int[]{100, 80, 60, 70, 60, 85, 100})));
        System.out.println("Stock span problem (using stack): "+Arrays.toString(stock_span_problem_stack(new int[]{100, 80, 60, 70, 60, 85, 100})));

        System.out.println("Next Greater Element: "+Arrays.toString(next_greater_element(new int[]{6,8,0,1,3})));
        System.out.println("Valid Parenthesis: "+valid_parenthesis("[]"));
        System.out.println("Duplicate Parentheses: "+duplicate_parentheses("((a+b)+(c+d))")); // (((a+(b)))+((c+d))
        System.out.println("Max Area in Histogram (brute force): "+max_area_in_histogram_brute_force(new int[]{2,1,5,6,2,3}));
        System.out.println("Max Area in Histogram (optimized): "+max_area_in_histogram_optimized_stack(new int[]{2,1,5,6,2,3}));
    }

    public static void push_at_bottom(Stack<Integer> stack, int data){
        if(stack.empty()){
            stack.push(data);
            return;
        }

        int val = stack.pop();
        push_at_bottom(stack, data);
        stack.push(val);
    }

    public static String reverse_string(String str){
        Stack<Character> stack = new Stack<>();
        for(int i=0; i<str.length(); i++){
            stack.push(str.charAt(i));
        }
        StringBuilder ans = new StringBuilder("");
        for(int i=0; i<str.length(); i++){
            char ch = stack.pop();
            ans.append(ch);
        }
        return ans.toString();
    }

    public static void reverse_a_stack(Stack<Integer> stack){
        if(stack.isEmpty()){
            return;
        }

        int top = stack.pop();
        reverse_a_stack(stack);
        push_at_bottom(stack, top);
    }

    public static int[] stock_span_problem_brute_force(int nums[]){
        int span[] = new int[nums.length];

        for(int i=0; i<nums.length; i++){
            int number_of_min = 0;
            for(int j=i; j>=0; j--){
                if(nums[j] <= nums[i]){
                    number_of_min++;
                }else{
                    break;
                }
            }
            span[i] = number_of_min;
        }

        return span;
    }

    public static int[] stock_span_problem_stack(int nums[]){
        int span[] = new int[nums.length];
        Stack<Integer> stack = new Stack<>();
        span[0] = 1;
        stack.push(0);

        for(int i=1; i<nums.length; i++){
            int today_price = nums[i];
            while(!stack.isEmpty() && today_price >= nums[stack.peek()]){
                stack.pop();
            }
            // span array
            if(stack.isEmpty()){
                span[i] = i+1; // highest element
            }else{
                int prevHigh = stack.peek();
                span[i] = i - prevHigh;
            }
            stack.push(i);
        }
        return span;
    }

    public static int[] next_greater_element(int nums[]){
        Stack<Integer> stack = new Stack<>();
        int next_greater[] = new int[nums.length];
        for (int i=nums.length-1; i>=0; i--) {
            int curr = nums[i];
            while(!stack.isEmpty() && curr >= stack.peek()){
                stack.pop();
            }
            if(stack.isEmpty()){
                next_greater[i] = -1;
            }else{
                next_greater[i] = stack.peek();
            }
            stack.push(nums[i]);
        }
        return next_greater;
    }

    public static boolean valid_parenthesis(String str){
        Stack<Character> stack = new Stack<>();
        for(int i=0; i<str.length(); i++){
            char ch = str.charAt(i);
            
            if(ch == '{' || ch == '(' || ch == '[' ) { //opening
                stack.push(ch);
            }else { // closing
                if(stack.isEmpty()){
                    return false;
                }

                if(ch == '}' && stack.peek() == '{'){
                    stack.pop();
                }
                else if(ch == ')' && stack.peek() == '('){
                    stack.pop();
                }
                else if(ch == ']' && stack.peek() == '['){
                    stack.pop();
                }
                else{
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }

    public static boolean duplicate_parentheses(String str){
        // expression given is balanced
        Stack<Character> stack = new Stack<>();

        for(int i=0; i<str.length(); i++){
            char ch = str.charAt(i);

            if(ch != ')'){ // ch is operand, operator, opening
                stack.push(ch);
            }else{
                int count = 0;
                while(stack.peek()!='('){ // finding opening
                    count++;
                    stack.pop();
                }
                if(count == 0){
                    return true; //duplicate exists
                }
                // else, remove top opening
                stack.pop();
            }
        }
        return false; // balanced string (equal amount of open and close) parsing completed
    }

    public static int max_area_in_histogram_brute_force(int nums[]){
        int max_area = 0;
        for(int i=0; i<nums.length; i++){
            // int curr_area = nums[i];
            int min = nums[i];
            for(int j=i; j>=0; j--){                
                min = Math.min(min, nums[j]);
                max_area = Math.max(max_area, min*(i-j+1));
            }
        }
        return max_area;
    }

    public static int max_area_in_histogram_optimized_stack(int nums[]){
        
        // Stack<Integer> stack = new Stack<>();
        int next_smallest_left[] = next_smallest_left_element(nums);
        int next_smallest_right[] = next_smallest_right_element(nums);
        int max_area = 0;
        for(int i=0; i<nums.length; i++){
            int height = nums[i];
            int width = next_smallest_right[i] - next_smallest_left[i] - 1;
            max_area = Math.max(max_area, height*width);
        }
        // System.out.println("NSL: "+Arrays.toString(next_smallest_left));
        // System.out.println("NSR: "+Arrays.toString(next_smallest_right));

        return max_area;
    }
    public static int[] next_smallest_left_element(int nums[]){
        int next_smallest_left[] = new int[nums.length];
        Stack<Integer> stack = new Stack<>();
        stack.push(0); //index
        next_smallest_left[0] = -1;

        for(int i=1; i<nums.length; i++){
            while(!stack.isEmpty() && nums[i] <= nums[stack.peek()]){
                stack.pop();
            }
            if(stack.isEmpty()){
                next_smallest_left[i] = -1;
            }else{
                next_smallest_left[i] = stack.peek(); //index
            }
            stack.push(i);
        }
        return next_smallest_left;
    }
    public static int[] next_smallest_right_element(int nums[]){
        int next_smallest_right[] = new int[nums.length];
        Stack<Integer> stack = new Stack<>();
        stack.push(nums.length-1); //index
        next_smallest_right[nums.length-1] = nums.length;

        for(int i=nums.length-2; i>=0; i--){
            while(!stack.isEmpty() && nums[i] <= nums[stack.peek()]){
                stack.pop();
            }
            if(stack.isEmpty()){
                next_smallest_right[i] = nums.length;
            }else{
                next_smallest_right[i] = stack.peek(); //index
            }
            stack.push(i);
        }
        return next_smallest_right;
    }

    public static void print_stack_JCF(Stack<Integer> stack){
        ArrayList<Integer> temp = new ArrayList<>();
        while(!stack.isEmpty()){
            int val = stack.pop();
            System.out.print(val + " ");
            temp.add(0, val);
        }
        System.out.println();

        int index = 0;
        while(index != temp.size()){
            int val = temp.get(index);
            stack.push(val);
            index++;
        }
    }
}

class Stack_ArrayList{
    ArrayList<Integer> list = new ArrayList<>();
    public boolean isEmpty(){
        return list.size() == 0;
    }

    // push
    public void push(int data){
        list.add(data);
    }

    //pop
    public int pop(){
        if(isEmpty()){
            return Integer.MIN_VALUE;
        }
        int top = list.get(list.size()-1);
        list.remove(list.size()-1);
        return top;
    }

    //peek
    public int peek(){
        if(isEmpty()){
            return Integer.MIN_VALUE;
        }
        return list.get(list.size()-1);
    }

    //print stack
    
    public void print_stack(){
        ArrayList<Integer> temp = new ArrayList<>();
        while(!isEmpty()){
            int val = pop();
            System.out.print(val + " ");
            temp.add(0, val);
        }
        System.out.println();

        int index = 0;
        while(index != temp.size()){
            int val = temp.get(index);
            push(val);
            index++;
        }
    }
}

class Stack_LinkedList{
    public class Node{
        int data;
        Node next;

        public Node(int data){
            this.data = data;
            this.next = null;
        }
    }

    public Node head;

    public boolean isEmpty(){
        return (head == null);
    }

    //push
    public void push(int data){
        Node newNode = new Node(data);
        newNode.next = head;
        head = newNode;
    }

    //pop
    public int pop(){
        if(head == null){
            return Integer.MIN_VALUE;
        }
        int val = head.data;
        head = head.next;
        return val;
    }

    //peek
    public int peek(){
        if(head == null){
            return Integer.MIN_VALUE;
        }
        int val = head.data;
        return val;
    }

    public void print_stack(){
        Node temp = head;
        while(temp != null){
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
        System.out.println();
    }
}