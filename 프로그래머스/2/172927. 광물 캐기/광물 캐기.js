let fatigues = [[1, 1, 1], [1, 1, 5], [1, 5, 25]];

function MakeGreed(picks, minerals)
{
    let greed = [];
    let allPicks = (picks[0] + picks[1] + picks[2]) * 5;
    let mLength = minerals.length;
    let dP = 0, iP = 0, sP = 0;
    let min = 0;
    if(allPicks < mLength) mLength = allPicks;
    for(let i=0;i<mLength;i++)
    {
        if(i%5 == 0 && i != 0)
        {
            greed.push([dP, iP, sP]);
            dP = 0, iP = 0, sP = 0;
        }
        if(minerals[i] == 'diamond')
        {
            min = 2;
            dP += fatigues[0][min];
            iP += fatigues[1][min];
            sP += fatigues[2][min];
        }
        else if(minerals[i] == 'iron')
        {
            min = 1;
            dP += fatigues[0][min];
            iP += fatigues[1][min];
            sP += fatigues[2][min];
        }   
        else
        {
            min = 0;
            dP += fatigues[0][min];
            iP += fatigues[1][min];
            sP += fatigues[2][min];
        }
    }
    greed.push([dP, iP, sP]);
    return greed;
}

function SearchGreed(greed, picks)
{
    greed.sort((a, b) => b[2] - a[2]);
    let cnt = 0;
    let fatigue = 0;
    for(let i=0;i<picks[0];i++)
    {
        if(cnt >= greed.length) return fatigue;
        fatigue += greed[cnt][0];
        cnt++;
    }
    for(let i=0;i<picks[1];i++)
    {
        if(cnt >= greed.length) return fatigue;
        fatigue += greed[cnt][1];
        cnt++;
    }
    for(let i=0;i<picks[2];i++)
    {
        if(cnt >= greed.length) return fatigue;
        fatigue += greed[cnt][2];
        cnt++;
    }
    return fatigue;
}

function solution(picks, minerals) 
{
    var answer = 0;
    let greed = MakeGreed(picks, minerals);
    answer = SearchGreed(greed, picks);
    return answer;
}