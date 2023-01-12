#pragma once

class Noveny
{
public:
    virtual ~Noveny() {}
    virtual bool is_Haszon() { return false; }
    virtual bool is_Virag() { return false; }
    int getEresiIdo() const { return eresiIdo; }
protected:
    int eresiIdo;
    Noveny(int ido) : eresiIdo(ido) {}
};

class Haszon : public Noveny
{
public:
    bool is_Haszon() override { return true; }
protected:
    Haszon(int i) : Noveny(i) {}
};

class Virag : public Noveny
{
public:
    bool is_Virag() override { return true; }
protected:
    Virag(int i) : Noveny(i) {}
};

class Hagyma : public Haszon
{
public:
    static Hagyma* peldany();
private:
    Hagyma() : Haszon(4) {}
    static Hagyma* _peldany;
};

class Burgonya : public Haszon
{
public:
    static Burgonya* peldany();
private:
    Burgonya() : Haszon(5) {}
    static Burgonya* _peldany;
};

class Borso : public Haszon
{
public:
    static Borso* peldany();
private:
    Borso() : Haszon(3) {}
    static Borso* _peldany;
};

class Tulipan : public Virag
{
public:
    static Tulipan* peldany();
private:
    Tulipan() : Virag(7) {}
    static Tulipan* _peldany;
};

class Szegfu : public Virag
{
public:
    static Szegfu* peldany();
private:
    Szegfu() : Virag(10) {}
    static Szegfu* _peldany;
};

class Rozsa : public Virag
{
public:
    static Rozsa* peldany();
private:
    Rozsa() : Virag(8) {}
    static Rozsa* _peldany;
};
