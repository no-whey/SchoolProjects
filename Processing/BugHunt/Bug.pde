// Sean Gordon
// CMPS 5J
// pa7 - BugHunt + Bug

class Bug {
  //Fields
  float x, y, speed;
  int s;
  color c;

  //Constructor
  Bug(float x, float y, int s) {
    this.x = x;
    this.y = y;
    this.s = s;
    speed = random(1, 1.5);
    c = color(random(0, 255), random(0, 255), random(0, 255));
  }

  //methods
  void crawl() {
    if (s == 1) {
      x += speed;
      if (x>width+15) x = -15;
    } else if (s == 2) {
      x -= speed;
      if (x < -15) x = width+15;
    } else if (s == 3) {
      y += speed;
      if (y>height+15) y = -15;
    } else if (s == 4) {
      y -= speed;
      if (y < -15) y = height+15;
    }
  }

  void display() {
    fill(c);
    if (s == 1 || s == 2) {
      line(x, y-15, x, y+15);
      line(x-10, y-15, x-10, y+15);
      line(x+10, y-15, x+10, y+15);
      ellipse(x, y, 30, 20);
    } else if (s == 3 || s == 4) {
      line(x-15, y, x+15, y);
      line(x-15, y-10, x+15, y-10);
      line(x-15, y+10, x+15, y+10);
      ellipse(x, y, 20, 30);
    }
  }

  void squash() {
    if (mouseOn() && mousePressed) {
      s = 0;
    }
  }

  void runAway() {
    if (scared() && mousePressed) {
      speed = speed*1.5;
    }
  }

  boolean mouseOn() {
    if (mouseX>x-15 && mouseX<x+15 && mouseY>y-15 && mouseY<y+15) {
      return true;
    } else {
      return false;
    }
  }

  boolean scared() {
    if (mouseX>x-30 && mouseX<x+30 && mouseY>y-30 && mouseY<y+30) {
      return true;
    } else {
      return false;
    }
  }
}

//621
//4 8 15 16 23 42
