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
 * Title: ANDChip
 * Description: This class is one of the gate chips method where it points out its properties 
 * such as its inputs and outputs as well as if its powered or grounded. This class also contains 
 * its image file and graphics.
 */

public class ANDChip extends Chip {

	// Fields for this Class
	boolean input[][] = new boolean[4][2];
	boolean output[] = new boolean[4];
	boolean isPowered;
	boolean isGrounded;
	Image img;
	boolean pbb;

	// Constructor Class
	public ANDChip(int x, int y) {

		super(x, y);
		// Loads in image for the selected chip
		try {
			// InputStream is = ANDChip.class.getResourceAsStream("Images/and_1.png");
			img = ImageIO.read(new FileInputStream("Images/and_1.png"));
		} catch (IOException e) {
			System.out.println(e);
		}

		// Sets size
		setSize(new Dimension(img.getWidth(null) - 20, img.getHeight(null)));
		for (int i = 0; i < 4; i++) {
			output[i] = false;
			for (int k = 0; k < 2; k++) {
				input[i][k] = false;
			}
		}
		// Originally the powered and grounded is false when placed
		isPowered = false;
		isGrounded = false;

	}

	// Constructor Class
	public ANDChip(boolean p) {

		super();
		setSize(186, 75);
		pbb = p;

	}

	// If powered, it will output the state its in
	public boolean outputState(boolean input1, boolean input2) {

		return input1 == input2 == true;

	}

	// Method to paint the area where this component will be placed
	public void paint(Graphics g) {

		Graphics2D g2d = (Graphics2D) (g);
		if (pbb == false) {
			g2d.drawImage(img, -10, 0, null);
		} else {
			g2d.setStroke(new BasicStroke(2));
			g2d.setColor(Color.white);
			g2d.drawRect(0, 0, 24 * 7 + 3, 73);
			g2d.drawString("AND", 150 / 2, 76 / 2);
		}

	}

	// Returns the x position of the chip
	public int getPoisiton() {

		return (int) powerPin.getX();

	}

	// Resets the chip to a state where all input, outputs, ground, and power are
	// false
	public void resetState() {

		for (int i = 0; i < 4; i++) {
			output[i] = false;
			for (int k = 0; k < 2; k++) {
				input[i][k] = false;
			}
		}
		isPowered = false;
		isGrounded = false;

	}

	// Getters and Setters for Powered
	@Override
	public void setPowered(boolean state) {
		isPowered = state;
	}

	@Override
	public void setGrounded(boolean state) {
		isGrounded = state;
	}

	// Input state for this chip
	@Override
	public void setInputPinState(boolean state, int x, int y) {
		input[x][y] = state;
	}

	// Returns true if pins equal to accommodate this chip
	@Override
	public boolean getOutput(int pinNum) {
		if (input[pinNum][0] == true && input[pinNum][1] == true) {
			return true;
		} else {
			return false;
		}
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
