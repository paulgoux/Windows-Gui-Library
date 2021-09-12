package windowsGui;
import java.awt.Frame;

import processing.core.PApplet;

public class SWindow extends PApplet {
	public BMS Bms;
	int x,y,w,h;
	boolean setLocation,setTitle,makeResizable,start,close;
	String title;

	public SWindow() {
		super();
	};

	public SWindow(int x_,int y_) {
		super();
		x = x_;
		y = y_;
		setLocation = true;
	};

	public SWindow(int x_,int y_,int ww, int hh) {
		super();
		x = x_;
		y = y_;
		w = ww; 
		hh = hh;

		setLocation = true;
	};

	public SWindow(int x_,int y_,int ww,int hh,String s) {
		super();
		x = x_;
		y = y_;
		w = ww;
		h = hh;
		setLocation = true;
		title = s;
		setTitle = true;
//		println("swindow constr" ,x,y,w,h);
		//    PApplet.runSketch(new String[] {this.getClass().getSimpleName()}, this);
	};

	public SWindow(int x_,int y_, String s, boolean k) {
		super();
		x = x_;
		y = y_;
		setLocation = true;
		title = s;
		setTitle = true;
		makeResizable = true;
	};


	public void settings() {
		size(w, h);
//		if(w>0&&h>0)size(w, h);
//		else size(500, 200);
	};

	public void setup() {
		background(150);
		Bms = new BMS(this,true);
	};

	public void draw() {
		Bms.run();
//		ellipse(random(width), random(height), random(50), random(50));
	};

	public void mousePressed() {
		closeWindow();
	};

	void closeWindow(){
		start = false;
//		close = true;
	};

	public void start(){
		start = true;
		if(mousePressed)close = false;
	};
};
