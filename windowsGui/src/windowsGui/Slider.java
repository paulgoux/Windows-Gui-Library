package windowsGui;

import java.awt.Color;
import java.lang.reflect.Field;
import java.util.HashMap;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PGraphics;
import processing.core.PVector;

public class Slider{
	public BMS Bms;
	Color c;
	public PApplet applet;
	public int id = -1,type,functionId,themeIndex;
	public float x,y,w,h,bx,by,bw,bh,valuex,valuey,btnw,btnh, value = 0,txoff,tyoff,spacing = 20,tsize = 12,ssize,
			temp,startvalue,endvalue,start,end,r1,r2,r3,r4,radius,pieVal,transparency,tempValue;
	public String label,parentVar,parentBool,itemLabel;
	public boolean drag,resize,border,fill = true ,toggle,visible = true,horizontal = true,vertical,matrix,
			classic,pie,radio,square,bar,mdown,mup,Label,right,up,down,left,tvisible = true,update = true,
			tdown,parentCanvas,mdown1,mdown2,topen,setLimits,INT,mouseOut,
			mouseIn,mfdown;
	public boolean localTheme;
	public int col, col2, col3;
	public int barcol,col4,tcol,slidercol,hovercol,toggleCol ,sliderbgcol;
	public String control = "";
	public Object Link,parentObject;
	public Menu parent;
	public tab tooltip,parentTab;
	public PVector mouse;
	public TextArea Start = null,End = null,Current = null;
	public HashMap<String,Boolean> values = new HashMap<String,Boolean>();
	public Theme theme;
	public Slider(float xx,float yy, float ww, float hh){

		x = xx;
		y = yy;
		w = ww;
		h = hh;
		bx = x;
		by = y;
		bw = w;
		bh = h;
		btnh = h+2;
		btnw = h;
		valuex = w/2;
		valuey = y;
		classic = true;
		bar = true;

		//createOptionsMenu();

	};

	public Slider(float xx,float yy, float ww, float hh,BMS bms) {
		Bms = bms;
		applet = bms.applet;
		theme = bms.theme;
		x = xx;
		y = yy;
		w = ww;
		h = hh;
		bx = x;
		by = y;
		bw = w;
		bh = h;
		btnh = h+2;
		btnw = h;
		valuex = w/2;
		valuey = y;
		//value = 0;
		//valuex = 0;
		classic = true;
		bar = true;
		initColors();
		//createOptionsMenu();

	};

	public Slider(float xx,float yy, float ww, float hh,BMS bms,boolean b1) {
		Bms = bms;
		applet = bms.applet;
		theme = bms.theme;
		x = xx;
		y = yy;
		w = ww;
		h = hh;
		bx = x;
		by = y;
		bw = w;
		bh = h;
		btnh = h+2;
		btnw = h;
		valuex = w/2;
		valuey = y;
		//value = 0;
		//valuex = 0;
		classic = true;
		bar = true;

		createOptionsMenu();

	};

	public Slider(float xx,float yy, float ww, float hh,String Label){

		x = xx;
		y = yy;
		w = ww;
		h = hh;
		bx = x;
		by = y;
		bw = w;
		bh = h;
		btnh = h+2;
		btnw = h;
		label = Label;
		valuex = w/2;
		valuey = y;
		value = 0;
		classic = true;
		bar = true;
		//createOptionsMenu();

	};

	public Slider(float xx,float yy, float ww, float hh,String Label,BMS bms) {
		Bms = bms;
		applet = bms.applet;
		theme = bms.theme;
		x = xx;
		y = yy;
		w = ww;
		h = hh;
		bx = x;
		by = y;
		bw = w;
		bh = h;
		btnh = h+2;
		btnw = h;
		label = Label;
		valuex = w/2;
		valuey = y;
		value = 0;
		classic = true;
		bar = true;
		createOptionsMenu();
		initColors();
	};

	public void createOptionsMenu(){
		applet.textSize(theme.slidertextsize);
		float X = x+w+applet.textWidth("0.000");
		tooltip = new tab(X,y,90,120,Bms);
		tooltip.parentSlider = this;
		String[] s1 = {"Reset"};

		Menu options = new Menu(0,0,80,s1,0,Bms);
		tooltip.w = options.w;
		tooltip.add(options);
		TextArea t1 = new TextArea(0,0+30,68,20,"Start",Bms);
		tooltip.add(t1);
		t1.w = options.w;
		t1 = new TextArea(0,0+60,68,20,"End",Bms);
		t1.w = options.w;
		tooltip.add(t1);
		t1 = new TextArea(0,0+90,68,20,"Value",Bms);
		t1.w = options.w;
		tooltip.add(t1);
		Start = tooltip.textareas.get(0);
		End = tooltip.textareas.get(1);
		tooltip.h = 90+20;

	};

	public void updateOptionsMenu(){
		//if(!tooltip.drag&&!dragged){
		//	    tooltip.x = x+w+applet.textWidth("0.000");
		Menu options = tooltip.menus.get(0);

		options.x = 0;
		options.y = 0;
		for(int i=0;i<tooltip.menus.get(0).items.size();i++){
			Button b = tooltip.menus.get(0).items.get(i);
			b.x = 0;
			b.y = 0 + 20 * i;
		}
		TextArea t1 = tooltip.textareas.get(0);
		t1.y = 0+30;
		t1.x = 0;
		t1 = tooltip.textareas.get(1);
		t1.y = 0+60;
		t1.x = 0;
		t1 = tooltip.textareas.get(2);
		t1.y = 0+90;
		t1.x = 0;
		Start = tooltip.textareas.get(0);
		End = tooltip.textareas.get(1);

		if(tooltip.y+tooltip.h>applet.height)tooltip.draggable = true;
		else tooltip.draggable = false;

	};

	public void updateOptionsMenu(PGraphics applet){
		//if(!tooltip.drag&&!dragged){
		//	    tooltip.x = x+w+applet.textWidth("0.000");
		//	    tooltip.y = y;
		Menu options = tooltip.menus.get(0);

		options.x = 0;
		options.y = 0;
		for(int i=0;i<tooltip.menus.get(0).items.size();i++){
			Button b = tooltip.menus.get(0).items.get(i);
			b.x = 0;
			b.y = 0 + 20 * i;
		}
		TextArea t1 = tooltip.textareas.get(0);
		t1.y = 0+30;
		t1.x = 0;
		t1 = tooltip.textareas.get(1);
		t1.y = 0+60;
		t1.x = 0;
		t1 = tooltip.textareas.get(2);
		t1.y = 0+90;
		t1.x = 0;
		Start = tooltip.textareas.get(0);
		End = tooltip.textareas.get(1);

		if(tooltip.y+tooltip.h>applet.height)tooltip.draggable = true;
		else tooltip.draggable = false;

	};

	public void initColors() {

		barcol = applet.color(0, 255, 73);
		col4 = applet.color(0,50);
		tcol = applet.color(255-theme.slidertransparency);
		slidercol = applet.color(255-theme.slidertransparency);
		hovercol = theme.sliderhcol;
		toggleCol = applet.color(50,0);
		sliderbgcol = applet.color(255-theme.slidertransparency);
	};
	
	public void save(fileOutput output) {
		output.writeLine(control);
		if(theme!=Bms.theme) {
			
		}else {
			
		}
	};

	public void logic() {
		if(mouse==null)mouse = new PVector(applet.mouseX,applet.mouseY);
		//		if (parent==null&&((pos(mouse)||btnpos(mouse))&&!mdown))mdown = true;
		if(tooltip!=null){
			applet.textSize(theme.slidertextsize);
			applet.strokeWeight(theme.sliderstrokesize);
			applet.stroke(theme.sliderstrokecol,theme.slidertransparency);
			if(!theme.sliderborder)applet.noStroke();

			applet.fill(theme.sliderfillcol,theme.slidertransparency);
			if(!theme.sliderfill)applet.noFill();
			if(pie) {
				if(tooltipPos()){
					applet.rect(x+w/2,y-h/2,20,h,theme.r1,theme.r2,theme.r3,theme.r4);
				}
			}else {
				if(tooltipPos()){

					String k = "0.0000";
					applet.rect(x+w+applet.textWidth(k),y,20,h,theme.r1,theme.r2,theme.r3,theme.r4);
				}
			}

			if(tooltipPos()&&theme.mouseReleased&&Bms.sliderObject==null){
				Bms.sliderObject = this;
				tooltip.toggle = true;
				tooltip.visible = true;
				mdown1 = true;
			}
			if(!tooltip.draggable){
				if((!tooltip.posTab()&&!mdown&&!mdown1&&applet.mousePressed)){
					if(Bms.sliderObject==this) Bms.sliderObject = null;
					tooltip.toggle = false;
					tooltip.visible = false;
					mdown1 = true;
					tdown = false;
					//cursor(ARROW);
				}}else 
					if((!tooltip.posTab()&&!mdown&&!mdown1&&applet.mousePressed)){
						if(Bms.sliderObject==this) Bms.sliderObject = null;
						tooltip.toggle = false;
						tooltip.visible = false;
						mdown1 = true;
						tdown = false;

						//cursor(ARROW);
					}
			if(tdown){
				applet.textSize(theme.slidertextsize);
				if(!vertical) {
					if(pie)tooltip.setPos(x+w/2,y+h/2);
					else  tooltip.setPos(x+w+applet.textWidth("0000"),y);
				}else {
					if(pie)tooltip.setPos(x+w/2,y+h/2);
					else tooltip.setPos(x,y+h+applet.textWidth("0000")+20);
				}
				tooltip.displayTab(); 
				if(!mdown1&&tooltip.toggle(0,0)){
					resetControl();
					valuex = w/2;
					if(pie)pieVal = 0;
					value = 0;
					mdown = true;
					tdown = false;
				}
				//start value ------------------------------
				TextArea t = tooltip.textareas.get(0);
//				t.mouse = tooltip.getParentMouse();
				if(t.textArea!=null)
//					applet.println("slider logic enter 00",t.tempLine);
				if(t.tempLine!=""&&Float.parseFloat(t.tempLine)>-99999999
						&&t.toggle&&applet.keyPressed&&applet.key==PConstants.ENTER){
//					applet.println("slider logic enter 01");
					if(parent!=null){
						try {
							startvalue = (float) Double.parseDouble(t.tempLine);
							if(pie)pieVal = valuex;
						}catch(NumberFormatException nfe) {
							applet.println("textbox no number");
						}
						//	            valuex = t.getValue();

						switch(functionId){

						case(0):set(startvalue,endvalue,parentObject, parentVar);
						break;
						case(1):set(startvalue,endvalue,parentObject, parentVar,parentBool);
						break;
						case(2):setint((int)startvalue,(int)endvalue,parentObject, parentVar);
						break;
						case(3):setint((int)startvalue,(int)endvalue);
						break;
						}
					}
//					setControl(value);
					t.reset();
				}
				
				TextArea t1 = tooltip.textareas.get(1);
				if(t1.tempLine!=""&&Float.parseFloat(t.tempLine)>-99999999
						&&t1.toggle&&applet.keyPressed&&applet.key==PConstants.ENTER){
//					applet.println("slider logic enter 02");
					if(parent!=null){
						try {
							value = (float) Double.parseDouble(t1.tempLine);
							if(pie)pieVal = valuex;
						}catch(NumberFormatException nfe) {
							applet.println("textbox no number");
						}

						switch(functionId){

						case(0):set(startvalue,endvalue,parentObject, parentVar);
						break;
						case(1):set(startvalue,endvalue,parentObject, parentVar,parentBool);
						break;
						case(2):setint((int)startvalue,(int)endvalue,parentObject, parentVar);
						break;
						case(3):setint((int)startvalue,(int)endvalue);
						break;
						}
					}
					setControl(value);
					t1.reset();
				}
				
				TextArea t2 = tooltip.textareas.get(2);
				if(t2.tempLine!=""&&Float.parseFloat(t2.tempLine)>-99999999
						&&t2.toggle&&applet.keyPressed&&applet.key==PConstants.ENTER){
//					applet.println("slider logic enter 03");
					if(parent!=null){
						try {
							endvalue = (float) Double.parseDouble(t2.tempLine);
//							if(pie)pieVal = valuex;
						}catch(NumberFormatException nfe) {
							applet.println("textbox no number");
						}

						switch(functionId){

						case(0):set(startvalue,endvalue,parentObject, parentVar);
						break;
						case(1):set(startvalue,endvalue,parentObject, parentVar,parentBool);
						break;
						case(2):setint((int)startvalue,(int)endvalue,parentObject, parentVar);
						break;
						case(3):setint((int)startvalue,(int)endvalue);
						break;
						}
					}
//					setControl(value);
					t2.reset();
				}
			}

			//					if(!applet.mousePressed){
			//						mdown1 = false;
			//
			//						for(int i=0;i<tooltip.menus.get(0).items.size();i++){
			//							Button b = tooltip.menus.get(0).items.get(i);
			//
			//							b.toggle = false;
			//						}}

		}
		if(!applet.mousePressed&&mdown1) {
			tdown = true;

		}
		if(!applet.mousePressed){
			mdown1 = false;
			mdown = false;
			if(!tdown&&Bms.sliderObject==this) Bms.sliderObject = null;
		}
	};

	public void draw(){
		applet.pushStyle();
		applet.textFont(theme.sliderfont);
		functions();
		logic();
		applet.popStyle();
	};

	public void logic(PGraphics canvas) {
		if(mouse==null)mouse = new PVector(applet.mouseX,applet.mouseY);
		boolean k = Bms.getObject();
		boolean k1 = Bms.getObject(this);
		boolean k2 = mouse.x>x+w+applet.textWidth("0000")
		&&mouse.x<x+w+applet.textWidth("0000")+40;
		if(pie)k2 = PApplet.dist(mouse.x,mouse.y,x,y)<radius;
		boolean k3 = mouse.y>y&&mouse.y<y+70;
//		if(theme.mouseReleased)applet.println(theme,"slider mrelease");
//		if(tooltipPos(mouse)&&Bms.theme.mouseReleased)
//			applet.println("slider tabpos");
		if(k&&Bms.theme.mouseReleased&&tooltip!=null&&tooltipPos(mouse)) {
			Bms.setObject(this);
			tdown = true;
			mdown1 = true;
			tooltip.visible = true;
			tooltip.toggle = true;
		}
		
		if(tdown&&Bms.theme.click&&!tooltipTabPos()&&!mdown) {
			tdown = false;
			Bms.clearObject(this);
			tooltip.visible = true;
			tooltip.toggle = true;
			applet.println("clear");
		}

		if(tooltip!=null){
			applet.textSize(theme.slidertextsize);
			if(!vertical) {
				if(pie)tooltip.setPos(x+w/2,y+h/2);
				else  tooltip.setPos(x+w+applet.textWidth("0000"),y);
			}else {
				if(pie)tooltip.setPos(x+w/2,y+h/2);
				else tooltip.setPos(x,y+h+applet.textWidth("0000")+20);
			}
			canvas.strokeWeight(theme.sliderstrokesize);
			canvas.stroke(theme.sliderstrokecol,theme.slidertransparency);
			if(!theme.sliderborder)canvas.noStroke();

			canvas.fill(theme.sliderfillcol,theme.slidertransparency);
			if(!theme.sliderfill)canvas.noFill();

			if(pie) {
				if(tooltipPos(mouse)&&!tdown){
					if(!vertical) canvas.rect(x+w/2,y-h/2,20,h,theme.r1,theme.r2,theme.r3,theme.r4);
					else canvas.rect(x+w/2,y-h/2,20,h,theme.r1,theme.r2,theme.r3,theme.r4);
				}
			}else {
				if(tooltipPos(mouse)&&!mdown1){

					String s1 = "0.0000";
					if(!vertical) canvas.rect(x+w+applet.textWidth(s1),y,20,h,theme.r1,theme.r2,theme.r3,theme.r4);
					else canvas.rect(x,y+h+applet.textWidth(s1),w,20,theme.r1,theme.r2,theme.r3,theme.r4);
				}
			}
//			
//			boolean k = tooltipPos(mouse)&&theme.mouseReleased;
//			
//			if(k&&Bms.getObject()) {
//				Bms.setObject(this);
//				tooltip.visible = true;
//				tooltip.toggle = true;
//				tdown = true;
//				Bms.currentObject = this;
//				Bms.sliderObject = this;
//				applet.println("slider logic tdown");
//			}

			if(tdown&&!mdown1){
				applet.textSize(theme.slidertextsize);
				//				if(pie)tooltip.setPos(x+w/2,y+h/2);
				//				else tooltip.setPos(x+w+applet.textWidth("0000"),y);
				tooltip.parentTab = parentTab;
				tooltip.displayTab(canvas); 
				if(tooltip.toggle(0,0)&&!mdown1){
					//						tooltip.menus.get(0).items.get(0).toggle = true;
					resetControl();
					valuex = w/2;
					if(pie)pieVal = 0;
					value = 0;
					mdown1 = true;
					//cursor(ARROW);
					tdown = false;
				}
				TextArea t = tooltip.textareas.get(0);
//				t.mouse = tooltip.getParentMouse();
//				if(t.textArea!=null)
//					applet.println("slider logic enter 00",t.tempLine);
				if(t.tempLine!=""
						&&t.toggle&&applet.keyPressed&&applet.key==PConstants.ENTER){
					if(parent!=null){
						try {
							startvalue = (float) Double.parseDouble(t.tempLine);
//							if(pie)pieVal = valuex;
						}catch(NumberFormatException nfe) {
							applet.println("textbox no number");
						}
						//	            valuex = t.getValue();

						switch(functionId){

						case(0):set(startvalue,endvalue,parentObject, parentVar);
						break;
						case(1):set(startvalue,endvalue,parentObject, parentVar,parentBool);
						break;
						case(2):setint((int)startvalue,(int)endvalue,parentObject, parentVar);
						break;
						case(3):setint((int)startvalue,(int)endvalue);
						break;
						}
					}
//					setControl(value);
					tooltip.visible = false;
					tooltip.toggle = false;
//					tdown = false;
//					t.reset();
				}
				
				TextArea t1 = tooltip.textareas.get(1);
				if(t1.tempLine!=""
						&&t1.toggle&&applet.keyPressed&&applet.key==PConstants.ENTER){
					if(parent!=null){
						try {
							endvalue = (float) Double.parseDouble(t1.tempLine);
//							if(pie)pieVal = valuex;
						}catch(NumberFormatException nfe) {
							applet.println("textbox no number");
						}

						switch(functionId){

						case(0):set(startvalue,endvalue,parentObject, parentVar);
						break;
						case(1):set(startvalue,endvalue,parentObject, parentVar,parentBool);
						break;
						case(2):setint((int)startvalue,(int)endvalue,parentObject, parentVar);
						break;
						case(3):setint((int)startvalue,(int)endvalue);
						break;
						}
					}
					
//					t1.reset();
					tooltip.visible = false;
					tooltip.toggle = false;
//					tdown = false;
				}
				
				TextArea t2 = tooltip.textareas.get(2);
				if(t2.tempLine!=""
						&&t2.toggle&&applet.keyPressed&&applet.key==PConstants.ENTER){
					if(parent!=null){
						try {
							value = (float) Double.parseDouble(t2.tempLine);
							if(pie)pieVal = valuex;
						}catch(NumberFormatException nfe) {
							applet.println("textbox no number");
						}

						switch(functionId){

						case(0):set(startvalue,endvalue,parentObject, parentVar);
						break;
						case(1):set(startvalue,endvalue,parentObject, parentVar,parentBool);
						break;
						case(2):setint((int)startvalue,(int)endvalue,parentObject, parentVar);
						break;
						case(3):setint((int)startvalue,(int)endvalue);
						break;
						}
					}
//					setControl(value);
					if(Link!=null)setControl(value);
					else if(pie)setControl();
					tooltip.visible = false;
					tooltip.toggle = false;
//					tdown = false;
//					t2.reset();
				}
			}
			
			

			//				if(!applet.mousePressed){
			//					mdown1 = false;
			//
			//					for(int i=0;i<tooltip.menus.get(0).items.size();i++){
			//						Button b = tooltip.menus.get(0).items.get(i);
			//
			//						b.toggle = false;
			//					}}

		}
		if(!applet.mousePressed&&mdown1) {
			tdown = true;
			//			

		}
		if(!applet.mousePressed){
			mdown1 = false;
			if(!tdown)if(Bms.getObject(this)) {
//				applet.println("slider tooltip s close 2",label);
//				Bms.clearObject(this);
			}
		}
//		if(k1)
//		if(tooltip!=null&&mouse.x>tooltip.x&&x<tooltip.x+tooltip.w
//				&&mouse.y>tooltip.y&&mouse.y<tooltip.y+tooltip.h) {
//			canvas.rect(tooltip.x,tooltip.y,tooltip.w,tooltip.h);
//		}
//		if(k1)
//			if(tooltip!=null&&tooltipTabPos()) {
//				canvas.rect(tooltip.x,tooltip.y,tooltip.w,tooltip.h);
//			}
	};



	public void draw(PGraphics canvas){
		canvas.pushStyle();
		canvas.textFont(theme.sliderfont);
		functions(canvas);
		logic(canvas);
		mouseFunctions(mouse);
		canvas.popStyle();
	};

	public void set(String a){
		if(a=="vertical"||a=="Vertical"||a=="VERTICAL"){}
	};


	public void resetControl(){
		Field field = null;

		try{
			field = Link.getClass().getField("sUpdate"); 
			//field.set(Link, "sUpdate");
			field.set(Link, true); 
		}catch (NullPointerException e) {
			PApplet.println("null slider");
		}catch (NoSuchFieldException e) {
			PApplet.println("no field: update");
		}catch (IllegalAccessException e) {
		} 

		try{
			field = Link.getClass().getField(control); 
			field.set(Link, 0); 
			//applet.println(valuex);
		}catch (NullPointerException e) {
			PApplet.println("slider resetc null p slider");
		}catch (NoSuchFieldException e) {
			PApplet.println("no field");
		}catch (IllegalAccessException e) {
		} 
	};

	public void setControl(float a){
		value = a;
		valuex = PApplet.map( value,startvalue,endvalue, 0+1, w-1);
		if(pie)pieVal = PApplet.map( value,startvalue,endvalue, 0, 2*applet.PI);
		Field field = null;
		try{
			field = Link.getClass().getField("sUpdate"); 
			//field.set(Link, "sUpdate");
			field.set(Link, true); 
			//applet.println(field.get(Link),"update",control,Link);
		}catch (NullPointerException e) {
			PApplet.println("s control; null");
		}catch (NoSuchFieldException e) {
			PApplet.println("s control; no field: update");
		}catch (IllegalAccessException e) {
		} 
		try{
			field = Link.getClass().getField(control); 
			//field.set(Link, "update");
			field.set(Link, a); 
			//applet.println(field.get(Link),valuex,control,Link);
		}catch (NullPointerException e) {
			PApplet.println("s control; null");
		}catch (NoSuchFieldException e) {
			PApplet.println("s control; no field");
		}catch (IllegalAccessException e) {
		} 
	};

	public void setControl(){
		valuex = PApplet.map( value,startvalue,endvalue, 0+1, w-1);
		if(pie)pieVal = PApplet.map( value,startvalue,endvalue, 0, 2*applet.PI);
		PApplet.println("slider set v",value,pieVal);

	};

	public void setStart(float a){
		Field field = null;
		try{
			field = Link.getClass().getField(control); 
			field.set(Link, a); 
		}catch (NullPointerException e) {
		}catch (NoSuchFieldException e) {
		}catch (IllegalAccessException e) {
		} 
	};



	public void setEnd(float a){
		Field field = null;
		try{
			field = Link.getClass().getField(control); 
			field.set(Link, a); 
		}catch (NullPointerException e) {
		}catch (NoSuchFieldException e) {
		}catch (IllegalAccessException e) {
		} 
	};

	public void setStartValue(float a) {
		start = a;
	};

	public void setEndValue(float a) {
		end = a;
	};

	public void functions(){
		applet.stroke(theme.sliderstrokecol,theme.slidertransparency);
		applet.strokeWeight(theme.sliderstrokesize); 
		if(!theme.sliderborder)applet.noStroke();

		if(classic){
			if(square)classicSquare();
			else if(radio)classicRadio();
			else if(bar)classicBar();
		}else if(matrix){
			if(square)Matrix();
			else if(radio){}
			else if(bar){}
		}else if(pie){
			if(square)pieSquare();
			else if(radio)pieRadio();
			else if(bar)pieBar();
		}
	};

	public void functions(PGraphics canvas){
		//		mouseFunctions(mouse);
		if(visible){
			canvas.stroke(theme.sliderstrokecol,theme.slidertransparency);
			canvas.strokeWeight(theme.sliderstrokesize); 
			if(!theme.sliderborder)canvas.noStroke();

			if(classic){
//				if(square)classicSquare(canvas);
//				if(radio)classicRadio(canvas);
				if(bar)classicBar(canvas);
			}else if(matrix){
				if(square)Matrix();
				else if(radio){}
				else if(bar){}
			}else if(pie){
				if(square)pieSquare(canvas);
				else if(radio)pieRadio();
				else if(bar)pieBar();
			}}
	};

	public void classicSquare(){
		//----------slider bg-----------
		applet.strokeWeight(theme.sliderstrokesize);
		applet.stroke(theme.sliderstrokecol,theme.slidertransparency);
		if(!theme.sliderborder)applet.noStroke();

		applet.fill(theme.sliderfillcol,theme.slidertransparency);
		if(pos(mouse))applet.fill(theme.sliderhcol,theme.slidertransparency);
		if(!theme.sliderfill)applet.noFill();

		if(vertical)applet.rect(x,y,w,h,theme.r1,theme.r2,theme.r3,theme.r4);
		else applet.rect(x,y,w,h,theme.r1,theme.r2,theme.r3,theme.r4);

		applet.textSize(theme.slidertextsize);

		if(parent==null){
			if(btnpos(mouse)||pos(mouse)||mdown) {
				applet.fill(col4);
				btnh = h+10;
			}
		}

		if(label!=null){
			applet.fill(theme.slidertextcol);
			if(vertical){
				applet.pushMatrix();
				applet.translate(x+txoff,y+tyoff);
				applet.rotate(PConstants.PI/2);

				applet.translate(-(x+txoff)-applet.textWidth(label)-5,-(y+tyoff));
				applet.text(label,x+txoff,y+tyoff);
				applet.popMatrix();
			}else applet.text(label,x-applet.textWidth(label),y+h);
		}
		if(!vertical)btnh = h;
		applet.strokeWeight(theme.sliderstrokesize);
		applet.stroke(theme.sliderstrokecol,theme.slidertransparency);
		if(!theme.sliderborder)applet.noStroke();
		//applet.fill(Bms.col);
		//		if(pos(mouse))applet.fill(theme.sliderhcol,theme.slidertransparency);
		//		if(vertical)applet.rect(x,y+valuex,btnw-2,btnw-2,theme.r1,theme.r2,theme.r3,theme.r4);
		//		else  applet.rect(x + valuex,y,btnw,btnh,theme.r1,theme.r2,theme.r3,theme.r4);
		//		applet.fill(255-theme.slidertransparency);

		//slider value-------------------
		applet.rectMode(PConstants.CORNER);
		applet.fill(theme.sliderfillcol,theme.slidertransparency);
		if(!theme.sliderfill)applet.noFill();

		if(vertical)applet.rect(x,y+valuex,btnw-2,btnw-2,theme.r1,theme.r2,theme.r3,theme.r4);
		else applet.rect(x+valuex,y,btnw,btnh,theme.r1,theme.r2,theme.r3,theme.r4);

		applet.fill(theme.slidertextcol);
		if(vertical){
			applet.pushMatrix();
			applet.translate(x+w+txoff,y+h+tyoff);
			applet.rotate(PConstants.PI/2);

			applet.translate(-(x+w+txoff),-(y+h+tyoff-w));
			applet.text(value,x+w+txoff,y+h+tyoff);
			applet.popMatrix();
		}else applet.text(value,x+w+txoff,y+h+tyoff);

	};

	public void classicSquare(PGraphics canvas){
		//----------slider bg-----------
		applet.textSize(theme.slidertextsize);
		canvas.strokeWeight(theme.sliderstrokesize);
		canvas.stroke(theme.sliderstrokecol,theme.slidertransparency);
		if(!theme.sliderborder)canvas.noStroke();

		canvas.fill(theme.sliderfillcol,theme.slidertransparency);
		if(pos(mouse))canvas.fill(theme.sliderhcol,theme.slidertransparency);
		if(!theme.sliderborder)canvas.noFill();

		if(vertical)canvas.rect(x,y,w,h,theme.r1,theme.r2,theme.r3,theme.r4);
		else canvas.rect(x,y,w,h,theme.r1,theme.r2,theme.r3,theme.r4);

		canvas.textSize(theme.slidertextsize);
		canvas.strokeWeight(theme.sliderstrokesize);
		canvas.stroke(theme.sliderstrokecol,theme.slidertransparency);
		if(parent==null){
			if(btnpos(mouse)||pos(mouse)||mdown) {
				canvas.fill(col4);
				btnh = h+10;
			}
		}


		if(label!=null){
			canvas.fill(theme.slidertextcol);
			if(vertical){
				canvas.pushMatrix();
				canvas.translate(x+txoff,y+tyoff);
				canvas.rotate(PConstants.PI/2);

				canvas.translate(-(x+txoff)-applet.textWidth(label)-5,-(y+tyoff));
				canvas.text(label,x+txoff,y+tyoff);
				canvas.popMatrix();
			}else canvas.text(label,x-applet.textWidth(label),y+h);
		}
		if(!vertical)btnh = h;

		canvas.strokeWeight(theme.sliderstrokesize);
		canvas.stroke(theme.sliderstrokecol,theme.slidertransparency);
		if(!theme.sliderborder)canvas.noStroke();

		canvas.fill(theme.sliderfillcol,theme.slidertransparency);
		if(pos(mouse))canvas.fill(theme.sliderhcol,theme.slidertransparency);
		if(!theme.sliderborder)canvas.noFill();

		//slider value-------------------
		canvas.rectMode(PConstants.CORNER);
		canvas.fill(theme.sliderfillcol,theme.slidertransparency);
		if(vertical)canvas.rect(x,y+valuex-btnw/2,btnw-2,btnw-2,theme.r1,theme.r2,theme.r3,theme.r4);
		else canvas.rect(x+valuex-btnw/2,y,btnw,btnh,theme.r1,theme.r2,theme.r3,theme.r4);

		canvas.fill(theme.slidertextcol);
		if(vertical){
			canvas.pushMatrix();
			canvas.translate(x+w+txoff,y+h+tyoff);
			canvas.rotate(PConstants.PI/2);

			canvas.translate(-(x+w+txoff),-(y+h+tyoff-w));
			canvas.text(value,x+w+txoff,y+h+tyoff);
			canvas.popMatrix();
		}else canvas.text(value,x+w+txoff,y+h+tyoff);

	};

	public void classicRadio(){
		//----------slider bg-----------
		applet.strokeWeight(theme.sliderstrokesize);
		applet.stroke(theme.sliderstrokecol,theme.slidertransparency);
		if(!theme.sliderborder)applet.noStroke();

		applet.fill(theme.sliderfillcol,theme.slidertransparency);
		if(pos(mouse))applet.fill(theme.sliderhcol,theme.slidertransparency);
		if(!theme.sliderborder)applet.noFill();

		if(vertical)applet.rect(x,y,w,h,theme.r1,theme.r2,theme.r3,theme.r4);
		else applet.rect(x,y+h/2-1,w,2,theme.r1,theme.r2,theme.r3,theme.r4);

		applet.textSize(theme.slidertextsize);
		applet.strokeWeight(theme.sliderstrokesize);
		applet.stroke(theme.sliderstrokecol,theme.slidertransparency);
		if(parent==null){
			if(btnpos(mouse)||pos(mouse)||mdown) {
				applet.fill(col4);
				btnh = h+10;
			}
		}


		if(label!=null){
			applet.fill(theme.slidertextcol);
			if(vertical){
				applet.pushMatrix();
				applet.translate(x+txoff,y+tyoff);
				applet.rotate(PConstants.PI/2);

				applet.translate(-(x+txoff)-applet.textWidth(label)-5,-(y+tyoff));
				applet.text(label,x+txoff,y+tyoff);
				applet.popMatrix();
			}else applet.text(label,x-applet.textWidth(label),y+h);
		}
		if(!vertical)btnh = h;

		applet.strokeWeight(theme.sliderstrokesize);
		applet.stroke(theme.sliderstrokecol,theme.slidertransparency);
		if(!theme.sliderborder)applet.noStroke();

		applet.fill(theme.sliderfillcol,theme.slidertransparency);
		if(pos(mouse))applet.fill(theme.sliderhcol,theme.slidertransparency);
		if(!theme.sliderborder)applet.noFill();

		//		if(vertical)applet.rect(x,y+valuex,btnw-2,btnw-2,theme.r1,theme.r2,theme.r3,theme.r4);
		//		else  applet.rect(x + valuex,y,btnw,btnh,theme.r1,theme.r2,theme.r3,theme.r4);
		applet.fill(255-theme.slidertransparency);

		//slider value-------------------
		applet.rectMode(PConstants.CORNER);
		applet.fill(theme.sliderfillcol,theme.slidertransparency);
		if(pos(mouse))  applet.fill(theme.sliderhcol,theme.slidertransparency);
		if(!theme.sliderborder)applet.noFill();
		if(vertical)applet.ellipse(x,y+valuex,btnw-2,btnw-2);
		else applet.ellipse(x+valuex,y+h/2,(btnw+2)/2,(btnw+2)/2);

		applet.fill(theme.slidertextcol);
		if(vertical){
			applet.pushMatrix();
			applet.translate(x+w+txoff,y+h+tyoff);
			applet.rotate(PConstants.PI/2);

			applet.translate(-(x+w+txoff),-(y+h+tyoff-w));
			applet.text(value,x+w+txoff,y+h+tyoff);
			applet.popMatrix();
		}else applet.text(value,x+w+txoff,y+h+tyoff);

	};

	public void classicRadio(PGraphics canvas){
		//----------slider bg-----------
		canvas.strokeWeight(theme.sliderstrokesize);
		canvas.stroke(theme.sliderstrokecol,theme.slidertransparency);
		if(!theme.sliderborder)canvas.noStroke();

		canvas.fill(theme.sliderfillcol,theme.slidertransparency);
		if(pos(mouse))canvas.fill(theme.sliderhcol,theme.slidertransparency);
		if(!theme.sliderborder)canvas.noFill();

		if(vertical)canvas.rect(x+w/2-1,y,2,h,theme.r1,theme.r2,theme.r3,theme.r4);
		else canvas.rect(x,y+h/2-1,w,2,theme.r1,theme.r2,theme.r3,theme.r4);

		canvas.textSize(theme.slidertextsize);
		canvas.strokeWeight(theme.sliderstrokesize);
		canvas.stroke(theme.sliderstrokecol,theme.slidertransparency);
		if(parent==null){
			if(btnpos(mouse)||pos(mouse)||mdown) {
				canvas.fill(col4);
				btnh = h+10;
			}
		}

		if(label!=null){
			canvas.fill(theme.slidertextcol);
			if(vertical){
				canvas.pushMatrix();
				canvas.translate(x+txoff,y+tyoff);
				canvas.rotate(PConstants.PI/2);

				canvas.translate(-(x+txoff)-applet.textWidth(label)-5,-(y+tyoff));
				canvas.text(label,x+txoff,y+tyoff);
				canvas.popMatrix();
			}else canvas.text(label,x-applet.textWidth(label),y+h);
		}
		if(!vertical)btnh = h;

		canvas.strokeWeight(theme.sliderstrokesize);
		canvas.stroke(theme.sliderstrokecol,theme.slidertransparency);
		if(!theme.sliderborder)canvas.noStroke();

		canvas.fill(theme.sliderfillcol,theme.slidertransparency);
		if(pos(mouse))canvas.fill(theme.sliderhcol,theme.slidertransparency);
		if(!theme.sliderborder)canvas.noFill();

		//		if(vertical)canvas.rect(x,y+valuex,btnw-2,btnw-2,theme.r1,theme.r2,theme.r3,theme.r4);
		//		else  canvas.rect(x + valuex,y,btnw,btnh,theme.r1,theme.r2,theme.r3,theme.r4);
		canvas.fill(255-theme.slidertransparency);

		//slider value-------------------
		canvas.rectMode(PConstants.CORNER);
		canvas.fill(theme.sliderfillcol,theme.slidertransparency);
		if(pos(mouse))  canvas.fill(theme.sliderhcol,theme.slidertransparency);
		if(!theme.sliderborder)canvas.noFill();
		if(vertical)canvas.ellipse(x+w/2,y+valuex,(btnw+2)/2,(btnw+2)/2);
		else canvas.ellipse(x+valuex,y+h/2,(btnw+2)/2,(btnw+2)/2);

		canvas.fill(theme.slidertextcol);
		if(vertical){
			canvas.pushMatrix();
			canvas.translate(x+w+txoff,y+h+tyoff);
			canvas.rotate(PConstants.PI/2);

			canvas.translate(-(x+w+txoff),-(y+h+tyoff-w));
			canvas.text(value,x+w+txoff,y+h+tyoff);
			canvas.popMatrix();
		}else canvas.text(value,x+w+txoff,y+h+tyoff);

	};

	public void classicBar(){
		//----------slider bg-----------
		applet.strokeWeight(theme.sliderstrokesize);
		applet.stroke(theme.sliderstrokecol,theme.slidertransparency);
		if(!theme.sliderborder)applet.noStroke();

		applet.fill(theme.sliderfillcol,theme.slidertransparency);
		if(pos())applet.fill(theme.sliderhcol,theme.slidertransparency);
		if(!theme.sliderfill)applet.noFill();

		if(vertical)applet.rect(x,y+valuex,w,h-valuex,theme.r1,theme.r2,theme.r3,theme.r4);
		else applet.rect(x+valuex,y,w-valuex,h,theme.r1,theme.r2,theme.r3,theme.r4);

		applet.textSize(theme.slidertextsize);
		if(parent==null){
			if(btnpos()||pos()||mdown)applet.fill(col4);btnh = h+10;
		}

		if(label!=null){
			applet.fill(theme.slidertextcol);
			applet.textSize(theme.slidertextsize);
			if(vertical){
				applet.pushMatrix();
				applet.translate(x,y);
				applet.rotate(PConstants.PI/2);
				applet.translate(-(x)-applet.textWidth("text")+txoff,-(y)+tyoff);
				if(tvisible)applet.text("text",x,y);
				applet.popMatrix();
			}
			else applet.text(label,x-applet.textWidth(label),y+h);
		}
		if(!vertical)btnh = h;
		applet.fill(255-theme.slidertransparency);

		//slider value-------------------
		if(vertical)applet.rect(x,y,w,valuex,theme.r1,theme.r2,theme.r3,theme.r4);
		else applet.rect(x,y,valuex,btnh,theme.r1,theme.r2,theme.r3,theme.r4);

		applet.fill(theme.slidertextcol);
		if(vertical){
			applet.pushMatrix();
			applet.translate(x+w+txoff,y+h+tyoff);
			applet.rotate(PConstants.PI/2);

			applet.translate(-(x+w+txoff),-(y+h+tyoff-w));
			if(tvisible)applet.text(value,x+w+txoff,y+h+tyoff);
			applet.popMatrix();
		}else if(tvisible)applet.text(value,x+w+txoff,y+h+tyoff);

	};

	public void classicBar(PGraphics canvas){
		canvas.strokeWeight(theme.sliderstrokesize);
		canvas.stroke(theme.sliderstrokecol,theme.slidertransparency);
		if(!theme.sliderborder)canvas.noStroke();

		if(mouse==null)mouse = new PVector(applet.mouseX,applet.mouseY);

		//----------slider bg-----------
		canvas.fill(theme.sliderbgcol,theme.slidertransparency);
		if(pos(mouse))canvas.fill(theme.sliderhcol,theme.slidertransparency);
		if(!theme.sliderfill)canvas.noFill();

		if(vertical)canvas.rect(x,y+valuex,w,h-valuex,0,theme.r2,theme.r3,0);
		else canvas.rect(x ,y,w,h,theme.r1,theme.r2,theme.r3,theme.r4);

		canvas.textSize(theme.slidertextsize);
		if(parent==null){
			if(btnpos(mouse)||pos(mouse)||mdown)canvas.fill(col4);btnh = h+10;
		}

		if(label!=null){
			canvas.fill(theme.slidertextcol);
			if(vertical){
				canvas.pushMatrix();
				canvas.translate(x+txoff,y+tyoff);
				canvas.rotate(PConstants.PI/2);

				canvas.translate(-(x+txoff)-applet.textWidth(label)-5,-(y+tyoff));
				canvas.text(label,x+txoff,y+tyoff);
				canvas.popMatrix();
			}else canvas.text(label,x-applet.textWidth(label),y+h);
		}
		if(!vertical)btnh = h;
		canvas.fill(255-theme.slidertransparency);
		//slider value-------------------

		canvas.fill(theme.sliderfillcol,theme.slidertransparency);
		if(vertical)canvas.rect(x,y,w,valuex,theme.r1,theme.r2,theme.r3,theme.r4);
		else canvas.rect(x,y,valuex,btnh,theme.r1,theme.r2,theme.r3,theme.r4);
		canvas.fill(theme.slidertextcol);
//		mouseFunctions(mouse);
		float t = applet.map(valuex,0,w,startvalue,endvalue);
//		if(mouseIn)applet.println("start",startvalue,endvalue);
//		if(mouseIn)applet.println("set mousein 01",label,value,x);
		if(vertical){
			canvas.pushMatrix();
			canvas.translate(x+w+txoff,y+h+tyoff);
			canvas.rotate(PConstants.PI/2);

			canvas.translate(-(x+w+txoff),-(y+h+tyoff-w));
			if(tvisible)canvas.text(t,x+w+txoff,y+h+tyoff);
			canvas.popMatrix();
		}
		else if(tvisible)canvas.text(t
				,x+w+txoff,y+h+tyoff);
		
	};

	public void Matrix(){

		applet.fill(col2);
		applet.rect(x,y,w,h);

	};

	public void pieSquare(){
		float val = 0;
		float v1 = 10;
		boolean k = PApplet.dist(applet.mouseX,applet.mouseY,x,y)<radius;
		applet.fill(theme.slidertextcol);
		applet.textSize(theme.slidertextsize);
		applet.strokeWeight(theme.sliderstrokesize/2);
		applet.stroke(theme.sliderstrokecol,theme.slidertransparency);
		if(!theme.sliderborder)applet.noStroke();
		if(!tooltipPos())
			if(applet.mousePressed&&k&&!mdown1&&Bms.sliderObject ==null){
				mdown1 = true;
				Bms.sliderObject = this;
			}
		//if(mdown)val = applet.map(applet.mouseX,0,width,0,2*applet.PI);
		if(mdown1)pieVal = PApplet.abs(2*PConstants.PI-(PApplet.atan2(x-applet.mouseX,y-applet.mouseY)+PConstants.PI/2));
		if(pieVal>2*PConstants.PI)pieVal -=PConstants.PI*2;

		applet.fill(theme.sliderfillcol,theme.slidertransparency);
		if(k)applet.fill(theme.sliderhcol,theme.slidertransparency);
		applet.ellipseMode(PConstants.CENTER);
		applet.ellipse( x,y,radius*2-v1,radius*2-v1);


		applet.fill(theme.sliderfillcol,theme.slidertransparency);
		if(k)
			applet.fill(theme.sliderhcol,theme.slidertransparency);
		applet.ellipse( x,y,radius*2-25,radius*2-25);
		applet.fill(theme.slidertextcol);

		//if(mdown1) {
		value = PApplet.map(pieVal,0,2*PConstants.PI,startvalue,endvalue);
		if(INT)value = (int) PApplet.map(pieVal,0,2*PConstants.PI,startvalue,endvalue);
		//}
		applet.text(value,x- applet.textWidth(applet.str(value))/2,y+h-40);
		if(!applet.mousePressed){
			mdown1 = false;
			if(Bms.sliderObject == this)Bms.sliderObject = null;
		}
	};

	public void pieSquare(PGraphics canvas){
		float v1 = 10;
		applet.textSize(theme.slidertextsize);
		applet.strokeWeight(theme.sliderstrokesize/2);
		applet.stroke(theme.sliderstrokecol,theme.slidertransparency);
		if(!theme.sliderborder)applet.noStroke();

		boolean k = parentTab.posTab()&&parentTab.visible
				&&!tooltipPos(mouse);


		boolean k1 = PApplet.dist(mouse.x,mouse.y,x,y)<radius;
		boolean k3 = Bms.getObject(this);
//		boolean k4 = 

		if(k1&&theme.click&&Bms.getObject()){
//			applet.println("slider piesq m mdown ",label);
			mdown = true;
			Bms.setObject(this);
		}
		if(mdown) {
//			Bms.setObject(this);
//			applet.println("slider piesq m mdown null obj",label);
		}
//		if(Bms.sliderObject!=null&&mdown)applet.println("slider piesq m mdown",label,Bms.sliderObject.label);
		if(k3)
		if(mdown)pieVal = PApplet.abs(2*PConstants.PI-(PApplet.atan2(x-mouse.x,y-mouse.y)+PConstants.PI/2));
		if(pieVal>2*PConstants.PI)pieVal -=PConstants.PI*2;

		canvas.fill(theme.slidertextcol);
		canvas.textSize(theme.slidertextsize);
		canvas.text(label,x - applet.textWidth(label)/2, y - radius);

		canvas.ellipseMode(PConstants.CENTER);
		canvas.fill(theme.sliderouterfcol,theme.slidertransparency);
		if(k&&(k1||mdown))canvas.fill(theme.sliderouterhcol,theme.slidertransparency);

		canvas.ellipse( x,y,radius*2-v1,radius*2-v1);
		canvas.fill(theme.sliderarcfcol,theme.slidertransparency);
		if(k&&(k1))canvas.fill(theme.sliderarchcol,theme.slidertransparency);
		canvas.arc(x,y, radius*2, radius*2, 0, pieVal, PConstants.PIE);

		canvas.fill(theme.sliderinnerfcol,theme.slidertransparency);
		if(k&&(k1||mdown))canvas.fill(theme.sliderinnerhcol);
		if(!theme.sliderfill)applet.noFill();
		canvas.arc(x,y, radius*2-25, radius*2-25, 0, pieVal, PConstants.PIE);
		//		if(!tooltipPos(mouse))
		if(k3&&k&&(k1||mdown)) {
			canvas.noStroke();
			canvas.fill(theme.sliderinnerhcol,theme.slidertransparency);
			canvas.stroke(theme.sliderinnerfcol,theme.slidertransparency);
			canvas.arc(x,y, radius*2-25, radius*2-25, 0, pieVal, PConstants.PIE);
		}
		applet.stroke(theme.sliderstrokecol,theme.slidertransparency);
		if(!theme.sliderborder)applet.noStroke();
		canvas.fill(theme.sliderinnerfcol,theme.slidertransparency);
		if(k&&(k1||mdown1))canvas.fill(theme.sliderinnerhcol);
		canvas.ellipse( x,y,radius*2-25,radius*2-25);

		applet.stroke(theme.sliderstrokecol,theme.slidertransparency);
		if(!theme.sliderborder)applet.noStroke();
		canvas.arc(x,y, radius*2, radius*2, 0, pieVal, PConstants.PIE);

		canvas.fill(theme.slidertextcol);
		canvas.textSize(theme.slidertextsize);
//		if(mdown&&Bms.sliderObject == this) {
			value = PApplet.map(pieVal,0,2*PConstants.PI,startvalue,endvalue);
			if(INT)value = (int) value;
//			applet.println("slider piesq int",INT,value);

//		}
		canvas.text(value,x- applet.textWidth("00000")/2,y+h-h/2+10);
		if(mdown&&!applet.mousePressed){
			mdown = false;
			if(Bms.getObject(this))Bms.clearObject(this);
		}
	};

	public void pieLogic(){

	};

	public void pieLogic(PVector mouse){
		if(PApplet.dist(mouse.x,mouse.y,x,y)<radius&&applet.mousePressed&&!mdown&&Bms.getObject()){
			mdown = true;
			Bms.setObject(this);

		}
		if(PApplet.dist(mouse.x,mouse.y,x,y)>radius&&applet.mousePressed&&!mdown&&Bms.getObject(this)){
			Bms.clearObject(this);

		}
		if(mdown&&Bms.getObject(this)){
			pieVal = PApplet.abs(2*PConstants.PI-(PApplet.atan2(x-mouse.x,y-mouse.y)+PConstants.PI/2));
			if(pieVal>2*PConstants.PI)pieVal -=PConstants.PI*2;
			value = PApplet.map(pieVal,0,2*PConstants.PI,startvalue,endvalue);
		}
	};

	public void pieRadio(){
		if(!fill){
			applet.noFill();
		}
		applet.fill(col2);
		applet.ellipseMode(PConstants.CENTER);
		applet.ellipse(x,y,w,h);
		applet.arc(x,y,w,h,valuex,valuey);
		applet.arc(x+10,y+10,w,h,valuex,valuey);
		applet.fill(col3);
		applet.ellipseMode(PConstants.CENTER);
		if(!horizontal){
			applet.ellipse(x+valuex,y,btnw,btnh);
		}else{
			applet.ellipse(x,y + valuey,10,btnh);
		}
	};

	public void pieBar(){
		if(!fill){
			applet.noFill();
		}
		applet.fill(col2);
		applet.ellipseMode(PConstants.CENTER);
		applet.ellipse(x,y,w,h);
		applet.arc(x,y,w,h,valuex,valuey);
		applet.arc(x+10,y+10,w,h,valuex,valuey);
		//line();
	};

	public void mouseFunctions(){
		if(pos()&&applet.mousePressed&&Bms.getObject()){
			Bms.setObject(this);
			mup = false;
			mdown = true;
			toggle = true;
		}
		boolean k = tooltip!=null&&tooltip.toggle;
		if(!k&&!parentCanvas&&Bms.getObject(this)){
			if(bar){
				if(vertical){
					if(applet.mouseY>y&&applet.mouseY<y + h-1)valuex = applet.mouseY-y;
					if(applet.mouseY>y+h-1)valuex = h-1;
				}else{
					if(applet.mouseX>x&&applet.mouseX<x + w-1)valuex = applet.mouseX-x;
					if(applet.mouseX>x+w-1)valuex = w-1;
				}}
			else if(radio||square){
				if(vertical){
					if(applet.mouseY>y&&applet.mouseY<y + h-1)valuex = applet.mouseY-y-1;
					if(applet.mouseY>y+h-btnw)valuex = h-btnw;
				}else{
					if(applet.mouseX>x&&applet.mouseX<x + w-1)valuex = applet.mouseX-x;
					if(applet.mouseX>x+w-btnw)valuex = w-btnw;
				}}
			if(mdown) {
				tempValue = PApplet.map( valuex, 1, w-1,startvalue,endvalue);
				value = tempValue;
			}
		}
		if(mdown&&!applet.mousePressed&&!tdown&&Bms.getObject(this)){
			mdown = false;
			toggle = false;
			Bms.clearObject(this);
		}
		if(mdown&&!applet.mousePressed&&Bms.getObject(this)){
			//			Bms.sliderObject = null;
			Bms.clearObject(this);
			mdown = false;
		}
		if(mdown&&!applet.mousePressed){
			mdown = false;
		}
		if(!mdown&&!applet.mousePressed&&Bms.getObject(this)){
			//			Bms.sliderObject = null;
			Bms.clearObject(this);
		}
	};

	public void mouseFunctions(PVector m){
		
		boolean k = tooltip==null||tooltip!=null&&!tooltipPos(m);
		if(Bms.theme.mousePressed&&!mfdown) {
			mouseOut = !pos(mouse);
			mouseIn = pos(mouse);
			mfdown = true;
		}
		
		if(mouseOut&&pos(mouse)&&Bms.getObject())mouseIn = true;
		
		if(mouseIn&&Bms.getObject()){
			Bms.setObject(this);
		}
		if(applet.mousePressed)
		if(mouseIn&&Bms.getObject(this)&&mfdown){
			if(bar){
				if(vertical){
					if(m.y>y&&m.y<y + h-1)valuex = m.y-y;
					if(m.y>y+h-1)valuex = h-1;
				}else{
					if(m.x>x&&m.x<x + w-1)valuex = m.x-x;
					if(m.x>x+w-1)valuex = w-1;
				}}
			else if(radio||square){
				if(vertical){
					if(m.y>y-1+btnw/2&&m.y<y + h-1)valuex = m.y-y-1;
					if(m.y>y+h-btnw/2)valuex = h-btnw/2;

				}else{
					if(m.x>x-1+btnw/2&&m.x<x + w)valuex = m.x-x;
					if(m.x>x+w-btnw/2)valuex = w-btnw/2;
				}}
				if(mdown) {
					tempValue = PApplet.map( valuex, 1, w-1,startvalue,endvalue);
					value = tempValue;
				}
			}
		if(mdown&&!applet.mousePressed) {
//			float v = applet.map(valuex, 0, w-1, startvalue, endvalue);
//			applet.println("start",startvalue,"end",endvalue,valuex,v);
			mouseIn = false;
			mouseOut = false;
			mdown = false;
		}
		
		if(!applet.mousePressed) {
			mfdown = false;
			if(Bms.getObject(this)&&!tdown) {
				Bms.clearObject(this);
			}
		}

	};

	boolean pos(){
		if(pie)return PApplet.dist(applet.mouseX,applet.mouseY,x,y)<radius;
		else return applet.mouseX>x&&applet.mouseX<x+w&&applet.mouseY>y&&applet.mouseY<y+h;
	};

	boolean pos(PVector m){
		if(pie)return PApplet.dist(m.x,m.y,x,y)<radius;
		else return m!=null&&m.x>x&&m.x<x+w&&m.y>y&&m.y<y+h;
		// else 
		//return applet.mouseX-parentTab.x>x&&applet.mouseX-parentTab.x<x+w&&applet.mouseY-parentTab.y>y&&applet.mouseY-parentTab.y<y+h;
		//return false;
	};



	boolean btnpos(){
		if(pie) {
			return PApplet.dist(applet.mouseX,applet.mouseY,x,y)<radius;
		}else {
			if(!horizontal){
				return applet.mouseX>x + valuex &&applet.mouseX<x+valuex+btnw&&applet.mouseY>y&&applet.mouseY<y+h;

			}
			else{
				return applet.mouseX>x&&applet.mouseX<x+w&&applet.mouseY>y+ valuey&&applet.mouseY<y + valuey +btnh;
			}
		}
	};

	boolean btnpos(PVector m){
		if(pie) {
			return PApplet.dist(m.x,m.y,x,y)<radius;
		}else {
			if(!horizontal){
				return m.x>x + valuex &&m.x<x+valuex+btnw&&m.y>y&&m.y<y+h;

			}
			else{
				return m!=null&&m.x>x&&m.x<x+w&&m.y>y+ valuey&&m.y<y + valuey;
			}
		}
	};

	boolean tooltipPos(){
		applet.textSize(theme.buttontextsize);
		applet.textSize(theme.buttontextsize);
		if(!vertical) {
			if(pie) {
				return applet.mouseX>x+w/2&&applet.mouseX<x+w/2+20
						&&applet.mouseY>y-h/2&&applet.mouseY<y+h/2;
			}else {
				return applet.mouseX>x+w+applet.textWidth("0.0000")&&applet.mouseX<x+w+applet.textWidth("0.0000")+20
						&&applet.mouseY>y&&applet.mouseY<y+h;
			}
		}else {
			if(pie) {
				return applet.mouseX>x+w/2&&applet.mouseX<x+w/2+20
						&&applet.mouseY>y-h/2&&applet.mouseY<y+h/2;
			}else {
				return applet.mouseX>x&&applet.mouseX<x+w
						&&applet.mouseY>y+h+applet.textWidth("0.0000")&&applet.mouseY<y+h+applet.textWidth("0.0000")+20;
			}
		}
		
	};

	boolean tooltipMenuPos(){
			return applet.mouseX>tooltip.x&&applet.mouseX<tooltip.x+tooltip.w
					&&applet.mouseY>tooltip.y&&applet.mouseY<tooltip.y+tooltip.h;
		
	};

	boolean tooltipPos(PVector m){
		applet.textSize(theme.buttontextsize);
		if(!vertical) {
			if(pie) {
				return mouse.x>x+w/2&&mouse.x<x+w/2+20
						&&mouse.y>y-h/2&&mouse.y<y+h/2;
			}else {
				return mouse.x>x+w+applet.textWidth("0.0000")&&mouse.x<x+w+applet.textWidth("0.0000")+20
						&&mouse.y>y&&mouse.y<y+h;
			}
		}else {
			if(pie) {
				return mouse.x>x-w/2&&mouse.x<x+w/2
						&&mouse.y>y+h/2&&mouse.y<y+h/2+20;
			}else {
				return mouse.x>x&&mouse.x<x+w
						&&mouse.y>y+h+applet.textWidth("0.0000")&&mouse.y<y+h+applet.textWidth("0.0000")+20;
			}
		}
	};

	boolean tooltipMenuPos(PVector m){

		return m.x>tooltip.x&&m.x<tooltip.x+tooltip.w&&m.y>tooltip.y&&m.y<tooltip.y+tooltip.h;
	};

	boolean tooltipTabPos() {
		return mouse.x>tooltip.x&&mouse.x<tooltip.x+tooltip.w&&mouse.y>tooltip.y&&mouse.y<tooltip.y+tooltip.h;

	};

	public void click(){

	};

	public void set(float start, float end,Object o,String s) {
		if(!setLimits) {
			setLimits(start,end);
			setLimits = true;
		}
		if(Link==null)Link = o;
		if(control=="")control = s;
		float v = 0;
		if(!tdown&&mdown&&Bms.getObject(this)){
			if((!tdown)){
				if(vertical)tempValue = PApplet.map( valuex, 0+1, h-1,start,end);
				else tempValue = PApplet.map( valuex, 0+1, w-1,start,end);
//				value = tempValue;
//				if(pie)value = PApplet.map( pieVal, 0, applet.PI*2,start,end);
			}
			Field field = null;
			try{
				field = o.getClass().getField(s); 
				if(mdown){
					field.set(o, tempValue); 
					update = false;
				}
			}catch (NullPointerException e) {
				applet.println("slider set 1 ex npe",o,s,value,"field: ", field);
			}catch (NoSuchFieldException e) {
				applet.println("slider set 1 ex nsf",o,s,value,"field: ", field);
			}catch (IllegalAccessException e) {
				applet.println("slider set 1 ex ia");
			}} 
	};
	
	public void setLimits(float start, float end) {
		startvalue = start;
		endvalue = end;
	};

	public void setPie(float start, float end,Object a,String b) {
		if(!setLimits) {
			setLimits(start,end);
			setLimits = true;
		}
		
		if(Link==null)Link = a;
		if(control=="")control = b;
		float v = 0;
		if((!tdown||update)&&applet.mousePressed){
			if(mdown1||update){
				v = PApplet.map( pieVal, 0, 2*applet.PI,start,end);
				value = v;
			}
			Field field = null;
			try{
				field = a.getClass().getField(b); 
				if(mdown1||update){
					field.set(a, value); 
					update = false;
				}
			}catch (NullPointerException e) {
			}catch (NoSuchFieldException e) {
			}catch (IllegalAccessException e) {
			}} 
	};

	public void setPieInt(int start, int end,Object a,String b) {
		if(!setLimits) {
			setLimits(start,end);
			setLimits = true;
		}
		if(Link==null)Link = a;
		if(control=="")control = b;
		float v = 0;
		if((!tdown||update)&&applet.mousePressed){
			if(mdown1||update){
				v = PApplet.map( pieVal, 0, w,start,end);
				value = v;
			}
			Field field = null;
			try{
				field = a.getClass().getField(b); 
				if(mdown1||update){
					field.set(a, value); 
					update = false;
				}
			}catch (NullPointerException e) {
			}catch (NoSuchFieldException e) {
			}catch (IllegalAccessException e) {
			}} 
	};

	public void set(float start, float end,Object a,String b,String c) {
		if(!setLimits) {
			setLimits(start,end);
			setLimits = true;
		}
		if(Link==null)Link = a;
		if(control=="")control = b;
		float v = 0;
		if((!tdown||update)&&mdown&&Bms.getObject(this)){
			if(mdown||update){
				if(vertical)v = PApplet.map( valuex, 0+1, h-1,start,end);
				else v = PApplet.map( valuex, 0+1, w-1,start,end);
				value = v;
			}
			Field field1 = null;
			try{
				field1 = a.getClass().getField(c); 
				if(mdown||update)field1.set(a, true); 
			}catch (NullPointerException e) {
			}catch (NoSuchFieldException e) {
			}catch (IllegalAccessException e) {
			}
			Field field = null;
			try{
				field = a.getClass().getField(b); 
				if(mdown||update){
					field.set(a, v); 
					update = false;
				}
			}catch (NullPointerException e) {
			}catch (NoSuchFieldException e) {
			}catch (IllegalAccessException e) {
			}} 
	};

	public void setPie(float start, float end,Object a,String b,String c) {
		if(!setLimits) {
			setLimits(start,end);
			setLimits = true;
		}
		if(Link==null)Link = a;
		if(control=="")control = b;
		float v = 0;
		if((!tdown||update)&&applet.mousePressed){
			if(mdown||update){
				v = PApplet.map( pieVal, 0, 2*applet.PI,start,end);
				value = v;
			}
			Field field1 = null;
			try{
				field1 = a.getClass().getField(c); 
				if(mdown||update)field1.set(a, true); 
			}catch (NullPointerException e) {
			}catch (NoSuchFieldException e) {
			}catch (IllegalAccessException e) {
			}
			Field field = null;
			try{
				field = a.getClass().getField(b); 
				if(mdown||update){
					field.set(a, v); 
					update = false;
				}
			}catch (NullPointerException e) {
			}catch (NoSuchFieldException e) {
			}catch (IllegalAccessException e) {
			}} 
	};

	public void set(float start, float end) {
		
		float v = 0;
		if(!setLimits) {
			setLimits(start,end);
			setLimits = true;
		}
		if(!vertical){
			if(applet.mousePressed)
			if(Bms.getObject(this)&&mouseIn){
				
				v = PApplet.map( valuex, 1, w-1,start,end);
				value = v;
				update = false;
//				mdown = true;
//				applet.println("set mousein 00",label,start,end,value);
			}}else{
				if(Bms.getObject(this)&&mouseIn){
//					if(applet.mousePressed)mdown = true;
					v = PApplet.map( valuex, 1, w-1,start,end);
					value = v;
					update = false;
				}}
//		if(!applet.mousePressed)mdown = false;
	};

	public void setPie(float start, float end) {

		float v = 0;
		if(!setLimits) {
			setLimits(start,end);
			setLimits = true;
		}
		if(!vertical){
			if((!tdown||update)&&mdown&&Bms.getObject(this)){
				v = PApplet.map( pieVal, 0, 2*applet.PI,start,end);
				value = v;
				update = false;
			}}else{
				if((!tdown||update)&&mdown&&Bms.getObject(this)){
					v = PApplet.map( pieVal, 0, 2*applet.PI,start,end);
					value = v;
					update = false;
				}}
		//if(mdown&&!applet.mousePressed){
		//  if(Bms.sliderObject==this)Bms.sliderObject = null;
		//  mdown = false;
		//}
	};

	public void setint(int start, int end,Object a,String b) {
		if(Link==null)Link = a;
		if(control=="")control = b;
		int v = 0;
		if((!tdown||update)&&applet.mousePressed){
			//valuex = 
			if(vertical)v = PApplet.floor(PApplet.map( valuex, 0+1, h-1,start,end));
			else v = PApplet.floor(PApplet.map( valuex, 0+1, w-1,start,end));
			value = v;
		}
		Field field = null;
		try{
			field = a.getClass().getField(b); 
			if(mdown||update){
				field.set(a, (v)); 
				update = false;
			}
		}catch (NoSuchFieldException e) {
		}catch (IllegalAccessException e) {
		}      //noLoop();
	};

	public void setint(int start, int end) {
		int v = 0;
		if((mdown||update)&&applet.mousePressed){
			v = PApplet.floor(PApplet.map( valuex, 1, w-1,start,end));
			value = v;
			update = false;
		}
	};

	public void setPieInt(int start, int end) {
		int v = 0;
		INT = true;
		if(!setLimits) {
			setLimits(start,end);
			setLimits = true;
		}
//		if(applet.mousePressed)applet.println("slider setpieint");

		if((mdown||update)&&applet.mousePressed){
			v = (int) PApplet.map( pieVal, 0, w,start,end);
//			applet.println("slider setpieint",v);
			value = v;
			update = false;
		}
	};

	public void setBms(BMS bms) {
		Bms = bms;
		applet = bms.applet;
		theme = bms.theme;
	};

	public void setPos(float a,float b) {
		x = a;
		bx = a;
		y = b;
		by = b;
	};

	public void setClassicBar() {

		classic = true;
		matrix = false;
		pie = false;
		radio = false;
		bar = true;
		square = false;
		if(tooltip!=null)tooltip.setPos(x+w+applet.textWidth("0000"),y);
	};

	public void setClassicSquare() {

		classic = true;
		matrix = false;
		pie = false;
		radio = false;
		bar = false;
		square = true;
		if(tooltip!=null)tooltip.setPos(x+w+applet.textWidth("0000"),y);
	};

	public void setClassicRadio() {

		classic = true;
		matrix = false;
		pie = false;
		radio = true;
		bar = false;
		square = false;
		if(tooltip!=null)tooltip.setPos(x+w+applet.textWidth("0000"),y);
	};

	public void setPieSquare() {

		classic = false;
		matrix = false;
		pie = true;
		radio = false;
		bar = false;
		square = true;
	};

	public void setPieBar() {

		classic = false;
		matrix = false;
		pie = true;
		radio = false;
		bar = true;
		square = false;
	};
};
