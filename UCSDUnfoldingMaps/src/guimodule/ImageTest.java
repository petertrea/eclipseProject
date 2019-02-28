package guimodule;

import processing.core.*;

public class ImageTest extends PApplet {
	private String URL = "http://cseweb.ucsd.edu/~minnes/palmTrees.jpg";
	private PImage img;
	
	public void setup() {
		size(1600,1600);		//set canvas size
//		background(255);	//set canvas color
//		stroke(0);			//set pen color
		img = loadImage(URL, "jpg");
		
		
		
/*		//set pixel or image
		set(0, 0, img);
		line(0, 0, width, height);
		line(0, height, width, 0);
*/	
		
/*		//createImage function
		img=createImage(66,66,ARGB);
		img.loadPixels();
		for (int i = 0; i < img.pixels.length; i++) {
			  img.pixels[i] = color(0, 90, 102, i % img.width * 2); 
		}
		img.updatePixels();
		image(img,50,50);	
*/		
	}
	
	public void draw() {
//		img.resize(width,0);	//resize loaded image to full width of canvas
//		image(img,0,0);			//display image
//		img.resize(0,height);	//resize loaded image to full height of canvas
		

//		imageMode(CORNERS);
		
//		image(img,50,50);	
		image(img,00,00,1600,1600);			//display image				

//		fill(255,161,0);
//		ellipse(width/4,height/5, width/5, height/5); //draw the sun
	}
}
