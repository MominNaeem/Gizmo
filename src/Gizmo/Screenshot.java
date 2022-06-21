package Gizmo;

import java.awt.Point;
import javax.swing.JFrame;

/*
 * Author: Momin Naeem
 * Date: Jan 11, 2019
 * Course: ICS4U1
 * Topic: Gizmo
 * Title: Screenshot
 * Description: This class contains the Screenshot part of the program, where it locates the 
 * frame, height and width where it'll take the screenshot.
 */

public class Screenshot {
	// Point Location, height and width
	JFrame frame;

	public Screenshot(JFrame Menu) {

		frame = Menu;
	}

	// Returns the location of the JFrame
	public Point getLocation() {

		Point location = frame.getLocation();
		return location;
	}

	// Returns the height of the JFrame
	public int getHeight() {

		int height = frame.getHeight();
		return height;
	}

	// Returns the width of the JFrame
	public int getWidth() {

		int width = frame.getWidth();
		return width;
	}

	// toString Method
	/*
	 * @Override public String toString(){ String output = "Height: " + height +
	 * "Width: " + width + "Location: " + location; return output; }
	 */
}
