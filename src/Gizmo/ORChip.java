package Gizmo;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/*
 * Author: Momin Naeem
 * Date: Jan 10, 2019
 * Course: ICS4U1
 * Topic: Gizmo
 * Title: ORChip
 * Description: This class is one of the gate chips class where it points out its properties 
 * such as its inputs and outputs as well as if its powered or grounded. This class also contains 
 * its image file and graphics.
 */

public class ORChip extends Chip {

	// Fields
	boolean input[][] = new boolean[4][2];
	boolean output[] = new boolean[4];
	boolean isPowered;
	boolean isGrounded;
	Image img;
	boolean pbb;

	// Primary Constructor
	public ORChip(int x, int y) {

		super(x, y);
		// Loads the chip's image
		try {

			// InputStream is = ORChip.class.getResourceAsStream("Images/Or.png");
			img = ImageIO.read(new FileInputStream("Images/Or.png"));
		} catch (IOException e) {

			System.out.println(e);
		}
		// Sets the size
		setSize(new Dimension(img.getWidth(null) - 20, img.getHeight(null)));
		// Sets the variables to their basic state
		for (int i = 0; i < 4; i++) {

			output[i] = false;
			for (int k = 0; k < 2; k++) {

				input[i][k] = false;
			}
		}
		isPowered = false;
		isGrounded = false;
	}

	// The Secondary Constructor used in the Schematics Screen
	public ORChip(boolean p) {

		super();
		setSize(186, 75);
		pbb = p;
	}

	@Override
	public void paint(Graphics g) {

		Graphics2D g2d = (Graphics2D) (g);
		if (pbb == false) {

			g2d.drawImage(img, -10, 0, null);
		} else {

			g2d.setStroke(new BasicStroke(2));
			g2d.setColor(Color.white);
			g2d.drawRect(0, 0, 24 * 7 + 3, 73);
			g2d.drawString("OR", 150 / 2, 76 / 2);
		}
	}

	// Returns the x position of the chip
	@Override
	public int getPoisiton() {
		return (int) powerPin.getX();
	}

	// Resets the chip to a state where all input, output, grounded and powered are
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
		input[x][y] = state;
	}

	@Override
	public boolean getOutput(int pinNum) {
		return input[pinNum][0] || input[pinNum][1];
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