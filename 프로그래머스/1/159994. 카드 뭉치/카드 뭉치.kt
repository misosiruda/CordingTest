class Solution {
    fun solution(cards1: Array<String>, cards2: Array<String>, goal: Array<String>): String {
        var answer: String = ""
        var i = 0
        var j = 0
        for (k in 0 until goal.size) {
            if (i == cards1.size) {
                if(cards2[j]==goal[k]) j++
                else {
                    return "No"
                }
            } else if (j == cards2.size) {
                if(cards1[i]==goal[k]) i++
                else {
                    return "No"
                }
            } else {
                if (cards1[i] == goal[k]) i++
                else if (cards2[j] == goal[k]) j++
                else {
                    return "No"
                }
            }
        }
        return "Yes"
    }
}