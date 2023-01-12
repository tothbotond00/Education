#include <iostream>
#include "file.h"
#include "system.h"
#include "directory.hpp"
#include <vector>

using namespace std;

int main()
{
    Directory* root = new Directory();
    System* system = new System(root);
    File* file1 = new File(20);
    File* file2 = new File(50);
    Directory* dir = new Directory();
    File* file3 = new File(40);
    dir ->addLog(file3);
    system->root->addLog(dir);
    system->root->addLog(file1);
    system->root->addLog(file2);
    vector<Log*> logs;
    File* file4 = new File(30);
    logs.push_back(file4);
    Directory* dir2 = new Directory(logs);
    system->root->addLog(dir2);
    cout<<dir2->getSize()<<endl;
    cout<<dir->getSize()<<endl;
    cout<<root->getSize()<<endl;
    delete system;
    ///Works perfectly
    return 0;
}
