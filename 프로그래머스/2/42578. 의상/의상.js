function solution(clothes)
{
    var dic = {
        add: function (str)
        {
            this[str] == null ? this[str] = 1 : this[str] += 1;
        }
    };
    clothes.map(a =>
    {
        let [name, kind] = a;
        dic.add(kind);
    });
    let answer = 1;
    for (e of Object.values(dic))
    {
        if (!isNaN(e))
        {
            answer = answer * (e + 1);
        }
    }
    return --answer;
}