public class Tries_Lecture {  
    /* 
    Topics Covered: 
     * Trie Implementation
     * Insert, Search, Prefix in Trie
     * Word Break Problem
     * Shortest Unique Prefix (in another file)
     * Count unique substring
     * Longest Word with Prefix
    */  

    public static boolean word_break_problem(Trie trie, String key){
        if(key.length() == 0){
            return true; // searched for entire left part
        }
        for(int i=1; i<=key.length(); i++){
            if(trie.search(key.substring(0, i)) && word_break_problem(trie, key.substring(i))){
                return true;
            }
        }

        return false;
    }

    public static int count_unique_substring(String word){
        Trie trie = new Trie();
        for(int i=0; i<word.length(); i++){
            trie.insert(word.substring(i)); // add all suffix to trie
        }

        return count_node(trie.root);
    }
    public static int count_node(Trie.Node root){
        if(root == null){
            return 0;
        }
        int count = 0;
        for(int i=0; i<26; i++){
            if(root.children[i] != null){
                count += count_node(root.children[i]);
            }
        }

        return count + 1;
    }

    public static void longest_word_with_prefix(String[] words){
        Trie trie = new Trie();
        for(String word : words){
            trie.insert(word);
        }


        System.out.println("longest_word_with_prefix_helper: "+longest_word_with_prefix_helper(new StringBuilder(""), trie.root));
        
    }

    static String ans = "";

    public static String longest_word_with_prefix_helper(StringBuilder temp, Trie.Node root){
        if(root == null){
            return ans;
        }
        for(int i=0; i<26; i++){
            if(root.children[i] != null && root.children[i].end_of_word == true){                
                char ch = (char)('a' + i);
                temp.append(ch);
                if(temp.length() > ans.length()){
                    ans = temp.toString();
                }
                longest_word_with_prefix_helper(temp, root.children[i]);
                //backtrack
                temp.deleteCharAt(temp.length()-1);
            }
        }

        return ans;
    }


    public static void main(String args[]){
        String[] words = {"the", "a", "there", "their", "any", "thee"};
        Trie trie1 = new Trie();
        for(String word: words){
            trie1.insert(word);
        }
        System.out.println("Check is word exist: "+trie1.search("thee"));
        System.out.println("Search prefix in trie: "+trie1.search_prefix("thie"));
        System.out.println("Search prefix in trie: "+trie1.search_prefix("thei"));

        // Question : Word Break Problem
        Trie trie2 = new Trie();
        String[] words2 = {"i", "like", "sam", "samsung", "mobile", "ice"};
        for(String word: words2){
            trie2.insert(word);
        }
        System.out.println("Word Break Problem: "+word_break_problem(trie2, "ilikesamsung"));

        // Question : Prefix Problem

        System.out.println("Count Unique Substring: "+count_unique_substring("ababa"));
        longest_word_with_prefix(new String[]{"a","banana","app","appl","ap","apply","apple"});
    }
}

class Trie {
    static class Node{
        Node[] children = new Node[26];
        boolean end_of_word = false;

        public Node(){
            for(int i=0; i<26; i++){
                this.children[i] = null;
            }
        }
    }

    public Node root = new Node(); // empty node 

    public void insert(String word){
        Node curr = root;
        for(int level=0; level<word.length(); level++){
            int idx = word.charAt(level) - 'a';
            if(curr.children[idx] == null){ // check for value at its index a-0 , b-1, c-2 ...
                curr.children[idx] = new Node(); // creating new Node at required index 
            }
            curr = curr.children[idx]; // for next letter, in next level
        }
        curr.end_of_word = true;
    }

    public boolean search(String word){
        Node curr = root;
        for(int lvl = 0; lvl < word.length(); lvl++){
            int idx = word.charAt(lvl) - 'a';
            if(curr.children[idx] == null){ // word not exist at this lvl, return false
                return false;
            }
            curr = curr.children[idx]; // word exist move to next lvl
        }
        return curr.end_of_word; // if it is end of the word then retrun true
    }

    public boolean search_prefix(String word){
        Node curr = root;
        for(int i=0; i<word.length(); i++){
            int idx = word.charAt(i) - 'a';
            if(curr.children[idx] == null){
                return false;
            }
            curr = curr.children[idx];
        }
        return true; // does not check for end_of_word
    }
}