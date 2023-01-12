#ifndef DIRECTORY_H_INCLUDED
#define DIRECTORY_H_INCLUDED

#include "log.h"
#include <vector>

class Directory : public Log
{
public:
    Directory() {}
    ~Directory()
    {
        for (Log* l : logs){
            delete l;
        }
    }
    Directory(std::vector<Log*> l)
    {
        for (unsigned int i =0; i< l.size(); i++){
            logs.push_back(l[i]);
        }
    }
    int getSize() override
    {
        int s = 0;
        for (Log* l : logs ){
            s+=l->getSize();
        }
        return s;
    }
    void addLog(Log* l)
    {
        logs.push_back(l);
    }
protected:
    std::vector<Log*> logs;
};

#endif // DIRECTORY_H_INCLUDED
