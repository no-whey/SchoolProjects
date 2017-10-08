/* Guess.java
 * Sean Gordon
 * skgordon #1405355
 * pa2
 * Try to guess the number the program thinks of
 */

import java.util.*;

class Guess{

   public static void main( String[] args ){

      int guess; 
      Scanner sc = new Scanner(System.in);
      Random rn = new Random();

      int num = rn.nextInt((10 - 1) +1) + 1;

      System.out.println("\nI'm thinking of an integer in the range 1 to 10. You have three guesses.\n");

      System.out.print("Enter your first guess: ");
      guess = sc.nextInt();

      if(guess == num){
         System.out.println("You win!\n");
         System.exit(0);
      }else if(guess > num){
         System.out.println("Your guess is too high.\n");
      }else if(guess < num){
         System.out.println("Your guess is too low.\n");
      }

      System.out.print("Enter your second guess: ");
      guess = sc.nextInt();

      if(guess == num){
         System.out.println("You win!\n");
         System.exit(0);
      }else if(guess > num){
         System.out.println("Your guess is too high.\n");
      }else if(guess < num){
         System.out.println("Your guess is too low.\n");
      }

      System.out.print("Enter your third guess: ");
      guess = sc.nextInt();

      if(guess == num){
         System.out.println("You win!\n");
         System.exit(0);
      }else if(guess > num){
         System.out.println("Your guess is too high.\n");
      }else if(guess < num){
         System.out.println("Your guess is too low.\n");
      }
      System.out.println("You lose. The number was "+num+".\n");
   }
}
