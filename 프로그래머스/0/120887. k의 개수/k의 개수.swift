import Foundation

func solution(_ i:Int, _ j:Int, _ k:Int) -> Int {
    let c = Character(String(k))
    return(Array(i...j)
    .flatMap { String($0) }
    .reduce(0) { curr, next in
    guard next == c else { return curr }
    return curr + 1})
}