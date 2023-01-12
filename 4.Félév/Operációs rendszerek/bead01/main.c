#include <stdio.h>
#include <stdlib.h>
#include <fcntl.h> 
#include <sys/types.h> 
#include <sys/stat.h>
#include <errno.h> 
#include <string.h>
#include <ctype.h>
#include <stdbool.h>

///CONST STRING ARRAY
const char distArr [] [20] = {"Barátfa", "Lovas", "Szula", "Kígyós-patak", "Malom telek", "Páskom" , "Káposztás"};

///FUNCTIONS
void signUp(FILE * f);
void changeData(FILE * f);
void deleteData(FILE * f);
void listAll(FILE * f);
void listFilter(FILE * f);
int isNumber(char s[]);
int isInArray(char * s);
void trimTrailing(char * str);
int listLines (FILE * f, int * i);
int isEmpty(const char *s);
void removeEmptyLines(FILE * fp);
void validateSignUp(char* sol);

///MAIN
int main(int argc,char** argv){
    printf("Húsvéti Locsolókirály választás\n");
    char numStr [50];
    int num = 0;
    FILE * f;
    f = fopen("data.txt","a+");
    if (f == NULL ){ perror("Nem sikerült megnyitni a fájlt\n"); exit(1); }
    do {
        printf("1. Jelentkezés\n");
        printf("2. Adat módosítása\n");
        printf("3. Adat törlése\n");
        printf("4. Teljes lista lekérése\n");
        printf("5. Terület alapján szűrés\n");
        printf("0. Kilépés\n");
        printf("Kérlek Válassz egy opciót: ");
        fgets(numStr, 50, stdin);
        trimTrailing(numStr);
        if(isNumber(numStr) == 0) num = -1;
        else num = atoi(numStr);
        printf("---------------------------------------------------\n");
        switch (num){
            case 1:
                signUp(f); break;
            case 2:
                changeData(f); break;
            case 3:
                deleteData(f); break;
            case 4:
                listAll(f); break;
            case 5:
                listFilter(f); break;
            case 0:
                printf("Viszlát!\n");break;
            default:
                printf("Hibás bemenet!\n");
                printf("---------------------------------------------------\n");
        }
    } while (num != 0);
    return 0;
}

///FUNCTION DETAILS

//Creates a newline for the data.txt with valid data from the stdin.
void signUp(FILE * f){
    char sol [120] = "\n";
    validateSignUp(sol); 
    fprintf(f, "%s", sol);
    printf("Jelentkezés sikeres!\n");
    printf("---------------------------------------------------\n");
    fclose(f);
    f = fopen("data.txt","a+");
}

//Changes a line from the data.txt file with valid data from the stdin
void changeData(FILE * f){
        int i = 1;
    int sol = listLines(f,&i);
    FILE * f2 ;
    char str [120];
    int ctr = 0;
    f2 = fopen("temp.txt", "w");
    while (!feof(f)){
        strcpy(str, "\0");
        fgets(str, 120, f);
        ctr++;
        if (ctr == sol){
            char sol [120] = "\n";
            validateSignUp(sol);
            strcat(sol,"\n");
            fprintf(f2, "%s", sol );
        } 
        else fprintf(f2, "%s", str);
    }
    fclose(f);
    fclose(f2);
    remove("data.txt");
    rename("temp.txt","data.txt");
    printf("\n");
    printf("---------------------------------------------------\n");
    f = fopen("data.txt","a+");
    removeEmptyLines(f);
    f = fopen("data.txt","a+");
}

//Deletes a row from the data.txt based on the input from the stdin
void deleteData(FILE * f){
    int i = 1;
    int sol = listLines(f,&i);
    FILE * f2 ;
    char str [120];
    int ctr = 0;
    f2 = fopen("temp.txt", "w");
    while (!feof(f)){
        strcpy(str, "\0");
        fgets(str, 120, f);
        ctr++;
        if (ctr != sol) fprintf(f2, "%s", str);
    }
    fclose(f);
    fclose(f2);
    remove("data.txt");
    rename("temp.txt","data.txt");
    printf("\n");
    printf("---------------------------------------------------\n");
    f = fopen("data.txt","a+");
    removeEmptyLines(f);
    f = fopen("data.txt","a+");
}

//Lists all contents of the data.txt file. 
void listAll(FILE * f) {
    char line [160];
    while (!feof(f)){
        if (fgets(line,sizeof(line),f) != NULL ) printf("%s",line);
    }
    printf("\n");
    printf("---------------------------------------------------\n");
    fclose(f);
    f = fopen("data.txt","a+");
}

//Filters the data.txt file by the imput from the stdin.
void listFilter(FILE * f){
    char district [50];
    bool match;
    char dump [50];
    do {
        printf("Kérlek add meg a kerületed: ");
        fgets(district, 50, stdin);
        trimTrailing(district);
        if ( isInArray(district) == 0 ) {
            printf("Nem létező kerületet adtál meg!\n");
            match = false;
        }else match = true; 
    }while (!match);
    printf("\n");
    char disName [2]= " ";
    strcat(district, " ");
    strcat(disName,district);
    char line [160];
    char *ret;
    while (!feof(f)){
        if (fgets(line,sizeof(line),f) != NULL  ){
            ret = strstr(line, disName);
            if (ret) printf("%s",line);
        }
    }
    printf("\n");
    printf("---------------------------------------------------\n");
    fclose(f);
    f = fopen("data.txt","a+");
}

//Checks if the string is a valid positive whole number.
int isNumber(char s[]){
    for (int i = 0; s[i]!= '\0'; i++){
        if (isdigit(s[i]) == 0) return 0;
    }
    return 1;
}

//Checks if the distArr contains the string.
int isInArray(char * s){
    int len = sizeof(distArr)/sizeof(distArr[0]);
    int i;
    for(i = 0; i < len; ++i){
        if(strcmp(distArr[i], s) == 0) return 1;
    }
    return 0;
}

//Trims the whitespaces from the end of the file.
void trimTrailing(char * str){
    int index, i;
    index = -1;
    i = 0;
    while(str[i] != '\0'){
        if(str[i] != ' ' && str[i] != '\t' && str[i] != '\n') index= i;
        i++;
    }
    str[index + 1] = '\0';
}

//Lists the contents of a file and asks a valid row number from console.
int listLines (FILE * f, int * i){
    char line [160];
    while (!feof(f)){
        if (fgets(line,sizeof(line),f) != NULL ){
            printf("(%d)",(*i));
            printf("%s",line);
        }
        (*i)++;
    }
    fclose(f);
    f = fopen("data.txt","a+");
    if (*i == 1){
        printf("A fájl üres!\n");
        return 0;
    }
    else {
        char numStr [50];
        bool match;
        do {
            printf("\nKérlek adj meg egy számot 1 és %d között: ",(*i)-1);
            fgets(numStr, 50, stdin);
            trimTrailing(numStr);
            if ( isNumber(numStr) == 0 ) {
                printf("Nem számot adtál meg!\n");
                match = false;
            }else if(atoi(numStr) < 1 || (*i-1) < atoi(numStr) ){
                printf("A szám nem 1 és %d között van!\n",(*i)-1);
                match = false;
            }else match = true; 
        }while (!match);
        return atoi(numStr);
    }
}

//Checks if a row is empty
int isEmpty(const char *s) {
    char ch;
    do {
        ch = *(s++);
        if(ch != ' ' && ch != '\t' && ch != '\n' && ch != '\r' && ch != '\0')
            return 0;
 
    } while (ch != '\0');
 
    return 1;
}

//Removes the uneccesary empty lines from the file
void removeEmptyLines(FILE * fp) {
    char filetmp[100] = "tmp.txt";  
    FILE *fptmp = fopen(filetmp, "w");  
   
    if (fp == NULL || fptmp == NULL) {
        printf("Error open file\n");
        exit(EXIT_FAILURE);
    }
     
    char s[120];
    int i = 1;
    while ((fgets(s, 100, fp)) != NULL) {
        if (!isEmpty(s))
            fputs(s, fptmp);
        i++;
    }
     
    fclose(fp);
    fclose(fptmp);
     
    remove("data.txt");
    rename(filetmp, "data.txt");
}

//Validates the input from the stdin for the data.txt file
void validateSignUp(char* sol){
    char name [50];
    char district [50];
    char regNumStr [20];
    printf("Kérlek add meg a neved: ");
    fgets(name, 50, stdin);
    trimTrailing(name);
    bool match;
    do {
        printf("Kérlek add meg a kerületed: ");
        fgets(district, 50, stdin);
        trimTrailing(district);
        if ( isInArray(district) == 0 ) {
            printf("Nem létező kerületet adtál meg!\n");
            match = false;
        }else match = true; 
    }while (!match);
    do {
        printf("Kérlek add meg hanyadjára jelentkezel a versenyre: ");
        fgets(regNumStr, 50, stdin);
        trimTrailing(regNumStr);
        if ( isNumber(regNumStr) == 0 ) {
            printf("Nem számot adtál meg!\n");
            match = false;
        }else match = true; 
    }while (!match);
    strcat(name, " "); strcat(district, " ");
    strcat(sol,strcat(strcat(name,district),regNumStr));
}