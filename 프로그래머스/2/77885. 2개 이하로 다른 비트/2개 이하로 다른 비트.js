function solution(numbers) {
    return numbers.map((n, i) => {
        const binary = n.toString(2).split('');
        const lastZeroIndex = binary.lastIndexOf('0');

        if (lastZeroIndex === -1) {
            // 111 => 1011
            binary.splice(1, 0, '0');
        } else if (binary.length - 1 === lastZeroIndex) {
            // 100 => 101
            binary[lastZeroIndex] = '1';
        } else {
            // 101 => 110
            [binary[lastZeroIndex], binary[lastZeroIndex + 1]] = ['1', '0']
        }
        return parseInt(binary.join(''), 2);
    });
}