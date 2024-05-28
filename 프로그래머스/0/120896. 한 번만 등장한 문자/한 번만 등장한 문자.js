function solution(s) {
    const arr = s.split("").sort()
    const resultArr = []
    const blackList = []

    for(let i = 0; i < arr.length; i++){
        if(resultArr.indexOf(arr[i]) === -1 && blackList.indexOf(arr[i]) === -1) {
            resultArr.push(arr[i])
        } else if(blackList.indexOf(arr[i]) === -1) {
            console.log(resultArr.indexOf(arr[i]), arr[i])
            resultArr.splice(resultArr.indexOf(arr[i]), 1)
            blackList.push(arr[i])
        }

    }
    return resultArr.join("")
}