package windowsGui;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PGraphics;
import processing.core.PImage;
import processing.opengl.PShader;

public class IMGP {
	public BMS Bms;
	public PApplet applet;
	public float Mean = 0,Variance,VarianceR,VarianceG,VarianceB,VarianceBR,s_mult,currentSliderPos = 30,ix,iy;
	public PImage img,img2, mean,mean_,meanGx,meanGy,blurX,blurY, threshold, variance,varianceR,varianceG,varianceB,varianceBR,Gaussian,
	kMeans, kNearest,sobel, sobelx, sobely,sobel2, sobel2x, sobel2y, sobelMax,sobelMin,sobelG,gradientB, blur,combined,current,canny,cannyT,temp;
	public int cutoff = 250,gridRes = 1,Type = 3,pointMax = 30,min = 40,max = 10,
			contourType = 1,linesCompleted,Mode = 4,thresh = 10,pixelThresh = 40,pixelThresh1 = 20;
	public String currentParameter,currentS;
	public boolean state,bdown,slidersState,mdown1,pieSliders,classicSliders;
	public int processes;
	public ArrayList<String>workFlowLabels=new ArrayList<String>();
	public String []ddLabels = {"Blur","BlurS","Canny","Canny2","Canny3","Canny4","CannyTrim","CannyTrim1",
			"GaussianS","Gaussian 3","Gaussian 5","Mean","Variance","Sobel","Sobel2a",
			"Sobel2b","Sobel Max","Sobel Max2", "Sobel Max3","Sobel Max4","SobelG","SobelG1",};

	public String []menuLabels = {"Load","Save Workflow","Save Image","Restore Menus","State"};
	public String []sobelSliderLabels = {"Mult","Range"};
	public String []sobel2aliderLabels = {"Mult","Range"};
	public String []sobel2bliderLabels = {"Mult","Range","Type"};
	public String []sobelSliderLabels1 = {"Mult","Range","Threshold"};
	public String []sobelGSSliderLabels = {"Mult","Range"};
	public String []sobelSSliderLabels = {"Mult","Range"};
	public String []sobelASliderLabels = {"Mult","Range"};
	public String []sobel2SliderLabels = {"Mult","Range"};
	public String []sobelMaxSliderLabels = {"Mult","Range"};
	public String []sobelMax2SliderLabels = {"Mult","Range"};
	public String []sobelMax3SliderLabels = {"Mult","Range"};
	public String []sobelMax4SliderLabels = {"Mult","Range"};
	public String []meanSliderLabels = {"Range"};
	public String []meanASliderLabels = {"Mult","Range"};
	public String []meanSSliderLabels = {"Mult","Range"};
	public String []varianceSliderLabels = {"Mult","Range"};
	public String []blurSliderLabels = {"Mult","Range"};
	public String []blurSSliderLabels ;
	public String []sharpenSliderLabels = {"Mult","Range"};
	public String []substractSliderLabels ;
	public String []gaussian3SliderLabels ;
	public String []gaussian5SliderLabels ;
	public String []gaussianSSliderLabels = {"Mult","Range"};
	public String []combineSliderLabels ;
	public String []cannySliderLabels = {"Mode","Thresh"};
	public String []canny2SliderLabels = {"Mode","Thresh","Thresh1"};
	public String []canny3SliderLabels = {"Mode","Thresh"};
	public String []cannyTSliderLabels = {"Type","Thresh"};
	public String []cannyT1SliderLabels = {"Type","Thresh"};
	public String []cannyT2SliderLabels = {"Type","Thresh","Thresh2"};
	public String []blendSliderLabels ;
	public String []variance2SliderLabels  = {"Range"};
	public String []variance3SliderLabels  = {"Range"};
	public String []menuLabels1 = {"Load","Save","Pick Camera","Load workflow","Save workflow","Restore Menus"};
	public String []menuLabels2 = {"Run"};
	public String []menuLabels3 = {"Clear"};
	public String []menuLabels4 = {"Run All"};
	public String [][]kernelLabels = {{"Range"},
			{"Mult","Output"},
			{"Mult","Range"},
			{"Mult","Range","Output"},
			{"Mult","Thresh1","Thresh2"},
			{"Mult","Range"},

	};
	public String [] functions = {"Blur","BlurS","Gaussian 3","Gaussian 5","GausianS","Mean","MeanS",
			"Variance", "VarianceS","Sobel", "SobelS","Sobel 2","Sobel Max","Canny","CannyS"};
	public HashMap<String,String[]> workFlowSliders = new HashMap<String,String[]>();
	public String []sliderLabels ,currentFolder,currentWorkFlow;
	public String file,folder,location;
	public PImage currentImage;
	public PGraphics canvas2;
	public Menu menu,run,runAll,clear;
	public Dropdown workFlowDD;
	public SliderBox sobelSlider, meanSlider, varianceSlider, blurSlider, sobelMaxSlider, sharpenSlider, gaussianSlider ;
	public float currentF;
	int currentImageIndex,counter;
	public int firstImageIndex;
	public boolean update = true,imageF = true,videoF,audioF,camF,mdown,m2down,updateSliders,sDown,docked;
	public final int IMAGES = 0,VIDEOS = 1,AUDIO = 2,MOVIE = 3,SOUND = 4;
	public boolean load = true,toggle,iUpdate,iUpdate2,iUpdate3,iUpdate4,wUpdate;
	public ArrayList<PImage> images = new ArrayList<PImage>();
	public ArrayList<PImage> imagesWF = new ArrayList<PImage>();
	public ArrayList<ArrayList<PImage>> imagesWF2 = new ArrayList<ArrayList<PImage>>();
	public ArrayList<ArrayList<PImage>> processedImages = new ArrayList<ArrayList<PImage>>();
	public ArrayList<PImage> thumbnails = new ArrayList<PImage>();
	public ArrayList<PImage> currentThumbnails = new ArrayList<PImage>();
	public ArrayList<PImage> nextThumbnails = new ArrayList<PImage>();
	public ArrayList<PImage> previousThumbnails = new ArrayList<PImage>();
	public ArrayList<SliderBox> sliderBoxes = new ArrayList<SliderBox>();
	public ArrayList<String> names = new ArrayList<String>();
	public fileInput input;
	public fileOutput output,outputWF;
	public tab info,sliders,workFlow,workFlow1,workFlow3;
	public PGraphics canvas,camera,c1,c2,pass1,pass2,pass3,pass4;
	public PShader Sobel,Blur,GaussianS,Sobel1,Sobel2,SobelG,SobelG1,SobelMax,SobelMax2,Canny,CannyTrim,CannyTrim1;
	public String []shaders = {"sobel.glsl","blur.glsl","gBlur.glsl"};
	public String imPath = "";
	public String shaderPath = "";
	//currentField;
	public String [] instructions;
	public Dock myDock;

	public int [][]SobelH = {{-1, -2, -1}, 
			{0, 0, 0}, 
			{1, 2, 1}};

	public int [][]SobelV = {{-1, 0, 1}, 
			{-2, 0, 2}, 
			{-1, 0, 1}};

	public int [][]SobelH_ = {{-2, -1, 0}, 
			{-1, 0, 1}, 
			{0, 1, 2}};

	public int [][]SobelV_ = {{0, 1, 2}, 
			{-1, 0, 1}, 
			{-2, -1, 0}};

	public int [][]edgev = {{-1, -2, -1}, 
			{0, 0, 0}, 
			{1, 2, 1}};

	public int [][]edgeh = {{-1, 0, 1}, 
			{-2, 0, 2}, 
			{-1, 0, 1}};

	public int [][]LapLacian = {{0, 1, 0}, 
			{-1, 4, -1}, 
			{0, 1, 0}};

	public int [][]LapLacianD = {{-1, -1, -1}, 
			{-1, 8, -1}, 
			{-1, -1, -1}};

	public int [][]edge = {{-1, 1, -1}, 
			{-1, 0, -1}, 
			{-1, -1, -1}};

	public int [][]meanX = {{1,1,1}, 
			{0,0,0}, 
			{1,1,1}};

	public int [][]meanY = {{1,1,1}, 
			{2,0,2}, 
			{1,0,1}};

	public float [][]gaussian3 = {{1,2,1}, 
			{2,4,2}, 
			{1,2,1}};

	public float [][]gaussian5 = {{1,4,7,4,1}, 
			{4,16,126,16,4}, 
			{7,26,41,26,7},
			{4,16,26,16,4},
			{1,4,7,4,1}};

	public int [][]neighbours;
	public float [][]gradient;
	
	public IMGP(BMS bms) {
		Bms = bms;
		applet = bms.applet;
		img = applet.createImage(applet.width,applet.height,applet.ARGB);
		img2 = applet.createImage(applet.width,applet.height,applet.ARGB);
		initCanvases();
		initHashMap();
	};
	
	public IMGP(String s) {
		img = applet.loadImage(s);
		img2 = applet.loadImage(s);
		initCanvases();
		initHashMap();
	};

	public IMGP(String s,BMS bms){
		Bms = bms;
		applet = bms.applet;
		img = applet.loadImage(s);
		img2 = applet.loadImage(s);
		
		
	};

	public IMGP(PImage p) {
		img = new PImage(p.width,p.height,PConstants.ARGB);
		img.pixels = p.pixels;
		initHashMap();
	};

	public IMGP(PImage p,BMS bms){
		Bms = bms;
		applet = bms.applet;
		img = new PImage(p.width,p.height,PConstants.ARGB);
		img.pixels = p.pixels;
		initHashMap();
	};

	public IMGP(int w,int h){
		img = new PImage(w, h, PConstants.ARGB);
		initHashMap();
	};

	public IMGP(int w,int h,BMS bms){
		Bms = bms;
		applet = bms.applet;
		img = new PImage(w, h, PConstants.ARGB);
		initHashMap();
	};

	public IMGP(int a,BMS bms){
		Bms = bms;
		applet = bms.applet;
		float mx = applet.width-200;
		if(a==0){
			imageF=true;
			menu = new Menu(200,0,90,50,"IMG",menuLabels,Bms);
			//menu.toggle=1;
			//menus.add(menu);
			//menu = new Menu(0,0,30,20,"File",flabels,Bms);
		}
		if(a==1||a==3)videoF=true;
		if(a==2||a==4){
			audioF=true;
			menu = new Menu(mx,23,100,menuLabels1,Bms);
		}
		if(a==6){
			imageF = false;
			camF = true;
			menu = new Menu(mx,23,100,menuLabels1,Bms);
		}

		myDock = new Dock(0,applet.height - 22,applet.width,24,Bms);
		myDock.parent = this;
		info = new tab(0,applet.height - 170,applet.width,150,"Images",Bms);
		sliders = new tab(applet.width-100,68,100,applet.height - 240,"Sliders",Bms);
		PGraphics pg = applet.createGraphics(200,(int)sliders.h);
		sliders.canvases.add(pg);
		sliders.parentDock = myDock;
		//		sliders.title.w -=20;
		Button b = new Button(80,0,20,20,"<",Bms);
		b.setclassicBar();
		sliders.add(b);
		workFlow = new tab(0,68,100,applet.height - 240,"Workflow",Bms);
		workFlow.parentDock = myDock;
		//workFlow.addState(1);
		workFlow1 = new tab(0,68,100,applet.height - 240,"Workflow",Bms);
		workFlow1.parentDock = myDock;
		workFlowDD = new Dropdown(0,0,100,"Function",ddLabels,0,Bms);
		//workFlow.add(1,workFlowDD);
		workFlow1.add(workFlowDD);
		workFlow1.toggle = true;
		b = new Button(workFlow1.w-20,20,20,20,Bms);
		b.setclassicBar();
		workFlow1.add(b);
		b = new Button(workFlow1.w-40,20,20,20,Bms);
		b.setclassicBar();
		workFlow1.add(b);
		TextArea m1 = new TextArea(0,0,100,workFlow.h - 60,Bms);
		run = new Menu(0,workFlow.h-60,100,menuLabels2,Bms);
		clear = new Menu(50,workFlow.h-60,workFlow.w-run.w,menuLabels3,Bms);
		runAll = new Menu(0,workFlow.h-40,workFlow.w,menuLabels4,Bms);
		run.w = 50;
		run.items.get(0).w = 50;
		clear.w = 50;
		clear.items.get(0).w = 50;
		runAll.w = 100;
		runAll.items.get(0).w = 100;
		workFlow.add(m1);
		workFlow.add(run);
		workFlow.add(clear);
		workFlow.add(runAll);
		workFlow1.add(run);
		workFlow1.add(clear);
		workFlow1.add(runAll);

		input = new fileInput(menu.items.get(0),true,Bms);
		//		input.window  = new Window(200,200,200,200,"C:\\Users\\paul goux\\",bms);
		PApplet.println("img const",input.window);
		output = new fileOutput(Bms);
		info.toggle = true;
		sliders.toggle = true;
		workFlow.toggle = true;
		info.draggable = true;
		info.scrollable = true;
		workFlow.draggable = true;
		sliders.draggable = true;
		sliders.scrollable = true;
		//workFlow.scrollable = true;
		canvas = applet.createGraphics(applet.width-200,applet.height - 190,applet.P2D);
		//menu.toggle=1;
		initHashMap();
		initDock1();
		initFiles();
	};

	public IMGP(){
	};
	
	public void loadImage() {
		
	};
	
	public void initCanny() {
		
	};
	
	public void initCanvases() {
		c1 = applet.createGraphics(img.width,img.height,applet.P2D);
		c2 = applet.createGraphics(img.width,img.height,applet.P2D);
		pass1 = applet.createGraphics(img.width,img.height,applet.P2D);
		pass2 = applet.createGraphics(img.width,img.height,applet.P2D);
		pass3 = applet.createGraphics(img.width,img.height,applet.P2D);
		pass4 = applet.createGraphics(img.width,img.height,applet.P2D);
		Sobel = applet.loadShader(shaderPath+"sobel.glsl");
		Sobel1 = applet.loadShader(shaderPath+"sobel1.glsl");
		Sobel2 = applet.loadShader(shaderPath+"sobel2.glsl");
		SobelG = applet.loadShader(shaderPath+"sobelG.glsl");
		SobelG1 = applet.loadShader(shaderPath+"sobelG1.glsl");
		SobelMax = applet.loadShader(shaderPath+"sobelMax.glsl");
		Canny = applet.loadShader(shaderPath+"canny.glsl");
		CannyTrim = applet.loadShader(shaderPath+"cannyTrim.glsl");
		CannyTrim1 = applet.loadShader(shaderPath+"cannyTrim1.glsl");
		GaussianS = applet.loadShader(shaderPath+"gaussian.glsl");
		c1.beginDraw();
		c1.image(img, 0, 0);
		c1.endDraw();
		pass1.beginDraw();
		pass1.image(img, 0, 0);
		pass1.endDraw();
		pass2.beginDraw();
		pass2.image(img, 0, 0);
		pass2.endDraw();
		pass3.beginDraw();
		pass3.image(img, 0, 0);
		pass3.endDraw();
		pass4.beginDraw();
		pass4.image(img, 0, 0);
		pass4.endDraw();
		initHashMap();
	};

	void initMenus() {

		menu = new Menu(applet.width-100,23,100,30,0,menuLabels,Bms);

		info = new tab(0,applet.height - 170,applet.width,150,"Images",Bms);
		sliders = new tab(applet.width-100,68,100,applet.height - 240,"Sliders",Bms);
		PGraphics pg = applet.createGraphics(200,(int)sliders.h);
		sliders.canvases.add(pg);

		//		sliders.title.w -=20;
		Button b = new Button(80,0,20,20,"<");
		b.setclassicBar();
		sliders.add(b);
		workFlow = new tab(0,68,100,applet.height - 240,"Workflow",Bms);
		TextArea m1 = new TextArea(0,20,100,applet.height - 240,Bms);
		workFlow.add(m1);

		input = new fileInput(menu.items.get(0),true,Bms);
		output = new fileOutput();
		info.toggle = true;
		sliders.toggle = true;
		workFlow.toggle = true;
		info.draggable = true;
		info.scrollable = true;
		workFlow.draggable = true;
		sliders.draggable = true;
		sliders.scrollable = true;
		canvas = applet.createGraphics(applet.width-200,applet.height - 190,applet.P2D);
		menu.toggle = true;
		initHashMap();
		initDock1();
		initFiles();
	};

	public void initDock1(){
		myDock.add(info);
		myDock.add(sliders);
		myDock.add(workFlow);
		myDock.add(workFlow1);
	};

	public void initFiles() {
		imPath = applet.dataPath("images");
		shaderPath = applet.dataPath("shaders");
	};

	public void initHashMap(){
		workFlowSliders.put("Blur",blurSliderLabels);
		workFlowSliders.put("BlurS",blurSSliderLabels);
		workFlowSliders.put("Gaussian 3",gaussian3SliderLabels);
		workFlowSliders.put("Gaussian 5",gaussian5SliderLabels);
		workFlowSliders.put("GaussianS",gaussianSSliderLabels);
		workFlowSliders.put("Mean",meanSliderLabels);
		workFlowSliders.put("MeanS",meanSSliderLabels);
		workFlowSliders.put("Sobel",sobelSliderLabels);
		workFlowSliders.put("SobelGS",sobelGSSliderLabels);
		workFlowSliders.put("SobelS",sobelSSliderLabels);
		workFlowSliders.put("SobelS2",sobelSSliderLabels);
		workFlowSliders.put("SobelS3",sobelSSliderLabels);
		workFlowSliders.put("Sobel 2",sobel2SliderLabels);
		workFlowSliders.put("Sobel Max",sobelMaxSliderLabels);
		workFlowSliders.put("Sobel Max2",sobelMaxSliderLabels);
		workFlowSliders.put("Sobel Max3",sobelMaxSliderLabels);
		workFlowSliders.put("Sobel Max4",sobelMaxSliderLabels);
		workFlowSliders.put("Canny",cannySliderLabels);
		workFlowSliders.put("CannyTrim",cannyTSliderLabels);
		workFlowSliders.put("CannyTrim1",cannyT1SliderLabels);
		workFlowSliders.put("Combine",combineSliderLabels);
		workFlowSliders.put("Substract",substractSliderLabels);
		workFlowSliders.put("Blend",blendSliderLabels);
		workFlowSliders.put("Variance",varianceSliderLabels);
		workFlowSliders.put("Variance2",variance2SliderLabels);
		workFlowSliders.put("Variance3",variance3SliderLabels);
	};

	Object parseParameter(String parameter) {
		try {
			return Integer.parseInt(parameter);
		} catch(NumberFormatException e) {
			try {
				return Float.parseFloat(parameter);
			} catch(NumberFormatException e1) {
				try {
					Field field = this.getClass().getField(parameter);
					return field.get(this);
				} catch (NoSuchFieldException e2){return null;}
				catch(IllegalAccessException e2) {
					throw new RuntimeException(e2);
				}
			}
		}
	};

	Class<?> getParameterClass(String parameter) {
		try {
			Integer.parseInt(parameter);
			return int.class;
		} catch(NumberFormatException e) {
			try {
				Float.parseFloat(parameter);
				return float.class;
			} catch(NumberFormatException e1) {

				if(parameter!=null)return PImage.class;
				else return null;
			}
		}
	};

	public void loadInput(){
		if(location!=null){
			String loc = "";
			if(file!=null||folder!=null){
				if(file!=null)loc = file;
				else loc = folder;

			}
			load = false;
			location = null;
		}
	};

	public void display(){

		if(toggle){
//			sliders.toggleU(0,this,"updateSliders");
			if(!docked){
				// sliders
				// Bms.dock
				docked = true;
			}
			if(!info.sliderh.mdown)myDock.logic();
			myDock.drawItems();


			save();
			workFlowLogic();
			infoTabLogic();

			info.displayTab();
			info.drawDynamicImages(info.images,currentImageIndex);
			slidersTabLogic();
			if(!state)workFlow.displayTab();
			else workFlow1.displayTab();
			menu.click();
			//important revise 
			//			if(menu.click(4))
			menu.toggle(4,this,"state");

			if(!state)runWorkFlow();
			else runWorkFlow1();
			displayCanvas();
			menu.draw();
			sliders.displayTab();
			if(imageF)loadImages();
			if(videoF)loadVideo();
			if(audioF)loadSound();
		}
	};

	public void displayCanvas(){
		if(!applet.mousePressed)mdown = false;

		if(currentImage!=null&&canvas!=null){
			if(iUpdate){
				canvas.beginDraw();
				canvas.background(50);
				canvas.image(currentImage,0,0);
				canvas.endDraw();

				iUpdate = false;
			}
			if(iUpdate2&&names.size()>0){
				if((firstImageIndex + currentImageIndex-3)<names.size()&&(firstImageIndex + currentImageIndex-3)>0)
					currentImage = applet.loadImage(names.get(firstImageIndex + currentImageIndex-3));
				canvas.beginDraw();
				canvas.background(50);
				canvas.image(currentImage,0,0);
				canvas.endDraw();
				iUpdate2 = false;
			}

		}

		if(iUpdate4&&imagesWF.size()>0){
			currentImage = imagesWF.get(imagesWF.size()-1);
			canvas.beginDraw();
			canvas.background(50);
			canvas.image(currentImage,0,0);
			canvas.endDraw();
			iUpdate4 = false;
		}else if(iUpdate4&&imagesWF.size()==0){
			PApplet.println("Workflow error...");
			iUpdate4 = false;
		}
		applet.image(canvas,workFlow.w,21);
	};

	public void slidersTabLogic(){
		int a = 200;
		int b = 100;
		int c = 40;
		//		if(sliders.click(0))slidersState++;
		//		if(slidersState==2)slidersState = 0;
		//		sliders.toggleU(0,this,"slidersState");
		boolean k = false;
		if(sliders.toggle(0,this,"slidersState")) {
			if(!slidersState)slidersState = true;
			else slidersState = false;
			updateSliders = true;
		}

		if(slidersState&&updateSliders){
			//			applet.println("img sliderstabl 00");
			sliders.canvasIndex = 1;

			//			sliders.title.w = a-20;
			sliders.title.w = a;

			sliders.setPos(applet.width -a,sliders.y);
			sliders.title.setPos(sliders.x,sliders.y);
			sliders.w = a;
			sliders.buttons.get(0).x = sliders.w-20;
			//			sliders.sliderh.w = a;
			sliders.sliderv.x = sliders.w-10;
			//			for(int i=0;i<sliders.sliderBoxes.size();i++){
			//				SliderBox s = sliders.sliderBoxes.get(i);
			//				if(s!=null)s.menu.x = c;
			//			}
			//
			//			for(int i=1;i<sliders.buttons.size();i++){
			//				if(sliders.buttons.size()>1){
			//					Button b1 = sliders.buttons.get(i);
			//					b1.x = c;
			//				}}
		}
		else if(!slidersState&&updateSliders){
			//			applet.println("img sliderstabl 01");
			sliders.canvasIndex = 0;
			sliders.setPos(applet.width -b,sliders.y);
			sliders.title.w = b;
			sliders.title.setPos(applet.width -b,sliders.y);
			sliders.w = b;
			sliders.buttons.get(0).x = sliders.w-20;
			sliders.sliderh.w = b;
			sliders.sliderv.x = sliders.w-10;

			//			for(int i=0;i<sliders.sliderBoxes.size();i++){
			//				SliderBox s = sliders.sliderBoxes.get(i);
			//				if(s!=null){
			//					s.menu.x = 0;
			//
			//				}
			//			}
			//			for(int i=1;i<sliders.buttons.size();i++){
			//				Button b1 = sliders.buttons.get(i);
			//				b1.x = 0;
			//			}
		}

		for(int i=0;i<sliders.sliderBoxes.size();i++){
			SliderBox s = sliders.sliderBoxes.get(i);
			if(s!=null){
				for(int j=0;j<s.menu.sliders.size();j++){
					Slider s1 = s.menu.sliders.get(j);
					if(s1.label=="Mult")s1.set(1,50);
					if(s1.label=="Range")s1.setint(1,10);
					if(s1.label=="Thresh")s1.set(1,255);
					if(s1.label=="Type")s1.setint(1,10);
				}
			}
		}
		if(updateSliders)updateSliders = false;
		if(!applet.mousePressed)mdown1 = false;
	};

	public void infoTabLogic(){
		if(thumbnails.size()>0)info.sliderh.setint(0,thumbnails.size(),this,"firstImageIndex");
		if(!applet.mousePressed&&info.posTab()){
			currentImageIndex = PApplet.floor(PApplet.map(applet.mouseX,0,info.w,0,7));
		}else if(info.posTab()&&!info.sliderh.mdown)iUpdate2 = true;

		if(info.sliderh.mdown&&applet.mousePressed&&info.posTab()&&!iUpdate&&applet.mouseX!=applet.pmouseX){
			iUpdate = true;
			mdown = true;
		}

		if(names!=null&&names.size()>0&&iUpdate){

			for(int i=firstImageIndex-4;i<firstImageIndex+5;i++){
				if(i>0&&i<thumbnails.size()){
					PImage p1 = thumbnails.get(i);

					if(!info.images.contains(p1)){
						info.images.add(p1);
					}
					if(info.images.size()>9)info.images.remove(0);
				}}
		}

	};

	public void workFlowLogic(){
		Dropdown d1 = null;
		if(state)d1 = workFlow1.dmenus.get(workFlow1.dmenus.size()-1);

		if(d1!=null&&d1.toggle){

			if(state&&workFlow1.buttons.get(0).toggle()&&d1.index>-1){

				img = currentImage;

				workFlow1.add(new Dropdown(0,workFlow1.dmenus.size()*20,100,"Function " + workFlow1.dmenus.size(),ddLabels,0,Bms));
				workFlow1.buttons.get(0).y+=20;
				workFlow1.buttons.get(1).y+=20;
				//applet.println(workFlow1.Dropdowns.get(0).)
				if(workFlowSliders.get(d1.label)!=null){
					SliderBox s = null;
					Button b1 = null; 
					if(sliders.buttons.size()>0&&sliders.buttons.get(0).toggle){
						s = new SliderBox(0,30+ currentSliderPos,90,5,workFlowSliders.get(d1.label),sliders);
						b1 = new Button(0,30+ currentSliderPos-20,90,20,d1.label);
						b1.setclassicBar();
					}else{
						s = new SliderBox(30,30+ currentSliderPos,90,5,workFlowSliders.get(d1.label),sliders);
						b1 = new Button(30,30+ currentSliderPos-20,90,20,d1.label);
						b1.setclassicBar();
					} 
					if(classicSliders) s.setClassicBar();
					if(pieSliders)s.setPieSquare();
					s.visible = true;
					s.tooltip = null;
					sliders.add(s);
					b1.border = false;
					sliders.add(b1);

				}else{
					Button b1;
					if(sliders.buttons.size()>0&&sliders.buttons.get(0).toggle){

						b1 = new Button(0,30+ currentSliderPos-20,90,20,d1.label);
						b1.setclassicBar();
					}else{
						b1 = new Button(30,30+ currentSliderPos-20,90,20,d1.label);
						b1.setclassicBar();
					} 
					sliders.buttons.add(b1);
					sliders.sliderBoxes.add(null);
				}

				SliderBox s1 = null;
				if(sliders.sliderBoxes.get(sliders.sliderBoxes.size()-1)!=null){
					s1 = sliders.sliderBoxes.get(sliders.sliderBoxes.size()-1);

					currentSliderPos = s1.y+s1.h ;
				}else{
					currentSliderPos += 40;
				}
			}

			if(workFlow1.buttons.get(1).toggle()&&workFlow1.dmenus.size()>1){
				if(workFlow1.dmenus.size()>=1)workFlow1.dmenus.remove(workFlow1.dmenus.size()-1);
				if(sliders.sliderBoxes.size()>=1){
					SliderBox s1 ;
					if(sliders.sliderBoxes.get(sliders.sliderBoxes.size()-1)!=null){
						s1 = sliders.sliderBoxes.get(sliders.sliderBoxes.size()-1);
						currentSliderPos = s1.y-s1.h-20;
						if(currentSliderPos<sliders.y+20)currentSliderPos = currentSliderPos+20;
						sliders.sliderBoxes.remove(sliders.sliderBoxes.size()-1);
						sliders.buttons.remove(sliders.buttons.size()-1);
					}
					else {
						sliders.sliderBoxes.remove(sliders.sliderBoxes.size()-1);
						sliders.buttons.remove(sliders.buttons.size()-1);
						currentSliderPos -= 20;
					}

					workFlow1.buttons.get(0).y-=20;
					workFlow1.buttons.get(1).y-=20;
				}
			}
		}
		if(info.sliderh.mdown)iUpdate2 = true;

		// if(workFlow1.menus.items.get(0).click(workflow.getMouse())){

		// }
	};

	public void workFlowLogic1(){
		//if()
	};

	public void runWorkFlow(){
		if(run.toggle(0)&&!state){
			PApplet.println("workflow 0");
			imagesWF = new ArrayList<PImage>();
			img = currentImage;

			if(workFlow.textareas.get(0).tempLine!=null){

				PApplet.println("Run",workFlow.textareas.get(0).textArea);
				String[] s = PApplet.splitTokens(workFlow.textareas.get(0).tempLine,",");
				String[] s1 = new String[1];
				currentWorkFlow = s1;
				s1[0] = workFlow.textareas.get(0).textArea[0];
				iUpdate3 = true;
				workflow(s1);
				//applet.println(s1);

			}else PApplet.println(false);
		}

		if(clear.toggle(0)){
			currentWorkFlow = null;
			workFlow.textareas.get(0).reset();
		}
	};

	public void runWorkFlow1(){
		if(run.toggle(0)&&state){
			imagesWF = new ArrayList<PImage>();
			img = currentImage;

			if(sliders.sliderBoxes.size()>0){
				String log = "Run state1";

				String []wf = new String[sliders.sliderBoxes.size()] ;
				PApplet.println("Run state1",workFlow.textareas.get(0).getValue());
				for(int i=0;i<sliders.sliderBoxes.size();i++){
					String s = workFlow1.dmenus.get(i).label + "(";
					SliderBox sl = null;
					if(sliders.sliderBoxes.get(i)!=null)
						sl = sliders.sliderBoxes.get(i);
					for(int j=0;j<sl.menu.sliders.size();j++){
						Slider slider = sl.menu.sliders.get(j);

						float v1 = -1;
						int v2 = -1;

						if(slider.label=="Mult"||slider.label=="Thresh"){
							if(j<sl.menu.sliders.size()-1)s += slider.value + ",";
							else s += slider.value ;
						}else{
							if(j<sl.menu.sliders.size()-1)s += (int)slider.value + ",";
							else s += (int)slider.value ;
						}
					}
					s += ")";
					if(s!=null)wf[i] = s;
				}
				PApplet.println(wf);
				workflow(wf);

			}else PApplet.println("No workflow available");
		}

		if(clear.toggle(0)&&state){
			currentWorkFlow = null;

			while(sliders.buttons.size()>1)sliders.buttons.remove(sliders.buttons.size()-1);
			while(sliders.sliderBoxes.size()>0)sliders.buttons.remove(sliders.sliderBoxes.size()-1);

		}
		// if(runAll.toggle(0)){

		//   for(int i=0;i<names.size();i++){

		//   }
		// }
	};

	public void loadImages(){
		input.listen();
		if(input.values!=null){
			PApplet.println("input",input.values.length);
			currentFolder = input.values;
			//names = new String[input.values.length];
			if(images.size()<input.values.length)
				for(int i=0;i<input.values.length;i++){
					String s1 = input.files[i].getAbsolutePath();
					String[] m1 = PApplet.match(s1, ".jpg");
					String[] m2 = PApplet.match(s1, ".jpeg");
					String[] m3 = PApplet.match(s1, ".gif");
					String[] m4 = PApplet.match(s1, ".png");
					String[] m5 = PApplet.match(s1, ".bmp");
					String[] m6 = PApplet.match(s1, ".JPG");
					String[] m7 = PApplet.match(s1, ".JPEG");
					String[] m8 = PApplet.match(s1, ".GIF");
					String[] m9 = PApplet.match(s1, ".PNG");
					String[] m10 = PApplet.match(s1, ".BMP");

					if (m1 != null||m2 != null||m3 != null||m4 != null||m5 != null||
							m6 != null||m7 != null||m8 != null||m9 != null||m10 != null) { 

						PImage thumbnail = applet.loadImage(s1);

						thumbnail.resize(150,90);
						thumbnails.add(thumbnail);
						names.add(s1);
					}
				}
			if(names.size()>0){
				PApplet.println("step 1","first index:",firstImageIndex);
				info.sliderh.valuex = 0;
				info.sliderh.value = 0;
				if(firstImageIndex<0)firstImageIndex=0;
				currentImage = applet.loadImage(names.get(firstImageIndex));
				iUpdate = true;
			}else{
				PApplet.println("No images found...");
				names = new ArrayList<String>();
			}
			input.values = null;
		} 

	};

	public void loadVideo(){


	};

	public void loadSound(){


	};

	public void save(){

	};

	public void workflow(String a){
		String[] s = PApplet.splitTokens(a, "-");
		PApplet.println("workflow 0");
		for(int i=0;i<s.length;i++){
			String s1 = s[i];

			//ArrayList<Integer> [] pIndex = strIndex(s1,"(",")");
			int [] pIndex = strIndex1(s1,"(",")");
			String function = s1.substring(0,pIndex[0]);

			//String[]parameters = new String [pIndex[0].size()];
			String[]parameters = PApplet.splitTokens(s[i].substring(pIndex[0]+1,pIndex[1]),",");
			parameters[parameters.length-1] =  parameters[parameters.length-1].substring(0,parameters.length-1);

			boolean image = false;
			Method method = null;
			try {
				method = this.getClass().getMethod(function,float.class,float.class);
				//IMGP instance = new IMGP();
				float result = (float) method.invoke(this, 1, 3);
				PApplet.println("result",result);
			} catch (SecurityException e) {
				PApplet.println(function , "se");
			}catch (NoSuchMethodException e) {  
				PApplet.println(function , "nsm");
			}
			catch (IllegalAccessException e) {  
				PApplet.println(function , "nsm");
			}
			catch (InvocationTargetException e) {  
				PApplet.println(function , "nsm");
			}
			for(int j=0;j<parameters.length;j++){

				float currentF = Float.parseFloat(parameters[j]);

				if(currentF>-10000000&&currentF<10000000){
					PApplet.println(function,"f " + currentF);
				}else PApplet.println(function,"s " + parameters[j]);

			}
		}
	};

	public void workflow(String[] a){
		if(!iUpdate3){
			PApplet.println("something wrong..");

		}
		if(iUpdate3&&a!=null){
			PApplet.println("workflow 0");
			String[] s = a;

			for(int i=0;i<s.length;i++){
				String s1 = s[i];
				if(s[i].length()>0){
					int [] pIndex = strIndex1(s1,"(",")");
					String function = s1.substring(0,pIndex[0]);

					String[]parameters = PApplet.splitTokens(s[i].substring(pIndex[0]+1,pIndex[1]),",");
					PApplet.print("Parameters",function +"(");
					String s2 = "";
					Class<?>[] parameterClasses = new Class<?>[parameters.length];
					Object[] parsedParameters = new Object[parameters.length];
					for(int j=0;j<parameters.length;j++){
						//applet.print(parameters[j]);

						parameterClasses[j] = getParameterClass(parameters[j]);
						parsedParameters[j] = parseParameter(parameters[j]);
						// s2+=parameterClasses[j]+" "+parameters[j];
						s2 += parameters[j];
						if(j<parameters.length-1)s2+=",";
					}
					PApplet.println(s2+")");

					update = true;
					try {
						Method method = this.getClass().getMethod(function, parameterClasses);
						method.invoke(this, parsedParameters);
						img = imagesWF.get(imagesWF.size()-1);
						workFlowLabels.add(function);
					} catch (NoSuchMethodException e){PApplet.println("nsm",function,parameterClasses[0]);e.printStackTrace();}
					catch(IllegalAccessException e){PApplet.println("ia");e.printStackTrace();}
					catch( InvocationTargetException e){PApplet.println("it...Check images");e.printStackTrace();}
				}}
			update = false;
		}else if(a==null){
			iUpdate3 = false;
			PApplet.println("Please correct function...");
		}

	};

	public void workflow2(String[] a){
		if(update&&a!=null){
			String[] s = a;

			for(int i=0;i<s.length;i++){
				String s1 = s[i];
				if(s[i].length()>0){
					int [] pIndex = strIndex1(s1,"(",")");
					String function = s1.substring(0,pIndex[0]);

					String[]parameters = PApplet.splitTokens(s[i].substring(pIndex[0]+1,pIndex[1]),",");
					PApplet.print("p",function +"(");
					String s2 = "";
					Class<?>[] parameterClasses = new Class<?>[parameters.length];
					Object[] parsedParameters = new Object[parameters.length];
					for(int j=0;j<parameters.length;j++){
						//applet.print(parameters[j]);

						parameterClasses[j] = getParameterClass(parameters[j]);
						parsedParameters[j] = parseParameter(parameters[j]);
						// s2+=parameterClasses[j]+" "+parameters[j];
						s2 += parameters[j];
						if(j<parameters.length-1)s2+=",";
					}
					PApplet.println(s2+")");

					update = true;
					try {
						Method method = this.getClass().getMethod(function, parameterClasses);
						method.invoke(this, parsedParameters);
					} catch (NoSuchMethodException e){PApplet.println("nsm",function,"...Check Params?");}
					catch(IllegalAccessException e){PApplet.println("ia") ;}
					catch( InvocationTargetException e){PApplet.println("it","This function is missing an image...");e.printStackTrace();}
				}}
			update = false;
		}else if(a==null)update = false;

		if(applet.keyPressed&&applet.key =='r')update = true;

	};


	float mult(float a,float b){
		return a * b;
	};

	//String[] split(String s,String s1){
	//  String[]S = applet.splitTokens(s.substring(pIndex[0]+1,pIndex[1]),",");
	//  parameters[parameters.length-1] =  parameters[parameters.length-1].replaceAll(")s+","");
	//  return 
	//};

	int [] strIndex1(String s){
		int[]index = new int [2];
		for(int i=0;i<s.length();i++){
			char c = s.charAt(i);
			if(c=='(')index[0] = i;
			if(c==')')index[1] = i;
		}
		return index;
	};

	ArrayList [] strIndex(String s){
		ArrayList[]index = new ArrayList [2];
		index[0] = new ArrayList<Integer>();
		index[1] = new ArrayList<Integer>();
		for(int i=0;i<s.length();i++){
			char c = s.charAt(i);
			if(c=='(')index[0].add(i);
			if(c==')')index[1].add(i);
		}
		return index;
	};

	int [] strIndex1(String s,String a,String b){
		int[]index = new int [2];
		for(int i=0;i<s.length();i++){
			char c = s.charAt(i);
			if(c=='a')index[0] = i;
			if(c=='b')index[1] = i;
		}
		return index;
	};

	ArrayList [] strIndex(String s,String a,String b){
		ArrayList[]index = new ArrayList [2];
		index[0] = new ArrayList<Integer>();
		index[1] = new ArrayList<Integer>();
		for(int i=0;i<s.length();i++){
			char c = s.charAt(i);
			if(c=='a')index[0].add(i);
			if(c=='b')index[1].add(i);
		}
		return index;
	};

	int findNext(String s){
		int a = -1;

		return a;
	};

	public void set(PImage p){
		img = p;
		c1 = applet.createGraphics(img.width,img.height,applet.P2D);
	};



	public void displayWF(String []s){
		logic();
		workflow(s);
		if(imagesWF.size()>0)
			applet.image(imagesWF.get(counter),ix,iy);
		applet.text(workFlowLabels.get(counter),10,10);
	};


	public void displayWFCanvas(String []s){
		logic();
		workflow2(s);
		//if(imagesWF.size()>0)applet.image(imagesWF.get(imagesWF.size()-1),x,y);

		//if(imagesWF.size()>0)
		//applet.image(imagesWF.get(counter),x,y);

	};

	public void logic(){
		int count = 0;
		if(applet.mousePressed&&!mdown){
			mdown = true;
			counter++;

		}

		if(counter<imagesWF.size()){
			if(imagesWF.get(counter).width>applet.width){
				if(applet.mouseX>0&&applet.mouseX<applet.width)
					ix = -(int)PApplet.map(applet.mouseX,0,applet.width,0,imagesWF.get(counter).width-applet.width);
			}
			if(imagesWF.get(counter).height>applet.height){
				if(applet.mouseY>0&&applet.mouseY<applet.height)
					iy = -(int)PApplet.map(applet.mouseY,0,applet.height,0,imagesWF.get(counter).height-applet.height);
			}
		}

		if(!applet.mousePressed){
			mdown = false;
			m2down = false; 
		}
		if(counter>imagesWF.size()-1)counter = 0;
		if(imagesWF.size()>0&&mdown&&!m2down){
			m2down = true;
			PApplet.println(workFlowLabels.get(counter),imagesWF.size());

		}

	};

	public void logic2(){
		//if(applet.mousePressed&&!mdown){
		//  mdown = true;
		//  counter++;
		//  applet.println(counter);
		//}

		if(applet.mouseX>0)counter = (int)PApplet.map(applet.mouseX,0,applet.width,0,imagesWF.size());
		//if(applet.mouseX>0)counter = (int)applet.map(applet.mouseX,0,width,0,cell.edges.size());

		if(!applet.mousePressed)mdown = false;
		//if(counter>cell.edges.size()-1)counter = 0;
		applet.fill(0);
		applet.text("c "+counter,10,20);
	};

	public void superPixel(){
		img.loadPixels();
		for(int j=0;j<img.pixels.length;j++){

			//if(applet.red(img.pixels[j])<200)img.pixels[j] = applet.color(255,0,0);
			if(applet.color(img.pixels[j])==applet.color(0))img.pixels[j] = applet.color(255,0,0);
		}
		img.updatePixels();
	};

	public void setBms(BMS bms) {
		Bms = bms;
		applet = bms.applet;
	};

	public void setBms(tab bms) {
		Bms = bms.Bms;
		applet = bms.Bms.applet;
	};
};