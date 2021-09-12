package windowsGui;

import processing.core.PApplet;
import processing.core.PFont;

public class Theme extends MouseFunctions{
	public BMS Bms;
	public fileOutput output;
	public PApplet p;
	public int col,bgcol,bcol,tcol,fcol,toggleCol,tabCol,sliderbgcol,hcol,
			   tabcol,strokeCol,sliderhcol,sliderfillcol,buttonstrokecol,
			   sliderstrokecol,tabstrokecol,dockfillcol,menufillcol,menuhcol,
			   menustrokecol,menubcol,tabbcol,sliderbcol,windowbcol,
			   windowfillcol,windowhighlightcol,windowbgcol,windownavcol,
			   windowquickcol,windowstrokecol,dockstrokecol,tabtextcol,
			   windowtextcol,buttontextcol,menutextcol,slidertextcol,
			   tabfillcol,buttonfillcol,buttonhcol,tabhcol,windowhcol,
			   buttontogglecol,menutogglecol,sliderarcfcol,sliderouterhcol,
			   sliderinnerfcol,sliderinnerhcol,sliderouterfcol,sliderarchcol,
			   textboxfillcol,textboxhcol,texboxtextcol,dropdownfillcol,
			   textboxstrokecol,dropdownhcol,dropdowntextcol,
			   dropdownstrokecol,scol,dropdowntogglecol,textareafillcol,
			   textareastrokecol,textareatextcol,textareahcol,maxcount = 60;
	public float slidertextsize,buttontextsize,menutextsize,
				 buttontrancparency,buttonstrokesize,tabstrokesize,
				 sliderstrokesize,menustrokesize,dockstrokesize,
				 docktransparency,dockheight,ssize = 1,wtsize,tabTitle,
				 textSize,tsize = 15,tabtextsize,windowtextsize,
				 windowtransparency,tabtransparency,slidertransparency,
				 menutransparency,buttontransparency,windowstrokesize,
				 textboxstrokesize,textboxtextsize,dropdowntextsize,
				 dropdownstrokesize,dropdowntransparency,dockr1,dockr2,dockr3,
				 dockr4,sliderr1,sliderr2,sliderr3,sliderr4,tabr1,tabr2,tabr3,
				 tabr4,buttonr1,buttonr2,buttonr3,buttonr4,windowr1,windowr2,windowr3,
				 windowr4,menur1,menur2,menur3,menur4,docktextsize,
				 textareastrokesize,textarear1,textarear2,textarear3,
				 textarear4,textareatransparency,textareatextsize,dclicklimit = 100;
	public boolean border = true,fill = true,transparent,sliderfill,menufill,
				   tabfill,windowfill,buttonfill,sliderborder,buttonborder,
				   tabborder,windowborder,menuborder,dockborder,dockfill,
				   dropdownborder,dropdownfill,mdown,textareaborder,
				   textareafill;
	public int counter;
	public float trans1 = 50,trans2,trans3,trans4;
	float radius = 0;
	public float r1,r2,r3,r4;
//	public float r1 = 10,r2 = 10,r3 = 10,r4 = 10;
	public PFont font,sliderfont,menufont,windowfont,tabfont,buttonfont,
		  dropdownfont,textareafont;
	public String fontName = "Consolas Bold Italic",dropDownFontName,textAreaFontName,sliderFontName,
				  sliderBoxFontName,tabFontName,buttonFontName,menuFontName,windowFontName;
	public Theme(PApplet p){
		this.p = p;
		init();
		registerMouse(this);
	};
	
	

	public void init() {
		col = p.color(0, 255, 73);
		bgcol = p.color(52,11,11);
		bcol = p.color(255);
		tcol = p.color(219, 147, 114); 
		fcol = p.color(0, 255, 73);
		hcol = p.color(3, 161, 252);
		toggleCol = p.color(55, 164, 160);
		tabcol = p.color(57, 3, 252);
		scol = p.color(183, 235, 52);
		sliderbgcol = p.color(255);
		slidertextsize = 12;
		sliderarcfcol = p.color(156, 230, 207);
		sliderarchcol = p.color(104, 217, 185);
		sliderinnerfcol = fcol;
		sliderinnerhcol = hcol;
		sliderouterfcol = p.color(199, 235, 224);
		sliderouterhcol = p.color(99, 207, 173);
		
		buttontogglecol = toggleCol;
		
		menutextsize = tsize;
		buttontextsize = tsize;
		tabtextsize = tsize;
		windowtextsize = tsize;
		dropdowntextsize = tsize;
		docktextsize = tsize;
		textareatextsize = tsize;
		slidertextsize = tsize;
		
		slidertextcol = tcol;
		tabtextcol = tcol;
		buttontextcol = tcol;
		menutextcol = tcol;
		windowtextcol = tcol;
		dropdowntextcol = tcol;
		textareatextcol = tcol;
		
		sliderfillcol = fcol;
		tabfillcol = tabcol;
		buttonfillcol = fcol;
		windowfillcol = tabcol;
		menufillcol = fcol;
		dropdownfillcol = fcol;
		textareafillcol = fcol;
		
		sliderhcol = hcol;
		tabhcol = hcol;
		buttonhcol = hcol;
		windowhcol = hcol;
		menuhcol = hcol;
		dropdownhcol = hcol;
		textareahcol = hcol;
		
		sliderstrokecol = scol;
		tabstrokecol = scol;
		buttonstrokecol = scol;
		windowstrokecol = scol;
		menustrokecol = scol;
		dropdownstrokecol = scol;
		textareastrokecol = scol;

		tabborder = border;
		sliderborder = border;
		buttonborder = border;
		windowborder = border;
		dockborder = border;
		menuborder = border;
		dropdownborder = border;
		textareaborder = border;
		
		tabfill = fill;
		sliderfill = fill;
		buttonfill = fill;
		windowfill = fill;
		menufill = fill;
		dropdownfill = fill;
		textareafill = fill;
		
		sliderstrokesize = ssize;
		buttonstrokesize = ssize;
		menustrokesize = ssize;
		tabstrokesize = ssize;
		windowstrokesize = ssize;
		dockstrokesize = ssize;
		dropdownstrokesize = ssize;
		textareastrokesize = ssize;
		
		slidertransparency = trans1;
		buttontransparency = trans1;
		tabtransparency = trans1;
		menutransparency = trans1;
		windowtransparency = trans1;
		docktransparency = trans1;
		dropdowntransparency = trans1;
		textareatransparency = trans1;
		
		font = p.createFont(fontName, 32);
		dropdownfont = font;
		tabfont = font;
		buttonfont = font;
		sliderfont = font;
		menufont = font;
		windowfont = font;
		textareafont = font;
		
		dropDownFontName = fontName;
		tabFontName = fontName;
		buttonFontName = fontName;
		sliderFontName = fontName;
		menuFontName = fontName;
		windowFontName = fontName;
		textAreaFontName = fontName;
		
		r1 = radius;
		r2 = radius;
		r3 = radius;
		r4 = radius;
		
	};

	public void highlight() {


	};

	public void setAllBorderSize(float a) {

		tabstrokesize = a;
		sliderstrokesize = a;
		menustrokesize = a;
		buttonstrokesize = a;
		windowstrokesize = a;
		dockstrokesize = a;
		dropdownstrokesize = a;
		textareastrokesize = a;
	};
	
	public void setAllBorderColor(int a) {

		tabstrokecol = a;
		sliderstrokecol = a;
		menustrokecol = a;
		buttonstrokecol = a;
		windowstrokecol = a;
		dockstrokecol = a;
		dropdownstrokecol = a;
		textareastrokecol = a;
		
	};
	
	public void setAllTextSize(float a) {

		tabtextsize = a;
		slidertextsize = a;
		menutextsize = a;
		buttontextsize = a;
		windowtextsize = a;
		dockheight = a + 5;
		dropdowntextsize = a;
		textareatextsize = a;
	};
	
	public void setAllTextCol(int a) {
		tcol = a;
		tabtextcol = a;
		slidertextcol = a;
		menutextcol = a;
		buttontextcol = a;
		windowtextcol = a;
		dropdowntextcol = a;
		textareatextcol = a;
		
	};
	
	public void setAllfillCol(int a) {

		tabfillcol = a;
		sliderfillcol = a;
		menufillcol = a;
		buttonfillcol = a;
		windowfillcol = a;
		dockfillcol = a;
		dropdownfillcol = a;
		textareafillcol = a;
	};
	
	public void setAllhCol(int a) {

		tabhcol = a;
		sliderhcol = a;
		menuhcol = a;
		buttonhcol = a;
		windowhcol = a;
		dropdownhcol = a;
		textareahcol = a;
	};
	
	public void setAllTransparency(int a) {

		tabtransparency = a;
		slidertransparency = a;
		menutransparency = a;
		buttontransparency = a;
		windowtransparency = a;
		docktransparency = a;
		dropdowntransparency = a;
		textareatransparency = a;
	};
	
	public void setAllTransparency(float a) {

		tabtransparency = a;
		slidertransparency = a;
		menutransparency = a;
		buttontransparency = a;
		windowtransparency = a;
		docktransparency = a;
		dropdowntransparency = a;
		textareatransparency = a;
	};
	
	public void setAllToggleCol(int a) {

		menutogglecol = a;
		buttontogglecol = a;
		dropdowntogglecol = a;
	};
	
	@Override
	public void mousePressed() {
		if(!mousePressed) {
			click = true;
			
			mouseClicked = true;
			//else click = false;
//			mdown = true;
			if(!dclick)mdown1 = true;
			else mdown1 = false;
			mousePressed = true;
			mouseReleased = false;
			mup = false;
			
//			counter++;
		}
		
		mdown = true;
		if(counter>0)dclick = true;
//		if(dclick)p.println("dlick");
//		p.println("theme mousePressed");
	};
	
	@Override 
	public void mouseReleased() {
//		p.println("theme mouseReleased");
		mouseReleased = false;
//		if(mousePressed)
		mouseReleased = true;
		mousePressed = false;
		click = false;
		mouseClicked = false;
		drag = false;
		mouseDragged = false;
//		mdown = false;
		mup = true;
//		if(dclick)counter=0;
//		dclick = false;
//		p.println("mr");
	};
	
	public void run() {
//		if(mouseReleased)p.println("theme drawthemes");

		if(dclick) {
//			p.println("dlick",counter);
			mdown = false;
			counter = 0;
		}
//		if(mdown)p.println("mdown",counter);
		if(mdown)counter++;
		if(counter>=maxcount) {
			mdown=false;
			counter = 0;
		}
		
		
		click = false;
		dclick = false;
//		if(mouseReleased)p.println("theme run mrelease off",this);
			mouseReleased = false;
	};
	
	public void setFontColor(int a,int b,int c) {
		tcol = p.color(a,b,c);
		setAllTextCol(tcol);
	};
	
	public void setFontColor(float a,float b,float c) {
		tcol = p.color(a,b,c);
		setAllTextCol(tcol);
	};
	
	public void setStrokeColor(int a,int b,int c) {
		scol = p.color(a,b,c);
		setAllBorderColor(scol);
	};
	
	public void setStrokeColor(float a,float b,float c) {
		scol = p.color(a,b,c);
		setAllBorderColor(scol);
	};
	
	public void setStrokeSize(float a) {
		ssize = a;
		setAllBorderSize(ssize);
	};
	
	public void save() {
		Bms.output.writeLine("col",col);
		Bms.output.writeLine("bgcol",bgcol);
		Bms.output.writeLine("bcol",bcol);
		Bms.output.writeLine("tcol",tcol); 
		Bms.output.writeLine("fcol",fcol);
		Bms.output.writeLine("hcol",hcol);
		Bms.output.writeLine("toggleCol",toggleCol);
		Bms.output.writeLine("tabcol",tabcol);
		Bms.output.writeLine("scol",scol);
		Bms.output.writeLine("sliderbgcol",sliderbgcol);
		Bms.output.writeLine("slidertextsize",slidertextsize);
		Bms.output.writeLine("sliderarcfcol",sliderarcfcol);
		Bms.output.writeLine("sliderarchcol",sliderarchcol);
		Bms.output.writeLine("sliderinnerfcol",sliderinnerfcol);
		Bms.output.writeLine("sliderinnerhcol",sliderinnerhcol);
		Bms.output.writeLine("sliderouterfcol",sliderouterfcol);
		Bms.output.writeLine("sliderouterhcol",sliderouterhcol);
		
		Bms.output.writeLine("buttontogglecol",buttontogglecol);
		
		Bms.output.writeLine("menutextsize",menutextsize);
		Bms.output.writeLine("buttontextsize",buttontextsize);
		Bms.output.writeLine("tabtextsize",tabtextsize);
		Bms.output.writeLine("windowtextsize",windowtextsize);
		Bms.output.writeLine("dropdowntextsize",dropdowntextsize);
		Bms.output.writeLine("docktextsize",docktextsize);
		Bms.output.writeLine("textareatextsize",textareatextsize);
		Bms.output.writeLine("slidertextsize",slidertextsize);
		
		Bms.output.writeLine("slidertextcol",slidertextcol);
		Bms.output.writeLine("tabtextcol",tabtextcol);
		Bms.output.writeLine("buttontextcol",buttontextcol);
		Bms.output.writeLine("menutextcol",menutextcol);
		Bms.output.writeLine("windowtextcol",windowtextcol);
		Bms.output.writeLine("dropdowntextcol",dropdowntextcol);
		Bms.output.writeLine("textareatextcol",textareatextcol);
		
		Bms.output.writeLine("sliderfillcol",sliderfillcol);
		Bms.output.writeLine("tabfillcol",tabfillcol);
		Bms.output.writeLine("buttonfillcol",buttonfillcol);
		Bms.output.writeLine("windowfillcol",windowfillcol);
		Bms.output.writeLine("menufillcol",menufillcol);
		Bms.output.writeLine("dropdownfillcol",dropdownfillcol);
		Bms.output.writeLine("textareafillcol",textareafillcol);
		
		Bms.output.writeLine("sliderhcol",sliderhcol);
		Bms.output.writeLine("tabhcol",tabhcol);
		Bms.output.writeLine("buttonhcol",buttonhcol);
		Bms.output.writeLine("windowhcol",windowhcol);
		Bms.output.writeLine("menuhcol",menuhcol);
		Bms.output.writeLine("dropdownhcol",dropdownhcol);
		Bms.output.writeLine("textareahcol",textareahcol);
		
		Bms.output.writeLine("sliderstrokecol",sliderstrokecol);
		Bms.output.writeLine("tabstrokecol",tabstrokecol);
		Bms.output.writeLine("buttonstrokecol",buttonstrokecol);
		Bms.output.writeLine("windowstrokecol",windowstrokecol);
		Bms.output.writeLine("menustrokecol",menustrokecol);
		Bms.output.writeLine("dropdownstrokecol",dropdownstrokecol);
		Bms.output.writeLine("textareastrokecol",textareastrokecol);

		Bms.output.writeLine("tabborder",tabborder);
		Bms.output.writeLine("sliderborder",sliderborder);
		Bms.output.writeLine("buttonborder",buttonborder);
		Bms.output.writeLine("windowborder",windowborder);
		Bms.output.writeLine("dockborder",dockborder) ;
		Bms.output.writeLine("menuborder",menuborder);
		Bms.output.writeLine("dropdownborder",dropdownborder);
		Bms.output.writeLine("textareaborder",textareaborder);
		
		Bms.output.writeLine("tabfill",tabfill);
		Bms.output.writeLine("sliderfill",sliderfill);
		Bms.output.writeLine("buttonfill",buttonfill);
		Bms.output.writeLine("windowfill",windowfill);
		Bms.output.writeLine("menufill",menufill);
		Bms.output.writeLine("dropdownfill",dropdownfill);
		Bms.output.writeLine("textareafill",textareafill);
		
		Bms.output.writeLine("sliderstrokesize",sliderstrokesize);
		Bms.output.writeLine("buttonstrokesize",buttonstrokesize);
		Bms.output.writeLine("menustrokesize",menustrokesize);
		Bms.output.writeLine("tabstrokesize",tabstrokesize);
		Bms.output.writeLine("windowstrokesize",windowstrokesize);
		Bms.output.writeLine("dockstrokesize",dockstrokesize);
		Bms.output.writeLine("dropdownstrokesize",dropdownstrokesize);
		Bms.output.writeLine("textareastrokesize",textareastrokesize);
		
		Bms.output.writeLine("slidertransparency",slidertransparency);
		Bms.output.writeLine("buttontransparency",buttontransparency);
		Bms.output.writeLine("tabtransparency",tabtransparency);
		Bms.output.writeLine("menutransparency",menutransparency);
		Bms.output.writeLine("windowtransparency",windowtransparency);
		Bms.output.writeLine("docktransparency",docktransparency);
		Bms.output.writeLine("dropdowntransparency",dropdowntransparency);
		Bms.output.writeLine("textareatransparency",textareatransparency);
		
		Bms.output.writeLine("font",fontName);
		Bms.output.writeLine("dropdownfont",dropDownFontName);
		Bms.output.writeLine("tabFontName",tabFontName);
		Bms.output.writeLine("buttonFontName",buttonFontName);
		Bms.output.writeLine("sliderFontName",sliderFontName);
		Bms.output.writeLine("menuFontName",menuFontName);
		Bms.output.writeLine("windowFontName",windowFontName);
		Bms.output.writeLine("textareaFontName",textAreaFontName);
		
		Bms.output.writeLine("r1",r1);
		Bms.output.writeLine("r2",r2);
		Bms.output.writeLine("r3",r3);
		Bms.output.writeLine("r4",r4);
	};
	
	public void load() {
		
	};
	

};
