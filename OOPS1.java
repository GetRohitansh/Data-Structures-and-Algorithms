import java.util.Arrays;

public class OOPS1 {
    /* 
    Topics Covered:
     * Classes and Objects 
     * Getters and Setters
     * Constructors
     * Constructor Overloading
     * Shallow Constructor VS Deep Constructor    
    */
    public static void main(String[] args) {
        Pen p1 = new Pen();
        p1.color = "Blue";
        System.out.println(p1.color);
        p1.setTip(5);
        System.out.println(p1.getTip());

        p1.marks[0] = 100;
        p1.marks[1] = 100;
        p1.marks[2] = 100;

        Pen p2 = new Pen(p1);
        p1.marks[1] = 90;
        System.out.println(Arrays.toString(p2.marks));
    }
}

class Pen {
    String color;
    private int tip;
    int marks[] = new int[3];

    void setTip(int tip){
        this.tip = tip;
    }

    int getTip(){
        return this.tip;
    }

    Pen(){
        System.out.println("Inside non parameterized constructor");
    }

    Pen(int tip){
        this.tip = tip;
        System.out.println("Inside parameterized constructor");
    }

    // shallow copy
    // Pen(Pen p1){
    //     this.color = p1.color;
    //     this.tip = p1.tip;
    //     this.marks = p1.marks;
    // }

    // deep copy
    Pen(Pen p1){
        int marks[] = new int[p1.marks.length];
        this.color = p1.color;
        this.tip = p1.tip;

        for(int i=0; i<marks.length; i++){
            this.marks[i] = p1.marks[i];
        }
    }
}
