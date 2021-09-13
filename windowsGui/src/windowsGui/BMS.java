package windowsGui;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

import processing.core.*;
import processing.opengl.PShader;
import processing.video.Capture;

public class BMS{
	public static PApplet applet;
	public int Mcount;
	public BMScamera camera;
	public int maxFolderSize = 100,mouseButton;
	public HashMap<Object,String> booleans = new HashMap<Object,String>();
	public int col , bgcol, bcol, tcol, fcol, hcol,toggleCol,tabcol,sliderbgcol
	,windowState;
	public float r1,r2,r3,r4,number1,number2,number3,number4,tsize = 12;
	public boolean updated,autoSave,globalDown,debug,borders,terrain3d,showGrid,showBoundaries,showCam,showShapes,
	showWordVectors,showImgProcessors,showMenus = true,showEntities,dockUpdateE,dockUpdateB,dockUpdateI,
	dockUpdateW,dockUpdateA,showNetworks,openWindow,Dock,bg,getSessionSaveRequest,getSessionLoadRequest,prefChecked,prefFound,prefLoaded,
	loadDropdownUpdated,writeComplete,loadComplete;
	public String clipBoard;
	public String []cameras;
	public String currentMouseObject;
	public Object currentObject;
	public Menu menuObject,file,reset,resetDialogue,shapes;
	public Window fmenu;
	public Slider sliderObject;
	public Window windowObject;
	public tab tabObject;
	Button buttonObject;
	//objectSelected;
	public Dropdown dropDownObject;
	public String[] Lines;
	public Button yes,no;
	public Dock dock;
	public tab resetDialogueTab,themeSettings,fontSettings,strokeSettings,restoreDialogueTab;
	public float autoSaveTimeout = 30,transparency;


	public ArrayList<Quad> qgrid = new ArrayList<Quad>();
	public ArrayList<Boundary> boundaries = new ArrayList<Boundary>();

	public ArrayList<Button> buttons = new ArrayList<Button>();
	public ArrayList<Menu> menus = new ArrayList<Menu>();
	public ArrayList<Dropdown> dmenus = new ArrayList<Dropdown>();

	public ArrayList<Slider> sliders = new ArrayList<Slider>();
	public ArrayList<SliderBox> sliderBoxes = new ArrayList<SliderBox>();

	public ArrayList<Table_> tables = new ArrayList<Table_>();

	public ArrayList<PImage> images = new ArrayList<PImage>();
	public ArrayList<String> links = new ArrayList<String>();

	public ArrayList<textBlock> textBlocks = new ArrayList<textBlock>();
	public ArrayList<TextArea> textAreas = new ArrayList<TextArea>();

	public ArrayList<Scene> scenes = new ArrayList<Scene>();
	public ArrayList<Window> windows = new ArrayList<Window>();
	public ArrayList<SWindow> swindows = new ArrayList<SWindow>();
	public ArrayList<tab> tabs = new ArrayList<tab>();
	public ArrayList<Theme> themes = new ArrayList<Theme>();

	public Boundary bb;
	public Window main;
	public fileInput File,Folder;
	public Menu menu;
	public String Location,absolutePath,dataPath,sketchPath;
	//String[] cameras = Capture.list();
	public String[] files;
	public fileOutput output;
	public Theme theme;
	public SWindow Window;
	DialogueBox sessionSave,savePerm,sessionLoad,multiSessionLoad;
	String []preferences;
	//Capture Cam;

	public BMS() {

	};

	public BMS(PApplet applet) {
		BMS.applet = applet;
		theme = new Theme(this);
		theme.init();
		initColors();
		setupDock();
		setupWindows();
		setCams();
	};

	public BMS(PApplet applet,boolean k) {
		BMS.applet = applet;
		theme = new Theme(this);
		theme.init();
		initColors();
		begin();
		setCams();
	};

	public BMS(PApplet applet,boolean k,boolean k1) {
		BMS.applet = applet;
		theme = new Theme(this);
		theme.init();
		initColors();
		begin();
		createCamera();
	};

	public void initColors(){
		initIO();
		initdBox();
		absolutePath = "c:\\";
		sketchPath = applet.sketchPath("");
		dataPath = sketchPath+"\\data";
		PApplet.println("Bms paths: ",absolutePath,sketchPath,dataPath);
		col = applet.color(0, 255, 73);
		bgcol = applet.color(255);
		bcol = applet.color(255);
		tcol = applet.color(0); 
		fcol = applet.color(0, 255, 73);
		hcol = applet.color(0, 255, 73,100);
		toggleCol = applet.color(55, 164, 160);
		tabcol = applet.color(0, 150, 255);
		sliderbgcol = applet.color(255);
		themes.add(theme);
	};

	public void initdBox() {
		int w = 200;
		int h = 100;
		float x = applet.width/2-w/2;
		float y = applet.height/2-h/2;
		sessionSave = new DialogueBox(x,y,w,h,"Save Preferences?",this);
		Button b1 = new Button(10,sessionSave.h-60,w/2-20,30,"Yes",this);
		Button b2 = new Button(20+b1.w+10,sessionSave.h-60,w/2-20,30,"No",this);
		sessionSave.add(b1);
		sessionSave.add(b2);
		sessionLoad = new DialogueBox(x,y,w,h,"Save Preferences?",this);
		sessionLoad.add(b1);
		sessionLoad.add(b2);
		multiSessionLoad = new DialogueBox(x,y,w,h,"Choose file",this);
		String []s = {"1"};
		Dropdown d1 = new Dropdown(20,10,100,20,0,"Select",s,this);
		multiSessionLoad.add(d1);
//		savePerm = new DialogueBox(x,y,w,h,"Restore on load?",this);
//		savePerm.add(b1);
//		savePerm.add(b2);
	}

	public void addWindow(int a,int b,int c,int d) {
		Window = new SWindow(a-5,b,c,d,"Second Window");
		swindows.add(Window);
	};

	public void addWindow(int a,int b,int c,int d,String s) {
		Window = new SWindow(a-5,b,c,d,s);
		swindows.add(Window);
	};

	public void add(Object o,String globalVar,boolean localVar){

		booleans.put(o,globalVar);

	};

	public void draw(){
		// fill(255);

		// text("hello",100,100);
	};
	
	public void initIO() {
		File = new fileInput(this);
		Folder = new fileInput(true,this);
		output = new fileOutput(this);
	};

	public void begin(){
		//	      PApplet.println("bms",applet);
		File = new fileInput(this);
		Folder = new fileInput(true,this);
		setupDock();
		if(debug)PApplet.println("dock");
		setupReset();
		if(debug)PApplet.println("reset");
		setupWindows();
		if(debug)PApplet.println("windows");
		setupMenus();
		if(debug)PApplet.println("menus");
		setupTabs();
		if(debug)PApplet.println("menus");
	};

	public void setupTabs() {
		if(debug)PApplet.println("bms setupt 00");
		themeSettings = new tab(0,55,200,400,"Theme",this);
		String [] s1 = {"red","green","blue","trans"};
		float [] v1 = {52, 235, 225,50};
		SliderBox sl2 = new SliderBox(50,40,90,90,10,s1,v1,this);

		if(debug)PApplet.println("bms setupm 02");
		sl2.menu.draggable = false;
		sl2.tooltip = null;
		sl2.setPieSquare();
		sl2.setStart(0);
		sl2.setEnd(255);
		themeSettings.setvScroll();
		themeSettings.draggable = true;
		themeSettings.add(sl2);
		if(debug)PApplet.println("bms setupm 03");
		dock.add(themeSettings);
		add(themeSettings);
		if(debug)PApplet.println("bms setupm 04");
		fontSettings = new tab(themeSettings.x+themeSettings.w+20,55,200,400,"Fonts",this);
		String [] s2 = {"red","green","blue",};
		float [] v2 = {52, 235, 225};
		SliderBox sl3 = new SliderBox(50,40,90,90,10,s2,v2,this);
		sl3.menu.draggable = false;
		sl3.tooltip = null;
		sl3.setPieSquare();
		sl3.setStart(0);
		sl3.setEnd(255);
		fontSettings.setvScroll();
		fontSettings.draggable = true;
		fontSettings.add(sl3);
		dock.add(fontSettings);
		add(fontSettings);

		strokeSettings = new tab(fontSettings.x+fontSettings.w+20,55,200,400,"Stroke",this);
		String [] s3 = {"red","green","blue","stroke"};
		float [] v3 = {52, 235, 225};
		SliderBox sl4 = new SliderBox(50,40,90,90,10,s3,v3,this);
		sl4.menu.draggable = false;
		sl4.tooltip = null;
		sl4.setPieSquare();
		sl4.setStart(0);
		sl4.setEnd(255);
		sl4.getSlider(3).endvalue = 10;
		strokeSettings.setvScroll();
		strokeSettings.draggable = true;
		strokeSettings.add(sl4);
		dock.add(strokeSettings);
		add(strokeSettings);
	};


	public void setupDock(){
		dock = new Dock(0,applet.height -22,applet.width,24,this);

	};

	public void dockLogic() {
		dock.y = applet.height - theme.dockheight;
		//dock.y = app

	};


	public void setupWindows(){
		//Boundary b = new Boundary(this);
		if(debug)PApplet.println("setupWindows 0");

		//applet.println("Bms setupw 00" ,b);
		if(debug)PApplet.println("setupWindows 1");

		fmenu = new Window(200,200,200,200,"C:\\Users\\paul goux\\",this);
		//		fmenu.toggle = true;
		if(debug)PApplet.println("setupWindows 2");
		fmenu.setRadius(10);
		PApplet.println("setupWindows 3");
		fmenu.rapidAccess = true;
		PApplet.println("setupWindows 4");
//		windows.add(fmenu);
		PApplet.println("setupWindows 4.1");
		dock.add(fmenu);
		PApplet.println("setupWindows 4.2");
		PApplet.println("setupWindows 5");
		PApplet.println(fmenu);
	};

	public void setupMenus(){
		if(debug)PApplet.println("bms setupm 00");
		String [] flabels = {"Settings","Fonts","Camera","Window","Save","Load","Reset"};
		file = new Menu(20,0,50,40,"File",flabels,this);

		menus.add(file);
		if(debug)PApplet.println("bms setupm 00");

		//----------------------file -----------------------------------
		if(file!=null){
			String []glabels = {"Terrain","Img","Cam","Video","Audio","Text"};
			file.items.get(0).submenu  = new Menu(file.items.get(0).x+file.items.get(0).w,file.items.get(0).y,70,glabels,this);
			file.setLink(0);
		}
		if(debug)PApplet.println("bms setupm 00");
	};


	public void setupReset(){
		resetDialogue = new Menu(applet.width/2 - 136,applet.height/2 - 22,275,45,"Are you sure you want to exit?",this);

		resetDialogue.visible = true;
		resetDialogue.highlightable = false;
		resetDialogue.free = true;
		yes = new Button(applet.width/2 - 136 ,applet.height/2 +2,resetDialogue.w/2,20,"Yes",this);
		yes.border = false;
		yes.toggleBox = true;
		yes.Bms = this;
		yes.applet = applet;
		no = new Button(yes.x + yes.w,applet.height/2 +2,resetDialogue.w/2,20,"No",this);
		no.Bms = this;
		no.applet = applet;
		no.border = false;
		no.toggleBox = true;
		resetDialogue.add(yes);
		resetDialogue.add(no);
		resetDialogue.setBms(this);
	};

	public void setupReset2(){
		resetDialogueTab = new tab(applet.width/2 - 136,applet.height/2 - 22,275,45,"Are you sure you want to exit?",this);

		resetDialogue.highlightable = false;
		resetDialogue.free = true;
		yes = new Button(applet.width/2 - 136 ,applet.height/2 +2,resetDialogue.w/2,20,"Yes",this);
		yes.border = false;
		yes.toggleBox = true;
		yes.Bms = this;
		yes.applet = applet;
		no = new Button(yes.x + yes.w,applet.height/2 +2,resetDialogue.w/2,20,"No",this);
		no.Bms = this;
		no.applet = applet;
		no.border = false;
		no.toggleBox = true;

		resetDialogue.items.add(yes);
		resetDialogue.items.add(no);
		yes.parent = resetDialogue;
		no.parent = resetDialogue;
	};


	public void loadImg(){

	};

	public void drawWindows(){
		for(int i=0;i<windows.size();i++){

			Window r = windows.get(i);
//			r.displayGrid();

		}
	};

	public void drawBoundaries(){
		if(debug&&theme.click)PApplet.println("bms displayGrid",boundaries.get(0));
		for(int i=0;i<boundaries.size();i++){

			Boundary b = boundaries.get(i);
			if(b.type==-1)b.draw();
			else b.draw2();
		}
	};

	public void createCamera() {
		camera = new BMScamera(this,1440,720);
	};

	public void run(){
		getSessionSaveRequest();
		if(getSessionSaveRequest||prefFound) {
			toggle(0,0,themeSettings,"toggle");
			if(toggle(0,3)) {
				fmenu.toggle = true;
				getButton(0,3).toggle = false;
			}

			if(camera!=null&&camera.settings!=null)toggle(0,1,camera.settings,"toggle");
			themeFunctions();
			//if(bg)
				applet.background(theme.bgcol);
			globalLogic();
			drawButtons();
			mainFunctions();
			if(showBoundaries)drawBoundaries();
			drawsliderBoxes();
			drawSliders();
			drawDropDowns();
			drawTabs();
			drawTextAreas();

			for(Menu menu : menus)menu.click();

			dock.logic();

			if(camera!=null&&getToggle(0,1)){
				camera.display();
				camera.camLogic();
			}
			//		  else if(camera!=null&&camera.cam.isFlashEnabled())camera.cam.disableFlash();

			drawMenus();
			drawWindows();
			dock.drawItems();
			if(toggle(0,4)) {
//				output.se
				writeComplete = false;
				output.overWrite = true;
				output.append = false;
				output.setSketchLocation("\\data\\preferences0.txt");
				
				save();
			}
			if(toggle(0,5)) {
//				output.se
				loadComplete = false;
				
				load();
			}
		}
	};

	public void runDock(){
		themeFunctions();
		if(bg)applet.background(theme.bgcol);
		globalLogic();
		drawButtons();
		//		mainFunctions();
		if(showBoundaries)drawBoundaries();
		drawsliderBoxes();
		drawSliders();
		drawDropDowns();
		drawTabs();
		for(Menu menu : menus)menu.click();
		dock.logic();
		drawMenus();
		drawWindows();
		dock.drawItems();
		drawTextAreas();

	};

	public void drawDropDowns() {
		for(int i=0;i<dmenus.size();i++) {
			Dropdown d = dmenus.get(i);
			d.applet = applet;
			d.displayDropdown();
		}
	};

	public void getSessionSaveRequest(){
		if(!prefChecked) {
			File.listFiles(sketchPath+"data\\");
			prefChecked = true;
		}else if(File.textFiles.size()>0){
			if(File.textFiles.size()==1) {
				prefFound = true;
			
			if(!prefLoaded) {
				String[] prefs = applet.loadStrings(sketchPath+"data\\"+File.textFiles.get(0));
				if(prefs[0].contains("autoLoad")) {
					getSessionLoadRequest = true;
//					getSessionSaveRequest = true;
				}
				prefLoaded = true;
				PApplet.println("bms display 01",prefLoaded);
			}
			}else if(File.textFiles.size()>1) {
				
//				if(!loadDropdownUpdated) {
//					multiSessionLoad.main.dmenus.get(0).items.get(0).label = "First";
//					multiSessionLoad.main.dmenus.get(1).items.get(1).label = "Last";
//					for(int i=0;i<File.textFiles.size();i++) {
//						String s = File.textFiles.get(i);
//						Dropdown d = multiSessionLoad.main.dmenus.get(0);
//						Button b = new Button(d.x,d.y+d.items.get(0).h*i+d.items.get(0).h*2,d.w,d.items.get(0).h,s,this);
//						d.items.add(b);
//					}
//					loadDropdownUpdated = true;
//				}else {
//					multiSessionLoad.draw();
//				}
//				for(int i=0;i<File.textFiles.size();i++) {
//					String s = File.textFiles.get(i);
//					String fileName = s.substring(0, s.indexOf("preferences"));
//					String sid = s.replace(fileName, "");
//					int id = -1;
//					try {
//						applet.println("menu id",sid);
//						id = Integer.valueOf(sid);
//					}catch(NumberFormatException e) {
//
////			          break;
//			        }
//					
//				}
			}
			
			
		}
		
		if(prefFound&&!getSessionLoadRequest) {
			sessionLoad.draw();
			if(sessionSave.main.toggle(0,this,"getSessionLoadRequest")) {
				save();
				
			}
			sessionSave.main.toggle(1,this,"getSessionSaveRequest");
		}
		
		
		if(!prefFound&&!getSessionSaveRequest) {
			sessionSave.draw();
			if(sessionSave.main.toggle(0,this,"getSessionSaveRequest")) {
				save();
				
			}
			sessionSave.main.toggle(1,this,"getSessionSaveRequest");
		}
	};

	public void run(int i){
		if(prefLoaded)PApplet.println("bms prefloaded");
		//else 
		if(getSessionSaveRequest||prefLoaded) {
			toggle(0,0,themeSettings,"toggle");
			toggle(0,1,themeSettings,"toggle");
			toggle(0,2,fmenu,"open");
			//		if(Window!=null&&theme.click)Window.start();
			//dockLogic();
			if(bg)applet.background(theme.bgcol);
			themeSettings.setvScroll(0, 200);
			fontSettings.setvScroll(0, 200);
			strokeSettings.setvScroll(0, 200);
			drawSWindows();
			if(camera!=null&&camera.settings!=null)toggle(0,1,camera.settings,"toggle");
			themeFunctions();
			if(debug)PApplet.println("bms display 01");
			globalLogic();

			if(debug)PApplet.println("bms display 2");
			if(debug)PApplet.println("bms display 3");

			if(showBoundaries) {
				drawBoundaries();
				if(dock.getItem("sliders")!=null) {
					dockUpdateB = true;
				}
			}else if(dockUpdateB) {
				//			dock.removeItem("sliders");
				dockUpdateB = false;
			}
			if(debug)PApplet.println("bms display 4.5");
			drawButtons();
			if(debug)PApplet.println("bms display 05");
			mainFunctions();
			if(debug)PApplet.println("bms display 06");
			if(debug)PApplet.println("bms display 07");
			drawsliderBoxes();
			if(debug)PApplet.println("bms display 08");
			drawSliders();
			if(debug)PApplet.println("bms display 09");
			drawDropDowns();
			if(debug)PApplet.println("bms display 10");
			drawTabs();
			if(debug)PApplet.println("bms display 11");
			for(Menu menu : menus)menu.click();

			dock.logic();

			if(camera!=null&&getToggle(0,1)){
				camera.display();
				camera.camLogic();
			}
			if(toggle(0,4)) {
				writeComplete = false;
				output.overWrite = true;
				output.append = false;
				output.setSketchLocation("\\data\\preferences0.txt");
				save();
			}
			//			  else if(camera!=null&&camera.cam.isFlashEnabled())camera.cam.disableFlash();

			if(showMenus)drawMenus();
			if(debug)PApplet.println("bms display 13");
			drawWindows();
			if(debug)PApplet.println("bms display 14");
			dock.drawItems();
			if(debug)
				PApplet.println("bms display 16");
			mouseButton = i;
			theme.run();
			drawThemes();
		}
	};

	public void runEmpty(int i){
		mouseButton = i;
		PApplet.println("bms display 01");
		globalLogic();
		drawButtons();
		//		mainFunctions();
		drawsliderBoxes();
		drawSliders();
		drawDropDowns();
		drawTabs();
		for(Menu menu : menus)menu.click();

		if(Dock)dock.logic();

		if(showMenus)drawMenus();
		drawWindows();
		if(Dock)dock.drawItems();
		mouseButton = i;
	};

	public void runSimple(int i) {

	};

	public void toggleAllButtons(){
		for(int i=0;i<buttons.size();i++) {
			buttons.get(i).toggle();
		}

	};

	public void toggleMenuButtons(int i){
		if(i<menus.size())menus.get(i).toggleAll();
	};

	public void toggleAllMenuButtons(){
		for(int i=0;i<menus.size();i++) {
			menus.get(i).toggleAll();
		}
	};

	public boolean toggle(int i){
		return getButton(i)!=null &&getButton(i).toggle();
	};

	public boolean toggle(int i,PVector m){
		return getButton(i)!=null &&getButton(i).toggle();
	};

	public boolean toggle(int i,int j){

		if(i<menus.size()&&j<menus.get(i).items.size()){

			return menus.get(i).toggle(j);
		}else {

			PApplet.println("BMS: button or menu not found..");
			return false;
		}
	};

	public boolean toggle(int i,int j,PVector m){

		if(i<menus.size()&&j<menus.get(i).items.size()){

			return menus.get(i).toggle(j,m);
		}else {
			PApplet.println("BMS: button or menu not found..");
			return false;
		}
	};

	public void selfToggle(int i){
		if(i<=buttons.size())
			buttons.get(i).toggle();
	};

	public void selfToggle(int i,PVector m){
		if( getButton(i)!=null)getButton(i).toggle();
	};

	public void selfToggle(int i,int j){

		if(i<menus.size()&&j<menus.get(i).items.size()){

			menus.get(i).toggle(j);
		}else {
			PApplet.println("BMS: button or menu not found..");
		}
	};

	public void selfToggle(int i,int j,PVector m){

		if(i<menus.size()&&j<menus.get(i).items.size()){

			menus.get(i).toggle(j,m);
		}else {
			PApplet.println("BMS: button or menu not found..");
		}
	};

	public void toggle(int i,Object o,String s){
		if(i<=buttons.size())buttons.get(i).toggle(o,s);
	};

	public void toggle(int i,int j,Object o,String s){
		if(i<=menus.size()&&j<menus.get(i).items.size())menus.get(i).toggle(j,o,s);
	};

	public void drawButtons(){
		for(int i=0;i<buttons.size();i++){
			Button b = buttons.get(i);

			b.draw();
		};
	};

	public void themeFunctions() {
		if(themeSettings!=null) {
			themeSettings.setvScroll(0, 20);
			Menu m1 = themeSettings.sliderBoxes.get(0).menu;
			Slider r = m1.sliders.get(0);
			Slider g = m1.sliders.get(1);
			Slider b = m1.sliders.get(2);
			Slider a = m1.sliders.get(3);
//			if(r.mdown)applet.println("slider test",r.label,theme.bgcol);
//			if(r.mdown ||g.mdown||b.mdown)  {
				theme.bgcol = applet.color(r.value,g.value,b.value);
//			}
			if(a.mdown)theme.setAllTransparency(a.value);
			
		};

		if(fontSettings!=null) {
			Menu m1 = fontSettings.sliderBoxes.get(0).menu;
			Slider r = m1.sliders.get(0);
			Slider g = m1.sliders.get(1);
			Slider b = m1.sliders.get(2);
			if(r.mdown ||g.mdown||b.mdown)setFontColor( r.value,g.value,b.value);
		}

		if(strokeSettings!=null) {
			Menu m1 = strokeSettings.sliderBoxes.get(0).menu;
			Slider r = m1.sliders.get(0);
			Slider g = m1.sliders.get(1);
			Slider b = m1.sliders.get(2);
			Slider s = m1.sliders.get(3);
			//			  s.set(0,20);

			if(r.mdown ||g.mdown||b.mdown)setStrokeColor( r.value,g.value,b.value);
			if(s.mdown)setStrokeSize(PApplet.max(0,s.value));
		}
	};

	public void mainFunctions(){
		//		debug = true;
		if(debug)PApplet.println("bms mainf 01");
		fmenu.displayGrid();
		if(debug)PApplet.println("mainf 02");
		//		debug = false;
	};

	public void drawTabs(){
		for(int i=0;i<tabs.size();i++){
			tab t = tabs.get(i);
			t.tabIndex = i;
			t.displayTab();
		};
	};

	public void drawTextBlocks(){
		for(int i=0;i<textBlocks.size();i++){
			textBlock t = textBlocks.get(i);
			t.draw();
		}
	};

	public void manageClipBoard(){
		//if(getTextFromClipboard ()!=clipBoard)clipBoard = getTextFromClipboard ();
	};



	public void reload(){
		if(buttons.get(0).toggle&&!updated){
			applet.frameCount = -1;
			updated = true;
		}
	};


	public void drawsliderBoxes(){
		for(int i=0;i<sliderBoxes.size();i++){

			SliderBox s = sliderBoxes.get(i);
			if(s.visible)s.draw();
			//s.menu.drawSliders();

		}
	};

	public void drawSliders(){
		for(int i=0;i<sliders.size();i++){

			Slider s = sliders.get(i);
			s.draw();
			s.mouseFunctions();
		}
	};

	public void drawThemes(){
		//		applet.println("BMS drawthemes",themes.size());
		for(int i=0;i<themes.size();i++){

			Theme s = themes.get(i);
			//s.run();
			//s.menu.drawSliders();

		}
	};

	public void drawTextAreas(){
		//		applet.println("BMS drawthemes",themes.size());
		for(int i=0;i<textAreas.size();i++){

			TextArea t = textAreas.get(i);
			t.draw();
			//s.run();
			//s.menu.drawSliders();

		}
	};

	public void drawMenus(){

		Mcount = 0;
		for(int i=0;i<menus.size();i++){

			Menu m = menus.get(i);
			m.draw();
		}
	};

	public void drawSWindows(){
		for(int i=0;i<swindows.size();i++){

			SWindow s = swindows.get(i);
			if(windowState==i&&openWindow&&theme.click)s.start();
			//			if(s.visible)s.draw();
			//s.menu.drawSliders();

		}
	};

	public void boundariesNscenes(){

		//------------------------------------------------------
		//Boundaries--------------------------------------------
	};

	public void autoSave(){

		if(autoSave){
			saveText();
		}

	};

	public void load(){
		fileInput input = File;
//		input.setSketchLocation("data\\preferences.txt");
		preferences = input.loadSketchFile("data\\preferences.txt");
//		Strings file = applet.loadStrings(Location)
		PApplet.println("load document frameCount", applet.frameCount);
		if(preferences!=null) {
//			for(int i=0;i<10;i++) {
//				applet.println(preferences[i]);
//			}
			loadThemes();
			loadMenu();
			loadTabs();
	//		saveButtons();
	//		saveText();
			loadSliderBox();
			loadDMenus();
			loadDock();
		}
//		saveWindow();
		
	};
	
	public void loadMenu() {
		for(int i=0;i<preferences.length;i++) {
			String s = preferences[i];
			if(s.contains("_Menu")) {
				String s1 = preferences[i+1];
				String s2 = s1.substring(0, s1.indexOf(","));
				String sid = s1.replace(s2, "");
				int id = -1;
				
				//theme id
				String menuString = preferences[i+1];
				String menus2 = s1.substring(0, s1.indexOf(","));
				String menusid = s1.replace(menus2, "");
				int menuid = -1;
				//bms menu index
				try {
					PApplet.println("menu id",sid);
					id = Integer.valueOf(sid);
				}catch(NumberFormatException e) {

//		          break;
		        }
				//bms theme id
				try {
					PApplet.println("theme id",menusid);
					menuid = Integer.valueOf(sid);
				}catch(NumberFormatException e) {

//		          break;
		        }
			
				Menu m = menus.get(id);
				m.x = 0;
				m.y = 0;
				menu.theme = themes.get(m.themeIndex);
				
			}
		}
	};
	
	public void loadTabs() {
		
	};
	
	public void loadSliderBox() {
		
	};
	
	public void loadDMenus() {
		
	};
	
	public void loadThemes() {
//		Field[] fields = Theme.class.getDeclaredFields();
//		for (Field field : fields) {
//		  Type type1 = field.getGenericType();
//		  System.out.println("field name: " + field.getName());
//		  if (type1 instanceof ParameterizedType) {
//		    ParameterizedType ptype = (ParameterizedType) type1;
//		    ptype.getRawType();
//		    System.out.println("-raw type:" + ptype.getRawType());
//		    System.out.println("-type arg: " + ptype.getActualTypeArguments()[0]);
//		  } else {
//			  Class s = field.getType();
//		    System.out.println("-field type: " + field.getType());
//		  }
//		}
		for(int i=0;i<preferences.length;i++) {
			String s = preferences[i];
			
			int last = 0;
			if(s.contains("------------------------------------------------")) {
				String s1 = preferences[i+1];
//				PApplet.println("theme value",s1);
				String sid = s1.substring(0, s1.indexOf(",")+1);
				sid = s1.replace(sid, "");
//				PApplet.println("theme sid",sid);
				int id = -1;
				try {
			          id = (Integer.valueOf(sid));
			          //println("num++", a);
//			          PApplet.println("theme id",id);
			        }
			        catch(NumberFormatException e) {
//			        	PApplet.println("theme id fail");
			        }
				if(id>-1) {
					for(int j=i+2;j<preferences.length;j++) {
						String s2 = preferences[j];
						if(s2.contains(",")) {
						String key = s2.substring(0, s2.indexOf(","));
						String value = s2.replace(key, "");
						value = value.replace(",", "");
//						PApplet.println("theme key:",key,"theme value:",value);
						Field field = null;
						Theme t = themes.get(id);
						try{
							field = t.getClass().getField(key); 
//							PApplet.println("key:",key,"value:",value);
							String type = field.getClass().getSimpleName();
//							type = type.replace("public" , "");
//							type = type.replace("Field" , "");
//							field.set(t, value); 
							if(field.getType().getName()=="boolean"){
								if(value.contains("true"))
									try {
										field.setBoolean(t, true);
//										PApplet.println("key",key+", type: bool -->>true");
									} catch (IllegalArgumentException | IllegalAccessException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								if(value.contains("false"))
									try {
										field.setBoolean(t, true);
//										PApplet.println("key",key+", type: bool -->>false");
									} catch (IllegalArgumentException | IllegalAccessException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
//								PApplet.println("key",key+", type: bool");
							}
							if(field.getType().getName()=="int"){
								
								try {
							          int ik = (Integer.valueOf(value));
							          //println("num++", a);
							          try {
										field.set(t, ik);
//										PApplet.println("key",key+", type: int -->>", ik);
									} catch (IllegalArgumentException | IllegalAccessException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
							        }
							        catch(NumberFormatException e) {
							        	PApplet.println("theme int fail");
							        }
							}
							if(field.getType().getName().contains("String")){
								
								try {
									field.set(t, value);
									t.setFonts();
//									PApplet.println("key",key+", type: String -->> fontName");
								} catch (IllegalArgumentException | IllegalAccessException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
							if(field.getType().getName()=="float"){
								try {
							          float ik = (Float.valueOf(value));
							          //println("num++", a);
							          try {
										field.set(t, ik);
//										PApplet.println("key",key+", type: float -->>", ik);
									} catch (IllegalArgumentException | IllegalAccessException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}}
							        catch(NumberFormatException e) {
							        	PApplet.println("theme float fail");
							        }
								
							}
						}catch (NullPointerException e) {
						}catch (NoSuchFieldException e) {
						}
//						catch (IllegalAccessException e) {
//						}
						
//						if(s2.contains("------------------------------------------------")) {
//							i = j;
//							break;
//						}
						
						
						}
					}
				}
				
				
				
				
			}
		}
	};
	
	public void loadDock() {
		
	}
	
	public void saveWindow(){

		for(int i=0;i<sliderBoxes.size();i++){
			SliderBox s = sliderBoxes.get(i);

			s.defaultSave();
		}
	};
	
	public void saveDock(){

//		for(int i=0;i<docks.size();i++){
//			Dock s = sliderBoxes.get(i);
//
//			s.save();
//		}
		dock.save();
	};
	
	public void saveWindows(){

		for(int i=0;i<sliderBoxes.size();i++){
			SliderBox s = sliderBoxes.get(i);

			s.save();
		}
	};
	
	public void savedMenus(){

		for(int i=0;i<dmenus.size();i++){
			Dropdown d = dmenus.get(i);
			d.arrayIndex = i;
			d.defaultSave();
		}
	};

	public void saveText(){

		for(int i=0;i<textAreas.size();i++){
			TextArea t = textAreas.get(i);
			t.arrayIndex = i;
			t.defaultSave();
		}
	};
	
	public void saveSliders(){

		for(int i=0;i<sliders.size();i++){
			Slider s = sliders.get(i);
			s.arrayIndex = i;
			s.defaultSave();
		}
	};

	public void saveSliderBox(){

		for(int i=0;i<sliderBoxes.size();i++){
			SliderBox s = sliderBoxes.get(i);
			s.BMSbound = true;
			s.arrayIndex = i;
			s.defaultSave();
		}
	};

	public void saveButtons(){

		for(int i=0;i<buttons.size();i++){
			Button b = buttons.get(i);
			b.arrayIndex = i;
			b.defaultSave();
		}
	};

	public void saveMenu(){

		for(int i=0;i<menus.size();i++){
			Menu m = menus.get(i);
			m.arrayIndex = i;
			m.save();
		}
	};

	public void saveTabs(){

		for(int i=0;i<tabs.size();i++){
			tab t = tabs.get(i);
			t.BMSbound = true;
			t.arrayIndex = i;
			t.defaultSave();
		}
	};
	
	public void saveThemes(){

		for(int i=0;i<themes.size();i++){
			Theme t = themes.get(i);
			t.index = i;
			t.save();
		}
	};

	public void save(){
		output.setSketchLocation("data\\preferences.txt");
		output.overWrite = true;
		output.checkLocation();
		output.writeFile = true;
		PApplet.println("frameCount", applet.frameCount);
		output.writeLine("autoLoad");
		if(!writeComplete) {
			saveMenu();
			saveTabs();
	//		saveButtons();
	//		saveText();
			saveSliderBox();
			savedMenus();
			saveDock();
	//		saveWindow();
			saveThemes();
			writeComplete = true;
		}
	};
	

	public void start(Object o,boolean localVar){
		Field field = null;

		String s = "hello";

		try{
			//field = o.getClass().getfield(s);
			field = applet.getClass().getField(s); 
		}catch (NullPointerException e) {
		}catch (NoSuchFieldException e) {
		}}

	public void eventListener(){

	};

	public void toggle(Object o,String globalVar,boolean localVar){
		Field field = null;

		try{

			field = applet.getClass().getField(globalVar);


		}catch (NullPointerException e) {
		}catch (NoSuchFieldException e) {
		}

	};

	public void globalLogic(){
		//if(dropDownObject!=null)applet.println(dropDownObject.label);
		if(dropDownObject!=null&&!dropDownObject.dclick){
			dropDownObject = null;
		}
		if(theme.click) globalDown = true;
		else {
			//			currentMouseObject = null;
			//			currentObject = null;
			//			globalDown = false;
		}

	};

	public boolean getToggle(int i){

		if(i<buttons.size()){
			Button b = buttons.get(i);

			if(b.toggle)return true;
			else return false;
		}else {
			PApplet.println("BMS: menu not found");
			return false;
		}
	};

	public boolean getToggle(int i,int j){

		if(i<menus.size()&&j<menus.get(i).items.size()){

			Button b = menus.get(i).items.get(j);

			if(b.toggle)return true;
			else return false;

		}else {

			PApplet.println("BMS: button or menu not found.");
			return false;

		}
	};

	public Button add(int i,Button b){
		//clear();
		b.Bms = this;
		b.applet = applet;
		buttons.add(b);
		return b;
	};

	public Menu add(int i,Menu m){
		//clear();
		m.Bms = this;
		m.applet = applet;
		menus.add(m);
		return m;
	};

	public Dropdown add(int i,Dropdown d){
		//clear();
		d.Bms = this;
		d.applet = applet;
		dmenus.add(d);
		return d;
	};


	public TextArea add(int i,TextArea t){
		//clear();
		t.Bms = this;
		t.applet = applet;
		textAreas.add(t);
		return t;
	};

	public textBlock add(int i,textBlock t){
		//clear();
		t.Bms = this;
		t.applet = applet;
		textBlocks.add(t);
		return t;
	};

	public String add(int i,String s){
		//clear();
		// tab k = states.get(i);
		// textblock.add(s);
		return s;
	};

	public Table_ add(int i,Table_ t){
		//clear();
		t.Bms = this;
		t.applet = applet;
		tables.add(t);
		return t;
	};

	public Button add(Button b){
		//clear();
		b.Bms = this;
		b.applet = applet;
		buttons.add(b);
		return b;
	};

	public Menu add(Menu m){
		//clear();
		m.Bms = this;
		m.applet = applet;
		menus.add(m);
		return m;
	};

	public Dropdown add(Dropdown d){
		//clear();
		d.setBms(this);
		dmenus.add(d);
		return d;
	};

	public TextArea add(TextArea t){
		//clear();
		t.Bms = this;
		t.applet = applet;
		textAreas.add(t);
		return t;
	};

	public String add(String s){
		//clear();
		//textBlocks.add(s);
		return s;
	};

	public Table_ add(Table_ t){
		//clear();
		t.Bms = this;
		t.applet = applet;
		tables.add(t);

		return t;
	};

	public SliderBox add(SliderBox s){
		s.setBms(this);
		sliderBoxes.add(s);

		return s;
	};

	public Slider add(Slider s){
		s.setBms(this);
		sliders.add(s);

		return s;
	};

	public tab add(tab t){
		tabs.add(t);
		if(t.title!=null)PApplet.println("bms",t.title.label);
		return t;
	};

	public Window add(Window s){
		s.Bms = this;
		s.applet = applet;
		windows.add(s);
		return s;
	};


	public Boundary add(Boundary b){
		b.Bms = this;
		b.applet = applet;
		boundaries.add(b);
		return b;
	};


	public void setRadius(float a){
		r1 = a;
		r2 = a;
		r3 = a;
		r4 = a;
		if(dock!=null)dock.setRadius(a);
		if(fmenu!=null)fmenu.setRadius(a);

		for (int i=0; i<dmenus.size(); i++) {
			Dropdown d = dmenus.get(i);
			d.setRadius(a);
		}

		for (int i=0; i<menus.size(); i++) {
			Menu d = menus.get(i);
			d.setRadius(a);
		}

		for (int i=0; i<sliderBoxes.size(); i++) {
			SliderBox s = sliderBoxes.get(i);
			s.menu.setRadius(a);
			if(s.tooltip!=null)s.tooltip.setRadius(a);
		}

		for (int i=0; i<tabs.size(); i++) {
			tab t = tabs.get(i);
			t.setRadius(a);
		}
		for (int i=0; i<textBlocks.size(); i++) {
			textBlock t = textBlocks.get(i);
			t.setRadius(a);
		}
		for (int i=0; i<textAreas.size(); i++) {
			TextArea t = textAreas.get(i);
			t.setRadius(a);
		}
	};

	public void setRadius(float a,float b,float c,float d){
		r1 = a;
		r2 = b;
		r3 = c;
		r4 = d;

		for (int i=0; i<dmenus.size(); i++) {
			Dropdown d1 = dmenus.get(i);
			d1.setRadius(a,b,c,d);
		}

		for (int i=0; i<menus.size(); i++) {
			Menu m = menus.get(i);
			m.setRadius(a,b,c,d);
		}

		for (int i=0; i<sliderBoxes.size(); i++) {
			SliderBox s = sliderBoxes.get(i);
			s.menu.setRadius(a,b,c,d);
			if(s.tooltip!=null)s.tooltip.setRadius(a,b,c,d);
		}

		for (int i=0; i<tabs.size(); i++) {
			tab t = tabs.get(i);
			t.setRadius(a);
		}

		for (int i=0; i<textBlocks.size(); i++) {
			textBlock t = textBlocks.get(i);
			t.setRadius(a,b,c,d);
		}
		for (int i=0; i<textAreas.size(); i++) {
			TextArea t = textAreas.get(i);
			t.setRadius(a,b,c,d);
		}
	};

	public void set(int i,float a,float b) {
		if(i<sliders.size())sliders.get(i).set(a,b);
	};

	public void setInt(int i,int a,int b) {
		if(i<sliders.size())sliders.get(i).setint(a,b);
	};

	public void set(int i,float a,float b,Object o,String s) {
		if(i<sliders.size())sliders.get(i).set(a,b,0,s);
	};

	public void setInt(int i,int a,int b,Object o,String s) {
		if(i<sliders.size())sliders.get(i).setint(a,b,o,s);
	};

	public void set(int i,int j,float a,float b) {
		if(i<sliderBoxes.size()&&j<sliderBoxes.get(i).menu.sliders.size())sliderBoxes.get(i).menu.sliders.get(j).set(a,b);
	};

	public void setInt(int i,int j,int a,int b) {
		if(i<sliderBoxes.size()&&j<sliderBoxes.get(i).menu.sliders.size())sliderBoxes.get(i).menu.sliders.get(j).setint(a,b);
	};

	public void set(int i,int j,float a,float b,Object o,String s) {
		if(i<sliderBoxes.size()&&j<sliderBoxes.get(i).menu.sliders.size())sliderBoxes.get(i).menu.sliders.get(j).set(a,b,0,s);
	};

	public void setInt(int i,int j,int a,int b,Object o,String s) {
		if(i<sliderBoxes.size()&&j<sliderBoxes.get(i).menu.sliders.size())sliderBoxes.get(i).menu.sliders.get(j).setint(a,b,o,s);
	};


	public void setAllBorder(boolean k) {
		theme.buttonborder = k;
		theme.tabborder = k;
		theme.sliderborder = k;
		theme.menuborder = k;
		theme.dropdownborder = k;
		theme.tabborder = k;
		theme.windowborder = k;
		for(int i=0;i<buttons.size();i++) {
			Button b = buttons.get(i);
			b.border = k;
		}
		for(int i=0;i<sliders.size();i++) {
			Slider s = sliders.get(i);
			s.border = k;
		}
		for(int i=0;i<sliderBoxes.size();i++) {
			SliderBox s = sliderBoxes.get(i);
			s.setAllBorder(k);
		}
		for(int i=0;i<menus.size();i++) {
			Menu m = menus.get(i);
			m.setAllBorder(k);
		}
		for(int i=0;i<dmenus.size();i++) {
			Dropdown d = dmenus.get(i);
			d.setAllBorder(k);
		}
		for(int i=0;i<tabs.size();i++) {
			tab b = tabs.get(i);
			b.setAllBorder(k);
		}
	};


	public void setTransparency(float a) {
		transparency = a;
		theme.buttontransparency = a;
		theme.tabtransparency = a;
		theme.slidertransparency = a;
		theme.menutransparency = a;
		theme.dropdowntransparency = a;
		theme.tabtransparency = a;
		theme.windowtransparency = a;
		for(int i=0;i<buttons.size();i++) {
			Button b = buttons.get(i);
			b.transparency = a;
		}
		for(int i=0;i<sliders.size();i++) {
			Slider b = sliders.get(i);
			b.transparency = a;
		}
		for(int i=0;i<menus.size();i++) {
			Menu m = menus.get(i);
			m.setAllTransparency(a);
		}
		for(int i=0;i<dmenus.size();i++) {
			Dropdown d = dmenus.get(i);
			d.setAllTransparency(a);
		}
		for(int i=0;i<sliderBoxes.size();i++) {
			SliderBox b = sliderBoxes.get(i);
			b.setAllTransparency(a);
		}
		for(int i=0;i<tabs.size();i++) {
			tab b = tabs.get(i);
			b.setAllTransparency(a);
		}
	};

	public void setLabel(int i,String s) {
		if(i<buttons.size())buttons.get(i).label = s;
	};

	public void setMenuLabel(int i,String s) {
		if(i<menus.size())menus.get(i).label = s;
	};

	public void setTabLabel(int i,String s) {
		if(i<tabs.size()&&tabs.get(i).title!=null)tabs.get(i).title.label = s;
	};

	public void removeTabLabel(int i,String s) {
		if(i<tabs.size()&&tabs.get(i).title!=null)tabs.get(i).title.label = "";
	};

	public void setTabCol(int i,int c) {
		if(i<tabs.size()&&tabs.get(i).title!=null) {
			tabs.get(i).tabcol = c;
			tabs.get(i).localTheme = true;
		}
	};

	public void setDropdownLabel(int i,String s) {
		if(i<dmenus.size()&&dmenus.get(i).title!=null)tabs.get(i).title.label = s;
	};

	public void setTabvScroll(int i,float a,float b) {
		if(tabs.size()>i&&tabs.get(i).sliderv!=null)tabs.get(i).sliderv.set(a,b);
	};

	public void setTabhScroll(int i,float a,float b) {
		if(tabs.size()>i&&tabs.get(i).sliderh!=null)tabs.get(i).sliderh.set(a,b);
	};

	public void setShader(PShader shader) {
		camera.shader = shader;
	};

	public void readCam() {
		camera.read();
	};

	public tab getTab(int i) {
		tab t = null;
		if(i<tabs.size())t =  tabs.get(i);
		return t;
	};

	public tab getTabByLabel(String s) {
		tab t = null;
		for(int i=0;i<tabs.size();i++) {
			if(tabs.get(i).title!=null&&tabs.get(i).title.label==s) {
				t =  tabs.get(i);
				break;
			}
		}
		return t;
	};

	public Boundary getBoundary(int i) {
		Boundary t = null;
		if(i<menus.size())t =  boundaries.get(i);
		return t;
	};

	public Menu getMenu(int i) {
		Menu t = null;
		if(i<menus.size())t =  menus.get(i);
		return t;
	};

	public Dropdown getdMenu(int i) {
		Dropdown t = null;
		if(i<dmenus.size())t =  dmenus.get(i);
		return t;
	};



	public Button getButtons(int i) {
		Button t = null;
		if(i<buttons.size())t =  buttons.get(i);
		return t;
	};

	public Button getButtonByLabel(String s) {
		Button t = null;
		int count = 0;
		for(int i=0;i<buttons.size();i++) {
			Button b = buttons.get(i);
			if(b.label.contains(s)) {
				count++;
				t = b;
			}
		}
		if(count==1)return t;
		else return null;
	};

	public Table_ getTable(int i) {
		Table_ t = null;
		if(i<tables.size())t =  tables.get(i);
		return t;
	};

	public TextArea getTextArea(int i) {
		TextArea t = null;
		if(i<textAreas.size())t =  textAreas.get(i);
		return t;
	};

	public void initShapes() {
		Boundary b = new Boundary(this);
		main.Boundaries.add(b);
	};

	public void cycle(int a,int b){
		if(a<buttons.size())buttons.get(a).cycle(b);
	};

	public void cycle(int a,int b,PVector m){
		if(a<buttons.size())buttons.get(a).cycle(b,m);
	};

	public void cycle(int a,int b,Object o,String s){
		if(a<buttons.size()) {
			buttons.get(a).cycle(o,s,b);
			if(theme.click)PApplet.println("bms cycle object",a,b);
		}else if(theme.click)PApplet.println("bms cycle object no such button");

	};

	public void cycle(int a,int b,PVector m,Object o,String s){
		if(a<buttons.size())buttons.get(a).cycle(o,s,b,m);
	};

	public void cycle(int a,int b, int c){
		if(a<menus.size()&&b<menus.get(a).items.size())menus.get(a).items.get(a).cycle(c);
	};

	public void cycle(int a,int b,int c,PVector m){
		if(a<menus.size()&&b<menus.get(a).items.size())menus.get(a).items.get(a).cycle(c,m);
	};

	public void cycle(int a,int b, int c,Object o,String s){
		if(a<menus.size()&&b<menus.get(a).items.size())menus.get(a).items.get(a).cycle(o,s,c);
	};

	public void cycle(int a,int b,int c,PVector m,Object o,String s){
		if(a<menus.size()&&b<menus.get(a).items.size())menus.get(a).items.get(a).cycle(o,s,c,m);
	};

	public boolean click(int i) {
		return getButton(i)!=null &&getButton(i).click;
	};

	public boolean click(int i,int j) {
		return getButton(i)!=null &&getButton(i).click;
	};

	public boolean click(int i,PVector m) {
		return getButton(i)!=null &&getButton(i).click;
	};

	public boolean click(int i,int j,PVector m) {
		return getButton(i)!=null &&getButton(i).click;
	};

	public boolean clickAll() {
		boolean k = false;
		for(int i=0;i<buttons.size();i++) {
			if(buttons.get(i).click)k = true;
		}
		return k;
	};

	public boolean clickAll(PVector m) {
		boolean k = false;
		for(int i=0;i<buttons.size();i++) {
			if(buttons.get(i).click)k = true;
		}
		return k;
	};

	public boolean clickAllMenus() {
		boolean k = false;
		for(int i=0;i<menus.size();i++) {
			if(menus.get(i).clickAll())k = true;
		}
		return k;
	};

	public boolean clickAllMenus(PVector m) {
		boolean k = false;
		for(int i=0;i<menus.size();i++) {
			if(menus.get(i).clickAll(m))k = true;
		}
		return k;
	};

	public Button getButton(int i) {
		if(i<buttons.size())return buttons.get(i);
		else return null;
	};

	public Button getButton(int i,int j) {
		if(i<menus.size()&&j<menus.get(i).items.size())return menus.get(i).items.get(j);
		else return null;
	};

	public void setFontColor(int a,int b,int c) {
		theme.setFontColor(a, b, c);
	};

	public void setFontColor(float a,float b,float c) {
		theme.setFontColor(a, b, c);
	};

	public void setStrokeColor(int a,int b,int c) {
		theme.setStrokeColor(a, b, c);
	};

	public void setStrokeColor(float a,float b,float c) {
		theme.setStrokeColor(a, b, c);
	};

	public void setStrokeSize(float a) {
		theme.setStrokeSize(a);
	};

	public void setObject(Slider s) {

		sliderObject = s;
		currentObject = s;
		//		applet.println("bms setobj s",sliderObject,currentObject);
	};

	public void setObject(tab s) {

		tabObject = s;
		currentObject = s;
		if(s.title!=null)buttonObject = s.title;
	};

	public void setObject(Window s) {
		//		if(theme.click ) applet.println("bms setobj w",currentObject,currentObject==null);

		windowObject = s;
		currentObject = s;
	};

	public void setObject(Menu s) {

		menuObject = s;
		currentObject = s;
	};

	public boolean getObject(Button s) {

		return buttonObject==s;
	};

	public boolean getObject(Slider s) {
		//		if(theme.click ) applet.println("bms getobj s",sliderObject,currentObject);
		return currentObject==s;
	};

	public boolean getObject(tab s) {

		return currentObject==s;
	};

	public boolean getObject(Window s) {

		return currentObject==s;
	};

	public boolean getObject() {
		//		if(theme.click ) applet.println("bms getobj",currentObject,currentObject==null);
		return currentObject==null;
	};

	public void clearObject(Slider s) {
		//		applet.println("bms clear slider",s.label);
		if(currentObject==s) {
			currentObject = null;
			sliderObject = null;
		}

	};

	public void clearObject(Menu s) {

		if(currentObject==s) {
			currentObject = null;
			menuObject = null;
		}

	};

	public void clearObject(tab s) {

		if(currentObject==s) {
			currentObject = null;
			tabObject = null;
			if(s.title!=null)buttonObject = null;
		}

	};

	public void clearObject(Window s) {
		//		if(theme.click ) applet.println("bms clear",currentObject,currentObject==null);

		if(currentObject==s) {
			currentObject = null;
			windowObject = null;
		}

	};

	public int []getWindowsAsInt() {
		int []n = new int [swindows.size()];

		for(int i=0;i<swindows.size();i++) {
			n[i] = i;
		}
		return n;
	};

	public String []getWindowsAsString() {
		String []n = new String [swindows.size()];

		for(int i=0;i<swindows.size();i++) {
			n[i] = PApplet.str(i);
		}
		return n;
	};

	public void loadPreferences() {


	};
	
	public void setCams() {
//		cameras = Capture.list();
	};
	
	public void setCams(String []s) {
		cameras = s;
	};
	
	public static class FieldSpy<T> {
	    public boolean[][] b = {{ false, false }, { true, true } };
	    public String name  = "Alice";
	    public ArrayList<Integer> list = new ArrayList<Integer>();
	    public T val;

	    public static  void getType(String... args) {
			try {
			    Class<?> c = Class.forName(args[0]);
			    Field f = c.getField(args[1]);
			    PApplet.println("Type: %s%n", f.getType());
			    PApplet.println("GenericType: %s%n", f.getGenericType());
	
		        // production code should handle these exceptions more gracefully
			} catch (ClassNotFoundException x) {
			    x.printStackTrace();
			} catch (NoSuchFieldException x) {
			    x.printStackTrace();
			}
	    };
	    
	    public static void getType(String s) {
			try {
//			    Class<?> c = Class.forName(args[0]);
			    Field f = Theme.class.getField(s);
			    PApplet.println("Type: %s%n", f.getType());
			    Object gType = f.getGenericType();
			    PApplet.println("GenericType: %s%n", f.getGenericType());
			    if(f.getType().equals(Boolean.TYPE)){System.out.println("true");}
			    f.getClass().getSimpleName();
		        // production code should handle these exceptions more gracefully
			} catch (NoSuchFieldException x) {
			    x.printStackTrace();
			}
	    };
	}

};