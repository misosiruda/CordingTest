class Solution {
fun solution(n: Int, lost: IntArray, reserve: IntArray): Int {
    var answer = 0
    var len = 0
    var lostLen = 0
    val numArray = IntArray(n){1}
    var lost = lost.sorted()
    var reserve = reserve.sorted()

    for(i in 1..n){
        if(len >= reserve.size){
            len = 0
        }
        if(lostLen >= lost.size){
            lostLen = 0
        }
        if(reserve[len] == i){
            numArray[reserve[len] - 1] += 1

            len++
        }
        if(lost[lostLen] == i){
            numArray[lost[lostLen] - 1] -= 1
            lostLen++
        }
    }

    println(numArray.contentToString())
    for(i in 0 until numArray.size){
        if(i == 0){
            if(numArray[i] == 2 && numArray[1] == 0){
                numArray[i] -= 1
                numArray[1] += 1
            }
            continue
        }
        if(i == numArray.size-1){
            if(numArray[i] == 2 && numArray[i - 1] == 0){
                numArray[i] -= 1
                numArray[i - 1] += 1
            }
            continue
        }

        if(numArray[i] == 2 && numArray[i - 1] == 0){
            numArray[i] -= 1
            numArray[i - 1] += 1
        }else if(numArray[i] == 2 && numArray[i + 1] == 0){
            numArray[i] -= 1
            numArray[i + 1] += 1
        }
    }
    println(numArray.contentToString())
    answer = numArray.filter { it >= 1 }.size


    return answer
}
}