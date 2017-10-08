//----------------------------------------------------------
// Sean Gordon
// BallBounce
// cmps 5J
// programming assignment 6
//----------------------------------------------------------

//Global Variable Declarations
float gravity, stopSpeed, dissipation; //environment variables
float X, Y, Xspeed, Yspeed; //ball variables
int ballRad;

void setup() {
  size(500, 500);
  background(0, 255, 255);
  smooth();
  ballRad = 50;
  gravity = 0.6; 
  stopSpeed = 0.2;
  dissipation = .08;
  X = 250;
  Y = 50;
}

void draw() {
  background(0, 255, 255);
  displayBall();
  if (mousePressed && mouseOnBall()) {
    holdBall();
  } else {
    moveBall();
    updateSpeed();
  }
}

void displayBall() {
  X = constrain(X, ballRad, width-ballRad);
  Y = constrain(Y, ballRad, height-ballRad);
  fill(255, 0, 0);
  noStroke();
  ellipseMode(RADIUS);
  ellipse(X, Y, ballRad, ballRad);
}

void holdBall() {
  X = mouseX;
  Y = mouseY;
  X = constrain(X, ballRad, width-ballRad);
  Y = constrain(Y, ballRad, height-ballRad);
  fill(255, 0, 0);
  noStroke();
  ellipseMode(RADIUS);
  ellipse(X, Y, ballRad, ballRad);
  Xspeed = mouseX-pmouseX;
  Yspeed = mouseY-pmouseY;
}

void moveBall() {
  X += Xspeed;
  Y += Yspeed;
}

void updateSpeed() {
  //X side of speed
  if ( abs(Xspeed)<stopSpeed ) {
    Xspeed = 0.0;
  } else if ( X > width-ballRad ) {
    Xspeed *= -(1-dissipation);
  } else if (X < ballRad) {
    Xspeed *= -(1-dissipation);
  }
  //Y side of speed
  if ( abs(Yspeed)<stopSpeed ) {
    Yspeed = 0.0;
  } else if ( Y > height-ballRad ) {
    Yspeed *= -(1-dissipation);
  } else if (Y < ballRad) {
    Yspeed *= -(1-dissipation);
  }
  Yspeed += gravity;
}

boolean mouseOnBall() {
  if (mouseX>X-ballRad && mouseX<X+ballRad && mouseY>Y-ballRad && mouseY<Y+ballRad) {
    return true;
  } else {
    return false;
  }
}

//621
//4 8 15 16 23 42
