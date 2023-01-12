const matrix = [[-1,-2,3], [-2,4,3]];

//17
console.log(matrix.filter(t => t.findIndex(s => s== 0) == -1).length)
//18
//flatMap használata laposít xd
console.log([].concat(...(matrix.map(t => t.filter(s => s < 0)))));