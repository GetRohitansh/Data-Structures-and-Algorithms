public class Recursion1 {
    /* 
    Topics Covered: 
     * Factorial of a number
     * Sum of N natural numbers 
     * Nth fibonacci number
     * Array is sorted (2 methods)
     * First Occurance of number in Array
     * Last Occurance of number in Array (2 methods)
     * X to the power of N O(n)
     * X to the power of N optimized O(logN)
    */
    public static void main(String[] args) {
        print_number_n_1_dec(10);
        System.out.println();
        print_number_n_1_inc(10);
        System.out.println();
        System.out.println("Factorial of a number: "+factorial(5));
        System.out.println("Sum of N natural numbers: "+sum_of_n_natural(5));
        System.out.println("Nth fibonacci number: "+nth_fibonacci(5));
        System.out.println("Array is sorted: "+sorted_ascending(new int[]{1,2,4,3,5}, 0));
        System.out.println("Array is sorted: "+sorted_ascending_2(new int[]{1,2,4,5}, 0));
        System.out.println("First Occurance: "+first_occurence(new int[]{8,6,4,41,4,3,4,7,5,3,5}, 5, 0));
        System.out.println("Last Occurance: "+last_occurence(new int[]{8,6,4,41,4,3,4,7,5,3,5}, 5, 0));
        System.out.println("Last Occurance 2: "+last_occurence2(new int[]{8,6,4,41,4,3,4,7,5,3,5}, 5, 10));
        System.out.println("X to the power of N: "+x_to_power_n(2, 10));
        System.out.println("X to the power of N (optimized): "+x_to_power_n_optimized(2, 25));
    }   

    public static void print_number_n_1_dec(int n){
        if(n==0){
            return;
        }
        System.out.print(n+" ");
        print_number_n_1_dec(n-1);
    }

    public static void print_number_n_1_inc(int n){
        if(n==0){
            return;
        }
        print_number_n_1_inc(n-1);
        System.out.print(n+" ");        
    }

    public static int factorial(int n){
        if(n==0){
            return 1;
        }

        return n * factorial(n-1);
    }

    public static int sum_of_n_natural(int n){
        if(n==1){
            return 1;
        }

        return n + sum_of_n_natural(n-1);
    }

    public static int nth_fibonacci(int n){
        if(n<=1){
            return n;
        }

        int nth = nth_fibonacci(n-1) + nth_fibonacci(n-2);
        return nth;
    }

    public static boolean sorted_ascending(int arr[], int i){
        if(i==arr.length-1){
            return true;
        }

        boolean sorted = arr[i] < arr[i+1];
        return sorted & sorted_ascending(arr, i+1);
    }

    public static boolean sorted_ascending_2(int arr[], int i){
        if(i == arr.length-1){
            return true;
        }


        if(arr[i]>arr[i+1]){
            return false;
        }
        return sorted_ascending_2(arr, i+1);
    }

    public static int first_occurence(int arr[], int key, int i){
        if(i==arr.length){
            return -1;
        }

        if(arr[i]==key){
            return i;
        }

        return first_occurence(arr, key, i+1);
    }

    public static int last_occurence(int arr[], int key, int i){
        if(i == arr.length){
            return -1;
        }

        int last = last_occurence(arr, key, i+1);
        if(arr[i] == key && last==-1) {
            return i;
        }

        return last;
    }

    public static int last_occurence2(int arr[], int key, int i){
        if(i==-1){
            return -1;
        }

        if(arr[i]==key){
            return i;
        }

        return first_occurence(arr, key, i-1);
    }

    public static int x_to_power_n(int x, int n){
        if(n == 0){
            return 1;
        }

        return x * x_to_power_n(x, n-1);
    }

    public static int x_to_power_n_optimized(int x, int n){
        if(n == 0){
            return 1;
        }

        int power = x_to_power_n_optimized(x, n/2);

        if(n%2 == 0){
            return  power * power ;
        }else{
            return x * power * power;
        }
    }
}
