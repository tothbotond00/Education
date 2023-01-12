import { Starship } from "./starship.js";
import { Asteroid } from "./asteroid.js";
import { Background } from "./background.js";

const canvas = document.querySelector('canvas')
const context = canvas.getContext('2d')

// Enums
const GameStates = {
  INGAME: 1,
  END: 2,
}

// State
let starship = new Starship({
  x: (canvas.width-30) / 2,
  y: canvas.height-60,
})
let asteroids = []
let gameState = GameStates.INGAME
let counter = 0
let background = new Background({
  width: canvas.width,
  height: canvas.height,
})

// Helper function
function random(a, b) {
  return Math.floor(Math.random() * (b - a + 1)) + a
}
function isCollision(r1, r2) {
  return !(
    r2.y + r2.height < r1.y ||
    r2.x > r1.x + r1.width ||
    r2.y > r1.y + r1.height ||
    r2.x + r2.width < r1.x
  )
}

// Game loop
let lastTime = performance.now()
function gameLoop(now = performance.now()) {
  const dt = (now - lastTime) / 1000
  lastTime = now

  update(dt)
  draw()

  // if (gameState !== 'END') {
  window.requestAnimationFrame(gameLoop)
  // }
}

function update(dt) {
  // Starship
  starship.update(dt)

  // New asteroid
  if (Math.random() < 0.03) {
    asteroids.push(new Asteroid({
      x: random(0, canvas.width),
      y: -50, 
      width: random(30, 50),
      height: random(30, 50),
      vx: random(-20, 20),
      vy: random(50, 120),
    }))
  }

  // Move asteroids
  asteroids.forEach(asteroid => {
    asteroid.update(dt)
    if (isCollision(starship, asteroid)) {
      if (gameState !== GameStates.END) {
        starship.explode()
      }
      gameState = GameStates.END
    }
  })

  // Delete asteroids
  const before = asteroids.length
  asteroids = asteroids.filter(asteroid => asteroid.y < canvas.height)
  const after = asteroids.length
  if (gameState !== GameStates.END) {
    counter += before - after
  }
}

function draw() {
  // Background
  background.draw(context)

  // Asteroid
  asteroids.forEach(asteroid => {
    asteroid.draw(context)
  })

  // Starship
  starship.draw(context)

  // Counter
  context.fillStyle = 'white'
  context.font = '25px Courier New'
  context.fillText(`Points: ${counter}`, 10, 30)

  // The end
  if (gameState === GameStates.END) {
    context.fillStyle = 'white'
    context.font = '100px Courier New'
    context.fillText('The end', 75, 200)
  }
}
gameLoop()

// Event listeners
document.addEventListener('keydown', onKeyDown)
document.addEventListener('keyup', onKeyUp)

function onKeyDown(e) {
  if (e.key === 'ArrowLeft') {
    starship.dir = -1
  }
  else if (e.key === 'ArrowRight') {
    starship.dir = 1
  }
}
function onKeyUp(e) {
  starship.dir = 0
}