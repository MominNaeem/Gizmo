package Gizmo;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

/*
 * Author: Momin Naeem
 * Date: Jan 10, 2019
 * Course: ICS4U1
 * Topic: Gizmo
 * Title: SchematicsMenu
 * Description: This class contains all the GUI and button related parts of the breadboard 
 * screen. It constructs all the buttons on the side with its images and labels. As well as 
 * all their Mouse listener actions if clicked. It also controls what the user wants to put on 
 * the board when clicked on a component.
 */

public class SchematicsMenu extends JFrame {

	// The Fields for the Schematics Screen
	JPanel area;
	Graphics2D drawer;
	// Buttons
	JButton back;
	JButton wireButton;
	JButton resistorButton;
	JButton undoButton;
	JButton deleteButton;
	JButton ledButton;
	JButton and;
	JButton nand;
	JButton nor;
	JButton not;
	JButton or;
	JButton xor;
	Board board;
	Snapper snapper;
	// Boolean Variables for the components
	boolean resistorP = false;
	boolean resistorStep2 = false;
	boolean ledP = false;
	boolean wireP = false;
	boolean wireStep2 = false;
	boolean andP = false;
	boolean nandP = false;
	boolean norP = false;
	boolean notP = false;
	boolean orP = false;
	boolean xorP = false;
	ArrayList<JComponent> componentList = new ArrayList();
	ArrayList<Integer> componentListID = new ArrayList();
	int x1 = 0;
	int y1 = 0;
	int x2 = 0;
	int y2 = 0;
	JButton save;
	Point location;
	int height;
	int width;
	Screenshot screenshot;
	ApplicationTest main;

	// Constructor Method
	public SchematicsMenu(ApplicationTest main) {

		this.main = main;
		// Sets JFrame's size, background color, and exit operation.
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		board = new Board(true);
		setSize(1300, 562);
		setBackground(Color.DARK_GRAY);
		// All necessary components
		and = new JButton("AND");
		nand = new JButton("NAND");
		nor = new JButton("NOR");
		or = new JButton("OR");
		xor = new JButton("XOR");
		not = new JButton("NOT");
		area = new JPanel();
		back = new JButton("Back");
		ledButton = new JButton("LED");
		wireButton = new JButton("Wire");
		resistorButton = new JButton("Resistor");
		undoButton = new JButton("Undo");
		deleteButton = new JButton("Delete");
		save = new JButton("Save");
		snapper = new Snapper();
		// Sets JPanel layout
		area.setLayout(null);
		// Sets the JComponent's locations and size
		ledButton.setBounds(850, 10, 100, 30);
		and.setBounds(850, 80 - 20, 100, 30);
		nand.setBounds(850, 130 - 20, 100, 30);
		or.setBounds(850, 180 - 20, 100, 30);
		nor.setBounds(850, 230 - 20, 100, 30);
		xor.setBounds(850, 280 - 20, 100, 30);
		not.setBounds(850, 330 - 20, 100, 30);
		wireButton.setBounds(850, 380 - 20, 100, 30);
		resistorButton.setBounds(850, 430 - 20, 100, 30);
		undoButton.setBounds(850, 480 - 20, 100, 30);
		deleteButton.setBounds(1000, 160, 100, 30);
		back.setBounds(1000, 80 - 20, 100, 30);
		save.setBounds(1000, 130 - 20, 100, 30);
		// Adds MouseListeners to all comppnents
		undoButton.addMouseListener(undoL);
		deleteButton.addMouseListener(deleteL);
		resistorButton.addMouseListener(resistorL);
		back.addMouseListener(backL);
		wireButton.addMouseListener(wireL);
		ledButton.addMouseListener(ledL);
		board.addMouseListener(boardL);
		and.addMouseListener(andL);
		nand.addMouseListener(nandL);
		nor.addMouseListener(norL);
		or.addMouseListener(orL);
		xor.addMouseListener(xorL);
		not.addMouseListener(notL);
		save.addMouseListener(saveL);
		// Adds components to the JPanel
		setup();
		// Adds JPanel area to this class
		add(area);
	}

	// MouseListener used to check if the user clicked on the back button
	MouseListener backL = new MouseListener() {

		@Override
		public void mouseClicked(MouseEvent e) {

			// If the user clicked on the back button
			if (back.contains(e.getPoint())) {

				// Goes back to the mainmenu
				main.switchFrame(main.MAIN_MENU);
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {
		}

		@Override
		public void mouseReleased(MouseEvent e) {
		}

		@Override
		public void mouseEntered(MouseEvent e) {
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}
	};

	// MouseListener used to check if the user clicked on the LED Button
	MouseListener ledL = new MouseListener() {

		@Override
		public void mouseClicked(MouseEvent e) {
		}

		@Override
		public void mousePressed(MouseEvent e) {

			// If the user pressed the LED Button
			if (ledButton.contains(e.getPoint())) {

				// If placing and LED BOOLEAN is false
				if (ledP == false) {

					// Resets all placing booleans
					resetPlacer();
					// Sets LED place boolean to true
					ledP = true;
				} else {

					// Sets LED place boolean to false
					ledP = false;// sets led place boolean false
				}
			}
		}

		@Override
		public void mouseReleased(MouseEvent e) {
		}

		@Override
		public void mouseEntered(MouseEvent e) {
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}
	};

	// MouseListener used to check if the user clicked on the Board Button
	MouseListener boardL = new MouseListener() {

		@Override
		public void mouseClicked(MouseEvent e) {
		}

		@Override
		public void mousePressed(MouseEvent e) {

			// If the user clicked on the board
			if (board.contains(e.getPoint())) {

				// If the user choose to place an LED before hand
				if (ledP == true) {

					// Snaps to the nearest point that the user clicked by
					x1 = snapper.snapToX((int) e.getPoint().getX());
					y1 = snapper.snapToY((int) e.getPoint().getY());
					// Makes sure you can't place an LED off the edge of the breadboard
					if (x1 >= 783 - 60) {

						// Error Message
						JOptionPane.showMessageDialog(null, "You cannot place an LED here");

						// If the pin is not already being used
					} else if (!snapper.ledPinUsed(x1, y1)) {

						// Adds an LED to the component list
						componentList.add(0, new LED());
						// Adds the LED's ID to the component List Array
						componentListID.add(0, 1);
						// Sets the visual location of the LED
						((LED) componentList.get(0)).setLocation(snapper.snapToX(x1) + 1, snapper.snapToY(y1) - 11);
						// Removes all the components from the JPanel
						area.removeAll();
						repaint();
						// Adds all components to JPanel in proper order
						redrawAll();
						repaint();
					} else {

						// Error Message
						JOptionPane.showMessageDialog(null, "This pin is already being used");
					}

					// If the user chooses to place a wire beforehand
				} else if (wireP == true) {

					// If the user has not chosen the first point of the wire
					if (!wireStep2) {

						x1 = snapper.wSnapToX(e.getX(), e.getY());
						y1 = snapper.wSnapToY(e.getY());
						// If the pin the user chose is not in use
						if (!snapper.pinUsed(x1, y1)) {

							wireStep2 = true;
						} else {

							// Error Message
							JOptionPane.showMessageDialog(null, "This pin is already being used");
						}
					} else {

						x2 = snapper.wSnapToX(e.getX(), e.getY());
						y2 = snapper.wSnapToY(e.getY());

						// Checks if the pin for the second point is beind used and if the wire connects
						// a logical chip's output to its input
						if (!snapper.pinUsed(x2, y2)
								&& (!snapper.connectsInOut(x1, y1, x2, y2) || (new Wire(x1, y1, x2, y2)).isGroundWire()
										|| (new Wire(x1, y1, x2, y2)).isPowerWire())) {

							wireStep2 = false;
							componentListID.add(0, 2);
							componentList.add(0, new Wire(x1, y1, x2, y2));
							((Wire) componentList.get(0)).setLocation(0, 0);
							area.removeAll();
							repaint();
							redrawAll();
							repaint();

						} else {

							// Error Message
							JOptionPane.showMessageDialog(null,
									"This pin is already being used or you tried to connect a chip's out to it's input");
							// Makes the pin originally taken up by the first point of the wire free
							snapper.resetPin(x1, y1);
							// Makes the pin originally taken up by the second point of the wire free
							snapper.resetPin(x2, y2);
							wireStep2 = false;
						}
					}

					// If the user chooses to place an ANDCHIP
				} else if (andP == true) {

					x1 = snapper.cSnapToX((int) e.getPoint().getX());
					y1 = snapper.cSnapToY();

					// Checks that none of the pins the chip would need are in use
					if (!snapper.cPinUsed(x1, y1, 3)) {

						componentList.add(0, new ANDChip(true));
						componentListID.add(0, 3);
						((ANDChip) componentList.get(0)).setLocation(x1 - 10, y1);
						area.removeAll();
						repaint();
						redrawAll();
						repaint();
					}

					// If the user chooses to place an NANDCHIP
				} else if (nandP == true) {

					x1 = snapper.cSnapToX((int) e.getPoint().getX());
					y1 = snapper.cSnapToY();
					if (!snapper.cPinUsed(x1, y1, 4)) {

						componentListID.add(0, 4);
						componentList.add(0, new NANDChip(true));
						((NANDChip) componentList.get(0)).setLocation(x1 - 10, y1);
						area.removeAll();
						repaint();
						redrawAll();
						repaint();
					}

					// If the user chooses to place a NORCHIP
				} else if (norP == true) {

					x1 = snapper.cSnapToX((int) e.getPoint().getX());
					y1 = snapper.cSnapToY();
					if (!snapper.cPinUsed(x1, y1, 5)) {

						componentList.add(0, new NORChip(true));
						componentListID.add(0, 5);
						((NORChip) componentList.get(0)).setLocation(x1 - 10, y1);
						area.removeAll();
						repaint();
						redrawAll();
						repaint();
					}

					// If the user chooses to place a NOTCHIP
				} else if (notP == true) {

					x1 = snapper.cSnapToX((int) e.getPoint().getX());
					y1 = snapper.cSnapToY();
					if (!snapper.cPinUsed(x1, y1, 6)) {

						componentList.add(0, new NOTChip(true));
						componentListID.add(0, 6);
						((NOTChip) componentList.get(0)).setLocation(x1 - 10, y1);
						area.removeAll();
						repaint();
						redrawAll();
						repaint();
					}

					// If the user chooses to place an ORCHIP
				} else if (orP == true) {

					x1 = snapper.cSnapToX((int) e.getPoint().getX());
					y1 = snapper.cSnapToY();
					if (!snapper.cPinUsed(x1, y1, 7)) {

						componentList.add(0, new ORChip(true));
						componentListID.add(0, 7);
						((ORChip) componentList.get(0)).setLocation(x1 - 10, y1);
						area.removeAll();
						repaint();
						redrawAll();
						repaint();
					}

					// If the user chooses to place a XORCHIP
				} else if (xorP == true) {

					x1 = snapper.cSnapToX((int) e.getPoint().getX());
					y1 = snapper.cSnapToY();
					if (!snapper.cPinUsed(x1, y1, 8)) {

						componentList.add(0, new XORChip(true));
						componentListID.add(0, 8);
						((XORChip) componentList.get(0)).setLocation(x1 - 10, y1);
						area.removeAll();
						repaint();
						redrawAll();
						repaint();
					}

					// If the user chooses to place a resistor
				} else if (resistorP == true) {

					if (!resistorStep2) {

						// Snaps to the resistor's first point to a ground pin
						x1 = snapper.resistorSnapToX(e.getX(), e.getY());
						y1 = snapper.resistorSnapToY(e.getY());
						if (!snapper.pinUsed(x1, y1)) {

							resistorStep2 = true;
						} else {

							// Error Message
							JOptionPane.showMessageDialog(null, "This pin is already being used");
						}
					} else {

						// Snaps the resistor's second point to a pin on the middle rows of the
						// breadboard
						x2 = snapper.resistorSnapToX2(e.getX());
						y2 = snapper.resistorSnapToY2(e.getX(), e.getY());
						if (!snapper.pinUsed(x2, y2)) {

							resistorStep2 = false;
							componentList.add(0, new Resistor(x1, y1, x2, y2));
							((Resistor) componentList.get(0)).setPbb();
							componentListID.add(0, 9);
							((Resistor) componentList.get(0)).setLocation(0, 0);
							area.removeAll();
							repaint();
							redrawAll();
							repaint();
						} else {

							// Error Message
							JOptionPane.showMessageDialog(null, "This pin is already being used");
							resistorStep2 = false;
							// Makes the pin originally taken up by the first point of the resistor free
							snapper.resetPin(x1, y1);
							// Makes the pin originally taken up by the second point of the resistor free
							snapper.resetPin(x2, y2);
						}
					}
				}
				// Resets the placing boolean for all components
				resetPlacer();
			}
		}

		@Override
		public void mouseReleased(MouseEvent e) {
		}

		@Override
		public void mouseEntered(MouseEvent e) {
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}

	};

	// MouseListener which checks to see if the user clicked the button that adds a
	// wire
	MouseListener wireL = new MouseListener() {

		@Override
		public void mouseClicked(MouseEvent e) {
		}

		@Override
		public void mousePressed(MouseEvent e) {

			if (wireButton.contains(e.getPoint())) {

				if (wireP == false) {

					resetPlacer();
					wireP = true;
				} else {

					wireP = false;
				}
			}
		}

		@Override
		public void mouseReleased(MouseEvent e) {
		}

		@Override
		public void mouseEntered(MouseEvent e) {
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}
	};

	// MouseListener which checks if the user clicked to add an ANDChip on the board
	MouseListener andL = new MouseListener() {

		@Override
		public void mouseClicked(MouseEvent e) {
		}

		@Override
		public void mousePressed(MouseEvent e) {

			if (and.contains(e.getPoint())) {

				if (andP == false) {

					resetPlacer();
					andP = true;
				} else {

					andP = false;
				}
			}
		}

		@Override
		public void mouseReleased(MouseEvent e) {
		}

		@Override
		public void mouseEntered(MouseEvent e) {
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}

	};

	// MouseListener which checks if the user clicked to add the NAND Chip on the
	// board
	MouseListener nandL = new MouseListener() {

		@Override
		public void mouseClicked(MouseEvent e) {
		}

		@Override
		public void mousePressed(MouseEvent e) {

			if (nand.contains(e.getPoint())) {

				if (nandP == false) {

					resetPlacer();
					nandP = true;
				} else {

					nandP = false;
				}
			}
		}

		@Override
		public void mouseReleased(MouseEvent e) {
		}

		@Override
		public void mouseEntered(MouseEvent e) {
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}

	};

	// MouseListener which checks if the user clicked to add a NORChip on the board
	MouseListener norL = new MouseListener() {

		@Override
		public void mouseClicked(MouseEvent e) {
		}

		@Override
		public void mousePressed(MouseEvent e) {

			if (nor.contains(e.getPoint())) {

				if (norP == false) {

					resetPlacer();
					norP = true;
				} else {

					norP = false;
				}
			}
		}

		@Override
		public void mouseReleased(MouseEvent e) {
		}

		@Override
		public void mouseEntered(MouseEvent e) {
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}

	};

	// MouseListener which checks if the user clicked to add a NOTChip on the board
	MouseListener notL = new MouseListener() {

		@Override
		public void mouseClicked(MouseEvent e) {
		}

		@Override
		public void mousePressed(MouseEvent e) {

			if (not.contains(e.getPoint())) {

				if (notP == false) {

					resetPlacer();
					notP = true;
				} else {

					notP = false;
				}
			}
		}

		@Override
		public void mouseReleased(MouseEvent e) {
		}

		@Override
		public void mouseEntered(MouseEvent e) {
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}

	};

	// MouseListener which checks if the user clicked to add a ORChip on the board
	MouseListener orL = new MouseListener() {

		@Override
		public void mouseClicked(MouseEvent e) {
		}

		@Override
		public void mousePressed(MouseEvent e) {

			if (or.contains(e.getPoint())) {

				if (orP == false) {

					resetPlacer();
					orP = true;
				} else {

					orP = false;
				}
			}
		}

		@Override
		public void mouseReleased(MouseEvent e) {
		}

		@Override
		public void mouseEntered(MouseEvent e) {
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}

	};

	// MouseListener which checks if the user clicked to add a XORChip on the board
	MouseListener xorL = new MouseListener() {

		@Override
		public void mouseClicked(MouseEvent e) {
		}

		@Override
		public void mousePressed(MouseEvent e) {

			if (xor.contains(e.getPoint())) {

				if (xorP == false) {

					resetPlacer();
					xorP = true;
				} else {

					xorP = false;
				}
			}
		}

		@Override
		public void mouseReleased(MouseEvent e) {
		}

		@Override
		public void mouseEntered(MouseEvent e) {
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}

	};

	// Mouse Listener which checks if the user clicked to add a resistor to the
	// board
	MouseListener resistorL = new MouseListener() {

		@Override
		public void mouseClicked(MouseEvent e) {
		}

		@Override
		public void mousePressed(MouseEvent e) {

			if (resistorButton.contains(e.getPoint())) {

				if (resistorP == false) {

					resetPlacer();
					resistorP = true;
				} else {

					resistorP = false;
				}

			}
		}

		@Override
		public void mouseReleased(MouseEvent e) {
		}

		@Override
		public void mouseEntered(MouseEvent e) {
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}
	};

	// Mouse Listener which check if the user clicked the undo button
	MouseListener undoL = new MouseListener() {

		@Override
		public void mouseClicked(MouseEvent e) {
		}

		@Override
		public void mousePressed(MouseEvent e) {

			// If the user pressed the undo button
			if (undoButton.contains(e.getPoint())) {

				// If there are components in the array
				if (componentList.size() > 0) {

					// Resets the pins that the component once used so it can be used again
					// If the component is an LED
					if (componentListID.get(0) == 1) {

						// Resets the pins based on LED properties
						snapper.resetLEDPin(((LED) componentList.get(0)).getPosition());

						// If the components is a wire
					} else if (componentListID.get(0) == 2) {

						// Resets the pins based on a Wire's properties
						snapper.resetWirePin(((Wire) componentList.get(0)).getPowerPin(),
								((Wire) componentList.get(0)).getGroundPin());

						// If the components is a chip
					} else if (componentListID.get(0) >= 3 && componentListID.get(0) <= 8) {

						// Resets the pins based on a logical chip's properies
						snapper.resetChipPin(new Point(((Chip) componentList.get(0)).getPoisiton(), 217));

						// Otherwise the component is a resistor
					} else if (componentListID.get(0) == 9) {

						// Resets the pins based on its properties
						snapper.resetWirePin(((Resistor) componentList.get(0)).getPowerPin(),
								((Resistor) componentList.get(0)).getGroundPin());
					}
					// Removes the latest placed components
					componentList.remove(0);
					// Removes the latest added componentsID
					componentListID.remove(0);
					// Redraws the JPanel
					area.removeAll();
					repaint();
					redrawAll();
					repaint();
				} else {

					System.out.println("Empty array");
				}
			}
		}

		@Override
		public void mouseReleased(MouseEvent e) {
		}

		@Override
		public void mouseEntered(MouseEvent e) {
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}
	};

	// MouseListener which checks if the user clicked on the delete button
	MouseListener deleteL = new MouseListener() {
		@Override
		public void mouseClicked(MouseEvent e) {
		}

		@Override
		public void mousePressed(MouseEvent e) {

			if (deleteButton.contains(e.getPoint())) {

				// Removes all components from the component list
				componentList.removeAll(componentList);
				// Removes component IDs from the array
				componentListID.removeAll(componentListID);
				// Clears all the pins so that the user can reuse it
				snapper.clearAllPins();
				// Redraws the panel
				area.removeAll();
				repaint();
				redrawAll();
				repaint();
			}
		}

		@Override
		public void mouseReleased(MouseEvent e) {
		}

		@Override
		public void mouseEntered(MouseEvent e) {
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}
	};

	private Dimension getDim() {

		if (board == null) {

			return new Dimension(100, 100);
		} else {

			return new Dimension(board.getDim());
		}

	}

	// Adds all the base components to the screen
	private void setup() {

		// Adds all basic original components back into the JPanel
		area.add(back);
		area.add(wireButton);
		area.add(ledButton);
		area.add(board);
		area.add(and);
		area.add(nand);
		area.add(nor);
		area.add(not);
		area.add(or);
		area.add(xor);
		area.add(resistorButton);
		area.add(undoButton);
		area.add(deleteButton);
		area.add(save);

	}

	// Resets all the booleans which tell the program which component to place
	private void resetPlacer() {

		// Set all to false
		nandP = false;
		norP = false;
		notP = false;
		orP = false;
		xorP = false;
		ledP = false;
		andP = false;
		xorP = false;
		if (wireStep2 == false) {

			wireP = false;
		}
		if (resistorStep2 == false) {

			resistorP = false;
		}
	}

	// Adds in all the components to the JPanel in proper order so they are drawn
	// properly
	private void redrawAll() {

		// Adds in all components placed by the user
		for (int i = 0; i < componentList.size(); i++) {

			area.add(componentList.get(i));
		}
		// Adds in the basic components
		setup();
	}

	// MouseListener to check if the user clicked on the save button
	MouseListener saveL = new MouseListener() {

		@Override
		public void mouseClicked(MouseEvent e) {

			if (save.contains(e.getPoint())) {

				try {

					// Saves the picture of the schematics menu as a PNG
					captureScreen();
				} catch (Exception ex) {

					// Error
					System.out.println("ERROR: " + ex.toString());
				}
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {
		}

		@Override
		public void mouseReleased(MouseEvent e) {
		}

		@Override
		public void mouseEntered(MouseEvent e) {
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}
	};

	// This allows the Schematics Screen class to get a screenshot object to take
	// the screenshot with
	public void getScreenshot(Screenshot temp) {

		screenshot = temp;
	}

	// Saves the picture of the screen as a png
	public void captureScreen() throws Exception {

		// Gets the location of the screenshot object
		location = screenshot.getLocation();
		// Gets the size of the SchematicsMenu
		Dimension screensize = new Dimension(screenshot.getWidth(), screenshot.getHeight());
		// Saves the info as a rectangle.
		Rectangle screenRectangle = new Rectangle(location, screensize);
		// Makes a new Robot
		Robot robot = new Robot();
		// Takes a screenshot
		BufferedImage image = robot.createScreenCapture(screenRectangle);
		// Saves the screenshot
		ImageIO.write(image, "png", new File(JOptionPane.showInputDialog("Please name the screenshot")));
	}

}
