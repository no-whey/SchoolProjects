/* charType.c
 * Sean Gordon
 * skgordon #1405355
 * CMPS 12M lab4
 * 4/29/15
 * Takes characters in one file, then categorizes them into another
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>
#include <assert.h>

#define MAX_STRING_LENGTH 100

void extract_chars(char* s, char* a, char* d, char* p, char* w);

int main(int argc, char* argv[]){
    FILE* in;
    FILE* out;
    char* line;
    char* alpha;
    char* digit;
    char* punct;
    char* white;
    int i=1;

    if(argc != 3){
        printf("Usage: %s <input-file> <output-file>\n", argv[0]);
        exit(EXIT_FAILURE);
    }

    in = fopen(argv[1], "r");
    if( in==NULL ){
        printf("Unable to read from file %s\n", argv[1]);
        exit(EXIT_FAILURE);
    }

    out = fopen(argv[2], "w");
    if( out==NULL ){
        printf("Unable to write to file %s\n", argv[2]);
        exit(EXIT_FAILURE);
    }

    line = calloc(MAX_STRING_LENGTH+1, sizeof(char));
    alpha = calloc(MAX_STRING_LENGTH+1, sizeof(char));
    digit = calloc(MAX_STRING_LENGTH+1, sizeof(char));
    punct = calloc(MAX_STRING_LENGTH+1, sizeof(char));
    white = calloc(MAX_STRING_LENGTH+1, sizeof(char));
    assert(line!=NULL && alpha!=NULL && digit!=NULL && punct!=NULL && white!=NULL);

    while( fgets(line, MAX_STRING_LENGTH, in) != NULL){
        extract_chars(line, alpha, digit, punct, white);
        fprintf(out, "line %d contains:\n", i);
        fprintf(out, "%d alphabetic characters: %s\n", (int)strlen(alpha), alpha);
        fprintf(out, "%d numeric characters: %s\n", (int)strlen(digit), digit);
        fprintf(out, "%d punctuation characters: %s\n", (int)strlen(punct), punct);
        fprintf(out, "%d whitespace characters: %s", (int)strlen(white), white);
        fprintf(out, "\n");
        i++;
    }

    free(line);
    free(alpha);
    free(digit);
    free(punct);
    free(white);

    fclose(in);
    fclose(out);

    return EXIT_SUCCESS;
}

void extract_chars(char* s, char* a, char* d, char* p, char* w){
    int i=0, ja=0, jd=0, jp=0, jw=0;
    while(s[i]!='\0' && i<MAX_STRING_LENGTH){
        if( isalpha((int)s[i]) ) a[ja++] = s[i];
        if( isdigit((int)s[i]) ) d[jd++] = s[i];
        if( ispunct((int)s[i]) ) p[jp++] = s[i];
        if( isspace((int)s[i]) ) w[jw++] = s[i];
        i++;
    }
    a[ja] = '\0';
    d[jd] = '\0';
    p[jp] = '\0';
    w[jw] = '\0';
}
