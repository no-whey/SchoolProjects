/* FileReverse.java
 * Sean Gordon
 * skgordon #1405355
 * CMPS 12M lab2
 * 4/16/15
 * Reverses the tokens in a file, and places it into a seperate file
 */

import java.io.*;
import java.util.Scanner;

class FileReverse{
   public static void main(String[] args) throws IOException{

      Scanner in = null;
      PrintWriter out = null;
      String line = null;
      String[] words = null;

      //Check the Command Line for proper argument list
      if(args.length < 2){
         System.out.println("Usage: FileReverse infile outfile");
         System.exit(1);
      }

      //Assignes files to be scanned and written
      in = new Scanner(new File(args[0]));
      out = new PrintWriter(new FileWriter(args[1]));

      //Checks through each line
      while(in.hasNextLine()){

         //Trims extra space, and allocates into string array
         line = in.nextLine().trim() + " ";;
         words = line.split("\\s+");

         //Reverses tokens in array, and prints to file
         for(int i = 0; i < words.length; i++){
            words[i] = stringReverse(words[i], words[i].length());
            out.println(words[i]);
         }
      }
      in.close();
      out.close();
   }

   //Reverses a string s from beginning to length n
   public static String stringReverse(String s, int n){
      if(n <= 0) return s;
      char c = s.charAt(n-1);
      return c + stringReverse(s.substring(0,n-1), n-1);
   }
}
