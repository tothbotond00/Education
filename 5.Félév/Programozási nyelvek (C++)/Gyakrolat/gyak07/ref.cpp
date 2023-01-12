void f(int);
void g(int&);
void h(const int&);

int main()
{
    int a = 3;
    f(2);
    f(a);
    f(a + 6);
    f(a++);
    f(++a);

        //g(2);
    g(a);
        //g(a + 6);
        //g(a++);
    g(++a);

    h(2);
    h(a);
    h(a + 6);
    h(a++);
    h(++a);
}