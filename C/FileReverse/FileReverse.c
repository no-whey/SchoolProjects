/* FileReverse.c
 * Sean Gordon
 * skgordon #1405355
 * CMPS 12M lab3
 * 4/21/15
 * Reverses tokens in a file, and prints them into a seperate file
 */

#include<stdio.h>
#include<stdlib.h>
#include<string.h>

void stringReverse(char* s);

int main(int argc, char* argv[]){

   FILE* in;
   FILE* out;
   char word[256];

   /* make sure there is the correct number of command line arguments */
   if(argc != 3){
      printf("Usage: %s <input file> <output file>\n", argv[0]);
      exit(EXIT_FAILURE);
   }

   /* open input file */
   in = fopen(argv[1], "r");
   if(in==NULL){
      printf("Unable to read from file %s\n", argv[1]);
      exit(EXIT_FAILURE);
   }

   /* open output file */
   out = fopen(argv[2], "w");
   if(out==NULL){
      printf("Unable to write to file %s\n", argv[2]);
      exit(EXIT_FAILURE);
   }

   /* scans from input, reverses tokens, then prints on seperate lines to output */
   while( fscanf(in, " %s", word) != EOF ){
      stringReverse(word);
      fprintf(out, "%s\n", word);
   }

   /* closes input and output */
   fclose(in);
   fclose(out);

   return(EXIT_SUCCESS);
}

void stringReverse(char* s){

   int i, j;
   char temp;
   for(i=0, j=strlen(s)-1; i<j; i++, j--){
      temp = s[i];
      s[i] = s[j];
      s[j] = temp;
   }
}
