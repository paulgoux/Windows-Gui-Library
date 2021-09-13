package windowsGui;

import java.util.ArrayList;
import java.util.HashMap;

import processing.core.PApplet;
import processing.core.PGraphics;
import processing.core.PVector;

public class Window  extends MouseFunctions{
	public BMS Bms;
	public PApplet applet;
	public PVector mouse;
	public float x,y,w,h,bx,by,dx,dy,x1,y1,x2,y2;
	public float bw,bh, navheight = 50,deltay,transparency = 200,transparency1 = 50,transparency2 = 80,
			transparency3 = 100,transparency4 = 150,r1,r2,r3,r4;
	public String label,itemLabel;
	public int type = 0,index = -1,windex = -1,level = 0,wid,counter,themeIndex;
	public int main;
	public int navcol,quickNavcol,fcol,bcol,xcol,quickNavItemcol,navItemcol,buttoncol,selectcol;
	public boolean drag,resize,hover,border,backgapplet,round,hidden,fill = true,debug,navigation = true,
			update,toggle,mdown,click,ndown,quickNavVisible,firstRun = true,
			buttonHover,init;
	public Button child,currentButton;
	public Menu parent,select;

	public float bsize = 12,tsize = 12,svalue,svalue2,colwidth = 100,rowheight = 80;;

	public boolean smdown,ddown,amendslider,initB,launchable = true,rapidAccess,navtoggle = true,transparent;
	public boolean show,open,close,visible=true;
	public ArrayList<Button> buttons = new ArrayList<Button>();
	public ArrayList<Button> quickNav = new ArrayList<Button>();
	public ArrayList<Button> buttonGrid = new ArrayList<Button>();
	public ArrayList<Slider> sliders = new ArrayList<Slider>();
	public ArrayList<Window> windows = new ArrayList<Window>();
	public ArrayList<PVector> track = new ArrayList<PVector>();
	public ArrayList<String> Links = new ArrayList<String>();
	public ArrayList<Boundary> Boundaries = new ArrayList<Boundary>();

	public ArrayList<Menu> menus = new ArrayList<Menu>();
	public ArrayList<Button> nav = new ArrayList<Button>();
	public HashMap<String,Boolean> values = new HashMap<String,Boolean>();
	int id,gw = 25,gh = 20;
	public float bordersize = 1,limit;
	public int cols = 40, rows = 30;
	public boolean showq,showf;
	public int col;
	public int scol;

	//sliderBox sliders;
	String []list;
	public String link,currentp,back,forward,currentl,currentf,currentLink,
			currentFolder;
	float [] window;
	int wcol;
	public TextArea fileName,fileDir;
	public Theme theme;
	public PGraphics canvas1,canvas2;

	Window(){
		update = true;
	};



	public Window(PApplet bms){
		applet = bms;
	};

	public Window(BMS bms){
		Bms = bms;
		applet = bms.applet;
		theme = bms.theme;
	};

	public Window(float X,float Y,float WW,float HH){

		x = X;
		y = Y;
		w = WW;
		h = HH;

		x1 = x;
		y1 = y;
		x2 = x + w;
		y2 = y + h;
		fill = true;
	};

	public Window(float X,float Y,float WW,float HH,BMS bms){

		x = X;
		y = Y;
		w = WW;
		h = HH;

		x1 = x;
		y1 = y;
		x2 = x + w;
		y2 = y + h;
		Bms = bms;
		applet = bms.applet;
		theme = bms.theme;
		fill = true;
	};

	public Window(float X,float Y,float WW,float HH,Boundary b){

		x = X;
		y = Y;
		w = WW;
		h = HH;
		b.x = x;
		b.y = y;
		b.h = h;
		b.w = w;


		x1 = x;
		y1 = y;
		x2 = x + w;
		y2 = y + h;
		Bms = b.Bms;
		applet = b.applet;
		theme = Bms.theme;
		Boundaries.add(b);
		fill = true;
		//		applet.println("Window const b",b.x,b.y,b.h,b.w,applet);
	};

	public Window(float xx, float yy, float ww,float hh,String dir,BMS bms){

		x = xx;
		y = yy;
		w = 500;
		h = hh;
		bw = w;
		bh = h;
		list = fileUtils.listFileNames(dir);
		link = dir;
		currentp = dir;
		Bms = bms;
		applet = bms.applet;
		theme = Bms.theme;
		ArrayList<String> temp = new ArrayList<String>();
		ArrayList<String> temp2 = new ArrayList<String>();

		if(list!=null)
			temp = fileUtils.trimNames(list);
		list = null;
		for(int i =0;i<temp.size();i++){

			String l = temp.get(i);
			String ndir = dir + "\\" + l + "\\";
			list = fileUtils.listFileNames(ndir);
			Button a = new Button( x ,y + 20 *(i), w-10,20,l,bms);
			if(list==null){



			}else {
				temp2 = fileUtils.trimNames(list);
				if(temp2.size()>0){
					a.submenu = new Menu(a.x+20,a.y+a.h,a.w-20,temp2,bms);
					a.submenu.setBms(Bms);
					a.submenu.slide = true;
					a.submenu.visible = false;
				}
			}
			a.border = false;
			a.setToggleBox();
			a.tyoff = 50;
			buttons.add(a);

		}

		parent = new Menu( x + w - 10,y , 10,h-20,bms);
		parent.setBms(Bms);
		Slider s = new Slider( x + w - 10,y , 10,h,bms);
		s.setClassicBar();
		s.tvisible = false;
		s.vertical = true;
		s.tooltip = null;

		sliders.add(s);
		Boundary b = new Boundary(x,y-10,w,h+10,4,bms);
		Boundaries.add(b);

		for(int i =0;i<buttons.size();i++){
			Button b1 = buttons.get(i);
			Button a = new Button(0,0,40,40,b1.label,bms);
			a.textbtm = true;
			a.tyoff = 25;
			a.txoff = -10;
			a.tcol = 0;
			a.border = false;
			buttonGrid.add(a);
		}
		cols = 5;
		rows = PApplet.round(buttonGrid.size()/cols)+1;

		b = new Boundary(x,y-50,cols*colwidth,5*rowheight + navheight,4,bms);
		Boundaries.add(b);
		windows.add(this);

		Button b2 = new Button(x+w - 80,y - 40 + 10, 30,30,"Back",bms);
		Button f = new Button(x+w - 80,y - 40 + 10, 30,30,"Forward",bms);
		Button X = new Button(x + colwidth*cols - 35,y-50 + 5, 30,30,"X",bms);

		X.txoff = 7;
		b2.border = false;
		f.border = false;
		X.border = false;
		//X.textbtm = true;
		nav.add(b2);
		nav.add(f);
		nav.add(X);

		for(int i=0;i<nav.size()-1;i++){
			Button b1 = nav.get(i);
			b1.x = x + 5 + 40 * i;
			b1.y = y - 50 + 5 + dy;
			b1.tyoff = 20;
			b1.txoff = -10;
		}

		initqNav();
		//		h = sliders.get(0).h;
		if(debug)applet.println("window",this);
		canvas1 = applet.createGraphics((int)w,(int)(rowheight*10));
		canvas2 = applet.createGraphics((int)w-10,(int)(rowheight*3.5));
	};

	public Window(float xx, float yy, float ww,float hh,String dir,int n,BMS bms){

		x = xx;
		y = yy;
		w = ww;
		h = hh;
		bw = w;
		bh = h;
		Bms = bms;
		applet = bms.applet;
		theme = Bms.theme;
		list = fileUtils.listFileNames(dir);
		link = dir;
		ArrayList<String> temp = new ArrayList<String>();
		ArrayList<String> temp2 = new ArrayList<String>();


		if(list!=null)temp = fileUtils.trimNames(list);
		//h = temp.size();
		int j = 0;
		int k = PApplet.floor(temp.size()/n);
		for(int i =0;i<temp.size();i++){

			String l = temp.get(i);
			String ndir = "C:\\Users\\admin" + "\\" + l + "\\";
			list = fileUtils.listFileNames(ndir);
			if(list==null){
				Button a = new Button( x + w*j,y + 20 *(i)-k*20*(j), w-10,20,l,bms);
				a.Bms = Bms;
				a.applet = Bms.applet;
				a.submenu = new Menu(x+20,y,w-20,20);
				a.submenu.setBms(Bms);
				a.submenu.borders(false);
				if(j>0) {
					a = new Button( x + w*j,y + 20 *(i)-k*20*(j)-40, w-10,20,l,bms);
					a.Bms = Bms;
					a.applet = Bms.applet;
				}
				a.border = false;
				a.classicBar = true;
				buttons.add(a);
				if(i>k*(j+1))j++;
			}else {

				Button a = new Button( x + w*j,y + 20 *(i)-k*20*(j), w-10,20,l,bms);
				a.Bms = Bms;
				a.applet = Bms.applet;
				a.submenu = new Menu(x+20,y,w-20,list);
				a.submenu.setBms(Bms);
				a.submenu.visible = false;
				a.submenu.slide = true;
				a.submenu.borders(false);
				if(j>0) a = new Button( x + w*j,y + 20 *(i)-k*20*(j)-40, w-10,20,l,bms);
				a.border = false;
				a.classicBar = true;
				buttons.add(a);
				if(i>k*(j+1))j++;
			}}
		//h = temp.size()*20;
		parent = new Menu( x + w*n - 10,y, 10,h-20,bms);
		parent.setBms(Bms);
		Slider s = new Slider( x + w*n - 10,y, 10,h,bms);
		s.setClassicBar();
		s.tvisible = false;
		s.vertical = true;
		s.tooltip = null;

		sliders.add(s);
		w = w*n;
		type = 1;
		Boundary b = new Boundary(x,y-10,w,h+20,4,bms);
		//b.id = -1;
		Boundaries.add(b);

		for(int i =0;i<buttons.size();i++){
			Button b1 = buttons.get(i);
			Button a = new Button(0,0,40,40,b1.label,bms);
			s.Bms = Bms;
			s.applet = Bms.applet;
			a.tcol = 0;
			a.textbtm = true;
			a.border = false;
			buttonGrid.add(a);
		}
		cols = 5;
		rows = PApplet.round(buttonGrid.size()/cols);
		windows.add(this);

		Button b2 = new Button(x+w - 80,y - 40 + 10, 30,30,"Back",bms);
		Button f = new Button(x+w - 80,y - 40 + 10, 30,30,"Forward",bms);
		Button X = new Button(x + colwidth*cols - 35,y-50 + 5, 30,30,"X",bms);
		b2.border = false;
		f.border = false;
		X.border = false;
		nav.add(b2);
		nav.add(f);
		nav.add(X);

		for(int i=0;i<nav.size()-1;i++){
			Button b1 = nav.get(i);
			b1.x = x + 5 + 40 * i;
			b1.y = y - 50 + 5 + dy;
			b1.tyoff = 25;
			b1.txoff = -10;
		}

		initqNav();
		//		h = sliders.get(0).h;
	};

	public void initNav(){

	};

	public void initqNav(){
		quickNavVisible = true;
		canvas1 = applet.createGraphics((int)w,(int)(rowheight*10));
		canvas2 = applet.createGraphics((int)w-10,(int)(rowheight*3.5));
		Button n = new Button(x - 35,y,25,25,"My Drive",Bms);
		n.tyoff = 20;
		n.txoff = -10;
		quickNav.add(n);
		n = new Button(x - 35,y,25,25,"Data",Bms);
		n.tyoff = 20;
		n.txoff = -10;
		n.textbtm = true;
		n.border = false;
		n.tcol = 0;
		quickNav.add(n);
		n = new Button(x - 35,y + 50,25,25,"Documents",Bms);
		n.tyoff = 20;
		n.txoff = -10;
		n.textbtm = true;
		n.border = false;
		n.tcol = 0;
		quickNav.add(n);
		n = new Button(x - 35,y + 100,25,25,"Downloads",Bms);
		n.tyoff = 20;
		n.txoff = -10;
		n.textbtm = true;
		n.border = false;
		n.tcol = 0;
		quickNav.add(n);
		fileName = new TextArea(0,0,370,25,"File name...",Bms);
//		fileName.setWindow(this);
		fileDir = new TextArea(0,0,370,20,Bms);
//		fileDir.setWindow(this);
		String [] s1 = {"Open","Cancel"};
		select = new Menu(0,0,90,20,10,s1,Bms);
	};

	@Override 
	public void mousePressed() {
		//		if(!ndown)
		//		if(pos()) {
		//			mdown = true;
		//			if(!mousePressed)click = true;
		//			else click = false;
		//			mouseClicked = true;
		//			//else click = false;
		//			mousePressed = true;
		//			mouseReleased = false;
		//			mup = false;
		//		}
		//		if(!mdown||ddown)
		//		if(navPos()) {
		//			ndown = true;
		//			if(!mousePressed)click = true;
		//			else click = false;
		//			mouseClicked = true;
		//			//else click = false;
		//			mousePressed = true;
		//			mouseReleased = false;
		//			mup = false;
		//		}
		//		if(dpos()) {
		//			ddown = true;
		//		}
	};

	@Override 
	public void mouseReleased() {

		if(mousePressed)mouseReleased = true;
		else mouseReleased = false;
		ddown = false;
		ndown = false;
		mdown = false;
		mousePressed = false;
		click = false;
		mouseClicked = false;
		drag = false;
		mouseDragged = false;
	};


	public void sLogic(){

		if(applet.mousePressed&&pos()){
			if(Bms.currentMouseObject==null)Bms.currentMouseObject = this.itemLabel;
		}

	};

	public void boundingbox(){
		applet.noStroke();
		applet.fill(theme.tabcol,theme.windowtransparency);
		applet.rect(x,y,w,h,r1,r2,r3,r4);

		applet.fill(theme.windowfillcol,theme.windowtransparency);
		applet.rect(x,y-10,w,10);
	};

	public void menu(){

		if(sliders.size()>0){
			Slider s = sliders.get(0);

			//s.value = map(s.valuex,0,s.h,0,buttons.get(index).submenu.items.size()*20);

			for(int i=0;i<buttons.size();i++){

				Button b = buttons.get(i);

				if(b.y>y+h)b.visible = false;
				else b.visible = true;

				if(index<0)svalue = s.value;
				if(index>=i||index==-1)b.y = b.by - s.valuex;
				else if(index>=0&&i>index)b.y = b.by - s.valuex + buttons.get(index).submenu.items.size()*20;

				b.x = x;
				b.by = y+10 + b.h*i;
				if(b.y+b.h<=y+h&&b.y>=y){
					if(b.pos())b.toggle();
					b.draw();
					applet.fill(255);
					applet.textSize(theme.tsize);
					if(b.submenu!=null)applet.text(">",b.x+b.w-10,b.y+20);
					if(b.submenu!=null&&b.subpos()&&applet.mousePressed&&!smdown){
						smdown = true;
						amendslider = false;
						index = i;
					}
					if(applet.mousePressed&&!b.pos())b.toggle=false;
					b.highlight();
				}
			}
		}
	};

	public void submenu(){
		if(index>-1){
			Button a = buttons.get(index);
			Menu m = a.submenu;
			//w = bw + 20;
			m.y = a.y+a.h + (dy);

			for(int j=0;j<m.items.size();j++){
				Button b = m.items.get(j);
				b.x = x + 20;
				if(b.y+b.h>y+h||b.y<y){
					b.visible=false;
					float dy2 = PApplet.abs(y - by);

					applet.noStroke();
					if(b.border)applet.strokeWeight(1);
					applet.fill(0,150);
					applet.rect(b.x,y,b.w,dy2,r1,r2,r3,r4);
				}
				//b.draw();
				else b.visible = true;

			}
			m.draw();
		}
	};

	public void scrollbar(){
		if(sliders.size()>0){
			Slider s = sliders.get(0);

			s.x = x+w-10;
			s.y = y;
			s.draw();
			//			s.mouseFunctions();
			s = sliders.get(0);
			s.r1 = 0;
			s.r2 = 0;
			s.r3 = 0;
			s.r4 = 0;

		}
	};

	public void scrollbar(PGraphics canvas){
		if(sliders.size()>0){
			Slider s = sliders.get(0);

			s.x = x+w-10;
			s.y = y;
			//s.draw(mouse);
			//s.mouseFunctions(mouse);
		}
	};

	public void wlogic(){

		float my = applet.mouseY;
		ArrayList<Float> temp = new ArrayList<Float>();
		float dy = 0;

		if(sliders.size()>0){
			Slider s = sliders.get(0);
			if(type==0)s.set(0,buttons.size()-h/20);
			else s.set(0,buttons.size()/3-h/20);
			if(index>-1&&buttons.get(index).pos()&&applet.mousePressed&&!smdown){index=-1;smdown = true;}
			if(pos()&&!s.mdown&&applet.mousePressed)smdown = true;
			if(!applet.mousePressed){smdown = false;ddown=false;}
			if(!pos()&&applet.mousePressed&&!s.mdown){index = -1;smdown = false;}
			if(dpos()&&applet.mousePressed&&!smdown&&!s.mdown&&Bms.getObject()){
				ddown = true;
				Bms.setObject(this);
			}

			if(ddown){
				update = true;
				Boundary b = Boundaries.get(0);
				b.mtranslate(b,new PVector(applet.mouseX,applet.mouseY));

				x = b.Boundaries.get(0).x1;
				y = b.Boundaries.get(0).y1+10;

			}

			if(index>-1&&!amendslider){
				if(!amendslider&&!s.pos()&&!applet.mousePressed){
					s.value = (index)*20;
					s.valuex = applet.map(index,0,buttons.size(),0,h);
				}

				if(s.pos()&&applet.mousePressed){
					temp.add(my);
					amendslider = true;
					dy = applet.mouseY - temp.get(0);

				}}
		}
	};

	public void displayGrid(){
		//		
		h = (7+1) * 50;
		Slider s = null;
		if(sliders.get(0)!=null)s = sliders.get(0);
		s.h = h;
		if(toggle||applet.frameCount==0) {
			open = true;
		}else {
			open = false;
		}
		if(close){
			toggle = false;
			open = false;
			close = false;
		}

		if(open||firstRun||buttonHover) {
			canvas1.beginDraw();
			//			if(firstRun)
			canvas1.background(255,0);
			canvas1.fill(theme.windowfillcol,theme.windowtransparency/40);
			canvas1.noStroke();
			//			if(firstRun)
			if(firstRun||buttonHover)canvas1.rect(0,0,w,h,r1,r2,r3,r4);
			//				applet.println("hhhh");
		}

		if(open){

			//			if(firstRun)applet.println("window display frun");
			Window w2 = windows.get(windows.size()-1);
			mouse = getMouse();
			float ay = 70;
			if(w2!=null&&!fileDir.toggle) {
				fileDir.label = w2.link;
			}
			//else fileDir.textBox = "";
			select.setPos(x+w-100,y+h-ay);
			fileName.setPos(x+20,y+h-ay);
			fileDir.setPos(x+90,y - 45);
			drawBorder();
			drawNav();
//			if(getClose())close = true;
			//id();
			drawRapidOptions();
			select.draw();
			select.toggleAll();
			select.toggle(0,this,"close");
			select.toggle(1,this,"close");
			drawScrollbar();
			gridlogic();
			grid();
			//canvas1.beginDraw();
			//				fileName.mouse = mouse;
			////				fileName.parentCanvas = canvas1;
			fileName.draw();
			////				fileDir.parentCanvas = canvas1;
			//				fileDir.mouse = mouse;
			fileDir.draw();

			if(open||firstRun||buttonHover) {
				canvas1.endDraw();
				update = false;
				canvas2.beginDraw();
				canvas2.background(255,0);
				//			canvas1.fill(theme.windowfillcol,theme.windowtransparency/40);
				//			canvas1.rect(0,0,w,h);

				if(true) {
					canvas2.image(canvas1,0,0-(int)s.value);
					firstRun = false;
				}
				canvas2.endDraw();
			}
		}
		
		if(open)applet.image(canvas2,x,y);
	};

	public void drawRapidOptions(){
		applet.noStroke();

		if(rapidAccess&&navtoggle){

			for(int i=0;i<quickNav.size();i++){
				Button b = quickNav.get(i);
				b.x = x - 70;
				b.y = y + 20 + 50 * i;
				//fill(0);
				b.draw();
				//b.setLabelOff();
				applet.fill(0);
				//	        applet.text(b.label,b.x-20,b.h+b.y+20);
				b.highlight();
			}
		}
	};

	public void drawScrollbar(){

		Slider s = sliders.get(0);

		s.setPos(x+cols*colwidth-10, y);
		s.set(0,rowheight*8);
		s.draw();
		s.mouseFunctions();
	};

	public void gridlogic(){

		ArrayList<Float> temp = new ArrayList<Float>();
		mdown = theme.click&&pos();
		if(theme.click&&dpos()&&Bms.getObject()) {
			Bms.setObject(this);
			ddown = true;
			dx = applet.mouseX - x;
			dy = applet.mouseY - y;
		}

		//		if(theme.mouseReleased)ddown = false;
		ndown = theme.click&&navPos();

		float my = applet.mouseY;
		Button X = nav.get(2);
		//		if(pos()&&applet.mousePressed&&!dposg()&&!ddown)smdown = true;
		//		if(nav.get(1).pos()||sliders.get(0).mdown
		//				||smdown||X.pos())ddown = false;
		//
		//		if(sliders.size()>0&&sliders.get(0)!=null){
		//			if(!ddown&&mdown&&!dposg()
		//					&&!sliders.get(0).pos()&&mdown&&!ddown
		//					&&!sliders.get(0).mdown){
		//				if(track.size()<2)track.add(new PVector(applet.mouseX,applet.mouseY));
		//				
		//			}}
		//		else {
		//			if(applet.mousePressed&&!dposg()
		//					&&!sliders.get(0).pos()&&!mdown&&!ddown){
		//				if(track.size()<2)track.add(new PVector(applet.mouseX,applet.mouseY));
		//				mdown = true;
		//
		//			}}
		//
		//

		//
		//		//text(track.size(),100,100);
		//		if(track.size()>0){
		//			//noFill();
		//			applet.fill(0,20);
		//			applet.stroke(0,50);
		//			applet.strokeWeight(1);
		//			applet.rect(track.get(0).x,track.get(0).y,(applet.mouseX - track.get(0).x ),(applet.mouseY - track.get(0).y));
		//			applet.noStroke();
		//		}

		Boundary b = Boundaries.get(1);
		//	    
		//if(!ddown&&!sliders.get(0).mdown)
		if(X.latch(this,"close")){
//			applet.println("wnd close hh",close);
		};

		if(ddown){
			x = applet.mouseX - dx;
			y = applet.mouseY - dy;
			update = true;
			//			b.mtranslate(new PVector(applet.mouseX,applet.mouseY));
			//			x = b.Boundaries.get(0).x1;
			//			y = b.Boundaries.get(0).y1+50;

			X.y = y-50 + 5 ;
			X.x = x + colwidth*cols - 35;
			//
			for(int i=0;i<nav.size()-1;i++){
				Button b1 = nav.get(i);

				b1.x = x + 5 + 40 * i;
				b1.y = y - 50 + 5 ;
			}
		}else{
			b.mdown = false;
		}

		if(theme.mouseReleased){
			smdown = false;
			ddown = false;
			mdown = false;
			track = new ArrayList<PVector>();
			ddown = false;

		}
		if(!applet.mousePressed&&
				Bms.getObject(this)) {

			Bms.clearObject(this);
		}

		if(sliders.get(0).pos()&&applet.mousePressed){
			temp.add((float) (applet.mouseY));
			deltay = applet.mouseY - temp.get(0);
		}

		rows = PApplet.round(windows.get(windows.size()-1).buttons.size()/5)+1;
	};



	public void drawBorder(){
		applet.stroke(0);
		if(!border)applet.noStroke();
		if(rapidAccess&&navtoggle){

			applet.fill(theme.windowfillcol,theme.windowtransparency);
			applet.rect(x - 80,y-navheight,colwidth*cols+80,h+navheight,r1,r2,r3,r4);

		}
		else if(navtoggle){
			applet.fill(theme.windowfillcol,theme.windowtransparency);
			applet.rect(x,y-navheight,colwidth*cols,h+navheight,r1,r2,r3,r4);
		}
		else if(rapidAccess) {
			applet.fill(theme.windowfillcol,theme.windowtransparency);
			applet.rect(x-80,y,colwidth*cols+80,h,r1,r2,r3,r4);
		}
		applet.fill(theme.windowfillcol,theme.windowtransparency);
		applet.rect(x,y,colwidth*cols,h,r1,r2,r3,r4);
	};

	public void drawNav(){

		for(int i=0;i<nav.size();i++){
			Button b = nav.get(i);

			b.textbtm = true;

			b.draw();
			b.highlight();

			if(b.pos()&&applet.mousePressed&&b.label=="Back"&&!smdown&&track.size()<1){
				smdown = true;
				if(windows.size()>1){
					windows.remove(windows.size()-1);
					currentp = windows.get(windows.size()-1).link;
					sliders.get(0).value = 0;sliders.get(0).valuey = 0;
					if(fileDir!=null) {
						fileDir.reset();
					}
				}
			}
			if(b.pos()&&applet.mousePressed&&b.label=="Forward"&&!smdown&&forward!=null&&track.size()<1){
				smdown = true;
				Window w1 = new Window(x,y,w,h,forward,Bms);
				windows.add(w1);
				currentp = windows.get(windows.size()-1).link;
				if(fileDir!=null) {
					fileDir.reset();
				}
			}
		}
		if(!applet.mousePressed)smdown = false;
	};

	public void grid(){
		Window w2 = windows.get(windows.size()-1);
		Slider s = sliders.get(0);
		//		s.mouseFunctions();

		//		s.set(0,2000);
		mouse = getMouse();
		counter = 0;
		if(true) {
			for(int i=0;i<rows;i++){
				for(int j=0;j<cols;j++){

					int pos = j+i*cols;
					Button b = null;
					if(pos<w2.buttonGrid.size()){
						b = w2.buttonGrid.get(pos);
						Button b1 = w2.buttons.get(pos);
						b.textbtm = true;
						b.mouse = mouse;
						b.yoff = -20;
						b.parentCanvas = true;
						b.setclassicBar();
						if(!b.textcheck)b.labelcheck(colwidth);
						if(!w2.init)b.setPos( 10 + colwidth * j,
								10 + (rowheight+b.tsize) * i);
						b.visible = true;
						
						boolean k = b.pos(mouse);
//						if(!b.set||k||buttonHover)
							b.draw(canvas1);
						b.set = true;
						if(k&&b.submenu!=null&&track.size()<1){
							int size = currentp.length();
							if(currentp.charAt(size-1)!='\\')currentp += "\\";
							currentl = currentp + b.blabel;
						}
						else if(k&&b.submenu==null){
							int size = currentp.length();
							if(currentp.charAt(size-1)!='\\')currentp += "\\";
							currentf = currentp + b.blabel;
						}
						if(k&&theme.dclick&&b1.submenu!=null&&!smdown&&track.size()<1){
							int size = currentp.length();
							if(currentp.charAt(size-1)!='\\')currentp += "\\";
							back = currentp;
							currentp += b.blabel;
							forward = currentp;
							Window w1 = new Window(w2.x+200,w2.y,w,h,currentp,Bms);
							windows.add(w1);
							windex = 0;
							s.value = 0;
							s.valuex = 0;
							wid = windows.size()-1;
							smdown = true;
							currentFolder = currentf;
						}else if(k&&theme.dclick&&b1.submenu==null&&!smdown&&track.size()<1){
							int size = currentp.length();
							if(currentp.charAt(size-1)!='\\')currentp += "\\";
							String file = currentp + b.blabel;
							currentf = file;
							currentLink = currentf;
							//if(launchable)launch(file);
							smdown = true;
						}
						if(k)counter++;
						//						rect
					}}}
			w2.init = true;
		}
		buttonHover = counter>0;
		if(!applet.mousePressed)smdown = false;
	};

	public void navbar(){

	};


	public void setRadius(float a){
		r1 = a;
		r2 = a;
		r3 = a;
		r4 = a;
		for(int i=0;i<buttons.size();i++){
			Button b = buttons.get(i);
			b.r1 = a;
			b.r2 = a;
			b.r3 = a;
			b.r4 = a;
		}

		for(int i=0;i<buttonGrid.size();i++){
			Button b = buttonGrid.get(i);
			b.r1 = a;
			b.r2 = a;
			b.r3 = a;
			b.r4 = a;
		}

		for(int i=0;i<quickNav.size();i++){
			Button b = quickNav.get(i);
			b.r1 = a;
			b.r2 = a;
			b.r3 = a;
			b.r4 = a;
		}

		for(int i=0;i<nav.size();i++){
			Button b = nav.get(i);
			b.r1 = a;
			b.r2 = a;
			b.r3 = a;
			b.r4 = a;
		}

		for(int i=0;i<sliders.size();i++){
			Slider b = sliders.get(i);
			b.r1 = a;
			b.r2 = a;
			b.r3 = a;
			b.r4 = a;
		}

		select.setRadius(a);
		fileName.setRadius(a);
		fileDir.setRadius(a);
	};

	public void setRadius(float a,float b,float c,float d){
		r1 = a;
		r2 = b;
		r3 = c;
		r4 = d;
		for(int i=0;i<buttons.size();i++){
			Button b1 = buttons.get(i);
			b1.r1 = a;
			b1.r2 = b;
			b1.r3 = c;
			b1.r4 = d;
		}

		for(int i=0;i<buttonGrid.size();i++){
			Button b1 = buttonGrid.get(i);
			b1.r1 = a;
			b1.r2 = b;
			b1.r3 = c;
			b1.r4 = d;
		}

		for(int i=0;i<quickNav.size();i++){
			Button b1 = quickNav.get(i);
			b1.r1 = a;
			b1.r2 = b;
			b1.r3 = c;
			b1.r4 = d;
		}

		for(int i=0;i<nav.size();i++){
			Button b1 = nav.get(i);
			b1.r1 = a;
			b1.r2 = b;
			b1.r3 = c;
			b1.r4 = d;
		}

		for(int i=0;i<sliders.size();i++){
			Slider b1 = sliders.get(i);
			b1.r1 = a;
			b1.r2 = b;
			b1.r3 = c;
			b1.r4 = d;
		}

		select.setRadius(a,b,c,d);
		fileName.setRadius(a,b,c,d);
		fileDir.setRadius(a,b,c,d);
	};

	boolean navPos(){
		boolean k = false;
		if(nav!=null){
			for(int i=0;i<nav.size();i++){
				if(nav.get(i).pos()){
					k = true;
					break;
				}
			}
		}
		return k;
	};

	boolean dpos(){
		return applet.mouseX>x&&applet.mouseX<x+w&&applet.mouseY>y-50&&applet.mouseY<y;

	};

	boolean dposg(){
		return applet.mouseX>x&&applet.mouseX<x+cols*colwidth&&applet.mouseY>y-50&&applet.mouseY<y;

	};

	public boolean pos(){

		return applet.mouseX>x&&applet.mouseX<x+w&&applet.mouseY>y&&applet.mouseY<y+h;

	};

	public void setBorder(Boolean b) {
		border = b;
	};

	void windowLogic() {
		//		if(!mdown&&!ddown&&!sliders.get(0).mdown)
		select.getButton(0).click(this,"close");
		//		if(!mdown&&!ddown&&!sliders.get(0).mdown)
		select.getButton(1).click(this,"close");
	};

	void windowLogic(PVector m) {
		//		if(!mdown&&!ddown&&!sliders.get(0).mdown)
		select.getButton(0).click(this,"close");
		//		if(!mdown&&!ddown&&!sliders.get(0).mdown)
		select.getButton(1).click(this,"close");
	};

	public PVector getMouse() {
		float a = 0;
		if(sliders.get(0)!=null)a = sliders.get(0).value;
		return new PVector(applet.mouseX-x,applet.mouseY-y+a);
	};

	public PVector getMouse2() {
		return new PVector(applet.mouseX,applet.mouseY);
	};


	public Button getOpen() {
		return select.getButton(0);
	};
	
	public boolean getClose() {
		return nav.get(2).pos()&&theme.mouseReleased||close;
	};
	
	public Button getCancel() {
		return select.getButton(1);
	};
	
	public void reset() {
		currentf = null;
		link = null;
		forward = null;
		back = null;
		open = false;
		toggle = false;
		close = false;
		
	};
};
