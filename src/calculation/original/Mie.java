package calculation.original;

import BMNum.Complex;

public class Mie
  extends Complex
{
  private boolean isZero = false;
  private double[] tau;
  private double[] pi;
  public Complex[] a;
  public Complex[] b;
  protected int lmax;
  public int more = 8;
  protected double xout;
  protected double radius;
  protected double wave;
  private Complex refrel;
  protected Complex nref;
  
  public static void preLoad() {}
  
  public int getLmax()
  {
    return this.lmax;
  }
  
  protected double refmed = 1.0D;
  private double k;
  public Complex s1;
  public Complex s2;
  public double s11;
  public double s12;
  public double s33;
  public double s34;
  
  public Mie() {}
  
  public Mie(double paramDouble1, double paramDouble2, Complex paramComplex)
  {
    this.nref = paramComplex;
    this.radius = paramDouble1;
    this.wave = paramDouble2;
  }
  
  public void setHostRefractiveIndex(double paramDouble)
  {
    this.refmed = paramDouble;
  }
  
  public void setRadiusWavelength(double paramDouble1, double paramDouble2)
  {
    this.radius = paramDouble1;
    this.wave = paramDouble2;
  }
  
  public void setRefractiveIndex(double paramDouble1, double paramDouble2)
  {
    this.nref = new Complex(paramDouble1, paramDouble2);
  }
  
  public void setRefractiveIndex(Complex paramComplex)
  {
    this.nref = new Complex(paramComplex);
  }
  
  private void calcPiTau(double paramDouble)
  {
    this.tau = new double[this.lmax];
    this.pi = new double[this.lmax];
    
    double d1 = 0.0D;double d2 = 1.0D;
    
    this.tau[0] = paramDouble;
    this.pi[0] = 1.0D;
    for (int i = 2; i <= this.lmax; i++)
    {
      double d3 = ((2 * i - 1) * paramDouble * d2 - i * d1) / (i - 1);
      this.pi[(i - 1)] = d3;
      this.tau[(i - 1)] = (i * paramDouble * d3 - (i + 1) * d2);
      d1 = d2;
      d2 = d3;
    }
  }
  
  public void calcS(double paramDouble)
  {
    calcPiTau(Math.cos(paramDouble));
    this.s1 = Complex.zero;
    this.s2 = Complex.zero;
    
    int i = 1;
    for (double d2 = 1.0D; i <= this.lmax; d2 += 1.0D)
    {
      double d1 = (2.0D * d2 + 1.0D) / (d2 * (d2 + 1.0D));
      
      this.s1 = Complex.add(Complex.mult(Complex.add(Complex.mult(this.tau[(i - 1)], this.b[(i - 1)]), Complex.mult(this.pi[(i - 1)], this.a[(i - 1)])), d1), this.s1);
      this.s2 = Complex.add(Complex.mult(Complex.add(Complex.mult(this.tau[(i - 1)], this.a[(i - 1)]), Complex.mult(this.pi[(i - 1)], this.b[(i - 1)])), d1), this.s2);i++;
    }
    double d3 = Complex.abs(this.s1);
    double d4 = Complex.abs(this.s2);
    
    this.s11 = (0.5D * (d4 * d4 + d3 * d3));
    this.s12 = (0.5D * (d4 * d4 - d3 * d3));
    this.s33 = (0.5D * (Complex.mult(Complex.conjg(this.s2), this.s1).real() + Complex.mult(Complex.conjg(this.s1), this.s2).real()));
    this.s34 = (0.5D * (Complex.mult(Complex.conjg(this.s1), this.s2).imag() - Complex.mult(Complex.conjg(this.s2), this.s1).imag()));
  }
  
  public boolean calcScattCoeffs()
  {
    this.k = (6.283185307179586D / this.wave * this.refmed);
    this.refrel = Complex.div(this.refmed, this.nref);
    this.xout = (this.k * this.radius);
    if (this.xout <= 1.0E-10D)
    {
      this.isZero = true;
      return false;
    }
    this.isZero = false;
    
    this.lmax = ((int)(this.xout + 4.0D * Math.pow(this.xout, 0.333D) + 2.0D + this.more));
    
    Complex localComplex1 = Complex.mult(this.refrel, this.xout);
    
    int i = Math.max(this.lmax, (int)Complex.abs(localComplex1)) + 15 + this.more;
    
    Complex[] arrayOfComplex = new Complex[i];
    
    arrayOfComplex[(i - 1)] = Complex.zero;
    for (int j = i - 1; j > 0; j--)
    {
      Complex localComplex2 = Complex.div(localComplex1, j + 1);
      
      arrayOfComplex[(j - 1)] = Complex.sub(Complex.div(Complex.add(localComplex2, arrayOfComplex[j]), 1.0D), localComplex2);
    }
    this.a = new Complex[this.lmax];
    this.b = new Complex[this.lmax];
    
    double d4 = Math.cos(this.xout);
    double d5 = Math.sin(this.xout);
    
    double d1 = -d5;
    double d2 = d4;
    
    Complex localComplex4 = new Complex(d5, -d2);
    
    int j = 1;
    for (double d7 = 1.0D; j <= this.lmax; d7 += 1.0D)
    {
      double d6 = (2.0D * d7 - 1.0D) * d5 / this.xout - d4;
      double d3 = (2.0D * d7 - 1.0D) * d2 / this.xout - d1;
      Complex localComplex3 = new Complex(d6, -d3);
      double d8 = d7 / this.xout;
      
      Complex localComplex5 = Complex.add(d8, Complex.div(this.refrel, arrayOfComplex[(j - 1)]));
      
      this.a[(j - 1)] = Complex.div(Complex.sub(localComplex4, Complex.mult(localComplex3, localComplex5)), Complex.sub(d5, Complex.mult(d6, localComplex5)));
      
      localComplex5 = Complex.add(d8, Complex.mult(arrayOfComplex[(j - 1)], this.refrel));
      
      this.b[(j - 1)] = Complex.div(Complex.sub(localComplex4, Complex.mult(localComplex3, localComplex5)), Complex.sub(d5, Complex.mult(d6, localComplex5)));
      
      d4 = d5;
      d5 = d6;
      d1 = d2;
      d2 = d3;
      localComplex4 = new Complex(d5, -d2);j++;
    }
    return true;
  }
  
  public double qscatt()
  {
    double d1 = 3.0D;
    if (this.isZero) {
      return 0.0D;
    }
    double d2 = 0.0D;
    for (int i = 0; i < this.lmax; d1 += 2.0D)
    {
      d2 += d1 * (
        Complex.mult(Complex.conjg(this.a[i]), this.a[i]).real() + 
        Complex.mult(Complex.conjg(this.b[i]), this.b[i]).real());i++;
    }
    d2 *= 2.0D / (this.xout * this.xout);
    return d2;
  }
  
  public double qext()
  {
    double d1 = 3.0D;
    if (this.isZero) {
      return 0.0D;
    }
    double d2 = 0.0D;
    for (int i = 0; i < this.lmax; d1 += 2.0D)
    {
      d2 += d1 * 
        Complex.add(this.a[i], this.b[i]).real();i++;
    }
    d2 *= 2.0D / (this.xout * this.xout);
    return d2;
  }
  
  public double qb()
  {
    if (this.isZero) {
      return 0.0D;
    }
    double d1 = 3.0D;
    double d2 = -1.0D;
    Complex localComplex = new Complex();
    for (int i = 0; i < this.lmax; d2 = -d2)
    {
      localComplex = Complex.add(localComplex, Complex.mult(d2 * d1, 
        Complex.sub(this.b[i], this.a[i])));i++;d1 += 2.0D;
    }
    return (localComplex.real() * localComplex.real() + localComplex.imag() * localComplex.imag()) / (this.xout * this.xout);
  }
  
  public double cscatt()
  {
    return qscatt() * this.radius * this.radius * 3.141592653589793D;
  }
  
  public double cext()
  {
    return qext() * this.radius * this.radius * 3.141592653589793D;
  }
  
  public double cb()
  {
    return qb() * this.radius * this.radius * 3.141592653589793D;
  }
  
  public double asymmetry()
  {
    double d2 = 0.0D;
    
    double d1 = 1.0D;
    for (int i = 1; i < this.lmax; d1 += 1.0D)
    {
      d2 += (2.0D * d1 + 1.0D) / (d1 * (d1 + 1.0D)) * (
        this.a[(i - 1)].real() * this.b[(i - 1)].real() + this.a[(i - 1)].imag() * this.b[(i - 1)].imag());i++;
    }
    d1 = 1.0D;
    for (int i = 1; i < this.lmax; d1 += 1.0D)
    {
      d2 += d1 * (d1 + 2.0D) / (d1 + 1.0D) * (
        this.a[(i - 1)].real() * this.a[i].real() + this.a[(i - 1)].imag() * this.a[i].imag() + 
        this.b[(i - 1)].real() * this.b[i].real() + this.b[(i - 1)].imag() * this.b[i].imag());i++;
    }
    return 4.0D * d2 / (this.xout * this.xout);
  }
  
  public static String getVersion()
  {
    return "Version 2.4: last changed: 24/11/1999";
  }
}
