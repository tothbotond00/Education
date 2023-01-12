export class Sprite {
  currentFrame = 0;
  timeSinceLastFrame = 0;
  loopCount = 0;

  constructor({
    image, 
    width, 
    height, 
    spritesPerRow = 1, 
    spritesCount = 1, 
    frameDuration = 0.03,
    numberOfLoops = Number.POSITIVE_INFINITY,
    isAnimating = true,
  }) {
    this.image = new Image();
    this.image.src = image;
    this.imageWidth = width;
    this.imageHeight = height;
    this.spritesPerRow = spritesPerRow;
    this.spritesCount = spritesCount;
    this.frameDuration = frameDuration;
    this.numberOfLoops = numberOfLoops;
    this.isAnimating = isAnimating;
  }

  update(dt) {
    if (this.isAnimating) {
      this.timeSinceLastFrame += dt;
  
      if (this.timeSinceLastFrame > this.frameDuration) {
        const newFrame = (this.currentFrame + 1) % this.spritesCount;
        this.currentFrame = newFrame;
        this.timeSinceLastFrame -= this.frameDuration;
        // this.timeSinceLastFrame = 0;
        if (newFrame === 0) {
          this.loopCount++;
        }
        if (this.loopCount === this.numberOfLoops) {
          this.isAnimating = false;
          this.currentFrame = this.spritesCount - 1;
        }
      }
    }
  }

  draw(context, targetX, targetY, width, height) {
    const sourceX = 
      (this.currentFrame % this.spritesPerRow) * 
      this.imageWidth;
    const sourceY = 
      Math.trunc(this.currentFrame / this.spritesPerRow) * 
      this.imageHeight;
    // debugger
    // console.log(this.currentFrame);
    context.drawImage(
      this.image,
      sourceX, sourceY, this.imageWidth, this.imageHeight, 
      targetX, targetY, width || this.width, height || this.height
    );
  }

  startAnimation() {
    this.isAnimating = true
  }
}