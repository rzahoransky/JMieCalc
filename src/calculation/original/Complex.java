package calculation.original;

public class Complex {
		  double real;
		  double imag;
		  public static final Complex i = new Complex(0.0D, 1.0D);
		  public static final Complex one = new Complex(1.0D);
		  public static final Complex zero = new Complex();
		  
		  public static void preLoad() {}
		  
		  public Complex()
		  {
		    this.real = (this.imag = 0.0D);
		  }
		  
		  public Complex(double paramDouble)
		  {
		    this.real = paramDouble;
		    this.imag = 0.0D;
		  }
		  
		  public Complex(double paramDouble1, double paramDouble2)
		  {
		    this.real = paramDouble1;
		    this.imag = paramDouble2;
		  }
		  
		  public Complex(Complex paramComplex)
		  {
		    this.real = paramComplex.real;
		    this.imag = paramComplex.imag;
		  }
		  
		  public double real()
		  {
		    return this.real;
		  }
		  
		  public double imag()
		  {
		    return this.imag;
		  }
		  
		  public static Complex chs(Complex paramComplex)
		  {
		    return new Complex(-paramComplex.real, -paramComplex.imag);
		  }
		  
		  public static Complex chs(double paramDouble)
		  {
		    return new Complex(-paramDouble);
		  }
		  
		  public static Complex id(Complex paramComplex)
		  {
		    return new Complex(paramComplex);
		  }
		  
		  public static Complex id(double paramDouble)
		  {
		    return new Complex(paramDouble);
		  }
		  
		  public static Complex mult(double paramDouble1, double paramDouble2)
		  {
		    return new Complex(paramDouble1 * paramDouble2);
		  }
		  
		  public static Complex mult(Complex paramComplex, double paramDouble)
		  {
		    return new Complex(paramDouble * paramComplex.real, paramDouble * paramComplex.imag);
		  }
		  
		  public static Complex mult(double paramDouble, Complex paramComplex)
		  {
		    return new Complex(paramDouble * paramComplex.real, paramDouble * paramComplex.imag);
		  }
		  
		  public static Complex mult(Complex paramComplex1, Complex paramComplex2)
		  {
		    return new Complex(
		      paramComplex1.real * paramComplex2.real - paramComplex1.imag * paramComplex2.imag, 
		      paramComplex1.real * paramComplex2.imag + paramComplex1.imag * paramComplex2.real);
		  }
		  
		  public static Complex div(double paramDouble1, double paramDouble2)
		  {
		    return new Complex(paramDouble2 / paramDouble1);
		  }
		  
		  public static Complex div(double paramDouble, Complex paramComplex)
		  {
		    return new Complex(paramComplex.real / paramDouble, paramComplex.imag / paramDouble);
		  }
		  
		  public static Complex div(Complex paramComplex, double paramDouble)
		  {
		    if (Math.abs(paramComplex.real) > Math.abs(paramComplex.imag))
		    {
		      double d1 = paramComplex.imag / paramComplex.real;
		      double d2 = paramComplex.real + paramComplex.imag * d1;
		      return new Complex(paramDouble / d2, -paramDouble * d1 / d2);
		    }
		    double d1 = paramComplex.real / paramComplex.imag;
		    double d2 = paramComplex.real * d1 + paramComplex.imag;
		    return new Complex(paramDouble * d1 / d2, -paramDouble / d2);
		  }
		  
		  public static Complex div(Complex paramComplex1, Complex paramComplex2)
		  {
		    double d1 = paramComplex2.real;double d2 = paramComplex2.imag;double d3 = paramComplex1.real;double d4 = paramComplex1.imag;
		    if (Math.abs(d3) > Math.abs(d4))
		    {
		      double d6 = d4 / d3;
		      double d5 = d3 + d4 * d6;
		      return new Complex((d1 + d2 * d6) / d5, (d2 - d1 * d6) / d5);
		    }
		    double d6 = d3 / d4;
		    double d5 = d3 * d6 + d4;
		    return new Complex((d1 * d6 + d2) / d5, (d2 * d6 - d1) / d5);
		  }
		  
		  public static Complex add(double paramDouble1, double paramDouble2)
		  {
		    return new Complex(paramDouble1 + paramDouble2);
		  }
		  
		  public static Complex add(Complex paramComplex, double paramDouble)
		  {
		    return new Complex(paramComplex.real + paramDouble, paramComplex.imag);
		  }
		  
		  public static Complex add(double paramDouble, Complex paramComplex)
		  {
		    return new Complex(paramDouble + paramComplex.real, paramComplex.imag);
		  }
		  
		  public static Complex add(Complex paramComplex1, Complex paramComplex2)
		  {
		    return new Complex(paramComplex1.real + paramComplex2.real, paramComplex1.imag + paramComplex2.imag);
		  }
		  
		  public static Complex sub(double paramDouble1, double paramDouble2)
		  {
		    return new Complex(paramDouble2 - paramDouble1);
		  }
		  
		  public static Complex sub(Complex paramComplex, double paramDouble)
		  {
		    return new Complex(paramDouble - paramComplex.real, -paramComplex.imag);
		  }
		  
		  public static Complex sub(double paramDouble, Complex paramComplex)
		  {
		    return new Complex(paramComplex.real - paramDouble, paramComplex.imag);
		  }
		  
		  public static Complex sub(Complex paramComplex1, Complex paramComplex2)
		  {
		    return new Complex(paramComplex2.real - paramComplex1.real, paramComplex2.imag - paramComplex1.imag);
		  }
		  
		  public static Complex conjg(Complex paramComplex)
		  {
		    return new Complex(paramComplex.real, -paramComplex.imag);
		  }
		  
		  public static Complex conjg(double paramDouble)
		  {
		    return new Complex(paramDouble);
		  }
		  
		  public static double abs(Complex paramComplex)
		  {
		    double d1 = Math.abs(paramComplex.real);double d2 = Math.abs(paramComplex.imag);
		    if (d1 > d2)
		    {
		      double d3 = d2 / d1;
		      return d1 * Math.sqrt(1.0D + d3 * d3);
		    }
		    double d3 = d1 / d2;
		    return d2 * Math.sqrt(1.0D + d3 * d3);
		  }
		  
		  public static double abs(double paramDouble)
		  {
		    return Math.abs(paramDouble);
		  }
		  
		  public static Complex exp(Complex paramComplex)
		  {
		    double d = Math.exp(paramComplex.real);
		    return new Complex(d * Math.cos(paramComplex.imag), d * Math.sin(paramComplex.imag));
		  }
		  
		  public static Complex exp(double paramDouble)
		  {
		    return new Complex(Math.exp(paramDouble));
		  }
		  
		  public static Complex cos(double paramDouble)
		  {
		    return new Complex(Math.cos(paramDouble));
		  }
		  
		  public static Complex sin(double paramDouble)
		  {
		    return new Complex(Math.sin(paramDouble));
		  }
		  
		  public static Complex log(double paramDouble)
		  {
		    return new Complex(Math.log(paramDouble));
		  }
		  
		  public static Complex sqrt(double paramDouble)
		  {
		    return new Complex(Math.sqrt(paramDouble));
		  }
		  
		  public static Complex cos(Complex paramComplex)
		  {
		    double d1 = Math.exp(paramComplex.imag);
		    double d2 = 1.0D / d1;
		    
		    return new Complex(0.5D * Math.cos(paramComplex.real) * (d1 + d2), 
		      0.5D * Math.sin(paramComplex.real) * (d2 - d1));
		  }
		  
		  public static Complex sin(Complex paramComplex)
		  {
		    double d1 = Math.exp(paramComplex.imag);
		    double d2 = 1.0D / d1;
		    
		    return new Complex(0.5D * Math.sin(paramComplex.real) * (d1 + d2), 
		      0.5D * Math.cos(paramComplex.real) * (d1 - d2));
		  }
		  
		  public static Complex log(Complex paramComplex)
		  {
		    return new Complex(
		      Math.log(abs(paramComplex)), 
		      Math.atan2(paramComplex.imag, paramComplex.real));
		  }
		  
		  public static Complex sqrt(Complex paramComplex)
		  {
		    if ((paramComplex.real == 0.0D) && (paramComplex.imag == 0.0D)) {
		      return new Complex();
		    }
		    double d1 = Math.abs(paramComplex.real);double d2 = Math.abs(paramComplex.imag);
		    double d4;
		    double d3;
		    if (d1 > d2)
		    {
		      d4 = d2 / d1;
		      d3 = Math.sqrt(d1) * Math.sqrt(0.5D * (1.0D + Math.sqrt(1.0D + d4 * d4)));
		    }
		    else
		    {
		      d4 = d1 / d2;
		      d3 = Math.sqrt(d2) * Math.sqrt(0.5D * (d4 + Math.sqrt(1.0D + d4 * d4)));
		    }
		    if (paramComplex.real >= 0.0D) {
		      return new Complex(d3, 0.5D * paramComplex.imag / d3);
		    }
		    if (paramComplex.imag >= 0.0D) {
		      return new Complex(d2 / d3 * 0.5D, d3);
		    }
		    return new Complex(d2 / d3 * 0.5D, -d3);
		  }
		  
		  public String toString()
		  {
		    return "(" + this.real + "," + this.imag + ")";
		  }
		  
		  public static double[] complex2Double(Complex[] paramArrayOfComplex)
		  {
		    double[] arrayOfDouble = new double[2 * paramArrayOfComplex.length];
		    int j = 0;
		    for (int k = 0; j < paramArrayOfComplex.length; j++)
		    {
		      arrayOfDouble[(k++)] = paramArrayOfComplex[j].real();
		      arrayOfDouble[(k++)] = paramArrayOfComplex[j].imag();
		    }
		    return arrayOfDouble;
		  }
		  
		  public static Complex[] double2Complex(double[] paramArrayOfDouble)
		  {
		    Complex[] arrayOfComplex = new Complex[(paramArrayOfDouble.length + 1) / 2];
		    int j = 0;
		    for (int k = 0; j < arrayOfComplex.length; k += 2)
		    {
		      arrayOfComplex[j] = new Complex(paramArrayOfDouble[k], paramArrayOfDouble[(k + 1)]);j++;
		    }
		    return arrayOfComplex;
		  }
		  
		  public static Complex pow(Complex paramComplex, int paramInt)
		  {
		    Complex localComplex2 = new Complex(1.0D);
		    if (paramInt < 0)
		    {
		      paramInt = -paramInt;
		      Complex localComplex1 = div(paramComplex, one);
		    }
		    else if (paramInt == 0)
		    {
		      return localComplex2;
		    }
		    for (Complex localComplex1 = new Complex(paramComplex); paramInt != 0; localComplex1 = mult(localComplex1, localComplex1))
		    {
		      if (paramInt % 2 == 1) {
		        localComplex2 = mult(localComplex2, localComplex1);
		      }
		      paramInt >>= 1;
		    }
		    return localComplex2;
		  }
		  
		  public static String getVersion()
		  {
		    return "Version 2.3: last changed: 25/11/1999";
		  }
		}


