/* Queens.java
 * Sean Gordon
 * skgordon #1405355
 * pa5
 * Finds solution to all n-Queens problem
 */

class Queens{

   public static void main(String[] args){
      boolean verbose = false;
      long n = 0, f = 0;
      int s = 0;

      // Checking for Verbose and proper input

      if(args.length == 2){
         if(args[0].equals("-v")){
            verbose = true;
            try{
               n = Long.parseLong(args[1]);
            }catch(NumberFormatException e){
               Error();
            }
         }else{
            Error();
         }

      }else if(args.length == 1){
         try{
            n = Long.parseLong(args[0]);
         }catch(NumberFormatException e){
            Error();
         }
      }else{
         Error();
      }

      // Initialize Array

      int[] A = new int[(int)n+1];

      A[0] = 0;
      for(int i=1; i<=n; i++){
         A[i] = i;
      }

      // Print out solution to n-Queens problem

      while(A[0] == 0){
         if(isSolution(A)){
            s += 1;
            if(verbose){
               System.out.print("(");
               for(int i=1; i<A.length-1; i++){
                  System.out.print(A[i]+", ");
               }
               System.out.print(A[(int)n]+")\n");
            }
         }
         nextPermutation(A);
      }
      System.out.println(n+"-Queens has "+s+" solutions");
   }

   // Scan and move permutations

   public static void nextPermutation(int[] A){
      int pivot = 0, successor = 0, pivot_index = 0;
      for(int i=A.length-2; i>0; i--){
         if(A[i]<A[i+1]){
            pivot = A[i];
            pivot_index = i;
            break;
         }
      }
      if(pivot == 0){
         reverse(A);
	 A[0] = 1;
         return;
      }
      for(int i=A.length-1; i>0; i--){
         if(A[i]>pivot){
            successor = i;
            break;
         }
      }
      swap(A, pivot_index, successor);
      reversePivot(A, pivot_index);
      return;
   }

   // Check diagonals

   public static boolean isSolution(int[] A){
      for(int i=1; i<A.length; i++){
         for(int j=1; j<A.length; j++){
            if((Math.abs(A[i]-A[j]) == Math.abs(i-j)) && i!=j){
               return false;
            }
         }
      }
      return true;
   }

   // Computes the factorial of a number (NO LONGER NEEDED)
/*
   public static long fact(long n){
      long f = 1;
      for(long i=n; i>0; i--){
         f = i*f;
      }
      return f;
   }
*/
   // Reverse, Reverse the Pivot, and Swap functions

   public static void reverse(int[] A){
      int i=1, j=A.length-1;
      while(i<j){
         swap(A, i, j);
         i++;
         j--;
      }
   }

   public static void reversePivot(int[] A, int pivot_index){
      int i=pivot_index+1, j=A.length-1;
      while(i<j){
         swap(A, i, j);
         i++;
         j--;
      }
   }

   public static void swap(int[] A, int i, int j){
      int temp = A[i];
      A[i] = A[j];
      A[j] = temp;
   }

   // Error message and quit for verbose

   public static void Error(){
      System.out.println("Usage: Queens [-v] number");
      System.out.println("Option: -v verbose output, print all solutions");
      System.exit(0);
   }
}
