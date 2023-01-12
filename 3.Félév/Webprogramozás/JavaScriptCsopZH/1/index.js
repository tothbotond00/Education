// Subtask a.
const str = "Lorem ipsum, dolor sit amet consectetur adipisicing elit. Cupiditate illum culpa ducimus modi. Aut itaque sunt, consectetur nam dolor hic maxime ipsa quidem tempore est repellat vero cumque at ipsam.";
console.log(str.split("").filter(v => v ==='m').length);

// Subtask b.
const matrix = [
  [
    {type: 'ground', visited: true},
    {type: 'ground', visited: true},
    {type: 'ground', visited: true},
  ],
  [
    {type: 'ground', visited: true},
    {type: 'water', visited: true},
    {type: 'water', visited: false},
  ],
  [
    {type: 'water', visited: true},
    {type: 'ground', visited: true},
    {type: 'ground', visited: true},
  ],
];

console.log(matrix.every(row => row.every(x=> x.visited )));

// Subtask c.
const results = {
  Search: [
    { title: 'game1', score: 4.65 },
    { title: 'game2', score: 3.22 },
    { title: 'game3', score: 6.78 },
    { title: 'game4', score: 5.92 },
    { title: 'game5', score: 3.99 },
    { title: 'game6', score: 7.65 },
    { title: 'game7', score: 2.56 },
    { title: 'game8', score: 7.11 },
    { title: 'game9', score: 6.54 },
    { title: 'game10', score: 4.82 },
    { title: 'game11', score: 6.18 },
    { title: 'game12', score: 4.33 },
  ],
};

console.log(results.Search.map(x => x.score).reduce((a,b) => a> b ? a : b,-Infinity));
