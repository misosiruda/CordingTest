function solution(brown, yellow) {
    var line = 1;
    while((brown-line*2)/2-2 !== yellow/line) line ++;
    return [yellow/line+2,line+2];
}