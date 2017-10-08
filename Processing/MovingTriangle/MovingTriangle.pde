//Sean Gordon
//CMPS 5J
//pa8 - Moving Triangle

//Global Variables
int x1, x2, x3, y1, y2, y3, state, speed;

void setup() {
  size(400, 400);
  background(0, 255, 0);
  smooth();
  x1 = 0;
  x2 = 0;
  x3 = 400;
  y1 = 0;
  y2 = 400;
  y3 = 400;
  speed = 3;
  state = 0;
}

void draw() {
  background(0, 255, 0);
  move();
  fill(255, 0, 255);
  triangle(x1, y1, x2, y2, x3, y3);
}

void move() {
  if (state == 0) {
    x1 += speed;
    if (x1 >= width) {
      x1 = width;
      state = 1;
    }
  } 
  if (state == 1) {
    y2 -= speed;
    if (y2 <= 0) {
      y2 = 0;
      state = 2;
    }
  } 
  if (state == 2) {
    x3 -= speed;
    if (x3 <= 0) {
      x3 = 0;
      state = 3;
    }
  } 
  if (state == 3) {
    y1 += speed;
    if (y1 >= height) {
      y1 = height;
      setup();
    }
  }
}

void mousePressed(){
  if (speed == 3){
    speed = 0;
  } else if (speed == 0){
    speed = 3;
  }
}

//621
//4 8 15 16 23 42
