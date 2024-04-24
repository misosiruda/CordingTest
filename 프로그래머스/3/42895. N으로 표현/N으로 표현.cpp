#include <string>
#include <vector>
#include <cmath>

using namespace std;

vector<int> list;
int count = 0;
int minCount = 9;

void BFS(int N, int number)
{
    if(count > 8) return;
    if(list.size() >= minCount) return;
    int lastNumber = (list.size() == 0) ? 0 : list.back();

    if(lastNumber == number) 
    { 
        minCount = min(count, minCount); 
        return; 
    }
    
    int n = 0; 
    int addCount = 0;

    for(int c = 1; c <= 10000000; c *= 10) 
    { 
        addCount++;
        //if(count + addCount >= minCount) continue;
        n += (N * c); 
        count += addCount; 
        
        list.push_back(lastNumber + n); 
        BFS(N, number); list.pop_back();
        
        if(lastNumber - n != 0) 
        { 
            list.push_back(lastNumber - n);
            BFS(N, number);
            list.pop_back(); 
        }
        
        list.push_back(lastNumber * n);
        BFS(N, number); 
        list.pop_back();

        if(lastNumber / n != 0) 
        { 
            list.push_back(lastNumber / n);
            BFS(N, number); 
            list.pop_back(); 
        }
        count -= addCount;
    }
    return;
}

int solution(int N, int number) {
    BFS(N, number);
    return minCount < 9 ? minCount : -1;
}