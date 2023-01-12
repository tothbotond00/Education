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

    Interval(int startHours, int startMinutes, int endHours, int endMinutes)
    {
        start = startHours * 60 + startMinutes;
        end = endHours * 60 + endMinutes;
    }

    void WriteOut()
    {
        int start_hours = start / 60;
        int start_minutes = start % 60;
        int end_hours = end / 60;
        int end_minutes = end % 60;

        cout << to_string(start_hours) << " " << to_string(start_minutes) << " " << to_string(end_hours) << " " << to_string(end_minutes);
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

struct Entry
{
    string direction;
    string user;
    int hours;
    int minutes;
};

struct Log
{
    list<Entry> entries;

    Entry* FindLastUserEntry(string user)
    {
        for(list<Entry>::reverse_iterator rit = entries.rbegin(); rit != entries.rend(); ++rit)
        {
            if (rit->user == user)
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
    string direction, user;
    int hours, minutes;
    while(!cin.eof())
    {
        cin >> direction;
        cin >> user;
        cin >> hours;
        cin >> minutes;

        Entry *lastUserEntry = log.FindLastUserEntry(user);

        if (lastUserEntry == NULL)
        {
            if (direction == "KI")
            {
                Interval i = Interval(0, 0, hours, minutes);
                intervals.Add(i);
            }
        }
        else
        {
            if (lastUserEntry->direction == direction)
            {
                continue;
            }
            else if (direction == "KI")
            {
                Interval i = Interval(lastUserEntry->hours, lastUserEntry->minutes, hours, minutes);
                intervals.Add(i);
            }
        }

        Entry e = {direction, user, hours, minutes};
        log.entries.push_back(e);
    }
    intervals.WriteOut();
    Entry *lastUserEntry = log.FindLastUserEntry(user);
    if(lastUserEntry->direction == "BE")
    {
        cout<<" "<<hours<<" "<<minutes<<" "<<23<<" "<<59;
    }
    cout<<" "<<endl;

    return 0;
}
