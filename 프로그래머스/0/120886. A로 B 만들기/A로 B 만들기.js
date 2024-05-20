function solution(before, after) {
    const a = after.split("")
    const b = before.split("")
    let c = ""

    for (let i = 0; i < a.length; i++){
        for (let j = 0; j < b.length; j++){
            if(a[i] === b[j]){
                c += a[i]
                b[j] = ""
                break;
            }
        }
    }

    console.log(after, c)
    return c === after ? 1 : 0
}