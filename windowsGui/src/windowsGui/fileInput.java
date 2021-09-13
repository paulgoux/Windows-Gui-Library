package windowsGui;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import processing.core.*;
import processing.core.PApplet;

public class fileInput{
	BMS Bms;
	PApplet applet;
	public String value;
	public String location,fileExt,fileName,folderPath,ext,absolutePath,fileContent;
	public boolean click = false,folder,counted,fileSelect,openWindow,
			fileOpened,folderOpened,folderSelect, showDialog,closeDialog,file;
	public float x,y,w,h;
	public Window window;
	public String [] values;
	public File []files;
	public ArrayList<String>fileNames = new ArrayList<String>();
	public ArrayList<String>imageFiles = new ArrayList<String>();
	public ArrayList<String>videoFiles = new ArrayList<String>();
	public ArrayList<String>audioFiles = new ArrayList<String>();
	public ArrayList<String>textFiles = new ArrayList<String>();
	public ArrayList<String>officeDocs = new ArrayList<String>();
	public ArrayList<String>Files = new ArrayList<String>();
	public ArrayList<String>images = new ArrayList<String>();
	public ArrayList<String>otherFiles = new ArrayList<String>();
	public HashMap<String, ArrayList<Integer>> extensions = new HashMap<String, ArrayList<Integer>>();
	public PImage img;
	public String[] text;
	File tempFile;
	DialogueBox dbox;

	public fileInput(){
	};

	public fileInput(boolean b){
		folder = true;
	};

	public fileInput(Button b,boolean a){

		x = b.x;
		y = b.y;
		w = b.w;
		h = b.h;

		folder = true;
	};

	public fileInput(Button b,BMS bms){
		Bms = bms;
		applet = bms.applet;
		x = b.x;
		y = b.y;
		w = b.w;
		h = b.h;

		folder = true;
		window = new Window(200,200,200,200,"C:\\Users\\paul goux\\",bms);
	};

	public fileInput(Button b,boolean a,Object o){

		x = b.x;
		y = b.y;
		w = b.w;
		h = b.h;

		folder = true;

	};

	public fileInput(BMS bms){
		Bms = bms;
		applet = bms.applet;
		window = new Window(200,200,200,200,"C:\\Users\\paul goux\\",bms);
	};

	public fileInput(boolean b,BMS bms){
		Bms = bms;
		applet = bms.applet;
		folder = true;
		window = new Window(200,200,200,200,"C:\\Users\\paul goux\\",bms);
		window.launchable = false;
	};

	public fileInput(Button b,boolean a,BMS bms){
		Bms = bms;
		applet = bms.applet;

		x = b.x;
		y = b.y;
		w = b.w;
		h = b.h;

		folder = true;
		window = new Window(200,200,200,200,"C:\\Users\\paul goux\\",bms);
		window.launchable = false;
	};

	public fileInput(Button b,boolean a,Object o,BMS bms){
		Bms = bms;
		applet = bms.applet;

		x = b.x;
		y = b.y;
		w = b.w;
		h = b.h;

		folder = true;
		window = new Window(200,200,200,200,"C:\\Users\\paul goux\\",bms);
		window.launchable = false;
	};

	public void initDialog() {
		int w = 200;
		int h = 100;
		float x = applet.width/2-w/2;
		float y = applet.height/2-h/2;
		dbox = new DialogueBox(x,y,w,h,"Dialogue 1",Bms);
		Button b1 = new Button(10,dbox.h-60,w/2-20,30,"Yes",Bms);
		Button b2 = new Button(20+b1.w+10,dbox.h-60,w/2-20,30,"No",Bms);
		TextArea t1 = new TextArea(10,10,w-20,20,"File name...",Bms);

		dbox.add(b1);
		dbox.add(b2); 
		//		dbox1 = new DialogueBox(x,y,w,h,"Dialogue 2",Bms);
		//		dbox1.add(b1);
		//		dbox1.add(b2);
		//		dbox2 = new DialogueBox(x,y,w,h,"Dialogue 3",Bms);
		//		dbox2.add(b1);
		//		dbox2.add(b2);
		//		dbox2.main.add(t1);
	};

	public void setLink(Button b){
		x = b.x;
		y = b.y;
		w = b.w;
		h = b.h;
	};

//	public PImage getImage() {
//		if(!fileSelect&&!folderSelect)
//			if(!folder){
//
//
//			}
//			window.displayGrid();
//			window.windowLogic();
//
//		if(!window.toggle&&!window.close) {
//			//			applet.println("fin wnd close");
//			fileSelect = false;
//			folderSelect = false;
//			//					rest = true;
//		}
//	};
	
	public void setLocation(String s) {
		location = s;
		folderPath = s.substring(0, s.lastIndexOf("\\"));
		fileName = s.replace(folderPath+"\\", "");
		getExt(fileName);
		PApplet.println("Fname", folderPath);
		PApplet.println("fileName", fileName);
		PApplet.println("ext", ext);
		init();
	};
	
	public void setSketchLocation(String s) {
		location = applet.sketchPath()+"\\"+s;
		folderPath = s.substring(0, s.lastIndexOf("\\"));
		fileName = s.replace(folderPath+"\\", "");
		getExt(fileName);
		PApplet.println("Fname", folderPath);
		PApplet.println("fileName", fileName);
		PApplet.println("ext", ext);
//		initSketch();
	};
	
	public void init() {
		absolutePath = "C:\\";
	};
	public void initSketch() {
		absolutePath = applet.sketchPath();
	};
	
	public void initSketchPath() {
		absolutePath = applet.sketchPath();
	};

	public void getExt(String location) {

		int count = 0;
		fileName = location.substring(0, location.indexOf("."));
		ext = location.replace(fileName, "");
		ext = ext.replace(".", "");
		ext = ext.replace(fileName, "");
	};

	public String getLocation(){
		String s = location;
		location = null;
		return s;
	};

	public String getFolder(){
		String s = folderPath;
		location = null;
		return s;
	};

	public void saveLocation(String location){
		this.location = location;
	};
	
	public String[] listFiles(String s1) {
		String  []s = null;
		String path = s1;
		applet.println("Files", "Path: " + path);
		File directory = new File(path);
		File[] files = directory.listFiles();
		if (files!=null) {
			s = new String [files.length];
			applet.println("Files", "Size: "+ files.length);
			for (int i = 0; i < files.length; i++)
			{
				s[i] = files[i].getName();
				Files.add(s[i]);
				filterAll(s[i]);

				applet.println("Files", "FileName: " + files[i].getName());
			}
			applet.println("Images", images.size());
			applet.println("text", textFiles.size());
			applet.println("Audio", audioFiles.size());
			applet.println("Other", otherFiles.size());
		}else applet.println("no files");
		
		return s;
	};
	
	public void filterAll(String s) {
		filterImage(s);
		filterText(s);
		filterAudio(s);
		filterVideo(s);
		filterOthers(s);
	};
	
	public void filterImage(String s) {
		if (s.contains("jpg")
				||s.contains("JPG")
				||s.contains("bmp")
				||s.contains("BMP")
				||s.contains("png")
				||s.contains("PNG")
				||s.contains("gif")
				||s.contains("GIF")) {
			if (!images.contains(s))images.add(s);
		}
	};

	public void filterText(String s) {
		if (s.contains("txt")
				||s.contains("doc")
				||s.contains("docx")
				||s.contains("csv")) {
			if (!textFiles.contains(s))textFiles.add(s);
		}
	};

	public void filterAudio(String s) {
		if (s.contains("ogg")
				||s.contains("mp3")
				||s.contains("wav")) {
			if (!audioFiles.contains(s))audioFiles.add(s);
		}
	};

	public void filterVideo(String s) {
		if (s.contains("mp4")
				||s.contains("wmv")
				||s.contains("avi")
				||s.contains("mkv")) {
			if (!videoFiles.contains(s))videoFiles.add(s);
		}
	};

	public void filterOthers(String s) {
		if (!images.contains(s)
				&&!textFiles.contains(s)
				&&!videoFiles.contains(s)
				&&!audioFiles.contains(s)) {
			if (!otherFiles.contains(s))otherFiles.add(s);
		}
	};
	
	public void listen(){
//		applet.fill(255);
		window = Bms.fmenu;
//		if(window.currentf!=null)applet.text(window.currentf, 0, 200);
		if(location==null&&!window.close)window.toggle = true;
//		if(location!=null)
//			applet.println("file input listen location wind close;",window.currentf,location);
//		if(window.toggle&&!window.close)window.displayGrid();
		if(window.getClose()||window.close){
			window.toggle = false;
			window.close = true;
			location = window.currentf;
			if(location==null)location = "";
			PApplet.println("fin Location: " + value);
			if(location!=null) {
				
				if(!folder)loadFile();
				else {
					PApplet.println("Location: " + location);
					String []s = getFolder(location);
				}
			}
			
		}
	};

	public void listen(PVector m){
		boolean k = false;
		//		if(window==null)window = new Window(200,200,200,200,"C:\\Users\\paul goux\\",Bms);
		if(!folder){
			if(Bms.theme.click){
				if(window!=null)window.open = true;
				openWindow = true;
				fileSelect = true;
			}}else {
				if(Bms.theme.click){
					if(window!=null)window.open = true;
					openWindow = true;
					fileSelect = true;
				}}

		if(openWindow&&window.open) {

			window.windowLogic();
		}
		if(openWindow&&!window.open){
			openWindow = false;
			location = window.currentf;
			applet.println("file input listen location m;",value);
		}
//		if(window!=null)window.displayGrid();
		if(fileSelect&&location!=null)PApplet.println("Location: " + location);

	};

	public void listen1(){
		//		Bms.theme.click;
		if(!folder){
			if(click){
				if(window!=null)window.open = true;
				openWindow = true;

			}
		}else {
			if(click){
				if(window!=null)window.open = true;
				openWindow = true;
				values = fileUtils.listNames(value);
			}
		}

		if(openWindow&&window.open) {
			window.windowLogic();
		}
		if(openWindow&&!window.open){
			openWindow = false;
			location = window.link;
		}
		if(window!=null)window.displayGrid();
	};

	public void listen_(){
		if(!folder) {

			//	    	selectInput("Select a file to process:", "fileSelected");
		}
		else {
			//	    	selectInput("Select a file to process:", "folderSelected");
		}
	};

	public void listExt() {

		if(values!=null&&!counted){
			for(int i=0;i<values.length;i++){

				File f = new File(values[i]);

				String type = fileUtils.getFileExtension(f);
				if(!extensions.containsKey(type)){
					ArrayList<Integer> n = new ArrayList<Integer>();
					n.add(i);
					extensions.put(type,n);
				}else extensions.get(type).add(i);
			}
			counted = true;
		}
	};

	public void getTextFiles(){
		
	};

	public void getImageFiles(){


	};

	public void getVideoFiles(){

		
	};

	public void getAudioFiles(){


	};

	public void getAudioFile(){


	};

	public void getImageFile(){

		img = applet.loadImage(location);
		applet.println("finput getimg",location,img);
	};
	
	public void loadImages() {
		
		
	};

	public void getDocument(){


	};

	public void getTextFile(){

		values = applet.loadStrings(location);
	};

	public void getVideoFile(){


	};

	public void loadFile() {
		if(file)
			if(!fileOpened) {
				String ext = getExt();
				if(ext!=null) {
					if(ext.contains("jpg")||ext.contains("jpeg")||ext.contains("JPG")||ext.contains("JPEG"))getImageFile();
					if(ext.contains("mp4")||ext.contains("mkv")||ext.contains("avi")||ext.contains("wmv"))getVideoFile();
					if(ext.contains("mp3")||ext.contains("ogg")||ext.contains("wma"))getAudioFile();
					if(ext.contains("txt")||ext.contains("csv"))getTextFile();
					if(ext.contains("doc")||ext.contains("DOC")||ext.contains("DOCX")||ext.contains("docx"))getDocument();
					applet.println("finput load",location,img);
				}else applet.println("finput load is not a file",location);
				fileOpened = true;
			}
		if(folder&&!folderOpened) {
			getFolder(value);
			folderOpened = true;
		}
	};

	//	public String []getFolder(String location) {
	//
	//		String []names = fileUtils.listNames(location);
	//
	//		if(names!=null&&!counted){
	//			for(int i=0;i<names.length;i++){
	//
	//				File f = new File(names[i]);
	//
	//				String type = fileUtils.getFileExtension(f);
	//				String[] m = PApplet.match(names[i], "ubyte");
	//
	//				if(m==null){
	//					if(!extensions.containsKey(type)){
	//						ArrayList<Integer> n = new ArrayList<Integer>();
	//						n.add(i);
	//						extensions.put(type,n);
	//						fileNames.add(names[i]);
	//					}else{
	//						extensions.get(type).add(i);
	//						fileNames.add(names[i]);
	//					}}
	//				else{
	//					if(!extensions.containsKey("ubyte")){
	//						ArrayList<Integer> n = new ArrayList<Integer>();
	//						n.add(i);
	//						extensions.put("ubyte",n);
	//						fileNames.add(names[i]);
	//					}else{
	//						extensions.get(type).add(i);
	//						fileNames.add(names[i]);
	//					}}}}
	//
	//		String []s = new String[fileNames.size()];
	//
	//		for(int i=0;i<fileNames.size();i++){
	//			s[i] = fileNames.get(i);
	//		}
	//		return s;
	//	};
	
	public String []getFile() {
		return values = applet.loadStrings(location);
	};
	
	public String []loadStrings() {
		return values = applet.loadStrings(location);
	};
	
	public String []getFile(String s) {
		setLocation(s);
		return values = applet.loadStrings(location);
	};
	
	public String []loadFile(String s) {
		setLocation(s);
		return values = applet.loadStrings(location);
	};
	
	public String []loadSkethFile(String s) {
		setSketchLocation(s);
		return values = applet.loadStrings(location);
	};
	
	public String []getSketchFile(String s) {
		setSketchLocation(s);
		return values = applet.loadStrings(location);
	};
	
	public String []loadSketchFile(String s) {
		setSketchLocation(s);
		return values = applet.loadStrings(location);
	};

	public String []getFolder(String location) {

		String []names = fileUtils.listNames(value);
		if(names!=null&&!counted){
			for(int i=0;i<names.length;i++){

				tempFile = new File(names[i]);

				String ext = fileUtils.getFileExtension(tempFile);
				String[] m = PApplet.match(names[i], "ubyte");

				if(m==null){
					if(ext!=null) {
						if(ext.contains("jpg")||ext.contains("jpeg")
								||ext.contains("JPG")||ext.contains("JPEG"))
							imageFiles.add(location+"//"+names[i]);
						if(ext.contains("mp4")||ext.contains("avi")
								||ext.contains("wmv")||ext.contains("mkv"))
							videoFiles.add(location+"//"+names[i]);
						if(ext.contains("mp3")||ext.contains("wmv")
								||ext.contains("ogg"))
							audioFiles.add(location+"//"+names[i]);
						if(ext=="txt"||ext=="csv")getTextFile();
						if(ext.contains("doc")||ext.contains("Doc")
								||ext.contains("docx")||ext.contains("DOCX"))
							officeDocs.add(location+"//"+names[i]);
						fileNames.add(location+"//"+names[i]);
					}}
				else{
					if(!extensions.containsKey("ubyte")){
						ArrayList<Integer> n = new ArrayList<Integer>();
						n.add(i);
						extensions.put("ubyte",n);
						fileNames.add(names[i]);
					}else{
						extensions.get(ext).add(i);
						fileNames.add(names[i]);
					}}}}

		String []s = new String[fileNames.size()];

		for(int i=0;i<fileNames.size();i++){
			s[i] = fileNames.get(i);
		}
		applet.println("finput getFolder",fileNames.size());
		return s;
	};

	public  boolean pos(PVector mouse){
		return mouse.x>x&&mouse.x<x+w&&mouse.y>y&&mouse.y<y+h;
	};

	public  boolean pos(){
		return applet.mouseX>x&&applet.mouseX<x+w&&applet.mouseY>y&&applet.mouseY<y+h;
	};

	public void fileSelected(File selection){
		if(selection != null)Bms.File.location = selection.getAbsolutePath();
	};

	public void folderSelected(File selection) {
		if(selection != null){
			Bms.File.location = selection.getAbsolutePath();
			Bms.Location = selection.getAbsolutePath();
		}
	};

	public void selectInput() {

	};

	public void selectFolder() {

	};

	public void getDim() {

	};

	public String getExt() {
		String s = null;
		if(location.contains(".")) {
			ext = location.replace(value.substring(0, value.lastIndexOf('.')),"");
			ext = fileExt.replace(".", "");
			applet.println("finput getext",ext);
			s = ext;
		}
		return s;
	};

	public void dboxLogic() {
		tab t = dbox.main;
		if (applet.mousePressed&&!closeDialog)showDialog = true;

		if (showDialog&&!closeDialog) {

			if(location==null) {
				//				if (Bms.theme.click)applet.println("show Dialogue");
				dbox.draw();
				if (t.toggle(0)&&t.textareas.get(0).tempLine.length()>0) {
					closeDialog = true;
					applet.println("get location",t.textareas.get(0).tempLine);
				}
				if (t.toggle(1)) {
					showDialog = false;
					closeDialog = true;
					applet.println("refused file request");
				}
			}
		}
	};
	
	public void reset() {
		fileOpened = false;
		folderOpened = false;
		location = null;
		tempFile = null;
//		ext = null;
	}
};
