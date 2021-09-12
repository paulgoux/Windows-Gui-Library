package windowsGui;

import processing.core.PApplet;
import processing.core.PGraphics;
import processing.core.PImage;
import processing.video.Capture;
import processing.video.*;

public class Webcam {
	public BMS Bms;
	public String[] cameras;
	public Capture cam;
	public PApplet applet;
	public PImage t;

	public Webcam(BMS b) {
		Bms = b;
		applet = b.applet;
		cameras = Bms.cameras;
		if (cameras.length == 0) {
			applet.println("There are no cameras available for capture.");
			//exit();
		} else {
			applet.println(cameras.length + " Available cameras:");
			for (int i = 0; i < cameras.length; i++) {
				applet.println(i + " " + cameras[i]);
			}

			// The camera can be initialized directly using an 
			// element from the array returned by list():
			cam = new Capture(applet, cameras[26]);
//			cam.start();
		}
		t = applet.createImage(applet.width,applet.height,applet.ARGB);
	};
	
	public Webcam(BMS b,int w,int h) {
		Bms = b;
		applet = b.applet;
		cameras = Bms.cameras;
		if (cameras.length == 0) {
			applet.println("There are no cameras available for capture.");
			//exit();
		} else {
			applet.println(cameras.length + " Available cameras:");
			for (int i = 0; i < cameras.length; i++) {
				applet.println(i + " " + cameras[i]);
			}

			// The camera can be initialized directly using an 
			// element from the array returned by list():
			cam = new Capture(applet, cameras[26]);
//			cam.start();
		}
		t = applet.createImage(w,h,applet.ARGB);
	};
	
	public Webcam(BMS b,String []s) {
		Bms = b;
		applet = b.applet;
		cameras = s;
		if (cameras.length == 0) {
			applet.println("There are no cameras available for capture.");
			//exit();
		} else {
			applet.println(cameras.length + " Available cameras:");
			for (int i = 0; i < cameras.length; i++) {
				applet.println(i + " " + cameras[i]);
			}

			// The camera can be initialized directly using an 
			// element from the array returned by list():
			cam = new Capture(applet, cameras[26]);
//			cam.start();
		}
		t = applet.createImage(applet.width,applet.height,applet.ARGB);
	};
	
	public Webcam(BMS b,String []s,int w,int h) {
		Bms = b;
		applet = b.applet;
		cameras = s;
		if (cameras.length == 0) {
			applet.println("There are no cameras available for capture.");
			//exit();
		} else {
			applet.println(cameras.length + " Available cameras:");
			for (int i = 0; i < cameras.length; i++) {
				applet.println(i + " " + cameras[i]);
			}

			// The camera can be initialized directly using an 
			// element from the array returned by list():
			cam = new Capture(applet, cameras[26]);
//			cam.start();
		}
		t = applet.createImage(applet.width,applet.height,applet.ARGB);
	};

	public void read(){
		if (cam.available() == true) {
			cam.read();
		}
	};

	public void display() {
		if (cam.available() == true) {
			cam.read();
		}
		applet.image(cam, 0, 0);
	};

	public void display(float x, float y) {
		if (cam.available() == true)cam.read();
		else applet.println("no Cam");
		applet.image(cam, x, y);
	};

	public void display(PGraphics c) {
		c.beginDraw();

		//c.shader(edges);
		if (cam.available() == true){
			cam.read();
			t = cam;
			//applet.println("cam");
		}
		//else applet.println("no cam");

		//c.rect(0,0,width,height);
		c.image(t, 0, 0);
		c.endDraw();
	};

	public PImage get() {
		return cam;
	};

	public PImage getCam() {
		return cam;
	};

	public void set(int a, int b, PImage img) {
		
	};
	
	public void stop() {
		if(cam.available())cam.stop();
		
	};
};