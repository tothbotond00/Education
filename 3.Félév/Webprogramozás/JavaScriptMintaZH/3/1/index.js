const txtNumbers = document.querySelector('#numbers')
const task1 = document.querySelector('#task1')
const task2 = document.querySelector('#task2')
const task3 = document.querySelector('#task3')
const task4 = document.querySelector('#task4')
const task5 = document.querySelector('#task5')

let x = [-1, 0, 1, 2, 3]

function tasks() {
  task1.innerHTML = x.filter(e => e % 3 === 0).length
  task2.innerHTML = x.find(e => e < 0) || 'No negative number'
  task3.innerHTML = x.every(e => e % 2 !== 0) ? 'All odds' : 'Evens also'
  task4.innerHTML = x.reduce((max, e) => e > max ? e : max, Number.NEGATIVE_INFINITY)
  task5.innerHTML = x.filter((e, i, x) => x.slice(0, i).every(prev => prev !== e)).join(',')
}

txtNumbers.addEventListener('input', onInput)
function onInput() {
  x = this.value.split(',')
  const good = x.every(e => !isNaN(e))
  this.classList.toggle('error', !good)
  if (good) {
    x = x.filter(e => e.trim() !== '').map(e => parseFloat(e))
    tasks()
  }
}