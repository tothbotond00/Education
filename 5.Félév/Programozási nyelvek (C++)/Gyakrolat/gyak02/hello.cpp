#include <cstdio>

int main()
{
    std::printf("Hello World\n");
    return 0;
}

//include itt nem elhagyható

/*
hello.cpp: In function ‘int main()’:
hello.cpp:5:10: error: ‘printf’ is not a member of ‘std’
    5 |     std::printf("Hello World\n");
      |          ^~~~~~
*/

//using namespace std; -> nem segít :/