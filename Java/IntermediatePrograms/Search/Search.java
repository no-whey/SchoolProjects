/* Search.java
 * Sean Gordon
 * skgordon #1405355
 * CMPS 12B pa2
 * 4/17/15
 * Searches a file for certain words
 */

import java.io.*;
import java.util.Scanner;

class Search{
   public static void main(String[] args) throws IOException{

      Scanner in = null;
      String[] word = null;
      int n, lineCount = 0;

      if(args.length < 2){
         System.out.println("Usage: Search file target1 [target2 ..]");
         System.exit(1);
      }

      // Checks how many lines/words are in the file
      in = new Scanner(new File(args[0]));
      while(in.hasNextLine()){
         lineCount++;
         in.nextLine();         
      }
      in.close();

      // Assigns the words to an array
      in = new Scanner(new File(args[0]));
      word = new String[lineCount];
      int[] lineNumber = new int[lineCount];
      for(int i=0; i<lineCount; i++){
         word[i] = in.nextLine();
         lineNumber[i] = i+1;
      }
      in.close();

      // Sort the array and find if/where the words are located
      mergeSort(word, lineNumber, 0, word.length-1);

      for(int i = 1; i<args.length; i++){
         n = binarySearch(word, 0, word.length-1, args[i]);
         if(n<0){
            System.out.println(args[i]+" not found");
         }else{
            System.out.println(args[i]+" found on line "+lineNumber[n]);
         }
      }
   }

   // Searches for a String target in a String array
   public static int binarySearch(String[] A, int p, int r, String target){
      int q;
      if(p > r){
         return -1;
      }else{
         q = (p+r)/2;
         if(target.compareTo(A[q])==0){
            return q;
         }else if(target.compareTo(A[q])<0){
            return binarySearch(A, p, q-1, target);
         }else{
            return binarySearch(A, q+1, r, target);
         }
      }
   }

   // Reorders a String array to be in alphabetical order
   public static void mergeSort(String[] word, int[] lineNumber, int p, int r){
      int q;
      if(p < r){
         q = (p+r)/2;
         mergeSort(word, lineNumber, p, q);
         mergeSort(word, lineNumber, q+1, r);
         merge(word, lineNumber, p, q, r);
      }
   }

   // Merges sorted subarrays
   public static void merge(String[] word, int[] lineNumber, int p, int q, int r){
      int n1 = q-p+1;
      int n2 = r-q;
      String[] L1 = new String[n1];
      String[] R1 = new String[n2];
      int[] L2 = new int[n1];
      int[] R2 = new int[n2];
      int i, j, k;

      for(i=0; i<n1; i++){
         L1[i] = word[p+i];
         L2[i] = lineNumber[p+i];
      }
      for(j=0; j<n2; j++){
         R1[j] = word[q+j+1];
         R2[j] = lineNumber[q+j+1];
      }
      i = 0; 
      j = 0;
      for(k=p; k<=r; k++){
         if( i<n1 && j<n2 ){
            if( L1[i].compareTo(R1[j])<0 ){
               word[k] = L1[i];
               lineNumber[k] = L2[i];
               i++;
            }else{
               word[k] = R1[j];
               lineNumber[k] = R2[j];
               j++;
            }
         }else if( i<n1 ){
            word[k] = L1[i];
            lineNumber[k] = L2[i];
            i++;
         }else{
            word[k] = R1[j];
            lineNumber[k] = R2[j];
            j++;
         }
      }
   }
}
