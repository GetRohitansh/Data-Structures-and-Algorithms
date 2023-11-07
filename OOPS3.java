public class OOPS3 {
    /* 
    Topics Covered: 
     * Abstract classes
     * Interfaces 
     * multiple inheritance
    */
    public static void main(String[] args) {
        Horse h = new Horse();
        h.eat();
        h.walk();

        Chicken c = new Chicken();
        c.eat();
        c.walk();

        Mustang m = new Mustang();
        m.eat();

        Queen q = new Queen();
        q.moves();

        Bear b = new Bear();
        b.eatsGrass();
        b.eatsMeat();
    }
}

//instance of abstract cannot be created
abstract class Animals {
    String color;

    Animals(){
        this.color = "Brown";
        System.out.println("Animal Constructor Called");
    }

    void eat(){
        System.out.println("Animal eats");
    }

    //no implementation inside for abstract class
    //gives idea that every subclass will have this function
    abstract void walk();
}

// It is must to implement code for abstract methods
class Horse extends Animals {
    Horse(){
        this.color = "Blue";
        System.out.println("Horse Constructor Called");
    }
    void walk(){
        System.out.println("Walks on 4 legs");
    }
}

class Mustang extends Horse {
    Mustang(){
        this.color = "Red";
        System.out.println("Mustang Constructor Called");
    }
    void walk(){
        System.out.println("Walks on 4 legs");
    }
}

class Chicken extends Animals {
    void walk(){
        System.out.println("Walks on 2 legs");
    }
}

interface ChessPlayer {
    void moves();
}

class Queen implements ChessPlayer {
    public void moves(){
        System.out.println("Right Left Up Down Diagonals (any number of steps)");
    }
}

class Rook implements ChessPlayer {
    public void moves(){
        System.out.println("Right Left Up Down (any number of steps)");
    }
}

class King implements ChessPlayer {
    public void moves(){
        System.out.println("Right Left Up Down Diagonals (1 step)");
    }
}

interface Herbivore {
    void eatsGrass();
}

interface Carnivore {
    void eatsMeat();
}

class Bear implements Herbivore, Carnivore {
    public void eatsGrass() {
        System.out.println("All types of grass");
    }
    public void eatsMeat() {
        System.out.println("All types of meats");
    }
}
