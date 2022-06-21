package Gizmo;

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
 * Date: Jan 8, 2019
 * Course: ICS4U1
 * Topic: Gizmo
 * Title: Board
 * Description: This class contains the image of the board where the screen should be a schematic 
 * or a breadboard. It also contains the dimensions of the boards.
 */

public class Board extends JComponent {

	// Image field
	Image img;

	// Board constructor class where boolean if the board should be a ppb board or a
	// regular board
	public Board(boolean pbb) {

		if (pbb == false) {
			// Loads regular breadboard image
			try {
				// InputStream is = getClass().getResourceAsStream("/Images/board.jpg");
				img = ImageIO.read(new FileInputStream("Images/board.jpg"));
			} catch (IOException e) {
				System.out.println(e);
			}
		} else {
			// Loads pcb breadboard image
			try {
				InputStream is = Board.class.getResourceAsStream("Images//board-PCB.jpg");
				img = ImageIO.read(new FileInputStream("Images/board-PCB.jpg"));
			} catch (IOException e) {
				System.out.println(e);
			}
		}
		// Sets size of JComponent
		setSize(getDim());

	}

	// Returns the dimension of the image
	public Dimension getDim() {

		return new Dimension(img.getWidth(null), img.getHeight(null));

	}

	// Paint components
	public void paintComponents(Graphics g) {
		super.paintComponent(g);
		paint(g);

	}

	// Used to draw the LED each time the frame refreshes
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) (g);
		g2d.drawImage(img, 1, 1, null);
	}
}
