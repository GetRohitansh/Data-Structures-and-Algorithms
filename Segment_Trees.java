import java.util.Arrays;

public class Segment_Trees {
    public static void main(String args[]){
       
    /* 
    Topics Covered:
     * Segment Tree (Maximum Subarray Sum) -> Creating, Querying, Updating
     * Segment Tree (Maximum Element) -> Creating, Querying, Updating
    */

        System.out.println("For Maximum Subarray Sum ---> ");

        int arr[] = {1,2,3,4,5,6,7,8,9};
        int tree[] = new int[4*arr.length];
        build_segment_tree_subarray_sum(arr, 0, 0, arr.length-1, tree);
        System.out.println("Build Segment Tree: "+Arrays.toString(tree));
        System.out.println("Get Sum query: "+get_sum(arr, 2, 5, tree));
        update_subarray_sum(arr, 2, 2, tree);
        System.out.println("After Updation of Array, Segment Tree: "+Arrays.toString(tree));
        update_subarray_sum(arr, 3, 3, tree);
        System.out.println("After Updation of Array, Segment Tree: "+Arrays.toString(tree));
        System.out.println("Get Sum query: "+get_sum(arr, 3, 3, tree));



        System.out.println("For Maximum Element ---> ");
        int arr2[] = {6,8,-1,2,17,1,3,2,4,10,11,12};
        int tree2[] = new int[4*arr2.length];
        build_segment_tree_max_element(arr2, tree2, 0, 0, arr2.length-1);
        System.out.println("Build Segment Tree: "+Arrays.toString(tree2));
        System.out.println("Get Maximum Element: "+get_max_element(tree2, arr2, 2, 4));
        update_maximum_element(arr2, tree2, 2, 3);
        System.out.println("After Updation of Array, Segment Tree: "+Arrays.toString(tree2));
        System.out.println("Get Maximum Element: "+get_max_element(tree2, arr2, 2, 2));
    }

    public static void build_segment_tree_subarray_sum(int arr[], int i, int s, int e, int tree[]){
        if(s == e){
            tree[i] = arr[s];
            return;
        }

        int m = s + (e-s)/2;
        build_segment_tree_subarray_sum(arr, 2*i+1, s, m, tree);
        build_segment_tree_subarray_sum(arr, 2*i+2, m+1, e, tree);

        tree[i] = tree[2*i+1] + tree[2*i+2] ;
    }

    public static int get_sum(int arr[], int qi, int qj, int tree[]){
        return get_sum_util(0, 0, arr.length-1, qi, qj, tree);
    }
    public static int get_sum_util(int i, int si, int sj, int qi, int qj, int tree[]){

        // case 1 : not overlapping (not included)
        if(si > qj || sj < qi){
            return 0;
        } else if(si >= qi && sj <= qj){ // case 2 : complete overlap
            return tree[i];
        } else { // case 3 : complete overlap
            int mid = si + (sj-si)/2;
            int left =  get_sum_util(2*i+1, si, mid, qi, qj, tree);
            int right =  get_sum_util(2*i+2, mid+1, sj, qi, qj, tree);

            return left + right;
        }
    }

    public static void update_subarray_sum(int arr[], int idx, int new_val, int tree[]){
        int diff = new_val - arr[idx];

        update_tree_subarray_sum_segment_tree(tree, 0 , 0, arr.length-1, idx, diff, arr);
        arr[idx] = new_val;
    }

    public static void update_tree_subarray_sum_segment_tree(int tree[], int i, int si, int sj , int idx, int diff, int arr[]){
        if(si == sj){ // if leaf return
            if(tree[i] == arr[idx])
                tree[i] = tree[i] + diff;
            return;
        }

        if(idx >= si && idx <= sj){
            tree[i] += diff;
        }

        int mid = si + (sj-si)/2;
        update_tree_subarray_sum_segment_tree(tree, 2*i+1, si, mid, idx, diff, arr);
        update_tree_subarray_sum_segment_tree(tree, 2*i+2, mid+1, sj, idx, diff, arr);
    }


    public static void build_segment_tree_max_element(int arr[], int tree[], int i, int s, int e){
        if(s == e){
            tree[i] = arr[s];
            return;
        }

        int m = s + (e-s)/2;

        build_segment_tree_max_element(arr, tree, 2*i+1, s, m);
        build_segment_tree_max_element(arr, tree, 2*i+2, m+1, e);

        tree[i] = Math.max(tree[2*i+1], tree[2*i+2]);
    }

    public static int get_max_element(int tree[], int arr[], int qi, int qj){
        int n = arr.length; // last index of tree
        return get_max_element_util(tree, 0, 0, n-1, arr, qi, qj);
    }

    public static int get_max_element_util(int tree[], int i, int si, int sj, int arr[], int qi, int qj){
        
        if(si > qj || sj < qi){// case 1 : not overlap

            return Integer.MIN_VALUE;
        }else if(si >= qi && sj <= qj){ // case 2 : complete overlap

            return tree[i];
        }else{ // case 3 : partial overlap

            int m = si + (sj - si)/2;
            int left = get_max_element_util(tree, 2*i + 1, si, m, arr, qi, qj);
            int right = get_max_element_util(tree, 2*i + 2, m + 1, sj, arr, qi, qj);

            return Math.max(left, right);
        } 
    }

    public static void update_maximum_element(int arr[], int tree[], int idx, int new_val){
        update_tree_maximum_element_segment_tree(tree, 0, 0, arr.length-1, idx, new_val, arr);
        arr[idx] = new_val;
    }

    public static void update_tree_maximum_element_segment_tree(int tree[], int i, int si, int sj, int idx, int new_val, int arr[]){
        if(si == sj){
            if(tree[i] == arr[idx])
                tree[i] = new_val;
            return;
        }

        if(si <= idx && idx <= sj){
            tree[i] = Math.max(new_val, tree[i]);
            int m = si + (sj - si)/2;
            update_tree_maximum_element_segment_tree(tree, 2*i+1, si, m, idx, new_val, arr);
            update_tree_maximum_element_segment_tree(tree, 2*i+2, m+1, sj, idx, new_val, arr);
        }
    }

    public static void update_tree_leaf_nodes(int tree[], int i, int si, int sj, int idx, int new_val){

    }
}
