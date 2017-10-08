
class Ball{
  
  float x, y;
  color c;
  boolean s;
  
  Ball(float xIn, float yIn){
    this.x = xIn;
    this.y = yIn;
    c = color(random(0, 255), random(0, 255), random(0, 255));
    s = true;
  }
  
  void display(){
    if(s){
      fill(c);
      ellipse(x,y,10,10);
    }
  }
 
  int eaten(float xIn, float yIn, float rad){
    if(covering(xIn, yIn, rad) && s){
      s = false;
      return 5;
    }
    else{
      return 0;
    }
  }
  
  
  
  boolean covering(float xIn, float yIn, float rad){
    return (dist(x, y, xIn, yIn) < rad/2);
  }
}