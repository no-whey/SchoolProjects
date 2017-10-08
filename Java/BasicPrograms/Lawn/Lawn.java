/* Lawn.java
 * Sean Gordon
 * skgordon #1405355
 * pa1
 * Calculates lawn area, and mowing time.
 */

import java.util.Scanner;

class Lawn{
   public static void main( String[] args ){
      
      double lLot, wLot, lHouse, wHouse, aLot, aHouse, aLawn, mRate, mTime;
      int s, m, h;
      Scanner sc = new Scanner(System.in);
      
      System.out.print("Enter the length and width of the lot, in feet: ");
      lLot = sc.nextDouble();
      wLot = sc.nextDouble();
      aLot = lLot*wLot;

      System.out.print("Enter the length and width of the house, in feet: ");
      lHouse = sc.nextDouble();
      wHouse = sc.nextDouble();
      aHouse = lHouse*wHouse;

      aLawn = aLot-aHouse;
      System.out.println("The lawn area is "+aLawn+" square feet.");
      System.out.print("Enter the mowing rate, in square feet per second: ");
      mRate = sc.nextDouble();
      mTime = aLawn/mRate;
      
      s = (int) Math.round(mTime);
      m = s/60;
      s = s%60;
      h = m/60;
      m = m%60;
      System.out.println("The mowing time is "+h+" hour"+ (h==1?" ":"s ")+m+" minute"+ (m==1?" ":"s ")+s+" second"+ (s==1?".":"s."));
   }
}
