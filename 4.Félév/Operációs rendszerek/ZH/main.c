#include <time.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>     //fork
#include <sys/wait.h>   //waitpid, signal
#include <errno.h>      //errno
#include <signal.h>     //signal
#include <sys/types.h>  //signal
#include <stddef.h>     // NULL
#include <sys/msg.h>    // msgsnd, msgrcv
#include <string.h>     // strlen

struct uzenet { 
    long mtype;
    char mtext [ 1024 ];
};

const char kerultek [7] [20] = {"Barátfa", "Lovas", "Szula", "Kígyós-patak", "Malom telek", "Páskom" , "Káposztás"};

///FUNCTIONS
void handler(int sign);
int szignalkuldes(pid_t kinek, int mit);
int uzenetsor_letrehoz(key_t kulcs);
void uzenet_kuld(int uzenetsor, const char message[1024]);
void numOverSignal(int signal);

///MAIN
int main(int argc,char** argv){

    pid_t tapsi = -1;
    pid_t fules = -1;

    key_t kulcs = ftok(argv[0], 1);

    int tapsi_uzenet = uzenetsor_letrehoz(kulcs);
    int fules_uzenet = uzenetsor_letrehoz(kulcs);

    signal(SIGUSR1, handler);

    tapsi = fork();

    if (tapsi == 0){
        ///TAPSI

        signal(SIGRTMIN,   numOverSignal);
        signal(SIGRTMIN+1, numOverSignal);

        //singal
        sleep(1);
        szignalkuldes(getppid(), SIGUSR1);

        //kerulet fogadas
        char kerulet [20];
        struct uzenet uz;
        int status; 
        status = msgrcv(tapsi_uzenet, &uz, 1024, 5, 0 );
        strcpy(kerulet,uz.mtext);
        printf("Tapsi vagyok, a kerületem: %s\n",kerulet);

        //nepszamlalas
        sleep(1);
        srand(time(NULL) + getppid());
        int nyuszik = (rand() % 51) + 50;
        char nyuszikStr [5];
        sprintf(nyuszikStr, "%d", nyuszik);
        uzenet_kuld(tapsi_uzenet, nyuszikStr);

        //korrigalas a szamlalasban BEFEJEZETLEN
        pause();

        exit(0);
    }

    fules = fork();
    if(fules == 0){
        ///FÜLES

        signal(SIGRTMIN, numOverSignal);
        signal(SIGRTMIN+1, numOverSignal);

        //signal 
        sleep(2);
        szignalkuldes(getppid(), SIGUSR1);

        //kerulet fogadas
        char kerulet [20];
        struct uzenet uz;
        int status; 
        status = msgrcv(fules_uzenet, &uz, 1024, 5, 0 );
        strcpy(kerulet,uz.mtext);
        printf("Füles vagyok, a kerületem: %s\n",kerulet);

        //nepszamlalas
        sleep(2);
        srand(time(NULL) + getppid());
        int nyuszik = (rand() % 51) + 50;
        char nyuszikStr [5];
        sprintf(nyuszikStr, "%d", nyuszik);
        uzenet_kuld(fules_uzenet, nyuszikStr);

        //korrigalas a szamlalasban BEFEJEZETLEN
        pause();
        
        exit(0);
    }
    ///FŐNYUSZI

    //singal
    pause();
    pause();
    printf("Tapsi és Füles is készen áll!\n");

    //kerulet fogadas
    srand(time(NULL));
    int r1 = rand() % 7;
    int r2 = r1;
    while ( r2 == r1){
        r2 = rand() % 7;
    }
    
    uzenet_kuld(tapsi_uzenet, kerultek[r1]);
    uzenet_kuld(fules_uzenet, kerultek[r2]);

    //nepszamlalas
    char szam1 [20];
    struct uzenet uz1;
    int status1; 
    status1 = msgrcv(tapsi_uzenet, &uz1, 1024, 5, 0 );
    strcpy(szam1,uz1.mtext);
    int tapsi_ker = atoi(szam1);

    char szam2 [20];
    struct uzenet uz2;
    int status2; 
    status2 = msgrcv(fules_uzenet, &uz2, 1024, 5, 0 );
    strcpy(szam2,uz2.mtext);
    int fules_ker = atoi(szam2);

    printf("Eredmények - %s : %d és %s : %d\n", kerultek[r1], tapsi_ker, kerultek[r2] ,fules_ker);

    int tavalyi1 = (rand() % 51) + 50;
    int tavalyi2 = (rand() % 51) + 50;

    //korrigalas a szamlalasban BEFEJEZETLEN

    if (tavalyi1 > tapsi_ker) { 
        printf("%s-ban nyuszifogyás történt(tavaly: %d most : %d)\n",kerultek[r1],tavalyi1,tapsi_ker );
        szignalkuldes(tapsi, SIGRTMIN);
    }
    else {
        printf("%s-ban nyuszigyaraposdás történt(tavaly: %d most : %d)\n",kerultek[r1],tavalyi1,tapsi_ker );
        szignalkuldes(tapsi, SIGRTMIN+1);
    }
    if (tavalyi2 > fules_ker){ 
        printf("%s-ban nyuszifogyás történt(tavaly: %d most : %d)\n",kerultek[r2],tavalyi2,fules_ker );
        szignalkuldes(fules, SIGRTMIN);
    }
    else {
        printf("%s-ban nyuszigyaraposdás történt(tavaly: %d most : %d)\n",kerultek[r2],tavalyi2,fules_ker );
        szignalkuldes(fules, SIGRTMIN+1);
    }

    wait(NULL);
    wait(NULL);
    printf("A főnyuszi vagyok, Tapsi és Füles is végett, jöhet a pihenés!!\n");
    return 0;
}

///FUNCTION DETAILS
void handler(int sign) { printf("Főnuyszi jelzést kapott!\n"); }

void numOverSignal(int signal) {
    signal = signal - SIGRTMIN;
    if (signal == 0) {
        // SIGRTMIN Nuszifogyás
        printf("%d: Keress_meg\n", getpid());
    } else {
        // SIGRTMIN+1 Nuyzsinövekedés
        printf("%d: Hazaterhet\n", getpid());
    }
}

int szignalkuldes(pid_t kinek, int mit) { return kill(kinek, mit); }

int uzenetsor_letrehoz(key_t kulcs){
    int uzenetsor = msgget(kulcs, 0600 | IPC_CREAT);
    if (uzenetsor < 0){
        printf("Hiba az %d uzenetsor letrehozaskor", uzenetsor);
        exit(EXIT_FAILURE);
    }
    return uzenetsor;
}

void uzenet_kuld(int uzenetsor, const char message[1024]) { 
    struct uzenet uz;
    uz.mtype = 5,
    strcpy(uz.mtext, message);
    int status; 
    status = msgsnd( uzenetsor, &uz, strlen ( uz.mtext ) + 1 , 0 ); 
    if (status < 0){
        perror("msgsnd");
    }
}