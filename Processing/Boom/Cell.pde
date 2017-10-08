//---------------------------------------------------------------
// Cell.pde
//---------------------------------------------------------------

class Cell{
  // fields
  float xpos;
  float ypos;
  float side;  // width and height of the cell side
  int state;   // 0=closed, 1=marked, 2=open, 3=exploded
  boolean hasBomb; // true iff this cell has a bomb
  int bombCount;   // number of neighbors having bombs
  
  // constructor
  Cell(float x, float y, float s, float p){
    xpos = x;
    ypos = y;
    side = s;
    state = 0;
    hasBomb = ( random(0,1)<p );
    bombCount = 0;
  }
  
  // methods  
  void display(){
    if( state==0 ){ // closed
      fill(255);
      rect(xpos,ypos,side,side);
    }else if( state==1 ){ // marked
      fill(255);
      rect(xpos,ypos,side,side);
      line(xpos,ypos,xpos+side,ypos+side);
      line(xpos+side,ypos,xpos,ypos+side);
    }else if( state==2 ){ // open
      fill(185);
      rect(xpos,ypos,side,side);
      textAlign(CENTER);
      textSize(16);
      fill(0);
      text(String.valueOf(bombCount),xpos,ypos+4,side,side);
    }else{  // state==3 exploded
      fill(255);
      rect(xpos,ypos,side,side);
      fill(255,0,0);
      ellipse(xpos+side/2,ypos+side/2,side-5,side-5);
    }
  }
  
  boolean mouseOn(){
    return xpos<mouseX && mouseX<xpos+side && ypos<mouseY && mouseY<ypos+side;
  }
  
  void incrementBombCount(){
    bombCount++;
  }
} 
