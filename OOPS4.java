public class OOPS4 {
    /* 
    Topics Covered: 
     * Static Keyword
     * Interfaces 
     * multiple inheritance
    */
    public static void main(String[] args) {
        Student s1 = new Student();
        s1.schoolName = "JMV";

        Student s2 = new Student();
        System.out.println(s2.schoolName);
        
        Horse h1 = new Horse();
        h1.getClass();
    }
}

class Student {
    String name;
    int roll;

    static String schoolName;

    void setName(String name){
        this.name = name;
    }

    String getName(){
        return this.name;
    }

    static int percentage(int phy, int chem, int math){
        return (phy+chem+math)/3;
    }
}

class Animal {
    String color;
    Animal() {
        System.out.println("This is animal constructor");
    }
}

class Horse extends Animal {
    Horse(){
        super();
        super.color = "brown";
        System.out.println("This is horse constructor, color: " + color);
    }
}
