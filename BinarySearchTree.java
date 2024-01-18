import java.util.ArrayList;
import java.util.Collections;

public class BinarySearchTree {
    /* 
    Topics Covered:
     * Build BST 
     * Inorder Traversal
     * Search in BST
     * Delete Node in BST
     * Print in Range
     * Root to Leaf all paths
     * Validate a BST
     * Mirror a BST
     * Build a Balanced BST from sorted array
     * Convert BST to Balanced BST
     * Size of largest BST in BT
     * Merge Two BST     
    */
    public static void main(String args[]){
        int values[] = {8,5,3,1,4,6,10,11,14};
        BST bst = new BST();
        BST.Node root = bst.buildBST(values);
        System.out.println("Inorder Traversal: ");
        bst.inorder_traversal(root);
        System.out.println("\nSearch in BST: "+search_in_BST(root, 10));
        root = bst.delete_node(root,5);
        bst.inorder_traversal(root);
        System.out.println("\nPrint in range: "); print_in_range(root, 5, 12);
        System.out.println("Root to Leaf Path: "); root_to_leaf_path(root, new ArrayList<>());
        System.out.println("Validate BST: "+validate_BST(root, null, null)); // works for both true, false
        System.out.println("Mirror a BST: ");   bst.inorder_traversal(mirror_A_BST(root));
        System.out.println();
        System.out.println("Build a Balanced BST from sorted array: ");
        int[] sorted_array = {3,5,6,8,10,11,12};
        
        BST.Node root2 = bst.build_balanced_BST_from_sorted_array(sorted_array, 0, sorted_array.length-1);
        bst.inorder_traversal(root2); System.out.println();

        int values3[] = {8,5,3,1,4,6,10,11,14};

        BST.Node root3 = bst.buildBST(values3);
        System.out.println("Convert BST to Balanced BST: ");
        root3 = convert_BST_to_Balanced_BST(root3, bst);
        bst.inorder_traversal(root3); System.out.println();


        /*
                                50
                             /      \
                            30       60
                          /    \   /    \
                         5     20 45    70
                                       /  \
                                      65   80
         */
        BST.Node root4 = bst.newNode(50);
        root4.left = bst.newNode(30);
        root4.left.left = bst.newNode(5);
        root4.left.right = bst.newNode(20);

        root4.right = bst.newNode(60);
        root4.right.left = bst.newNode(45);
        root4.right.right = bst.newNode(70);
        root4.right.right.left = bst.newNode(65);
        root4.right.right.right = bst.newNode(80);
        
        System.out.println("Size of largest BST in BT: "+size_of_largest_BST_in_BT(root4).max_BST_size);
        System.out.println("Merge Two BST: ");
        BST.Node root_1 = bst.newNode(2);
        root_1.left = bst.newNode(1);
        root_1.right = bst.newNode(4);

        BST.Node root_2 = bst.newNode(9);
        root_2.left = bst.newNode(3);
        root_2.right = bst.newNode(12);
        bst.inorder_traversal(merge_two_BST(root_1, root_2));
    }

    public static boolean search_in_BST(BST.Node root, int key){ // O(H)
        if(root == null){
            return false;
        }

        if(root.data == key){
            return true;
        }

        if(root.data > key){
            return search_in_BST(root.left, key);
        }else{
            return search_in_BST(root.right, key);
        }
    }

    public static void print_in_range(BST.Node root, int k1, int k2){
        if(root == null){
            return;
        }
        if(root.data >= k1 && root.data <= k2){
            print_in_range(root.left, k1, k2);
            System.out.print(root.data + " ");
            print_in_range(root.right, k1, k2);
        }else if(root.data < k1){
            print_in_range(root.left, k1, k2);
        }else if(root.data > k2){
            print_in_range(root.right, k1, k2);
        }
    }

    public static void root_to_leaf_path(BST.Node root, ArrayList<Integer> path){
        if(root == null){
            return;
        }

        path.add(root.data);

        if(root.right == null && root.left == null){
            System.out.println("    "+path);
        }
        
        root_to_leaf_path(root.left, path);
        root_to_leaf_path(root.right, path);
        path.remove(path.size() - 1); // backtrack (remove last node from path)
    }

    public static boolean validate_BST(BST.Node root, BST.Node min, BST.Node max){
        if(root == null){
            return true;
        }

        // check if node in valid range (checks both min and max : if, else if)
        if(min != null && root.data <= min.data){
            return false;
        }else if(max != null && root.data >= max.data){
            return false;
        }

        boolean left = validate_BST(root.left, min, root);
        boolean right = validate_BST(root.right, root, max);

        return left && right;
    }

    public static BST.Node mirror_A_BST(BST.Node root){
        if(root == null){
            return null;
        }

        
        BST.Node temp = root.right;
        root.right = root.left;
        root.left = temp;

        root.left = mirror_A_BST(root.left);
        root.right = mirror_A_BST(root.right);

        return root;
    }

    public static BST.Node convert_BST_to_Balanced_BST(BST.Node root, BST bst){
        ArrayList<Integer> list = inorder_traversal_list(root, new ArrayList<>());
        BST.Node root_new = bst.build_Balanced_BST(list, 0, list.size()-1);
        return root_new;
    }
    public static ArrayList<Integer> inorder_traversal_list(BST.Node root, ArrayList<Integer> nums){
        if(root == null){
            return nums;
        }

        inorder_traversal_list(root.left, nums);
        nums.add(root.data);
        inorder_traversal_list(root.right, nums);

        return nums;
    }

    public static class Info{
        boolean isBST;
        int size;
        int min;
        int max;
        int max_BST_size;

        public Info(boolean isBST, int size, int min, int max, int max_BST_size){
            this.isBST = isBST;
            this.size = size;
            this.min = min;
            this.max = max;
            this.max_BST_size = max_BST_size;
        }
    }

    // public static int maxBST = 0;
    public static Info size_of_largest_BST_in_BT(BST.Node root){
        if(root == null){
            return new Info(true, 0, Integer.MAX_VALUE, Integer.MIN_VALUE, 0);
        }
        

        Info left = size_of_largest_BST_in_BT(root.left);
        Info right = size_of_largest_BST_in_BT(root.right);

        int size = left.size + right.size + 1;
        int minimum = Math.min(root.data, Math.min(left.min, right.min));
        int maximum = Math.max(root.data, Math.max(left.max, right.max));
        int maxBST = Math.max(left.max_BST_size, right.max_BST_size);

        //if root is less than max value of left subtree or root is greater than min of right subtree return false
        if(root.data <= left.max || root.data >= right.min){
            return new Info(false, size, minimum, maximum, maxBST);
        }

        if(left.isBST && right.isBST){
            maxBST = Math.max(maxBST, size); // if only both subtree are BST then calc, max BST height
            return new Info(true, size, minimum, maximum, maxBST);
        }

        return new Info(false, size, minimum, maximum, maxBST);
    }

    public static BST.Node merge_two_BST(BST.Node root1, BST.Node root2){
        BST bst = new BST();
        ArrayList<Integer> list = new ArrayList<>();
        inorder_traversal_list(root1, list);
        inorder_traversal_list(root2, list);

        Collections.sort(list);
        BST.Node root = bst.build_Balanced_BST(list, 0, list.size()-1);
        return root;
    }
}

class BST{
    class Node {
        int data;
        Node left;
        Node right;

        Node(int data){
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    public Node newNode(int data){
        return new Node(data);
    }

    public Node buildBST(int values[]){
        Node root = null;
        for (int i : values) {
            root = insert(root, i);
        }

        return root;
    }

    public Node insert(Node root, int val){
        if(root == null){
            root =  new Node(val);
            return root;
        }

        if(root.data > val){
            //left subtree
            root.left = insert(root.left, val);
        }else {
            root.right = insert(root.right, val);
        }

        return root;
    }

    public void inorder_traversal(Node root){
        if(root == null){
            return;
        }

        inorder_traversal(root.left);
        System.out.print(root.data + " ");
        inorder_traversal(root.right);
    }

    public Node delete_node(Node root, int val){
        if(root == null) {
            return null;
        }

        if(root.data > val){
            root.left = delete_node(root.left, val);
        }else if(root.data < val){
            root.right = delete_node(root.right, val);
        }else{ // node to delete
            //case 1
            if(root.left == null && root.right == null){
                return null;
            } 

            //case 2
            if(root.left == null){
                return root.right;
            }else if(root.right == null){
                return root.left;
            }

            //case 3
            Node successor = inorder_successor(root.right);
            root.data = successor.data;
            root.right = delete_node(root.right, successor.data);
        }
        return root;        
    }
    public Node inorder_successor(Node root){ // here root is root.right of parent
        while(root.left != null){
            root = root.left;
        }
        return root;
    }

    public Node build_balanced_BST_from_sorted_array(int[] values, int start, int end){
        if(start > end){
            return null;
        }

        int mid = start + (end - start)/2;
        Node root = new Node(values[mid]);
        root.left = build_balanced_BST_from_sorted_array(values, start, mid-1);
        root.right = build_balanced_BST_from_sorted_array(values, mid+1, end);

        return root;
    }

    public Node build_Balanced_BST(ArrayList<Integer> nums, int start, int end){
        if(start > end){
            return null;
        }

        int mid = start + (end - start)/2;
        Node root = new Node(nums.get(mid));
        root.left = build_Balanced_BST(nums, start, mid-1);
        root.right = build_Balanced_BST(nums, mid+1, end);

        return root;
    }
}
