package windowsGui;

import java.lang.reflect.Field;
import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PGraphics;
import processing.core.PImage;
import processing.core.PVector;

public class Dock{
	public BMS Bms;
	public PApplet applet;
	public float x,y,w,h,bx,by,bw,bh,currentWidth,currentHeight,r1,r2,r3,r4;
	public ArrayList<String> names = new ArrayList<String>();
	public ArrayList<Button> buttons = new ArrayList<Button>();
	public ArrayList<PGraphics> canvases = new ArrayList<PGraphics>();
	public ArrayList<PImage> thumbnails = new ArrayList<PImage>();
	public ArrayList<Object> objects = new ArrayList<Object>();
	public ArrayList<Boolean> tflags = new ArrayList<Boolean>();
	//	ArrayList<Boolean> tflags = new ArrayList<Boolean>();
	public boolean update,vertical,horizontal,mdown;
	public String loc;
	public Object currentObject;
	public Object parent;
	public Theme theme,newTheme;
	public PImage tempThumbnail;
	public PGraphics thumbnailCanvas;

	public Dock(float x,float y,float w,float h,BMS bms){
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		bx = x;
		by = y;
		bw = w;
		bh = h;
		Bms = bms;
		applet = bms.applet;
		theme = Bms.theme;
		tempThumbnail = applet.createImage(90,100,applet.ARGB);
		thumbnailCanvas = applet.createGraphics(90,100);
	};

	public Dock(float x,float y,float w,float h,Object parent){
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		bx = x;
		by = y;
		bw = w;
		bh = h;
		this.parent = parent; 
		tempThumbnail = applet.createImage(90,100,applet.ARGB);
		thumbnailCanvas = applet.createGraphics(90,100);
	};

	public void add(tab t){
		boolean set = t.toggle;

		t.toggle = true;
		t.displayTab();
		t.toggle = set;
		String loc = t.label;
		applet.textSize(theme.docktextsize);
		float bw = applet.textWidth(loc)+20;
		PGraphics canvas = applet.createGraphics((int)bw,100);
		canvases.add(canvas);
		//		tempThumbnail = applet.createImage((int)t.w,(int)t.h,applet.ARGB);
		PGraphics c = t.states.get(t.state).canvas;
		tempThumbnail = c.get();
		tempThumbnail.resize((int)t.w/4,(int)t.h/4);
		thumbnails.add(tempThumbnail);
		applet.println("dock tab add",thumbnails.size(),canvases.size(),t.title.label);
		Button b = new Button(currentWidth,y,bw,h,loc,Bms);
		b.setClassicBar();
		buttons.add(b);
		objects.add(t);
		currentWidth += bw;
		names.add(loc);
		tflags.add(true);
	};

	public void add(Window t){

		boolean set = t.open;
		t.open = true;
		t.firstRun = true;
		t.displayGrid();
		t.open = set;
		String loc ="";
		applet.textSize(theme.docktextsize);
		if(t.link!=null)loc = t.link;
		float bw = applet.textWidth(loc)+40;
		PGraphics canvas = applet.createGraphics((int)bw,100);
		canvases.add(canvas);
		PGraphics c = t.canvas2;
		tempThumbnail = c.get();
		tempThumbnail.resize((int)t.w/4,(int)t.h/4);
		thumbnails.add(tempThumbnail);
		Button b = new Button(currentWidth,y,bw,h,loc,Bms);
		b.applet = applet;
		b.Bms = Bms;
		b.setClassicBar();
		buttons.add(b);
		objects.add(t);
		currentWidth += bw;
		names.add(loc);
		tflags.add(false);
	};

	public void logic(){
		applet.textSize(theme.docktextsize);
		//		if(applet.mousePressed&&pos()&&!mdown){
		//			loc = Bms.currentMouseObject;
		//
		//			update = false;
		//			mdown = true;
		//		}
		//
		//		if(!update&&mdown&&!applet.mousePressed&&pos()&&!names.contains(loc)){
		//			update = true;
		//			mdown = false;
		//		}
		//		if(pos()&&update&&loc!=null&&!names.contains(loc)){
		//			Bms.currentMouseObject = null;
		//			Bms.currentObject = null;
		//			PGraphics canvas = applet.createGraphics((int)applet.textWidth(loc),100);
		//			canvases.add(canvas);
		//
		//			Button b = new Button(currentWidth,y,applet.textWidth(loc),h,loc,Bms);
		//			b.setClassicBar();
		//			buttons.add(b);
		//
		//			objects.add(currentObject);
		//			currentWidth += applet.textWidth(loc);
		//
		//			names.add(loc);
		//			update = false;
		//			mdown = false;
		//			loc = null;
		//			currentObject = null;
		//		}
		//		if(!applet.mousePressed){
		//			mdown = false;
		//		}
	};

	public void draw(){

		//         applet.beginDraw();
		//
		//         applet.endDraw();
		//         applet.image(canvas,x,y);

	};

	public void draw(PGraphics canvas){

		canvas.beginDraw();

		canvas.endDraw();
		applet.image(canvas,x,y);

	};

	public void drawItems(){
		boolean k = false;
		Button b1 = null;
		y = applet.height-h;
		PImage img = null;
		for(int i=0;i<buttons.size();i++){
			Button b = buttons.get(i);
			canvases.get(i).beginDraw();
			canvases.get(i).background(0,0);

			b.x = 0;
			b.y = 0;
			b.h = h;
			b.mouse = getMouse(b);
			b.theme = theme;
			b.draw(canvases.get(i));
			b.parentDock = this;
			b.toggle(objects.get(i),"toggle",b.mouse);
			if(b.pos(b.mouse)) {
				img = thumbnails.get(i);
				if(img!=null) {
					//					if(tflags.get(i)) {
					//						tempThumbnail = t.states.get(t.state).canvas.get();
					////						tflags.add(null)
					//						//tempThumbnail.resize(70,80);
					//					}
					k = true;
					b1 = b;
					tempThumbnail = img;
				}
				//				
				//				
			}
			canvases.get(i).endDraw();
			applet.image(canvases.get(i),b.bx,applet.height-h);

		}
		if(k)applet.image(tempThumbnail, b1.bx, y - 100);
		if(pos()&&applet.mousePressed&&Bms.currentObject!=null){

			applet.stroke(theme.dockstrokecol,theme.docktransparency);
			applet.strokeWeight(theme.dockstrokesize);
			if(!theme.dockborder)applet.noStroke();

			applet.fill(theme.dockfillcol,theme.docktransparency);
			if(!theme.dockfill)applet.noFill();
			applet.rect(x,y,w,h,r1,r2,r3,r4);
		}
		if(newTheme!=null)newTheme.run();
	};

	void setThemeRadius() {
		r1 = theme.r1;
		r2 = theme.r2;
		r3 = theme.r3;
		r4 = theme.r4;
	};

	PVector getMouse(Button b){

		return new PVector(applet.mouseX-x-b.bx,applet.mouseY-y);
	};

	PVector getMouse(){

		return new PVector(applet.mouseX-x,applet.mouseY-y);
	};

	boolean pos(){
		return applet.mouseX>x&&applet.mouseX<x+w&&applet.mouseY>y&&applet.mouseY<y+h;
	};

	boolean pos(PVector mouse){

		return mouse.x>x&&mouse.x<x+w&&mouse.y>y&&mouse.y<y+h;

	};

	public void setRadius(float a){
		if(newTheme==null) newTheme = new Theme(applet);
		theme = newTheme;
		newTheme.r1 = a;
		newTheme.r2 = a;
		newTheme.r3 = a;
		newTheme.r4 = a;

		newTheme.buttonr1 = a;
		newTheme.buttonr2 = a;
		newTheme.buttonr3 = a;
		newTheme.buttonr4 = a;

		Bms.themes.add(newTheme);

		for(int i=0;i<buttons.size();i++){
			Button b1 = buttons.get(i);
			b1.theme = theme;
			b1.themeIndex = Bms.themes.size();
		}

	};

	public void setRadius(float a,float b,float c,float d){
		if(newTheme==null) newTheme = new Theme(applet);
		theme = newTheme;
		newTheme.r1 = a;
		newTheme.r2 = b;
		newTheme.r3 = c;
		newTheme.r4 = d;

		newTheme.buttonr1 = a;
		newTheme.buttonr2 = b;
		newTheme.buttonr3 = c;
		newTheme.buttonr4 = d;

		Bms.themes.add(newTheme);

		for(int i=0;i<buttons.size();i++){
			Button b1 = buttons.get(i);
			b1.theme = theme;
			b1.themeIndex = Bms.themes.size();
		}

	};

	public void setBorder(boolean k) {
		if(newTheme==null) newTheme = new Theme(applet);
		theme = newTheme;
		theme.dockborder = k;
		theme.buttonborder = k;

		Bms.themes.add(newTheme);

		for(int i=0;i<buttons.size();i++){
			Button b1 = buttons.get(i);
			b1.theme = theme;
			b1.themeIndex = Bms.themes.size();
		}
	};

	public void setStrokeSize(float k) {
		if(newTheme==null) newTheme = new Theme(applet);
		theme = newTheme;
		theme.dockborder = true;
		theme.buttonstrokesize = k;

		Bms.themes.add(newTheme);

		for(int i=0;i<buttons.size();i++){
			Button b1 = buttons.get(i);
			b1.theme = theme;
			b1.themeIndex = Bms.themes.size();
		}
	};

	public void setStrokeCol(int k) {
		if(newTheme==null) newTheme = new Theme(applet);
		theme = newTheme;
		theme.dockborder = true;
		theme.buttonstrokecol = k;

		Bms.themes.add(newTheme);

		for(int i=0;i<buttons.size();i++){
			Button b1 = buttons.get(i);
			b1.theme = theme;
			b1.themeIndex = Bms.themes.size();
		}
	};

	public void setStrokeCol(float a,float b,float c) {
		if(newTheme==null) newTheme = new Theme(applet);
		theme = newTheme;
		theme.dockborder = true;
		theme.buttonstrokecol = applet.color(a,b,c);

		Bms.themes.add(newTheme);

		for(int i=0;i<buttons.size();i++){
			Button b1 = buttons.get(i);
			b1.theme = theme;
			b1.themeIndex = Bms.themes.size();
		}
	};

	public void setStrokeCol(float a,float b,float c,float d) {
		if(newTheme==null) newTheme = new Theme(applet);
		theme = newTheme;
		theme.dockborder = true;
		theme.buttonstrokecol = applet.color(a,b,c,d);

		Bms.themes.add(newTheme);

		for(int i=0;i<buttons.size();i++){
			Button b1 = buttons.get(i);
			b1.theme = theme;
			b1.themeIndex = Bms.themes.size();
		}
	};

	public void setFillCol(int k) {
		if(newTheme==null) newTheme = new Theme(applet);
		theme = newTheme;
		theme.dockborder = true;
		theme.buttonfillcol = k;

		Bms.themes.add(newTheme);

		for(int i=0;i<buttons.size();i++){
			Button b1 = buttons.get(i);
			b1.theme = theme;
			b1.themeIndex = Bms.themes.size();
		}
	};

	public void setFillCol(float a,float b,float c) {
		if(newTheme==null) newTheme = new Theme(applet);
		theme = newTheme;
		theme.dockborder = true;
		theme.buttonfillcol = applet.color(a,b,c);

		Bms.themes.add(newTheme);

		for(int i=0;i<buttons.size();i++){
			Button b1 = buttons.get(i);
			b1.theme = theme;
			b1.themeIndex = Bms.themes.size();
		}
	};

	public void setFillCol(float a,float b,float c,float d) {
		if(newTheme==null) newTheme = new Theme(applet);
		theme = newTheme;
		theme.dockborder = true;
		theme.buttonfillcol = applet.color(a,b,c,d);

		Bms.themes.add(newTheme);

		for(int i=0;i<buttons.size();i++){
			Button b1 = buttons.get(i);
			b1.theme = theme;
			b1.themeIndex = Bms.themes.size();
		}
	};

	public Object getItem(int i){
		Object o = null;
		if(i<objects.size())return objects.get(i);
		else return null;
	};

	public String getName(int i){
		Object o = null;
		if(i<names.size())return names.get(i);
		else return null;
	};

	public Object getItem(String s){
		Object o = null;
		for(int i=0;i<buttons.size();i++) {
			Button b = buttons.get(i);
			if(b.label!=null&&b.label==s) {
				o = objects.get(i);
				break;
			}
		}
		return o;
	};

	public void removeItem(String s){
		int i = names.indexOf(s);
		if(i>-1) {
			objects.remove(i);
			names.remove(i);
			buttons.remove(i);
		}
	};

};
