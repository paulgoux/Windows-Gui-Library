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
import processing.video.Movie; 

import java.awt.Rectangle;
import java.awt.Robot;
//import java.awt.AWTException;
//import it.tidalwave.java.awt.Rectangle.*;
//import hudson.plugins.robot.*;

public class imageProcessor {
	public BMS Bms;
	public PApplet p;
	public float Mean = 0, Variance, VarianceR, VarianceG, VarianceB, VarianceBR, s_mult, currentSliderPos = 30, ix, iy;
	public PImage img, img2;
	//mean, mean_, meanGx, meanGy, blurX, blurY, threshold, variance, varianceR, varianceG, varianceB, varianceBR, Gaussian, 
	//kMeans, kNearest, sobel, sobelx, sobely, sobel2, sobel2x, sobel2y, sobelMax, sobelMin, sobelG, gradientB, blur, combined, current, canny, cannyT, temp;
	public int cutoff = 250, gridRes = 1, Type = 3, pointMax = 30, min = 40, max = 10, 
			contourType = 1, linesCompleted, Mode = 4, thresh = 10, pixelThresh = 40,
			pixelThresh1 = 20,shaderId = -1;
	public String currentParameter, currentS, folderLocation, audioLocation, videoLocation;

	public boolean state, bdown, slidersState, mdown1, pieSliders, classicSliders, getCam, 
	loadImage, loadVideo, getScreenShot, recorder, dataFolder, menus, applyShader;
	public int processes, function = -1;
	public ArrayList<String>workFlowLabels=new ArrayList<String>();
	public String []ddLabels = {"Blur", "BlurS", "Canny", "Canny2", "Canny3", "Canny4", "CannyTrim", "CannyTrim1", 
			"GaussianS", "Gaussian 3", "Gaussian 5", "Mean", "Variance", "Sobel", "Sobel2a", 
			"Sobel2b", "Sobel Max", "Sobel Max2", "Sobel Max3", "Sobel Max4", "SobelG", "SobelG1", };

	public String []menuLabels = {"Load", "Save Workflow", "Save Image", "Restore Menus", "State"};
	public String []sobelSliderLabels = {"Mult", "Range"};
	public String []sobel2aliderLabels = {"Mult", "Range"};
	public String []sobel2bliderLabels = {"Mult", "Range", "Type"};
	public String []sobelSliderLabels1 = {"Mult", "Range", "Threshold"};
	public String []sobelGSSliderLabels = {"Mult", "Range"};
	public String []sobelSSliderLabels = {"Mult", "Range"};
	public String []sobelASliderLabels = {"Mult", "Range"};
	public String []sobel2SliderLabels = {"Mult", "Range"};
	public String []sobelMaxSliderLabels = {"Mult", "Range"};
	public String []sobelMax2SliderLabels = {"Mult", "Range"};
	public String []sobelMax3SliderLabels = {"Mult", "Range"};
	public String []sobelMax4SliderLabels = {"Mult", "Range"};
	public String []meanSliderLabels = {"Range"};
	public String []meanASliderLabels = {"Mult", "Range"};
	public String []meanSSliderLabels = {"Mult", "Range"};
	public String []varianceSliderLabels = {"Mult", "Range"};
	public String []blurSliderLabels = {"Mult", "Range"};
	public String []blurSSliderLabels ;
	public String []sharpenSliderLabels = {"Mult", "Range"};
	public String []substractSliderLabels ;
	public String []gaussian3SliderLabels ;
	public String []gaussian5SliderLabels ;
	public String []gaussianSSliderLabels = {"Mult", "Range"};
	public String []combineSliderLabels ;
	public String []cannySliderLabels = {"Mode", "Thresh"};
	public String []canny2SliderLabels = {"Mode", "Thresh", "Thresh1"};
	public String []canny3SliderLabels = {"Mode", "Thresh"};
	public String []cannyTSliderLabels = {"Type", "Thresh"};
	public String []cannyT1SliderLabels = {"Type", "Thresh"};
	public String []cannyT2SliderLabels = {"Type", "Thresh", "Thresh2"};
	public String []blendSliderLabels ;
	public String []variance2SliderLabels  = {"Range"};
	public String []variance3SliderLabels  = {"Range"};
	public String []menuLabels2 = {"Run"};
	public String []menuLabels3 = {"Clear"};
	public String []menuLabels4 = {"Run All"};
	public String [][]kernelLabels = {{"Range"}, 
			{"Mult", "Output"}, 
			{"Mult", "Range"}, 
			{"Mult", "Range", "Output"}, 
			{"Mult", "Thresh1", "Thresh2"}, 
			{"Mult", "Range"}, 

	};
	public String [] functions = {"Blur", "BlurS", "Gaussian 3", "Gaussian 5", "GausianS", "Mean", "MeanS", 
			"Variance", "VarianceS", "Sobel", "SobelS", "Sobel 2", "Sobel Max", "Canny", "CannyS"};
	public HashMap<String, String[]> workFlowSliders = new HashMap<String, String[]>();
	public String []sliderLabels, currentFolder, currentWorkFlow;
	public String file, folder, location;
	public PImage currentImage;
	public PGraphics canvas2;
	public Menu menu, run, runAll, clear;
	public Dropdown workFlowDD;
	public SliderBox sobelSlider, meanSlider, varianceSlider, blurSlider, sobelMaxSlider, sharpenSlider, gaussianSlider ;
	public float currentF;
	int currentImageIndex, counter;
	public int firstImageIndex;
	public boolean update = true, imageF = true, videoF, audioF, camF, mdown, m2down, updateSliders, sDown, docked;
	public final int IMAGES = 0, VIDEOS = 1, AUDIO = 2, MOVIE = 3, SOUND = 4;
	public boolean load = true, toggle, iUpdate, iUpdate2, iUpdate3, iUpdate4, wUpdate;
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
	public fileOutput output, outputWF;
	public tab info, sliders, workFlow, workFlow1, workFlow3;
	public PGraphics canvas, camera, c1, c2, pass1, pass2, pass3, pass4;
	public PShader mean, Sobel, Blur, GaussianS, Sobel1, Sobel2, SobelG, SobelG1, 
	SobelMax, SobelMax2, Canny, CannyTrim, CannyTrim1, variance, 
	gaussian, gaussian1, gaussian2,tempShader,sobel,canny;
	public String []shaders = {"sobel.glsl", "blur.glsl", "gBlur.glsl"};
	public String imPath = "";
	public String shaderPath = "shaders\\";
	//currentField;
	public String [] instructions;
	public Dock myDock;
	ScreenShot screen;
	Webcam cam;
	Movie myMovie;
	tab meanTab,varianceTab,sobelTab,cannyTab,gaussianTab,gaussian1Tab,gaussian2Tab,
	blurTab,shaderSliders;

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

	public int [][]meanX = {{1, 1, 1}, 
			{0, 0, 0}, 
			{1, 1, 1}};

	public int [][]meanY = {{1, 1, 1}, 
			{2, 0, 2}, 
			{1, 0, 1}};

	public float [][]gaussian3 = {{1, 2, 1}, 
			{2, 4, 2}, 
			{1, 2, 1}};

	public float [][]gaussian5 = {{1, 4, 7, 4, 1}, 
			{4, 16, 126, 16, 4}, 
			{7, 26, 41, 26, 7}, 
			{4, 16, 26, 16, 4}, 
			{1, 4, 7, 4, 1}};

	public int [][]neighbours;
	public float [][]gradient;

	public imageProcessor(BMS bms) {
		Bms = bms;
		p = bms.applet;
		img = p.createImage(p.width, p.height, p.ARGB);

		initCanvases();
		initHashMap();
	};

	public imageProcessor(String s) {
		img = p.loadImage(s);

		initCanvases();
		initHashMap();
	};

	public imageProcessor(String s, BMS bms) {
		Bms = bms;
		p = bms.applet;
		img = p.loadImage(s);
	};

	public imageProcessor(PImage p) {
		img = new PImage(p.width, p.height, PConstants.ARGB);
		img.pixels = p.pixels;
		initHashMap();
	};

	public imageProcessor(PImage p1, BMS bms) {
		Bms = bms;
		p = bms.applet;
		img = new PImage(p1.width, p.height, PConstants.ARGB);
		img.pixels = p1.pixels;
		initHashMap();
	};

	public imageProcessor(int w, int h) {
		img = new PImage(w, h, PConstants.ARGB);
		initHashMap();
	};

	public imageProcessor(int w, int h, BMS bms) {
		Bms = bms;
		p = bms.applet;
		img = new PImage(w, h, PConstants.ARGB);
		initHashMap();
	};

	//  public imageProcessor() {
	//  };



	public void initCanny() {
	};

	public void initScreenShot() {
		screen = new ScreenShot(Bms, p.displayWidth, p.displayHeight);
	};

	public void initScreenShot(int x, int y) {
		screen = new ScreenShot(Bms, x, y);
	};

	public void initWebcam(int x, int y) {
		cam = new Webcam(Bms);
	};

	public void loadShaders(){
		Blur = p.loadShader(shaderPath+"blur.glsl");
		mean = p.loadShader(shaderPath+"mean.glsl");
		variance = p.loadShader(shaderPath+"variance.glsl");
		sobel = p.loadShader(shaderPath+"sobel.glsl");
		Sobel1 = p.loadShader(shaderPath+"sobel1.glsl");
		Sobel2 = p.loadShader(shaderPath+"sobel2.glsl");
		SobelG = p.loadShader(shaderPath+"sobelG.glsl");
		SobelG1 = p.loadShader(shaderPath+"sobelG1.glsl");
		SobelMax = p.loadShader(shaderPath+"sobelMax.glsl");
		canny = p.loadShader(shaderPath+"canny3.glsl");
		Canny = p.loadShader(shaderPath+"canny.glsl");
		CannyTrim = p.loadShader(shaderPath+"cannyTrim.glsl");
		CannyTrim1 = p.loadShader(shaderPath+"cannyTrim1.glsl");
		gaussian = p.loadShader(shaderPath+"gaussian.glsl");
		gaussian1 = p.loadShader(shaderPath+"gaussian1.glsl");
		gaussian2 = p.loadShader(shaderPath+"gaussian2.glsl");
	};

	public void initCanvases() {
		c1 = p.createGraphics(img.width, img.height);
		c2 = p.createGraphics(img.width, img.height);
		pass1 = p.createGraphics(img.width, img.height);
		pass2 = p.createGraphics(img.width, img.height);
		pass3 = p.createGraphics(img.width, img.height);
		pass4 = p.createGraphics(img.width, img.height);

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
		p.println("initcanvases");
	};

	public void initCanvasesP2D() {
		c1 = p.createGraphics(img.width, img.height);
		c2 = p.createGraphics(img.width, img.height);
		pass1 = p.createGraphics(img.width, img.height);
		pass2 = p.createGraphics(img.width, img.height);
		pass3 = p.createGraphics(img.width, img.height);
		pass4 = p.createGraphics(img.width, img.height);

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
		p.println("initcanvases");
	};

	public void initMenus() {

		//menu = new Menu(p.width-250, 23, 100, 30, 0, menuLabels, Bms);

		info = new tab(0, p.height - 170, p.width, 150, "Images", Bms);
		sliders = new tab(p.width-100, 68, 100, p.height - 240, "Sliders", Bms);
		PGraphics pg = p.createGraphics(200, (int)sliders.h);
		sliders.canvases.add(pg);

		//    sliders.title.w -=20;
		Button b1 = new Button(80, 0, 20, 20, "<", Bms);
		b1.setclassicBar();
		sliders.add(b1);
		workFlow = new tab(0, 68, 100, p.height - 240, "Workflow", Bms);
		TextArea m1 = new TextArea(0, 0, 100, workFlow.h - 60, Bms);
		run = new Menu(0, workFlow.h-60, 100, menuLabels2, Bms);
		clear = new Menu(50, workFlow.h-60, workFlow.w-run.w, menuLabels3, Bms);
		runAll = new Menu(0, workFlow.h-40, workFlow.w, menuLabels4, Bms);
		workFlow.add(m1);

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
		input = new fileInput(Bms);
		output = new fileOutput(Bms);
		info.toggle = true;
		sliders.toggle = true;
		workFlow.toggle = true;
		info.draggable = true;
		info.scrollable = true;
		workFlow.draggable = true;
		sliders.draggable = true;
		sliders.scrollable = true;
		canvas = p.createGraphics(p.width-200, p.height - 190);

		initHashMap();
		initDock1();
		initFiles();

		workFlow1 = new tab(0, 68, 100, p.height - 240, "Workflow", Bms);
		//workFlow1.parentDock = myDock;
		workFlowDD = new Dropdown(0, 0, 100, "Function", ddLabels, 0, Bms);
		//workFlow.add(1,workFlowDD);
		workFlow1.add(workFlowDD);
		workFlow1.toggle = true;
		Button b2 = new Button(workFlow1.w-20, 20, 20, 20, Bms);
		b2.setclassicBar();
		workFlow1.add(b2);
		Button b3 = new Button(workFlow1.w-40, 20, 20, 20, Bms);
		b3.setclassicBar();
		workFlow1.add(b3);
		workFlow1.toggle = true;
		workFlow.toggle = true;
		Bms.dock.add(workFlow);
		Bms.dock.add(workFlow1);
		//blur
		shaderSliders = new tab(0, 90, 200, 500, "Blur", Bms);
		String[] tabstates = {"Mean","Variance","Sobel","Canny","Gaussian",
				"Gaussian 2","Gaussian 3"};
		shaderSliders.addStates(tabstates);
		//shaderSliders.toggle = true;
		shaderSliders.draggable = true;
		//blur
		String[] sl1Labels = {"mult", "range"};
		SliderBox sl1 = new SliderBox(40, 20, 90, 20, 10, sl1Labels,Bms);
		//sl1.setPieSquare();
		sl1.setClassicBar();
		shaderSliders.add(0,sl1);
		//mean
		String[] sl2Labels = {"mult", "range", "min"};
		SliderBox sl2 = new SliderBox(40, 20, 90, 20, 10, sl2Labels,Bms);
		//sl1.setPieSquare();
		sl2.setClassicBar();
		shaderSliders.add(1,sl2);
		// variance
		String[] sl3Labels = {"mult", "intensity", "min"};
		SliderBox sl3 = new SliderBox(40, 20, 90, 20, 10, sl3Labels,Bms);
		//sl1.setPieSquare();
		sl3.setClassicBar();
		shaderSliders.add(2,sl3);
		//sobel1
		String[] sl4Labels = {"mult", "intensity", "min","Type"};
		SliderBox sl4 = new SliderBox(40, 20, 90, 20, 10, sl4Labels,Bms);
		//sl1.setPieSquare();
		sl4.setClassicBar();
		shaderSliders.add(3,sl4);
		//sobel2
		//String[] sl5Labels = {"mult", "intensity", "min","Type"};
		//SliderBox sl5 = new SliderBox(40, 20, 90, 20, 10, sl5Labels,Bms);
		////sl1.setPieSquare();
		//sl5.setClassicBar();
		//shaderSliders.add(4,sl5);
		////sobel3
		//String[] sl6Labels = {"mult", "intensity", "min","Type"};
		//SliderBox sl6 = new SliderBox(40, 20, 90, 20, 10, sl6Labels,Bms);
		////sl1.setPieSquare();
		//sl6.setClassicBar();
		//shaderSliders.add(5,sl6);
		//canny
		String[] sl7Labels = {"mult", "intensity", "min", "vidPos", "type", "Blur R", 
				"Sigma", "Size"};
		SliderBox sl7 = new SliderBox(40, 20, 90, 20, 10, sl7Labels,Bms);
		//sl1.setPieSquare();
		sl7.setClassicBar();
		shaderSliders.add(4,sl7);
		//gaussian1
		String[] sl8Labels = {"Range", "Sigma", "Size"};
		SliderBox sl8 = new SliderBox(40, 20, 90, 20, 10, sl8Labels,Bms);
		//sl1.setPieSquare();
		sl8.setClassicBar();
		shaderSliders.add(5,sl8);
		//gaussian2
		String[] sl9Labels = {"Range", "Sigma", "Size"};
		SliderBox sl9 = new SliderBox(40, 20, 90, 20, 10, sl9Labels,Bms);
		//sl1.setPieSquare();
		sl9.setClassicBar();
		shaderSliders.add(6,sl9);
		//gaussian3
		String[] sl10Labels = {"Range", "Sigma", "Size"};
		SliderBox sl10 = new SliderBox(40, 20, 90, 20, 10, sl10Labels,Bms);
		//sl1.setPieSquare();
		sl10.setClassicBar();
		shaderSliders.add(7,sl10);
		Bms.add(shaderSliders);



		//b.add(
	};

	public void initDock1() {
		//myDock.add(info);
		//myDock.add(sliders);
		//myDock.add(workFlow);
		//myDock.add(workFlow1);
	};

	public void initFiles() {
		imPath = p.dataPath("images");
		//shaderPath = p.dataPath("shaders");
	};

	public void initHashMap() {
		workFlowSliders.put("Blur", blurSliderLabels);
		workFlowSliders.put("BlurS", blurSSliderLabels);
		workFlowSliders.put("Gaussian 3", gaussian3SliderLabels);
		workFlowSliders.put("Gaussian 5", gaussian5SliderLabels);
		workFlowSliders.put("GaussianS", gaussianSSliderLabels);
		workFlowSliders.put("Mean", meanSliderLabels);
		workFlowSliders.put("MeanS", meanSSliderLabels);
		workFlowSliders.put("Sobel", sobelSliderLabels);
		workFlowSliders.put("SobelGS", sobelGSSliderLabels);
		workFlowSliders.put("SobelS", sobelSSliderLabels);
		workFlowSliders.put("SobelS2", sobelSSliderLabels);
		workFlowSliders.put("SobelS3", sobelSSliderLabels);
		workFlowSliders.put("Sobel 2", sobel2SliderLabels);
		workFlowSliders.put("Sobel Max", sobelMaxSliderLabels);
		workFlowSliders.put("Sobel Max2", sobelMaxSliderLabels);
		workFlowSliders.put("Sobel Max3", sobelMaxSliderLabels);
		workFlowSliders.put("Sobel Max4", sobelMaxSliderLabels);
		workFlowSliders.put("Canny", cannySliderLabels);
		workFlowSliders.put("CannyTrim", cannyTSliderLabels);
		workFlowSliders.put("CannyTrim1", cannyT1SliderLabels);
		workFlowSliders.put("Combine", combineSliderLabels);
		workFlowSliders.put("Substract", substractSliderLabels);
		workFlowSliders.put("Blend", blendSliderLabels);
		workFlowSliders.put("Variance", varianceSliderLabels);
		workFlowSliders.put("Variance2", variance2SliderLabels);
		workFlowSliders.put("Variance3", variance3SliderLabels);
	};

	Object parseParameter(String parameter) {
		try {
			return Integer.parseInt(parameter);
		} 
		catch(NumberFormatException e) {
			try {
				return Float.parseFloat(parameter);
			} 
			catch(NumberFormatException e1) {
				try {
					Field field = this.getClass().getField(parameter);
					return field.get(this);
				} 
				catch (NoSuchFieldException e2) {
					return null;
				}
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
		} 
		catch(NumberFormatException e) {
			try {
				Float.parseFloat(parameter);
				return float.class;
			} 
			catch(NumberFormatException e1) {

				if (parameter!=null)return PImage.class;
				else return null;
			}
		}
	};

	public void loadInput() {
		if (location!=null) {
			String loc = "";
			if (file!=null||folder!=null) {
				if (file!=null)loc = file;
				else loc = folder;
			}
			load = false;
			location = null;
		}
	};

	public PGraphics display() {

		if (toggle) {
			//      sliders.toggleU(0,this,"updateSliders");
			if (!docked) {
				// sliders
				// Bms.dock
				docked = true;
			}
			//if(!info.sliderh.mdown)myDock.logic();
			//myDock.drawItems();
			//println("img disp,00");

			save();


			if (menus) {

				workFlowLogic();
				infoTabLogic();

				info.displayTab();
				//info.drawDynamicImages(info.images,currentImageIndex);
				slidersTabLogic();

				if (!state) {
					//workFlow.toggle = true;
					//workFlow1.toggle = false;
					//println("///");
					workFlow.displayTab();
				} else {
					workFlow1.displayTab();
					//workFlow.toggle = false;
					//workFlow1.toggle = true;
				}

				//if(!state)runWorkFlow();
				//else runWorkFlow1();
				//if(function==
				//displayCanvas();
				//sliders.displayTab();
			}
			if (cam!=null&&function != 4&&cam.cam.available()){
				cam.stop();
				cam = null;
			}
			if(function>-1)shaderSliders.toggle = true;
			else shaderSliders.toggle = false;

			if (function==0)loadImage();
			if (function==1)loadFolder();
			if (function==2)getScreenShot();
			if (function==3)loadVideo();
			if (function==4)getCam();

			if (shaderId==-1)simple();
			if (shaderId==0)blur();
			if (shaderId==1)mean();
			if (shaderId==2)variance();
			if (shaderId==3)sobel();
			if (shaderId==4)canny();
		}
		if (Bms.fmenu.getClose()) {
			function = -1;
		}
		displayMenus();
		//image(c1,0,0);
		return c1;
	};

	public void displayMenus(){
		if(shaderId>-1){
			shaderSliders.state = shaderId;
		}
	};

	public void displayCanvas() {
		if (!p.mousePressed)mdown = false;

		if (currentImage!=null&&canvas!=null) {
			if (iUpdate) {
				canvas.beginDraw();
				canvas.background(50);
				canvas.image(currentImage, 0, 0);
				canvas.endDraw();

				iUpdate = false;
			}
			if (iUpdate2&&names.size()>0) {
				if ((firstImageIndex + currentImageIndex-3)<names.size()&&(firstImageIndex + currentImageIndex-3)>0)
					currentImage = p.loadImage(names.get(firstImageIndex + currentImageIndex-3));
				canvas.beginDraw();
				canvas.background(50);
				canvas.image(currentImage, 0, 0);
				canvas.endDraw();
				iUpdate2 = false;
			}
		}

		if (iUpdate4&&imagesWF.size()>0) {
			currentImage = imagesWF.get(imagesWF.size()-1);
			canvas.beginDraw();
			canvas.background(50);
			canvas.image(currentImage, 0, 0);
			canvas.endDraw();
			iUpdate4 = false;
		} else if (iUpdate4&&imagesWF.size()==0) {
			PApplet.println("Workflow error...");
			iUpdate4 = false;
		}
		p.image(canvas, workFlow.w, 21);
	};

	public void slidersTabLogic() {
		int a = 200;
		int b = 100;
		int c = 40;
		//    if(sliders.click(0))slidersState++;
		//    if(slidersState==2)slidersState = 0;
		//    sliders.toggleU(0,this,"slidersState");
		boolean k = false;
		if (sliders.toggle(0, this, "slidersState")) {
			if (!slidersState)slidersState = true;
			else slidersState = false;
			updateSliders = true;
		}

		if (slidersState&&updateSliders) {
			//      p.println("img sliderstabl 00");
			sliders.canvasIndex = 1;

			//      sliders.title.w = a-20;
			sliders.title.w = a;

			sliders.setPos(p.width -a, sliders.y);
			sliders.title.setPos(sliders.x, sliders.y);
			sliders.w = a;
			sliders.buttons.get(0).x = sliders.w-20;
			//      sliders.sliderh.w = a;
			sliders.sliderv.x = sliders.w-10;
			//      for(int i=0;i<sliders.sliderBoxes.size();i++){
			//        SliderBox s = sliders.sliderBoxes.get(i);
			//        if(s!=null)s.menu.x = c;
			//      }
			//
			//      for(int i=1;i<sliders.buttons.size();i++){
			//        if(sliders.buttons.size()>1){
			//          Button b1 = sliders.buttons.get(i);
			//          b1.x = c;
			//        }}
		} else if (!slidersState&&updateSliders) {
			//      p.println("img sliderstabl 01");
			sliders.canvasIndex = 0;
			sliders.setPos(p.width -b, sliders.y);
			sliders.title.w = b;
			sliders.title.setPos(p.width -b, sliders.y);
			sliders.w = b;
			sliders.buttons.get(0).x = sliders.w-20;
			sliders.sliderh.w = b;
			sliders.sliderv.x = sliders.w-10;

			//      for(int i=0;i<sliders.sliderBoxes.size();i++){
			//        SliderBox s = sliders.sliderBoxes.get(i);
			//        if(s!=null){
			//          s.menu.x = 0;
			//
			//        }
			//      }
			//      for(int i=1;i<sliders.buttons.size();i++){
			//        Button b1 = sliders.buttons.get(i);
			//        b1.x = 0;
			//      }
		}

		for (int i=0; i<sliders.sliderBoxes.size(); i++) {
			SliderBox s = sliders.sliderBoxes.get(i);
			if (s!=null) {
				for (int j=0; j<s.menu.sliders.size(); j++) {
					Slider s1 = s.menu.sliders.get(j);
					if (s1.label=="Mult")s1.set(1, 50);
					if (s1.label=="Range")s1.setint(1, 10);
					if (s1.label=="Thresh")s1.set(1, 255);
					if (s1.label=="Type")s1.setint(1, 10);
				}
			}
		}
		if (updateSliders)updateSliders = false;
		if (!p.mousePressed)mdown1 = false;
	};

	public void infoTabLogic() {
		if (thumbnails.size()>0)info.sliderh.setint(0, thumbnails.size(), this, "firstImageIndex");
		if (!p.mousePressed&&info.posTab()) {
			currentImageIndex = PApplet.floor(PApplet.map(p.mouseX, 0, info.w, 0, 7));
		} else if (info.posTab()&&!info.sliderh.mdown)iUpdate2 = true;

		if (info.sliderh.mdown&&p.mousePressed&&info.posTab()&&!iUpdate&&p.mouseX!=p.pmouseX) {
			iUpdate = true;
			mdown = true;
		}

		if (names!=null&&names.size()>0&&iUpdate) {

			for (int i=firstImageIndex-4; i<firstImageIndex+5; i++) {
				if (i>0&&i<thumbnails.size()) {
					PImage p1 = thumbnails.get(i);

					if (!info.images.contains(p1)) {
						info.images.add(p1);
					}
					if (info.images.size()>9)info.images.remove(0);
				}
			}
		}
	};

	public void workFlowLogic() {
		Dropdown d1 = null;
		if (state)d1 = workFlow1.dmenus.get(workFlow1.dmenus.size()-1);

		if (d1!=null&&d1.toggle) {

			if (state&&workFlow1.buttons.get(0).toggle()&&d1.index>-1) {

				img = currentImage;

				workFlow1.add(new Dropdown(0, workFlow1.dmenus.size()*20, 100, "Function " + workFlow1.dmenus.size(), ddLabels, 0, Bms));
				workFlow1.buttons.get(0).y+=20;
				workFlow1.buttons.get(1).y+=20;
				//p.println(workFlow1.Dropdowns.get(0).)
				if (workFlowSliders.get(d1.label)!=null) {
					SliderBox s = null;
					Button b1 = null; 
					if (sliders.buttons.size()>0&&sliders.buttons.get(0).toggle) {
						s = new SliderBox(0, 30+ currentSliderPos, 90, 5, workFlowSliders.get(d1.label), sliders);
						b1 = new Button(0, 30+ currentSliderPos-20, 90, 20, d1.label);
						b1.setclassicBar();
					} else {
						s = new SliderBox(30, 30+ currentSliderPos, 90, 5, workFlowSliders.get(d1.label), sliders);
						b1 = new Button(30, 30+ currentSliderPos-20, 90, 20, d1.label);
						b1.setclassicBar();
					} 
					if (classicSliders) s.setClassicBar();
					if (pieSliders)s.setPieSquare();
					s.visible = true;
					s.tooltip = null;
					sliders.add(s);
					b1.border = false;
					sliders.add(b1);
				} else {
					Button b1;
					if (sliders.buttons.size()>0&&sliders.buttons.get(0).toggle) {

						b1 = new Button(0, 30+ currentSliderPos-20, 90, 20, d1.label);
						b1.setclassicBar();
					} else {
						b1 = new Button(30, 30+ currentSliderPos-20, 90, 20, d1.label);
						b1.setclassicBar();
					} 
					sliders.buttons.add(b1);
					sliders.sliderBoxes.add(null);
				}

				SliderBox s1 = null;
				if (sliders.sliderBoxes.get(sliders.sliderBoxes.size()-1)!=null) {
					s1 = sliders.sliderBoxes.get(sliders.sliderBoxes.size()-1);

					currentSliderPos = s1.y+s1.h ;
				} else {
					currentSliderPos += 40;
				}
			}

			if (workFlow1.buttons.get(1).toggle()&&workFlow1.dmenus.size()>1) {
				if (workFlow1.dmenus.size()>=1)workFlow1.dmenus.remove(workFlow1.dmenus.size()-1);
				if (sliders.sliderBoxes.size()>=1) {
					SliderBox s1 ;
					if (sliders.sliderBoxes.get(sliders.sliderBoxes.size()-1)!=null) {
						s1 = sliders.sliderBoxes.get(sliders.sliderBoxes.size()-1);
						currentSliderPos = s1.y-s1.h-20;
						if (currentSliderPos<sliders.y+20)currentSliderPos = currentSliderPos+20;
						sliders.sliderBoxes.remove(sliders.sliderBoxes.size()-1);
						sliders.buttons.remove(sliders.buttons.size()-1);
					} else {
						sliders.sliderBoxes.remove(sliders.sliderBoxes.size()-1);
						sliders.buttons.remove(sliders.buttons.size()-1);
						currentSliderPos -= 20;
					}

					workFlow1.buttons.get(0).y-=20;
					workFlow1.buttons.get(1).y-=20;
				}
			}
		}
		//if(info.sliderh.mdown)iUpdate2 = true;

		// if(workFlow1.menus.items.get(0).click(workflow.getMouse())){

		// }
	};

	public void workFlowLogic1() {
		//if()
	};

	public void runWorkFlow() {
		if (run.toggle(0)&&!state) {
			PApplet.println("workflow 0");
			imagesWF = new ArrayList<PImage>();
			img = currentImage;

			if (workFlow.textareas.get(0).tempLine!=null) {

				PApplet.println("Run", workFlow.textareas.get(0).textArea);
				String[] s = PApplet.splitTokens(workFlow.textareas.get(0).tempLine, ",");
				String[] s1 = new String[1];
				currentWorkFlow = s1;
				s1[0] = workFlow.textareas.get(0).textArea[0];
				iUpdate3 = true;
				workflow(s1);
				//p.println(s1);
			} else PApplet.println(false);
		}

		if (clear.toggle(0)) {
			currentWorkFlow = null;
			workFlow.textareas.get(0).reset();
		}
	};

	public void runWorkFlow1() {
		if (run.toggle(0)&&state) {
			imagesWF = new ArrayList<PImage>();
			img = currentImage;

			if (sliders.sliderBoxes.size()>0) {
				String log = "Run state1";

				String []wf = new String[sliders.sliderBoxes.size()] ;
				PApplet.println("Run state1", workFlow.textareas.get(0).getValue());
				for (int i=0; i<sliders.sliderBoxes.size(); i++) {
					String s = workFlow1.dmenus.get(i).label + "(";
					SliderBox sl = null;
					if (sliders.sliderBoxes.get(i)!=null)
						sl = sliders.sliderBoxes.get(i);
					for (int j=0; j<sl.menu.sliders.size(); j++) {
						Slider slider = sl.menu.sliders.get(j);

						float v1 = -1;
						int v2 = -1;

						if (slider.label=="Mult"||slider.label=="Thresh") {
							if (j<sl.menu.sliders.size()-1)s += slider.value + ",";
							else s += slider.value ;
						} else {
							if (j<sl.menu.sliders.size()-1)s += (int)slider.value + ",";
							else s += (int)slider.value ;
						}
					}
					s += ")";
					if (s!=null)wf[i] = s;
				}
				PApplet.println(wf);
				workflow(wf);
			} else PApplet.println("No workflow available");
		}

		if (clear.toggle(0)&&state) {
			currentWorkFlow = null;

			while (sliders.buttons.size()>1)sliders.buttons.remove(sliders.buttons.size()-1);
			while (sliders.sliderBoxes.size()>0)sliders.buttons.remove(sliders.sliderBoxes.size()-1);
		}
		// if(runAll.toggle(0)){

		//   for(int i=0;i<names.size();i++){

		//   }
		// }
	};

	public PGraphics applyShader(PShader s, HashMap h) {
		if (applyShader) {
			canvas.beginDraw();
			canvas.shader(s);
			p.image(img, 0, 0);
			canvas.endDraw();
		}
		return canvas;
	};

	public void loadImages() {
		input.listen();
		if (input.values!=null) {
			PApplet.println("input", input.values.length);
			currentFolder = input.values;
			//names = new String[input.values.length];
			if (images.size()<input.values.length)
				for (int i=0; i<input.values.length; i++) {
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

						PImage thumbnail = p.loadImage(s1);

						thumbnail.resize(150, 90);
						thumbnails.add(thumbnail);
						names.add(s1);
					}
				}
			if (names.size()>0) {
				PApplet.println("step 1", "first index:", firstImageIndex);
				info.sliderh.valuex = 0;
				info.sliderh.value = 0;
				if (firstImageIndex<0)firstImageIndex=0;
				currentImage = p.loadImage(names.get(firstImageIndex));
				iUpdate = true;
			} else {
				PApplet.println("No images found...");
				names = new ArrayList<String>();
			}
			input.values = null;
		}
	};

	public void loadFolder() {
		Bms.Folder.listen();
	};

	public void loadImage() {
		Bms.File.listen();
		if (Bms.File.img!=null)img = Bms.File.img;
	};

	public void loadVideo() {
		Window f = Bms.fmenu;
		if (videoLocation==null) {
			Bms.File.listen();
			if (f.currentf!=null&&f.select.toggle(0)) {
				videoLocation = f.currentf;
				if (myMovie==null) {
					myMovie = new Movie(p, videoLocation);
				} else {
					myMovie.play();
					img = myMovie;
				}
			}
		} else {
			if (myMovie==null)myMovie = new Movie(p, videoLocation);
			else {
				myMovie.play();
				img = myMovie;
			}
		}
	};
	public void loadVideo(String s) {
		myMovie = new Movie(p, s);
		myMovie.play();
		if (Bms.fmenu.getClose()) {
			//      p.println("imgp vid close");
			function = -1;
		}
	};


	public void loadSound() {
		Bms.File.listen();
		if (Bms.fmenu.getClose())function = -1;
	};

	public void getScreenShot() {
		if (screen == null) {
			initScreenShot();
			p.println("get screen");
			//screen = new ScreenShot();
		} else {
			img = screen.getScreenshot();
		}
	};

	public void getScreenShot(int w, int h) {
		if (function==2)
			if (screen == null)screen = new ScreenShot(Bms, w, h);
			else {
				img = screen.getScreenshot();
			}
	};

	public void getCam() {
		if (cam == null) {

			cam = new Webcam(Bms);
			p.println("getcam",cam);
			cam.cam.start();
			//println("imgp start cam");
		} else {
			//cam.display(c1);
			img = cam.getCam();
			//c1.beginDraw();
			//c1.image(img,0,0);
			//c1.endDraw();
			if(p.mousePressed)p.println("imgp read cam");
		}
	};

	public void save() {
	};

	public void workflow(String a) {
		String[] s = PApplet.splitTokens(a, "-");
		PApplet.println("workflow 0");
		for (int i=0; i<s.length; i++) {
			String s1 = s[i];

			//ArrayList<Integer> [] pIndex = strIndex(s1,"(",")");
			int [] pIndex = strIndex1(s1, "(", ")");
			String function = s1.substring(0, pIndex[0]);

			//String[]parameters = new String [pIndex[0].size()];
			String[]parameters = PApplet.splitTokens(s[i].substring(pIndex[0]+1, pIndex[1]), ",");
			parameters[parameters.length-1] =  parameters[parameters.length-1].substring(0, parameters.length-1);

			boolean image = false;
			Method method = null;
			try {
				method = this.getClass().getMethod(function, float.class, float.class);
				//imageProcessor instance = new imageProcessor();
				float result = (float) method.invoke(this, 1, 3);
				PApplet.println("result", result);
			} 
			catch (SecurityException e) {
				PApplet.println(function, "se");
			}
			catch (NoSuchMethodException e) {  
				PApplet.println(function, "nsm");
			}
			catch (IllegalAccessException e) {  
				PApplet.println(function, "nsm");
			}
			catch (InvocationTargetException e) {  
				PApplet.println(function, "nsm");
			}
			for (int j=0; j<parameters.length; j++) {

				float currentF = Float.parseFloat(parameters[j]);

				if (currentF>-10000000&&currentF<10000000) {
					PApplet.println(function, "f " + currentF);
				} else PApplet.println(function, "s " + parameters[j]);
			}
		}
	};

	public void workflow(String[] a) {
		if (!iUpdate3) {
			PApplet.println("something wrong..");
		}
		if (iUpdate3&&a!=null) {
			PApplet.println("workflow 0");
			String[] s = a;

			for (int i=0; i<s.length; i++) {
				String s1 = s[i];
				if (s[i].length()>0) {
					int [] pIndex = strIndex1(s1, "(", ")");
					String function = s1.substring(0, pIndex[0]);

					String[]parameters = PApplet.splitTokens(s[i].substring(pIndex[0]+1, pIndex[1]), ",");
					PApplet.print("Parameters", function +"(");
					String s2 = "";
					Class<?>[] parameterClasses = new Class<?>[parameters.length];
					Object[] parsedParameters = new Object[parameters.length];
					for (int j=0; j<parameters.length; j++) {
						//p.print(parameters[j]);

						parameterClasses[j] = getParameterClass(parameters[j]);
						parsedParameters[j] = parseParameter(parameters[j]);
						// s2+=parameterClasses[j]+" "+parameters[j];
						s2 += parameters[j];
						if (j<parameters.length-1)s2+=",";
					}
					PApplet.println(s2+")");

					update = true;
					try {
						Method method = this.getClass().getMethod(function, parameterClasses);
						method.invoke(this, parsedParameters);
						img = imagesWF.get(imagesWF.size()-1);
						workFlowLabels.add(function);
					} 
					catch (NoSuchMethodException e) {
						PApplet.println("nsm", function, parameterClasses[0]);
						e.printStackTrace();
					}
					catch(IllegalAccessException e) {
						PApplet.println("ia");
						e.printStackTrace();
					}
					catch( InvocationTargetException e) {
						PApplet.println("it...Check images");
						e.printStackTrace();
					}
				}
			}
			update = false;
		} else if (a==null) {
			iUpdate3 = false;
			PApplet.println("Please correct function...");
		}
	};

	public void workflow2(String[] a) {
		if (update&&a!=null) {
			String[] s = a;

			for (int i=0; i<s.length; i++) {
				String s1 = s[i];
				if (s[i].length()>0) {
					int [] pIndex = strIndex1(s1, "(", ")");
					String function = s1.substring(0, pIndex[0]);

					String[]parameters = PApplet.splitTokens(s[i].substring(pIndex[0]+1, pIndex[1]), ",");
					PApplet.print("p", function +"(");
					String s2 = "";
					Class<?>[] parameterClasses = new Class<?>[parameters.length];
					Object[] parsedParameters = new Object[parameters.length];
					for (int j=0; j<parameters.length; j++) {
						//p.print(parameters[j]);

						parameterClasses[j] = getParameterClass(parameters[j]);
						parsedParameters[j] = parseParameter(parameters[j]);
						// s2+=parameterClasses[j]+" "+parameters[j];
						s2 += parameters[j];
						if (j<parameters.length-1)s2+=",";
					}
					PApplet.println(s2+")");

					update = true;
					try {
						Method method = this.getClass().getMethod(function, parameterClasses);
						method.invoke(this, parsedParameters);
					} 
					catch (NoSuchMethodException e) {
						PApplet.println("nsm", function, "...Check Params?");
					}
					catch(IllegalAccessException e) {
						PApplet.println("ia") ;
					}
					catch( InvocationTargetException e) {
						PApplet.println("it", "This function is missing an image...");
						e.printStackTrace();
					}
				}
			}
			update = false;
		} else if (a==null)update = false;

		if (p.keyPressed&&p.key =='r')update = true;
	};


	float mult(float a, float b) {
		return a * b;
	};

	//String[] split(String s,String s1){
	//  String[]S = p.splitTokens(s.substring(pIndex[0]+1,pIndex[1]),",");
	//  parameters[parameters.length-1] =  parameters[parameters.length-1].replaceAll(")s+","");
	//  return 
	//};

	int [] strIndex1(String s) {
		int[]index = new int [2];
		for (int i=0; i<s.length(); i++) {
			char c = s.charAt(i);
			if (c=='(')index[0] = i;
			if (c==')')index[1] = i;
		}
		return index;
	};

	ArrayList [] strIndex(String s) {
		ArrayList[]index = new ArrayList [2];
		index[0] = new ArrayList<Integer>();
		index[1] = new ArrayList<Integer>();
		for (int i=0; i<s.length(); i++) {
			char c = s.charAt(i);
			if (c=='(')index[0].add(i);
			if (c==')')index[1].add(i);
		}
		return index;
	};

	int [] strIndex1(String s, String a, String b) {
		int[]index = new int [2];
		for (int i=0; i<s.length(); i++) {
			char c = s.charAt(i);
			if (c=='a')index[0] = i;
			if (c=='b')index[1] = i;
		}
		return index;
	};

	ArrayList [] strIndex(String s, String a, String b) {
		ArrayList[]index = new ArrayList [2];
		index[0] = new ArrayList<Integer>();
		index[1] = new ArrayList<Integer>();
		for (int i=0; i<s.length(); i++) {
			char c = s.charAt(i);
			if (c=='a')index[0].add(i);
			if (c=='b')index[1].add(i);
		}
		return index;
	};

	int findNext(String s) {
		int a = -1;

		return a;
	};

	public void set(PImage p1) {
		img = p1;
		c1 = p.createGraphics(img.width, img.height);
	};



	public void displayWF(String []s) {
		logic();
		workflow(s);
		if (imagesWF.size()>0)
			p.image(imagesWF.get(counter), ix, iy);
		p.text(workFlowLabels.get(counter), 10, 10);
	};


	public void displayWFCanvas(String []s) {
		logic();
		workflow2(s);
		//if(imagesWF.size()>0)p.image(imagesWF.get(imagesWF.size()-1),x,y);

		//if(imagesWF.size()>0)
		//p.image(imagesWF.get(counter),x,y);
	};

	public void logic() {
		int count = 0;
		if (p.mousePressed&&!mdown) {
			mdown = true;
			counter++;
		}

		if (counter<imagesWF.size()) {
			if (imagesWF.get(counter).width>p.width) {
				if (p.mouseX>0&&p.mouseX<p.width)
					ix = -(int)PApplet.map(p.mouseX, 0, p.width, 0, imagesWF.get(counter).width-p.width);
			}
			if (imagesWF.get(counter).height>p.height) {
				if (p.mouseY>0&&p.mouseY<p.height)
					iy = -(int)PApplet.map(p.mouseY, 0, p.height, 0, imagesWF.get(counter).height-p.height);
			}
		}

		if (!p.mousePressed) {
			mdown = false;
			m2down = false;
		}
		if (counter>imagesWF.size()-1)counter = 0;
		if (imagesWF.size()>0&&mdown&&!m2down) {
			m2down = true;
			PApplet.println(workFlowLabels.get(counter), imagesWF.size());
		}
	};

	public void logic2() {
		//if(p.mousePressed&&!mdown){
		//  mdown = true;
		//  counter++;
		//  p.println(counter);
		//}

		if (p.mouseX>0)counter = (int)PApplet.map(p.mouseX, 0, p.width, 0, imagesWF.size());
		//if(p.mouseX>0)counter = (int)p.map(p.mouseX,0,width,0,cell.edges.size());

		if (!p.mousePressed)mdown = false;
		//if(counter>cell.edges.size()-1)counter = 0;
		p.fill(0);
		p.text("c "+counter, 10, 20);
	};

	public PGraphics simple() {
		c1.beginDraw();
		//if (img!=null)

		c1.image(img, 0, 0);
		c1.endDraw();
		//image(img,0,0);
		//println("simple",c1);
		return c1;

	};

	public PGraphics mean() {
		shaderSliders.set(0,0,0,10);
		shaderSliders.set(0,1,0,10);
		c1.beginDraw();
		mean.set("mult",shaderSliders.getSlider(0,0).value);
		mean.set("mult",shaderSliders.getSlider(0,1).value);
		c1.shader(mean);

		c1.image(img, 0, 0);
		c1.endDraw();
		return c1;
	};

	public PGraphics canny() {
		c1.beginDraw();
		c1.background(0);
		c1.image(img, 0, 0);
		c1.endDraw();
		pass1.beginDraw();            
		pass1.shader(gaussian);  
		pass1.image(c1, 0, 0);
		pass1.endDraw();
		c2.beginDraw();
		c2.shader(canny);
		c2.image(pass1, 0, 0);
		c2.resetShader();
		c2.endDraw();
		return c1;
	};

	public PGraphics gaussian() {
		c1.beginDraw();
		c1.shader(gaussian);
		c1.image(img, 0, 0);
		c1.endDraw();
		return c1;
	};

	public PGraphics blur() {
		tab t = shaderSliders.states.get(0);
		t.set(0,0,0,10);
		t.set(0,1,0,10);
		c1.beginDraw();
		if(Blur==null){
			//Blur = loadShader("blur.glsl");
			p.println("blr nll shader");
		}
		else {
			//println("blr shader");
			Blur.set("mult",t.getSlider(0,0).value);
			Blur.set("range",t.getSlider(0,1).value);
		}
		c1.shader(Blur);
		c1.image(img, 0, 0);
		c1.endDraw();
		return c1;
	};

	public PGraphics variance() {
		tab t = shaderSliders.states.get(2);
		t.set(0,0,0,10);
		t.set(0,1,0,10);
		c1.beginDraw();
		variance.set("mult",t.getSlider(0,0).value);
		variance.set("mult",t.getSlider(0,1).value);
		c1.shader(variance);
		c1.image(img, 0, 0);
		c1.endDraw();
		return c1;
	};

	public PGraphics sobel() {
		tab t = shaderSliders.states.get(3);
		t.set(0,0,0,10);
		t.set(0,1,0,10);
		c1.beginDraw();
		sobel.set("mult",t.getSlider(0,0).value);
		sobel.set("range",t.getSlider(0,1).value);
		c1.shader(sobel);
		c1.image(img, 0, 0);
		c1.endDraw();
		return c1;
	};

	public PGraphics sharpen() {
		return c1;
	};

	public PGraphics poissonDist() {
		return c1;
	};

	public PGraphics dither() {
		return c1;
	};

	public void shaderLogic() {

		if (function==0){
			tempShader = mean;

		}
		if (function==1){

		}
		if (function==2){

		}
		if (function==3){

		}
		if (function==4){

		}
		if (function==0){

		}
	}

	public void setBms(BMS bms) {
		Bms = bms;
		p = bms.applet;
	};

	public void setBms(tab bms) {
		Bms = bms.Bms;
		p = bms.Bms.applet;
	};

	public void setVideoDLocation(String s) {
		videoLocation = s;
		dataFolder = true;
	};

	public void setVideoLocation(String s) {
		videoLocation = s;
	};
};
