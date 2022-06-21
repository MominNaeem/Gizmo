package Gizmo;

import java.awt.BasicStroke;
import java.awt.Point;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.swing.JComponent;

/*
 * Author: Momin Naeem
 * Date: Jan 10, 2019
 * Course: ICS4U1
 * Topic: Gizmo
 * Title: LED
 * Description: This class contains the LEDs properties, images, if its in the breadboard 
 * screen or schematic. This color also contains the different colors of LEDs and if its powered 
 * or not to change the image.
 */

public class LED extends JComponent {

	// Fields for LED
	Point powerPin;
	Point groundPin;
	boolean isPowered;
	Image img;
	int color;
	boolean pbb = false;

	// Primary Constructor, c is the color ID,
	public LED(int c, int x, int y) {

		powerPin = new Point(x, y);
		groundPin = new Point(x + 24, y);
		isPowered = false;
		color = c;
		setColor(color);
		setSize(getDim());
	}

	// The secondary constructor, only used if the LED is going to be placed on the
	// Schematics Board
	public LED() {

		pbb = true;
		setSize(24, 24);
	}

	// This method is used to set the LED's COLOR
	// c = 1 is green, c = 2 is red, c > 2 is blue
	private void setColor(int x) {

		if (isPowered == false) {

			// Sets the led's image as the unpowered led
			try {

				// InputStream is = LED.class.getResourceAsStream("/Images/Led White.png");
				img = ImageIO.read(new FileInputStream("Images/Led White.png"));
			} catch (IOException e) {

				System.out.println(e);
			}
			// Sets the led's image as the green led
		} else {

			if (x == 1) {

				try {

					// InputStream is = LED.class.getResourceAsStream("Images//Led Green.png");
					img = ImageIO.read(new FileInputStream("Images/Led Green.png"));
				} catch (IOException e) {

					System.out.println(e);
				}
				// Sets the led's image as the red led
			} else if (x == 2) {

				try {

					// InputStream is = LED.class.getResourceAsStream("Images//Led Red.png");
					img = ImageIO.read(new FileInputStream("Images/Led Red.png"));
				} catch (IOException e) {

					System.out.println(e);
				}
				// Sets the led's image as the blue led
			} else {

				try {
					// InputStream is = LED.class.getResourceAsStream("Images//Led Blue.png");
					img = ImageIO.read(new FileInputStream("Images/Led Blue.png"));
				} catch (IOException e) {

					System.out.println(e);
				}
			}
		}
	}

	// This method draws the LED frame each time it refreshes
	public void paintComponents(Graphics g) {

		super.paintComponent(g);
		paint(g);
	}

	// Used to draw the LED each time the frame refreshes
	public void paint(Graphics g) {

		Graphics2D g2d = (Graphics2D) (g);
		if (pbb == false) {

			// Draws the led as the image
			g2d.drawImage(img, 0, 0, null);
		} else {

			// Draws a white circle
			g2d.setStroke(new BasicStroke(2));
			g2d.setColor(Color.white);
			g2d.drawOval(0, 0, 24, 24);
		}
	}

	// Returns the dimension of the LED's image
	private Dimension getDim() {

		return new Dimension(img.getWidth(null), img.getHeight(null));
	}

	// Sets the state of the LED
	public void setState(boolean bool) {

		isPowered = bool;
		setColor(color);
	}

	// Returns the position of the LED
	public Point getPosition() {

		return powerPin;
	}

	// Resets the state of the LED
	public void resetState() {

		isPowered = false;
		setColor(color);
	}

}
