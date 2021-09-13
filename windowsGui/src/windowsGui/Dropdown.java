package windowsGui;

import processing.core.PApplet;
import processing.core.PGraphics;
import processing.core.PVector;

public class Dropdown extends Menu {
	//	  BMS bms;
	//	  PApplet applet;
	public Slider slider;
	public float windowx,bwindowx,hiddenw,r1,r2,r3,r4,txoff,tyoff;
	public int dcount,windowSize = 5,arrayIndex;
	public boolean dclick,visible = true,dclose,draggable,mcoord,BMSbound;
	public Button title;
	public Boundary boundary;
	public PVector mouse;
	public tab parentTab;
	public Menu subMenu;
	public Dropdown dMenu;
	public Theme theme,newTheme;
	public Dropdown() {

	};

	public Dropdown(float xx, float yy, float ww, float h, String[] Labels,BMS bms) {

		Bms = bms;
		applet = bms.applet;
		theme = Bms.theme;
		dmenu = true;
		x = xx;
		y = yy;
		bx = x;
		by = y;

		w = ww;

		setLabels(Labels);

		windowx = windowSize * h;
		bwindowx = windowx;
		boundary = new Boundary(x,y,w,h,4,bms);
		setBms(bms);
	};
	
	public Dropdown(float xx, float yy, float ww, float h,String l, String[] Labels,BMS bms) {

		Bms = bms;
		applet = bms.applet;
		theme = Bms.theme;
		dmenu = true;
		x = xx;
		y = yy;
		bx = x;
		by = y;

		w = ww;
		label = l;
		blabel = l;

		setLabels(Labels);

		windowx = windowSize * h;
		bwindowx = windowx;
		boundary = new Boundary(x,y,w,h,4,bms);
		setBms(bms);
	};


	public Dropdown(float xx, float yy, float ww, String[] Labels,BMS bms) {

		Bms = bms;
		applet = bms.applet;
		theme = Bms.theme;
		dmenu = true;
		x = xx;
		y = yy;
		bx = x;
		by = y;

		w = ww;

		setLabels(Labels);
		if(Labels.length>5)setSlider();
		h = 20;

		windowx = windowSize * h;
		bwindowx = windowx;
		boundary = new Boundary(x,y,w,h,4,bms);
		setBms(bms);
	};

	public Dropdown(float xx, float yy, float ww, String l, String[] Labels,BMS bms) {

		Bms = bms;
		applet = bms.applet;
		theme = Bms.theme;
		dmenu = true;
		x = xx;
		y = yy;
		bx = x;
		by = y;

		w = ww;

		label = l;
		blabel = l;

		setLabels(Labels);
		h = 20;
		windowx = windowSize * 20;
		if(Labels.length>5)setSlider();
		bwindowx = windowx;
		boundary = new Boundary(x,y,w,h,4,bms);
		//	    setBms(bms);
	};
	
	public Dropdown(float xx, float yy, float ww,float hh,float ss, String l, String[] Labels,BMS bms) {

		Bms = bms;
		applet = bms.applet;
		theme = Bms.theme;
		dmenu = true;
		x = xx;
		y = yy;
		bx = x;
		by = y;

		w = ww;

		label = l;
		blabel = l;

		setLabels(Labels);
		h = hh;
		windowx = windowSize * (h+ss);
		if(Labels.length>5)setSlider();
		bwindowx = windowx;
		boundary = new Boundary(x,y,w,h,4,bms);
		//	    setBms(bms);
	};



	public Dropdown(float xx, float yy, float ww, String l, String[] Labels, int k,BMS bms) {

		Bms = bms;
		applet = bms.applet;
		theme = Bms.theme;
		dmenu = true;
		x = xx;
		y = yy;
		bx = x;
		by = y;

		w = ww;

		label = l;
		blabel = l;

		windowx = windowSize * 20;
		bwindowx = windowx;
		setLabels_(Labels);
		if(Labels.length>5)setSlider_();
		h = 20;
		boundary = new Boundary(x,y,w,h,4,bms);
		setBms(bms);
	};

	public Dropdown(float xx, float yy, float ww, String[] Labels, int k,BMS bms) {

		Bms = bms;
		applet = bms.applet;
		theme = Bms.theme;
		dmenu = true;
		x = xx;
		y = yy;
		w = ww;
		h = 20 * (k + 1);
		bx = x;
		by = y;

		windowx = windowSize * 20;
		bwindowx = windowx;
		setLabels(Labels);
		if(Labels.length>5)setSlider();
		boundary = new Boundary(x,y,w,h,4,bms);
		setBms(bms);
	};

	public void labelsToArray(String []a){
		for (int i=0; i<a.length; i++) {
			String s = a[i];

			labels.add(s);

		}
	};

	public void setLabels(String[] a) {

		if (label==null) label = a[0];
		float mwidth = w;
		title = new Button(x,y,w,20,label,Bms);
		title.parent = this;

		for (int i=0; i<a.length; i++) {

			Button b =null;
			if(applet.textWidth(a[i])>mwidth) mwidth = applet.textWidth(a[i]+20);
		}
		for (int i=0; i<a.length; i++) {

			Button b =null;
			if(applet.textWidth(a[i])>mwidth) mwidth = applet.textWidth(a[i]);
			if(a.length<5) b = new Button(x, (y + 20) + 20 * i, mwidth, 20,a[i],Bms);
			else b = new Button(x, (y + 20) + 20 * i, mwidth - 10, 20,a[i],Bms);

			b.border = false;
			//b.parent = this;
			b.classicBar = true;
			b.visible = true;
			items.add(b);
		}
		//	    setToggleBar();
		w = mwidth;

	};

	public void setLabels_(String[] a) {

		if (label==null) label = a[0];
		float mwidth = w;
		title = new Button(x,y,w,20,label,Bms);
		title.parent = this;

		for (int i=0; i<a.length; i++) {

			Button b =null;
			if(applet.textWidth(a[i])>mwidth) mwidth = applet.textWidth(a[i]+20);
		}
		for (int i=0; i<a.length; i++) {

			Button b =null;
			if(applet.textWidth(a[i])>mwidth) mwidth = applet.textWidth(a[i]);
			if(a.length<5) b = new Button(x, (y + 20) + 20 * i, mwidth, 20,a[i],Bms);
			else b = new Button(x, (y + 20) + 20 * i, mwidth - 10, 20,a[i],Bms);

			b.border = false;
			b.classicBar = true;
			//b.parent = this;
			b.visible = true;
			items.add(b);
		}

		hiddenw = mwidth;

	};

	public void setSlider() {
		slider = new Slider(x + w - 10, y + 20, 10, 20*5,Bms);
		slider.vertical = true;
		slider.bar = true;
		slider.classic = true;
		slider.visible = true;
		slider.valuex = 0;
		slider.vertical = true;
		slider.tvisible = false;
	};

	public void setSlider_() {
		slider = new Slider(x + hiddenw - 10, y + 20, 10, 20*5,Bms);

		slider.vertical = true;
		slider.bar = true;
		slider.classic = true;
		slider.visible = true;
		slider.valuex = 0;
		slider.vertical = true;
		slider.tvisible = false;
	};

	public void displayDropdown(){
		applet.fill(255);

		if(items.size()>0){
			mousePos = new PVector(applet.mouseX,applet.mouseY);
			drawLabel();
			selfClick();
			if(dclick)drawButtons();
			drawSlider();
			drawDragbox();
			drag();

			if (pos()||(dclick&&subPos()))menu = true;
			if(dMenu!=null){
				dMenu.x = x + w;
				dMenu.y = y;
			}
			if(subMenu!=null){
				subMenu.x = x + w;
				subMenu.y = y;
			}
			drawSubMenu();
		}
	};

	public void drawSubMenu(){
		if(subMenu!=null){
			if(subMenuPos())subMenu.show = true;
			if(pos()&&!subMenuPos())subMenu.show = false;
		}
		if(subMenu!=null&&subMenu.show)subMenu.trace();
		if(dMenu!=null&&subMenuPos())dMenu.displayDropdown();
	};

	public void drawSubMenu(PVector mouse){
		if(subMenu!=null&&subMenuPos(mouse))subMenu.show = true;
		//else if(subMenu!=null)subMenu.show = false;
		if(subMenu!=null)subMenu.trace();
		if(dMenu!=null&&subMenuPos(mouse))dMenu.displayDropdown();
	};

	public void displayDropdown(PGraphics canvas){
		applet.fill(255);

		if(items.size()>0){
			drawLabel(canvas);
			selfClick(mouse);
			//if(dclick)
				drawButtons(canvas);
			drawSlider(canvas);
			drawDragbox(canvas);
			drag(mouse);
			drawSubMenu(mouse);
			
			if (pos(mouse)||(dclick&&subPos(mouse))||(dMenu!=null&&dMenu.menu)||(subMenu!=null&&subMenu.menu))menu = true;
			else menu = false;
			if(dMenu!=null){
				dMenu.x = x + w;
				dMenu.y = y;
			}
			if(subMenu!=null){
				subMenu.x = x + w;
				subMenu.y = y;
			}
		}
	};
	
	void setThemeRadius() {
		r1 = theme.r1;
		r2 = theme.r2;
		r3 = theme.r3;
		r4 = theme.r4;
		
	};

	public void drawButtons(){

		dcount = 0;
		mousePos = new PVector(applet.mouseX,applet.mouseY);
		if(dclick&&slider!=null){

			applet.fill(255);
			applet.rect(x,y+h,w,slider.h,r1,r2,r3,r4);
			applet.fill(theme.dockfillcol);
			applet.rect(x,y+h,w,slider.h,r1,r2,r3,r4);
		}

		for(int i=0;i<items.size();i++){
			Button b = items.get(i);

			if(drag){
				b.x = x;
				b.y = y + 20 + 20 * i;
				b.by= y + 20 + 20 * i; 
			}

			if(slider!=null&&slider.mdown)b.y = b.by - slider.value;

			if(slider!=null){

				if(!slider.mdown){
					if(!dclick)b.visible = false;
					else if(b.y + b.h < y + h + windowx+1) b.visible = true;
					if(b.y + b.h > y + h + windowx+1||b.y<y+h-1)b.visible = false;
				}
				else {
					if(b.y + b.h > y + h + windowx+1||b.y<y+h-1)b.visible = false;
					else b.visible = true;
				}}
			else{
				if(!dclick)b.visible = false;
				else b.visible = true;

				b.x = x;
				b.y = y + 20 + 20 * i;
			}
			if(b.y<y+h-1)b.visible = false;
			if(b.visible){

				b.draw();
				b.highlight();
				if(b.pos(mousePos))dcount++;
			}

			if(slider!=null){

				if(applet.mousePressed&&b.pos(mousePos)&&b.visible&&!slider.mdown){
					label = b.label;
					index = i;
					dclick = false;
				}
			}
			else{
				if(applet.mousePressed&&b.pos(mousePos)&&b.visible){
					label = b.label;
					index = i;
					dclick = false;
				}}}
		if(slider!=null){if(dcount==0&&applet.mousePressed&&!pos(mousePos)&&!slider.pos(mousePos)&&!slider.mdown)dclick = false;}
		else if(dcount==0&&applet.mousePressed&&!pos(mousePos))dclick = false;
		//if(mdown2)dclick
	};
	
	public void drawItems(PGraphics canvas) {
		
		dcount = 0;
		if(dclick&&slider!=null){
			canvas.fill(255);
			canvas.rect(x,y+h,w,slider.h,r1,r2,r3,r4);
			canvas.fill(theme.dropdownfillcol);
			canvas.rect(x,y+h,w,slider.h,r1,r2,r3,r4);
		}

		for(int i=0;i<items.size();i++){

			Button b = items.get(i);

			if(drag){
				b.x = x;
				b.y = y + 20 + 20 * i;
				b.by= y + 20 + 20 * i; 
			}

			if(slider!=null&&slider.mdown)b.y = b.by - (slider.value);

			if(slider!=null){
				if(!slider.mdown){
					if(!dclick)b.visible = false;
					else if(b.y + b.h < y + h + windowx+1) b.visible = true;

				}else {
					if(b.y + b.h > y + h + windowx+1||b.y<y+h-1)b.visible = false;
					else b.visible = true;

				}}else{
					if(!dclick)b.visible = false;
					else b.visible = true;
				}
			if(b.y<y+h-1)b.visible = false;
			if(b.visible){
				b.mouse = mouse;
				//b.parentCanvas = true;
				b.draw(canvas);
				b.highlight(mouse);
				if(b.pos(mouse))dcount++;
			}

			if(slider!=null){

				if(applet.mousePressed&&b.pos(mouse)&&b.visible&&!slider.mdown){
					label = b.label;
					index = i;
					dclick = false;
				}} else{
					if(applet.mousePressed&&b.pos(mouse)&&b.visible){
						label = b.label;
						index = i;
						dclick = false;
					}}}
		if(slider!=null){if(dcount==0&&applet.mousePressed&&!pos(mouse)&&!slider.pos(mouse)&&!slider.mdown)dclick = false;}
		else if(dcount==0&&applet.mousePressed&&!pos(mouse))dclick = false;
	}

	public void drawButtons(PGraphics canvas){
		setThemeRadius();
		dcount = 0;
		if(dclick&&slider!=null){

			canvas.fill(theme.dropdownfillcol,theme.dropdowntransparency);
			canvas.rect(x,y+h,w,slider.h,r1,r2,r3,r4);
		}

		for(int i=0;i<items.size();i++){

			Button b = items.get(i);

			if(drag){
				b.x = x;
				b.y = y + 20 + 20 * i;
				b.by= y + 20 + 20 * i; 
			}else {
				
				b.x = x;
				b.y = y + 20 + 20 * i;
				b.by= y + 20 + 20 * i; 
			}

			if(slider!=null)b.y = b.by - (slider.value);

			if(slider!=null){
				if(!slider.mdown){
					if(!dclick)b.visible = false;
					else if(b.y + b.h < y + h + windowx+1)
						b.visible = true;

				}else {
					if(b.y + b.h > y + h + windowx+1&&b.y<y+h-1)b.visible = false;
					else
						b.visible = true;

				}}else{
					if(!dclick)b.visible = false;
					else b.visible = true;
				}
			if(b.y<y+h-1)b.visible = false;
			if(b.y+b.h>y+h+windowx-1)b.visible = false;
			if(b.visible){
				b.mouse = mouse;
				b.parentCanvas = true;
				b.draw(canvas);
				b.highlight(mouse);
				if(b.pos(mouse))dcount++;
			}
			if(slider!=null){
				if(applet.mousePressed&&b.pos(mouse)&&b.visible&&!slider.mdown){
					label = b.label;
					index = i;
					dclick = false;
				}} else{
					if(applet.mousePressed&&b.pos(mouse)&&b.visible){
						label = b.label;
						index = i;
						dclick = false;
					}}}
		if(slider!=null){if(dcount==0&&applet.mousePressed&&!pos(mouse)&&!slider.pos(mouse)&&!slider.mdown)dclick = false;}
		else if(dcount==0&&applet.mousePressed&&!pos(mouse))dclick = false;
	};

	public void drawBoundary(){

		if(draggable){
			if(border)boundary.draw2();
			drag();
		}

	};

	public void drawDragbox(){

		if(draggable){
			applet.fill(0,50);
			applet.rect(x,y - 10,w,10);
		}

	};

	public void drawDragbox(PGraphics canvas){

		if(draggable){
			canvas.fill(0,50);
			canvas.rect(x,y - 10,w,10);
		}

	};


	public void drawSlider(){
		if(slider!=null){
			slider.x = x + w - 10;
			slider.y = y + 20;
		}
		if(slider!=null&&dclick){
			slider.draw();
			slider.mouseFunctions();
			slider.set(0,(items.size()));
		}
	};

	public void drawSlider(PGraphics canvas){
		if(slider!=null){
			slider.x = x + w - 10;
			slider.y = y + 20;
		}
		if(slider!=null&&dclick){
			slider.mouse = mouse;
			slider.draw(canvas);
			slider.mouseFunctions(mouse);
			//slider.set(0,500);
			slider.set(0,(items.size()));
		}
	};

	public void drawLabel(){
		
		applet.stroke(theme.dropdownstrokecol,theme.dropdowntransparency);
		applet.strokeWeight(theme.dropdownstrokesize);
		if(!theme.dropdownborder)applet.noStroke();
		applet.fill(theme.dropdownfillcol,theme.dropdowntransparency);
		if(pos())applet.fill(theme.dropdownhcol,theme.dropdowntransparency);
		if(!theme.dropdownfill)applet.noFill();
		applet.rect(x,y,w,h,r1,r2,r4,r4);
		applet.fill(theme.dropdowntextcol);
		applet.pushStyle();
		applet.textFont(theme.tabFont);
		applet.textSize(theme.dropdowntextsize);
		applet.text(label,x+2+txoff,y+tsize+2+tyoff);
		applet.popStyle();

	};

	public void drawLabel(PGraphics canvas){

		canvas.stroke(theme.dropdownstrokecol,theme.dropdowntransparency);
		canvas.strokeWeight(theme.dropdownstrokesize);
		if(!theme.dropdownborder)canvas.noStroke();
		canvas.fill(theme.dropdownfillcol,theme.dropdowntransparency);
		if(pos(mouse))canvas.fill(theme.dropdownhcol,theme.dropdowntransparency);
		if(!theme.dropdownfill)canvas.noFill();
		canvas.rect(x,y,w,h,r1,r2,r4,r4);
		canvas.fill(theme.dropdowntextcol);
		canvas.pushStyle();
		canvas.textFont(theme.tabFont);
		canvas.textSize(theme.dropdowntextsize);
		canvas.text(label,x+2+txoff,y+tsize+2+tyoff);
		canvas.popStyle();
	};

	public void drag(){
		if(slider!=null){
			if(dpos()&&applet.mousePressed&&!drag&&!slider.mdown){
				if(draggable){
					drag = true;
					dx = x - applet.mouseX;
					dy = y - applet.mouseY;
					mdown = true;
				}}}
		else{
			if(dpos()&&applet.mousePressed&&!drag){
				if(draggable){
					drag = true;
					dx = x - applet.mouseX;
					dy = y - applet.mouseY;
					mdown = true;
				}}}

		if(drag){
			x = applet.mouseX + dx;
			y = applet.mouseY + dy;
		}

		if(!applet.mousePressed){
			dx = 0;
			dy = 0;
			drag = false;
		}
	};

	public void drag(PVector m){
		if(slider!=null){
			if(dpos(mouse)&&applet.mousePressed&&!drag&&!slider.mdown){
				if(draggable){
					drag = true;
					dx = x - m.x;
					dy = y - m.y;
					mdown = true;
				}}}else{
					if(dpos(mouse)&&applet.mousePressed&&!drag){
						if(draggable){
							drag = true;
							dx = x - m.x;
							dy = y - m.y;
							mdown = true;
						}}}

		if(drag){
			x = m.x + dx;
			y = m.y + dy;
		}

		if(!applet.mousePressed){
			dx = 0;
			dy = 0;
			drag = false;
		}
	};

	public void selfClick(){
		mousePos = new PVector(applet.mouseX,applet.mouseY);
		if(slider!=null){
			if(Bms.dropDownObject==null&&pos(mousePos)&&applet.mousePressed&&!mdown&&!dclick&&!slider.mdown&&!drag){

				Bms.dropDownObject = this;
				dclick = true;
				mdown = true;
			}
			if(pos(mousePos)&&applet.mousePressed&&!mdown&&dclick&&!slider.mdown&&!drag){
				dclick = false;
				mdown = true;
			}}
		else{

			if(Bms.dropDownObject==null&&pos(mousePos)&&applet.mousePressed&&!mdown&&!dclick&&!drag){

				dclick = true;
				mdown = true;
			}
			if(pos(mousePos)&&applet.mousePressed&&!mdown&&dclick&&!drag){
				dclick = false;
				mdown = true;
			}}
		if(!applet.mousePressed)mdown = false;
		if(pos(mousePos))col = theme.hcol;
		else col = theme.fcol;
		if(dclick)col = theme.toggleCol;
		if(dclick&&pos(mousePos))col = applet.color(theme.toggleCol,100);
	};

	public void selfClick(PVector mouse){
		if(slider!=null){
			if(Bms.dropDownObject==null&&pos(mouse)&&applet.mousePressed&&!mdown&&!dclick&&!slider.mdown&&!drag){
				Bms.dropDownObject = this;
//				PApplet.println("dropdown click",Bms.dropDownObject);
				dclick = true;
				mdown = true;
			}
			if(pos(mouse)&&applet.mousePressed&&!mdown&&dclick&&!slider.mdown&&!drag){
				dclick = false;
				mdown = true;
			}}
		else{
			if(Bms.dropDownObject==null&&pos(mouse)&&applet.mousePressed&&!mdown&&!dclick&&!drag){
				Bms.dropDownObject = this;
//				PApplet.println("dropdown click null",Bms.dropDownObject);
				dclick = true;
				mdown = true;
			}
			if(pos(mouse)&&applet.mousePressed&&!mdown&&dclick&&!drag){
				dclick = false;
				mdown = true;
			}}
		if(!applet.mousePressed)mdown = false;
		if(pos(mouse))col = theme.hcol;
		else col = theme.fcol;
		if(dclick)col = theme.toggleCol;
		if(dclick&&pos(mouse))col = applet.color(theme.toggleCol,100);
		//else col = Bms.fcol;

	};

	public void toggled(int i,Object o,String c){
		Button b = items.get(i);

		if(b.visible)b.toggle(o,c);

	};

	public void sptoggled(Object o,String c){

		for(int i=0;i<items.size();i++){
			Button b = items.get(i);
			if(b.visible)b.toggle(o,c);
			if(!b.pos()&&applet.mousePressed)b.toggle=false;
		}

	};

	boolean subPos(){
		return applet.mouseX>x&&applet.mouseX<x+w&&applet.mouseY>y+20&&applet.mouseY<y+20+items.size()*20;
	};

	boolean subPos(PVector m){
		return m.x>x&&m.x<x+w&&m.y>y+20&&m.y<y+20+items.size()*20;
	};

	boolean dpos(){
		return applet.mouseX>x&&applet.mouseX<x+w&&applet.mouseY>y-10&&applet.mouseY<y;
	};

	boolean dpos(PVector m){
		return m.x>x&&m.x<x+w&&m.y>y+20&&m.y<y;
	};

	boolean subMenuPos(){
		return applet.mouseX>x+w-20&&applet.mouseX<x+w&&applet.mouseY>y&&applet.mouseY<y+h;
	};

	boolean subMenuPos(PVector m){
		return m.x>x+w-20&&m.x<x+w&&m.y>y&&m.y<y+h;
	};

	public void setBms(BMS bms) {
		Bms = bms;
		applet = bms.applet;
		for(int i=0;i<items.size();i++) {
			Button b = items.get(i);
			b.Bms = Bms;
			b.applet = applet;
		}

	};
	
	public void setTextColor(int c) {
		initColors();
		if(newTheme==null) newTheme = new Theme(Bms);
		newTheme.dropdowntextcol = c;
		theme = newTheme;
	};
	
	public void setTextColor(float a, float b,float c,float d) {
		initColors();
		if(newTheme==null) newTheme = new Theme(Bms);
		newTheme.dropdowntextcol = applet.color(a,b,c,d);
		newTheme.buttontextcol = applet.color(a,b,c,d);
		theme = newTheme;
		for(int i=0;i<items.size();i++) {
			Button b1 = items.get(i);
			b1.setTextCol(a);
		}
	};
	
	public void setTextCol(int c) {
		initColors();
		tcol = c;
		localTheme = true;
	};
	
	public void setTextCol(float a, float b,float c,float d) {
		initColors();
		tcol = applet.color(a,b,c,d);
		localTheme = true;
	};
	
	public void save() {
		
	};
	
	public void defaultSave() {
		
	};

	
};
