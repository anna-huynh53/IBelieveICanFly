import java.util.ArrayList;
import java.awt.Image;

public class Animation {
	
	private ArrayList<Image> frames;
	private int currFrame;
	
	private long start;
	private long wait;
	
	private boolean oneCycle;
	
	public Animation() {
		oneCycle = false;
	}
	
	public void setFrames(ArrayList<Image> frames) {
		this.frames = frames;
		currFrame = 0;
		start = System.nanoTime();
		oneCycle = false;
	}
	
	public void update() {
		long elapsed = (System.nanoTime()-start)/1000000;
		if (elapsed > wait) {
			currFrame++;
			start = System.nanoTime();
		}
		if (currFrame == frames.size()) {
			currFrame = 0;
			oneCycle = true;
		}	
	}
	
	public Image getCurrImage() { 
		return frames.get(currFrame); 
	}
	
	public boolean doneOneCycle() { 
		return oneCycle; 
	}
	
	public void increaseCurrFrame() {
		currFrame+=2;
		while (currFrame >= frames.size()) {
			currFrame-=1;
		}
	}
	
	public void setWait(int waitTime) { 
		wait = waitTime;
	}
}