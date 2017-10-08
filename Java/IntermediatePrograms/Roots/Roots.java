/* Roots.java
 * Sean Gordon
 * skgordon #1405355
 * pa4
 * Finds the roots of a polynomial equation
 */

import java.util.Scanner;

class Roots{

   public static void main(String[] args){

      double resolution = 0.01, tolerance = 0.0000001, threshold = 0.001;
      double deg, L, R, a, b, root;
      int coef;
      boolean root_found = false;
      Scanner sc = new Scanner(System.in);

      System.out.print("Enter the degree: ");
      deg = sc.nextDouble();

      coef = (int)deg + 1;
      double[] C = new double[coef];
      double[] D = new double[coef];

      System.out.print("Enter "+C.length+" coefficients: ");
      for(int i=0; i<C.length; i++){
         C[i] = sc.nextDouble();
      }

      System.out.print("Enter the left and right endpoints: ");
      L = sc.nextDouble();
      R = sc.nextDouble();

      a = L;
      b = a+resolution;

      System.out.println();

      D = diff(C);

      for(double i=a; i<=b; i+=resolution){
         if (poly(C, i)*poly(C, b)<0){
            root = findRoot(C, i, b, tolerance);
            System.out.print("Root found at ");
            System.out.printf("%.5f%n", root);
            root_found = true;
         }else if(poly(D, i)*poly(D, b)<0){
            root = findRoot(D, i, b, tolerance);
            if(Math.abs(poly(C, root))<threshold){
               System.out.print("Root found at ");
               System.out.printf("%.5f%n", root);
               root_found = true;
            }
         }
         if(b<=R){
            a=b;
            b+=resolution;
         }
      }
      if(!root_found) System.out.println("No roots were found in the specified range.");
   }

   public static double poly(double[] C, double x){

      double sum = 0, temp = 0;
      for(int i=0; i<C.length; i++){
            temp = C[i]*(Math.pow(x, i));
            sum += temp;
      }
      return sum;
   }

   public static double[] diff(double[] C){

      double[] D = new double[C.length];
      for(int i=0; i<C.length; i++){
         D[i] =  i*C[i];
      }
      return D;
   }

   public static double findRoot(double[] C, double a, double b, double tolerance){
      
      double m = (a+b)/2;
      double left = poly(C, a);
      double mid = poly(C, m);
      double right = poly(C, b);
      double width = b-a;
      while( width > tolerance){
         m = (a+b)/2;
         left = poly(C, a);
         mid = poly(C, m);
         if( left*mid < 0){
            b = m;
         } else {
            a = m;
         }
         width = b-a;
      }
      return m;
   }
}
