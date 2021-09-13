package windowsGui;

import processing.core.PApplet;
import processing.core.PGraphics;
import processing.core.PVector;

public class TextArea extends KeyboardFunctions{
	public BMS Bms;
	public PApplet applet;
	float x,y,w,h,bx,by,textSize = 12,spacing = 2,txoff,tyoff,lxpos,r1,r2,r3,r4,value;
	public int last,lines,xpos,ypos,themeIndex,arrayIndex;
	public String [] textArea;
	public String text = "",blankLine="",tempLine = "",label = "";
	public boolean update,blink,visible = true,vScroll,hScroll,tabBound;
	public boolean mdown,toggle,border,bgFill,localTheme,setMouse,isNumber,box
	,line;
	public char currentKey;
	public int col;
	public PVector mouse;
	public PGraphics canvas,parentCanvas;
	public tab parentTab;
	Theme myTheme;
	Window parentWindow;
	Slider parentSlider;

	public TextArea(float x_,float y_,float w_,float h_,BMS bms){
		Bms = bms;
		applet = bms.applet;
		myTheme = bms.theme;
		register(this);
		//		init(applet);
		x = x_;
		bx = x;
		y = y_;
		by = y;
		w = w_;
		h = h_;

		//applet.textSize(textSize);
		canvas = applet.createGraphics((int) w+1000,(int) h+1000);
		//while(applet.textWidth(blankLine)<w-applet.textWidth("")


	};

	public TextArea(float x_,float y_,float w_,float h_,String Label,BMS bms){
		Bms = bms;
		applet = bms.applet;
		myTheme = bms.theme;
		register(this);
		//		init(applet);
		label = Label;
		x = x_;
		bx = x;
		y = y_;
		by = y;
		w = w_;
		h = h_;

		canvas = applet.createGraphics((int) w,(int) h);
		//applet.textSize(textSize);
		//while(applet.textWidth(blankLine)<w-applet.textWidth("")

	};

	public TextArea(float x_,float y_,float w_,float h_,String Label,fileOutput f){
		Bms = f.Bms;
		applet = Bms.applet;
		myTheme = Bms.theme;
		register(this);
		//		init(applet);
		label = Label;
		x = x_;
		bx = x;
		y = y_;
		by = y;
		w = w_;
		h = h_;

		canvas = applet.createGraphics((int) w,(int) h);
		//applet.textSize(textSize);
		//while(applet.textWidth(blankLine)<w-applet.textWidth("")

	};

	public void draw(){
		col = myTheme.textareafillcol;
		//		if(pos())col = myTheme.textareahcol;
		boolean k = parentSlider==null&&Bms.getObject()||parentSlider!=null&&Bms.getObject(parentSlider);
		textSize = myTheme.textareatextsize;
		setMouse();
		if(pos())
			col = applet.color(0);
		//		getCursor();
		//if(mouse)
//		applet.println("tearea draw 00");
		if(parentTab!=null)draw(parentCanvas,true);
		else draw(true);

	};

	@Override
	public void keyPressed() {
		getKey();
	};

	public void draw(boolean k){
		logic(true);
		getCursor(true);
		if(textSize>h-textSize)textSize = h;
		//getKey();

		if(canvas!=null){
			canvas.beginDraw();
			canvas.background(0,0);

			canvas.stroke(myTheme.textareastrokecol,myTheme.textareatransparency);
			canvas.strokeWeight(myTheme.textareastrokesize);
			if(!myTheme.textareaborder)canvas.noStroke();

			canvas.fill(col,myTheme.textareatransparency);
			if(!myTheme.textareafill)canvas.noFill();
			if(!line)
				canvas.rect(0,0,w,h,r1,r2,r3,r4);
			else {
				canvas.stroke(myTheme.textareafillcol,myTheme.textareatransparency);
				canvas.strokeWeight(myTheme.textareastrokesize);
				if(pos())canvas.stroke(myTheme.textareahcol,myTheme.textareatransparency);
				canvas.line(0,0+h-2,w,h-2);
			}

			canvas.stroke(0);
			canvas.strokeWeight(2);
			if(label!=null){
				canvas.fill(myTheme.textareatextcol);
				canvas.text(label,0,0+(textSize)-3);
				//println(label);
			}
			float ry = textSize;

			canvas.fill(myTheme.textareatextcol);
			applet.textSize(textSize);
			lxpos = 0;
//			canvas.stroke(9);
//			canvas.strokeWeight(29);
			if(textArea!=null&&xpos>-1&&ypos>-1&&ypos<textArea.length
					&&xpos<=textArea[ypos].length())
				lxpos = applet.textWidth(textArea[ypos].substring(0,xpos));
			if(applet.frameCount%60==0)blink = false;
			else if(applet.frameCount%30==0)blink = true;
			if(blink&&toggle)canvas.line(lxpos,0+ypos*(textSize+spacing),0+lxpos,0+ypos*(textSize+spacing)+textSize);

			if(textArea!=null){

				canvas.fill(0,0,255,50);

				canvas.stroke(myTheme.textareastrokecol,myTheme.textareatransparency);
				//				canvas.strokeWeight(myTheme.textareastrokesize);
				canvas.noStroke();
				if(!myTheme.textareaborder)applet.noStroke();

				for(int i=0;i<textArea.length;i++){

					//canvas.rect(0,0+(textSize+spacing)*i,w,ry);
				}

				canvas.fill(myTheme.textareatextcol);
				canvas.textSize(ry);
				if(textSize==h)tyoff = -3;
				for(int i=0;i<textArea.length;i++){
					String s = textArea[i];

					canvas.text(s,0+txoff,0+(textSize+spacing)*i+textSize+tyoff);
				}
			}
			canvas.endDraw();
			applet.image(canvas,x,y);
			//println(canvas.width);
		}
	};

	public void draw(PGraphics scene,boolean b1){

		if(scene !=null){

			logic(true);
			getCursor(true);
			if(pos())applet.fill(255);
			if(textSize>h-textSize)textSize = h;
			applet.textSize(textSize);
			if(canvas!=null&&visible){
				canvas.beginDraw();
				canvas.background(0,0);

				canvas.stroke(myTheme.textareastrokecol,myTheme.textareatransparency);
				canvas.strokeWeight(myTheme.textareastrokesize);
				if(!myTheme.textareaborder)canvas.noStroke();

				canvas.fill(col,myTheme.textareatransparency);
				if(!myTheme.textareafill)canvas.noFill();

				if(!line)
					canvas.rect(0,0,w,h,r1,r2,r3,r4);
				else {
					canvas.stroke(myTheme.textareafillcol,myTheme.textareatransparency);
					canvas.strokeWeight(myTheme.textareastrokesize);
					if(pos())canvas.stroke(myTheme.textareahcol,myTheme.textareatransparency);
					canvas.line(0,h-1,w,h-1);
				}



				canvas.stroke(myTheme.textareastrokecol,myTheme.textareatransparency);
				canvas.strokeWeight(2);
				float ry = textSize;
				applet.textSize(textSize);
				if(label!=null){

					canvas.fill(0);
					canvas.fill(myTheme.textareatextcol);
					canvas.text(label,0,0+(textSize)-3);
				}
				canvas.fill(myTheme.textareatextcol);
				//				canvas.textSize(h);
				//				applet.textSize(h);
				//lxpos = 0;
				if(textArea!=null&&xpos>-1&&ypos>-1&&ypos<textArea.length
						&&xpos<=textArea[ypos].length())
					lxpos = applet.textWidth(textArea[ypos].substring(0,xpos));
				if(applet.frameCount%30==0&&blink)blink = false;
				else if(applet.frameCount%30==0&&!blink)blink = true;
				if(blink&&toggle)canvas.line(lxpos,0+ypos*(textSize+spacing),0+lxpos,0+ypos*(textSize+spacing)+textSize);

				if(textArea!=null){
					canvas.fill(0,0,255);
					canvas.noStroke();
					//										if(!line)
//					for(int i=0;i<textArea.length;i++){
//
//						if(textArea.length>1) {
//							if(i==0)
//								canvas.rect(x,y+(textSize+spacing)*i,w,ry,r1,r2,0,0);
//							else if(i==textArea.length-1)
//								canvas.rect(0,0+(textSize+spacing)*i,w,ry,0,0,r3,r4);
//							else 
//								canvas.rect(0,0+(textSize+spacing)*i,w,ry);
//						}else canvas.rect(0,0+(textSize+spacing)*i,w,ry,r1,r2,r3,r4);
//					}
//					canvas.stroke(myTheme.textareafillcol,myTheme.textareatransparency);
//					canvas.strokeWeight(myTheme.textareastrokesize);
//					
//					canvas.line(x,y+h-2,w,2);
					canvas.fill(myTheme.textareatextcol);
					canvas.textSize(myTheme.textareatextsize);
					canvas.textSize(ry);
					if(textSize==h)tyoff = -3;
					for(int i=0;i<textArea.length;i++){
						String s = textArea[i];

						canvas.text(s,0+txoff,0+(textSize+spacing)*i+textSize+tyoff);
					}
				}

				canvas.endDraw();
				//scene.beginDraw();
				scene.image(canvas,x,y);
				//scene.endDraw();
			}
		}
	};

	public void update(){
		if(update){
			if(ypos==last){

			}else{
				int l = text.length()-1;
				textArea[last] = textArea[last]+text.charAt(l);
			}

			update = false;
		}
	};

	public void keyLogic(){

	};

	public void logic(){
		if(textArea ==null){
			textArea = new String[1];
			textArea[0] = "";

		}
		if(textArea!=null&&textArea.length>0){
			lines = textArea.length-1;
			last = textArea[lines].length()-1;
		}

		if(pos()&&applet.mousePressed)toggle = true;
		if(!pos()&&applet.mousePressed)toggle = false;

		if(vScroll){

		}
		if(hScroll){

		}
		//if(!pos&&applet.mousePressed)toggle=false;
		//if(
	};

	public void logic(boolean b1){
		if(textArea ==null){
			textArea = new String[1];
			textArea[0] = "";

		}
		if(label!=null&&toggle)label = null;

		//if(pos(mouse))applet.println(mouse.x,mouse.y);
		if(pos(mouse)&&applet.mousePressed)toggle = true;
		if(!pos(mouse)&&applet.mousePressed)toggle = false;

		if(vScroll){

		}
		if(hScroll){

		}
		//if(!pos&&applet.mousePressed)toggle=false;
		//if(
	};

	public void logic(PVector mouse){
		if(mouse!=null){
			if(textArea ==null){
				textArea = new String[1];
				textArea[0] = "";

			}
			if(textArea!=null&&textArea.length>0){
				lines = textArea.length-1;
				last = textArea[lines].length()-1;
			}

			if(pos(mouse)&&applet.mousePressed)toggle = true;
			if(pos(mouse)&&!applet.mousePressed)toggle = false;

			if(vScroll){

			}
			if(hScroll){

			}
		}
		//if(!pos&&applet.mousePressed)toggle=false;
		//if(
	};

	public void getKey(){
		boolean b1 = ypos*(textSize+spacing)<h-(textSize+spacing)*2;
		boolean b2 = lxpos<w;
		if(applet.key!=applet.CODED&&applet.key!=applet.BACKSPACE&&applet.key!=applet.ENTER){
			String s = "";
			s+= applet.key;
			b2 = lxpos<w - applet.textWidth(s);
		}
		if(b2&&!b1&&applet.key!=applet.ENTER)b1 = true;
		if(toggle&&b1&&b2){
			applet.textSize(textSize);
			if(applet.key!=applet.CODED&&applet.key!=applet.BACKSPACE&&applet.key!=applet.ENTER){
				currentKey = applet.key;


				if(textArea==null){

					textArea = new String[1];
					textArea[0] = text;
					xpos = text.length();
				}else{
					if(ypos<textArea.length&&ypos>-1){
						if(xpos==tempLine.length()){
							tempLine += currentKey;

							textArea[ypos] = tempLine;
							xpos = textArea[ypos].length();
							setText(textArea);
						}else if(xpos<tempLine.length()){
							String s1 = tempLine.substring(0,xpos);
							String s2 = "";
							if(xpos>2)textArea[ypos].substring(xpos-2,textArea[ypos].length()-1);
							else if(textArea[ypos].length()>0)
								textArea[ypos].substring(0,textArea[ypos].length()-1);

							tempLine = s1+currentKey;

							for(int i=xpos;i<textArea[ypos].length();i++){
								char a = textArea[ypos].charAt(i);
								tempLine+= a;
							}
							textArea[ypos] = tempLine;
							xpos =s1.length()+1;
							setText(textArea);
						}

					}
				}

			}
			else if(applet.key==applet.BACKSPACE){
				backSpace();
			}else if(applet.key==applet.ENTER){
				enter();
			}
			arrowKeys();
		}
	};

	public void backSpace(){
		if(textArea!=null){
			if(textArea.length==1||ypos==0){
				if(xpos==textArea[0].length()){
					if(xpos>0){
						tempLine = textArea[0].substring(0,textArea[0].length()-1);
						xpos = tempLine.length();
						textArea[0] = tempLine;
					}

				}else if(xpos>0){
					tempLine = textArea[0].substring(0,xpos-1);
					xpos = tempLine.length();
					for(int i=xpos+1;i<textArea[0].length();i++){
						tempLine+= textArea[0].charAt(i);
					}
					textArea[0] = tempLine;
				}
			}else{

				if(xpos==textArea[ypos].length()||xpos==0){

					if(xpos>0){

						tempLine = textArea[ypos].substring(0,textArea[ypos].length()-1);
						xpos = tempLine.length();
						textArea[ypos] = tempLine;
					}else{
						String s2 = textArea[ypos].substring(xpos,textArea[ypos].length());
						String[] temp = new String[textArea.length-1];
						//temp[ypos-1] = textArea[ypos-1];
						for(int i=0;i<ypos;i++){
							temp[i] = textArea[i];
						}
						temp[ypos-1]+= s2;
						for(int i=ypos+1;i<textArea.length;i++){
							temp[i-1] = textArea[i];
						}
						//
						tempLine = textArea[ypos];
						xpos = textArea[ypos-1].length();
						tempLine += s2;
						for(int i=0;i<textArea[ypos].length();i++){
							char a = textArea[ypos].charAt(i);
							tempLine += a;
						}
						textArea = temp;
						ypos --;
					}

				}else{
					if(xpos>0){
						tempLine = textArea[ypos].substring(0,xpos-1);
						xpos = tempLine.length();
						for(int i=xpos+1;i<textArea[ypos].length();i++){
							tempLine+= textArea[ypos].charAt(i);
						}
						textArea[ypos] = tempLine;
					}else{
						tempLine = textArea[ypos-1].substring(0,textArea[ypos-1].length());
						//xpos = tempLine.length();
						for(int i=xpos+1;i<textArea[ypos].length();i++){
							tempLine+= textArea[ypos].charAt(i);
						}
						textArea[ypos] = tempLine;
					}
				}
			}
		}
	};

	public void enter(){
		if(ypos ==textArea.length-1){
			if(xpos==tempLine.length()){
				String []temp = new String[textArea.length+1];
				for(int i=0;i<temp.length;i++){
					if(i<textArea.length)temp[i] = textArea[i];
					else {
						temp[i] = "";
					}
				}
				tempLine = "";
				textArea = temp;
				setText(textArea);
				ypos = textArea.length-1;
			}else{
				String []temp = new String[textArea.length+1];
				for(int i=0;i<textArea.length;i++){
					temp[i] = textArea[i];
				}
				String s1 = "",s2 = "";
				//for(int i=0;i<ypos-1;i++){
				//  temp[i] = textArea[i];
				//}
				temp[ypos] = textArea[ypos].substring(0,xpos);
				//temp[ypos] = "";
				temp[temp.length-1] = textArea[ypos].substring(xpos,textArea[ypos].length());


				tempLine = temp[ypos+1];
				//xpos = tempLine.length();
				textArea = temp;
				setText(textArea);
				ypos = textArea.length-1;
				//xpos = 0;
			}
			xpos = 0;

		}else{
			String []temp = new String[textArea.length+1];

			if(xpos==textArea[ypos].length()){

				for(int i=0;i<ypos+1;i++){
					temp[i] = textArea[i];
				}
				temp[ypos+1] = "";
				for(int i=ypos+2;i<temp.length;i++){
					temp[i] = textArea[i-1];
				}
				tempLine = "";
				textArea = temp;
				setText(textArea);
				ypos ++;
				xpos = 0;
			}
			else{
				for(int i=0;i<ypos;i++){
					temp[i] = textArea[i];
				}

				for(int i=ypos+1;i<textArea.length+1;i++){
					temp[i] = textArea[i-1];
				}

				if(xpos>textArea[ypos].length())xpos = textArea[ypos].length();
				temp[ypos] = textArea[ypos].substring(0,xpos);
				//xpos = temp[ypos].length();
				temp[ypos+1] = textArea[ypos].substring(xpos,textArea[ypos].length());
				for(int i=ypos+2;i<temp.length;i++){
					temp[i] = textArea[i-1];
				}
				tempLine = temp[ypos];
				textArea = temp;
				setText(textArea);
				//xpos = tempLine.length();
				xpos = 0;
				ypos ++;
			}

		}
	};

	public void arrowKeys(){
		if(applet.keyCode==37&&xpos>0){
			xpos--;
		}else if(applet.keyCode==37&&xpos==0&&ypos>0){
			ypos--;
			xpos = textArea[ypos].length();
			tempLine = textArea[ypos];
		}else if(applet.keyCode==39&&xpos<textArea[ypos].length()){
			xpos++;
		}else if(applet.keyCode==39&&xpos==textArea[ypos].length()&&ypos<textArea.length-1){
			xpos = 0;
			ypos++;
			tempLine = textArea[ypos];
		}else if(applet.keyCode==38&&ypos>0){
			ypos--;
			tempLine = textArea[ypos];
			if(tempLine=="")xpos = 0;
			if(xpos>tempLine.length())xpos = tempLine.length();
		}else if(applet.keyCode==40&&ypos<textArea.length-1){
			ypos++;
			tempLine = textArea[ypos];
			if(tempLine=="")xpos = 0;
			if(xpos>tempLine.length())xpos = tempLine.length();
		}
	};

	public void setText(String s){
		text = s;
		textArea = new String[1];
		textArea[0] = s;
	};

	public void setText(String[] s){
		text = "";
		for(int i=0;i<s.length;i++){
			String s1 = s[i];
			if(s.length>0&&s1.length()>0&&s1.charAt(s1.length()-1)!='\n')s1 += "\n";
			//s[i] = s1;
			text+=s1;
		}
	};

	public boolean pos(){
		boolean k = false;
		float max = 0;

		return mouse!=null&&mouse.x>x&&mouse.x<x+w&&mouse.y>y&&mouse.y<y+h;
	};

	public boolean lpos(){
		boolean k = false;
		float max = 0;
		if(textArea!=null)max = textArea.length*(textSize+spacing);

		if(parentTab==null)return applet.mouseX>x&&applet.mouseX<x+w&&applet.mouseY>y&&applet.mouseY<y+max;
		else return mouse!=null&&mouse.x>x&&mouse.x<x+w&&mouse.y>y&&mouse.y<y+max;

	};

	public boolean pos(PVector m){
		boolean k = false;
		if(m ==null)m = setMouse();
		if(parentCanvas==null)return m.x>x&&m.x<x+w&&m.y>y&&m.y<y+h;
		else return m.x>x&&m.x+x<x+w&&m.y>y&&m.y+y<y+h;
		
	};

	public boolean lpos(PVector m){
		boolean k = false;
		float max = textArea.length*(textSize+spacing);
		if(parentCanvas==null)return m.x>x&&m.x<x+w&&m.y>y&&m.y<y+max;
		else return m.x>x&&m.x+x<x+w&&m.y>y&&m.y+y<y+max;
	};

	public int getCursor(){
		int k = -1;
		int kx = -1;
		if(pos()){
			if(applet.mousePressed)toggle = true;
			if(lpos()&&applet.mousePressed){
				//canvas.beginDraw();
				applet.textSize(textSize);
				float max = textArea.length*(textSize+spacing);
				float tw = applet.textWidth(textArea[ypos]);
				k = applet.floor(applet.map(applet.mouseY,y,y+max,0,textArea.length));

				if(k<textArea.length){

					ypos = k;

					String s = "";
					for(int i=0;i<textArea[k].length();i++){
						s += textArea[k].charAt(i);
						float tw1 = applet.textWidth(s)-5;
						if(applet.mouseX<x+tw1&&applet.mouseX>x)break;
						else kx = i;
						if(i==textArea[k].length()-1&&applet.mouseX>x+tw1&&applet.mouseX>x)kx = textArea[k].length();
					}
					//canvas.endDraw();
					if(kx>-1)xpos = kx;
					tempLine = textArea[k];
					if(tempLine.length()==0)xpos = 0;
				}
				if(textArea!=null&&k<textArea.length&&textArea[k].length()==0)xpos = 0;
			}}else if(applet.mousePressed)toggle = false;

		return k;
	};

	public int getCursor(boolean b1){
		int k = -1;
		int kx = -1;
		applet.fill(255);
		applet.textSize(textSize);
		boolean k1 = parentSlider==null&&Bms.getObject()||parentSlider!=null&&Bms.getObject(parentSlider);

		//		parentCanvas.ellipse(mouse.x, mouse.y, 20, 20);
		//		else if(applet.mousePressed)applet.println("txtarea no mouse");
		if(pos()){
			if(applet.mousePressed)toggle = true;
			if(lpos()&&applet.mousePressed){
				float max = textArea.length*(textSize+spacing);
				float tw = applet.textWidth(textArea[ypos]);
				k = applet.floor(applet.map(mouse.y,y,y+max,0,textArea.length));


				if(k<textArea.length){
					//int 
					ypos = k;
					
					String s = "";
					for(int i=0;i<textArea[k].length();i++){
						s += textArea[k].charAt(i);
						float tw1 = applet.textWidth(s)-5;
						if(mouse.x<x+tw1&&mouse.x>x)break;
						else kx = i;

						if(i==textArea[k].length()-1&&mouse.x>x+tw1&&mouse.x>x)kx = textArea[k].length();
					}
					if(kx>-1)xpos = kx;

					//if(tempLine.length()==0)xpos = 0;
				}
				if(textArea!=null&&k<textArea.length&&textArea[k].length()==0)xpos = 0;
			}}else if(applet.mousePressed)toggle = false;

		return k;
	};

	public int getCursor(PVector mouse){
		int k = -1;
		int kx = -1;
		boolean k1 = parentSlider==null&&Bms.getObject()||parentSlider!=null&&Bms.getObject(parentSlider);
		applet.textSize(textSize);
		if(pos()){
			if(applet.mousePressed)toggle = true;
			if(lpos()&&applet.mousePressed){
				float max = textArea.length*(textSize+spacing);
				float tw = applet.textWidth(textArea[ypos]);
				k = applet.floor(applet.map(mouse.y,y,y+max,0,textArea.length));


				if(k<textArea.length){

					ypos = k;
					applet.textSize(textSize);
					String s = "";
					for(int i=0;i<textArea[k].length();i++){
						s += textArea[k].charAt(i);
						float tw1 = applet.textWidth(s)-5;

						if(mouse.x<x+tw1&&mouse.x>x)break;
						else kx = i;

						if(i==textArea[k].length()-1&&mouse.x>x+tw1&&mouse.x>x)kx = textArea[k].length();
					}
					if(kx>-1)xpos = kx;
				}
				tempLine = textArea[k];
			}}else if(applet.mousePressed)toggle = false;

		return k;
	};

	public PVector setMouse(){
		if(parentTab==null){
			mouse = new PVector(applet.mouseX,applet.mouseY);
		}
		else {

			//			mouse.x = applet.mouseX;
			//			mouse.y = applet.mouseY;

		}
		return mouse;
	};

	public void addLine() {
		String []temp = new String[textArea.length];
		for(int i=0;i<textArea.length;i++) {
			temp[i] = textArea[i];
		}
		tempLine = "";
		textArea = temp;
		ypos = textArea.length-1;
		textArea[ypos] = "";
		xpos = 0;

	};

	public void addLineAt(int a) {
		String []temp = new String[textArea.length];
		for(int i=0;i<a;i++) {
			temp[i] = textArea[i];
		}
		temp[a] = "";
		tempLine = "";
		for(int i=a;i<textArea.length;i++) {
			temp[i] = textArea[i-1];
		}
		textArea = temp;
		ypos = textArea.length-1;
		textArea[ypos] = "";
		xpos = 0;

	};


	public void addLineAt(int a,int j) {
		String []temp = new String[textArea.length];
		for(int i=0;i<a;i++) {
			temp[i] = textArea[i].substring(0,j);
		}
		temp[a] = "";
		for(int i=xpos;i<textArea[ypos].length();i++) {
			temp[a] += textArea[ypos].charAt(i);
		}
		tempLine = temp[a];
		for(int i=a;i<textArea.length;i++) {
			temp[i] = textArea[i-1];
		}
		textArea = temp;
		ypos = textArea.length-1;
		textArea[ypos] = "";
		xpos = 0;

	};

	//public float

	public void setRadius(float a) {

		r1 = a;
		r2 = a;
		r3 = a;
		r4 = a;
	};

	public void setRadius(float a,float b,float c,float d) {

		r1 = a;
		r2 = b;
		r3 = c;
		r4 = d;
	};

	public void setTab(tab t){
		Bms = t.Bms;
		applet = t.applet;
		parentTab = t;
		parentCanvas = t.canvas;
		setMouse = true;
	};

	public void setWindow(Window t){
		Bms = t.Bms;
		applet = t.applet;
		parentWindow = t;
		parentCanvas = t.canvas1;
		setMouse = true;
	};

	public void reset() {
		textArea = new String[1];
		textArea[0] = "";
		tempLine = "";
		text = "";
		xpos = 0;
		ypos = 0;


	};

	public void setPos(float a,float b) {

		x = a;
		bx = a;
		y = b;
		by = b;
	};

	public float getValue() {
		float a;
		if(tempLine.length()>0) {
			return Float.parseFloat(tempLine);
		}else return -999999;
	};

	public void save() {

		
	};
	
	public void defaultSave() {

		for(int i=0;i<textArea.length;i++) {
			
		}
	};

	public void set(float a) {

		if(isNumber) {
			value = a;
			tempLine = "";
			tempLine+= a;
			setText(tempLine);
		}

	};

	public void setSize(float a){
		box = true;
		textSize = a;
		h = a;

	};

};
