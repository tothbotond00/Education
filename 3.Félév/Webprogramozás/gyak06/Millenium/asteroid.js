import { Sprite } from "./sprite.js";

export class Asteroid extends Sprite {

  constructor({x = 0, y = 0, width = 40, height = 40, vx = 0, vy = 100}) {
    super({
      image: 'asteroid.png',
      width: 72,
      height: 72,
      spritesPerRow: 5,
      spritesCount: 19,
      frameDuration: 0.1,
    })
    this.x = x
    this.y = y
    this.width = width
    this.height = height
    this.vx = vx
    this.vy = vy
  }

  update(dt) {
    super.update(dt) // animate
    this.x += this.vx * dt   // ds = v * dt
    this.y += this.vy * dt   // ds = v * dt
  }

  draw(context) {
    super.draw(context, this.x, this.y, this.width, this.height)
  }
}