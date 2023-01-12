const canvas = document.querySelector('canvas.audio')
const ctx = canvas.getContext('2d')
const btnChange = document.querySelector('#btn-change')
const btnAnimation = document.querySelector('#btn-animation')

const h = (new Array(20)).fill(0).map(e => random(-5, 5))

function random(a, b) {
  return Math.floor(Math.random() * (b-a+1)) + a
}
function change() {
  for (let i = 0; i < h.length; i++) {
    h[i] += random(-1, 1)
  }
}
function draw() {
  ctx.clearRect(0, 0, canvas.width, canvas.height)

  ctx.strokeStyle = 'gray'
  ctx.lineWidth = 3

  ctx.beginPath()
  ctx.moveTo(0, canvas.height / 2)
  for (let i = 0; i < h.length; i++) {
    ctx.lineTo((i+1) * 10, canvas.height / 2 - h[i])
  }
  ctx.lineTo(canvas.width, canvas.height / 2)
  ctx.stroke()
}
draw()

btnChange.addEventListener('click', onChange)
function onChange(e) {
  change()
  draw()
}

btnAnimation.addEventListener('click', onStart)
function onStart(e) {
  animationLoop()
}
function animationLoop() {
  requestAnimationFrame(animationLoop)
  change()
  draw()
}