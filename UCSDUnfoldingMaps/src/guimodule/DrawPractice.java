package guimodule;

import processing.core.PApplet;
import processing.core.PFont;

public class DrawPractice extends PApplet{
	private PFont font1;
	private PFont font2;
	
	public void setup() {
		size(1600,1600);			//set canvas size
		background(200,200,200);	//set canvas color
		stroke(20);				//set pen color		
		
		font1 = createFont("blankDark-1-3.mbtiles", 32);
		textFont(font1);
		text("word", 1000, 50);
		
		font2 = createFont("blankLight-1-3.mbtiles", 64);
		textFont(font2);
		fill(0);
		textAlign(CENTER, CENTER);
		text("!@#$%", width/2, height/2);
		
//		list();
		//textLeading()
		String lines = "L1\nL2\nL3";
		textSize(12);
		fill(0);  // Set fill to black

		textLeading(10);  // Set leading to 10
		text(lines, 10, 25);

		textLeading(20);  // Set leading to 20
		text(lines, 40, 25);

		textLeading(30);  // Set leading to 30
		text(lines, 70, 25);

		//textAlign()
//		background(0);
		stroke(153);
		textSize(48);
		textAlign(CENTER, BOTTOM);
		line(100, 130, width, 130);
		text("CENTER,BOTTOM", 350, 130);
		textAlign(CENTER, CENTER);
		line(100, 250, width, 250);
		text("CENTER,CENTER", 350, 250);
		textAlign(CENTER, TOP);
		line(100, 370, width, 370);
		text("CENTER,TOP", 350, 370);
		
	}
	public void draw() {
/*		
		fill(255,255,0);			//fill yellow color
		ellipse(200,200,390,390);	//draw face (a round ellipse)
		fill(0,0,0);				//fill black color
		ellipse(120,130, 50,70);	//draw eyes (two small ellipse)
		ellipse(280,130, 50,70);
//		noStroke();
		noFill();					//background color
		arc(200,280,100,100,0,PI);	//draw mouth (arc)
*/		
//		circle(56, 46, 55);
//		square(30, 20, 55);
		
//		quad(38, 31, 86, 20, 69, 63, 30, 76);
//		rect(30, 20, 55, 55, 3, 6, 12, 18);
//		triangle(30, 75, 58, 20, 86, 75);

/*		
		line(30, 20, 85, 20);
		stroke(126);
		line(85, 20, 85, 75);
		stroke(255);
		line(85, 75, 30, 75);
*/	
	}

}
