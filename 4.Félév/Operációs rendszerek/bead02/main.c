#include <stdio.h>
#include <time.h>
#include <stdlib.h>
#include <fcntl.h> 
#include <sys/types.h> 
#include <sys/stat.h>
#include <unistd.h>
#include <errno.h> 
#include <string.h>
#include <ctype.h>
#include <stdbool.h>
#include <sys/wait.h>

///STRUCTS
struct bunny {
    char *name;
    char *distName;
    int signUpNum;
    int score;
};

///CONST STRING ARRAY
const char distArr [] [20] = {"Barátfa", "Lovas", "Szula", "Kígyós-patak", "Malom telek", "Páskom" , "Káposztás kert"};

///FUNCTIONS
void signUp(FILE *f);
void changeData(FILE *f);
void deleteData(FILE *f);
void listAll(FILE *f);
void listFilter(FILE *f);
int startRace(FILE *f);

//bead2
void fillStruct(struct bunny *e, char l []);
void fillFullStruct(struct bunny *e, char l []);
void structFromFile(FILE *f,struct bunny *versenyzok [], int *length);
void fullStructFromFile(FILE *f,struct bunny *versenyzok [], int *length);
int structForChild1(struct bunny *versenyzok [],struct bunny versenyzokChild1 [], int length);
int structForChild2(struct bunny *versenyzok [],struct bunny versenyzokChild2 [], int length);
void sendToChild(struct bunny versenyzokChild1 [],int lengthChild1, int fdp1 []);
void sendToParent(struct bunny *versenyzokChild [], int lengthChild, int fdc []);
void makeStructChild(struct bunny *versenyzokChild [], int length, int fdp [], char *filename);
void runCompetition(struct bunny *versenyzokChild [], int length);
void calculaeMaximum(struct bunny *vegzettek [],int length);


//bead1
int isNumber(char s[]);
int isInArray(char *s);
void trimTrailing(char *str);
int listLines (FILE *f, int *i);
int isEmpty(const char *s);
void removeEmptyLines(FILE *fp);
void validateSignUp(char *sol);

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
        printf("6. Locsolókirály kiválasztása\n");
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
            case 6:
                srand(time(NULL));
                if(startRace(f) == 0) return 0;
                else break;
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
void signUp(FILE *f){
    char sol [120] = "\n";
    validateSignUp(sol); 
    fprintf(f, "%s", sol);
    printf("Jelentkezés sikeres!\n");
    printf("---------------------------------------------------\n");
    fclose(f);
    f = fopen("data.txt","a+");
}

//Changes a line from the data.txt file with valid data from the stdin
void changeData(FILE *f){
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
void deleteData(FILE *f){
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
void listAll(FILE *f) {
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
void listFilter(FILE *f){
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
    char disName [2]= ",";
    strcat(district, ",");
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

//Starts the race and calculates the SprinklerKing
int startRace(FILE *f){
    int fdp1[2];
    int fdp2[2];
    int fdc1[2];
    int fdc2[2];
    if (pipe(fdp1) == -1) {
        fprintf(stderr, "Pipe 1 Failed");
        return 1;
    }else if (pipe(fdp2) == -1) {
        fprintf(stderr, "Pipe 2 Failed");
        return 1;
    }else if (pipe(fdc1) == -1) {
        fprintf(stderr, "Pipe 3 Failed");
        return 1;
    }else if (pipe(fdc2) == -1) {
        fprintf(stderr, "Pipe 4 Failed");
        return 1;
    }
    pid_t  child1 = fork();
    if (child1 < 0){perror("A fork hívás sikertelen volt\n"); exit(1);}
    if (child1 > 0){
        pid_t  child2 = fork();
        if (child2 < 0){perror("A fork hívás sikertelen volt\n"); exit(1);}
        //PARENT
        if (child2 > 0){
            //puting data in structures
            struct bunny *versenyzok [100];
            int length = 0;
            structFromFile(f,versenyzok,&length);
            fclose(f);
            f = fopen("data.txt","a+");
            struct bunny versenyzokChild1 [100];
            int lengthChild1 = structForChild1(versenyzok, versenyzokChild1, length);
            struct bunny versenyzokChild2 [100];
            int lengthChild2 = structForChild2(versenyzok, versenyzokChild2, length);
            for(int i = 0; i < length; i++){
                free(versenyzok[i]);
            }
            //Sending data
            write(fdp1[1], &lengthChild1, sizeof(int));
            sendToChild(versenyzokChild1,lengthChild1, fdp1);
            write(fdp2[1], &lengthChild2, sizeof(int));
            sendToChild(versenyzokChild2,lengthChild2, fdp2);
            //Receiving data
            char *c1 = malloc(130 * lengthChild1);
            read(fdc1[0], c1, 130 * lengthChild1);
            char *c2 = malloc(130 * lengthChild2);
            read(fdc2[0], c2, 130 * lengthChild2);
            //Processing data
            struct bunny *vegzettek [100];
            int length2 = 0;
            strcat(c1,c2);
            FILE *f2 = fopen("temp.txt", "a+");
            fputs(c1,f2);
            free(c1); free(c2);
            fclose(f2);
            f2 = fopen("temp.txt", "a+");
            fullStructFromFile(f2,vegzettek,&length2);
            fclose(f2);
            remove("temp.txt");
            //Calculate Maximum
            calculaeMaximum(vegzettek, length);
            for(int i = 0; i < length; i++){
                free(vegzettek[i]);
            }
            return 1;
        }
        //CHILD2
        else {
            //Receiving data
            struct bunny *versenyzokChild2 [100] ;
            int length;
            int temp = 0;
            read(fdp2[0],&length, sizeof(int) );
            char *c = malloc(120 * length);
            read(fdp2[0], c, 120* length);
            //Processing data
            FILE *f2 = fopen("temp2.txt", "a+");
            fputs(c,f2);
            free(c);
            fclose(f2);
            f2 = fopen("temp2.txt", "a+");
            structFromFile(f2,versenyzokChild2,&temp);
            fclose(f2);
            remove("temp2.txt");
            runCompetition(versenyzokChild2, length);
            //Sending data
            sendToParent(versenyzokChild2, length, fdc2);
            for(int i = 0; i < length; i++){
                free(versenyzokChild2[i]);
            }
            return 0;
        }
    }
    //CHILD1
    else {
        //Receiving data
        struct bunny *versenyzokChild1 [100] ;
        int length;
        int temp = 0;
        read(fdp1[0],&length, sizeof(int) );
        char *c = malloc(120 * length);
        read(fdp1[0], c, 120* length);
        //Processing data
        FILE *f2 = fopen("temp1.txt", "a+");
        fputs(c,f2);
        free(c);
        fclose(f2);
        f2 = fopen("temp1.txt", "a+");
        structFromFile(f2,versenyzokChild1,&temp);
        fclose(f2);
        remove("temp1.txt");
        runCompetition(versenyzokChild1, length);
        //Sending data
        sendToParent(versenyzokChild1, length, fdc1);
        for(int i = 0; i < length; i++){
                free(versenyzokChild1[i]);
        }        
        return 0;
    }
}

//Fills a bunny type struct from a line divided by commas
void fillStruct(struct bunny *e, char l []){
    char name [50] = "";
    char distName [50] = "";
    char numStr [20] = "";
    int proc = 0;
    int index = 0;
    trimTrailing(l);
    while (index != strlen(l)){
        if (proc == 0 && l[index] != ',') strncat(name,&l[index],1);
        else if (proc == 1 && l[index] != ',')  strncat(distName,&l[index],1);
        else if (proc == 2 && l[index] != ',')  strncat(numStr,&l[index],1);
        else proc++;
        index++;
    }
    e->name = strdup(name);
    e->distName = strdup(distName);
    e->signUpNum = atoi(numStr);
}

//Fills a bunny type (with scores) struct from a line divided by commas
void fillFullStruct(struct bunny *e, char l []){
    char name [50] = "";
    char distName [50] = "";
    char numStr [20] = "";
    char scoreStr [20] = "";
    int proc = 0;
    int index = 0;
    trimTrailing(l);
    while (index != strlen(l)){
        if (proc == 0 && l[index] != ',') strncat(name,&l[index],1);
        else if (proc == 1 && l[index] != ',')  strncat(distName,&l[index],1);
        else if (proc == 2 && l[index] != ',')  strncat(numStr,&l[index],1);
        else if (proc == 3 && l[index != ','])  strncat(scoreStr,&l[index],1);
        else proc++;
        index++;
    }
    e->name = strdup(name);
    e->distName = strdup(distName);
    e->signUpNum = atoi(numStr);
    e->score = atoi(scoreStr);
}

//Fills an array of bunnies from a file using fillStruct()
void structFromFile(FILE *f,struct bunny *versenyzok [], int *length){
    char line [160];
    while (!feof(f)){
        if (fgets(line,sizeof(line),f) != NULL  ){
            struct bunny *e = (struct bunny*)malloc(sizeof(struct bunny));
            fillStruct(e, line);
            versenyzok[*length] = e;
            (*length)++;
        }
    }
}

void fullStructFromFile(FILE *f,struct bunny *versenyzok [], int *length){
    char line [160];
    while (!feof(f)){
        if (fgets(line,sizeof(line),f) != NULL  ){
            struct bunny *e = (struct bunny*)malloc(sizeof(struct bunny));
            fillFullStruct(e, line);
            versenyzok[*length] = e;
            (*length)++;
        }
    }
}

//Lists a bunny array into another by: Barátfa, Lovas, Kígyós-patak, Káposztás kert
int structForChild1(struct bunny *versenyzok [],struct bunny versenyzokChild1 [], int length){
    int lengthChild1 = 0;
    for(int i = 0; i < length; i++){
        char* str = versenyzok[i]->distName;
        trimTrailing(str);
        if (strcmp("Barátfa", str) == 0 || strcmp("Lovas", str) == 0
            || strcmp("Kígyós-patak", str) == 0 || strcmp("Káposztás kert", str) == 0 ){
            versenyzokChild1[lengthChild1].distName = versenyzok[i]->distName;
            versenyzokChild1[lengthChild1].signUpNum = versenyzok[i]->signUpNum;
            versenyzokChild1[lengthChild1].name =versenyzok[i]->name;
            lengthChild1++;
        }
    }
    return lengthChild1;
}

//Lists a bunny array into another by: Szula, Malom telek, Páskom
int structForChild2(struct bunny *versenyzok [],struct bunny versenyzokChild2 [], int length){
    int lengthChild2 = 0;
    for(int i = 0; i < length; i++){
        char* str = versenyzok[i]->distName;
        trimTrailing(str);
        if (strcmp("Szula", str) == 0 || strcmp("Malom telek", str) == 0 || strcmp("Páskom", str) == 0){
            versenyzokChild2[lengthChild2].distName = versenyzok[i]->distName;
            versenyzokChild2[lengthChild2].signUpNum = versenyzok[i]->signUpNum;
            versenyzokChild2[lengthChild2].name =versenyzok[i]->name;
            lengthChild2++;
        }
    }
    return lengthChild2;
}

//Sends the data in a string through a pipe for the child
void sendToChild(struct bunny versenyzokChild [],int lengthChild, int fdp []){
    char *sol = malloc(120 * lengthChild);
    strcpy(sol, "");
    for (int i = 0 ; i< lengthChild; i++){
        char *str = malloc (120);
        strcpy(str,versenyzokChild[i].name);
        strcat(str,",");
        strcat(str,versenyzokChild[i].distName);
        strcat(str,",");
        char num[20];
        sprintf(num,"%d",versenyzokChild[i].signUpNum);
        strcat(str, num);
        strcat(sol, str);
        strcat(sol, "\n");
        free(str);
    }
    write(fdp[1], sol, 120*lengthChild);
    free(sol);     
}

//Send the data back in a string through a pipe for the parent
void sendToParent(struct bunny *versenyzokChild [], int lengthChild, int fdc []){
    char *sol = malloc(130 * lengthChild);
    strcpy(sol, "");
    for (int i = 0 ; i < lengthChild; i++){
        char *str = malloc (130);
        strcpy(str,versenyzokChild[i]->name);
        strcat(str,",");
        strcat(str,versenyzokChild[i]->distName);
        strcat(str,",");
        char num[20];
        sprintf(num,"%d",versenyzokChild[i]->signUpNum);
        strcat(str, num);
        strcat(str,",");
        char score[20];
        sprintf(score,"%d",versenyzokChild[i]->score);
        strcat(str, score);
        strcat(sol, str);
        strcat(sol, "\n");
        free(str);
    }
    write(fdc[1], sol, 130*lengthChild);
    free(sol);
}

//Runs the competition for the child (generates random number for all the bunnies)
void runCompetition(struct bunny *versenyzokChild [], int length){
    for(int i = 0; i < length; i++){
        versenyzokChild[i]->score = (rand() % 100)+1;
    }
}

//Calculates the bunny who got the maximum amount of eggs :)
void calculaeMaximum(struct bunny *vegzettek [],int length){
    printf("\n");
    int maximum = vegzettek[0]->score;
    char* name = malloc(50);
    char* district = malloc(50);
    name = vegzettek[0]->name;
    district = vegzettek[0]->distName;
    for(int i = 1; i < length; i++){
        int szam =  vegzettek[i]->score;
        if ( szam > maximum ){
            maximum = szam;
            name = vegzettek[i]->name;
            district = vegzettek[i]->distName;
        }
    }
    printf("A győztes: %s %s-ból aki %d tojást gyűjtött\n", name, district, maximum);
    printf("\n");
    printf("---------------------------------------------------\n");
    free(name);
    free(district);
}

//Checks if the string is a valid positive whole number.
int isNumber(char s[]){
    for (int i = 0; s[i]!= '\0'; i++){
        if (isdigit(s[i]) == 0) return 0;
    }
    return 1;
}

//Checks if the distArr contains the string.
int isInArray(char *s){
    int len = sizeof(distArr)/sizeof(distArr[0]);
    int i;
    for(i = 0; i < len; ++i){
        if(strcmp(distArr[i], s) == 0) return 1;
    }
    return 0;
}

//Trims the whitespaces from the end of the file.
void trimTrailing(char *str){
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
int listLines (FILE *f, int *i){
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
void removeEmptyLines(FILE *fp) {
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
void validateSignUp(char *sol){
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
    strcat(name, ","); strcat(district, ",");
    strcat(sol,strcat(strcat(name,district),regNumStr));
}