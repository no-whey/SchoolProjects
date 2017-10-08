/* GCD.java
 * Sean Gordon
 * skgordon #1405355
 * pa3
 * Evaluates the GCD of two numbers
 */

import java.util.Scanner;

class GCD{
  
    public static void main(String[] args){
      
      int n1, n2, nBig, nSmall, g, temp;
      Scanner sc = new Scanner(System.in);

      System.out.print("Enter a positive integer: ");

      while(true){
         while(!sc.hasNextInt()){
            sc.next();
            System.out.print("Please enter a positive integer: ");
         }
         temp = sc.nextInt();
         
         if(temp > 0) break;
         System.out.print("Please enter a positive integer: ");
      }
      n1 = temp;

      System.out.print("Enter another positive integer: ");
      
      while(true){
         while(!sc.hasNextInt()){
            sc.next();
            System.out.print("Please enter a positive integer: ");
         }
         temp = sc.nextInt();

         if(temp > 0) break;
         System.out.print("Please enter a positive integer: ");
      }
      n2 = temp;
      
      nBig = Math.max(n1,n2);
      nSmall = Math.min(n1,n2);

      while(nSmall != 0){
         temp = nBig % nSmall;
         nBig = nSmall;
         nSmall = temp;
      }
      g = nBig;

      System.out.println("The GCD of "+n1+" and "+n2+" is "+g+"\n");
   }
}
