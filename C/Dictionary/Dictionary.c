/* Dictionary.c
 * Sean Gordon
 * skgordon #1405355
 * CMPS 12B pa5
 * 5/30/15
 * Hash Table implementation of the Dictionary ADT
 */

#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<assert.h>
#include"Dictionary.h"

const int tableSize = 101;

/* Node Definition and Operations */
typedef struct NodeObj{
   char* key;
   char* value;
   struct NodeObj* next;
} NodeObj;

typedef NodeObj* Node;

Node newNode(char* k, char* v){
   Node N = malloc(sizeof(NodeObj));
   assert(N!=NULL);
   N->key = k;
   N->value = v;
   N->next = NULL;
   return(N);
}

void freeNode(Node* pN){
   if(pN!=NULL && *pN!=NULL){
      free(*pN);
      *pN = NULL;
   }
}

/* Hash Functions */
// rotate_left()
// rotate the bits in an unsigned int
unsigned int rotate_left(unsigned int value, int shift) {
   int sizeInBits = 8*sizeof(unsigned int);
   shift = shift & (sizeInBits - 1);
   if(shift == 0)
      return value;
   return (value << shift) | (value >> (sizeInBits - shift));
}

// pre_hash()
// turn a string into an unsigned int
unsigned int pre_hash(char* input) { 
   unsigned int result = 0xBAE86554;
   while (*input) { 
      result ^= *input++;
      result = rotate_left(result, 5);
   }
   return result;
}

// hash()
// turns a string into an int in the range 0 to tableSize-1
int hash(char* key){
   return pre_hash(key)%tableSize;
}

/* Dictionary Private Functions */
typedef struct DictionaryObj{
   int numItems;
   Node* table;
} DictionaryObj;

Node findKey(Dictionary D, char* k){
   int index = hash(k);
   Node N = D->table[index];
   while(N != NULL && strcmp(k, N->key)!=0) N = N->next;
   return N;
}

/* Public Functions */
Dictionary newDictionary(void){
   Dictionary D = malloc(sizeof(DictionaryObj));
   assert(D!=NULL);
   D->numItems = 0;
   D->table = calloc(tableSize, sizeof(NodeObj));
   return D;
}

void freeDictionary(Dictionary* pD){
   if(pD!=NULL && *pD!=NULL){
      if(!isEmpty(*pD)) makeEmpty(*pD);
      free((*pD)->table);
      free(*pD);
      *pD = NULL;
   }
}

int isEmpty(Dictionary D){
   if(D == NULL){
      fprintf(stderr,
         "Dictionary Error: isEmpty() called on NULL Dictionary reference\n");
      exit(EXIT_FAILURE);
   }
   return (D->numItems==0);
}

int size(Dictionary D){
   if(D == NULL){
      fprintf(stderr, 
         "Dictionary Error: size() called on NULL Dictionary reference\n");
      exit(EXIT_FAILURE);
   }
   return D->numItems;
}

char* lookup(Dictionary D, char* k){
   Node N;
   if(D == NULL){
      fprintf(stderr,
         "Dictionary Error: lookup() called on NULL Dictionary reference\n");
      exit(EXIT_FAILURE);
   }
   N = findKey(D, k);
   return ( N == NULL ? NULL : N->value );
}

void insert(Dictionary D, char* k, char* v){
   Node N;
   Node P;
   int index;
   if(D == NULL){
      fprintf(stderr,
         "Dictionary Error: insert() called on NULL Dictionary reference\n");
      exit(EXIT_FAILURE);
   }
   if(lookup(D, k)!=NULL){
      fprintf(stderr,
         "Dictionary Error: cannot insert duplicate key: %s\n", k);
      exit(EXIT_FAILURE);
   }
   index = hash(k);
   if(D->table[index] == NULL){
      N = newNode(k, v);
      D->table[index] = N;
   }else{
      P = D->table[index];
      while(P->next != NULL) P = P->next;
      P->next = newNode(k, v);
   }
   D->numItems++;
}

void delete(Dictionary D, char* k){
   Node N;
   Node P;
   int index;
   if(D == NULL){
      fprintf(stderr,
         "Dictionary Error: delete() called on NULL Dictionary reference\n");
      exit(EXIT_FAILURE);
   }
   if(lookup(D, k)==NULL){
      fprintf(stderr,
         "Dictionary Error: cannot delete non-existant key: %s\n", k);
      exit(EXIT_FAILURE);
   }
   index = hash(k);
   if(D->table[index] == findKey(D, k)){
      N = D->table[index];
      D->table[index] = D->table[index]->next;
      N->next = NULL;
      freeNode(&N);
   }else{
      P = D->table[index];
      N = findKey(D, k);
      while(P->next != N) P = P->next;
      P->next = N->next;
      N->next = NULL;
      freeNode(&N);
   }
   D->numItems--;
}

void makeEmpty(Dictionary D){
   Node N;
   int i;
   if(D == NULL){
      fprintf(stderr,
         "Dictionary Error: makeEmpty() called on NULL Dictionary reference\n");
      exit(EXIT_FAILURE);
   }
   for(i=0; i<tableSize; i++){
      N = D->table[i];
      while(N != NULL){
         delete(D, N->key);
         N = D->table[i];
      }
   }
}

void printDictionary(FILE* out, Dictionary D){
   Node N;
   int i;
   if(D == NULL){
      fprintf(stderr,
         "Dictionary Error: printDictionary() called on NULL Dictionary reference\n");
      exit(EXIT_FAILURE);
   }
   for(i=0; i<tableSize; i++){
      N = D->table[i];
      while(N != NULL){
         fprintf(out, "%s %s\n", N->key, N->value);
         N = N->next;
      }
   }
}
