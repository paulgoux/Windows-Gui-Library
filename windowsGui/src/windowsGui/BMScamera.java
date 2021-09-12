package windowsGui;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PGraphics;
import processing.core.PImage;
import processing.opengl.PShader;
import processing.video.*;

public class BMScamera{

	public BMS Bms;
	public tab settings;
	public PApplet applet;
	public PGraphics canvas;
	public PShader shader;
	public boolean flash,menus;
	float mult = 0.0f,counter = 0.0f;
	String[] cameras = Capture.list();
	Capture cam;
	public PImage img;

	public BMScamera(BMS bms){
		Bms = bms;
		applet = bms.applet;
		canvas = bms.applet.createGraphics((int) (applet.width), (int)(applet.height));
		img = applet.createImage(applet.width,applet.height,applet.ARGB);
		bms.camera = this;
		initCam();
	};

	public BMScamera(BMS bms,boolean b){
		Bms = bms;
		applet = bms.applet;
		canvas = bms.applet.createGraphics((int) (applet.width), (int)(applet.height));
		img = applet.createImage(applet.width,applet.height,applet.ARGB);
		bms.camera = this;
		initCam();
	};

	public BMScamera(BMS bms,int w,int h,boolean b){
		Bms = bms;
		applet = bms.applet;
		canvas = bms.applet.createGraphics((int) (w), (int)(h),PConstants.P2D);
		img = applet.createImage(w,h,applet.ARGB);
		bms.camera = this;
		initCam();
		initMenus();
	};

	public BMScamera(int w,int h,BMS bms,boolean b){
		Bms = bms;
		applet = bms.applet;
		canvas = bms.applet.createGraphics((int) (w), (int)(h));
		img = applet.createImage(w,h,applet.ARGB);
		bms.camera = this;
		initCam();
		initMenus();
	};

	public BMScamera(int w,int h,BMS bms){
		Bms = bms;
		applet = bms.applet;
		canvas = bms.applet.createGraphics((int) (w), (int)(h));
		img = applet.createImage(w,h,applet.ARGB);
		bms.camera = this;
		initCam();
	};
	
	public BMScamera(BMS bms,int w,int h){
		Bms = bms;
		applet = bms.applet;
		canvas = bms.applet.createGraphics((int) (w), (int)(h));
		img = applet.createImage(w,h,applet.ARGB);
		bms.camera = this;
		initCam();
	};

	public void initCam() {

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
			cam.start();
		}

	};

	public void initMenus() {

		settings = new tab(0,200,200,400,"Settings",Bms);
		settings.toggle = true;
		settings.visible = true;
		settings.draggable = true;
		settings.scrollable = true;
		settings.vscroll = true;

		String []s = {"Save","Load","Flash"};
		Menu menu = new Menu(0,40,60,s,Bms);
		menu.setClassicBar();
		settings.add(menu);

		String []s2 = {"Counter","Mult"};
		SliderBox sl1 = new SliderBox(20,120,90,90,10,s2,Bms);
		sl1.menu.draggable = false;
		sl1.tooltip = null;
		sl1.setPieSquare();
		sl1.setFloat(0,0,7);
		sl1.setFloat(1,1,20);
		sl1.setPieSquare();
		settings.add(sl1);
		settings.setRadius(10);
		settings.setAlignment("center");
		Bms.camera = this;
		//Bms.add(settings);
		Bms.dock.add(settings);
	};

	public void display(){
		canvas.beginDraw();
		//	      if (applet.frameCount>10&&!cam.isStarted()){cam.start();}
		if(shader!=null) {
			mult = settings.getValue(0,1);
			counter = PApplet.floor(settings.getValue(0,0));
			shader.set("mult",mult);
			shader.set("type",counter);
		}
		canvas.shader(shader);
		canvas.fill(0);
		canvas.rect(0,0,applet.width,applet.height);
		canvas.imageMode(PConstants.CENTER);
		//	      canvas.image(cam, applet.width/2, applet.height/2+20);
		canvas.imageMode(PConstants.CORNER);
		canvas.fill(0);
		canvas.rect(0,0,applet.width,20);
		canvas.endDraw();
		applet.image(canvas,0,0);
		settings.displayTab();
	};

	public void display(PGraphics c){
		canvas.beginDraw();
		//	      if (applet.frameCount>10&&!cam.isStarted()){cam.start();}
		if(shader!=null) {
			mult = settings.getValue(0,1);
			counter = PApplet.floor(settings.getValue(0,0));
			shader.set("mult",mult);
			shader.set("type",counter);
		}
		canvas.shader(shader);
		canvas.fill(0);
		canvas.rect(0,0,applet.width,applet.height);
		//		canvas.imageMode(PConstants.CENTER);
		canvas.image(cam, applet.width/2, applet.height/2+20);
		//		canvas.imageMode(PConstants.CORNER);
		canvas.fill(0);
		canvas.rect(0,0,applet.width,20);
		canvas.endDraw();
		c.image(canvas,0,0);
		settings.displayTab();
	};

	public void camLogic(){
		if(settings.getToggle(0,2))flash = false;
		else flash = true;

		settings.sliderv.set(0,100);
	};

	public void setBms(BMS bms) {

		Bms = bms;
		applet = bms.applet;
		settings.Bms = bms; 
		settings.sliderv.setBms(bms);
	};

	public void setShader() {

		shader = applet.loadShader("edges.glsl");
	};

	public void read() {
		//		  cam.read();

	};

	public void setShader(PShader s) {
		shader = s;
	};
};
