// lecture 2 JAVA BASICS
public class JavaBasics{
    public static void main(String args[]){
        
        System.out.println("println: Hello World");
        System.out.printf("printf: %s Hello World\n","{Append this in front}");
        System.out.print("print: Hello World");

        // Pattern
        System.out.println();
        int n = 5;
        for(int i=0; i<n; i++){            
            for(int j=0; j<n-i; j++){
                System.out.print("*");
            }
            System.out.println();
        }
    }
}
