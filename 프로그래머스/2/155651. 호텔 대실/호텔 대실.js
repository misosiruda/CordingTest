function solution(book_time) {
  const times = book_time.map((v, idx) => {
    const [start, end] = v;

    const parsedStart = start.split(":").map((v2, i) => {
      if (i === 0) {
        if (v2 === "00" || v2 === "0") return 24 * 60;
        return Number(v2) * 60;
      }

      return Number(v2)
    }).reduce((acc, value) => acc + value);

    const parsedEnd = end.split(":").map((v2, i) => {
      if (i === 0) {
        if (v2 === "00" || v2 === "0") return 24 * 60;
        return Number(v2) * 60;
      }

      return Number(v2)
    }).reduce((acc, value) => acc + value) + 10;

    return [parsedStart, parsedEnd]
  });

  const sorted_times = times.sort((a, b) => a[0] - b[0]);

  const rooms = []
  let cnt = 0;

  for (let i = 0; i < sorted_times.length; i++) {
    const [nStart, nEnd] = sorted_times[i];

    if (rooms.length <= 0) {
      rooms.push(sorted_times[i]);
      cnt++;
    }else {
      let flag = false;

      for (let k = 0; k < rooms.length; k ++) {
        const [rStart, rEnd] = rooms[k];

        if (rEnd <= nStart) {
          rooms[k] = sorted_times[i];
          flag=true
          break;
        }
      }

      if (!flag) {
        rooms.push(sorted_times[i]);
        cnt++;
      }
    }
  }  

  return rooms.length;
}