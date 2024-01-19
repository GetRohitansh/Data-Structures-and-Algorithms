import java.util.LinkedList;
import java.util.Queue;

public class AVLTrees {

    /* 
    Topics Covered: 
     * Insert in AVL
     * All cases (LL, LR, RR, RL)
    */
    static class Node {
        int data, height;
        Node left, right;

        Node(int data){
            this.data = data;
            this.height = 1;
        }
    }

    public static int height(Node root){
        if (root == null){
            return 0;
        }

        return root.height;
    }

    public static int getBalance(Node root){
        if(root == null){
            return 0;
        }
        return height(root.left) - height(root.right);
    }

    public static Node rotateLeft(Node x){
        Node y = x.right;
        Node T2 = y.left;

        // perform rotation
        y.left = x;
        x.right = T2;

        // update height
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        // Return new root
        return y;
    }

    public static Node rotateRight(Node y){
        Node x = y.left;
        Node T2 = x.right;

        // perform rotation
        x.right = y;
        y.left = T2;

        // update height
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        // Return new root
        return x;
    }

    public static Node insert(Node root, int key){
        if(root == null){
            return new Node(key);
        }

        if(key < root.data){
            root.left = insert(root.left, key);
        }else if(key > root.data){
            root.right = insert(root.right, key);
        }else {
            return root; // Duplicate value is not allowed
        }

        root.height = 1 + Math.max(height(root.left), height(root.right));

        // Get root's balance factor
        int balanceFactor = getBalance(root);

        // Left Left case
        if(balanceFactor > 1 && key < root.left.data){
            return rotateRight(root);
        }

        //Right Right case
        if(balanceFactor < -1 && key > root.right.data){
            return rotateLeft(root);
        }

        //Left Right case
        if(balanceFactor > 1 && key > root.left.data){
            root.left = rotateLeft(root.left);
            return rotateRight(root);
        }

        //Right Left case
        if(balanceFactor < -1 && key < root.right.data){
            root.right = rotateRight(root.right);
            return rotateLeft(root);
        }

        return root;
    }

    public static void level_order_traversal(Node root){
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        queue.add(null);

        while(!queue.isEmpty()){
            Node current = queue.remove();
            if(current == null){
                if(queue.isEmpty()){
                    break;
                }
                System.out.println();
                queue.add(null);
            }else{
                System.out.print(current.data + " ");
                if(current.left != null){
                    queue.add(current.left);
                }
                if(current.right != null){
                    queue.add(current.right);
                }
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        // AVL Trees are self balancing trees
        Node root = new Node(40);
        root = insert(root, 20);
        root = insert(root, 10);
        root = insert(root, 25);
        root = insert(root, 30);
        root = insert(root, 22);
        root = insert(root, 50);

        level_order_traversal(root);
    }
}
