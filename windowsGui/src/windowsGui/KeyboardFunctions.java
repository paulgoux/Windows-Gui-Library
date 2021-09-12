package windowsGui;

import processing.core.PApplet;
import processing.event.KeyEvent;

public class KeyboardFunctions {
	PApplet p;

	public KeyboardFunctions() {

	};

	public void init(PApplet p) {
		this.p = p;
		p.registerMethod("keyEvent", this);  
	};
	
	public void register(TextArea t) {
		this.p = t.applet;
		p.registerMethod("keyEvent", this);  
	};

	public void destroy(){
		//destroys the registermethod or something like that 
	};

	public void keyEvent(final KeyEvent evt) {
		switch(evt.getAction()) {
		case KeyEvent.PRESS:
			keyPressed();
//			p.println(p.keyCode);
			//getKeyCode();
			break;
		case KeyEvent.RELEASE:
//			keyReleased();
			//getKeyCode();
			break;
		}
	};

	public void keyPressed() {
		//this.key1 = key;
		//this.keyCode1 = keyCode;
	};

	public void keyReleased() {
	};
};
