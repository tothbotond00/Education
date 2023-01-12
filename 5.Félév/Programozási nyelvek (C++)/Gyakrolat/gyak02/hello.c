int main()
{
  printf( "Hello World!\n" );
  return 0;
}

//inlcude nélkül is működne de adna egy warningot

/*
hello.c: In function ‘main’:
hello.c:3:3: warning: implicit declaration of function ‘printf’ [-Wimplicit-function-declaration]
    3 |   printf( "Hello World!\n" );
      |   ^~~~~~
hello.c:1:1: note: include ‘<stdio.h>’ or provide a declaration of ‘printf’
  +++ |+#include <stdio.h>
    1 | int main()
hello.c:3:3: warning: incompatible implicit declaration of built-in function ‘printf’ [-Wbuiltin-declaration-mismatch]
    3 |   printf( "Hello World!\n" );
      |   ^~~~~~
hello.c:3:3: note: include ‘<stdio.h>’ or provide a declaration of ‘printf’

*/
