const placesInput = document.querySelector('#places')
const speciesInput = document.querySelector('#species')
const button = document.querySelector('#btn-generate')
const tableContainer = document.querySelector('#table-container')
const task1 = document.querySelector('#task-1')
const task2 = document.querySelector('#task-2')
const task3 = document.querySelector('#task-3')
const task4 = document.querySelector('#task-4')
const task5 = document.querySelector('#task-5')

let matrix = []

button.addEventListener('click', onGenerate)
function onGenerate(e) {
  const n = placesInput.valueAsNumber
  const m = speciesInput.valueAsNumber

  matrix = generateMatrix(n, m)
  console.log(matrix);

  tableContainer.innerHTML = genTable(matrix)
  tasks()
}
function generateMatrix(n, m) {
  const matrix = []
  for(let i = 0; i<n; i++) {
    const row = []
    for(let j = 0; j<m; j++) {
      row.push(0)
    }
    matrix.push(row)
  }
  return matrix
}
function genTable(matrix) {
  return `
    <table>
      ${matrix.map(row => `
        <tr>
          ${row.map(value => `<td>${value}</td>`).join('')}
        </tr>
      `).join('')}
    </table>
  `
}
tableContainer.addEventListener('click', onTableClick)
function onTableClick(e) {
  if (e.target.matches('td')) {
    const {x, y} = xyCoord(e.target)
    matrix[y][x]++
    tableContainer.innerHTML = genTable(matrix)
    tasks()
  }
}
function xyCoord(td) {
  const tr = td.parentNode
  return {
    x: td.cellIndex,
    y: tr.sectionRowIndex
  }
}
function tasks() {
  task1.innerHTML = matrix[0].some(e => e>0) ? 'Yes' : 'No'
  task2.innerHTML = matrix.filter(row => row.some(e => e > 10)).length
  let t3 = matrix.findIndex(row => row.every(e => e === 0)) + 1
  task3.innerHTML = t3 === 0 ? "No" : t3
}
