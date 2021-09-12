package windowsGui;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;

import processing.core.PApplet;
import processing.core.PImage;

public class ScreenShot {
	BMS Bms;
	PApplet p;
	public int w,h;
	public PImage screenshot,img;
	public Rectangle dimension;
	public Robot robot;
	public ScreenShot(BMS b) {
		Bms = b;
		p = b.applet;
		screenshot = p.createImage(p.displayWidth, p.displayHeight, p.ARGB);
		dimension  = new Rectangle(p.displayWidth, p.displayHeight);

		try {
			robot = new Robot();
		}
		catch (AWTException cause) {
			p.println(cause);
			p.exit();
		}
	}

	public ScreenShot(BMS b, int w, int h) {
		Bms = b;
		p = b.applet;
		this.w = w;
		this.h = h;
		screenshot = p.createImage(w, h, p.ARGB);
		dimension  = new Rectangle(w, h);
		p.println("new screenrecorder");
		try {
			robot = new Robot();
		}
		catch (AWTException cause) {
			p.println(cause);
			p.exit();
		}
	};

	public final PImage getScreenshot() {
		//return new PImage(bot.createScreenCapture(dim));

		robot.createScreenCapture(dimension).getRGB(0, 0
				, dimension.width, dimension.height
				, screenshot.pixels, 0, dimension.width);

		screenshot.updatePixels();

		return screenshot;
	};
};
