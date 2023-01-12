import { Sprite } from "./sprite.js";

export class Background extends Sprite {

  constructor({width = 40, height = 40}) {
    super({
      image: 'bg.png',
      width: 600,
      height: 405,
      isAnimating: false,
    })
    this.width = width
    this.height = height
  }

  draw(context) {
    super.draw(context, 0, 0, this.width, this.height)
  }
}