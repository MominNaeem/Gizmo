package Gizmo;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import javax.swing.JComponent;

/*
 * Author: Momin Naeem
 * Date: Jan 10, 2019
 * Course: ICS4U1
 * Topic: Gizmo
 * Title: Chip
 * Description: This class contains the abstract class of all the chips which contains 
 * information if the chip is powered or grounded or also what state its in.
 */

public abstract class Chip extends JComponent {

	// Fields
	Point powerPin;
	Point groundPin;

	// Constructor
	public Chip(int x, int y) {

		powerPin = new Point(x, y);
		groundPin = new Point(x + (6 * 24), y + 20);

	}

	// Constructor
	public Chip() {

	}

	// Paint components
	@Override
	public void paintComponents(Graphics g) {

		super.paintComponent(g);
		paint(g);

	}

	// Returns the x position of the top left pin of the chip
	abstract public int getPoisiton();

	// Sets the chip to powered, state the state of the power pin(on of off)
	abstract public void setPowered(boolean state);

	// Sets the chip to grounded, state grounded or not
	abstract public void setGrounded(boolean state);

	// Sets the state of on of the input chips
	// State the state of the input chip, on or off
	// The position in the input chip array of the pin which is being powered
	abstract public void setInputPinState(boolean state, int x, int y);

	// Returns the output of the logical chip
	// pinNum the output pin's number
	abstract public boolean getOutput(int pinNum);

	// Whether the chip is powered or not
	abstract public boolean isPowered();

	// Returns whether the chip is grounded or not
	abstract public boolean isGrounded();

}