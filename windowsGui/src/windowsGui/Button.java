package windowsGui;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PGraphics;
import processing.core.PImage;
import processing.core.PVector;

public class Button {
	public BMS Bms;
	public PApplet applet;
	public PGraphics canvas;
	public float x,y,bx,by,w,h,size,textsize = 12,xoff,yoff,bsize,tsize = 12,tyoff,txoff,tmax = 2,tw,th;
	public int id,toggle2,type,counter,themeIndex;
	public float scalew,scaleh,r1,r2,r3,r4,rx,ry,transparency;

	public String label,blabel;
	public PImage img;
	public boolean drag,resize, radio,update,border = true,vertical,horizontal,gif,Img,value,textright,textbtm,textleft,
			textup,texttoggle,animate = true,toggleb,mdown,m1down,sdown,visible = true,plain = true,labelVisible = true
			,up,right,down,classicBar,toggleBox,mdown2,textcheck,parentCanvas,subLeft,click,mclick,m2down,toggle;
	public boolean localTheme,mdown1,mup,register,hover,dclick,set,BMSbound;
	public int fcol,bcol,hcol,col,tcol,col1 = fcol,toggleCol,arrayIndex;
	public Menu parent;
	public Menu submenu;
	public Window subwindow;
	public ArrayList<Button> buttons = new ArrayList<Button>();
	public HashMap<String,Boolean> values = new HashMap<String,Boolean>();
	public String Text = "" ;
	public TextArea textbox;
	public PVector mouse;
	public tab parentTab;
	public Dock parentDock;
	public Dropdown dMenu;
	public tooltip info;
	boolean isTitle;
	public Theme theme,newTheme;

	public Button(float xx, float yy, float ww, float hh, String Label){

		x = xx;
		y = yy;
		bx = x;
		by = y;
		w = ww;
		h = hh;
		label = Label;
		blabel = label;
		size = 1;
		textsize = hh/2+hh/5;
		bsize = tsize;

	};



	public Button(float xx, float yy, float ww, float hh, String Label,BMS bms){
		Bms = bms;
		applet = BMS.applet;
		theme = Bms.theme;
		x = xx;
		y = yy;
		bx = x;
		by = y;
		w = ww;
		h = hh;
		label = Label;
		blabel = label;
		size = 1;
		textsize = hh/2+hh/5;
		bsize = tsize;

	};

	public Button(float xx, float yy, float ww, float hh){

		x = xx;
		y = yy;
		bx = x;
		by = y;
		w = ww;
		h = hh;
		size = 1;
		textsize = hh/2+hh/5;
		bsize = tsize;

	};

	public Button(float xx, float yy, float ww, float hh,BMS bms){
		Bms = bms;
		applet = BMS.applet;

		x = xx;
		y = yy;
		bx = x;
		by = y;
		w = ww;
		h = hh;
		size = 1;
		textsize = hh/2+hh/5;
		bsize = tsize;
		theme = Bms.theme;
		initColors();
	};

	public Button(float xx, float yy, float ww, float hh,int Cols){

		x = xx;
		y = yy;
		bx = x;
		by = y;
		w = ww;
		h = hh;
		size = 1;
		textsize = hh/2+hh/5;
		bsize = tsize;
		value = true;
		textbox = new TextArea(x,y,ww+1,hh,Bms);

	};

	public Button(float xx, float yy, float ww, float hh,int Cols, String Label){

		x = xx;
		y = yy;
		bx = x;
		by = y;
		w = ww;
		h = hh;
		size = 1;
		textsize = hh/2+hh/5;
		bsize = tsize;
		label = Label;
		blabel = label;
		value = true;
		textbox = new TextArea(x,y,ww+1,hh,Label,Bms);

	};

	public Button(){

	};

	public void save(){
//		Bms.output.reset();
		Bms.output.setLocation(Bms.dataPath+"\\preferences.txt");
		Bms.output.writeLine("");
		Bms.output.writeLine("button");
		Bms.output.writeLine("themeIndex",themeIndex);
		Bms.output.writeLine(x+",");
		Bms.output.write(y+",");
		Bms.output.write(w+",");
		Bms.output.write(h+",");
		Bms.output.write(y+",");
		Bms.output.write(label+",");
		Bms.output.write((submenu==null)+",");
	};
	
	public void defaultSave(){
//		Bms.output.reset();
		Bms.output.setLocation(Bms.dataPath+"\\preferences.txt");
		Bms.output.writeLine("");
		Bms.output.writeLine("_button");
		if(BMSbound) Bms.output.writeLine("BmsBound");
		Bms.output.writeLine("arrayIndex",arrayIndex);
		Bms.output.writeLine("themeIndex",themeIndex);
		Bms.output.writeLine(x+",");
		Bms.output.write(y+",");
		Bms.output.write(w+",");
		Bms.output.write(h+",");
		Bms.output.write(y+",");
		Bms.output.write(label+",");
		Bms.output.write((submenu==null)+",");
	};

	public void load(){

	};

	public void draw2(){
		applet.fill(theme.bcol,theme.buttontransparency);
		applet.rect(x,y,w,h);
	};

	public void btn(){
		if(!radio&&!classicBar){
			applet.strokeWeight(theme.buttonstrokesize);
			applet.stroke(theme.buttonstrokecol);
			if(!theme.buttonborder)applet.noStroke();
			if(parent!=null&&type!=0){
				x = parent.x;
				//y = parent.y;
			}

			applet.fill(col,theme.buttontransparency);
			if(!theme.buttonfill)applet.noFill();
			applet.rect(x ,y,w,h,r1,r2,r3,r4);

			applet.textSize(textsize);

			if(label!=null){

				if(scaleh>0){
					applet.pushMatrix();
					applet.translate(0,y*scaleh+h/2);
					applet.scale(1,scaleh);
				}
				if(scalew>0){
					applet.pushMatrix();
					applet.translate(x*scalew,0);
					applet.scale(scalew,1);
				}
				applet.fill(theme.buttontextcol);
				applet.textSize(theme.buttontextsize);
				// if(!textbtm&&!textright&&!textup)applet.text(label,x+5+txoff,y+13+tyoff+5);
				if(textbtm)applet.text(label,x+txoff,y+h+tyoff+tsize);
				applet.textSize(theme.buttontextsize);
				if(scaleh>0){
					applet.scale(1,1/scaleh);
					applet.popMatrix();
				}
				if(scalew>0){
					applet.scale(-scalew,1/scalew);
					applet.popMatrix();
				}

			}
			if(value){

				if(textup)textbox.y     = y - h;
				if(textbtm)textbox.y    = y + h;
				if(textleft) textbox.x  = x - w;
				if(textright) textbox.x = x + w;
				Text = textbox.tempLine;

				if(pos()||textbox.pos()||textbox.toggle)texttoggle=true;
				else texttoggle = false;
				if(texttoggle)textbox.draw();
				if(textbox.pos()&&applet.mousePressed&&parent!=null){ parent.draw();}
				else parent.toggle = false;
			}
			if(img!=null){
				applet.image(img,x,y+1,w,h);
			}}
	};



	public void initColors() {

		//		  fcol = applet.color(0, 255, 73);
		//		  bcol = applet.color(0,100);
		//		  hcol = applet.color(255,50);
		//		  col = fcol;
		//		  tcol = applet.color(theme.buttontransparency);
		//		  col1 = fcol;
		//		  toggleCol = applet.color(50,0);

		col = applet.color(0, 255, 73);
		bcol = applet.color(theme.buttontransparency);
		tcol = applet.color(theme.buttontransparency); 
		fcol = applet.color(0, 255, 73);
		hcol = applet.color(0, 255, 73,100);
		toggleCol = applet.color(55, 84, 63);
	};

	public void drawPlain() {
		if(plain){
			applet.stroke(theme.buttonstrokecol,theme.buttontransparency);
			applet.strokeWeight(theme.buttonstrokesize);
			if(!theme.buttonborder)applet.noStroke();
			if(w>h){

				applet.fill(col,theme.buttontransparency);
				if(!theme.buttonfill)applet.noFill();
				applet.rect(x+xoff,y+yoff,w,h,r1,r2,r3,r4);

				if(label!=null){

					applet.fill(theme.buttontextcol);
					applet.textSize(theme.buttontextsize);

					if(up)applet.text(label,x+txoff,y-3+tyoff);
					if(right)applet.text(label,x+w+2,y+tyoff);
					if(down)applet.text(label,x,y+tsize*2+2+tyoff);
					else applet.text(label,x +5 + txoff,y+tsize + tyoff + 4);
					applet.textSize(theme.buttontextsize);
				}}
			else{
				applet.fill(col,theme.buttontransparency);
				if(!theme.buttonfill)applet.noFill();
				applet.rect(x+xoff,y+yoff,w,h,r1,r2,r3,r4);

				if(label!=null){
					applet.fill(theme.buttontextcol);
					applet.textSize(theme.buttontextsize);
					if(up)applet.text(label,x+txoff,y-3+tyoff);
					if(right)applet.text(label,x+w+2+txoff,y+tyoff);
					if(down)applet.text(label,x+txoff,y+tsize*2+2+tyoff);
					else applet.text(label,x + 5 +txoff,y+w/2+tsize/2+tyoff);
					applet.textSize(theme.buttontextsize);
				}}}
	};

	public void drawPlain(PGraphics canvas) {
		if(plain){
			canvas.fill(0);
			canvas.stroke(theme.buttonstrokecol,theme.buttontransparency);
			if(!theme.buttonborder)canvas.noStroke();
			//		      canvas.ellipse(mouse.x,mouse.y,20,20);
			if(w>h){

				canvas.fill(col,theme.buttontransparency);
				if(!theme.buttonfill)canvas.noFill();
				canvas.rect(x+xoff,y+yoff,w,h,r1,r2,r3,r4);

				if(label!=null){

					canvas.fill(theme.buttontextcol);
					canvas.textSize(theme.buttontextsize);

					if(up)canvas.text(label,x+txoff,y-3+tyoff);
					if(right)canvas.text(label,x+w+2,y+tyoff);
					if(down)canvas.text(label,x,y+tsize*2+2+tyoff);
					else canvas.text(label,x +5 + txoff,y+tsize + tyoff + 4);
					canvas.textSize(theme.buttontextsize);
				}}
			else{


				canvas.fill(col,theme.buttontransparency);
				if(!theme.buttonfill)canvas.noFill();
				canvas.rect(x+xoff,y+yoff,w,h,r1,r2,r3,r4);

				if(label!=null){
					canvas.fill(theme.buttontextcol);
					canvas.textSize(theme.buttontextsize);
					if(up)canvas.text(label,x+txoff,y-3+tyoff);
					if(right)canvas.text(label,x+w+2+txoff,y+tyoff);
					if(down)canvas.text(label,x+txoff,y+tsize*2+2+tyoff);
					else canvas.text(label,x + 5 +txoff,y+w/2+tsize/2+tyoff);
					canvas.textSize(theme.buttontextsize);
				}}}
	};

	public void radioLogic() {

	}

	public void drawClassicRadio(){
		if(radio){
			applet.stroke(theme.buttonstrokecol,theme.buttontransparency);
			applet.strokeWeight(theme.buttonstrokesize);
			if(!theme.buttonborder)applet.noStroke();

			applet.fill(col,theme.buttontransparency);
			if(!theme.buttonfill)applet.noFill();
			applet.rect(x +rx + xoff,y+yoff,w,h,r1,r2,r3,r4);

			if(label!=null){
				applet.textSize(theme.buttontextsize);
				applet.fill(theme.buttontextcol);
				if(up)applet.text(label,x + txoff,y-3+ tyoff);
				if(right)applet.text(label,x+w+2+ txoff,y+ tyoff);
				if(down)applet.text(label,x+ txoff,y+tsize*2+2+ tyoff);
				else applet.text(label,x +5 + txoff,y + tsize + tyoff);
				applet.textSize(theme.buttontextsize);
			}

			applet.fill(0);
			if(toggle)applet.fill(theme.buttontransparency);
			applet.ellipseMode(PConstants.CENTER);
			applet.ellipse(x+rx+w/2+xoff ,y+h/2+yoff,h-5,h-5);
		}
	};

	//revise code
	public void drawClassicBar() {
		if(classicBar){
			applet.stroke(theme.buttonstrokecol,theme.buttontransparency);
			applet.strokeWeight(theme.buttonstrokesize);
			if(!theme.buttonborder)applet.noStroke();

			applet.fill(col,theme.buttontransparency);
			if(!theme.buttonfill)applet.noFill();
			applet.rect(x+xoff,y+yoff,w,h,r1,r2,r3,r4);
			if(label!=null){

				applet.fill(theme.buttontextcol);
				applet.textSize(theme.buttontextsize);

				if(up)applet.text(label,x+txoff,y-3+tyoff);
				if(right)applet.text(label,x+w+2+txoff,y+tyoff);
				if(down)applet.text(label,x+txoff,y+tsize*2+2+tyoff);
				else applet.text(label,x +5 + txoff,y+tsize + tyoff + 4);
				applet.textSize(theme.buttontextsize);
			}
		}

	};

	public void drawTogglebox() {
		if(toggleBox){
			applet.stroke(theme.buttonstrokecol,theme.buttontransparency);
			applet.strokeWeight(theme.buttonstrokesize);
			if(!theme.buttonborder)applet.noStroke();

			applet.fill(col,theme.buttontransparency);
			if(!theme.buttonfill)applet.noFill();
			applet.rect(x + rx+xoff,y+yoff,w,h,r1,r2,r3,r4);

			if(label!=null){
				applet.fill(theme.buttontextcol);
				applet.textSize(theme.buttontextsize);
				if(up)applet.text(label,x+txoff,y-3+tyoff);
				if(right)applet.text(label,x+w+2+txoff,y+tyoff);
				if(down)applet.text(label,x+txoff,y+tsize*2+2+tyoff);
				else applet.text(label,x + 5 + txoff,y+tsize + tyoff + 4);
				applet.textSize(theme.buttontextsize);
			}
			applet.fill(col,theme.buttontransparency);

			if(!toggle){
				applet.rect(x + rx+xoff,y,w/2+yoff,h,r1,r2,r3,r4);
				applet.fill(theme.buttontextcol);
				applet.text("OFF",x+rx+w+10+txoff,y+tsize+tyoff+4);
			}else{
				applet.fill(theme.buttonfillcol,theme.buttontransparency);
				applet.rect(x+rx+w/2+xoff,y,w/2+yoff,h,r1,r2,r3,r4);
				applet.fill(theme.buttontextcol);
				applet.text("ON",x+rx+w+10+txoff,y+tsize+tyoff+4);
			}
		}
	};

	public void drawClassicRadio(PGraphics canvas){
		if(radio){
			canvas.strokeWeight(theme.buttonstrokesize);
			canvas.stroke(theme.buttonstrokecol,theme.buttontransparency);
			if(!theme.buttonborder)canvas.noStroke();

			canvas.fill(col,theme.buttontransparency);
			canvas.rect(x +rx + xoff,y+yoff,w,h,r1,r2,r3,r4);

			if(label!=null){
				canvas.textSize(theme.buttontextsize);
				canvas.fill(theme.buttontextcol);
				if(up)canvas.text(label,x + txoff,y-3+ tyoff);
				if(right)canvas.text(label,x+w+2+ txoff,y+ tyoff);
				if(down)canvas.text(label,x+ txoff,y+tsize*2+2+ tyoff);
				else canvas.text(label,x +5 + txoff,y + tsize + tyoff);
				canvas.textSize(theme.buttontextsize);
			}

			canvas.fill(0);
			if(toggle)canvas.fill(theme.buttontransparency);
			canvas.ellipseMode(PConstants.CENTER);
			canvas.ellipse(x+rx+w/2+xoff ,y+h/2+yoff,h-5,h-5);
		}
	};

	//revise code
	public void drawClassicBar(PGraphics canvas) {
		if(classicBar){
			canvas.fill(0);
			canvas.strokeWeight(theme.buttonstrokesize);
			canvas.stroke(theme.buttonstrokecol,theme.buttontransparency);
			if(!theme.buttonborder)canvas.noStroke();

			canvas.fill(col,theme.buttontransparency);
			if(!theme.buttonfill)canvas.noFill();
			canvas.rect(x+xoff,y+yoff,w,h,r1,r2,r3,r4);

			if(label!=null){
//				if(parent!=null&&parent.dmenu)applet.println("button",label);
				canvas.fill(theme.buttontextcol);
				canvas.textSize(theme.buttontextsize);

				if(up)canvas.text(label,x+txoff,y-3+tyoff);
				if(right)canvas.text(label,x+w+2+txoff,y+tyoff);
				if(down)canvas.text(label,x+txoff,y+tsize*2+2+tyoff);
				else canvas.text(label,x +5 + txoff,y+tsize + tyoff + 4);
				canvas.textSize(theme.buttontextsize);
			}
		}
	};

	public void drawTogglebox(PGraphics canvas) {
		if(toggleBox){
			canvas.strokeWeight(theme.buttonstrokesize);
			canvas.stroke(theme.buttonstrokecol,theme.buttontransparency);
			if(!theme.buttonborder)applet.noStroke();

			canvas.fill(col,theme.buttontransparency);
			if(!theme.buttonfill)canvas.noFill();
			canvas.rect(x + rx+xoff,y+yoff,w,h,r1,r2,r3,r4);

			if(label!=null){
				canvas.fill(theme.buttontextcol);
				canvas.textSize(theme.buttontextsize);
				if(up)canvas.text(label,x+txoff,y-3+tyoff);
				if(right)canvas.text(label,x+w+2+txoff,y+tyoff);
				if(down)canvas.text(label,x+txoff,y+tsize*2+2+tyoff);
				else canvas.text(label,x + 5 + txoff,y+tsize + tyoff + 4);
				canvas.textSize(theme.buttontextsize);
			}
			canvas.fill(col,theme.buttontransparency);

			if(!toggle){
				canvas.rect(x + rx+xoff,y,w/2+yoff,h,r1,r2,r3,r4);
				canvas.fill(theme.buttontextcol);
				canvas.text("OFF",x+rx+w+10+txoff,y+tsize+tyoff+4);
			}else{
				canvas.fill(theme.buttonfillcol,theme.buttontransparency);
				canvas.rect(x+rx+w/2+xoff,y,w/2+yoff,h,r1,r2,r3,r4);
				canvas.fill(theme.buttontextcol);
				canvas.text("ON",x+rx+w+10+txoff,y+tsize+tyoff+4);
			}
		}
	};

	void getThemeradius() {
		r1 = theme.r1;
		r2 = theme.r2;
		r3 = theme.r3;
		r4 = theme.r4;
		if(isTitle) {
			r3 = 0;
			r4 = 0;
		}
	};

	public void draw(){
		getThemeradius();
		highlight();
		logic();
//		if(getParent()&&click)PApplet.println("btn draw parent toggle");
		applet.textSize(theme.buttontextsize);
		applet.pushStyle();
		applet.textFont(theme.buttonFont);
		if(plain)drawPlain();
		if(radio)drawClassicRadio();
		if(classicBar)drawClassicBar();
		if(toggleBox)drawTogglebox();
		applet.popStyle();
		if(subpos())toggle2 = 1;
		else toggle2 = 0;

		applet.strokeWeight(1);
		//		if(hover)applet.ellipse(applet.mouseX, applet.mouseY, 20, 20);

		if(info!=null)info.draw();
	};

	public void logic(){
		//		click = false;
		if(applet.mousePressed)mdown = true;
		if(animate){
			if(pos()||toggle){
				if(bsize<tsize+tmax) bsize += 0.5;
			}else if(bsize>tsize)bsize -= 0.5;
		}
		if(radio||toggleBox){
		}

		if(parentTab!=null||parentDock!=null)click = theme.click&&pos();
		else click = theme.click&&pos();
//		if(click)
	};

	public void logic(PVector m){
		if(animate){
			if(pos(m)||toggle){
				if(bsize<tsize+tmax) bsize += 0.5;
			}else if(bsize>tsize)bsize -= 0.5;

		}
		if(radio||toggleBox){
		}
		if(parentTab!=null||parentDock!=null)click = theme.click&&pos();

	};

	public void draw(PGraphics canvas) {
		getThemeradius();
		if(mouse==null)mouse = new PVector(applet.mouseX,applet.mouseY);
		logic(mouse);
		highlight(mouse);
		canvas.textSize(theme.buttontextsize);
		canvas.pushStyle();
		canvas.textFont(theme.buttonFont);
		if(plain)drawPlain(canvas);
		else if(radio)drawClassicRadio(canvas);
		else if(classicBar)drawClassicBar(canvas);
		else if(toggleBox)drawTogglebox(canvas);
		canvas.popStyle();
		if(subpos(mouse))toggle2 = 1;
		else toggle2 = 0;

		canvas.strokeWeight(1);
		if(info!=null)info.draw();

	};

	public void radio(){

		radio = true;
	};



	public void labelcheck(float a){

		if(applet.textWidth(label)>a){
			for(int i=10;i<label.length();i++){
				float lw = applet.textWidth(label.substring(0,i)+ " ...");
				if(lw>a){
					label = label.substring(0,i-1) + "...";
					textcheck = true;
					break;
				}
			}}
		else textcheck = true;

	};

	public boolean pos(){
		//		if(radio||toggleBox){
		//			return x +rx < applet.mouseX && applet.mouseX < x + w+rx && y < applet.mouseY && applet.mouseY < y + h+2;
		//		}else{
		//			applet.fill(theme.buttontransparency);
		//			if( x  < applet.mouseX && applet.mouseX < x + w && y < applet.mouseY && applet.mouseY < y + h+2)applet.fill(0);
		//			//else 
		//		if("label"=="Button1")applet.ellipse(applet.mouseX,applet.mouseY,20,20);
		return  applet.mouseX > x && applet.mouseX < x + w && applet.mouseY > y && applet.mouseY < y+h;
		//}

	};

	boolean radioPos(){
		//return false;
		applet.textSize(theme.buttontextsize);
		return x +applet.textWidth(label)+20 < applet.mouseX && applet.mouseX < x + w+applet.textWidth(label)+20 && y < applet.mouseY && applet.mouseY < y + h+2;
	};

	boolean pos(PVector m){
		if(radio||toggleBox){

			return m!=null&&rx+x  < m.x && m.x < x+rx + w && y < m.y && m.y < y + h+2;

		}else{
			return m!=null&&x  < m.x && m.x < x + w && y < m.y && m.y < y + h+2;
		}

	};

	boolean pos(PGraphics m){
		if(radio||toggleBox){

			return m!=null&&rx+x  < mouse.x && mouse.x < x+rx + w && y < mouse.y && mouse.y < y + h+2;

		}else{
			return m!=null&&x  < mouse.x && mouse.x < x + w && y < mouse.y && mouse.y < y + h+2;
		}
	};

	boolean pos2(){

		return x < applet.mouseX && applet.mouseX < x + w + applet.textWidth(label)+5 && y < applet.mouseY && applet.mouseY < y + h+2;
	};

	boolean pos2(PVector m){

		return m!=null&&x < m.x && m.x < x + w + applet.textWidth(label)+5 && y < m.y && m.y < y + h+2;
	};

	boolean pos3(){

		return x + applet.textWidth(label)+5 < applet.mouseX && applet.mouseX < x + w + applet.textWidth(label)+5 && y < applet.mouseY && applet.mouseY < y + h+2;
	};

	boolean pos3(PVector m){

		return x + applet.textWidth(label)+5 < m.x && m.x < x + w + applet.textWidth(label)+5 && y < m.y && m.y < y + h+2;
	};

	public void getValue(){
		//if(key='ENTER')
	};

	public void setName(String a){
		label = a;
	};

	String getName(){
		String a = "";
		if (label!=null) a = label;

		return a;
	};

	public boolean click(Object a,String b){
		if((parent!=null&&click&&parent.toggle)||click){
			Field field = null;

			try{
				field = a.getClass().getField(b); 

				field.set(a, toggle); 
			}catch (NullPointerException e) {
			}catch (NoSuchFieldException e) {
			}catch (IllegalAccessException e) {
			}}
		return click;
	};

	public void reverseclick(Object a,String b){
		if(parent!=null){
			//if(!pos()&&parent.toggle&&applet.mousePressed)mdown = true;
			if(pos()&&parent.toggle&&applet.mousePressed&&!mdown)mdown = true;

			if(mdown)toggle = true;
			else toggle = false;
			if(mdown && !applet.mousePressed){mdown = false;}

			Field field = null;

			try{
				field = a.getClass().getField(b); 

				if(mdown)field.set(a, true); 
				else field.set(a, false);
			}catch (NullPointerException e) {
			}catch (NoSuchFieldException e) {
			}catch (IllegalAccessException e) {
			}}
		else{
			if(pos()&&applet.mousePressed&&!mdown)mdown = true;

			if(mdown){
				toggle = true;
			}
			else toggle = false;
			if(mdown && !applet.mousePressed){mdown = false;}

			Field field = null;

			try{
				field = a.getClass().getField(b); 

				if(!mdown)field.set(a, true); 
				else field.set(a, false);
			}catch (NullPointerException e) {
			}catch (NoSuchFieldException e) {
			}catch (IllegalAccessException e) {
			}
		}
	};

	public boolean latch(Object a,String b){
		PVector m = new PVector(applet.mouseX,applet.mouseY);
		boolean k = theme.mouseReleased&&hover;
		if(mouse!=null)m = mouse;

		if(k) {

			Field field = null;

			try{
				field = a.getClass().getField(b); 
				field.set(a, true); 
//				applet.println("btn latch",field.get(a),label);

			}catch (NullPointerException e) {
			}catch (NoSuchFieldException e) {
			}catch (IllegalAccessException e) {
			}}
		if(!applet.mousePressed)mdown = false;
		return k;
	};


	public boolean gettoggle() {

		return toggle;
	};

	public boolean getToggle() {
		toggle();

		return toggle;
	};

	public boolean getToggle(PVector m) {
		toggle();

		return toggle;
	};

	public boolean setAutotoggle() {
		if(click) {
			if(toggle)toggle = false;
			else toggle = true;
		}
		return click;
	};
	
	boolean getParent() {
		return parent == null
				|| parent!=null&&parent.parentMenu==null&&parent.toggle
				||parent!=null&&parent.parentMenu!=null&&parent.toggle
				&&parent.parentMenu.toggle
				&&parent.parentMenu.index==parent.subId;
	};

	public boolean toggle() {
		boolean k = getParent();
		if(theme.click&&hover&&k) {
			if(toggle)toggle = false;
			else toggle = true;
			//			applet.println("btn toggle",label,theme.click,toggle);
		}

		return theme.click&&hover;
	};
	
	public boolean toggle(PVector m) {
//		boolean k = getParent();
		hover = pos(m);
		if(theme.click&&hover) {
			if(toggle)toggle = false;
			else toggle = true;
			//			applet.println("btn toggle",label,theme.click,toggle);
		}

		return theme.click&&hover;
	};

	public boolean toggle(boolean a) {
		boolean k = theme.click&&hover&&getParent();
		if(k) {
			if(toggle)toggle = false;
			else toggle = true;
			a = toggle;
			//			applet.println("btn toggle",label,theme.click,toggle);
		}

		return k;
	};
	
	public boolean toggle(boolean a,PVector m) {
		boolean k = theme.click&&pos(m)&&getParent();
		if(k) {
			if(toggle)toggle = false;
			else toggle = true;
			a = toggle;
//						applet.println("btn toggle",label,theme.click,toggle,a);
		}

		return k;
	};

	public boolean toggle(boolean a,boolean b) {
		boolean k = theme.click&&hover&&getParent();
		if(k) {
			if(toggle)toggle = false;
			else toggle = true;
			a = toggle;
			b = toggle;
			//			applet.println("btn toggle",label,theme.click,toggle);
		}

		return k;
	};

	public boolean toggle(boolean a,boolean b,boolean c) {
		boolean k = theme.click&&hover&&getParent();
		if(k) {
			if(toggle)toggle = false;
			else toggle = true;
			a = toggle;
			b = toggle;
			c = toggle;
			//			applet.println("btn toggle",label,theme.click,toggle);
		}

		return k;
	};

	public boolean toggle(boolean[] a) {
		boolean k = theme.click&&hover&&getParent();
		if(k) {
			if(toggle)toggle = false;
			else toggle = true;
			for(int i=0;i<a.length;i++) {
				a[i] = toggle;
			}
			//			applet.println("btn toggle",label,theme.click,toggle);
		}

		return k;
	};

	public boolean toggle(Object a, String b){
		boolean k = theme.click&&pos()&&getParent();
		//		if(theme.click)applet.println("button toggle 00",label);
		if(parent!=null&&parent.toggle&&k||parent==null&&k){
			Field field = null;
			toggle();
			//				applet.println("button toggle 00",label,b);
			try{
				//	
				
				
				field = a.getClass().getField(b); 

					field.set(a, toggle);
			}catch (NullPointerException e) {
				PApplet.println("button toggle 1 npe");
			}catch (NoSuchFieldException e) {
				PApplet.println("button toggle 1 nsfe");
			}catch (IllegalAccessException e) {
				PApplet.println("button toggle 1 iae");
			}}
		if(!applet.mousePressed){
			mdown = false;
		}
		return click;
	};

	public void toggle(Object a, String b,PGraphics c){
		if(parent!=null){
			if(parent.toggle&&click){
				Field field = null;

				try{
					toggle();
					field = a.getClass().getField(b); 

					field.set(a, toggle); 

				}catch (NullPointerException e) {
					PApplet.println("np np",c,a,b);
				}catch (NoSuchFieldException e) {
					PApplet.println("np nsf",c,a,b);
				}catch (IllegalAccessException e) {
					PApplet.println("np ia",c,a,b);
				}}}else{

					if(click){

						Field field = null;

						try{

							field = a.getClass().getField(b); 

							field.set(a, toggle);
						}catch (NullPointerException e) {
							PApplet.println("p np",c,a,b);
						}catch (NoSuchFieldException e) {
							PApplet.println("p nsf",c,a,b);
						}catch (IllegalAccessException e) {
							PApplet.println("p ia",c,a,b);
						}}}
		if(!applet.mousePressed)mdown = false;
	};

	public boolean toggle(Object a, String b,PVector m){

		if(m==null)m = new PVector(applet.mouseX,applet.mouseY);
		click = theme.click&&pos(m)&&getParent();
		//		if(theme.click)PApplet.println("toggle pv click",theme.click);
		//		if(pos())PApplet.println("toggle pv pos");
		//		if(theme.click&&pos(m))PApplet.println("toggle pv click pos",theme.click);
		if(click)
			if(parent!=null&&parent.toggle||parent==null){

				Field field = null;

				try{
					toggle();
					field = a.getClass().getField(b); 

						field.set(a, toggle);

				}catch (NullPointerException e) {
					PApplet.println("toggle pv np np",m,a,b);
				}catch (NoSuchFieldException e) {
					PApplet.println("toggle pv np nsf",m,a,b);
				}catch (IllegalAccessException e) {
					PApplet.println("toggle pv np ia",m,a,b);
				}}
		if(!applet.mousePressed)mdown = false;
		return click;
	};

	public boolean toggle2(Object a, String b,PVector m){

		if(m==null)m = new PVector(applet.mouseX,applet.mouseY);
		boolean k = click = theme.click&&pos(m);
		boolean k1 = false;
		if(k&&theme.mouseReleased) {
			counter++;
			k1 = true;
		}
		//		boolean k1 = click = theme.click&&pos(m);
		if(k1)
			if(parent!=null&&parent.toggle||parent==null){

				Field field = null;

				try{
					toggle();
					field = a.getClass().getField(b); 

					field.set(a, toggle); 

				}catch (NullPointerException e) {
					PApplet.println("toggle pv np np",m,a,b);
				}catch (NoSuchFieldException e) {
					PApplet.println("toggle pv np nsf",m,a,b);
				}catch (IllegalAccessException e) {
					PApplet.println("toggle pv np ia",m,a,b);
				}}
		if(!applet.mousePressed)mdown = false;
		return click;
	};

	public void toggle(Object a, String b,PVector m,boolean k){
		if(m==null)m = new PVector(applet.mouseX,applet.mouseY);
		if(parent!=null){
			if(parent.toggle&&click){
				Field field = null;

				try{
					toggle();
					field = a.getClass().getField(b); 
					boolean k1 = field.getBoolean(a);
					toggle();
					field.set(a, toggle); 

				}catch (NullPointerException e) {
					PApplet.println("toggle pv np np",m,a,b);
				}catch (NoSuchFieldException e) {
					PApplet.println("toggle pv np nsf",m,a,b);
				}catch (IllegalAccessException e) {
					PApplet.println("toggle pv np ia",m,a,b);
				}}}else{

					if(click){

						Field field = null;

						try{

							field = a.getClass().getField(b); 
							field = a.getClass().getField(b); 
							boolean k1 = field.getBoolean(a);
							toggle();
							field.set(a, toggle); 
						}catch (NullPointerException e) {
							PApplet.println("toggle pv p np",m,a,b);
						}catch (NoSuchFieldException e) {
							PApplet.println("toggle pv p nsf",m,a,b);
						}catch (IllegalAccessException e) {
							PApplet.println("toggle pv p ia",m,a,b);
						}}}
		if(!applet.mousePressed)mdown = false;
	};
	
	public boolean itoggle(Object a, String b){
		boolean k = Bms.theme.click&&pos()&&getParent();
		if(k){
			mdown = false;
			Field field = null;

			try{
				toggle();
				field = a.getClass().getField(b); 

				field.set(a, id); 
				}catch (NoSuchFieldException e) {
				PApplet.println("nsf");
				}catch (NullPointerException e) {
					PApplet.println("npe");
				}catch (IllegalAccessException e) {
					PApplet.println("iae");
				}
		}
		return k;
	};
	
	public boolean itoggle(int [] c){
		boolean k = Bms.theme.click&&pos()&&getParent();
		if(k){
			mdown = false;
			toggle();

			for(int i=0;i<parent.items.size();i++){
				if(i!=id){
					parent.items.get(i).toggle = false;
				}}
			}
		return k;
	};
	
	public boolean itoggle(String [] c){
		boolean k = Bms.theme.click&&pos()&&getParent();
		if(k){
			mdown = false;
			toggle();
			
			for(int i=0;i<parent.items.size();i++){
				Button b = parent.items.get(i);
				String s1 = b.label;
				if(s1!=label){
					parent.items.get(i).toggle = false;
				}}
			}
		return k;
	};
	
	public boolean itoggle(Object a,String b,int [] c){
		
		boolean k = Bms.theme.click&&pos()&&getParent();
		if(k){
//			applet.println("btn itoggle",label);
			mdown = false;
			toggle();
			
			for(int i=0;i<parent.items.size();i++){
				if(i!=id){
					parent.items.get(i).toggle = false;
				}}
			Field field = null;
			try{
//				toggle();
				field = a.getClass().getField(b); 
				field.set(a, id); 
				}catch (NoSuchFieldException e) {
				PApplet.println("nsf");
				}catch (NullPointerException e) {
					PApplet.println("npe");
				}catch (IllegalAccessException e) {
					PApplet.println("iae");
				}
			}
		return k;
	};
	
	public boolean itoggle(Object a,String s,String [] c){
		boolean k = Bms.theme.click&&pos()&&getParent();
		if(k){
			mdown = false;
			toggle();
			
			for(int i=0;i<parent.items.size();i++){
				Button b = parent.items.get(i);
				String s1 = b.label;
				if(s1!=label){
					parent.items.get(i).toggle = false;
				}}
			Field field = null;
			try{
//				toggle();
				field = a.getClass().getField(s); 
				field.set(a, id); 
				}catch (NoSuchFieldException e) {
				PApplet.println("nsf");
				}catch (NullPointerException e) {
					PApplet.println("npe");
				}catch (IllegalAccessException e) {
					PApplet.println("iae");
				}
			}
		return k;
	};
	
	public void sptoggle(Object a, String b,String [] c){
		boolean k = Bms.theme.click&&pos()&&getParent();
		if(k){
			mdown = false;
			Field field = null;

			try{
				toggle();
				field = a.getClass().getField(b); 

				field.set(a, toggle); 
				}catch (NoSuchFieldException e) {
				PApplet.println("nsf");
				}catch (NullPointerException e) {
					PApplet.println("npe");
				}catch (IllegalAccessException e) {
					PApplet.println("iae");
				}

			for(int i=0;i<c.length;i++){
				String next = c[i];
				if(next!=b){
					//applet.println("button ",next,b," ");
					try{
						field = a.getClass().getField(next); 
						field.set(a, false); 
						parent.items.get(i).toggle = false;
						//applet.print(field.get(a)," ");
					}catch (NullPointerException e) {
						PApplet.println("nsf");
					}catch (NoSuchFieldException e) {
						PApplet.println("npe");
					}catch (IllegalAccessException e) {
						PApplet.println("iae");
					}
				}}}
	};

	public void sptoggle(Object a, String b,String [] c,PVector m){


		if(click){
			mdown = false;
			Field field = null;

			try{

				field = a.getClass().getField(b); 

				if(toggle){

					field.set(a, true); 
				}else {
					field.set(a, false);
					//applet.println("button ",next,a," ");
				}}catch (NoSuchFieldException e) {
					PApplet.println("nsf");
				}catch (NullPointerException e) {
					PApplet.println("npe");
				}catch (IllegalAccessException e) {
					PApplet.println("iae");
				}

			for(int i=0;i<c.length;i++){
				String next = c[i];
				if(next!=b){
					//applet.println("button ",next,b," ");
					try{
						field = a.getClass().getField(next); 
						field.set(a, false); 
						parent.items.get(i).toggle = false;
						//applet.print(field.get(a)," ");
					}catch (NullPointerException e) {
						PApplet.println("nsf");
					}catch (NoSuchFieldException e) {
						PApplet.println("npe");
					}catch (IllegalAccessException e) {
						PApplet.println("iae");
					}
				}}}
	};

	public void sptoggle2(Object a, String b,String [] c){

		if(click && parent.toggle){
			Field field = null;

			try{

				field = a.getClass().getField(b); 

				if(toggle){

					field.set(a, true); 
				}else {
					field.set(a, false);
					//applet.println("button ",next,a," ");
				}}catch (NoSuchFieldException e) {
					PApplet.println("nsf");
				}catch (NullPointerException e) {
					PApplet.println("npe");
				}catch (IllegalAccessException e) {
					PApplet.println("iae");
				}

			for(int i=0;i<c.length;i++){
				String next = c[i];
				if(next!=b){
					//applet.println("button ",next,b," ");
					try{
						field = a.getClass().getField(next); 
						field.set(a, false); 
						PApplet.print(field.get(a)," ");
					}catch (NullPointerException e) {
						PApplet.println("nsf");
					}catch (NoSuchFieldException e) {
						PApplet.println("npe");
					}catch (IllegalAccessException e) {
						PApplet.println("iae");
					}
				}}}
	};


	public void cycle(int b){
		if(parent!=null){
			if(pos() && parent.toggle&&applet.mousePressed&&!mdown){
				mdown = true;
				counter ++;

				if(counter==b)counter = 0;
			}
		}

		if(!applet.mousePressed){
			mdown = false;
		}
	};

	public void cycle(int b,PVector m){
		if(parent!=null){
			if(pos(m) && parent.toggle&&applet.mousePressed&&!mdown){
				mdown = true;
				counter ++;

				if(counter==b)counter = 0;
			}
		}

		if(!applet.mousePressed){
			mdown = false;
		}
	};

	public void cycle(int b,PGraphics canvas){
		if(parent!=null){
			if(pos(mouse) && parent.toggle&&applet.mousePressed&&!mdown){
				mdown = true;
				counter ++;

				if(counter==b)counter = 0;
			}
		}

		if(!applet.mousePressed){
			mdown = false;
		}
	};

	public void cycle(Object a,String b,int c) {
		if(mouse==null)mouse = new PVector(applet.mouseX,applet.mouseY);
		else mouse = new PVector(applet.mouseX,applet.mouseY);

		if(parent!=null){
			if(pos(mouse)&&parent.toggle&&counter<c&&applet.mousePressed&&!mdown){
				counter ++;
				mdown = true;
				PApplet.println("button cycle mdown");
			}

			if(pos(mouse)&&parent.toggle&&counter==c&&applet.mousePressed&&!mdown){
				counter = 0;
				mdown = true;
			}
			if(!applet.mousePressed)mdown = false;

			if(pos(mouse) && mdown&&!mdown1){

				mdown1 = true;

				Field field = null;

				try{

					field = a.getClass().getField(b); 
					field.set(a, counter); 
				}catch (NullPointerException e) {
					PApplet.println("Button cycle parent npe");
				}catch (NoSuchFieldException e) {
					PApplet.println("Button cycle parent nfe");
				}catch (IllegalAccessException e) {
					PApplet.println("Button cycle parent iae");
				}}}
		else {

			if(pos(mouse)&&applet.mousePressed&&counter<c&&!mdown){
				counter ++;
				mdown = true;
				PApplet.println("button cycle mdown");
			}

			else if(pos(mouse)&&applet.mousePressed&&counter==c&&!mdown){
				counter = 0;
				mdown = true;
				PApplet.println("button cycle mdown");
			}
			if(!applet.mousePressed)mdown = false;

			if(pos(mouse) && mdown&&!mdown1){
				mdown1 = true;
				Field field = null;

				try{

					field = a.getClass().getField(b); 
					field.set(a, counter); 
				}catch (NullPointerException e) {
					PApplet.println("Button cycle no parent npe");
				}catch (NoSuchFieldException e) {
					PApplet.println("Button cycle no parent nfe");
				}catch (IllegalAccessException e) {
					PApplet.println("Button cycle no parent iae");
				}
			}
		}
		if(!applet.mousePressed) {
			mdown = false;
			mdown1 = false;
		}
	};

	public void cycle(Object a,String b,int c,PVector mouse) {
		if(mouse==null)mouse = new PVector(applet.mouseX,applet.mouseY);
		if(parent!=null){
			if(pos(mouse)&&parent.toggle&&applet.mousePressed&&!mdown){
				mdown = true;
				PApplet.println("button cycle pv mdown");
			}
			if(pos(mouse) && mdown&&!mdown1){

				mdown1 = true;

				Field field = null;

				try{

					field = a.getClass().getField(b); 
					field.set(a, counter); 
				}catch (NullPointerException e) {
					PApplet.println("Button cycle pv parent npe");
				}catch (NoSuchFieldException e) {
					PApplet.println("Button cycle pv parent nfe");
				}catch (IllegalAccessException e) {
					PApplet.println("Button cycle pv parent iae");
				}}}
		else {

			if(pos(mouse)&&applet.mousePressed&&counter<c&&!mdown){
				counter++;
				mdown = true;
			}

			else if(pos(mouse)&&applet.mousePressed&&counter==c&&!mdown){
				counter = 0;
				mdown = true;
			}
			if(!applet.mousePressed)mdown = false;

			if(pos(mouse) && mdown&&!mdown1){
				mdown1 = true;
				Field field = null;

				try{

					field = a.getClass().getField(b); 
					field.set(a, counter); 
				}catch (NullPointerException e) {
					PApplet.println("Button cycle pv no parent npe");
				}catch (NoSuchFieldException e) {
					PApplet.println("Button cycle pv no parent nfe");
				}catch (IllegalAccessException e) {
					PApplet.println("Button cycle pv no parent iae");
				}
			}
		}
		if(!applet.mousePressed) {
			mdown = false;
			mdown1 = false;
		}
	};


	public void highlight2(){
		col = theme.col;
	}

	public void highlight2(PVector m){
		col = theme.col;
	}

	public void highlight3(){
		if(pos()) {
			col = theme.buttonhcol;
		}
		else {
			col = theme.buttonfillcol;
		}

	};

	public void highlight3(PVector m){
		if(pos(m)) {
			col = theme.buttonhcol;
		}
		else {
			col = theme.buttonfillcol;
		}

	};

	public void highlight(String l){

		if(toggle){
			col = theme.buttontogglecol;
			hover = false;
			if(pos()){
				hover = true;
				col = theme.buttonhcol;
			}
		}else{
			hover = false;
			col = theme.buttonfillcol;
			if(pos()){
				hover = true;
				col = theme.buttonhcol;
			}
		}

		PApplet.println("button highlights ",l);
	};

	public void highlight(){

		if(toggle){
			col = theme.buttontogglecol;
			hover = false;
			if(pos()){
				hover = true;
				col = theme.buttonhcol;
				//				applet.ellipse(mouse.x, mouse.y, 20, 20);
				//				applet.ellipse(applet.mouseX, applet.mouseY, 20, 20);

			}
		}else{
			hover = false;
			col = theme.buttonfillcol;
			if(pos()){
				hover = true;

				//				applet.ellipse(mouse.x, mouse.y, 20, 20);

				col = theme.buttonhcol;
			}
		}


	};

	public void highlight(PVector m){
		hover = false;
		if(toggle){
			col = theme.buttontogglecol;
			if(pos(mouse)){
				hover = true;
				col = theme.buttonhcol;
			}
		}else{
			col = theme.buttonfillcol;
			if(pos(mouse)){
				hover = true;
				col = theme.buttonhcol;
			}
		}
	};
	
	public void highlightPV(PVector m){
		hover = false;
		if(toggle){
			col = theme.buttontogglecol;
			if(pos(m)){
				hover = true;
				col = theme.buttonhcol;
			}
		}else{
			col = theme.buttonfillcol;
			if(pos(m)){
				hover = true;
				col = theme.buttonhcol;
			}
		}
	};

	public void setLabelOff() {
		labelVisible = false;
	};

	public void setVars(float a,float b,float c,float d,String s) {

		x = a;
		y = b;
		bx = x;
		by = y;
		w = c;
		h = d;
		label = s;
	};

	public void setVars(float a,float b,float c,float d) {

		x = a;
		y = b;
		bx = x;
		by = y;
		w = c;
		h = d;

	};

	boolean subpos(){
		float X = 0;
		float Y = 0;
		if(parent!=null){
			X = parent.x;
			Y = parent.y;
		}

		return applet.mouseX> x + w-20+xoff&& applet.mouseX < x + w&& applet.mouseY > y&& applet.mouseY < y + h;

	};

	boolean subpos(PVector m){
		float X = 0;
		float Y = 0;
		if(parent!=null){
			X = parent.x;
			Y = parent.y;
		}

		return m.x> x + w-20+xoff&& m.x < x + w&& m.y > y&& m.y < y + h;

	};

	boolean subposLeft(){
		float X = 0;
		float Y = 0;
		if(parent!=null){
			X = parent.x;
			Y = parent.y;
		}

		return applet.mouseX> x&& applet.mouseX < x + 20&& applet.mouseY > y&& applet.mouseY < y + h;

	};

	boolean subposLeft(PVector m){

		return m.x> x&& m.x < x + 20&& m.y > y&& m.y < y + h;

	};

	public void setPos(float a,float b) {
		x = a;
		bx = a;
		y = b;
		by = b;
	};

	public void setRadius(float a) {
		if(newTheme==null)newTheme = new Theme(applet);
		theme = newTheme;
		theme.buttonr1 = a;
		theme.buttonr2 = a;
		theme.buttonr3 = a;
		theme.buttonr4 = a;
		r1 = a;
		r2 = a;
		r3 = a;
		r4 = a;
	};
	
	public void setTitleRadius(Theme t) {
//		theme = t;
		r1 = theme.buttonr1;
		r2 = theme.buttonr2;
		
	};

	public void setTitleRadius(Theme theme, float a) {
		newTheme = theme;
		theme = newTheme;
		theme.buttonr1 = a;
		theme.buttonr2 = a;
	};

	public void setTitleRadius(Theme t, float a,float b) {
		newTheme = t;
		theme = newTheme;
		theme.buttonr1 = a;
		theme.buttonr2 = b;
	};
	
	public void setTitleRadius(float a,float b) {
		r1 = a;
		r2 = b;
	};
	
	public void setRadius(Theme t) {
		newTheme = t;
		theme = t;
		
		r1 = theme.buttonr1;
		r2 = theme.buttonr2;
		r3 = theme.buttonr3;
		r4 = theme.buttonr4;
	};

	public void setRadius(Theme t, float a) {
		this.theme = t;
		newTheme = t;
		r1 = theme.buttonr1 = a;
		r2 = theme.buttonr2 = a;
		r3 = theme.buttonr3 = a;
		r4 = theme.buttonr4 = a;
	};

	public void setRadius(float a,float b,float c,float d) {
		if(newTheme==null) newTheme = new Theme(Bms);
		newTheme.buttontextcol = applet.color(a);
		r1 = newTheme.buttonr1 = a;
		r2 = newTheme.buttonr2 = b;
		r3 = newTheme.buttonr3 = c;
		r4 = newTheme.buttonr4 = d;
		theme = newTheme;
		
	};

	public void setRadius(Theme t, float a,float b,float c,float d) {
		theme = t;
		newTheme = t;
		r1 = newTheme.buttonr1 = a;
		r2 = newTheme.buttonr2 = b;
		r3 = newTheme.buttonr3 = c;
		r4 = newTheme.buttonr4 = d;
	};
	
	public void setTextCol(Theme t) {
		theme = t;
	};

	public void setTextCol(Theme t, float a) {
		
		newTheme = t;
		newTheme.buttontextcol = applet.color(a);
		theme = newTheme;
	};

	public void setTextCol(Theme t,float a,float b,float c,float d) {
		newTheme = t;
		newTheme.buttontextcol = applet.color(a,b,c,d);
		theme = newTheme;
		
	};

	public void setTextSize(Theme theme, float a) {
		this.theme = theme;
		newTheme = theme;
		theme.buttontextsize = a;
	};

	public void setFillCol(Theme theme, float a) {
		this.theme = theme;
		newTheme = theme;
		theme.buttonfillcol = applet.color(a);
	};

	public void setFillCol(Theme theme,float a,float b,float c,float d) {
		newTheme = theme;
		theme = newTheme;
		theme.buttonfillcol = applet.color(a,b,c,d);
	};

	public void sethCol(Theme theme, float a) {
		this.theme = theme;
		newTheme = theme;
		theme.buttonhcol = applet.color(a);
	};

	public void sethCol(Theme theme,float a,float b,float c,float d) {
		if(newTheme==null) newTheme = new Theme(Bms);
		newTheme.buttonhcol = applet.color(a);
		theme = newTheme;
	};

	public void setTextCol( float a) {
		if(newTheme==null) newTheme = new Theme(Bms);
		newTheme.buttontextcol = applet.color(a);
		theme = newTheme;
	};

	public void setTextCol(float a,float b,float c,float d) {
		if(newTheme==null) newTheme = new Theme(Bms);
		newTheme.buttontextcol = applet.color(a,b,c,d);
		theme = newTheme;
	};
	
	public void setTextColor( float a) {
		if(newTheme==null) newTheme = new Theme(Bms);
		newTheme.buttontextcol = applet.color(a);
		theme = newTheme;
	};

	public void setTextColor(float a,float b,float c,float d) {
		if(newTheme==null) newTheme = new Theme(Bms);
		newTheme.buttontextcol = applet.color(a,b,c,d);
		theme = newTheme;
	};

	public void setTextSize( float a) {
		if(newTheme==null) newTheme = new Theme(Bms);
		newTheme.buttontextsize = applet.color(a);
		theme = newTheme;
	};

	public void setFillCol( float a) {
		if(newTheme==null) newTheme = new Theme(Bms);
		newTheme.buttonfillcol = applet.color(a);
		theme = newTheme;
	};

	public void setFillCol(float a,float b,float c,float d) {
		if(newTheme==null) newTheme = new Theme(Bms);
		newTheme.buttonfillcol = applet.color(a,b,c,d);
		theme = newTheme;
	};

	public void sethCol( float a) {
		if(newTheme==null) newTheme = new Theme(Bms);
		newTheme.buttonhcol = applet.color(a);
		theme = newTheme;
	};

	public void sethCol(float a,float b,float c,float d) {
		if(newTheme==null) newTheme = new Theme(Bms);
		newTheme.buttonhcol = applet.color(a,b,c,d);
		theme = newTheme;
	};

	public void setClassicBar() {

		classicBar = true;
		toggleBox = false;
		radio = false;
		plain = false;

	};
	
	public void setRadio() {
		plain = false;
		toggleBox = false;
		classicBar = false;
		radio = true;
	};

	public void setToggleBox() {
		plain = false;
		toggleBox = true;
		classicBar = false;
		radio = false;
	};

	public void setclassicBar() {
		plain = false;
		toggleBox = false;
		classicBar = true;
		radio = false;
	};

	public void setPlain() {
		plain = plain;
		toggleBox = false;
		classicBar = false;
		radio = false;
	};

};

