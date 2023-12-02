public class LinkedListLecture{
    /* 
    Topics Covered: 
     * Linked List Methods
     * Iterative Search
     * Recursive Seaerch
     * Reverse a LinkedList
     * Find and Remove Nth node from end
     * Check Palindrome
     * Detect Cycle in LinkedList
     * Remove Cycle from LinkedList
     * Merge Sort in LinkedList
     * Zig Zag LinkedList
     * Doubly Linked List Methods
     * Reverse Doubly Linked List
     * Circular LinkedList methods
    */
    public static void main(String args[]){
        LinkedList ll = new LinkedList();
        ll.add_at_first(1);
        ll.add_at_first(2);
        ll.add_at_last(3);
        ll.add_at_last(4);
        ll.add(0, 5);
        ll.remove_at_first();
        ll.remove_at_last();
        ll.add_at_last(4);

        ll.print_LL();
        System.out.println(ll.head.data);

        LinkedList ll2 = new LinkedList();
        ll2.add_at_last(3);
        System.out.println(ll2.head.data);

        LinkedList LL = new LinkedList();
        LL.add_at_last(10);
        LL.add_at_last(19);
        LL.add_at_last(20);
        LL.add_at_last(15);
        LL.add_at_last(9);
        LL.add_at_last(5);
        System.out.println("Iterative Search in Linked List: "+iterative_search(LL, 15));
        System.out.println("Recursive Search in Linked List: "+recursive_search(LL.head, 15, 0));
        reverse_Linked_list(LL); System.out.println("Reverse a Linked List: "); LL.print_LL();
        System.out.println("Find and Remove Nth data from End: "+find_and_remove_nth_node_from_end(LL, 1));

        LinkedList palindrome_LL = new LinkedList();
        palindrome_LL.add_at_last(1);
        palindrome_LL.add_at_last(2);
        palindrome_LL.add_at_last(3);
        palindrome_LL.add_at_last(4);
        palindrome_LL.add_at_last(1);
        System.out.println("Palindrome Linked List: "+check_Palindrome(palindrome_LL));

        
        LinkedList cyclic_LL = new LinkedList();
        cyclic_LL.head = cyclic_LL.new_node(10);
        cyclic_LL.head.next = cyclic_LL.new_node(20);
        cyclic_LL.head.next.next = cyclic_LL.new_node(30);
        cyclic_LL.head.next.next.next = cyclic_LL.new_node(40);
        cyclic_LL.head.next.next.next.next = cyclic_LL.new_node(35);
        cyclic_LL.head.next.next.next.next.next = cyclic_LL.new_node(60);
        cyclic_LL.head.next.next.next.next.next.next = cyclic_LL.head.next.next;
        // 10 -> 20 -> 30 -> 40 -> 10
        //head                    head
        // 10 -> 20 -> 30 -> 40 -> 50 -> 60 -> 30

        System.out.println("Detect Cycle in LL: "+detect_cycle(cyclic_LL.head));
        System.out.println("Remove Cycle from LL: "); remove_cycle(cyclic_LL.head); cyclic_LL.print_LL();
        System.out.println("Detect Cycle in LL: "+detect_cycle(cyclic_LL.head));
        System.out.println("Merge Sort in Linked List: "); print_linked_list(mergeSort(LL.head));
        System.out.println("Zig Zag Linked List: "); zig_zag_LL(LL.head);

        Doubly_LinkedList doubly_LL = new Doubly_LinkedList();
        doubly_LL.add_at_first(40);
        doubly_LL.add_at_first(30);
        doubly_LL.add_at_first(20);
        doubly_LL.add_at_first(10);
        doubly_LL.add_at_last(50);
        doubly_LL.remove_from_first();
        doubly_LL.remove_from_last();
        doubly_LL.print_doubly_LL(); // 20 30 40

        doubly_LL.reverse_doubly_LL();
        doubly_LL.print_doubly_LL();

        Circular_LinkedList circular_LL = new Circular_LinkedList();
        circular_LL.add_at_front(40);
        circular_LL.add_at_front(30);
        circular_LL.add_at_front(20);
        circular_LL.add_at_front(10);
        circular_LL.add_at_last(50);
        circular_LL.add_in_between(35, 3);
        circular_LL.print_circular_LL();
    }

    public static void print_linked_list(LinkedList.Node head){
        LinkedList.Node temp = head;
        while(temp != null){
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
        System.out.println();
    }

    public static int iterative_search(LinkedList LL, int key){
        LinkedList.Node temp = LL.head;
        int idx = 0;
        while(idx < LL.size){
            if(temp.data == key){
                return idx;
            }
            temp = temp.next;
            idx++;
        }
        return -1;
    }

    public static int recursive_search(LinkedList.Node head, int key, int idx){
        if(head == null){
            return -1;
        }

        if(head.data == key){
            return idx;
        }
        return recursive_search(head.next, key, idx+1);        
    }

    public static void reverse_Linked_list(LinkedList LL){
        LinkedList.Node prev = null;
        LinkedList.Node curr = LL.tail =  LL.head;
        LinkedList.Node next = curr.next;

        while(curr != null){
            curr.next = prev;
            prev = curr;
            curr = next;
            if(next != null){
                next = next.next;
            }
        }
        LL.head = prev;
    }

    public static int find_and_remove_nth_node_from_end(LinkedList LL, int N){
        LinkedList.Node prev = LL.head;
        LinkedList.Node curr = LL.head;

        int distance = -1;
        while(curr.next != null){
            distance++;
            curr = curr.next;
            if(distance >= N){
                prev = prev.next;
            }
        }
        int data = prev.next.data;
        prev.next = prev.next.next;
        return data;
    }

    public static LinkedList.Node find_middle(LinkedList.Node head){
        LinkedList.Node slow = head;
        LinkedList.Node fast = head;

        while(fast != null && fast.next != null){
            slow = slow.next; // +1
            fast = fast.next.next; // +2
        }
        return slow;
    }
    public static boolean check_Palindrome(LinkedList LL){
        if(LL.head == null || LL.head.next == null){
            return true;
        }

        //step 1 - find mid
        LinkedList.Node midNode = find_middle(LL.head);
        
        //step 2 - reverse 2nd half
        LinkedList.Node prev = null;
        LinkedList.Node curr = midNode;
        LinkedList.Node next = curr.next;

        while(curr != null){
            curr.next = prev;
            prev = curr;
            curr = next;
            if(next != null){
                next = next.next;
            }
        }
        LinkedList.Node right_head = prev;
        LinkedList.Node left_head = LL.head;

        //step 3 - check left half & right half
        while(right_head != null){
            if(left_head.data != right_head.data){
                return false;
            }
            left_head = left_head.next;
            right_head = right_head.next;
        }

        return true;
    }

    public static boolean detect_cycle(LinkedList.Node head){
        LinkedList.Node slow = head;
        LinkedList.Node fast = head;

        while(fast != null && fast.next != null){            
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast){
                return true;
            }
        }

        return false; // if loop breaks then no cycle
    }

    public static void remove_cycle(LinkedList.Node head){
        // step 1 : Detect Cycle
        LinkedList.Node slow = head;
        LinkedList.Node fast = head;
        boolean cycle = false; // cycle not exists

        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;

            // found middle point
            if(slow == fast){
                cycle = true; //cycle exists
                break;
            }
        }

        if(cycle == false){
            return;
        }
        
        // step 2.a : slow = head
        slow = head;
        LinkedList.Node prev = fast;

        // step 2.b : slow -> +1, fast -> +1
        while(slow != fast){
            prev = fast;
            slow = slow.next;
            fast = fast.next;
        }

        // previous is last node
        prev.next = null;
    }

    public static LinkedList.Node mergeSort(LinkedList.Node head){
        if(head == null || head.next == null){
            return head;
        }
        // step 1 : find mid node
        LinkedList.Node slow = head;
        LinkedList.Node fast = head.next; // to get mid in even in left half last node

        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        // slow is mid

        LinkedList.Node right_head = slow.next;
        slow.next = null; // Two Linked List

        LinkedList.Node left = mergeSort(head);
        LinkedList.Node right = mergeSort(right_head);

        return merge(left, right);
    }

    public static LinkedList.Node merge(LinkedList.Node head, LinkedList.Node right_head){

        LinkedList temp_LL = new LinkedList();
        LinkedList.Node merged_list = temp_LL.new_node(-1);
        LinkedList.Node temp_node = merged_list;

        while(head != null && right_head != null){
            if(head.data < right_head.data){
                temp_node.next = head;
                head = head.next;
                temp_node = temp_node.next;
            }else{
                temp_node.next = right_head;
                right_head = right_head.next;
                temp_node = temp_node.next;
            }
        }
        while(head != null){
            temp_node.next = head;
            head = head.next;
            temp_node = temp_node.next;
        }
        while(right_head != null){
            temp_node.next = right_head;
            right_head = right_head.next;
            temp_node = temp_node.next;
        }
         
        return merged_list.next;
    }

    public static void zig_zag_LL(LinkedList.Node head){
        // step 1 : find mid node
        LinkedList.Node slow = head;
        LinkedList.Node fast = head.next; // to get mid in even in left half last node

        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        // slow is mid
        LinkedList.Node mid = slow;
        LinkedList.Node curr = mid.next;
        mid.next = null;

        // step 2 : reverse second half
        LinkedList.Node prev = null;
        LinkedList.Node next;

        while(curr != null){
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
 
        LinkedList.Node left = head;
        LinkedList.Node right = prev;

        // step 3 : alternate merging
        LinkedList.Node next_left, next_right;

        while(left != null && right != null){
            next_left = left.next;
            left.next = right;
            next_right = right.next;
            right.next = next_left;

            left = next_left;
            right = next_right;
        }

        print_linked_list(head);

    }
}



class LinkedList {
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

    // Return single node
    public Node new_node(int data){
        return new Node(data);
    }


    // Linked List Methods
    public void add_at_first(int data){
        
        //step 1 : create new node
        size++;
        Node newNode = new Node(data);
        if(head == null){ //empty LL
            head = tail = newNode;
            return;
        }

        //step 2 : newnode's next = head
        newNode.next = head;

        //step 3 :  make newnode head
        head = newNode;
    }

    public void add_at_last(int data){
        
        //step 1 : create new node
        size++;
        Node newNode = new Node(data);
        if(head == null){ //empty LL
            head = tail = newNode;
            return;
        }

        //step 2 : newnode's next = tail
        tail.next = newNode;

        //step 3 :  make newnode tail
        tail = newNode;
    }

    public void add(int index, int data){
        if(index == 0){
            add_at_first(data);
            return;
        }

        Node newNode = new Node(data);
        Node prev = head;

        int idx = 0;
        while(idx != index-1){
            prev = prev.next;
            idx++;
        }

        if(prev.next == null){
            add_at_last(data);
            return;
        }

        size++;
        newNode.next = prev.next;
        prev.next = newNode;
    }

    public void print_LL(){
        Node temp = head;
        while(temp != null){
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
        System.out.println();
    }

    public int remove_at_first(){
        if(size == 0){
            System.out.println("Linked List is empty");
            return Integer.MIN_VALUE;
        }else if(size == 1){
            int val = head.data;
            head = tail = null;
            size = 0;
            return val;
        }
        int val = head.data;
        head = head.next;
        size--;
        return val;
    }

    public int remove_at_last(){
        if(size == 0){
            System.out.println("Linked List is empty");
            return Integer.MIN_VALUE;
        }else if(size == 1){
            int val = tail.data;
            head = tail = null;
            size = 0;
            return val;
        }

        //prev = index - 2
        Node prev = head;
        for(int i=0; i<size-2; i++){
            prev = prev.next;
        }

        int val = prev.next.data; // == tail.data
        prev.next = null;
        tail = prev;
        size--;
        return val;
    }
}


class Doubly_LinkedList {
    public class Node {
        int data;
        Node next;
        Node prev;

        public Node(int data){
            this.data = data;
            this.next = null;
            this.prev = null;
        }
    }

    public Node head;
    public Node tail;
    public int size;

    public void add_at_last(int data){
        Node newnode = new Node(data);
        if(size == 0){
            head = tail = newnode;
            size++;
            return;
        }

        tail.next = newnode;
        newnode.prev = tail;
        newnode.next = null;
        tail = newnode;
        size++;
    }

    public void add_at_first(int data){
        Node newnode = new Node(data);
        if(size == 0){
            head = tail = newnode;
            size++;
            return;
        }

        head.prev = newnode;
        newnode.next = head;
        head = newnode;
        size++;
    }

    public int remove_from_first(){
        if(size == 0){
            return Integer.MIN_VALUE;
        }

        int val = head.data;

        if(size == 1){            
            head = tail = null;
            return val;
        }
        Node temp = head;
        temp = head.next;
        head.next = null;
        temp.prev = null;
        head = temp;
        size--;

        return val;
    }

    public int remove_from_last(){
        if(size == 0){
            return Integer.MIN_VALUE;
        }

        int val = head.data;

        if(size == 1){            
            head = tail = null;
            return val;
        }

        Node temp = tail;
        temp = tail.prev;
        tail.prev = null;
        temp.next = null;
        tail = temp;
        size--;

        return val;
    }
    
    public void print_doubly_LL(){
        Node temp = head;
        while(temp != null){
            System.out.print(temp.data + " <-> ");
            temp = temp.next;
        }
        System.out.println();
    }

    public void reverse_doubly_LL(){
        Node prev = null;
        Node curr = head;
        Node next = curr.next;

        tail = curr;

        while(curr != null){
            next = curr.next;
            curr.next = prev;
            curr.prev = next;
            prev = curr;
            curr = next;
        }
        head = prev;
    }
}



class Circular_LinkedList {
    public class Node {
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

    public void add_at_front(int data){
        Node newNode = new Node(data);

        if(size == 0){
            head = tail = newNode;
            newNode.next = newNode;
            size++;
            return;
        }

        newNode.next = head;
        tail.next = newNode;
        head = newNode;
        size++;
    }

    public void add_at_last(int data){
        Node newNode = new Node(data);

        if(size == 0){
            head = tail = newNode;
            newNode.next = newNode;
            size++;
            return;
        }

        tail.next = newNode;
        newNode.next = head;
        tail = newNode;
        size++;
    }

    public void add_in_between(int data, int index){
        Node newNode = new Node(data);

        if(size == 0){
            head = tail = newNode;
            newNode.next = newNode;
            size++;
            return;
        }

        index = index % size;

        if(index == 0){
            add_at_front(data);
            return;
        }else if(index == (size-1)){
            add_at_last(data);
            return;
        }

        Node temp = head;
        while(index != 1){
            temp = temp.next;
            index--;
        }

        newNode.next = temp.next;
        temp.next = newNode;
        size++;
    }

    public void print_circular_LL(){
        if(size == 0){
            return;
        }

        Node temp = head;

        while(temp != tail){
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
        System.out.println(temp.data);
    }
}