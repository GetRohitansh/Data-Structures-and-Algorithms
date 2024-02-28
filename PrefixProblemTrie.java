import java.util.ArrayList;

public class PrefixProblemTrie {
    static class Node{
        Node[] children = new Node[26];
        boolean end_of_word = false;
        int freq;

        public Node(){
            for(int i=0; i<26; i++){
                this.children[i] = null;
            }
            freq = 1;
        }
    }

    public static Node root = new Node();
    

    public static void insert(String word){
        Node curr = root;
        for(int i=0; i<word.length(); i++){
            int idx = word.charAt(i) - 'a';
            if(curr.children[idx] == null){
                curr.children[idx] = new Node();
            }else{
                curr.children[idx].freq++;
            }
            curr = curr.children[idx];
        }
        curr.end_of_word = true;
    }

    public static void search_prefix(String word, ArrayList<String> ans){
        Node curr = root;
        String str = "";
        for(int i=0; i<word.length(); i++){
            int idx = word.charAt(i) - 'a';
            str += word.charAt(i);
            if(curr.children[idx].freq == 1){
                ans.add(str);
                break;
            }
            curr = curr.children[idx];
        }
    }

    public static void search_prefix_recursive(Node root, String ans){
        if(root == null){
            return;
        }

        if(root.freq == 1){
            System.out.print(ans + " ");
            return;
        }
        for(int i=0; i<26; i++){ 
            if(root.children[i] != null){
                search_prefix_recursive(root.children[i], ans+(char)(i+'a'));
            }
        }
    }

    public static void main(String[] args) {
        root.freq = -1;
        String[] words = {"zebra", "dog", "duck", "dove"};
        for(String word: words){
            insert(word);
        }
        ArrayList<String> ans = new ArrayList<>();
        for(String word: words){
            search_prefix(word, ans);
        }
        System.out.println("Shortest Unique Prefix: "+ans);
        System.out.print("Shortest Unique Prefix Recursive: "); search_prefix_recursive(root, ""); System.out.println();
    }
}
