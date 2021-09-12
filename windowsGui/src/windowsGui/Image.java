package windowsGui;

import processing.core.PApplet;
import processing.core.PGraphics;
import processing.core.PImage;

public class Image {
	BMS Bms;
	PApplet applet;
	public float x, y;
	public int tabIndex,BMSIndex;
	public PImage img;
	public tab parentTab;
	
	
	public Image(){
		
	};
	
	void draw() {
		applet.image(img,x,y);
	};
	
	void draw(PGraphics c) {
		c.image(img,x,y);
	};
};
