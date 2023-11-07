public class OOPSAssignment {
    public static void main(String[] args) {
        Complex c1 = new Complex(10, 10);
        Complex c2 = new Complex(5, 5);
        Complex ans = Complex.product(c1, c2);
        
        System.out.printf("%d + %di",ans.real, ans.imaginary);
    }
}

class Complex {
    int real;
    int imaginary;

    Complex(){
        this.real = 0;
        this.imaginary = 0;
    }

    Complex(int real, int imaginary){
        this.real = real;
        this.imaginary = imaginary;
    }

    static Complex sum(Complex c1, Complex c2){
        Complex ans = new Complex(c1.real+c2.real, c1.imaginary+c2.imaginary);
        return ans;
    }

    static Complex difference(Complex c1, Complex c2){
        Complex ans = new Complex(c1.real-c2.real, c1.imaginary-c2.imaginary);
        return ans;
    }

    static Complex product(Complex c1, Complex c2){
        Complex ans = new Complex(c1.real*c2.real - c1.imaginary*c2.imaginary, c1.real*c2.imaginary + c1.imaginary*c2.real);
        return ans;
    }
}
