#include<bits/stdc++.h>
#include <iostream>
using namespace std;

struct Cmp {
    bool operator() (pair<int, int>& A, pair<int, int>& B)
    {
        if (A.first != B.first) return A.first < B.first;
        return A.second > B.second;
    }
};


int solution(vector<int> p, int k) {
    int res = 0;
    priority_queue<pair<int ,int>, vector<pair<int, int>>, Cmp> pq;
    for(int i = 0; i < p.size(); i++) pq.push(make_pair(p[i], i));
    int idx = -1;
    while(true) {
        pair<int, int> tmp = pq.top();
        if (idx > tmp.second) {
            tmp.second += p.size();
            pq.pop();
            pq.push(tmp);
            
        } else {
            res++;
            idx = tmp.second;
            if(tmp.second % p.size() == k) return res;
            pq.pop();
        }
    }

    return res;
}