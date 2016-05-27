import java.util.ArrayList;
import java.awt.Image;

/**
 * Entities and items can have animation by setting a new Animation object with
 * an array of images to cycle through at timed intervals.
 *
 */
public class Animation {
	private ArrayList<Image> frames;
	private int currFrame;
	
	private long start;
	private long delay;
	
	public Animation() {
		start = System.nanoTime();
		delay = 0;
	}
	
	/**
	 * Updates the animation by moving to the next frame
	 */
	public void update() {
		long elapsed = (System.nanoTime()-start)/1000000;
		if (elapsed > delay) {
			currFrame++;
			start = System.nanoTime();
		}
		if (currFrame == frames.size()) {
			currFrame = 0;
		}	
	}
	
	/**
	 * Increase the current frame in the animation
	 */
	public void increaseCurrFrame() {
		currFrame+=2;
		while (currFrame >= frames.size()) {
			currFrame-=1;
		}
	}
	
	/**
	 * Gets current frame image in the animation
	 * Used to draw each frame
	 * @return
	 */
	public Image getCurrImage() { 
		return frames.get(currFrame); 
	}
	
	/**
	 * Sets the array of images to be used for the animation
	 * @param array of images
	 */
	public void setFrames(ArrayList<Image> frames) {
		this.frames = frames;
		currFrame = 0;
		start = System.nanoTime();
	}
	
	/**
	 * Sets a delay (in secs) until the next frame is shown 
	 * Used to slow down the animation
	 * @param wait time
	 */
	public void setWait(int delay) { 
		this.delay = delay;
	}
}