/* DictionaryTest.c
 * Sean Gordon
 * skgordon #1405355
 * CMPS 12B pa5
 * 5/30/15
 * Test file for the Hash Table Dictionary ADT
 */

#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include"Dictionary.h"

#define MAX_LEN 180

int main(int argc, char* argv[]){
    Dictionary A = newDictionary();

    printf("==========Section 1==========\n");
    insert(A, "one", "une");
    insert(A, "two", "deux");
    insert(A, "three", "trois");
    insert(A, "four", "quatre");
    insert(A, "five", "cinq");
    printf("Size of A: %d\n", size(A));
    printf("Is A empty?: %s\n", (isEmpty(A)?"true":"false"));
    printf("Lookup for 'four': %s\n", lookup(A, "four"));
    printDictionary(stdout, A);

    printf("==========Section 2==========\n");
    delete(A, "two");
    delete(A, "four");
    printf("Size of A: %d\n", size(A));
    printf("Is A empty?: %s\n", (isEmpty(A)?"true":"false"));
    makeEmpty(A);
    printf("Size of A: %d\n", size(A));
    printf("Is A empty?: %s\n", (isEmpty(A)?"true":"false"));
    freeDictionary(&A);

    printf("==========Section 3==========\n");
    Dictionary B = newDictionary();
    insert(B, "France", "Paris");
    insert(B, "Spain", "Madrid");
    insert(B, "Germany", "Berlin");
    insert(B, "Britain", "London");
    printf("Size of B: %d\n", size(B));
    printf("Is B empty?: %s\n", (isEmpty(B)?"true":"false"));
    delete(B, "France");
    printDictionary(stdout, B);

    printf("==========Section 4==========\n");
    printf("This section will cause errors if uncommented\n");
    //printf("Lookup for 'one': %s\n", lookup(A, "one"));
    //insert(B, "Germany", "Munich");
    //delete(B, "five");
    //delete(B, "France");

    printf("==========Section 5==========\n");
    freeDictionary(&B);

    return(EXIT_SUCCESS);
}
