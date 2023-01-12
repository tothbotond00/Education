class Rectangle {
  double x ;double y;
  double width; double height;

  Rectangle (double x, double y , double width, double height ){
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
  }

  public Point topRight() {
    if (width<0 && height<0){
      return new Point (x,y);
    }
    else if (width <0 && height>0){
      return new Point (x,y+height);
    }
    else if (width >0 && height<0){
      return new Point (x+width,y);
    }
    else {
      return new Point (x+width, y+height);
    } 
  }

  public Point topLeft() {
    if (width<0 && height<0){
      return new Point (x+width,y);
    }
    else if (width <0 && height>0){
      return new Point (x+width,y+height);
    }
    else if (width >0 && height<0){
      return new Point (x,y);
    }
    else {
      return new Point (x, y+height);
    } 
  }

  public Point bottomLeft(){
    if (width<0 && height<0){
      return new Point (x+width,y+height);
    }
    else if (width <0 && height>0){
      return new Point (x+width,y);
    }
    else if (width >0 && height<0){
      return new Point (x,y+height);
    }
    else {
      return new Point (x, y);
    } 
  }

  public Point bottomRight(){
    if (width<0 && height<0){
      return new Point (x,y+height);
    }
    else if (width <0 && height>0){
      return new Point (x,y);
    }
    else if (width >0 && height<0){
      return new Point (x+width,y+height);
    }
    else {
      return new Point (x+width, y);
    } 
    
  }
}