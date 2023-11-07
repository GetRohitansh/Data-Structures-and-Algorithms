import java.util.Arrays;

public class StringAssignment {
    public static void main(String[] args) {
        System.out.println("Lowercase vowels: "+lowercaseVowels("ABaCDeiEFoUbdsu"));

        String str = "ApnaCollege".replace("l","");
        System.out.println("Remove l: "+str);
        System.out.println("Anagram: "+anagram("heart", "earth"));
    }

    public static int lowercaseVowels(String str){
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if(ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u'){
                count++;
            }
        }
        return count;
    }

    // If two strings contain the same characters but in a different order, they can be said to be anagrams.
    // Consider race and care.In this case, race's characters can be formed into a care, or care's characters can be formed into race.
    public static boolean anagram(String str1, String str2){
        if(str1.length()==str2.length()){
            char[] str1array = str1.toCharArray();
            char[] str2array = str2.toCharArray();
            
            Arrays.sort(str1array);
            Arrays.sort(str2array);

            if(Arrays.equals(str1array, str2array)){
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }
}
