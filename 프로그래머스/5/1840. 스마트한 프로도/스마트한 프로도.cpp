#include <string>
#include <vector>
#include <iostream>
#include <algorithm>
using namespace std;
struct node;
struct edge
{
    int num;
    int type=0;//1:m0 2:mt 3:m0&mt
    node *node1;
    node *node2;
    int enabled=true;
    bool isadjacent(edge* e2)
    {
        if(node1==e2->node1||node2==e2->node1||node1==e2->node2||node2==e2->node2)
        {
            return true;
        }
        else
            return false;
    }
};
struct node
{
    int num;
    vector<edge*> edges;  
};
struct edge_set
{
    vector<edge*> edges;
    bool isatomic()
    {
        return edges.size()==1;
    }

    bool iscycle()
    {
        if(edges.size()<=2)
            return false;
        edge* front = edges.front();
        edge* back = edges.back();
        if(front->isadjacent(back))
            return true;
        else
            return false;
    }
    int count_mt_m0()
    {
        int r = 0;
       for(int i=0;i<edges.size();i++)
       {
           if(edges[i]->type==1)
               r--;
           else
               r++;
       }
        return r;
    }
    vector<vector<int>> process()
    {
        vector<vector<int>> result;
        if(iscycle())
        {
            if(edges.front()->type==2)
            {
                edges.push_back(edges.front());
                edges.erase(edges.begin());
            }
        }
        else if(edges.front()->type==1)
        {
            reverse(edges.begin(),edges.end());
        }
        for(int i=0;i<edges.size();i++)
        {
            edge* e = edges[i];
            if(e->type==2)
            {
                if(i+1==edges.size())//end point
                    result.push_back({1,e->num});//add
            }
            else
            {
                result.push_back({0,e->num});//erase
                if(i-1>=0)
                {
                    result.push_back({1,edges[i-1]->num});
                }
            }
        }

        return result;
    }
    void print_pattern()
    {
        for(int i=0;i<edges.size();i++)
        {
            cout<<edges[i]->type<<endl;
        }
    }
    void expand()
    {
        node* n1 = edges[0]->node1;
        node* n2 = edges[0]->node2;

        for(int i=0;i<n1->edges.size();i++)
        {
            edge* e = n1->edges[i];

            if(e->type==1||e->type==2)
            {
                if(!e->enabled)
                    continue;
                else
                    e->enabled = false;

                edges.insert(edges.begin(),e);
                if(e->node1==n1)
                    n1 = e->node2;
                else
                    n1 = e->node1;
                i=-1;
            }
        }

        for(int i=0;i<n2->edges.size();i++)
        {
            edge* e = n2->edges[i];
            if(e->type==1||e->type==2)
            {
                if(!e->enabled)
                    continue;
                else
                    e->enabled = false;

                edges.insert(edges.end(),e);
                if(e->node1==n2)
                    n2 = e->node2;
                else
                    n2 = e->node1;
                i=-1;
            }
        }

    }
};
bool cmp(edge_set* s1, edge_set* s2)
{
    return s1->count_mt_m0()>s2->count_mt_m0();
}
vector<vector<int>> solution(int n, int m, vector<int> a, vector<int> b, int k, int m1, int m2, vector<int> e1, vector<int> e2) {
    vector<vector<int>> answer;

    vector<node*> nodes;
    vector<edge*> edges;
    for(int i=0;i<n;i++)
    {
        node * n = new node();
        n->num = i+1;
        nodes.push_back(n);
    }

    for(int i=0;i<m;i++)
    {
        edge* e = new edge();
        e->num = i+1;
        edges.push_back(e);
        e->node1 = nodes[a[i]-1];
        e->node2 = nodes[b[i]-1];
        nodes[a[i]-1]->edges.push_back(e);
        nodes[b[i]-1]->edges.push_back(e);
    }

    for(int i=0;i<m1;i++)
    {
        edges[e1[i]-1]->type = 1;
    }
    for(int i=0;i<m2;i++)
    {
        edges[e2[i]-1]->type += 2;
    }

    vector<edge_set*> es;

    int i = 0;
    while(i<edges.size())
    {
        edge* e  = edges[i];
        i++;
        if((!e->enabled)||e->type==0||e->type==3)
        {

            continue;
        }
        e->enabled = false;
        edge_set* eset  = new edge_set();
        es.push_back(eset);
        eset->edges.push_back(e);
        eset->expand();
    }

    /*for(int i=0;i<es.size();i++)
    {
        es[i]->print_pattern();
    }*/
    sort(es.begin(),es.end(),cmp);

    for(int i=0;i<es.size();i++)
    {
        auto temp = es[i]->process();
        answer.insert(answer.end(),temp.begin(),temp.end());
        /*for(auto i : temp)
        {
            cout<<i[0]<<" "<<i[1]<<endl;
        }*/
    }

    return answer;
}