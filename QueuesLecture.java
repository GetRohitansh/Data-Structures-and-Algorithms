import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.Deque;

public class QueuesLecture {
    /* 
    Topics Covered: 
     * Queue using array
     * Circular using (can be implemented with array only)
     * Queue using LinkedList
     * Queue using Java Collection Framework
     * Queue using 2 stack (push in O(N))
     * Queue using 2 stack (pop in O(N))
     * First Non Repeating Letter
     * Interleaved 2 Halves of a Queue
     * Queue Reversal
     * Deque JCF implementation
     * Stack using Deque
     * Queue using Deque
    */
    public static void main(String[] args) {
        Queue_Array queue_Array = new Queue_Array(6);
        queue_Array.add(10);
        queue_Array.add(20);
        queue_Array.add(30);
        queue_Array.remove();
        queue_Array.add(40);
        queue_Array.add(50);
        queue_Array.add(60);
        System.out.print("Queue using array: ");
        queue_Array.print_queue_array();

        Circular_Queue circular_queue = new Circular_Queue(5);
        circular_queue.add(10);
        circular_queue.add(20);
        circular_queue.add(30);
        circular_queue.add(40);
        circular_queue.add(50);
        circular_queue.remove();
        circular_queue.remove();
        circular_queue.remove();
        circular_queue.add(10);
        circular_queue.add(20);
        circular_queue.add(30);
        System.out.print("Circular Queue (done using array only): ");
        circular_queue.print_circular_queue();
        

        Queue_LinkedList queue_LinkedList = new Queue_LinkedList();
        queue_LinkedList.add(10);
        queue_LinkedList.add(20);
        queue_LinkedList.add(30);
        queue_LinkedList.add(40);
        queue_LinkedList.add(50);
        queue_LinkedList.remove();
        queue_LinkedList.remove();
        queue_LinkedList.remove();
        queue_LinkedList.add(10);
        queue_LinkedList.add(20);
        System.out.print("Queue using LinkedList: ");
        queue_LinkedList.print_queue_LinkedList();

        Queue<Integer> queue_JCF = new LinkedList<>();
        queue_JCF.add(11);
        queue_JCF.add(1);
        queue_JCF.add(2);
        queue_JCF.add(3);
        queue_JCF.add(4);
        queue_JCF.add(5);
        queue_JCF.add(6);
        queue_JCF.add(7);
        queue_JCF.add(8);
        queue_JCF.add(9);
        queue_JCF.add(10);        
        queue_JCF.remove();
        System.out.print("Queue using Java Collection Framework: ");
        System.out.println(queue_JCF);

        /*Queue using 2 stack (push in O(N))
     * Queue using 2 stack (pop in O(N))
     * First Non Repeating Letter
     * Interleaved 2 Halves of a Queue
     * Queue Reversal
     * Deque JCF implementation
     * Stack using Deque
     * Queue using Deque */
        Queue_2_Stack_n_push queue_2_Stack_n_push = new Queue_2_Stack_n_push();
        queue_2_Stack_n_push.add(1);
        queue_2_Stack_n_push.add(2);
        queue_2_Stack_n_push.add(3);
        queue_2_Stack_n_push.remove();
        queue_2_Stack_n_push.add(4);
        queue_2_Stack_n_push.add(5);
        System.out.print("Queue using 2 stack (push in O(N)): ");
        queue_2_Stack_n_push.print_queue_2_stack();

        Queue_2_Stack_n_pop queue_2_Stack_n_pop = new Queue_2_Stack_n_pop();
        queue_2_Stack_n_pop.add(1);
        queue_2_Stack_n_pop.add(2);
        queue_2_Stack_n_pop.add(3);
        queue_2_Stack_n_pop.remove();
        queue_2_Stack_n_pop.add(4);
        queue_2_Stack_n_pop.add(5);
        System.out.print("Queue using 2 stack (pop in O(N)): ");
        queue_2_Stack_n_pop.print_queue_2_stack();

        Stack_using_2_queue_n_pop stack_using_2_queue_n_pop = new Stack_using_2_queue_n_pop();
        stack_using_2_queue_n_pop.push(1);
        stack_using_2_queue_n_pop.push(2);
        stack_using_2_queue_n_pop.push(3);
        stack_using_2_queue_n_pop.push(4);
        stack_using_2_queue_n_pop.pop();
        System.out.print("Stack using 2 queue (pop in O(N)): ");
        stack_using_2_queue_n_pop.print_stack_2_queue();

        System.out.println("First Non Repeating Letter: "+first_non_repeating_letter("aabccxb"));
        System.out.println("Interleaved 2 Halves of a Queue: "+interleave_2_halves_of_queue(queue_JCF));
        System.out.println("Queue Reversal: "+queue_reversal(queue_JCF));

        Deque<Integer> deque = new LinkedList<>();  
        deque.addFirst(1); // 1
        deque.addFirst(2); // 2 1
        deque.addLast(3); // 2 1 3
        deque.addLast(4); // 2 1 3 4
        System.out.println("Deque JCF implementation: "+deque);
        deque.removeFirst();
        deque.removeLast();
        System.out.println("Deque JCF implementation: "+deque);

        Stack_Using_Deque stack_using_deque = new Stack_Using_Deque();
        stack_using_deque.push(1);
        stack_using_deque.push(2);
        stack_using_deque.push(3);
        stack_using_deque.push(4);
        stack_using_deque.push(5);
        stack_using_deque.push(6);
        stack_using_deque.pop();
        System.out.print("Stack using Deque: ");
        stack_using_deque.print_stack_using_deque();


        Queue_Using_Deque queue_using_deque = new Queue_Using_Deque();
        queue_using_deque.add(1);
        queue_using_deque.add(2);
        queue_using_deque.add(3);
        queue_using_deque.add(4);
        queue_using_deque.add(5);
        queue_using_deque.add(6);
        queue_using_deque.remove();
        System.out.print("Queue using Deque: ");
        queue_using_deque.print_queue_using_deque();

    }    

    public static String first_non_repeating_letter(String str){
        Queue<Character> queue = new LinkedList<>();
        StringBuilder ans = new StringBuilder("");
        char freq[] = new char[26];
        for(int i=0; i<str.length(); i++){
            char ch = str.charAt(i);
            freq[ch-'a']++;
            queue.add(ch);

            while(!queue.isEmpty() && freq[queue.peek()-'a'] > 1){
                queue.remove();
            }
            if(queue.isEmpty()){
                ans.append('-');
            }else{
                ans.append(queue.peek());
            }
        }
        return ans.toString();
    }

    public static Queue<Integer> interleave_2_halves_of_queue(Queue<Integer> queue){
        Queue<Integer> queue_1st_half = new LinkedList<>();
        int size = queue.size();
        for(int i=0; i<size/2; i++){
            queue_1st_half.add(queue.remove());
        }

        for(int i=0; i<size/2; i++){
            queue.add(queue_1st_half.remove());
            queue.add(queue.remove());
        }
        return queue;
    }

    public static Queue<Integer> queue_reversal(Queue<Integer> queue){
        Stack<Integer> stack = new Stack<>();

        while(!queue.isEmpty()){
            stack.push(queue.remove());
        }
        while(!stack.isEmpty()){
            queue.add(stack.pop());
        }

        return queue;
    }
}

class Queue_Array{
    int array[];
    int size;
    int rear;

    Queue_Array(int size){
        this.array = new int[size];
        this.size = size;
        this.rear = -1;
    }

    public boolean isEmpty(){
        return (rear == -1);
    }

    //add
    public void add(int data){
        if(rear == size-1){
            return; // array is filled
        }
        rear = rear + 1;
        array[rear] = data;
    }

    public int remove(){
        if(rear == -1){
            return Integer.MIN_VALUE; // array is empty
        }
        int val = array[0];
        for(int i=1; i<=rear; i++){
            array[i-1] = array[i];
        }
        rear = rear - 1;  
        return val;         
    }

    public int peek(){
        if(rear == -1){
            return Integer.MIN_VALUE;
        }
        return array[0];
    }

    public void print_queue_array(){
        for(int i=0; i<=rear; i++){
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }
}

class Circular_Queue{
    int array[];
    int size;
    int rear;
    int front;

    Circular_Queue(int size){
        this.array = new int[size];
        this.size = size;
        this.rear = -1;
        this.front = -1;
    }

    public boolean isEmpty(){
        return (rear == -1 && front == -1);
    }

    public boolean isFull(){
        return (rear + 1)%size == front;
    }

    public void add(int data){
        if(isFull()){
            return; // full
        }
        if(front == -1){
            front++;
        }
        rear = (rear + 1)%size;
        array[rear] = data;
    }

    public int remove(){
        if(isEmpty()){
            return Integer.MIN_VALUE; // full
        }
        
        int val = array[front];
        if(rear == front){ // last element
            rear = front = -1;
            return val;
        }
        front = (front + 1)%size;
        return val;
    }

    public int peek(){
        if(isEmpty()){
            return Integer.MIN_VALUE;
        }
        return array[front];
    }

    public void print_circular_queue(){
        if(isEmpty()){
            return;
        }

        if(front < rear){
            for(int i=front; i<=rear; i++){
                System.out.print(array[i] + " ");
            }
            System.out.println();
        }else{
            for(int i=front; i<=(size+rear); i++){
                System.out.print(array[i%size] + " ");
            }
            System.out.println();
        }
    }
}

class Queue_LinkedList{
    
    public class Node{
        int data;
        Node next;

        public Node(int data){
            this.data = data;
            this.next = null;
        }
    }

    public Node head;
    public Node tail;
    public int size;

    public boolean isEmpty(){
        return head == null;
    }

    public void add(int data){
        Node newNode = new Node(data);
        size++;
        if(head == null){
            head = tail = newNode;
            return;
        }
        
        tail.next = newNode;
        tail = newNode;
    }

    public int remove(){        
        if(isEmpty()){
            return Integer.MIN_VALUE;
        }
        size--;
        int val = head.data;        
        head = head.next;

        return val;
    }

    public int peek(){        
        if(isEmpty()){
            return Integer.MIN_VALUE;
        }
        int val = head.data;     
        return val;
    }

    public void print_queue_LinkedList(){
        if(isEmpty()){
            return;
        }

        Node temp = head;
        while(temp != null){
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
        System.out.println();
    }
}

class Queue_2_Stack_n_push{
    Stack<Integer> s1, s2;

    public Queue_2_Stack_n_push(){
        this.s1 = new Stack<>(); // main 
        this.s2 = new Stack<>();
    }

    public void add(int data){
        if(!s1.isEmpty()){

            while(!s1.isEmpty()){
                s2.push(s1.pop());
            }

            s1.push(data);

            while(!s2.isEmpty()){
                s1.push(s2.pop());
            }
        }else{
            s1.push(data);
        }
    }

    public int remove(){
        if(s1.isEmpty()){
            return Integer.MIN_VALUE;
        }
        return s1.pop();
    }

    public int peek(){
        if(s1.isEmpty()){
            return Integer.MIN_VALUE;
        }
        return s1.peek();
    }

    public void print_queue_2_stack(){
        while(!s1.isEmpty()){
            System.out.print(s1.pop() + " ");
        }
        System.out.println();
    }
}

class Queue_2_Stack_n_pop{
    Stack<Integer> s1, s2;

    public Queue_2_Stack_n_pop(){
        this.s1 = new Stack<>(); // main 
        this.s2 = new Stack<>();
    }

    public int remove(){
        if(s1.isEmpty()){
            return Integer.MIN_VALUE;
        }

        while(!s1.isEmpty()){
            s2.push(s1.pop());
        }
        int val = s2.pop();
        while(!s2.isEmpty()){
            s1.push(s2.pop());
        }
        return val;
    }

    public void add(int data){
        s1.push(data);
    }

    public int peek(){
        if(s1.isEmpty()){
            return Integer.MIN_VALUE;
        }

        while(!s1.isEmpty()){
            s2.push(s1.pop());
        }
        int val = s2.peek();
        while(!s2.isEmpty()){
            s1.push(s2.pop());
        }
        return val;
    }

    public void print_queue_2_stack(){
        while(!s1.isEmpty()){
            System.out.print(remove() + " ");
        }
        System.out.println();
    }
}

class Stack_using_2_queue_n_pop{
    Queue<Integer> q1 = new LinkedList<>();
    Queue<Integer> q2 = new LinkedList<>();

    public boolean isEmpty(){
        return q1.isEmpty() && q2.isEmpty();
    }

    public void push(int data){
        if(!q1.isEmpty()){
            q1.add(data);
        }else{
            q2.add(data);
        }
    }

    public int pop(){
        if(isEmpty()){
            return Integer.MIN_VALUE;
        }

        int top = Integer.MIN_VALUE;

        if(!q1.isEmpty()){ // q1 is not empty (q1 contains elements)
            while(!q1.isEmpty()){
                top = q1.remove();
                if(q1.isEmpty()){
                    break; // last element
                }
                q2.add(top);
            }
        }else {
            while(!q2.isEmpty()){
                top = q2.remove();
                if(q2.isEmpty()){
                    break; // last element
                }
                q1.add(top);
            }
        }

        return top;
    }

    public int peek(){
        if(isEmpty()){
            return Integer.MIN_VALUE;
        }

        int top = Integer.MIN_VALUE;

        if(!q1.isEmpty()){ // q1 is not empty (q1 contains elements)
            while(!q1.isEmpty()){
                top = q1.remove();
                q2.add(top);
            }
        }else {
            while(!q2.isEmpty()){
                top = q2.remove();
                q1.add(top);
            }
        }

        return top;
    }

    public void print_stack_2_queue(){
        while(!isEmpty()){
            System.out.print(pop() + " ");
        }
        System.out.println();
    }
}

class Stack_Using_Deque{
    Deque<Integer> deque = new LinkedList<>();

    public void push(int data){
        deque.addLast(data);
    }

    public int pop(){
        return deque.removeLast();
    }

    public int peek(){
        return deque.getLast();
    }

    public void print_stack_using_deque(){
        while(!deque.isEmpty()){
            System.out.print(pop() + " ");
        }
        System.out.println();
    }
}

class Queue_Using_Deque{
    Deque<Integer> deque = new LinkedList<>();

    public void add(int data){
        deque.addLast(data);
    }

    public int remove(){
        return deque.removeFirst();
    }

    public int peek(){
        return deque.getFirst();
    }

    public void print_queue_using_deque(){
        while(!deque.isEmpty()){
            System.out.print(remove() + " ");
        }
        System.out.println();
    }
}
