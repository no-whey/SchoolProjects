/* Extrema.java
 * Sean Gordon
 * skgordon #1405355
 * CMPS 12B pa1
 * 4/5/15
 * Finds the maximum and minimum values in an array, recursively
 */

class Extrema{

   public static void main(String[] args){
      int[] B = {65, 45, -56, 75, -500, 50, 300, 300, 0, 0};
      System.out.println( "max = " + maxArray(B, 0, B.length-1) );
      System.out.println( "min = " + minArray(B, 0, B.length-1) );
   }

// maxArray() returns largest value in an int array
   static int maxArray(int[] A, int p, int r){
      int q;
      if(r-p == 0) return A[p];
      if(r-p == 1) return max(A[p], A[r]);
      q = (p+r)/2;
      return max(maxArray(A, p, q), maxArray(A, q+1, r));
   }

// minArray() returns smallest value in an int array
   static int minArray(int[] A, int p, int r){
      int q;
      if(r-p == 0) return A[p];
      if(r-p == 1) return min(A[p], A[r]);
      q = (p+r)/2;
      return min(minArray(A, p, q), minArray(A, q+1, r));
   }

// Helper Functions min(a,b) and max(a,b)

   static int min(int a, int b){
      if(a>b){
         return b;
      }else{
         return a;
      }
   }

   static int max(int a, int b){
      if(a>b){
         return a;
      }else{
         return b;
      }
   }
}
