public class Strings {

    /* 
    Topics Covered: 
     * concatenation
     * palindrome string
     * Shortest path
     * String compare
     * Substring
     * largest string
     * string builder
     * capitalize a sentence
     * string compress
    */
    public static void main(String[] args) {
        
        String str = "ABCD";
        String str2 = new String("XYZ");
        System.out.println("Contatenation: "+str+str2);

        System.out.println("String Palindrome: "+palindromeString("madam"));
        System.out.println("Shortest distance: "+shortestPath("WNEENESENNN"));

        System.out.println("String Compare: ");
        String s1 = "Tony";
        String s2 = "Tony"; // s1 and s2 reference to same varible
        String s3 = new String("Tony");

        if(s1 == s2){
            System.out.println("\tString are equal");
        }else{
            System.out.println("\tString are not equal");
        }

        //this checks if reference are pointing to same variable
        if(s1 == s3){
            System.out.println("\tString are equal");
        }else{
            System.out.println("\tString are not equal");
        }

        //this checks if values are same
        if(s1.equals(s3)){
            System.out.println("\tString are equal");
        }else{
            System.out.println("\tString are not equal");
        }


        String str3 = "HelloWorld";
        System.out.println("Substrings: "+str3.substring(0, 5));
        
        System.out.println("largest of the strings is: "+largestString(new String[]{"apple", "mango", "banana"}));

        StringBuilder sb = new StringBuilder("");
        for(char ch='a'; ch<='z'; ch++){
            sb.append(ch);
        }
        System.out.println("String Builder: "+sb.toString());
        System.out.println("Capitalize the string: "+capitalize("hello, i am    shradha"));
        System.out.println("Compress the string: "+stringCompression("aaabbcccdd"));
    }

    public static boolean palindromeString(String str){
        int length = str.length();
        for(int i=0; i<length/2; i++){
            if(str.charAt(i)!=str.charAt(length-1-i)){
                return false;
            }
        }
        return true;
    }

    //route containing 4 directions (E,W,N,S) each one unit give shortest path
    public static float shortestPath(String path){
        int x_axis = 0; // east(+1), west(-1)
        int y_axis = 0; // north(+1), south(-1)
        for(int i=0; i<path.length(); i++){
            if (path.charAt(i) == 'E') {
                x_axis++;
            } else if (path.charAt(i) == 'W') {
                x_axis--;
            } else if (path.charAt(i) == 'N') {
                y_axis++;
            } else {
                y_axis--;
            }
        }
        return (float)Math.sqrt(x_axis*x_axis + y_axis*y_axis);
    }

    public static String largestString(String str[]){
        String largest = str[0];
        for(int i=1; i<str.length; i++){
            if(largest.compareToIgnoreCase(str[i])<0){
                largest = str[i];
            }
        }
        return largest;
    }

    public static String capitalize(String str){
        StringBuilder sb = new StringBuilder("");

        char ch = Character.toUpperCase(str.charAt(0));
        sb.append(ch);

        for(int i=1; i<str.length(); i++){
            if(str.charAt(i)==' '){
                while(str.charAt(i)==' '){
                    sb.append(str.charAt(i));
                    i++;
                }                
                ch = Character.toUpperCase(str.charAt(i));
                sb.append(ch);

            }else{
                sb.append(str.charAt(i));
            }
        }
        return sb.toString();
    }

    public static String stringCompression(String str){
        StringBuilder sb = new StringBuilder("");
        for(int i=0; i<str.length(); i++){
            Integer count = 1;
            while(i<str.length()-1 && str.charAt(i) == str.charAt(i+1)){
                count++;
                i++;
            }
            sb.append(str.charAt(i));
            if(count>1) sb.append(count);
        }
        return sb.toString();
    }
}
