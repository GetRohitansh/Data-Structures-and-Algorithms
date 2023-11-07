public class OOPS2 {
    /* 
    Topics Covered: 
     * Inheritance
     * Polymorphism
    */
    public static void main(String[] args) {
        Fish sharks = new Fish();
        sharks.eat();

        Dogs tommy = new Dogs();
        tommy.legs = 4;
        tommy.breed = "Indian Parohs";

        Calculator calc = new Calculator();
        System.out.println(calc.sum(12, 32, 43));
        System.out.println(calc.sum(12, 32));
        System.out.println(calc.sum(10.42f, 3.23f));

        Animals d1 = new Deer();
        d1.eat();

    }
}

// Base class
class Animals {
    String color;
    void eat(){
        System.out.println("eat anything");
    }
    void breathe(){
        System.out.println("breathe");
    }
}

// Derived class or Subclass
class Fish extends Animals{
    int fins;
    void swim(){
        System.out.println("swims");
    }
}

class Mammals extends Animals{
    int legs;
}

class Dogs extends Mammals {
    String breed;
}

// compile time polymorphism
class Calculator {
    int sum(int a, int b){
        return a + b;
    }

    float sum(float a, float b){
        return a + b;
    }

    int sum(int a, int b, int c){
        return a+b+c;
    }
}

// runtime polymorphism
class Deer extends Animals {
    void eat(){
        System.out.println("Eats Grass");
    }
}
