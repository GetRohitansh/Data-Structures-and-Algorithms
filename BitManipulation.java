public class BitManipulation {

    /* 
    Topics Covered: 
     * Find if even or odd
     * Get ith bit
     * Set (1) ith bit
     * Clear (0) ith bit
     * Update ith bit (0/1)
     * clear (0) last i bits
     * clear (0) range of bits (i to j)
     * check if a number is power of two
     * count number of set (1) bits
     * Fast Exponention O(log₂n)
    */
    public static void main(String[] args) {
        evenOrOdd(19232);
        System.out.println("get ith bit (0-based index): "+get_ith_bit(10, 3)); // 1010
        System.out.println("set ith bit (0-based index): "+set_ith_bit(10, 2)); // 1110
        System.out.println("clear ith bit (0-based index): "+clear_ith_bit(10, 1)); // 1000
        System.out.println("update ith bit (0-based index): "+update_ith_bit(10, 2, 1)); // 1000
        System.out.println("clear last i bits (0-based index): "+clear_last_i_bit(15, 2)); // 1100
        System.out.println("clear range of bits (0-based index): "+clear_range_of_bits(2515, 2, 7)); // 1100
        System.out.println("Check if a number is power of 2: "+is_power_of_2(16));
        System.out.println("Count set bits: "+countSetBits(15));
        System.out.println("Fast Exponentiation: "+fastExpo(3, 5));
    }

    public static void evenOrOdd(int num){
        int bit = num & 1;
        String value = (bit == 0) ? "even" : "odd";
        System.out.println(value);
    }

    public static int get_ith_bit(int num, int i){
        return 1 & num>>i;
        // or 
        // int bitmask = 1<<i;
        // if((num & bitmask) == 0){
        //     return 0;
        // }else{
        //     return 1;
        // }
    }

    public static int set_ith_bit(int num, int i){
        return num | (1<<i);
    }

    public static int clear_ith_bit(int num, int i){
        return num & ~(1<<i);
    }

    public static int update_ith_bit(int num, int i, int newVal){
        if(newVal == 0){
            return num & ~(1<<i);
        }else{
            return num | (1<<i);
        }        
    }

    public static int clear_last_i_bit(int num, int i){
        return num & (~0<<i);
    }

    public static int clear_range_of_bits(int num, int i, int j){
        int till_j = num & (~0<<(j+1));
        int till_i = num & (~0<<(i+1));

        return num & (till_j | ~till_i);
    }

    public static boolean is_power_of_2(int num){
        return (num & (num-1)) == 0;
    }

    public static int countSetBits(int n){
        int count = 0;
        while(n>0){
            if((n & 1) != 0){
                count++;
            }
            n = n >> 1;
        }
        return count;
    }

    public static int fastExpo(int a, int n){
        int ans = 1;
        while(n > 0){
            if((n & 1)!=0){
                ans = ans * a;
            }
            a = a * a;
            n = n >> 1;
        }
        return ans;
    }
}

