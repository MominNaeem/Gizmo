package Gizmo;

import java.awt.Point;
import javax.swing.JOptionPane;

/*
 * Author: Momin Naeem
 * Date: Jan 13, 2019
 * Course: ICS4U1
 * Topic: Gizmo
 * Title: Snapper
 * Description: This class is here to help the user to click on the breadboard points easily. 
 * With each point on the breadboard, it will snap to a point if the user doesn't exactly click on 
 * that pixel. With the snap, it will be around the pin, to make it feel like the user clicked on 
 * that pin instead of another one by mistake. All it does is take points around the pins and make 
 * it that designated point.
 */

public class Snapper {

	// Fields
	public boolean pins[][];
	public boolean chipOutputPins[][];

	// Primary Constructor
	public Snapper() {

		pins = new boolean[1000][600];
		chipOutputPins = new boolean[1000][600];
		for (int i = 25; i <= 485; i += 24) {

			for (int j = 38; j <= 783; j += 24) {

				pins[j][i] = false;
			}
		}
		for (int i = 25; i <= 485; i += 24) {

			for (int j = 38; j <= 783; j += 24) {

				chipOutputPins[j][i] = false;
			}
		}
	}

	// This method will returns an X position on the breadboard which is the closest
	// to the actual point on the breadboard
	public int snapToX(int x) {

		int helper = x;
		// Checks if the user clicked outside the bounds of the breadboard, and returns
		// the closest point
		if (helper > 783) {

			return 783;
		} else if (helper < 38) {

			return 38;
		}
		// Loops through each point to find which one the spot the user clicked on is
		// closest to
		for (int i = 0; i < 32; i++) {

			if (helper <= 50 + (24 * i)) {

				helper = 38 + (24 * i);
				i = 100;
			}
		}
		// Returns the closest point
		return helper;
	}

	// This method is used to wire x locations to their proper spot on the
	// breadboard
	public int wSnapToX(int x, int y) {

		int helper = x;
		int temp = 0;
		// If the user clicked near the top row of points
		if (y < 120) {

			// Checks if the user clicked outside the bounds of the breadboard, and returns
			// the closest point
			if (helper < 87) {

				return 86;
			} else if (helper > 757) {

				return 758;
			}
			for (int i = 0; i < 29; i++) {

				if (helper <= 99 + (24 * i)) {

					helper = 86 + (24 * i);
					i = 100;
					temp = i;
				}
			}
			// If the user clicked near the bottom row of points
		} else if (y > 420) {

			// Checks if the user clicked outside the bounds of the breadboard, and returns
			// the closest point
			if (helper < 87) {

				return 86;
			} else if (helper > 757) {

				return 758;
			}
			for (int i = 0; i < 29; i++) {

				if (helper <= 99 + (24 * i)) {

					helper = 86 + (24 * i);
					i = 100;
					temp = 100;
				}
			}
			// If the user clicked near the two middle rows of points
		} else {

			// Checks if the user clicked outside the bounds of the breadboard, and returns
			// the closest point
			if (helper > 783) {

				return 782;
			} else if (helper < 38) {

				return 38;
			}
			for (int i = 0; i < 32; i++) {

				if (helper <= 50 + (24 * i)) {

					helper = 38 + (24 * i);
					i = 100;
				}
			}
		}
		// Since there aren't always points in the breadboard, if the program snapped to
		// somewhere where it thought there was one, it snaps to the closest real point
		if (temp == 100) {

			if (helper == 206) {

				helper -= 24;
			} else if (helper == 350) {

				helper -= 24;
			} else if (helper == 494) {

				helper -= 24;
			} else if (helper == 638) {

				helper -= 24;
			}
		}
		return helper;

	}

	// Used to snap the components to their proper y position
	public int snapToY(int y) {

		int helper = y;
		// If the user clicks to low, it snaps to the lowest point
		if (y > 396) {

			return 288 + (4 * 24);
		}
		// Loops through all the points in the top middle row, to find the point the
		// user clicked nearest to
		if (y < 250) {

			for (int i = 0; i < 5; i++) {

				if (helper <= 133 + (24 * i)) {

					helper = 121 + (24 * i);
					i = 100;
				}
			}
			// Loops through all the points in the bottom middle row to find the point the
			// user clicked nearest to
		} else {

			for (int i = 0; i < 5; i++) {

				if (helper <= 300 + (24 * i)) {

					helper = 289 + (24 * i);
					i = 100;
				}
			}
		}
		// Returns the point
		return helper;
	}

	// This method is used to snap wires to their proper y positions
	public int wSnapToY(int y) {

		int helper = y;
		// Loops through all the points in the top middle row, to find the point the
		// user clicked nearest to
		if (y < 250 && y > 110) {

			if (y > 217) {

				return 217;
			}
			for (int i = 0; i < 5; i++) {

				if (helper <= 133 + (24 * i)) {

					helper = 121 + (24 * i);
					i = 100;
				}
			}
			// Loops through all the points in the bottom middle row to find the point the
			// user clicked nearest to
		} else if (y > 250 && y < 420) {

			for (int i = 0; i < 5; i++) {

				if (y > 387) {
					return 386;
				}
				if (helper <= 300 + (24 * i)) {

					helper = 288 + (24 * i);
					i = 100;
				}
			}
			// If the user clicked near the top row, it finds the proper y position
		} else if (y < 120) {

			if (y < 38) {

				return 26;
			} else {
				return 50;
			}
			// If the user clicked near the bottom row, it finds the proper y position
		} else {

			if (y < 469) {

				return 458;
			} else {
				return 484;
			}
		}
		// Returns the proper y position
		return helper;
	}

	// This method snaps a logical chip to the correct x position
	public int cSnapToX(int x) {

		int helper = x;
		// Checks if the user clicked outside the bounds of the breadboard, and returns
		// the closest point
		if (helper > 783) {

			return 783;
		} else if (helper < 38) {

			return 38;
		}
		// Lops through each point to find which one the spot the user clicked on is
		// closest to
		for (int i = 0; i < 32; i++) {

			if (helper <= 50 + (24 * i)) {

				helper = 38 + (24 * i);
				i = 100;
			}
		}
		// Returns the closest point
		return helper;
	}

	// Returns the proper y position for the chip
	public int cSnapToY() {

		return 217;
	}

	public boolean pinUsed(int x, int y) {

		if (pins[x][y] == false) {

			pins[x][y] = true;
			return false;
		}
		return true;
	}

	public boolean cPinUsed(int x, int y, int ID) {

		// If the chip is too close to the right edge
		if (x > 38 + (24 * 25)) {

			// Error Message
			JOptionPane.showMessageDialog(null, "You cant place a chip here as it will be off the edge");
			return true;
		}
		// If the chip would overlap with another component (on the top middle row)
		for (int i = 0; i < 7; i++) {

			if (pins[x + (24 * i)][217] == true) {

				System.out.println(i + " " + "Hello");

				// Error Message
				JOptionPane.showMessageDialog(null,
						"A pin that the chip would need to connect to is already being used");
				return true;
			}
		}
		// If the chip would overlap with another component (on the bottom middle row)
		for (int i = 0; i < 7; i++) {

			if (pins[x + (24 * i)][288]) {

				System.out.println(i + " " + "Hi");
				// Error Message
				JOptionPane.showMessageDialog(null,
						"A pin that the chip would need to connect to is already being used");
				return true;
			}
		}
		// Sets the pins the chip is using to true (used)
		for (int i = 0; i < 7; i++) {

			pins[x + (24 * i)][288] = true;
		}
		// Sets the pins the chip is using to true (used)
		for (int i = 0; i < 7; i++) {

			pins[x + (24 * i)][217] = true;
		}
		// Checks which pins will be the output pins for the chip
		// ANDCHIP
		if (ID == 3) {
			// Sets the location of the chips output pins
			for (int i = 0; i < 7; i++) {

				chipOutputPins[x + (24 * 2)][288] = true;
				chipOutputPins[x + (24 * 5)][288] = true;
			}
			// Sets the location of the chips output pins
			for (int i = 0; i < 7; i++) {

				chipOutputPins[x + (24 * 3)][217] = true;
				chipOutputPins[x + (24 * 6)][217] = true;
			}

			// NANDCHIP
		} else if (ID == 4) {

			// Sets the location of the chips output pins
			for (int i = 0; i < 7; i++) {

				chipOutputPins[x + (24 * 2)][288] = true;
				chipOutputPins[x + (24 * 5)][288] = true;
			}
			// Sets the location of the chips output pins
			for (int i = 0; i < 7; i++) {

				chipOutputPins[x + (24 * 3)][217] = true;
				chipOutputPins[x + (24 * 6)][217] = true;
			}

			// NORCHIP
		} else if (ID == 5) {

			// Sets the location of the chips output pins
			for (int i = 0; i < 7; i++) {

				chipOutputPins[x + (24 * 2)][288] = true;
				chipOutputPins[x + (24 * 5)][288] = true;
			}
			// Sets the location of the chips output pins
			for (int i = 0; i < 7; i++) {

				chipOutputPins[x + (24 * 3)][217] = true;
				chipOutputPins[x + (24 * 6)][217] = true;
			}

			// NOTCHIP
		} else if (ID == 6) {

			// Sets the location of the chips output pins
			for (int i = 0; i < 7; i++) {

				chipOutputPins[x + (24 * 1)][288] = true;
				chipOutputPins[x + (24 * 3)][288] = true;
				chipOutputPins[x + (24 * 5)][288] = true;
			}
			// Sets the location of the chips output pins
			for (int i = 0; i < 7; i++) {

				chipOutputPins[x + (24 * 2)][217] = true;
				chipOutputPins[x + (24 * 4)][217] = true;
				chipOutputPins[x + (24 * 6)][217] = true;
			}

			// ORCHIP
		} else if (ID == 7) {

			// Sets the location of the chips output pins
			for (int i = 0; i < 7; i++) {

				chipOutputPins[x + (24 * 2)][288] = true;
				chipOutputPins[x + (24 * 5)][288] = true;
			}
			// Sets the location of the chips output pins
			for (int i = 0; i < 7; i++) {

				chipOutputPins[x + (24 * 3)][217] = true;
				chipOutputPins[x + (24 * 6)][217] = true;
			}

			// XORCHIP
		} else if (ID == 8) {

			for (int i = 0; i < 7; i++) {

				chipOutputPins[x + (24 * 2)][288] = true;
				chipOutputPins[x + (24 * 5)][288] = true;
			}
			// Sets the location of the chips output pins
			for (int i = 0; i < 7; i++) {

				chipOutputPins[x + (24 * 3)][217] = true;
				chipOutputPins[x + (24 * 6)][217] = true;
			}
		}
		return false;
	}

	public boolean ledPinUsed(int x, int y) {

		if (pins[x][y] == false && pins[x + 24][y] == false) {

			pins[x][y] = true;
			pins[x + 24][y] = true;
			return false;
		}
		return true;
	}

	public void resetPin(int x, int y) {

		pins[x][y] = false;
	}

	public void resetLEDPin(Point x) {

		pins[(int) x.getX()][(int) x.getY()] = false;
		pins[(int) x.getX() + 24][(int) x.getY()] = false;
	}

	public void resetChipPin(Point x) {

		pins[(int) x.getX()][(int) x.getY()] = false;
		for (int i = 0; i < 7; i++) {

			pins[(int) x.getX() + (24 * i)][(int) x.getY()] = false;
		}
		for (int i = 0; i < 7; i++) {

			pins[(int) x.getX() + (24 * i)][288] = false;
		}
		for (int i = 0; i < 7; i++) {

			chipOutputPins[(int) x.getX() + (24 * i)][(int) x.getY()] = false;
		}
		for (int i = 0; i < 7; i++) {

			chipOutputPins[(int) x.getX() + (24 * i)][288] = false;
		}
	}

	public void resetWirePin(Point one, Point two) {

		pins[(int) one.getX()][(int) one.getY()] = false;
		pins[(int) two.getX()][(int) two.getY()] = false;
	}

	public void clearAllPins() {

		for (int i = 25; i <= 485; i += 24) {

			for (int j = 38; j <= 783; j += 24) {

				pins[j][i] = false;
			}
		}
		for (int i = 0; i < 1000; i++) {

			for (int j = 0; j < 600; j++) {

				chipOutputPins[i][j] = false;
			}
		}
	}

	public boolean connectsInOut(int x1, int y1, int x2, int y2) {

		if (y1 < 250 && y2 < 250) {

			if (chipOutputPins[x1][217] == true) {

				return (x1 - 24) == x2 || (x1 - 48) == x2;
			} else if (chipOutputPins[x2][217] == true) {

				return (x2 - 24) == x1 || (x2 - 48) == x1;
			} else {

				return false;
			}
		} else if (y1 < 250 && y2 > 250) {

			return false;
		} else if (y1 > 250 && y2 < 250) {

			return false;
		} else {
			if (chipOutputPins[x1][288] == true) {

				return (x1 - 24) == x2 || (x1 - 48) == x2;
			} else if (chipOutputPins[x2][288] == true) {
				return (x2 - 24) == x1 || (x2 - 48) == x1;
			} else {

				return false;
			}
		}
	}

	public int resistorSnapToX(int x, int y) {

		int helper = x;
		// If the user clicked near the top row of points
		if (y < 300) {

			// Checks if the user clicked outside the bounds of the breadboard, and returns
			// the closest point
			if (helper < 87) {

				return 86;
			} else if (helper > 757) {

				return 758;
			}
			for (int i = 0; i < 29; i++) {

				if (helper <= 99 + (24 * i)) {

					helper = 86 + (24 * i);
					i = 100;
				}
			}
			// If the user clicked near the bottom row of points
		} else if (y > 300) {

			// Checks if the user clicked outside the bounds of the breadboard, and returns
			// the closest point
			if (helper < 87) {

				return 86;
			} else if (helper > 757) {

				return 758;
			}
			for (int i = 0; i < 29; i++) {

				if (helper <= 99 + (24 * i)) {

					helper = 86 + (24 * i);
					i = 101;
				}
			}
			// If the user clicked near the two middle rows of points
		}
		// Since there aren't always points in the breadboard, if the program snapped to
		// somewhere where it thought there was one, it snaps to the closest real point
		if (helper == 206) {

			helper -= 24;
		} else if (helper == 350) {

			helper -= 24;
		} else if (helper == 494) {

			helper -= 24;
		} else if (helper == 638) {

			helper -= 24;
		}
		return helper;
	}

	public int resistorSnapToY(int y) {

		if (y < 300) {

			return 26;
		} else {

			return 458;
		}
	}

	public int resistorSnapToY2(int x, int y) {

		int helper = y;
		// Loops through all the points in the top middle row, to find the point the
		// user clicked nearest to
		if (y < 110) {

			return 217;
		} else if (y > 420) {

			return 386;
		}
		if (y < 250 && y > 110) {

			for (int i = 0; i < 5; i++) {

				if (helper <= 133 + (24 * i)) {

					helper = 121 + (24 * i);
					i = 100;
				}
			}
			// Loops through all the points in the bottom middle row to find the point the
			// user clicked nearest to
		} else if (y > 250 && y < 420) {

			for (int i = 0; i < 5; i++) {

				if (helper <= 300 + (24 * i)) {

					helper = 288 + (24 * i);
					i = 100;
				}
			}
			// If the user clicked near the top row, it finds the proper y position
		}
		return helper;
	}

	public int resistorSnapToX2(int x) {

		int helper = x;
		if (helper > 783) {

			return 782;
		} else if (helper < 38) {

			return 38;
		}
		for (int i = 0; i < 32; i++) {

			if (helper <= 50 + (24 * i)) {

				helper = 38 + (24 * i);
				i = 100;
			}
		}
		return helper;
	}
}
