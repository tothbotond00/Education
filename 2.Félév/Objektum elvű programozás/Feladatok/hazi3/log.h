#ifndef LOG_H_INCLUDED
#define LOG_H_INCLUDED

class Log
{
public:
    virtual ~Log() {}
    virtual int getSize() { return 0; }
};

#endif // LOG_H_INCLUDED
