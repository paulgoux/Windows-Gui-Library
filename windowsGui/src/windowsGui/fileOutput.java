package windowsGui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

public class fileOutput {
	public BMS Bms;
	public PApplet applet;
	public FileWriter output;
	float x, y, w, h;
	public boolean save, onMouseUp, mdown, debug, append, appendFile, match, 
	append2, overWrite, overWriteOnce = true, writeOnce, writeFile, click, saveImage, 
	getPermission = true, fileExists, reWrite, folderExists, overWriteFirst, overWritelast, 
	writeFirst, folderCreated,appendFolder,getWritePermission,showDialog,closeDialog;
	public int counter = -1, counter2, writeCount, failCount;
	public File file, file2, file3;
	public File[] SDcards ; 
	public String location, filePath, folderPath = "",title1,title2;
	String text = "oioijsofoivnsoindv", absolutePath, ext, fileName, fileContent = "";
	String androidDialogueTitle = "Would you like to overWrite", 
			dialogueB1Title = "", dialogueB2Title = "", dialogueBody;
	int msgId;
	PImage img;
	DialogueBox dbox,dbox1,dbox2,dbox3,dbox4;

	public fileOutput() {
	};

	public fileOutput(BMS bms) {
		Bms = bms;
		applet = bms.applet;
	};

	public fileOutput(boolean a, PApplet applet) {

		this.applet = applet;
		overWrite = true;
		appendFile = true;
	};

	public fileOutput(PApplet app) {
		applet = app;
		//init();
	};
	//currently unused
	public fileOutput(String location, PApplet applet) {

		this.applet = applet;
		//img = applet.get();
		setLocation(location);
		init();
	};

	public fileOutput(PApplet applet, String location) {

		this.applet = applet;
		setLocation(location);
		init();
	};


	public void init() {
		x = 0;
		y = 0;
		w = applet.width;
		h = applet.height;

		String s1 = "Would you like to overWrite "+fileName+"."+ext+"?";
		float dboxWidth = 120;
		float tSize = 20;
		applet.textSize(tSize);
		float dw = applet.textWidth(s1)+100;
		float dboxHeight = 150;
		float dx = applet.width/2-dw/2;
		float dy = applet.height/2-50/2;
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
		dbox1 = new DialogueBox(x,y,w,h,"Dialogue 2",Bms);
		dbox1.add(b1);
		dbox1.add(b2);
		dbox2 = new DialogueBox(x,y,w,h,"Dialogue 3",Bms);
		dbox2.add(b1);
		dbox2.add(b2);
		dbox2.main.add(t1);
	};

	public void loadStrings() {
		loadFile();
	};

	String loadFile() {

		FileInputStream fis = null;
		if (writeFile) {
			try {
				//if(
				fis = new FileInputStream (new File(file3.getAbsolutePath()));


				InputStreamReader isr = new InputStreamReader(fis);
				// READ STRING OF UNKNOWN LENGTH
				StringBuilder sb = new StringBuilder();
				char[] inputBuffer = new char[2048];
				int l;
				// FILL BUFFER WITH DATA
				while ((l = isr.read(inputBuffer)) != -1) {
					sb.append(inputBuffer, 0, l);
//					applet.println("write data", inputBuffer, 0, l);
				}
				// CONVERT BYTES TO STRING
				fileContent = sb.toString();

				fis.close();
			}
			catch (Exception e) {
				applet.println("cannot fetch file", e);
			} 
			finally {
				if (fis != null) {

					fis = null;
				}
			}
		}
		return fileContent;
	};

	public void open() {
	};

	public void close() {
		writeCount++;
		if (writeCount>0)append = true;

		try {
			output.flush();
			output.close();
		}
		catch(IOException e) {
		}
	};

	public void writeLine(String s){
		if (writeFile&&location!=null) {
//			applet.println("writeLine string");
			checkLocation();
			writeLn(s);
			close();
			loadStrings();
		}
	};

	public void writeLine(float s){
		if (writeFile&&location!=null) {
			//applet.println("write");
			writeLn(s);
			close();
			loadStrings();
		}
	};

	public void writeLine(int s){
		if (writeFile&&location!=null) {
			//applet.println("write");
			writeLn(s);
			close();
			loadStrings();
		}
	};

	public void writeLine(String s1, int s){
		if (writeFile&&location!=null) {
			//applet.println("write");
			writeLn(s1,s);
			close();
			loadStrings();
		}
	};

	public void writeLine(String s1, boolean s){
		if (writeFile&&location!=null) {
			//applet.println("write");
			writeLn(s1,s);
			close();
			loadStrings();
		}
	};

	public void writeLine(String s1, float s){
		if (writeFile&&location!=null) {
			//applet.println("write");
			writeLn(s1,s);
			close();
			loadStrings();
		}
	};

	public void writeLine(String s1, String s){
		if (writeFile&&location!=null) {
			//applet.println("write");
			writeLn(s1,s);
			close();
			loadStrings();
		}
	};

	public void writeLine(String[] s){
		if (writeFile&&location!=null) {
			//applet.println("write");
			writeLn(s);
			close();
			loadStrings();
		}
	};

	public void writeLine(float[] s){
		if (writeFile&&location!=null) {
			//applet.println("write");
			writeLn(s);
			close();
			loadStrings();
		}
	};

	public void writeLine(int[] s){
		if (writeFile&&location!=null) {
			//applet.println("write");
			writeLn(s);
			close();
			loadStrings();
		}
	};

	public void write(String s) {
//		applet.println("write s",s);
		if (!overWrite||writeCount==0)checkLocation();
		try {

			output = new FileWriter(file2, append);
		}
		catch(IOException e) {
			applet.println("write s fail",s);
		}
		printWrite(s);
	};

	public void write(float s) {

		if (!overWrite||writeCount==0)checkLocation();
		try {

			output = new FileWriter(file2, append);
		}
		catch(IOException e) {
		}
		printWrite(s);
	};

	public void writeLn(String s) {
		if (!overWrite||writeCount==0)checkLocation();
//		applet.println("writeln", s);
		try {
			output = new FileWriter(file2, append);
		}
		catch(IOException e) {
		}
		printWriteLn(s);
	};

	public void writeLn(String s1,int s) {
		if (!overWrite||writeCount==0)checkLocation();
//		applet.println("writeln", s);
		try {
			output = new FileWriter(file2, append);
		}
		catch(IOException e) {
		}
		printWriteLn(s1,s);
	};

	public void writeLn(String s1,float s) {
		if (!overWrite||writeCount==0)checkLocation();
//		applet.println("writeln", s);
		try {
			output = new FileWriter(file2, append);
		}
		catch(IOException e) {
		}
		printWriteLn(s1,s);
	};

	public void writeLn(String s1,boolean s) {
		if (!overWrite||writeCount==0)checkLocation();
//		applet.println("writeln", s);
		try {
			output = new FileWriter(file2, append);
		}
		catch(IOException e) {
		}
		printWriteLn(s1,s);
	};

	public void writeLn(String s1,String s) {
		if (!overWrite||writeCount==0)checkLocation();
//		applet.println("writeln", s);
		try {
			output = new FileWriter(file2, append);
		}
		catch(IOException e) {
		}
		printWriteLn(s1,s);
	};

	public void writeLn(float s) {
		if (!overWrite)checkLocation();
//		applet.println("writeln", s);
		try {
			output = new FileWriter(file2, append);
		}
		catch(IOException e) {
		}
		printWriteLn(s);
	};

	public void writeLn(int s) {
		if (!overWrite)checkLocation();
//		applet.println("writeln", s);
		try {
			output = new FileWriter(file2, append);
		}
		catch(IOException e) {
		}
		printWriteLn(s);
	};

	public void writeLn(float[] s) {
		if (!overWrite)checkLocation();
//		applet.println("writeln", s);
		try {
			output = new FileWriter(file2, append);
		}
		catch(IOException e) {
		}
		printWriteLn(s);
	};

	public void writeLn(int[] s) {
		if (!overWrite)checkLocation();
//		applet.println("writeln", s);
		try {
			output = new FileWriter(file2, append);
		}
		catch(IOException e) {
		}
		printWriteLn(s);
	};

	public void write(String []s) {
		if (!overWrite)checkLocation();
		try {
			output = new FileWriter(file2, append);
		}
		catch(IOException e) {
		}
		printWrite(s);
	};

	public void write(float []s) {
		if (!overWrite)checkLocation();
		try {
			output = new FileWriter(file2, append);
		}
		catch(IOException e) {
		}
		printWrite(s);
	};

	public void writeLn(String []s) {
		if (!overWrite)checkLocation();
		try {
			output = new FileWriter(file2, append);
		}
		catch(IOException e) {
		}
		printWriteLn(s);
	};

	public void printWrite(String s) {
		if (!overWrite)checkLocation();
		try {
			output.append(s);
		}
		catch(IOException e) {
		}
		catch(NullPointerException e) {
			applet.println("printw s fail", file2, e);
		}
		close();
	};

	public void printWrite(float s) {
		if (!overWrite)checkLocation();
		try {
			output.append(applet.str(s));
		}
		catch(IOException e) {
		}
		catch(NullPointerException e) {
			applet.println("printw s fail", file2, e);
		}
		close();
	};

	public void printWriteLn(String s) {
		if (!overWrite)checkLocation();
		try {
//			applet.println("write", s);
			output.append(s);
			output.append("\n");
		}
		catch(IOException e) {
		}
		catch(NullPointerException e) {
			applet.println("printwln s fail", file2, e);
		}
	};

	public void printWriteLn(float s) {
		if (!overWrite)checkLocation();
		try {
//			applet.println("write", s);
			output.append(applet.str(s));
			output.append("\n");
		}
		catch(IOException e) {
		}
		catch(NullPointerException e) {
			applet.println("printwln s fail", file2, e);
		}
	};

	public void printWriteLn(int s) {
		if (!overWrite)checkLocation();
		try {
			//	      applet.println("write", s);
			output.append(applet.str(s));
			output.append("\n");
		}
		catch(IOException e) {
		}
		catch(NullPointerException e) {
			applet.println("printwln s fail", file2, e);
		}
	};

	public void printWriteLn(String s1, int s) {
		if (!overWrite)checkLocation();
		try {
			//	      applet.println("write", s);
			output.append(s1+",");
			output.append(applet.str(s));
			output.append("\n");
		}
		catch(IOException e) {
		}
		catch(NullPointerException e) {
			applet.println("printwln s fail", file2, e);
		}
	};

	public void printWriteLn(String s1, float s) {
		if (!overWrite)checkLocation();
		try {
			//	      applet.println("write", s);
			output.append(s1+",");
			output.append(applet.str(s));
			output.append("\n");
		}
		catch(IOException e) {
		}
		catch(NullPointerException e) {
			applet.println("printwln s fail", file2, e);
		}
	};

	public void printWriteLn(String s1, boolean s) {
		if (!overWrite)checkLocation();
		try {
			//	      applet.println("write", s);
			output.append(s1+",");
			output.append(applet.str(s));
			output.append("\n");
		}
		catch(IOException e) {
		}
		catch(NullPointerException e) {
			applet.println("printwln s fail", file2, e);
		}
	};

	public void printWriteLn(String s1, String s) {
		if (!overWrite)checkLocation();
		try {
			//	      applet.println("write", s);
			output.append(s1+",");
			output.append(s);
			output.append("\n");
		}
		catch(IOException e) {
		}
		catch(NullPointerException e) {
			applet.println("printwln s fail", file2, e);
		}
	};

	public void printWriteLn(int[] s) {
		if (!overWrite)checkLocation();
		try {
			for (int i=0; i<s.length; i++) {
				//	        applet.println("write", s[i]);
				output.append(applet.str(s[i]));
				output.append("\n");
			} 
		}
		catch(IOException e) {
		}
		catch(NullPointerException e) {
			applet.println("printwln s fail", file2, e);
		}
	};

	public void printWrite(String []s) {
		if (!overWrite)checkLocation();
		try {
			for (int i=0; i<s.length; i++) {
				output.append(s[i]);
			}
		}
		catch(IOException e) {
		}
		catch(NullPointerException e) {
			applet.println("printwln s fail", file2, e);
		}
	};

	public void printWrite(float []s) {
		if (!overWrite)checkLocation();
		try {
			for (int i=0; i<s.length; i++) {
				output.append(applet.str(s[i]));
			}
		}
		catch(IOException e) {
		}
		catch(NullPointerException e) {
			applet.println("printwln s fail", file2, e);
		}
	};

	public void printWriteLn(String []s) {
		if (!overWrite)checkLocation();
		try {
			for (int i=0; i<s.length; i++) {
				output.append(s[i]);
				output.append("\n");
			}
		}
		catch(IOException e) {
		}
		catch(NullPointerException e) {
			applet.println("printwln s fail", file2, e);
		}
	};

	public void printWriteLn(float []s) {
		if (!overWrite)checkLocation();
		try {
			for (int i=0; i<s.length; i++) {
				output.append(applet.str(s[i]));
				output.append("\n");
			}
		}
		catch(IOException e) {
		}
		catch(NullPointerException e) {
			applet.println("printwln s fail", file2, e);
		}
	};


	File findFolder() {
		String s1 = "Would you like to overWrite ";
		while (!folderExists) {
			try { 
				file = new File(absolutePath + "\\" + folderPath);
//				PApplet.println("checking folder", file);
				if (!file.exists()) {
					file.mkdirs();
					folderCreated = true;
					s1 +=fileName+"."+ext;
					file2 = new File(file, "\\"+fileName + "." + ext);
					androidDialogueTitle = file2.getAbsolutePath();
				} else {
					folderExists = true;
//					PApplet.println("folder Exists");
				}
			} 
			catch (Exception e) { 
				failCount++;
				if (failCount<5)PApplet.println("Error while creating folder: " + absolutePath, folderPath);
			}
		}
		return file;
	};

	public void findFirstIndex() {
		if (writeCount==0||overWrite) {
			try { 
				String s1 = fileName;
				if (overWrite)s1 = fileName;
				else s1 +=counter;

				file2 = new File(absolutePath+"\\"+folderPath+"\\"+s1+"."+ext);
				androidDialogueTitle = file2.getAbsolutePath();

				if (overWrite) {
//					PApplet.println("overWriting file", file2);
					fileExists = true;

					if (writeFile||overWrite) {
						file3 = file2;
						if (!saveImage)output = new FileWriter(file3, true);
						if (writeCount==0) {
							writeFirst = true;
							if (!saveImage)output = new FileWriter(file3, append);
						}
						writeCount++;
					}
				} else {
				}
			}
			catch (Exception e) { 

				failCount++;
				if (failCount<5)PApplet.println("Error while saving first file: " + e);
			}
		}
	};

	public void findLastIndex() {
		if (counter==-1)counter = 0;
//		PApplet.println("checking last file", file2);
		while (!fileExists) {

			try { 
				String s1 = absolutePath+"\\"+folderPath+"\\"+fileName+(counter)+"."+ext;
				String s2 = "Would you like to create File ";
				String s3 = absolutePath+"\\"+folderPath+"\\"+fileName+"."+ext;
				file2 = new File(s1);
				//PApplet.println("checking last file", file2);
				if (!writeFirst&&overWrite)androidDialogueTitle = s2+fileName+"."+ext;
				else androidDialogueTitle = s2+fileName+counter+"."+ext;
				if (file2.exists()) { 
					counter++;
				} else {
					//PApplet.println("checking last file ", file2);
					file3 = new File(s3);

					if (writeFile) {
						fileExists = true;
						file3 = file2;
						if (!saveImage)output = new FileWriter(file3, true);
						writeCount ++;
					} else file2 = new File(s3);
					break;
				}
			}
			catch (Exception e) { 
				failCount++;
				if (failCount<5)PApplet.println("Error while saving last file: " + e);
			}
		}
	};

	public void findFile() {

		findFirstIndex();
		findLastIndex();
	};

	public void checkLocation() {
//		PApplet.println("find folder");
		findFolder();
//		PApplet.println("find file");
		findFile();
	};

	public void logic() {
		if (applet.mousePressed&&!mdown) {
			img = applet.get();
			mdown = true;
		}
		if (!applet.mousePressed)mdown = false;
	};

	public void saveImage() {
		if (writeFile) {
			logic();
//			applet.println("saveImage", file3, fileName, counter, ext);
			String s1 = file3.getAbsolutePath();
			//if(!overWrite&&counter==0
//			applet.println(s1);
			//if(s1!=null){
			img.save(s1);
			if (!overWrite)counter++;
			if (!overWrite)file3 = new File(file+"\\"+fileName+counter+"."+ext);
			//}
		}
	};

	public void listen() {
		//if(writeFile&&dbox.main.getButton(0).click)checkLocation();
	};

	public void readContents() {
		if (fileContent!=null) {
			applet.fill(0);
			applet.text(counter, 20, 10);
			applet.text(fileContent, 20, 20);
		} else {
			applet.fill(0);
			applet.text("no file", 20, 20);
		}
	};


	public void setLocation(String s) {
		//absolutePath = "C:\\Users\\paul goux\\Documents";
		absolutePath = applet.sketchPath("");
		location = s;
		String s1 = "";
		if(s.contains("\\"))s1 = s.substring(0, s.lastIndexOf("\\"));
		folderPath = s1;
		if(folderPath==null)folderPath = "";
		fileName = s.replace(folderPath+"\\", "");
		getExt(fileName);
		PApplet.println("Fname", folderPath);
		PApplet.println("fileName", fileName);
		PApplet.println("counter", counter);
		PApplet.println("ext", ext);
//		checkLocation();
//		init();
	};
	

	public void dboxLogic() {
		tab t = dbox.main;
		tab t1 = dbox1.main;
		tab t2 = dbox2.main;
		if (applet.mousePressed&&!closeDialog)showDialog = true;
		if (getWritePermission)showDialog = false;

		if (showDialog&&!closeDialog) {
				      
			if(location==null) {
//				if (Bms.theme.click)applet.println("show Dialogue");
				dbox2.draw();
				if (t2.toggle(0)&&t2.textareas.get(0).tempLine.length()>0) {
//					closeDialog = true;
					setLocation(t2.textareas.get(0).tempLine);
//					applet.println("get location",t2.textareas.get(0).tempLine);
				}
				if (t2.toggle(1)) {
					showDialog = false;
					closeDialog = true;
//					applet.println("refused file request");
				}
			}else {
				if (!folderExists||!folderExists&&!fileExists) {
					//	        if (applet.click)applet.println("no folder");

					if (dbox!=null) {
						//if(!folderCreated)t.title.label = title1 +" " + fileName +"."+ext;
						//else 
						dbox.draw();
						t.title.label = title1 +" " + fileName +"."+ext;
						if (t.toggle(0, this, "getWritePermission")) {
							writeFile = true;
							overWrite = true;
							getWritePermission = true;
//							applet.println("folder found new file yes");
						}
						if (t.toggle(1, this, "getWritePermission")) {
							getWritePermission = true;
//							applet.println("folder found new file no");
						}
					}
				} else {
					//	        if (applet.click)applet.println("file found", fileName +"."+ext);
					//androidDialogueTitle = file2.getAbsolutePath();
					//String s1 = file.getAbsolutePath();
					dbox1.draw();
					if (dbox1!=null) {
						if (fileExists)t1.title.label = title2 +" " + fileName +"."+ext;
						else t1.title.label = title1 +" " + fileName +"."+ext;
						if (t1.toggle(0, this, "getWritePermission")) {
							writeFile = true;
							overWrite = true;
							getWritePermission = true;
//							applet.println("folder created new file yes", file2);
						}
						if (t1.toggle(1, this, "getWritePermission")) {
							if (fileExists)writeFile = true;

							getWritePermission = true;
//							applet.println("folder created new file no");
						}
					}
				}
			}
		}
	};

	public void getExt(String location) {

		int count = 0;
		fileName = location.substring(0, location.indexOf("."));
		ext = location.replace(fileName, "");
		ext = ext.replace(".", "");
		ext = ext.replace(fileName, "");
	};


	public boolean click() {
		boolean k = false;
		if (pos()&&applet.mousePressed&&!click) {
			click = true;
			k = false;
		} else if (click&&!applet.mousePressed) {
			k = true;
			click = false;
		}
		return k;
	};

	public  boolean pos(PVector mouse) {
		return mouse.x>x&&mouse.x<x+w&&mouse.y>y&&mouse.y<y+h;
	};

	public  boolean pos() {
		return applet.mouseX>x&&applet.mouseX<x+w&&applet.mouseY>y&&applet.mouseY<y+h;
	};
};
