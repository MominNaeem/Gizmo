package Gizmo;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;

/*
 * Author: Momin Naeem
 * Date: Jan 10, 2019
 * Course: ICS4U1
 * Topic: Gizmo
 * Title: NOTChip
 * Description: This class is one of the gate chips class where it points out its properties 
 * such as its inputs and outputs as well as if its powered or grounded. This class also contains 
 * its image file and graphics.
 */

public class NOTChip extends Chip {

	// Fields
	boolean input[] = new boolean[6];
	boolean output[] = new boolean[6];
	boolean isPowered;
	boolean isGrounded;
	Image img;
	boolean pbb;

	// Primary Constructor
	public NOTChip(int x, int y) {

		super(x, y);
		// Loads the chips image
		try {

			// InputStream is = NOTChip.class.getResourceAsStream("Images/Not.png");
			img = ImageIO.read(new FileInputStream("Images/Not.png"));
		} catch (IOException e) {

			System.out.println(e);
		}
		// Sets the JComponent's size
		setSize(new Dimension(img.getWidth(null) - 20, img.getHeight(null)));
		// Sets variables to their basic state
		for (int i = 0; i < 6; i++) {

			input[i] = false;
			output[i] = false;
		}
		isPowered = false;
		isGrounded = false;
	}

	// Secondary Constructor, used in the Schematics screen
	public NOTChip(boolean p) {

		super();
		setSize(186, 75);
		pbb = p;
	}

	@Override
	public void paint(Graphics g) {

		Graphics2D g2d = (Graphics2D) (g);
		if (pbb == false) {

			// Draws the chips image
			g2d.drawImage(img, -10, 0, null);
		} else {
			// Draws a white square with the chip's name in the middle
			g2d.setStroke(new BasicStroke(2));
			g2d.setColor(Color.white);
			g2d.drawRect(0, 0, 24 * 7 + 3, 73);
			g2d.drawString("NOT", 150 / 2, 76 / 2);
		}
	}

	// Returns the x position of the logical chip
	@Override
	public int getPoisiton() {

		return (int) powerPin.getX();
	}

	// Resets the chip to a state where all input, output, grounded and powered are
	// false
	public void resetState() {

		for (int i = 0; i < 6; i++) {

			input[i] = false;
			output[i] = false;
		}
		isPowered = false;
		isGrounded = false;
	}

	@Override
	public void setPowered(boolean state) {
		isPowered = state;
	}

	@Override
	public void setGrounded(boolean state) {
		isGrounded = state;
	}

	@Override
	public void setInputPinState(boolean state, int x, int y) {
	}

	// Sets the state of one of the input pins on the NOT Chip
	public void setNOTInputPinState(boolean state, int x) {
		input[x] = state;
	}

	@Override
	public boolean getOutput(int pinNum) {
		return !input[pinNum];
	}

	@Override
	public boolean isPowered() {
		return isPowered;
	}

	@Override
	public boolean isGrounded() {
		return isGrounded;
	}

}