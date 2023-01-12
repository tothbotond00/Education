template <class T>
class Vector
{
    int s, cap;
    T* p;
public:
    Vector(int);
    ~Vector();
    Vector(const Vector<T>& );
    Vector<T>& operator= (const Vector<T>& );
    void push_back(const T& t);
    int size() const { return s; }
    const T& operator[](int idx) const { return p[idx];}
    T& operator[](int idx) { return p[idx];}
};

template <class T>
Vector<T>::Vector(int i)
{
    s = 0;
    cap = i;
    p = new T[cap];
}

template <class T>
Vector<T>::~Vector()
{
    delete [] p;
}

template <class T>
void Vector<T>::push_back(const T& t)
{
    if (s == cap)
    {
        cap *= 2;
        T* q = new T[cap];
        for (int i = 0; i < s; i++)
        {
            q[i] = p[i];
        }
        delete [] p;
        p = q;
    }
    p[s++] = t;
}

template <class T>
Vector<T>::Vector(const Vector<T>& rhs)
{
    cap = rhs.cap;
    s = rhs.s;
    p = new T[cap];
    for (int i = 0; i < s; i++)
    {
        p[i] = rhs.p[i];
    }
}

template <class T>
Vector<T>& Vector<T>::operator= (const Vector<T>& rhs)
{
    if (this != &rhs)
    {
        delete [] p;
        cap = rhs.cap;
        s = rhs.s;
        p = new T[cap];
        for (int i = 0; i < s; i++)
        {
            p[i] = rhs.p[i];
        }
    }
    return *this;
}