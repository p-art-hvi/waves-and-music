package ca.ubc.ece.cpen221.mp1.utils;

public class ComplexNumber {
    //used to format the complex number as x+yi
    public static final int XY = 0;
    //used to format the complex number as R.cis(theta)
    public static final int RCIS = 1;
    //the real part of the complex number
    public double real;
    //the imaginary part of the complex number
    public double imaginary;

    //constructs a new complex number object
    public ComplexNumber(){
        real = 0.0;
        imaginary = 0.0;
    }

    public ComplexNumber(double real, double imaginary){
        this.real = real;
        this.imaginary = imaginary;
    }

    public void multiply(ComplexNumber z){
        set(multiply(this, z));
    }

    public void set(ComplexNumber z){
        this.real = z.real;
        this.imaginary = z.imaginary;
    }

    public  static ComplexNumber multiply(ComplexNumber z1, double doubleNum){
        double real1 = (z1.real * doubleNum);
        double imaginary1 = (z1.imaginary * doubleNum);
        return new ComplexNumber(real1, imaginary1);
    }

    public static ComplexNumber multiply(ComplexNumber z1, ComplexNumber z2){
        double real1 = (z1.real * z2.real) - (z1.imaginary * z2.imaginary);
        double imaginary1 = (z1.real * z2.imaginary) + (z1.imaginary * z2.real);
        return new ComplexNumber(real1, imaginary1);
    }

    public static double mod(ComplexNumber z){
        double mod = Math.sqrt((z.real * z.real) + (z.imaginary * z.imaginary));
        return mod;
    }
    public double realComp(){
        return this.real;
    }
    public double imaginaryComp(){
        return this.imaginary;
    }

    public void add(ComplexNumber z){
        real = this.real + z.real;//z.realComp();
        imaginary = this.imaginary + z.imaginary;//z.imaginaryComp();
    }
    public void reset(){
        real = 0.0;
        imaginary = 0.0;
    }
    public double realPart(){return this.real;}
    public double imagPart(){return this.imaginary;}
}
