//Agario

int n = 50;
Ball[] B = new Ball[n];
float mX, mY, rad, Xspeed, Yspeed;
color col;

void setup(){
  size(800, 800);
  smooth();
  mX = width/2;
  mY = height/2;
  Xspeed = 0;
  Yspeed = 0;
  rad = 30;
  col = color(0, 255, 255);
  for(int i=0; i<n; i++){
    B[i] = new Ball(random(5, 795), random(5, 795));
  }
}

void draw(){
  background(170);
  myBall();
  for(int i=0; i<n; i++){
    rad += B[i].eaten(mX, mY, rad);
    B[i].display(); 
  }
}

void myBall(){
  updateSpeed();
  mX += Xspeed;
  mY += Yspeed;
  fill(col);
  ellipse(mX, mY, rad, rad);
}

void updateSpeed(){
  if(mX<mouseX && (mX+(rad/2))<width) Xspeed = 1;
  else if(mX>mouseX && (mX-(rad/2)>0)) Xspeed = -1;
  else Xspeed = 0;
  if(mY<mouseY && (mY+(rad/2)<height)) Yspeed = 1;
  else if(mY>mouseY && (mY-(rad/2))>0) Yspeed = -1;
  else Yspeed = 0;
}