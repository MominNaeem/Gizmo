package Gizmo;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import javax.swing.JComponent;

/*
 * Author: Momin Naeem
 * Date: Jan 9, 2019
 * Course: ICS4U1
 * Topic: Gizmo
 * Title: Wire
 * Description: This class is the wire class where it points out its properties 
 * such as its inputs and outputs as well as if its powered or grounded. This class also contains 
 * its image file and graphics. It also states the start wire and the end wire to help with the 
 * circuit pathing when it runs.
 */

public class Wire extends JComponent {
	// Fields
	Point powerPin;
	Point groundPin;
	int x;
	boolean state;
	boolean groundWire;
	boolean powerWire;
	int y;

	// Primary Constructor where startx and starty are the first points and x and y
	// are the second points
	public Wire(int startx, int starty, int endx, int endy) {

		// If the wire is connected to the grounded rows
		if (starty == 26 || starty == 458 || endy == 26 || endy == 458) {

			// Sets the wire to be a grounding wire
			groundWire = true;
			// If the first point is connected to the board
			if (starty == 26 || starty == 458) {

				powerPin = new Point(endx, endy);
				// The grounded coordinates are set to the variable groundPin
				groundPin = new Point(startx, starty);
			} else {

				powerPin = new Point(startx, starty);
				// The ground coordinates are set to the variable groundPin
				groundPin = new Point(endx, endy);
			}
			// If the wire is connected to the powered rows
		} else if (starty == 26 + 24 || starty == 458 + 24 || endy == 26 + 24 || endy == 458 + 24) {

			// Sets the wire to be a power wire
			powerWire = true;

			// If the first point is connected to power
			if (starty == 26 + 24 || starty == 458 + 24) {

				// The powered coordinates are set to variable powerPin
				powerPin = new Point(startx, starty);
				groundPin = new Point(endx, endy);
			} else {

				groundPin = new Point(startx, starty);
				// The powered coordinates are set to variable powerPin
				powerPin = new Point(endx, endy);
			}
			// Otherwise its a connecting wire
		} else {

			groundWire = false;
			powerWire = false;
			powerPin = new Point(startx, starty);
			groundPin = new Point(endx, endy);
			// The wire is not powered
			state = false;
		}
		setSize(1000, 1000);
	}

	// Returns whether or not the wire is connected to power
	public boolean isPowerWire() {
		return powerWire;
	}

	// Returns whether or not this wire is connected to ground
	public boolean isGroundWire() {
		return groundWire;
	}

	// Resets the state of the wire
	public void resetState() {
		state = false;
	}

	// Sets the state the wire is in
	public void setState(boolean state) {
		this.state = state;
	}

	// Returns the state of wire
	public boolean isPowered() {
		return state;
	}

	// Returns the location of the powered pin
	public Point getPowerPin() {
		return powerPin;
	}

	// Returns position of the grounded pin
	public Point getGroundPin() {
		return groundPin;
	}

	public void paintComponents(Graphics g) {

		super.paintComponents(g);
		paint(g);
	}

	public void paint(Graphics g) {

		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(6));
		g2.setColor(Color.white);
		g2.drawLine((int) powerPin.getX(), (int) powerPin.getY(), ((int) groundPin.getX()), ((int) groundPin.getY()));
	}
}
