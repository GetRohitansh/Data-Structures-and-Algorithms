import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.ArrayList;

public class BinaryTreeLecture {
    /* 
    Topics Covered: 
     * Binary Tree
     * Build Binary Tree (pre-order)
     * Pre-Order Traversal
     * In-Order Traversal
     * Post-Order Traversal
     * Level-Order Traversal (print in steps, print in single line)
     * Height of the Tree
     * Count of nodes of the Tree
     * sum of nodes of the Tree
     * Diameter of Tree
     * Diameter of Tree (optimized)
     * Subtree of Another Tree
     * Top View of the Tree
     * Kth Level of Tree Iteration
     * Kth Level of Tree Recursion
     * Lowest Common Ancestor(Approach 1)
     * Lowest Common Ancestor(Approach 2)
     * Minimum Distance Between Nodes
     * Kth Ancestor of node
     * Transform to Sum Tree
    */
    public static void main(String[] args) {
        BinaryTree binarytree = new BinaryTree();
        BinaryTree.Node root = binarytree.build_tree(new int[] { 1, 2, 4, -1, -1, 5, -1, -1, 3, -1, 6, -1, -1 });
        System.out.println("Create a pre-order binary tree: " + root.data);
        System.out.println("Pre-order traversal: ");
        binarytree.pre_order_print(root);
        System.out.println("\nIn-order traversal: ");
        binarytree.in_order_print(root);
        System.out.println("\nPost-order traversal: ");
        binarytree.post_order_print(root);
        System.out.println("\nLevel-order traversal: ");
        binarytree.level_order_print(root);
        binarytree.level_order_nextline_print(root);
        System.out.println("Height of the Tree: " + binarytree.height_of_tree(root));
        System.out.println("Count of nodes of the Tree: " + binarytree.count_of_nodes(root));
        System.out.println("sum of nodes of the Tree: " + binarytree.sum_of_nodes(root));
        System.out.println("Diameter of Tree: " + binarytree.diameter_of_tree(root));
        System.out.println("Diameter of Tree (optimized): " + binarytree.diameter_of_tree_optimized(root).diameter);
        BinaryTree binarySubTree = new BinaryTree();
        BinaryTree.Node subtree_root = binarySubTree.build_tree(new int[] { 3, -1, 6, -1, -1 });
        System.out.println("Subtree of Another Tree: " + subtree_of_another_tree(root, subtree_root));
        BinaryTree binaryTree = new BinaryTree();
        BinaryTree.Node root_new = binaryTree.build_tree(new int[] { 1,2,-1,4,-1,5,-1,6,-1,-1,3,-1,-1 });
        System.out.println("Top View of the Tree: ");
        top_view_of_tree(root_new);
        System.out.print("Kth Level of Tree Iteration: ");
        kth_level_tree_iteration(root_new, 5);
        System.out.print("\nKth Level of Tree Recursion: ");
        kth_level_tree_recursion(root_new, 1, 5);
        System.out.println("\nLowest Common Ancestor(Approach 1): "+lowest_common_ancestor_approach_1(root, 4, 5).data);
        System.out.println("Lowest Common Ancestor(Approach 2): "+lowest_common_ancestor_approach_2(root, 4, 5).data);
        System.out.println("Minimum Distance Between Nodes: "+minimum_distance_between_two_nodes_implementation_1(root, 4, 6));
        System.out.println("Kth Ancestor of node: "+kth_ancestor_of_node(root, 4, 2));
        System.out.println("Transform to Sum Tree: "+transform_to_sum_tree(root));
        System.out.println("Inorder traversal of sum tree: ");
        binaryTree.level_order_nextline_print(root);
    }

    public static boolean subtree_of_another_tree(BinaryTree.Node root_tree, BinaryTree.Node root_subtree) {
        if (root_tree == null && root_subtree == null) {
            return true;
        }
        if (root_tree == null || root_subtree == null) {
            return false;
        }

        if (root_tree.data == root_subtree.data) {
            return check_two_tree_same(root_tree, root_subtree);
        } else {// check for subtree
            return subtree_of_another_tree(root_tree.left, root_subtree)
                    || subtree_of_another_tree(root_tree.right, root_subtree); // check if ans exist in either left or
                                                                               // right subtree
        }
    }

    public static boolean check_two_tree_same(BinaryTree.Node root1, BinaryTree.Node root2) {
        if (root1 == null && root2 == null) {
            return true;
        }
        if (root1 == null || root2 == null) {
            return false;
        }

        if (root1.data == root2.data) {
            return check_two_tree_same(root1.left, root2.left) && check_two_tree_same(root1.right, root2.right); // if 
                                                                                                                 // both
                                                                                                                 // true
                                                                                                                 // return
                                                                                                                 // true
        } else {
            return false;
        }
    }

    static class Node_Info {
        BinaryTree.Node node;
        int horizontal_distance;

        Node_Info(BinaryTree.Node node, int horizontal_distance) {
            this.node = node;
            this.horizontal_distance = horizontal_distance;
        }
    }
    public static void top_view_of_tree(BinaryTree.Node root) {

        Queue<Node_Info> queue = new LinkedList<>();
        HashMap<Integer, BinaryTree.Node> map = new HashMap<>(); // (Horizontal Distance, Node Value)

        int min = 0, max = 0;
        queue.add(new Node_Info(root, 0));
        queue.add(null);

        while (!queue.isEmpty()) {

            Node_Info current = queue.remove();

            if (current == null) {
                if (queue.isEmpty()) {
                    break;
                } else {
                    queue.add(null);
                }
            } else {

                // first time storing
                if (!map.containsKey(current.horizontal_distance)) {
                    map.put(current.horizontal_distance, current.node);
                }

                // add left and right children of node to queue
                if (current.node.left != null) {
                    queue.add(new Node_Info(current.node.left, current.horizontal_distance - 1));
                    min = Math.min(min, current.horizontal_distance - 1);
                }

                if (current.node.right != null) {
                    queue.add(new Node_Info(current.node.right, current.horizontal_distance + 1));
                    max = Math.max(max, current.horizontal_distance + 1);
                }
            }
        }

        for (int i = min; i <= max; i++) {
            System.out.print(map.get(i).data + " ");
        }
        System.out.println();
    }

    public static void kth_level_tree_iteration(BinaryTree.Node root, int level){
        Queue<BinaryTree.Node> queue = new LinkedList<>();
        
        queue.add(null);
        queue.add(root);        

        int level_track = 1;
        while(!queue.isEmpty()){
            BinaryTree.Node current = queue.remove();
            if(current == null){
                if(level_track == level){
                    while(queue.peek() != null){
                        System.out.print(queue.remove().data + " ");
                    }
                    break;
                }else{
                    level_track++;
                    queue.add(null);
                }
            } else {
                if(current.left != null){
                    queue.add(current.left);
                }
                if(current.right != null){
                    queue.add(current.right);
                }
            }
        }
    }
    public static void kth_level_tree_recursion(BinaryTree.Node root, int current_level, int level){
        if(root == null || current_level > level){
            return;
        }
        if(current_level == level){
            System.out.print(root.data + " ");
            return;
        }
        kth_level_tree_recursion(root.left, current_level+1, level);
        kth_level_tree_recursion(root.right, current_level+1, level);
    }

    public static BinaryTree.Node lowest_common_ancestor_approach_1(BinaryTree.Node root, int n1, int n2){

        ArrayList<BinaryTree.Node> path1 = new ArrayList<>();
        ArrayList<BinaryTree.Node> path2 = new ArrayList<>();

        get_path_root_node(root, n1, path1);        
        get_path_root_node(root, n2, path2);
        path1.add(root);
        path2.add(root);

        for(int i=0; i<path1.size() && i<path2.size(); i++){
            if(path1.get(i) == path2.get(i)){
                return path1.get(i);
            }
        }        

        return null;
    }
    public static boolean get_path_root_node(BinaryTree.Node root, int node, ArrayList<BinaryTree.Node> path){
        if(root == null){
            return false;
        }

        // if node n1 is found, add to path
        if(root.data == node){
            path.add(root);
            return true;
        }
        
        // if path exist through this node, then return true
        if (get_path_root_node(root.left, node, path) || get_path_root_node(root.right, node, path)) {
            path.add(root);
        }

        // if path does not exist through this node, return false
        return false;
    }

    public static BinaryTree.Node lowest_common_ancestor_approach_2(BinaryTree.Node root, int n1, int n2){
        if(root == null){
            return null;
        }

        if(root.data == n1 || root.data == n2){
            return root;
        }

        BinaryTree.Node left_LCA = lowest_common_ancestor_approach_2(root.left, n1, n2);
        BinaryTree.Node right_LCA = lowest_common_ancestor_approach_2(root.right, n1, n2);

        if(left_LCA == null){
            return right_LCA;
        }

        if(right_LCA == null){
            return left_LCA;
        }

        return root;
    }  
    
    public static int minimum_distance_between_two_nodes_implementation_1(BinaryTree.Node root, int n1, int n2){
        BinaryTree.Node lowest_common_ancestor = lowest_common_ancestor_approach_2(root, n1, n2);
        int distance_from_n1 = distance_between_nodes(lowest_common_ancestor, n1, 0);
        int distance_from_n2 = distance_between_nodes(lowest_common_ancestor, n2, 0);
        return distance_from_n1 + distance_from_n2;
    }
    public static int distance_between_nodes(BinaryTree.Node root, int value, int level){
        if(root == null){
            return 0;
        }
        if(root.data == value){
            return level;
        }

        int result = distance_between_nodes(root.left, value, level+1); // finds first in left
        if(result != 0){ // if ans is not zero return ans
            return result;
        } // else node is from right
        result = distance_between_nodes(root.right, value, level+1);
        return result;
    }

    public static int kth_ancestor_of_node(BinaryTree.Node root, int n, int k){
        if(root == null) {
            return -1;
        }
        
        if(root.data == n){
            return 0;
        }

        int leftDist = kth_ancestor_of_node(root.left, n, k);
        int rightDist = kth_ancestor_of_node(root.right, n, k);

        if(leftDist == -1 && rightDist == -1){
            return -1;
        }

        int max = Math.max(leftDist, rightDist);
        if(max+1 == k){
            System.out.println("Value of Ancestor (Kth Ancestor of Node): "+root.data);
        }
        return max+1;
    }

    public static int transform_to_sum_tree(BinaryTree.Node root){
        if(root == null){
            return 0;
        }

        int left_subtree = transform_to_sum_tree(root.left);
        int right_subtree = transform_to_sum_tree(root.right);        
        int sum = left_subtree + right_subtree + root.data;

        root.data = sum - root.data;
        return sum;
    }
}

class BinaryTree {
    class Node {
        int data;
        Node left;
        Node right;

        Node(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    int idx = -1;

    public Node build_tree(int nums[]) {
        idx++;
        if (nums[idx] == -1) {
            return null;
        }
        Node newnode = new Node(nums[idx]);
        newnode.left = build_tree(nums);
        newnode.right = build_tree(nums);

        return newnode;
    }

    public void pre_order_print(Node root) {
        if (root == null) {
            return;
        }

        System.out.print(root.data + " ");
        pre_order_print(root.left);
        pre_order_print(root.right);
    }

    public void in_order_print(Node root) {
        if (root == null) {
            return;
        }

        in_order_print(root.left);
        System.out.print(root.data + " ");
        in_order_print(root.right);
    }

    public void post_order_print(Node root) {
        if (root == null) {
            return;
        }

        post_order_print(root.left);
        post_order_print(root.right);
        System.out.print(root.data + " ");
    }

    public void level_order_print(Node root) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node node = queue.remove();
            System.out.print(node.data + " ");
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }
        System.out.println();
    }

    public void level_order_nextline_print(Node root) {
        System.out.println();
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        queue.add(null);
        while (!queue.isEmpty()) {
            Node node = queue.remove();
            if (node == null) {
                if (queue.isEmpty()) {
                    break;
                }
                System.out.println();
                queue.add(null);
            } else {
                System.out.print(node.data + " ");
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
        }
        System.out.println();
    }

    public int height_of_tree(Node root) {
        if (root == null) {
            return 0;
        }

        int left = height_of_tree(root.left);
        int right = height_of_tree(root.right);

        return Math.max(left, right) + 1;
    }

    public int count_of_nodes(Node root) {
        if (root == null) {
            return 0;
        }

        int left = count_of_nodes(root.left);
        int right = count_of_nodes(root.right);

        return left + right + 1;
    }

    public int sum_of_nodes(Node root) {
        if (root == null) {
            return 0;
        }

        int left = sum_of_nodes(root.left);
        int right = sum_of_nodes(root.right);

        return left + right + root.data;
    }

    public int diameter_of_tree(Node root) {
        if (root == null) {
            return 0;
        }
        // passes through root (Left Height + Right Height + 1)
        int self_diameter = height_of_tree(root.left) + height_of_tree(root.right) + 1;

        // does not passes through root
        int left_diameter = diameter_of_tree(root.left);
        int right_diameter = diameter_of_tree(root.right);

        return Math.max(self_diameter, Math.max(left_diameter, right_diameter));
    }

    public Details diameter_of_tree_optimized(Node root) {
        if (root == null) {
            return new Details(0, 0);
        }
        // passes through root (Left Height + Right Height + 1)
        int left_height = diameter_of_tree_optimized(root.left).height;
        int right_height = diameter_of_tree_optimized(root.right).height;
        int self_diameter = left_height + right_height + 1;

        // does not passes through root
        int left_diameter = diameter_of_tree_optimized(root.left).diameter;
        int right_diameter = diameter_of_tree_optimized(root.right).diameter;

        return new Details(Math.max(self_diameter, Math.max(left_diameter, right_diameter)),
                Math.max(left_height, right_height) + 1);
    }

    class Details {
        int diameter;
        int height;

        Details(int diameter, int height) {
            this.diameter = diameter;
            this.height = height;
        }
    }

}
