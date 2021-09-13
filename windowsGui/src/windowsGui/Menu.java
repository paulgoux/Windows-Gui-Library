package windowsGui;

import java.util.ArrayList;
import java.util.HashMap;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PGraphics;
import processing.core.PVector;

public class Menu {
	public PApplet applet;
	public BMS Bms;
	public int id, item, t, toggle2, type = 0, index =-1, subindex = -1, t2, sindex=-1, counter, slcount,
			nindex = -1,rows,cols,themeIndex,tabIndex,BMSIndex,subId,BmsIndex,arrayIndex;
	public float x, y, bx, by, w, h, xoff, yoff, window = 0, htotal, Yoff, xpos, ypos, tsize = 12, bsize,
			spacing, twidth,r1,r2,r3,r4,transparency,ghSpacing,gvSpacing,windowWidth,windowHeight;
	public PVector mouse,mousePos;
	public String label, blabel,itemLabel;
	float dx,dy;
	public boolean drag = false, resize = false, slide = false, border = false, menu, menuhover = false, 
			highlightable = true, animate, vertical, horizontal = false, dmenu,toggleBox,radio,classicBar,
			classicSquare,classicRadio,pieSquare,plain,debug,pie,compact,bg;
	public boolean mdown, mup, smdown, smup, listbox, open_menu, sltoggle, free,parentRight,draggable,parentCanvas,
	m2down,m3down,getIndex,m4down,m5down,m6down,subMenuPos,toggle;
	public boolean visible = true,show = true,localTheme,grid;
	public ArrayList<Button> items = new ArrayList<Button>();
	public ArrayList<Slider> sliders = new ArrayList<Slider>();
	public ArrayList<Dropdown> dMenus = new ArrayList<Dropdown>();
	public ArrayList<Boolean> child = new ArrayList<Boolean>();
	public ArrayList<String> labels = new ArrayList<String>();
	public HashMap<String, Boolean> values ;
	public Window mWindow = null;
	public Button parent;
	public Menu parentMenu;
	public tab tab;
	public PGraphics localCanvas;
	public tab parentTab;
	//Menu dmenu;
	public Dropdown dMenu;
	public SliderBox sliderParent;
	public Theme theme,newTheme;

	public Menu link;
	//color col = applet.color(0,150), bcol, tcol = applet.color(255-theme.tabtransparency), fcol = applet.color(255, 80), hcol = applet.color(255, 50),toggleCol = applet.color(50,0);
	public int col, bcol, tcol, fcol, hcol,toggleCol;

	public Menu() {

	};

	public Menu(float xx, float yy, float ww, float hh, String Label) {
		x = xx;
		y = yy;
		bx = x;
		by = y;
		w = ww;
		h = hh;
		label = Label;
		xoff = 0;
		yoff = 0;
		toggle = false;
		bcol = col;
		bsize = tsize;
		windowWidth = w;
		windowHeight = h;
	};

	public Menu(float xx, float yy, float ww, float hh, String Label,BMS bms) {
		Bms = bms;
		applet = bms.applet;
		x = xx;
		y = yy;
		bx = x;
		by = y;
		w = ww;
		h = hh;
		label = Label;
		xoff = 0;
		yoff = 0;
		toggle = false;
		bcol = col;
		bsize = tsize;
		windowWidth = w;
		windowHeight = h;
		theme = bms.theme;
	};

	public Menu(float xx, float yy, float ww, float hh) {
		x = xx;
		y = yy;
		bx = x;
		by = y;
		w = ww;
		h = hh;
		xoff = 0;
		yoff = 0;
		toggle = false;
		bcol = col;
		bsize = tsize;
	};

	public Menu(float xx, float yy, float ww, float hh,BMS bms) {
		Bms = bms;
		applet = bms.applet;
		theme = bms.theme;
		x = xx;
		y = yy;
		bx = x;
		by = y;
		w = ww;
		h = hh;
		xoff = 0;
		yoff = 0;
		toggle = false;
		bcol = col;
		bsize = tsize;
		windowWidth = w;
		windowHeight = h;
	};



	public Menu(float xx, float yy, float ww, float hh, String Label, String [] list) {

		x = xx;
		y = yy;
		bx = x;
		by = y;
		w = ww;
		h = hh;
		xoff = 0;
		yoff = 0;
		label = Label;
		toggle = false;
		bcol = col;
		bsize = tsize;
		slide = true;
		float bwidth = 0;

		for (int i =0; i<list.length; i++) {
			String l = list[i];

			if (applet.textWidth(l)+hh>bwidth)bwidth = applet.textWidth(l)+20;
			Button a = new Button( x, y + hh *(i+1), bwidth, h, l);
			a.border = false;
			a.parent = this;
			items.add(a);
		}
		twidth = bwidth*items.size();
		windowWidth = twidth;
		windowHeight = (h+spacing) * items.size();
	};

	public Menu(float xx, float yy, float ww, float hh, String Label, String [] list,BMS bms) {
		Bms = bms;
		applet = bms.applet;
		theme = bms.theme;
		x = xx;
		y = yy;
		bx = x;
		by = y;
		w = ww;
		h = hh;
		xoff = 0;
		yoff = 0;
		label = Label;
		toggle = false;
		bcol = col;
		bsize = tsize;
		slide = true;
		float bwidth = 0;
		for (int i =0; i<list.length; i++) {
			String l = list[i];

			if (applet.textWidth(l)+hh>bwidth)bwidth = applet.textWidth(l)+20;
		}

		for (int i =0; i<list.length; i++) {
			String l = list[i];
			Button a = new Button( x, y + hh *(i+1), bwidth, h, l,bms);
			a.border = false;
			a.parent = this;
			items.add(a);
		}
		twidth = bwidth*items.size();
		windowWidth = twidth;
		windowHeight = (h+spacing) * items.size();
	};

	public Menu(float xx, float yy, float ww, float hh, String Label, String [] list, int n) {
		x = xx;
		y = yy;
		bx = x;
		by = y;
		w = ww;
		h = hh;
		xoff = 0;
		yoff = 0;
		label = Label;
		toggle = false;
		bcol = col;
		bsize = tsize;
		slide = true;
		float bwidth = 0;
		spacing = 0;
		//println(y)
		for (int i =0; i<list.length; i++) {
			String l = list[i];

			if (applet.textWidth(l)+20>bwidth)bwidth = applet.textWidth(l)+20;
		}

		if(w>bwidth)bwidth = w;

		for (int i =0; i<list.length; i++) {
			String l = list[i];

			Button a = new Button( x, y + hh *(i+1), bwidth, hh, l);
			a.border = false;
			a.parent = this;
			items.add(a);
		}
		//h = hh * items.size();
		twidth = bwidth*items.size();
		windowWidth = twidth;
		windowHeight = (h+spacing) * items.size();
	};

	public Menu(float xx, float yy, float ww, float hh,float ss, String Label, String [] list,BMS bms) {
		Bms = bms;
		applet = bms.applet;
		theme = Bms.theme;
		x = xx;
		y = yy;
		bx = x;
		by = y;
		w = ww;
		h = hh;
		xoff = 0;
		yoff = 0;
		label = Label;
		toggle = false;
		bcol = col;
		bsize = tsize;
		slide = true;
		spacing = 0;


		for (int i =0; i<list.length; i++) {
			String l = list[i];

			Button a = new Button( x, y + (hh+ss) *(i+1), w, hh, l,bms);
			a.border = false;
			a.parent = this;
			items.add(a);
		}
		twidth = w*items.size();
		windowWidth = twidth;
		windowHeight = (h+spacing) * items.size();
	};

	public Menu(float xx, float yy, float ww, String [] list) {
		x = xx;
		y = yy;
		bx = x;
		by = y;
		w = ww;

		h = list.length*20;
		xoff = 0;
		yoff = 0;
		toggle = false;
		bcol = col;
		bsize = tsize;
		//slide = true;

		float bwidth = 0;

		for (int i =0; i<list.length; i++) {
			String l = list[i];

			if (applet.textWidth(l)+20>bwidth)bwidth = applet.textWidth(l)+20;
		}

		for (int i =0; i<list.length; i++) {
			String l = list[i];
			Button a = new Button( x, y + 20 *(i+1), bwidth, 20, l);
			a.border = false;
			a.parent = this;
			items.add(a);
		}

		twidth = (bwidth)*items.size();
		windowWidth = twidth;
		windowHeight = (h+spacing) * items.size();
	};

	public Menu(float xx, float yy, float ww,float hh,float ss, String [] list,BMS bms) {
		Bms = bms;
		applet = bms.applet;
		theme = bms.theme;
		x = xx;
		y = yy;
		bx = x;
		by = y;
		w = ww;
		spacing = ss;
		h = list.length*(hh+ss);
		xoff = 0;
		yoff = 0;
		toggle = true;
		bcol = col;
		bsize = tsize;
		//slide = true;

		float bwidth = 0;

		for (int i =0; i<list.length; i++) {
			String l = list[i];

			if (applet.textWidth(l)+20>bwidth)bwidth = applet.textWidth(l)+20;
		}

		for (int i =0; i<list.length; i++) {
			String l = list[i];
			Button a = new Button( x, y + (hh+ss) *(i+1), bwidth, hh, l,bms);
			a.border = false;
			a.parent = this;
			items.add(a);
		}

		twidth = bwidth*items.size();
		w = bwidth;
		windowWidth = twidth;
		windowHeight = (h+spacing) * items.size();
	};

	public Menu(float xx, float yy, float ww, String [] list,BMS bms) {
		Bms = bms;
		applet = bms.applet;
		theme = bms.theme;
		x = xx;
		y = yy;
		bx = x;
		by = y;
		w = ww;

		h = list.length*20;
		xoff = 0;
		yoff = 0;
		toggle = false;
		bcol = col;
		bsize = tsize;
		//slide = true;

		float bwidth = 0;

		for (int i =0; i<list.length; i++) {
			String l = list[i];

			if (applet.textWidth(l)+20>bwidth)bwidth = applet.textWidth(l)+20;
		}

		for (int i =0; i<list.length; i++) {
			String l = list[i];
			Button a = new Button( x, y + 20 *(i+1), bwidth, 20, l,bms);
			a.parent = this;
			a.border = false;
			items.add(a);
		}

		twidth = bwidth*items.size();
		w = bwidth;
		windowWidth = twidth;
		windowHeight = (h+spacing) * items.size();
	};

	public Menu(float xx, float yy, float ww,float hh, String [] list,BMS bms) {
		Bms = bms;
		applet = bms.applet;
		theme = bms.theme;
		x = xx;
		y = yy;
		bx = x;
		by = y;
		w = ww;

		h = list.length*hh;
		xoff = 0;
		yoff = 0;
		toggle = false;
		bcol = col;
		bsize = tsize;
		//slide = true;

		float bwidth = 0;

		for (int i =0; i<list.length; i++) {
			String l = list[i];

			if (applet.textWidth(l)+20>bwidth)bwidth = applet.textWidth(l)+20;
		}

		for (int i =0; i<list.length; i++) {
			String l = list[i];
			Button a = new Button( x, y + 20 *(i+1), bwidth, 20, l,bms);
			a.parent = this;
			a.border = false;
			items.add(a);
		}

		twidth = bwidth*items.size();
		w = bwidth;
		windowWidth = twidth;
		windowHeight = (h+spacing) * items.size();
	};

	public Menu(float xx, float yy, float ww, ArrayList<String> list) {
		x = xx;
		y = yy;
		w = ww;

		h = list.size()*20;
		xoff = 0;
		yoff = 0;
		toggle = false;
		bcol = col;
		bsize = tsize;
		//slide = true;

		float bwidth = 0;

		for (int i =0; i<list.size(); i++) {
			String l = list.get(i);

			if (applet.textWidth(l)+20>bwidth)bwidth = applet.textWidth(l)+20;
		}

		for (int i =0; i<list.size(); i++) {
			String l = list.get(i);

			Button a = new Button( x, y + 20 *(i+1), bwidth, 20, l);
			a.border = false;
			a.parent = this;
			items.add(a);
		}

		twidth = bwidth*items.size();
		w = bwidth;
		windowWidth = twidth;
		windowHeight = (h+spacing) * items.size();
	};

	public Menu(float xx, float yy, float ww, ArrayList<String> list,BMS bms) {
		Bms = bms;
		applet = bms.applet;
		theme = bms.theme;
		x = xx;
		y = yy;
		w = ww;

		h = list.size()*20;
		xoff = 0;
		yoff = 0;
		toggle = false;
		bcol = col;
		bsize = tsize;
		//slide = true;

		float bwidth = 0;

		for (int i =0; i<list.size(); i++) {
			String l = list.get(i);

			if (applet.textWidth(l)+20>bwidth)bwidth = applet.textWidth(l)+20;
		}

		for (int i =0; i<list.size(); i++) {
			String l = list.get(i);

			Button a = new Button( x, y + 20 *(i+1), bwidth, 20, l,bms);
			a.border = false;
			a.parent = this;
			items.add(a);
		}

		twidth = bwidth*items.size();
		w = bwidth;
		windowWidth = twidth;
		windowHeight = (h+spacing) * items.size();
	};


	public Menu(float xx, float yy, float ww, String [] list, float k) {

		x = xx;
		y = yy;
		bx = x;
		by = y;
		w = ww;
		h = list.length*(20+k) - k;
		xoff = 0;
		yoff = 0;
		toggle = false;
		bcol = col;
		bsize = tsize;
		spacing = k;

		float bwidth = 0;

		for (int i =0; i<list.length; i++) {
			String l = list[i];

			if (applet.textWidth(l)+20>bwidth)bwidth = applet.textWidth(l)+20;
		}

		for (int i =0; i<list.length; i++) {
			String l = list[i];
			Button a = new Button( x, y + (20+k) *(i+1), bwidth, 20, l);
			a.border = false;
			a.parent = this;
			a.toggleBox = true;
			items.add(a);
		}

		twidth = bwidth*items.size();
		w = bwidth;
		windowWidth = twidth;
		windowHeight = (h+spacing) * items.size();
	};

	public Menu(float xx, float yy, float ww, String [] list, float k,BMS bms) {
		Bms = bms;
		applet = bms.applet;
		theme = bms.theme;
		x = xx;
		y = yy;
		bx = x;
		by = y;
		w = ww;
		h = list.length*(20+k) - k;
		xoff = 0;
		yoff = 0;
		toggle = false;
		bcol = col;
		bsize = tsize;
		spacing = k;

		float bwidth = 0;

		for (int i =0; i<list.length; i++) {
			String l = list[i];

			if (applet.textWidth(l)+20>bwidth)bwidth = applet.textWidth(l)+20;
		}

		for (int i =0; i<list.length; i++) {
			String l = list[i];
			Button a = new Button( x, y + (20+k) *(i+1), bwidth, 20, l,bms);
			a.border = false;
			a.parent = this;

			items.add(a);
		}

		twidth = bwidth*items.size();
		w = bwidth;
		windowWidth = twidth;
		windowHeight = (h+spacing) * items.size();
	};

	public Menu(float xx, float yy, float ww, String [] list, float k,float h) {

		x = xx;
		y = yy;
		bx = x;
		by = y;
		w = ww;
		h = list.length*(h+k) - k;
		xoff = 0;
		yoff = 0;
		toggle = false;
		bcol = col;
		bsize = tsize;
		spacing = k;

		float bwidth = 0;

		for (int i =0; i<list.length; i++) {
			String l = list[i];

			if (applet.textWidth(l)+20>bwidth)bwidth = applet.textWidth(l)+20;
		}

		for (int i =0; i<list.length; i++) {
			String l = list[i];
			Button a = new Button( x, y + (h+k) *(i+1+k), bwidth, 20, l);
			a.border = false;
			a.parent = this;
			a.toggleBox = true;
			items.add(a);
		}

		twidth = bwidth*items.size();
		w = bwidth;
		windowWidth = twidth;
		windowHeight = (h+spacing) * items.size();
	};

	public Menu(float xx, float yy, String [] list) {

		x = xx;
		y = yy;
		bx = x;
		by = y;
		h = list.length*(20);
		xoff = 0;
		yoff = 0;
		toggle = false;
		bcol = col;
		bsize = tsize;

		float bwidth = 0;

		for (int i =0; i<list.length; i++) {
			String l = list[i];

			if (applet.textWidth(l)+20>bwidth)bwidth = applet.textWidth(l)+20;
		}

		for (int i =0; i<list.length; i++) {
			String l = list[i];
			Button a = new Button( x, y + (20) *(i), bwidth, 20, l);
			a.border = false;
			a.parent = this;
			a.toggleBox = true;
			items.add(a);
		}

		twidth = bwidth*items.size();
		w = bwidth;
		windowWidth = twidth;
		windowHeight = (h+spacing) * items.size();
	};

	public Menu(String k, float xx, float yy, float ww, String dir) {
		if (k=="dir"|| k=="DIR"||k=="Dir") {
			x = xx;
			y = yy;
			bx = x;
			by = y;
			w = ww;

			xoff = 0;
			yoff = 0;
			toggle = false;
			bcol = col;
			bsize = tsize;

			String []list = fileUtils.listFileNames(dir);

			for (int i =0; i<list.length; i++) {

				String l = list[i];
				for (int j=0; j<list[i].length(); j++) {
					Button a = new Button( x, y + 20 *(i), w, 20, l);
					a.border = false;
					a.toggleBox = true;
					a.parent = this;
					items.add(a);
				}
			}
		}

		h = items.size()*20;
		Slider s = new Slider(x, y, 20, 20);
		s.vertical = true;
		sliders.add(s);
		windowWidth = twidth;
		windowHeight = (h+spacing) * items.size();
	};



	public void save(){
//		Bms.output.reset();
		applet.println("save menu");
		Bms.output.writeLine("");
		Bms.output.writeLine("Menu");
		Bms.output.writeLine("themeIndex",themeIndex);
		Bms.output.writeLine("localTheme",localTheme);
		Bms.output.write(x+",");
		Bms.output.write(y+",");
		Bms.output.write(w+",");
		Bms.output.write(h+",");
		Bms.output.write(label+",");
//		for(int i=0;i<items.size();i++) {
//			Button b = items.get(i);
//			Bms.output.writeLine("button,"+i+","+items.get(i).label);
//			if(b.submenu!=null) {
//				Bms.output.write("subMenu,");
//				for(int j=0;j<b.submenu.items.size();j++) {
//					Button b1 = items.get(i);
//					Bms.output.write("subMenu,");
//				}
//			}
//		}
//		Bms.output.write("Type,");
//		if(classicBar)Bms.output.write("classicBar");
//		else if(classicSquare)Bms.output.write("cif(lassicSquare");
//		else if(classicRadio)Bms.output.write("classicRadio");
//		else if(toggleBox)Bms.output.write("toggleBox");
//		else if(grid) {
//			Bms.output.write("grid");
//		}
//		else Bms.output.write("plain");
		Bms.output.writeLine("");
		applet.println("saved menu");
	};
	
	public void defaultSave(){
//		Bms.output.reset();
		applet.println("save menu");
		Bms.output.writeLine("");
		Bms.output.writeLine("_Menu");
		Bms.output.writeLine("BmsIndex",BmsIndex);
		Bms.output.writeLine("themeIndex",themeIndex);
		Bms.output.writeLine("localTheme",localTheme);
		Bms.output.write(x+",");
		Bms.output.write(y+",");
		Bms.output.write(w+",");
		Bms.output.write(h+",");
		Bms.output.write(label+",");
//		for(int i=0;i<items.size();i++) {
//			Button b = items.get(i);
//			Bms.output.writeLine("button,"+i+","+items.get(i).label);
//			if(b.submenu!=null) {
//				Bms.output.write("subMenu,");
//				for(int j=0;j<b.submenu.items.size();j++) {
//					Button b1 = items.get(i);
//					Bms.output.write("subMenu,");
//				}
//			}
//		}
//		Bms.output.write("Type,");
//		if(classicBar)Bms.output.write("classicBar");
//		else if(classicSquare)Bms.output.write("cif(lassicSquare");
//		else if(classicRadio)Bms.output.write("classicRadio");
//		else if(toggleBox)Bms.output.write("toggleBox");
//		else if(grid) {
//			Bms.output.write("grid");
//		}
//		else Bms.output.write("plain");
		Bms.output.writeLine("");
		applet.println("saved menu");
	};

	public void load(){

	};

	public void setParentCanvas(PGraphics canvas){
		parentCanvas = true;
		localCanvas = canvas;

	};

	public void draw() {
		if(debug)applet.println("menu draw start");
		logic();
		if(debug)applet.println("menu draw logic");
		if(show)trace();
		if(debug)applet.println("menu draw trace");
		if (draggable) {
			if(!parentCanvas){
				applet.stroke(theme.menustrokecol,theme.menutransparency);
				applet.strokeWeight(theme.menustrokesize);
				if(!theme.menuborder)applet.noStroke();
				applet.fill(col,theme.menutransparency);
				if(!theme.menufill)applet.noFill();
				applet.rect(x,y-5, w, 5,theme.r1,theme.r2,theme.r3,theme.r4);
			}else{
				localCanvas.stroke(theme.menustrokecol,theme.menutransparency);
				localCanvas.strokeWeight(theme.menustrokesize);
				if(!theme.menuborder)applet.noStroke();
				localCanvas.fill(col,theme.menutransparency);
				if(!theme.menufill)localCanvas.noFill();
				localCanvas.rect(x,y-5, w, 5,theme.r1,theme.r2,theme.r3,theme.r4);
			}
		}
		if(debug)applet.println("menu draw end");
	};

	public void draw2() {
		if(applet.mousePressed)click();
		logic();
		if(show)trace();
		if (draggable) {
			if(!parentCanvas){
				applet.stroke(theme.menustrokecol,theme.menutransparency);
				applet.fill(0,266,0,150);
				applet.rect(x,y-5, w, 5);
			}else{
				localCanvas.stroke(theme.menustrokecol,theme.menutransparency);
				localCanvas.fill(0,150);
				localCanvas.rect(x,y-5, w, 5);
			}
		}
	};

	public void draw(PGraphics canvas) {
		canvas.strokeWeight(theme.menustrokesize);
		if(!border)canvas.noStroke();
		if(mouse!=null)logic(mouse);
		if(show)trace(canvas);


	};

	public void simpleDraw(PGraphics canvas) {
		if(mouse!=null)logic(mouse);
		canvas.strokeWeight(theme.menustrokesize);
		if(show)trace(canvas);
		if(!border)canvas.noStroke();

	};

	public void drawWindow() {
	}

	public void trace() {
		t2 = 0;
		slcount = 0;
		getState();
		drawButtons();
		//	    drawDmenu();
		drawsliders();
	};

	public void trace(PGraphics canvas) {
		t2 = 0;
		slcount = 0;

		getState(canvas);
		drawButtons(canvas);
		//drawDmenu();
		drawsliders();
	};

	public void getState() {

		applet.stroke(theme.menustrokecol,theme.menutransparency);
		applet.strokeWeight(theme.menustrokesize);
		if(!theme.menuborder)applet.noStroke();
		if(highlightable)highlight();
		applet.fill(col,theme.menutransparency);
		if(!theme.menufill)applet.noFill();
		if (visible&&label!=null)applet.rect(x, y, w, h,theme.r1,theme.r2,theme.r3,theme.r4);

		if(label!=null) {
			applet.pushStyle();
			applet.textFont(theme.menuFont);
			applet.fill(theme.menutextcol);
			applet.textSize(theme.menutextsize);
			applet.text(label, x+xoff, y+yoff + 18);
			applet.textSize(theme.menutextsize);
			applet.popStyle();
			//fill(col,theme.menutransparency);
		} else {
			toggle = true;
		}


	};

	public void getState(PGraphics canvas) {
		canvas.stroke(theme.menustrokecol,theme.menutransparency);
		canvas.strokeWeight(theme.menustrokesize);
		if(!theme.menuborder)canvas.noStroke();
		if(highlightable)highlight(canvas);
		if (!border)canvas.noStroke();

		canvas.fill(col,theme.menutransparency);
		if(!theme.menufill)canvas.noFill();
		if (visible&&label!=null)canvas.rect(x, y, w, h,theme.r1,theme.r2,theme.r3,theme.r4);
		canvas.fill(0);

		if(label!=null) {
			canvas.pushStyle();
			canvas.textFont(theme.menuFont);
			canvas.fill(theme.menutextcol);
			canvas.textSize(theme.menutextsize);
			canvas.text(label, x+xoff, y+yoff + 18);
			canvas.textSize(theme.menutextsize);
			canvas.popStyle();
			//fill(col,theme.menutransparency);
		} else {
			toggle = true;
		}
	};

	public void drawButtons() {

		float speed = h/5;
		if (items.size()>0) {
			float cx = 0;
			float cy = 0;
			for (int i=0; i<items.size(); i++) {

				Button a = items.get(i);
				a.id = i;
				if(parentTab!=null)a.parentTab = parentTab;
				if(grid) {
					if(cols>0) {
						int r = i/cols;
						int c = i%cols;
						cx +=  c * (a.w + ghSpacing);
						cy +=  r * (a.h + gvSpacing); 
						a.x = x + cx;
						a.y = y + cy; 
					}else if(applet.frameCount%10==0)applet.println("cols or rows not defined, pleat check setGrid()");
				}else {
					if (a.parent!=null&&label==null&&!horizontal){
						a.y = a.parent.y + i * (a.h + spacing);
						a.x = a.parent.x;
					}

					if (a.parent!=null&&label!=null&&!horizontal&&!free){
						a.y = a.parent.y + a.h + i * (a.h + spacing);
						a.x = a.parent.x;
					}

					if (a.parent!=null&&label!=null&&horizontal) {
						a.x = a.parent.x + (a.parent.w + spacing) + i * a.w;
						a.y = y+a.h;
					}
				}
				if(a.submenu!=null){
					if(!a.submenu.parentRight){
						a.submenu.x = a.x + a.w;
						a.submenu.y = a.y;
					}else{
						a.submenu.x = a.x - a.submenu.w;
						a.submenu.y = a.y;
					}
				}

				if(a.dMenu!=null){
					a.dMenu.x = a.x + a.w;
					a.dMenu.y = a.y;
				}

				if (toggle) {
					if (a.pos(mousePos)&&i>=0) {
						t2 ++;
						index = i;
					}
					if (a.subpos(mousePos)&&i>=0) {
						t2 ++;
						subindex = a.id;
					}}

				if (!slide) {
					if(horizontal)window = windowWidth;
					else window = windowHeight;
				} else {
					if (toggle) {
						window += speed;
						if(grid) {
							if (window>y + items.size()/cols+1*items.get(0).h) window = y + items.size()/cols+1*items.get(0).h;
						}else {
							if (!horizontal) {
								if (window>y + windowHeight) window = windowHeight;
							} else {
								if (window>x+windowWidth)window = windowWidth;
							}}} else {
								window -= speed;
								if (window<0)window = 0;
							}}

				if (!horizontal) {
					if (y + window >= a.y) {
						if (a.visible){
							if(!parentCanvas) {
								a.draw();
							}
							else {
								a.mouse = mouse;
								a.draw(localCanvas);
							}}
					}
				} else {
					if (x + window >= a.x) {
						if (a.visible){
							if(!parentCanvas)a.draw();
							else {
								a.mouse = mouse;
								a.draw(localCanvas);
							}
						}
					}}}}

	};

	public void drawButtons(PGraphics canvas) {


		if (items.size()>0) {
			float speed = 2,cx = 0,cy = 0;
			for (int i=0; i<items.size(); i++) {

				Button a = items.get(i);
				a.id = i;

				a.mouse = mouse;

				if(grid) {

					if(cols>0) {
						int r = i/cols;
						int c = i%cols;
						if(compact) {

							a.tw = applet.textWidth(a.label)+20;
							a.w = a.tw;
							if(i>0)cx = items.get(i-1).x+items.get(i-1).tw+ghSpacing;
							else cx = x;
							if(i%cols==0)cx = 0;
							a.x = cx;
						}else {
							cx = c * (a.w + ghSpacing);
						}
						//						a.y =  r * (a.h + gvSpacing); 
						a.y = y+r * (20 + gvSpacing);
						a.setPos(cx, a.y);
						if(cx>w)w = cx;
						if(cy>h)h = cy;
					}else if(applet.frameCount%10==0)applet.println("cols or rows not defined, please check setGrid()");
				}else {

					if (a.parent!=null&&label==null&&!horizontal){
						a.y = a.parent.y + i * (a.h + spacing);
						a.x = a.parent.x;
					}

					if (a.parent!=null&&label!=null&&!horizontal&&!free){
						a.y = a.parent.y + h + i * (a.h + spacing);
						a.x = a.parent.x;
					}

					if (a.parent!=null&&label!=null&&horizontal) {
						a.x = a.parent.x + (a.parent.w + spacing) + i * a.w;
						a.y = y;
					}
				}
				if(a.submenu!=null){
					if(!a.submenu.parentRight){
						a.submenu.x = a.x + a.w;
						a.submenu.y = a.y;
					}else{
						a.submenu.x = a.x - a.submenu.w;
						a.submenu.y = a.y;
					}
				}

				if(a.dMenu!=null){
					a.dMenu.x = a.x + a.w;
					a.dMenu.y = a.y;
				}

				if (toggle) {
					if (a.pos(mouse)&&i>=0) {
						t2 ++;
						index = i;
					}
					if (a.subpos(mouse)&&i>=0) {
						t2 ++;
						subindex = a.id;
					}}

				if (!slide) {
					if (toggle) {
						if(horizontal)window = windowWidth;
						else window = windowHeight;
					}
					else window = 0;
				} else {
					if (toggle) {
						window += speed*3;
						if(grid) {
							if (window>y + items.size()/cols+1*items.get(0).h) window = y + items.size()/cols+1*items.get(0).h;
						}else {
							if (!horizontal) {
								if (window>y + windowHeight) window = windowHeight;
							} else {
								if (window>x+windowWidth)window = windowWidth;
							}}} else {
								window -= speed*3;
								if (horizontal) {
									if(window<x)window = 0;
								}else if(window<y)window = 0;
							}}
				
				if (!horizontal) {
//					if(grid) {
//						
//						if(cols>0) {
//							int r = i/cols;
//							int c = i%cols;
//							//			        	  if(compact) {
//							//			        		  
//							//			        		  a.tw = applet.textWidth(a.label)+20;
//							//			        		  a.w = a.tw;
//							//			        		  if(i>1)a.x = items.get(i-1).x+items.get(i-1).w;
//							//			        	  }else {
//							//			        		  a.x = c * (a.w + ghSpacing);
//							//			        	  }
//							a.y =  y+ (r) * (a.h + gvSpacing); 
//						}}
//					
//					if(grid)a.draw(canvas);
//					
//					else {
//						a.visible = true;
//						if (y + window >= a.y) a.visible = false;
//						
////						applet.println("sliderdown");
//						if(a.visible) {
//							a.draw(canvas);
////							applet.println(y+window,a.y);
//						}
//
//					}
					if (y + window >= a.y) {
						if (a.visible){
							if(!parentCanvas) {
								a.draw(canvas);
							}
							else {
								a.mouse = mouse;
								a.draw(canvas);
							}}
					}
					
				} else {
					if(grid)a.draw(canvas);
					else if (x + window >= a.x) {
						if (a.visible)a.draw(canvas);

					}}

			}}

	};



	public void drawDmenu() {
		float speed = 2;
		if (dMenus.size()>0) {

		}
	};

	public void add(Slider s,float spacing){
		s.Bms = Bms;
		s.applet = this.applet;
		s.theme = theme;
		sliders.add(s);

		if(items.size()==0)h = (s.h+spacing)*sliders.size();
		else h = (items.get(0).h+spacing)*items.size()+(s.h+spacing)*sliders.size();
	};

	public void add(Slider s){
		s.Bms = Bms;
		s.applet = this.applet;
		s.theme = theme;
		sliders.add(s);

		if(items.size()==0)h = (s.h+spacing)*sliders.size();
		else h = (items.get(0).h+spacing)*items.size()+(s.h+spacing)*sliders.size();
	};

	public void add(Button b){
		b.Bms = Bms;
		b.applet = this.applet;
		b.parent = this;
		b.theme = theme;
		items.add(b);

		if(sliders.size()==0)h = (b.h+spacing)*items.size();
		else h = (sliders.get(0).h+spacing)*sliders.size()+(b.h+spacing)*items.size();
	};

	public void add(String s){
		if(items.size()>0) {
			Button b = new Button(x,y+items.size()*items.get(0).h+spacing,items.get(0).w,items.get(0).h,s,Bms);
			b.Bms = Bms;
			b.applet = this.applet;
			b.parent = this;
			items.add(b);

			if(sliders.size()==0)h = (b.h+spacing)*items.size();
			else h = (sliders.get(0).h+spacing)*sliders.size()+(b.h+spacing)*items.size();
			h += items.get(0).h;
		}
	};

	public void add(String s,float h1){
		if(items.size()>0) {
			Button b = new Button(x,y+items.size()*items.get(0).h+spacing,items.get(0).w,h1,s,Bms);
			b.Bms = Bms;
			b.applet = this.applet;
			b.parent = this;
			items.add(b);

			h+= h1;
		}
	};

	public void drawsliders() {
		float speed = 2;

		if (sliders.size()>0&&show) {

			for (int i=sliders.size()-1;i>-1; i--) {

				Slider a = sliders.get(i);
				if(parentCanvas)a.mouse = mouse;
				if(parentTab!=null)a.parentTab = parentTab;
				//else a.mouse = new PVector(applet.mouseX,applet.mouseY);
				if (a.toggle||a.mdown) slcount ++;
				a.parent = this;
				if (a.id==-1)a.id = i;
				if (vertical) {
					//					a.vertical = true;
					if(grid) {

					}else {
						//						a.horizontal = true; 
						a.y = y; 
						a.x = x + (a.w + spacing) * i;
						//						a.h = 
					}
				}else{
					if(grid) {
						if(cols<1)cols = 1;
						int r = i/cols;
						int c = i%cols;
						if(a.pie) {
							a.y = a.h/2+y + (a.h + gvSpacing+20) * r; 
							a.x = x+a.w/2 + (a.w + ghSpacing+20)* c;

							//	  		          if(a.tooltip!=null)a.tooltip.setPos(a.x+a.,a.y-a.h/2);
						}else {
							float bx = x + c * (a.w + ghSpacing);
							float by = y + r * (a.h + gvSpacing); 
							a.setPos(bx,by);
							
							//		        	  if(a.tooltip!=null)a.tooltip.setPos(a.x+a.w,a.y);
						}
					}else {
						if(a.pie) {
							a.y = a.h/2+y + (a.h + spacing+20) * i; 
							a.x = x+a.w/2;
							//	  		            if(a.tooltip!=null)a.tooltip.setPos(a.x+a.w/2,a.y-a.h/2);
						}else {
							a.y = y + (a.h + spacing) * i; 
							a.x = x;
						}
					}
				}
				if(!parentCanvas){
					if ((a.pos()||a.btnpos())&&!smdown) {
						smdown = true;
						sindex = i;
						a.mdown = true;
					}}else if ((a.pos(mouse)||a.btnpos(mouse))&&!smdown) {
						smdown = true;
						sindex = i;
						a.mdown = true;
					}
				if(sindex!=i)a.mdown = false;
				if (!applet.mousePressed&&smdown) { 
					smdown = false;
					a.mdown = false;
				}
				if (!slide) {
					if (toggle) {
						if (vertical)window = y + (spacing +a.h)*(sliders.size());
						else window = x + (spacing +a.w)*(sliders.size());
					}} else {
						if (toggle) {
							window += speed;
							if (vertical) {
								if (window>y + a.h * sliders.size()) {
									window = y + a.h * sliders.size();
								}} else {
									if (window>x + a.w * sliders.size()) {
										window = x + a.w * sliders.size();
									}}} else {
										window -= speed;
										if (window<=0) window = 0;

									}}
				if (vertical) {
					if (y + window >= a.y){

						if (a.visible){
							if(!parentCanvas)a.draw();
							else a.draw(localCanvas);
						}
					}} else {
						if (x + window >= a.x){

							if (a.visible){
								if(!parentCanvas)a.draw();
								else a.draw(localCanvas);
							}}}}
		}
		if (slcount>0) sltoggle = true;
		else sltoggle = false;

	};

	public void borders(Boolean k) {
		for (int i=0; i<items.size(); i++) {
			Button b = items.get(i);

			b.border = k;
		}
	};

	public void update() {
		counter = 0;
		for (int i=0; i<items.size(); i++) {

			Button a = items.get(i);
			if (a.pos()) counter++;
		}
	};

	public void draw4(){

		applet.fill(255-theme.tabtransparency);
		//	    PApplet.println("hishe",applet.mouseX,applet.mouseY);
		for (int i=0; i<sliders.size(); i++) {

			Slider a = sliders.get(i);
			if(parentCanvas)a.mouse = mouse;
			a.draw();
		}
	};

	public void draw5(){
		applet.fill(0);
		applet.rect(x,y,20,20);
	};

	public void setBorders(Boolean a) {
		for (int i=0; i<items.size(); i++) {
			Button b = items.get(i);

			b.border = a;
		}
	};

	public void logic() {

		mousePos = new PVector(applet.mouseX,applet.mouseY);
		if(applet.mousePressed&&pos()&&!dpos()&&!drag)m5down = true;
		if(mouse!=null) mousePos = mouse;
		//if(index>-1&&subindex==-1&&!pos()&&applet.mousePressed)index = -1;
		if (mWindow!=null)y = sliders.get(0).valuey;
		if (highlightable) {
			if(!localTheme){
				if (pos(mousePos)) col = theme.menuhcol;
				else col = theme.menufillcol;
			}else{
				if (pos(mousePos)) col = theme.menuhcol;
				else col = theme.menufillcol;
			}
		}
		if(!localTheme){
			if (toggle)col = theme.toggleCol;
			else col = theme.menufillcol;
		}else{
			if (toggle)col = theme.toggleCol;
			else col = theme.menufillcol;
		}

		if ((pos()||toggle)) open_menu = true;
		else open_menu = false;
		if (draggable&&dpos()&&applet.mousePressed&&!drag&&!mdown&&!m5down) {
			mdown = true;
			drag = true;
			dx = applet.mouseX - x;
			dy = applet.mouseY - y;
		}

		if(drag){
			x = applet.mouseX - dx;
			y = applet.mouseY - dy;
			bx = applet.mouseX - dx;
			by = applet.mouseY - dy;
			if(sliderParent!=null)sliderParent.setPos(x,y);

			for (int i=0;i<items.size();i++){
				Button b = items.get(i);
				b.x = x;
			}}

		float X = (applet.mouseX - applet.pmouseX);
		float Y = (applet.mouseY - applet.pmouseY);

		if (!applet.mousePressed) {
			mdown = false;
			mup = true;
			drag = false;
			m5down = false;

		}

		if (t2>0)menuhover = true;
		else menuhover = false;

		if (!pos()) {
			if(toggle)
				if (applet.mousePressed&&!menu&&!menuhover&&sliders.size()==0&&index>0&&!m3down) {
					m3down = true;
				}

			if(m3down&&!applet.mousePressed){
				m3down = false;
				getIndex = true;
			}

		}

		if(index>-1&&subindex>-1&&items.get(subindex).submenu!=null&&items.get(subindex).submenu.parentRight){
			//if( index>-1&&subindex>-1&&items.get(subindex).pos()&&!items.get(subindex).subpos())subindex = -1;
		}else if(index>-1&&subindex>-1&&items.get(subindex).submenu!=null){
			if( index>-1&&subindex>-1&&items.get(subindex).pos()&&!items.get(subindex).subpos())subindex = -1;
		}
		if (subindex!=index&&subindex!=-1&&items.size()>0&&sliders.size()==0)index = -1;


		if (items.size()>0&&index>=0&&toggle) {
			Button a = items.get(index);
			a.highlight2();
		}

		if (type==2) {
			toggle = true;
			if (sliders.size()>0&&sindex>=0&&smdown) {

				Slider b = sliders.get(sindex);

//				b.mouseFunctions();
				if (b.btnpos()||b.pos()||b.mdown)b.btnh = b.h+5;
				if (!b.mdown) b.btnh = b.h;
				//if (b.btnpos()||b.pos()||b.mdown)b.mouseFunctions();
			}

			if (sliders.size()>0&&sindex>=0&&!smdown) {
				Slider b = sliders.get(sindex); 
				b.btnh = b.h+2;
			}}

		if (items.size()>0&&subindex>=0&&subindex==index&&toggle&&sliders.size()==0) {
			Button a = items.get(subindex);

			if (a.submenu!=null&&a.id==subindex) {

				if (a.submenu.pos2()||a.submenu.menu)menu = true;
				else menu = false; 

				a.submenu.draw();
			}
			if (a.dMenu!=null&&a.id==subindex) {

				if (a.dMenu.menu)menu = true;
				else menu = false; 
				

				if(a.pos()&&!a.subpos()){}
				else a.dMenu.displayDropdown();

			}
		}
		if (!animate) {
			if (pos()||toggle) {
				if (bsize<tsize+2) bsize += 0.5;
			} else if (bsize>tsize&&bsize>2)bsize -= 0.5;
		}
		if(!applet.mousePressed){
			m5down = false; 
			if(label==null&&Bms.menuObject==this) {
				mdown = false;
				Bms.menuObject = null;

			}
		}
	};

	public void logic(PVector m) {

		if(mousePos==null)mousePos = new PVector(applet.mouseX,applet.mouseY);
		if(mouse!=null) mousePos = mouse;

		if(applet.mousePressed&&pos(mouse)&&!dpos(mouse)&&!drag)m5down = true;
		//if(index>-1&&subindex==-1&&!pos(mouse)&&applet.mousePressed)index = -1;
		if(applet.mousePressed&&pos(mousePos)&&!dpos(mousePos)&&!drag)m5down = true;
		if(mouse!=null) mousePos = mouse;
		//if(index>-1&&subindex==-1&&!pos()&&applet.mousePressed)index = -1;
		if (mWindow!=null)y = sliders.get(0).valuey;
		if (highlightable) {
			if(!localTheme){
				if (pos(mousePos)) col = theme.menuhcol;
				else col = theme.menufillcol;
			}else{
				if (pos(mousePos)) col = theme.menuhcol;
				else col = theme.menufillcol;
			}
		}
		if(!localTheme){
			if (toggle)col = theme.toggleCol;
			else col = theme.menufillcol;
		}else{
			if (toggle)col = theme.toggleCol;
			else col = theme.menufillcol;
		}

		if ((pos(mousePos)||toggle)) open_menu = true;
		else open_menu = false;
		if (draggable&&dpos(mousePos)&&applet.mousePressed&&!drag&&!mdown&&!m5down) {
			mdown = true;
			drag = true;
			dx = applet.mouseX - x;
			dy = applet.mouseY - y;
		}

		if(drag){
			x = applet.mouseX - dx;
			y = applet.mouseY - dy;
			bx = applet.mouseX - dx;
			by = applet.mouseY - dy;

			for (int i=0;i<items.size();i++){
				Button b = items.get(i);
				b.x = x;
			}}

		float X = (applet.mouseX - applet.pmouseX);
		float Y = (applet.mouseY - applet.pmouseY);

		if (!applet.mousePressed) {
			mdown = false;
			mup = true;
			drag = false;
			m5down = false;

		}

		if (t2>0)menuhover = true;
		else menuhover = false;

		if (!pos(mousePos)) {
			if(toggle)
				if (applet.mousePressed&&!menu&&!menuhover&&sliders.size()==0&&index>0&&!m3down) {
					m3down = true;
				}

			if(m3down&&!applet.mousePressed){
				m3down = false;
				getIndex = true;
			}

		}

		if(index>-1&&subindex>-1&&items.get(subindex).submenu!=null&&items.get(subindex).submenu.parentRight){
			//if( index>-1&&subindex>-1&&items.get(subindex).pos(mousePos)&&!items.get(subindex).subpos(mousePos))subindex = -1;
		}else if(index>-1&&subindex>-1&&items.get(subindex).submenu!=null){
			if( index>-1&&subindex>-1&&items.get(subindex).pos(mousePos)&&!items.get(subindex).subpos(mousePos))subindex = -1;
		}
		if (subindex!=index&&subindex!=-1&&items.size()>0&&sliders.size()==0)index = -1;


		if (items.size()>0&&index>=0&&toggle) {
			Button a = items.get(index);
			a.highlight2();
		}

		if (type==2) {
			toggle = true;
			if (sliders.size()>0&&sindex>=0&&smdown) {

				Slider b = sliders.get(sindex);

//				b.mouseFunctions(mousePos);
				if (b.btnpos(mousePos)||b.pos(mousePos)||b.mdown)b.btnh = b.h+5;
				if (!b.mdown) b.btnh = b.h;
				//if (b.btnpos(mousePos)||b.pos(mousePos)||b.mdown)b.mouseFunctions(mousePos);
			}

			if (sliders.size()>0&&sindex>=0&&!smdown) {
				Slider b = sliders.get(sindex); 
				b.btnh = b.h+2;
			}}

		if (items.size()>0&&subindex>=0&&subindex==index&&toggle&&sliders.size()==0) {
			Button a = items.get(subindex);

			if (a.submenu!=null&&a.id==subindex) {

				if (a.submenu.pos2()||a.submenu.menu)menu = true;
				else menu = false; 

				a.submenu.draw();
			}
			if (a.dMenu!=null&&a.id==subindex) {
				
				if (a.dMenu.menu)menu = true;
				else menu = false; 


				if(a.pos(mousePos)&&!a.subpos(mousePos)){}
				else a.dMenu.displayDropdown();

			}
		}
		if (!animate) {
			if (pos(mousePos)||toggle) {
				if (bsize<tsize+2) bsize += 0.5;
			} else if (bsize>tsize&&bsize>2)bsize -= 0.5;
		}
		if(!applet.mousePressed){
			m5down = false; 
			if(label==null&&Bms.menuObject==this) {
				mdown = false;
				Bms.menuObject = null;

			}
		}
	};

	public void setLink(Menu a) {

		link = a;
	};

	public void setPos(float a,float b) {
		x = a;
		bx = a;
		y = b;
		by = b;
	};

	public void setPiePos(float a,float b) {
		x = a;
		bx = a;
		y = b;
		by = b;

		//		  if(tool)
	};

	public void setHorizontal() {
		horizontal = true;
		float bbw = 0;
		for(int i=0;i<items.size();i++) {
			Button b1 = items.get(i);
			bbw += b1.w + spacing;
			b1.setPos(x+w+i*(b1.w+spacing),y);
		}
		windowWidth = bbw;
		windowHeight = h;
		if(label!=null)w = applet.textWidth(label)+20;
	};

	boolean pos() {

		return applet.mouseX>x&&applet.mouseX<x+w&&applet.mouseY>y&&applet.mouseY<y+h+1;
	};

	boolean pos(PVector m) {

		return m.x>x&&m.x<x+w&&m.y>y&&m.y<y+h+1;
	};

	boolean pos2() {

		return applet.mouseX>x&&applet.mouseX<x+w&&applet.mouseY>y&&applet.mouseY<y+20*(items.size());
	};
	boolean pos2(PVector m) {

		return m.x>x&&m.x<x+w&&m.y>y&&m.y<y+20*(items.size());
	};
	boolean pos3() {

		return applet.mouseX>x&&applet.mouseX<x+w&&applet.mouseY>y&&applet.mouseY<y+20*(items.size());
	};
	boolean pos4() {

		return applet.mouseX>x&&applet.mouseX<x+w&&applet.mouseY>y&&applet.mouseY<y+20*(sliders.size());
	};

	public void drag() {
	};

	boolean dpos() {
		return applet.mouseX>x&&applet.mouseX<x+w&&applet.mouseY>y-10&&applet.mouseY<y;
	};

	boolean dpos(PVector m) {
		return m.x>x&&m.x<x+w&&m.y>y-10&&m.y<y;
	};

	boolean outpos() {
		float X = applet.mouseX;
		float Y = applet.mouseY;
		return (X < x || X > x + w+10) || (Y < y || Y > y + h * (items.size()+1));
	};


	public boolean click(int a){
		return getButton(a)!=null&&getButton(a).click;
	};

	public void click(int i, PApplet a, String b) {
		Button k = items.get(i);
		k.click(a, b);
		k.toggle = false;
		if(!applet.mousePressed)k.mdown=false;
	};

	boolean pos(int i){
		return items.get(i).pos(mousePos);

	};

	// boolean click(int a, Object b, String c){
	//   boolean k = false;
	//   if(click(a)) k = true;

	//   Field field = null;

	//   try{
	//        field = b.getClass().getField(c); 

	//        if((click(a))){
	//          field.set(c, true); 
	//        }else {
	//          field.set(c, false);
	//        }
	//   }catch (NullPointerException e) {
	//   }catch (NoSuchFieldException e) {
	//   }catch (IllegalAccessException e) {
	//   }

	//   return k;
	// };

	boolean itemSelected(){
		boolean k = false;
		for(int i=0;i<items.size();i++){
			Button b = items.get(i);

			if(b.pos()){
				k = true;
				index = i;
				if(applet.mousePressed){
					m4down = true;
				}
			}
		}
		if(!applet.mousePressed)m4down = false;
		return k;
	};

	boolean itemSelected(PGraphics canvas){
		boolean k = false;
		for(int i=0;i<items.size();i++){
			Button b = items.get(i);

			if(b.pos(mouse)){
				k = true;
				index = i;
				if(applet.mousePressed){
					m4down = true;
				}
			}
		}
		if(!applet.mousePressed)m4down = false;
		return k;
	};

	boolean itemSelected(PVector m ){
		boolean k = false;
		for(int i=0;i<items.size();i++){
			Button b = items.get(i);

			if(b.pos(m)){
				k = true;
				index = i;
				if(applet.mousePressed){
					m4down = true;
				}
			}
		}
		if(!applet.mousePressed)m4down = false;
		return k;
	};

	public void click() {
		if (parent==null) {
			boolean k = itemSelected();
			if(k&&applet.mousePressed)m4down = true;
			if(index>-1&&items.get(index).submenu!=null&&items.get(index).submenu.pos())subMenuPos = true;
			else subMenuPos = false;

			if (pos() && applet.mousePressed&&Bms.menuObject==null&&!m3down&&!toggle) {
				toggle = true;
				Bms.menuObject = this;
				m3down = true;
				//println("l1");
			}
			if (!m3down&&pos() && applet.mousePressed&&Bms.menuObject==this&&toggle) {
				m3down = true;
				toggle = false;
				Bms.menuObject.toggle = false;
				Bms.menuObject.index = -1;
				Bms.menuObject=null;
				//println("l2");
			}
			if(applet.mousePressed&&k){
				m3down = true;
			}

			if (!pos() && applet.mousePressed&&!k&&!m4down&&!subMenuPos&&!m3down) {
				m3down = true;
				toggle = false;
				if(Bms.menuObject==this){
					Bms.menuObject.toggle = false;
					Bms.menuObject.index = -1;
					Bms.menuObject=null;
				}
				//println("l3");
			}
		}

		if(!applet.mousePressed){
			m3down = false;
			m4down = false;
		}
	};

	public void click(PGraphics canvas) {
		if (parent==null) {
			boolean k = itemSelected(mouse);
			if(k&&applet.mousePressed)m4down = true;
			if(index>-1&&items.get(index).submenu!=null&&items.get(index).submenu.pos(mouse))subMenuPos = true;
			else subMenuPos = false;

			if (pos(mouse) && applet.mousePressed&&Bms.menuObject==null&&!m3down&&!toggle) {
				toggle = true;
				Bms.menuObject = this;
				m3down = true;
				//println("l1");
			}
			if (!m3down&&pos(mouse) && applet.mousePressed&&Bms.menuObject==this&&toggle) {
				m3down = true;
				toggle = false;
				Bms.menuObject.toggle = false;
				Bms.menuObject.index = -1;
				Bms.menuObject=null;
				//println("l2");
			}
			if(applet.mousePressed&&k){
				m3down = true;
			}

			if (!pos(mouse) && applet.mousePressed&&!k&&!m4down&&!subMenuPos&&!m3down) {
				m3down = true;
				toggle = false;
				if(Bms.menuObject==this){
					Bms.menuObject.toggle = false;
					Bms.menuObject.index = -1;
					Bms.menuObject=null;
				}
				//println("l3");
			}
		}

		if(!applet.mousePressed){
			m3down = false;
			m4down = false;
		}
	};

	public void click(boolean m){

		if(pos()&&applet.mousePressed&&!m2down&&!toggle){
			toggle = true;
			m2down = true;
		}

		if(!applet.mousePressed)m2down = false;
	};

	public boolean clickAll() {
		boolean k = false;
		if(mouse==null)mouse = new PVector(applet.mouseX,applet.mouseY);
		if(toggle)
		for(int i=0;i<items.size();i++) {
			if(items.get(i).click)k = true;
		}
		return k;
	};

	public boolean clickAll(PVector m) {
		boolean k = false;
		if(toggle)
		for(int i=0;i<items.size();i++) {
			if(items.get(i).click)k = true;
		}
		return k;
	};

	// public void selfClick(int i) {
	//   Button k = items.get(i);

	//   return k.click(mouse);

	// };

	boolean selfClick(int i) {
		return getButton(i)!=null &&getButton(i).click;

	};

	public void selfClick(int i, Object o, String b) {
		//Button k = items.get(i);
		//if (link==null)k.selfClick(o, b);
		//else if (link.toggle)k.toggle(o, b);

	};

	public void toggle(int i, String b) {
		Button k = items.get(i);
		k.toggle();
		//k.toggle(o, b);
		;
	};
	public boolean toggle(int i) {
		Button k = items.get(i);
		//		if (link==null)return k.toggle();
		//		else if (link.toggle)
		return toggle&&k.toggle();
		//		else return false;

	};

	public boolean toggle(int i, PVector m) {
		Button k = items.get(i);
		
		if (link==null)return toggle&&k.toggle();
		else if (link.toggle)return toggle&&k.toggle();
		else return false;

	};

	public void toggle(int i, PApplet o, String b) {
		Button k = items.get(i);
		if(toggle)
		if (link==null)k.toggle(o, b);
		else if (link.toggle)k.toggle(o, b);
		;
	};

	public boolean toggle(int i, Object o, String s) {
		boolean k = false;
		if(toggle)
		if(i<items.size()) {
			Button b = items.get(i);
			if(show){
				if (link==null)k =  b.toggle(o, s);
				else if (link.toggle)k =  b.toggle(o, s);
			}
		}
		return k;
	};

	public boolean toggle(int i, Object o, String s,PVector m) {
		boolean k = false;
		if(i<items.size()) {
			Button b = items.get(i);
			if(show){
				if (link==null)k =  b.toggle(o, s,m);
				else if (link.toggle)k =  b.toggle(o, s,m);
			}
		}
		return k;
	};


	//	public void toggle2(int i, Object o, String b) {
	//		Button k = items.get(i);
	//		if (link==null)k.toggle2(o, b);
	//		else if (link.toggle)k.toggle2(o, b);
	//		;
	//	};

	public void toggle(Menu m, int i, Object o, String b) {
		Button k = items.get(i);
		//if(parent!=null&&parent.toggle)
		if (m.toggle)k.toggle(o, b);
	};

	public void sptoggle(int n, Object a, String b, String [] c) {
		Button k = items.get(n);
		k.sptoggle(a, b, c);
	};

	public void sptoggle(int[] n, Object a, String b, String [] c) {
		for(int i=0;i<n.length;i++) {
			Button k = items.get(i);
			k.sptoggle(a, b, c);
		}
	};

	public void sptoggle(int n, Object a, String b, String [] c,PVector m) {
		Button k = items.get(n);
		k.sptoggle(a, b, c,m);
	};

	public void sptoggle(int n, Object a, String b, String [] c,Menu m) {

		Button k = items.get(n);

		k.sptoggle(a, b, c);
	};

	public void sptoggle( Object a, String [] c) {


		for(int i=0;i<c.length;i++){
			Bms.shapes.sptoggle(i,a,c[i],c);
			if(applet.mousePressed)
				if(!items.get(i).pos()){
					items.get(i).toggle = false;
				}
			//	      PApplet.println("item toggle = ",items.get(i).toggle);
		}
	};

	public void sptoggle2( Object a, String [] c,Menu m) {


		for(int i=0;i<c.length;i++){
			Bms.shapes.sptoggle(i,a,c[i],c,m);
		}
	};

	public boolean itoggle(int i, int[] b) {

		return toggle&&getButton(i).itoggle( b);
	};

	public boolean itoggle(int i, String [] c) {

		return toggle&&getButton(i).itoggle(c);
	};

	public boolean itoggle(int[] b) {
		boolean k = false;
		if(toggle)
		for(int i=0;i<b.length;i++) {

			k = getButton(b[i]).itoggle( b);
			if(k)break;
		}
		return k;
	};

	public boolean itoggle( String [] c) {
		boolean k = false;
		if(toggle)
		for(int i=0;i<c.length;i++) {
			for(int j=0;j<items.size();j++) {
				String s = items.get(j).label;
				if(s==c[i]) {
					k = getButton(j).itoggle(c);
					if(k)break;
				}
				if(k)break;

			}}

		return k;
	};

	public boolean itoggle(int i, Object a, String b, String [] c) {

		return toggle&&getButton(i).itoggle(a,b,c);
	};

	public boolean itoggle(int i, Object a, String b, int [] c) {

		return getButton(i).itoggle(a,b,c);
	};

	public boolean itoggle(Object a, String b,int[] c) {
		boolean k = false;
		//		if(Bms.theme.click)
		if(toggle)
		for(int i=0;i<c.length;i++) {

			k = getButton(c[i]).itoggle(a,b,c);
			if(k)break;
		}
		return k;
	};

	public boolean itoggle(Object a, String b, String [] c) {
		boolean k = false;

		if(Bms.theme.click&&toggle)
			for(int i=0;i<c.length;i++) {
				for(int j=0;j<items.size();j++) {
					String s = items.get(j).label;
					if(s==c[i]) {
						k = getButton(j).itoggle(a,b,c);
						if(k)break;
					}
					if(k)break;

				}}

		return k;
	};

	public boolean toggleAll() {
		boolean b1 = false;

		if(toggle) {
			if(parentTab==null) {
				for(int i=0;i<items.size();i++){
					Button k = items.get(i);
					if(k.toggle())b1 = true;
				}
			}else {
				for(int i=0;i<items.size();i++){
					Button k = items.get(i);
					if(k.toggle())b1 = true;
				} 
			}
		}
		return b1;
	};

	public boolean toggleAll(PVector m) {
		boolean b1 = false;

		if (toggle)
			for(int i=0;i<items.size();i++){
				Button k = items.get(i);
				if(k.toggle())b1 = true;
			}
		return b1;
	};

	public boolean toggleAll(PGraphics canvas) {
		boolean b1 = false;

		if (toggle)
			for(int i=0;i<items.size();i++){
				Button k = items.get(i);
				if(k.toggle())b1 = true;
			}

		return b1;
	};

	public void cycle(int b){
		for(int i=0;i<items.size();i++){
			Button k = items.get(i);
			if (toggle)k.cycle(b);
		}
	};

	public void cycle(int b,PVector m){
		for(int i=0;i<items.size();i++){
			Button k = items.get(i);
			if (toggle)k.cycle(b,mouse);
		}
	};

	public void cycle(int b,Object o,String s){
		for(int i=0;i<items.size();i++){
			Button k = items.get(i);
			if (toggle)k.cycle(o,s,b,mouse);
		}
	};

	public void cycle(int b,Object o,String s,PVector m){
		for(int i=0;i<items.size();i++){
			Button k = items.get(i);
			if (toggle)k.cycle(o,s,b,mouse);
		}
	};

	public void slide(float start, float end) {
		sliders.get(0).set(start, end);
	};

	public void set(int a, boolean b) {
		items.get(a).toggle=b;
	};

	public void set(int a, int b) {
		items.get(a).counter=b;
	};



	public void set_parent(Button b) {

		parent = b;

		//for(int i=0;i<m.items.size();i++){
		//  Button k = m.items.get(i);

		//}
	};

	public void setLink(int k) {
		Menu m = items.get(k).submenu;

		m.link = this;
	};

	String[] getItems(){
		String[] s = new String[items.size()];
		for(int i=0;i<items.size();i++){
			s[i] = items.get(i).label;
		}
		return s;
	};

	public void setPlain() {
		for(int i=0;i<items.size();i++){
			Button b = items.get(i);
			b.plain = true;
			b.radio = false;
			b.toggleBox = false;
			b.classicBar = false;
		}
	};

	public void setRadio() {
		float bwidth = 0;
		radio = true;
		for (int i =0; i<items.size(); i++) {
			Button b = items.get(i);
			String l = b.label;

			if (applet.textWidth(l)+20>bwidth)bwidth = applet.textWidth(l)+20;
			//if(
		}
		for(int i=0;i<items.size();i++){
			Button b = items.get(i);
			b.plain = false;
			b.radio = true;
			b.toggleBox = false;
			b.classicBar = false;
			b.rx = bwidth;
		}


		//		    bwidth = 0;
		//		    for (int i =0; i<sliders.size(); i++) {
		//			  Slider b = sliders.get(i);
		//		      String l = b.label;
		//
		//		      if (applet.textWidth(l)+20>bwidth)bwidth = applet.textWidth(l)+20;
		//		      //if(
		//		    }
		for(int i=0;i<sliders.size();i++){
			Slider b = sliders.get(i);

			//b.rx = bwidth;
			b.classic = true;
			b.matrix = false;
			b.pie = false;
			b.radio = true;
			b.bar = false;
			b.square = false;

		}

	};

	public void setClassicBar() {
		classicBar = true;
		for(int i=0;i<items.size();i++){
			Button b = items.get(i);
			b.plain = false;
			b.radio = false;
			b.toggleBox = false;
			b.classicBar = true;
		}

		for(int i=0;i<sliders.size();i++){
			Slider b = sliders.get(i);

			b.classic = true;
			b.matrix = false;
			b.pie = false;
			b.radio = false;
			b.bar = true;
			b.square = false;

		}

	};

	public void setClassicBox() {
		toggleBox = true;
		for(int i=0;i<items.size();i++){
			Button b = items.get(i);
			b.plain = false;
			b.radio = false;
			b.toggleBox = true;
			b.classicBar = false;
		}
	};

	public void setClassicSquare() {
		classicSquare = true;
		for(int i=0;i<items.size();i++){

			Button b = items.get(i);
			b.plain = false;
			b.radio = false;
			b.toggleBox = false;
			b.classicBar = false;
		}

		for(int i=0;i<sliders.size();i++){
			Slider b = sliders.get(i);

			b.classic = true;
			b.matrix = false;
			b.pie = false;
			b.radio = false;
			b.bar = false;
			b.square = true;

		}

	};

	public void setClassicRadio() {
		classicRadio = true;
		for(int i=0;i<items.size();i++){
			Button b = items.get(i);
			b.plain = false;
			b.radio = true;
			b.toggleBox = false;
			b.classicBar = false;
		}

		for(int i=0;i<sliders.size();i++){
			Slider b = sliders.get(i);

			b.classic = true;
			b.matrix = false;
			b.pie = false;
			b.radio = true;
			b.bar = false;
			b.square = false;

		}

	};

	public void setPieSquare() {
		pieSquare = true;
		for(int i=0;i<sliders.size();i++){
			Slider b = sliders.get(i);

			b.classic = false;
			b.matrix = false;
			b.pie = true;
			b.radio = false;
			b.bar = false;
			b.square = true;
			//b.valuex = 2*PConstants.PI;
			b.valuex = applet.map(b.value,0,255,0,2*PConstants.PI);
			b.radius = w/2;
			b.y = y + b.h + (b.h/2)*i;
		}

		if(sliders.size()>0)h = (sliders.get(0).h+sliders.get(0).spacing)*sliders.size();

	};

	public void setButtonRadius(float a){

		if(newTheme==null) newTheme = new Theme(Bms);
		theme = newTheme;
		newTheme.r1 = a;
		newTheme.r2 = a;
		newTheme.r3 = a;
		newTheme.r4 = a;

		newTheme.buttonr1 = a;
		newTheme.buttonr2 = a;
		newTheme.buttonr3 = a;
		newTheme.buttonr4 = a;

		newTheme.tabr1 = a;
		newTheme.tabr2 = a;
		newTheme.tabr3 = a;
		newTheme.tabr4 = a;

		newTheme.sliderr1 = a;
		newTheme.sliderr2 = a;
		newTheme.sliderr3 = a;
		newTheme.sliderr4 = a;

		for(int i=0;i<items.size();i++){
			Button b1 = items.get(i);
			b1.theme = theme;
			b1.themeIndex = Bms.themes.size();
			if(b1.submenu!=null){
				for(int j=0;j<b1.submenu.items.size();j++){
					Button b2 = b1.submenu.items.get(j);
					b2.theme = theme;
				}
			}
		}
	};
	
	public void setButtonRadius(float a,Theme newTheme){

		theme = newTheme;
		theme.r1 = a;
		theme.r2 = a;
		theme.r3 = a;
		theme.r4 = a;

		theme.buttonr1 = a;
		theme.buttonr2 = a;
		theme.buttonr3 = a;
		theme.buttonr4 = a;

		theme.tabr1 = a;
		theme.tabr2 = a;
		theme.tabr3 = a;
		theme.tabr4 = a;

		theme.sliderr1 = a;
		theme.sliderr2 = a;
		theme.sliderr3 = a;
		theme.sliderr4 = a;

		for(int i=0;i<items.size();i++){
			Button b1 = items.get(i);
			b1.theme = theme;
			b1.themeIndex = Bms.themes.size();
			if(b1.submenu!=null){
				for(int j=0;j<b1.submenu.items.size();j++){
					Button b2 = b1.submenu.items.get(j);
					b2.theme = theme;
				}
			}
		}
	};

	public void setSliderRadius(float a){
		if(newTheme==null) newTheme = new Theme(Bms);
		theme = newTheme;

		newTheme.sliderr1 = a;
		newTheme.sliderr2 = a;
		newTheme.sliderr3 = a;
		newTheme.sliderr4 = a;
		
		for(int i=0;i<sliders.size();i++){
			Slider b1 = sliders.get(i);
			b1.theme = theme;
			if(b1.tooltip!=null) {
				b1.tooltip.theme = theme;

			}
		}
	};
	
	public void setSliderRadius(float a,Theme newTheme){
		theme = newTheme;

		theme.sliderr1 = a;
		theme.sliderr2 = a;
		theme.sliderr3 = a;
		theme.sliderr4 = a;

		for(int i=0;i<sliders.size();i++){
			Slider b1 = sliders.get(i);
			b1.theme = theme;
			if(b1.tooltip!=null) {
				b1.tooltip.theme = theme;

			}
		}
	};

	public void setButtonRadius(float a,float b,float c,float d){

		if(newTheme==null) newTheme = new Theme(Bms);
		theme = newTheme;
		newTheme.r1 = a;
		newTheme.r2 = b;
		newTheme.r3 = c;
		newTheme.r4 = d;

		newTheme.buttonr1 = a;
		newTheme.buttonr2 = b;
		newTheme.buttonr3 = c;
		newTheme.buttonr4 = d;
		
		for(int i=0;i<items.size();i++){
			Button b1 = items.get(i);
			b1.theme = theme;
			b1.themeIndex = Bms.themes.size();
			if(b1.submenu!=null){
				for(int j=0;j<b1.submenu.items.size();j++){
					Button b2 = b1.submenu.items.get(j);
					b2.theme = theme;
				}
			}
		}
	};
	
	public void setButtonRadius(float a,float b,float c,float d,Theme newTheme){

		theme = newTheme;
		theme.r1 = a;
		theme.r2 = b;
		theme.r3 = c;
		theme.r4 = d;

		theme.buttonr1 = a;
		theme.buttonr2 = b;
		theme.buttonr3 = c;
		theme.buttonr4 = d;

		for(int i=0;i<items.size();i++){
			Button b1 = items.get(i);
			b1.theme = theme;
			b1.themeIndex = Bms.themes.size();
			if(b1.submenu!=null){
				for(int j=0;j<b1.submenu.items.size();j++){
					Button b2 = b1.submenu.items.get(j);
					b2.theme = theme;
				}
			}
		}
	};

	public void setSliderRadius(float a,float b,float c,float d){
		if(newTheme==null) newTheme = new Theme(Bms);
		theme = newTheme;

		newTheme.sliderr1 = a;
		newTheme.sliderr2 = a;
		newTheme.sliderr3 = a;
		newTheme.sliderr4 = a;
		
		for(int i=0;i<sliders.size();i++){
			Slider b1 = sliders.get(i);
			b1.theme = theme;
			if(b1.tooltip!=null) {
				b1.tooltip.theme = theme;

			}
		}
	};
	
	public void setSliderRadius(float a,float b,float c,float d,Theme newTheme){
		theme = newTheme;

		theme.sliderr1 = a;
		theme.sliderr2 = a;
		theme.sliderr3 = a;
		theme.sliderr4 = a;
		

		for(int i=0;i<sliders.size();i++){
			Slider b1 = sliders.get(i);
			b1.theme = theme;
			if(b1.tooltip!=null) {
				b1.tooltip.theme = theme;

			}
		}
	};

	public void setAlignment(String s){

		if(s=="CENTER"||s=="center"||s=="Center"){

			xoff = (w-applet.textWidth(label))/2;
			yoff = h/3;

			for(int i=0;i<items.size();i++){
				Button b = items.get(i);
				b.tyoff = b.h/3;
				b.txoff = (b.w-applet.textWidth(b.label))/2-((b.w-applet.textWidth(b.label))/2)/2;
				if(b.submenu!=null){
					for(int j=0;j<b.submenu.items.size();j++){
						Button b2 = b.submenu.items.get(j);
						if(b2.h>20)b2.tyoff = b2.h/3;
						b2.txoff = (b2.w-applet.textWidth(b2.label))/2-((b2.w-applet.textWidth(b2.label))/2)/2;
						//b2.txoff = b2.w-applet.textWidth(b2.label)/2;
					}
				}
			}

			for(int i=0;i<sliders.size();i++){
				Slider b = sliders.get(i);
			}
		}

		if(s=="RIGHT"||s=="right"||s=="Right"){

			xoff = (w-applet.textWidth(label))-((w-applet.textWidth(label)))/4;
			yoff = h/3;

			for(int i=0;i<items.size();i++){
				Button b = items.get(i);
				b.tyoff = b.h/3;
				b.txoff = (b.w-applet.textWidth(b.label))-((b.w-applet.textWidth(b.label)))/4;
				if(b.submenu!=null){
					for(int j=0;j<b.submenu.items.size();j++){
						Button b2 = b.submenu.items.get(j);
						b2.tyoff = b2.h/3;
						b2.txoff = (b2.w-applet.textWidth(b2.label))-((b2.w-applet.textWidth(b2.label)))/4;
					}
				}
			}

			for(int i=0;i<sliders.size();i++){
				Slider b = sliders.get(i);
			}
		}

		if(s=="LEFT"||s=="left"||s=="Left"){

			xoff = 5;
			yoff = h/3;

			for(int i=0;i<items.size();i++){
				Button b = items.get(i);
				b.tyoff = b.h/3;
				b.txoff = 0;
				if(b.submenu!=null){
					for(int j=0;j<b.submenu.items.size();j++){
						Button b2 = b.submenu.items.get(j);
						b2.tyoff = b2.h/3;
						b2.txoff = 0;
					}
				}
			}

			for(int i=0;i<sliders.size();i++){
				Slider b = sliders.get(i);
			}
		}
	};

	public void highlight(){

		if(!pos()){
			col = theme.menufillcol;
		}
		else{
			col = theme.menuhcol;
		}
		if(toggle){
			col = theme.toggleCol;
		}
		if(toggle&&pos()){
			col = applet.color(theme.menufillcol,100);
		}
	};

	public void highlight(PGraphics canvas){
		if(!pos(mouse)){
			col = theme.menufillcol;
		}
		else{
			col = theme.menuhcol;
		}
		if(toggle){
			col = theme.toggleCol;
		}
		if(toggle&&pos(mouse)){
			col = applet.color(theme.menufillcol,100);
		}
	};

	public void setBms(BMS bms) {
		Bms = bms;
		applet = bms.applet;
		for(int i=0;i<items.size();i++) {
			Button b = items.get(i);
			b.Bms = Bms;
			b.applet = applet;
			b.initColors();
		}
		for(int i=0;i<sliders.size();i++) {
			Slider b = sliders.get(i);
			b.Bms = Bms;
			b.applet = applet;
			b.initColors();
		}
	};

	public void setTab(tab t) {
		parentTab = t;
		applet = t.applet;
		for(int i=0;i<items.size();i++) {
			Button b = items.get(i);
			b.parentTab = t;
			b.applet = applet;
			b.initColors();
		}
		for(int i=0;i<sliders.size();i++) {
			Slider b = sliders.get(i);
			b.parentTab = t;
			b.applet = applet;
			b.initColors();
		}
	};

	public void setclassicBar() {
		classicBar = true;
		float bwidth = 0;
		for (int i =0; i<items.size(); i++) {
			Button l = items.get(i);

			if (applet.textWidth(l.label)+20>bwidth)bwidth = applet.textWidth(l.label)+20;
		}

		for (int i =0; i<items.size(); i++) {

			Button a = items.get(i);

			a.rx = bwidth;
			a.parent = this;
			a.classicBar = true;
			a.radio = false;
			a.plain = false;
			a.toggleBox = false;
		}

		w = bwidth+w;
		twidth = w;
	};

	public void setToggleBox() {
		toggleBox = true;
		float bwidth = 0;
		for (int i =0; i<items.size(); i++) {
			Button l = items.get(i);

			if (applet.textWidth(l.label)+20>bwidth)bwidth = applet.textWidth(l.label)+20;
		}

		for (int i =0; i<items.size(); i++) {

			Button a = items.get(i);

			a.rx = bwidth;
			a.parent = this;
			a.classicBar = false;
			a.radio = false;
			a.plain = false;
			a.toggleBox = true;
		}

		w = bwidth+w;
		twidth = w;
	};

	public void setBorder() {
		border = true;
	};

	public void setAllBorder(boolean k) {
		border = true;
		localTheme = true;
		if(k) {
			for(int i=0;i<items.size();i++) {
				Button b = items.get(i);
				b.border = true;
				b.localTheme = true;
			}
			for(int i=0;i<sliders.size();i++) {
				Slider b = sliders.get(i);
				b.border = true;
				b.localTheme = true;
			}
		}else {
			for(int i=0;i<items.size();i++) {
				Button b = items.get(i);
				b.border = false;
				b.localTheme = true;
			}
			for(int i=0;i<sliders.size();i++) {
				Slider b = sliders.get(i);
				b.border = false;
				b.localTheme = true;
			}
		}
	};

	public void setTransparency(float a) {
		transparency = a;
		localTheme = true;
	};

	public void setAllTransparency(float a) {
		transparency = a;
		localTheme = true;
		for(int i=0;i<items.size();i++) {
			Button b = items.get(i);
			b.transparency = a;
			b.localTheme = true;
		}
		for(int i=0;i<sliders.size();i++) {
			Slider b = sliders.get(i);
			b.transparency = a;
			b.localTheme = true;
		}
	};

	public void setMouse() {
		if(mouse!=null) {
			for(int i=0;i<items.size();i++) {
				Button b = items.get(i);
				b.mouse = mouse;
			}
			for(int i=0;i<sliders.size();i++) {
				Slider s = sliders.get(i);
				s.mouse = mouse;
			}
		}

	};

	public void initColors() {
		col = Bms.fcol;
		bcol = Bms.bcol;
		fcol = Bms.fcol;
		hcol = Bms.hcol;
		toggleCol = Bms.toggleCol;

	};

	public void setTextColor(int c) {
		if(newTheme==null) newTheme = new Theme(Bms);
		theme = newTheme;
		initColors();
		tcol = c;
		localTheme = true;
	};
	
	public void setTextColor(float a, float b,float c,float d) {
		if(newTheme==null) newTheme = new Theme(Bms);
		theme = newTheme;
		initColors();
		tcol = applet.color(a,b,c,d);
		localTheme = true;
	};
	
	public void setTextColor(Theme t) {
		theme = t;
		initColors();
		tcol = t.menutextcol;
		localTheme = true;
	};
	
	public void setTextCol(int c) {
		if(newTheme==null) newTheme = new Theme(Bms);
		theme = newTheme;
		initColors();
		tcol = c;
		localTheme = true;
	};
	
	public void setTextCol(float a, float b,float c,float d) {
		if(newTheme==null) newTheme = new Theme(Bms);
		theme = newTheme;
		initColors();
		tcol = applet.color(a,b,c,d);
		localTheme = true;
	};
	
	public void setTextCol(Theme t) {
		theme = t;
		initColors();
		tcol = t.menutextcol;
		localTheme = true;
	};

	public void setAllTextColor(int c) {
		if(newTheme==null) newTheme = new Theme(Bms);
		theme = newTheme;
		tcol = c;
		localTheme = true;

		for(int i=0;i<items.size();i++) {
			Button b = items.get(i);
			b.initColors();
			b.localTheme = true;
			b.tcol = c;
		}
		for(int i=0;i<sliders.size();i++) {
			Slider s = sliders.get(i);
			s.initColors();
			s.localTheme = true;
			s.tcol = c;
		}
	};
	
	public void setAllTextColor(float c) {
		if(newTheme==null) newTheme = new Theme(Bms);
		theme = newTheme;
		tcol = applet.color(c);
		localTheme = true;

		for(int i=0;i<items.size();i++) {
			Button b = items.get(i);
			b.initColors();
			b.localTheme = true;
			b.tcol = applet.color(c);
		}
		for(int i=0;i<sliders.size();i++) {
			Slider s = sliders.get(i);
			s.initColors();
			s.localTheme = true;
			s.tcol = applet.color(c);
		}
	};
	
	public void setAllTextColor(float a, float b,float c,float d) {
		if(newTheme==null) newTheme = new Theme(Bms);
		theme = newTheme;
		tcol = applet.color(a,b,c,d);
		localTheme = true;

		for(int i=0;i<items.size();i++) {
			Button b1 = items.get(i);
			b1.initColors();
			b1.localTheme = true;
			b1.tcol = applet.color(a,b,c,d);
		}
		for(int i=0;i<sliders.size();i++) {
			Slider s = sliders.get(i);
			s.initColors();
			s.localTheme = true;
			s.tcol = applet.color(a,b,c,d);
		}
	};
	
	public void setAllTextColor(int a, int b,int c,int d) {
		if(newTheme==null) newTheme = new Theme(Bms);
		theme = newTheme;
		tcol = applet.color(a,b,c,d);
		localTheme = true;

		for(int i=0;i<items.size();i++) {
			Button b1 = items.get(i);
			b1.initColors();
			b1.localTheme = true;
			b1.tcol = applet.color(a,b,c,d);
		}
		for(int i=0;i<sliders.size();i++) {
			Slider s = sliders.get(i);
			s.initColors();
			s.localTheme = true;
			s.tcol = applet.color(a,b,c,d);
		}
	};
	
	public void setAllTextColor(Theme t) {
		tcol = theme.menutextcol;
		localTheme = true;

		for(int i=0;i<items.size();i++) {
			Button b1 = items.get(i);
			b1.initColors();
			b1.localTheme = true;
			b1.tcol = tcol;
		}
		for(int i=0;i<sliders.size();i++) {
			Slider s = sliders.get(i);
			s.initColors();
			s.localTheme = true;
			s.tcol = tcol;
		}
	};

	public void setGrid(int i,int j) {
		cols = i;
		rows = j;
		ghSpacing = 0;
		gvSpacing = 0;
		grid = true;

	};

	public void setGrid(int i,int j,float h,float v) {
		cols = i;
		rows = j;
		ghSpacing = h;
		gvSpacing = v;
		grid = true;
	};

	public void setCompact(int i,int j,float h,float v) {
		compact = true;
	};

	public Button getButton(int i) {
		if(i<items.size())return items.get(i);
		else return null;
	};

	public Slider getSlider(int i) {
		if(i<sliders.size())return sliders.get(i);
		else return null;
	};

	public Menu getSubMenu(int i) {
		if(i<items.size()&&items.get(i).submenu!=null)return items.get(i).submenu;
		else return null;
	};

	public void setRadius(float a){
		if(newTheme==null) newTheme = new Theme(Bms);
		theme = newTheme;
		newTheme.r1 = a;
		newTheme.r2 = a;
		newTheme.r3 = a;
		newTheme.r4 = a;

		newTheme.buttonr1 = a;
		newTheme.buttonr2 = a;
		newTheme.buttonr3 = a;
		newTheme.buttonr4 = a;

		newTheme.tabr1 = a;
		newTheme.tabr2 = a;
		newTheme.tabr3 = a;
		newTheme.tabr4 = a;

		newTheme.sliderr1 = a;
		newTheme.sliderr2 = a;
		newTheme.sliderr3 = a;
		newTheme.sliderr4 = a;



		for(int i=0;i<items.size();i++){
			Button b1 = items.get(i);
			b1.theme = theme;
			b1.themeIndex = Bms.themes.size();
			if(b1.submenu!=null){
				for(int j=0;j<b1.submenu.items.size();j++){
					Button b2 = b1.submenu.items.get(j);
					b2.theme = theme;
				}
			}
		}

		for(int i=0;i<sliders.size();i++){
			Slider b1 = sliders.get(i);
			b1.theme = theme;
			if(b1.tooltip!=null) {
				b1.tooltip.theme = theme;

			}
		}

	};

	public void setRadius(float a,float b,float c,float d){
		if(newTheme==null) newTheme = new Theme(Bms);
		theme = newTheme;
		newTheme.r1 = a;
		newTheme.r2 = b;
		newTheme.r3 = c;
		newTheme.r4 = d;

		newTheme.buttonr1 = a;
		newTheme.buttonr2 = b;
		newTheme.buttonr3 = c;
		newTheme.buttonr4 = d;

		newTheme.tabr1 = a;
		newTheme.tabr2 = a;
		newTheme.tabr3 = a;
		newTheme.tabr4 = a;

		newTheme.sliderr1 = a;
		newTheme.sliderr2 = a;
		newTheme.sliderr3 = a;
		newTheme.sliderr4 = a;



		for(int i=0;i<items.size();i++){
			Button b1 = items.get(i);
			b1.theme = theme;
			b1.themeIndex = Bms.themes.size();
			if(b1.submenu!=null){
				for(int j=0;j<b1.submenu.items.size();j++){
					Button b2 = b1.submenu.items.get(j);
					b2.theme = theme;
				}
			}
		}

		for(int i=0;i<sliders.size();i++){
			Slider b1 = sliders.get(i);
			b1.theme = theme;
			if(b1.tooltip!=null) {
				b1.tooltip.theme = theme;

			}
		}

	};

	public void setBorder(boolean k) {
		if(newTheme==null) newTheme = new Theme(Bms);
		theme = newTheme;
		theme.dockborder = k;
		theme.buttonborder = k;

		newTheme.tabborder = k;
		newTheme.sliderborder = k;

		for(int i=0;i<items.size();i++){
			Button b1 = items.get(i);
			b1.theme = theme;
			b1.themeIndex = Bms.themes.size();
			if(b1.submenu!=null){
				for(int j=0;j<b1.submenu.items.size();j++){
					Button b2 = b1.submenu.items.get(j);
					b2.theme = theme;
				}
			}
		}

		for(int i=0;i<sliders.size();i++){
			Slider b1 = sliders.get(i);
			b1.theme = theme;
			if(b1.tooltip!=null) {
				b1.tooltip.theme = theme;
			}
		}
	};

	public void setStrokeSize(float k) {
		if(newTheme==null) newTheme = new Theme(Bms);
		theme = newTheme;

		theme.buttonstrokesize = k;
		theme.sliderstrokesize = k;
		theme.tabstrokesize = k;

		for(int i=0;i<items.size();i++){
			Button b1 = items.get(i);
			b1.theme = theme;
			b1.themeIndex = Bms.themes.size();
			if(b1.submenu!=null){
				for(int j=0;j<b1.submenu.items.size();j++){
					Button b2 = b1.submenu.items.get(j);
					b2.theme = theme;
				}
			}
		}

		for(int i=0;i<sliders.size();i++){
			Slider b1 = sliders.get(i);
			b1.theme = theme;
			if(b1.tooltip!=null) {
				b1.tooltip.theme = theme;
			}
		}
	};

	public void setStrokeCol(int k) {
		if(newTheme==null) newTheme = new Theme(Bms);
		theme = newTheme;
		theme.buttonstrokecol = k;
		theme.sliderstrokecol = k;
		theme.tabstrokecol = k;

		for(int i=0;i<items.size();i++){
			Button b1 = items.get(i);
			b1.theme = theme;
			b1.themeIndex = Bms.themes.size();
			if(b1.submenu!=null){
				for(int j=0;j<b1.submenu.items.size();j++){
					Button b2 = b1.submenu.items.get(j);
					b2.theme = theme;
				}
			}
		}

		for(int i=0;i<sliders.size();i++){
			Slider b1 = sliders.get(i);
			b1.theme = theme;
			if(b1.tooltip!=null) {
				b1.tooltip.theme = theme;
			}
		}
	};

	public void setStrokeCol(float a,float b,float c) {
		if(newTheme==null) newTheme = new Theme(Bms);
		theme = newTheme;
		theme.buttonstrokecol = applet.color(a,b,c);
		theme.sliderstrokecol = applet.color(a,b,c);
		theme.tabstrokecol = applet.color(a,b,c);

		for(int i=0;i<items.size();i++){
			Button b1 = items.get(i);
			b1.theme = theme;
			b1.themeIndex = Bms.themes.size();
			if(b1.submenu!=null){
				for(int j=0;j<b1.submenu.items.size();j++){
					Button b2 = b1.submenu.items.get(j);
					b2.theme = theme;
				}
			}
		}

		for(int i=0;i<sliders.size();i++){
			Slider b1 = sliders.get(i);
			b1.theme = theme;
			if(b1.tooltip!=null) {
				b1.tooltip.theme = theme;
			}
		}
	};

	public void setStrokeCol(float a,float b,float c,float d) {
		if(newTheme==null) newTheme = new Theme(Bms);
		theme = newTheme;
		theme.buttonstrokecol = applet.color(a,b,c,d);
		theme.sliderstrokecol = applet.color(a,b,c,d);
		theme.tabstrokecol = applet.color(a,b,c,d);

		for(int i=0;i<items.size();i++){
			Button b1 = items.get(i);
			b1.theme = theme;
			b1.themeIndex = Bms.themes.size();
			if(b1.submenu!=null){
				for(int j=0;j<b1.submenu.items.size();j++){
					Button b2 = b1.submenu.items.get(j);
					b2.theme = theme;
				}
			}
		}

		for(int i=0;i<sliders.size();i++){
			Slider b1 = sliders.get(i);
			b1.theme = theme;
			if(b1.tooltip!=null) {
				b1.tooltip.theme = theme;
			}
		}
	};

	public void setFillCol(int k) {
		if(newTheme==null) newTheme = new Theme(Bms);
		theme = newTheme;
		theme.buttonfillcol = k;
		theme.sliderfillcol = k;
		theme.tabfillcol = k;

		for(int i=0;i<items.size();i++){
			Button b1 = items.get(i);
			b1.theme = theme;
			b1.themeIndex = Bms.themes.size();
			if(b1.submenu!=null){
				for(int j=0;j<b1.submenu.items.size();j++){
					Button b2 = b1.submenu.items.get(j);
					b2.theme = theme;
				}
			}
		}

		for(int i=0;i<sliders.size();i++){
			Slider b1 = sliders.get(i);
			b1.theme = theme;
			if(b1.tooltip!=null) {
				b1.tooltip.theme = theme;
			}
		}
	};

	public void setFillCol(float a,float b,float c) {
		if(newTheme==null) newTheme = new Theme(Bms);
		theme = newTheme;
		theme.buttonfillcol = applet.color(a,b,c);
		theme.sliderfillcol = applet.color(a,b,c);
		theme.tabfillcol = applet.color(a,b,c);

		for(int i=0;i<items.size();i++){
			Button b1 = items.get(i);
			b1.theme = theme;
			b1.themeIndex = Bms.themes.size();
			if(b1.submenu!=null){
				for(int j=0;j<b1.submenu.items.size();j++){
					Button b2 = b1.submenu.items.get(j);
					b2.theme = theme;
				}
			}
		}

		for(int i=0;i<sliders.size();i++){
			Slider b1 = sliders.get(i);
			b1.theme = theme;
			if(b1.tooltip!=null) {
				b1.tooltip.theme = theme;
			}
		}
	};

	public void setFillCol(float a,float b,float c,float d) {
		if(newTheme==null) newTheme = new Theme(Bms);
		theme = newTheme;
		theme.buttonfillcol = applet.color(a,b,c,d);
		theme.sliderfillcol = applet.color(a,b,c,d);
		theme.tabfillcol = applet.color(a,b,c,d);

		for(int i=0;i<items.size();i++){
			Button b1 = items.get(i);
			b1.theme = theme;
			b1.themeIndex = Bms.themes.size();
			if(b1.submenu!=null){
				for(int j=0;j<b1.submenu.items.size();j++){
					Button b2 = b1.submenu.items.get(j);
					b2.theme = theme;
				}
			}
		}

		for(int i=0;i<sliders.size();i++){
			Slider b1 = sliders.get(i);
			b1.theme = theme;
			if(b1.tooltip!=null) {
				b1.tooltip.theme = theme;
			}
		}
	};

	public void setRadius(Theme theme, float a){
		newTheme = theme;
		this.theme = theme;
		newTheme.r1 = a;
		newTheme.r2 = a;
		newTheme.r3 = a;
		newTheme.r4 = a;

		newTheme.buttonr1 = a;
		newTheme.buttonr2 = a;
		newTheme.buttonr3 = a;
		newTheme.buttonr4 = a;

		newTheme.tabr1 = a;
		newTheme.tabr2 = a;
		newTheme.tabr3 = a;
		newTheme.tabr4 = a;

		newTheme.sliderr1 = a;
		newTheme.sliderr2 = a;
		newTheme.sliderr3 = a;
		newTheme.sliderr4 = a;

		for(int i=0;i<items.size();i++){
			Button b1 = items.get(i);
			b1.theme = theme;
			b1.themeIndex = Bms.themes.size();
			if(b1.submenu!=null){
				for(int j=0;j<b1.submenu.items.size();j++){
					Button b2 = b1.submenu.items.get(j);
					b2.theme = theme;
				}
			}
		}

		for(int i=0;i<sliders.size();i++){
			Slider b1 = sliders.get(i);
			b1.theme = theme;
			if(b1.tooltip!=null) {
				b1.tooltip.theme = theme;

			}
		}

	};

	public void setRadius(Theme theme, float a,float b,float c,float d){
		newTheme = theme;
		this.theme = theme;
		newTheme.r1 = a;
		newTheme.r2 = b;
		newTheme.r3 = c;
		newTheme.r4 = d;

		newTheme.buttonr1 = a;
		newTheme.buttonr2 = b;
		newTheme.buttonr3 = c;
		newTheme.buttonr4 = d;

		newTheme.tabr1 = a;
		newTheme.tabr2 = a;
		newTheme.tabr3 = a;
		newTheme.tabr4 = a;

		newTheme.sliderr1 = a;
		newTheme.sliderr2 = a;
		newTheme.sliderr3 = a;
		newTheme.sliderr4 = a;

		for(int i=0;i<items.size();i++){
			Button b1 = items.get(i);
			b1.theme = theme;
			b1.themeIndex = Bms.themes.size();
			if(b1.submenu!=null){
				for(int j=0;j<b1.submenu.items.size();j++){
					Button b2 = b1.submenu.items.get(j);
					b2.theme = theme;
				}
			}
		}

		for(int i=0;i<sliders.size();i++){
			Slider b1 = sliders.get(i);
			b1.theme = theme;
			if(b1.tooltip!=null) {
				b1.tooltip.theme = theme;

			}
		}

	};

	public void setBorder(Theme theme, boolean k) {
		newTheme = theme;
		this.theme.buttonborder = k;

		newTheme.tabborder = k;

		newTheme.sliderborder = k;



		for(int i=0;i<items.size();i++){
			Button b1 = items.get(i);
			b1.theme = theme;
			b1.themeIndex = Bms.themes.size();
			if(b1.submenu!=null){
				for(int j=0;j<b1.submenu.items.size();j++){
					Button b2 = b1.submenu.items.get(j);
					b2.theme = theme;
				}
			}
		}

		for(int i=0;i<sliders.size();i++){
			Slider b1 = sliders.get(i);
			b1.theme = theme;
			if(b1.tooltip!=null) {
				b1.tooltip.theme = theme;
			}
		}
	};

	public void setStrokeSize(Theme theme, float k) {
		newTheme = theme;
		this.theme.buttonstrokesize = k;

		newTheme.sliderstrokesize = k;
		newTheme.tabstrokesize = k;

		for(int i=0;i<items.size();i++){
			Button b1 = items.get(i);
			b1.theme = theme;
			b1.themeIndex = Bms.themes.size();
			if(b1.submenu!=null){
				for(int j=0;j<b1.submenu.items.size();j++){
					Button b2 = b1.submenu.items.get(j);
					b2.theme = theme;
				}
			}
		}

		for(int i=0;i<sliders.size();i++){
			Slider b1 = sliders.get(i);
			b1.theme = theme;
			if(b1.tooltip!=null) {
				b1.tooltip.theme = theme;
			}
		}
	};

	public void setStrokeCol(Theme theme, int k) {
		newTheme = theme;
		theme.buttonstrokecol = k;

		newTheme.sliderstrokecol = k;
		newTheme.tabstrokecol = k;

		for(int i=0;i<items.size();i++){
			Button b1 = items.get(i);
			b1.theme = theme;
			b1.themeIndex = Bms.themes.size();
			if(b1.submenu!=null){
				for(int j=0;j<b1.submenu.items.size();j++){
					Button b2 = b1.submenu.items.get(j);
					b2.theme = theme;
				}
			}
		}

		for(int i=0;i<sliders.size();i++){
			Slider b1 = sliders.get(i);
			b1.theme = theme;
			if(b1.tooltip!=null) {
				b1.tooltip.theme = theme;
			}
		}
	};

	public void setStrokeCol(Theme theme, float a,float b,float c) {
		newTheme = theme;
		this.theme.buttonstrokecol = applet.color(a,b,c);

		newTheme.sliderstrokecol = applet.color(a,b,c);
		newTheme.tabstrokecol = applet.color(a,b,c);

		for(int i=0;i<items.size();i++){
			Button b1 = items.get(i);
			b1.theme = theme;
			b1.themeIndex = Bms.themes.size();
			if(b1.submenu!=null){
				for(int j=0;j<b1.submenu.items.size();j++){
					Button b2 = b1.submenu.items.get(j);
					b2.theme = theme;
				}
			}
		}

		for(int i=0;i<sliders.size();i++){
			Slider b1 = sliders.get(i);
			b1.theme = theme;
			if(b1.tooltip!=null) {
				b1.tooltip.theme = theme;
			}
		}
	};

	public void setStrokeCol(Theme theme, float a,float b,float c,float d) {
		newTheme = theme;
		this.theme = newTheme;
		this.theme.buttonstrokecol = applet.color(a,b,c,d);

		newTheme.sliderstrokecol = applet.color(a,b,c,d);
		newTheme.tabstrokecol = applet.color(a,b,c,d);

		for(int i=0;i<items.size();i++){
			Button b1 = items.get(i);
			b1.theme = theme;
			b1.themeIndex = Bms.themes.size();
			if(b1.submenu!=null){
				for(int j=0;j<b1.submenu.items.size();j++){
					Button b2 = b1.submenu.items.get(j);
					b2.theme = theme;
				}
			}
		}

		for(int i=0;i<sliders.size();i++){
			Slider b1 = sliders.get(i);
			b1.theme = theme;
			if(b1.tooltip!=null) {
				b1.tooltip.theme = theme;
			}
		}
	};

	public void setFillCol(Theme theme, int k) {
		newTheme = theme;

		theme.buttonfillcol = k;

		newTheme.sliderfillcol = k;
		newTheme.tabfillcol = k;

		for(int i=0;i<items.size();i++){
			Button b1 = items.get(i);
			b1.theme = theme;
			b1.themeIndex = Bms.themes.size();
			if(b1.submenu!=null){
				for(int j=0;j<b1.submenu.items.size();j++){
					Button b2 = b1.submenu.items.get(j);
					b2.theme = theme;
				}
			}
		}

		for(int i=0;i<sliders.size();i++){
			Slider b1 = sliders.get(i);
			b1.theme = theme;
			if(b1.tooltip!=null) {
				b1.tooltip.theme = theme;
			}
		}
	};

	public void setFillCol(Theme theme, float a,float b,float c) {
		if(newTheme==null) newTheme = new Theme(Bms);
		theme = newTheme;

		theme.buttonfillcol = applet.color(a,b,c);

		newTheme.sliderfillcol = applet.color(a,b,c);
		newTheme.tabfillcol = applet.color(a,b,c);

		for(int i=0;i<items.size();i++){
			Button b1 = items.get(i);
			b1.theme = theme;
			b1.themeIndex = Bms.themes.size();
			if(b1.submenu!=null){
				for(int j=0;j<b1.submenu.items.size();j++){
					Button b2 = b1.submenu.items.get(j);
					b2.theme = theme;
				}
			}
		}

		for(int i=0;i<sliders.size();i++){
			Slider b1 = sliders.get(i);
			b1.theme = theme;
			if(b1.tooltip!=null) {
				b1.tooltip.theme = theme;
			}
		}
	};

	public void setFillCol(Theme theme, float a,float b,float c,float d) {
		if(newTheme==null) newTheme = new Theme(Bms);
		theme = newTheme;

		theme.buttonfillcol = applet.color(a,b,c,d);

		newTheme.sliderfillcol = applet.color(a,b,c,d);
		newTheme.tabfillcol = applet.color(a,b,c,d);

		for(int i=0;i<items.size();i++){
			Button b1 = items.get(i);
			b1.theme = theme;
			b1.themeIndex = Bms.themes.size();
			if(b1.submenu!=null){
				for(int j=0;j<b1.submenu.items.size();j++){
					Button b2 = b1.submenu.items.get(j);
					b2.theme = theme;
				}
			}
		}

		for(int i=0;i<sliders.size();i++){
			Slider b1 = sliders.get(i);
			b1.theme = theme;
			if(b1.tooltip!=null) {
				b1.tooltip.theme = theme;
			}
		}
	};

	public Menu createSubMenu(int i,String []s) {
		Button b3 = null;
		if(i<items.size()) {
			b3 = getButton(i);
			b3.submenu  = new Menu(b3.x+b3.w, b3.y, 70, s, Bms);
			b3.submenu.parentMenu = this;
			b3.submenu.subId = i;
			setLink(i);
		}
		if(b3!=null)return b3.submenu;
		else return null;
	};

	public Menu createSubMenu(int i,String []s,float w) {
		Button b3 = null;
		if(i<items.size()) {
			b3 = getButton(i);
			b3.submenu  = new Menu(b3.x+b3.w, b3.y, 70, s, Bms);
			b3.submenu.parentMenu = this;
			setLink(i);
		}
		if(b3!=null)return b3.submenu;
		else return null;
	};
	public Menu createSubmenu(int i,int w,String[]s) {
		Button b3 = getButton(i);
		b3.submenu  = new Menu(b3.x+b3.w, b3.y, 70, s, Bms);
		b3.submenu.parentMenu = this;
//		b3.submenu.setClassicBar();
		setLink(i);
		return b3.submenu;
	};
	
	public Menu getSub(int i) {
		
		if(getButton(i)!=null) return getButton(i).submenu;
		else return null;
	};
};
