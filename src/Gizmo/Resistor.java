package Gizmo;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import javax.swing.JComponent;

/*
 * Author: Momin Naeem
 * Date: Jan 10, 2019
 * Course: ICS4U1
 * Topic: Gizmo
 * Title: Resistor
 * Description: This class is the resistor class where it points out its properties 
 * such as its inputs and outputs as well as if its powered or grounded. This class also contains 
 * its image file and graphics.
 */

public class Resistor extends JComponent {

	// Fields and Variables
	Point powerPin;
	Point groundPin;
	int x;
	int y;
	boolean pbb = false;

	// Primary Constructor, startx and starty are the first points and x and y are
	// the second points
	public Resistor(int startx, int starty, int endx, int endy) {

		powerPin = new Point(startx, starty);
		groundPin = new Point(endx, endy);
		setSize(1000, 1000);
	}

	// If the resistor is made on breaboard
	public void setPbb() {

		pbb = true;
	}

	// Returns the location of the powered pin
	public Point getPowerPin() {
		return powerPin;
	}

	// Returns the location of the grounded pin
	public Point getGroundPin() {
		return groundPin;
	}

	public void paintComponents(Graphics g) {

		super.paintComponents(g);
		paint(g);
	}

	// Paint Method for Resistor
	public void paint(Graphics g) {

		Graphics2D g2 = (Graphics2D) g;
		if (pbb == false) {

			// Draws a light gray line between the two points of the resistor
			g2.setStroke(new BasicStroke(6));
			g2.setColor(Color.LIGHT_GRAY);
			g2.drawLine((int) powerPin.getX(), (int) powerPin.getY(), ((int) groundPin.getX()),
					((int) groundPin.getY()));
		} else {

			// Draws a light gray line between the two points of the resistor
			g2.setStroke(new BasicStroke(2));
			g2.setColor(Color.LIGHT_GRAY);
			int[] xPoints = { ((int) powerPin.getX()), ((int) groundPin.getX()), ((int) groundPin.getX()),
					((int) powerPin.getX()) };
			int[] yPoints = { ((int) powerPin.getY()), ((int) groundPin.getY()), ((int) groundPin.getY()) + 10,
					((int) powerPin.getY()) + 10 };
			g2.drawPolygon(xPoints, yPoints, 4);

		}

	}

}
