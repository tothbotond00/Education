import { Sprite } from "./sprite.js";

export class Starship extends Sprite {
  width = 46
  height = 62
  vx = 100 // px/s
  dir = 0
  state = 0

  constructor({x = 0, y = 0}) {
    super({
      image: 'millennium-falcon.png',
      width: 230,
      height: 316,
      isAnimating: false,
    })
    this.x = x
    this.y = y
    this.explosionSprite = new Sprite({
      image: 'explosion.png',
      width: 192,
      height: 192,
      spritesPerRow: 5,
      spritesCount: 8,
      numberOfLoops: 1,
      isAnimating: false,
      frameDuration: 0.1
    })
  }

  update(dt) {
    this.x += this.dir * this.vx * dt   // ds = v * dt
    this.explosionSprite.update(dt)
  }

  draw(context) {
    if (this.state === 0) {
      super.draw(context, this.x, this.y, this.width, this.height)
    } else {
      this.explosionSprite.draw(context, this.x, this.y, this.width, this.height)
    }
  }

  explode() {
    this.state = 1
    this.explosionSprite.startAnimation()
  }
}