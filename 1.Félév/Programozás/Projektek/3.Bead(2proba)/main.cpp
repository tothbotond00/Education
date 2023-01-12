#include <iostream>
#include <list>
#include <functional>

using namespace std;

class Interval
{
public:
    int start;
    int end;

    Interval(int start, int end)
        : start(start), end(end)
    {
    }

    Interval(int startOra, int startPerc, int endOra, int endPerc)
    {
        start = startOra * 60 + startPerc;
        end = endOra * 60 + endPerc;
    }

    void WriteOut()
    {
        int start_ora = start / 60;
        int start_perc = start % 60;
        int end_ora = end / 60;
        int end_perc = end % 60;

        cout << to_string(start_ora) << " " << to_string(start_perc) << " " << to_string(end_ora) << " " << to_string(end_perc);
    }
};

class Intervals
{
private:
    list<Interval> inner_list;

    bool Intersects(Interval a, Interval b)
    {
        return a.start <= b.end && a.end >= b.start;
    }

    Interval Merge(Interval a, Interval b)
    {
        return Interval(min(a.start, b.start), max(a.end, b.end));
    }

public:
    void Add(Interval i)
    {
        list<Interval> new_list;
        for(list<Interval>::iterator it = inner_list.begin(); it != inner_list.end(); ++it)
        {
            if (Intersects(*it, i))
            {
                i = Merge(*it, i);
            }
            else
            {
                new_list.push_back(*it);
            }
        }
        new_list.push_back(i);
        inner_list = new_list;
    }

    void WriteOut()
    {
        if(inner_list.size()!=0)
        {
            cout << inner_list.size();
        }else   cout<< 1;
        for(list<Interval>::iterator it = inner_list.begin(); it != inner_list.end(); ++it)
        {
            cout << " ";
            it->WriteOut();
        }
    }
};

struct Bemenet
{
    string beki;
    string azon;
    int ora;
    int perc;
};

struct Log
{
    list<Bemenet> entries;

    Bemenet* FindLastAzonBemenet(string azon)
    {
        for(list<Bemenet>::reverse_iterator rit = entries.rbegin(); rit != entries.rend(); ++rit)
        {
            if (rit->azon == azon)
            {
                return &*rit;
            }
        }

        return NULL;
    }
};

int main()
{
    int n;
    if (!cin.eof())
    {
        cin >> n;
    }

    Log log;
    Intervals intervals;
    string beki, azon;
    int ora, perc;
    while(!cin.eof())
    {
        cin >> beki;
        cin >> azon;
        cin >> ora;
        cin >> perc;

        Bemenet *lastAzonBemenet = log.FindLastAzonBemenet(azon);

        if (lastAzonBemenet == NULL)
        {
            if (beki == "KI")
            {
                Interval i = Interval(0, 0, ora, perc);
                intervals.Add(i);
            }
        }
        else
        {
            if (lastAzonBemenet->beki == beki)
            {
                continue;
            }
            else if (beki == "KI")
            {
                Interval i = Interval(lastAzonBemenet->ora, lastAzonBemenet->perc, ora, perc);
                intervals.Add(i);
            }
        }
        Bemenet e = {beki, azon, ora, perc};
        log.entries.push_back(e);
    }
    intervals.WriteOut();
    Bemenet *lastAzonBemenet = log.FindLastAzonBemenet(azon);
    if(lastAzonBemenet->beki == "BE")
    {
        cout<<" "<<ora<<" "<<perc<<" "<<23<<" "<<59;
    }
    cout<<" "<<endl;
    return 0;
}

